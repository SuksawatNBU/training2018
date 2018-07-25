package com.cct.trn.web.security.mainpage.action;

import org.apache.struts2.ServletActionContext;

import util.log.LogUtil;
import util.web.SessionUtil;

import com.cct.common.CommonAction;
import com.cct.domain.GlobalVariable;
import com.cct.domain.Progressbar;
import com.cct.exception.AuthorizationException;

public class ProgressBarAction extends CommonAction {

	private Progressbar model = new Progressbar();

	private static final long serialVersionUID = -7180942456024412378L;

	public String running() throws AuthorizationException {

		try {
			String time = "";
			if (ServletActionContext.getRequest().getParameter("keyBar") != null) {
				time = ServletActionContext.getRequest().getParameter("keyBar").toString();
			}

			int progress = (int) SessionUtil.get(GlobalVariable.PERCENT + time);
			String progressTxt = (String) SessionUtil.get(GlobalVariable.PERCENT_TXT + time);
			boolean flagProcess = (boolean) SessionUtil.get(GlobalVariable.FLAG_PROCESS + time);
			model.setProgress(progress);
			model.setFlagProcess(flagProcess);
			model.setProgressTxt(progressTxt);

			if (progress >= 100) {
				model.setFlagProcess(false);
				SessionUtil.remove(GlobalVariable.PERCENT + time);
				SessionUtil.remove(GlobalVariable.PERCENT_TXT + time);
				SessionUtil.remove(GlobalVariable.FLAG_PROCESS + time);

			}

		} catch (Exception e) {
			LogUtil.COMMON.error("", e);
		}

		return "progress";
	}

	public Progressbar getModel() {
			return model;
	}

}
