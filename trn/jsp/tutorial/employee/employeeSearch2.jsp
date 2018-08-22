<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Employee</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<s:include value="/jsp/template/jquery-inputdatetimeformat.jsp"/>
	<s:include value="/jsp/tutorial/employee/include/employeeSearch-ja-css2.jsp"/>
</head>
<body>

	<s:form id="addEditForm" name="addEditForm" method="post" cssClass="margin-zero" >
		
	    <!------------------------------------- Result ------------------------------------->
	    <!-- div สำหรับแสดงผล  -->
		<div id="divTableAdd" class="RESULT" style="width: 95%; height:280px;" ></div>
		<!--ส่วนของการกำหนด parameters -->
		<s:set var="divresult" value='{"divTableAdd"}'/> 
		<s:set var="listTableData" value='%{"listResult"}'/> <!-- domain list เช่น userData.listPermission -->
		<s:set var="columnName" value='{getText("emp.fullname"), getText("emp.sex"), getText("emp.department"), getText("emp.position")}'/>
		<s:set var="columnID" value='{"employeeId"}'/> <!-- PK popup -->
		<s:set var="columnData" value='{"fullname","sex","departmentDesc","positionDesc"}'/>
		<s:set var="columnCSSClass" value='{"col-width-100px","col-width-50px","col-width-200px","col-width-150px"}'/>
		<s:set var="hiddenData" value='{"fullname","sex","departmentDesc","positionDesc"}'/> <!-- domain properties สำหรับดึงข้อมูลมาเก็บเป็น hidden -->
		<s:set var="settingTable" value='{"1000:true:true"}'/>
		<!-- include table template -->
		<s:include value="/jsp/template/tableAddDeleteRow.jsp"/>
		
		<!-- Custom header icons -->       
        <script>
            var iconPopup ="<a href='javascript:void(0)' onclick='dialogSingleOpen(\"dialog-popup\", 900, 500, true, initDialog, \"chooseRow\", jQuery(\"div#divTableAdd  #idsSelectedRow\").val())'><img src='<s:url value='/images/icon/i_add.png' />'/> เพิ่มข้อมูล</a>";
            jQuery("#headerTable_<s:property value='#divresult[0]'/> .RIGHT").append(iconPopup);
        </script>
         
        <!-- Popup -->       
        <div id="dialog-popup" title="รายชื่อพนักงาน" style="display: none;">
            <s:include value="/jsp/tutorial/employee/tableAddDeleteRowPopup.jsp"/>
        </div>
         
        <s:hidden name="page"/>
        <s:token/>
        
        <div>Page Enployee</div>
        
	</s:form>
	
</body>
</html>