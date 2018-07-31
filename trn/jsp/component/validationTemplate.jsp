<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<script type="text/javascript" src="<s:url value='/js/button/button.js' />"></script>
<script type="text/javascript">
	var contextPath='<%=request.getContextPath()%>';

	jQuery(document).ready(function(){
		initButton(contextPath);
		
		$( "#tabs" ).tabs();
		 genCustomTableStyle("divResult1",1200,null,null);	
		 genCustomTableStyle("divResult2",1200,null,null);
		 genCustomTableStyle("divResult3",1200,null,null);
		 setDefault();
		 setSpinner();
	});

	function sf(){
		jQuery("#countryId").autocompletezAjax([{		// UI มาตรฐาน
            url: "<s:url value='/countrySelectItemServlet'/>",  	// โหลด Country Name ผ่าน servlet
            defaultKey: "",     // กำหนดค่า key ตัวแรกของ Autocomplete
            defaultValue: "",    // กำหนดค่า value ตัวแรกของ Autocomplete
            requireInput: true,  // กำหนดให้เป็น Require field
            clearForm: true, // กำหนดค่า สำหรับการ clear value เมื่อมี event ที่ปุ่ม Clear
            validName: "Country", // กำหนดค่า สำหรับการ validation
            cssStyle: "text-transform: uppercase;"
        }]);
	
		jQuery("#countId").autocompletezAjax([{		// UI มาตรฐาน
            url: "<s:url value='/countrySelectItemServlet'/>",  	// โหลด Country Name ผ่าน servlet
            defaultKey: "",     // กำหนดค่า key ตัวแรกของ Autocomplete
            defaultValue: "",    // กำหนดค่า value ตัวแรกของ Autocomplete
            requireInput: true,  // กำหนดให้เป็น Require field
            clearForm: true, // กำหนดค่า สำหรับการ clear value เมื่อมี event ที่ปุ่ม Clear
            validName: "CountryAAAAAA", // กำหนดค่า สำหรับการ validation
            cssStyle: "text-transform: uppercase;"
        }]);
	}
	
	function setDefault(){
		
		// jQuery("#btnSearch").hide();
		jQuery("#btnBack").hide();
		jQuery("#btnPrint").hide();
		jQuery("#LoginType").val("B");
	}
	
	function clearFormCallBack(){
		jQuery("#LoginType").val("B");
		jQuery("#LoginType").selectmenu('refresh');
		jQuery("#carrierCode").val("TEST TEST");
		jQuery("#date_dd_sl_mm_sl_yyyy").val("12/09/2016");
	}
	
	
	function valid() {
		if(!validateFormInputAll()) {
			return false;
		}
		
		jQuery("input, select").each(function (index) {
			console.log(jQuery(this).val());
		});
		alert("submit");
	}

	function setSpinner() {
		jQuery( "#spinner1" ).spinner({
			min: 1,
			max: 5 
		});

		jQuery( "#spinner2" ).spinner({
			min: 1,
			max: 5 
		});

		jQuery('#spinner1').val(2);
		jQuery('#spinner2').val(1);
	}
</script>

 	
<style type="text/css">
	.highlight{
		color: blue;
	}
	
tr.detail:nth-child(even) {
    background-color: #D2E1FF ;
}

</style>
</head>

