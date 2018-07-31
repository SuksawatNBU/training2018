<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<style type="text/css">
	<s:include value="/css/autocomplete/autocomplete.css"/>
</style>

<script type="text/javascript">
	// กำหนด config ต่างๆ ของ autocomplate
	var autocompleteConfig = {
		contextPath: '<%=request.getContextPath()%>',
		msgShowAll: '<s:text name="clickInformation"/>',
		msgNotMatch: '<s:text name="notFound"/>'
	};

	<s:include value="/js/autocomplete/autocomplete.js"/>
	<s:include value="/js/autocomplete/autocomplete-ajax.js"/>	
</script>