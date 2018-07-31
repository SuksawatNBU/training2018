<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pagination-tag" uri="http://util.paginationtagiii" %>

<html>

<head>
<script type="text/javascript">
	var json;
	
	/**
	* initial หน้า dialog กำหนดค่า criteria ตั้งต้น
	* @param divElement: div tag include jsp popup file
	*/
	function initialCriteria(criteriaPopup) {
		jQuery(divPopup + " #criteriaChooseIds").val('');
		jQuery(divPopup + " #criteriaPopup_isNavigate").val(isNavigate);
		jQuery('div' + divPopup + ' #divPageNavigate').html(""); // clear navigate page
		jQuery(":input[type='text']:first").focus(); // set focus อยู่ที่ช่องแรก
		if (criteriaPopup != null) {
			jQuery(divPopup + " #criteriaPopup_headerSortsSelect").val(criteriaPopup.headerSortsSelect);
			jQuery(divPopup + " #criteriaPopup_headerSortsSize").val(criteriaPopup.headerSortsSize);
			for(var i = 0; i < jQuery(divPopup + " #criteriaPopup_headerSortsSize").val(); i++){
				// set criteria for header sort
				jQuery(divPopup + " #seq_" + i).val("0");
				jQuery(divPopup + " #order_" + i).val("ASC");
				jQuery(divPopup + " #column_" + i).val(criteriaPopup.headerSorts[i].column);
				
				// set style sheet
				jQuery(divPopup + " #orderActive_" + i).removeClass().addClass("ORDER_ACTIVE showTooltipH");
				jQuery(divPopup + " #i_triangle_" + i).removeClass().addClass("DataTables_sort_icon css_right ui-icon ui-icon-triangle-1-n");
			}
			
			jQuery(divPopup + " #criteriaPopup_start").val(criteriaPopup.start);
			jQuery(divPopup + " #criteriaPopup_linePerPage").val(criteriaPopup.linePerPage);
			jQuery(divPopup + " #criteriaPopup_totalResult").val(criteriaPopup.totalResult);
			jQuery(divPopup + " #criteriaPopup_checkMaxExceed").val(criteriaPopup.checkMaxExceed);
		}
	}
	
	function clearDialogWindows (divResult) {
		// clear message ที่แสดง
		clearMessage ();

		// clear Table
		clearPopupResult('tableId_'+divResult);
		
		// ซ่อนผลลัพธ์การค้นหา
		jQuery('div'+ divPopup +' #'+divResult).hide();
	}
	
	function clearMessage () {
		// clear message ที่แสดง
		jQuery('div'+ divPopup +' #msgLinkDetailPopup').hide();
		jQuery('div'+ divPopup +' #tblMessagePopup').removeAttr("class").addClass("ui-corner-all");
		jQuery('div'+ divPopup +' #spanMsgPopup').html('');
		jQuery('div'+ divPopup +' #spanMsgPopupIcon').removeAttr('class');
	}
	
	/**
	* วาดข้อมูลลงตารางผลการค้นหาที่หน้า popup
	* @param divElementResult - div ผลลัพธ์จากการค้นหาที่หน้า Popup
	* @param data - JSON data
	* @param message - exception from search
	* @param criteriaPopup - object criteria from popup 
	**/
	function renderData(divElementResult, data, message, criteriaPopup, isView){
		//console.info(renderData);
		
		//clear checkbok, tableResult, message
		checkboxToggle('checkAllPopup', false);
		checkboxToggle('idPopup', false);
		
		clearPopupResult('tableId_'+divElementResult);
		jQuery('div'+ divPopup +' #spanMsgPopup*').html('');
		
		//draw table
		if (data != null && data.length > 0) {
			json = data;
			jQuery(divPopup + ' #'+divElementResult).show();
			jQuery(divPopup + ' #rowResult').loadJSON(data);
			
			
			jQuery(divPopup + ' #totalResultPopup').html(criteriaPopup.totalResult);
			jQuery(divPopup + " #criteriaPopup_totalResult").val(criteriaPopup.totalResult);
			dialogSetStyle('div' + divPopup + ' #tableId_'+divElementResult+' tbody tr', divElementResult, isView);
			
			//console.info(isNavigate);
			//เพิ่ม check กรณี ที่มี navigate และ ต้องเปิด popup ซ้อน โดยที่ตัวต่อไปไม่มี navigate
			var navigateCheckDoublePopup = "";
			if(jQuery("#navigateCheck") != "undefined"){
				navigateCheckDoublePopup = jQuery("#navigateCheck").val();
			}
			//console.info(navigateCheckDoublePopup);
			
			if (isNavigate == 'true' || navigateCheckDoublePopup == "true") {
				refreshDivNavigate(divElementResult);
			}
		} else {
			if (message != null) {
				if (criteriaPopup.totalResult > 0) {
					jQuery('div#'+divElementResult).hide();
					// กรณีข้อมูลมีมากเกินที่ config
					if (isNavigate == 'true') {
						// กรณีมีแบบ navigate แสดง confirm alert ยืนยันการค้นหาข้อมูล
						if(confirm(message) == false){
							return false;
						}
						
						jQuery(divPopup + " #criteriaPopup_checkMaxExceed").val(false);
						var searchAgainFunc = jQuery(divPopup + ' #ajaxFunction').val();
						window[searchAgainFunc]();
					} else {
						// กรณีไม่มี navigate แสดง message ระบุเงื่อนไขเพิ่ม
						setMessagePopup('div' + divPopup, message);
					}
				} else {
					jQuery('div#'+divElementResult).hide();
					setMessagePopup('div' + divPopup, message);	
				}
			}
		}
		
		// setClassAll();
	}
	
	function refreshDivNavigate(){
		var strurl= "<s:url value='/jsp/template/pageNavigateInDivAjax.jsp' />";

		if(jQuery(divPopup + ' #ajaxFunction').val() != "") {
			var start = jQuery(divPopup + " #criteriaPopup_start").val();
			var linePerPage = jQuery(divPopup + ' #criteriaPopup_linePerPage').val();
			var totalResult = jQuery(divPopup + ' #criteriaPopup_totalResult').val();
			var ajaxFunction = jQuery(divPopup + ' #ajaxFunction').val();
			
			strurl = strurl + "?start="+start+"&linePerPage="+linePerPage+"&totalResult="+totalResult+"&criteriaName=criteriaPopup&ajaxScript=getIdCheckBox();"+ajaxFunction+"();";
			jQuery('div' + divPopup + ' #divPageNavigate').load(strurl);
		}
		
		// สำหรับ checked check box ของข้อมูลที่ทำการเลือกไปแล้ว
		// setChecked();
	}
