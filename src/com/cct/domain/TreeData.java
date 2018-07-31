package com.cct.domain;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class TreeData implements Serializable {

	private static final long serialVersionUID = 2906088577218283699L;

	private String htmlTree;
	private Map<String, Tree> mapTree = new LinkedHashMap<String, Tree>();

	public Map<String, Tree> getMapTree() {
		return mapTree;
	}

	public void setMapTree(Map<String, Tree> mapTree) {
		this.mapTree = mapTree;
	}

	public String getHtmlTree() {
		return htmlTree;
	}

	public void setHtmlTree(String htmlTree) {
		this.htmlTree = htmlTree;
	}

}
