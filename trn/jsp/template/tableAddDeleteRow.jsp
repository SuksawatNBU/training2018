<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>

<script type="text/javascript">

	function deleteRow_by_elname(div_elname,tableId){ 	
		//เรียก function กลาง ลบแถว
		//param1 : ชื่อ checkbox ของหน้าหลัก
		//param2 : id ตารางของหน้าหลัก
		//param3 : id ที่เก็บค่า ids ไว้  where not in ที่หน้า popup
		
		// Arunya.k		
		tableDeleteRow( jQuery('div#' + div_elname + ' input[name=\'cnkColumnId\']:checked'), tableId,jQuery('div#' + div_elname + '  #idsSelectedRow'));
		//console.info(jQuery('div#' + div_elname + '  #idsSelectedRow'));
		
		//uncheck checkbox all
		jQuery("input[name='checkAll']").prop('checked', false); 
		
		setClassAll();
	}
</script>
</head>
	<!-- ******************************* DIV RESULT ********************************* -->		
	
	<!-- ******************************* HEADER ********************************* -->
	<table class="TOTAL_RECORD" id="headerTable_<s:property value='#divresult[0]'/>" style="display: none">
		<tr>
			<td class="LEFT"></td>
			<td class="RIGHT"></td>
		</tr>
	</table>
	<table id="model_<s:property value='#divresult[0]'/>" style="display: none;" >
		<!-- ******************************* COLUMN HEADER ********************************* -->				
		<tr class="th">
			<th class="order ui-state-default"><s:text name="no" /></th>
			<th class="checkbox ui-state-default"><input type="checkbox"  name="checkAll"  onClick="checkboxToggle( jQuery('div#<s:property value="#divresult[0]"/>  input[name=\'cnkColumnId\']'), this.checked)" /></th>		
													
			<s:iterator value="columnName" status="statusH" var="column_name">		
				<th class="<s:property value="#columnCSSClass[#statusH.index]" /> ui-state-default"><s:property value = "#column_name"/></th>
			</s:iterator>																
		</tr>
		
		<s:set var="listAddEditNameArray" value='%{#listTableData.split("[.]")}'/>
		<!-- Loop get object from string name -->
		<s:iterator value="listAddEditNameArray" status="statusAddEdit" var="listAddEdit" >
			<s:if test="#statusAddEdit.index == 0">
				<s:set var="listAddEditData"  value="[#listAddEdit]" />
			</s:if>	
			<s:else>
				<s:set var="listAddEditData"  value="#listAddEditData[#listAddEdit]" />
			</s:else>									
		</s:iterator>
		
		<!-- ******************************* COLUMN DETAIL ********************************* -->
		<s:iterator value="#listAddEditData" status="status" var="domain" >	
		
			<s:if test="#status.even == true">
				<s:set var="tabclass" value="%{'LINE_EVEN'}" />
			</s:if>
			<s:else>
				<s:set var="tabclass" value="%{'LINE_ODD'}" />
			</s:else>
			
			<s:if test='#domain.deleteFlag == "Y"'>
				<tr class="td <s:property value='tabclass'/>" style="display: none;">
			</s:if>
			<s:else>
				<tr class="td <s:property value='tabclass'/>">
			</s:else>
				<td class="order"><s:property value="%{#status.index + 1}"/></td>
				<td class="checkbox">
					<!-- columnID -->
					<s:iterator value="columnID" status="status" var="cId" >
						<s:set var="cIds" value='%{#cId.split("[.]")}'/>
							<s:iterator value="cIds" status="status" var="cIdss" >
								<s:if test="#status.index == 0">
									<s:set var="dataId"  value="#domain[#cIdss]" />
								</s:if>	
								<s:else>
									<s:set var="dataId"  value="#dataId[#cIdss]" />
								</s:else>									
						</s:iterator>
					</s:iterator>
					
