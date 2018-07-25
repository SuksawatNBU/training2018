package com.cct.trn.web.javascript.requireinputform.action;

import org.apache.log4j.Logger;

import util.log.LogUtil;

import com.cct.common.CommonAction;
import com.cct.trn.core.javascript.requireinputform.domdel.RequireInputFormModel;
import com.opensymphony.xwork2.ModelDriven;

public class RequireInputFormAction extends CommonAction implements ModelDriven<RequireInputFormModel> {

	private static final long serialVersionUID = -8937836769224842997L;

	private RequireInputFormModel model = new RequireInputFormModel();

	public RequireInputFormAction() {
		
	}

	public String init() {
		LogUtil.TRAINING.debug("init");
		return ReturnType.INIT.getResult();
	}
	
	@Override
	public RequireInputFormModel getModel() {
		return model;
	}
	
	@Override
	protected Logger loggerInititial() {
		return LogUtil.TRAINING;
	}
}
