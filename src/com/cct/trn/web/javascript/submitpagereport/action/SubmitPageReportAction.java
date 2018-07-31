package com.cct.trn.web.javascript.submitpagereport.action;

import org.apache.log4j.Logger;

import util.log.LogUtil;

import com.cct.common.CommonAction;
import com.cct.trn.core.javascript.submitpagereport.domdel.SubmitPageReportModel;
import com.opensymphony.xwork2.ModelDriven;

public class SubmitPageReportAction extends CommonAction implements ModelDriven<SubmitPageReportModel> {

	private static final long serialVersionUID = -8937836769224842997L;

	private SubmitPageReportModel model = new SubmitPageReportModel();

	public SubmitPageReportAction() {
		
	}

	public String init() {
		LogUtil.TRAINING.debug("init");
		return ReturnType.INIT.getResult();
	}
	
	@Override
	public SubmitPageReportModel getModel() {
		return model;
	}
	
	@Override
	protected Logger loggerInititial() {
		return LogUtil.TRAINING;
	}
}