<!-- 					Arunya.k -->
					<input type="checkbox" id="cnkColumnId" name="cnkColumnId" value="<s:property value="#dataId"/>"/> <!-- checkbox For Check Popup not in? -->
						
					<!-- hiddenData หลัก -->
					<input type="hidden" name="<s:property value='#listTableData'/>[<s:property value='#status.index ' />].id" value="<s:property value="#domain.id"/>"/> <!-- id For Check Added? -->
					<input type='hidden' name="<s:property value='#listTableData'/>[<s:property value='#status.index ' />].deleteFlag" value='<s:property value="#domain.deleteFlag"/>'/> <!-- deleteFlag For Check Deleted? -->
						
					<!-- hiddenData เพิ่มเติม -->
					<s:iterator value="hiddenData" status="statusHidd" var="hidden" >	
						<s:set var="hiddens" value='%{#hidden.split("[.]")}'/>
							<s:iterator value="hiddens" status="status" var="hiddenss" >	
								<s:if test="#status.index == 0">
									<s:set var="subHidd"  value="#domain[#hiddenss]" />
								</s:if>
								<s:else>
									<s:set var="subHidd"  value="#subHidd[#hiddenss]" />
								</s:else>
						</s:iterator>						
						<input type="hidden" name="<s:property value='#listTableData'/>[<s:property value='#status.index ' />].<s:property value="#hidden"/>" value="<s:property value="#subHidd"/>" />
					</s:iterator>						
				</td>
				
				<!-- columnData -->
				<s:iterator value="columnData" status="status" var="key" >	
					<s:set var="keys" value='%{#key.split("[.]")}'/>
						<s:iterator value="keys" status="status" var="keyss" >		
						<s:if test="#status.index == 0">
							<s:set var="indexs"  value="#domain[#keyss]" />
						</s:if>
						<s:else>
							<s:set var="indexs" value="#indexs[#keyss]" />
						</s:else>														
						</s:iterator>	
						<td class="<s:property value="#columnCSSClass[#status.index]" /> <s:property value="#key" /> "><s:property value="#indexs" escapeHtml="false"/></td>				
				</s:iterator>
			</tr>
		</s:iterator>	
	</table>

	<!-- ******************************* FOOTER ********************************* -->
	<table class="RESULT_FOOTER" id="footerTable_<s:property value='#divresult[0]'/>" style="display: none">
		<tr>
			<td class="LEFT">
				<a href="javascript:void(0);" id="btnDel" onclick="deleteRow_by_elname('<s:property value="#divresult[0]"/>','tableId_<s:property value='#divresult[0]'/>');"><img src="<s:url value='/images/icon/i_del.png' />">&nbsp;<s:text name="deleteSelect" /></a>&nbsp;&nbsp;
				<s:hidden id="idsSelectedRow" value=""/> <!-- variable for popup -->
			</td>
			<td class="RIGHT"></td>
		</tr>
	</table>
	
	
	<s:set var="stTable" value='%{#settingTable[0].split("[:]")}'/>
	<script>
		<!--crate table -->
		jQuery('div#<s:property value="#divresult[0]"/>').ready(function(){
			var table = new Object();
				table.width = "<s:property value='#stTable[0]'/>";
	        var tOverX = "<s:property value='#stTable[1]'/>";
	        var tOverY = "<s:property value='#stTable[2]'/>";
			if(tOverX == "true"){
				table.overX = true;
		    }else{
		    	table.overX = false;
			}

			if(tOverY == "true"){
				table.overY = true;
			}else{
		    	table.overY = false;
			}
	
			var myTableId = 'tableId_<s:property value="#divresult[0]"/>';
			//1. apply style table
			genTable('<s:property value="#divresult[0]"/>',table,true);
			
			//2. ถ้ามี tr ที่ถูกลบให้ update order & style 
			if(jQuery('table#'+'tableId_<s:property value="#divresult[0]"/>'+' tr[style*="display: none;"]').length>0){
				tableReOrderSetStyleRow('table#'+'tableId_<s:property value="#divresult[0]"/>'+' tr:not([style*="display: none;"])'); 
			}
			
			//3. set ids to idsSelectedRow
			var valueIds;
			jQuery('table#'+'tableId_<s:property value="#divresult[0]"/>'+' tr:not([style*="display: none;"])').each(function(index){
				var that = this;
				var hiddenId = jQuery(that).find("td > input[type='checkbox']").val();
				if(index == 0){
					valueIds = hiddenId;
				}else{
					valueIds = valueIds + "," + hiddenId;
				}
			});

			//  jQuery('#idsSelectedRow').val(valueIds);
			jQuery('div#<s:property value="#divresult[0]"/>  #idsSelectedRow').val(valueIds);
		});
	</script>		
		
	<s:token/>
</html>