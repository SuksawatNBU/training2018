package com.cct.trn.core.standardui.dropdownlist.domain;

import java.util.ArrayList;
import java.util.List;

import com.cct.common.CommonModel;
import com.cct.common.CommonSelectItem;

public class DropdownlistModel extends CommonModel {

	private static final long serialVersionUID = -8383912104127320200L;
	
	private List<CommonSelectItem> lstProjects = new ArrayList<CommonSelectItem>();
	private List<CommonSelectItem> lstSystems = new ArrayList<CommonSelectItem>();
	private List<CommonSelectItem> lstSubSystems = new ArrayList<CommonSelectItem>();
	private List<CommonSelectItem> lstAllUser = new ArrayList<CommonSelectItem>();
	
	private String userId;
	private String userName;

	public List<CommonSelectItem> getLstProjects() {
		return lstProjects;
	}

	public void setLstProjects(List<CommonSelectItem> lstProjects) {
		this.lstProjects = lstProjects;
	}

	public List<CommonSelectItem> getLstSystems() {
		return lstSystems;
	}

	public void setLstSystems(List<CommonSelectItem> lstSystems) {
		this.lstSystems = lstSystems;
	}

	public List<CommonSelectItem> getLstSubSystems() {
		return lstSubSystems;
	}

	public void setLstSubSystems(List<CommonSelectItem> lstSubSystems) {
		this.lstSubSystems = lstSubSystems;
	}

	public List<CommonSelectItem> getLstAllUser() {
		return lstAllUser;
	}

	public void setLstAllUser(List<CommonSelectItem> lstAllUser) {
		this.lstAllUser = lstAllUser;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
