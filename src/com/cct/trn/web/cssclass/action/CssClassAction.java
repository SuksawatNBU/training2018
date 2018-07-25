package com.cct.trn.web.cssclass.action;

import org.apache.log4j.Logger;

import util.log.LogUtil;

import com.cct.common.CommonAction;
import com.cct.trn.core.cssclass.CssClassModel;
import com.opensymphony.xwork2.ModelDriven;

public class CssClassAction extends CommonAction implements ModelDriven<CssClassModel> {

	private static final long serialVersionUID = 4557027877706302838L;

	private CssClassModel model = new CssClassModel();

	public CssClassAction() {
		
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
	public CssClassModel getModel() {
		return model;
	}

	@Override
	protected Logger loggerInititial() {
		return LogUtil.TRAINING;
	}
}
