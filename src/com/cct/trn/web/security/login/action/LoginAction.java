package com.cct.trn.web.security.login.action;

import java.util.Locale;

import org.apache.log4j.Logger;

import util.log.LogUtil;
import util.web.SessionUtil;

import com.cct.common.CommonAction;
import com.cct.common.CommonUser;
import com.cct.domain.GlobalVariable;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;
import com.cct.trn.core.security.login.domain.LoginModel;
import com.cct.trn.core.security.login.service.LoginManager;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends CommonAction implements ModelDriven<LoginModel> {

	private static final long serialVersionUID = 4557027877706302838L;

	private LoginModel model = new LoginModel();

	public LoginAction() {
		LogUtil.LOGIN.info("userId: " + getUserIdFromSession() + ", sessionId: " + SessionUtil.getId());

		// set style ให้ button
		SessionUtil.setAttribute("themeActive", SessionUtil.getContextName() + "/css/theme/" + ParameterConfig.getParameter().getApplication().getTheme()
				+ "/jquery-ui.css");
	}

	public String init() {
		LogUtil.INITIAL.debug("init");
		
		String result = ReturnType.INIT.getResult();
		try {
			String[] ignore = { CommonUser.DEFAULT_SESSION_ATTRIBUTE, GlobalVariable.DEFAULT_PARAMETER_LANGUAGE };
			SessionUtil.removesIgnore(ignore);
			SessionUtil.setAttribute(GlobalVariable.THEME_ACTIVE, SessionUtil.getContextName() 
					+ "/css/theme/" + ParameterConfig.getParameter().getApplication().getTheme()
					+ "/jquery-ui.css");

			CommonUser commonUser = (CommonUser) SessionUtil.getAttribute(CommonUser.DEFAULT_SESSION_ATTRIBUTE);
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
			
			// search menu
			LoginManager manager = new LoginManager(null, getUser(), getLocale()); 
			CommonUser user = manager.login();
			commonUser.setMapOperator(user.getMapOperator());
			
			SessionUtil.put(CommonUser.DEFAULT_SESSION_ATTRIBUTE, commonUser);
			
		} catch (Exception e) {
			LogUtil.INITIAL.error("", e);
		} finally {

		}
		return result;
	}

	@Override
	public LoginModel getModel() {
		return model;
	}

	@Override
	protected Logger loggerInititial() {
		return LogUtil.LOGIN;
	}

}
