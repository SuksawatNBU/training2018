<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<script type="text/javascript">

	function sf() {
		
	}
	
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
	
</script>