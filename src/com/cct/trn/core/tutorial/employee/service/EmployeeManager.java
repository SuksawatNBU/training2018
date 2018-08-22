package com.cct.trn.core.tutorial.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cct.common.CommonSelectItem;
import com.cct.common.CommonUser;
import com.cct.exception.DuplicateException;
import com.cct.exception.MaxExceedException;
import com.cct.abstracts.AbstractManager;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;
import com.cct.trn.core.tutorial.employee.domain.Employee;
import com.cct.trn.core.tutorial.employee.domain.EmployeeModel;
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
	
	public List<EmployeeSearch> search2(EmployeeSearchCriteria criteria) throws Exception {
		
		List<EmployeeSearch> listResult = new ArrayList<EmployeeSearch>();
		try{
			//1.นับจำนวนรายการที่ค้นพบ
			criteria.setTotalResult(service.countData(criteria));
			LogUtil.TRAINING.debug("COUNT DATA [" + criteria.getTotalResult() + "] record.");
			
			if (criteria.getTotalResult() == 0) {
				// Nothing
			} else if ((criteria.isCheckMaxExceed()) && (criteria.getTotalResult() > ParameterConfig.getParameter().getApplication().getMaxExceed())) {
				// เกินจำนวนที่กำหนด
	            throw new MaxExceedException();
	        } else {
	        	// ค้นหาข้อมูล
	        	listResult = service.search2(conn, criteria, user, locale);
	        }
		}catch (Exception e) {
			throw e;
		}
		return listResult;
	}
	
public List<EmployeeSearch> searchPopup(EmployeeSearchCriteria criteria) throws Exception {
		
		List<EmployeeSearch> listResult = new ArrayList<EmployeeSearch>();
		try{
			//1.นับจำนวนรายการที่ค้นพบ
			criteria.setTotalResult(service.countData(criteria));
			LogUtil.TRAINING.debug("COUNT DATA [" + criteria.getTotalResult() + "] record.");
			
			if (criteria.getTotalResult() == 0) {
				// Nothing
			} else if ((criteria.isCheckMaxExceed()) && (criteria.getTotalResult() > ParameterConfig.getParameter().getApplication().getMaxExceed())) {
				// เกินจำนวนที่กำหนด
	            throw new MaxExceedException();
	        } else {
	        	// ค้นหาข้อมูล
	        	listResult = service.searchPopup(conn, criteria);
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
		
		//1.ตรวจสอบบันทึกข้อมูลผู้ใช้ซ้ำ
		boolean isDup = service.checkDup(conn, obj, user, locale);
		if(isDup){
			throw new DuplicateException();
		}
		
		try {
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
		int employeeId = 0 ;
		
		//1.ตรวจสอบบันทึกข้อมูลผู้ใช้ซ้ำ
		boolean isDup = service.checkDup(conn, obj, user, locale);
        if(isDup){
			throw new DuplicateException();
		}
        
		try {
	        //2.Begin transaction
	        conn.setAutoCommit(false);
	 
	        //3.แก้ไขข้อมูลผู้ใช้งาน
	        employeeId = service.edit(conn, obj, user, locale);

	        conn.commit();
	 
	    } catch (Exception e) {
	        conn.rollback();
	        throw e;
	    } finally {
	        conn.setAutoCommit(true);
	    }
	    return employeeId;
	}
	
	public int edit2(EmployeeModel obj) throws Exception {
		try {
	        conn.setAutoCommit(false);
	         
	        //loop บันทึก/ลบ ข้อมูล 
	        for (EmployeeSearch tableAddDeleteRow : obj.getListResult()) {
	            if (tableAddDeleteRow.getId().equals("") && !tableAddDeleteRow.getDeleteFlag().equals("Y")) { // เป็น pk ว่าง และ deleteFlag ไม่เป็น Y 
	                //กรณีเพิ่ม
	                /*dao.add(conn, tableAddDeleteRow, user, locale);*/
	                 
	            } else if(tableAddDeleteRow.getDeleteFlag().equals("Y") && !tableAddDeleteRow.getId().equals("")) { // เป็น deleteFlagเป็น Y และมี pk ไว้ลบ 
	                //กรณีลบ
	                /*dao.delete(conn, tableAddDeleteRow.getId(), user, locale);*/
	            }
	        }
	         
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
	
	public Employee defaultValue(){
		Employee emp = new Employee();
		try {
	        emp.setSex("M");
	        emp.setStartWorkDate(service.convertDate(null, "defaultDate"));
	        emp.setWorkStatus("T");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emp;
	}
	
	public XSSFWorkbook exportExcelEmployee(CCTConnection conn, EmployeeSearchCriteria criteria, Locale locale) throws Exception{
		List<EmployeeSearch> listResult = new ArrayList<EmployeeSearch>();
		try{
			//1. ค้นหาข้อมูลมาออกรายงาน
	        listResult = service.searchExportEmployee(conn, criteria, locale);
	        LogUtil.TRAINING.debug("listEmployee [" + listResult.size() + "] record.");
		}catch (Exception e) {
			throw e;
		}
		
		//2. ถ้ามี list สร้างรายงาน
		if (listResult.size() > 0) {
			XSSFWorkbook workbook = service.exportExcelEmployee(listResult, criteria);
			return workbook;
		}
		return null;
	}
	
	public String readReport() throws Exception{
		
		return null;
	}
}
