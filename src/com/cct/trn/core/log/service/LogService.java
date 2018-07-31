package com.cct.trn.core.log.service;

import java.util.Map;

import util.database.CCTConnection;
import util.log.LogUtil;

import com.cct.common.CommonUser;
import com.cct.domain.GlobalVariable;
import com.cct.domain.Operator;
import com.cct.trn.core.config.parameter.domain.SQLPath;
import com.cct.trn.core.log.domain.MailErrorLog;
import com.cct.trn.core.log.domain.UserDetailLog;

public class LogService {
	

	
	private LogDAO dao;

	public LogService() {
		this.dao = new LogDAO(SQLPath.LOG_SQL);
	}

	protected UserDetailLog searchUser(CCTConnection conn, String userUcpId) throws Exception {
		return getDao().searchUser(conn, userUcpId);
	}

	protected int insertLogEvent(CCTConnection conn,UserDetailLog userDetailLog, String operatorId, String logDetail, String userUcpId) throws Exception{
		return getDao().insertLogEvent(conn, userDetailLog, operatorId, logDetail, userUcpId);
	}
	
	/**
	 * SQL : Insert Log Email Error_SQL
	 * Description : Save log when send email error.
	 * 
	 * @param conn
	 * @param operatorId
	 * @param log
	 * @param user
	 * @param e
	 * @return
	 * @throws Exception
	 */
	protected int insertLogEmailError(CCTConnection conn, String operatorId, MailErrorLog log ,CommonUser user, Exception e) throws Exception{
		return getDao().insertLogEmailError(conn, operatorId, log, user, e);
	}
	
	/**
	 * SQL : Insert ADM_LOG_ERROR_UCP_SQL
	 * Description : บันทึกข้อมูล Log error
	 * @param conn
	 * @param log
	 * @param obj
	 * @param operatorId
	 * @param e
	 * @param user
	 * @return
	 * @throws Exception
	 */
	protected int insertLogError(CCTConnection conn, UserDetailLog log, Object obj, String operatorId, Exception e, CommonUser user) throws Exception {
		LogUtil.COMMON.debug("insertLogError");
		
		try {
			log.setParentId(getParentId(user, operatorId));
			log.setOperatorId(operatorId);
			log.setLogDetail(getErrorMessage(e));
			
			String className = obj.getClass().getName();
			String methodName = getErrorMethod(className, e);
			
			return dao.insertLogError(conn, log, className, methodName, user);

		} catch (Exception ex) {
			throw ex;
		}
	}
	
	/**
	 * get parent id by operator id
	 * @param user
	 * @param operatorId
	 * @return
	 * @throws Exception
	 */
	private String getParentId(CommonUser user, String operatorId) throws Exception {
		String parentId = null;
		try {
			Map<String, Operator> mapOperator = user.getMapMenu();
			Operator operator = mapOperator.get(operatorId);
			if (operator != null) {
				parentId = operator.getParentId();
			}
			
		} catch (Exception e) {
			throw e;
		}
		return parentId;
	}
	
	
	/**
	 * get error method
	 * @param className
	 * @param e
	 * @return
	 */
	private String getErrorMethod(String className, Exception e) {
		String errorMethod = "";
		for (int i = 0; i < e.getStackTrace().length; i++) {
			String line = e.getStackTrace()[i].toString();
			int indexOfClassName = line.indexOf(className);
			if (indexOfClassName > -1) {
				errorMethod = line.substring(indexOfClassName + className.length() + 1, line.indexOf("("));
				break;
			}
		}
		return errorMethod;
	}
	
	private String getErrorMessage(Exception e) {
		String errorMessage = "";
		StringBuilder errorBuilder = new StringBuilder();
		errorBuilder.append(String.format("%s%n", e.getMessage()));
		
		if (e.getCause() != null) {
			errorBuilder.append(String.format("%s%n", e.getCause()));
		}
		
		for (int i = 0; i < GlobalVariable.LIMIT_LINE_ERROR; i++) {
			if (e.getStackTrace().length <= i) {
				break;
			} else {
				errorBuilder.append(String.format("%s%n", e.getStackTrace()[i]));
			}
		}

		if (errorBuilder.length() > GlobalVariable.LIMIT_CHARECTOR) {
			errorMessage = errorBuilder.substring(0, GlobalVariable.LIMIT_CHARECTOR - 1);
		} else {
			errorMessage = errorBuilder.toString();
		}
		return errorMessage;
	}

	public LogDAO getDao() {
		return dao;
	}
}
