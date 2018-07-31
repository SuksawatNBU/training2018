package com.cct.trn.core.mail.service;

import java.util.Locale;

import util.database.CCTConnection;
import util.mail.domain.configuration.MailTemplate;

import com.cct.abstracts.AbstractService;
import com.cct.common.CommonUser;
import com.cct.trn.core.config.parameter.domain.SQLPath;
import com.cct.trn.core.log.domain.MailErrorLog;

public class MailService extends AbstractService {
	private MailDAO dao = new MailDAO();
	public MailService(CCTConnection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		this.dao = new MailDAO();
		this.dao.setSqlPath(SQLPath.MAIL_SQL);
	}

	/**
	 * ค้นหา template โดยใช้ template code
	 * @param id
	 * @return
	 * @throws Exception
	 */
	protected MailTemplate searchById(String templateCode) throws Exception {
		return dao.searchById(conn, templateCode, user, locale);
	}

	protected void searchMailConfig(MailErrorLog errorLog) throws Exception{
		dao.searchMailConfig(conn, errorLog);
	}

}
