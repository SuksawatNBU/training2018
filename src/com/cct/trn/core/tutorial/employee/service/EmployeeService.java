package com.cct.trn.core.tutorial.employee.service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.cct.abstracts.AbstractService;
import com.cct.common.CommonSelectItem;
import com.cct.common.CommonUser;
import com.cct.domain.Transaction;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;
import com.cct.trn.core.config.parameter.domain.SQLPath;
import com.cct.trn.core.tutorial.employee.domain.Employee;
import com.cct.trn.core.tutorial.employee.domain.EmployeeSearch;
import com.cct.trn.core.tutorial.employee.domain.EmployeeSearchCriteria;

import util.calendar.CalendarUtil;
import util.database.CCTConnection;
import util.log.LogUtil;

public class EmployeeService extends AbstractService{
	
	private EmployeeDao dao;
	
	public EmployeeService(CCTConnection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		this.dao = new EmployeeDao();
		this.dao.setSqlPath(SQLPath.EMPLOYEE_SQL);
	}
	
	// นับจำนวนข้อมูล
	protected long countData(EmployeeSearchCriteria criteria) throws Exception {
		long totalResult = 0;
	    try {
	        totalResult = dao.countData(conn, criteria, user, locale);
	    } catch (Exception e) {
	        LogUtil.SEC.error("", e);
	        throw e;
	    }
		return totalResult;
	}
	
	//Service ตรวจสอบบันทึกข้อมูลผู้ใช้ซ้ำ
	protected boolean checkDup(CCTConnection conn, Employee obj, CommonUser user, Locale locale) throws Exception {
		boolean isDup = false;
		try {
			isDup = dao.checkDup(conn, obj, user, locale);
			LogUtil.SEC.debug("isDup: " + isDup);
		}catch (Exception e) {
			LogUtil.SEC.error("", e);
		}
		return isDup;
    }
	
	//Service เพิ่มข้อมูลผู้ใช้
	protected int add(CCTConnection conn, Employee obj, CommonUser user, Locale locale) throws Exception {
		try {
			return dao.add(conn, obj, user, locale);
		}catch (Exception e) {
			LogUtil.SEC.error("", e);
			throw e;
		}
	}
	
	protected int edit(CCTConnection conn, Employee obj, CommonUser user, Locale locale) throws Exception {
		try {
	        return dao.edit(conn, obj, user, locale);
		}catch (Exception e) {
			LogUtil.SEC.error("", e);
			throw e;
		}
	}

	protected int delete(CCTConnection conn, String ids, CommonUser user, Locale locale) throws Exception {
		int id = 0;
		try {
			id = dao.delete(conn, ids, user, locale);
		}catch (Exception e) {
			LogUtil.SEC.error("", e);
			throw e;
		}
		return id;
	}
	
	// สืบค้นข้อมูล
	protected List<EmployeeSearch> search(CCTConnection conn, EmployeeSearchCriteria criteria, CommonUser user, Locale locale) throws Exception {
		List<EmployeeSearch> listResult = new ArrayList<EmployeeSearch>();
	    try {
	    	//2.ค้นหาข้อมูล
	        listResult = dao.search(conn, criteria, user, locale);
	        //3.แปลงข้อมูล
	        listResult = convertValue(listResult);
	    } catch (Exception e) {
	        LogUtil.SEC.error("", e);
	        throw e;
	    }
		return listResult;
	}
	
	//Service ค้นหาข้อมูลผู้ใช้ตาม id
	protected Employee searchById(CCTConnection conn, String id, CommonUser user, Locale locale) throws Exception {
		Employee result = new Employee();
		try {
			//1.ค้นหาข้อมูล
		    result = dao.searchById(conn, id, user, locale);	
		    //2.แปลงรูปแบบวันที่
		    result.setStartWorkDate(convertDate(result.getStartWorkDate(), "outSearchById"));
		    if(result.getWorkStatus().equals("W")){
		    	result.setEndWorkDate(convertDate(result.getEndWorkDate(), "outSearchById"));
		    }
		} catch (Exception e) {
			LogUtil.SEC.error("", e);
		    throw e;
		}
		return result;
	}
	
