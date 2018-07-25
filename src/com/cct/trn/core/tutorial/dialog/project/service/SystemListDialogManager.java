package com.cct.trn.core.tutorial.dialog.project.service;

import java.util.List;
import java.util.Locale;

import util.database.CCTConnection;

import com.cct.abstracts.AbstractManager;
import com.cct.common.CommonDomain;
import com.cct.common.CommonUser;
import com.cct.trn.core.tutorial.dialog.project.domain.SystemListDialog;
import com.cct.trn.core.tutorial.dialog.project.domain.SystemListDialogSearchCriteria;

public class SystemListDialogManager extends AbstractManager<SystemListDialogSearchCriteria, CommonDomain, SystemListDialog, CommonUser, Locale>{
	
private SystemListDialogService service = null;
	
	public SystemListDialogManager(CCTConnection conn,CommonUser user, Locale locale) {
		super(conn, user, locale);
		service = new SystemListDialogService(conn,  user, locale);
	}

	@Override
	public List<CommonDomain> search(SystemListDialogSearchCriteria criteria)
			throws Exception {
		return null;
	}
	
	public List<CommonDomain> searchDetail(SystemListDialogSearchCriteria criteria)
			throws Exception {
		List<CommonDomain> lstResult = null;
		try {
			criteria.setTotalResult(service.countData(criteria));
			lstResult = service.searchDetail(criteria);
			
		} catch (Exception e) {
			throw e;
		}
		return lstResult;
	}

	@Override
	public SystemListDialog searchById(String id) throws Exception {
		return null;
	}

	@Override
	public int add(SystemListDialog obj) throws Exception {
		return 0;
	}

	@Override
	public int edit(SystemListDialog obj) throws Exception {
		return 0;
	}

	@Override
	public int delete(String ids) throws Exception {
		return 0;
	}

	@Override
	public int updateActive(String ids, String activeFlag) throws Exception {
		return 0;
	}
	
}
