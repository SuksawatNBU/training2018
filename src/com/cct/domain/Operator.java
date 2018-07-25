package com.cct.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Operator extends Tree {

	private static final long serialVersionUID = 7707784123126207916L;

	private String url;

	private String operatorId;
	private String parentOperatorIds;
	private Map<String, Operator> mapOperator = new LinkedHashMap<String, Operator>();

	private String operatorType;
	private String visible;

	private String systemName;
	private String menuName;
	private String functionName;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public Map<String, Operator> getMapOperator() {
		return mapOperator;
	}

	public void setMapOperator(Map<String, Operator> mapOperator) {
		this.mapOperator = mapOperator;
	}

	public String getParentOperatorIds() {
		return parentOperatorIds;
	}

	public void setParentOperatorIds(String parentOperatorIds) {
		this.parentOperatorIds = parentOperatorIds;
	}

	public String getOperatorType() {
		return operatorType;
	}

	public void setOperatorType(String operatorType) {
		this.operatorType = operatorType;
	}
	
	public String getVisible() {
		return visible;
	}

	public void setVisible(String visible) {
		this.visible = visible;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
}