<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<script type="text/javascript">
		function sf(){
		}

		function initMenu () {
			submitPage("<s:url value='/jsp/security/initMainpage.action' />");
		}
		
	</script>
</head>
<body>
<s:form cssClass="margin-zero" name="cssClass">
	<div class="CRITERIA CRITERIA_1280">
		<div align="center">
			<table style="width: 95%" cellpadding="5">
				<thead style="background-color:#3383bb">
					<tr>
						<th style="width:10%; height: 20px;">
							<font color="white" size="3">
								Class
							</font>
						</th>
						<th style="width:90%; height: 20px;">
							<font color="white" size="3">
								Result
							</font>
						</th>
					</tr>
				</thead>
				<tbody style="background-color: #e0e0e0">
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>center</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							สำหรับ การจัดกึ่งกลาง
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>checkbox</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							สำหรับ checkbox เพื่อการจัดกึ่งกลาง กำหนด  width เป็น 25px
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>order</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							สำหรับ column ลำดับใน table เพื่อการจัดกึ่งกลาง กำหนด width เป็น 40px
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>edit</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							สำหรับ column แก้ไขใน table เพื่อการจัดกึ่งกลาง กำหนด width เป็น 60px
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>view</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							สำหรับ column แสดง ใน table เพื่อการจัดกึ่งกลาง กำหนด  width เป็น 60px
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>status</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							สำหรับ column สถานะ ใน table เพื่อการจัดกึ่งกลาง
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>date</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							ใช้สำหรับ column  วันที่ ใน table เพื่อการจัดกึ่งกลาง
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>LABEL</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							ใช้สำหรับจัดชิดขวา
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>VALUE</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							ใช้สำหรับจัดชิดซ้าย
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>requireGroup</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							ใช้สำหรับ การตรวจสอบ validate ของ Radio และ Checkbox
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>input_idcard</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							ใช้สำหรับ การตรวจสอบรูปแบบหมายเลขบัตรประจำตัวประชาชน
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>input_email</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							ใช้สำหรับ การตรวจสอบรูปแบบอีเมล์
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>input_password_format</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							ใช้สำหรับ การตรวจสอบรูปแบบรหัสผ่าน
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>requireInput</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							ใช้สำหรับ การตรวจสอบ validate
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>accordion</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							ใช้สำหรับ accordion ไว้สำหรับสร้างเมนูหรือเนื้อหา ที่สามารถซ่อน/แสดงได้
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>input_datepicker</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							ใช้สำหรับการกำหนด format วันที่ในรูปแบบมาตรฐาน DD/MM/YYYY
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>input_timeformat</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							ใช้สำหรับการกำหนด format เวลาในรูปแบบทั่วไป
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>progressbar_cct</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							ใช้สำหรับ ProgressBar
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>suggestionClient</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							ใช้สำหรับ Suggestion แบบ Client Filter
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>suggestion</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							ใช้สำหรับ Suggestion แบบ Server Filter
						</td>
					</tr>
					<tr>
						<td align="left" style="padding-left: 10px; width: 10%;">
							<b>HIGHLIGHT</b>
						</td>
						<td align="left" style="padding-left: 10px;">
							ใช้สำหรับ กำหนดตัวหนังสือเป็นสีแดง
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</s:form>
</body>
</html>