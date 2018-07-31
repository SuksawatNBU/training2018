package com.cct.domain;

import java.io.Serializable;

public class Transaction implements Serializable {

	private static final long serialVersionUID = -5059872355175070364L;

	private String createUser;
	private String createDate;
	private String createRemark;

	private String updateUser;
	private String updateDate;
	private String updateRemark;

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateRemark() {
		return createRemark;
	}

	public void setCreateRemark(String createRemark) {
		this.createRemark = createRemark;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateRemark() {
		return updateRemark;
	}

	public void setUpdateRemark(String updateRemark) {
		this.updateRemark = updateRemark;
	}

}
