package util.mail.service;

import java.util.ArrayList;
import java.util.List;

import util.log.LogUtil;
import util.mail.configuration.MailConfigurationService;
import util.mail.content.MailContentService;
import util.mail.domain.configuration.MailConfiguration;
import util.mail.domain.configuration.MailTemplate;
import util.mail.domain.content.MailAttach;
import util.mail.domain.content.MailItem;
import util.mail.domain.content.MailSender;
import util.mail.domain.logging.MailDataLogging;
import util.mail.domain.logging.MailProcessLogging;
import util.mail.exception.MailException;

public class MailSenderManager {

	private MailItem mItem = null;
	private MailDataLogging dLog = null;
	private MailProcessLogging pLog = null;
	private MailSender mSender = null;

	public void send(List<MailTemplate> mTemplates, List<String> infomations, MailConfiguration mConfig, List<MailAttach> mAttachs, List<MailAttach> tAttachs) throws Exception {
		MailContentService contentService = new MailContentService(null);
		MailSenderService senderService = new MailSenderService();
		MailConfigurationService configService = new MailConfigurationService();
		try {

			/* 4. Loop 0: As Email Template_SQL(pIndex0) */
			LogUtil.MAIL.debug("Loop-0 start from template");
			for (MailTemplate mTemplate : mTemplates) {
				LogUtil.MAIL.debug("Check sender list... ");
				// check List information 0,1,2
				dLog = configService.searchSenderList(infomations);
				if (dLog != null) {
					LogUtil.MAIL.debug("Check sender list... [fail]");
					throw new MailException(dLog);
				}
				LogUtil.MAIL.debug("Check sender list... [ok]");

				/* Loop 1 : As Object[SendList].pIndex0 */
				LogUtil.MAIL.debug("Loop-1 start from content");
				int index = 0;
				for (String infomation : infomations) {
					if (index <= 1) {
						index += 1;
						continue;
					}
					// replace parameter
					String contentTemplate = contentService.replaceParameter(mTemplate, infomation);

					// Replace Mail Subject
					String subjectTemplate = contentService.replaceParameterSubject(mTemplate, infomation);

					LogUtil.MAIL.debug("Add mail item... ");
					mItem = new MailItem();
					pLog = contentService.beforeAddMailItem(mItem, mTemplate, infomation, contentTemplate, subjectTemplate, null);
					if (pLog != null) {
						LogUtil.MAIL.debug("Add mail item... [fail]");
						throw new MailException(pLog);
					}

					// นำไฟล์ที่ส่งเข้ามา infomations เข้าไป sprit ค่า และเก็บลง
					// List object mAttachs
					pLog = contentService.mailAttach(mAttachs, infomation);
					if (pLog != null) {
						LogUtil.MAIL.debug("mailAttach mail... [fail]");
						LogUtil.MAIL.debug("Can't Attach Mail From Program!");
						throw new MailException(pLog);
					}
				}
				/*******************************************************************************************************************************/

				mSender = new MailSender();
				// เตรียมข้อมูลทั้งหมดก่อนการส่งเมล์ โดยเก็บไว้ที่ mSender
				pLog = senderService.beforeSendMail(mSender, mConfig, mItem, tAttachs, mAttachs);
				if (pLog != null) {
					LogUtil.MAIL.debug("before send mail... [fail]");
					LogUtil.MAIL.debug("Can't convert data after send!");
					throw new MailException(pLog);

				} else {
					// เรียก service send mail
					pLog = senderService.send(mSender, mConfig);
					if (pLog != null) {
						LogUtil.MAIL.error("Can't send mail please see error this!");
						throw new MailException(pLog);
					} else {
						LogUtil.MAIL.error("Send mail complete");
					}
				}
			}

		} catch (Exception e) {
			throw new MailException(pLog);
		}

	}

