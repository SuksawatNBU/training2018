package com.cct.trn.web.interceptor;

import util.log.LogUtil;
import util.web.SessionUtil;

import com.cct.common.CommonAction;
import com.cct.common.CommonUser;
import com.opensymphony.xwork2.ActionInvocation;

public class TokenInterceptor extends org.apache.struts2.interceptor.TokenInterceptor {

	private static final long serialVersionUID = 5521505366657681585L;

	protected String handleInvalidToken(ActionInvocation invocation) {
		String actionName = invocation.getProxy().getActionName();
		String returnvalue = CommonAction.ReturnType.HOME.getResult();
		LogUtil.INTERCEPTOR.error("userId: " + getUserIdFromSession() + ", sessionId: " + SessionUtil.getId());
		try {
			LogUtil.INTERCEPTOR.debug("actionName :- " + actionName);
			if (actionName.startsWith("add")) {

			} else if (actionName.startsWith("edit")) {

			} else if (actionName.startsWith("update")) {
				returnvalue = CommonAction.ReturnType.SEARCH_DO.getResult();
			}
		} catch (Exception e) {
			LogUtil.INTERCEPTOR.error("", e);
		}
		LogUtil.INTERCEPTOR.debug("Forward to :- [" + returnvalue + "]");
		SessionUtil.put(CommonAction.MESSAGE, "Double post.");
		return returnvalue;
	}

	protected String handleValidToken(ActionInvocation invocation) {
		String returnvalue = CommonAction.ReturnType.HOME.getResult();
		try {
			returnvalue = invocation.invoke();
		} catch (Exception e) {
			LogUtil.INTERCEPTOR.error("userId: " + getUserIdFromSession() + ", sessionId: " + SessionUtil.getId());
			LogUtil.INTERCEPTOR.error("", e);
		}
		return returnvalue;
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
