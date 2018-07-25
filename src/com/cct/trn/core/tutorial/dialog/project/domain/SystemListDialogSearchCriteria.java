package com.cct.trn.core.tutorial.dialog.project.domain;

import com.cct.domain.HeaderSorts;
import com.cct.domain.SearchCriteria;

public class SystemListDialogSearchCriteria extends SearchCriteria{

	private static final long serialVersionUID = 547636573686583216L;
	
	private String projectId;
	
	public SystemListDialogSearchCriteria() {
		HeaderSorts[] arrayHeaderSorts = { 
				new HeaderSorts("SYSTEM_NAME", HeaderSorts.ASC, "0")
				, new HeaderSorts("CREATE_DATE", HeaderSorts.ASC, "0")
				, new HeaderSorts("UPDATE_DATE", HeaderSorts.ASC, "0") 
				, new HeaderSorts("ACTIVE", HeaderSorts.ASC, "0") 
		};

		setHeaderSorts(arrayHeaderSorts);
		setHeaderSortsSize(arrayHeaderSorts.length);
	}
	
	// กำหนด default header sorts ให้สำหรับการกด search ครั้งแรก
	public void setDefaultHeaderSorts() {
		for (int i = 0; i < getHeaderSortsSize(); i++) {
			getHeaderSorts()[0].setOrder(HeaderSorts.ASC);
			getHeaderSorts()[i].setSeq("");
		}
		getHeaderSorts()[0].setSeq("0");
		setHeaderSortsSelect("0");
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

}
