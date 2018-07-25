package com.cct.trn.core.security.login.service;

import java.util.List;
import java.util.Locale;

import util.database.CCTConnection;

import com.cct.abstracts.AbstractManager;
import com.cct.common.CommonUser;

public class LoginManager extends AbstractManager<Object, Object, Object, CommonUser, Locale> {

	private LoginService service = null;

	public LoginManager(CCTConnection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		this.service = new LoginService(conn, user, locale);
	}
	
	public CommonUser login() throws Exception {
		CommonUser commonUser = new CommonUser();
		try {
			service.searchMenuAndFunction(commonUser);
			
		} catch (Exception e) {
			throw e;
		}
		return commonUser;
	}

	
	/**
	 * @deprecated ไม่ได้ใช้
	 */
	@Override
	public List<Object> search(Object criteria) throws Exception {
		return null;
	}

	/**
	 * @deprecated ไม่ได้ใช้
	 */
	@Override
	public Object searchById(String id) throws Exception {
		return null;
	}

	/**
	 * @deprecated ไม่ได้ใช้
	 */
	@Override
	public int add(Object obj) throws Exception {
		return 0;
	}

	/**
	 * @deprecated ไม่ได้ใช้
	 */
	@Override
	public int edit(Object obj) throws Exception {
		return 0;
	}

	/**
	 * @deprecated ไม่ได้ใช้
	 */
	@Override
	public int delete(String ids) throws Exception {
		return 0;
	}

	/**
	 * @deprecated ไม่ได้ใช้
	 */
	@Override
	public int updateActive(String ids, String activeFlag) throws Exception {
		return 0;
	}
}
