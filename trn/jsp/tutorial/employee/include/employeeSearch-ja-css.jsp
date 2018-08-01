<%@ page contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">

	function sf() {
		if(jQuery("[name='criteria.criteriaKey']").val() != ""){
            searchAjax();
        }
	}

	/* Auto Complete */
	jQuery(document).ready(function(){
		// เรียกใช้งาน Autocomplete widget สำหรับสร้าง Autocomplete
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
	
	/* Input Date */
	$(function(){
		jQuery("#criteria_startWorkDate").input_dateformat({
			dateformat : "dd_sl_mm_sl_yyyy" ,
			selectDateRange: { 
	            dateTo: "criteria_endWorkDate" // ??????????????????
	        }
	    });
		jQuery("#criteria_endWorkDate").input_dateformat({
			dateformat : "dd_sl_mm_sl_yyyy" ,
			selectDateRange: {
				dateFrom: "criteria_startWorkDate" 	// ????????????????	
			} 
		});
	});

	function searchPage() {
        document.getElementsByName('criteria.criteriaKey')[0].value = '';
        searchAjax()
    }
	
	function searchAjax(){
		var aOption = {
			divResultId: "div_datatable",
			tableId: "tableResult",
			checkbox:"Y",
			urlSearch: "<s:url value='/jsp/tutorial/searchEmployee.action' />",
			urlEdit: {
                url: "<s:url value='/jsp/tutorial/gotoEditEmployee.action' />",
                authen: "<s:property value='ATH.edit' />"
            },
            urlView: {
                url: "<s:url value='/jsp/tutorial/gotoViewEmployee.action' />",
                authen: "<s:property value='ATH.view' />"
            },
			pk: "employee.id",
			fixedCoumnsLeft : 6,	//Fixed column ทางซ้ายเริ่มตั้งแต่ 0-5
			createdRowFunc: "manageRow"
		};
		
		var colData = [
			{ data: null,                       class: "order",             orderable: false, "width":"50px"},
			{ data: null,                       class: "d_checkbox center", orderable: false, "width":"30px", defaultContent: ""},
			{ data: null,                       class: "d_edit center",     orderable: false, "width":"30px", defaultContent: ""},
			{ data: null,                       class: "d_view center col_view",     orderable: false, "width":"30px", defaultContent: ""},
			{ data: "fullname",                 class: "left",   orderable: false, "width":"100px"},
			{ data: "sex",                      class: "col-width-auto",    orderable: false, "width":"50px"},
			{ data: "departmentDesc",           class: "left",   orderable: false, "width":"200px"},
			{ data: "positionDesc",             class: "left",   orderable: false, "width":"150px"},
			{ data: "startWorkDate",            class: "center", orderable: false, "width":"80px"},
			{ data: "endWorkDate",              class: "center", orderable: false, "width":"80px"},
			{ data: "workStatus",               class: "left",   orderable: false, "width":"150px"},
			{ data: "transaction.createUser",   class: "left",   orderable: false, "width":"80px"},
			{ data: "transaction.createDate",   class: "center", orderable: false, "width":"80px"},
			{ data: "transaction.updateUser",   class: "left",   orderable: false, "width":"80px"},
			{ data: "transaction.updateDate",   class: "center", orderable: false, "width":"80px"},
			{ data: "transaction.updateRemark", class: "left",   orderable: false, "width":"80px"},
		];
		
		fixedColumns("<%=request.getContextPath()%>", colData , aOption);
		<%-- dataTable("<%=request.getContextPath()%>", colData, aOption); --%>
    }
	
	 function manageRow(row, data) {
         jQuery(".thaiName", row).html("<span title='"+data.fullThaiName+"'>"+data.thaiName+"</span>");
         jQuery(".documentType", row).html("<span title='"+data.fullDocumentType+"'>"+data.documentType+"</span>");
         jQuery(".engName", row).html("<span title='"+data.fullEngName+"'>"+data.engName+"</span>");
     }
	
    function clearPage() {
    	submitPage("<s:url value='/jsp/tutorial/initEmployee.action' />");
    }
    
    function addPage(){
        submitPage("<s:url value='/jsp/tutorial/gotoAddEmployee.action' />");
    }
    
    /**
     * ใช้สำหรับ submit กรณีเพิ่ม, แก้ไข, แสดง
     * ต้องใช้ร่วมกับไฟล์ inputmethod.js
     * @param action
     * @param emName (hidden name ของ id ใน model)
     * @param valId
     */
    function submitAction(action, elName, valId){
        if(valId != null && valId != ""){
            document.getElementsByName(elName)[0].value = valId;
        }
        submitPage(action);
    }
    
</script>