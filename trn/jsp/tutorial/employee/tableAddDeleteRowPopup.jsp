<%@ page contentType="text/html; charset=UTF-8" pageEncoding = "UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
	
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
		<tr>
			<td class="BORDER"></td>
			<td class="LABEL"><s:label for="criteria_prefixId" value="คำนำหน้าชื่อ" /></td>
			<td class="VALUE"><s:select id="criteria_prefixId" name="criteria.prefixId" list="listPrefix" headerKey="" headerValue="" listKey="key" listValue="value" cssClass ="combox"/></td>
			<td class="LABEL"><s:label for="criteria_fullname" value="ชื่อ-สกุล" /></td>
			<td class="VALUE"><s:textfield id="criteria_fullname" name="criteria.fullname" maxlength="100" cssClass="combox" /></td>
			<td class="BORDER"></td>
		</tr>
		<tr>
			<td class="BORDER"></td>
			<td class="LABEL"><s:label for="criteria_departmentName" value="สังกัด" /></td>
			<td class="VALUE">
				<s:textfield id="criteria_departmentId" name="criteria.departmentId" code-of="autocomplet_department" cssClass="autocomplete" />
				<s:textfield id="criteria_departmentDesc" name="criteria.departmentDesc" text-of="autocomplet_department" cssClass="autocomplete" />
			</td>
			<td class="LABEL"><s:label for="criteria_positionDesc" value="แผนก" /></td>
			<td class="VALUE">
				<s:textfield id="criteria_positionId" name="criteria.positionId" code-of="autocomplete_position" cssClass="autocomplete" />
				<s:textfield id="criteria_positionDesc" name="criteria.positionDesc" text-of="autocomplete_position" cssClass="autocomplete" />
			</td>
			<td class="BORDER"></td>
		</tr>
	</table>
			 
	<s:include value="/jsp/template/button.jsp">
		<s:param name="buttonType" value="%{'search_dialog,enable'}" />
		<s:param name="function" value="%{'searchPopupCus(), initDialog(), dialogClose()'}" />
	</s:include>
			
	<!-- =============== Start Table Template Section ======================== -->
	<!-- div ผลลัพธ์จากการค้นหาที่หน้า Popup  -->
	<div id="popupResult" style="display:none; width: 100%; height:280px; " ></div>
	<!--ส่วนของการกำหนด parameters -->
	<s:set var="divresult" value='{"listResult"}'/> 
	<s:set var="columnName" value='{getText("emp.fullname"), getText("emp.sex"), getText("emp.department"), getText("emp.position")}'/>
	<s:set var="columnData" value='{"fullname","sex","departmentDesc","positionDesc"}'/>
	<s:set var="columnCSSClass" value='{"col-width-100px","col-width-50px","col-width-200px","col-width-150px"}'/>
	<s:set var="ajaxFunction" value='%{"reSearchPopupCus"}'/>
	<s:set var="criteriaName" value='%{"criteriaPopup"}'/>
	<s:set var="settingTable" value='{"1000:false:true"}'/>
	<!-- include table template -->
	<s:include value="/jsp/template/tableDialogWindows.jsp"/>
	<!-- ================ End Table Template Section ====================== -->
			 
	<s:hidden id="criteriaIds" name="criteriaPopup.customer.ids"/>