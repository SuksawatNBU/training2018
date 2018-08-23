<%@ page contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

	<script type="text/javascript">
		function initDialog() {
		    jQuery('div'+ divPopup +' #criteriaIds').val(ids);
		     
		    // clear เงื่อนไขการค้นหา 
		     
		    // เรียกใช้งาน function สำหรับการ clear หน้าจอ
		    clearDialogWindows ('popupResult');
		     
		    jQuery.ajax({
		        type : "POST",
		        url : "<s:url value='/jsp/tutorial/initEmployeeDialogAction.action' />",
		        data : null,
		        async : true,
		        success : function(data) {
		            // initial criteria popup for sort header table
		            initialCriteria(data.criteriaPopup);
		            console.log("criteriaPopup --> ", data.criteriaPopup);
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
		    
		    /* jQuery.ajax({
		        type : "POST",
		        url : "<s:url value='/jsp/tutorial/searchPopupEmployeeDialogAction.action' />",
		        data : jQuery(divPopup + ' :input').serialize(),
		        async : true,
		        success : function(data) {
		        	renderData("popupResult", data.lstResult, data.messagePopup, data.criteriaPopup, false);
		        }
		    }); */
		 
		    jQuery.ajax({
		        type : "POST",
		        url : "<s:url value='/jsp/tutorial/searchPopupEmployeeDialogAction.action' />",
		        data : jQuery(divPopup + ' :input').serialize(),
		        async : true,
		        success : function(data) {
		            renderData("popupResult", data.lstResult, data.messagePopup, data.criteriaPopup, false);
		        }
		    });
		}
	</script>
	
	<s:include value="/jsp/template/message_popup.jsp"/>
 
	<table class="FORM">
		<tr style="display: none;">
			<td class="BORDER"></td>
			<td class="LABEL"></td>
			<td class="VALUE"></td>
			<td class="LABEL"></td>
			<td class="VALUE"></td>
			<td class="BORDER"></td>
		</tr>
		<%-- <tr>
			<td class="BORDER"></td>
			<td class="LABEL"><s:label for="criteria_prefixId" value="คำนำหน้าชื่อ" /></td>
			<td class="VALUE"><s:select id="criteria_prefixId" name="criteriaPopup.prefixId" list="listPrefix" headerKey="" headerValue="" listKey="key" listValue="value" cssClass ="combox"/></td>
			<td class="LABEL"><s:label for="criteria_fullname" value="ชื่อ-สกุล" /></td>
			<td class="VALUE"><s:textfield id="criteria_fullname" name="criteriaPopup.fullname" maxlength="100" cssClass="combox" /></td>
			<td class="BORDER"></td>
		</tr> --%>
	</table>
			 
	<s:include value="/jsp/template/button.jsp">
		<s:param name="buttonType" value="%{'search_dialog,enable'}" />
		<s:param name="function" value="%{'searchPopupCus(), initDialog(), dialogClose()'}" />
	</s:include>
			
	<!-- =============== Start Table Template Section ======================== -->
	<!-- div ผลลัพธ์จากการค้นหาที่หน้า Popup  -->
	<div id="popupResult" style="display:none; width: 100%; height:280px; " ></div>
	<!--ส่วนของการกำหนด parameters -->
	<s:set var="divresult" value='{"popupResult"}'/> 
	<s:set var="columnName" value='{getText("emp.fullname"), getText("emp.sex"), getText("emp.department"), getText("emp.position")}'/>
	<s:set var="columnData" value='{"fullname","sex","departmentDesc","positionDesc"}'/>
	<s:set var="columnCSSClass" value='{"col-width-100px","col-width-50px","col-width-200px","col-width-150px"}'/>
	<s:set var="ajaxFunction" value='%{"reSearchPopupCus"}'/>
	<s:set var="criteriaName" value='%{"criteriaPopup"}'/>
	<s:set var="settingTable" value='{"1000:false:true"}'/>
	<!-- include table template -->
	<s:include value="/jsp/template/tableDialogWindows.jsp"/>
	<!-- ================ End Table Template Section ====================== -->
			 
	<s:hidden id="criteriaIds" name="criteriaPopup.ids"/>