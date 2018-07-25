package util.mail.domain.configuration;

import java.io.Serializable;

public class MailTemplate implements Serializable {

	private static final long serialVersionUID = 8159694896026845535L;
	private String siteId;
	private String siteCode;
	private String operatorId;
	private String templateOperatorId;
	private String languageId;
	private String sendCon;
	private String resendCon;
	private int resendLimit;
	private int resendTime;
	private String attachTemplateCon;
	private String attachProgramCon;
	private int processSeq;
	private String templateId;
	private String templateCode;
	private String templateSubject;
	private String templatePurpose;
	private String templateContent;
	private String startDate;
	private String endDate;

	private String refStatus;
	private String refTablename;
	private String refFieldId;
	private String refFieldStatus;

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getTemplateOperatorId() {
		return templateOperatorId;
	}

	public void setTemplateOperatorId(String templateOperatorId) {
		this.templateOperatorId = templateOperatorId;
	}

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public String getSendCon() {
		return sendCon;
	}

	public void setSendCon(String sendCon) {
		this.sendCon = sendCon;
	}

	public String getResendCon() {
		return resendCon;
	}

	public void setResendCon(String resendCon) {
		this.resendCon = resendCon;
	}

	public int getResendLimit() {
		return resendLimit;
	}

	public void setResendLimit(int resendLimit) {
		this.resendLimit = resendLimit;
	}

	public int getResendTime() {
		return resendTime;
	}

	public void setResendTime(int resendTime) {
		this.resendTime = resendTime;
	}

	public String getAttachTemplateCon() {
		return attachTemplateCon;
	}

	public void setAttachTemplateCon(String attachTemplateCon) {
		this.attachTemplateCon = attachTemplateCon;
	}

	public String getAttachProgramCon() {
		return attachProgramCon;
	}

	public void setAttachProgramCon(String attachProgramCon) {
		this.attachProgramCon = attachProgramCon;
	}

	public int getProcessSeq() {
		return processSeq;
	}

	public void setProcessSeq(int processSeq) {
		this.processSeq = processSeq;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateSubject() {
		return templateSubject;
	}

	public void setTemplateSubject(String templateSubject) {
		this.templateSubject = templateSubject;
	}

	public String getTemplatePurpose() {
		return templatePurpose;
	}

	public void setTemplatePurpose(String templatePurpose) {
		this.templatePurpose = templatePurpose;
	}

	public String getTemplateContent() {
		return templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getRefStatus() {
		return refStatus;
	}

	public void setRefStatus(String refStatus) {
		this.refStatus = refStatus;
	}

	public String getRefTablename() {
		return refTablename;
	}

	public void setRefTablename(String refTablename) {
		this.refTablename = refTablename;
	}

	public String getRefFieldId() {
		return refFieldId;
	}

	public void setRefFieldId(String refFieldId) {
		this.refFieldId = refFieldId;
	}

	public String getRefFieldStatus() {
		return refFieldStatus;
	}

	public void setRefFieldStatus(String refFieldStatus) {
		this.refFieldStatus = refFieldStatus;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
