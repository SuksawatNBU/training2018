package com.cct.trn.core.security.authorization.service;

import java.util.Map;

import util.log.LogUtil;

import com.cct.domain.Operator;
import com.cct.trn.core.security.authorization.domain.Authorize;
import com.cct.trn.core.security.authorization.domain.PFCode;

public class AuthorizationService {

	public Authorize checkAuthorize(PFCode pfcode, Map<String, Operator> mapFunction) throws Exception {
		LogUtil.SEC.info("");

		Authorize ath = new Authorize();
		try {
			if (mapFunction.get(pfcode.getAddFunction()) != null) {
				ath.setAdd(true);
			}
			if (mapFunction.get(pfcode.getSearchFunction()) != null) {
				ath.setSearch(true);
			}
			if (mapFunction.get(pfcode.getEditFunction()) != null) {
				ath.setEdit(true);
			}
			if (mapFunction.get(pfcode.getViewFunction()) != null) {
				ath.setView(true);
			}
			if (mapFunction.get(pfcode.getChangeFunction()) != null) {
				ath.setChange(true);
			}
			if (mapFunction.get(pfcode.getConfigFunction()) != null) {
				ath.setConfig(true);
			}
			if (mapFunction.get(pfcode.getImportFunction()) != null) {
				ath.setImport(true);
			}
			if (mapFunction.get(pfcode.getPrintFunction()) != null) {
				ath.setPrint(true);
			}
			if (mapFunction.get(pfcode.getExportFunction()) != null) {
				ath.setExport(true);
			}
			if (mapFunction.get(pfcode.getResetPasswordFunction()) != null) {
				ath.setResetPassword(true);
			}
			if (mapFunction.get(pfcode.getConfirmAddFunction()) != null) {
				ath.setConfirmAdd(true);
			}
			if (mapFunction.get(pfcode.getConfirmEditFunction()) != null) {
				ath.setConfirmEdit(true);
			}
			if (mapFunction.get(pfcode.getActiveFunction()) != null) {
				ath.setActive(true);
			}
			if (mapFunction.get(pfcode.getInActiveFunction()) != null) {
				ath.setInActive(true);
			}
			if (mapFunction.get(pfcode.getDeleteFunction()) != null) {
				ath.setDelete(true);
			}
			
		} catch (Exception e) {
			throw e;
		}
		return ath;
	}
}
