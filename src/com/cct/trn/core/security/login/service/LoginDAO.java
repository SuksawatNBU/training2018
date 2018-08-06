package com.cct.trn.core.security.login.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import util.database.CCTConnection;
import util.log.LogUtil;

import com.cct.abstracts.AbstractDAO;
import com.cct.common.CommonUser;
import com.cct.domain.Operator;

public class LoginDAO extends AbstractDAO<Object, Object, Object, CommonUser, Locale> {

	/**
	 * หาข้อมูลสิทธิ์_SQL (SEARCH MIN, MAX MENU LEVEL)
	 * @param conn
	 * @param userId
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	protected Operator searchOperatorLevel(CCTConnection conn, String operatorIds,  Locale locale, Long siteId) throws Exception {

		Operator operatorLevel = new Operator();
		operatorLevel.setMinLevel(1);
		operatorLevel.setMaxLevel(4);
		return operatorLevel;
	}

	/**
	 * หาข้อมูลสิทธิ์_SQL (SEARCH MENU)
	 * @param conn
	 * @param userId
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	protected Map<String, Operator> searchOperator(CCTConnection conn, String operatorIds,  Locale locale, Long siteId) throws Exception {
		LogUtil.INITIAL.debug("searchOperator");
		
		Map<String, Operator> mapOperator = new LinkedHashMap<>();
		try {
			/** SITUtil **/
/*			mapOperator.put("10100000", operatorBuilder("10000000", "10100000", "SITUtil", "", "M", 1));
			mapOperator.put("10100100", operatorBuilder("10100000", "10100100", "Bundle", "", "M", 2));
			mapOperator.put("10100200", operatorBuilder("10100000", "10100200", "Calendar", "", "M", 2));
			mapOperator.put("10100300", operatorBuilder("10100000", "10100300", "Date", "", "M", 2));
			mapOperator.put("10100400", operatorBuilder("10100000", "10100400", "Number", "", "M", 2));
			mapOperator.put("10100500", operatorBuilder("10100000", "10100500", "Cryptography", "", "M", 2));
			mapOperator.put("10100600", operatorBuilder("10100000", "10100600", "Encode/Decode", "", "M", 2));
			mapOperator.put("10100700", operatorBuilder("10100000", "10100700", "String", "", "M", 2));
			mapOperator.put("10100800", operatorBuilder("10100000", "10100800", "File", "", "M", 2));*/
//			mapOperator.put("10100900", operatorBuilder("10100000", "10100900", "Mail", "", "M", 2));
//			mapOperator.put("10101000", operatorBuilder("10100000", "10101000", "XML", "", "M", 2));
			
			/** Standard UI **/
			mapOperator.put("10200000", operatorBuilder("10000000", "10200000", "Standard UI", "", "M", 1));
			mapOperator.put("10200100", operatorBuilder("10200000", "10200100", "Miscellaneous", "jsp/standardui/initMiscellaneous.action", "M", 2));
			mapOperator.put("10200200", operatorBuilder("10200000", "10200200", "Button", "jsp/standardui/initButton.action", "M", 2));
			mapOperator.put("10200300", operatorBuilder("10200000", "10200300", "Dropdown List", "jsp/standardui/initDropdownlist.action", "M", 2));
			mapOperator.put("10200400", operatorBuilder("10200000", "10200400", "Autocomplete", "jsp/standardui/initAutocomplete.action", "M", 2));
//			mapOperator.put("10200500", operatorBuilder("10200000", "10200500", "Suggestion", "", "M", 2));
			mapOperator.put("10200600", operatorBuilder("10200000", "10200600", "Table", "jsp/standardui/initTable.action", "M", 2));
			mapOperator.put("10200700", operatorBuilder("10200000", "10200700", "Data Table", "jsp/standardui/initDataTable.action", "M", 2));
			mapOperator.put("10200800", operatorBuilder("10200000", "10200800", "Dialog", "jsp/standardui/initDialog.action", "M", 2));
			
			/** CSS Class **/
			mapOperator.put("10300000", operatorBuilder("10000000", "10300000", "CSS Class", "", "M", 1));
			mapOperator.put("10300100", operatorBuilder("10300000", "10300100", "CSS Class", "jsp/cssclass/initCssClass.action", "M", 2));
			
