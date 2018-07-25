package com.cct.trn.web.javascript.requireinput.action;

import org.apache.log4j.Logger;

import util.log.LogUtil;

import com.cct.common.CommonAction;
import com.cct.trn.core.javascript.requireinput.domdel.RequireInputModel;
import com.opensymphony.xwork2.ModelDriven;

public class RequireInputAction extends CommonAction implements ModelDriven<RequireInputModel> {

	private static final long serialVersionUID = -8937836769224842997L;

	private RequireInputModel model = new RequireInputModel();

	public RequireInputAction() {
		
	}

	public String init() {
		LogUtil.TRAINING.debug("init");
		return ReturnType.INIT.getResult();
	}
	
	@Override
	public RequireInputModel getModel() {
		return model;
	}
	
	@Override
	protected Logger loggerInititial() {
		return LogUtil.TRAINING;
	}
}
