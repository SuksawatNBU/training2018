/**
 * @Author : kampon.n
 * @Version 1.0
 * @Create date :
 * @Last update : 2012-08-31
 * @Current Version 1.0
 */
package util.mail.content;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

import util.log.LogUtil;
import util.mail.domain.configuration.MailConfiguration;
import util.mail.domain.configuration.MailTemplate;
import util.mail.domain.content.MailAttach;
import util.mail.domain.content.MailItem;
import util.mail.domain.logging.MailProcessLogging;

public class MailContentService {

	private ResourceBundle bundle;

	public MailContentService(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	/**
	 * @description before add mail job
	 * @param generateDate
	 * @param conn
	 * @return MailDataLogging
	 */
	public String getGenerateDate(Connection conn) {
		LogUtil.MAIL.info("");
		String generateDate = null;

		try {

//			generateDate = "TO_DATE('" + DateUtil.getCurrentDateDB(conn, ParameterConfig.getDateFormat().getForDatabaseHHMMSS(), DbType.ORA) + "')"; //CREATE_DATE

//			generateDate = "TO_DATE('" + DateUtil.getC(conn, DbType.ORA) + "','" + BeanParameter.getDateFormat4InserUpdate() + "')";
			LogUtil.MAIL.debug("generateDate :- " + generateDate);
		} catch (Exception e) {
			LogUtil.MAIL.error("", e);
		}
		return generateDate;
	}



	/**
	 * @description before add mail item
	 * @param job
	 * @param template
	 * @param infomation
	 * @param contentTemplate
	 * @param conn
	 * @return MailProcessLogging
	 */
	public MailProcessLogging beforeAddMailItem(MailItem mItem, MailTemplate template, String infomation, String contentTemplate, String subjectTemplate, Connection conn) {
		LogUtil.MAIL.info("");
		MailProcessLogging log = null;

		try {
			String[] informationItem = infomation.split("\\|\\|");
			String[] mailReceivers = informationItem[0].split("\\:\\:");
			mItem.setRefItemId(null);

			String lastSendDate = null;
			String lastSendStatus = MailConfiguration.FLAG_N;

			String canceled = MailConfiguration.FLAG_N;

			mItem.setSubject(subjectTemplate);
			mItem.setContent(contentTemplate);
			mItem.setMailReceiver(mailReceivers[1]);
			mItem.setLastSendDate(lastSendDate);
			mItem.setLastSendStatus(lastSendStatus);
			mItem.setCanceled(canceled);

		} catch (Exception e) {
			LogUtil.MAIL.error("", e);
			log = new MailProcessLogging();
			log.setType(MailProcessLogging.STATUS_PROCESS);
			log.setMessage("beforeAddMailItem " + bundle.getString("system.Cant_insert_EMA_Mail_Item") + " " + e.toString());
		}
		return log;
	}



	/**
	 *
	 * @param attachs
	 * @param mItem
	 * @param conn
	 * @return MailProcessLogging log
	 */
	public MailProcessLogging mailAttach(List<MailAttach> attachs, String infomation) {
		LogUtil.MAIL.info("");
		MailProcessLogging log = null;
		Statement stmt = null;
		ResultSet rst = null;
		String sql = "";

		try {
			String[] arrFile = infomation.split("\\|\\|");
			if (arrFile[arrFile.length - 1].equals("none")) {
				return log;
			}
			String[] fileNames = arrFile[arrFile.length - 1].split(",");

			MailAttach attach = null;
			for (String fileName : fileNames) {
				attach = new MailAttach();
				attach.setItemAttachName(fileName);
				attachs.add(attach);
			}

		} catch (Exception e) {
			LogUtil.MAIL.error("", e);
			LogUtil.MAIL.error("SQL " + sql.toString());
			log = new MailProcessLogging();
			log.setType(MailProcessLogging.STATUS_PROCESS);
			log.setMessage("searchMailTemplateAttach " + "Cant_Load_Data_Program_Attach_File "  + e.toString());
		} finally {
			try {
				if (rst != null) {
					rst.close();
				}
			} catch (Exception ex) {
				LogUtil.MAIL.error("", ex);
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception ex) {
				LogUtil.MAIL.error("", ex);
			}
		}

		return log;
	}


	/**
	 *
	 * @param template
	 * @param information
	 * @return String Content Template
	 */
	public String replaceParameter(MailTemplate template, String information) {
		LogUtil.MAIL.info("");
		String content = template.getTemplateContent();
		try {

			String[] arrInformation = information.split("\\|\\|");
			int index1 = 1;

			for (; index1 < arrInformation.length - 1;) {
				String arrContent = arrInformation[index1].toString();
				String[] subContent = arrContent.split("\\:\\:");

				int indexOf = content.indexOf(subContent[0]);
				if(indexOf == -1){
					index1++;
					continue;
				}

				LogUtil.MAIL.debug("indexOf :- " + indexOf);
				LogUtil.MAIL.debug("content.substring :- " + content.substring(indexOf, (indexOf + subContent[0].length())));

				String temp = content.substring(0, indexOf) + subContent[1] + content.substring(indexOf + subContent[0].length(), content.length());
				// LogUtil.MAIL.debug("temp :- " + temp);
				content = temp;

				indexOf = content.indexOf(subContent[0]);
				if(indexOf != -1){
					continue;
				} else {
					index1++;
				}

			}

		} catch (Exception e) {
			LogUtil.MAIL.error("", e);
		}

		return content;
	}

	/**
	 *
	 * @param template
	 * @param information
	 * @return String Subject Template
	 */
	public String replaceParameterSubject(MailTemplate template, String information) {
		LogUtil.MAIL.info("");
		String subject = template.getTemplateSubject();
		try {

			String[] arrInformation = information.split("\\|\\|");
			int index1 = 1;

			for (; index1 < arrInformation.length - 1;) {
				String arrContent = arrInformation[index1].toString();
				String[] subContent = arrContent.split("\\:\\:");

				int indexOf = subject.indexOf(subContent[0]);
				if(indexOf == -1){
					index1++;
					continue;
				}

				LogUtil.MAIL.debug("indexOf :- " + indexOf);
				LogUtil.MAIL.debug("content.substring :- " + subject.substring(indexOf, (indexOf + subContent[0].length())));

				String temp = subject.substring(0, indexOf) + subContent[1] + subject.substring(indexOf + subContent[0].length(), subject.length());
				LogUtil.MAIL.debug("temp :- " + temp);
				subject = temp;

				indexOf = subject.indexOf(subContent[0]);
				if(indexOf != -1){
					continue;
				} else {
					index1++;
				}
			}

		} catch (Exception e) {
			LogUtil.MAIL.error("", e);
		}

		return subject;
	}



}
