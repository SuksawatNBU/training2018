package com.cct.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GlobalVariable implements Serializable {

	private static final long serialVersionUID = 7226578754399777637L;

	public static final String DEFAULT_PARAMETER_LANGUAGE = "language";

	public static final Map<String, String> MAP_OF_ACTION_NAME_FOR_SKIP_AUTHENTICATE = new HashMap<String, String>();
	static {
		MAP_OF_ACTION_NAME_FOR_SKIP_AUTHENTICATE.put("initLogin", "initLogin");
		MAP_OF_ACTION_NAME_FOR_SKIP_AUTHENTICATE.put("checkAuthenticationLogin", "checkAuthenticationLogin");
		MAP_OF_ACTION_NAME_FOR_SKIP_AUTHENTICATE.put("checkLogin", "checkLogin");
		MAP_OF_ACTION_NAME_FOR_SKIP_AUTHENTICATE.put("initForgotPassword", "initForgotPassword");
		MAP_OF_ACTION_NAME_FOR_SKIP_AUTHENTICATE.put("changeForgotPassword", "changeForgotPassword");
		MAP_OF_ACTION_NAME_FOR_SKIP_AUTHENTICATE.put("changePasswordLogin", "changePasswordLogin");
		MAP_OF_ACTION_NAME_FOR_SKIP_AUTHENTICATE.put("initRegistration", "initRegistration");
		MAP_OF_ACTION_NAME_FOR_SKIP_AUTHENTICATE.put("addRegistration", "addRegistration");
		MAP_OF_ACTION_NAME_FOR_SKIP_AUTHENTICATE.put("gotoViewRegistration", "addRegistration");
	}

	public static final Map<String, String> MAP_OF_ACTION_NAME_FOR_SKIP_CHANGE_PASSWORD_FORCE = new HashMap<String, String>();
	static {
		MAP_OF_ACTION_NAME_FOR_SKIP_CHANGE_PASSWORD_FORCE.put("initLogin", "initLogin");
		MAP_OF_ACTION_NAME_FOR_SKIP_CHANGE_PASSWORD_FORCE.put("checkAuthenticationLogin", "checkAuthenticationLogin");
		MAP_OF_ACTION_NAME_FOR_SKIP_CHANGE_PASSWORD_FORCE.put("checkLogin", "checkLogin");
		MAP_OF_ACTION_NAME_FOR_SKIP_CHANGE_PASSWORD_FORCE.put("initForgotPassword", "initForgotPassword");
		MAP_OF_ACTION_NAME_FOR_SKIP_CHANGE_PASSWORD_FORCE.put("changeForgotPassword", "changeForgotPassword");
		MAP_OF_ACTION_NAME_FOR_SKIP_CHANGE_PASSWORD_FORCE.put("changePasswordLogin", "changePasswordLogin");
		MAP_OF_ACTION_NAME_FOR_SKIP_CHANGE_PASSWORD_FORCE.put("initRegistration", "initRegistration");
		MAP_OF_ACTION_NAME_FOR_SKIP_CHANGE_PASSWORD_FORCE.put("addRegistration", "addRegistration");
		MAP_OF_ACTION_NAME_FOR_SKIP_CHANGE_PASSWORD_FORCE.put("gotoViewRegistration", "addRegistration");
		MAP_OF_ACTION_NAME_FOR_SKIP_CHANGE_PASSWORD_FORCE.put("initChangePassword", "initChangePassword");
		MAP_OF_ACTION_NAME_FOR_SKIP_CHANGE_PASSWORD_FORCE.put("addChangePassword", "addChangePassword");
		MAP_OF_ACTION_NAME_FOR_SKIP_CHANGE_PASSWORD_FORCE.put("cancelChangePassword", "cancelChangePassword");
		MAP_OF_ACTION_NAME_FOR_SKIP_CHANGE_PASSWORD_FORCE.put("logoutLogin", "logoutLogin");
	}

	public static final String HIGHLIGHT_BEGIN = "<font color='red'>";
	public static final String HIGHLIGHT_END = "</font>";
	public static final String NEW_LINE = "<br>";

	public static final String THEME_ACTIVE = "themeActive";

	//Progress bar
	public static final String FLAG_PROCESS = "flagProcess";
	public static final String PERCENT = "percent";
	public static final String PERCENT_TXT = "percentTxt";

	public static final String FLAG_ACTIVE = "Y";
	public static final String FLAG_INACTIVE = "N";
	public static final String FLAG_DELETED = "Y";

	public static final String SYSTEM = "Web FrameWork";
	public static final int RPP_DEFAULT = 100;
	public static final int maxCharDisplayOutputMessage = 100;

	public static final int LIMIT_LINE_ERROR = 20;
	public static final int LIMIT_CHARECTOR = 4000;
	public static final int LIMIT_DEPDATE_CONFIG = 366;
	public static final int EXPIRE_MIN  = 1;
	public static final int EXPIRE_MAX  = 9999;
	public static final int EXPIRE_DAY_MAX  = 365;
	public static final int EXPIRE_MONTH_MAX  = 60;
	public static final int EXPIRE_YEAR_MAX  = 5;
	
	public static final String DATE_FORMAT_FOR_SEARCH = "YYYY-MM-DD";
	public static final String START_TIME = "00:00:00";
	public static final String END_TIME = "23:59:59";

	public enum Delimeter {
		DOT("."), BLANK(" "), EMPTY(""), STAR("*"), COMMA(","), COLON(":");

		private String value;

		private Delimeter(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

	/**
	 * เธชเธ–เธฒเธ�เธฐเธ�เธฒเธฃเน�เธชเธ”เธ� Captcha
	 * @author Admin
	 *
	 */
	public enum LoginCaptcha {
		NONE("1"), SHOW("2"), SHOW_WHEN_INCORRECT("3");

		private String value;

		private LoginCaptcha(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}


	public enum Password {
		MIN(4), MAX(20);

		private Integer value;

		private Password(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

	public enum Timeout {
		MIN(30), MAX(60);

		private Integer value;

		private Timeout(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}
	
	public enum Lockuser {
		MIN(1), MAX(6);

		private Integer value;

		private Lockuser(Integer value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

	public enum Message {
		NO_PERMISSIONS("No permissions use task function.")
		, SERVER_ERROR("Internal server error.")
		, SESSION_EXPIRED("Session expired.")
		, SESSION_OVERRIDE_CODE("40010");

		private String value;

		private Message(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public static enum MessageType {
		ALERT("A")
		, CONFIRM("C")
		;

		private String value;

		private MessageType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum AttributeName {
		COUNT_LOGIN_WRONG("COUNT_LOGIN_WRONG")
		, GOTO_CHANGE_PASSWORD("GOTO_CHANGE_PASSWORD")
		, GOTO_PROFILE("GOTO_PROFILE");
		private String value;

		private AttributeName(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum MessageCode {
		INVALID_CAPTCHA("10017")
		, INVALID_USERNAME("30039")
		, USER_NOT_AVAILABLE("30022")
		, PASSWORD_EXPIRED("30044")
		, FOUND_PROBLEM("30037")
		, FOUND_OVERRIDE("40003")
		, USER_IS_LOCKED("30054")
		, IP_NOT_MATCH("30048")
		, REQUIRED_DATA("10002")
		, INVALID_USERID_OR_PASSWORD("30032");

		private String value;

		private MessageCode(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum TimeDefault {
		START_HHMM("00:00")
		, END_HHMM("23:59")
		, START_HHMMSS("00:00:00")
		, END_HHMMSS("23:59:59");

		private String value;

		private TimeDefault(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * Reference sub system report folder
	 */
	public enum SystemReportFolder {
		USER_ADMIN("user/admin/")
		, CARRIER("carrierportal/carrier/")
		, CARRIER_USER("carrierportal/carrieruser/")
		,STD_AIRPORT("standard/airport/")
		;

		private String value;

		private SystemReportFolder(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum CarrierUserStatus {
		ACTIVE("A")
		, DISABLED("D")
		, INACTIVE("I")
		, PROVISIONAL("P")
		, REJECTED("R");

		private String value;

		private CarrierUserStatus(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum DateUnit {
		DAY("day")
		, MONTH("month")
		, YEAR("year");

		private String value;

		private DateUnit(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum CarrierUserType {
		BATCH_USER("Carrier Batch User")
		, ADMINISTRATOR_USER("Carrier Administrator User")
		, NORMAL_USER("Carrier Normal User");

		private String value;

		private CarrierUserType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum LoginType {
		NORMAL("Normal Log-in")
		, AUTO_BATCH("Automatic Batch Upload Log-in");

		private String value;

		private LoginType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum CarrierStatus {
		ACTIVE("A")
		, INACTIVE("I")
		, PROVISIONAL("P");

		private String value;

		private CarrierStatus(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum ChangePassword {
		CANCEL_RESULT("CANCEL_RESULT")
		, RESULT_MAIN("MAIN")
		, RESULT_LOGIN("LOGIN");

		private String value;

		private ChangePassword(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	public enum CarrierType {
		AIR("A")
		, GENERAL_AVIATION("G");

		private String value;

		private CarrierType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	public enum AirportStatus {
		INTERNATIONAL("I", "International airport");

		private String id;
		private String value;

		private AirportStatus(String id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getId() {
			return id;
		}
		
		public String getValue() {
			return value;
		}
	}
	

}