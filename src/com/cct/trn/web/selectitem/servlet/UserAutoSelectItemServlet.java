package com.cct.trn.web.selectitem.servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.struts2.ServletActionContext;

import util.database.CCTConnection;
import util.log.LogUtil;

import com.cct.common.CommonSelectItem;
import com.cct.common.CommonSelectItemServlet;
import com.cct.common.CommonUser;
import com.cct.trn.core.selectitem.service.SelectItemManager;

public class UserAutoSelectItemServlet extends CommonSelectItemServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7143849373819145463L;

	@Override
	public List<CommonSelectItem> searchSelectItem(CCTConnection conn, Locale locale, CommonUser commonUser, String term, String limit) throws Exception {
		LogUtil.SELECTOR.debug("UserAutoSelectItemServlet");
		
		List<CommonSelectItem> lstSelectItems = new ArrayList<CommonSelectItem>(); 
		try {
			String departmentId = "";
			if(ServletActionContext.getRequest().getParameter("departmentId") != null){
				departmentId = ServletActionContext.getRequest().getParameter("departmentId");
			}
			
			SelectItemManager manager = new SelectItemManager(conn, commonUser, locale);
			LogUtil.SELECTOR.debug("TERM: " + term + ", LIMIT: " + limit + ", DEPARTMENT ID: " + departmentId);
			
			lstSelectItems = manager.searchUserAutoSelectItem(term, limit, departmentId);
		
		} catch (Exception e) {
			LogUtil.SELECTOR.error(e);
		}
		return lstSelectItems; 
	}
}
