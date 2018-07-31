package com.cct.trn.web.security.mainpage.action;

import util.log.LogUtil;
import util.web.SessionUtil;

import com.cct.common.CommonAction;
import com.cct.common.CommonUser;
import com.cct.domain.GlobalVariable;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;

public class MainpageAction extends CommonAction {

	private static final long serialVersionUID = -7180942456024412378L;

	public MainpageAction() {

	}

	public String init() {

		String result = ReturnType.INIT.getResult();
		CommonUser commonUser = null;
		try {
			String[] ignore = { CommonUser.DEFAULT_SESSION_ATTRIBUTE, GlobalVariable.DEFAULT_PARAMETER_LANGUAGE };
			SessionUtil.removesIgnore(ignore);
			SessionUtil.setAttribute(GlobalVariable.THEME_ACTIVE, SessionUtil.getContextName() + "/css/theme/" + ParameterConfig.getParameter().getApplication().getTheme()
					+ "/jquery-ui.css");

			commonUser = (CommonUser) SessionUtil.getAttribute(CommonUser.DEFAULT_SESSION_ATTRIBUTE);
			
		} catch (Exception e) {
			LogUtil.LOGIN.error("", e);
		} finally {

		}
		return result;
	}
}
