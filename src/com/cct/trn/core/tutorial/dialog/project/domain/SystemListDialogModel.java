package com.cct.trn.core.tutorial.dialog.project.domain;

import java.util.ArrayList;
import java.util.List;

import com.cct.common.CommonModel;
import com.cct.domain.SearchCriteria;
public class SystemListDialogModel extends CommonModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1434223490465119975L;
	
	private SystemListDialogSearchCriteria criteriaPopup = new SystemListDialogSearchCriteria();
	private List<SystemListDialog> lstSystemListDialogs = new ArrayList<SystemListDialog>();
	
	@Override
	public SystemListDialogSearchCriteria getCriteriaPopup() {
		return criteriaPopup;
	}
	
	@Override
	public void setCriteriaPopup(SearchCriteria criteria) {
		this.criteriaPopup = (SystemListDialogSearchCriteria) criteria;
	}

	public List<SystemListDialog> getLstSystemListDialogs() {
		return lstSystemListDialogs;
	}

	public void setLstSystemListDialogs(List<SystemListDialog> lstSystemListDialogs) {
		this.lstSystemListDialogs = lstSystemListDialogs;
	}

}
