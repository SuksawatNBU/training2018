package com.cct.trn.core.tutorial.dialog.project.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import util.database.CCTConnection;
import util.database.CCTConnectionUtil;
import util.database.SQLUtil;
import util.log.LogUtil;
import util.string.StringUtil;
import util.type.DatabaseType.DbType;
import util.type.StringType.ResultType;

import com.cct.abstracts.AbstractDAO;
import com.cct.common.CommonDomain;
import com.cct.common.CommonUser;
import com.cct.trn.core.tutorial.dialog.project.domain.SystemListDialog;
import com.cct.trn.core.tutorial.dialog.project.domain.SystemListDialogSearchCriteria;

public class SystemListDialogDAO extends AbstractDAO<SystemListDialogSearchCriteria, SystemListDialog, SystemListDialog, CommonUser, Locale>{

	@Override
	protected long countData(CCTConnection conn,
			SystemListDialogSearchCriteria criteria, CommonUser user,
			Locale locale) throws Exception {
		
		LogUtil.DIALOG.debug("countData");
		
		long tot = 0;
		
		int paramIndex = 0;
		Object[] params = new Object[1];
		params[paramIndex] = criteria.getProjectId();
		
		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchCount"
				, params);
		LogUtil.DIALOG.debug(sql);
		
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			if (rst.next()) {
				tot = rst.getLong("TOT");
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		return tot;
	}

	@Override
	protected List<SystemListDialog> search(CCTConnection conn,
			SystemListDialogSearchCriteria criteria, CommonUser user,
			Locale locale) throws Exception {
		return null;
	}

	@Override
	protected SystemListDialog searchById(CCTConnection conn, String id,
			CommonUser user, Locale locale) throws Exception {
		return null;
	}

	@Override
	protected int add(CCTConnection conn, SystemListDialog obj,
			CommonUser user, Locale locale) throws Exception {
		return 0;
	}

	@Override
	protected int edit(CCTConnection conn, SystemListDialog obj,
			CommonUser user, Locale locale) throws Exception {
		return 0;
	}

	@Override
	protected int delete(CCTConnection conn, String ids, CommonUser user,
			Locale locale) throws Exception {
		return 0;
	}

	@Override
	protected int updateActive(CCTConnection conn, String ids,
			String activeFlag, CommonUser user, Locale locale) throws Exception {
		return 0;
	}

	@Override
	protected boolean checkDup(CCTConnection conn, SystemListDialog obj,
			CommonUser user, Locale locale) throws Exception {
		return false;
	}
	
	protected List<CommonDomain> searchDetail(CCTConnection conn, CommonUser user, Locale local
			, SystemListDialogSearchCriteria criteria) throws Exception {
		LogUtil.DIALOG.debug("searchDetail");
		
		List<CommonDomain> lstResult = new ArrayList<CommonDomain>();
		
		int paramIndex = 0;
		Object[] params = new Object[1];
		params[paramIndex] = StringUtil.replaceSpecialString(criteria.getProjectId(), DbType.MYSQL, ResultType.NULL);
		
		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchListSystem"
				, params);
		LogUtil.DIALOG.debug(sql);
		
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			
			SystemListDialog result = null;
			int rowNo = 0;
			while (rst.next()) {
				result = new SystemListDialog();
				result.setRownum(String.valueOf(++rowNo));
				result.setSystemName(StringUtil.nullToString(rst.getString("SYSTEM_NAME")));
				result.setCreateDate(StringUtil.nullToString(rst.getString("CREATE_DATE")));
				result.setUpdateDate(StringUtil.nullToString(rst.getString("UPDATE_DATE")));
				result.getActive().setCode(StringUtil.nullToString(rst.getString("ACTIVE")));
				
				lstResult.add(result);

			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		return lstResult;
	}
}

