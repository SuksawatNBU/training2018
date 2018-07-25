package com.cct.trn.core.dialog.security.user.domain;

import com.cct.common.CommonModel;
import com.cct.domain.SearchCriteria;

public class UserDialogModel extends CommonModel{

	private static final long serialVersionUID = -4107670848372512915L;
	
	private UserDialogSearchCriteria criteriaPopup = new UserDialogSearchCriteria();
	
	
	@Override
	public UserDialogSearchCriteria getCriteriaPopup() {
		return criteriaPopup;
	}
	
	@Override
	public void setCriteriaPopup(SearchCriteria criteria) {
		this.criteriaPopup = (UserDialogSearchCriteria) criteria;
	}

}
