package util.mail.domain.configuration;

public class MailProperty {

	private String attachPath;
	private String protocal;
	private String host;
	private String smtpAuth;
	private String smtpTlsEnable;
	private String smtpSslTrust;
	private String encoding;
	private String multipart;
	private String contentType;
	private String smtpSocketFactory;
	private String smtpTransport;

	private boolean useSmtpAuth;
	private boolean useSmtpTlsEnable;
	private boolean useSmtpSslTrust;
	private boolean useDebug;

	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	public String getProtocal() {
		return protocal;
	}
	public void setProtocal(String protocal) {
		this.protocal = protocal;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getSmtpAuth() {
		return smtpAuth;
	}
	public void setSmtpAuth(String smtpAuth) {
		this.smtpAuth = smtpAuth;
	}
	public String getSmtpTlsEnable() {
		return smtpTlsEnable;
	}
	public void setSmtpTlsEnable(String smtpTlsEnable) {
		this.smtpTlsEnable = smtpTlsEnable;
	}
	public String getSmtpSslTrust() {
		return smtpSslTrust;
	}
	public void setSmtpSslTrust(String smtpSslTrust) {
		this.smtpSslTrust = smtpSslTrust;
	}
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	public String getMultipart() {
		return multipart;
	}
	public void setMultipart(String multipart) {
		this.multipart = multipart;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public boolean isUseSmtpAuth() {
		return useSmtpAuth;
	}
	public void setUseSmtpAuth(boolean useSmtpAuth) {
		this.useSmtpAuth = useSmtpAuth;
	}
	public boolean isUseSmtpTlsEnable() {
		return useSmtpTlsEnable;
	}
	public void setUseSmtpTlsEnable(boolean useSmtpTlsEnable) {
		this.useSmtpTlsEnable = useSmtpTlsEnable;
	}
	public boolean isUseSmtpSslTrust() {
		return useSmtpSslTrust;
	}
	public void setUseSmtpSslTrust(boolean useSmtpSslTrust) {
		this.useSmtpSslTrust = useSmtpSslTrust;
	}
	public boolean isUseDebug() {
		return useDebug;
	}
	public void setUseDebug(boolean useDebug) {
		this.useDebug = useDebug;
	}
	public String getSmtpSocketFactory() {
		return smtpSocketFactory;
	}
	public void setSmtpSocketFactory(String smtpSocketFactory) {
		this.smtpSocketFactory = smtpSocketFactory;
	}
	public String getSmtpTransport() {
		return smtpTransport;
	}
	public void setSmtpTransport(String smtpTransport) {
		this.smtpTransport = smtpTransport;
	}
}
