<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<script type="text/javascript">

	function sf() {}
	
	/* Auto Complete */
	jQuery(document).ready(function(){
		// เรียกใช้งาน Autocomplete widget สำหรับสร้าง Autocomplete
	    jQuery("#employee_departmentId").autocompletezAjax([{  // UI มาตรฐาน   
	        url: "<s:url value='/departmentAutoSelectItemServlet'/>",  // url สำหรับ request ข้อมูล
	        defaultKey: "",     // กำหนดค่า key ตัวแรกของ Autocomplete
	        defaultValue: ""    // กำหนดค่า value ตัวแรกของ Autocomplete
	    },{
	        inputModelId: 'employee_positionId',
	        url: "<s:url value='/positionAutoSelectItemServlet'/>",
	        postParamsId: {departmentId: "employee_departmentId"},
	        defaultKey: "",
	        defaultValue: ""
	    }]);
	});
	
	/* Input Date */
	$(function(){
		jQuery("#employee_startWorkDate").input_dateformat({
			dateformat : "dd_sl_mm_sl_yyyy"
	    });
	});
	
	function saveAdd(){
	    //1.ขั้นตอนการตรวจสอบ validate
	    if(!validateAll()){
	        return false;
	    }
	    //2.Confirm dialog
	    if(confirm('<s:text name="50003" />') == false){ 
	        return false;
	    }
	    submitPage("<s:url value='/jsp/tutorial/addEmployee.action' />");     
	};
	
	function saveEdit(){
	    //1.ขั้นตอนการตรวจสอบ validate
	    if(!validateAll()){
	        return false;
	    }
	    //2.Confirm dialog
	    if(confirm('<s:text name="50004" />') == false){  
	        return false;
	    }
	    submitPage("<s:url value='/jsp/tutorial/editEmployee.action' />");    
	}
	
	/* กดปุ่มยกเลิกเพิ่ม   */
	function cancelAdd() {
		if(!confirm("<s:text name='50008' />")){
			return false;
		}
		submitPageForm();
	}
	
	/* กดปุ่มยกเลิกแก้ไข  */
	function cancelEdit(){
		if(!confirm("<s:text name='50009' />")){
			return false;
		}
		submitPageForm();
	}
	
	/* กดปุ่มยกเลิกแสดง */
	function cancelView(){
		if (!confirm('<s:text name="50010" />')) {
            return false;
        }
		submitPageForm();
	}
	
</script>