<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<script type="text/javascript">
		
		function sf(){
		    genCustomTableStyle("divResult",1000,null,null);
		    genCustomTableStyle("divResult2",1000,null,null);		
		    genCustomTableStyle("divResult3",1000,null,null);	
		    
		 	// เรียกใช้งาน Autocomplete widget สำหรับสร้าง Autocomplete
		  //AUTOCOMPLETE SERVLET
		    jQuery("#autocompleteArrPortNameCode").autocompletezAjax([{	// UI มาตรฐาน
                url: "<s:url value='/PortInterCountrySelectItemServlet'/>",  	// โหลด INTER COUNTRY PORT ผ่าน servlet
                defaultKey: "",     // กำหนดค่า key ตัวแรกของ Autocomplete
                defaultValue: ""    // กำหนดค่า value ตัวแรกของ Autocomplete
            }]);
		 	
		 	
			// เรียกใช้งาน Autocomplete widget สำหรับสร้าง Autocomplete
		 	 //AUTOCOMPLETE SERVLET
		    jQuery("#autocompleteDepPortNameCode").autocompletezAjax([{	// UI มาตรฐาน
                url: "<s:url value='/PortInterWordAppsSelectItemServlet'/>",  	// โหลด inter Word Port ผ่าน servlet
                defaultKey: "",     // กำหนดค่า key ตัวแรกของ Autocomplete
                defaultValue: ""    // กำหนดค่า value ตัวแรกของ Autocomplete
            }]);
			
			
			
			//สร้าง
			
		  //Arrival port
			// เรียกใช้งาน Autocomplete widget สำหรับสร้าง Autocomplete
			  //AUTOCOMPLETE SERVLET
			    jQuery("#autocompleteArrivalPortNameCode").autocompletezAjax([{	// UI มาตรฐาน
	                url: "<s:url value='/PortInterTrigerSelectItemServlet'/>",  	// โหลด INTER COUNTRY PORT ผ่าน servlet
	                postParamsId: {direction: "direction", portType: "arrPortType"},		// param ใส่ ID ของ param ที่จะส่งค่าเข้าไป
	                defaultKey: "",     // กำหนดค่า key ตัวแรกของ Autocomplete
	                defaultValue: "" ,   // กำหนดค่า value ตัวแรกของ Autocomplete
                	requireInput: true,  // กำหนดให้เป็น Require field
                    clearForm: true, // กำหนดค่า สำหรับการ clear value เมื่อมี event ที่ปุ่ม Clear
                    validName:"<s:text name='ss.first_foreign_port'/>" , // กำหนดค่า สำหรับการ validation
                    cssStyle: "text-transform: uppercase;"
	            }]);
			 	
				//Departure port
				// เรียกใช้งาน Autocomplete widget สำหรับสร้าง Autocomplete
			 	 //AUTOCOMPLETE SERVLET
			    jQuery("#autocompleteDepturePortNameCode").autocompletezAjax([{	// UI มาตรฐาน
	                url: "<s:url value='/PortInterTrigerSelectItemServlet'/>",  	// โหลด inter Word Port ผ่าน servlet
	                postParamsId: {direction: "direction", portType: "depPortType"},		// param ใส่ ID ของ param ที่จะส่งค่าเข้าไป
	                defaultKey: "",     // กำหนดค่า key ตัวแรกของ Autocomplete
	                defaultValue: "" ,   // กำหนดค่า value ตัวแรกของ Autocomplete
	                requireInput: true,  // กำหนดให้เป็น Require field
	                clearForm: true, // กำหนดค่า สำหรับการ clear value เมื่อมี event ที่ปุ่ม Clear
	                validName:"<s:text name='ss.first_foreign_port'/>" , // กำหนดค่า สำหรับการ validation
	                cssStyle: "text-transform: uppercase;"
	            }]);
				
				
			  //Departure port Inter Word+ APP + Country
				// เรียกใช้งาน Autocomplete widget สำหรับสร้าง Autocomplete
			 	 //AUTOCOMPLETE SERVLET
			    jQuery("#autocompleteDepWPortNameCode").autocompletezAjax([{	// UI มาตรฐาน
	                url: "<s:url value='/PortInterWordAllSelectItemServlet'/>",  	// โหลด inter Word Port ผ่าน servlet
	                defaultKey: "",     // กำหนดค่า key ตัวแรกของ Autocomplete
	                defaultValue: "" ,   // กำหนดค่า value ตัวแรกของ Autocomplete
	                minLength : fillAtLeast,	// กำหนดค่าพิมพ์อย่างน้อย 3 ตัวอักษรถึงจะค้นหา
	                clearForm: true, // กำหนดค่า สำหรับการ clear value เมื่อมี event ที่ปุ่ม Clear
	                cssStyle: "text-transform: uppercase;"
	            }]);
			  
			  //Arrival port Inter Word+ APP + Country
				// เรียกใช้งาน Autocomplete widget สำหรับสร้าง Autocomplete
			 	 //AUTOCOMPLETE SERVLET
			    jQuery("#autocompleteArrWPortNameCode").autocompletezAjax([{	// UI มาตรฐาน
	                url: "<s:url value='/PortInterWordAllSelectItemServlet'/>",  	// โหลด inter Word Port ผ่าน servlet
	                defaultKey: "",     // กำหนดค่า key ตัวแรกของ Autocomplete
	                defaultValue: "" ,   // กำหนดค่า value ตัวแรกของ Autocomplete
	                minLength : fillAtLeast,	// กำหนดค่าพิมพ์อย่างน้อย 3 ตัวอักษรถึงจะค้นหา
	                clearForm: true, // กำหนดค่า สำหรับการ clear value เมื่อมี event ที่ปุ่ม Clear
	                cssStyle: "text-transform: uppercase;"
	            }]);
				
				
				
			  //Load AutoComplete
			    changeDirection();
			    //Set Function Change
			    jQuery("#direction_id").selectmenu({
					  change: function( event, ui ) {
						//managerDropdownList();
						changeDirection();
					  }
		   		});
		    
		}
		
		function changeDirection(){
			var directionId = jQuery("#direction_id option:selected").val();
			jQuery("#direction").val(directionId);
			
		    var value = {codeValue: "", textValue: ""}
	         jQuery('#autocompleteArrivalPortNameCode').autocompletezAjax('autocompleteValue', 0, value);
	         jQuery('#autocompleteDepturePortNameCode').autocompletezAjax('autocompleteValue', 0, value);
				
		}
		
		
		
	</script>
	<style type="text/css">
		.exCode{
			padding: 10px; 
			background-color: #F5DA81; 
			width: 95%; 
			border-radius: 5px; 
			margin-bottom: 20px;
		}
		
		.exCode2{
			padding: 10px; 
			background-color: #F6C483; 
			width: 95%; 
			border-radius: 5px; 
			margin-bottom: 20px;
		}
		.globalTypColor{
			color: #0040FF;
		}
		.successColor{
			color: #088A08;
		}
	</style>
