package com.cct.trn.core.standardui.dropdownlist.service;

import java.util.List;
import java.util.Locale;

import util.database.CCTConnection;

import com.cct.abstracts.AbstractDAO;
import com.cct.common.CommonUser;

public class DropdownlistDAO extends AbstractDAO<Object, Object, Object, CommonUser, Locale> {

	@Override
	protected long countData(CCTConnection conn, Object criteria,
			CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected List<Object> search(CCTConnection conn, Object criteria,
			CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object searchById(CCTConnection conn, String id, CommonUser user,
			Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int add(CCTConnection conn, Object obj, CommonUser user,
			Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int edit(CCTConnection conn, Object obj, CommonUser user,
			Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int delete(CCTConnection conn, String ids, CommonUser user,
			Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int updateActive(CCTConnection conn, String ids,
			String activeFlag, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected boolean checkDup(CCTConnection conn, Object obj, CommonUser user,
			Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
