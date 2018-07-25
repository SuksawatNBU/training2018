<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.cct.common.CommonModel.PageType"%>

<%
	String defaultEnable = "enable";
	String defaultEnable_ATH = "true";

	String defaultNextTab = "fristtag";
	String defaultBackTab = "lasttag";
	String[] buttonAttribute = null;
	String buttonType = "";
	String criteriaName = "criteria";

	String[] functionAttribute = null;
	String function = "";
	try {
		if (request.getParameter("buttonType") != null) {
			buttonType = request.getParameter("buttonType").trim();
			buttonAttribute = buttonType.split(",");
			if (buttonAttribute.length < 1) {
				buttonAttribute = null;
				return;
			} else {
				for (int i = 0; i < buttonAttribute.length; i++) {
					buttonAttribute[i] = buttonAttribute[i].trim();
				}
			}
		}

		if (request.getParameter("criteriaName") != null) {
			criteriaName = request.getParameter("criteriaName").toString();
		}
		request.setAttribute("criteriaName", criteriaName);

		if (request.getParameter("function") != null) {
			function = request.getParameter("function").trim();
			functionAttribute = function.split(",");
		}
	} catch (Exception ex) {
		//ex.printStackTrace();
	}
%>

<script type="text/javascript" src="<s:url value='/js/button/button.js' />"></script>
<script type="text/javascript">
	jQuery(document).ready(function(){
		var contextPath='<%=request.getContextPath()%>';
		initButton(contextPath);
	});
</script>

