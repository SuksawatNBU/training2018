package com.cct.trn.core.log.service;

import java.sql.SQLException;

import util.database.CCTConnection;
import util.log.LogUtil;

import com.cct.common.CommonUser;
import com.cct.trn.core.log.domain.MailErrorLog;
import com.cct.trn.core.log.domain.UserDetailLog;

public class LogManager {
	
	private static LogService service;
	
	public static void addAuditLog(CCTConnection conn, String operatorId, String logDetail, CommonUser user) {
		LogUtil.COMMON.debug("LogManager addAuditLog :" + logDetail);

		service = new LogService();

		try {
			// (Ref No.9) (usr) ค้นหาข้อมูล User ที่ทำรายการ_SQL
//			UserDetailLog userDetailLog = service.searchUser(conn, user.getUserId());

			//BEGIN TRANSACTION
//			conn.setAutoCommit(false);

//			service.insertLogEvent(conn, userDetailLog, operatorId, logDetail, user.getUserId());

			// COMMIT TRANSACTION
//			conn.commit();

		} catch (Exception e) {
			LogUtil.COMMON.error("",e);

			try {
				// ROLLBACK TRANSACTION
				conn.rollback();
			} catch (SQLException ex) {
				LogUtil.COMMON.error("",ex);
			}

		} finally {
			try {
				// AUTO COMMIT TRUE
				conn.setAutoCommit(true);
			} catch (SQLException ex) {
				LogUtil.COMMON.error("",ex);
			}
		}
	}

	public static void addMailErrorLog(CCTConnection conn, String operatorId, MailErrorLog log ,CommonUser user, Exception e) {
		LogUtil.COMMON.debug("addMailErrorLog");
		
		service = new LogService();
		try{
//			service.insertLogEmailError(conn, operatorId, log, user, e);
			
		}catch(Exception ex){
			LogUtil.COMMON.error("",e);
		}
	}

	public static void addErrorLog(CCTConnection conn, String operatorId, Object obj, Exception e, CommonUser user) {
		LogUtil.COMMON.debug("LogManager addErrorLog :" + obj);

		service = new LogService();
		try {
			// (Ref No.9) (usr) ค้นหาข้อมูล User ที่ทำรายการ_SQL
//			UserDetailLog log = service.searchUser(conn, user.getUserId());

			//BEGIN TRANSACTION
//			conn.setAutoCommit(false);

//			service.insertLogError(conn, log, obj, operatorId, e, user);

			// COMMIT TRANSACTION
//			conn.commit();

		} catch (Exception ee) {
			LogUtil.COMMON.error("",ee);

			try {
				// ROLLBACK TRANSACTION
				conn.rollback();
				
			} catch (SQLException ex) {
				LogUtil.COMMON.error("",ex);
			}

		} finally {
			try {
				// AUTO COMMIT TRUE
				conn.setAutoCommit(true);
				
			} catch (SQLException ex) {
				LogUtil.COMMON.error("",ex);
			}
		}
	}
}
