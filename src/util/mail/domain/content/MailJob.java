package util.mail.domain.content;

import java.io.Serializable;

public class MailJob implements Serializable {

	private static final long serialVersionUID = 1444483527032746953L;
	private String mailId;
	private String jobId;
	private String templateOperatorId;
	private String sendCon;
	private String resendCon;
	private int resendTime;

	private String startSend;
	private String endSend;
	private int resendNum;
	private String mailSendor;
	private int genMailTot;
	private int sendMailTot;

	private String canceled;
	private String crateDate;
	private String crateUser;
	private String createStation;
	private String updateDate;
	private String updateUser;
	private String updateStation;

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getTemplateOperatorId() {
		return templateOperatorId;
	}

	public void setTemplateOperatorId(String templateOperatorId) {
		this.templateOperatorId = templateOperatorId;
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

	public int getResendTime() {
		return resendTime;
	}

	public void setResendTime(int resendTime) {
		this.resendTime = resendTime;
	}

	public String getStartSend() {
		return startSend;
	}

	public void setStartSend(String startSend) {
		this.startSend = startSend;
	}

	public String getEndSend() {
		return endSend;
	}

	public void setEndSend(String endSend) {
		this.endSend = endSend;
	}

	public int getResendNum() {
		return resendNum;
	}

	public void setResendNum(int resendNum) {
		this.resendNum = resendNum;
	}

	public String getMailSendor() {
		return mailSendor;
	}

	public void setMailSendor(String mailSendor) {
		this.mailSendor = mailSendor;
	}

	public int getGenMailTot() {
		return genMailTot;
	}

	public void setGenMailTot(int genMailTot) {
		this.genMailTot = genMailTot;
	}

	public int getSendMailTot() {
		return sendMailTot;
	}

	public void setSendMailTot(int sendMailTot) {
		this.sendMailTot = sendMailTot;
	}

	public String getCanceled() {
		return canceled;
	}

	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}

	public String getCrateDate() {
		return crateDate;
	}

	public void setCrateDate(String crateDate) {
		this.crateDate = crateDate;
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

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateStation() {
		return updateStation;
	}

	public void setUpdateStation(String updateStation) {
		this.updateStation = updateStation;
	}
}
