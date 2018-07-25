package com.cct.trn.web.standardui.datatable.action;

import org.apache.log4j.Logger;

import util.log.LogUtil;

import com.cct.common.CommonAction;
import com.cct.trn.core.standardui.datatable.domain.DataTableModel;
import com.opensymphony.xwork2.ModelDriven;

public class DataTableAction extends CommonAction implements ModelDriven<DataTableModel> {

	private static final long serialVersionUID = -8937836769224842997L;

	private DataTableModel model = new DataTableModel();

	public DataTableAction() {
		
	}

	public String init() {
		LogUtil.TRAINING.debug("init");
		return ReturnType.INIT.getResult();
	}
	
	@Override
	public DataTableModel getModel() {
		return model;
	}
	
	@Override
	protected Logger loggerInititial() {
		return LogUtil.TRAINING;
	}
}
