package util.mail.service;

import java.io.File;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.naming.InitialContext;

import util.log.LogUtil;
import util.mail.domain.configuration.MailConfiguration;
import util.mail.domain.content.MailAttach;
import util.mail.domain.content.MailItem;
import util.mail.domain.content.MailSender;
import util.mail.domain.logging.MailDataLogging;
import util.mail.domain.logging.MailProcessLogging;

import com.sun.mail.smtp.SMTPTransport;

public class MailSenderService {

	/**
	 *
	 * @param mail
	 * @return MailProcessLogging
	 */
	public MailProcessLogging send(MailSender mail, MailConfiguration mConfig) {
		LogUtil.MAIL.info("send");
		MailProcessLogging log = null;


//		String host = "10.10.1.44";
//		String user = "appsservice@thai-apps.com";
//		String password = "BlueberryCheesecake2015";
//
//		Properties props = System.getProperties();
//
//		props.put(mConfig.PROPERTY.getHost(), mConfig.getMailServer());
//		props.put(mConfig.PROPERTY.getSmtpTransport(), mConfig.CONFIG.getSmtpTransport());
//		props.put(mConfig.PROPERTY.getSmtpSslTrust(), mConfig.CONFIG.isUseSmtpAuth());
//		props.put(mConfig.PROPERTY.getProtocal(), mConfig.CONFIG.getProtocal());
//		props.put(mConfig.PROPERTY.getSmtpSslTrust(), mConfig.getMailServer());
//
//		Session session = Session.getDefaultInstance(props, null);
//		session.setDebug(true);



		try {

//			Transport transport = session.getTransport("smtps");
//
//			MimeMessage mimemessage = new MimeMessage(session);
//			mimemessage.setSubject(mail.getSubject(), mConfig.CONFIG.getEncoding());
//			mimemessage.setFrom(new InternetAddress(mConfig.getEmail()));
//			mimemessage.addRecipients(Message.RecipientType.TO, mail.getRecivers());
//
//			// เน€เธ�เธดเน�เธกเธฃเธญเธ�เธฃเธฑเธ�เธ�เธฒเธฃเธชเน�เธ� mail เน�เธ�เธ�เธกเธต carbon copy
//			if (mail.getReciversCC() != null && mail.getReciversCC().length > 0) {
//				mimemessage.addRecipients(Message.RecipientType.CC, mail.getReciversCC());
//			}
//
//			String html = mail.getBody();
//			BodyPart bodypart = new MimeBodyPart();
//			bodypart.setContent(html, mConfig.CONFIG.getContentType());
//
//			MimeMultipart multipart = new MimeMultipart(mConfig.CONFIG.getMultipart());
//			multipart.addBodyPart(bodypart);
//
//			for (File file : mail.getFiles()) {
//				FileDataSource source = new FileDataSource(file);
//				MimeBodyPart mimebodypart = new MimeBodyPart();
//				mimebodypart.setDataHandler(new DataHandler(source));
//				mimebodypart.setFileName(MimeUtility.encodeText(source.getName()));
//				multipart.addBodyPart(mimebodypart);
//			}
//
//			mimemessage.setContent(multipart);
//			mimemessage.setSentDate(new java.util.Date());
//			mimemessage.saveChanges();
//
//
//			transport.connect(mConfig.getMailServer(), mConfig.getEmail(), mail.getConfiguration().getPassword());
//			transport.sendMessage(mimemessage, mimemessage.getAllRecipients());
//			transport.close();


			// mConfig.setEmail("contact@pinkanakorn.or.th");
			// mConfig.setMailServer("mail.pinkanakorn.or.th");
			// mConfig.setPassword("12345");

			LogUtil.MAIL.debug("fix bug JDK-7016078 : javax.net.ssl.SSLException");
			LogUtil.MAIL.debug(mConfig.CONFIG.getProtocal());
			// TODO: fix bug JDK-7016078 : javax.net.ssl.SSLException: Received
			// fatal alert: internal_error starting JDK 7 b126
			// http://bugs.java.com/view_bug.do?bug_id=7016078
			// เธ�เธงเธฃเธ�เธณเธซเธ�เธ”เธ�เน�เธฒ "com.sun.net.ssl.enableECC=false" เธ—เธตเน� java option เธ�เธญเธ�
			// jvm
			// System.setProperty("com.sun.net.ssl.enableECC", "false");
			Properties properties = System.getProperties();
			properties.put(mConfig.PROPERTY.getProtocal(), mConfig.CONFIG.getProtocal()); // mail.smtp.port: 465
			properties.put(mConfig.PROPERTY.getHost(), mConfig.getMailServer()); // mail.smtp.host: smtp.gmail.com
			properties.put(mConfig.PROPERTY.getSmtpSocketFactory(), mConfig.CONFIG.getSmtpSocketFactory()); // mail.smtp.socketFactory.class: javax.net.ssl.SSLSocketFactory
			//properties.put("ssl.SocketFactory.provider", "com.ibm.jsse2.SSLSocketFactoryImpl");
			//properties.put("ssl.ServerSocketFactory.provider", "com.ibm.jsse2.SSLServerSocketFactoryImpl");

			// properties.put("com.sun.net.ssl.enableECC", "false");
			properties.put("mail.smtp.timeout", "6000");
			properties.put("mail.smtp.connectiontimeout", "6000");

			Session session = null;
			if (mConfig.CONFIG.isUseSmtpAuth()) {
				LogUtil.MAIL.debug("session use authen");
				properties.put(mConfig.PROPERTY.getSmtpAuth(), mConfig.CONFIG.isUseSmtpAuth());
				Authenticator authenticator = new SMTPAuthenticator(mConfig.getEmail(), mConfig.getPassword());
				session = Session.getInstance(properties, authenticator);
				
//				InitialContext context = new InitialContext();
//				LogUtil.MAIL.debug(session);
//				session = (Session) context.lookup("mail/localmail");
//				LogUtil.MAIL.debug(session);
				
			} else {
				session = Session.getInstance(properties);
			}
			session.setDebug(mConfig.CONFIG.isUseDebug());

			properties.put(mConfig.PROPERTY.getSmtpTlsEnable(), mConfig.CONFIG.isUseSmtpSslTrust());
			if (mConfig.CONFIG.isUseSmtpSslTrust()) {
				properties.put(mConfig.PROPERTY.getSmtpSslTrust(), mConfig.getMailServer());
			}

			MimeMessage mimemessage = new MimeMessage(session);
			mimemessage.setSubject(mail.getSubject(), mConfig.CONFIG.getEncoding());
			mimemessage.setFrom(new InternetAddress(mConfig.getEmail()));
			mimemessage.addRecipients(Message.RecipientType.TO, mail.getRecivers());

			// เน€เธ�เธดเน�เธกเธฃเธญเธ�เธฃเธฑเธ�เธ�เธฒเธฃเธชเน�เธ� mail เน�เธ�เธ�เธกเธต carbon copy
			if (mail.getReciversCC() != null && mail.getReciversCC().length > 0) {
				mimemessage.addRecipients(Message.RecipientType.CC, mail.getReciversCC());
			}

			String html = mail.getBody();
			BodyPart bodypart = new MimeBodyPart();
			bodypart.setContent(html, mConfig.CONFIG.getContentType());

			MimeMultipart multipart = new MimeMultipart(mConfig.CONFIG.getMultipart());
			multipart.addBodyPart(bodypart);

			for (File file : mail.getFiles()) {
				FileDataSource source = new FileDataSource(file);
				MimeBodyPart mimebodypart = new MimeBodyPart();
				mimebodypart.setDataHandler(new DataHandler(source));
				mimebodypart.setFileName(MimeUtility.encodeText(source.getName()));
				multipart.addBodyPart(mimebodypart);
			}

			mimemessage.setContent(multipart);
			mimemessage.setSentDate(new java.util.Date());
			mimemessage.saveChanges();

			LogUtil.MAIL.debug("send > " + mConfig.getMailServer() + " > " + mConfig.getEmail() + " > " + mConfig.getPassword());
			Transport t = session.getTransport(mConfig.CONFIG.getSmtpTransport());
			t.connect(mConfig.getMailServer(), mConfig.getEmail(), mail.getConfiguration().getPassword());
			t.sendMessage(mimemessage, mimemessage.getAllRecipients());
			t.close();

		} catch (Exception e) {
			LogUtil.MAIL.error("", e);
			log = new MailProcessLogging();
			log.setType(MailProcessLogging.STATUS_DATA);
			log.setMessage("send " + e.toString());
		}
		return log;
	}

