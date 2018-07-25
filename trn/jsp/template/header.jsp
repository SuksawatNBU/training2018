<%@page import="util.log.LogUtil"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.cct.common.CommonAction"%>
<%@ page import="com.cct.common.CommonUser"%>
<%@ page import="com.cct.domain.GlobalVariable"%>
<%@ page import="com.cct.domain.Operator"%>
<%@ page import="util.web.SessionUtil"%>
<%@ page import="util.web.MenuUtil"%>

<%
	String htmlHeader = "";
	boolean haveUser = false;
	String F_CODE = request.getParameter("F_CODE");
	boolean isForceChangePassword = false;
	try {
		CommonUser user = new CommonAction(true).getUser();
// 		LogUtil.INITIAL.debug("User : " + user);
		if (user != null) {
			haveUser = true;
			htmlHeader = MenuUtil.searchLabel(user.getMapMenu(), F_CODE);
			
			// Force change password
			if (user.getForcePwChangeFlag().equalsIgnoreCase(GlobalVariable.FLAG_ACTIVE)) {
				isForceChangePassword = true;
			}
		}
		
	} catch (Exception ex) {
		//ex.printStackTrace();
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%if (haveUser)  {%>
	<%if (!isForceChangePassword)  {%>
		<table class="header" style="border: none;">
			<tr >
				<td class="left" style="width: 110px;">
					<s:include value="/jsp/template/app_menu_full.jsp"/>
				</td>
				<td class="left" style="width: 600px;" id="headerNavigate"><b><%=htmlHeader%></b></td>
				<td class="right" style="padding-right: 0px;"><s:include value="/jsp/template/profile_menu.jsp"></s:include></td>
			</tr>
		</table>
	<%} else { %>
		<table class="header">
			<tr >
				<td class="left" style="width: 610px; padding-left: 5px;">
				&nbsp;
			</td>
				<td class="right" style="padding-right: 0px;">
					&nbsp;
				</td>
			</tr>
		</table>
	<%} %>
<%} else { %>
	<table class="header">
		<tr >
			<td class="left" style="width: 610px; padding-left: 5px;">
				&nbsp;
			</td>
			<td class="right" style="padding-right: 0px;">
				&nbsp;
			</td>
		</tr>
	</table>
<%} %>
</html>