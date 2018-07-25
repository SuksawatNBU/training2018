package com.cct.trn.core.tutorial.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.cct.common.CommonUser;
import com.cct.exception.MaxExceedException;
import com.cct.abstracts.AbstractManager;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;
import com.cct.trn.core.tutorial.employee.domain.Employee;
import com.cct.trn.core.tutorial.employee.domain.EmployeeSearch;
import com.cct.trn.core.tutorial.employee.domain.EmployeeSearchCriteria;

import util.database.CCTConnection;
import util.log.LogUtil;


public class EmployeeManager extends AbstractManager<EmployeeSearchCriteria, EmployeeSearch, Employee, CommonUser, Locale> {

	private EmployeeService service = null;
	
	public EmployeeManager(CCTConnection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<EmployeeSearch> search(EmployeeSearchCriteria criteria) throws Exception {
		
		List<EmployeeSearch> listResult = new ArrayList<EmployeeSearch>();
		try{
			//1.นับจำนวนรายการที่ค้นพบ
			criteria.setTotalResult(service.countData(conn, criteria));
			LogUtil.TRAINING.debug("COUNT DATA [" + criteria.getTotalResult() + "] record.");
			
			if (criteria.getTotalResult() == 0) {
				// Nothing
			} else if ((criteria.isCheckMaxExceed()) && (criteria.getTotalResult() > ParameterConfig.getParameter().getApplication().getMaxExceed())) {
				// เกินจำนวนที่กำหนด
	            throw new MaxExceedException();
	        } else {
	        	// ค้นหาข้อมูล
	            listResult = service.search(conn, criteria, null, null);
	        }

		}catch (Exception e) {
			throw e;
		}
		return listResult;
	}

	@Override
	public Employee searchById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(Employee obj) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edit(Employee obj) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String ids) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateActive(String ids, String activeFlag) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
