package com.cct.trn.web.dialog.security.user.action;

import java.util.List;

import org.apache.log4j.Logger;

import util.database.CCTConnection;
import util.log.LogUtil;

import com.cct.common.CommonDialogAction;
import com.cct.common.CommonDomain;
import com.cct.domain.SearchCriteria;
import com.cct.trn.core.dialog.security.user.domain.UserDialogModel;
import com.cct.trn.core.dialog.security.user.domain.UserDialogSearchCriteria;
import com.cct.trn.core.dialog.security.user.service.UserDialogManager;

public class UserDialogAction extends CommonDialogAction{

	private static final long serialVersionUID = 8451519183796162744L;
	
	private UserDialogModel model = new UserDialogModel();
	
	@Override
	public List<CommonDomain> search(CCTConnection conn) throws Exception {
		return new UserDialogManager(conn, getUser(), getLocale()).search((UserDialogSearchCriteria) getModel().getCriteriaPopup());
	}
	
	@Override
	public List<CommonDomain> searchById(CCTConnection conn, String ids) throws Exception {
		return new UserDialogManager(conn, getUser(), getLocale()).searchListById(ids);
	}
	
	@Override
	public void getComboForSearch(CCTConnection conn) {
	
	}
	
	@Override
	public UserDialogModel getModel() {
		return model;
	}
	
	@Override
	protected Logger loggerInititial() {
		return LogUtil.DIALOG;
	}
	
	@Override
	public SearchCriteria initSearchCriteria() {
		return new UserDialogSearchCriteria();
	}
}
