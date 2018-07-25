package com.cct.trn.core.tutorial.employee.service;

import java.util.List;
import java.util.Locale;

import com.cct.common.CommonUser;
import com.cct.trn.core.config.parameter.domain.SQLPath;
import com.cct.trn.core.tutorial.employee.domain.Employee;
import com.cct.trn.core.tutorial.employee.domain.EmployeeSearch;
import com.cct.trn.core.tutorial.employee.domain.EmployeeSearchCriteria;

import util.database.CCTConnection;

public class EmployeeService {
	
	private EmployeeDao dao;
	
	public EmployeeService() {
		this.dao = new EmployeeDao();
		this.dao.setSqlPath(SQLPath.EMPLOYEE_SQL);
	}
	
	protected long countData(CCTConnection conn, EmployeeSearchCriteria criteria) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	protected boolean checkDup(CCTConnection conn, Employee obj, CommonUser user, Locale locale) throws Exception {
        // checkDup: call dao for checkDup add/edit
        return false;
    }
	
	protected int add(CCTConnection conn, Employee obj, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	protected int edit(CCTConnection conn, Employee obj, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	protected int delete(CCTConnection conn, String ids, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	protected List<EmployeeSearch> search(CCTConnection conn, EmployeeSearchCriteria criteria, CommonUser user, Locale locale) throws Exception {
		return dao.search(conn, criteria, user, locale);
	}
	
	protected Employee searchById(CCTConnection conn, String id, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
