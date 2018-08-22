<%@ page contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

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
	    elmArr[3] = obj.departmentDesc;
	    elmArr[3] = obj.positionDesc;
	     
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
	
	function initDialog() {
	    jQuery('div'+ divPopup +' #criteriaIds').val(ids);
	     
	    // clear เงื่อนไขการค้นหา 
	    // ...
	     
	    // เรียกใช้งาน function สำหรับการ clear หน้าจอ
	    clearDialogWindows ('popupResult');
	     
	    jQuery.ajax({
	        type : "POST",
	        url : "<s:url value='/jsp/tutorial/initEmployee2.action' />",
	        data : null,
	        async : true,
	        success : function(data) {
	            // initial criteria popup for sort header table
	            /* initialCriteria(data.criteriaPopup); */
	            console.log("data :", data);
	            alert("");
	        }
	    });
	}
	 
	function searchPopupCus() {
	    jQuery('div'+ divPopup +' #criteriaPopupStart').val(1); // ทำการกำหนดค่า start = 1 เสมอ เมื่อมีการ click ปุ่มค้นหา 
	    reSearchPopupCus();
	}
	
	function reSearchPopupCus() {
	    // clear message ที่แสดง
	    clearMessage ();
	 
	    jQuery.ajax({
	        type : "POST",
	        url : "/jsp/tutorial/searchPopupEmployee2.action",
	        data : jQuery(divPopup + ' :input').serialize(),
	        async : true,
	        success : function(data) {
	            renderData("popupResult", data.lstResult, data.messagePopup, data.criteriaPopup, false);
	        }
	    });
	}
</script>

<style>
	
</style>