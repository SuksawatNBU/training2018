package com.cct.trn.core.dialog.security.user.service;

import java.util.List;
import java.util.Locale;

import util.database.CCTConnection;

import com.cct.abstracts.AbstractService;
import com.cct.common.CommonDomain;
import com.cct.common.CommonUser;
import com.cct.trn.core.config.parameter.domain.SQLPath;
import com.cct.trn.core.dialog.security.user.domain.UserDialogSearchCriteria;

public class UserDialogService extends AbstractService {
	
	UserDialogDAO dao = new UserDialogDAO();

	public UserDialogService(CCTConnection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		dao.setSqlPath(SQLPath.DIALOG_SQL);
	}

	protected long countData(UserDialogSearchCriteria criteriaPopup) throws Exception {
		return dao.countData(conn, criteriaPopup, user, locale);
	}

	protected List<CommonDomain> search(UserDialogSearchCriteria criteriaPopup) throws Exception {
		return dao.search(conn, criteriaPopup, user,locale);
	}

	protected List<CommonDomain> searchListById(String ids) throws Exception {
		return dao.searchListById(conn, ids, user, locale);
	}
                                                                                                                                                                                                                                                                                                                                                                                
}
