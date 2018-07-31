package com.cct.exception;

import util.web.SessionUtil;

import com.cct.common.CommonAction;

public class UCPValidateException  extends Exception {

	private static final long serialVersionUID = 1170045810112294610L;

	public UCPValidateException() {
		super();
	}

	public UCPValidateException(String args0) {
		super(args0);
		//SessionUtil.setAttribute(CommonAction.MESSAGE,"invalid");
		String[] messages = { "E", args0};
		SessionUtil.put(CommonAction.MESSAGE, messages);
	}

}