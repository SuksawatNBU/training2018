<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
	function initUserDialog() {
		jQuery('div'+ divPopup +' #criteriaIds').val(ids);
		
		jQuery("#userSearchDialog >table.BUTTON >tbody >tr >td").removeClass("LEFT_80").css("width","200px");
		
		// clear เงื่อนไขการค้นหา 
		jQuery('div'+ divPopup +' #criteriaPopup_user_userId').val('');
		jQuery('div'+ divPopup +' #criteriaPopup_user_givenName').val('');
		jQuery('div'+ divPopup +' #criteriaPopup_user_familyName').val('');
		jQuery('div'+ divPopup +' #criteriaPopup_user_emailAddress').val('');
		jQuery('div'+ divPopup +' #criteriaPopup_user_phoneNumber').val('');
		
		// เรียกใช้งาน function สำหรับการ clear หน้าจอ
		clearDialogWindows ('userSearchResultDialog');
		
		jQuery.ajax({
			type : "POST",
			url : "<s:url value='/jsp/dialog/initUserDialog.action' />",
			data: null,
			async : true,
			success : function(data) {
				// initial criteria popup for sort header table
				initialCriteria(data.criteriaPopup);
				
				//เรียกใช้เพื่อใส่ style ให้กับมัน
		 		initialWidget();
			}
		});
		
	}
	
	function searchUserDialog() {
		jQuery('div'+ divPopup +' #criteriaPopupStart').val(1);
		researchUserDialog();
	}
	
	function researchUserDialog () {
		// clear message ที่แสดง
		clearMessage();
		
		jQuery.ajax({
			type : "POST",
			url : "<s:url value='/jsp/dialog/searchListUserDialog.action' />",
			data : jQuery(divPopup + ' :input').serialize(),
			async : true,
			dataType: "json",
			success : function(data) {
				renderData("userSearchResultDialog", data.lstResult, data.messagePopup, data.criteriaPopup, false);
				
				manageRow();
			}
		});
	}
	/*
	จัด style ข้อมูลในตาราง
	*/
	function manageRow(){

		jQuery('.userStatus').hide();// ซ่อน  user status
		jQuery("#tableId_userSearchResultDialog >tbody >tr").each(function(i){

			
 			//สถานะการใช้งาน
			var status = jQuery(this).find('.userStatus span.active >span.code').html();
			var msgStatus = jQuery(this).find('span.active >span.desc').html();
			imgActive = "<s:url value='/images/icon/i_open.png' />";
			imgNonActive = "<s:url value='/images/icon/i_close.png' />";
			if(status == "Y"){
				jQuery(this).find('td:nth-child(4)').html("<span class='active' style='display:none'><span class='desc'>"+msgStatus+"</span></span><center><img src= '"+ imgActive +"' title='"+msgStatus+"'/></center>");
			}else if(status == "N"){
				jQuery(this).find('td:nth-child(4)').html("<span class='active' style='display:none'><span class='desc'>"+msgStatus+"</span></span><center><img src= '"+ imgNonActive +"' title='"+msgStatus+"'/></center>");
			}
			
		});
		
		jQuery('#boxHeaderuserSearchResultDialog >table >tbody >tr >th').removeClass("cursor").removeAttr("onmousedown");
		jQuery('#boxHeaderuserSearchResultDialog >table >tbody >tr >th >table > tbody >tr >td.VALUE >font').removeClass("ORDER_ACTIVE showTooltipH");
		jQuery('#boxHeaderuserSearchResultDialog >table >tbody >tr >th >table > tbody >tr >td.LABEL').remove();
		
	}
	
	/*div สำหรับ กด ดุ detail Error*/
	function showErrorPopupDetail(elName) {
		var html = document.getElementById(elName).innerHTML;
		html = getMessageResponse(html);
		if (html.length > 0) {
			alert(html);
		}
	}
	
</script>
<s:include value="/jsp/template/message_popup.jsp"/>
<table class="FORM">
	<tr style="display: none;">
		<td class="LABEL"></td>
		<td class="VALUE"></td>
		<td class="LABEL"></td>
		<td class="VALUE"></td>
	</tr>
	<tr>
		<td class="LABEL"><s:text name="sec.user_id"/><em>&nbsp;&nbsp;</em></td>
		<td class="VALUE"><s:textfield id="criteriaPopup_user_userId" name="criteriaPopup.user.userId" maxlength="35" /></td>
		<td class="LABEL"></td>
		<td class="VALUE"></td>
		
	</tr>
	<tr>
		<td class="LABEL"><s:text name="sec.given_name"/><em>&nbsp;&nbsp;</em></td>
		<td class="VALUE"><s:textfield id="criteriaPopup_user_givenName" name="criteriaPopup.user.givenName"  maxlength="50"/>
		</td>
		<td class="LABEL"><s:text name="sec.family_name" /><em>&nbsp;&nbsp;</em></td>
		<td class="VALUE"><s:textfield id="criteriaPopup_user_familyName" name="criteriaPopup.user.familyName" maxlength="50" />
		</td>
	</tr>
	<tr>
		<td class="LABEL"><s:text name="sec.email_address"/><em>&nbsp;&nbsp;</em></td>
		<td class="VALUE"><s:textfield id="criteriaPopup_user_emailAddress" name="criteriaPopup.user.emailAddress" maxlength="50" /></td>
		<td class="LABEL"><s:text name="sec.telephone_contact" /><em>&nbsp;&nbsp;</em></td>
		<td class="VALUE"><s:textfield id="criteriaPopup_user_phoneNumber" name="criteriaPopup.user.phoneNumber" maxlength="100" /></td>
	</tr>
</table>

<!------------------------------------- BUTTON ----------------------------------->
<s:include value="/jsp/template/button.jsp">
	<s:param name="buttonType" value="%{'search_dialog,enable'}" />
	<s:param name="function" value="%{'searchUserDialog(), initUserDialog(), dialogClose()'}" />
</s:include>
<!------------------------------------- BUTTON ------------------------------------->

<!-- =============== Start Table Template Section ======================== -->
<!-- div ผลลัพธ์จากการค้นหาที่หน้า Popup  -->
<div id="userSearchResultDialog" style="width: 100%; height:350px; " ></div>
<!--ส่วนของการกำหนด parameters -->
<s:set var="divresult" value='{"userSearchResultDialog"}'/>
<s:set var="columnName" value='{"",getText("sec.user_status"),getText("sec.user_id"),getText("sec.fullname"),getText("sec.email_address"),getText("sec.telephone_contact")}'/>
<s:set var="columnData" value='{"active.code","active.desc","userId","fullName","emailAddress","phoneNumber"}'/>
<s:set var="columnCSSClass" value='{"col-width-75px userStatus","col-width-75px","col-width-150px","col-width-auto","col-width-225px","col-width-225px"}'/>
<s:set var="ajaxFunction" value='%{"researchUserDialog"}'/>
<s:set var="urlPopupAction" value='%{"searchListByIdUserDialog.action"}'/>
<s:set var="criteriaName" value='%{"criteriaPopup"}'/>
<s:set var="settingTable" value='{"1400:true:true"}'/>
<!-- include table template -->
<s:include value="/jsp/template/tableDialogWindows.jsp"/>
<!-- ================ End Table Template Section ====================== -->
<s:hidden id="criteriaIds" name="criteriaPopup.user.ids"/>
