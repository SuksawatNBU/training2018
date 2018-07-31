<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<script type="text/javascript">
		function sf(){
			// insert code here
			jQuery(".accordion").accordion();
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
			<b>Accordion</b><b>&emsp;</b> คือ Widget สำหรับสร้างเมนูหรือเนื้อหา ที่สามารถซ่อน/แสดงได้ (Displays collapsible content panels for presenting information in a limited amount of space.)
		</p>
		<table class="FORM" border="1">
			<thead style="background-color:#3383bb">
				<tr>
					<th style="width: 40%; padding-left: 10px; text-align: center;">
						<font color="white" size="3">
							<b>Example</b>
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
					Accordion
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<div class="accordion">
						<h3>Header1</h3>
						<div>Detail1</div>
						<h3>Header2</h3>
						<div>Detail2</div>
						<h3>Header3</h3>
						<div>Detail3</div>
					</div>	
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