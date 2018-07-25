package com.cct.trn.core.dialog.security.operator.domain;

import com.cct.common.CommonModel;
import com.cct.domain.TreeData;

public class ReportDialogModel extends CommonModel {

	private static final long serialVersionUID = 7106185753248104718L;
	
	private TreeData treeData = new TreeData();

	public TreeData getTreeData() {
		return treeData;
	}

	public void setTreeData(TreeData treeData) {
		this.treeData = treeData;
	}

}
