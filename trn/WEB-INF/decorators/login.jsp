<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="Cache-control" content="no-cache, no-store, must-revalidate" />
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0" />		
		
		<title><s:text name="applicationName"/></title>
		<s:include value="/jsp/template/javascript.jsp"/>
		<s:include value="/jsp/template/css.jsp"/>
		<s:include value="/jsp/template/resize.jsp"/>
		<s:include value="/jsp/template/theme_style.jsp"/>
		
		<decorator:head/>
		
	</head>
	<body onload="sf();" class="margin-zero" style="overflow: hidden;">
		<table class="body body_1280" style="margin-left: auto; margin-right: auto;">
			<tr id="BODY_TR_BANNER">
				<td class="content"><%-- <s:include value="/jsp/template/banner.jsp"/> --%></td>
			</tr>
			<tr id="BODY_TR_HEADER">
				<td class="content"></td>
			</tr>
			<tr id="BODY_TR_CONTENT">
				<td id="BODY_TD_CONTENT" class="contentForm" >
					<div class="ui-state-hover" style="display: none;"></div>
					<div class="ui-state-default" style="display: none;"></div>
					<div class="ui-state-active" id="dood_color_ui_state_active" style="display: none;"></div>					
					<decorator:body/>
					<s:include value="/jsp/template/jquerytheme.jsp"/>
				</td>
			</tr>
			
		</table>
		<s:include value="/jsp/template/javascript-validate.jsp"/>
		<s:include value="/jsp/template/javascript-lasted.jsp"/>
		<s:include value="/jsp/template/footer.jsp"/>
		
		<script type="text/javascript">
			initLogin = "Y";
			var genarateHeight = jQuery(window).height() - (2 + jQuery("#BODY_TR_BANNER").height() + jQuery("#BODY_TR_HEADER").height());
			var currentHeight = jQuery("#BODY_TR_CONTENT").height();
			contentHeight = jQuery("#BODY_TR_CONTENT").height();
			if (currentHeight < genarateHeight) {
				
				jQuery("#BODY_TD_CONTENT").height(genarateHeight -80);
			}
		</script>
	</body>
</html>