package com.cct.trn.core.selectitem.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import util.TRNUtil;
import util.database.CCTConnection;
import util.database.CCTConnectionUtil;
import util.database.SQLUtil;
import util.log.LogUtil;
import util.string.StringUtil;
import util.type.StringType.ResultType;

import com.cct.abstracts.AbstractDAO;
import com.cct.common.CommonSelectItem;

public class SelectItemDAO extends AbstractDAO<Object, Object, Object, Object, Object> {

	protected Map<String, List<CommonSelectItem>> searchGlobalDataSelectItem(CCTConnection conn, Locale locale) throws Exception {

		Map<String, List<CommonSelectItem>> mapGlobalData = new HashMap<String, List<CommonSelectItem>>();

		int paramIndex = 0;
		Object[] params = new Object[1];
		params[paramIndex++] = locale.getLanguage();

		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchGlobalDataSelectItem"
				, params);
		LogUtil.SELECTOR.debug(sql);

		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			while (rst.next()) {
				String globalType = StringUtil.nullToString(rst.getString("GLOBAL_TYPE_CODE"));
				if (mapGlobalData.get(globalType) == null) {
					mapGlobalData.put(globalType, new ArrayList<CommonSelectItem>());
				}

				CommonSelectItem selectItem = new CommonSelectItem();
				selectItem.setKey(StringUtil.nullToString(rst.getString("GLOBAL_DATA_CODE")));
				selectItem.setValue(StringUtil.nullToString(rst.getString("GLOBAL_DATA_VALUE")));
				mapGlobalData.get(globalType).add(selectItem);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		return mapGlobalData;
	}
	
