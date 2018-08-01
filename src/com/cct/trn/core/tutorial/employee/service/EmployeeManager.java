package com.cct.trn.core.tutorial.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.cct.common.CommonSelectItem;
import com.cct.common.CommonUser;
import com.cct.exception.DuplicateException;
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
		service = new EmployeeService(conn, user, locale);
	}

	@Override
	public List<EmployeeSearch> search(EmployeeSearchCriteria criteria) throws Exception {
		
		List<EmployeeSearch> listResult = new ArrayList<EmployeeSearch>();
		try{
			//1.นับจำนวนรายการที่ค้นพบ
			criteria.setTotalResult(service.countData(criteria));
			LogUtil.TRAINING.debug("COUNT DATA [" + criteria.getTotalResult() + "] record.");
			
			if (criteria.getTotalResult() == 0) {
				// Nothing
				System.out.println("--> Nothing");
			} else if ((criteria.isCheckMaxExceed()) && (criteria.getTotalResult() > ParameterConfig.getParameter().getApplication().getMaxExceed())) {
				// เกินจำนวนที่กำหนด
	            throw new MaxExceedException();
	        } else {
	        	// ค้นหาข้อมูล
	        	listResult = service.search(conn, criteria, user, locale);
	        }
		}catch (Exception e) {
			throw e;
		}
		return listResult;
	}

	@Override
	public Employee searchById(String id) throws Exception {
		Employee result = new Employee();
		try {
	        //1.ค้นหาข้อมูลผู้ใช้ตาม id ที่เลือก
			result = service.searchById(conn, id, user, locale);
	 
	    } catch (Exception e) {
	        throw e;
	    }
		return result;
	}

	@Override
	public int add(Employee obj) throws Exception {
		int employeeId = 0 ;
		try {
	        //1.ตรวจสอบบันทึกข้อมูลผู้ใช้ซ้ำ
			boolean isDup = service.checkDup(conn, obj, user, locale);
			if(isDup){
				throw new DuplicateException();
			}
	 
	        //2.Begin transaction
	        conn.setAutoCommit(false);
	 
	        //3.เพิ่มข้อมูลผู้ใช้งาน
	        employeeId = service.add(conn, obj, user, locale);
	 
	        //4. Commit transaction
	        conn.commit();
	 
	    } catch (Exception e) {
	        //5. Rollback transaction เมื่อเกิด Error
	        conn.rollback();
	        throw e;
	    } finally {
	        //6. Set AutoCommit กลับคืนเป็น True
	        conn.setAutoCommit(true);
	    }
	    return employeeId;
	}

	/**
	 * @Description สำหรับแก้ไขข้อมูลผู้ใช้งาน โดยมีการตรวจสอบ รหัสผู้ใช้งานซ้ำก่อน หากซ้ำจะไม่ทำการ แก้ไขข้อมูล
	 */
	@Override
	public int edit(Employee obj) throws Exception {
		try {
	        //1.ตรวจสอบบันทึกข้อมูลผู้ใช้ซ้ำ
	        service.checkDup(conn, obj, user, locale);
	 
	        //2.Begin transaction
	        conn.setAutoCommit(false);
	 
	        //3.แก้ไขข้อมูลผู้ใช้งาน
	        service.edit(conn, obj, user, locale);
	 
	        //4.แก้ไขข้อมูลสิทธิ์ผู้ใช้
	        service.editOperation(obj, Integer.parseInt(obj.getId()));
	 
	        conn.commit();
	 
	    } catch (Exception e) {
	        conn.rollback();
	        throw e;
	    } finally {
	        conn.setAutoCommit(true);
	    }
	    return 0;
	}

	@Override
	public int delete(String ids) throws Exception {
		try {
			return service.delete(conn, ids, user, locale);
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public int updateActive(String ids, String activeFlag) throws Exception {
		return 0;
	}
	
	public List<CommonSelectItem> searchPrefixSelectItem(CCTConnection conn,Locale locale){
		List<CommonSelectItem> listResult = new ArrayList<CommonSelectItem>();
		try {
			listResult = service.searchPrefixSelectItem(conn, locale);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}
}
