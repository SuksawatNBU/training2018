package com.cct.trn.core.tutorial.project.domain;

import java.util.ArrayList;
import java.util.List;

import com.cct.common.CommonModel;
import com.cct.common.CommonSelectItem;
import com.cct.domain.SearchCriteria;

public class ProjectModel extends CommonModel{

	private static final long serialVersionUID = -1359120115499607216L;
	
	/** สำหรับเก็บเงื่อนไขการค้น **/
	private ProjectSearchCriteria criteria = new ProjectSearchCriteria();
	
	/** สำหรับหน้าเพิ่ม, หน้าแก้ไข และแสดง **/
	private Project project = new Project(); 
	
	/** combo **/
	private List<CommonSelectItem> lstActiveStatus = new ArrayList<CommonSelectItem>();
	//private List<CommonSelectItem> lstAllUser = new ArrayList<CommonSelectItem>();

	@Override
	public ProjectSearchCriteria getCriteria() {
		return criteria;
	}

	@Override
	public void setCriteria(SearchCriteria criteria) {
		this.criteria = (ProjectSearchCriteria) criteria;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<CommonSelectItem> getLstActiveStatus() {
		return lstActiveStatus;
	}

	public void setLstActiveStatus(List<CommonSelectItem> lstActiveStatus) {
		this.lstActiveStatus = lstActiveStatus;
	}

	public void setCriteria(ProjectSearchCriteria criteria) {
		this.criteria = criteria;
	}

}