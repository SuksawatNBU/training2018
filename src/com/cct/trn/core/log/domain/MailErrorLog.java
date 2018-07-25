package com.cct.trn.core.log.domain;

public class MailErrorLog {
	private String emailConfigId;
	private String emailSender;
	private String emailTo;
	private String emailTemplateId;

	public String getEmailConfigId() {
		return emailConfigId;
	}

	public void setEmailConfigId(String emailConfigId) {
		this.emailConfigId = emailConfigId;
	}

	public String getEmailSender() {
		return emailSender;
	}

	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getEmailTemplateId() {
		return emailTemplateId;
	}

	public void setEmailTemplateId(String emailTemplateId) {
		this.emailTemplateId = emailTemplateId;
	}
	
}
