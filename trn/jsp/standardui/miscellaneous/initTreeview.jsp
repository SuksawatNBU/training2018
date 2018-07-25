<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<script type="text/javascript">
		var ids = "";           //ตัวกลางในการวาด tree มีการตรวจสอบ ids ใน popup ซึ่งในที่นี้ต้องประกาศทิ้งไว้ด้วย var ids = ""
	    var yWidth = 800;       //จะต้องกำหนดความกว้าง และความสูงเอง ที่ javascript 
	    var yHeight = 560;      //จะต้องกำหนดความกว้าง และความสูงเอง ที่ javascript 
    
		function sf(){
			// insert code here
			initTree();     //Initiate treeview
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
		
		function setTreeType(type){ 
			var action = "<s:url value='/jsp/standardui/setTypeMiscellaneous.action' />";
			console.log(type);
			console.log(action);
			submitAction(action, "typeSelected", type);
		}
		
		/** function onclick for node inside */
	    function eventOnClick (id) {
	        var obj = json[id];
	    }
		
	</script>
</head>
<body>
<s:form cssClass="margin-zero" name="template">
	<div class="CRITERIA CRITERIA_1280">
		<p>
			<b>Treeview</b><b>&emsp;</b> เป็น UI ที่ใช้สำหรับวาด tree เป็น plugin หรือส่วนเสริมที่เข้ามาใน jQuery UI ในบทนี้ผู้เขียนขออธิบายเพียงแค่การนำไปใช้งานเพื่อให้ได้ tree แต่ละชนิดเท่านั้น ส่วนการใช้งานอื่นๆสามารถดูรายละเอียดเพิ่มเติมได้ที่ Dialog choose tree หรือ Dialog show tree
		</p>
		<br><b>ข้อจำกัดการใช้งาน :</b>
		<ol>
		  <li>จะต้องกำหนดความกว้าง และความสูงเอง ที่ javascript </li>
		  <li>ตัวกลางในการวาด tree มีการตรวจสอบ ids ใน popup ซึ่งในที่นี้ต้องประกาศทิ้งไว้ด้วย var ids = "";</li>
		  <li>ปัจจุบันยังไม่สามารถแสดง tree มากกว่า 1 แบบในหน้าเดียวกันได้</li>
		</ol>
    
    	<br>
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
					Tab
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input id="btnSpan" type="button" value="SPAN" onclick="setTreeType('SPAN');">
			    	<input id="btnImage" type="button" value="IMAGE" onclick="setTreeType('IMAGE');">
			    	<input id="btnCheckbox" type="button" value="CHECKBOX" onclick="setTreeType('CHECKBOX');">
			    	<s:hidden name="typeSelected"/>
			    	
					<div id="divTreeView" style="height: 600px">
						<s:set var="url" value='{"/jsp/standardui/searchTreeviewMiscellaneous.action"}'/>
						<s:set var="treeId" value='{"treeOperator"}'/>
						<s:set var="treeType" value='{typeSelected}'/>
						<s:set var="event" value='{"eventOnClick"}'/> <!-- even onclick last level of each node. -->
						
						<!-- include tree template -->
						<s:include value="/jsp/template/tree.jsp"/>
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