package com.cct.trn.web.template.action;

import org.apache.log4j.Logger;

import util.log.LogUtil;

import com.cct.common.CommonAction;
import com.cct.trn.core.template.domain.TemplateModel;
import com.opensymphony.xwork2.ModelDriven;

public class TemplateAction extends CommonAction implements ModelDriven<TemplateModel> {

	private static final long serialVersionUID = 4557027877706302838L;

	private TemplateModel model = new TemplateModel();

	public TemplateAction() {
		
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
	public TemplateModel getModel() {
		return model;
	}

	@Override
	protected Logger loggerInititial() {
		return LogUtil.TRAINING;
	}
}
