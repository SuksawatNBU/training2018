<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Employee</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<s:include value="/jsp/template/jquery-inputdatetimeformat.jsp"/>
	<s:include value="/jsp/tutorial/employee/include/employeeAddEditView-ja-css2.jsp"/>
</head>
<body>
	<s:form id="addEditForm" name="addEditForm" method="post" namespace="/jsp/tutorial" cssClass="margin-zero" onsubmit="return false;" >
		
		<!-- set class ให้กับหน้า -->
		<s:if test="page.getPage() == 'view'"> 
		 	<s:set var="viewDisable" value="true" />
		</s:if>
		<s:else> 
			<s:set var="viewDisable" value="" />
		</s:else>
		
		<s:if test="page.getPage() == 'edit'">
			<s:set var="editDisable" value="true" />
		</s:if>
		<s:else>
			<s:set var="editDisable" value="" />
		</s:else>
		
		<span>viewDisable : <s:text name="%{viewDisable}"/></span><br/>
		<span>editDisable : <s:text name="%{editDisable}"/></span>

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
							cssClass ="combox requireInput" disabled="%{viewDisable}" />
						</td>
						<td class="LABEL">
							<s:text name="emp.sex" /><em>*</em>
						</td>
						<td class="VALUE">
							<s:if test="page.getPage() == 'edit'">
								<s:iterator value="listSex" >
									<label class="checkbox-inline">
										<input type="radio" id="employee_sex" name="employee.sex" value='<s:text name="key"/>'/>
										<s:property value="value"/>
									</label>
								</s:iterator>
							</s:if>
							<s:else>
								<s:iterator value="listSex" >
									<label class="checkbox-inline">
										<input type="radio" id="employee_sex" name="employee.sex" value='<s:text name="key"/>'/>
										<s:property value="value"/>
									</label>
								</s:iterator>
							</s:else>
						</td>					
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:text name="emp.name"/><em>*</em>
						</td>
						<td class="VALUE">
							<s:textfield id="employee_name" name="employee.name" cssClass="requireInput" disabled="%{viewDisable}"/>
						</td>
						<td class="LABEL">
							<s:text name="emp.surname" /><em>*</em>
						</td>
						<td class="VALUE">
							<s:textfield id="employee_surname" name="employee.surname" cssClass="requireInput" disabled="%{viewDisable}"/>
						</td>					
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:text name="emp.nickName"/><em>*</em>
						</td>
						<td class="VALUE">
							<s:textfield id="employee_nickName" name="employee.nickName" cssClass="requireInput" disabled="%{viewDisable}"/>
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
							<s:textfield id="employee_departmentId" name="employee.departmentId" code-of="autocomplet_department" cssClass="autocomplete requireInput" disabled="%{viewDisable}"/>
							<s:textfield id="employee_departmentDesc" name="employee.departmentDesc" text-of="autocomplet_department" cssClass="autocomplete" />
						</td>
						<td class="LABEL">
							<s:text name="emp.position"/><em>*</em>
						</td>
						<td class="VALUE">
							<s:textfield id="employee_positionId" name="employee.positionId" code-of="autocomplet_position" cssClass="autocomplete requireInput" disabled="%{viewDisable}"/>
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
							<s:if test="page.getPage() == 'edit'">
								<s:textfield id="employee_startWorkDate" name="employee.startWorkDate" cssClass="requireInput" disabled="%{editDisable}"/>
							</s:if>
							<s:else>
								<s:textfield id="employee_startWorkDate" name="employee.startWorkDate" cssClass="requireInput" disabled="%{viewDisable}"/>
							</s:else>
						</td>
						<td class="LABEL">
							<s:if test="page.getPage() == 'edit'"> 
								<s:text name="emp.endDate"/><em>*</em>
							</s:if>
						</td>
						<td class="VALUE">
							<s:if test="page.getPage() == 'edit'"> 
								<s:textfield id="employee_endWorkDate" name="employee.endWorkDate" cssClass="requireInput inputdatepickerDisabled"/>
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
							<s:iterator value="listWorkStatus">
								<label class="checkbox-inline"  onclick='changEndWorkDate(<s:property value="key"/>)'>
									<input type="radio" id="employee_workStatus" name="employee.workStatus" value='<s:property value="key"/>' />
									<s:property value="value"/>
								</label>
							</s:iterator>
						</td>
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:text name="emp.createRemark"/>
						</td>
						<td class="VALUE" colspan="3">
							<s:textarea id="employee_transaction_createRemark" name="employee.transaction.createRemark" disabled="%{viewDisable}"/>
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