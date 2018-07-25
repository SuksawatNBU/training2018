<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<script type="text/javascript">
		function sf(){
			// insert code here
			jQuery("#test_time1").input_timeformat({
                timeformat :  "hh_cl_mm", // กำหนดรูปแบบ
                selectTimeRange: { 
                    timeTo: "test_time2" // กำหนดวันที่สิ้นสุด
                }
            });
         
            jQuery("#test_time2").input_timeformat({
                timeformat :  "hh_cl_mm", // กำหนดรูปแบบ
                selectTimeRange: { 
                    timeFrom: "test_time1" // กำหนดวันที่เริ่มต้น
                }
            });
            
            
            
            
            
            
            jQuery("#input_date1").input_dateformat({
                dateformat :  "dd_sl_mm_sl_yyyy", // กำหนดรูปแบบ
                selectDateRange: { 
                    dateTo: "input_date2" // กำหนดวันที่สิ้นสุด
                },
                selectTimeRange: { 
                    timeTo: "input_time2",      // กำหนดเวลาสิ้นสุด
                    timeFrom: "input_time1" // กำหนดเวลาเริ่มต้น
                }
            });
         
            jQuery("#input_date2").input_dateformat({
                dateformat :  "dd_sl_mm_sl_yyyy", // กำหนดรูปแบบ
                selectDateRange: { 
                    dateFrom: "input_date1" // กำหนดวันที่เริ่มต้น
                },
                selectTimeRange: { 
                    timeTo: "input_time2", // กำหนดเวลาสิ้นสุด
                    timeFrom: "input_time1"  // กำหนดเวลาเริ่มต้น
                }
            });
            // เรียกใช้งาน input_timeformat widget สำหรับสร้าง Input time format
            jQuery("#input_time1").input_timeformat({
                timeformat :  "hh_cl_mm", // กำหนดรูปแบบ
                selectTimeRange: { 
                    timeTo: "input_time2"   // กำหนดเวลาสิ้นสุด
                },
                selectDateRange: {
                    dateFrom: "input_date1",  // กำหนดวันที่เริ่มต้น
                    dateTo: "input_date2"  // กำหนดวันที่สิ้นสุด
                }
            });
         
            jQuery("#input_time2").input_timeformat({
                timeformat :  "hh_cl_mm", // กำหนดรูปแบบ
                selectTimeRange: { 
                    timeFrom: "input_time1"   // กำหนดเวลาเริ่มต้น
                },
                selectDateRange: {
                    dateFrom: "input_date1",  // กำหนดวันที่เริ่มต้น
                    dateTo: "input_date2" // กำหนดวันที่สิ้นสุด
                }
            });
		}

		function initMenu () {
			submitPage("<s:url value='/jsp/security/initMainpage.action' />");
		}
		
		function backPage () {
			submitPage("<s:url value='/jsp/standardui/initMiscellaneous.action' />");
		}
		
		function validate() {
			if(!validateAll()){ 
		        return false;
		    }
		}
		
	</script>
</head>
<body>
<s:form cssClass="margin-zero" name="standardui">
	<div class="CRITERIA CRITERIA_1280">
		<p>
			<b>Input time format</b><b>&emsp;</b> คือการใช้งาน  <em>widget</em> ฟังก์ชั่น <em>input_timeformat</em> เพื่อรับข้อมูลเวลา
		</p>
		<table class="FORM" border="1">
			<thead style="background-color:#3383bb">
				<tr>
					<th style="width: 40%; padding-left: 10px; text-align: center;">
						<font color="white" size="3">
							<b>Example</b>
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
					รูปแบบมาตรฐาน
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<s:textfield name="test.time0" id="test_time0" cssClass="input_timeformat" />
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					การใช้งานแบบกำหนดช่วงเวลาเริ่มต้น และสิ้นสุด
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<s:textfield name="test.time1" id="test_time1"/>
					<s:textfield name="test.time2" id="test_time2"/>
				</td>
			</tr>
			<tr>
				<td style="width: 40%; padding-left: 10px;">
					การใช้งานแบบกำหนดช่วงวันเวลาเริ่มต้น และสิ้นสุด
				</td>
				<td style="width: 60%; padding-left: 10px;">
					<s:textfield name="input.date1" id="input_date1"/>
					<s:textfield name="input.time1" id="input_time1"/>
					<br>
					<s:textfield name="input.date2" id="input_date2"/>
					<s:textfield name="input.time2" id="input_time2"/>
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