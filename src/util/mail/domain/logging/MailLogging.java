package util.mail.domain.logging;

import java.io.Serializable;

public class MailLogging implements Serializable {

	private static final long serialVersionUID = 4646365081652469636L;
	public static final String STATUS_PROCESS = "P";
	public static final String STATUS_DATA = "D";

	private String type;
	private String message;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
