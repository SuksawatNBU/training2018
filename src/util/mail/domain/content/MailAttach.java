package util.mail.domain.content;

import java.io.File;
import java.io.Serializable;

public class MailAttach implements Serializable {

	private static final long serialVersionUID = -5664958932868498854L;
	private String mailAttachId;
	private String mailItemId;
	private String itemAttachName;
	private File fileAttach;

	public String getMailAttachId() {
		return mailAttachId;
	}

	public void setMailAttachId(String mailAttachId) {
		this.mailAttachId = mailAttachId;
	}

	public String getMailItemId() {
		return mailItemId;
	}

	public void setMailItemId(String mailItemId) {
		this.mailItemId = mailItemId;
	}

	public String getItemAttachName() {
		return itemAttachName;
	}

	public void setItemAttachName(String itemAttachName) {
		this.itemAttachName = itemAttachName;
	}

	public File getFileAttach() {
		return fileAttach;
	}

	public void setFileAttach(File fileAttach) {
		this.fileAttach = fileAttach;
	}

}
