package com.cct.trn.core.dialog.standardui.treeview.service;

import java.util.Map;

import util.database.CCTConnection;

import com.cct.domain.Tree;

public class TreeDialogManager {

	CCTConnection conn;
	TreeDialogDAO dao;

	public TreeDialogManager(CCTConnection conn) {
		this.conn = conn;
		this.dao = new TreeDialogDAO();
	}

	public Map<String, Tree> searchTree() throws Exception {
		return dao.searchTree(conn);
	}

}