<table class="BUTTON">
	<tr>
		<td class="CENTER" colspan="2">
			<s:include value="/jsp/template/message.jsp"/>		
		</td>
	</tr>
	<tr>
		<td class="LEFT RIGHT_70"></td>
		<td class="RIGHT LEFT_30">
			<s:if test="%{#buttonType.size() > 0}">
				<s:iterator value="#buttonType" status="status" var="buttonAttr" >
					<!----------------------------------------
					###[1] CHECK ENABLE STATUS BUTTON 
					----------------------------------------->
					<s:set var="statusEnable" value="#buttonEnable[#status.index]"/>
		
					<!----------------------------------------
					###[2] CHECK FUNCTION BUTTON 
					----------------------------------------->
					<s:set var="function" value=""/>
					<s:if test="#buttonFunction[#status.index] != null && #buttonFunction[#status.index] != ''">
						<s:set var="btnFunction" value='#buttonFunction[#status.index].split(":")'/>
						
						<s:iterator value="btnFunction" status="num" var="item" >
							<s:if test="#function != null && #function != ''">
								<s:if test="#num.index == 1">
									<s:set var="function" value="#item +' '+ #function"/>
								</s:if>
								<s:else>
									<s:set var="function" value='#function + "\'" + #item + "\'" +","'/>
								</s:else>
							</s:if>
							<s:else>
								<s:set var="function" value="#item + '('"/>
							</s:else>
						</s:iterator>
						
						<s:if test="#btnFunction.length == 1">
							<s:set var="function" value="#function.substring(0,#function.length()-1)+ '();'"/>
						</s:if>
						<s:elseif test="#btnFunction.length == 2">
							<s:set var="function" value="#function.substring(0,#function.length())+ ');'"/>
						</s:elseif>
						<s:else>
							<s:set var="function" value="#function.substring(0,#function.length()-1)+ ');'"/>
						</s:else>
					</s:if>
					
					<!----------------------------------------
					###[3] CREATE BUTTON
					----------------------------------------->
					<s:if test="#buttonAttr.toUpperCase() == 'SEARCH'">
						<s:if test="#statusEnable">
							<button id="btnSearch" class="btnSearch" type="button" onclick="<s:property value='#function.trim()'/>">
								<s:text name="search"/>
							</button>
						</s:if>
						<s:else>
							<button id="btnSearch"  class="btnSearch" type="button" onclick="return false;" tabindex="-1" disabled="disabled">
								<s:text name="search"/>
							</button>
						</s:else>
					</s:if>
					
					<s:if test="#buttonAttr.toUpperCase() == 'CLEAR'">
						<s:if test="#statusEnable">
							<button id="btnClear"  class="btnClear" type="button" onclick="<s:property value='#function.trim()'/>">
								<s:text name="clear"/>
							</button>
						</s:if>
						<s:else>
							<button id="btnClear"  class="btnClear" type="button" onclick="return false;" tabindex="-1" disabled="disabled">
								<s:text name="clear"/>
							</button>
						</s:else>
					</s:if>
					
					<!-- Send email -->
					<s:if test="#buttonAttr.toUpperCase() == 'SENDEMAIL'">
						<s:if test="#statusEnable">
							<button id="btnSendEmail" class="btnSendEmail" type="button" onclick="<s:property value='#function.trim()'/>">
								<s:text name="Send email"/>
							</button>
						</s:if>
						<s:else>
							<button id="btnSendEmail"  class="btnSendEmail" type="button" onclick="return false;" tabindex="-1" disabled="disabled">
								<s:text name="Send email"/>
							</button>
						</s:else>
					</s:if>
					
					<!-- Edit -->
					<s:if test="#buttonAttr.toUpperCase() == 'EDIT'">
						<s:if test="#statusEnable">
							<button id="btnEdit" class="btnEdit" type="button" onclick="<s:property value='#function.trim()'/>">
								<s:text name="Edit"/>
							</button>
						</s:if>
						<s:else>
							<button id="btnEdit"  class="btnEdit" type="button" onclick="return false;" tabindex="-1" disabled="disabled">
								<s:text name="Edit"/>
							</button>
						</s:else>
					</s:if>
					
					<!-- Back -->
					<s:if test="#buttonAttr.toUpperCase() == 'BACK'">
						<s:if test="#statusEnable">
							<button id="btnBack" class="btnBack" type="button" onclick="<s:property value='#function.trim()'/>">
								<s:text name="Back"/>
							</button>
						</s:if>
						<s:else>
							<button id="btnBack"  class="btnBack" type="button" onclick="return false;" tabindex="-1" disabled="disabled">
								<s:text name="Back"/>
							</button>
						</s:else>
					</s:if>
					
					<!-- Add another record -->
					<s:if test="#buttonAttr.toUpperCase() == 'ADD_ANOTHER_RECORD'">
						<s:if test="#statusEnable">
							<button id="btnAddAnotherRecord" class="btnAddAnotherRecord" type="button" onclick="<s:property value='#function.trim()'/>">
								<s:text name="Add another record"/>
							</button>
						</s:if>
						<s:else>
							<button id="btnAddAnotherRecord"  class="btnAddAnotherRecord" type="button" onclick="return false;" tabindex="-1" disabled="disabled">
								<s:text name="Add another record"/>
							</button>
						</s:else>
					</s:if>
					
					<!-- Validate -->
					<s:if test="#buttonAttr.toUpperCase() == 'VALIDATEFORM'">
						<s:if test="#statusEnable">
							<button id="btnValidate" class="btnValidate" type="button" onclick="<s:property value='#function.trim()'/>">
								<s:text name="Validate"/>
							</button>
						</s:if>
						<s:else>
							<button id="btnValidate" class="btnValidate" type="button" onclick="return false;" tabindex="-1" disabled="disabled">
								<s:text name="Validate"/>
							</button>
						</s:else>
					</s:if>
					
					<!-- Test -->
					<s:if test="#buttonAttr.toUpperCase() == 'TEST'">
						<s:if test="#statusEnable">
							<button id="btnTest" class="btnTest" type="button" onclick="<s:property value='#function.trim()'/>">
								<s:text name="Test"/>
							</button>
						</s:if>
						<s:else>
							<button id="btnTest" class="btnTest" type="button" onclick="return false;" tabindex="-1" disabled="disabled">
								<s:text name="Test"/>
							</button>
						</s:else>
					</s:if>
					
					<s:elseif test="#buttonAttr.toUpperCase() == 'CLOSE'">
						<button id="btnClose"  class="btnClose"  type="button" onclick="<s:property value='#function.trim()'/>">
							<s:text name="Close"/>
						</button>
					</s:elseif>
					
					
				</s:iterator>
			</s:if>
		</td>
	</tr>
	<tr>
		<td style="height: 14px;" class="CENTER">&nbsp;</td>
	</tr>
</table>

<script type="text/javascript">
	// สำหรับลบ input ส่วนเกิน
	jQuery(document).ready(function() {
		jQuery("input[name='" + '<s:property value="%{#request.criteriaName}"/>' + ".criteriaKey']").each(function(index) {
			//console.info(index);
			if (index > 0) {
				jQuery(this).remove();
			}
		});

		jQuery("input[name='P_CODE']").each(function(index) {
			if (index > 0) {
				jQuery(this).remove();
			}
		});

		jQuery("input[name='F_CODE']").each(function(index) {
			if (index > 0) {
				jQuery(this).remove();
			}
		});

		jQuery("input[name='page']").each(function(index) {
			if (index > 0) {
				jQuery(this).remove();
			}
		});
	});
</script>
