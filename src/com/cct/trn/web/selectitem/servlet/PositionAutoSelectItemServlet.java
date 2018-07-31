package com.cct.trn.web.selectitem.servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.cct.common.CommonSelectItem;
import com.cct.common.CommonSelectItemServlet;
import com.cct.common.CommonUser;
import com.cct.trn.core.selectitem.service.SelectItemManager;

import util.database.CCTConnection;
import util.log.LogUtil;
import util.web.SessionUtil;

public class PositionAutoSelectItemServlet extends CommonSelectItemServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8011082820043746205L;
	
	@Override
	public List<CommonSelectItem> searchSelectItem(CCTConnection conn, Locale locale, CommonUser commonUser, String term, String limit) throws Exception {
		LogUtil.SELECTOR.debug("PositionAutoSelectItemServlet");
		
		List<CommonSelectItem> lstSelectItems = new ArrayList<CommonSelectItem>(); 
		try {	
			String departmentId = "";
			if (SessionUtil.requestParameter("departmentId") != null) {
				departmentId = SessionUtil.requestParameter("departmentId");
			}
			
			SelectItemManager manager = new SelectItemManager(conn, commonUser, locale);
			LogUtil.SELECTOR.debug("TERM: " + term + ", LIMIT: " + limit + ", DepartmentId: " + departmentId);
			
			lstSelectItems = manager.searchPositionAutoSelectItem(term, limit, departmentId);
		
		} catch (Exception e) {
			LogUtil.SELECTOR.error(e);
		}
		return lstSelectItems; 
	}

}
