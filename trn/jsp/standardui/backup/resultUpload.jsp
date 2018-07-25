<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<script type="text/javascript" src="/struts2/js/plupload/js/plupload.full.min.js"></script>
<script type="text/javascript" src="/struts2/js/plupload/js/jquery.ui.plupload/jquery.ui.multiPlupload.js"></script>
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
			if(!validateAll()){ 
		        return false;
		    }
		}
		
		function callbackUpload(obj, file){
			console.log("callbackUpload");
			console.log(obj);
			console.log(file);
			
			jQuery("#upload_documentName").val(obj.fileUploadFileName);
			jQuery("#uploadFileName").val(obj.fileUploadFileName);
			jQuery("#uploadFileNameTmp").val(obj.fileUploadFileNameTmp);
			jQuery("#uploadContentType").val(obj.fileUploadContentType);
			jQuery("#uploadPath").val("<s:property value='@com.cct.trn.core.config.parameter.domain.ParameterConfig@getParameter().getAttachFile().getTmpPath()' />");
		}
		
	</script>
</head>
<body>
<s:form cssClass="margin-zero" name="template">
	<div class="CRITERIA CRITERIA_1280">
		<p>
			<b>Upload</b><b>&emsp;</b> คือการ upload file ด้วย plupload plugin เป็นอีกหนึ่งแนวทางในการพัฒนาโปรแกรมให้สามารถ upload file ต่างๆขึ้นมายัง server ได้
			โดยลักษณะเด่นของ plugin นี้ก็คือ design ที่ look and feel และ support บนหลายๆ browser อีกข้อที่โดดเด่นก็คือสามารถเลือกไฟล์ได้ครั้งละหลายๆไฟล์พร้อมกัน การ upload file ด้วย plupload มี 3 แบบด้วยกัน 
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
					Single file upload
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
					<div id="divUploadResult">
						<div id='container' class="RESULT">
							<span class="Object">
		                    	<input type="text" id="upload_documentName" disabled="disabled" class="txt" />
			                    <input type="button" 
			                    	id="pickfileUpload" 
			                    	value="Browse" 
			                    	validName="Upload file" 
			                    	validFile="uploadFileName"
			                    	class="requireFill"
			                    	style="margin-bottom: 3px;" />
		                    </span>
						</div>
					</div>
					
					<input type="text" id="uploadContentType" value="" />
					<input type="text" id="uploadFileName" name="uploadFileName" value="" />
					<input type="text" id="uploadFileNameTmp" value="" />
					<input type="text" id="uploadPath" value="" />
					
					<s:set var="divresult" value='{"divUploadResult"}'/>
					<s:url var="urlUpload" value='/jsp/standardui/browseWithoutThumbnailMiscellaneous.action' /> 
					<s:set var="filters" value='%{"jpg,JPG,jpeg,JPEG,png,PNG,gif,GIF,tif,TIF,pdf,doc,docx,xls,xlsx"}'/>
					<s:set var="properties" value='#{"maxFileSize":1}'/> 
					<s:set var="btnPickfiles" value='%{"pickfileUpload"}'/>
					<s:set var="callback" value='%{"callbackUpload"}'/> 
					<s:include value="/jsp/template/upload.jsp"/>
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Multiple file upload
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Custom file upload
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