	private class SMTPAuthenticator extends javax.mail.Authenticator {
		String username;
		String password;

		private SMTPAuthenticator(String authenticationUser, String authenticationPassword) {
			username = authenticationUser;
			password = authenticationPassword;
		}

		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	}

	/**
	 *
	 * @param mSender
	 * @param configuration
	 * @param job
	 * @param item
	 * @param tAttachs
	 * @param mAttachs
	 * @return MailProcessLogging log
	 */
	public MailProcessLogging beforeSendMail(MailSender mSender, MailConfiguration configuration, MailItem item, List<MailAttach> tAttachs, List<MailAttach> mAttachs) {
		LogUtil.MAIL.info("");
		MailProcessLogging log = null;
		try {
			mSender.setConfiguration(configuration);

			mSender.setSubject(item.getSubject());
			mSender.setBody(item.getContent());

			// mSender.setSender(new InternetAddress(job.getMailSendor()));
			mSender.setSender(new InternetAddress(configuration.getEmail()));

			String[] receivers = item.getMailReceiver().split(",");
			InternetAddress[] iAddress = new InternetAddress[receivers.length];
			for (int i = 0; i < receivers.length; i++) {
				iAddress[i] = new InternetAddress(receivers[i]);
			}
			mSender.setRecivers(iAddress);

			// เธฃเธญเธ�เธฃเธฑเธ�เธ�เธฒเธฃเธชเน�เธ� mail เน�เธ�เธ�เธกเธต carbon copy
			if (item.getMailReceiverCC() != null && item.getMailReceiverCC().length() > 0) {
				String[] receiversCC = item.getMailReceiverCC().split(",");
				InternetAddress[] iAddressCC = new InternetAddress[receiversCC.length];
				for (int i = 0; i < receiversCC.length; i++) {
					iAddressCC[i] = new InternetAddress(receiversCC[i]);
				}
				mSender.setReciversCC(iAddressCC);
			}

			// template
			List<File> files = new ArrayList<File>();
			for (int indexList = 0; indexList < tAttachs.size(); indexList++) {
				tAttachs.get(indexList).setFileAttach(new File(configuration.getAttachPath() + "" + tAttachs.get(indexList).getItemAttachName()));
				if (tAttachs.get(indexList).getFileAttach().canRead()) {
					files.add(tAttachs.get(indexList).getFileAttach());
				} else {
					LogUtil.MAIL.debug("load file Template... [fail]");
					LogUtil.MAIL.debug("loop Load File Template " + indexList + " FileName " + tAttachs.get(indexList).getItemAttachName());
				}
			}

			for (int indexList = 0; indexList < mAttachs.size(); indexList++) {
				mAttachs.get(indexList).setFileAttach(new File(configuration.getAttachPath() + "" + mAttachs.get(indexList).getItemAttachName()));
				if (mAttachs.get(indexList).getFileAttach().canRead()) {

					files.add(mAttachs.get(indexList).getFileAttach());

				} else {
					LogUtil.MAIL.debug("load file item... [fail]");
					LogUtil.MAIL.debug("loop load file item " + indexList + " FileName " + mAttachs.get(indexList).getItemAttachName());
				}
			}

			mSender.setFiles(files);

		} catch (Exception e) {
			LogUtil.MAIL.error("", e);
			log = new MailProcessLogging();
			log.setType(MailDataLogging.STATUS_DATA);
			log.setMessage("beforeSend " + e.toString());
		}

		return log;
	}

