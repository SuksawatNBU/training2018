package com.cct.trn.core.log.service;

import java.sql.ResultSet;
import java.sql.Statement;

import util.TRNUtil;
import util.database.CCTConnection;
import util.database.CCTConnectionUtil;
import util.database.SQLUtil;
import util.log.LogUtil;
import util.string.StringUtil;
import util.type.StringType.ResultType;

import com.cct.common.CommonUser;
import com.cct.domain.GlobalVariable;
import com.cct.trn.core.config.parameter.domain.SQLPath;
import com.cct.trn.core.log.domain.MailErrorLog;
import com.cct.trn.core.log.domain.UserDetailLog;

public class LogDAO {

	private SQLPath sqlPath;

	public LogDAO(SQLPath sqlPath) {
		this.sqlPath = sqlPath;
	}
	
	/**
	 * SQL : (usr) ค้นหาข้อมูล User ที่ทำรายการ_SQL
	 * @param conn
	 * @param userUcpId
	 * @return
	 * @throws Exception
	 */
	protected UserDetailLog searchUser(CCTConnection conn, String userUcpId) throws Exception {

		LogUtil.COMMON.debug("searchUser");
		UserDetailLog result = null;

		int paramIndex = 0;
		Object[] params = new Object[1];
		params[paramIndex] = TRNUtil.convertLongValue(userUcpId);

		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchUser"
				, params);
		LogUtil.COMMON.debug("SQL searchUser : " + sql);

		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			
			while (rst.next()) {
				result = new UserDetailLog();
				
				result.setUserName(StringUtil.nullToString(rst.getString("USERNAME")));
				result.setFamilyName(StringUtil.nullToString(rst.getString("FAMILY_NAME")));
				result.setGivenName(StringUtil.nullToString(rst.getString("GIVEN_NAME")));
				
			}
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		return result;
	}

	/**
	 * SQL : Insert ADM_LOG_EVENT_UCP_SQL
	 * Description : บันทึกข้อมูล Log event 
	 * @param conn
	 * @param userDetailLog
	 * @param operatorId
	 * @param LogDetail
	 * @param userUcpId
	 * @return
	 * @throws Exception
	 */
	protected int insertLogEvent(CCTConnection conn,UserDetailLog userDetailLog, String operatorId, String logDetail, String userUcpId) throws Exception{
		int id = 0;
		
		int paramIndex = 0;
		Object[] params = new Object[7];
		params[paramIndex++] = TRNUtil.convertLongValue(userUcpId);	
		params[paramIndex++] = StringUtil.replaceSpecialString(userDetailLog.getUserName(), conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(userDetailLog.getFamilyName(), conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(userDetailLog.getGivenName(), conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = TRNUtil.convertLongValue(operatorId);	
		params[paramIndex++] = TRNUtil.convertLongValue(operatorId);	
		params[paramIndex++] = StringUtil.replaceSpecialString(logDetail, conn.getDbType(), ResultType.NULL);

		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "insertLogEvent"
				, params);
		LogUtil.COMMON.debug("SQL insertLogEvent : "+sql);

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeStatement(stmt);
		}
		
		return id;
	}
	
	/**
	 * SQL : Insert Log Email Error_SQL
	 * Description : Save log when send email error.
	 * 
	 * @param conn
	 * @param operatorId
	 * @param log
	 * @param user
	 * @param ex
	 * @return
	 * @throws Exception
	 */
	protected int insertLogEmailError(CCTConnection conn, String operatorId, MailErrorLog log ,CommonUser user, Exception ex) throws Exception{
		int id = 0;
		
		int paramIndex = 0;
		Object[] params = new Object[8];
		params[paramIndex++] = TRNUtil.convertLongValue(log.getEmailConfigId());	
		params[paramIndex++] = StringUtil.replaceSpecialString(log.getEmailSender(), conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(log.getEmailTo(), conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(log.getEmailTemplateId(), conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = TRNUtil.convertLongValue(operatorId);	
		params[paramIndex++] = TRNUtil.convertLongValue(operatorId);
		params[paramIndex++] = StringUtil.replaceSpecialString(GlobalVariable.SYSTEM, conn.getDbType(), ResultType.NULL);/*CREATE_SYSTEM*//*รับค่าจาก Login ว่ามาจากหน้าเว็บใด เช่น UCP, CP*/
		params[paramIndex] = StringUtil.replaceSpecialString(ex.getMessage(), conn.getDbType(), ResultType.NULL);

		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "insertLogEmailError"
				, params);
		LogUtil.COMMON.debug("SQL insertLogEmailError : "+sql);

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeStatement(stmt);
		}
		
		return id;
	}
	
	/**
	 * SQL : Insert ADM_LOG_ERROR_UCP_SQL
	 * Description : บันทึกข้อมูล Log error
	 * @param conn
	 * @param log
	 * @param className
	 * @param methodName
	 * @param user
	 * @return
	 * @throws Exception
	 */
	protected int insertLogError(CCTConnection conn, UserDetailLog log, String className, String methodName, CommonUser user) throws Exception {
		int id = 0;
		
		int paramIndex = 0;
		Object[] params = new Object[9];
		params[paramIndex++] = TRNUtil.convertLongValue(user.getUserId());
		params[paramIndex++] = StringUtil.replaceSpecialString(log.getUserName(), conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(log.getFamilyName(), conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(log.getGivenName(), conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = TRNUtil.convertLongValue(log.getParentId());
		params[paramIndex++] = TRNUtil.convertLongValue(log.getOperatorId());
		params[paramIndex++] = StringUtil.replaceSpecialString(className, conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(methodName, conn.getDbType(), ResultType.NULL);
		params[paramIndex] = StringUtil.replaceSpecialString(log.getLogDetail(), conn.getDbType(), ResultType.NULL);

		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "insertLogError"
				, params);
		LogUtil.COMMON.debug("\nSQL insertLogError : \n" + sql);

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeStatement(stmt);
		}
		return id;
	}
	
	public SQLPath getSqlPath() {
		return sqlPath;
	}

}
