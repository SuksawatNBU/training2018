<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.opensymphony.xwork2.ActionContext"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="com.cct.common.CommonUser"%>
<%@ page import="com.cct.domain.Operator"%>
<%@ page import="util.web.MenuFullUtil"%>
<%@ page import="util.web.SessionUtil"%>

<%@ page import="com.cct.common.CommonAction"%>
<%@ page import="com.cct.domain.GlobalVariable"%>

<%
	boolean haveUser = false;
	String context = request.getContextPath();
	String P_CODE = request.getParameter("P_CODE");
	String menu = "";
	try {
		if (SessionUtil.get(CommonUser.DEFAULT_SESSION_ATTRIBUTE) != null) {
			CommonUser user = (CommonUser)SessionUtil.get(CommonUser.DEFAULT_SESSION_ATTRIBUTE);
			menu = new MenuFullUtil(context, user.getMapOperator()).genarateMenuBar();
			haveUser = true;
		}
	} catch(Exception e) {
		e.printStackTrace();
	}
%>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="<s:url value='/js/menufull/fullmenu.css' />">
<script type="text/javascript" src="<s:url value='/js/menufull/fullmenu.js' />"></script>
<script type="text/javascript">
	var maxShowMenu  =  "<s:property value='@com.cct.trn.core.config.parameter.domain.ParameterConfig@getParameter().getApplication().getMaxShowMenu()'/>";

	jQuery(document).ready(function() {
		maxShowMenu = 3;
		var tbl = "<table class='tableStyle'>"
			var newLine = 0;
			var flagCloseTr = false;
			jQuery("#menu > li ").each(function () {
				if (parseInt(newLine,10) == 0) {
					tbl += "<tr>";
				}
				
				tbl += "<td class='table-td'>";
				tbl += "<ul class='ui-corner-all ui-widget'>";
				tbl += "<li>";
				tbl += jQuery(this).html();
				tbl += "</li></ul>";
				tbl += "</td>";
				
				newLine++;
				 if (parseInt(newLine,10) >= parseInt(maxShowMenu,10)) {
					newLine = 0;
					flagCloseTr = true;
				}
				 
				if (flagCloseTr) {
					tbl += "</tr>";
					flagCloseTr = false;
				}
			});
		
			tbl += "</table>";
		
		//tableMenu
		jQuery("#tableMenu").html(tbl);
		jQuery('#flyout').fullmenu();
	});
</script>
</head>
<!-- menuFull menuFull-linear menuFull-columnar ui-menu ui-widget ui-widget-content ui-widget ui-state-default -->

<%if (haveUser) {%>
	<div>
		<a id="flyout" tabindex="0"  class="fg-button fg-button-icon-right ui-widget ui-corner-all ui-state-default" style="width: 50px;text-align: center;">
			<span id="ui-cilck-popup" class="ui-icon ui-icon-grip-diagonal-se" ></span>
			<s:text name="menu"/>&nbsp;
		</a>
	</div>
	
	<%=menu%>	
	<div id="tableMenu" class="menuFullTest ui-widget-content" style="display: none;"></div>		
<%} %>
	
</html>
