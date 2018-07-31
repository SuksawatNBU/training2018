package com.cct.trn.core.tutorial.project.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import util.TRNUtil;
import util.calendar.CalendarUtil;
import util.database.CCTConnection;
import util.database.CCTConnectionUtil;
import util.database.SQLUtil;
import util.log.LogUtil;
import util.string.StringUtil;
import util.type.StringType.ResultType;

import com.cct.abstracts.AbstractDAO;
import com.cct.common.CommonUser;
import com.cct.domain.GlobalVariable;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;
import com.cct.trn.core.tutorial.project.domain.Project;
import com.cct.trn.core.tutorial.project.domain.ProjectSearch;
import com.cct.trn.core.tutorial.project.domain.ProjectSearchCriteria;

public class ProjectDAO extends AbstractDAO<ProjectSearchCriteria, ProjectSearch, Project, CommonUser, Locale> {

	/*
	 * นับจำนวนข้อมูลโครงการ 
	 */
	@Override
	protected long countData(CCTConnection conn, ProjectSearchCriteria criteria, CommonUser user, Locale locale)throws Exception {
		int total = 0 ;
		int paramIndex = 0;
		
		String startDate = null;
		if (criteria.getStartDate() != null && !criteria.getStartDate().equals("")) {
			startDate = CalendarUtil.convertDateString(criteria.getStartDate()
					, ParameterConfig.getParameter().getDateFormat().getForDisplay()
					, locale
					, GlobalVariable.DATE_FORMAT_FOR_SEARCH + GlobalVariable.Delimeter.BLANK + GlobalVariable.START_TIME
					, ParameterConfig.getParameter().getApplication().getDatabaseLocale());
		}
		
		String endDate = null;
		if (criteria.getEndDate() != null && !criteria.getEndDate().equals("")) {
			endDate = CalendarUtil.convertDateString(criteria.getEndDate()
					, ParameterConfig.getParameter().getDateFormat().getForDisplay()
					, locale
					, GlobalVariable.DATE_FORMAT_FOR_SEARCH + GlobalVariable.Delimeter.BLANK + GlobalVariable.START_TIME
					, ParameterConfig.getParameter().getApplication().getDatabaseLocale());
			
		}
		
		LogUtil.TRAINING.debug("Create Date : " + startDate + " -> " + endDate);
		
		Object[] params = new Object[6];
		params[paramIndex++] = StringUtil.replaceSpecialString(startDate, conn.getDbType(),ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(endDate, conn.getDbType(),ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getProjectCode(), conn.getDbType(),ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getProjectName(), conn.getDbType(),ResultType.NULL);
		params[paramIndex++] = TRNUtil.convertLongValue(criteria.getSpmCode());
		params[paramIndex] = StringUtil.replaceSpecialString(criteria.getActiveCode(), conn.getDbType(),ResultType.NULL);
		
		String sql = SQLUtil.getSQLString(
				conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchCount"
				,params);
		LogUtil.TRAINING.debug("SQL searchCount :" +sql);

		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			if (rst.next()) {
				total = rst.getInt("TOT");
			}

		}catch (Exception e){
			throw e;
		}finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}

