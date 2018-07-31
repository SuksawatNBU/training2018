package util.mail.configuration;

import util.mail.domain.configuration.MailConfiguration;


public class MailConfigurationManager {

	public void init() throws Exception {
		try {
			new MailConfigurationService().init();
		} catch (Exception e) {

		}
	}

	public void init(String path) throws Exception {
		try {
			MailConfiguration.FILE_PATH = path;
			init();
		} catch (Exception e) {

		}
	}
	
	public void init(MailConfiguration config, String path) throws Exception {
		try {
			MailConfiguration.FILE_PATH = path;
			new MailConfigurationService().init(config);
		} catch (Exception e) {

		}
	}
}
