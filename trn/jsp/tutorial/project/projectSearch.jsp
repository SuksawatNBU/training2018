<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.cct.trn.core.config.parameter.domain.ParameterConfig"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

	function sf() {
		if(jQuery("[name='criteria.criteriaKey']").val() != ""){
            searchAjax();
        }
		
		jQuery("#criteria_departmentId").autocompletezAjax([{
			url: "<s:url value='/departmentAutoSelectItemServlet'/>",
            defaultKey: "",     // กำหนดค่า key ตัวแรกของ Autocomplete
            defaultValue: ""    // กำหนดค่า value ตัวแรกของ Autocomplete
        },{
            inputModelId: 'criteria_spmCode',
            url: "<s:url value='/userAutoSelectItemServlet'/>",
            postParamsId: {departmentId: "criteria_departmentId"},
            defaultKey: "",     // กำหนดค่า key ตัวแรกของ Autocomplete
            defaultValue: ""    // กำหนดค่า value ตัวแรกของ Autocomplete
        }]);
		
		jQuery("#criteria_startDate").input_dateformat({
            dateformat :  "dd_sl_mm_sl_yyyy", // กำหนดรูปแบบ
            selectDateRange: { 
                dateTo: "criteria_endDate" // กำหนดวันที่สิ้นสุด
            }
        });

        jQuery("#criteria_endDate").input_dateformat({
            dateformat :  "dd_sl_mm_sl_yyyy", // กำหนดรูปแบบ
            selectDateRange: { 
                dateFrom: "criteria_startDate" // กำหนดวันที่เริ่มต้น
            }
        });
		
	}
	
	//set status footer
	var footer1 = "<a href='javascript:void(0);' onclick='updateActive(\"Y\",\"<s:text name='50001'/>\");' class='action-link'><img src='<s:url value='/images/icon/i_open.png' />'> <s:text name='std.active' /></a>";
	var footerActive = "<a href='javascript:void(0);' onclick='submitStatus(\"<s:url value='/jsp/tutorial/updateActiveProject.action'/>\",\"Y\",\"<s:text name='50001'/>\");' class='action-link'><img src='<s:url value='/images/icon/i_open.png' />' title='<s:text name='active'/>'> <s:text name='active' /></a>&nbsp;&nbsp;";
	var footerInActive = "<a href='javascript:void(0);' onclick='submitStatus(\"<s:url value='/jsp/tutorial/updateActiveProject.action'/>\",\"N\",\"<s:text name='50002'/>\");' class='action-link'><img src='<s:url value='/images/icon/i_close.png' />' title='<s:text name='inactive'/>'> <s:text name='inactive' /></a>&nbsp;&nbsp;";
	
	function searchAjax(){
		var aOption = {
				divResultId: "div_datatable",
				tableId: "tableResult",
				urlSearch: "<s:url value='/jsp/tutorial/searchProject.action'/>",
				urlEdit: {
					url: "<s:url value='/jsp/tutorial/gotoEditProject.action' />",
					authen: "<s:property value='ATH.edit' />"
				},
// 				urlView: {
// 					url: "<s:url value='/jsp/tutorial/gotoViewProject.action' />",
// 					authen: "<s:property value='ATH.view' />"
// 				},
				pk: "project.id",	//input hidden in footer
				createdRowFunc: "manageRow",
				footerHtml : footerActive+footerInActive
		};
		var colData = [
						{ data: null,class: "order", orderable: false, defaultContent: "", "width":"60px"},
						{ data: null, class: "d_checkbox center", orderable: false, defaultContent: "", "width":"35px"},
						{ data: null, class: "d_edit center", orderable: false, defaultContent: "", "width":"35px"},
						{ data: null, class: "d_view center col_view", orderable: false, defaultContent: "", "width":"35px"},
						{ data: "projectCode", class: "", "width":"120px"},
						{ data: "projectName",class: "col-width-auto"},
						{ data: "spmName", class: "", "width":"230px"},
						{ data: "createDate", class: "center", "width":"150px"},
						{ data: "active",class: "d_status center", "width":"60px"}
						];
		
		dataTable("<%=request.getContextPath()%>", colData, aOption);
    }
	
	function manageRow(row, data) {
		var isView = "<s:property value='ATH.view'/>";
		var htmlIconView = "";
		if (isView) {
			htmlIconView = jQuery("#tempIconViewEnable").html();
			htmlIconView = htmlIconView.replace("xxx", data.id);
			
		} else {
			htmlIconView = jQuery("#tempIconViewDisable").html();
			
		}
		
// 		console.log(htmlIconView)
		jQuery(row).find("td").eq(3).html(htmlIconView);

	}
	
	function searchPage() {
		document.getElementsByName('criteria.criteriaKey')[0].value = '';
		searchAjax();
	}
	
	function clearPage() {
		submitPage("<s:url value='/jsp/tutorial/initProject.action' />");
	}
	
	function addPage(){
		submitPage("<s:url value='/jsp/tutorial/gotoAddProject.action' />");
	}
	
</script>
</head>

