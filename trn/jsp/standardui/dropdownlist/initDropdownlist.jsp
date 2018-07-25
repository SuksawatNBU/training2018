<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<script type="text/javascript">
		function sf(){
			// insert code here
			// เรียกใช้งาน Dropdownlist widget สำหรับสร้าง Dropdownlist
            jQuery("#dropdownlistNormal").dropdownlist([{   // UI มาตรฐาน
                url: "<s:url value='/qaUserAutoSelectItemServlet'/>", // กำหนดให้โหลดจังหวัดผ่าน servlet   
                defaultKey: "",     // กำหนดค่า key ตัวแรกของ Dropdownlist
                defaultValue: ""    // กำหนดค่า value ตัวแรกของ Dropdownlist
            }]);
			
            jQuery("#dropdownlistRequire").dropdownlist([{   // UI มาตรฐาน
                url: "<s:url value='/qaUserAutoSelectItemServlet'/>", // กำหนดให้โหลดจังหวัดผ่าน servlet   
                defaultKey: "",     	// กำหนดค่า key ตัวแรกของ Dropdownlist
                defaultValue: "",    	// กำหนดค่า value ตัวแรกของ Dropdownlist
                requireInput: true  	// กำหนดให้เป็น Require field
            }]);
            
            jQuery("#dropdownlistDisable").dropdownlist([{ // Default disabled
                url: "<s:url value='/qaUserAutoSelectItemServlet'/>", // กำหนดให้โหลดจังหวัดผ่าน servlet   
                defaultKey: "",     // กำหนดค่า key ตัวแรกของ Dropdownlist
                defaultValue: "",   // กำหนดค่า value ตัวแรกของ Dropdownlist
                disabled: true      // กำหนดให้แสดงผลเป็น disabled 
            }]);
            
            
            jQuery("#project_id").dropdownlist(
                    [{  
                        url: "<s:url value='/projectSelectItemServlet'/>",  // กำหนดให้โหลดจังหวัดผ่าน servlet  
                        defaultKey: "",     // กำหนดค่า key ตัวแรกของ Dropdownlist
                        defaultValue: ""    // กำหนดค่า value ตัวแรกของ Dropdownlist
                    },{
                        inputModelId: 'system_id',
                        url: "<s:url value='/systemSelectItemServlet'/>",        // กำหนดให้โหลดอำเภอผ่าน servlet
                        postParamsId: {projectId: "project_id"},
                        defaultKey: "",     // กำหนดค่า key ตัวแรกของ Dropdownlist
                        defaultValue: ""    // กำหนดค่า value ตัวแรกของ Dropdownlist
                    }]
                );
            
            jQuery("#project_id2").dropdownlist([{ // Default disabled
                url: "<s:url value='/qaUserAutoSelectItemServlet'/>", // กำหนดให้โหลดจังหวัดผ่าน servlet   
                defaultKey: "",     // กำหนดค่า key ตัวแรกของ Dropdownlist
                defaultValue: "",   // กำหนดค่า value ตัวแรกของ Dropdownlist
            }]);
            
            jQuery("#project_id3").dropdownlist([{ // Default disabled
                url: "<s:url value='/qaUserAutoSelectItemServlet'/>", // กำหนดให้โหลดจังหวัดผ่าน servlet   
                defaultKey: "",     // กำหนดค่า key ตัวแรกของ Dropdownlist
                defaultValue: "",   // กำหนดค่า value ตัวแรกของ Dropdownlist
            }]);
		}

		function initMenu () {
			submitPage("<s:url value='/jsp/security/initMainpage.action' />");
		}
		
		function backPage () {
			submitPage("<s:url value='/jsp/standardui/initMiscellaneous.action' />");
		}
		
		function search() {
			// validate old version
// 			if(!validateAll()){ 
// 		        return false;
// 		    }
			
			// validate all version
			if(!validateFormInputAll()){ 
		        return false;
		    }
		}
		
		function getValue() {
			var value = jQuery('#project_id2').dropdownlist('dropdownlistValue', 0);
			console.log(value)
			
			jQuery("#project2Key").val(value);
		}
		
		function setValue() {
			jQuery('#user_province_code').dropdownlist('dropdownlistValue', 0, jQuery("#tmpProvince").val());
		}
		
	</script>
</head>
<body>
<s:form cssClass="margin-zero" name="standardui">
	<div class="CRITERIA CRITERIA_1280">
		<p>
			<b>Dropdownlist</b><b>&emsp;</b> คือการใช้งาน Dropdownlist ในรูปแบบต่างๆ เช่น การวาด, ทำ filter และอื่นๆ
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
					Standard
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<s:select list="lstAllUser" name="userCode" 
						headerKey="" 
						headerValue=""
						listKey="key"
						listValue="value"
						/>
				
					<br>
				
					<input type="text" id="dropdownlistNormal" name="dropdownlistNormal" />
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Require field
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input type="text" id="dropdownlistRequire" name="dropdownlistRequire" />
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Disable
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input type="text" id="dropdownlistDisable" name="dropdownlistDisable" />
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Filter
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input type="text" id="project_id" name="projectId" style="width: 180px;"/>
					<input type="text" id="system_id" name="systemId" style="width: 180px;"/>
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Get value
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<s:textfield name="project2Key" id="project2Key" />
					<s:hidden name="project2Value" id="project2Value" />
					<input type="text" id="project_id2" name="projectId2" style="width: 180px;"/>
					<input type="button" value="Get" onclick="getValue()">
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Set value
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input type="text" id="project_id3" name="projectId3" style="width: 180px;"/>
					<s:textfield name="project3Key" />
					<s:textfield name="project3Value" />
					<input type="button" value="Set" onclick="setValue()">
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Event beforeChange
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Event afterChange
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Radio
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<div id="userGroupId" class="requireGroup" validName="User Group">
						<s:iterator value="lstAllUser" status="status" var="result">
							<input type="radio" 
								name="radioUser" 
								value='<s:property value="#result.key" />' 
								id='radio-<s:property value="#status.index+1" />' 
								class="requireInput <s:property value="#cssClass"/>"
								/>
								
							<span class="margin-radio">
								<s:property value="#result.value" />
							</span>
							<br>
						</s:iterator>
						
						<s:hidden name="user.userCode" />
						<s:hidden name="user.userName" />
					</div>
				</td>
			</tr>
		</table>
	</div>
	<br>
	<s:include value="/jsp/template/button_predifine.jsp">
		<s:set var="buttonType" value='{"VALIDATEFORM", "Back"}'/>
		<s:set var="buttonEnable" value='{true, true}'/>
		<s:set var="buttonFunction" value='{ "search", "backPage"}'/>
	</s:include>
</s:form>
</body>
</html>