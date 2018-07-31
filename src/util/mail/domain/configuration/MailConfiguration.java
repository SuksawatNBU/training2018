package util.mail.domain.configuration;

import java.io.Serializable;

public class MailConfiguration implements Serializable {

	private static final long serialVersionUID = 6101403404898590461L;
	public static String FILE_PATH = "";
	public static final String FILE_CONFIG = "mail-conf.xml";
	public static final String FILE_LOG = "mail-log.properties";

	public static final int MIN_OF_SENDER_LIST = 1;
	public static final int SUB_INDEX_LIST = 2;
	public static final String FLAG_Y = "Y";
	public static final String FLAG_N = "N";
	public static final String BEFORE_JOB_ID = "0";
	public static final int INDEX_SENDER = 1;
	public static final String DELIMITER_TWOCORON = "::";

	public static final boolean ENABLE = true;
	public static final boolean DISABLE = false;
	public static final boolean SEND_OK = true;
	public static final boolean SEND_FAIL = false;

	private  String email;
	private  String password;
	private  String mailServer;
	private  int attachSizeLimit;
	private  int attachNoLimit;
	private  String attachPath;
	private  String mailProtocal;

	public  MailProperty PROPERTY = new MailProperty();
	public  MailProperty CONFIG = new MailProperty();


	public static String getFILE_PATH() {
		return FILE_PATH;
	}
	public static void setFILE_PATH(String fILE_PATH) {
		FILE_PATH = fILE_PATH;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static String getFileConfig() {
		return FILE_CONFIG;
	}
	public static String getFileLog() {
		return FILE_LOG;
	}
	public static int getMinOfSenderList() {
		return MIN_OF_SENDER_LIST;
	}
	public static int getSubIndexList() {
		return SUB_INDEX_LIST;
	}
	public static String getFlagY() {
		return FLAG_Y;
	}
	public static String getFlagN() {
		return FLAG_N;
	}
	public static String getBeforeJobId() {
		return BEFORE_JOB_ID;
	}
	public static int getIndexSender() {
		return INDEX_SENDER;
	}
	public static String getDelimiterTwocoron() {
		return DELIMITER_TWOCORON;
	}
	public static boolean isEnable() {
		return ENABLE;
	}
	public static boolean isDisable() {
		return DISABLE;
	}
	public static boolean isSendOk() {
		return SEND_OK;
	}
	public static boolean isSendFail() {
		return SEND_FAIL;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMailServer() {
		return mailServer;
	}
	public void setMailServer(String mailServer) {
		this.mailServer = mailServer;
	}
	public int getAttachSizeLimit() {
		return attachSizeLimit;
	}
	public void setAttachSizeLimit(int attachSizeLimit) {
		this.attachSizeLimit = attachSizeLimit;
	}
	public int getAttachNoLimit() {
		return attachNoLimit;
	}
	public void setAttachNoLimit(int attachNoLimit) {
		this.attachNoLimit = attachNoLimit;
	}
	public String getAttachPath() {
		return attachPath;
	}
	public void setAttachPath(String attachPath) {
		this.attachPath = attachPath;
	}
	public String getMailProtocal() {
		return mailProtocal;
	}
	public void setMailProtocal(String mailProtocal) {
		this.mailProtocal = mailProtocal;
	}
	public MailProperty getPROPERTY() {
		return PROPERTY;
	}
	public void setPROPERTY(MailProperty pROPERTY) {
		PROPERTY = pROPERTY;
	}
	public MailProperty getCONFIG() {
		return CONFIG;
	}
	public void setCONFIG(MailProperty cONFIG) {
		CONFIG = cONFIG;
	}

}
