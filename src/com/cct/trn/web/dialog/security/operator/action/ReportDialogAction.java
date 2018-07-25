package com.cct.trn.web.dialog.security.operator.action;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import util.database.CCTConnection;
import util.database.CCTConnectionProvider;
import util.database.CCTConnectionUtil;
import util.log.LogUtil;
import util.web.TreeUtil;

import com.cct.common.CommonDialogAction;
import com.cct.trn.core.config.parameter.domain.DBLookup;
import com.cct.trn.core.dialog.security.operator.domain.ReportDialogModel;
import com.cct.trn.core.dialog.security.operator.service.OperatorDialogManager;

public class ReportDialogAction extends CommonDialogAction {

	private static final long serialVersionUID = -3006412008299272820L;
	
	private ReportDialogModel model = new ReportDialogModel();

	public String search() {
		LogUtil.SEC.debug("searchTree");

		String treeId = "";
		if (ServletActionContext.getRequest().getParameter("treeId") != null) {
			treeId = ServletActionContext.getRequest().getParameter("treeId").trim();
		}

		String treeType = "";
		if (ServletActionContext.getRequest().getParameter("treeType") != null) {
			treeType = ServletActionContext.getRequest().getParameter("treeType").trim();
		}

		String event = "";
		if (ServletActionContext.getRequest().getParameter("event") != null) {
			event = ServletActionContext.getRequest().getParameter("event").trim();
		}

		Map<String, com.cct.domain.Tree> mapTree = new LinkedHashMap<String, com.cct.domain.Tree>();
		CCTConnection conn = null;
		try {
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());
			mapTree = new OperatorDialogManager(conn, getUser(), getLocale()).searchReportTree();
		} catch (Exception e) {
			setMessagePopup(getModel(), null, e.toString());
			LogUtil.SEC.error("", e);
		} finally {
			CCTConnectionUtil.close(conn);
		}

		if (mapTree.size() == 0) {
			getModel().getTreeData().setMapTree(null);
			getModel().getTreeData().setHtmlTree(null);
		} else {
			getModel().getTreeData().setMapTree(mapTree);
			getModel().getTreeData().setHtmlTree(new TreeUtil(treeId, treeType, mapTree, event).genarateTree());
		}

		return "searchListTree";
	}

	@Override
	public ReportDialogModel getModel() {
		return model;
	}

	@Override
	protected Logger loggerInititial() {
		return LogUtil.DIALOG;
	}


}
