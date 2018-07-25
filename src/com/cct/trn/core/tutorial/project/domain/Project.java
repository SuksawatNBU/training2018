package com.cct.trn.core.tutorial.project.domain;

import com.cct.common.CommonDomain;

public class Project extends CommonDomain {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7203031935890932040L;

	private String projectCode;			// รหัสโครงการ
	private String projectName;			// ชื่อโครงการ
	private String spmCode;				// รหัส ของ software project manager
	private String spmName;				// ชื่อ ของ software project manager
	private String path;				// folder path ที่ไว้เก็บ source code robot test
	private String createDate;			// วันที่สร้างโครงการ
	private String departmentId;
	private String departmentName;

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

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
}
