package com.cct.trn.core.security.authorization.domain;

import java.util.Map;

import org.apache.struts2.json.JSONUtil;

import util.log.LogUtil;

public enum PFCode {
	// ---------: Program code and Function code :-----------
	// --: Keys : search, add, edit, view, change, reset, delete, config,
	// import, print, register
	// SEC
	SEC_CONFIG("10100100", "{'" + FunctionType.ADD + "':'10100107'}")
	, SEC_LOGIN("11400300", "{'" + FunctionType.SEARCH + "':'11400390'}")
	, SEC_SIGN_OUT("11400400", "{'" + FunctionType.SEARCH + "':'11400490'}")
	, SEC_VIEW_PROFILE("11400100", "{'" + FunctionType.VIEW + "':'11400190'}")

	, TUR_PROJECTS("10600100", "{'" + FunctionType.SEARCH + "':'10600101', '" + FunctionType.ADD + "':'10600102','" + FunctionType.EDIT + "':'10600103','" + FunctionType.VIEW + "':'10600104','" + FunctionType.CHANGE + "':'10600105'}")
	, TRN_EMAPLOYEE("10700100", "{'" + FunctionType.SEARCH + "':'10700101', '" + FunctionType.ADD + "':'10700102','" + FunctionType.EDIT + "':'10700103','" + FunctionType.VIEW + "':'10700104','" + FunctionType.CHANGE + "':'10700105','" + FunctionType.DELETE + "':'10700106'}")
	
	;
	// ---------: Program code and Function code :-----------
	// ---------: Fucntion code :-----------
	private String searchFunction;
	private String addFunction;
	private String editFunction;
	private String viewFunction;
	private String deleteFunction;
	private String changeFunction;
	private String configFunction;
	private String importFunction;
	private String exportFunction;
	private String printFunction;
	private String resetPasswordFunction;

	private String confirmAddFunction;
	private String confirmEditFunction;
	
	private String activeFunction;
	private String inActiveFunction;
	// ---------: Fucntion code :-----------

	private String programCode;
	private Map<String, String> funcs = null;

	@SuppressWarnings("unchecked")
	private PFCode(String programCode, String functions) {
		this.programCode = programCode;
		try {
			this.funcs = (Map<String, String>) JSONUtil.deserialize(functions);
			searchFunction = funcs.get(FunctionType.SEARCH);
			addFunction = funcs.get(FunctionType.ADD);
			editFunction = funcs.get(FunctionType.EDIT);
			viewFunction = funcs.get(FunctionType.VIEW);
			deleteFunction = funcs.get(FunctionType.DELETE);
			changeFunction = funcs.get(FunctionType.CHANGE);
			configFunction = funcs.get(FunctionType.CONFIG);
			importFunction = funcs.get(FunctionType.IMPORT);
			exportFunction = funcs.get(FunctionType.EXPORT);
			printFunction = funcs.get(FunctionType.PRINT);
			resetPasswordFunction = funcs.get(FunctionType.RESETPASSWORD);

			confirmAddFunction = funcs.get(FunctionType.CONFIRM_ADD);
			confirmEditFunction = funcs.get(FunctionType.CONFIRM_EDIT);
			
			activeFunction = funcs.get(FunctionType.ACTIVE);
			inActiveFunction = funcs.get(FunctionType.INACTIVE);
		} catch (Exception e) {
			LogUtil.SEC.error("", e);
		}
	}

	public String getProgramCode() {
		return programCode;
	}

	public Map<String, String> getFuncs() {
		return funcs;
	}

	public String getFunctionCode(String programCode, String functionType) {
		String functionCode = null;
		for (PFCode code : PFCode.values()) {
			if (code.getProgramCode().equals(programCode)) {
				functionCode = code.getFuncs().get(functionType);
				break;
			}
		}
		return functionCode;
	}

	public String getSearchFunction() {
		return searchFunction;
	}

	public String getSearchFunction(String programCode) {
		return getFunctionCode(programCode, FunctionType.SEARCH);
	}

	public String getAddFunction() {
		return addFunction;
	}

	public String getAddFunction(String programCode) {
		return getFunctionCode(programCode, FunctionType.ADD);
	}

	public String getEditFunction() {
		return editFunction;
	}

	public String getEditFunction(String programCode) {
		return getFunctionCode(programCode, FunctionType.EDIT);
	}

	public String getViewFunction() {
		return viewFunction;
	}

	public String getViewFunction(String programCode) {
		return getFunctionCode(programCode, FunctionType.VIEW);
	}

	public String getDeleteFunction() {
		return deleteFunction;
	}

	public String getDeleteFunction(String programCode) {
		return getFunctionCode(programCode, FunctionType.DELETE);
	}

	public String getChangeFunction() {
		return changeFunction;
	}

	public String getChangeFunction(String programCode) {
		return getFunctionCode(programCode, FunctionType.CHANGE);
	}

	public String getConfigFunction() {
		return configFunction;
	}

	public String getConfigFunction(String programCode) {
		return getFunctionCode(programCode, FunctionType.CONFIG);
	}

	public String getImportFunction() {
		return importFunction;
	}

	public String getImportFunction(String programCode) {
		return getFunctionCode(programCode, FunctionType.IMPORT);
	}

	public String getExportFunction() {
		return exportFunction;
	}

	public String getExportFunction(String programCode) {
		return getFunctionCode(programCode, FunctionType.EXPORT);
	}

	public String getPrintFunction() {
		return printFunction;
	}

	public String getPrintFunction(String programCode) {
		return getFunctionCode(programCode, FunctionType.PRINT);
	}

	public String getResetPasswordFunction() {
		return resetPasswordFunction;
	}

	public String getResetPasswordFunction(String programCode) {
		return getFunctionCode(programCode, FunctionType.RESETPASSWORD);
	}

	public String getConfirmAddFunction() {
		return confirmAddFunction;
	}

	public String getConfirmAddFunction(String programCode) {
		return getFunctionCode(programCode, FunctionType.CONFIRM_ADD);
	}
	
	public String getConfirmEditFunction() {
		return confirmEditFunction;
	}

	public String getConfirmEditFunction(String programCode) {
		return getFunctionCode(programCode, FunctionType.CONFIRM_EDIT);
	}
	
	public String getActiveFunction() {
		return activeFunction;
	}
	
	public String getActiveFunction(String programCode) {
		return getFunctionCode(programCode, FunctionType.ACTIVE);
	}
	
	public String getInActiveFunction() {
		return inActiveFunction;
	}
	
	public String getInActiveFunction(String programCode) {
		return getFunctionCode(programCode, FunctionType.INACTIVE);
	}
	
}