			/** Common Java Script Function **/
/*			mapOperator.put("10400000", operatorBuilder("10000000", "10400000", "Common Java Script Function", "", "M", 1));
			mapOperator.put("10400100", operatorBuilder("10400000", "10400100", "Validate Requier Input", "", "M", 2));
			mapOperator.put("10400200", operatorBuilder("10400000", "10400200", "Validate Requier Input Form", "", "M", 2));
			mapOperator.put("10400300", operatorBuilder("10400000", "10400300", "Validate Require Selected Group", "", "M", 2));
			mapOperator.put("10400400", operatorBuilder("10400000", "10400400", "Validate Input Format", "", "M", 2));
			mapOperator.put("10400500", operatorBuilder("10400000", "10400500", "Validate Selected Row", "", "M", 2));
			mapOperator.put("10400600", operatorBuilder("10400000", "10400600", "Submit Page", "", "M", 2));
			mapOperator.put("10400700", operatorBuilder("10400000", "10400700", "Submit Page Report", "", "M", 2));
			mapOperator.put("10400800", operatorBuilder("10400000", "10400800", "sf", "", "M", 2));*/
			
			/** JSP Template **/
/*			mapOperator.put("10500000", operatorBuilder("10000000", "10500000", "JSP Template", "", "M", 1));
			mapOperator.put("10500100", operatorBuilder("10500000", "10500100", "JSP Template", "jsp/template/initTemplate.action", "M", 2));*/

			/** Tutorial **/
			mapOperator.put("10600000", operatorBuilder("10000000", "10600000", "Tutorial", "", "M", 1));
			mapOperator.put("10600100", operatorBuilder("10600000", "10600100", "Tutorial", "jsp/tutorial/initProject.action", "M", 2));
			mapOperator.put("10600101", operatorBuilder("10600100", "10600101", "SEARCH", "", "F", 3));
			mapOperator.put("10600102", operatorBuilder("10600100", "10600102", "ADD", "", "F", 3));
			mapOperator.put("10600103", operatorBuilder("10600100", "10600103", "EDIT", "", "F", 3));
			mapOperator.put("10600104", operatorBuilder("10600100", "10600104", "VIEW", "", "F", 3));
			mapOperator.put("10600105", operatorBuilder("10600100", "10600105", "CHANGE", "", "F", 3));
			
			/** Employee **/
			mapOperator.put("10700000", operatorBuilder("10000000", "10700000", "Employee", "", "M", 1));
			mapOperator.put("10700100", operatorBuilder("10700000", "10700100", "Search", "jsp/tutorial/initEmployee.action", "M", 2));
			mapOperator.put("10700101", operatorBuilder("10700100", "10700101", "SEARCH", "", "F", 3));
			mapOperator.put("10700102", operatorBuilder("10700100", "10700102", "ADD", "", "F", 3));
			mapOperator.put("10700103", operatorBuilder("10700100", "10700103", "EDIT", "", "F", 3));
			mapOperator.put("10700104", operatorBuilder("10700100", "10700104", "VIEW", "", "F", 3));
			mapOperator.put("10700105", operatorBuilder("10700100", "10700105", "CHANGE", "", "F", 3));
			mapOperator.put("10700106", operatorBuilder("10700100", "10700106", "DELETE", "", "F", 3));
			
			
		} catch (Exception e) {
			throw e;
		}

		return mapOperator;
	}
	
	/**
	 * @deprecated ไม่ได้ใช้งาน
	 */
	@Override
	protected long countData(CCTConnection conn, Object criteria, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @deprecated ไม่ได้ใช้งาน
	 */
	@Override
	protected List<Object> search(CCTConnection conn, Object criteria, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @deprecated ไม่ได้ใช้งาน
	 */
	@Override
	protected Object searchById(CCTConnection conn, String id, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @deprecated ไม่ได้ใช้งาน
	 */
	@Override
	protected int add(CCTConnection conn, Object obj, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @deprecated ไม่ได้ใช้งาน
	 */
	@Override
	protected int edit(CCTConnection conn, Object obj, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @deprecated ไม่ได้ใช้งาน
	 */
	@Override
	protected int delete(CCTConnection conn, String ids, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @deprecated ไม่ได้ใช้งาน
	 */
	@Override
	protected int updateActive(CCTConnection conn, String ids, String activeFlag, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @deprecated ไม่ได้ใช้งาน
	 */
	@Override
	protected boolean checkDup(CCTConnection conn, Object obj, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	private Operator operatorBuilder(String parentId, String currentId, String label, String url, String type, int level) {
		Operator operator = new Operator();
		try {
			operator = new Operator();
			operator.setOperatorId(currentId);
			operator.setOperatorType(type);
			operator.setUrl(url);
			operator.setVisible("Y");
			operator.setParentId(parentId);
			operator.setCurrentId(currentId);
			operator.setLabel(label);
			operator.setSystemName(label);
			operator.setCurrentLevel(level);
			
		} catch (Exception e) {
			LogUtil.LOGIN.error("", e);
		}
		return operator;
	}

}
