package com.cct.trn.core.tutorial.project.domain;

import com.cct.domain.HeaderSorts;
import com.cct.domain.SearchCriteria;

public class ProjectSearchCriteria extends SearchCriteria {

	/**
	 * 
	 */
	private static final long serialVersionUID = -759305746681527167L;
	
	private String projectCode;			// รหัสโครงการ
	private String projectName;			// ชื่อโครงการ
	private String spmCode;				// รหัส ของ software project manager
	private String spmName;				// ชื่อ ของ software project manager
	private String activeCode;			// รหัส สถานะการใช้งาน
	private String activeDesc;			// ชื่อ สถานะการใช้งาน
	private String departmentId;		// รหัสของ แผนก
	private String departmentName;		// ชื่อ แผนก
	private String startDate;			// วันที่เริ่มต้น
	private String endDate;				// วันที่สิ้นสุด

	public ProjectSearchCriteria() {
		HeaderSorts[] arrayHeaderSorts = {
				new HeaderSorts("", HeaderSorts.ASC, "0")
				, new HeaderSorts("", HeaderSorts.ASC, "0")
				, new HeaderSorts("", HeaderSorts.ASC, "0")
				, new HeaderSorts("", HeaderSorts.ASC, "0")
				, new HeaderSorts("PROJECT_CODE", HeaderSorts.ASC, "0")
				, new HeaderSorts("PROJECT_NAME", HeaderSorts.ASC, "0")
				, new HeaderSorts("SPM_NAME", HeaderSorts.ASC, "0")
				, new HeaderSorts("CREATE_DATE", HeaderSorts.ASC, "0")
				, new HeaderSorts("ACTIVE", HeaderSorts.ASC, "0")
		};
		setHeaderSorts(arrayHeaderSorts);
		setHeaderSortsSize(arrayHeaderSorts.length);
	}

	public void setDefaultHeaderSorts() {
		for (int i = 0; i < getHeaderSortsSize(); i++) {
			getHeaderSorts()[0].setOrder(HeaderSorts.ASC);
			getHeaderSorts()[i].setSeq("");
		}
		getHeaderSorts()[0].setSeq("0");
		setHeaderSortsSelect("4");
	}
	
	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getSpmCode() {
		return spmCode;
	}

	public void setSpmCode(String spmCode) {
		this.spmCode = spmCode;
	}

	public String getSpmName() {
		return spmName;
	}

	public void setSpmName(String spmName) {
		this.spmName = spmName;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public String getActiveDesc() {
		return activeDesc;
	}

	public void setActiveDesc(String activeDesc) {
		this.activeDesc = activeDesc;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}