</head>
<body>
<s:form cssClass="margin-zero" name="template">	
	<br>
		<a href="<%=request.getContextPath()%>/jsp/initComponent.action" style="padding-left: 20px"><img src="<s:url value='/images/icon/i_search.png' />" /><font color="blue" size="1">Goto page Component</font></a>
	<br>
	<br>
	<div id="divResult">
	    <div id="divResultBorderCustom">
	     
	        <!-- *********************** 1.div title **************************** -->
	        <div id="divTitleCustom">
	            <!-- 
	            	Table title สำหรับวาด table ส่วนของจำนวนรายการที่ค้นพบและ navigate page
	            -->
	            &nbsp;&nbsp;&nbsp;Non-Trigger
	        </div>
	        <!-- *********************** 1.div title **************************** -->
	         
	        <!-- *********************** 2.div header **************************** -->
	        <div id="divHeaderCustom">
	            <table id="subHeaderCustom">
	                <tr>
						<th class="order">No.</th>
						<th style="width: 200px;">Caption</th>
						<th style="width: 250px;">Autocomplete</th>
						<th >Remark</th>
	                </tr>
	            </table>
	        </div>
	        <!-- *********************** 2.div header **************************** -->
	         
	        <!-- *********************** 3.div detail **************************** -->
	        <div id="divDetailCustom">
	            <table id="tableId_divDetailCustom">
	                <tr>
	               		<td class="order">1.</td>
	               		<td class="successColor" style="width: 200px;">
	               			Inter Country Port <br>
	               			<font color="red"> (Port Inter เฉพาะประเทศนั้นๆ)</font> <br>
	               			<h4>การใช้งาน</h4>
	               			<ul>
	               				<li>Transborder Port</li>
	               			
	               			</ul>
	               		</td>
	               		<td style="width: 250px;">
	               		 	<s:textfield id="autocompleteArrPortNameCode" name="autocompleteArrPortNameCode" code-of="autocompleteArrPortName_autocomplete" cssStyle="width: 200px;"/>
                    		<s:textfield id="autocompleteArrPortNameText" name="autocompleteArrPortNameText" text-of="autocompleteArrPortName_autocomplete" cssStyle="width: 200px;"/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
	               			Servlet : &#60s:url value='/PortInterCountrySelectItemServlet'/&#62; <br><br>
	               			
	               			<p>JS : </p>
	               				 <font color="green">
	               				 jQuery("#autocompleteArrPortNameCode").autocompletezAjax([{  <font color="gray">// UI มาตรฐาน </font><br>
                						 &emsp;&emsp;url: "<s:url value='/PortInterCountrySelectItemServlet'/>",  <font color="gray">	// โหลด  Inter Country Port ผ่าน servlet</font><br>
                						 &emsp;&emsp;defaultKey: "",    <font color="gray"> // กำหนดค่า key ตัวแรกของ Autocomplete</font><br>
                						 &emsp;&emsp;defaultValue: "" ,   <font color="gray">// กำหนดค่า value ตัวแรกของ Autocomplete</font><br>
                						 &emsp;&emsp;requireInput: true,  <font color="gray">// กำหนดให้เป็น Require field</font><br>
           				 				 &emsp;&emsp;clearForm: true, <font color="gray">// กำหนดค่า สำหรับการ clear value เมื่อมี event ที่ปุ่ม Clear</font><br>
							             &emsp;&emsp;validName:"<s:text name='ss.first_foreign_port'/>" , <font color="gray">// กำหนดค่า สำหรับการ validation</font><br>
							             &emsp;&emsp;cssStyle: "text-transform: uppercase;" <font color="gray">//กำหนด css</font><br>
           						}]); <br>
           						</font>
	               			
	               			</span>
	               		</td>
	               	</tr>
	               	
	               	 <tr>
	               		<td class="order">2.</td>
	               		<td class="successColor" style="width: 200px;">
	               			Inter Word Port <br>
	               			<font color="red"> (Port Inter ทั่วโลก)</font> <br>
	               			<h4>การใช้งาน</h4>
	               			<ul>
	               				<li>Last foreign port</li>
	               			
	               			</ul>
	               		</td>
	               		<td style="width: 250px;">
	               		 	<s:textfield id="autocompleteDepPortNameCode" name="autocompleteDepPortNameCode" code-of="autocompleteDepPortName_autocomplete" cssStyle="width: 200px;"/>
                    		<s:textfield id="autocompleteDepPortNameText" name="autocompleteDepPortNameText" text-of="autocompleteDepPortName_autocomplete" cssStyle="width: 200px;"/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
	               			Servlet : &#60s:url value='/PortInterWordAppsSelectItemServlet'/&#62; <br><br>
	               			
	               			<p>JS :</p>
	               			 <font color="green">
	               			 		jQuery("#autocompleteDepPortNameCode").autocompletezAjax([{	<font color="gray">// UI มาตรฐาน</font>  <br>
                						 &emsp;&emsp;url: "<s:url value='/PortInterWordAppsSelectItemServlet'/>",  <font color="gray">	// โหลด  Inter Word Port ผ่าน servlet</font><br>
                						 &emsp;&emsp;defaultKey: "",    <font color="gray"> // กำหนดค่า key ตัวแรกของ Autocomplete</font><br>
                						 &emsp;&emsp;defaultValue: "" ,   <font color="gray">// กำหนดค่า value ตัวแรกของ Autocomplete</font><br>
                						 &emsp;&emsp;requireInput: true,  <font color="gray">// กำหนดให้เป็น Require field</font><br>
           				 				 &emsp;&emsp;clearForm: true, <font color="gray">// กำหนดค่า สำหรับการ clear value เมื่อมี event ที่ปุ่ม Clear</font><br>
							             &emsp;&emsp;validName:"<s:text name='ss.first_foreign_port'/>" , <font color="gray">// กำหนดค่า สำหรับการ validation</font><br>
							             &emsp;&emsp;cssStyle: "text-transform: uppercase;" <font color="gray">//กำหนด css</font><br>
           							 }]); <br>
	               			</font>
	               			</span>
	               		</td>
	               	</tr>
	               	
	               	
	               	 <tr>
	               		<td class="order">3.</td>
	               		<td class="successColor" style="width: 200px;">
	               			Inter Word Port <br>
	               			<font color="red"> (กรณีของ Port ทั้งหมด   PORT Inter World + Port APP + Cambudia inter port)</font> <br>
	               			<h4>การใช้งาน</h4>
	               			<ul>
	               				<li>ManageSchedule :<br>
	               				 Arrival Port
	               				<br>
	               				Departure Port
	               				</li>
	               			
	               			</ul>
	               		</td>
	               		<td style="width: 250px;">
	               			Arrival Port : <br>
	               		 	<s:textfield id="autocompleteDepWPortNameCode" name="autocompleteDepWPortNameCode" code-of="autocompleteDepWPortName_autocomplete" cssStyle="width: 200px;"/>
                    		<s:textfield id="autocompleteDepWPortNameText" name="autocompleteDepWPortNameText" text-of="autocompleteDepWPortName_autocomplete" cssStyle="width: 200px;"/>
                    		
                    		<br>
                    		<br>
                    		Departure Port<br>
                    		<s:textfield id="autocompleteArrWPortNameCode" name="autocompleteArrWPortNameCode" code-of="autocompleteArrWPortName_autocomplete" cssStyle="width: 200px;"/>
                    		<s:textfield id="autocompleteArrWPortNameText" name="autocompleteArrWPortNameText" text-of="autocompleteArrWPortName_autocomplete" cssStyle="width: 200px;"/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
	               			Servlet : &#60s:url value='/PortInterWordAllSelectItemServlet'/&#62; <br><br>
	               			
	               			<p>JS :</p>
	               			 <font color="green">
	               			 		jQuery("#autocompleteDepWPortNameCode").autocompletezAjax([{	<font color="gray">// UI มาตรฐาน</font>  <br>
                						 &emsp;&emsp;url: "<s:url value='//PortInterWordAllSelectItemServlet'/>",  <font color="gray">	// โหลด  Inter Word Port ผ่าน servlet</font><br>
                						 &emsp;&emsp;defaultKey: "",    <font color="gray"> // กำหนดค่า key ตัวแรกของ Autocomplete</font><br>
                						 &emsp;&emsp;defaultValue: "" ,   <font color="gray">// กำหนดค่า value ตัวแรกของ Autocomplete</font><br>
                						 &emsp;&emsp;requireInput: true,  <font color="gray">// กำหนดให้เป็น Require field</font><br>
           				 				 &emsp;&emsp;clearForm: true, <font color="gray">// กำหนดค่า สำหรับการ clear value เมื่อมี event ที่ปุ่ม Clear</font><br>
							             &emsp;&emsp;validName:"<s:text name='ss.first_foreign_port'/>" , <font color="gray">// กำหนดค่า สำหรับการ validation</font><br>
							             &emsp;&emsp;cssStyle: "text-transform: uppercase;" <font color="gray">//กำหนด css</font><br>
           							 }]); <br>
	               			</font>
	               			</span>
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
	<div id="divResult2">
	    <div id="divResultBorderCustom">
	     
	        <!-- *********************** 1.div title **************************** -->
	        <div id="divTitleCustom">
	            <!-- 
	            	Table title สำหรับวาด table ส่วนของจำนวนรายการที่ค้นพบและ navigate page
	            -->
	            &nbsp;&nbsp;&nbsp;Trigger
	        </div>
	        <!-- *********************** 1.div title **************************** -->
	         
	        <!-- *********************** 2.div header **************************** -->
	        <div id="divHeaderCustom">
	            <table id="subHeaderCustom">
	                <tr>
						<th class="order">No.</th>
						<th style="width: 200px;">Caption</th>
						<th style="width: 250px;">Autocomplete</th>
						<th >Remark</th>
	                </tr>
	            </table>
	        </div>
	        <!-- *********************** 2.div header **************************** -->
	         
	        <!-- *********************** 3.div detail **************************** -->
	        <div id="divDetailCustom">
	            <table id="tableId_divDetailCustom">
	                <tr>
	               		<td class="order">1.</td>
	               		<td class="successColor">
	               			ข้อมูลของ Departure Port และ Arrival Port<br>
	               			 เปลี่ยนแปลงตามการเลือกข้อมูล Combo direction<br>
	               		</td>
	               		<td colspan="2">
	               		
	               			<table style="border: none;">
	               				<tr> 
	               					<td> 
	               					<p>Departure Port</p>
	               						<s:select 
			               				id="direction_id"
										list="lstJourneyTypeStatusSelectItem"
										listKey="key"
										listValue="value" 
										cssStyle="width: 200px;" 
										/>
	               					</td>    
	               				</tr>
	               				<tr>	
	               					<td> 
	               						<p>Departure Port</p>
					               		<s:textfield id="autocompleteDepturePortNameCode" name="autocompleteDepturePortNameCode" code-of="autocompleteDepturePortName_autocomplete" cssStyle="width: 200px;"/>
				                    	<s:textfield id="autocompleteDepturePortNameText" name="autocompleteDepturePortNameText" text-of="autocompleteDepturePortName_autocomplete" cssStyle="width: 200px;"/>
	               					</td>
	               				</tr>	
	               				<tr>
	               					<td> 
	               						<p>Arrival Port</p>
					               		<s:textfield id="autocompleteArrivalPortNameCode" name="autocompleteArrivalPortNameCode" code-of="autocompleteArrivalPortName_autocomplete" cssStyle="width: 200px;"/>
				                    	<s:textfield id="autocompleteArrivalPortNameText" name="autocompleteArrivalPortNameText" text-of="autocompleteArrivalPortName_autocomplete" cssStyle="width: 200px;"/>
	               					</td>
	               				
	               				</tr>
	               			
	               			</table>
	               		
	               		</td>
	               	</tr>
	               	<tr>
	               		<td colspan="4" style="background-color:ede8b9c"> 
	               			<span class="globalTypColor" >คำอธิบาย  </span><br><br>
	               			1. ทำการประกาศตัวแปร  hiกden ค่า สำหรับนำไปใช้งาน<br>
	               			
	               			<font color="green">&lt;s:hidden id="depPortType" value="D"/&gt; <font color="gray">//สำหรับ ระบุ AutoComplete Departure Port</font><br>
							&lt;s:hidden id="arrPortType" value="A"/&gt; <font color="gray">//สำหรับ ระบุ AutoComplete Arrival Port</font><br>
							&lt;s:hidden id="direction" value=" "/&gt;   <font color="gray">//สำหรับ เก็บค่าเมื่อมีการเลือก direction</font><br>
							</font>
							<br>
							
							2. ทำการสร้าง AutoComplete เมื่อโหลดหน้าจอ  เรียกใช้งาน servlet และส่ง Param เข้าไปเป็นเลื่อนไข<br>
							   //Arrival port<br>
							  // เรียกใช้งาน Autocomplete widget สำหรับสร้าง Autocomplete<br>
							  //AUTOCOMPLETE SERVLET<br>
							   <font color="green"> jQuery("#autocompleteArrivalPortNameCode").autocompletezAjaxFilter([{	<font color="gray">// UI มาตรฐาน</font><br>
					                &emsp;&emsp;url: "<s:url value='/PortInterTrigerSelectItemServlet'/>",  	<font color="gray">// โหลด INTER  PORT ผ่าน servlet</font><br>
					                &emsp;&emsp;postParamsId: {direction: "direction", portType: "arrPortType"}, <font color="gray">// param ใส่ ID ของ param ที่จะส่งค่าเข้าไป</font><br>
					                &emsp;&emsp;defaultKey: "",     <font color="gray">// กำหนดค่า key ตัวแรกของ Autocomplete</font><br>
					                &emsp;&emsp;defaultValue: "" ,   <font color="gray">// กำหนดค่า value ตัวแรกของ Autocomplete</font><br>
					                &emsp;&emsp;requireInput: true,  <font color="gray">// กำหนดให้เป็น Require field</font><br>
          				 			&emsp;&emsp;clearForm: true, <font color="gray">// กำหนดค่า สำหรับการ clear value เมื่อมี event ที่ปุ่ม Clear</font><br>
						             &emsp;&emsp;validName:"<s:text name='ss.first_foreign_port'/>" , <font color="gray">// กำหนดค่า สำหรับการ validation</font><br>
						             &emsp;&emsp;cssStyle: "text-transform: uppercase;" <font color="gray">//กำหนด css</font><br>
					            }]);<br><br><br>
							 	</font>
							 	
								//Departure port<br>
								//เรียกใช้งาน Autocomplete widget สำหรับสร้าง Autocomplete<br>
							 	 //AUTOCOMPLETE SERVLET<br>
							     <font color="green">jQuery("#autocompleteDepturePortNameCode").autocompletezAjaxFilter([{	<font color="gray"> // UI มาตรฐาน</font><br>
					                &emsp;&emsp;url: "<s:url value='/PortInterTrigerSelectItemServlet'/>",  	   <font color="gray"> // โหลด inter  Port ผ่าน servlet</font><br>
					                &emsp;&emsp;postParamsId: {direction: "direction", portType: "depPortType"},   <font color="gray"> // param ใส่ ID ของ param ที่จะส่งค่าเข้าไป</font><br>
					                &emsp;&emsp;defaultKey: "",     <font color="gray">// กำหนดค่า key ตัวแรกของ Autocomplete</font><br>
					                &emsp;&emsp;defaultValue: "",   <font color="gray"> // กำหนดค่า value ตัวแรกของ Autocomplete</font><br>
					                &emsp;&emsp;requireInput: true,  <font color="gray">// กำหนดให้เป็น Require field</font><br>
         				 			&emsp;&emsp;clearForm: true, <font color="gray">// กำหนดค่า สำหรับการ clear value เมื่อมี event ที่ปุ่ม Clear</font><br>
					            	 &emsp;&emsp;validName:"<s:text name='ss.first_foreign_port'/>" , <font color="gray">// กำหนดค่า สำหรับการ validation</font><br>
					           		 &emsp;&emsp;cssStyle: "text-transform: uppercase;" <font color="gray">//กำหนด css</font><br>
					            }]);<br>
	               				</font>
	               				<br>
	               				<p>สร้าง script change combo direction </p>
	               				<font color="green">
	               				jQuery("#direction_id").selectmenu({<br>
								   &emsp;&emsp;change: function( event, ui ) {<br>
									 &emsp;&emsp;changeDirection(); <font color="gray"> //เมื้่อมีกสารเปลี่ยนแปลงเรียกเข้า Funtion</font><br>
								  }
								  <br><br>
					  </font>
		   		});
	               				
				            <br><br>
				            
				            3. เมื่อมีการ Change Combo Direction <br>
							 - เมื่อ Change Combo Direction ให้ set ค่าใส่ตัวแปร ที่สร้างไว้ <br>
							<font color="green">jQuery("#direction").val(jQuery("#direction_id option:selected").val());</font><br><br>
							<br><br>
							- สั่ง clear AutoCompete <br>
							<font color="green">var value = {codeValue: "", textValue: ""}<br>
					         jQuery('#autocompleteArrivalPortNameCode').autocompletezAjax('autocompleteValue', 0, value);<br>
					         jQuery('#autocompleteDepturePortNameCode').autocompletezAjax('autocompleteValue', 0, value);<br>	
	               			</font>
	               		</td>
	               	</tr>
	               	
	               	<tr>
	               		<td class="order">2.</td>
	               		<td>Race</td>
	               		<td>
	               		
	               		
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
	               			เรียกใช้ : selectItemManager.searchRaceSelectItem();
	               			</span>
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
	<s:hidden id="depPortType" value="D"/>
	<s:hidden id="arrPortType" value="A"/>
	<s:hidden id="direction" value=" "/>
	
<br><br><br>
</s:form>
</body>
</html>