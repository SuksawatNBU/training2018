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
import com.cct.trn.core.dialog.security.operator.domain.ProgramDialogModel;
import com.cct.trn.core.dialog.security.operator.service.OperatorDialogManager;

public class ProgramDialogAction extends CommonDialogAction {

	private static final long serialVersionUID = 3305622154532266367L;
	
	private ProgramDialogModel model = new ProgramDialogModel();


	public String search() {
		LogUtil.DIALOG.debug("searchTree");

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
			mapTree = new OperatorDialogManager(conn, getUser(), getLocale()).searchMenuTree();
		} catch (Exception e) {
			setMessagePopup(getModel(), null, e.toString());
			LogUtil.DIALOG.error("", e);
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

	public ProgramDialogModel getModel() {
		return model;
	}

	@Override
	protected Logger loggerInititial() {
		return LogUtil.DIALOG;
	}


}
