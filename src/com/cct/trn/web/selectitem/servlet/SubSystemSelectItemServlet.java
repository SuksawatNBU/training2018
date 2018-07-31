package com.cct.trn.web.selectitem.servlet;

import java.util.List;
import java.util.Locale;

import org.apache.struts2.ServletActionContext;

import util.database.CCTConnection;
import util.log.LogUtil;

import com.cct.common.CommonSelectItem;
import com.cct.common.CommonSelectItemServlet;
import com.cct.common.CommonUser;
import com.cct.trn.core.selectitem.service.SelectItemManager;

public class SubSystemSelectItemServlet extends CommonSelectItemServlet {

	private static final long serialVersionUID = -7137386246211593535L;

	@Override
	public List<CommonSelectItem> searchSelectItem(CCTConnection conn, Locale locale, CommonUser commonUser, String term, String limit) throws Exception {

		SelectItemManager manager = new SelectItemManager(conn, commonUser, locale);
		LogUtil.SELECTOR.debug("term: " + term + ", limit: " + limit);

		String systemId = "";
		if(ServletActionContext.getRequest().getParameter("systemId") != null){
			systemId = ServletActionContext.getRequest().getParameter("systemId");
		}
		
		return manager.searchSubSystemsAutoSelectItem(systemId, term);
	}
	
}