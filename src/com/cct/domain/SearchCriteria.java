package com.cct.domain;

import java.io.Serializable;
import java.util.Locale;

public abstract class SearchCriteria implements Serializable {

	private static final long serialVersionUID = -7032911859591086173L;

	/* เป็น False เมื่อผู้ใช้กดยืนยันที่หน้าจอให้แสดงผลกรณีเกิน Maxexceed */
	private boolean checkMaxExceed = true;
	private boolean alertMaxExceed = false;

	private String criteriaKey;
	private int linePerPage;
	private int start = 0;
	private long totalResult = 0;

	private HeaderSorts[] headerSorts;
	private String headerSortsSelect;
	private String orderSortsSelect;	/** Anusorn.l 2015-06-19 ใช้สำหรับ datatable **/
	private int headerSortsSize;
	
	private String defaultHeaderSortsSelect;
	private String defaultOrderSortsSelect;

	private String statusForUpdate;
	private String selectedIds;
	private String urlSearchAction; // เพิ่มเก็บ url ของ search action เพื่อให้ตัวกลางวาด table ใช้ตอน page navigate

	// nanthaporn.p 20140826
	private String navigatePopup; // สำหรับกำหนดการใช้งาน page navigate ที่หน้า dialog popup
	
	// for progressBar
	private String keyPro;
	
	public String getOrderList() {
		String orderReturn = null;
		try {
			StringBuilder orderList = new StringBuilder();
			if ((headerSortsSelect != null) && (headerSortsSelect.trim().equals("") == false)) {
				String[] indexs = headerSortsSelect.split(",");
				for (int i = 0; i < indexs.length; i++) {
					if (headerSorts[Integer.parseInt(indexs[i])].getOrder().trim().equals("") == false) {
						orderList.append(", ");
						orderList.append(headerSorts[Integer.parseInt(indexs[i])].getColumn().trim());
						orderList.append(" ");
						orderList.append(headerSorts[Integer.parseInt(indexs[i])].getOrder().trim());
					}
				}
			}

			if (orderList.length() > 0) {
				orderList.deleteCharAt(0);
			} else {
				if (orderList.length() > 0) {
					orderList.append(headerSorts[0].getColumn().trim());
					orderList.append(" ");
					orderList.append(headerSorts[0].getOrder().trim());
				}
			}
			orderReturn = orderList.toString();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return orderReturn;
	}
	
	public String getOrderList(Integer[] columnIndex, Locale locale) {
		String orderReturn = null;
		try {
			StringBuilder orderList = new StringBuilder();
			if ((headerSortsSelect != null) && (headerSortsSelect.trim().equals("") == false)) {
				String[] indexs = headerSortsSelect.split(",");
				for (int i = 0; i < indexs.length; i++) {
					if (headerSorts[Integer.parseInt(indexs[i])].getOrder().trim().equals("") == false) {
						orderList.append(", ");
						orderList.append(headerSorts[Integer.parseInt(indexs[i])].getColumn().trim());
						if (columnIndex[Integer.parseInt(indexs[i])] != null) {
							orderList.append("_" + locale.getLanguage().toUpperCase());	
						}
						orderList.append(" ");
						orderList.append(headerSorts[Integer.parseInt(indexs[i])].getOrder().trim());
					}
				}
			}

			if (orderList.length() > 0) {
				orderList.deleteCharAt(0);
			} else {
				if (orderList.length() > 0) {
					orderList.append(headerSorts[0].getColumn().trim());
					orderList.append(" ");
					orderList.append(headerSorts[0].getOrder().trim());
				}
			}
			orderReturn = orderList.toString();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return orderReturn;
	}

	public String getCriteriaKey() {
		return criteriaKey;
	}

	public void setCriteriaKey(String criteriaKey) {
		this.criteriaKey = criteriaKey;
	}

	public int getLinePerPage() {
		return linePerPage;
	}

	public void setLinePerPage(int linePerPage) {
		this.linePerPage = linePerPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public long getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(long totalResult) {
		this.totalResult = totalResult;
	}

	public HeaderSorts[] getHeaderSorts() {
		return headerSorts;
	}

	public void setHeaderSorts(HeaderSorts[] headerSorts) {
		this.headerSorts = headerSorts;
	}

	public int getHeaderSortsSize() {
		return headerSortsSize;
	}

	public void setHeaderSortsSize(int headerSortsSize) {
		this.headerSortsSize = headerSortsSize;
	}

	public String getHeaderSortsSelect() {
		return headerSortsSelect;
	}

	public void setHeaderSortsSelect(String headerSortsSelect) {
		this.headerSortsSelect = headerSortsSelect;
	}

	public String getSelectedIds() {
		return selectedIds;
	}

	public void setSelectedIds(String selectedIds) {
		this.selectedIds = selectedIds;
	}

	public String getStatusForUpdate() {
		return statusForUpdate;
	}

	public void setStatusForUpdate(String statusForUpdate) {
		this.statusForUpdate = statusForUpdate;
	}

	public boolean isCheckMaxExceed() {
		return checkMaxExceed;
	}

	public void setCheckMaxExceed(boolean checkMaxExceed) {
		this.checkMaxExceed = checkMaxExceed;
	}

	public void setDefaultHeaderSorts() {

	}

	public String getUrlSearchAction() {
		return urlSearchAction;
	}

	public void setUrlSearchAction(String urlSearchAction) {
		this.urlSearchAction = urlSearchAction;
	}

	public String getNavigatePopup() {
		return navigatePopup;
	}

	public void setNavigatePopup(String navigatePopup) {
		this.navigatePopup = navigatePopup;
	}

	public String getOrderSortsSelect() {
		return orderSortsSelect;
	}

	public void setOrderSortsSelect(String orderSortsSelect) {
		this.orderSortsSelect = orderSortsSelect;
	}

	public String getKeyPro() {
		return keyPro;
	}

	public void setKeyPro(String keyPro) {
		this.keyPro = keyPro;
	}
	
	public boolean isAlertMaxExceed() {
		return alertMaxExceed;
	}

	public void setAlertMaxExceed(boolean alertMaxExceed) {
		this.alertMaxExceed = alertMaxExceed;
	}

	public String getDefaultHeaderSortsSelect() {
		return defaultHeaderSortsSelect;
	}

	public void setDefaultHeaderSortsSelect(String defaultHeaderSortsSelect) {
		this.defaultHeaderSortsSelect = defaultHeaderSortsSelect;
	}

	public String getDefaultOrderSortsSelect() {
		return defaultOrderSortsSelect;
	}

	public void setDefaultOrderSortsSelect(String defaultOrderSortsSelect) {
		this.defaultOrderSortsSelect = defaultOrderSortsSelect;
	}

}