	/**
	 * Autocomplete à¸œà¸¹à¹‰à¹ƒà¸Šà¹‰à¸‡à¸²à¸™à¸£à¸°à¸šà¸š QA (GM, SA, SD, PG à¹�à¸¥à¸° QA)
	 * 
	 * @param conn
	 * @param locale
	 * @param term
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	protected List<CommonSelectItem> searchAllQAUserAutoSelectItem(CCTConnection conn, Locale locale, String term, String limit) throws Exception {
		List<CommonSelectItem> listSelectItem = new ArrayList<CommonSelectItem>();
		
		int paramIndex = 0;
		Object[] params = new Object[2];
		params[paramIndex++] = StringUtil.replaceSpecialString(term, conn.getDbType(), ResultType.NULL);
		params[paramIndex] = StringUtil.replaceSpecialString(limit, conn.getDbType(), ResultType.NULL);
		

		String sql = SQLUtil.getSQLString(
				conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchAllQAUserAutoSelectItem"
				, params);
		LogUtil.SELECTOR.debug(sql);

		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			while (rst.next()) {
				CommonSelectItem selectItem = new CommonSelectItem();
				selectItem.setKey(StringUtil.nullToString(rst.getString("USER_ID")));
				selectItem.setValue(StringUtil.nullToString(rst.getString("FULL_NAME")));
				listSelectItem.add(selectItem);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		return listSelectItem;
	}
	
	/**
	 * à¸„à¹‰à¸™à¸«à¸²à¸‚à¹‰à¸­à¸‡à¸¡à¸¹à¸¥ AutoComplete Department  
	 * @param conn
	 * @param locale
	 * @param term
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	protected List<CommonSelectItem> searchDepartmentAutoSelectItem(CCTConnection conn, Locale locale, String term, String limit) throws Exception {
		List<CommonSelectItem> listSelectItem = new ArrayList<CommonSelectItem>();
		
		int paramIndex = 0;
		Object[] params = new Object[2];
		params[paramIndex++] = StringUtil.replaceSpecialString(term, conn.getDbType(), ResultType.NULL);
		params[paramIndex] = StringUtil.replaceSpecialString(limit, conn.getDbType(), ResultType.NULL);
		
		String sql = SQLUtil.getSQLString(
				conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchDepartmentAutoSelectItem"
				, params);
		LogUtil.SELECTOR.debug(sql);

		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			while (rst.next()) {
				CommonSelectItem selectItem = new CommonSelectItem();
				selectItem.setKey(StringUtil.nullToString(rst.getString("DEPARTMENT_ID")));
				selectItem.setValue(StringUtil.nullToString(rst.getString("DEPARTMENT_NAME")));
				listSelectItem.add(selectItem);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		return listSelectItem;
	}
	
	protected List<CommonSelectItem> searchPositionAutoSelectItem(CCTConnection conn, Locale locale, String term, String limit, String departmentId) throws Exception {
		List<CommonSelectItem> listSelectItem = new ArrayList<CommonSelectItem>();
		
		int paramIndex = 0;
		Object[] params = new Object[3];
		params[paramIndex++] = StringUtil.replaceSpecialString(term, conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(departmentId, conn.getDbType(), ResultType.NULL);
		params[paramIndex] = StringUtil.replaceSpecialString(limit, conn.getDbType(), ResultType.NULL);
		
		String sql = SQLUtil.getSQLString(
				conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchPositiontAutoSelectItem"
				, params);
		LogUtil.SELECTOR.debug(sql);

		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			while (rst.next()) {
				CommonSelectItem selectItem = new CommonSelectItem();
				selectItem.setKey(StringUtil.nullToString(rst.getString("POSITION_ID")));
				selectItem.setValue(StringUtil.nullToString(rst.getString("POSITION_NAME")));
				listSelectItem.add(selectItem);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		return listSelectItem;
	}
	
	/**
	 * à¸„à¹‰à¸™à¸«à¸²à¸‚à¹‰à¸­à¸‡à¸¡à¸¹à¸¥ AutoComplete User
	 * @param conn
	 * @param locale
	 * @param term
	 * @param limit
	 * @param departmentId
	 * @return
	 * @throws Exception
	 */
	protected List<CommonSelectItem> searchUserAutoSelectItem(CCTConnection conn, Locale locale, String term, String limit, String departmentId) throws Exception {
		List<CommonSelectItem> listSelectItem = new ArrayList<CommonSelectItem>();
		
		int paramIndex = 0;
		Object[] params = new Object[3];
		params[paramIndex++] = StringUtil.replaceSpecialString(departmentId, conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(term, conn.getDbType(), ResultType.NULL);
		params[paramIndex] = StringUtil.replaceSpecialString(limit, conn.getDbType(), ResultType.NULL);
		
		String sql = SQLUtil.getSQLString(
				conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchUserAutoSelectItem"
				, params);
		LogUtil.SELECTOR.debug(sql);
		
		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			while (rst.next()) {
				CommonSelectItem selectItem = new CommonSelectItem();
				selectItem.setKey(StringUtil.nullToString(rst.getString("USER_ID")));
				selectItem.setValue(StringUtil.nullToString(rst.getString("FULLNAME")));
				listSelectItem.add(selectItem);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		return listSelectItem;
	}

	/**
	 * @deprecated à¹„à¸¡à¹ˆà¹ƒà¸Šà¹‰à¸‡à¸²à¸™
	 * */
	@Override
	protected long countData(CCTConnection conn, Object criteria, Object user, Object locale) throws Exception {
		return 0;
	}

	/**
	 * @deprecated à¹„à¸¡à¹ˆà¹ƒà¸Šà¹‰à¸‡à¸²à¸™
	 * */
	@Override
	protected List<Object> search(CCTConnection conn, Object criteria, Object user, Object locale) throws Exception {
		return null;
	}

	/**
	 * @deprecated à¹„à¸¡à¹ˆà¹ƒà¸Šà¹‰à¸‡à¸²à¸™
	 * */
	@Override
	protected Object searchById(CCTConnection conn, String id, Object user, Object locale) throws Exception {
		return null;
	}

	/**
	 * @deprecated à¹„à¸¡à¹ˆà¹ƒà¸Šà¹‰à¸‡à¸²à¸™
	 * */
	@Override
	protected int add(CCTConnection conn, Object obj, Object user, Object locale) throws Exception {
		return 0;
	}

	/**
	 * @deprecated à¹„à¸¡à¹ˆà¹ƒà¸Šà¹‰à¸‡à¸²à¸™
	 * */
	@Override
	protected int edit(CCTConnection conn, Object obj, Object user, Object locale) throws Exception {
		return 0;
	}

	/**
	 * @deprecated à¹„à¸¡à¹ˆà¹ƒà¸Šà¹‰à¸‡à¸²à¸™
	 * */
	@Override
	protected int delete(CCTConnection conn, String ids, Object user, Object locale) throws Exception {
		return 0;
	}

	/**
	 * @deprecated à¹„à¸¡à¹ˆà¹ƒà¸Šà¹‰à¸‡à¸²à¸™
	 * */
	@Override
	protected int updateActive(CCTConnection conn, String ids, String activeFlag, Object user, Object locale) throws Exception {
		return 0;
	}

	/**
	 * @deprecated à¹„à¸¡à¹ˆà¹ƒà¸Šà¹‰à¸‡à¸²à¸™
	 * */
	@Override
	protected boolean checkDup(CCTConnection conn, Object obj, Object user, Object locale) throws Exception {
		return false;
	}
	
	/**
	 * à¸ˆà¸±à¸‡à¸«à¸§à¸±à¸”
	 * @param conn
	 * @param locale
	 * @param term
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	protected List<CommonSelectItem> searchProvinceSelectItem(CCTConnection conn, Locale locale, String term, String limit) throws Exception {
		List<CommonSelectItem> listSelectItem = new ArrayList<CommonSelectItem>();

		int paramIndex = 0;
		Object[] params = new Object[2];
		params[paramIndex++] = StringUtil.replaceSpecialString(term, conn.getDbType(), ResultType.NULL);
		params[paramIndex++] = StringUtil.replaceSpecialString(limit, conn.getDbType(), ResultType.NULL);

		String sql = SQLUtil.getSQLString(
				conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchProvinceSelectItem"
				, params);
		LogUtil.INITIAL.debug(sql);

		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			while (rst.next()) {
				CommonSelectItem selectItem = new CommonSelectItem();

				selectItem.setKey(rst.getString("PROVINCE_ID"));
				selectItem.setValue(rst.getString("PROVINCE_NAME"));

				listSelectItem.add(selectItem);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		return listSelectItem;
	}
	
	/**
	 * Autocomplete à¹‚à¸„à¸£à¸‡à¸�à¸²à¸£ (project)
	 * 
	 * @param conn
	 * @param locale
	 * @param term
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	protected List<CommonSelectItem> searchProjectsAutoSelectItem(CCTConnection conn, Locale locale, String term, String limit) throws Exception {
		List<CommonSelectItem> listSelectItem = new ArrayList<CommonSelectItem>();

		int paramIndex = 0;
		Object[] params = new Object[2];
		params[paramIndex++] = StringUtil.replaceSpecialString(term, conn.getDbType(), ResultType.NULL);
		params[paramIndex] = StringUtil.replaceSpecialString(limit, conn.getDbType(), ResultType.NULL);
		
		String sql = SQLUtil.getSQLString(
				conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchProjectsAutoSelectItem"
				, params);
		LogUtil.SELECTOR.debug(sql);

		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			while (rst.next()) {
				CommonSelectItem selectItem = new CommonSelectItem();
				selectItem.setKey(StringUtil.nullToString(rst.getString("PROJECT_ID")));
				selectItem.setValue(StringUtil.nullToString(rst.getString("PROJECT_NAME")));
				listSelectItem.add(selectItem);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		return listSelectItem;
	}
	
	/**
	 * Autocomplete à¸£à¸°à¸šà¸š (system)
	 * @param conn
	 * @param projectId
	 * @param sysTemsName
	 * @return
	 * @throws Exception
	 */
	protected List<CommonSelectItem> searchSystemsAutoSelectItem(CCTConnection conn ,Locale locale, String projectId, String sysTemsName) throws Exception {
		List<CommonSelectItem> listSelectItem = new ArrayList<CommonSelectItem>();
		
		if (projectId.isEmpty()) {
			return listSelectItem;
		}
			int paramIndex = 0;
			Object[] params = new Object[2];
			params[paramIndex++] = TRNUtil.convertLongValue(projectId);
			params[paramIndex++] = StringUtil.replaceSpecialString(sysTemsName, conn.getDbType());
		
		String sql = SQLUtil.getSQLString(
				conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchSystemsAutoSelectItem"
				,params);
		
		LogUtil.SELECTOR.debug(sql);

		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			while (rst.next()) {
				CommonSelectItem selectItem = new CommonSelectItem();
				selectItem.setKey(StringUtil.nullToString(rst.getString("SYSTEM_ID")));
				selectItem.setValue(StringUtil.nullToString(rst.getString("SYSTEM_NAME")));
				listSelectItem.add(selectItem);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		return listSelectItem;
	}
	
	/**
	 * Autocomplete à¸£à¸°à¸šà¸šà¸¢à¹ˆà¸­à¸¢ (sub system)
	 * 
	 * @param conn
	 * @param systemId
	 * @param subSystemName
	 * @return
	 * @throws Exception
	 */
	protected List<CommonSelectItem> searchSubSystemsAutoSelectItem(CCTConnection conn, Locale locale, String systemId, String subSystemName) throws Exception {
		List<CommonSelectItem> listSelectItem = new ArrayList<CommonSelectItem>();
		
		if (systemId.isEmpty()) {
			return listSelectItem;
		}
			int paramIndex = 0;
			Object[] params = new Object[2];
			params[paramIndex++] = TRNUtil.convertLongValue(systemId);
			params[paramIndex++] = StringUtil.replaceSpecialString(subSystemName, conn.getDbType());
		
		String sql = SQLUtil.getSQLString(
				conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchSubSystemsAutoSelectItem"
				,params);
		
		LogUtil.SELECTOR.debug(sql);

		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			while (rst.next()) {
				CommonSelectItem selectItem = new CommonSelectItem();
				selectItem.setKey(StringUtil.nullToString(rst.getString("SUB_SYSTEM_ID")));
				selectItem.setValue(StringUtil.nullToString(rst.getString("SUB_SYSTEM_NAME")));
				listSelectItem.add(selectItem);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		return listSelectItem;
	}
	
}