<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>

<script type="text/javascript">
	
	function sf() {
		
	}
	
</script>
</head>
<body>
	<s:form id="addEditViewForm" name="addEditViewForm" method="post" cssClass="margin-zero" >
	
		<!--------------------------------------- include --------------------------------------->
		<s:include value="/jsp/template/transaction.jsp" />
		<s:hidden name="criteria.criteriaKey" />
		<s:hidden name="P_CODE"/>
	    <s:hidden name="F_CODE"/>
	    <s:hidden name="page"/>
		<s:hidden name="object.id" />
		<s:token />
		<!--------------------------------------- include --------------------------------------->
			
	</s:form>
</body>
</html>