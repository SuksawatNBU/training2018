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
			<b>Tooltip</b><b>&emsp;</b> คือสิ่งที่จะแสดงคำอธิบายเพิ่มเติมให้กับผู้ใช้ถึงการทำงานของ component หรือข้อมูลที่ต้องกรอก
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
					<p>Input Text</p>
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					<p>Image</p>
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					<p>Link</p>
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
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