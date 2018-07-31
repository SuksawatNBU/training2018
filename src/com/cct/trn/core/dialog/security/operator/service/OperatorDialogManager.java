package com.cct.trn.core.dialog.security.operator.service;

import java.util.Locale;
import java.util.Map;

import util.database.CCTConnection;

import com.cct.common.CommonUser;
import com.cct.domain.Tree;

public class OperatorDialogManager {
	
	private OperatorDialogService service = null;
	
	public OperatorDialogManager(CCTConnection conn, CommonUser user, Locale locale) {
		service = new OperatorDialogService(conn, user, locale);
	}

	public Map<String, Tree> searchMenuTree() throws Exception {
		return service.searchMenuProgramTree();
	}

	public Map<String, Tree> searchReportTree() throws Exception {
		return service.searchMenuReportTree();
	}

}
