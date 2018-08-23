<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Employee</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<s:include value="/jsp/template/jquery-inputdatetimeformat.jsp"/>
	<script type="text/javascript" src="<s:url value='/js/table/table.js' />"></script>
	<script type="text/javascript">
		function sf() {
			
		}
		
		/* Auto Complete */
		jQuery(document).ready(function(){
		    jQuery("#criteria_departmentId").autocompletezAjax([{  // UI มาตรฐาน   
		        url: "<s:url value='/departmentAutoSelectItemServlet'/>",  // url สำหรับ request ข้อมูล
		        defaultKey: "",     // กำหนดค่า key ตัวแรกของ Autocomplete
		        defaultValue: ""    // กำหนดค่า value ตัวแรกของ Autocomplete
		    },{
		        inputModelId: 'criteria_positionId',
		        url: "<s:url value='/positionAutoSelectItemServlet'/>",
		        postParamsId: {departmentId: "criteria_departmentId"},
		        defaultKey: "",
		        defaultValue: ""
		    }]);
		});
		
	    function edit(){
	        if(confirm('<s:text name="50004" />') == false){ 
	            return false;
	        }   
	        submitPage("<s:url value='/jsp/tutorial/editEmployee2.action'/>");
	    }
		
		function chooseRow(obj){
		    //1. หาค่า index ก่อนหน้า
		    var tableId = "tableId_divTableAdd";
		    var index = jQuery('table#'+tableId+' tbody tr').length;    
		     
		    var clArr = ["checkbox", "col-width-150px", "col-width-150px", ""];
		    var elmArr = new Array();
		    //elemnet 3 element ที่ต้องวาดคือ  input checkbox, hidden deleteFlag, hidden id
		    elmArr[0] = "<input type='checkbox' id='cnkColumnId' name='cnkColumnId' value='"+ obj.id +"'/>"
		        + "<input type='hidden' name='listResult["+index+"].deleteFlag' value=''/>"
		        + "<input type='hidden' name='listResult["+index+"].id' value=''/>"
		        //other hidden data
		        + "<input type='hidden' name='listResult["+index+"].employeeId' value='"+ obj.employeeId +"'/>"
		        + "<input type='hidden' name='listResult["+index+"].fullname' value='"+ obj.fullname +"'/>"
		        + "<input type='hidden' name='listResult["+index+"].sex' value='"+ obj.sex +"'/>"
		        + "<input type='hidden' name='listResult["+index+"].departmentDesc' value='"+ obj.departmentDesc +"'/>"
		        + "<input type='hidden' name='listResult["+index+"].positionDesc' value='"+ obj.positionDesc +"'/>";
		 	
		    //other column 1, 2, 3, ..., n
		    elmArr[1] = obj.employeeId;
		    elmArr[2] = obj.fullname;
		    elmArr[3] = obj.sex;
		    elmArr[4] = obj.departmentDesc;
		    elmArr[5] = obj.positionDesc;
		     
		    //2. create row 
		    tableCreateTableRow(tableId, clArr, elmArr);
		 	
		    //3. เก็บค่า id ไว้ใน textfield เวลาค้นหาจะเอาไป where not in
		    var ids = jQuery('div#divTableAdd  #idsSelectedRow').val(); //idsSelectedRow ถูกวาดมาจาก Table Template การ Seletc ต้องทำผ่าน DIV
		    if(ids != ""){
		        ids = ids + "," +obj.provinceId;
		    }else{
		        ids = obj.provinceId;
		    }
		    jQuery('div#divTableAdd  #idsSelectedRow').val(ids);
		 	
		    //4. close dialog
		    //Dialog will close itselft.
		}
	</script>
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