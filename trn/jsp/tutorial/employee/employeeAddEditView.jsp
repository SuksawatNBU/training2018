<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Employee</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<s:include value="/jsp/template/jquery-inputdatetimeformat.jsp"/>
	<s:include value="/jsp/tutorial/employee/include/employeeAddEditView-ja-css.jsp"/>
</head>
<body>
	<s:form id="addEditForm" name="addEditForm" method="post" namespace="/jsp/tutorial" cssClass="margin-zero" onsubmit="return false;" >
		
		<!-- set class ให้กับหน้า -->
		<s:if test="page.getPage() == 'view'"> 
		 	<s:set var="disableForm" value="true" />
		</s:if>
		<s:else> 
			<s:set var="readOnly" value="" />
		</s:else>
		
		<s:if test="page.getPage() == 'edit'">
			<s:set var="empReadOnly" value="true" />
		</s:if>
		<s:else>
			<s:set var="empReadOnly" value="" />
		</s:else>
		
		<div>
			<table class="FORM" style="float:left; margin:13px;">
				<tbody>
					<tr style="display: none;">
						<td class="BORDER"></td>
						<td class="LABEL"></td>
						<td class="VALUE"></td>
						<td class="LABEL"></td>
						<td class="VALUE"></td>
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:text name="emp.prefixId"/><em>*</em>
						</td>
						<td class="VALUE">
							<s:select id="employee_prefixId" name="employee.prefixId" 
							list="listPrefix" headerKey="" headerValue="" listKey="key" listValue="value" 
							cssClass ="combox requireInput" disabled="%{disableForm}" />
						</td>
						<td class="LABEL">
							<s:text name="emp.sex" /><em>*</em>
						</td>
						<td class="VALUE">
							<s:radio id="employee_sex" name="employee.sex" list="listSex" listKey="key" listValue="value" cssClass ="requireInput " disabled="%{disableForm} %{projCodeReadOnly}" />
						</td>					
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:text name="emp.name"/><em>*</em>
						</td>
						<td class="VALUE">
							<s:textfield id="employee_name" name="employee.name" cssClass="requireInput" disabled="%{disableForm}"/>
						</td>
						<td class="LABEL">
							<s:text name="emp.surname" /><em>*</em>
						</td>
						<td class="VALUE">
							<s:textfield id="employee_surname" name="employee.surname" cssClass="requireInput" disabled="%{disableForm}"/>
						</td>					
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:text name="emp.nickName"/><em>*</em>
						</td>
						<td class="VALUE">
							<s:textfield id="employee_nickName" name="employee.nickName" cssClass="requireInput" disabled="%{disableForm}"/>
						</td>
						<td class="LABEL"></td>
						<td class="VALUE"></td>
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:text name="emp.department"/><em>*</em>
						</td>
						<td class="VALUE">
							<s:textfield id="employee_departmentId" name="employee.departmentId" code-of="autocomplet_department" cssClass="autocomplete requireInput" disabled="%{disableForm}"/>
							<s:textfield id="employee_departmentDesc" name="employee.departmentDesc" text-of="autocomplet_department" cssClass="autocomplete" />
						</td>
						<td class="LABEL">
							<s:text name="emp.position"/><em>*</em>
						</td>
						<td class="VALUE">
							<s:textfield id="employee_positionId" name="employee.positionId" code-of="autocomplet_position" cssClass="autocomplete requireInput" disabled="%{disableForm}"/>
							<s:textfield id="employee_positionDesc" name="employee.positionDesc" text-of="autocomplet_position" cssClass="autocomplete" />
						</td>
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:text name="emp.startDate"/><em>*</em>
						</td>
						<td class="VALUE">
							<s:textfield id="employee_startWorkDate" name="employee.startWorkDate" cssClass="requireInput" disabled="%{disableForm} %{projCodeReadOnly}"/>
						</td>
						<td class="LABEL">
							<s:if test='%{employee.workStatus eq "W"}'>
								<s:text name="emp.endDate"/><em>*</em>
							</s:if>
						</td>
						<td class="VALUE">
							<s:if test='%{employee.workStatus eq "W"}'>
								<s:textfield id="employee_endWorkDate" name="employee.endWorkDate" cssClass="requireInput" disabled="%{disableForm}"/>
							</s:if>
						</td>
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:text name="emp.status"/><em>*</em>
						</td>
						<td class="VALUE" colspan="3">
							<s:radio id="employee_workStatus" name="employee.workStatus" list="listWorkStatus" listKey="key" listValue="value" cssClass="requireInput" disabled="%{disableForm}" />
						</td>
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:text name="emp.createRemark"/>
						</td>
						<td class="VALUE" colspan="3">
							<s:textarea id="employee_transaction_createRemark" name="employee.transaction.createRemark" disabled="%{disableForm}"/>
						</td>
						<td class="BORDER"></td>
					</tr>
				</tbody>
			</table>
			
			<!--------------------------------------- ADD PAGE --------------------------------------->		
			<s:if test='page.getPage() == "add"'>
				<s:include value="/jsp/template/button.jsp">
					<s:param name="buttonType" value="%{'add,enable'}" />
				</s:include>
			</s:if>
			<!--------------------------------------- EDIT PAGE --------------------------------------->
			<s:elseif test='page.getPage() == "edit"'>
				<s:include value="/jsp/template/button.jsp">
					<s:param name="buttonType" value="%{'edit,enable'}" />
				</s:include>
			</s:elseif>
			<!--------------------------------------- VIEW PAGE --------------------------------------->
			<s:elseif test='page.getPage() == "view"'>
				<s:include value="/jsp/template/button.jsp">
					<s:param name="buttonType" value="%{'view,enable'}" />
				</s:include>
			</s:elseif>
			
			<s:hidden name="criteria.criteriaKey" />
			<s:hidden name="P_CODE"/>
		    <s:hidden name="F_CODE"/>
		    <s:hidden name="page"/>
		    <s:hidden name="employee.id"/>
			<s:token />	
		</div>
	</s:form>
</body>
</html>