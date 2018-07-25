<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<script type="text/javascript">
		
	
		
	function sf(){
		
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
	</style>
	
</head>
<body>
<s:form cssClass="margin-zero" name="template">	
	<br>
		<a href="<%=request.getContextPath()%>/jsp/initComponent.action" style="padding-left: 20px"><img src="<s:url value='/images/icon/i_search.png' />" /><font color="blue" size="1">Goto page Component</font></a>
	<br>
	<br>
	    
	
	<br><br><br>
	<div style="width:1000px; margin-left: auto; margin-right: auto;">
	    <div class="exCode">
		</div>
	</div>
</s:form>
</body>
</html>