package com.cct.trn.core.tutorial.employee.service;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.cct.abstracts.AbstractService;
import com.cct.common.CommonSelectItem;
import com.cct.common.CommonUser;
import com.cct.domain.Operator;
import com.cct.domain.Transaction;
import com.cct.trn.core.config.parameter.domain.SQLPath;
import com.cct.trn.core.tutorial.employee.domain.Employee;
import com.cct.trn.core.tutorial.employee.domain.EmployeeSearch;
import com.cct.trn.core.tutorial.employee.domain.EmployeeSearchCriteria;

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
		long totalResult =0;
	    try {
	        totalResult = dao.countData(conn, criteria, user, locale);
	    } catch (Exception e) {
	        LogUtil.SEC.error("", e);
	        throw e;
	    }
		return totalResult;
	}
	
	// ตรวจสอบบันทึกข้อมูลผู้ใช้ซ้ำ
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
	
	protected int add(CCTConnection conn, Employee obj, CommonUser user, Locale locale) throws Exception {
		try {
			//เพิ่มข้อมูลผู้ใช้งาน
			return dao.add(conn, obj, user, locale);
		}catch (Exception e) {
			LogUtil.SEC.error("", e);
			throw e;
		}
	}

	protected int edit(CCTConnection conn, Employee obj, CommonUser user, Locale locale) throws Exception {
		try {
			//เพิ่มข้อมูลผู้ใช้งานและสิทธิ์การใช้งาน
	        return dao.edit(conn, obj, user, locale);
		}catch (Exception e) {
			LogUtil.SEC.error("", e);
			throw e;
		}
	}
	
	
	protected void editOperation(Employee obj, int userId) throws Exception{
		
	}

	protected int delete(CCTConnection conn, String ids, CommonUser user, Locale locale) throws Exception {
		int id = 0;
		try {
			id = dao.delete(conn, ids, user, locale);
		}catch (Exception e) {
			LogUtil.SEC.error("", e);
		}
		return id;
	}
	
	// สืบค้นข้อมูล
	protected List<EmployeeSearch> search(CCTConnection conn, EmployeeSearchCriteria criteria, CommonUser user, Locale locale) throws Exception {
		List<EmployeeSearch> listResult = new ArrayList<EmployeeSearch>();
	    try {
	        listResult = dao.search(conn, criteria, user, locale);
	        // แปลงข้อมูล
	        listResult = convertValue(listResult);
	    } catch (Exception e) {
	        LogUtil.SEC.error("", e);
	        throw e;
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
	        	emp.setStartWorkDate(convertDate(employee.getStartWorkDate(), 1));	//แปลง
	        	emp.setEndWorkDate(convertDate(employee.getEndWorkDate(), 1));	//แปลง
	        	emp.setWorkStatus(convertWorkStatus(employee.getWorkStatus()));		//แปลง
	        	transaction.setCreateUser(employee.getTransaction().getCreateUser());
	        	transaction.setCreateDate(convertDate(employee.getTransaction().getCreateDate(), 2));	//แปลง
	        	transaction.setUpdateUser(employee.getTransaction().getUpdateUser());
	        	transaction.setUpdateDate(convertDate(employee.getTransaction().getUpdateDate(), 2));	//แปลง
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
	
	public String convertDate(String date, int func) throws ParseException{
		String dateTH = "";
		Format formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("th", "TH"));
		
		if(func == 1){
			if(date.equals(null) || date.equals("")) return null;
			try {
				DateFormat df = new SimpleDateFormat("dd/MM/yy");
				Date parseDate = df.parse(date);
				dateTH = formatter.format(parseDate);
			} catch (Exception e) {
				throw e;
			}
		}
		else if(func == 2){
			if(date.equals(null) || date.equals("")) return null;
			try {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
				Date parseDate = df.parse(date);
				dateTH = formatter.format(parseDate);
			} catch (Exception e) {
				throw e;
			}
		}

		// ถ้ามี 0 นำหน้าจะตัดออก
		/*boolean check = dateTH.startsWith("0");
		if (check) {
			dateTH = dateTH.substring(1, dateTH.length());
		}*/
		return dateTH;
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
		if(status.equals("N")) return "N";
		else if(status.equals("W")) return "พนักงานประจำ";
		else return null;
	}
	
	//Service ค้นหาข้อมูลผู้ใช้ตาม id
	protected Employee searchById(CCTConnection conn, String id, CommonUser user, Locale locale) throws Exception {
		Employee result = new Employee();
	    try {
	    	result = dao.searchById(conn, id, user, locale);
	    } catch (Exception e) {
	        LogUtil.SEC.error("", e);
	        throw e;
	    }
		return result;
	}
	
	//Service ค้นหาสิทธิ์การใช้งานโปรแกรมของผู้ใช้
	protected String searchOperatorByUserID(CCTConnection conn,String userId) throws Exception{
	    try {
	        return  dao.searchOperatorDataListById(conn, userId, locale);
	    } catch (Exception e) {
	        LogUtil.SEC.error("", e);
	        throw e;
	    }
	}
	
	//3.Service ค้นหารายละเอียดเมนูและสิทธิ์ 
	protected List<Operator> searchOperatorById(CCTConnection conn,String operatorById) throws Exception{
	    List<Operator> lstOperater = new ArrayList<Operator>();
	 
	    try {
	        /*Map<String, Tree> mapOperator = dao.searchOperatorByOperatorId(conn, operatorById, locale);
	        for (String key : mapOperator.keySet()) {
	            Operator operator = (Operator) mapOperator.get(key);
	             
	            if (operator.getOperatorType().equals(MenuUtil.OPERATOR_TYPE_FUNCTION)) {
	                String groupId = getGroupOperatorId(mapOperator, operator.getCurrentId());
	                operator.setFunctionName(getLableFunction(mapOperator, groupId));
	                operator.setMenuName(getLableMenu(mapOperator, groupId));
	                operator.setSystemName(getLableSystem(mapOperator, groupId));
	                operator.setParentIds(groupId);
	                lstOperater.add(operator);
	            }
	        }*/
	    } catch (Exception e) {
	        LogUtil.SEC.error("", e);
	        throw e;
	    }
	    return lstOperater;
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

}
