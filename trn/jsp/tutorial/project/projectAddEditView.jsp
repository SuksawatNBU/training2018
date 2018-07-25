<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>

<script type="text/javascript">
	
	function sf() {
		/* set checkbox ให้ check เมื่อค่า Active เป็น Y และ set checkbox ให้ uncheck เมื่อค่า Active เป็น N */
		if(jQuery( "input[name='project.active.code']" ).val()=="Y") {
			jQuery("#chk_active").prop("checked", true);
		}
		else{
			jQuery("#chk_active").prop("checked", false);
		}
		
		jQuery("#project_departmentId").dropdownlist(
				[{
					url: "<s:url value='/departmentAutoSelectItemServlet'/>",
	                defaultKey: "",     // กำหนดค่า key ตัวแรกของ Dropdownlist
	                defaultValue: "",    // กำหนดค่า value ตัวแรกของ Dropdownlist
	                requireInput: true 
             	},{
                 	inputModelId: 'project_spmCode',
                 	url: "<s:url value='/userAutoSelectItemServlet'/>",
                 	postParamsId: {departmentId: "project_departmentId"},
                 	defaultKey: "",     // กำหนดค่า key ตัวแรกของ Dropdownlist
                 	defaultValue: "",    // กำหนดค่า value ตัวแรกของ Dropdownlist
                 	requireInput: true 
             	}]
         );
		
		// Carrier code
// 		jQuery("#project_spmCode").autocompletezAjax([{		// UI มาตรฐาน
//             url: "<s:url value='/qaUserAutoSelectItemServlet'/>",  	// โหลด Country Name ผ่าน servlet
//             defaultKey: "",     	// กำหนดค่า key ตัวแรกของ Autocomplete
//             defaultValue: "",    	// กำหนดค่า value ตัวแรกของ Autocomplete
//             requireInput: false  	// กำหนดให้เป็น Require field
//         }]);
		
	}
	
	jQuery(document).ready(function(){
		/* set ค่า Active เมื่อคลิกเปลี่ยนค่า checkbox */
		jQuery('#chk_active').click(function() {
	        if (!jQuery(this).is(':checked')) {
	        	jQuery( "input[name='project.active.code']" ).val("N");
	        }
	        else{
	        	jQuery( "input[name='project.active.code']" ).val("Y");
	        }
	    });
    });
	
	/* กดปุ่มบันทึก   */
	function saveAdd(){
		// validate old version
		if(!validateAll()){ 
	        return false;
	    }
		
		// validate all version
// 		if(!validateFormInputAll()){
// 			return false;
// 		}
		
		if(!confirm("<s:text name='50006' />")){
			return false;
		}
		submitPage("<s:url value='/jsp/tutorial/addProject.action' />");
	}
	
	/* กดปุ่มแก้ไข   */
	function saveEdit(){
		// validate old version
		if(!validateAll()){ 
	        return false;
	    }
		
		// validate all version
// 		if(!validateFormInputAll()){
// 			return false;
// 		}
		
		if(!confirm("<s:text name='50007' />")){
			return false;
		}
		submitPage("<s:url value='/jsp/tutorial/editProject.action' />");
	}
	
	/* กดปุ่มยกเลิกเพิ่ม   */
	function cancelAdd(){
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
	
	function submitPageForm() {
	     if (document.getElementsByName('criteria.criteriaKey')[0].value == '') {
	        action = "<s:url value='/jsp/tutorial/initProject.action'/>";
	    } else {
	        action = "<s:url value='/jsp/tutorial/cancelProject.action'/>";
	    } 
	    submitPage(action);
	}
	
</script>
</head>
<body>
	<s:form id="addEditForm" name="addEditForm" method="post" namespace="/jsp/tutorial" cssClass="margin-zero" onsubmit="return false;" >
		<!-- set class ให้กับหน้า -->
		<s:if test="page.getPage() == 'view'"> 
		 	<s:set var="readOnly" value="%{'readonly'}" />
		</s:if>
		<s:else> 
			<s:set var="readOnly" value="" />
		</s:else>
		
		<s:if test="page.getPage() == 'edit'">
			<s:set var="projCodeReadOnly" value="%{'readonly'}" />
		</s:if>
		<s:else>
			<s:set var="projCodeReadOnly" value="" />
		</s:else>
		<div class="">
			<table class="FORM" style="float:left; margin:13px;">
				<tbody>
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
							<s:text name="pro.projectCode"/><em>*</em>
						</td>
						<td class="VALUE" colspan="3">
							<s:textfield name="project.projectCode" 
								id="project_projectCode" 
								cssClass ="txt requireInput %{readOnly} %{projCodeReadOnly}" 
								cssStyle="width: 500px;"
								validName="%{getText('pro.projectCode')}" 
								maxlength="255"/>
								
							<s:if test="page.getPage() != 'add'">
								<s:hidden name="project.projectCode"/>
							</s:if>
						</td>
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:text name="pro.projectName"/><em>*</em>
						</td>
						<td class="VALUE" colspan="3">
							<s:textfield name="project.projectName" 
								id="project_projectName" 
								cssClass ="project requireInput %{readOnly}" 
								cssStyle="width: 500px;"
								validName="%{getText('pro.projectName')}" 
								maxlength="255"/>
						</td>
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL">
							<s:text name="pro.department"/><em>*</em>
						</td>
						<td class="VALUE">
							<s:if test="page.getPage() == 'view'">
								<s:textfield id="project_departmentName" 
									name="project.departmentName" 
									cssClass="combox txt %{readOnly}"
									/>
							</s:if>
							<s:else>
								<s:textfield id="project_departmentId" name="project.departmentId" cssClass="autocomplete requireInput" validName="%{getText('pro.department')}" />
								<s:hidden id="project_departmentName" name="project.departmentName" cssClass="autocomplete"  />
							</s:else>
						</td>
						<td class="LABEL">
							<s:text name="pro.spm"/><em>*</em>
						</td>
						<td class="VALUE">
							<s:if test="page.getPage() == 'view'">
								<s:textfield id="project_spmName" 
									name="project.spmName" 
									cssClass="combox txt %{readOnly}"
									/>
							</s:if>
							<s:else>
								<s:textfield id="project_spmCode" name="project.spmCode" cssClass="autocomplete requireInput" validName="%{getText('pro.spm')}" />
								<s:hidden id="project_spmName" name="project.spmName" cssClass="autocomplete" />
							</s:else>
							
						<td class="BORDER"></td>
					</tr>
					<tr>
						<td class="BORDER"></td>
						<td class="LABEL"></td>
						<td class="VALUE">
							<s:checkbox id="chk_active" name="" cssClass="%{readOnly}"/>
							<s:text name="pro.activeStatus"/>
							<s:hidden name="project.active.code"/>
							<s:hidden name="project.active.desc"/>
						</td>
						<td class="LABEL"></td>
						<td class="VALUE"></td>
						<td class="BORDER"></td>
					</tr>
				</tbody>
			</table>
					
			<!--------------------------------------- ADD PAGE --------------------------------------->		
			<s:if test='page.getPage() == "add"'>
				<s:include value="/jsp/template/button.jsp">
					<s:param name="buttonType" value="%{'add,enable'}" />
				</s:include>
			</s:if>
			<!--------------------------------------- EDIT PAGE --------------------------------------->
			<s:elseif test='page.getPage() == "edit"'>
				<s:include value="/jsp/template/button.jsp">
					<s:param name="buttonType" value="%{'edit,enable'}" />
				</s:include>
			</s:elseif>
			<!--------------------------------------- VIEW PAGE --------------------------------------->
			<s:elseif test='page.getPage() == "view"'>
				<s:include value="/jsp/template/button.jsp">
					<s:param name="buttonType" value="%{'view,enable'}" />
				</s:include>
			</s:elseif>
				
			<s:textfield name="criteria.criteriaKey" />
			<s:textfield name="P_CODE"/>
		    <s:textfield name="F_CODE"/>
		    <s:textfield name="page"/>
			<s:textfield name="project.id" />

			<s:token />
		</div>
	</s:form>
</body>
</html>