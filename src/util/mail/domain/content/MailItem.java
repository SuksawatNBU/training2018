package util.mail.domain.content;

import java.io.Serializable;

public class MailItem implements Serializable {

	private static final long serialVersionUID = 2018701213870585134L;
	private String mailItemId;
	private String mailId;
	private String subject;
	private String content;
	private String mailReceiver;

	private String lastSendDate;
	private String lastSendStatus;
	private String canceled;

	private String createDate;
	private String crateUser;
	private String createStation;
	private String refItemId;

	private String mailReceiverCC;

	public String getMailItemId() {
		return mailItemId;
	}

	public void setMailItemId(String mailItemId) {
		this.mailItemId = mailItemId;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMailReceiver() {
		return mailReceiver;
	}

	public void setMailReceiver(String mailReceiver) {
		this.mailReceiver = mailReceiver;
	}

	public String getLastSendDate() {
		return lastSendDate;
	}

	public void setLastSendDate(String lastSendDate) {
		this.lastSendDate = lastSendDate;
	}

	public String getLastSendStatus() {
		return lastSendStatus;
	}

	public void setLastSendStatus(String lastSendStatus) {
		this.lastSendStatus = lastSendStatus;
	}

	public String getCanceled() {
		return canceled;
	}

	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCrateUser() {
		return crateUser;
	}

	public void setCrateUser(String crateUser) {
		this.crateUser = crateUser;
	}

	public String getCreateStation() {
		return createStation;
	}

	public void setCreateStation(String createStation) {
		this.createStation = createStation;
	}

	public String getRefItemId() {
		return refItemId;
	}

	public void setRefItemId(String refItemId) {
		this.refItemId = refItemId;
	}

	public String getMailReceiverCC() {
		return mailReceiverCC;
	}

	public void setMailReceiverCC(String mailReceiverCC) {
		this.mailReceiverCC = mailReceiverCC;
	}

}
