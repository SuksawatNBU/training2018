package com.cct.trn.core.dialog.standardui.treeview.domain;

import java.io.Serializable;

import com.cct.domain.TreeData;

public class TreeDialog implements Serializable {

	private static final long serialVersionUID = -8718819484581381498L;

	private TreeData treeData = new TreeData();

	public TreeData getTreeData() {
		return treeData;
	}

	public void setTreeData(TreeData treeData) {
		this.treeData = treeData;
	}

}
