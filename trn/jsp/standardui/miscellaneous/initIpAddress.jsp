<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<script type="text/javascript">
		function sf(){
			// insert code here
			jQuery("#ipNormal").input_ipaddress();
			jQuery("#ipGet").input_ipaddress();
			jQuery("#ipSet").input_ipaddress();
			jQuery("#ipToggle").input_ipaddress();
			
			jQuery("#ipDisable").input_ipaddress();
			jQuery("#ipDisable").input_ipaddress("toDisabled");
// 			<input type="text" id="ipDisable" name="ipDisable" />
		}

		function getValue () {
			// insert code here
			var value = jQuery("#ipGet").input_ipaddress('ipaddressValue')
			console.log(value);
			jQuery("#resultValue").val(value);
		}
		
		function setValue () {
			// insert code here
			var value = jQuery('#newValue').val()
			console.log(value)
			jQuery("#ipSet").input_ipaddress('ipaddressValue', value);
		}
		
		function setEnable () {
			jQuery('#ipToggle').input_ipaddress('toEnabled');
		}
		
		function setDisable () {
			jQuery('#ipToggle').input_ipaddress('toDisabled');
		}
		
		function initMenu () {
			submitPage("<s:url value='/jsp/security/initMainpage.action' />");
		}
		
		function backPage () {
			submitPage("<s:url value='/jsp/standardui/initMiscellaneous.action' />");
		}
		
		function validate() {
			if(!validateAll()){ 
		        return false;
		    }
		}
		
	</script>
</head>
<body>
<s:form cssClass="margin-zero" name="standardui">
	<div class="CRITERIA CRITERIA_1280">
		<p>
			<b>Input ipaddress format</b><b>&emsp;</b> คือการใช้งาน Input ipaddress format
		</p>
		<table class="FORM" border="1">
			<thead style="background-color:#3383bb">
				<tr>
					<th style="width: 40%; padding-left: 10px; text-align: center;">
						<font color="white" size="3">
							<b>Standard</b>
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
					รูปแบบมาตรฐาน
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input type="text" id="ipNormal" name="ipNormal" />
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Require Input
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Disable
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
					<input type="text" id="ipDisable" name="ipDisable" />
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Get value
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input type="text" id="resultValue" />
					<input type="button" value="Get value" onclick="getValue();" />
					<em>&nbsp;</em>
					<input type="text" id="ipGet" name="ipGet" />
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Set value
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input type="text" id="newValue">
					<input type="button" value="Set value" onclick="setValue();" />
					<em>&nbsp;</em>
					<input type="text" id="ipSet" name="ipSet" />
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Toggle Enable/Disable
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input type="button" value="Enable" onclick="setEnable();" />
					<em>&nbsp;</em>
					<input type="button" value="Disable" onclick="setDisable();" />
					<input type="text" id="ipToggle" name="ipToggle" />
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