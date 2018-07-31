package com.cct.common;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import com.cct.domain.Operator;

public class CommonUser implements Serializable {

	private static final long serialVersionUID = 8392198685797505086L;

	public static final String DEFAULT_SESSION_ATTRIBUTE = "user";

	private String userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String prefixName;

	private String startDate;
	private String endDate;
	private String active;
	private String lockStatus;
	private String passwordDate;
	private String fullName;
	private String organizationId;

	private Locale locale;

	private String email;
	
	private String forcePwChangeFlag;
	private int timeOut;

	private Map<String, Operator> mapOperator = new LinkedHashMap<String, Operator>();		// ใช้สหระบเช็คสิทธ์ และวาด menu
	private Map<String, Operator> mapMenu = new LinkedHashMap<String, Operator>();			// ใช้สำหรับวาด system navigate

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Map<String, Operator> getMapOperator() {
		return mapOperator;
	}

	public void setMapOperator(Map<String, Operator> mapOperator) {
		this.mapOperator = mapOperator;
	}

	public String getPasswordDate() {
		return passwordDate;
	}

	public void setPasswordDate(String passwordDate) {
		this.passwordDate = passwordDate;
	}

	public String getPrefixName() {
		return prefixName;
	}

	public void setPrefixName(String prefixName) {
		this.prefixName = prefixName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getForcePwChangeFlag() {
		return forcePwChangeFlag;
	}

	public void setForcePwChangeFlag(String forcePwChangeFlag) {
		this.forcePwChangeFlag = forcePwChangeFlag;
	}

	public Map<String, Operator> getMapMenu() {
		return mapMenu;
	}

	public void setMapMenu(Map<String, Operator> mapMenu) {
		this.mapMenu = mapMenu;
	}

	public int getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

}