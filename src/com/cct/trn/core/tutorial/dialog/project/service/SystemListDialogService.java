package com.cct.trn.core.tutorial.dialog.project.service;

import java.util.List;
import java.util.Locale;

import util.database.CCTConnection;

import com.cct.abstracts.AbstractService;
import com.cct.common.CommonDomain;
import com.cct.common.CommonUser;
import com.cct.trn.core.config.parameter.domain.SQLPath;
import com.cct.trn.core.tutorial.dialog.project.domain.SystemListDialogSearchCriteria;


public class SystemListDialogService extends AbstractService{
	
	private SystemListDialogDAO dao = new SystemListDialogDAO();

	public SystemListDialogService(CCTConnection conn,CommonUser user, Locale locale) {
		super(conn, user, locale);
		setDao(new SystemListDialogDAO());
		getDao().setSqlPath(SQLPath.SYS_DIALOG_SQL);
	}
	
	public long countData(SystemListDialogSearchCriteria criteria) throws Exception {
		return dao.countData(conn, criteria, user, locale);
	}
	
	public List<CommonDomain> searchDetail(SystemListDialogSearchCriteria criteria) throws Exception {
		return dao.searchDetail(conn, user, locale, criteria);
	}

	public SystemListDialogDAO getDao() {
		return dao;
	}

	public void setDao(SystemListDialogDAO dao) {
		this.dao = dao;
	}
}
