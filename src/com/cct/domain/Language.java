package com.cct.domain;

import java.io.Serializable;
import java.util.Locale;

public class Language implements Serializable {

	private static final long serialVersionUID = 2249694822960177687L;

	private String id;
	private String code;
	private String desc;

	private Locale locale;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}