	/**
	 *
	 * @param mSender
	 * @param configuration
	 * @param job
	 * @param item
	 * @param tAttachs
	 * @param mAttachs
	 * @return MailProcessLogging log
	 */
	public MailProcessLogging beforeSendMailCarbonCopy(MailSender mSender, MailConfiguration configuration, MailItem item, List<MailAttach> tAttachs, List<MailAttach> mAttachs) {
		LogUtil.MAIL.info("");
		MailProcessLogging log = null;
		try {
			mSender.setConfiguration(configuration);

			mSender.setSubject(item.getSubject());
			mSender.setBody(item.getContent());

			// mSender.setSender(new InternetAddress(job.getMailSendor()));
			mSender.setSender(new InternetAddress(configuration.getEmail()));

			String[] receivers = item.getMailReceiver().split(",");
			InternetAddress[] iAddress = new InternetAddress[receivers.length];
			for (int i = 0; i < receivers.length; i++) {
				iAddress[i] = new InternetAddress(receivers[i]);
			}
			mSender.setRecivers(iAddress);

			String[] receiversCC = item.getMailReceiverCC().split(",");
			InternetAddress[] iAddressCC = new InternetAddress[receiversCC.length];
			for (int i = 0; i < receiversCC.length; i++) {
				iAddressCC[i] = new InternetAddress(receiversCC[i]);
			}
			mSender.setReciversCC(iAddressCC);

			// template
			List<File> files = new ArrayList<File>();
			for (int indexList = 0; indexList < tAttachs.size(); indexList++) {
				tAttachs.get(indexList).setFileAttach(new File(configuration.getAttachPath() + "" + tAttachs.get(indexList).getItemAttachName()));
				if (tAttachs.get(indexList).getFileAttach().canRead()) {
					files.add(tAttachs.get(indexList).getFileAttach());
				} else {
					LogUtil.MAIL.debug("load file Template... [fail]");
					LogUtil.MAIL.debug("loop Load File Template " + indexList + " FileName " + tAttachs.get(indexList).getItemAttachName());
				}
			}

			for (int indexList = 0; indexList < mAttachs.size(); indexList++) {
				mAttachs.get(indexList).setFileAttach(new File(configuration.getAttachPath() + "" + mAttachs.get(indexList).getItemAttachName()));
				if (mAttachs.get(indexList).getFileAttach().canRead()) {

					files.add(mAttachs.get(indexList).getFileAttach());

				} else {
					LogUtil.MAIL.debug("load file item... [fail]");
					LogUtil.MAIL.debug("loop load file item " + indexList + " FileName " + mAttachs.get(indexList).getItemAttachName());
				}
			}

			mSender.setFiles(files);

		} catch (Exception e) {
			LogUtil.MAIL.error("", e);
			log = new MailProcessLogging();
			log.setType(MailDataLogging.STATUS_DATA);
			log.setMessage("beforeSend " + e.toString());
		}

		return log;
	}
}
