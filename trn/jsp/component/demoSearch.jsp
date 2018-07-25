<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<script type="text/javascript">


/* 
 * สำหรับกำหนด data ที่ต้องการแสดง
 * data : attribute ใน object ของ listResult
 * class : Style class 
 * orderable : defaultis true
 * defaultContent : ค่า default ที่แสดง
 */
var colData = [
			{ data: null, class: "center order", orderable: false, defaultContent: ""},
			{ data: "serviceCode",class: "serviceCode", "width":"250px"},
			{ data: "serviceDesc", class: "serviceDesc", "width":"auto"},
			];
			
			
function sf(){
   
    if(jQuery("[name='criteria.criteriaKey']").val() != ""){
		searchAjax();
	}

}

function searchPage() {
    document.getElementsByName('criteria.criteriaKey')[0].value = '';
    
    
    searchAjax();
    
    
}

function addPage() {
	
	
}

function editPage(id) {
	
}

function clearFormCallBack(){
	/*สำหรับ  ดำเนินกยารต่อต่างๆ หลังจากที่เคลียร์ข้อมูลแล้ว*/
}

function searchAjax(){
	/*วิ่งเข้าไปค้นหาข้อมูล  */
}


</script>
</head>
<body>
	<s:form id="searchForm" name="searchForm" method="post" namespace="/jsp/serviceschedule" action="initManageservice" cssClass="margin-zero" onsubmit="return false;">
		<div class="CRITERIA CRITERIA_1280">
			<table class="FORM">
				<tr style="display: none;">
					<td class="BORDER"> </td>
					<td class="LABEL"></td>
					<td class="VALUE"></td>
					<td class="BORDER"></td>
				</tr>
				<tr>
					<td class="BORDER"></td>
					<td class="LABEL"> <s:text name="cp.carrierType"></s:text><em>&nbsp;&nbsp;</em></td>
					<td class="VALUE">
						<s:textfield name="criteria.carrierType" id="criteria_carrierType" cssStyle="width: 300px" disabled="true" readonly="true"/>
					</td>
					<td class="BORDER"></td>
				</tr>
				<tr>
					<td class="BORDER"></td>
					<td class="LABEL"> <s:text name="cp.carrierName"></s:text><em>&nbsp;&nbsp;</em></td>
					<td class="VALUE">
						<s:textfield name="criteria.carrierName" id="criteria_carrierName" cssStyle="width: 300px" disabled="true" readonly="true"/>
					</td>
					<td class="BORDER"></td>
				</tr>
				<tr>
					<td class="BORDER"></td>
					<td class="LABEL"> <s:text name="cp.aircraft_call_sign"></s:text><em>&nbsp;&nbsp;</em></td>
					<td class="VALUE">
						<s:textfield name="criteria.serviceCode" id="criteria_serviceCode" cssClass="clearform" cssStyle="width: 300px" maxlength="100"/>
					</td>
					<td class="BORDER"></td>
				</tr>
				<tr>
					<td class="BORDER"></td>
					<td class="LABEL"> <s:text name="recordsPerPage"></s:text><em>&nbsp;&nbsp;</em></td>
					<td class="VALUE">
						<div>
							<s:select cssClass="lpp-style clearform" name="criteria.linePerPage"  list="LPP" />
						</div>
					</td>
					<td class="BORDER"></td>
				</tr>
			</table>
			
				<!------------------------------------- BUTTON ----------------------------------->
			<s:include value="/jsp/template/button.jsp">
				<s:param name="buttonType" value="%{'search'}" />
			</s:include>
		
		</div>	
		
		<div class="RESULT RESULT_1280">
		    <div id="div_datatable" class="ex_highlight_row" style="display: none;">	
		    	<table class="display" id="tableResult">
					<thead>
						<tr>
							<th><s:text name="no"/></th>
							<th><s:text name="cp.aircraft_call_sign"/></th>
							<th><s:text name="cp.aircraft_description"/></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" class="dataTables_empty">Loading data from server</td>
						</tr>
					</tbody>
				</table>
		    </div>
    	</div>

		<s:token/>
	</s:form>
</body>
</html>