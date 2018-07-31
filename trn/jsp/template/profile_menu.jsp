<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.cct.trn.core.config.parameter.domain.ParameterConfig"%>
<%@ page import="com.cct.common.CommonAction"%>
<%@ page import="com.cct.common.CommonUser"%>
<%@ page import="util.bundle.BundleUtil"%>
<%@ page import="util.web.SessionUtil"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.Locale"%>
<%
boolean haveProfile = false;
String context = request.getContextPath();
Locale locale = new CommonAction(true).getLocale();
String email = "";
String username = "";
String fullname = "";

try {
	CommonUser user = new CommonAction(true).getUser();
	if (user != null) {
		email = user.getEmail() == null ? "" : user.getEmail();
		username = user.getUserName();
		fullname = user.getFullName();
		locale = user.getLocale();
		haveProfile = true;
	}
} catch(Exception e) {
// 	e.printStackTrace();
}

ResourceBundle sec = BundleUtil.getBundle("resources.bundle.security.MessageSecurity", locale);

String profile = "";
try {
	profile = "<div id='news-items-3' class='hidden'>";
	profile += "<table class='' style='float: right; width: 100%; padding: 5px;'>";
	profile += "<tr>";
	profile += "<td nowrap='nowrap'><span style='width: 200px;'><font style='font-weight: bold;'>&nbsp;" + sec.getString("sec.login_user_id") + ": </font>" + username + "</span></td>";
	profile += "</tr>";
	profile += "<tr>";
	profile += "<td nowrap='nowrap'><span style='width: 200px;'><font style='font-weight: bold;'>&nbsp;" + sec.getString("sec.fullname") + ": </font>" + fullname + "</span></td>";
	profile += "</tr>";
	profile += "<tr>";
	profile += "<td>";
	profile += "<table style='width: 100%; padding: 0px; border-collapse: collapse;'>";
	profile += "<tr>";
	profile += "<td style='width: 40%; text-align: left;'>";
	profile += "<a id='btnProfile' tabindex='-1' href='javascript:void(0);' onclick='profile();' class='LINK'>" + "<img src='" + context + "/images/icon/i_view.png'/>" + sec.getString("sec.profile_view_profile") + "</a>";
	profile += "</td>";
	profile += "<td style='width: 40%; text-align: left;'>";
	profile += "<a id='btnChangePassword' tabindex='-1' href='" + context + "/jsp/security/initChangePassword.action' class='LINK'>" + "<img src='" + context + "/images/icon/i_edit.png'/>" + sec.getString("sec.profile_change_password") + "</a>";
	profile += "</td>";
	profile += "<td style='width: 20%; text-align: center;'>";
	profile += "<a id='btnLogoff' tabindex='-1' href='" + context + "/jsp/security/logoutLogin.action' class='LINK'>" + "<img src='" + context + "/images/icon/i_logout.png'/>" + sec.getString("sec.profile_sign_out") + "</a>";
	profile += "</td>";
	profile += "</tr>";
	profile += "</table>";
	profile += "</td>";
	profile += "</tr>";
	profile += "</table>";
	profile += "</div>";
} catch(Exception e) {
// 	e.printStackTrace();
}
// 	System.out.println(profile);
%>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <script type="text/javascript" src="<s:url value='/js/flyout/fg.menu.js' />"></script>
	<link href="<s:url value='/js/flyout/fg.menu.css' />" rel="stylesheet" type="text/css">
	<style type="text/css">
		.hidden { position:absolute; top:0; left:-9999px; width:1px; height:1px; overflow:auto; }
		
		.profile-fg-button { clear:none; margin:0 0px 0px 0px; padding: 0px 0px 0px 0px; text-decoration:none !important; cursor:pointer; position: relative; text-align: center; zoom: 1; }
		.profile-fg-button { color: black; border: 0px; background: transparent; }
		
		a.profile-fg-button { text-align: center; font-weight: normal;}
		
	</style>
    <script type="text/javascript">
	    jQuery(function(){
	    	
	    	// MENUS
			jQuery('#flat').menufg({
				content: jQuery('#flat').next().html(), // grab content from this page
				showSpeed: 300,
				width: 400,
				positionOpts: {
					// posX: 'left',
					posX: 'right',
					posY: 'bottom',
					offsetX: -50,
					offsetY: 2,
					directionH: 'left',
					directionV: 'down',
					detectH: true, // do horizontal collision detection
					detectV: true, // do vertical collision detection
					linkToFront: false
				}
			});
	    });
    </script>
</head>
<%if (haveProfile) { %>
	<table class="FORM" style="border-collapse: collapse; border-top-left-radius: 0px; border-top-right-radius: 0px; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px;">
		<tr>
			<td>
				<s:if test="useHomeMenu">
					<div class="BUTTON FLOAT_RIGHT BORDER_LEFT">
						<a tabindex='-1' href="<%=context%>/jsp/security/initMainpage.action">
							<img src="<s:url value='/images/menu/i_home.png' />">
							<br/>
							<s:text name="home" />
						</a>
					</div>
				</s:if>
				<s:if test="page.getPage() == 'search'">
					<s:if test="ATH.add && useAddMenu">
						<div class="BUTTON FLOAT_RIGHT BORDER_LEFT" onclick="addPage();">
							<img src="<s:url value='/images/menu/i_add.png' />">
							<br/>
							<s:text name="add" />
						</div>
					</s:if>
				</s:if>
				<s:if test="page.getPage() == 'edit'">
					<s:if test="useRefreshMenu">
						<div class="BUTTON FLOAT_RIGHT BORDER_LEFT" onclick="reloadEditPage();">
							<img src="<s:url value='/images/menu/i_refresh.png' />">
							<br/>
							<s:text name="reload" />
						</div>
					</s:if>
				</s:if>
			</td>
		</tr>
	</table>
<% } else { %>
	<table class="FORM" style="border-collapse: collapse; border-top-left-radius: 0px; border-top-right-radius: 0px; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px;">
		<tr>
			<td>
				<div class="BUTTON FLOAT_RIGHT BORDER_LEFT">
					<a tabindex='-1' href="">
						<img src="<s:url value='/images/menu/i_exit.png' />">
						<br/>
						<s:text name="exit"/>
					</a>
				</div>
			</td>
		</tr>
	</table>
<% }%>