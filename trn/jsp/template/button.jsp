<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.cct.common.CommonModel.PageType"%>

<%
	String defaultEnable = "enable";
	
	/*jaiko 20150219  เพิ่มตัวแปรใหม่เพื่อเทียบกับสิทธิที่ ATH จาก CommonAction*/
	String defaultEnable_ATH = "true";
	/**************************************/

	String defaultNextTab = "fristtag";
	String defaultBackTab = "lasttag";
	String[] buttonAttribute = null;
	String buttonType = "";
	String criteriaName = "criteria";

	String[] functionAttribute = null;
	String function = "";

	try {
		if (request.getParameter("buttonType") != null) {
			buttonType = request.getParameter("buttonType").trim();
			buttonAttribute = buttonType.split(",");
			if (buttonAttribute.length < 2) {
				buttonAttribute = null;
				return;
			} else {
				for (int i = 0; i < buttonAttribute.length; i++) {
					buttonAttribute[i] = buttonAttribute[i].trim();
				}
			}
		}

		if (request.getParameter("criteriaName") != null) {
			criteriaName = request.getParameter("criteriaName").toString();
		}
		request.setAttribute("criteriaName", criteriaName);

		if (request.getParameter("function") != null) {
			function = request.getParameter("function").trim();
			functionAttribute = function.split(",");
		}
	} catch (Exception ex) {
		//ex.printStackTrace();
	}

	//		x	search >> ค้นหา + ล้างหน้าจอ = search,enable,fristtag,lasttag
	//		x	add >> บันทึก + ยกเลิก = add,enable,fristtag,lasttag
	//		x	edit >> แก้ไข + ยกเลิก  = edit,enable,fristtag,lasttag
	//		x	view >> ปิดหน้าจอ  = view,enable,fristtag,lasttag
	//		x	report >> พิมพ์ + ล้างหน้าจอ  = report,enable,fristtag,lasttag
	//		x	print >> พิมพ์ + ยกเลิก  = print,enable,fristtag,lasttag
%>

<script type="text/javascript" src="<s:url value='/js/button/button.js' />"></script>
<script type="text/javascript">
	jQuery(document).ready(function(){
		initButton();
	});
</script>

