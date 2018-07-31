package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.cryptography.DecryptionUtil;
import util.mail.configuration.MailConfigurationManager;
import util.mail.domain.configuration.MailConfiguration;
import util.mail.domain.configuration.MailTemplate;
import util.mail.service.MailSenderManager;
import util.type.CrytographyType.DecType;

public class TestEmail {

	public static void main(String[] args) {

		String emailSender = "";
		String passwordSender = "";
		String mailServer = "";
		String mailProtocal = "";
		String mailPort = "";
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rst = null;
		try {
			// step1 load the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// step2 create the connection object
			conn = DriverManager.getConnection("jdbc:oracle:thin:@10.100.70.59:1521:ORCL", "cp_cambodia", "cp_Cambodia2016");

			// step3 create the statement object
			stmt = conn.createStatement();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM  EMA_EMAIL_CONFIG WHERE ACTIVE = 'Y' AND CREATE_SYSTEM = 'CP' ");
			
			// step4 execute query
			rst = stmt.executeQuery(sql.toString());
			if (rst.next()) {
				emailSender = rst.getString("EMAIL_SENDER");
				passwordSender = DecryptionUtil.doDecrypt(rst.getString("PASSWORD_SENDER"), DecType.AES128);
				mailServer = rst.getString("MAIL_SERVER");
				mailProtocal = rst.getString("MAIL_PROTOCOL");
				mailPort = rst.getString("MAIL_PORT");
			}
			
			String path = "D:/nanthaporn.p/2016/APPS_cambodia/02-workspace/wk-dev/cp_cambodia/cp/WEB-INF/";
			MailConfiguration config = new MailConfiguration();
			MailConfigurationManager manager = new MailConfigurationManager();
			manager.init(config, path);
			
			config.getCONFIG().setProtocal(mailPort);
			config.getCONFIG().setHost(mailServer);
			
			config.setEmail(emailSender);
			config.setPassword(passwordSender);
			config.setMailServer(mailServer);
			config.setMailProtocal(mailProtocal);
			
			MailTemplate mTemplate = new MailTemplate();
			mTemplate.setTemplateCode("ADM_USER_UCP_01");
			mTemplate.setTemplateSubject("Carrier Registration");
			mTemplate.setTemplatePurpose("process the approval for this Carrier and associated Carrier Administrator.");
			mTemplate.setTemplateContent("TEST SEND MAIL");
			
			List<String> infomations = new ArrayList<String>();
			infomations.add("ADM_USER_UCP_01");
			infomations.add(config.getEmail());
			infomations.add("<<receiver>>::nanthaporn.p@somapait.com||<<userID>>::nanthaporn.p||<<password>>::123456||none");
			
			MailSenderManager mailManager = new MailSenderManager();
			mailManager.send(mTemplate, infomations, config);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			// step5 close the connection object
			try {
				if (rst != null) {
					rst.close();
				}
				
				if (stmt != null) {
					stmt.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
