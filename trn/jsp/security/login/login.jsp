<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title><s:text name="applicationName"/></title>
<s:include value="/jsp/template/javascript.jsp"/>
<script type="text/javascript" src="<s:url value='/js/jquery-ui-initial.js' />"></script>
<link href="<s:url value='/css/login-not-ie.css' />" rel="stylesheet" type="text/css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/theme/<s:property value='@com.cct.ucp.core.config.parameter.domain.ParameterConfig@getParameter().getApplication().getTheme()' />/jquery-ui.css"/>
<link type="text/css" rel="stylesheet" href="<s:url value='/css/theme/jquery-ui-theme-custom.css' />"/>
<script type="text/javascript">

	var xLogin = false;
	jQuery(document).on("keydown",function(event){
		
		var keyCode = event.which || event.keyCode;
		
		if (keyCode == 13) {

			if (xLogin == false) {
				if (validateFormInputAll() == false) {
					return;
				}
				submitPage("<s:url value='/jsp/security/checkLogin.action' />");
			}
		}
	});

	function sf() {
		// ลบกรอบสีขาวของ message
		jQuery("table#tblMessage td.MESSAGE").css("border-bottom", "none");
		jQuery("table#tblMessage td.MESSAGE").css("border-top", "none");
		
		//เพิ่มความสูงของ div footer ถ้ามีปุ่ม CANCEL
// 		if(jQuery("#btnCancel").length > 0){
// 			jQuery(".login_footer").css('height', '65px')
// 		}
		
		jQuery("#btnLoginx").focusin(function() {
			xLogin = true;
		});
		
		jQuery("#btnLoginx").focusout(function() {
			xLogin = false;
		});
		
		jQuery("[name='username']")[0].focus();
	}

	function login() {
		if (xLogin) {
			if (validateFormInputAll() == false) {
				// ลบกรอบสีขาวของ message
				jQuery("table#tblMessage td.MESSAGE").css("border-bottom", "none");
				jQuery("table#tblMessage td.MESSAGE").css("border-top", "none");
				
				return;
			}
			
			submitPage("<s:url value='/jsp/security/checkLogin.action' />");
		}
	}
	
</script>
</head>
<body onload="sf();">
<s:form id="loginForm" name="loginForm" action="checkLogin" namespace="/jsp/security" method="POST" cssClass="margin-zero">
	<table class="body body_1280" >
		<tr style="height: 120px;">
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr style="vertical-align: top;">
			<td style="width: 40%">&nbsp;</td>
			<td>
				<div class="login_form">
					<div class="login_title  ui-widget-header">
						<table>
							<tr>
								<td align="left" style="width: 330px;"><s:text name="sec.sign_in_header"/>&nbsp;&nbsp;&nbsp;</td>
								<td align="right"></td>
							</tr>
						</table>
					</div>
					
					<div class="login_body" style="margin-top: 10px;">
						<table class="FORM">
							<tr>
								<td style="width: 80px;" class="LABEL">
									<s:text name="sec.login_user_id"/><em>&nbsp;*&nbsp;</em>
								</td>
								<td class="VALUE">
									<s:textfield id="username" 
										name="username" 
										cssClass="requireInput %{#cssClass}" 
										cssStyle="width: 90%"  
										validName="%{getText('sec.login_user_id')}" 
										maxlength="35" />
								</td>
							</tr>
							<tr>
								<td style="width: 80px;" class="LABEL">
									<s:text name="sec.login_password"/><em>&nbsp;*&nbsp;</em>
								</td>
								<td class="VALUE">
									<s:password id="password" 
										name="password" 
										cssClass="requireInput %{#cssClass}" 
										cssStyle="width: 90%"  
										validName="%{getText('sec.login_password')}" 
										maxlength="20" />
								</td>
							</tr>
						</table>
					</div>
					
					<div class="login_footer">
						<s:include value="/jsp/template/message.jsp"/>
					
						<table>
							<tr>
								<td style="width: 450px;" align="center">
									<input type="button" class="BLUELOGIN" id="btnLoginx" value='<s:text name="sec.sign_in_button"/>' onclick="login();"/>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</td>
			<td style="width: 40%">&nbsp;</td>
		</tr>
	</table>
	<s:token />
</s:form>
</body>
</html>