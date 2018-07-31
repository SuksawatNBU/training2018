<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
	function initDialog() {
		
		clearDialogWindows("popupResult");
		
		var params = {id : ids};
		
		jQuery.ajax({
		    type : "POST",
		    url : "<s:url value='/jsp/tutorial/initForSearchListByIdSystemDialog.action' />",
		    data : jQuery.param(params),
		    async : false,
		    success : function(data) {
// 				console.log(data)
		        if (data != null) {
		            initialCriteria(data.criteriaPopup);
		            renderData("popupResult", data.lstResult, data.messagePopup, data.criteriaPopup, true);
		            
		            updateIcon();
		            
		        } else {
		            alert('ไม่พบข้อมูลที่ต้องการ');
		        }
		    }
		});
	}
	
	function searchListDetail() {
		var params = {id : ids};
	
		jQuery.ajax({
		    type : "POST",
		    url : "<s:url value='/jsp/tutorial/searchListByIdSystemDialog.action' />",
		    data : jQuery.param(params),
		    async : false,
		    success : function(data) {
		        if (data != null) {
		            renderData("popupResult", data.lstResult, data.messagePopup, data.criteriaPopup, true);
		            
		            updateIcon();
		            
		        } else {
		            alert('ไม่พบข้อมูลที่ต้องการ');
		        }
		    }
		});
	}
	
	function updateIcon() {
		jQuery("#popupResult #boxDetailpopupResult span.code").each(function() {
        	if (jQuery(this).html() == "Y") {
        		jQuery(this).html("<img title='Active' src='/training2018/images/icon/i_open.png'>")
        	} else {
        		jQuery(this).html("<img title='Inactive' src='/training2018/images/icon/i_close.png'>")
        	}
        })
	}
	
</script>
    
<html>
	<s:include value="/jsp/template/message_popup.jsp"/>

	<div id="popupResult" style="display:none; width: 100%; height:280px; " ></div>
	
	<s:set var="divresult" value='{"popupResult"}'/>
	<s:set var="columnName" value='{getText("pro.systemName"), getText("pro.createDateDialog"), getText("pro.updateDateDialog"), getText("pro.activeStatus")}'/>
	<s:set var="columnData" value='{"systemName", "createDate", "updateDate", "active.code"}'/>
	<s:set var="columnCSSClass" value='{"", "col-width-200px date","col-width-200px date", "col-width-150px date"}'/>
	<s:set var="ajaxFunction" value='%{"searchListDetail"}'/>
	<s:set var="urlPopupAction" value=''/>
	<s:set var="criteriaName" value='%{"criteriaPopup"}'/>
	<s:set var="settingTable" value='{"1200:false:true"}'/>
	<s:include value="/jsp/template/tableDialogWindows.jsp"/>
	
	<!------------------------------------- BUTTON ----------------------------------->
	<s:set var="buttonType" value='{"CLOSE"}'/>
	<s:set var="buttonFunction" value='{"dialogClose"}'/>
	<s:include value="/jsp/template/button_predifine.jsp"/> 
	
	<s:hidden id="criteriaKey" name="criteriaPopup.criteriaKey" />
	
</html>