package com.cct.trn.core.standardui.miscellaneous.domain;

import com.cct.common.CommonModel;
import com.cct.trn.core.dialog.standardui.treeview.domain.TreeDialog;

public class MiscellaneousModel extends CommonModel {

	private static final long serialVersionUID = -8383912104127320200L;
	
	private String inputDate;
	private TreeDialog tree = new TreeDialog();
	private String typeSelected;
	
	// progress bar
	private int progress = 0;
	private boolean flagProcess;
	
	public TreeDialog getTree() {
		return tree;
	}

	public void setTree(TreeDialog tree) {
		this.tree = tree;
	}

	public String getInputDate() {
		return inputDate;
	}

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	public String getTypeSelected() {
		return typeSelected;
	}

	public void setTypeSelected(String typeSelected) {
		this.typeSelected = typeSelected;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public boolean isFlagProcess() {
		return flagProcess;
	}

	public void setFlagProcess(boolean flagProcess) {
		this.flagProcess = flagProcess;
	}

}
