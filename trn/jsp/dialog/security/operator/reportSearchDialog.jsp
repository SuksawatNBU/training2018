<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<script type="text/javascript">

</script>

<s:set var="url" value='{"/jsp/dialog/searchReportDialog.action"}'/>
<s:set var="treeId" value='{"treeReportOperator"}'/>
<s:set var="treeType" value='{"CHECKBOX"}'/> <!-- SPAN/IMAGE/CHECKBOX -->
<s:set var="event" value=''/> <!-- even onclick last level of each node. -->
 
<!-- 2. ส่วนของการ include template tree -->
<s:include value="/jsp/template/tree.jsp"/>