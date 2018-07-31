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
		
		function validate() {
			alert("validate")
			if(!validateAll()){ 
		        return false;
		    }
		}
		
		function searchPage() {
			alert("searchPage")
		}
		
		function clearPage() {
			alert("clearPage")
		}
		
	</script>
</head>
<body>
<s:form cssClass="margin-zero" name="standardui">
	<div class="CRITERIA CRITERIA_1280">
		<p>
			<b>Button</b><b>&emsp;</b> สามารถแยกได้เป็น 3 ลัษณะ คือ 
		</p>
		<p>
			<b>1. Standard Button</b><b>&emsp;</b> คือ ชุดของปุ่มมาตรฐานที่พร้อมให้ใช้งาน โดยภายในจะมีการครอบสิทธ์การใช้งานอยู่ด้วย
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
					Search Button Type
				</td>
					<td style="width: 60%; padding-left: 10px;">
						<s:include value="/jsp/template/button.jsp">
						<s:param name="buttonType" value="%{'search, enable'}" />
					</s:include>
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Add Button Type
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Edit Button Type
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					View Button Type
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Report Button Type
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Print Button Type
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					SearchDialog Button Type
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					ChooseDialog Button Type
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
		</table>
		<p>
			<b>2. Custom Button</b><b>&emsp;</b> คือ ปุ่มเฉพาะที่มีการใช้งานไม่ตรงกับปุ่มมาตรฐาน
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
					Try you self
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
		</table>
		<p>
			<b>3. Predefine Button</b><b>&emsp;</b> คือ ปุ่มปุ่มที่ไม่ได้เป็นมาตรฐาน แต่มีการใช้งานร่วมกันจึงมีการสร้างไว้เป็นส่วนกลางเพื่อลดความซ้ำซ้อนของ code
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
					Try you self
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
		</table>
	</div>
	<s:include value="/jsp/template/button_predifine.jsp">
		<s:set var="buttonType" value='{"VALIDATEFORM", "Back", "Test"}'/>
		<s:set var="buttonEnable" value='{true, true, true}'/>
		<s:set var="buttonFunction" value='{ "validate", "backPage", "validate"}'/>
	</s:include>
</s:form>
</body>
</html>