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
	 * Autocomplete à¸œà¸¹à¹‰à¹ƒà¸Šà¹‰à¸‡à¸²à¸™à¸£à¸°à¸šà¸š QA (GM, SA, SD, PG à¹�à¸¥à¸° QA)
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
	 * Autocomplete à¹‚à¸„à¸£à¸‡à¸�à¸²à¸£ (project)
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
	 * Autocomplete à¸£à¸°à¸šà¸š (system)
	 * @param conn
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	protected List<CommonSelectItem> searchSystemsAutoSelectItem(String projectId, String systemName) throws Exception {
		return dao.searchSystemsAutoSelectItem(conn, locale, projectId, systemName);
	}
	
	/**
	 * Autocomplete à¸£à¸°à¸šà¸šà¸¢à¹ˆà¸­à¸¢ (sub system)
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
	 * à¸„à¹‰à¸™à¸«à¸²à¸‚à¹‰à¸­à¸‡à¸¡à¸¹à¸¥ AutoComplete Department  
	 * @param term
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	protected List<CommonSelectItem> searchDepartmentAutoSelectItem(String term, String limit) throws Exception {
		return dao.searchDepartmentAutoSelectItem(conn, locale, term, limit);
	}
	
	protected List<CommonSelectItem> searchPositionAutoSelectItem(String term, String limit, String departmentId) throws Exception {
		return dao.searchPositionAutoSelectItem(conn, locale, term, limit, departmentId);
	}
	
	/**
	 * à¸„à¹‰à¸™à¸«à¸²à¸‚à¹‰à¸­à¸‡à¸¡à¸¹à¸¥ AutoComplete User
	 * @param term
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	protected List<CommonSelectItem> searchUserAutoSelectItem(String term, String limit, String departmentId) throws Exception {
		return dao.searchUserAutoSelectItem(conn, locale, term, limit, departmentId);
	}
}