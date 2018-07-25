package com.cct.trn.web.javascript.requireselectgroup.action;

import org.apache.log4j.Logger;

import util.log.LogUtil;

import com.cct.common.CommonAction;
import com.cct.trn.core.javascript.requireselectgroup.domdel.RequireSelectGroupModel;
import com.opensymphony.xwork2.ModelDriven;

public class RequireSelectGroupAction extends CommonAction implements ModelDriven<RequireSelectGroupModel> {

	private static final long serialVersionUID = -8937836769224842997L;

	private RequireSelectGroupModel model = new RequireSelectGroupModel();

	public RequireSelectGroupAction() {
		
	}

	public String init() {
		LogUtil.TRAINING.debug("init");
		return ReturnType.INIT.getResult();
	}
	
	@Override
	public RequireSelectGroupModel getModel() {
		return model;
	}
	
	@Override
	protected Logger loggerInititial() {
		return LogUtil.TRAINING;
	}
}
