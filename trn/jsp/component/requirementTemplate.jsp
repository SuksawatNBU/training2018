<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
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
								ข้อความที่ใช้งานในระบบทั้งหมด จะทำการดึงมาจาก table CON_MESSAGE ซึ่งปัจจุบันได้ทำการ gen เป็นไฟล์ properties ไว้
									<ul>
										<li> ส่วนของ java จะทำการเรียกใช้งานจาก bundle ที่ชื่อว่า MessageAlert_xx.properties (xx คือ locale ตาม web application ที่ใช้งาน)</li>
										<li>ส่วนของ javascript จะถูกกำหนดการเรียกใช้งานอยู่ที่ไฟล์ javascript.jsp ซึ่งการเรียกใช้งานจะทำการเรียกใช้จาก validateMessage.CODE_10001 เป็นต้น</li>
									</ul>
									<br>
								</span>
							</td>
						</tr>
						
						<tr>
							<td class="order">2</td>
							<td>
								<span class="globalTypColor" >
									ข้อมูลที่ใช้งานร่วมกันในระบบจะถูกเก็บอยู่ที่ CON_GLOBAL_DATA โดยแยกตามประเภทของ CON_GLOBAL_TYPE ซึ่งจะถูก load เก็บไว้ใน memory ตอน start service การเรียกใช้งานจะอ้างอิงจาก key (key คือค่า CON_GLOBAL_TYPE.GLOBAL_TYPE_CODE ให้ทำการดึงจาก class GlobalType
									<br><font color="red">*** เมื่อมีการ update DB ในส่วนของ table CON_GLOBAL_TYPE และ CON_GLOBAL_DATA ให้ทำการ update class enum GlobalType โดยใช้ค่า GLOBAL_TYPE_CODE เป็นค่าอ้างอิง</font>
									<br>
								</span>
							</td>
						</tr>
						
						<tr>
							<td class="order">3</td>
							<td>
								<span class="globalTypColor" >
								การใช้งาน database
									<ul>
										<li>database ระหว่าง CP และ UCP จะใช้งานร่วมกัน แต่จะแยกตาราง User และตาราง Log ออกจากกันเท่านั้น</li>
										<li>ตาราง SEC_OPERATOR จะใช้ตารางเดียวกัน โดยจะมีการเพิ่มฟิลด์ 1 column ที่จะใช้แยกว่าเป็น เมนู และสิทธิ์ของ Content CP หรือ UCP</li>
										<li>การกำหนด config ของ system จะทำการแยก table โดยของ CP จะใช้ตาราง xxx ส่วนของ UCP จะใช้ตาราง ADM_CONFIG_SYSTEM_UCP</li>
										<li>ตารางที่ใช้ร่วมกันทั้ง CP และ UCP ได้แก่ 
											<ol>CON_GLOBAL_TYPE	ตารางข้อมูลประเภท Config พื้นฐาน</ol>
											<ol>CON_GLOBAL_DATA	ตารางข้อมูล Config พื้นฐาน ตามแต่ละประเภท Config พื้นฐาน</ol>
											<ol>CON_MESSAGE		ตารางข้อมูลข้อความแจ้งเตือนการทำงานระบบ</ol>
											<ol>EMA_EMAIL_CONFIG	ตารางข้อมูล Config สำหรับ mail server</ol>
											<ol>EMA_EMAIL_TEMPLATE	ตารางข้อมูล Template Email</ol>
										</li>
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