<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<script type="text/javascript">
		jQuery(document).ready(function(){
			// เรียกใช้งาน Autocomplete widget สำหรับสร้าง Autocomplete
            jQuery("#userCode").autocompletez([{  // UI มาตรฐาน   
                defaultKey: "",     // กำหนดค่า key ตัวแรกของ Autocomplete
                defaultValue: ""    // กำหนดค่า value ตัวแรกของ Autocomplete
            }]);
			
            jQuery("#projectCode").autocompletez([{  // UI มาตรฐาน   
                defaultKey: "",     // กำหนดค่า key ตัวแรกของ Autocomplete
                defaultValue: ""    // กำหนดค่า value ตัวแรกของ Autocomplete
            },{
                inputModelId: 'systemCode',    // id ของ input อำเภอ
                url: "<s:url value='/systemSelectItemServlet'/>",        // กำหนดให้โหลดอำเภอผ่าน servlet
                postParamsId: {projectId: "projectCode"},
                defaultKey: "",     // กำหนดค่า key ตัวแรกของ Autocomplete
                defaultValue: ""    // กำหนดค่า value ตัวแรกของ Autocomplete
            }]);
			
		});
		
		function sf(){
			
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
			<b>Autocomplete</b><b>&emsp;</b> คือการใช้งาน Autocomplete ในรูปแบบต่างๆ เช่น การวาด, ทำ filter และอื่นๆ
		</p>
		<table class="FORM" border="1">
			<thead style="background-color:#3383bb">
				<tr>
					<th style="width: 40%; padding-left: 10px; text-align: center;">
						<font color="white" size="3">
							<b>Simple</b>
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
					Standard
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<s:select list="lstUser"
						id="user_autocomplete"
						listKey="key"
						listValue="value"
						headerKey=""
						headerValue=""
						/>
					<s:hidden id="userCode" name="userCode" code-of="user_autocomplete" cssClass="requireInput"/>
					<s:hidden id="userDesc" name="userDesc" text-of="user_autocomplete"/>
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Require field
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
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Filter
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<s:select list="lstProject"
						id="project_autocomplete"
						listKey="key"
						listValue="value"
						headerKey=""
						headerValue=""
						/>
					<s:hidden id="projectCode" name="projectCode" code-of="project_autocomplete"/>
					<s:hidden id="projectDesc" name="projectDesc" text-of="project_autocomplete"/>
					
					<s:hidden id="systemCode" name="systemCode" code-of="system_autocomplete"/>
					<s:hidden id="systemDesc" name="systemDesc" text-of="system_autocomplete"/>
				</td>
			</tr>
		</table>
		<br>
		<p>
			<b>Autocomplete Ajax</b><b>&emsp;</b> คือการใช้งาน Autocomplete โดยผ่าน ajax ยิงไปที่ servlet servlet ในรูปแบบต่างๆ เช่น การวาด, ทำ filter และอื่นๆ
		</p>
		<table class="FORM" border="1">
			<thead style="background-color:#3383bb">
				<tr>
					<th style="width: 40%; padding-left: 10px; text-align: center;">
						<font color="white" size="3">
							<b>Simple</b>
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
					Standard
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Require field
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
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Filter
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