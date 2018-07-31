<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.cct.common.CommonUser"%>
<%@ page import="util.web.SessionUtil"%>

<html>
<head>
	<script type="text/javascript">
		function sf(){
			 genCustomTableStyle("divResult",1200,null,null);
		}

		
		function gotoComponent(txt){ 
			action = "<s:url value='/jsp/gotoPageComponent.action' />" + "?comp="+txt;
			submitPage(action);
		}
	</script>
	<style type="text/css">
		.colorH:hover {
		    background-color: #D8D8D8;
		}
	</style>
</head>
<body>
<s:form cssClass="margin-zero" name="template">
		
		
		<div id="divResult">
		    <div id="divResultBorderCustom">
		        <!-- *********************** 2.div header **************************** -->
		        <div id="divHeaderCustom">
		            <table id="subHeaderCustom">
		                <tr>
							<th style="width: 33%">UI</th>
							<th style="width: 33%">SERVER</th>
							<th >รายละเอียดเพิ่มเติม</th>
		                </tr>
		            </table>
		        </div>
		        <!-- *********************** 2.div header **************************** -->
		         
		        <!-- *********************** 3.div detail **************************** -->
		        <div id="divDetailCustom">
		            <table id="tableId_divDetailCustom">
		            	<tr>
							<td style="width: 33%">
								<ul>
										<li><a  class="LINK" href="javascript:void(0);" onclick="gotoComponent('requirementPG');">การใช้งานอื่นๆ ตามมาตรฐานโครงการ</a></li>
										<li><a  class="LINK" href="javascript:void(0);" onclick="gotoComponent('standardComp');">มาตรฐาน การเขียนหน้า JSP</a></li>
										<li><a  class="LINK" href="javascript:void(0);" onclick="gotoComponent('dropdownlist');">DropdownList</a></li>
										<li><a  class="LINK" href="javascript:void(0);" onclick="gotoComponent('autocomplete');">AutoComplete</a></li>
										<li><a  class="LINK" href="javascript:void(0);" onclick="gotoComponent('buttonComp');">Button</a></li>
										<li><a  class="LINK" href="javascript:void(0);" onclick="gotoComponent('validation');">Validate JS  && ClearForm</a></li>
										<li><a  class="LINK" href="javascript:void(0);" onclick="gotoComponent('passwordFormat');">การแสดงรูปแบบรหัสผ่าน</a></li>
									</ul>
									<br>
							
							</td>
							<td style="width: 33%">
								<span class="globalTypColor" >
									<ul>
										<li><a  class="LINK" href="javascript:void(0);" onclick="#">Validate Server</a></li>
									</ul>
									<br>
								</span>
							</td>
							<td>
								<span class="globalTypColor" >
									<ul>
										<li><a  class="LINK" href="javascript:void(0);" onclick="gotoComponent('requirement');">รายละเอียดเพิ่มเติม</a></li>
									</ul>
									<br>
								</span>
							</td>
						</tr>
						
						
						
					</table>
				</div>
			</div>
		</div>
		
		
		



</s:form>
</body>
</html>