<body>
	<s:form id="searchForm" name="searchForm" method="post" cssClass="margin-zero" onsubmit="return false;">
	<br>
		<a href="<%=request.getContextPath()%>/jsp/initComponent.action" style="padding-left: 20px"><img src="<s:url value='/images/icon/i_search.png' />" /><font color="blue" size="1">Goto page Component</font></a>
	<br>
	<br>
		<h3> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;Vaildate </h3>
		
		<div id="tabs">
			  <ul>
			    <li><a href="#tabs-1">Demo</a></li>
			    <li><a href="#tabs-2">การ vaildate require field</a></li>
			    <li><a href="#tabs-3">การ vaildate format data</a></li>
			    <li><a href="#tabs-4">ClearForm</a></li>
			  </ul>
		
			<div id="tabs-1">
				<s:include value="/jsp/component/validateForm.jsp" />
			</div>
			<div id="tabs-2">
				การ vaildate require field<br>
				<ul>
					<li>field ที่ต้องทำการตรวจสอบการกรอกข้อมูล ห้ามเป็นค่าว่าง ให้ทำการกำหนด <font color="red"> class requireInput</font></li>
					<li>การใส่เครื่อง * สำหรับ field ที่ห้ามเป็นค่าว่าง ให้ใช้<font color="red"> &lt;em&gt;*&lt;/em&gt;</font></li>
					<li>สร้าง attribute ที่ชื่อว่า <font color="red"> validName</font> และกำหนด value คือ caption ที่ต้องการให้แสดง message</li>
				</ul>
		เช่น <font color="blue"> &lt;s:textfield name="carrierCode" cssClass="<font color="red">requireInput</font>" style="width: 200px;"<font color="red"> validName="Carrier code"</font> /&gt;</font>
			</div>
			
		<div id="tabs-3">
				<p>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;field ที่ต้องทำการ validate format ของ  <b>CP</b> ให้ทำการกำหนด  element ดังนี้</p>
				<div id="divResult1">
				    <div id="divResultBorderCustom">

				        <!-- *********************** 2.div header **************************** -->
				        <div id="divHeaderCustom">
				            <table id="subHeaderCustom">
				                <tr>
									<th class="order">No.</th>
									<th style="width: 125px;">Data Element</th>
									<th style="width: 150px;">cp_validation</th>
									<th style="width: 300px;">Validate Format</th>
									<th>Example code</th>
				                </tr>
				            </table>
				        </div>
				     </div>
	        <!-- *********************** 2.div header **************************** -->
	         
	        <!-- *********************** 3.div detail **************************** -->
				        <div id="divDetailCustom" class="detail">
				            <table id="tableId_divDetailCustom">
				                <tr class="detail">
				               		<td class="order">1</td>
				               		<td class="successColor" style="width: 125px;">Aircraft callsign</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_aircraft_callsign
										</span>
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											<ul>
												<li>ตัวอักษรภาษาอังกฤษตัวใหญ่ A-Z หรือตัวเลข 0-9 เท่านั้น</li>
												<li>จำนวนตัวอักษร min 3 ตัวอักษร max 8 ตัวอักษร</li>
											</ul>
										</span>
				               		</td>
				               		<td>
				               			<span class="globalTypColor" >
										<font color="blue">	
										&lt;s:textfield id="aircraftCallSign"  name="aircraftCallSign" 
											style="width: 200px;" <font color="red">cp_validation="input_aircraft_callsign"</font>/&gt;
										</font>
										</span>
				               		</td>
				               	</tr>
				               	
				               	 <tr class="detail">
				               		<td class="order">2</td>
				               		<td class="successColor" style="width: 125px;">Service number</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_service_number
										</span>
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											<ul>
												<li>ตัวอักษรภาษาอังกฤษตัวใหญ่ A-Z หรือตัวเลข 0-9 เท่านั้น</li>
												<li>จำนวนตัวอักษร min 3 ตัวอักษร max 8 ตัวอักษร</li>
											</ul>
											
										</span>
				               		</td>
				               		<td>
				               			<span class="globalTypColor" >
										<font color="blue">	
										&lt;s:textfield id="serviceNumber"  name="serviceNumber" 
											style="width: 200px;" <font color="red">cp_validation="input_service_number"</font>/&gt;
										</font>
										</span>
										<br>
				               		</td>
				               	</tr>
				               	
				               	<tr class="detail">
				               		<td class="order"></td>
				               		<td class="successColor" style="width: 125px;">Flight number</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_flight_number
										</span>
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											<ul><b><font color="red">สำหรับหน้าค้นหา</font></b>
												<li>ตัวอักษรภาษาอังกฤษตัวใหญ่ A-Z หรือตัวเลข 0-9 เท่านั้น</li>
												<li>จำนวนตัวอักษร min 3 ตัวอักษร max 8 ตัวอักษร</li>
											</ul>
											
										</span>
				               		</td>
				               		<td>
				               			<span class="globalTypColor" >
										<font color="blue">	
										&lt;s:textfield id="flightnumber"  name="flightnumber" 
												cssClass="requireInput clearform" style="width: 200px;" 
												validName="Flight number" <font color="red">cp_validation="input_flight_number"</font> /&gt;
										</font>
										</span>
										<br>
				               		</td>
				               	</tr>
				               	
				               	
				               	 <tr class="detail">
				               		<td class="order"></td>
				               		<td class="successColor" style="width: 125px;">Flight number</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_format_flight_number
										</span>
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											<ul><b><font color="red">สำหรับหน้าที่ต้องการค่า flight number ไป insert / update ลงฐานข้อมูล</font></b>
												<li>ตัวอักษรภาษาอังกฤษตัวใหญ่ A-Z หรือตัวเลข 0-9 เท่านั้น</li>
												<li>จำนวนตัวอักษร min 3 ตัวอักษร max 8 ตัวอักษร</li>
												<li>format AA(X)NNNN(X) whereformat ‘ AA(X)NNNN(X)
													‘AA’ will be 2 digit IATA Code for this Airlinformat ‘ AA(X)NNNN(X)
													‘AA’ will be 2 digit IATA Code for this Airline
													‘N’ is mandatory numeric
												</li>
											</ul>
											
										</span>
				               		</td>
				               		<td>
				               			<span class="globalTypColor" >
										<font color="blue">	
										&lt;s:textfield id="flightnumber"  name="flightnumber" 
												cssClass="requireInput clearform" style="width: 200px;" 
												validName="Flight number" <font color="red">cp_validation="input_format_flight_number"</font> /&gt;
										</font>
										</span>
										<br>
				               		</td>
				               	</tr>
				               	
				               	 <tr class="detail">
				               		<td class="order"></td>
				               		<td class="successColor" style="width: 125px;">Flight number</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_flight_format_number
										</span>
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											<ul><b><font color="red">สำหรับตรวจสอบ format ด้านท้าย เช่น FD1234 จะตรวจสอบแค่ 1234</font></b>
												<li>ตัวอักษรภาษาอังกฤษตัวใหญ่ A-Z หรือตัวเลข 0-9 เท่านั้น</li>
												<li>จำนวนตัวอักษร min 3 ตัวอักษร max 8 ตัวอักษร</li>
												<li>format AA(X)NNNN(X) whereformat ‘ AA(X)NNNN(X)
													‘AA’ will be 2 digit IATA Code for this Airlinformat ‘ AA(X)NNNN(X)
													‘AA’ will be 2 digit IATA Code for this Airline
													‘N’ is mandatory numeric
												</li>
											</ul>
											
										</span>
				               		</td>
				               		<td>
				               			<span class="globalTypColor" >
										<font color="blue">	
										&lt;s:textfield id="flightnumber"  name="flightnumber" 
												cssClass="requireInput clearform" style="width: 200px;" 
												validName="Flight number" <font color="red">cp_validation="input_format_flight_number"</font> /&gt;
										</font>
										</span>
										<br>
				               		</td>
				               	</tr>
				               	
				               	
				                 <tr class="detail">
				               		<td class="order">3</td>
				               		<td class="successColor" style="width: 125px;">User ID (ฝั่ง CP)
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_user_id
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											ตัวอักษรภาษาอังกฤษตัวใหญ่ A-Zหรือตัวเลข 0-9เท่านั้น
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield name="userId" style="width: 200px;" <font color="red">cp_validation="input_user_id"</font>/&gt;</font>
										</span>
				               		</td>
				               	</tr>
				               	
				               	 
				              	 <tr class="detail">
				               		<td class="order">4</td>
				               		<td class="successColor" style="width: 125px;">Family name (PAX)
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_family_name_pax
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											ตัวอักษรภาษาอังกฤษตัวใหญ่ A-Z เท่านั้น
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield name="familyNamePax" style="width: 200px;" <font color="red">cp_validation="input_family_name_pax" </font>/&gt;</font>
										</span>
				               		</td>
				               	</tr>
				               	
				                <tr class="detail">
				               		<td class="order">5</td>
				               		<td class="successColor" style="width: 125px;">Given name(s) (PAX)
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_given_name_pax
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											ตัวอักษรภาษาอังกฤษตัวใหญ่ A-Z หรือวรรค (Space)…... เท่านั้น
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield name="givenNamePax" style="width: 200px;" <font color="red">cp_validation="input_given_name_pax" </font>/&gt;</font>
										</span>
				               		</td>
				               	</tr>
				               	
				               	 <tr class="detail">
				               		<td class="order">6</td>
				               		<td class="successColor" style="width: 125px;">Document number<br>
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_document_number
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											ตัวอักษรภาษาอังกฤษตัวใหญ่ A-Z หรือตัวเลข 0-9เท่านั้น
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield   name="documentNumber" 
											style="width: 200px;"  <font color="red">cp_validation="input_document_number" </font>/&gt;</font>
										</span>
				               		</td>
				               	</tr>
				               					               					             				               	
				               	 <tr class="detail">
				               		<td class="order">7</td>
				               		<td class="successColor" style="width: 125px;">Port<br>
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_port
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											ตัวอักษรภาษาอังกฤษตัวใหญ่ A-Z หาข้อมูลมาแสดงตั้งแต่ตัวอักษรตัวแรก % หลัง
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield name="port" 
											style="width: 200px;" <font color="red"> cp_validation="input_port" </font>/&gt;</font>
										</span>
				               		</td>
				               	</tr>
				               	
				               	 <tr class="detail">
				               		<td class="order">8</td>
				               		<td class="successColor" style="width: 125px;">Reservation system code<br>
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_reservation_system_code
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											ตัวอักษรภาษาอังกฤษตัวใหญ่ A-Zหรือตัวเลข 0-9 เท่านั้น
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield name="reservation" 
											style="width: 200px;" <font color="red"> cp_validation="input_reservation_system_code"</font>/&gt;</font>
										</span>
				               		</td>
				               	</tr>
				               	
				               	 <tr class="detail">
				               		<td class="order">9</td>
				               		<td class="successColor" style="width: 125px;">Record locator<br>
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_record_locator
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											ตัวอักษรภาษาอังกฤษตัวใหญ่ A-Zหรือตัวเลข 0-9 เท่านั้น
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield name="recordLocator" 
											style="width: 200px;" <font color="red">cp_validation="input_record_locator"</font>/&gt;</font>
										</span>
				               		</td>
				               	</tr>
				               	
				               	 <tr class="detail">
				               		<td class="order">10</td>
				               		<td class="successColor" style="width: 125px;">Nationality<br>
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_nationality
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											ตัวอักษรภาษาอังกฤษตัวใหญ่ A-Zเท่านั้น
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield  name="nationality"
											style="width: 200px;"  <font color="red">cp_validation="input_nationality"</font>/&gt;</font>
										</span>
				               		</td>
				               	</tr>
				               	
				                <tr class="detail">
				               		<td class="order">11</td>
				               		<td class="successColor" style="width: 125px;">Issuing State<br>
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_issuing_state
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											ตัวอักษรภาษาอังกฤษตัวใหญ่ A-Zเท่านั้น
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield 
											style="width: 200px;"  <font color="red">cp_validation="input_issuing_state"</font>/&gt;</font>
										</span>
				               		</td>
				               	</tr>
				               	
				               	 <tr class="detail">
				               		<td class="order">12</td>
				               		<td class="successColor" style="width: 125px;">Country of birth<br>
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_country_of_birth
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											ตัวอักษรภาษาอังกฤษตัวใหญ่ A-Zเท่านั้น
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield  
											style="width: 200px;"  <font color="red">cp_validation="input_country_of_birth"</font>/&gt;</font>
										</span>
				               		</td>
				               	</tr>
				             </table>
				          </div>
				      
	          	</div><br><br><br>

         		<p>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;field ที่ต้องทำการ validate format ของ  <b>UCP</b> ให้ทำการกำหนด element ดังนี้</p>
				<div id="divResult2">
		    		<div id="divResultBorderCustom">
		        <!-- *********************** 2.div header **************************** -->
			        <div id="divHeaderCustom">
			            <table id="subHeaderCustom">
			                <tr>
								<th class="order">No.</th>
								<th style="width: 125px;">Data Element</th>
								<th style="width: 150px;">cp_validation</th>
								<th style="width: 300px;">Validate Format</th>
								<th>Example code</th>
			                </tr>
			            </table>
			        </div>
			        
			        <div id="divDetailCustom">
		            <table id="tableId_divDetailCustom">
		               	
				               	 <tr class="detail">
				               		<td class="order">1</td>
				               		<td class="successColor" style="width: 125px;">User ID (ฝั่ง UCP)<br>
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_user_id
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											ตัวอักษรภาษาอังกฤษ a-zหรือ A-Zหรือตัวเลข 0-9เท่านั้น
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield  name="userId"
											style="width: 200px;"  <font color="red">cp_validation="input_user_id"</font>/&gt;</font>
										</span>
										<!-- <input type="text" style="width: 250px;" cp_validation="input_user_id">
										<input type="button" onclick="test();"> -->
				               		</td>
				               	</tr>
		            </table>
		            </div>
		            </div>
		        </div><br><br><br>
		        
		        <p>&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;field ที่ต้องทำการ validate format ที่ใช้ร่วมกัน ของ <b>CP และ UCP</b> ให้ทำการกำหนด element ดังนี้</p>
				<div id="divResult3">
		    		<div id="divResultBorderCustom">
		        <!-- *********************** 2.div header **************************** -->
			        <div id="divHeaderCustom">
			            <table id="subHeaderCustom">
			                <tr>
								<th class="order">No.</th>
								<th style="width: 125px;">Data Element</th>
								<th style="width: 150px;">cp_validation</th>
								<th style="width: 300px;">Validate Format</th>
								<th>Example code</th>
			                </tr>
			            </table>
			        </div>
			        
			        <div id="divDetailCustom">
		            <table id="tableId_divDetailCustom">
		                <tr class="detail">
				               		<td class="order">1</td>
				               		<td class="successColor" style="width: 125px;">Carrier code
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_carrier_code
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											ตัวอักษรภาษาอังกฤษตัวใหญ่ A-Zหรือตัวเลข 0-9เท่านั้น
										</span>
				               		</td>
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield id="carrierCode" name="carrierCode" style="width: 200px;"<font color="red"> cp_validation="input_carrier_code"</font>/&gt;</font>
										</span>
										<<!-- input type="text" style="width: 250px;" cp_validation="input_carrier_code">
										<input type="button" onclick="test();"> -->
				               		</td>
				               	</tr>
				               	 <tr class="detail">
				               		<td class="order">2</td>
				               		<td class="successColor" style="width: 125px;">Family name (User)
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_family_name_user
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											ตัวอักษรภาษาอังกฤษตัวใหญ่ A-Z เท่านั้น
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield name="familyName" style="width: 200px;"  <font color="red"> cp_validation="input_family_name_user"</font>/&gt;</font>
										</span>
										<!-- <input type="text" style="width: 250px;" cp_validation="input_family_name_user">
										<input type="button" onclick="test();"> -->
				               		</td>
				               	</tr>
				               	 <tr class="detail">
				               		<td class="order">3</td>
				               		<td class="successColor" style="width: 125px;">Given name(s) (User)
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_given_name_user
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											ตัวอักษรภาษาอังกฤษตัวใหญ่ A-Z หรือวรรค (Space)…... เท่านั้น
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield name="givenName" style="width: 200px;" <font color="red">cp_validation="input_given_name_user" </font>/&gt;</font>
										</span>
										<!-- <input type="text" style="width: 250px;" cp_validation="input_given_name_user">
										<input type="button" onclick="test();"> -->
				               		</td>
				               	</tr>
				               	
				               	 <tr class="detail">
				               		<td class="order">4</td>
				               		<td class="successColor" style="width: 125px;">Telephone contact<br>
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_telephone_contact
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											กรอกตัวเลข 0-9 หรือเครื่องหมาย +
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield name="telephone" 
								 			style="width: 200px;" <font color="red"> cp_validation="input_telephone_contact"</font>/&gt;</font>
										</span>
										<!-- <input type="text" style="width: 250px;" cp_validation="input_telephone_contact">
										<input type="button" onclick="test();"> -->
				               		</td>
				               	</tr>
				               	
				               	<tr class="detailvalid">
				               		<td class="order">5</td>
				               		<td class="successColor" style="width: 125px;">Ext.<br>
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_ext
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											กรอกตัวเลข 0-9 เท่านั้น
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield name="ext" 
											 style="width: 200px;" <font color="red">cp_validation="input_ext"</font>/&gt;</font>
										</span>
										<!-- <input type="text" style="width: 250px;" cp_validation="input_ext">
										<input type="button" onclick="test();"> -->
				               		</td>
				               	</tr>
				               	
				               	 <tr class="detail">
				               		<td class="order">6</td>
				               		<td class="successColor" style="width: 125px;">Facsimile number<br>
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_facsimile_number
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											กรอกตัวเลข 0-9 หรือเครื่องหมาย + 
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
				               				<font color="blue">	&lt;s:textfield name="facsimileNumber" 
											style="width: 200px;" <font color="red">cp_validation="input_facsimile_number"</font>/&gt;</font>
										</span>
										<!-- <input type="text" style="width: 250px;" cp_validation="input_facsimile_number">
										<input type="button" onclick="test();"> -->
										
				               		</td>
				               	</tr>
				               	
				               	 <tr class="detail">
				               		<td class="order">7</td>
				               		<td class="successColor" style="width: 125px;">Email address<br>
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_email_address
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											ตัวอักษรภาษาอังกฤษตัวเล็ก a-z + อักขระพิเศษ ตาม Format Email
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield  name="email" 
											style="width: 200px;"  cssClass="<font color="red">input_email</font>"/&gt;</font>
										</span>
										<!-- <input type="text" style="width: 250px;" cp_validation="input_email_address">
										<input type="button" onclick="test();"> -->
				               		</td>
				               	</tr>
				               	
				               	
				               	<tr class="detail">
				               		<td class="order">8</td>
				               		<td class="successColor" style="width: 125px;">Date<br>
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_dateformat_*
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											FormatYYYYMMDD แต่ที่หน้าจอแสดงเป็น DD/MM/YYYY
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield  name="date" 
											style="width: 200px;"  cssClass="<font color="red">input_dateformat</font>"/&gt;</font>
										</span>
										<!-- <input type="text" style="width: 250px;" cp_validation="input_dateformat_*">
										<input type="button" onclick="test();"> -->
				               		</td>
				               	</tr>
				               	
				               		
				               	 <tr class="detail">
				               		<td class="order">9</td>
				               		<td class="successColor" style="width: 125px;">Time<br>
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_timeformat_*
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											Format HH24:MI
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield  name="time" 
											style="width: 200px;"  cssClass="<font color="red">input_timeformat</font>"/&gt;</font>
										</span>
										<!-- <input type="text" style="width: 250px;" cp_validation="input_timeformat">
										<input type="button" onclick="test();"> -->
				               		</td>
				               	</tr>
				               	
				               	
				               	<tr class="detail">
				               		<td class="order">10</td>
				               		<td class="successColor" style="width: 125px;">Password<br>
				               		</td>
				               		<td style="width: 150px;">
				               			<span class="globalTypColor" >
											input_password_format
										</span>
				               			
				               		</td>
				               		<td style="width: 300px;">
				               			<span class="globalTypColor" >
											ตาม table config
										</span>
				               		</td>
				               		
				               		<td>
				               			<span class="globalTypColor" >
											<font color="blue">	&lt;s:textfield  name="password" 
											style="width: 200px;"  cssClass="<font color="red">input_password_format</font>"/&gt;</font>
										</span>
										<!-- <input type="text" style="width: 250px;" cp_validation="input_password_format">
										<input type="button" onclick="test();"> -->
				               		</td>
				               	</tr>
				               	
		               </table>
		               </div>
		               </div>
		        </div>
		        
		</div> <!-- END div tab-3 -->
		
		<div id="tabs-4">
			การ clear form<br>
				<ul>
					<li>6.1	field ที่ต้องการ clear ตอนกดปุ่ม Clear ให้ทำการกำหนด class clearForm</font></li>
					<li>หากหน้าจอการทำงานมีการกำหนดค่าตั้งต้นให้ทำการสร้าง function ที่ชื่อว่า “clearFormCallBack” และ code ไว้ที่ function การทำงานนี้</li>
				</ul>
				<br>
			
			<div class="SyntaxHighlighter SyntaxHighlighter_1024">
			 			Example
				<pre class="brush: java;highlight: [14,15]">
					<span class="globalTypColor" >
						<font color="blue">	&lt;s:textfield name="telephone" cssClass="requireInput input_telephone<font color="red"> clearform</font>" style="width: 150px;" validName="Telephone" /&gt;</font>
					</span>
				
				
					/**
					 * ทำงานหลังจาก ClearForm เสร็จแล้ว
					 *
					 */
					function clearFormCallBack(){
						/*Default combo*/
						jQuery("#LoginType").val("B");
						jQuery("#LoginType").selectmenu('refresh');
						
						/*Default text*/
						jQuery("#carrierCode").val("TEST TEST");
						
						/*Default calendar*/
						jQuery("#date_dd_sl_mm_sl_yyyy").val("12/09/2016");
					}
				</pre>
			</div>
			
			</div>
	</div>
		
	</s:form>
</body>
</html>