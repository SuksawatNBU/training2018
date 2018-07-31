package com.cct.trn.core.standardui.autocomplete.domain;

import java.util.ArrayList;
import java.util.List;

import com.cct.common.CommonModel;
import com.cct.common.CommonSelectItem;
import com.cct.common.CommonUser;

public class AutocompleteModel extends CommonModel {

	private static final long serialVersionUID = -8383912104127320200L;

	private List<CommonSelectItem> lstUser = new ArrayList<CommonSelectItem>();
	private List<CommonSelectItem> lstProject = new ArrayList<CommonSelectItem>();
	
	private CommonUser user = new CommonUser();

	public List<CommonSelectItem> getLstUser() {
		return lstUser;
	}

	public void setLstUser(List<CommonSelectItem> lstUser) {
		this.lstUser = lstUser;
	}

	public CommonUser getUser() {
		return user;
	}

	public void setUser(CommonUser user) {
		this.user = user;
	}

	public List<CommonSelectItem> getLstProject() {
		return lstProject;
	}

	public void setLstProject(List<CommonSelectItem> lstProject) {
		this.lstProject = lstProject;
	}
	
}
