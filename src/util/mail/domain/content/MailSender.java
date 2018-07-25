package util.mail.domain.content;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.mail.internet.InternetAddress;

import util.mail.domain.configuration.MailConfiguration;

public class MailSender implements Serializable {

	private static final long serialVersionUID = 3540659079778984225L;

	private MailConfiguration configuration;
	private InternetAddress sender;
	private InternetAddress[] recivers;
	private String subject;
	private String body;
	private List<File> files;

	private InternetAddress[] reciversCC;

	public InternetAddress getSender() {
		return sender;
	}

	public void setSender(InternetAddress sender) {
		this.sender = sender;
	}

	public InternetAddress[] getRecivers() {
		return recivers;
	}

	public void setRecivers(InternetAddress[] recivers) {
		this.recivers = recivers;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public MailConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(MailConfiguration configuration) {
		this.configuration = configuration;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	public InternetAddress[] getReciversCC() {
		return reciversCC;
	}

	public void setReciversCC(InternetAddress[] reciversCC) {
		this.reciversCC = reciversCC;
	}

}
