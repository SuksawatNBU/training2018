package com.cct.common;

import java.io.Serializable;

public class CommonSelectItem implements Serializable {

	private static final long serialVersionUID = 6771221109764211702L;

	private String key;
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}