package com.cct.domain;

import java.io.Serializable;

public class Active implements Serializable {

	private static final long serialVersionUID = -1351276682380887107L;

	private String code;
	private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}