package com.cct.trn.core.selectitem.service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import util.database.CCTConnection;

import com.cct.abstracts.AbstractManager;
import com.cct.common.CommonSelectItem;
import com.cct.common.CommonUser;
import com.cct.domain.Language;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;

public class SelectItemManager extends AbstractManager<Object, Object, Object, Object, Object> {

	private static Map<Locale, Map<String, List<CommonSelectItem>>> mapGlobalData = new HashMap<Locale, Map<String, List<CommonSelectItem>>>();

	private SelectItemService service = null;

	public SelectItemManager(CCTConnection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		this.service = new SelectItemService(conn, user, locale);
	}

	/**
	 * à¸�à¸³à¸«à¸™à¸”à¸„à¹ˆà¸²à¹€à¸£à¸´à¹ˆà¸¡à¸•à¹‰à¸™à¹ƒà¸«à¹‰à¸�à¸±à¸š Combo à¸—à¸µà¹ˆà¹„à¸¡à¹ˆà¸¡à¸µà¸�à¸²à¸£à¹€à¸›à¸¥à¸·à¹ˆà¸¢à¸™à¸šà¹ˆà¸­à¸¢
	 *
	 * @throws Exception
	 */
	public void init() throws Exception {
		try {
			initGlobalDataSelectItem(conn);
		} catch (Exception e) {
			throw e;
		}
	}

	private void initGlobalDataSelectItem(CCTConnection conn) throws Exception {
		for (Language language : ParameterConfig.getParameter().getApplication().getSupportLanguageList()) {
			Map<String, List<CommonSelectItem>> mapSelectItem = service.searchGlobalDataSelectItem(language);
			if (mapSelectItem.size() == 0) {
				mapSelectItem = mapGlobalData.get(ParameterConfig.getParameter().getApplication().getApplicationLocale());
			}
			mapGlobalData.put(language.getLocale(), mapSelectItem);
		}
	}

	public static Map<Locale, Map<String, List<CommonSelectItem>>> getMapGlobalData() {
		return mapGlobalData;
	}

	public static void setMapGlobalData(Map<Locale, Map<String, List<CommonSelectItem>>> mapGlobalData) {
		SelectItemManager.mapGlobalData = mapGlobalData;
	}

	/**
	 * @deprecated à¹„à¸¡à¹ˆà¹„à¸”à¹‰à¹ƒà¸Šà¹‰à¸‡à¸²à¸™
	 */
	@Override
	public List<Object> search(Object criteria) throws Exception {
		return null;
	}

	/**
	 * @deprecated à¹„à¸¡à¹ˆà¹„à¸”à¹‰à¹ƒà¸Šà¹‰à¸‡à¸²à¸™
	 */
	@Override
	public Object searchById(String id) throws Exception {
		return null;
	}

	/**
	 * @deprecated à¹„à¸¡à¹ˆà¹„à¸”à¹‰à¹ƒà¸Šà¹‰à¸‡à¸²à¸™
	 */
	@Override
	public int add(Object obj) throws Exception {
		return 0;
	}

	/**
	 * @deprecated à¹„à¸¡à¹ˆà¹„à¸”à¹‰à¹ƒà¸Šà¹‰à¸‡à¸²à¸™
	 */
	@Override
	public int edit(Object obj) throws Exception {
		return 0;
	}

	/**
	 * @deprecated à¹„à¸¡à¹ˆà¹„à¸”à¹‰à¹ƒà¸Šà¹‰à¸‡à¸²à¸™
	 */
	@Override
	public int delete(String ids) throws Exception {
		return 0;
	}

	/**
	 * @deprecated à¹„à¸¡à¹ˆà¹„à¸”à¹‰à¹ƒà¸Šà¹‰à¸‡à¸²à¸™
	 */
	@Override
	public int updateActive(String ids, String activeFlag) throws Exception {
		return 0;
	}
	
	public List<CommonSelectItem> searchProvinceSelectItem(CCTConnection conn, Locale locale, String term, String limit) throws Exception {
		return service.searchProvinceSelectItem(conn, locale, term, limit);
	}
	
	/**
	 * Autocomplete à¸œà¸¹à¹‰à¹ƒà¸Šà¹‰à¸‡à¸²à¸™à¸£à¸°à¸šà¸š QA (GM, SA, SD, PG à¹�à¸¥à¸° QA)
	 * @param conn
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	public List<CommonSelectItem> searchAllQAUserAutoSelectItem(String term, String limit) throws Exception {
		return service.searchAllQAUserAutoSelectItem(term,limit);
	}
	
	/**
	 * Autocomplete à¹‚à¸„à¸£à¸‡à¸�à¸²à¸£ (project)
	 * @param term
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<CommonSelectItem> searchProjectsAutoSelectItem(String term, String limit) throws Exception {
		return service.searchProjectsAutoSelectItem(term,limit);
	}
	
	/**
	 * Autocomplete à¸£à¸°à¸šà¸š (system)
	 * 
	 * @param projectId
	 * @param systemName
	 * @return
	 * @throws Exception
	 */
	public List<CommonSelectItem> searchSystemsAutoSelectItem(String projectId, String systemName) throws Exception {
		return service.searchSystemsAutoSelectItem(projectId, systemName);
	}
	
	/**
	 * Autocomplete à¸£à¸°à¸šà¸šà¸¢à¹ˆà¸­à¸¢ (sub system)
	 * 
	 * @param systemId
	 * @param subSystemName
	 * @return
	 * @throws Exception
	 */
	public List<CommonSelectItem> searchSubSystemsAutoSelectItem(String systemId, String subSystemName) throws Exception {
		return service.searchSubSystemsAutoSelectItem(systemId, subSystemName);
	}
	
	/**
	 * à¸„à¹‰à¸™à¸«à¸²à¸‚à¹‰à¸­à¸‡à¸¡à¸¹à¸¥ AutoComplete Department  
	 * @param term
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<CommonSelectItem> searchDepartmentAutoSelectItem(String term, String limit) throws Exception {
		return service.searchDepartmentAutoSelectItem(term, limit);
	}
	
	public List<CommonSelectItem> searchPositionAutoSelectItem(String term, String limit, String departmentId) throws Exception {
		return service.searchPositionAutoSelectItem(term, limit, departmentId);
	}
	
	/**
	 * à¸„à¹‰à¸™à¸«à¸²à¸‚à¹‰à¸­à¸‡à¸¡à¸¹à¸¥ AutoComplete User
	 * @param term
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<CommonSelectItem> searchUserAutoSelectItem(String term, String limit, String departmentId) throws Exception {
		return service.searchUserAutoSelectItem(term, limit, departmentId);
	}
}