<body>
	<s:form id="searchForm" name="searchForm" method="post" cssClass="margin-zero" onsubmit="return false;">
		<div id="divSearchForm" class="CRITERIA">
			<div id="divCriteria" class="RESULT_BOX BORDER_COLOR">
				<div class="RESULT_BOX_TITAL ">
					<table class="TOTAL_RECORD">
						<tr>
							<td class="LEFT" style="width: 10%;">
								<s:text name="criteria"/>
							</td>
						</tr>
					</table>
				</div>
				
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
						<td class="LABEL">
							<s:text name="pro.projectCode" /><em>&nbsp;&nbsp;</em>
						</td>
						<td class="VALUE">
							<s:textfield id="criteria_projectCode" name="criteria.projectCode" maxlength="100" cssClass="combox" />
						</td>
						<td class="LABEL">
							<s:text name="pro.projectName" /><em>&nbsp;&nbsp;</em>
						</td>
						<td class="VALUE">
							<s:textfield id="criteria_projectName" name="criteria.projectName" maxlength="100" cssClass="combox" />
						</td>
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:text name="pro.department" /><em>&nbsp;&nbsp;</em>
						</td>
						<td class="VALUE">
							<!-- AutoComplete -->
							<s:textfield id="criteria_departmentId" name="criteria.departmentId" code-of="criteria_departmentId_autocomplete" cssClass="autocomplete" />
							<s:textfield id="criteria_departmentName" name="criteria.departmentName" text-of="criteria_departmentId_autocomplete" cssClass="autocomplete" />
						</td>
						<td class="LABEL">
							<s:text name="pro.fullname" /><em>&nbsp;&nbsp;</em>
						</td>
						<td class="VALUE">
							<!-- AutoComplete -->
							<s:textfield id="criteria_spmCode" name="criteria.spmCode" code-of="criteria_spmCode_autocomplete" cssClass="autocomplete" />
							<s:textfield id="criteria_spmName" name="criteria.spmName" text-of="criteria_spmCode_autocomplete" cssClass="autocomplete" />
						</td>
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:text name="pro.startDate" /><em>&nbsp;&nbsp;</em>
						</td>
						<td class="VALUE">
							<s:textfield id="criteria_startDate" name="criteria.startDate"/>
						</td>
						<td class="LABEL">
							<s:text name="pro.endDate" /><em>&nbsp;&nbsp;</em>
						</td>
						<td class="VALUE">
							<s:textfield id="criteria_endDate" name="criteria.endDate"/>
						</td>
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:text name="pro.activeStatus" /><em>&nbsp;&nbsp;</em>
						</td>
						<td class="VALUE">
							<s:select name="criteria.activeCode"
								list="lstActiveStatus" 
								headerKey=""
								headerValue="%{getText('all')}" 
								listKey="key" 
								listValue="value"
								cssClass = "combox"
								cssStyle="width: 190px;"
								/>
						</td>
						<td class="LABEL"></td>
						<td class="VALUE"></td>
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:text name="recordsPerPage" /><em>&nbsp;&nbsp;</em>
						</td>
						<td class="VALUE">
							<s:select id="criteria_linePerPage" cssClass="lpp-style clearform" name="criteria.linePerPage"  list="LPP" />
						</td>
						<td class="LABEL"></td>
						<td class="VALUE"></td>
						<td class="BORDER"></td>
					</tr>
					<!-- 
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL"></td>
						<td class="VALUE"></td>
						<td class="LABEL"></td>
						<td class="VALUE"></td>
						<td class="BORDER"></td>
					</tr>
					 -->
				</table>
				
				<!------------------------------------- BUTTON ----------------------------------->
				<s:include value="/jsp/template/button.jsp">
					<s:param name="buttonType" value="%{'search,enable'}" />
				</s:include>
			</div>
		</div>
	
		<br>
			
		<!--------------------------------------- divResult --------------------------------------->
		<div class="RESULT RESULT_SYSTEM">
		    <div id="div_datatable" class="ex_highlight_row" style="display: none;">	
		    	<table class="display" id="tableResult">
					<thead>
						<tr>
							<th><s:text name="pro.no"></s:text></th>
							<th><input type="checkbox" name="chkall" onClick="checkboxToggle('criteria.selectedIds',this.checked)" /></th>
							<th></th>
							<th></th>
							<th><s:text name="pro.projectCode" /></th>
							<th><s:text name="pro.projectName" /></th>
							<th><s:text name="pro.spm" /></th>
							<th><s:text name="pro.createDate" /></th>
							<th><s:text name="pro.status" /></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="8" class="dataTables_empty">Loading data from server</td>
						</tr>
					</tbody>
				</table>
	    	</div>
	   	</div>
	   	
	   	<div id="dialogView" title="<s:text name='pro.projectDetail'/>" style="display: none;">
		    <s:include value="/jsp/tutorial/dialog/systemListDialog.jsp"/>
		</div>
		
		<div id="tempIconViewEnable" style="display: none;">
			<a href="javascript:void(0)" onclick="dialogSingleOpen('dialogView', 1250, 750, true, initDialog, '', 'xxx', 'false');"  style="cursor: pointer;">
			    <img src="<s:url value='/images/icon/i_view.png' />" border="0">
			</a>
		</div>
		<div id="tempIconViewDisable" style="display: none;">
			<a href="javascript:void(0)" >
			    <img src="<s:url value='/images/icon/i_view_dis.png' />" border="0">
			</a>
		</div>

	   	<s:include value="/jsp/template/hiddenSearchForDatatable.jsp" />
	   	<s:hidden name="project.id" />
	   	<!--------------------------------------- End divResult --------------------------------------->
	   	<s:token />
	</s:form>
</body>
</html>