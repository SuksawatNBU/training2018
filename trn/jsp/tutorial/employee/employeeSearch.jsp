<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Employee</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<s:include value="/jsp/template/jquery-inputdatetimeformat.jsp"/>
	<s:include value="/jsp/tutorial/employee/include/employeeSearch-ja-css.jsp"/>
</head>
<body>

	<s:form id="searchForm" name="searchForm" method="post" namespace="/jsp/tutorial" action="initAction" cssClass="margin-zero" onsubmit="return false;">
		
	    <!------------------------------------- Criteria ------------------------------------->
	    <div id="divSerachForm" class="CRITERIA CRITERIA_1280">
	    	<div id="divCriteria" class="RESULT_BOX BORDER_COLOR" style="display: ;">
			
				<table class="FORM" id="divCriteria_TableHorizontal">
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
							<s:label for="criteria_prefixId" value="คำนำหน้าชื่อ" />
						</td>
						<td class="VALUE">
							<s:select id="criteria_prefixId" name="criteria.prefixId" list="listPrefix" headerKey="" headerValue="" listKey="key" listValue="value" cssClass ="combox"/>
						</td>
						<td class="LABEL">
							<s:label for="criteria_fullname" value="ชื่อ-สกุล" />
						</td>
						<td class="VALUE">
							<s:textfield id="criteria_fullname" name="criteria.fullname" maxlength="100" cssClass="combox" />
						</td>					
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:label for="criteria_nickname" value="ชื่อเล่น" />
						</td>
						<td class="VALUE">
							<s:textfield id="criteria_nickname" name="criteria.nickname" maxlength="100" cssClass="combox" />
						</td>
						<td class="LABEL">
							<s:label for="criteria_sex" value="เพศ" />
						</td>
						<td class="VALUE">
							<s:select id="criteria_sex" name="criteria.sex" list="listSex"  headerKey="" headerValue="" listKey="key" listValue="value" cssClass ="combox"/>
						</td>		
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:label for="criteria_departmentName" value="สังกัด" />
						</td>
						<td class="VALUE">
							<s:textfield id="criteria_departmentId" name="criteria.departmentId" code-of="autocomplet_department" cssClass="autocomplete" />
							<s:textfield id="criteria_departmentName" name="criteria.departmentName" text-of="autocomplet_department" cssClass="autocomplete" />
						</td>
						<td class="LABEL">
							<s:label for="criteria_positionDesc" value="แผนก" />
						</td>
						<td class="VALUE">
							<s:textfield id="criteria_positionId" name="criteria.positionId" code-of="autocomplete_position" cssClass="autocomplete" />
							<s:textfield id="criteria_positionDesc" name="criteria.positionDesc" text-of="autocomplete_position" cssClass="autocomplete" />
						</td>
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:label for="criteria_startWorkDate" value="ช่วงวันที่เริ่มงาน ตั้งแต่" />
						</td>
						<td class="VALUE">
							<s:textfield id="criteria_startWorkDate" name="criteria.startWorkDate" maxlength="100" cssClass="input_datapicker"/>
						</td>
						<td class="LABEL">
							<s:label for="criteria_endWorkDate" value="ถึง" />
						</td>
						<td class="VALUE">
							<s:textfield id="criteria_endWorkDate" name="criteria.endWorkDate" maxlength="100" cssClass="input_datapicker"/>
						</td>
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:label for="criteria_workStatus" value="สถานะการทำงาน" />
						</td>
						<td class="VALUE">
							<s:select id="criteria_workStatus" name="criteria.workStatus" list="listWorkStatus" headerKey="" headerValue="ทั้งหมด" listKey="key" listValue="value"/>
						</td>
						<td class="LABEL">
							<s:label for="criteria_endWorkDate" value="จำนวนข้อมูลต่อหน้า" />
						</td>
						<td class="VALUE">
							<s:select id="criteria_linePerPage" cssClass="lpp-style clearform" name="criteria.linePerPage"  list="LPP" />
						</td>
						<td class="BORDER"></td>
					</tr>
				</table>
				
				<s:include value="/jsp/template/button.jsp">
			        <s:param name="buttonType" value="%{'search,enable'}"/>
			    </s:include>
				
			</div>
		</div>
	    
	    <!------------------------------------- Result ------------------------------------->
	    <div class="RESULT">
	    <div id="div_datatable" class="ex_highlight_row" style="display: none;">
	    	<table class="display" id="tableResult">
	    		<thead>
	    			<tr>
	    				<th><s:text name="emp.no" /></th>
	    				<th></th>
	    				<th></th>
	    				<th></th>
	    				<th><s:text name="emp.fullname" /></th>
	    				<th><s:text name="emp.sex" /></th>
	    				<th><s:text name="emp.department" /></th>
	    				<th><s:text name="emp.position" /></th>
	    				<th><s:text name="emp.startDate" /></th>
	    				<th><s:text name="emp.endDate" /></th>
	    				<th><s:text name="emp.workStatus" /></th>
	    				<th><s:text name="emp.createUser" /></th>
	    				<th><s:text name="emp.createDate" /></th>
	    				<th><s:text name="emp.updateUser" /></th>
	    				<th><s:text name="emp.updateDate" /></th>
	    				<th><s:text name="emp.createRemark" /></th>
	    			</tr>
	    		</thead>
	    		<tbody>
					<tr>
						<!-- Loading Progress -->
						<td colspan="16" class="dataTables_empty">Loading data from server</td>
					</tr>
				</tbody>
	        </table>
	   	</div>
	   	</div>
	   	
	   	<button type="button" onclick="">Dalete</button>
	   	
	   	<!------------------------------------- BUTTON ------------------------------------->
	   	<div style="display: none;"><s:include value="/jsp/template/hiddenSearchForDatatable.jsp" /></div>
	    <s:hidden name="employee.id" />
	    <s:token/>
    
	</s:form>
	
</body>
</html>