package com.cct.trn.web.selectitem.servlet;

import java.util.List;
import java.util.Locale;

import util.database.CCTConnection;
import util.log.LogUtil;

import com.cct.common.CommonSelectItem;
import com.cct.common.CommonSelectItemServlet;
import com.cct.common.CommonUser;
import com.cct.trn.core.selectitem.service.SelectItemManager;

//@WebServlet("/suggestDataSelectItemServlet")
public class SuggestDataSelectItemServlet extends CommonSelectItemServlet {

	private static final long serialVersionUID = -2018123156904737038L;

	@Override
	public List<CommonSelectItem> searchSelectItem(CCTConnection conn, Locale locale, CommonUser commonUser, String term, String limit) throws Exception {
		LogUtil.SELECTOR.debug("term: " + term + ", limit: " + limit);
		SelectItemManager manager = new SelectItemManager(conn, commonUser, locale);
		return manager.searchAllQAUserAutoSelectItem(term, limit);
	}
}
