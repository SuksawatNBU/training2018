package com.cct.trn.web.javascript.submitpage.action;

import org.apache.log4j.Logger;

import util.log.LogUtil;

import com.cct.common.CommonAction;
import com.cct.trn.core.javascript.submitpage.domdel.SubmitPageModel;
import com.opensymphony.xwork2.ModelDriven;

public class SubmitPageAction extends CommonAction implements ModelDriven<SubmitPageModel> {

	private static final long serialVersionUID = -8937836769224842997L;

	private SubmitPageModel model = new SubmitPageModel();

	public SubmitPageAction() {
		
	}

	public String init() {
		LogUtil.TRAINING.debug("init");
		return ReturnType.INIT.getResult();
	}
	
	@Override
	public SubmitPageModel getModel() {
		return model;
	}
	
	@Override
	protected Logger loggerInititial() {
		return LogUtil.TRAINING;
	}
}