	public void send(MailTemplate mTemplate, List<String> infomations, MailConfiguration mConfig) throws Exception {

		List<MailAttach> tAttachs = new ArrayList<MailAttach>(); // Attach mail
		List<MailAttach> mAttachs = new ArrayList<MailAttach>(); // ไฟล์ Attrach

		MailContentService contentService = new MailContentService(null);
		MailSenderService senderService = new MailSenderService();
		MailConfigurationService configService = new MailConfigurationService();
		try {

			/* 4. Loop 0: As Email Template_SQL(pIndex0) */
			LogUtil.MAIL.debug("Check sender list... ");
			// check List information 0,1,2
			dLog = configService.searchSenderList(infomations);
			if (dLog != null) {
				LogUtil.MAIL.debug("Check sender list... [fail]");
				throw new MailException(dLog);
			}
			LogUtil.MAIL.debug("Check sender list... [ok]");

			/* Loop 1 : As Object[SendList].pIndex0 */
			LogUtil.MAIL.debug("Loop-1 start from content");
			int index = 0;
			for (String infomation : infomations) {
				if (index <= 1) {
					index += 1;
					continue;
				}
				// replace parameter
				String contentTemplate = contentService.replaceParameter(mTemplate, infomation);

				// Replace Mail Subject
				String subjectTemplate = contentService.replaceParameterSubject(mTemplate, infomation);

				LogUtil.MAIL.debug("Add mail item... ");
				mItem = new MailItem();
				pLog = contentService.beforeAddMailItem(mItem, mTemplate, infomation, contentTemplate, subjectTemplate, null);
				if (pLog != null) {
					LogUtil.MAIL.debug("Add mail item... [fail]");
					throw new MailException(pLog);
				}

				// นำไฟล์ที่ส่งเข้ามา infomations เข้าไป sprit ค่า และเก็บลง
				// List object mAttachs
				mAttachs.clear();
				pLog = contentService.mailAttach(mAttachs, infomation);
				if (pLog != null) {
					LogUtil.MAIL.debug("mailAttach mail... [fail]");
					LogUtil.MAIL.debug("Can't Attach Mail From Program!");
					throw new MailException(pLog);
				}

				mSender = new MailSender();
				// เตรียมข้อมูลทั้งหมดก่อนการส่งเมล์ โดยเก็บไว้ที่ mSender
				pLog = senderService.beforeSendMail(mSender, mConfig, mItem, tAttachs, mAttachs);
				if (pLog != null) {
					LogUtil.MAIL.debug("before send mail... [fail]");
					LogUtil.MAIL.debug("Can't convert data after send!");
					throw new MailException(pLog);

				} else {
					// เรียก service send mail
					pLog = senderService.send(mSender, mConfig);
					if (pLog != null) {
						LogUtil.MAIL.error("Can't send mail please see error this!");
						throw new MailException(pLog);
					} else {
						LogUtil.MAIL.error("Send mail complete");
					}
				}

			}
		} catch (Exception e) {
			throw new MailException(pLog);
		}

	}

	public void sendCarbonCopy(MailTemplate mTemplate, List<String> infomations, MailConfiguration mConfig, String receiverCC) throws Exception {

		List<MailAttach> tAttachs = new ArrayList<MailAttach>(); // Attach mail
		List<MailAttach> mAttachs = new ArrayList<MailAttach>(); // ไฟล์ Attrach

		MailContentService contentService = new MailContentService(null);
		MailSenderService senderService = new MailSenderService();
		MailConfigurationService configService = new MailConfigurationService();
		try {

			/* 4. Loop 0: As Email Template_SQL(pIndex0) */
			LogUtil.MAIL.debug("Check sender list... ");
			// check List information 0,1,2
			dLog = configService.searchSenderList(infomations);
			if (dLog != null) {
				LogUtil.MAIL.debug("Check sender list... [fail]");
				throw new MailException(dLog);
			}
			LogUtil.MAIL.debug("Check sender list... [ok]");

			/* Loop 1 : As Object[SendList].pIndex0 */
			LogUtil.MAIL.debug("Loop-1 start from content");
			int index = 0;
			for (String infomation : infomations) {
				if (index <= 1) {
					index += 1;
					continue;
				}
				// replace parameter
				String contentTemplate = contentService.replaceParameter(mTemplate, infomation);

				// Replace Mail Subject
				String subjectTemplate = contentService.replaceParameterSubject(mTemplate, infomation);

				LogUtil.MAIL.debug("Add mail item... ");
				mItem = new MailItem();
				pLog = contentService.beforeAddMailItem(mItem, mTemplate, infomation, contentTemplate, subjectTemplate, null);
				if (pLog != null) {
					LogUtil.MAIL.debug("Add mail item... [fail]");
					throw new MailException(pLog);
				}

				if (!receiverCC.equals("")) {
					mItem.setMailReceiverCC(receiverCC);
				}

				// นำไฟล์ที่ส่งเข้ามา infomations เข้าไป sprit ค่า และเก็บลง
				// List object mAttachs
				mAttachs.clear();
				pLog = contentService.mailAttach(mAttachs, infomation);
				if (pLog != null) {
					LogUtil.MAIL.debug("mailAttach mail... [fail]");
					LogUtil.MAIL.debug("Can't Attach Mail From Program!");
					throw new MailException(pLog);
				}

				mSender = new MailSender();
				// เตรียมข้อมูลทั้งหมดก่อนการส่งเมล์ โดยเก็บไว้ที่ mSender
				pLog = senderService.beforeSendMail(mSender, mConfig, mItem, tAttachs, mAttachs);
				if (pLog != null) {
					LogUtil.MAIL.debug("before send mail... [fail]");
					LogUtil.MAIL.debug("Can't convert data after send!");
					throw new MailException(pLog);

				} else {
					// เรียก service send mail
					pLog = senderService.send(mSender, mConfig);
					if (pLog != null) {
						LogUtil.MAIL.error("Can't send mail please see error this!");
						throw new MailException(pLog);
					} else {
						LogUtil.MAIL.error("Send mail complete");
					}
				}

			}
		} catch (Exception e) {
			throw new MailException(pLog);
		}

	}

}
