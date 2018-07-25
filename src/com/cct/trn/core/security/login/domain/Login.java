package com.cct.trn.core.security.login.domain;

import com.cct.common.CommonDomain;

public class Login extends CommonDomain {

	private static final long serialVersionUID = -4196930762894785496L;

	private String loginID;
	private String username;
	private String password;

	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
