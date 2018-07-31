package com.cct.domain;

public enum GlobalType {

	CARRIER_TYPE("CarrierType")
	, USER_TYPE("UserType")
	, LOGIN_TYPE("LoginType")
	, CARRIER_USER_STATUS("CarrierUserStatus")
	, USER_STATUS("UserStatus")
	, MOVEMENT_TYPE("MovementType")
	, TRAVELLER_TYPE("TravellerType")
	, DATA_SOURCE("DataSource")
	, JOURNEY_TYPE("JourneyType")
	, DELETE_STATUS("DeleteStatus")
	, CARRIER_STATUS("CarrierStatus")
	, FLIGHT_STATUS("FlightStatus")
	, SCHEDULE_TYPE("ScheduleType")
	, DOCUMENT_TYPE("DocumentType")
	, BOARD_STATUS("BoardStatus")
	, CHECK_IN_TYPE("CheckInType")
	, GENDER_TYPE("GenderType")
	, MANIFEST_TYPE("ManifestType")
	, TYPE_OF_ARRIVAL("TypeOfArrival")
	, TYPE_OF_DEPARTURE("TypeOfDeparture")
	, ACTIVE_STATUS("ActiveStatus")
	, DATE_UNIT("DateUnit")
	, SEX("Sex")
	, WORK_STATUS("WorkStatus")
	;

	private String value;

	private GlobalType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
