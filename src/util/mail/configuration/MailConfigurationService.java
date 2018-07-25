/**
 * @Author : kampon.n
 * @Version 1.0
 * @Create date :
 * @Last update : 2012-08-31
 * @Current Version 1.0
 */
package util.mail.configuration;

import java.util.List;

import util.file.FileManagerUtil;
import util.log.LogUtil;
import util.mail.domain.configuration.MailConfiguration;
import util.mail.domain.configuration.MailProperty;
import util.mail.domain.logging.MailDataLogging;
import util.string.StringUtil;
import util.xml.XmlReadUtil;

public class MailConfigurationService {

	private static MailConfiguration mailConfigOfficer = new MailConfiguration();

	protected void init(MailConfiguration mailConfig) throws Exception {

		//PropertyConfigurator.configure(MailConfiguration.FILE_PATH + MailConfiguration.FILE_LOG);

		XmlReadUtil reader = new XmlReadUtil(MailConfiguration.FILE_PATH + MailConfiguration.FILE_CONFIG);

		MailProperty propertry = new MailProperty();
		propertry.setProtocal(reader.doRead("property", "protocal"));
		propertry.setHost(reader.doRead("property", "host"));
		propertry.setSmtpTransport(reader.doRead("property", "smtp-transport"));
		propertry.setSmtpAuth(reader.doRead("property", "smtp-auth"));
		propertry.setSmtpTlsEnable(reader.doRead("property", "smtp-tls-enable"));
		propertry.setSmtpSslTrust(reader.doRead("property", "smtp-ssl-trust"));
		propertry.setSmtpSocketFactory(reader.doRead("property", "mail-smtp-socketFactory"));

		MailProperty config = new MailProperty();
		config.setSmtpTransport(reader.doRead("config", "smtp-transport"));
		config.setUseSmtpAuth(Boolean.valueOf(reader.doRead("config", "smtp-auth")));
		config.setUseSmtpTlsEnable(Boolean.valueOf(reader.doRead("config", "smtp-tls-enable")));
		config.setUseSmtpSslTrust(Boolean.valueOf(reader.doRead("config", "smtp-ssl-trust")));
		config.setUseDebug(Boolean.valueOf(reader.doRead("config", "debug")));
		config.setEncoding(reader.doRead("config", "encoding"));
		config.setContentType(reader.doRead("config", "content-type"));
		config.setMultipart(reader.doRead("config", "multi-part"));
		config.setSmtpSocketFactory(StringUtil.nullToString(reader.doRead("config", "mail-smtp-socketFactory")));

		mailConfig.setAttachNoLimit(Integer.parseInt(reader.doRead("mailServer","attach_no_limit")));
		mailConfig.setAttachSizeLimit(Integer.parseInt(reader.doRead("mailServer","attach_size_limit")));
		mailConfig.setAttachPath(reader.doRead("mailServer","attach_path"));
		FileManagerUtil.crateDirectoryWithoutOverwrite(mailConfig.getAttachPath());

		// ใช้ SecurityConfig

		mailConfig.PROPERTY = propertry;
		mailConfig.CONFIG = config;

		MailConfigurationService.setMailConfigOfficer(mailConfig);
	}

	protected void init() {
		LogUtil.MAIL.info("");
		try {
//			System.out.println(MailConfiguration.FILE_PATH + MailConfiguration.FILE_LOG);
			//PropertyConfigurator.configure(MailConfiguration.FILE_PATH + MailConfiguration.FILE_LOG);

			XmlReadUtil reader = new XmlReadUtil(MailConfiguration.FILE_PATH + MailConfiguration.FILE_CONFIG);

			MailProperty propertry = new MailProperty();
			propertry.setProtocal(reader.doRead("property", "protocal"));
			propertry.setHost(reader.doRead("property", "host"));
			propertry.setSmtpTransport(reader.doRead("property", "smtp-transport"));
			propertry.setSmtpAuth(reader.doRead("property", "smtp-auth"));
			propertry.setSmtpTlsEnable(reader.doRead("property", "smtp-tls-enable"));
			propertry.setSmtpSslTrust(reader.doRead("property", "smtp-ssl-trust"));
			propertry.setSmtpSocketFactory(reader.doRead("property", "mail-smtp-socketFactory"));

			MailProperty config = new MailProperty();
			config.setSmtpTransport(reader.doRead("config", "smtp-transport"));
			config.setUseSmtpAuth(Boolean.valueOf(reader.doRead("config", "smtp-auth")));
			config.setUseSmtpTlsEnable(Boolean.valueOf(reader.doRead("config", "smtp-tls-enable")));
			config.setUseSmtpSslTrust(Boolean.valueOf(reader.doRead("config", "smtp-ssl-trust")));
			config.setUseDebug(Boolean.valueOf(reader.doRead("config", "debug")));
			config.setEncoding(reader.doRead("config", "encoding"));
			config.setContentType(reader.doRead("config", "content-type"));
			config.setMultipart(reader.doRead("config", "multi-part"));
			config.setSmtpSocketFactory(StringUtil.nullToString(reader.doRead("config", "mail-smtp-socketFactory")));

			MailConfiguration mailConfig = new MailConfiguration();
			mailConfig.setAttachNoLimit(Integer.parseInt(reader.doRead("mailServer","attach_no_limit")));
			mailConfig.setAttachSizeLimit(Integer.parseInt(reader.doRead("mailServer","attach_size_limit")));
			mailConfig.setAttachPath(reader.doRead("mailServer","attach_path"));
			FileManagerUtil.crateDirectoryWithoutOverwrite(mailConfig.getAttachPath());

			mailConfig.setMailProtocal(reader.doRead("mailServer","mail_protocol"));
			
			mailConfig.PROPERTY = propertry;
			mailConfig.CONFIG = config;

			MailConfigurationService.setMailConfigOfficer(mailConfig);

		} catch (Exception e) {
			LogUtil.MAIL.error("", e);
		}
	}



	/**
	 * @description check length of information
	 * @param infomations
	 * @return MailDataLogging log
	 */
	public MailDataLogging searchSenderList(List<String> infomations) {
		LogUtil.MAIL.info("");
		MailDataLogging log = null;
		if (infomations.size() < MailConfiguration.MIN_OF_SENDER_LIST) {
			log = new MailDataLogging();
			log.setType(MailDataLogging.STATUS_DATA);
			log.setMessage("Detail NotFound");
			return log;
		} else {
			return log;
		}

	}

	public static MailConfiguration getMailConfigOfficer() {
		return mailConfigOfficer;
	}

	public static void setMailConfigOfficer(MailConfiguration mailConfigOfficer) {
		MailConfigurationService.mailConfigOfficer = mailConfigOfficer;
	}


}
