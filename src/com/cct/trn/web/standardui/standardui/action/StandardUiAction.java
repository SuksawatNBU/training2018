package com.cct.trn.web.standardui.standardui.action;

import org.apache.log4j.Logger;

import util.log.LogUtil;

import com.cct.common.CommonAction;
import com.cct.trn.core.standardui.miscellaneous.domain.MiscellaneousModel;
import com.opensymphony.xwork2.ModelDriven;

public class StandardUiAction extends CommonAction implements ModelDriven<MiscellaneousModel> {

	private static final long serialVersionUID = 4557027877706302838L;

	private MiscellaneousModel model = new MiscellaneousModel();

	public StandardUiAction() {
		
	}

	public String init() {
		LogUtil.TRAINING.debug("init");
		
		String result = ReturnType.INIT.getResult();
		try {
			
		} catch (Exception e) {
			LogUtil.TRAINING.error("", e);
		} finally {

		}
		return result;
	}

	@Override
	public MiscellaneousModel getModel() {
		return model;
	}

	@Override
	protected Logger loggerInititial() {
		return LogUtil.TRAINING;
	}
}