		return total;
	}
	
	/*
	 * ค้นหาข้อมูลโครงการ
	 */
	@Override
	protected List<ProjectSearch> search(CCTConnection conn,ProjectSearchCriteria criteria, CommonUser user, Locale locale)throws Exception {
		LogUtil.TRAINING.debug("search");
		
		List<ProjectSearch> listResult = new ArrayList<ProjectSearch>();
			
		String startDate = null;
		if (criteria.getStartDate() != null && !criteria.getStartDate().equals("")) {
			startDate = CalendarUtil.convertDateString(criteria.getStartDate()
					, ParameterConfig.getParameter().getDateFormat().getForDisplay()
					, locale
					, GlobalVariable.DATE_FORMAT_FOR_SEARCH + GlobalVariable.Delimeter.BLANK + GlobalVariable.START_TIME
					, ParameterConfig.getParameter().getApplication().getDatabaseLocale());
		}
		
		String endDate = null;
		if (criteria.getEndDate() != null && !criteria.getEndDate().equals("")) {
			endDate = CalendarUtil.convertDateString(criteria.getEndDate()
					, ParameterConfig.getParameter().getDateFormat().getForDisplay()
					, locale
					, GlobalVariable.DATE_FORMAT_FOR_SEARCH + GlobalVariable.Delimeter.BLANK + GlobalVariable.START_TIME
					, ParameterConfig.getParameter().getApplication().getDatabaseLocale());
			
		}
		
		LogUtil.TRAINING.debug("Create Date : " + startDate + " -> " + endDate);
		
		int paramIndex = 0;
		Object[] params = new Object[9];
		params[paramIndex++] = StringUtil.replaceSpecialString(startDate, conn.getDbType(),ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(endDate, conn.getDbType(),ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getProjectCode(), conn.getDbType(),ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getProjectName(), conn.getDbType(),ResultType.NULL);
		params[paramIndex++] = TRNUtil.convertLongValue(criteria.getSpmCode());
		params[paramIndex++] = StringUtil.replaceSpecialString(criteria.getActiveCode(), conn.getDbType(),ResultType.NULL);
		params[paramIndex++] = criteria.getOrderList();
		params[paramIndex++] = criteria.getStart() - 1;
		params[paramIndex] = criteria.getLinePerPage();
		
		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClass()
				, getSqlPath().getPath()
				, "searchProject"
				, params);
		LogUtil.TRAINING.debug(sql);
		
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			while (rst.next()) {
				ProjectSearch result = new ProjectSearch();
				result.setId(StringUtil.nullToString(rst.getString("PROJECT_ID")));
				result.setProjectCode(StringUtil.nullToString(rst.getString("PROJECT_CODE")));
				result.setProjectName(StringUtil.nullToString(rst.getString("PROJECT_NAME")));
				result.setSpmName(StringUtil.nullToString(rst.getString("SPM_NAME")));
				result.setCreateDate(StringUtil.nullToString(rst.getString("START_DATE")));
				result.getActive().setDesc(StringUtil.nullToString(rst.getString("ACTIVE")));
				result.getActive().setCode(StringUtil.nullToString(rst.getString("ACTIVE")));
				listResult.add(result);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		
		return listResult;
	
	}

	/*
	 * ค้นหาโดยระบุ id(แสดงข้อมูลแก้ไข)
	 */
	@Override
	protected Project searchById(CCTConnection conn, String id,CommonUser user, Locale locale) throws Exception {
		LogUtil.TRAINING.debug("searchById");
			
		int paramIndex = 0;

		Object[] params = new Object[1];
		params[paramIndex++] = TRNUtil.convertLongValue(id);

		String sql = SQLUtil.getSQLString(
				conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchById"
				, params );
		LogUtil.TRAINING.debug("SQL searchById :" +sql);

		Project project = null;

		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			if (rst.next()) {
				project = new Project();
				project.setId(StringUtil.nullToString(rst.getString("PROJECT_ID")));
				project.setProjectCode(StringUtil.nullToString(rst.getString("PROJECT_CODE")));
				project.setSpmCode(StringUtil.nullToString(rst.getString("SPM_ID")));
				project.setSpmName(StringUtil.nullToString(rst.getString("SPM_NAME")));
				project.setProjectName(StringUtil.nullToString(rst.getString("PROJECT_NAME")));
				project.getActive().setCode(StringUtil.nullToString(rst.getString("ACTIVE")));
				project.setDepartmentId(StringUtil.nullToString(rst.getString("DEPARTMENT_ID")));
				project.setDepartmentName(StringUtil.nullToString(rst.getString("DEPARTMENT_NAME")));
				
//				Transaction transaction =  new Transaction();
//				transaction.setCreateDate(StringUtil.nullToString(rst.getString("CREATE_DATE")));
//				transaction.setCreateUser(StringUtil.nullToString(rst.getString("CREATE_USER")));
//				transaction.setUpdateDate(StringUtil.nullToString(rst.getString("UPDATE_DATE")));
//				transaction.setUpdateUser(StringUtil.nullToString(rst.getString("UPDATE_USER")));
//				project.setTransaction(transaction);

			}

		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeStatement(stmt);
		}
		
		return project;
	}
	
	/*
	 * บันทึกข้อมูลโครงการ
	 */
	@Override
	protected int add(CCTConnection conn, Project obj, CommonUser user,Locale locale) throws Exception {
			int paramIndex = 0;
			
			Object[] params = new Object[7];
			params[paramIndex++] = StringUtil.replaceSpecialString(obj.getProjectCode(), conn.getDbType(),ResultType.NULL);
			params[paramIndex++] = StringUtil.replaceSpecialString(obj.getProjectCode(), conn.getDbType(),ResultType.NULL);
			params[paramIndex++] = StringUtil.replaceSpecialString(obj.getSpmCode(), conn.getDbType(),ResultType.NULL);
			params[paramIndex++] = StringUtil.replaceSpecialString(obj.getProjectName(), conn.getDbType(),ResultType.NULL);
			params[paramIndex++] = StringUtil.replaceSpecialString(obj.getProjectCode(), conn.getDbType(),ResultType.NULL);
			params[paramIndex++] = StringUtil.replaceSpecialString(obj.getActive().getCode(), conn.getDbType(),ResultType.NULL);
			params[paramIndex++] = TRNUtil.convertLongValue(user.getUserId());
			
			String sql = SQLUtil.getSQLString(
					conn.getSchemas()
					, getSqlPath().getClassName()
					, getSqlPath().getPath()
					, "insertProject"
					, params );
			LogUtil.TRAINING.debug("SQL insertProject :" +sql);
	
			Statement stmt = null;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(sql.toString());
	
			} catch (Exception e) {
				throw e;
			} finally {
				CCTConnectionUtil.closeStatement(stmt);
			}
			return 0;
	}
	
	/*
	 *แก้ไขข้อมูลโครงการ
	 */
	@Override
	protected int edit(CCTConnection conn, Project obj, CommonUser user,Locale locale) throws Exception {
		LogUtil.TRAINING.info("edit");

		int paramIndex = 0;
		Object[] params = new Object[5];
		params[paramIndex++] = TRNUtil.convertLongValue(obj.getSpmCode());
		params[paramIndex++] = StringUtil.replaceSpecialString(obj.getProjectName(), conn.getDbType(),ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(obj.getActive().getCode(), conn.getDbType(),ResultType.NULL);
		params[paramIndex++] = TRNUtil.convertLongValue(user.getUserId());
		params[paramIndex++] = TRNUtil.convertLongValue(obj.getId());
		

		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "updateProject"
				, params);

		LogUtil.TRAINING.debug("[SAVE EDIT PROJECT ] :"+sql);
		Statement stmt = null;

		try {

			stmt = conn.createStatement();
			stmt.executeUpdate(sql.toString());


		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeStatement(stmt);
		}

		return 0;
	}

	@Override
	protected int delete(CCTConnection conn, String ids, CommonUser user,Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	 * เปลี่ยนสถานะ Active
	 */
	@Override
	protected int updateActive(CCTConnection conn, String ids,String activeFlag, CommonUser user, Locale locale) throws Exception {
		int paramIndex = 0;
	    Object[] params = new Object[3];
	    
	    params[paramIndex++] = StringUtil.replaceSpecialString(activeFlag, conn.getDbType(),ResultType.NULL);
	    params[paramIndex++] =  TRNUtil.convertLongValue(user.getUserId());
	    params[paramIndex++] = StringUtil.replaceSpecialString(ids, conn.getDbType(),ResultType.NULL);
	 
	    String sql = SQLUtil.getSQLString(conn.getSchemas()
	            , getSqlPath().getClassName()
	            , getSqlPath().getPath()
	            , "setActiveStatus"
	            , params);
	    LogUtil.SEC.debug("SQL : " + sql);
	 
	    Statement stmt = null;
	    try {
	        stmt = conn.createStatement();
	        stmt.executeUpdate(sql);
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        CCTConnectionUtil.closeStatement(stmt);
	    }
	    return 0;
	}
	
	/*
	 * ตรวจสอบข้อมูลซ้ำ
	 */
	@Override
	protected boolean checkDup(CCTConnection conn, Project obj,CommonUser user, Locale locale) throws Exception {
		boolean checkDup = false;
		int count = 0 ;
		int paramIndex = 0;

		Object[] params = new Object[2];
		params[paramIndex++] = StringUtil.replaceSpecialString(obj.getProjectCode(), conn.getDbType(),ResultType.NULL);
		params[paramIndex++] = TRNUtil.convertLongValue(obj.getId());

		String sql = SQLUtil.getSQLString(
				conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchCountDup"
				, params );
		LogUtil.TRAINING.debug("SQL checkDup :" +sql);

		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			if (rst.next()) {
				count = rst.getInt("TOT");
			}
			if(count > 0){
				checkDup = true;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		return checkDup;
	}
	
}