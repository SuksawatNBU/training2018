<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<script type="text/javascript">
		function sf(){
			// insert code here
			jQuery("#progressbar").progressbar({
				value: true
			});
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
		
		function showProgress() {
		    progressbartext = jQuery("#progressbar");
		    progressbartext.progressbar("value", false);
		 
		    var params = {
		        progress : 0 //Initail progress value
		    };
		 
		    jQuery.ajax({
		        type: "POST",
		        url : "<s:url value='/jsp/standardui/runningProcessMiscellaneous.action'/>",
		        dataType : 'json',
		        global : false,
		        async: true,	//ไม่ให้ทำงานพร้อมกัน ทำ ajax ให้เสร็จก่อน ค่อยทำ java script
		        data: params,
		        success: function(data){
		            progressbartext.progressbar("value", true);
		        }
		    });
		}
	    
		
		
		
		
		
	    var intervalProgress1;
		function call1() {
			progressbartext = jQuery("#progressbar1");
			progressbartext.progressbartext( "value", 0 );
			intervalProgress1 = setInterval(function(){showProgress1();}, 100);
		}
		
	    function showProgress1(){
			var progress = 0;
			var flagprocess = true;
			
			progressbartext = jQuery("#progressbar1");
			var params = {
				progress : progressbartext.progressbartext( "value")
			};
	
			jQuery.ajax({
				type: "POST",
				url : "<s:url value='/jsp/standardui/runningProgressMiscellaneous.action'/>",
				dataType : 'json',
				async: true,  //ไม่ให้ทำงานพร้อมกัน      ทำ ajax ให้เสร็จก่อน ค่อยทำ java script
				data: params,
				global: false,
				success: function(data){
					progress = data.progress;
					flagprocess = data.flagProcess;
					if (flagprocess == false) {
						clearInterval(intervalProgress1);
					}
					progressbartext.progressbartext( "value", data.progress );
				}
			});
		}
	
		var intervalProgress2;
		function call2() {
			intervalProgress2 = setInterval(function(){showProgress2();}, 100);
		}
	
		function showProgress2(){
			var progress = 0;
			var flagprocess = true;
			progressbartext2 = jQuery("#progressbar2");
	
			var params = {
				progress : progressbartext2.progressbartext( "value")
			};
	
			jQuery.ajax({
				type: "POST",
				url : "<s:url value='/standardui/runningProgressMiscellaneous.action'/>",
				dataType : 'json',
				async: true,  //ไม่ให้ทำงานพร้อมกัน      ทำ ajax ให้เสร็จก่อน ค่อยทำ java script
				data: params,
				global: false,
				success: function(data){
					progress = data.progress;
					flagprocess = data.flagProcess;
					if (flagprocess == false) {
						clearInterval(intervalProgress2);
					}
					progressbartext2.progressbartext( "value", data.progress );
				}
			});
		}
	
	</script>
</head>
<body>
<s:form cssClass="margin-zero" name="template">
<div class="CRITERIA CRITERIA_1280">
		<p>
			<b>Progress bar</b><b>&emsp;</b> แสดงการ progress bar โดย ในที่นี้แบ่ง progress bar ออกได้เป็น 2 ประเภท คือ 
		</p>
		<ol>
			<li>Loading ProgressBar คือ Progress Bar แบบแสดงข้อมูล Load การทำงาน</li>
			<li>Percent ProgressBar คือ Progress Bar แบบแสดงข้อมูล Percent การทำงาน</li>
		</ol>
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
					Loading ProgressBar
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
					<div id="progressbar" class="progressbar_cct"></div>
					<br>
					<input type="button" onclick="showProgress();" value="Load Progress">
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					Percent ProgressBar
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<b>Insert code here</b>
					<!-- Insert code here -->
					<div id="progressbar1" class="progressbar_cct" ></div>
					<br>
					<input type="button" onclick="call1()" value="Load Progress">
			
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