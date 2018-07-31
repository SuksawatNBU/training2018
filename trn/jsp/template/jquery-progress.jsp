<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<style type="text/css">
	<s:include value="/css/jquery-progress.css"/>
</style>

<script type="text/javascript">
	var progressValue = {
		msgSuccess: '<s:text name="30006"/>'
	};
	
	<s:include value="/js/jquery/jquery-progress.js"/>
	jQuery( document ).ready(function() {
		// สร้าง progress bar ให้ auto จาก div ที่มี class .progressbar
		jQuery("div .progressbar_cct").progressbartext({});
	});
</script>