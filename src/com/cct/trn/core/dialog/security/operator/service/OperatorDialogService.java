package com.cct.trn.core.dialog.security.operator.service;

import java.util.Locale;
import java.util.Map;

import util.database.CCTConnection;

import com.cct.abstracts.AbstractService;
import com.cct.common.CommonUser;
import com.cct.domain.Tree;
import com.cct.trn.core.config.parameter.domain.SQLPath;

public class OperatorDialogService extends AbstractService {
	
	private OperatorDialogDAO dao = null;
	
	public OperatorDialogService(CCTConnection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		dao = new OperatorDialogDAO();
		dao.setSqlPath(SQLPath.DIALOG_SQL);
	}
	
	protected Map<String, Tree> searchMenuProgramTree() throws Exception {
		Tree tree = dao.searchLevelProgram(conn,locale,user);
		return dao.searchMenuProgramTree(conn, locale,user,tree);
	}

	protected Map<String, Tree> searchMenuReportTree() throws Exception {
		Tree tree = dao.searchLevelReport(conn,locale,user);
		return dao.searchMenuReportTree(conn, locale,user, tree);
	}


}
