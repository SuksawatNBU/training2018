package com.cct.trn.core.dialog.standardui.treeview.domain;

import com.cct.common.CommonModel;

public class TreeDialogModel extends CommonModel {

	private static final long serialVersionUID = 3095484042616540439L;

	private TreeDialog tree = new TreeDialog();

	public TreeDialog getTree() {
		return tree;
	}

	public void setTree(TreeDialog tree) {
		this.tree = tree;
	}

}
