
<%@page import="com.cct.domain.Validation.ValidationAttr"%>
<%@page import="com.cct.domain.Validation"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<script type="text/javascript">
	var input_aircraft_callsign = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAttr@AIRCRAFT_CALL_SIGN.getAttr()]' />";
	var input_flight_number = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAttr@FLIGHT_NUMBER.getAttr()]' />";
	var input_service_number = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAttr@SERVICE_NUMBER.getAttr()]' />";
	var input_carrier_code = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAttr@CARRIER_CODE.getAttr()]' />";
	var input_user_id = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAttr@USER_ID.getAttr()]' />";
	var input_family_name_user = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAttr@FAMILY_NAME_USER.getAttr()]' />";
	var input_given_name_user = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAttr@GIVEN_NAME_USER.getAttr()]' />";
	var input_family_name_pax = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAttr@FAMILY_NAME_PAX.getAttr()]' />";
	var input_given_name_pax = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAttr@GIVEN_NAME_PAX.getAttr()]' />";
	var input_document_number = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAttr@DOCUMENT_NUMBER.getAttr()]' />";
	var input_telephone_contact = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAttr@TELEPHONE_CONTACT.getAttr()]' />";
	var input_ext = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAttr@EXT.getAttr()]' />";
	var input_facsimile_number = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAttr@FACSIMILE_NUMBER.getAttr()]' />";
	var input_port = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAttr@PORT.getAttr()]' />";
	var input_reservation_sys_code = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAttr@RESERVATION_SYSTEM_CODE.getAttr()]' />";
	var input_record_locator = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAttr@RECORD_LOCATOR.getAttr()]' />";
		
	var min_aircraft_callsign = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_LENGTH[@com.cct.domain.Validation$ValidationLength@MIN_AIRCRAFT_CALL_SIGN.getAttr()]' />";
	var max_aircraft_callsign = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_LENGTH[@com.cct.domain.Validation$ValidationLength@MAX_AIRCRAFT_CALL_SIGN.getAttr()]' />";
	var min_flight_number = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_LENGTH[@com.cct.domain.Validation$ValidationLength@MIN_FLIGHT_NUMBER.getAttr()]' />";
	var max_flight_number = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_LENGTH[@com.cct.domain.Validation$ValidationLength@MAX_FLIGHT_NUMBER.getAttr()]' />";
	var min_service_number = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_LENGTH[@com.cct.domain.Validation$ValidationLength@MIN_SERVICE_NUMBER.getAttr()]' />";
	var max_service_number = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_LENGTH[@com.cct.domain.Validation$ValidationLength@MAX_SERVICE_NUMBER.getAttr()]' />";
	var min_air_carier_code = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_LENGTH[@com.cct.domain.Validation$ValidationLength@MIN_AIR_CARRIER_CODE.getAttr()]' />";
	var max_air_carier_code = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_LENGTH[@com.cct.domain.Validation$ValidationLength@MAX_AIR_CARRIER_CODE.getAttr()]' />";
	var min_general_aviation_carrier_code = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_LENGTH[@com.cct.domain.Validation$ValidationLength@MIN_GENERAL_AVIATION_CARRIER_CODE.getAttr()]' />";
	var max_general_aviation_carrier_code = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_LENGTH[@com.cct.domain.Validation$ValidationLength@MAX_GENERAL_AVIATION_CARRIER_CODE.getAttr()]' />";
	
	// Password ตาม REG_CONFIG_SYSTEM ///////
	var regPwMinLenght = "<s:property value='@com.cct.trn.core.config.regis.domain.RegConfigSystem@getPwMinLenght()'/>";
	var regPwMaxLenght = "<s:property value='@com.cct.trn.core.config.regis.domain.RegConfigSystem@getPwMaxLenght()'/>";
	var regPwUpperCaseStatus = "<s:property value='@com.cct.trn.core.config.regis.domain.RegConfigSystem@getPwUpperCase()'/>";// Y or N
	var regPwLowerCaseStatus = "<s:property value='@com.cct.trn.core.config.regis.domain.RegConfigSystem@getPwLowerCase()'/>";// Y or N
	var regPwDigitStatus = "<s:property value='@com.cct.trn.core.config.regis.domain.RegConfigSystem@getPwDigit()'/>";// Y or N
	var regPwSpecialCharStatus = "<s:property value='@com.cct.trn.core.config.regis.domain.RegConfigSystem@getPwSpecialChar()'/>";// Y or N
	
	var regPwUpperCase = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationRegPasswordAttr@UPPER_CASE.getAttr()]' />";
	var regPwLowerCase = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationRegPasswordAttr@LOWER_CASE.getAttr()]' />";
	var regPwDigitCase = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationRegPasswordAttr@DIGIT.getAttr()]' />";
	var regPwSpecialCase = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationRegPasswordAttr@SPECIAL_CHAR.getAttr()]' escapeHtml='false'/>";
	/////////////////////////////////
	
	// ADM Config Password
	var pwAdmMinLenght = "<s:property value='@com.cct.trn.core.config.system.domain.ADMSystemConfig@getPwminLength()'/>";
	var pwAdmMaxLenght = "<s:property value='@com.cct.trn.core.config.system.domain.ADMSystemConfig@getPwmaxLength()'/>";
	var pwAdmBigChar = "<s:property value='@com.cct.trn.core.config.system.domain.ADMSystemConfig@getPwformatBigChar()'/>";			// Y or N
	var pwAdmLitChar = "<s:property value='@com.cct.trn.core.config.system.domain.ADMSystemConfig@getPwformatLitChar()'/>";			// Y or N
	var pwAdmNumChar = "<s:property value='@com.cct.trn.core.config.system.domain.ADMSystemConfig@getPwformatNum()'/>";				// Y or N
	var pwAdmSpecialChar = "<s:property value='@com.cct.trn.core.config.system.domain.ADMSystemConfig@getPwformatSpecial()'/>";		// Y or N
	
	var pwAdmFormatBigChar = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAdmPasswordFormat@FORMAT_BIG_CHAR.getAttr()]' />";
	var pwAdmFormatLitChar = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAdmPasswordFormat@FORMAT_LIT_CHAR.getAttr()]' />";
	var pwAdmFormatNumChar = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAdmPasswordFormat@FORMAT_NUM_CHAR.getAttr()]' />";
	var pwAdmFormatSpecialChar = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAdmPasswordFormat@FORMAT_SPECIAL_CHAR.getAttr()]' escapeHtml='false'/>";
	
	// Master data
	var input_airport_code_iata = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAttr@AIRPORT_CODE_IATA.getAttr()]' />";

	//ตรวจสอบการกรอก ตัวเลข
	var input_number = "<s:property value='@com.cct.domain.Validation@MAP_OF_VALIDATION_FORMAT_JS[@com.cct.domain.Validation$ValidationAttr@INPUT_NUMBER.getAttr()]' />";
	
	jQuery( document ).ready(function() {
		// สำหรับกำหนด attribute ที่ต้องการให้ค่าที่ทำการกรอกเป็นอักษรพิมพ์ใหญ่แบบอัตโนมัติ และ กำหนด event onkeypress
		jQuery("[cp_validation='input_aircraft_callsign']").css("text-transform","uppercase").attr('onkeypress', 'return chkCharAndNumber(event);').attr('maxlength', max_aircraft_callsign); // A-Z, 0-9
		// jQuery("[cp_validation='input_flight_number']").css("text-transform","uppercase").attr('onkeypress', 'return chkCharAndNumber(event);').attr('maxlength', max_flight_number); // A-Z, 0-9
		jQuery("[cp_validation='input_format_flight_number']").css("text-transform","uppercase").attr('onkeypress', 'return chkCharAndNumber(event);').attr('maxlength', max_flight_number); // A-Z, 0-9
		jQuery("[cp_validation='input_flight_format_number']").css("text-transform","uppercase").attr('onkeypress', 'return chkCharAndNumber(event);').attr('maxlength', 6); // A-Z, 0-9
		jQuery("[cp_validation='input_service_number']").css("text-transform","uppercase").attr('onkeypress', 'return chkCharAndNumber(event);').attr('maxlength', max_service_number); // A-Z, 0-9
		jQuery("[cp_validation='input_carrier_code']").css("text-transform","uppercase").attr('onkeypress', 'return chkCharAndNumber(event);'); // A-Z, 0-9
		jQuery("[cp_validation='input_air_carrier_code']").css("text-transform","uppercase").attr('onkeypress', 'return chkCharAndNumber(event);').attr('maxlength', max_air_carier_code); // A-Z, 0-9
		jQuery("[cp_validation='input_general_aviation_carrier_code']").css("text-transform","uppercase").attr('onkeypress', 'return chkCharAndNumber(event);').attr('maxlength', max_general_aviation_carrier_code); // A-Z, 0-9
		jQuery("[cp_validation='input_user_id']").attr('onkeypress', 'return chkCharAndNumber(event);'); // A-Z, a-z, 0-9
		jQuery("[cp_validation='input_family_name_user']").css("text-transform","uppercase").attr('onkeypress', 'return chkEngSpaceApostropheHyphenInput(event);'); // A-Z, hyphen(-), apostrophe(') or (Space)
		jQuery("[cp_validation='input_given_name_user']").css("text-transform","uppercase").attr('onkeypress', 'return chkEngSpaceApostropheHyphenInput(event);'); // A-Z, hyphen(-), apostrophe(') or (Space)
		jQuery("[cp_validation='input_family_name_pax']").css("text-transform","uppercase").attr('onkeypress', 'return chkEngSpaceApostropheHyphenInput(event);'); // A-Z, hyphen(-), apostrophe(') or (Space)
		jQuery("[cp_validation='input_given_name_pax']").css("text-transform","uppercase").attr('onkeypress', 'return chkEngSpaceApostropheHyphenInput(event);'); // A-Z, hyphen(-), apostrophe(') or (Space)
		jQuery("[cp_validation='input_document_number']").css("text-transform","uppercase").attr('onkeypress', 'return chkCharAndNumber(event);'); // A-Z, 0-9
		jQuery("[cp_validation='input_telephone_contact']").attr('onkeypress', 'return chkNumberAndPlus(event);'); // 0-9, +
		jQuery("[cp_validation='input_ext']").attr('onkeypress', 'return chkNumber(event);'); // 0-9
		jQuery("[cp_validation='input_facsimile_number']").attr('onkeypress', 'return chkNumberAndPlus(event);'); // 0-9, +
		jQuery("[cp_validation='input_port']").css("text-transform","uppercase").attr('onkeypress', 'return chkEngNotSpaceInput(event);'); // A-Z
		jQuery("[cp_validation='input_reservation_sys_code']").css("text-transform","uppercase").attr('onkeypress', 'return chkCharAndNumber(event);'); // A-Z, 0-9
		jQuery("[cp_validation='input_record_locator']").css("text-transform","uppercase").attr('onkeypress', 'return chkCharAndNumber(event);'); // A-Z, 0-9
		
		// Master data
		jQuery("[cp_validation='input_airport_code_iata']").css("text-transform","uppercase").attr('onkeypress', 'return chkEngNotSpaceInput(event);'); // A-Z

		jQuery("[cp_validation='input_number']").attr('onkeypress', 'return chkNumber(event);'); // 0-9
	});

	var objFocus;
	var msgLabel = "";
	function validateFormInputAll (type) {
		/* clear msg */
		clearMessage();
		msgLabel = "";
		objFocus = null;
		
		/** validate require */
		jQuery("input, select, textarea").each(function (index) {
			var className = jQuery(this).attr("class");
			if (className != undefined) {
				if (className.indexOf('readonly') == -1) {
					if (className.indexOf('requireInput') > -1) {
						console.log(jQuery(this));
						var value;
						if ((jQuery(this).attr('type') == 'radio') || (jQuery(this).attr('type') == 'checkbox')) {
							var name = jQuery(this).attr( "name" );
							if(jQuery("input[name='" + name + "']:checked").length > 0) {
								if (jQuery("input[name='" + name + "']").val() != undefined && jQuery("input[name='" + name + "']").val() != "") {
									value = jQuery("input[name='" + name + "']").val();	
								} else {
									value = "Y";
								}
							} else {
								value = "";
							}
						} else {
							value = trim(jQuery(this).val())
						}

						if (value == "") {
							var validName;
							if (jQuery(this).attr("validName") != undefined) {
								validName = jQuery(this).attr("validName");
							} else {
								var type = jQuery(this).attr("type");
								var className = jQuery(this).attr("class");
								
								if ((type == 'radio') || (type == 'checkbox')) {
									validName = jQuery(this).parent().attr('validName');
								} else {
									if (className.indexOf('input_dateformat_') > -1) {
										var eleId = jQuery(this).attr("id").replace("_dd_sl_mm_sl_yyyy","");
										validName = jQuery("#"+eleId).attr("validName");
									} else if (className.indexOf('input_timeformat_') > -1) {
										var eleId = jQuery(this).attr("id").replace("_hh_cl_mm","");
										validName = jQuery("#"+eleId).attr("validName");
									}
								}
							}
							
							if (validName != undefined) {
								if ((jQuery(this).attr('type') == 'radio') || (jQuery(this).attr('type') == 'checkbox')) {
									msg = validateMessage.CODE_10029.replace("xxx", validName); // xxx must select at least one item.
								} else {
									msg = validateMessage.CODE_10003.replace("xxx", validName); // xxx cannot be blank.
								}
								setMessageValid(type, msg, jQuery(this));
							}
						} else {
							validateFormatInputAll(type, jQuery(this));
						}
					} else {
						if (trim(jQuery(this).val()) != "") {
							validateFormatInputAll(type, jQuery(this));
						}
					}
				}
			}
			

			if (jQuery(this).css("text-transform") == "uppercase") {
				jQuery(this).val(jQuery(this).val().toUpperCase());
			}
		});
		/** end validate require */
		
		if (objFocus != undefined && msgLabel != "") {
			objFocus.focus();
			
			if (type == 'sum') {
				jQuery('.messFed .MESSAGE').html("Insufficient data.");
			} else if (type == 'part') { 
				
			} else {
				jQuery('.messFed .MESSAGE').html(msgLabel);
				jQuery('.messFed').css("height", "auto");
			}
			
			return false;
		} else {
			return true;
		}
	}

	function clearMessage() {
		jQuery("div.messFed table#tblMessage").removeAttr("class").addClass("FORM");
		jQuery("div.messFed table#tblMessage tbody tr td").remove();
		jQuery("div.messFed table#tblMessage tbody tr").html("<td class='MESSAGE'>&nbsp;</td>");

		jQuery(".msgLabel").each(function (index) {
			jQuery(this).html("");	
		});
		
		jQuery(".border_select").each(function (index) {
			jQuery(this).removeClass("border_select");
		});
	}

	function validateFormatInputAll(type, obj) {
		/** validate format */
		var className = obj.attr("class");
		
		if (className.indexOf('input_email') > -1) {
			if (validateEmailFormatFormIndex(obj) == false) {
				msg = validateMessage.CODE_10004.replace("xxx", "Email address");
				setMessageValid(type, msg, obj);
			}
		} else if (className.indexOf('input_dateformat_') > -1) {
			var status = inputValidateDateFormatValueName(jQuery(obj)[0]);
			if ((status == 'empty') || status == 'ok') {

			} else {
				var validN = "";
				var validNTemp = obj;
				for(var i= 0;i<5;i++){
					var n = jQuery(validNTemp).parent().prev();
					if(n.length > 0){
						if(n[0].tagName == "TD"){
							validN = jQuery(validNTemp).parent().prev("td").html().replace("<em>*</em>","");
							validN = validN.replace("<em>&nbsp;*</em>","");
							break;
						}else{
							validNTemp = jQuery(validNTemp).parent();
						}
					}else{
						validNTemp = jQuery(validNTemp).parent();
					}
					
				}
				
				if(validN == ""){
					validT = "Date";
				}
				
				
				//var validN = jQuery(obj).parent().prev("td").html().replace("<em>*</em>","");
				msg = validateMessage.CODE_10004.replace("xxx", validN);
				setMessageValid(type, msg, obj);
			}
		} else if (className.indexOf('input_timeformat_') > -1) {
			var status = inputValidateTimeFormatValueName(jQuery(obj)[0]);
			if ((status == 'empty') || status == 'ok') {

			} else {
				msg = validateMessage.CODE_10004.replace("xxx", "Time");
				setMessageValid(type, msg, obj);
			}
		}
		/** end validate format */
		
		var validation = obj.attr("cp_validation");
		if (validation != undefined) {
			validateFormatInput (type, obj, validation);
		}
		
	}
		
	function validateFormatInput (type, obj, validation) {
		if (validation.indexOf('input_aircraft_callsign') > -1) {
			if (validateAircraftCallsign(obj) == false) {
				msg = validateMessage.CODE_10001.replace("xxx", "Aircraft call sign");
				setMessageValid(type, msg, obj);
			} else {
				if (validateLengthInputAircraftCallsign(obj) == false) {
					msg = validateMessage.CODE_10022.replace("xxx", "Aircraft call sign");
					setMessageValid(type, msg, obj);
				} else {
					
				}
			}
			
		/* } else if (validation.indexOf('input_flight_number') > -1) {
			if (validateFlightNumber(obj) == false) {
				msg = validateMessage.CODE_10001.replace("xxx", "Flight number");
				setMessageValid(type, msg, obj);
			} else {
				if (validateLengthInputFlightNumber(obj) == false) {
					msg = validateMessage.CODE_10021.replace("xxx", "Flight number");
					setMessageValid(type, msg, obj);
				} else {
					
				}
			} */

		} else if (validation.indexOf('input_format_flight_number') > -1) {
			if (validateFlightNumber(obj) == false) {
				msg = validateMessage.CODE_10001.replace("xxx", "Flight number");
				setMessageValid(type, msg, obj);
			} else {
				if (validateLengthInputFlightNumber(obj) == false) {
					msg = validateMessage.CODE_10021.replace("xxx", "Flight number");
					setMessageValid(type, msg, obj);
				} else {
					if (validateFlightNumberFormat(obj) == false) {
						msg = validateMessage.CODE_10012.replace("xxx", "Flight number");
						setMessageValid(type, msg, obj);
					} else {
						
					}
				}
			}

		} else if (validation.indexOf('input_flight_format_number') > -1) {
			if (validateFlightNumber(obj) == false) {
				msg = validateMessage.CODE_10001.replace("xxx", "Flight number");
				setMessageValid(type, msg, obj);
			} else {
				if (validateFlightFormatNumber(obj) == false) {
					msg = validateMessage.CODE_10012.replace("xxx", "Flight number");
					setMessageValid(type, msg, obj);
				} else {
					
				}
			}

		} else if (validation.indexOf('input_service_number') > -1) {
			if (validateServiceNumber(obj) == false) {
				msg = validateMessage.CODE_10001.replace("xxx", "Service number");
				setMessageValid(type, msg, obj);
			} else {
				if (validateLengthInputServiceNumber(obj) == false) {
					msg = validateMessage.CODE_10021.replace("xxx", "Service number");
					setMessageValid(type, msg, obj);
				} else {
					
				}
			}
			
		} else if (validation.indexOf('input_carrier_code') > -1) {
			if (validateCarrierCode(obj) == false) {
				msg = validateMessage.CODE_10001.replace("xxx", "Carrier code");
				setMessageValid(type, msg, obj);
			} else {
				
			}
			
		} else if (validation.indexOf('input_user_id') > -1) {
			if (validateUserId(obj) == false) {
				msg = validateMessage.CODE_10028.replace("xxx", "User ID");
				setMessageValid(type, msg, obj);
			} else {
				
			}
			
		} else if (validation.indexOf('input_family_name_user') > -1) {
			if (validateFamilyNameUser(obj) == false) {
				msg = validateMessage.CODE_10018.replace("xxx", "Family name");
				setMessageValid(type, msg, obj);
			} else {
				
			}
			
		} else if (validation.indexOf('input_given_name_user') > -1) {
			if (validateGivenNameUser(obj) == false) {
				msg = validateMessage.CODE_10017.replace("xxx", "Given name(s)");
				setMessageValid(type, msg, obj);
			} else {
				
			}

		} else if (validation.indexOf('input_family_name_pax') > -1) {
			if (validateFamilyNamePax(obj) == false) {
				msg = validateMessage.CODE_10018.replace("xxx", "Family name");
				setMessageValid(type, msg, obj);
			} else {
				
			}
			
		} else if (validation.indexOf('input_given_name_pax') > -1) {
			if (validateGivenNamePax(obj) == false) {
				msg = validateMessage.CODE_10017.replace("xxx", "Given name(s)");
				setMessageValid(type, msg, obj);
			} else {
				
			}

		} else if (validation.indexOf('input_document_number') > -1) {
			if (validateDocumentNumber(obj) == false) {
				msg = validateMessage.CODE_10001.replace("xxx", "Document number");
				setMessageValid(type, msg, obj);
			} else {
				
			}
			
		} else if (validation.indexOf('input_telephone_contact') > -1) {
			if (validateTelephoneContact(obj) == false) {
				msg = validateMessage.CODE_10019.replace("xxx", "Telephone contact");
				setMessageValid(type, msg, obj);
			}
		
		} else if (validation.indexOf('input_ext') > -1) {
			if (validateExt(obj) == false) {
				msg = validateMessage.CODE_10020.replace("xxx", "Ext.");
				setMessageValid(type, msg, obj);
			}
			
		} else if (validation.indexOf('input_facsimile_number') > -1) {
			if (validateFacsimileNumber(obj) == false) {
				msg = validateMessage.CODE_10019.replace("xxx", "Facsimile number");
				setMessageValid(type, msg, obj);
			}
			
		} else if (validation.indexOf('input_port') > -1) {
			if (validatePort(obj) == false) {
				msg = validateMessage.CODE_10010.replace("xxx", "Port");
				setMessageValid(type, msg, obj);
			} else {
				
			}
			
		} else if (validation.indexOf('input_reservation_sys_code') > -1) {
			if (validateReservationSysCode(obj) == false) {
				msg = validateMessage.CODE_10001.replace("xxx", "Reservation system code");
				setMessageValid(type, msg, obj);
			} else {
				
			}
			
		} else if (validation.indexOf('input_record_locator') > -1) {
			if (validateRecordLocator(obj) == false) {
				msg = validateMessage.CODE_10001.replace("xxx", "Record locator");
				setMessageValid(type, msg, obj);
			} else {
				
			}
			
		}else if (validation.indexOf('input_reg_password') > -1) {
			if (validateRegPassword(obj) == false) {
				msg = validateMessage.CODE_10004.replace("xxx", "Password");
				setMessageValid(type, msg, obj);
			} else {
			
			}
			
		} else if (validation.indexOf('input_adm_password') > -1) {
			if (validateAdmPassword(obj) == false) {
				var validName = "Password";
				if (jQuery(obj).attr("validName") != undefined) {
					validName = jQuery(obj).attr("validName");
				}
				
				msg = validateMessage.CODE_10004.replace("xxx", validName);
				setMessageValid(type, msg, obj);
				
			} else {
			
			}
			
		} else if (validation.indexOf('input_confirm_password') > -1) {
			var eleId = jQuery(obj).attr("validTo");
			var newPassword = jQuery("#"+eleId).val();
			
			if (eleId != undefined && trim(newPassword) != "") {
				var newPassword = jQuery("#" + eleId).val();
				var confirmPassword = jQuery(obj).val();
				
				if (newPassword != confirmPassword) {
					setMessageValid(type, validateMessage.CODE_10071, obj);
				}
			}

		}else if (validation.indexOf('input_account_expiry_date') > -1) {
			if (validateAccountExpiryDate(obj) == false) {
				msg = validateMessage.CODE_10036;
				setMessageValid(type, msg, obj);
			} else {
			
			}
		
		}else if (validation.indexOf('input_spinner_check_minmax') > -1){
			var eleId = jQuery(obj).attr("validTo");
			var min = parseInt(jQuery(obj).val());
			var max = parseInt(jQuery("#" + eleId).val());			
			var validNameMin = jQuery(obj).attr("validName");
			var validNameMax = jQuery("#"+eleId).attr("validName");
			var spinnerMaxValue = jQuery("#" + eleId).val();
			
				if (validateExt(obj) == false ) {
					msg = validateMessage.CODE_30067;
					setMessageValid(type, msg, obj);
				}
				if(validateExt(jQuery("#"+eleId)) == false && spinnerMaxValue != "" && jQuery.trim(jQuery("#"+eleId).val()) != ""){
					msg = validateMessage.CODE_30067;
					setMessageValid(type, msg, jQuery("#"+eleId));
				}
				
				if (eleId != undefined) {
					if (min > max) {
						msg = validateMessage.CODE_10037.replace("xxx", validNameMin);
						msg = msg.replace("yyy", validNameMax);
						setMessageValid(type, msg, obj);
					}
				}
			
		}else if (validation.indexOf('input_mail_port') > -1){
			if (validateExt(obj) == false) {
				var validName = jQuery(obj).attr("validName");
				msg = validateMessage.CODE_10020.replace("xxx", validName);
				setMessageValid(type, msg, obj);
			}
		}else if (validation.indexOf('input_spinner_format') > -1){
			if (validateExt(obj) == false) {
				var validName = jQuery(obj).attr("validName");
				msg = validateMessage.CODE_30067;
				setMessageValid(type, msg, obj);
			}
			
		} else if (validation.indexOf('input_air_carrier_code') > -1) {
			if (validateAirCarrierCode(obj) == false) {
				msg = validateMessage.CODE_10004.replace("xxx", "Carrier code");
				setMessageValid(type, msg, obj);
			} else {
				
			}
			
		} else if (validation.indexOf('input_general_aviation_carrier_code') > -1) {
			if (validateGeneralAviationCarrierCode(obj) == false) {
				msg = validateMessage.CODE_10004.replace("xxx", "Carrier code");
				setMessageValid(type, msg, obj);
			} else {
				
			}
			
		} else if (validation.indexOf('input_airport_code_iata') > -1) {
			if (validateAirportCodeIata(obj) == false) {
				msg = validateMessage.CODE_10039.replace("xxx", "Airport code (IATA)");
				setMessageValid(type, msg, obj);
			} else {
				
			}

		}else if (validation.indexOf('input_number') > -1) {
			if (validateInputNumber(obj) == false) {
				msg = validateMessage.CODE_30067;
				setMessageValid(type, msg, obj);
			}
			
		}
		
		
	}
	
	/*
	* validate pasword ตามค่า config
	*/
	function validateRegPassword(el){
		
		var st = true;
		if(jQuery(el).val().indexOf(" ") >= 0){
			return false;
		}
		
		if(trim(jQuery(el).val()).length < regPwMinLenght ){
			return false;
		}
		
		if(trim(jQuery(el).val()).length > regPwMaxLenght ) {
			return false;	
		}
		
		if(regPwUpperCaseStatus == "Y"){
			var regularExpression = new RegExp(regPwUpperCase);
			st = regularExpression.test(trim(jQuery(el).val()));
			if(!st)return st;
		}
		
		if(regPwLowerCaseStatus == "Y"){
			var regularExpression = new RegExp(regPwLowerCase);
			st = regularExpression.test(trim(jQuery(el).val()));
			if(!st)return st;
		}
		
		if(regPwDigitStatus == "Y"){
			var regularExpression = new RegExp(regPwDigitCase);
			st = regularExpression.test(trim(jQuery(el).val()));
			if(!st)return st;
		}
		
		if(regPwSpecialCharStatus == "Y"){
			var specialString = regPwSpecialCase;
			var str = jQuery(el).val();
			st = true;
			for (var i=0; i < specialString.length; i++) {
				if (str.indexOf(specialString.substring(i, i+1)) > -1) {
					 st = false;
				}
			}
			if (st) return false;
			
		}
		
		return true;
		
	}
	
	/** Validate ADM Password **/	
	function validateAdmPassword(el) {
		var st = true;
		if(jQuery(el).val().indexOf(" ") >= 0){
			return false;
		}
		
		if (trim(jQuery(el).val()).length < pwAdmMinLenght ) {
			return false;
		}
		
		if (trim(jQuery(el).val()).length > pwAdmMaxLenght ) {
			return false;	
		}
		
		if (pwAdmBigChar == "Y") {
			var regularExpression = new RegExp(pwAdmFormatBigChar);
			st = regularExpression.test(trim(jQuery(el).val()));
			if (!st) return st;
		}
		
		if (pwAdmLitChar == "Y") {
			var regularExpression = new RegExp(pwAdmFormatLitChar);
			st = regularExpression.test(trim(jQuery(el).val()));
			if (!st) return st;
		}
		
		if (pwAdmNumChar == "Y") {
			var regularExpression = new RegExp(pwAdmFormatNumChar);
			st = regularExpression.test(trim(jQuery(el).val()));
			if (!st) return st;
		}
		
		if (pwAdmSpecialChar == "Y") {
			var specialString = pwAdmFormatSpecialChar;
			var str = jQuery(el).val();
			st = true;
			for (var i=0; i < specialString.length; i++) {
				if (str.indexOf(specialString.substring(i, i+1)) > -1) {
					 st = false;
				}
			}
			if (st) return false;
		}
		return true;
	}
	
	/*
	* validation account expiry date : ต้องมากว่าวันที่ปัจจุบัน
	* validation account expiry date > curent date
	*/
	function validateAccountExpiryDate(el){
		// ถ้ามากกาว่า เป็น true
		return checkGetherCurrentDate(trim(jQuery(el).val()));
			
	}

	/**
	 * validation carrier code [A-Z and 0-9]
	 * */
	function validateCarrierCode(el) {
		var regularExpression = new RegExp(input_carrier_code);
		return regularExpression.test(trim(jQuery(el).val().toUpperCase()));
	}

	/**
	 * validation family name user [A-Z]
	 * */
	function validateFamilyNameUser(el) {
		var regularExpression = new RegExp(input_family_name_user);
		return regularExpression.test(trim(jQuery(el).val().toUpperCase()));
	}

	/**
	 * validation given name(s) user [A-Z or (space)]
	 * */
	function validateGivenNameUser(el) {
		var regularExpression = new RegExp(input_given_name_user);
		return regularExpression.test(trim(jQuery(el).val().toUpperCase()));
	}

	/**
	 * validation family name pax [A-Z]
	 * */
	function validateFamilyNamePax(el) {
		var regularExpression = new RegExp(input_family_name_pax);
		return regularExpression.test(trim(jQuery(el).val().toUpperCase()));
	}

	/**
	 * validation given name(s) pax [A-Z or (space)]
	 * */
	function validateGivenNamePax(el) {
		var regularExpression = new RegExp(input_given_name_pax);
		return regularExpression.test(trim(jQuery(el).val().toUpperCase()));
	}

	/**
	 * validation telephone contract [0-9 or +]
	 * */
	function validateTelephoneContact(el) {
		var regularExpression = new RegExp(input_telephone_contact);
		
		// alert(regularExpression.test(trim(jQuery(el).val())));
		if (regularExpression.test(trim(jQuery(el).val())) == true) {
			// ตรวจสอบกรณีมีการระบุเครื่องหมาย + ต้องอยู่ตัวแรก
			var count = (trim(jQuery(el).val()).match(/[+]/g) || []).length;
			// alert(count);
			if (count <= 1 && trim(jQuery(el).val()).indexOf('+') <= 0 ) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;			
		}
	}

	/**
	 * validation aircraft call sign [A-Z and 0-9]
	 * */
	function validateAircraftCallsign(el) {
		var regularExpression = new RegExp(input_aircraft_callsign);
		return regularExpression.test(trim(jQuery(el).val().toUpperCase()));
	}

	/**
	 * validation service number [A-Z and 0-9]
	 * */
	function validateServiceNumber(el) {
		var regularExpression = new RegExp(input_service_number);
		return regularExpression.test(trim(jQuery(el).val().toUpperCase()));
	}

	/**
	 * validation flight number [A-Z and 0-9]
	 * */
	function validateFlightNumber(el) {
		var regularExpression = new RegExp(input_flight_number);
		return regularExpression.test(trim(jQuery(el).val().toUpperCase()));
	}

	/**
	 * check min char aircraft call sign 3 to 8
	 * */
	function validateLengthInputAircraftCallsign(el) {
		if (trim(jQuery(el).val().toUpperCase()).length < min_aircraft_callsign || trim(jQuery(el).val().toUpperCase()).length > max_aircraft_callsign) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * check min char service number 3 to 8
	 * */
	function validateLengthInputServiceNumber(el) {
		if (trim(jQuery(el).val().toUpperCase()).length < min_service_number || trim(jQuery(el).val().toUpperCase()).length > max_service_number) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * check min char flight number 3 to 8
	 * */
	function validateLengthInputFlightNumber(el) {
		if (trim(jQuery(el).val().toUpperCase()).length < min_flight_number || trim(jQuery(el).val().toUpperCase()).length > max_flight_number) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * validation user id [A-Z, a-z or 0-9]
	 * */
	function validateUserId(el) {
		var regularExpression = new RegExp(input_user_id);
		return regularExpression.test(trim(jQuery(el).val().toUpperCase()));
	}

	/**
	 * validation document number [A-Z, 0-9]
	 * */
	function validateDocumentNumber(el) {
		var regularExpression = new RegExp(input_document_number);
		return regularExpression.test(trim(jQuery(el).val().toUpperCase()));
	}

	/**
	 * validation port [A-Z]
	 * */
	function validatePort(el) {
		var regularExpression = new RegExp(input_port);
		return regularExpression.test(trim(jQuery(el).val().toUpperCase()));
	}

	/**
	 * validation facsimile number [0-9, +]
	 * */
	function validateFacsimileNumber(el) {
		var regularExpression = new RegExp(input_facsimile_number);
		if (regularExpression.test(trim(jQuery(el).val())) == true) {
			// ตรวจสอบกรณีมีการระบุเครื่องหมาย + ต้องอยู่ตัวแรก
			var count = (trim(jQuery(el).val()).match(/[+]/g) || []).length;
			if (count <= 1 && trim(jQuery(el).val()).indexOf('+') <= 0 ) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;			
		}
	}

	/**
	 * validation ext [0-9]
	 * */
	function validateExt(el) {
		var regularExpression = new RegExp(input_ext);
		return regularExpression.test(trim(jQuery(el).val().toUpperCase()));
	}
	
	/**
	 * validation reservation system code [A-Z, 0-9]
	 * */
	function validateReservationSysCode(el) {
		var regularExpression = new RegExp(input_reservation_sys_code);
		return regularExpression.test(trim(jQuery(el).val().toUpperCase()));
	}

	/**
	 * validation record locator [A-Z, 0-9]
	 * */
	function validateRecordLocator(el) {
		var regularExpression = new RegExp(input_record_locator);
		return regularExpression.test(trim(jQuery(el).val().toUpperCase()));
	}

	/**
	 * validation date of birth < current date
	 * */
	function validateDateOfBirth(el) {
		if(trim(jQuery(el).val()) != "") {
			var arrDob = trim(jQuery(el).val().toUpperCase()).split("/");
			var dob = new Date(arrDob[2], parseInt(arrDob[1]) - 1, arrDob[0]);
			
			var q = new Date();
			var m = q.getMonth();
			var d = q.getDate();
			var y = q.getFullYear();
			var currentDate = new Date(y,m,d);
			
			if(dob > currentDate) {
			    return false;
			} else {
			    return true;
			}
		}
	}
	
	var regularExpChar = new RegExp("^[A-Z]+$");
	var regularExpNumber = new RegExp("^[0-9]+$");
	function validateFlightNumberFormat(el) {
		// (X)NNNN(X) 
		// X: Optional
		var regularExpression;
		var val = trim(jQuery(el).val().toUpperCase())
		if (val.length < 3 || val.length > 8) {
			return false;
		} else {
			// 1. ตรวจสอบ carrier code 2 ตัวแรกต้องเป็นตัวอักษร
			var carrierCode = val.substr(0, 2);
			var flightNo = val.substr(2, val.length);
			var lenghtFlightNo = flightNo.length;
			
			if (regularExpChar.test(trim(carrierCode))) {
				// 1. ตรวจสอบ carrier code 2 ตัวแรกต้องเป็นตัวอักษร
				// alert(lenghtFlightNo);
				if (lenghtFlightNo == 6) {
					checkFlightLenght6 (flightNo)
				} else if (lenghtFlightNo == 5) {
					checkFlightLenght5 (flightNo, lenghtFlightNo);
				} else if (lenghtFlightNo == 4) {
					checkFlightLenght4 (flightNo, lenghtFlightNo);
				} else if (lenghtFlightNo == 3) {
					checkFlightLenght3 (flightNo, lenghtFlightNo);
				} else if (lenghtFlightNo == 2) {
					checkFlightLenght2 (flightNo, lenghtFlightNo);
				} else if (lenghtFlightNo == 1) {
					// alert(flightNo);
					if (regularExpNumber.test(trim(flightNo))) {
						// กรณีที่เป็นตัวเลขทั้งหมด
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}
	
	function checkChar (val) {
		if (regularExpChar.test(trim(val))) {
			return true;
		} else {
			return false;
		}
	}
	
	function checkNum (val) {
		if (regularExpNumber.test(trim(val))) {
			return true;
		} else {
			return false;
		}
	}

	function checkFlightLenght2 (flightNo, lenghtFlightNo) {
		if (regularExpNumber.test(trim(flightNo))) {
			// กรณีที่เป็นตัวเลขทั้งหมด
			return true;
		} else if (checkChar(flightNo.charAt(0))) { // ตรวจสอบ ขึ้นต้นด้วยตัวอักษร?
			// ตรวจสอบ ที่เหลือเป็นตัวเลข?
			if (!checkNum(flightNo.substr(1, 1))) {
				return false;
			} else {
				return true;
			}
		} else if (checkChar(flightNo.charAt(lenghtFlightNo-1))) { // ตรวจสอบ ลงท้ายด้วยตัวอักษร?
			// ตรวจสอบ ที่เหลือเป็นตัวเลข?
			if (!checkNum(flightNo.substr(0, 1))) {
				return false;
			} else {
				return true;
			}		
		} else {
			return false;
		}
	}

	function checkFlightLenght3 (flightNo, lenghtFlightNo) {
		if (checkChar(flightNo.charAt(0)) && checkChar(flightNo.charAt(lenghtFlightNo-1))) {
			// ตรวจสอบ ที่เหลือเป็นตัวเลข?
			if (!checkNum(flightNo.substr(1, 1))) {
				return false;
			} else {
				return true;
			}
		} else if (regularExpNumber.test(trim(flightNo))) {
			// กรณีที่เป็นตัวเลขทั้งหมด
			return true;
		} else if (checkChar(flightNo.charAt(0))) { // ตรวจสอบ ขึ้นต้นด้วยตัวอักษร?
			// ตรวจสอบ ที่เหลือเป็นตัวเลข?
			if (!checkNum(flightNo.substr(1, 2))) {
				return false;
			} else {
				return true;
			}
		} else if (checkChar(flightNo.charAt(lenghtFlightNo-1))) { // ตรวจสอบ ลงท้ายด้วยตัวอักษร?
			// ตรวจสอบ ที่เหลือเป็นตัวเลข?
			if (!checkNum(flightNo.substr(0, 2))) {
				return false;
			} else {
				return true;
			}		
		} else {
			return false;
		}
	}

	function checkFlightLenght4 (flightNo, lenghtFlightNo) {
		if (checkChar(flightNo.charAt(0)) && checkChar(flightNo.charAt(lenghtFlightNo-1))) {
			// ตรวจสอบ ที่เหลือเป็นตัวเลข?
			if (!checkNum(flightNo.substr(1, 2))) {
				return false;
			} else {
				return true;
			}
		} else if (regularExpNumber.test(trim(flightNo))) {
			// กรณีที่เป็นตัวเลขทั้งหมด
			return true;
		} else if (checkChar(flightNo.charAt(0))) { // ตรวจสอบ ขึ้นต้นด้วยตัวอักษร?
			// ตรวจสอบ ที่เหลือเป็นตัวเลข?
			if (!checkNum(flightNo.substr(1, 3))) {
				return false;
			} else {
				return true;
			}
		} else if (checkChar(flightNo.charAt(lenghtFlightNo-1))) { // ตรวจสอบ ลงท้ายด้วยตัวอักษร?
			// ตรวจสอบ ที่เหลือเป็นตัวเลข?
			if (!checkNum(flightNo.substr(0, 3))) {
				return false;
			} else {
				return true;
			}		
		} else {
			return false;
		}
	}

	function checkFlightLenght5 (flightNo, lenghtFlightNo) {
		if (checkChar(flightNo.charAt(0)) && checkChar(flightNo.charAt(lenghtFlightNo-1))) {
			// ตรวจสอบ ที่เหลือเป็นตัวเลข?
			if (!checkNum(flightNo.substr(1, 3))) {
				return false;
			} else {
				return true;
			}
		} else if (checkChar(flightNo.charAt(0))) { // ตรวจสอบ ขึ้นต้นด้วยตัวอักษร?
			// ตรวจสอบ ที่เหลือเป็นตัวเลข?
			if (!checkNum(flightNo.substr(1, 4))) {
				return false;
			} else {
				return true;
			}
		} else if (checkChar(flightNo.charAt(lenghtFlightNo-1))) { // ตรวจสอบ ลงท้ายด้วยตัวอักษร?
			// ตรวจสอบ ที่เหลือเป็นตัวเลข?
			if (!checkNum(flightNo.substr(0, 4))) {
				return false;
			} else {
				return true;
			}		
		} else {
			return false;
		}
	}
	
	function checkFlightLenght6 (flightNo) {
		// ตรวจสอบ ตัวแรกเป็นตัวอักษร
		if (!checkChar(flightNo.charAt(0))) {
			return false;
		}
		
		// ตรวจสอบ ตัวสุดท้ายเป็นตัวอักษร
		if (!checkChar(flightNo.charAt(5))) {
			return false;
		}
		
		// ตรวจสอบ ส่วนกลางเป็นตัวเลข
		if (!checkNum(flightNo.substr(1, 4))) {
			return false;
		}
	}
	
	function validateFlightFormatNumber(el) {
		// (X)NNNN(X) 
		// X: Optional
		var regularExpression;
		var val = trim(jQuery(el).val().toUpperCase())
		// if (val.length < 4 || val.length > 6) {
		if (val.length < 1 || val.length > 6) {
			return false;
		} else {
			// 1. ตรวจสอบ carrier code 2 ตัวแรกต้องเป็นตัวอักษร
			var flightNo = val;
			var lenghtFlightNo = flightNo.length;
			if (lenghtFlightNo == 6) {
				checkFlightLenght6 (flightNo)
			} else if (lenghtFlightNo == 5) {
				checkFlightLenght5 (flightNo, lenghtFlightNo);
			} else if (lenghtFlightNo == 4) {
				checkFlightLenght4 (flightNo, lenghtFlightNo);
			} else if (lenghtFlightNo == 3) {
				checkFlightLenght3 (flightNo, lenghtFlightNo);
			} else if (lenghtFlightNo == 2) {
				checkFlightLenght2 (flightNo, lenghtFlightNo);
			} else if (lenghtFlightNo == 1) {
				if (regularExpNumber.test(trim(flightNo))) {
					// กรณีที่เป็นตัวเลขทั้งหมด
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}
	
	/**
	 * validation air carrier code [A-Z and 0-9 and lenght 2-2]
	 * */
	function validateAirCarrierCode(el) {
		if (trim(jQuery(el).val().toUpperCase()).length < min_air_carier_code || trim(jQuery(el).val().toUpperCase()).length > max_air_carier_code) {
			return false;
		} else {
			return validateCarrierCode(el);
		}
	}
	
	/**
	 * validation general aviation carrier code [A-Z and 0-9 and lenght 2-3]
	 * */
	function validateGeneralAviationCarrierCode(el) {
		if (trim(jQuery(el).val().toUpperCase()).length < min_general_aviation_carrier_code || trim(jQuery(el).val().toUpperCase()).length > max_general_aviation_carrier_code) {
			return false;
		} else {
			return validateCarrierCode(el);
		}
	}
	
	/**
	 * validation airport code IATA [A-Z]
	 * */
	function validateAirportCodeIata(el) {
		var regularExpression = new RegExp(input_airport_code_iata);
		return regularExpression.test(trim(jQuery(el).val().toUpperCase()));
	}

	/**
	 * validation input number [0-9]
	 * */
	function validateInputNumber(el) {
		var regularExpression = new RegExp(input_number);
		return regularExpression.test(jQuery(el).val().toUpperCase());
	}
	
	function setMessageValid(type, msg, obj) {
		if (type == 'part') {
			msgLabel = msg;
			obj.nextAll(".msgLabel").html("<font color='red'><b>" + msgLabel + "</b></font>");
		} else if (type == 'sum') {
			obj.addClass("border_select");
		} else {
			// console.log(msgLabel.indexOf(msg));
			if (msgLabel.indexOf(msg) == -1) {
				msgLabel += msg + "<br/>";
			}
			
			if (jQuery(obj).css("display") == "none" && jQuery(obj).prop("tagName") == "SELECT") {
				jQuery(obj).next().addClass("border_select");
			} else if ((jQuery(obj).attr('type') == 'radio') || (jQuery(obj).attr('type') == 'checkbox')) {
				jQuery(obj).addClass("border_select");
				if (jQuery(obj).parent().attr("class") == "requireGroup") {
					jQuery(obj).parent().addClass("border_select");
				}
			} else if (jQuery(obj).css("display") == "none" && jQuery(obj).prop("tagName") == "INPUT") {
				jQuery(obj).next().addClass("border_select");
			} else {
				obj.addClass("border_select");
			}
		}
		
		if (objFocus == undefined) {
			if (jQuery(obj).css("display") == "none" && jQuery(obj).prop("tagName") == "SELECT") {
				objFocus = jQuery(obj).next();
			} else {
				objFocus = obj;
			}
		}
	}
</script>