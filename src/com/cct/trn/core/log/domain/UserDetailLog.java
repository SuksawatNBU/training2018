package com.cct.trn.core.log.domain;

import java.io.Serializable;

public class UserDetailLog implements Serializable{
	private static final long serialVersionUID = -3097525671392089224L;

	
	private String logEventUcpId;
	private String userUcpId;
	private String userName;
	private String familyName;
	private String givenName;
	private String parentId;
	private String operatorId;
	private String eventDate;
	private String logDetail;
	
	
	public String getLogEventUcpId() {
		return logEventUcpId;
	}
	public void setLogEventUcpId(String logEventUcpId) {
		this.logEventUcpId = logEventUcpId;
	}
	public String getUserUcpId() {
		return userUcpId;
	}
	public void setUserUcpId(String userUcpId) {
		this.userUcpId = userUcpId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getLogDetail() {
		return logDetail;
	}
	public void setLogDetail(String logDetail) {
		this.logDetail = logDetail;
	}
	
	
	
}
