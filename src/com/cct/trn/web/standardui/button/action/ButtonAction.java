package com.cct.trn.web.standardui.button.action;

import org.apache.log4j.Logger;

import util.log.LogUtil;

import com.cct.common.CommonAction;
import com.cct.trn.core.standardui.button.domain.ButtonModel;
import com.opensymphony.xwork2.ModelDriven;

public class ButtonAction extends CommonAction implements ModelDriven<ButtonModel> {

	private static final long serialVersionUID = -8937836769224842997L;

	private ButtonModel model = new ButtonModel();

	public ButtonAction() {
		ATH.setSearch(true);
		ATH.setEdit(true);
		ATH.setAdd(true);
		ATH.setView(true);
		ATH.setExport(true);
		ATH.setPrint(true);
		
	}

	public String init() {
		LogUtil.TRAINING.debug("init");
		return ReturnType.INIT.getResult();
	}
	
	@Override
	public ButtonModel getModel() {
		return model;
	}
	
	@Override
	protected Logger loggerInititial() {
		return LogUtil.TRAINING;
	}
}
