<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

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
<s:form cssClass="margin-zero" name="standardui">
	<div class="CRITERIA CRITERIA_1280">
		<div align="center">
			<table style="width: 95%" cellpadding="5">
				<thead style="background-color:#3383bb">
					<tr>
						<th colspan="2" style="width:90%; height: 20px;">
							<font color="white" size="3">
								<s:text name="std.miscellaneous" />
							</font>
						</th>
					</tr>
				</thead>
				<tbody style="background-color: #e0e0e0">
					<tr>
						<td align="center" style="width: 10%">1.</td>
						<td align="left">
							<a href="<%=request.getContextPath()%>/jsp/standardui/initDateMiscellaneous.action"><font color="blue">Input date format</font></a>
						</td>
					</tr>
					<tr>
						<td align="center" style="width: 10%">2.</td>
						<td align="left">
							<a href="<%=request.getContextPath()%>/jsp/standardui/initTimeMiscellaneous.action"><font color="blue">Input time format</font></a> 
						</td>
					</tr>
					<tr>
						<td align="center" style="width: 10%">3.</td>
						<td align="left">
							<a href="<%=request.getContextPath()%>/jsp/standardui/initIpAddressMiscellaneous.action"><font color="blue">IP Address</font></a> 
						</td>
					</tr>
					<tr>
						<td align="center" style="width: 10%">4.</td>
						<td align="left">
							<a href="<%=request.getContextPath()%>/jsp/standardui/initSuggestionMiscellaneous.action"><font color="blue">Suggestion</font></a> 
						</td>
					</tr>
					<tr>
						<td align="center" style="width: 10%">5.</td>
						<td align="left">
							<a href="<%=request.getContextPath()%>/jsp/standardui/initTooltipMiscellaneous.action"><font color="blue">Tooltip</font></a> 
						</td>
					</tr>
					<tr>
						<td align="center" style="width: 10%">6.</td>
						<td align="left">
							<a href="<%=request.getContextPath()%>/jsp/standardui/initUploadMiscellaneous.action"><font color="blue">Upload</font></a> 
						</td>
					</tr>
					<tr>
						<td align="center" style="width: 10%">7.</td>
						<td align="left">
							<a href="<%=request.getContextPath()%>/jsp/standardui/initAccordionMiscellaneous.action"><font color="blue">Accordion</font></a> 
						</td>
					</tr>
					<tr>
						<td align="center" style="width: 10%">8.</td>
						<td align="left">
							<a href="<%=request.getContextPath()%>/jsp/standardui/initTabMiscellaneous.action"><font color="blue">Tab</font></a> 
						</td>
					</tr>
					<tr>
						<td align="center" style="width: 10%">9.</td>
						<td align="left">
							<a href="<%=request.getContextPath()%>/jsp/standardui/initTreeviewMiscellaneous.action"><font color="blue">Treeview</font></a> 
						</td>
					</tr>
					<tr>
						<td align="center" style="width: 10%">10.</td>
						<td align="left">
							<a href="<%=request.getContextPath()%>/jsp/standardui/initProgressbarMiscellaneous.action"><font color="blue">Progressbar</font></a> 
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</s:form>
</body>
</html>