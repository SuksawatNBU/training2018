package util.mail.exception;

import util.mail.domain.logging.MailLogging;


public class MailException extends Exception {

	private static final long serialVersionUID = 7971928734531405001L;
	private MailLogging logging;

	public MailException() {
		super();
	}

	public MailException(MailLogging logging) {
		this.logging = logging;
	}

	public String getType() {
		return logging.getType();
	}

	public String getMessage() {
		return logging.getMessage();
	}

	public void setLogging(MailLogging logging) {
		this.logging = logging;
	}
}
