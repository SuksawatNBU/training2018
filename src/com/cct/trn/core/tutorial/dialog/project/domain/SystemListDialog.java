package com.cct.trn.core.tutorial.dialog.project.domain;

import com.cct.common.CommonDomain;

public class SystemListDialog extends CommonDomain{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3454144760871399921L;
	
	private String systemId;
	private String systemName;
	private String createDate;
	private String updateDate;

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

}
