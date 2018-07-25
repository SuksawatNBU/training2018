package com.cct.trn.web.standardui.dropdownlist.action;

import org.apache.log4j.Logger;

import util.database.CCTConnection;
import util.database.CCTConnectionProvider;
import util.database.CCTConnectionUtil;
import util.log.LogUtil;

import com.cct.common.CommonAction;
import com.cct.trn.core.config.parameter.domain.DBLookup;
import com.cct.trn.core.selectitem.service.SelectItemManager;
import com.cct.trn.core.standardui.dropdownlist.domain.DropdownlistModel;
import com.opensymphony.xwork2.ModelDriven;

public class DropdownlistAction extends CommonAction implements ModelDriven<DropdownlistModel> {

	private static final long serialVersionUID = -8937836769224842997L;

	private DropdownlistModel model = new DropdownlistModel();

	public DropdownlistAction() {
		
	}

	public String init() {
		LogUtil.TRAINING.debug("init");
		CCTConnection conn = null;
		try {
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());
			
			SelectItemManager manager = new SelectItemManager(conn, getUser(), getLocale());
			model.setLstAllUser(manager.searchAllQAUserAutoSelectItem(null, null));
			
		} catch (Exception e) {
			LogUtil.TRAINING.error(e);
		} finally {
	        CCTConnectionUtil.close(conn);
		}
		return ReturnType.INIT.getResult();
	}
	
	@Override
	public DropdownlistModel getModel() {
		return model;
	}
	
	@Override
	protected Logger loggerInititial() {
		return LogUtil.TRAINING;
	}
}
