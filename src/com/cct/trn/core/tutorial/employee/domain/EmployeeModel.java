package com.cct.trn.core.tutorial.employee.domain;

import java.util.ArrayList;
import java.util.List;

import com.cct.common.CommonModel;
import com.cct.common.CommonSelectItem;
import com.cct.domain.SearchCriteria;

public class EmployeeModel extends CommonModel {

//	Data Type  ----------------------------------------------------------------------------
	private static final long serialVersionUID = 5256991629723406666L;
	// Object สำหรับเก็บเงื่อนการค้นหา
	private EmployeeSearchCriteria criteria = new  EmployeeSearchCriteria();
	// Object สำหรับใช้ในการ add edit view
	private Employee employee = new Employee();
	// Object สำหรับการเก็บข้อมูล combo ที่อยู่บนหน้าจอ
	private List<CommonSelectItem> listPrefix = new ArrayList<CommonSelectItem>();
	private List<CommonSelectItem> listSex = new ArrayList<CommonSelectItem>();
	private List<CommonSelectItem> listWorkStatus = new ArrayList<CommonSelectItem>();
	
//	Getter and Setter ---------------------------------------------------------------------
	@Override
	public EmployeeSearchCriteria getCriteria() {
		return criteria;
	}
	
	@Override
	public void setCriteria(SearchCriteria criteria) {
		this.criteria = (EmployeeSearchCriteria) criteria;
	}
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<CommonSelectItem> getListPrefix() {
		return listPrefix;
	}

	public void setListPrefix(List<CommonSelectItem> listPrefix) {
		this.listPrefix = listPrefix;
	}

	public List<CommonSelectItem> getListSex() {
		return listSex;
	}

	public void setListSex(List<CommonSelectItem> listSex) {
		this.listSex = listSex;
	}

	public List<CommonSelectItem> getListWorkStatus() {
		return listWorkStatus;
	}

	public void setListWorkStatus(List<CommonSelectItem> listWorkStatus) {
		this.listWorkStatus = listWorkStatus;
	}

	public void setCriteria(EmployeeSearchCriteria criteria) {
		this.criteria = criteria;
	}
	
	
	
	
	

}
