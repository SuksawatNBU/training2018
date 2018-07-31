<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<script type="text/javascript">
		function sf(){
			// insert code here
			var jsonSuggestData = <s:property value="jsonSuggestData" escapeHtml="false"/>;
			console.log(jsonSuggestData);
			
			jQuery(".suggestionClient").ready(function(){
		        jQuery(".suggestionClient").suggestion({
		            source: jsonSuggestData,
		            minLength: 2,
		            delay: 500,
		            autoFocus: true,
		            select: function( event, ui ) {
		                console.log(ui.item);
		            }
		        });
		    });
			
			jQuery(".suggestion").ready(function(){
		        jQuery(".suggestion").suggestion({
		            source: "<s:url value='/suggestDataSelectItemServlet'/>",
		            minLength: 2,
		            delay: 500,
		            autoFocus: true,
		            select: function( event, ui ) {
		                console.log(ui.item);
		            }
		        });
		    });
		}
	
		function initMenu () {
			submitPage("<s:url value='/jsp/security/initMainpage.action' />");
		}
		
		function backPage () {
			submitPage("<s:url value='/jsp/standardui/initMiscellaneous.action' />");
		}
		
		function validate() {
			// validate old version
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
			<b>Suggestion</b><b>&emsp;</b> คือการใช้งาน UI ในรูปแบบคล้ายๆ textbox แต่เพิ่มการ filter ในระหว่างกรอกข้อมูลเพื่อให้รวดเร็วในการกรอกมากขึ้น
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
					<p>Suggestion (Client Filter)</p>
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input type="text" placeholder="กรุณากรอกเรื่องร้องเรียนที่ต้องการ" style="width: 200px;" class="suggestionClient">
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					<p>Suggestion (Server Filter)</p>
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input type="text" placeholder="กรุณากรอกเรื่องร้องเรียน" style="width: 200px;" class="suggestion">
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