package com.cct.trn.core.tutorial.employee.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.cct.abstracts.AbstractDAO;
import com.cct.common.CommonSelectItem;
import com.cct.common.CommonUser;
import com.cct.domain.Transaction;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;
import com.cct.trn.core.tutorial.employee.domain.Employee;
import com.cct.trn.core.tutorial.employee.domain.EmployeeSearch;
import com.cct.trn.core.tutorial.employee.domain.EmployeeSearchCriteria;

import util.calendar.CalendarUtil;
import util.database.CCTConnection;
import util.database.CCTConnectionUtil;
import util.database.SQLUtil;
import util.log.LogUtil;
import util.string.StringUtil;
import util.type.StringType.ResultType;

public class EmployeeDao extends AbstractDAO<EmployeeSearchCriteria, EmployeeSearch, Employee, CommonUser, Locale> {

	/**
     * ใช้สำหรับนับจำนวนข้อมูลหน้าค้นหา
     * */
	@Override
	protected long countData(CCTConnection conn, EmployeeSearchCriteria criteria, CommonUser user, Locale locale) throws Exception {
		int count = 0;
		
		String startDate = StringUtil.replaceSpecialString(criteria.getStartWorkDate(), conn.getDbType(), ResultType.NULL);
		if (startDate != null) {
			Calendar startCalendar = CalendarUtil.getCalendarFromDateString(startDate, ParameterConfig.getParameter().getDateFormat().getForDisplay(), ParameterConfig.getParameter().getApplication().getDatabaseLocale());
			startDate = CalendarUtil.getDateStringFromCalendar(startCalendar, "YYYY-MM-DD HH:mm:ss");
		}	
		String endDate = StringUtil.replaceSpecialString(criteria.getEndWorkDate(), conn.getDbType(), ResultType.NULL);
		if (endDate != null) {
			Calendar endCalendar = CalendarUtil.getCalendarFromDateString(endDate, ParameterConfig.getParameter().getDateFormat().getForDisplay(), ParameterConfig.getParameter().getApplication().getDatabaseLocale());
			endDate = CalendarUtil.getDateStringFromCalendar(endCalendar, "YYYY-MM-DD HH:mm:ss");
		}
		
	    int paramIndex = 0;
	    Object[] params = new Object[9];
	    params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getPrefixId(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getFullname(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getNickname(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getSex(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getDepartmentId(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getPositionId(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = startDate;
        params[paramIndex++] = endDate;
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getWorkStatus(), conn.getDbType(), ResultType.NULL);
        
	    String sql = SQLUtil.getSQLString(conn.getSchemas()
	            , getSqlPath().getClassName()
	            , getSqlPath().getPath()
	            , "searchCountEmployee"
	            , params);
	    LogUtil.SEC.debug("SQL : " + sql);
	    
	    Statement stmt = null;
	    ResultSet rst = null;
	    try {
	        stmt = conn.createStatement();
	        rst = stmt.executeQuery(sql);
	        if (rst.next()) {
	            count = rst.getInt("TOT");
	        }
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        CCTConnectionUtil.closeAll(rst, stmt);
	    }
	    return count;
	}

	/**
     * ใช้สำหรับค้นหาข้อมูล
     * */
	@Override
	protected List<EmployeeSearch> search(CCTConnection conn, EmployeeSearchCriteria criteria, CommonUser user, Locale locale) throws Exception {
		int paramIndex = 0;
		List<EmployeeSearch> listResult = new ArrayList<EmployeeSearch>();
		
		String startDate = StringUtil.replaceSpecialString(criteria.getStartWorkDate(), conn.getDbType(), ResultType.NULL);
		if (startDate != null) {
			Calendar startCalendar = CalendarUtil.getCalendarFromDateString(startDate, ParameterConfig.getParameter().getDateFormat().getForDisplay(), ParameterConfig.getParameter().getApplication().getDatabaseLocale());
			startDate = CalendarUtil.getDateStringFromCalendar(startCalendar, "YYYY-MM-DD HH:mm:ss");
		}	
		String endDate = StringUtil.replaceSpecialString(criteria.getEndWorkDate(), conn.getDbType(), ResultType.NULL);
		if (endDate != null) {
			Calendar endCalendar = CalendarUtil.getCalendarFromDateString(endDate, ParameterConfig.getParameter().getDateFormat().getForDisplay(), ParameterConfig.getParameter().getApplication().getDatabaseLocale());
			endDate = CalendarUtil.getDateStringFromCalendar(endCalendar, "YYYY-MM-DD HH:mm:ss");
		}
		
        Object[] params = new Object[11];
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getPrefixId(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getFullname(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getNickname(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getSex(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getDepartmentId(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getPositionId(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = startDate;
        params[paramIndex++] = endDate;
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getWorkStatus(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = criteria.getStart() - 1;
        params[paramIndex++] = criteria.getLinePerPage();
		
        String sql = SQLUtil.getSQLString(conn.getSchemas()
                , getSqlPath().getClassName()
                , getSqlPath().getPath()
                , "searchEmployee"
                , params);
        LogUtil.SEC.debug("SQL : " + sql);
        
        Statement stmt = null;
        ResultSet rst = null;
        try {
            stmt = conn.createStatement();
            rst = stmt.executeQuery(sql);
            while (rst.next()) {
            	EmployeeSearch result = new EmployeeSearch();
            	Transaction transaction = new Transaction();
            	result.setId(StringUtil.nullToString(rst.getString("EMPLOYEE_ID")));
            	result.setFullname(StringUtil.nullToString(rst.getString("FULLNAME")));
            	result.setSex(StringUtil.nullToString(rst.getString("SEX")));
            	result.setDepartmentDesc(StringUtil.nullToString(rst.getString("DEPARTMENT_NAME")));
            	result.setPositionDesc(StringUtil.nullToString(rst.getString("POSITION_NAME")));
            	result.setStartWorkDate(StringUtil.nullToString(rst.getString("START_WORK_DATE")));
            	result.setEndWorkDate(StringUtil.nullToString(rst.getString("END_WORK_DATE")));
            	result.setWorkStatus(StringUtil.nullToString(rst.getString("WORK_STATUS")));
            	transaction.setCreateUser(StringUtil.nullToString(rst.getString("CREATE_USER")));
            	transaction.setCreateDate(StringUtil.nullToString(rst.getString("CREATE_DATE")));
            	transaction.setUpdateUser(StringUtil.nullToString(rst.getString("UPDATE_USER")));
            	transaction.setUpdateDate(StringUtil.nullToString(rst.getString("UPDATE_DATE")));
            	transaction.setCreateRemark(StringUtil.nullToString(rst.getString("REMARK")));
            	result.setTransaction(transaction);
                listResult.add(result);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            CCTConnectionUtil.closeAll(rst, stmt);
        }
		return listResult;
	}

	/**
	* @Description ค้นหาข้อมูลผู้ใช้ตาม id ที่เลือก
	* @return Employee
	* @throws Exception
	*/
	@Override
	protected Employee searchById(CCTConnection conn, String id, CommonUser user, Locale locale) throws Exception {

		int paramIndex = 0;
        Object[] params = new Object[1];
        params[paramIndex++] = StringUtil.replaceSpecialString(id, conn.getDbType(), ResultType.NULL);
		
        String sql = SQLUtil.getSQLString(conn.getSchemas()
                , getSqlPath().getClassName()
                , getSqlPath().getPath()
                , "searchByIdEmployee"
                , params);
        LogUtil.SEC.debug("SQL : " + sql);
        
        Employee result = new Employee();
        Statement stmt = null;
        ResultSet rst = null;
        try {
            stmt = conn.createStatement();
            rst = stmt.executeQuery(sql);
            if (rst.next()) {
            result.setId(StringUtil.nullToString(rst.getString("EMPLOYEE_ID")));
            result.setPrefixId(StringUtil.nullToString(rst.getString("PREFIX_ID")));
            result.setPrefixName(StringUtil.nullToString(rst.getString("PREFIX_NAME")));
            result.setName(StringUtil.nullToString(rst.getString("NAME")));
            result.setSurname(StringUtil.nullToString(rst.getString("SURNAME")));
            result.setNickName(StringUtil.nullToString(rst.getString("NICK_NAME")));
            result.setSex(StringUtil.nullToString(rst.getString("SEX")));
            result.setDepartmentId(StringUtil.nullToString(rst.getString("DEPARTMENT_ID")));
           	result.setDepartmentDesc(StringUtil.nullToString(rst.getString("DEPARTMENT_NAME")));
            result.setPositionId(StringUtil.nullToString(rst.getString("POSITION_ID")));
            result.setPositionDesc(StringUtil.nullToString(rst.getString("POSITION_NAME")));
            result.setStartWorkDate(StringUtil.nullToString(rst.getString("START_WORK_DATE")));
            result.setEndWorkDate(StringUtil.nullToString(rst.getString("END_WORK_DATE")));
            result.setWorkStatus(StringUtil.nullToString(rst.getString("WORK_STATUS")));
            
            Transaction transaction = new Transaction();
            transaction.setCreateUser(StringUtil.nullToString(rst.getString("CREATE_USER")));
            transaction.setCreateDate(StringUtil.nullToString(rst.getString("CREATE_DATE")));
            transaction.setUpdateUser(StringUtil.nullToString(rst.getString("UPDATE_USER")));
            transaction.setUpdateDate(StringUtil.nullToString(rst.getString("UPDATE_DATE")));
            transaction.setCreateRemark(StringUtil.nullToString(rst.getString("REMARK")));
            result.setTransaction(transaction);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            CCTConnectionUtil.closeAll(rst, stmt);
        }
		return result;
	}

	/**
	* @Description ตรวจสอบข้อมูล ผู้ใช้ซ้ำ  ทั้งเพิ่มและแก้ไขใช้ร่วมกัน  โดยตรวจสอบจาก  รหัสผ่านใช้งาน(login)
	* @return boolean
	* @throws Exception
	*/
	@Override
	protected boolean checkDup(CCTConnection conn, Employee obj, CommonUser user, Locale locale) throws Exception {
		boolean checkDup = false;
		int paramIndex = 0;
		
        Object[] params = new Object[6];
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getName(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getSurname(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getNickName(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getSex(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getPositionId(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getId(), conn.getDbType(), ResultType.NULL);
		
        System.out.println("obj.getId() : " + obj.getId());
        String sql = SQLUtil.getSQLString(conn.getSchemas()
                , getSqlPath().getClassName()
                , getSqlPath().getPath()
                , "checkDupEmployee"
                , params);
        LogUtil.SEC.debug("SQL : " + sql);
        
        Statement stmt = null;
        ResultSet rst = null;
        try {
            stmt = conn.createStatement();
            rst = stmt.executeQuery(sql);
            if (rst.next()) {
            	if(rst.getLong(1) != 0){
            		checkDup = true;
            	}
            }
        } catch (Exception e) {
            throw e;
        } finally {
            CCTConnectionUtil.closeAll(rst, stmt);
        }
		return checkDup;
	}
	
	/**
	* @Description สำหรับเพิ่มข้อมูลผู้ใช้งาน
	* @return int
	* @throws Exception
	*/
	@Override
	protected int add(CCTConnection conn, Employee obj, CommonUser user, Locale locale) throws Exception {
		int paramIndex = 0;
	    int id = 0;
	    
	    String startDate = StringUtil.replaceSpecialString(obj.getStartWorkDate(), conn.getDbType(), ResultType.NULL);
		if (startDate != null) {
			Calendar startCalendar = CalendarUtil.getCalendarFromDateString(startDate, ParameterConfig.getParameter().getDateFormat().getForDisplay(), ParameterConfig.getParameter().getApplication().getDatabaseLocale());
			startDate = CalendarUtil.getDateStringFromCalendar(startCalendar, ParameterConfig.getParameter().getDateFormat().getForDatabaseInsert());
		}

	    Object[] params = new Object[10];
	    params[paramIndex++] = StringUtil.replaceSpecialString(obj.getName(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getSurname(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getNickName(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getPrefixId(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getSex(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getPositionId(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = startDate;
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getWorkStatus(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getTransaction().getCreateRemark(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(user.getUserId(), conn.getDbType(), ResultType.NULL);
        
        String sql = SQLUtil.getSQLString(conn.getSchemas()
        		, getSqlPath().getClassName()
        		, getSqlPath().getPath()
        		, "insertEmployee"
        		, params);
        LogUtil.SEC.debug(sql);
        
        try {
            id = conn.executeInsertStatement(sql);
        } catch (Exception e) {
            throw e;
        }
        return id ;
	}
	
	/**
	* @Description สำหรับเพิ่มสิทธิ์ผู้ใช้งาน
	* @throws Exception
	*/
	protected void addOperator(CCTConnection conn,int userId ,String operatorId ,String languageId) throws Exception {
	    try {
	        
	    } catch (Exception e) {
	        throw e;
	    } finally {
	    	
	    }
	}

	/**
	* @Description สำหรับแก้ไขข้อมูลผู้ใช้งาน โดยมีการตรวจสอบ รหัสผู้ใช้งานซ้ำก่อน หากซ้ำจะไม่ทำการ แก้ไขข้อมูล
	* @return int
	* @throws Exception
	*/
	@Override
	protected int edit(CCTConnection conn, Employee obj, CommonUser user, Locale locale) throws Exception {
		int paramIndex = 0;
	    int id = 0;
	    
	    String startDate = StringUtil.replaceSpecialString(obj.getStartWorkDate(), conn.getDbType(), ResultType.NULL);
		if (startDate != null) {
			Calendar startCalendar = CalendarUtil.getCalendarFromDateString(startDate, ParameterConfig.getParameter().getDateFormat().getForDisplay(), ParameterConfig.getParameter().getApplication().getDatabaseLocale());
			startDate = CalendarUtil.getDateStringFromCalendar(startCalendar, ParameterConfig.getParameter().getDateFormat().getForDatabaseInsert());
		}	
		String endDate = StringUtil.replaceSpecialString(obj.getEndWorkDate(), conn.getDbType(), ResultType.NULL);
		if (endDate != null) {
			Calendar endCalendar = CalendarUtil.getCalendarFromDateString(endDate, ParameterConfig.getParameter().getDateFormat().getForDisplay(), ParameterConfig.getParameter().getApplication().getDatabaseLocale());
			endDate = CalendarUtil.getDateStringFromCalendar(endCalendar, ParameterConfig.getParameter().getDateFormat().getForDatabaseInsert());
		}

	    Object[] params = new Object[11];
	    params[paramIndex++] = StringUtil.replaceSpecialString(obj.getName(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getSurname(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getNickName(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getPrefixId(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getPositionId(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = startDate;
        params[paramIndex++] = endDate;
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getWorkStatus(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getTransaction().getCreateRemark(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getTransaction().getUpdateUser(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(obj.getId(), conn.getDbType(), ResultType.NULL);
        
        String sql = SQLUtil.getSQLString(conn.getSchemas()
        		, getSqlPath().getClassName()
        		, getSqlPath().getPath()
        		, "editEmployee"
        		, params);
        LogUtil.SEC.debug(sql);
     
        Statement stmt = null;
        try {
        	stmt = conn.createStatement();
        	stmt.executeUpdate(sql);
        } catch (Exception e) {
            throw e;
        } finally {
        	CCTConnectionUtil.closeAll(null, stmt);
        }
        return id ;
	}

	/**
     * ใช้สำหรับการบันทึกลบข้อมูล
     * */
	@Override
	protected int delete(CCTConnection conn, String ids, CommonUser user, Locale locale) throws Exception {
		int paramIndex = 0;

	    Object[] params = new Object[2];
	    params[paramIndex++] = StringUtil.replaceSpecialString(user.getUserId(), conn.getDbType(), ResultType.NULL);
	    params[paramIndex++] = StringUtil.replaceSpecialString(ids, conn.getDbType(), ResultType.NULL);
       
        String sql = SQLUtil.getSQLString(conn.getSchemas()
        		, getSqlPath().getClassName()
        		, getSqlPath().getPath()
        		, "deleteEmployee"
        		, params);
        LogUtil.SEC.debug(sql);
     
        Statement stmt = null;
        try {
        	stmt = conn.createStatement();
        	stmt.executeUpdate(sql);
        } catch (Exception e) {
            throw e;
        } finally {
        	CCTConnectionUtil.closeAll(null, stmt);
        }
        return 0 ;
	}

	/**
     * ใช้สำหรับการบันทึกเปลี่ยนสถานะ
     * */
	@Override
	protected int updateActive(CCTConnection conn, String ids, String activeFlag, CommonUser user, Locale locale) throws Exception {
		return 0;
	}

	
	protected List<CommonSelectItem> searchPrefixSelectItem(CCTConnection conn, Locale locale) throws Exception{
		List<CommonSelectItem> listPrefix = new ArrayList<CommonSelectItem>();

        String sql = SQLUtil.getSQLString(conn.getSchemas()
                , getSqlPath().getClassName()
                , getSqlPath().getPath()
                , "searchPrefixSelectItem");
        LogUtil.SEC.debug("SQL : " + sql);
        
        Statement stmt = null;
        ResultSet rst = null;
        try {
            stmt = conn.createStatement();
            rst = stmt.executeQuery(sql);
            while (rst.next()) {
            	CommonSelectItem result = new CommonSelectItem();
            	result.setKey(StringUtil.nullToString(rst.getString("PREFIX_ID")));
            	result.setValue(StringUtil.nullToString(rst.getString("PREFIX_NAME")));
            	listPrefix.add(result);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            CCTConnectionUtil.closeAll(rst, stmt);
        }
		return listPrefix;
	}
	
	protected List<EmployeeSearch> searchExportEmployee(CCTConnection conn, EmployeeSearchCriteria criteria, Locale locale) throws Exception {
		int paramIndex = 0;
		List<EmployeeSearch> listResult = new ArrayList<EmployeeSearch>();
		
		String startDate = StringUtil.replaceSpecialString(criteria.getStartWorkDate(), conn.getDbType(), ResultType.NULL);
		if (startDate != null) {
			Calendar startCalendar = CalendarUtil.getCalendarFromDateString(startDate, ParameterConfig.getParameter().getDateFormat().getForDisplay(), ParameterConfig.getParameter().getApplication().getDatabaseLocale());
			startDate = CalendarUtil.getDateStringFromCalendar(startCalendar, "YYYY-MM-DD HH:mm:ss");
		}	
		String endDate = StringUtil.replaceSpecialString(criteria.getEndWorkDate(), conn.getDbType(), ResultType.NULL);
		if (endDate != null) {
			Calendar endCalendar = CalendarUtil.getCalendarFromDateString(endDate, ParameterConfig.getParameter().getDateFormat().getForDisplay(), ParameterConfig.getParameter().getApplication().getDatabaseLocale());
			endDate = CalendarUtil.getDateStringFromCalendar(endCalendar, "YYYY-MM-DD HH:mm:ss");
		}
		
        Object[] params = new Object[9];
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getPrefixId(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getFullname(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getNickname(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getSex(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getDepartmentId(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getPositionId(), conn.getDbType(), ResultType.NULL);
        params[paramIndex++] = startDate;
        params[paramIndex++] = endDate;
        params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getWorkStatus(), conn.getDbType(), ResultType.NULL);
		
        String sql = SQLUtil.getSQLString(conn.getSchemas()
                , getSqlPath().getClassName()
                , getSqlPath().getPath()
                , "searchExportEmployee"
                , params);
        LogUtil.SEC.debug("SQL : " + sql);
        
        Statement stmt = null;
        ResultSet rst = null;
        try {
            stmt = conn.createStatement();
            rst = stmt.executeQuery(sql);
            while (rst.next()) {
            	EmployeeSearch result = new EmployeeSearch();
            	Transaction transaction = new Transaction();
            	result.setId(StringUtil.nullToString(rst.getString("EMPLOYEE_ID")));
            	result.setFullname(StringUtil.nullToString(rst.getString("FULLNAME")));
            	result.setSex(StringUtil.nullToString(rst.getString("SEX")));
            	result.setDepartmentDesc(StringUtil.nullToString(rst.getString("DEPARTMENT_NAME")));
            	result.setPositionDesc(StringUtil.nullToString(rst.getString("POSITION_NAME")));
            	result.setStartWorkDate(StringUtil.nullToString(rst.getString("START_WORK_DATE")));
            	result.setEndWorkDate(StringUtil.nullToString(rst.getString("END_WORK_DATE")));
            	result.setWorkStatus(StringUtil.nullToString(rst.getString("WORK_STATUS")));
            	transaction.setCreateUser(StringUtil.nullToString(rst.getString("CREATE_USER")));
            	transaction.setCreateDate(StringUtil.nullToString(rst.getString("CREATE_DATE")));
            	transaction.setUpdateUser(StringUtil.nullToString(rst.getString("UPDATE_USER")));
            	transaction.setUpdateDate(StringUtil.nullToString(rst.getString("UPDATE_DATE")));
            	transaction.setCreateRemark(StringUtil.nullToString(rst.getString("REMARK")));
            	result.setTransaction(transaction);
                listResult.add(result);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            CCTConnectionUtil.closeAll(rst, stmt);
        }
		return listResult;
	}
	
}
