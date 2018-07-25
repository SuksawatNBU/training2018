<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<script type="text/javascript">
		function sf(){
		    jQuery("#countryId").autocompletezAjax([{		// UI มาตรฐาน
                url: "<s:url value='/countrySelectItemServlet'/>",  	// โหลด Country Name ผ่าน servlet
                defaultKey: "",     // กำหนดค่า key ตัวแรกของ Autocomplete
                defaultValue: "",    // กำหนดค่า value ตัวแรกของ Autocomplete
                requireInput: true,  // กำหนดให้เป็น Require field
                clearForm: true, // กำหนดค่า สำหรับการ clear value เมื่อมี event ที่ปุ่ม Clear
                validName: "Country" // กำหนดค่า สำหรับการ validation
            }]);
		}
		
		function searchPage(){
			alert('go to search page');
		}
		
		function clearFormCallBack() {
			alert('clear form call back');
		}
		
		function backPage(){
			alert('go to back page');
		}
	</script>
</head>
<body>
<s:form cssClass="margin-zero" name="template">	
	<table class="FORM">
		<tr style="">
			<td class="BORDER"></td>
			<td class="LABEL"></td>
			<td class="VALUE"></td>
			<td class="BORDER"></td>
		</tr>
		<tr>
			<td class="BORDER"></td>
			<td class="LABEL"><s:text name="Carrier code"/><em>*</em></td>
			<td class="VALUE">
				<s:textfield name="carrierCode" cssClass="requireInput input_carrier_code clearform" style="width: 200px;" validName="Carrier code" />
				<span class="msgLabel"></span>
			</td>
			<td class="BORDER"></td>
		</tr>
		<tr>
			<td class="BORDER"></td>
			<td class="LABEL"><s:text name="Family name"/><em>*</em></td>
			<td class="VALUE">
				<s:textfield name="familyName" cssClass="requireInput input_family_name clearform" style="width: 200px;" validName="Family name" />
				<span class="msgLabel"></span>
			</td>
			<td class="BORDER"></td>
		</tr>
		<tr>
			<td class="BORDER"></td>
			<td class="LABEL"><s:text name="Given name"/><em>*</em></td>
			<td class="VALUE">
				<s:textfield name="givenName" cssClass="input_given_name clearform" style="width: 200px;" validName="Given name" />
				<span class="msgLabel"></span>
			</td>
			<td class="BORDER"></td>
		</tr>
		<tr>
			<td class="BORDER"></td>
			<td class="LABEL"><s:text name="Password"/><em>*</em></td>
			<td class="VALUE">
				<s:textfield name="password" cssClass="requireInput input_password_format clearform" style="width: 200px;" validName="Password" />
				<span class="msgLabel"></span>
			</td>
			<td class="BORDER"></td>
		</tr>
		<tr>
			<td class="BORDER"></td>
			<td class="LABEL"><s:text name="Login type"/><em>*</em></td>
			<td class="VALUE">
				<s:select
					id="loginType"
					name="loginType"
					list="lstLoginTypeSelectItem"
					headerKey=""
					headerValue=""
					listKey="key"
					listValue="value"
					style="width: 200px;"
					cssClass="requireInput clearform"
					validName="Login type" />
				<span class="msgLabel"></span>
			</td>
			<td class="BORDER"></td>
		</tr>
		<tr>
			<td class="BORDER"></td>
			<td class="LABEL"><s:text name="Email"/><em>*</em></td>
			<td class="VALUE">
				<s:textfield name="email" cssClass="requireInput input_email clearform" style="width: 200px;" validName="Email" />
				<span class="msgLabel"></span>
			</td>
			<td class="BORDER"></td>
		</tr>
		<tr>
			<td class="BORDER"></td>
			<td class="LABEL"><s:text name="Telephone"/><em>*</em></td>
			<td class="VALUE">
				<s:textfield name="telephone" cssClass="requireInput input_telephone clearform" style="width: 200px;" validName="Telephone" />
				<span class="msgLabel"></span>
			</td>
			<td class="BORDER"></td>
		</tr>
		<tr>
			<td class="BORDER"></td>
			<td class="LABEL"><s:text name="Date"/><em>*</em></td>
			<td class="VALUE">
				<s:textfield name="date" id="date" cssClass="requireInput input_datepicker clearform" style="width: 200px;" validName="Date" />
				<span class="msgLabel"></span>
			</td>
			<td class="BORDER"></td>
		</tr>
		<tr>
			<td class="BORDER"></td>
			<td class="LABEL"><s:text name="Time"/><em>*</em></td>
			<td class="VALUE">
				<s:textfield name="time" id="time" cssClass="requireInput input_timeformat clearform" style="width: 200px;" validName="Time" />
				<span class="msgLabel"></span>
			</td>
			<td class="BORDER"></td>
		</tr>
		<tr>
			<td class="BORDER"></td>
			<td class="LABEL"><s:text name="Country"/><em>*</em></td>
			<td class="VALUE">
				<s:textfield id="countryId" name="countryId" code-of="countryId_autocomplete" />
				<s:textfield id="countryDesc" name="countryDesc" text-of="countryId_autocomplete" />
				<span class="msgLabel"></span>
			</td>
			<td class="BORDER"></td>
		</tr>
		<tr>
			<td class="BORDER"></td>
			<td class="LABEL"><s:text name="Hobby"/><em>*</em></td>
			<td class="VALUE">
				<div id="flagHobbyGroupId" class="requireGroup" validName="Hobby">
	           		<input type="checkbox" name="flagHobby" id="readBook" class="requireInput" value="A"/><em>&nbsp;&nbsp;</em>อ่านหนังสือ
				    <input type="checkbox" name="flagHobby" id="movie" class="requireInput" value="B"/><em>&nbsp;&nbsp;</em>ดูหนัง
				    <input type="checkbox" name="flagHobby" id="listenMusic" class="requireInput" value="C"/><em>&nbsp;&nbsp;</em>ฟังเพลง
				    <input type="checkbox" name="flagHobby" id="sport" class="requireInput" value="D"/><em>&nbsp;&nbsp;</em>เล่นกีฬา
				</div>
				<span class="msgLabel"></span>
			</td>
			<td class="BORDER"></td>
		</tr>
		<tr>
			<td class="BORDER"></td>
			<td class="LABEL"><s:text name="Gender"/><em>*</em></td>
			<td class="VALUE">
				<div id="flagGenderGroupId" class="requireGroup" validName="Gender">
	           		<input type="radio" name="flagGender" id="Male" class="requireInput" value="M"/><em>&nbsp;&nbsp;</em>เพศชาย
				    <input type="radio" name="flagGender" id="Femail" class="requireInput" value="F"/><em>&nbsp;&nbsp;</em>เพศหญิง
				</div>
				<span class="msgLabel"></span>
			</td>
			<td class="BORDER"></td>
		</tr>
	</table>
	    
	<s:include value="/jsp/template/button.jsp">
		<s:param name="buttonType" value="%{'search'}" />
	</s:include>
	<s:include value="/jsp/template/javascript-validpasswordformat.jsp"/>
</s:form>
</body>
</html>