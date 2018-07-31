package com.cct.trn.core.dialog.security.user.service;

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
import util.type.StringType.ResultType;

import com.cct.abstracts.AbstractDAO;
import com.cct.common.CommonDomain;
import com.cct.common.CommonUser;
import com.cct.trn.core.dialog.security.user.domain.UserDialog;
import com.cct.trn.core.dialog.security.user.domain.UserDialogSearchCriteria;

public class UserDialogDAO  extends AbstractDAO<UserDialogSearchCriteria, CommonDomain, UserDialog, CommonUser, Locale> {

	protected long countData(CCTConnection conn,UserDialogSearchCriteria criteriaPopup, CommonUser user,Locale locale) throws Exception {
		long total = 0;
		
		int paramIndex = 0;
		Object[] params = new Object[6];
		params[paramIndex++] = StringUtil.replaceSpecialString(criteriaPopup.getUser().getUserId(), conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(criteriaPopup.getUser().getGivenName(), conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(criteriaPopup.getUser().getFamilyName(), conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(criteriaPopup.getUser().getEmailAddress(), conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(criteriaPopup.getUser().getPhoneNumber(), conn.getDbType(), ResultType.NULL);
		params[paramIndex] = StringUtil.replaceSpecialString(criteriaPopup.getUser().getIds(), conn.getDbType(), ResultType.NULL);
		
		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchCountUser"
				, params);
		
		LogUtil.DIALOG.debug(sql);
		
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			
			if (rst.next()) {
				total = rst.getLong("TOT");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		
		return total;
	}

	protected List<CommonDomain> search(CCTConnection conn,UserDialogSearchCriteria criteriaPopup, CommonUser user, Locale locale) throws Exception {
		List<CommonDomain> listCommon = new ArrayList<CommonDomain>();

		int paramIndex = 0;
		Object[] params = new Object[7];
		params[paramIndex++] = StringUtil.replaceSpecialString(criteriaPopup.getUser().getUserId(), conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(criteriaPopup.getUser().getGivenName(), conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(criteriaPopup.getUser().getFamilyName(), conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(criteriaPopup.getUser().getEmailAddress(), conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(criteriaPopup.getUser().getPhoneNumber(), conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(criteriaPopup.getUser().getIds(), conn.getDbType(), ResultType.NULL);
		
		params[paramIndex] = StringUtil.replaceSpecialString("", conn.getDbType(), ResultType.NULL);
		
		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchUser"
				, params);
		
		LogUtil.DIALOG.debug(sql);
		
		Statement stmt = null;
		ResultSet rst = null;
		
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			
			while (rst.next()) {
				UserDialog detail = new UserDialog();
				detail.setRownum(StringUtil.nullToString(rst.getString("ROW_NO")));
				detail.setIdPopup(rst.getString("USER_UCP_ID"));
				detail.setId(rst.getString("USER_UCP_ID"));
				detail.setUserId(rst.getString("USER_ID"));
				detail.setFullName(StringUtil.nullToString(rst.getString("FULLNAME")));
				detail.setEmailAddress(StringUtil.nullToString(rst.getString("EMAIL_ADDRESS")));
				detail.setPhoneNumber(StringUtil.nullToString(rst.getString("TELEPHONE_CONTACT")));
				detail.getActive().setCode(StringUtil.nullToString(rst.getString("USER_STATUS")));
				detail.getActive().setDesc(StringUtil.nullToString(rst.getString("USER_STATUS_V")));
				detail.setDeleted(StringUtil.nullToString(rst.getString("DELETED")));
				
				listCommon.add(detail);
				
			}
			
			
		} catch (Exception e) {
			throw e;
		}finally{
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		
		return listCommon;
	}

	protected List<CommonDomain> searchListById(CCTConnection conn, String ids,CommonUser user, Locale locale) throws Exception {
		List<CommonDomain> listCommon = new ArrayList<CommonDomain>();
		
		int paramIndex = 0;
		Object[] params = new Object[7];
		params[paramIndex++] = StringUtil.replaceSpecialString("", conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString("", conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString("", conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString("", conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString("", conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString("", conn.getDbType(), ResultType.NULL);
		params[paramIndex] = ids;
		
		String sql = SQLUtil.getSQLString(
				conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchUser"
				, params);
		
		LogUtil.DIALOG.debug(sql);
		
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);

			while (rst.next()) {
				UserDialog detail = new UserDialog();
				detail.setRownum(StringUtil.nullToString(rst.getString("ROW_NO")));
				detail.setIdPopup(rst.getString("USER_UCP_ID"));
				detail.setId(rst.getString("USER_UCP_ID"));
				detail.setUserId(rst.getString("USER_ID"));
				detail.setFullName(StringUtil.nullToString(rst.getString("FULLNAME")));
				detail.setEmailAddress(StringUtil.nullToString(rst.getString("EMAIL_ADDRESS")));
				detail.setPhoneNumber(StringUtil.nullToString(rst.getString("TELEPHONE_CONTACT")));
				detail.getActive().setCode(StringUtil.nullToString(rst.getString("USER_STATUS")));
				detail.getActive().setDesc(StringUtil.nullToString(rst.getString("USER_STATUS_V")));
				detail.setDeleted(StringUtil.nullToString(rst.getString("DELETED")));
				
				listCommon.add(detail);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		
		return listCommon;
	}

	@Override
	protected UserDialog searchById(CCTConnection conn, String id,
			CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int add(CCTConnection conn, UserDialog obj, CommonUser user,
			Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int edit(CCTConnection conn, UserDialog obj, CommonUser user,
			Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int delete(CCTConnection conn, String ids, CommonUser user,
			Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int updateActive(CCTConnection conn, String ids,
			String activeFlag, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected boolean checkDup(CCTConnection conn, UserDialog obj,
			CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}


}
