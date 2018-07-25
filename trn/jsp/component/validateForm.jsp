<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<table class="FORM">
	<tr style="">
		<td class="BORDER"></td>
		<td class="LABEL"></td>
		<td class="VALUE"></td>
		<td class="LABEL"></td>
		<td class="VALUE"></td>
		<td class="BORDER"></td>
	</tr>
	<tr class ="">
		<td class="BORDER"></td>
		<td class="LABEL"><s:text name="Carrier code"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield id="carrierCode2"  name="carrierCode2" 
				cssClass="requireInput clearform" style="width: 150px;" 
				validName="Carrier code" cp_validation="input_carrier_code" />
			<em>A-Z, 0-9</em>
		</td>
		<td class="LABEL"><s:text name="Aircraft call sign"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield id="aircraftCallSign"  name="aircraftCallSign" 
				cssClass="requireInput clearform" style="width: 150px;"
				validName="Aircraft call sign" cp_validation="input_format_flight_number" />
			<em>A-Z, 0-9</em>
		</td>
		<td class="BORDER"></td>
	</tr>
	<tr class ="">
		<td class="BORDER"></td>
		<td class="LABEL"><s:text name="Flight number"/>(open schedule)<em>*</em></td>
		<td class="VALUE">
			<s:textfield name="carrierCode" disabled="true" cssStyle="width:20px"/>
			<s:textfield id="flightnumber"  name="flightnumber" 
				cssClass="requireInput clearform" style="width: 150px;" 
				validName="Flight number" cp_validation="input_flight_format_number" />
			<em>(X)0-9(X)</em>
		</td>
		<td class="LABEL"><s:text name="Country"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield id="countId" name="countId" code-of="countId_autocomplete" />
			<s:textfield id="countDesc" name="countDesc" text-of="countId_autocomplete" />
		</td>
		<td class="BORDER"></td>
	</tr>
	<tr>
		<td class="BORDER"></td>
		<td class="LABEL"><s:text name="Family name"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield name="familyName" 
				cssClass="requireInput clearform" style="width: 150px;" 
				validName="Family name" cp_validation="input_family_name_user" />
			<em>A-Z</em>
		</td>
		<td class="LABEL"><s:text name="Given name"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield name="givenName"
				cssClass="requireInput clearform" style="width: 150px;"  
				validName="Given name" cp_validation="input_given_name_user" />
			<em>A-Z, (space)</em>
		</td>
		<td class="BORDER"></td>
	</tr>
	<tr>
		<td class="BORDER"></td>
		<td class="LABEL"><s:text name="User ID"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield name="userId"
				cssClass="requireInput clearform" style="width: 150px;"  
				validName="User ID" cp_validation="input_user_id" />
			<em>A-Z, 0-9</em>
		</td>
		<td class="LABEL"><s:text name="Password"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield name="password" 
				cssClass="requireInput clearform" style="width: 150px;" 
				validName="Password"/>
		</td>
		<td class="BORDER"></td>
	</tr>
	<tr>
		<td class="BORDER"></td>
		<td class="LABEL"><s:text name="Login type"/><em>*</em></td>
		<td class="VALUE">
			<s:select id="LoginType"
				list="lstLoginTypeSelectItem"
				headerKey=""
				headerValue=""
				listKey="key"
				listValue="value"
				style="width: 150px;"
				cssClass="requireInput clearform"
				validName="Login type" />
		</td>
		<td class="LABEL"><s:text name="Email"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield name="email" 
				cssClass="requireInput input_email clearform" style="width: 150px;" 
				validName="Email" />
		</td>
		<td class="BORDER"></td>
	</tr>
	<tr>
		<td class="BORDER"></td>
		<td class="LABEL"><s:text name="Telephone"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield name="telephone" 
				cssClass="requireInput clearform" style="width: 150px;" 
				validName="Telephone" cp_validation="input_telephone_contact" />
			<em>(+)0-9</em>
		</td>
		<td class="LABEL"><s:text name="Ext"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield name="ext" 
				cssClass="requireInput clearform" style="width: 150px;" 
				validName="Ext" cp_validation="input_ext" />
			<em>0-9</em>
		</td>
		<td class="BORDER"></td>
	</tr>
	<tr>
		<td class="BORDER"></td>
		<td class="LABEL"><s:text name="Date"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield id="date" name="date" 
				cssClass="requireInput input_datepicker clearform"  
				validName="Date"/>
			<s:textfield name="time"
				cssClass="requireInput input_timeformat clearform"  
				validName="Time"/>
		</td>
		<td class="LABEL"><s:text name="Date of birth"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield name="dob"
				cssClass="requireInput input_datepicker clearform"  
				validName="Date of birth"/>
		</td>
		<td class="BORDER"></td>
	</tr>
	<tr>
		<td class="BORDER"></td>
		<td class="LABEL"><s:text name="Country"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield id="countryId" name="countryId" code-of="countryId_autocomplete" />
			<s:textfield id="countryDesc" name="countryDesc" text-of="countryId_autocomplete" />
		</td>
		<td class="LABEL"><s:text name="Document number"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield name="documentNumber" 
				cssClass="requireInput clearform" style="width: 150px;" 
				validName="Document number" cp_validation="input_document_number" />
			<em>A-Z, 0-9</em>
		</td>
		<td class="BORDER"></td>
	</tr>
	<tr>
		<td class="BORDER"></td>
		<td class="LABEL"><s:text name="Facsimile number"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield name="facsimileNumber" 
				cssClass="requireInput clearform" style="width: 150px;" 
				validName="Facsimile number" cp_validation="input_facsimile_number" />
			<em>0-9,+</em>
		</td>
		<td class="LABEL"><s:text name="Port"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield name="port" 
				cssClass="requireInput clearform" style="width: 150px;" 
				validName="Port" cp_validation="input_port" />
			<em>A-Z</em>
		</td>
		<td class="BORDER"></td>
	</tr>
	<tr>
		<td class="BORDER"></td>
		<td class="LABEL"><s:text name="Reservation system code"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield name="reservationSystemCode" 
				cssClass="requireInput clearform" style="width: 150px;" 
				validName="Reservation system code" cp_validation="input_reservation_sys_code" />
			<em>A-Z, 0-9</em>
		</td>
		<td class="LABEL"><s:text name="Record locator"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield name="recordLocator" 
				cssClass="requireInput clearform" style="width: 150px;" 
				validName="Record locator" cp_validation="input_record_locator" />
			<em>A-Z, 0-9</em>
		</td>
		<td class="BORDER"></td>
	</tr>
	<tr>
		<td class="BORDER"></td>
		<td class="LABEL"><s:text name="Service number"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield 
				cssClass="requireInput clearform" style="width: 150px;" 
				validName="Service number" cp_validation="input_service_number" />
			<em>A-Z, 0-9</em>
		</td>
		<td class="LABEL"><s:text name="Family name (PAX)"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield 
				cssClass="requireInput clearform" style="width: 150px;" 
				validName="Family name(PAX)" cp_validation="input_family_name_pax" />
			<em>A-Z</em>
		</td>
		<td class="BORDER"></td>
	</tr>
	<tr>
		<td class="BORDER"></td>
		<td class="LABEL"><s:text name="Given name(s) (PAX)"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield 
				cssClass="requireInput clearform" style="width: 150px;" 
				validName="Given name(s) (PAX)" cp_validation="input_given_name_pax" />
			<em>A-Z, (space)</em>
		</td>
		<td class="LABEL"><s:text name="Nationality"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield 
				cssClass="requireInput clearform" style="width: 150px;" 
				validName="Nationality" cp_validation="input_nationality" />
		</td>
		<td class="BORDER"></td>
	</tr>
	<tr>
		<td class="BORDER"></td>
		<td class="LABEL"><s:text name="Issuing State"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield 
				cssClass="requireInput clearform" style="width: 150px;" 
				validName="Issuing State" cp_validation="input_issuing_state" />
		</td>
		<td class="LABEL"><s:text name="Country of birth"/><em>*</em></td>
		<td class="VALUE">
			<s:textfield 
				cssClass="requireInput clearform" style="width: 150px;" 
				validName="Country of birth" cp_validation="input_country_of_birth" />
		</td>
		<td class="BORDER"></td>
	</tr>
	<tr>
		<td class="BORDER"></td>
		<td class="LABEL"><s:text name="Hobby"/><em>*</em></td>
		<td class="VALUE">
			<div id="flagHobbyGroupId" class="requireGroup clearform" validName="Hobby" style="width: 300px;">
           		<input type="checkbox" name="flagHobby" id="readBook" class="requireInput" value="A"/><em>&nbsp;&nbsp;</em>อ่านหนังสือ
			    <input type="checkbox" name="flagHobby" id="movie" class="requireInput" value="B"/><em>&nbsp;&nbsp;</em>ดูหนัง
			    <input type="checkbox" name="flagHobby" id="listenMusic" class="requireInput" value="C"/><em>&nbsp;&nbsp;</em>ฟังเพลง
			    <input type="checkbox" name="flagHobby" id="sport" class="requireInput" value="D"/><em>&nbsp;&nbsp;</em>เล่นกีฬา
			</div>
		</td>
		<td class="LABEL"><s:text name="Gender"/><em>*</em></td>
		<td class="VALUE">
			<div id="flagGenderGroupId" class="requireGroup clearform" validName="Gender" style="width: 150px;">
           		<input type="radio" name="flagGender" id="Male" class="requireInput" value="M"/><em>&nbsp;&nbsp;</em>เพศชาย
			    <input type="radio" name="flagGender" id="Femail" class="requireInput" value="F"/><em>&nbsp;&nbsp;</em>เพศหญิง
			</div>
		</td>
		<td class="BORDER"></td>
	</tr>
	<tr>
		<td class="BORDER"></td>
		<td class="LABEL"><s:text name="Spinner 1"/></td>
		<td class="VALUE"><input id="spinner1" name="spinner1" validName="<s:text name="Spinner 1"/>"></td>
		<td class="LABEL"><s:text name="Spinner 2"/></td>
		<td class="VALUE"><input id="spinner2" name="spinner2" validName="<s:text name="Spinner 2"/>" cp_validation="input_spinner_check_minmax" validTo="spinner1"></td>
		<td class="BORDER"></td>
	</tr>
</table>

<s:include value="/jsp/template/button_predifine.jsp">
	<s:set var="buttonType" value='{"SEARCH", "CLEAR"}'/>
	<s:set var="buttonEnable" value='{true, true}'/>
	<s:set var="buttonFunction" value='{"valid:return:1", "clearForm"}'/>
</s:include>