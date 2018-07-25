package com.cct.trn.web.selectitem.servlet;

import java.util.List;
import java.util.Locale;

import util.database.CCTConnection;
import util.log.LogUtil;

import com.cct.common.CommonSelectItem;
import com.cct.common.CommonSelectItemServlet;
import com.cct.common.CommonUser;
import com.cct.trn.core.selectitem.service.SelectItemManager;

public class QaUserAutoSelectItemServlet extends CommonSelectItemServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1167540527271932442L;

	@Override
	public List<CommonSelectItem> searchSelectItem(CCTConnection conn, Locale locale, CommonUser commonUser, String term, String limit) throws Exception {

		SelectItemManager manager = new SelectItemManager(conn, commonUser, locale);
		LogUtil.SELECTOR.debug("TERM: " + term + ", LIMIT: " + limit);

		return manager.searchAllQAUserAutoSelectItem(term, limit);
	}
}
