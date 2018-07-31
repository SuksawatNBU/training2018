package com.cct.trn.core.mail.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Locale;

import util.cryptography.DecryptionUtil;
import util.database.CCTConnection;
import util.database.CCTConnectionUtil;
import util.database.SQLUtil;
import util.log.LogUtil;
import util.mail.configuration.MailConfigurationService;
import util.mail.domain.configuration.MailTemplate;
import util.string.StringUtil;
import util.type.CrytographyType.DecType;
import util.type.StringType.ResultType;

import com.cct.abstracts.AbstractDAO;
import com.cct.common.CommonUser;
import com.cct.trn.core.log.domain.MailErrorLog;

public class MailDAO extends AbstractDAO<Object, Object, MailTemplate, CommonUser, Locale> {

	@Override
	protected long countData(CCTConnection conn, Object criteria, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected List<Object> search(CCTConnection conn, Object criteria, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	protected void searchMailConfig(CCTConnection conn, MailErrorLog errorLog) throws Exception{
		Statement stmt = null;
		ResultSet rst = null;
		String sql = SQLUtil.getSQLString(conn.getSchemas()
				, getSqlPath().getClassName()
				, getSqlPath().getPath()
				, "searchMailConfig"
				);
		try{
			LogUtil.MAIL.debug(sql);

			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			if(rst.next()){
				MailConfigurationService.getMailConfigOfficer().setEmail(StringUtil.nullToString(rst.getString("EMAIL_SENDER")));
				MailConfigurationService.getMailConfigOfficer().setPassword(DecryptionUtil.doDecrypt(StringUtil.nullToString(rst.getString("PASSWORD_SENDER")), DecType.AES128));
				MailConfigurationService.getMailConfigOfficer().setMailProtocal(StringUtil.nullToString(rst.getString("MAIL_PROTOCOL")));
				MailConfigurationService.getMailConfigOfficer().setMailServer(StringUtil.nullToString(rst.getString("MAIL_SERVER")));
				MailConfigurationService.getMailConfigOfficer().getCONFIG().setProtocal(StringUtil.nullToString(rst.getString("MAIL_PORT")));

				errorLog.setEmailConfigId(StringUtil.nullToString(rst.getString("EMAIL_CONFIG_ID")));
				errorLog.setEmailSender(StringUtil.nullToString(rst.getString("EMAIL_SENDER")));
			}
		}catch(Exception e){
			throw e;
		}
	}




	@Override
	protected MailTemplate searchById(CCTConnection conn, String templateCode, CommonUser user, Locale locale) throws Exception {
		MailTemplate result = null;

		int paramIndex = 0;
		Object[] params = new Object[1];
		params[paramIndex++] = StringUtil.replaceSpecialString(templateCode, conn.getDbType(), ResultType.EMPTY);
		String sql = SQLUtil.getSQLString(conn.getSchemas()
			, getSqlPath().getClassName()
			, getSqlPath().getPath()
			, "searchTemplate"
			, params);
		LogUtil.MAIL.debug(sql);

		Statement stmt = null;
		ResultSet rst = null;
		try {
			stmt = conn.createStatement();
			rst = stmt.executeQuery(sql);
			if (rst.next()) {
				result = new MailTemplate();
				result.setTemplateCode(StringUtil.nullToString(rst.getString("TEMPLATE_CODE")));
				result.setTemplateSubject(StringUtil.nullToString(rst.getString("TEMPLATE_SUBJECT")));
				result.setTemplatePurpose(StringUtil.nullToString(rst.getString("TEMPLATE_PURPOSE")));
				result.setTemplateContent(StringUtil.nullToString(rst.getString("TEMPLATE_CONTENT")));
				result.setTemplateId(StringUtil.nullToString(rst.getString("EMAIL_TEMPLATE_ID")));
			}
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(rst, stmt);
		}
		return result;
	}

	@Override
	protected int add(CCTConnection conn, MailTemplate obj, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int edit(CCTConnection conn, MailTemplate obj, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int delete(CCTConnection conn, String ids, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int updateActive(CCTConnection conn, String ids, String activeFlag, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected boolean checkDup(CCTConnection conn, MailTemplate obj, CommonUser user, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
