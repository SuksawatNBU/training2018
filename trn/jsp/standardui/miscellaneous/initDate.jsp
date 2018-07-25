<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<script type="text/javascript">
		function sf(){
			// insert code here
			jQuery("#inputdate_format5").input_dateformat({
		        dateformat :  "dd_sl_mm_sl_yyyy",
		        inputdatepicker: false
		    });
			
			jQuery("#inputdate_format6").input_dateformat({
				dateformat :  "dd_sl_mm_sl_yyyy",
		        inputdatepicker: false
		    });
			
			jQuery("#inputdate_format7").input_dateformat({
				dateformat :  "dd_sl_mm_sl_yyyy",
		        inputdatepicker: false
		    });
			
			jQuery("#inputdate_format8").input_dateformat({
				dateformat :  "dd_sl_mm_sl_yyyy",
		        inputdatepicker: false
		    });
			
			
			
			
			
			jQuery("#inputdate_format9").input_dateformat({
				dateformat :  "dd_sl_mm_sl_yyyy"
		    });
			
			jQuery("#inputdate_format10").input_dateformat({
				dateformat :  "dd_sl_mm_sl_yyyy"
		    });
			
			jQuery("#inputdate_format11").input_dateformat({
				dateformat :  "dd_sl_mm_sl_yyyy"
		    });
			
			jQuery("#inputdate_format12").input_dateformat({
				dateformat :  "dd_sl_mm_sl_yyyy"
		    });
			
			
			
			jQuery("#inputdate_format1_from").input_dateformat({
                dateformat :  "dd_sl_mm_sl_yyyy", // กำหนดรูปแบบ
                selectDateRange: { 
                    dateTo: "inputdate_format2_to" // กำหนดวันที่สิ้นสุด
                }
            });
 
            jQuery("#inputdate_format2_to").input_dateformat({
                dateformat :  "dd_sl_mm_sl_yyyy", // กำหนดรูปแบบ
                selectDateRange: { 
                    dateFrom: "inputdate_format1_from" // กำหนดวันที่เริ่มต้น
                }
            });
            
            
		}

		function initMenu () {
			submitPage("<s:url value='/jsp/security/initMainpage.action' />");
		}
		
		function backPage () {
			submitPage("<s:url value='/jsp/standardui/initMiscellaneous.action' />");
		}
		
		function getDateValue () {
			// insert code here
			var value = jQuery("#inputdate_format13").input_dateformat('dateValue');
			console.log(value)
			jQuery("#resultValue").val(value.dateForShow);
		}
		
		function setDateValue () {
			// insert code here
			var value = jQuery("#newValue").val();
			console.log(value)
			jQuery("#inputdate_format14").input_dateformat('dateValue', value);
		}
		
		function validate() {
			// validate old version
			if(!validateAll()){ 
		        return false;
		    }
			
			// validate all version
// 			if(!validateFormInputAll()){ 
// 		        return false;
// 		    }
		}
	</script>
</head>
<body>
<s:form cssClass="margin-zero" name="standardui">
	<div class="CRITERIA CRITERIA_1280">
		<p>
			<b>Input date format</b><b>&emsp;</b> คือการใช้งาน Input date format ในรูปแบบต่างๆ เช่น การกำหนด format วันที่, การใช้งานร่วมกับ datepicker และอื่นๆ
		</p>
		<table class="FORM" border="1">
			<thead style="background-color:#3383bb">
				<tr>
					<th style="width: 40%; padding-left: 10px; text-align: center;">
						<font color="white" size="3">
							<b>Predefine</b>
						</font>
					</th>
					<th style="width: 60%; padding-left: 10px; text-align: center;">
						<font color="white" size="3">
							<b>Result</b>
						</font>
					</th>
				</tr>
			</thead>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					รูปแบบมาตรฐาน DD/MM/YYYY
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input type="text" id="inputdate_format1" name="inputdate.format1" class="input_datepicker"/>
					<input type="text" id="inputdate_format2" name="inputdate.format2" class="input_datepicker requireInput"/>
					<input type="text" id="inputdate_format3" name="inputdate.format3" class="input_datepicker" readonly="readonly"/>
					<input type="text" id="inputdate_format4" name="inputdate.format4" class="input_datepicker" disabled="disabled"/>
					<s:textfield name="test" id="test" cssClass="input_datepicker" readonly="readonly"/>
				</td>
			</tr>
		</table>
		
		<br>
		
		<table class="FORM" border="1">
			<thead style="background-color:#3383bb">
				<tr>
					<th style="width: 40%; padding-left: 10px; text-align: center;">
						<font color="white" size="3">
							<b>Custom</b>
						</font>
					</th>
					<th style="width: 60%; padding-left: 10px; text-align: center;">
						<font color="white" size="3">
							<b>Result</b>
						</font>
					</th>
				</tr>
			</thead>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					<p>Input date format</p>
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input type="text" id="inputdate_format5" name="inputdate.format5"/>
					<input type="text" id="inputdate_format6" name="inputdate.format6" class="requireInput"/>
					<input type="text" id="inputdate_format7" name="inputdate.format7" readonly="readonly"/>
					<input type="text" id="inputdate_format8" name="inputdate.format8" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					<p>Input date format with datepicker</p>
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input type="text" id="inputdate_format9" name="inputdate.format9"/>
					<input type="text" id="inputdate_format10" name="inputdate.format10" class="requireInput"/>
					<input type="text" id="inputdate_format11" name="inputdate.format11" readonly="readonly"/>
					<input type="text" id="inputdate_format12" name="inputdate.format12" disabled="disabled"/>
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					<p>Select a Date Range</p>
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input type="text" id="inputdate_format1_from" name="inputdate.format1"/>
					<input type="text" id="inputdate_format2_to" name="inputdate.format2"/>
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					<p>Get date from input date format</p>
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input type="text" id="resultValue" />
					<input type="button" value="Get Date value" onclick="getDateValue();" />
					<em>&nbsp;</em>
					<input type="text" id="inputdate_format13" name="inputdate.format13" class="input_datepicker"/>
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					<p>Set date to input date format</p>
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<input type="text" id="newValue">
					<input type="button" value="Set Date value" onclick="setDateValue();" />
					<em>&nbsp;</em>
					<input type="text" id="inputdate_format14" name="inputdate.format14" class="input_datepicker"/>
				</td>
			</tr>
		</table>
	</div>
	<br>
	<s:include value="/jsp/template/button_predifine.jsp">
		<s:set var="buttonType" value='{"VALIDATEFORM", "Back"}'/>
		<s:set var="buttonEnable" value='{true, true}'/>
		<s:set var="buttonFunction" value='{ "validate", "backPage"}'/>
	</s:include>
</s:form>
</body>
</html>