</script>
</head>
	<s:hidden name="criteriaPopup.start" id="criteriaPopup_start"/>
	<s:hidden name="criteriaPopup.totalResult" id="criteriaPopup_totalResult"/>
	<s:hidden name="criteriaPopup.checkMaxExceed" id="criteriaPopup_checkMaxExceed"/>
	<s:hidden name="criteriaPopup.linePerPage" id="criteriaPopup_linePerPage"/>
	<s:hidden name="criteriaPopup.headerSortsSelect" id="criteriaPopup_headerSortsSelect"/> 
	<s:hidden name="criteriaPopup.headerSortsSize" id="criteriaPopup_headerSortsSize"/>
	<s:hidden name="criteriaPopup.chooseIds" id="criteriaChooseIds" />
	<s:hidden id="ajaxFunction" value="%{ajaxFunction}"/>
	<s:hidden id="divresult" value="%{#divresult[0]}"/>
	<s:hidden name="criteriaPopup.navigatePopup" id="criteriaPopup_isNavigate" />
	
	<!-- ******************************* DIV RESULT ********************************* -->
	<table class="TOTAL_RECORD" id="headerTable_<s:property value='#divresult[0]'/>">
		<tr>
			<td class="LEFT"><s:text name='numberOfSearch' />&nbsp;<span id="totalResultPopup"></span>&nbsp;<s:text name='order' /></td>
			<td class="RIGHT">
				<div id="divPageNavigate"></div>
			</td>
		</tr>
	</table>		
	<table id="model_<s:property value='#divresult[0]'/>">
		<!-- ******************************* COLUMN HEADER ********************************* -->				
		<tr class="th">
			<th class="order ui-state-default"><s:text name="no" /></th>
			<th id="isShow_<s:property value='#divresult[0]'/>" class="checkbox ui-state-default"><input type="checkbox"  name="checkAllPopup" onClick="delAllIdsUncheck(this);checkboxToggle('idPopup',this.checked)" /></th>		
													
			<s:iterator value="columnName" status="statusH" var="column_name">
				<th class="cursor <s:property value="#columnCSSClass[#statusH.index]" /> ui-state-default" onmousedown="orderByHeaderWithEventForDialog(<s:property value="#statusH.index" />, 'criteriaPopup', event, '<s:property value="#divresult[0]"/>');<s:property value='#ajaxFunction'/>();">
					<s:hidden name="criteriaPopup.headerSorts[%{#statusH.index}].seq" id="seq_%{#statusH.index}"/>
					<s:hidden name="criteriaPopup.headerSorts[%{#statusH.index}].column" id="column_%{#statusH.index}"/>
					<s:hidden name="criteriaPopup.headerSorts[%{#statusH.index}].order" id="order_%{#statusH.index}"/>
					<table class="RESULT_HEADER_SORT">
						<tr>
							<td class="VALUE">
								<font id="orderActive_<s:property value="#statusH.index" />" class="ORDER_ACTIVE showTooltipH"><s:property value="#column_name" escapeHtml="false"/></font>
							</td>
							<td class="LABEL">
								<span id="i_triangle_<s:property value="#statusH.index" />" class="DataTables_sort_icon css_right ui-icon ui-icon-triangle-1-n"></span>
							</td>
						</tr>
					</table>
				</th>
			</s:iterator>																
		</tr>
		
		<!-- ******************************* COLUMN DETAIL ********************************* -->
		<tr class="td model_<s:property value='#divresult[0]'/>_tr" id="rowResult">
			<td class="order"><span class="rownum"></span></td>
			<td class="checkbox">
				<input type="checkbox" id="idPopup" name="idPopup" onclick="delIdsUncheck(this);"/>
				<input type="hidden" class="idPopup" name="idPopupVal" id="idPopupVal"/>
			</td>
			
			<!--loop columnData -->
			<s:iterator value="columnData" status="status" var="colData" >	
				<td class="<s:property value="#columnCSSClass[#status.index]" /> ">
					<s:set var="colDatas" value='%{#colData.split("[.]")}'/>
					<s:iterator value="colDatas" status="status" var="colDatass" >	
						<span class='<s:property value="#colDatass"/>'>
					</s:iterator>
					<s:iterator value="colDatas" status="status" var="colDatass" >	
						</span>
					</s:iterator>
				</td>	
			</s:iterator>
		</tr>
	</table>
	
	<table id="buttonPopup_<s:property value='#divresult[0]'/>" style="display: none; table-layout: fixed;" class="FORM" >
		<tr>
			<td style="width: 60%; height: 5px;"></td>
			<td style="width: 40%; height: 5px;">
		</tr>
		<tr>
			<td style="width: 60%;"></td>
			<td style="width: 40%;">
				<button id="btnOk"  class="btnAdd" onclick="dialogChooseMultiRecordForNavi('<s:url value="/jsp/dialog/" /><s:property value="#urlPopupAction" />')">
					<s:text name="ok"/>
				</button>
				<button id="bntCloseDialog_<s:property value='#divresult[0]'/>" class="btnCancel"   type="button" onclick="return dialogClose();" >
					<s:text name="closePage"/>
				</button>
			</td>
		</tr>
	</table>

	<s:set var="stTable" value='%{#settingTable[0].split("[:]")}'/>
	<script>
		<!--crate table -->
		jQuery('div#<s:property value="#divresult[0]"/>').ready(function(){
			var table = new Object();
				table.width = "<s:property value='#stTable[0]'/>";
	
			var tOverX = "<s:property value='#stTable[1]'/>"; //scoll ล่าง
			var tOverY = "<s:property value='#stTable[2]'/>"; //scoll ข้าง
	
			if (tOverX == "true") {
				table.overX = true;
		    } else {
		    	table.overX = false;
			}
		
			if  (tOverY == "true") {
				table.overY = true;
		    } else {
		    	table.overY = false;
			}
		
			//1. apply style table
			genTable('<s:property value="#divresult[0]"/>', table, true);
			
			//2. move button popup to div table
			var buttonPopup = jQuery("#buttonPopup_<s:property value='#divresult[0]'/>");
			jQuery("#<s:property value='#divresult[0]'/>").append(buttonPopup);
		});
	</script>
</html>