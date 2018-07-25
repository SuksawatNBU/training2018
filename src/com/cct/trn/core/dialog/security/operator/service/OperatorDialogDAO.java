package com.cct.trn.core.dialog.security.operator.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import util.database.CCTConnection;
import util.database.CCTConnectionUtil;
import util.database.SQLUtil;
import util.log.LogUtil;
import util.string.StringUtil;

import com.cct.abstracts.AbstractDAO;
import com.cct.common.CommonUser;
import com.cct.domain.Operator;
import com.cct.domain.Tree;

public class OperatorDialogDAO extends AbstractDAO<Object, Object, Object, CommonUser, Locale> {

	protected Tree searchLevelProgram(CCTConnection conn, Locale locale, CommonUser user) throws Exception{
		
		Tree tree = null;
		
		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchLevelProgram");
		
		LogUtil.DIALOG.debug(sql);
		
		Statement stmt = null;
		ResultSet rst = null;
		
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			
			if (rst.next()) {
				tree = new Tree();
				tree.setMinLevel(rst.getInt("MIN_OPERATOR_LEVEL"));
				tree.setMaxLevel(rst.getInt("MAX_OPERATOR_LEVEL"));
				
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		
		return tree;
	}

	protected Map<String, Tree> searchMenuProgramTree(CCTConnection conn,Locale locale, CommonUser user, Tree tree) throws Exception{
		Map<String, Tree> mapTree = new LinkedHashMap<String, Tree>();
		
		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchOperatorProgram");
		
		LogUtil.DIALOG.debug(sql);

		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			
			String level = "0";
			while (rst.next()) {
				level = StringUtil.nullToString(rst.getString("OPERATOR_LEVEL"));
				if (level.equals("0")) {
					continue;
				}

				Operator operator = new Operator();
				
				operator.setCurrentId(StringUtil.nullToString(rst.getString("OPERATOR_ID")));
				operator.setParentId(StringUtil.nullToString(rst.getString("PARENT_ID")));
				operator.setLabel(StringUtil.nullToString(rst.getString("LABEL_EN")));
				operator.setOperatorType(StringUtil.nullToString(rst.getString("OPERATOR_TYPE")));
				
								
				operator.setCurrentLevel(Integer.valueOf(level));
				operator.setMinLevel(tree.getMinLevel());
				operator.setMaxLevel(tree.getMaxLevel());

				mapTree.put(operator.getCurrentId(), operator);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		
		return mapTree;
	}
	
	protected Tree searchLevelReport(CCTConnection conn, Locale locale, CommonUser user) throws Exception{
		Tree tree = null;
		
		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchLevelReport");
		
		LogUtil.DIALOG.debug(sql);
		
		Statement stmt = null;
		ResultSet rst = null;
		
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			
			if (rst.next()) {
				tree = new Tree();
				tree.setMaxLevel(rst.getInt("MAX_OPERATOR_LEVEL"));
				tree.setMinLevel(rst.getInt("MIN_OPERATOR_LEVEL"));
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		
		return tree;
	}
	
	protected Map<String, Tree> searchMenuReportTree(CCTConnection conn,Locale locale, CommonUser user, Tree tree) throws Exception{
		
		Map<String, Tree> mapTree = new LinkedHashMap<String, Tree>();

		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchOperatorReport");
		
		LogUtil.DIALOG.debug(sql);

		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			
			String level = "0";
			while (rst.next()) {
				level = StringUtil.nullToString(rst.getString("OPERATOR_LEVEL"));
				if (level.equals("0")) {
					continue;
				}

				Operator operator = new Operator();
				operator.setOperatorType(StringUtil.nullToString(rst.getString("OPERATOR_TYPE")));
				operator.setUrl(StringUtil.nullToString(rst.getString("URL")));
				
				operator.setCurrentId(StringUtil.nullToString(rst.getString("OPERATOR_ID")));
				operator.setParentId(StringUtil.nullToString(rst.getString("PARENT_ID")));
				
				operator.setLabel(StringUtil.nullToString(rst.getString("LABEL_EN")));
				operator.setCurrentLevel(Integer.valueOf(level));
				operator.setMinLevel(tree.getMinLevel());
				operator.setMaxLevel(tree.getMaxLevel());
				
				operator.getActive().setCode(StringUtil.nullToString(rst.getString("ACTIVE")));

				mapTree.put(operator.getCurrentId(), operator);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
				
		return mapTree;
	}

	@Override
	protected long countData(CCTConnection conn, Object criteria,
			CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected List<Object> search(CCTConnection conn, Object criteria,
			CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object searchById(CCTConnection conn, String id, CommonUser user,
			Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int add(CCTConnection conn, Object obj, CommonUser user,
			Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int edit(CCTConnection conn, Object obj, CommonUser user,
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
	protected boolean checkDup(CCTConnection conn, Object obj, CommonUser user,
			Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	


}
