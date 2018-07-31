package com.cct.trn.web.interceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import util.database.CCTConnection;
import util.database.CCTConnectionUtil;
import util.log.LogUtil;
import util.web.SessionUtil;

import com.cct.common.CommonAction;
import com.cct.common.CommonUser;
import com.cct.domain.GlobalVariable;
import com.cct.exception.AuthorizationException;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor implements Interceptor {

	private static final long serialVersionUID = -1921944000166163942L;

	@Override
	public void destroy() {
		LogUtil.INTERCEPTOR.debug("");
	}

	@Override
	public void init() {
		LogUtil.INTERCEPTOR.debug("");
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = CommonAction.ReturnType.HOME.getResult();

		String localeNameFromParameter = null;
		String actionName = null;
		CommonUser commonUser = null;
		String sessionId = null;
		try {
			// กำหนด CSS
			SessionUtil.put(GlobalVariable.THEME_ACTIVE, SessionUtil.getContextName() + "/css/theme/" + ParameterConfig.getParameter().getApplication().getTheme()
					+ "/jquery-ui.css");

			// Set ค่าลงตัวแปรเพื่อใช้ในการตรวจสอบ
			localeNameFromParameter = SessionUtil.requestParameter(GlobalVariable.DEFAULT_PARAMETER_LANGUAGE);
			actionName = invocation.getProxy().getActionName();
			commonUser = (CommonUser) SessionUtil.get(CommonUser.DEFAULT_SESSION_ATTRIBUTE);
			sessionId = SessionUtil.getId();

			// FIXME กำหนด user เริ่มต้นของระบบ
			if (commonUser == null) {
				commonUser = new CommonUser();
				commonUser.setUserId("726");
				commonUser.setUserName("สุรพงศ์");
				commonUser.setLastName("อมรพรวิทยา");
				Locale locale = new Locale(ParameterConfig.getParameter()
						.getApplication().getApplicationLocaleString()
						.toLowerCase(), ParameterConfig.getParameter()
						.getApplication().getApplicationLocaleString()
						.toUpperCase());
				commonUser.setLocale(locale);
			}
			
			// FIXME กำหนดค่าเริ่มต้น locale ของระบบ
			if (localeNameFromParameter == null) {
//				localeNameFromParameter = "en";
				localeNameFromParameter = ParameterConfig.getParameter().getApplication().getApplicationLocaleString();
			}
			
		} catch (Exception e) {
			manageException(e);
			return result;
		}

		try {
			// Action ดังต่อไปนี้ไม่ต้องตรวจสอบ User
			if (GlobalVariable.MAP_OF_ACTION_NAME_FOR_SKIP_AUTHENTICATE.get(actionName) != null) {
				setLocaleToAction(invocation, localeNameFromParameter, commonUser);
				setNoCache(invocation);
				return invocation.invoke();
			}
		} catch (Exception e) {
			manageException(e);
			return result;
		}

		// ตรวจสอบ Session ที่ใช้ว่าตรงกับที่เก็บไว้หรือไม่
		CCTConnection conn = null;
		try {
			if (commonUser != null) {
				setLocaleToAction(invocation, localeNameFromParameter, commonUser);
				setNoCache(invocation);

//				conn = new CCTConnectionProvider().getConnection(conn, DBLookup.ORA_OC.getLookup());
//
//				LoginManager manager = new LoginManager(conn, null, null);
//				if (manager.foundOtherLoginLog(sessionId, commonUser)) {
//					LogUtil.INTERCEPTOR.debug("The session " + sessionId + " is not match.");
//					ResourceBundle bundle = BundleUtil.getBundle("resources.bundle.common.MessageAlert", invocation.getInvocationContext().getLocale());
//
//					SessionUtil.put(CommonAction.MESSAGE, bundle.getString(GlobalVariable.Message.SESSION_OVERRIDE_CODE.getValue()));
//					SessionUtil.remove(CommonUser.DEFAULT_SESSION_ATTRIBUTE);
//					return result;
//				}
			}
			
		} catch (Exception e) {
			manageException(e);
			return result;
		} finally {
			CCTConnectionUtil.close(conn);
		}

		try {
			// Action ดังต่อไปนี้ต้องตรวจสอบ User
			if (commonUser == null) {
				// ไม่มี User ใน Session
				setLocaleToAction(invocation, localeNameFromParameter, commonUser);
				SessionUtil.put(CommonAction.MESSAGE, GlobalVariable.Message.SESSION_EXPIRED.getValue());
				
			} else {
				if (GlobalVariable.MAP_OF_ACTION_NAME_FOR_SKIP_CHANGE_PASSWORD_FORCE.get(actionName) == null) {
					// FIXME
//					if(commonUser.getForcePwChangeFlag().equalsIgnoreCase("Y")){
//						setLocaleToAction(invocation, localeNameFromParameter, commonUser);
//						//SessionUtil.put(CommonAction.MESSAGE, GlobalVariable.Message.PLEASE_CHANGE_PASSWORD_FORCE.getValue());
//						return "checkChangePasswordForced";
//					}
				}

				// มี User ใน Session ให้ทำงานได้
				setLocaleToAction(invocation, localeNameFromParameter, commonUser);
				setNoCache(invocation);
				result = invocation.invoke();
			}
		} catch (Exception e) {
			manageException(e);
		}
		return result;
	}

	private void setLocaleToAction(ActionInvocation invocation, String localeNameFromParameter, CommonUser commonUser) {
		if (commonUser == null) {
			if ((localeNameFromParameter == null) || localeNameFromParameter.trim().isEmpty()) {
				if ((SessionUtil.get(GlobalVariable.DEFAULT_PARAMETER_LANGUAGE)) == null) {
					// LogUtil.INTERCEPTOR.debug("ถ้าไม่มี user และ ไม่มีการขอเปลื่ยน locale ใช้ default from application");
					localeNameFromParameter = ParameterConfig.getParameter().getApplication().getApplicationLocale().getLanguage();
				} else {
					localeNameFromParameter = SessionUtil.get(GlobalVariable.DEFAULT_PARAMETER_LANGUAGE).toString();
				}
				invocation.getInvocationContext().setLocale(new Locale(localeNameFromParameter.toLowerCase(), localeNameFromParameter.toUpperCase()));

			} else {// รอรับกรณีหน้าที่ไม่มีการ login
				// LogUtil.INTERCEPTOR.debug("ถ้าไม่มี user แต่มีการขอเปลื่ยน locale ให้ใช้ locale จากที่ขอเปลื่ยน");
				SessionUtil.put(GlobalVariable.DEFAULT_PARAMETER_LANGUAGE, localeNameFromParameter);
				invocation.getInvocationContext().setLocale(new Locale(localeNameFromParameter.toLowerCase(), localeNameFromParameter.toUpperCase()));
			}
		} else {
			if ((localeNameFromParameter == null) || localeNameFromParameter.trim().isEmpty()) {
				if ((SessionUtil.get(GlobalVariable.DEFAULT_PARAMETER_LANGUAGE)) == null) {
					// LogUtil.INTERCEPTOR.debug("ถ้ามี user และไม่มีการขอเปลื่ยน locale ให้ใช้ locale from user");
					localeNameFromParameter = commonUser.getLocale().getLanguage();
				} else {
					localeNameFromParameter = SessionUtil.get(GlobalVariable.DEFAULT_PARAMETER_LANGUAGE).toString();
				}
				invocation.getInvocationContext().setLocale(new Locale(localeNameFromParameter.toLowerCase(), localeNameFromParameter.toUpperCase()));
			} else {
				// LogUtil.INTERCEPTOR.debug("ถ้ามี user และมีการขอเปลื่ยน locale ให้ใช้ locale จากที่ขอเปลื่ยน และกำหนดค่าให้กับ user locale ด้วย");
				SessionUtil.put(GlobalVariable.DEFAULT_PARAMETER_LANGUAGE, localeNameFromParameter);
				commonUser.setLocale(new Locale(localeNameFromParameter.toLowerCase(), localeNameFromParameter.toUpperCase()));
				invocation.getInvocationContext().setLocale(commonUser.getLocale());
			}
		}
	}

	private void setNoCache(ActionInvocation invocation) {
		HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(StrutsStatics.HTTP_RESPONSE);
		response.setHeader("Cache-Control", "no-cache");

		// Forces caches to obtain a new copy of the page from the origin server
		response.setHeader("Cache-Control", "no-store");

		// Causes the proxy cache to see the page as "stale"
		response.setHeader("Pragma", "no-cache");

		// Directs caches not to store the page under any circumstance
		response.setDateHeader("Expires", 0);
	}

	private void manageException(Exception e) {
		LogUtil.INTERCEPTOR.error("userId: " + getUserIdFromSession() + ", sessionId: " + SessionUtil.getId());
		LogUtil.INTERCEPTOR.error("", e);
		if (e instanceof AuthorizationException) {
			SessionUtil.put(CommonAction.MESSAGE, GlobalVariable.Message.NO_PERMISSIONS.getValue());
		} else {
			SessionUtil.put(CommonAction.MESSAGE, GlobalVariable.Message.SERVER_ERROR.getValue());
		}
	}

	private String getUserIdFromSession() {
		String userId = null;
		if (getUser() != null) {
			userId = getUser().getUserId();
		}
		return userId;
	}

	private CommonUser getUser() {
		return (CommonUser) SessionUtil.get(CommonUser.DEFAULT_SESSION_ATTRIBUTE);
	}
}
