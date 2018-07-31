package com.cct.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Validation implements Serializable {

	private static final long serialVersionUID = -4842128319238979099L;

	public enum ValidationAttr {
		AIRCRAFT_CALL_SIGN("AIRCRAFT_CALL_SIGN")
		, FLIGHT_NUMBER("FLIGHT_NUMBER")
		, SERVICE_NUMBER("SERVICE_NUMBER")
		, CARRIER_CODE("CARRIER_CODE")
		, USER_ID("USER_ID")
		, FAMILY_NAME_USER("FAMILY_NAME_USER")
		, GIVEN_NAME_USER("GIVEN_NAME_USER")
		, FAMILY_NAME_PAX("FAMILY_NAME_PAX")
		, GIVEN_NAME_PAX("GIVEN_NAME_PAX")
		, DOCUMENT_NUMBER("DOCUMENT_NUMBER")
		, TELEPHONE_CONTACT("TELEPHONE_CONTACT")
		, EXT("EXT")
		, FACSIMILE_NUMBER("FACSIMILE_NUMBER")
		, PORT("PORT")
		, RESERVATION_SYSTEM_CODE("RESERVATION_SYSTEM_CODE")
		, RECORD_LOCATOR("RECORD_LOCATOR")
		, EMAIL("EMAIL")
		, AIRPORT_CODE_IATA("AIRPORT_CODE_IATA")
		, INPUT_NUMBER("INPUT_NUMBER")
		
		;

		private String attr;

		private ValidationAttr(String attr) {
			this.attr = attr;
		}

		public String getAttr() {
			return attr;
		}
	}

	public enum ValidationLength {
		MAX_AIRCRAFT_CALL_SIGN("MAX_AIRCRAFT_CALL_SIGN")
		, MIN_AIRCRAFT_CALL_SIGN("MIN_AIRCRAFT_CALL_SIGN")
		, MAX_FLIGHT_NUMBER("MAX_FLIGHT_NUMBER")
		, MIN_FLIGHT_NUMBER("MIN_FLIGHT_NUMBER")
		, MAX_SERVICE_NUMBER("MAX_SERVICE_NUMBER")
		, MIN_SERVICE_NUMBER("MIN_SERVICE_NUMBER")
		, MIN_AIR_CARRIER_CODE("MIN_AIR_CARRIER_CODE")
		, MAX_AIR_CARRIER_CODE("MAX_AIR_CARRIER_CODE")
		, MIN_GENERAL_AVIATION_CARRIER_CODE("MIN_GENERAL_AVIATION_CARRIER_CODE")
		, MAX_GENERAL_AVIATION_CARRIER_CODE("MAX_GENERAL_AVIATION_CARRIER_CODE")
		;

		private String attr;

		private ValidationLength(String attr) {
			this.attr = attr;
		}

		public String getAttr() {
			return attr;
		}
	}
	
	public enum ValidationRegPasswordAttr {
		UPPER_CASE("UPPER_CASE")
		, LOWER_CASE("LOWER_CASE")
		, DIGIT("DIGIT")
		, SPECIAL_CHAR("SPECIAL_CHAR")
		
		
		;

		private String attr;

		private ValidationRegPasswordAttr(String attr) {
			this.attr = attr;
		}

		public String getAttr() {
			return attr;
		}
	}
	
	public enum ValidationAdmPasswordFormat {
		FORMAT_NUM_CHAR("FORMAT_NUM_CHAR")
		, FORMAT_BIG_CHAR("FORMAT_BIG_CHAR")
		, FORMAT_LIT_CHAR("FORMAT_LIT_CHAR")
		, FORMAT_SPECIAL_CHAR("FORMAT_SPECIAL_CHAR")
		;

		private String attr;

		private ValidationAdmPasswordFormat(String attr) {
			this.attr = attr;
		}

		public String getAttr() {
			return attr;
		}
	}

	public static final Map<String, String> MAP_OF_VALIDATION_FORMAT = new HashMap<String, String>();
	static {
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.AIRCRAFT_CALL_SIGN.getAttr(), "^[0-9A-Z]+$");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.FLIGHT_NUMBER.getAttr(), "^[0-9A-Z]+$");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.SERVICE_NUMBER.getAttr(), "^[0-9A-Z]+$");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.CARRIER_CODE.getAttr(), "^[0-9A-Z]+$");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.USER_ID.getAttr(), "^[0-9A-Za-z]+$");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.FAMILY_NAME_USER.getAttr(), "^[A-Z-' ]+$");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.GIVEN_NAME_USER.getAttr(), "^[A-Z-' ]+$");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.FAMILY_NAME_PAX.getAttr(), "^[A-Z-' ]+$");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.GIVEN_NAME_PAX.getAttr(), "^[A-Z-' ]+$");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.DOCUMENT_NUMBER.getAttr(), "^[0-9A-Z]+$");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.TELEPHONE_CONTACT.getAttr(), "^[0-9+]+$");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.EXT.getAttr(), "^[0-9]+$");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.FACSIMILE_NUMBER.getAttr(), "^[0-9+]+$");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.PORT.getAttr(), "^[A-Z]+$");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.RESERVATION_SYSTEM_CODE.getAttr(), "^[0-9A-Z]+$");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.RECORD_LOCATOR.getAttr(), "^[0-9A-Z]+$");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.EMAIL.getAttr(),"^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$");
		
		MAP_OF_VALIDATION_FORMAT.put(ValidationRegPasswordAttr.UPPER_CASE.getAttr(), ".*[A-Z].*");
		MAP_OF_VALIDATION_FORMAT.put(ValidationRegPasswordAttr.LOWER_CASE.getAttr(), ".*[a-z].*");
		MAP_OF_VALIDATION_FORMAT.put(ValidationRegPasswordAttr.DIGIT.getAttr(), ".*[0-9].*");
		MAP_OF_VALIDATION_FORMAT.put(ValidationRegPasswordAttr.SPECIAL_CHAR.getAttr(), ".*[!@#$%^&*?+_()|\\]\\[].*");
		
		MAP_OF_VALIDATION_FORMAT.put(ValidationAdmPasswordFormat.FORMAT_BIG_CHAR.getAttr(), ".*[A-Z].*");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAdmPasswordFormat.FORMAT_LIT_CHAR.getAttr(), ".*[a-z].*");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAdmPasswordFormat.FORMAT_NUM_CHAR.getAttr(), ".*[0-9].*");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAdmPasswordFormat.FORMAT_SPECIAL_CHAR.getAttr(), ".*[!@#$%^&*?+_()|\\]\\[].*");
		// master data
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.AIRPORT_CODE_IATA.getAttr(), "^[A-Z]+$");
		MAP_OF_VALIDATION_FORMAT.put(ValidationAttr.INPUT_NUMBER.getAttr(), "^[0-9]+$");
	}

	public static final Map<String, String> MAP_OF_VALIDATION_FORMAT_JS = new HashMap<String, String>();
	static {
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAttr.AIRCRAFT_CALL_SIGN.getAttr(), "^(?=.*[0-9A-Z])");
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAttr.FLIGHT_NUMBER.getAttr(), "^(?=.*[0-9A-Z])");
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAttr.SERVICE_NUMBER.getAttr(), "^(?=.*[0-9])(?=.*[A-Z])");
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAttr.CARRIER_CODE.getAttr(), "^[0-9A-Z]+$");
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAttr.USER_ID.getAttr(), "^[0-9A-Za-z]+$");
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAttr.FAMILY_NAME_USER.getAttr(), "^[A-Z-' ]+$"); // A-Z, hyphen, apostrophe(') or (Space)
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAttr.GIVEN_NAME_USER.getAttr(), "^[A-Z-' ]+$"); // A-Z, hyphen, apostrophe(') or (Space)
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAttr.FAMILY_NAME_PAX.getAttr(), "^[A-Z-' ]+$"); // A-Z, hyphen, apostrophe(') or (Space)
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAttr.DOCUMENT_NUMBER.getAttr(), "^(?=.*[0-9A-Z])");
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAttr.GIVEN_NAME_PAX.getAttr(), "^[A-Z-' ]+$"); // A-Z, hyphen, apostrophe(') or (Space)
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAttr.TELEPHONE_CONTACT.getAttr(), "^[0-9+]+$");
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAttr.EXT.getAttr(), "^[0-9]+$");
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAttr.FACSIMILE_NUMBER.getAttr(), "^[0-9+]+$");
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAttr.PORT.getAttr(), "^[A-Z]+$");
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAttr.RESERVATION_SYSTEM_CODE.getAttr(), "^(?=.*[0-9A-Z])");
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAttr.RECORD_LOCATOR.getAttr(), "^(?=.*[0-9A-Z])");
		
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationRegPasswordAttr.UPPER_CASE.getAttr(), "[A-Z]");
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationRegPasswordAttr.LOWER_CASE.getAttr(), "[a-z]");
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationRegPasswordAttr.DIGIT.getAttr(), "[0-9]");
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationRegPasswordAttr.SPECIAL_CHAR.getAttr(), "!@#$%^&*?+_()|[]");
		
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAdmPasswordFormat.FORMAT_BIG_CHAR.getAttr(), "[A-Z]");
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAdmPasswordFormat.FORMAT_LIT_CHAR.getAttr(), "[a-z]");
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAdmPasswordFormat.FORMAT_NUM_CHAR.getAttr(), "[0-9]");
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAdmPasswordFormat.FORMAT_SPECIAL_CHAR.getAttr(), "!@#$%^&*?+_()|[]");
		
		// master data
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAttr.AIRPORT_CODE_IATA.getAttr(), "^[A-Z]+$");
		MAP_OF_VALIDATION_FORMAT_JS.put(ValidationAttr.INPUT_NUMBER.getAttr(), "^[0-9]+$");
	}

	public static final Map<String, String> MAP_OF_VALIDATION_LENGTH = new HashMap<String, String>();
	static {
		MAP_OF_VALIDATION_LENGTH.put(ValidationLength.MAX_AIRCRAFT_CALL_SIGN.getAttr(), "8");
		MAP_OF_VALIDATION_LENGTH.put(ValidationLength.MIN_AIRCRAFT_CALL_SIGN.getAttr(), "3");
		MAP_OF_VALIDATION_LENGTH.put(ValidationLength.MAX_FLIGHT_NUMBER.getAttr(), "8");
		MAP_OF_VALIDATION_LENGTH.put(ValidationLength.MIN_FLIGHT_NUMBER.getAttr(), "3");
		MAP_OF_VALIDATION_LENGTH.put(ValidationLength.MAX_SERVICE_NUMBER.getAttr(), "8");
		MAP_OF_VALIDATION_LENGTH.put(ValidationLength.MIN_SERVICE_NUMBER.getAttr(), "3");
		MAP_OF_VALIDATION_LENGTH.put(ValidationLength.MAX_AIR_CARRIER_CODE.getAttr(), "3");
		MAP_OF_VALIDATION_LENGTH.put(ValidationLength.MIN_AIR_CARRIER_CODE.getAttr(), "2");
		MAP_OF_VALIDATION_LENGTH.put(ValidationLength.MAX_GENERAL_AVIATION_CARRIER_CODE.getAttr(), "3");
		MAP_OF_VALIDATION_LENGTH.put(ValidationLength.MIN_GENERAL_AVIATION_CARRIER_CODE.getAttr(), "2");
	}

}