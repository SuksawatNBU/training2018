package com.cct.domain;

import java.io.Serializable;

public class HeaderSorts implements Serializable {

	private static final long serialVersionUID = 2489867188624653141L;
	public static final String SPACE = " ";
	public static final String DESC = "DESC";
	public static final String ASC = "ASC";

	private String column;
	private String order;
	private String seq;

	public HeaderSorts() {
		
	}
	
	public HeaderSorts(String column, String order, String seq) {
		this.column = column;
		this.order = order;
		this.seq = seq;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
}
