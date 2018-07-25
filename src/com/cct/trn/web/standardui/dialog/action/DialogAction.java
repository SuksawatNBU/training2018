package com.cct.trn.web.standardui.dialog.action;

import org.apache.log4j.Logger;

import util.log.LogUtil;

import com.cct.common.CommonAction;
import com.cct.trn.core.standardui.dialog.domain.DialogModel;
import com.opensymphony.xwork2.ModelDriven;

public class DialogAction extends CommonAction implements ModelDriven<DialogModel> {

	private static final long serialVersionUID = -8937836769224842997L;

	private DialogModel model = new DialogModel();

	public DialogAction() {
		
	}

	public String init() {
		LogUtil.TRAINING.debug("init");
		return ReturnType.INIT.getResult();
	}
	
	@Override
	public DialogModel getModel() {
		return model;
	}
	
	@Override
	protected Logger loggerInititial() {
		return LogUtil.TRAINING;
	}
}
