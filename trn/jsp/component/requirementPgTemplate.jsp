<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<s:url value='/jsp/component/style-component.css' />"/>
<script type="text/javascript">
	jQuery(document).ready(function(){
		 genCustomTableStyle("divResult",1200,null,null);
	});

	function sf(){
		
	}
	
</script>
<style type="text/css">
	.highlight{
		color: blue;
	}

</style>
</head>
<body>
	<s:form id="searchForm" name="searchForm" method="post" cssClass="margin-zero" onsubmit="return false;">
	<br>
		<a href="<%=request.getContextPath()%>/jsp/initComponent.action" style="padding-left: 20px"><img src="<s:url value='/images/icon/i_search.png' />" /><font color="blue" size="1">Goto page Component</font></a>
	<br>
	<br>
		<h3> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;ข้อกำหนดและมาตรฐาน โครงการ </h3>
		
		<div id="divResult">
		    <div id="divResultBorderCustom">
		        <!-- *********************** 2.div header **************************** -->
		        <div id="divHeaderCustom">
		            <table id="subHeaderCustom">
		                <tr>
							<th class="order">No.</th>
							<th >Detail</th>
		                </tr>
		            </table>
		        </div>
		        <!-- *********************** 2.div header **************************** -->
		         
		        <!-- *********************** 3.div detail **************************** -->
		        <div id="divDetailCustom">
		            <table id="tableId_divDetailCustom">
		            	<tr>
							<td class="order">1</td>
							<td>
								<span class="globalTypColor" >
									<h3>MessageAlert</h3>
									หากต้องการ ใช้  bundle MessageAlert ต่างๆ ให้ทำการเรียกใช้ที่ ไฟล์  MessageAlert_xx.properties (xx คือ locale ตาม web application ที่ใช้งาน)
									<ul>
										<li> <h4>ส่วนของ java </h4> จะทำการเรียกใช้งานจาก bundle ที่ชื่อว่า MessageAlert_xx.properties (xx คือ locale ตาม web application ที่ใช้งาน)
											<ol>วิธีการ</ol>
											<p>
											
											<p class="code">ResourceBundle bundle = BundleUtil.getBundle("resources.bundle.common.MessageAlert", locale);<br>
																bundle.getString("10004");<br>
											
											</p>
											
										</li>
										<li><h4>ส่วนของ javascript</h4> จะถูกกำหนดการเรียกใช้งานอยู่ที่ไฟล์ javascript.jsp ซึ่งการเรียกใช้งานจะทำการเรียกใช้จาก validateMessage.CODE_10001 เป็นต้น
											<ol>วิธีการ</ol>
												** ไฟล์ javascript.jsp เพิ่ม Code ที่ต้องการเรียกใช้ หากยังไม่มี<br>
										<p class="code">
												var validateMessage = { <br>
														CODE_10001: '&lt;s:text name="10001"/&gt;' <br>
												} <br>
										</p>				
												
											<ol><h4>javascript</h4></ol>
											<p class="code"> validateMessage.CODE_10001;</p>	
												
										
										</li>
										
										<li><h4>ส่วนของ jsp </h4>
											<ol>วิธีการ</ol>
											<p class="code" >
												&lt;s:text name="10003"/&gt;
											</p>	
										</li>
									</ul>
									<br>
								</span>
							</td>
						</tr>
						
						<tr>
							<td class="order">2</td>
							<td>
								<span class="globalTypColor" >
									<h3>ค่า Default LinePerPage</h3>
									<p class="code">
										jQuery("#criteria_linePerPage").val(defaultLinePerpage);
									</p>
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