	protected List<CommonSelectItem> searchPrefixSelectItem(CCTConnection conn, Locale locale){
		List<CommonSelectItem> listResult = new ArrayList<CommonSelectItem>();
		try {
			listResult = dao.searchPrefixSelectItem(conn,locale);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}	
	
	public List<EmployeeSearch> convertValue(List<EmployeeSearch> list) throws Exception{
		List<EmployeeSearch> listResult = new ArrayList<EmployeeSearch>();
		try{
			for(EmployeeSearch employee : list){
	        	EmployeeSearch emp = new EmployeeSearch();
	        	Transaction transaction = new Transaction();
	        	emp.setId(employee.getId());
	        	emp.setFullname(employee.getFullname());
	        	emp.setSex(convertSex(employee.getSex()));	//แปลง
	        	emp.setDepartmentDesc(convertDepartment(employee.getDepartmentDesc()));	//แปลง
	        	emp.setPositionDesc(convertPosition(employee.getPositionDesc()));	//แปลง
	        	emp.setStartWorkDate(convertDate(employee.getStartWorkDate(), "outSearch"));	//แปลง
	        	emp.setEndWorkDate(convertDate(employee.getEndWorkDate(), "outSearch"));	//แปลง
	        	emp.setWorkStatus(convertWorkStatus(employee.getWorkStatus()));		//แปลง
	        	transaction.setCreateUser(employee.getTransaction().getCreateUser());
	        	transaction.setCreateDate(convertDate(employee.getTransaction().getCreateDate(), "outSearch"));	//แปลง
	        	transaction.setUpdateUser(employee.getTransaction().getUpdateUser());
	        	transaction.setUpdateDate(convertDate(employee.getTransaction().getUpdateDate(), "outSearch"));	//แปลง
	        	transaction.setCreateRemark(employee.getTransaction().getCreateRemark());
	        	emp.setTransaction(transaction);
	        	listResult.add(emp);
	        }
		}catch (Exception e) {
			LogUtil.SEC.error("", e);
	        throw e;
		}
		return listResult;
	}
	
	public String convertSex(String sex){
		if(sex.equals("M")) return "ชาย"; 
		else if(sex.equals("F")) return "หญิง";
		else return null;
	}
	
	public String convertDate(String date, String func) throws Exception{
		String parseDate = "";

		// ฟังก์ชันค้นหา
		if(func == "outSearch"){
			if(date.equals(null) || date.equals("")) return null;
			try {
				Calendar calendar = CalendarUtil.getCalendarFromDateString(date, ParameterConfig.getParameter().getDateFormat().getForDisplay(), ParameterConfig.getParameter().getApplication().getDatabaseLocale());
				parseDate = CalendarUtil.getDateStringFromCalendar(calendar, "DD/MM/YYYY");
			} catch (Exception e) {
				throw e;
			}
		}
		//ฟังชันค้นหาข้อมูลตาม ไอดีผู้ใช้
		if(func == "outSearchById"){
			if(date.equals(null) || date.equals("")) return null;
			try {
				Calendar calendar = CalendarUtil.getCalendarFromDateString(date, ParameterConfig.getParameter().getDateFormat().getForDisplay(), ParameterConfig.getParameter().getApplication().getDatabaseLocale());
				parseDate = CalendarUtil.getDateStringFromCalendar(calendar, "DD/MM/YYYY");
			} catch (Exception e) {
				throw e;
			}
		}
		// ฟังก์ชันแก้ไขข้อมูล
		if(func == "inEdit"){
			if(date.equals(null) || date.equals("")) return null;
			try {
				Calendar calendar = CalendarUtil.getCalendarFromDateString(date, ParameterConfig.getParameter().getDateFormat().getForDisplay(), ParameterConfig.getParameter().getApplication().getDatabaseLocale());
				parseDate = CalendarUtil.getDateStringFromCalendar(calendar, "DD/MM/YYYY");
			} catch (Exception e) {
				throw e;
			}
		}
		//วันที่ปัจจุบัน
		if(func == "defaultValue"){
			try {
				Format outputEN = new SimpleDateFormat("dd/MM/yyyy", new Locale("en", "EN"));
				Date conDate = new Date();
				parseDate = outputEN.format(conDate);
			} catch (Exception e) {
				throw e;
			}
		}
		return parseDate;
	}
	
	public String convertDepartment(String department){
		if(department.equals("COMMAND CONTROL")) return "คอมมาน คอนโทรล เทคโนโลยี";
		else if(department.equals("SOMAPA IT")) return "โสมาภา อินฟอร์เมชั่น เทคโนโลยี";
		else return null;
	}
	
	public String convertPosition(String position){
		if(position.equals("System Analysis")) return "นักวิเคราะห์ระบบ";
		else if(position.equals("System Design")) return "ซอฟต์แวร์ดีไซน์";
		else if(position.equals("Programer")) return "โปรแกรมเมอร์";
		else if(position.equals("Tester")) return "นักทดสอบระบบ";
		else if(position.equals("Business analysis")) return "นักวิเคราะห์ธุรกิจ";
		else if(position.equals("Call Center")) return "บริการข้อมูลลูกค้า";
		else if(position.equals("Technical Support")) return "นักบริการด้านเทคนิค";
		else return null;
	}
	
	public String convertWorkStatus(String status){
		if(status.equals("T")) return "พนักงานทดลองงาน";
		else if(status.equals("C")) return "พนักงานปัจจุบัน";
		else if(status.equals("R")) return "อดีตพนักงาน";
		else if(status.equals("W")) return "เลิกจ้าง";
		else return null;
	}
}
