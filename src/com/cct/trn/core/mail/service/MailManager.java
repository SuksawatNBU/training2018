package com.cct.trn.core.mail.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import util.database.CCTConnection;
import util.mail.configuration.MailConfigurationService;
import util.mail.domain.configuration.MailTemplate;
import util.mail.service.MailSenderManager;

import com.cct.abstracts.AbstractManager;
import com.cct.common.CommonUser;
import com.cct.trn.core.log.domain.MailErrorLog;

public class MailManager extends AbstractManager<Object, Object, MailTemplate, CommonUser, Locale> {

	private MailService service = null;

	public enum TemplateCode {
		REG_REGIS_01("REG_REGIS_01")
		, ADM_USER_UCP_01("ADM_USER_UCP_01")
		, ADM_USER_UCP_02("ADM_USER_UCP_02")
		, REG_USER_02("REG_USER_02")
		, REG_USER_03("REG_USER_03")
		, CPT_USER_01_A("CPT_USER_01_A")
		, CPT_USER_02_A("CPT_USER_02_A")
		, CPT_USER_03_A("CPT_USER_03_A")
		, CPT_USER_03_N("CPT_USER_03_N")
		;

		private String value;

		private TemplateCode(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}


	public static final Map<TemplateCode, String> MAP_OF_MAIL_FORMAT = new HashMap<TemplateCode, String>();
	static {
		MAP_OF_MAIL_FORMAT.put(TemplateCode.REG_REGIS_01, "<<receiver>>::%s||<<carrier_name_subject>>::%s||<<carrier_type_desc>>::%s||<<carrier_code>>::%s||<<carrier_name>>::%s||<<family_name>>::%s||<<given_name>>::%s||<<phone_number>>::%s||<<ext_number>>::%s||<<fax_number>>::%s||<<email_address>>::%s||<<time_create>>::%s||none");
		MAP_OF_MAIL_FORMAT.put(TemplateCode.ADM_USER_UCP_01, "<<receiver>>::%s||<<userID>>::%s||<<password>>::%s||none");
		MAP_OF_MAIL_FORMAT.put(TemplateCode.ADM_USER_UCP_02, "<<receiver>>::%s||<<userID>>::%s||<<password>>::%s||none");
		MAP_OF_MAIL_FORMAT.put(TemplateCode.REG_USER_02, "<<receiver>>::%s||<<carrier_name_subject>>::%s||<<carrier_code>>::%s||<<carrier_type_desc>>::%s||<<carrier_name>>::%s||<<user_id>>::%s||<<family_name>>::%s||<<given_name>>::%s||<<phone_number>>::%s||<<ext_number>>::%s||<<email_address>>::%s||none");
		MAP_OF_MAIL_FORMAT.put(TemplateCode.REG_USER_03, "<<receiver>>::%s||<<user_id>>::%s||<<family_name>>::%s||<<given_name>>::%s||<<phone_number>>::%s||<<ext_number>>::%s||<<email_address>>::%s||none");
		
		MAP_OF_MAIL_FORMAT.put(TemplateCode.CPT_USER_01_A, "<<receiver>>::%s||<<fullname>>::%s||<<userId>>::%s||<<password>>::%s||<<carrierType>>::%s||<<carrierCode>>::%s||<<carrierName>>::%s||<<familyName>>::%s||<<givenName>>::%s||<<telephoneContact>>::%s||<<facsimileNumber>>::%s||<<emailAddress>>::%s||<<accountExpiryDate>>::%s||<<timeCreated>>::%s||<<contactAdmin>>::%s||none");
		MAP_OF_MAIL_FORMAT.put(TemplateCode.CPT_USER_02_A, "<<receiver>>::%s||<<fullname>>::%s||<<userId>>::%s||<<password>>::%s||<<carrierType>>::%s||<<carrierCode>>::%s||<<carrierName>>::%s||<<familyName>>::%s||<<givenName>>::%s||<<telephoneContact>>::%s||<<facsimileNumber>>::%s||<<emailAddress>>::%s||<<accountExpiryDate>>::%s||<<timeCreated>>::%s||<<contactAdmin>>::%s||none");
		MAP_OF_MAIL_FORMAT.put(TemplateCode.CPT_USER_03_A, "<<receiver>>::%s||<<fullname>>::%s||<<userId>>::%s||<<carrierType>>::%s||<<carrierCode>>::%s||<<carrierName>>::%s||<<familyName>>::%s||<<givenName>>::%s||<<telephoneContact>>::%s||<<facsimileNumber>>::%s||<<emailAddress>>::%s||<<accountExpiryDate>>::%s||<<timeCreated>>::%s||<<contactAdmin>>::%s||none");
		MAP_OF_MAIL_FORMAT.put(TemplateCode.CPT_USER_03_N, "<<receiver>>::%s||<<fullname>>::%s||<<userId>>::%s||<<carrierType>>::%s||<<carrierCode>>::%s||<<carrierName>>::%s||<<familyName>>::%s||<<givenName>>::%s||<<telephoneContact>>::%s||<<facsimileNumber>>::%s||<<emailAddress>>::%s||<<accountExpiryDate>>::%s||<<timeCreated>>::%s||<<contactCarrierAdmin>>::%s||none");
	}


	public void send(List<String> infomations, TemplateCode templateCode, MailErrorLog errorLog) throws Exception {
		service.searchMailConfig(errorLog);
		MailTemplate template = service.searchById(templateCode.getValue());
		// set EMAIL_TEMPLATE_ID : ใช้สำหรับลง log email error
		errorLog.setEmailTemplateId(template.getTemplateId());

		List<String> lstMail = new ArrayList<String>();
		lstMail.add(templateCode.getValue());
		lstMail.add(MailConfigurationService.getMailConfigOfficer().getEmail());
		lstMail.addAll(infomations);

		MailSenderManager manager = new MailSenderManager();
		manager.send(template, lstMail, MailConfigurationService.getMailConfigOfficer());
	}


	public MailManager(CCTConnection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		this.service = new MailService(conn, user, locale);
	}

	@Override
	public List<Object> search(Object criteria) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MailTemplate searchById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int add(MailTemplate obj) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edit(MailTemplate obj) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String ids) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateActive(String ids, String activeFlag) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


}
