<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<script type="text/javascript">
		function sf(){
		    genCustomTableStyle("divResult",1200,null,null);			
		    genCustomTableStyle("divResult2",1200,null,null);		

		}
		
		function searchPage() {
			alert('searchPage');
		}
		
		function backPage() {
			alert('backPage');
		}

		function gotoAddAnother() {
			alert('gotoAddAnother');
		}

		function deleted() {
			alert('deleted');
		}
	</script>
	<style type="text/css">
		.globalTypColor{
			color: #0040FF;
		}
		
		.successColor{
			color: #088A08;
		}
		.unSuccessColor{
			color: #DF3A01;
		}
		.exCode{
			padding: 10px; 
			background-color: #F5DA81; 
			width: 95%; 
			border-radius: 5px; 
			margin-bottom: 20px;
		}
	</style>
	
</head>
<body>
<s:form cssClass="margin-zero" name="template">	
	<br>
		<a href="<%=request.getContextPath()%>/jsp/initComponent.action" style="padding-left: 20px"><img src="<s:url value='/images/icon/i_search.png' />" /><font color="blue" size="1">Goto page Component</font></a>
	<br>
	<br>
	  <h3>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;button</h3> 
		<ul>
			<li><a></a>
				 <ol>
					<li><a class="LINK" href="#divResult">วิธีการใช้งาน</a></li>
					<li><a class="LINK" href="#RemarkDetail" >คำอธิบายการทำงานชองแต่ละปุ่ม </a></li>
				</ol> 					
			</li>
		</ul>
	    <ul>
			<li><a class="">button แต่ละหน้าจอการทำงานให้ทำการ include ไฟล์ “/jsp/template/button.jsp” โดยส่ง parameter ตามรายละเอียดการทำงานแต่ละหน้าจอ ดังคำอธิบายด้านล่าง
			(ภายในไฟล์ button.jsp ได้ทำการ include ไฟล์ “/jsp/template/message.jsp” สำหรับการแสดง message เรียบร้อยแล้ว)
			</a>
			<li><a class="">การ enable / disable ของปุ่มจะเป็นไปตามสิทธิ์การใช้งานหลักของหน้าจอนั้นๆ</a>
			<!--	<ul>
					<li><a></a>
						 <ol>
							<li><a class="LINK" href="javascript:void(0);" onclick="gotoTutorial('/autocomplete/standard');">สร้าง UI, Require field และ Default disabled</a></li>
							<li><a class="LINK" href="javascript:void(0);" onclick="gotoTutorial('/autocomplete/filter');">การทำ filter</a></li>
						</ol> 					
					</li>
				</ul>-->
			
			</li>
		</ul>
	<div id="divResult">
	    <div id="divResultBorderCustom">
	        <!-- *********************** 2.div header **************************** -->
	        <div id="divHeaderCustom">
	            <table id="subHeaderCustom">
	                <tr>
						<th class="order">No.</th>
						<th style="width: 150px;">Caption</th>
						<th style="width: 450px;">Button</th>
						<th >Remark</th>
	                </tr>
	            </table>
	        </div>
	        <!-- *********************** 2.div header **************************** -->
	         
	        <!-- *********************** 3.div detail **************************** -->
	        <div id="divDetailCustom">
	            <table id="tableId_divDetailCustom">
	               	<tr>
	               		<td class="order">1</td>
	               		<td class="successColor" style="width: 150px;">Search <br>
	               			หน้าค้นหา จะประกอบไปด้วยปุ่ม
	               		</td>
	               		<td style="width: 450px;">
	               			<s:include value="/jsp/template/button.jsp">
								<s:param name="buttonType" value="%{'search'}" />
							</s:include>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								 &lt;s:include value="/jsp/template/button.jsp"&gt; <br>
											&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&lt;s:param name="buttonType" value="%{'search'}" /&gt;<br>
								&lt;/s:include&gt;
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">2</td>
	               		<td class="successColor" style="width: 150px;">Add
	               		 <br>
	               			หน้าเพิ่ม
	               		</td>
	               		<td style="width: 450px;">
	               			<s:include value="/jsp/template/button.jsp">
								<s:param name="buttonType" value="%{'add'}" />
							</s:include>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								&lt;s:include value="/jsp/template/button.jsp"&gt; <br>
											&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&lt;s:param name="buttonType" value="%{'add'}" /&gt;<br>
								&lt;/s:include&gt;
							</span>
	               		</td>
	               	</tr>
	              <tr>
	               		<td class="order">3</td>
	               		<td class="successColor" style="width: 150px;">Edit
	               			<br>
	               			หน้าแก้ไข
	               		</td>
	               		<td style="width: 450px;">
	               			<s:include value="/jsp/template/button.jsp">
								<s:param name="buttonType" value="%{'edit'}" />
							</s:include>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								&lt;s:include value="/jsp/template/button.jsp"&gt; <br>
											&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&lt;s:param name="buttonType" value="%{'edit'}" /&gt;<br>
								&lt;/s:include&gt; 
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">4</td>
	               		<td class="successColor" style="width: 150px;">View
	               			<br>
	               			หน้าแสดง
	               		</td>
	               		<td style="width: 450px;">
	               			<s:include value="/jsp/template/button.jsp">
								<s:param name="buttonType" value="%{'view'}" />
							</s:include>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								 &lt;s:include value="/jsp/template/button.jsp"&gt; <br>
											&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&lt;s:param name="buttonType" value="%{'view'}" /&gt;<br>
								&lt;/s:include&gt; 
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">5</td>
	               		<td class="successColor" style="width: 150px;">Register
	               			<br>
	               			หน้าลงทะเบียน
	               		
	               		</td>
	               		<td style="width: 450px;">
	               			<s:include value="/jsp/template/button.jsp">
								<s:param name="buttonType" value="%{'register'}" />
							</s:include>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								&lt;s:include value="/jsp/template/button.jsp"&gt; <br>
											&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&lt;s:param name="buttonType" value="%{'register'}" /&gt;<br>
								&lt;/s:include&gt; 
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">6</td>
	               		<td class="successColor" style="width: 150px;">Login
	               			<br>
	               			หน้า login
	               		</td>
	               		<td style="width: 450px;">
	               			<s:include value="/jsp/template/button.jsp">
								<s:param name="buttonType" value="%{'login'}" />
							</s:include>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								 &lt;s:include value="/jsp/template/button.jsp"&gt; <br>
											&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&lt;s:param name="buttonType" value="%{'login'}" /&gt;<br>
								&lt;/s:include&gt;
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">7</td>
	               		<td class="successColor" style="width: 150px;">Customs
	               		<br>
	               		อื่นๆ: กรณีที่เป็นการแสดงปุ่ม นอกเหนือจากมาตรฐานที่กำหนด
	               		</td>
	               		<td style="width: 450px;">
	               			<s:include value="/jsp/template/button_predifine.jsp">
								<s:set var="buttonType" value='{"CREATE_ANOTHER", "DELETE", "BACK"}'/>
								<s:set var="buttonEnable" value='{ATH.add, false, false}'/>
								<s:set var="buttonFunction" value='{"gotoAddAnother", "deleted", "backPagezzz"}'/>
							</s:include>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								 &lt;s:include value="/jsp/template/button_predifine.jsp"&gt; <br>
											&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&lt;s:set var="buttonType" value='{"CREATE_ANOTHER", "DELETE", "BACK"}'/&gt;<br>
											&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&lt;s:set var="buttonEnable" value='{ATH.add, ATH.delete, true}'/&gt;<br>
											&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&lt;s:set var="buttonFunction" value='{"gotoAddAnother", "deleted", "backPage"}'/&gt;<br>
								&lt;/s:include&gt;
							</span>
							<br><br>
								&nbsp;&nbsp;&nbsp;&nbsp;ให้ใช้ไฟล์ “/jsp/template/button_predifine.jsp” แทน “/jsp/template/button.jsp” โดยจะต้องส่งค่า parameter ดังนี้
								<br>1.	buttonType ระบุชื่อ button ที่ตรงกับที่ทำการสร้างไว้ที่ไฟล์ “/jsp/template/button_predifine.jsp” เช่น CREATE_ANOHER หรือ DELETE เป็นต้น
								<br>2.	buttonEnable ให้ทำการระบุสิทธิ์ที่ต้องการใช้กับปุ่มการทำงาน เช่น ATH.add หรือ ATH.delete เป็นต้น
								<br>3.	buttonFunction ให้ทำการระบุชื่อ function การทำงานที่ต้องการสำหรับปุ่มนั้นๆ เช่น gotoAddAnother หรือ delete เป็นต้น
							<br><br>	&nbsp;&nbsp;&nbsp;&nbsp;หากมีการเพิ่มปุ่มใหม่ ซึ่งนอกเหนือจากที่มีอยู่แล้ว ให้ทำการเพิ่ม ไว้ ที่ไฟล์  button_predefine.jsp โดย value ใช้เป็นเลื่อนไขในการแสดงปุ่ม  ส่วนimage จะเป็นการกำหนดชื่อ  class เช่น<br>
							&lt;img class="iconBack" /&gt; <br>
							 ส่วนการกำหนด อยู่ที่ button.js ซึ่งหากไม่มี icon ที่ต้องการแสดง ให้ทำการเพิ่มที่ไฟล์ button.js function initButton() ได้เลย
							
	               		</td>
	               	</tr>
	            </table>
	        </div>
	        <!-- *********************** 3.div detail **************************** -->
	         
	        <!-- *********************** 4.div footer **************************** -->
	        <div id="divFooterCustom">
	             <!-- 
	             	Table Footer วาด table ส่วนของ icon ตรงมุมด้านล่างและ navigate page
	             -->
	        </div>
	    	<!-- *********************** 4.div footer **************************** -->
	     
	    </div>
	</div>
	<br>
	<p id="RemarkDetail"> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;<font color="red">*** หมายเหตุ: คำอธิบายการทำงานชองแต่ละปุ่ม </font></p>
	<div id="divResult2">
	    <div id="divResultBorderCustom">
	        <!-- *********************** 2.div header **************************** -->
	        <div id="divHeaderCustom">
	            <table id="subHeaderCustom">
	                <tr>
						<th class="order">No.</th>
						<th style="width: 100px;">Button name</th>
						<th style="width: 100px;">Main function</th>
						<th style="width: 300px;">Detail</th>
						<th >Remark</th>
	                </tr>
	            </table>
	        </div>
	        <!-- *********************** 2.div header **************************** -->
	         
	        <!-- *********************** 3.div detail **************************** -->
	        <div id="divDetailCustom">
	            <table id="tableId_divDetailCustom">
	               	<tr>
	               		<td class="order">1</td>
	               		<td class="successColor" style="width: 100px;">Search</td>
	               		<td style="width: 100px;">
	               			searchPage();
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
							สำหรับค้นหาข้อมูลตามเงื่อนไขของหน้าจอการทำงานนั้นๆ
							</span>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
							ก่อนเรียก main function จะมีการเรียกใช้งาน เรียกใช้งาน function validateFormInputAll();
							</span>
	               		</td>
	               		
	               	</tr>
	               	
	               	
	               	<tr>
	               		<td class="order">2</td>
	               		<td class="successColor" style="width: 100px;">Clear</td>
	               		<td style="width: 100px;">
	               			clearForm();<br>
	               			 <font color="red"> ไม่ต้องสร้างเอง</font>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
							สำหรับล้างเงื่อนไขของหน้าจอการทำงานนั้นๆ แต่ถ้าหากมีการกำหนดค่า default ให้ทำการ code ไว้ที่ <font color="red"> function clearFormCallBack()</font> ซึ่งอยู่ที่หน้าจอของตนเอง
							</span>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
							การทำงานของ main function จะอยู่ที่ไฟล์ button.jsp
							</span>
	               		</td>
	               	</tr>
	               	
	               	<tr>
	               		<td class="order">3</td>
	               		<td class="successColor" style="width: 100px;">Back</td>
	               		<td style="width: 100px;">
	               			backPage();
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
							สำหรับกลับไปยังหน้าจอก่อนหน้า
							</span>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
							
							</span>
	               		</td>
	               	</tr>
	               	
	               	<tr>
	               		<td class="order">4</td>
	               		<td class="successColor" style="width: 100px;">Print</td>
	               		<td style="width: 100px;">
	               			printBrowser(); <br>
	               			 <font color="red"> ไม่ต้องสร้างเอง</font>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
							สำหรับพิมพ์หน้า browser ของหน้าจอที่ทำงานอยู่
							</span>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								การทำงานของ mail function อยู่ที่ไฟล์ button.jsp ซึ่งจะทำการ print หน้า browser ให้
							</span>
	               		</td>
	               	</tr>
	               	
	               	<tr>
	               		<td class="order">5</td>
	               		<td class="successColor" style="width: 100px;">Submit</td>
	               		<td style="width: 100px;">
	               			saveAdd();
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
							สำหรับบันทึกเพิ่มข้อมูล
							</span>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								ก่อนเรียก main function จะมีการเรียกใช้งาน เรียกใช้งาน function validateFormInputAll();
							</span>
	               		</td>
	               	</tr>
	               	
	               	<tr>
	               		<td class="order">6</td>
	               		<td class="successColor" style="width: 100px;">Update</td>
	               		<td style="width: 100px;">
	               			saveEdit();
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
							สำหรับบันทึกแก้ไขข้อมูล
							</span>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								ก่อนเรียก main function จะมีการเรียกใช้งาน เรียกใช้งาน function validateFormInputAll();
							</span>
	               		</td>
	               	</tr>
	               	
	               	
	               	<tr>
	               		<td class="order">7</td>
	               		<td class="successColor" style="width: 100px;">Register</td>
	               		<td style="width: 100px;">
	               			Register();
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
							สำหรับลงทะเบียนเพื่อเข้าใช้งานในระบบ
							</span>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								ก่อนเรียก main function จะมีการเรียกใช้งาน เรียกใช้งาน function validateFormInputAll();
							</span>
	               		</td>
	               	</tr>
	               	
	               	<tr>
	               		<td class="order">8</td>
	               		<td class="successColor" style="width: 100px;">Login</td>
	               		<td style="width: 100px;">
	               			Login();
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
							สำหรับเข้าสู่ระบบ
							</span>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								ก่อนเรียก main function จะมีการเรียกใช้งาน เรียกใช้งาน function validateFormInputAll();
							</span>
	               		</td>
	               	</tr>
	               	
	               	<tr>
	               		<td class="order">9</td>
	               		<td class="successColor" style="width: 100px;">Forgot password</td>
	               		<td style="width: 100px;">
	               			forgotPassword();
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
							สำหรับไปหน้าลืมรหัสผ่าน
							</span>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								
							</span>
	               		</td>
	               	</tr>
	               	
	               	
	               	
	               </table>
	              </div>
	            </div>
	           </div>
	          	          
	
	<br><br><br>
</s:form>
</body>
</html>