<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">

	function sf() {
		
	}
	
</script>
</head>

<body>
	<s:form id="searchForm" name="searchForm" method="post" namespace="/jsp/package" action="initAction" cssClass="margin-zero" onsubmit="return false;">
	
	<!--------------------------------------- divSearchCriteria --------------------------------------->
	<div class="">
		<!-- code for search criteria -->
	</div>
	<!--------------------------------------- divSearchCriteria --------------------------------------->
	
	<!--------------------------------------- divResult --------------------------------------->
	<div class="">
		<!-- code for display table -->
   	</div>
   	<!--------------------------------------- divResult --------------------------------------->
   	
   	<!--------------------------------------- include --------------------------------------->
   	<s:include value="/jsp/template/hiddenSearchForDatatable.jsp" />
   	<s:hidden name="projects.id" />
   	<s:token />
   	<!--------------------------------------- include --------------------------------------->
	
	</s:form>
</body>
</html>