<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<s:url value='/jsp/component/style-component.css' />"/>
	<script type="text/javascript">
		
	
		
	function sf(){
		$( "#tabs" ).tabs();
	}
			
		
	</script>
	<style type="text/css">
		.globalTypColor{
			color: #0040FF;
		}
		
		.successColor{
			color: #088A08;
		}
		.unSuccessColor{
			color: #DF3A01;
		}
		.exCode{
			padding: 10px; 
			background-color: #F5DA81; 
			width: 95%; 
			border-radius: 5px; 
			margin-bottom: 20px;
		}
		.div6{
			width: 400px; 
			position: absolute; 
			background-color: #f9f9f7; 
			padding: 5px; 
			margin-top: -5px; 
			border-right: 1px solid #c1bda8;
		}
		
		pre {
    display: block;
    font-family: monospace;
    white-space: pre;
    margin: 1em 0;
}
	</style>
	
</head>
<body>
<s:form cssClass="margin-zero" name="">	
	<br>
		<a href="<%=request.getContextPath()%>/jsp/initComponent.action" style="padding-left: 20px"><img src="<s:url value='/images/icon/i_search.png' />" /><font color="blue" size="1">Goto page Component</font></a>
	<br>
	<br>
	
	<br>
	
	<div id="tabs">
		  <ul>
		    <li><a href="#tabs-1">Page Search</a></li>
		  </ul>
	
		<div id="tabs-1">
			<b>Page search : ข้อกำหนด</b><br>
		    	<ul>
		    		<li><font color="red"><b>READ HERE:</b>การกำหนดการวางโครงสร้าง Code JSP สำหรับหน้าค้นหา เพื่อให้รองรับ ที่มีขนาดตั้งแต่ 0 < 1300 px และ > 1300 px   </font></li><br> 
		    		<li><font color="red"><b>ส่วนการกำหนดเงื่อนไขการค้นหา</b></font> 
		    			<ol>กำหนด div ให้มี class="CRITERIA CRITERIA CRITERIA_1280"</ol>
		    			<ol> กำหนดให้แสดงกรอบ criteria <br><br>
		    				<div style="background-color: #C0C0C0"">
		    				&lt;div id="divCriteria" class="RESULT_BOX BORDER_COLOR STYLE_CRITERIA_1600" style="display: ;"&gt;<br>
								 &emsp;&emsp;&lt;div class="RESULT_BOX_TITAL"&gt;<br>
									  &emsp;&emsp;&emsp;&emsp;&lt;table class="TOTAL_RECORD"&gt;<br>
										 &emsp;&emsp; &emsp;&emsp;&lt;tr>&lt;td class="LEFT" style="width: 10%;"&gt;&lt;s:text name="criteria"/&gt;&lt;/td&gt;&lt;/tr&gt;<br>
									  &emsp;&emsp;&emsp;&emsp;&lt;/table><br>
								 &emsp;&emsp;&lt;/div&gt;<br>
								</div>
		    			</ol><br>
		    			
		    			<ol>กำหนด table class = "FORM"</ol>
		    			<ol>ภายใต้ Table กำหนด class ของ td ให้ td แรก และท้ายสุด td class ="BORDER"</ol><br>
		    		</li>
		    	</ul>
		    	<ul>
		    		<li><font color="red"><b>ส่วนการแสดงผลการค้นหา</b></font> 
		    			<ol>ตาม Framewor เดิม</ol><br>
		    			<ol><font color="green"> ตัวอย่างการใช้งาน  ระบบ managerService</font>
		    				 <p style="background-color: #C0C0C0"> file path : /jsp/servicechedule/manageservice/manageServiceSearch.jsp </p>
		    			</ol>
		    		</li>
		    	</ul>
		    	<br><img  src="<s:url value='/images/tutorial/criteria.png' /> "><br>
		    	<br><img  src="<s:url value='/images/tutorial/cp2.png' /> ">
		
		</div>
		
	
		<div style="padding-left: 110px; width: 900px; display: '';">
	   		<br>
	    	
	    	
	    	
	    	
	    </div>
</s:form>
</body>
</html>