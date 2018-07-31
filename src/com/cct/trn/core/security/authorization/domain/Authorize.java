package com.cct.trn.core.security.authorization.domain;

import java.io.Serializable;

public class Authorize implements Serializable {

	private static final long serialVersionUID = -189536501919799652L;

	/** สำหรับการ config สิทธิ์การใช้งาน */
	private boolean isSearch = false;
	private boolean isAdd = false;
	private boolean isEdit = false;
	private boolean isView = false;
	private boolean isDelete = false;
	private boolean isChange = false;
	private boolean isConfig = false;
	private boolean isReset = false;
	private boolean isImport = false;
	private boolean isPrint = false;
	private boolean isRegister = false;
	private boolean isExport = false;
	private boolean isResetPassword = false;

	private boolean isConfirmAdd = false;
	private boolean isConfirmEdit = false;
	
	private boolean isActive = false;
	private boolean isInActive = false;
	
	public boolean isSearch() {
		return isSearch;
	}

	public void setSearch(boolean isSearch) {
		this.isSearch = isSearch;
	}

	public boolean isAdd() {
		return isAdd;
	}

	public void setAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}

	public boolean isEdit() {
		return isEdit;
	}

	public void setEdit(boolean isEdit) {
		this.isEdit = isEdit;
	}

	public boolean isView() {
		return isView;
	}

	public void setView(boolean isView) {
		this.isView = isView;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	public boolean isChange() {
		return isChange;
	}

	public void setChange(boolean isChange) {
		this.isChange = isChange;
	}

	public boolean isConfig() {
		return isConfig;
	}

	public void setConfig(boolean isConfig) {
		this.isConfig = isConfig;
	}

	public boolean isReset() {
		return isReset;
	}

	public void setReset(boolean isReset) {
		this.isReset = isReset;
	}

	public boolean isImport() {
		return isImport;
	}

	public void setImport(boolean isImport) {
		this.isImport = isImport;
	}

	public boolean isPrint() {
		return isPrint;
	}

	public void setPrint(boolean isPrint) {
		this.isPrint = isPrint;
	}

	public boolean isRegister() {
		return isRegister;
	}

	public void setRegister(boolean isRegister) {
		this.isRegister = isRegister;
	}

	public boolean isExport() {
		return isExport;
	}

	public void setExport(boolean isExport) {
		this.isExport = isExport;
	}

	public boolean isResetPassword() {
		return isResetPassword;
	}

	public void setResetPassword(boolean isResetPassword) {
		this.isResetPassword = isResetPassword;
	}

	public boolean isConfirmAdd() {
		return isConfirmAdd;
	}

	public void setConfirmAdd(boolean isConfirmAdd) {
		this.isConfirmAdd = isConfirmAdd;
	}

	public boolean isConfirmEdit() {
		return isConfirmEdit;
	}

	public void setConfirmEdit(boolean isConfirmEdit) {
		this.isConfirmEdit = isConfirmEdit;
	}
	
	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isInActive() {
		return isInActive;
	}

	public void setInActive(boolean isInActive) {
		this.isInActive = isInActive;
	}
	
	
}
