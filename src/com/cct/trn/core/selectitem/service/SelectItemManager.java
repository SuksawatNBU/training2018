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
	 * กำหนดค่าเริ่มต้นให้กับ Combo ที่ไม่มีการเปลื่ยนบ่อย
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
	 * @deprecated ไม่ได้ใช้งาน
	 */
	@Override
	public List<Object> search(Object criteria) throws Exception {
		return null;
	}

	/**
	 * @deprecated ไม่ได้ใช้งาน
	 */
	@Override
	public Object searchById(String id) throws Exception {
		return null;
	}

	/**
	 * @deprecated ไม่ได้ใช้งาน
	 */
	@Override
	public int add(Object obj) throws Exception {
		return 0;
	}

	/**
	 * @deprecated ไม่ได้ใช้งาน
	 */
	@Override
	public int edit(Object obj) throws Exception {
		return 0;
	}

	/**
	 * @deprecated ไม่ได้ใช้งาน
	 */
	@Override
	public int delete(String ids) throws Exception {
		return 0;
	}

	/**
	 * @deprecated ไม่ได้ใช้งาน
	 */
	@Override
	public int updateActive(String ids, String activeFlag) throws Exception {
		return 0;
	}
	
	public List<CommonSelectItem> searchProvinceSelectItem(CCTConnection conn, Locale locale, String term, String limit) throws Exception {
		return service.searchProvinceSelectItem(conn, locale, term, limit);
	}
	
	/**
	 * Autocomplete ผู้ใช้งานระบบ QA (GM, SA, SD, PG และ QA)
	 * @param conn
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	public List<CommonSelectItem> searchAllQAUserAutoSelectItem(String term, String limit) throws Exception {
		return service.searchAllQAUserAutoSelectItem(term,limit);
	}
	
	/**
	 * Autocomplete โครงการ (project)
	 * @param term
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<CommonSelectItem> searchProjectsAutoSelectItem(String term, String limit) throws Exception {
		return service.searchProjectsAutoSelectItem(term,limit);
	}
	
	/**
	 * Autocomplete ระบบ (system)
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
	 * Autocomplete ระบบย่อย (sub system)
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
	 * ค้นหาข้องมูล AutoComplete Department  
	 * @param term
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<CommonSelectItem> searchDepartmentAutoSelectItem(String term, String limit) throws Exception {
		return service.searchDepartmentAutoSelectItem(term, limit);
	}
	
	/**
	 * ค้นหาข้องมูล AutoComplete User
	 * @param term
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<CommonSelectItem> searchUserAutoSelectItem(String term, String limit, String departmentId) throws Exception {
		return service.searchUserAutoSelectItem(term, limit, departmentId);
	}
}