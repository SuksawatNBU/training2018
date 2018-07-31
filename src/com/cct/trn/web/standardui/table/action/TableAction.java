package com.cct.trn.web.standardui.table.action;

import org.apache.log4j.Logger;

import util.log.LogUtil;

import com.cct.common.CommonAction;
import com.cct.trn.core.standardui.table.domain.TableModel;
import com.opensymphony.xwork2.ModelDriven;

public class TableAction extends CommonAction implements ModelDriven<TableModel> {

	private static final long serialVersionUID = -8937836769224842997L;

	private TableModel model = new TableModel();

	public TableAction() {
		
	}

	public String init() {
		LogUtil.TRAINING.debug("init");
		return ReturnType.INIT.getResult();
	}
	
	@Override
	public TableModel getModel() {
		return model;
	}
	
	@Override
	protected Logger loggerInititial() {
		return LogUtil.TRAINING;
	}
}
