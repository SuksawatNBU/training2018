<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<script type="text/javascript">
		function sf(){
			// insert code here
		}

		function initMenu () {
			submitPage("<s:url value='/jsp/security/initMainpage.action' />");
		}
		
		function backPage () {
			submitPage("<s:url value='/jsp/standardui/initMiscellaneous.action' />");
		}
		
		function getDateValue () {
			// insert code here
		}
		
		function setDateValue () {
			// insert code here
		}
		
		function validate() {
			// validate old version
			if(!validateAll()){ 
		        return false;
		    }
			
			// validate all version
// 			if(!validateFormInputAll()){ 
// 		        return false;
// 		    }
		}
	</script>
</head>
<body>
<s:form cssClass="margin-zero" name="standardui">
	<div class="CRITERIA CRITERIA_1280">
		<p>
			<b>Input date format</b><b>&emsp;</b> คือการใช้งาน Input date format ในรูปแบบต่างๆ เช่น การกำหนด format วันที่, การใช้งานร่วมกับ datepicker และอื่นๆ
		</p>
		<table class="FORM" border="1">
			<thead style="background-color:#3383bb">
				<tr>
					<th style="width: 40%; padding-left: 10px; text-align: center;">
						<font color="white" size="3">
							<b>Predefine</b>
						</font>
					</th>
					<th style="width: 60%; padding-left: 10px; text-align: center;">
						<font color="white" size="3">
							<b>Result</b>
						</font>
					</th>
				</tr>
			</thead>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					รูปแบบมาตรฐาน DD/MM/YYYY
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
					<input type="text" id="inputdate_format1" name="inputdate.format1" class="input_datepicker"/>
				</td>
			</tr>
		</table>
		
		<br>
		
		<table class="FORM" border="1">
			<thead style="background-color:#3383bb">
				<tr>
					<th style="width: 40%; padding-left: 10px; text-align: center;">
						<font color="white" size="3">
							<b>Custom</b>
						</font>
					</th>
					<th style="width: 60%; padding-left: 10px; text-align: center;">
						<font color="white" size="3">
							<b>Result</b>
						</font>
					</th>
				</tr>
			</thead>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					<p>Input date format</p>
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					<p>Input date format with datepicker</p>
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					<p>Select a Date Range</p>
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					<p>Get date from input date format</p>
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input type="text" id="resultValue" />
					<input type="button" value="Get Date value" onclick="getDateValue();" />
					<em>&nbsp;</em>
					<input type="text" id="inputdate_format10" name="inputdate.format10" class="input_datepicker"/>
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					<p>Set date to input date format</p>
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input type="text" id="newValue">
					<input type="button" value="Set Date value" onclick="setDateValue();" />
					<em>&nbsp;</em>
					<input type="text" id="inputdate_format11" name="inputdate.format11" class="input_datepicker"/>
				</td>
			</tr>
		</table>
	</div>
	<br>
	<s:include value="/jsp/template/button_predifine.jsp">
		<s:set var="buttonType" value='{"VALIDATEFORM", "Back"}'/>
		<s:set var="buttonEnable" value='{true, true}'/>
		<s:set var="buttonFunction" value='{ "validate", "backPage"}'/>
	</s:include>
</s:form>
</body>
</html>