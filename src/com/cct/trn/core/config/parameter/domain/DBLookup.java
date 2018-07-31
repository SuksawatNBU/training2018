package com.cct.trn.core.config.parameter.domain;

public enum DBLookup {
	MYSQL_TRAINING("0");

	private String lookup;

	private DBLookup(String lookup) {
		this.lookup = lookup;
	}

	public String getLookup() {
		return lookup;
	}
	
}