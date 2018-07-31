package util.mail.domain;

import java.util.HashMap;
import java.util.Map;

public class MailVariable {

	public enum TemplateCode {
		REG_REGIS_01("REG_REGIS_01")
		, ADM_USER_UCP_01("ADM_USER_UCP_01")
		, ADM_USER_UCP_02("ADM_USER_UCP_02")
		, REG_USER_02("REG_USER_02")
		, REG_USER_03("REG_USER_03")
		
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
		
	}

}
