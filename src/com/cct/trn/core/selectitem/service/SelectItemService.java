package com.cct.trn.core.selectitem.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import util.database.CCTConnection;

import com.cct.abstracts.AbstractService;
import com.cct.common.CommonSelectItem;
import com.cct.common.CommonUser;
import com.cct.domain.Language;
import com.cct.trn.core.config.parameter.domain.SQLPath;

public class SelectItemService extends AbstractService {

	private SelectItemDAO dao = null;

	public SelectItemService(CCTConnection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		this.dao = new SelectItemDAO();
		this.dao.setSqlPath(SQLPath.SELECT_ITEM_SQL);
	}

	protected Map<String, List<CommonSelectItem>> searchGlobalDataSelectItem(Language language) throws Exception {
		return dao.searchGlobalDataSelectItem(conn, language.getLocale());
	}

	protected List<CommonSelectItem> searchProvinceSelectItem(CCTConnection conn, Locale locale, String term, String limit) throws Exception {
		return dao.searchProvinceSelectItem(conn, locale, term, limit);
	}
	
	/**
	 * Autocomplete ผู้ใช้งานระบบ QA (GM, SA, SD, PG และ QA)
	 * 
	 * @param term
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	protected List<CommonSelectItem> searchAllQAUserAutoSelectItem(String term, String limit) throws Exception {
		return dao.searchAllQAUserAutoSelectItem(conn, locale, term, limit);
	}
	
	/**
	 * Autocomplete โครงการ (project)
	 * 
	 * @param term
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	protected List<CommonSelectItem> searchProjectsAutoSelectItem(String term, String limit) throws Exception {
		return dao.searchProjectsAutoSelectItem(conn, locale, term, limit);
	}
	
	/**
	 * Autocomplete ระบบ (system)
	 * @param conn
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	protected List<CommonSelectItem> searchSystemsAutoSelectItem(String projectId, String systemName) throws Exception {
		return dao.searchSystemsAutoSelectItem(conn, locale, projectId, systemName);
	}
	
	/**
	 * Autocomplete ระบบย่อย (sub system)
	 * 
	 * @param systemId
	 * @param subSystemName
	 * @return
	 * @throws Exception
	 */
	protected List<CommonSelectItem> searchSubSystemsAutoSelectItem(String systemId, String subSystemName) throws Exception {
		return dao.searchSubSystemsAutoSelectItem(conn, locale, systemId, subSystemName);
	}
	
	/**
	 * ค้นหาข้องมูล AutoComplete Department  
	 * @param term
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	protected List<CommonSelectItem> searchDepartmentAutoSelectItem(String term, String limit) throws Exception {
		return dao.searchDepartmentAutoSelectItem(conn, locale, term, limit);
	}
	
	/**
	 * ค้นหาข้องมูล AutoComplete User
	 * @param term
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	protected List<CommonSelectItem> searchUserAutoSelectItem(String term, String limit, String departmentId) throws Exception {
		return dao.searchUserAutoSelectItem(conn, locale, term, limit, departmentId);
	}
}