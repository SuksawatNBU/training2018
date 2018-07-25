package com.cct.trn.core.tutorial.employee.domain;


import com.cct.common.CommonDomain;

public class Employee extends CommonDomain {

	private static final long serialVersionUID = -5377136574847222390L;
	private String name;
	private String surname;
	private String nickName;
	private String prefixId;
	private String sex;
	private String positionId;
	private String startWorkDate;
	private String endWorkDate;
	private String workStatus;
	/* 
	private String employeeId;
	private String remark;
	private String createDate;
	private String createUser;
	private String updateDate;
	private String updateUser;
	*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPrefixId() {
		return prefixId;
	}
	public void setPrefixId(String prefixId) {
		this.prefixId = prefixId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPositionId() {
		return positionId;
	}
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	public String getStartWorkDate() {
		return startWorkDate;
	}
	public void setStartWorkDate(String startWorkDate) {
		this.startWorkDate = startWorkDate;
	}
	public String getEndWorkDate() {
		return endWorkDate;
	}
	public void setEndWorkDate(String endWorkDate) {
		this.endWorkDate = endWorkDate;
	}
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
	
	
	
	
	

}
