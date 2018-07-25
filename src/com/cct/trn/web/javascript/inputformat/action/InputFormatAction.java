package com.cct.trn.web.javascript.inputformat.action;

import org.apache.log4j.Logger;

import util.log.LogUtil;

import com.cct.common.CommonAction;
import com.cct.trn.core.javascript.inputformat.domdel.InputFormatModel;
import com.opensymphony.xwork2.ModelDriven;

public class InputFormatAction extends CommonAction implements ModelDriven<InputFormatModel> {

	private static final long serialVersionUID = -8937836769224842997L;

	private InputFormatModel model = new InputFormatModel();

	public InputFormatAction() {
		
	}

	public String init() {
		LogUtil.TRAINING.debug("init");
		return ReturnType.INIT.getResult();
	}
	
	@Override
	public InputFormatModel getModel() {
		return model;
	}
	
	@Override
	protected Logger loggerInititial() {
		return LogUtil.TRAINING;
	}
}
