package com.cct.trn.web.selectitem.servlet;

import java.util.List;
import java.util.Locale;

import util.database.CCTConnection;
import util.log.LogUtil;

import com.cct.common.CommonSelectItem;
import com.cct.common.CommonSelectItemServlet;
import com.cct.common.CommonUser;
import com.cct.trn.core.selectitem.service.SelectItemManager;

public class ProjectSelectItemServlet extends CommonSelectItemServlet {
	
	private static final long serialVersionUID = 44557595987018569L;

	@Override
	public List<CommonSelectItem> searchSelectItem(CCTConnection conn, Locale locale, CommonUser commonUser, String term, String limit) throws Exception {

		SelectItemManager manager = new SelectItemManager(conn, commonUser, locale);
		LogUtil.SELECTOR.debug("TERM: " + term + ", LIMIT: " + limit);

		return manager.searchProjectsAutoSelectItem(term, limit);
	}
}
