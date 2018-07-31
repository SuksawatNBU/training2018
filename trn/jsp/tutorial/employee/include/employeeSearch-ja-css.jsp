<%@ page contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">

	function sf() {
		
	}

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

	function searchPage() {
        document.getElementsByName('criteria.criteriaKey')[0].value = '';
        searchAjax()
    }
	
	function searchAjax(){
		var aOption = {
			divResultId: "div_datatable",
			tableId: "tableResult",
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
			fixedCoumnsLeft : 5,	//Fixed column ทางซ้ายเริ่มตั้งแต่ 0-5
			fixedCoumnsRight : 0,   //Fixed column ทางขวาเริ่มตั้งแต่ 0-0
			createdRowFunc: "manageRow"
		};
		
		var colData = [
			{ data: null,                       class: "order",             orderable: false, "width":"50px"},
			{ data: null,                       class: "d_checkbox center", orderable: false, "width":"30px", defaultContent: ""},
			{ data: null,                       class: "d_edit center",     orderable: false, "width":"30px", defaultContent: ""},
			{ data: null,                       class: "d_view center",     orderable: false, "width":"30px", defaultContent: ""},
			{ data: "fullname",                 class: "left",   orderable: false, "width":"100px"},
			{ data: "sex",                      class: "left",   orderable: false, "width":"50px"},
			{ data: "departmentDesc",           class: "left",   orderable: false},
			{ data: "positionDesc",             class: "left",   orderable: false},
			{ data: "startWorkDate",            class: "center", orderable: false},
			{ data: "endWorkDate",              class: "center", orderable: false},
			{ data: "workStatus",               class: "left",   orderable: false},
			{ data: "transaction.createUser",   class: "left",   orderable: false},
			{ data: "transaction.createDate",   class: "center", orderable: false},
			{ data: "transaction.updateUser",   class: "left",   orderable: false},
			{ data: "transaction.updateDate",   class: "center", orderable: false},
			{ data: "transaction.updateRemark", class: "left",   orderable: false},
		];
		
		dataTable("<%=request.getContextPath()%>", colData, aOption);
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
    function editPage(id){
        submitPage("<!--Action Class to submit'-->");
    }
    function viewPage(id){
        submitPage("<!--Action Class to submit'-->");
    }
    
    
    
    
</script>