package com.cct.trn.web.javascript.selectedrow.action;

import org.apache.log4j.Logger;

import util.log.LogUtil;

import com.cct.common.CommonAction;
import com.cct.trn.core.javascript.selectedrow.domdel.SelectedRowModel;
import com.opensymphony.xwork2.ModelDriven;

public class SelectedRowAction extends CommonAction implements ModelDriven<SelectedRowModel> {

	private static final long serialVersionUID = -8937836769224842997L;

	private SelectedRowModel model = new SelectedRowModel();

	public SelectedRowAction() {
		
	}

	public String init() {
		LogUtil.TRAINING.debug("init");
		return ReturnType.INIT.getResult();
	}
	
	@Override
	public SelectedRowModel getModel() {
		return model;
	}
	
	@Override
	protected Logger loggerInititial() {
		return LogUtil.TRAINING;
	}
}
