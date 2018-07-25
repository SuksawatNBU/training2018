<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.cct.common.CommonUser"%>
<%@ page import="util.web.SessionUtil"%>

<%
String haveMenu = "true";
if (SessionUtil.getAttribute("useMenu") != null) {
	haveMenu = (String) SessionUtil.getAttribute("useMenu");
}
%>

<html>
<head>
	<script type="text/javascript">
		function sf(){

		}

		function initMenu () {
			submitPage("<s:url value='/jsp/security/initMainpage.action' />");
		}
		
	</script>
</head>
<body>
<s:form cssClass="margin-zero" name="template">
<!-- 	<br> -->
<%-- 		<a href="<%=request.getContextPath()%>/jsp/initComponent.action" style="padding-left: 20px"><img src="<s:url value='/images/icon/i_search.png' />" /><font color="blue" size="3">Goto page Component</font></a> --%>
<!-- 	<br> -->
<!-- 	<br> -->
	
	
<!-- 		<table class="FORM"> -->
		
<!-- 			<tr> -->
<!-- 				<td align="center"> -->
<!-- 					DIV HEADER -->
<!-- 					<div align="center"> -->
<!-- 						<table id="table555" class="RESULT_CONTENT" style="width: 1100px"> -->
<!-- 							<tr style="display: none;">  -->
<!-- 								<td style="width: 5px;" class="date"></td> -->
<!-- 								<td></td> -->
<!-- 							</tr> -->
<!-- 							<tr>  -->
<!-- 								<td class="date" colspan="2"> Link System</td> -->
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td class="date">1.</td> -->
<%-- 								<td><a href="<%=request.getContextPath()%>/jsp/user/admin/initAdmin.action"><font color="blue">user access(Admin)</font></a></td> --%>
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td class="date">2.</td> -->
<%-- 								<td><a href="<%=request.getContextPath()%>/jsp/carrierportal/carrier/initCarrier.action"><font color="blue">Carrier Portal > Carrier</font></a></td> --%>
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td class="date">3.</td> -->
<%-- 								<td><a href="<%=request.getContextPath()%>/jsp/carrierportal/carrier/initCarrierUser.action"><font color="blue">Carrier Portal > Carrier User</font></a></td> --%>
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td class="date">4.</td> -->
<%-- 								<td><a href="<%=request.getContextPath()%>/jsp/carrierportal/carrier/initCarrierMessageBoard.action"><font color="blue">Carrier Portal > Carrier Message Board</font></a></td> --%>
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td class="date">5.</td> -->
<%-- 								<td><a href="<%=request.getContextPath()%>/jsp/security/initChangePassword.action"><font color="blue">UCP > Change Password</font></a></td> --%>
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td class="date">6.</td> -->
<%-- 								<td><a href="<%=request.getContextPath()%>/jsp/security/initProfile.action"><font color="blue">UCP > View Profile</font></a></td> --%>
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td class="date">7.</td> -->
<%-- 								<td><a href="<%=request.getContextPath()%>/jsp/user/userauditevent/initUserAuditEvent.action"><font color="blue">User Audit Event</font></a></td> --%>
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td class="date">8.</td> -->
<%-- 								<td><a href="<%=request.getContextPath()%>/jsp/user/ucpconfiguration/initUCPConfiguration.action"><font color="blue">UCP Configuration</font></a></td> --%>
<!-- 							</tr> -->
<!-- 							<tr> -->
<!-- 								<td class="date">9.</td> -->
<%-- 								<td><a href="<%=request.getContextPath()%>/jsp/user/cpconfiguration/initCPConfiguration.action"><font color="blue">CP Configuration</font></a></td> --%>
<!-- 							</tr> -->
<!-- 						</table>	 -->
<!-- 					</div> -->
<!-- 				</td> -->
<!-- 			</tr> -->
				
<!-- 		</table> -->
<!-- 	<br> -->
	
</s:form>
</body>
</html>