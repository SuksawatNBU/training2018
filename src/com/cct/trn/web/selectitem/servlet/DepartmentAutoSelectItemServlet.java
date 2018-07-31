package com.cct.trn.web.selectitem.servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import util.database.CCTConnection;
import util.log.LogUtil;

import com.cct.common.CommonSelectItem;
import com.cct.common.CommonSelectItemServlet;
import com.cct.common.CommonUser;
import com.cct.trn.core.selectitem.service.SelectItemManager;

public class DepartmentAutoSelectItemServlet extends CommonSelectItemServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7686039894563622122L;

	@Override
	public List<CommonSelectItem> searchSelectItem(CCTConnection conn, Locale locale, CommonUser commonUser, String term, String limit) throws Exception {
		LogUtil.SELECTOR.debug("DepartmentAutoSelectItemServlet");
		
		List<CommonSelectItem> lstSelectItems = new ArrayList<CommonSelectItem>(); 
		try {
			SelectItemManager manager = new SelectItemManager(conn, commonUser, locale);
			LogUtil.SELECTOR.debug("TERM: " + term + ", LIMIT: " + limit);
			
			lstSelectItems = manager.searchDepartmentAutoSelectItem(term, limit);
		
		} catch (Exception e) {
			LogUtil.SELECTOR.error(e);
		}
		return lstSelectItems; 
	}
}