<table class="BUTTON">
	<tr>
		<td colspan="2" class="CENTER">
			<s:include value="/jsp/template/message.jsp"/>		
		</td>
	</tr>
	<tr>
		<td class="LEFT RIGHT_70"></td>
		<td class="RIGHT LEFT_30">
			<%if (PageType.SEARCH.getPage().equals(buttonAttribute[0])) { %>
				<%-- jaiko 20150219 --%>
				<%if (defaultEnable_ATH.equals(request.getAttribute("ATH.search").toString())) { %>
					<button id="btnSearch"  class="btnSearch"  type="button" onclick="return searchPage();">
						<s:text name="search"/>
					</button>
					<button id="btnRefresh"  class="btnRefresh" type="button" onclick="return clearPage();" >
						<s:text name="clear"/>
					</button>
				<%}else{ %>
					<button id="btnSearch"  class="btnSearch"  type="button" onclick="return false;" tabindex="-1" disabled="disabled">
						<s:text name="search"/>
					</button>
					<button id="btnRefresh"  class="btnRefresh"  type="button" onclick="return clearPage();" >
						<s:text name="clear"/>
					</button>
				<%}%>
			<%} %>
			
			<%if(PageType.EDIT.getPage().equals(buttonAttribute[0])) { %>
				<%-- jaiko 20150219 --%>
				<%if (defaultEnable_ATH.equals(request.getAttribute("ATH.edit").toString())) { %>
					<button id="btnEdit"  class="btnEdit"  type="button" onclick="return saveEdit();">
						<s:text name="edit"/>
					</button>
					<button id="btnCancel"  class="btnCancel"  type="button" onclick="return cancelEdit();" >
						<s:text name="cancel"/>
					</button>
				<%}else{ %>
					<button id="btnEdit"  class="btnEdit"  type="button" onclick="return false;" tabindex="-1" disabled="disabled">
						<s:text name="edit"/>
					</button>
					<button id="btnCancel"  class="btnCancel"   type="button" onclick="return cancelEdit();" >
						<s:text name="cancel"/>
					</button>
				<%}%>
				<s:hidden name="urlEdit"/>
			<%}%>

			<%if (PageType.ADD.getPage().equals(buttonAttribute[0])) { %>
				<%-- jaiko 20150219 --%>
				<%if (defaultEnable_ATH.equals(request.getAttribute("ATH.add").toString())) { %>
					<button id="btnAdd"  class="btnAdd"   type="button" onclick="return saveAdd();">
						<s:text name="save"/>
					</button>
					<button id="btnCancel"  class="btnCancel"   type="button" onclick="return cancelAdd();" >
						<s:text name="cancel"/>
					</button>
				<%}else{ %>
					<button id="btnAdd"  class="btnAdd"  type="button" onclick="return false;" tabindex="-1" disabled="disabled">
						<s:text name="save"/>
					</button>
					<button id="btnCancel"  class="btnCancel"   type="button" onclick="return cancelAdd();" >
						<s:text name="cancel"/>
					</button>
				<%} %>
			<%} %>

			<%if (PageType.VIEW.getPage().equals(buttonAttribute[0])) { %>
				<%-- jaiko 20150219 --%>
				<%if (defaultEnable_ATH.equals(request.getAttribute("ATH.view").toString())) { %>
					<%if(functionAttribute != null && functionAttribute.length >= 1){%>
						<button id="btnCancel"  class="btnCancel"  type="button" onclick="return <%=functionAttribute[0]%>;">
							<s:text name="closePage"/>
						</button>
					<%}else{%>
						<button id="btnCancel"  class="btnCancel"   type="button" onclick="return cancelView();" >
							<s:text name="closePage"/>
						</button>
					<%}%>
				<%}%>
			<%} %>

			<%if (PageType.REPORT.getPage().equals(buttonAttribute[0])) { %>
				<%if (defaultEnable.equals(buttonAttribute[1])) { %>
					<button id="btnPrint"  class="btnPrint"  type="button" onclick="return print();">
						<s:text name="printButton"/>
					</button>
					<button id="btnClear"  class="btnClear"   onclick="return clearPage();" type="button" >
						<s:text name="clear"/>
					</button>
				<%} else { %>
					<button id="btnPrint"  class="btnPrint"   type="button" onclick="return false;" tabindex="-1" disabled="disabled">
						<s:text name="printButton"/>
					</button>
					<button id="btnClear"  class="btnClear"   type="button" onclick="return clearPage();" >
						<s:text name="clear"/>
					</button>
				<%} %>
			<%} %>
			
			<%if (PageType.PRINT.getPage().equals(buttonAttribute[0])) { %>
				<%if (defaultEnable.equals(buttonAttribute[1])) { %>
					<button id="btnPrint"  class="btnPrint"  type="button" onclick="return print();">
						<s:text name="printButton"/>
					</button>
					<button id="btnCancel"  class="btnCancel"   type="button" onclick="return cancel();" >
						<s:text name="cancel"/>
					</button>
				<%} else { %>
					<button id="btnPrint"  class="btnPrint"   onclick="return false;" tabindex="-1" disabled="disabled">
						<s:text name="printButton"/>
					</button>
					<button id="btnCancel"  class="btnCancel"   type="button" onclick="return cancel();" >
						<s:text name="cancel"/>
					</button>
				<%} %>
			<%} %>

			<%if (PageType.SEARCH_DIALOG.getPage().equals(buttonAttribute[0])) { %>
				<%if(functionAttribute.length >= 3){%>
					<%if (defaultEnable.equals(buttonAttribute[1])) { %>
						<button id="btnSearch"  class="btnSearch"  type="button" onclick="return <%=functionAttribute[0]%>;">
							<s:text name="search"/>
						</button>
						<button id="btnRefresh"  class="btnRefresh" type="button" onclick="return <%=functionAttribute[1]%>;" >
							<s:text name="clear"/>
						</button>
						<button id="btnCancel"  class="btnCancel"   type="button" onclick="return <%=functionAttribute[2]%>;" >
							<s:text name="closePage"/>
						</button>
					<%}else{%>
						<button id="btnSearch"  class="btnSearch"  type="button" disabled="disabled" >
							<s:text name="search"/>
						</button>
						<button id="btnRefresh"  class="btnRefresh" type="button" onclick="return <%=functionAttribute[1]%>;" >
							<s:text name="clear"/>
						</button>
						<button id="btnCancel"  class="btnCancel"   type="button" onclick="return <%=functionAttribute[2]%>;" >
							<s:text name="closePage"/>
						</button>
					<%}%>
				<%}%>
			<%}%>
			
			
			<%if (PageType.CHOOSE_DIALOG.getPage().equals(buttonAttribute[0])) { %>
				<%if(functionAttribute.length >= 2){%>
					<%if (defaultEnable.equals(buttonAttribute[1])) { %>
						<button id="btnOk"  class="btnAdd"  type="button" onclick="return <%=functionAttribute[0]%>;">
							<s:text name="ok"/>
						</button>
						<button id="btnCancel"  class="btnCancel" type="button" onclick="return <%=functionAttribute[1]%>;" >
							<s:text name="closePage"/>
						</button>
					<%}else{ %>
						<button id="btnOk"  class="btnOK" onclick="return false;" tabindex="-1" disabled="disabled">
							<s:text name="ok"/>
						</button>
						<button id="btnCancel"  class="btnCancel"   type="button" onclick="return <%=functionAttribute[1]%>;" >
							<s:text name="cancel"/>
						</button>
					<%} %>
				<%}%>
			<%}%>
		</td>
	</tr>
	<tr>
		<td style="height: 5px;"></td>
		<td style="height: 5px;"></td>
	</tr>
</table>

<script type="text/javascript">
	// สำหรับลบ input ส่วนเกิน
	jQuery(document).ready(function() {
		jQuery("input[name='" + '<s:property value="%{#request.criteriaName}"/>' + ".criteriaKey']").each(function(index) {
			//console.info(index);
			if (index > 0) {
				jQuery(this).remove();
			}
		});

		jQuery("input[name='P_CODE']").each(function(index) {
			if (index > 0) {
				jQuery(this).remove();
			}
		});

		jQuery("input[name='F_CODE']").each(function(index) {
			if (index > 0) {
				jQuery(this).remove();
			}
		});

		jQuery("input[name='page']").each(function(index) {
			if (index > 0) {
				jQuery(this).remove();
			}
		});
	});
</script>