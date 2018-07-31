package com.cct.abstracts;

import java.util.Locale;

import util.database.CCTConnection;

import com.cct.common.CommonUser;

public abstract class AbstractService {
	protected CCTConnection conn = null;
	protected CommonUser user = null;
	protected Locale locale = null;

	/**
	 * @param conn
	 */
	public AbstractService(CCTConnection conn, CommonUser user, Locale locale) {
		this.conn = conn;
		this.user = user;
		this.locale = locale;
	}
}
