package com.cct.trn.core.dialog.security.user.domain;

import com.cct.common.CommonDomain;


public class UserDialog extends CommonDomain {

	private static final long serialVersionUID = -5056649625376191895L;
	
	private String userId;
	private String givenName;
	private String familyName;
	private String fullName;
	private String emailAddress;
	private String phoneNumber;
	private String extNumber;
	
	private String deleted;
	private String ids;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getExtNumber() {
		return extNumber;
	}
	public void setExtNumber(String extNumber) {
		this.extNumber = extNumber;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	

}
