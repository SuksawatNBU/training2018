package com.cct.trn.core.security.login.service;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import util.database.CCTConnection;

import com.cct.abstracts.AbstractService;
import com.cct.common.CommonUser;
import com.cct.domain.Operator;
import com.cct.trn.core.config.parameter.domain.SQLPath;

public class LoginService extends AbstractService {

	private LoginDAO dao = null;

	public LoginService(CCTConnection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		this.dao = new LoginDAO();
		this.dao.setSqlPath(SQLPath.LOGIN_SQL);
	}

	/**
	 * ค้นหา operator
	 * @param commonUser
	 * @param config
	 * @param siteId
	 * @throws Exception
	 */
	protected void searchMenuAndFunction(CommonUser commonUser) throws Exception {
		try {
			Map<String, Operator> mapOperator = new LinkedHashMap<String, Operator>();
			mapOperator =  searchDetailOperatorByOperatorId(null, locale, null);
			commonUser.setMapOperator(mapOperator);
			
		} catch (Exception e){
			throw e;
		}
	}

	/**
	 * ค้นหารายละเอียดเมนูและสิทธิ์
	 * @param operatorIds
	 * @param locale
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	protected Map<String, Operator> searchDetailOperatorByOperatorId(String operatorIds, Locale locale, Long siteId) throws Exception {

		Map<String, Operator> mapOperator = new LinkedHashMap<String, Operator>();

		try {
			Operator operatorLevel = dao.searchOperatorLevel(conn, operatorIds, locale, siteId);
			
			mapOperator = dao.searchOperator(conn, operatorIds, locale, siteId);
			for (String key : mapOperator.keySet()) {
				mapOperator.get(key).setMinLevel(operatorLevel.getMinLevel());
				mapOperator.get(key).setMaxLevel(operatorLevel.getMaxLevel());
				break;
			}
		} catch (Exception e) {
			throw e;
		}

		return mapOperator;
	}
}