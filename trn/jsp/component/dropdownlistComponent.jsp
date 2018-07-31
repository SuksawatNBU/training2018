<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<script type="text/javascript">
		
		function sf(){
		    genCustomTableStyle("divResult",1000,null,null);			
		    genCustomTableStyle("divResult2",1000,null,null);		

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
    <div style="padding-left: 110px; width: 900px; display: none;">
   		<br>
    	<b style="color: red">READ HERE:</b>   Column Caption จะแสดงสี เพื่อบอกสถานะของ dropdown ว่ามีการดึงค่ามาจาก sql แล้วหรือยัง  <br>
    	<b class="successColor">สีเขียว :</b> combo ดึงค่ามาจาก sql แล้ว สามารถนำไปใช้ได้เลย<br>
    	<b class="unSuccessColor">สีส้ม       :</b> เป็น dropdown ที่มีการ fix ค่าไว้ ยังไม่ได้ดึงข้อมูลมาจาก sql  
    	(หาก programmer ต้องการเรียกใช้งาน dropdown นี้  ให้นำ sql ไปวางไว้ที่   SelectItem.sql และแก้ไขที่  SelectItemDAO.java เพื่อให้สามารถทำงานต่อไปได้) 
    	<br>
    	<b class="globalTypColor">สีฟ้า:</b>Column Remark ตัวอักษรสีฟ้า คือ การเรียกใช้ข้อมูลจาก Global Type
    	
    	
    	
    	<br><br>
    	<b>หมยเหตุ1:</b> เมื่อวาง sql หรือเรียกใช้งานแบบ golbalData แล้ว  ให้เปลี่ยนสานะจาก <font class="unSuccessColor">สีส้ม</font>  ให้เป็น <font class="successColor">สีเขียว</font> ด้วย โดยการเปลี่ยนจาก  class=<font color="blue">"unSuccessColor"</font> เป็น  class=<font color="blue">"successColor"</font>
    	<br>
    	<b>หมยเหตุ2:</b>หากมีการเรียกใช้งาน combo แบบ golbalData ให้ไปลบ Method ของcomboนั้น ที่อยู่ใน SelectItemManager.java และ SelectItemDAO.java ด้วย เนื่องจากไม่ได้ถูกเรียกใช้งานแล้ว
    	<br>
    	<div><b>หมยเหตุ3:</b>หากมีกาเรียกใช้งาน combo แบบ golbalData แล้วให้เปลี่ยนเป็นสีฟ้าด้วย  โดยใส่ class=<font class="globalTypColor">"globalTypColor"</font></div>
    	<br>
    </div>
	    
	  <br><br>  
	<div id="divResult">
	    <div id="divResultBorderCustom">
	     
	        <!-- *********************** 1.div title **************************** -->
	        <div id="divTitleCustom">
	            <!-- 
	            	Table title สำหรับวาด table ส่วนของจำนวนรายการที่ค้นพบและ navigate page
	            -->
	            &nbsp;&nbsp;&nbsp;การดึงข้อมูลจาก Con GlobalType
	        </div>
	        <!-- *********************** 1.div title **************************** -->
	         
	        <!-- *********************** 2.div header **************************** -->
	        <div id="divHeaderCustom">
	            <table id="subHeaderCustom">
	                <tr>
						<th class="order">No.</th>
						<th style="width: 150px;">Caption</th>
						<th style="width: 250px;">Dropdownlist</th>
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
	               		<td class="successColor" style="width: 150px;">CarrierType</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstCarrierTypeSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.CARRIER_TYPE.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">2</td>
	               		<td class="successColor" style="width: 150px;">UserType</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstUserTypeSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.USER_TYPE.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">3</td>
	               		<td class="successColor" style="width: 150px;">LoginType</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstLoginTypeSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.LOGIN_TYPE.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">4</td>
	               		<td class="successColor" style="width: 150px;">CarrierUserStatus</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstCarrierUserStatusSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.CARRIER_USER_STATUS.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">5</td>
	               		<td class="successColor" style="width: 150px;">UserStatus</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstUserStatusSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.USER_STATUS.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">6</td>
	               		<td class="successColor" style="width: 150px;">MovementType</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstMovementTypeSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.MOVEMENT_TYPE.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">7</td>
	               		<td class="successColor" style="width: 150px;">TravellerType</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstTravellerTypeSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.TRAVELLER_TYPE.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">8</td>
	               		<td class="successColor" style="width: 150px;">DataSource</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstDataSourceSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.DATA_SOURCE.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">9</td>
	               		<td class="successColor" style="width: 150px;">JourneyType</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstJourneyTypeSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.JOURNEY_TYPE.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">10</td>
	               		<td class="successColor" style="width: 150px;">DeleteStatus</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstDeleteStatusSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.DELETE_STATUS.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">11</td>
	               		<td class="successColor" style="width: 150px;">CarrierStatus</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstCarrierStatusSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.CARRIER_STATUS.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">12</td>
	               		<td class="successColor" style="width: 150px;">FlightStatus</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstFlightStatus"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.FLIGHT_STATUS.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">13</td>
	               		<td class="successColor" style="width: 150px;">ScheduleType</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstScheduleTypeSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.SCHEDULE_TYPE.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">14</td>
	               		<td class="successColor" style="width: 150px;">DocumentType</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstDocumentTypeSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.DOCUMENT_TYPE.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">15</td>
	               		<td class="successColor" style="width: 150px;">BoardStatus</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstBoardStatusSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.BOARD_STATUS.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">16</td>
	               		<td class="successColor" style="width: 150px;">CheckInType</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstCheckInTypeSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.CHECK_IN_TYPE.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">17</td>
	               		<td class="successColor" style="width: 150px;">GenderType</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstGenderTypeSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.GENDER_TYPE.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">18</td>
	               		<td class="successColor" style="width: 150px;">ManifestType</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstManifestTypeSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.MANIFEST_TYPE.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">19</td>
	               		<td class="successColor" style="width: 150px;">TypeOfArrival</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstTypeOfArrivalSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.TYPE_OF_ARRIVAL.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">20</td>
	               		<td class="successColor" style="width: 150px;">TypeOfDeparture</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstTypeOfDepartureSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.TYPE_OF_DEPARTURE.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">21</td>
	               		<td class="successColor" style="width: 150px;">ActiveStatus</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstActiveStatusSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.ACTIVE_STATUS.getValue())
							</span>
	               		</td>
	               	</tr>
	               	<tr>
	               		<td class="order">22</td>
	               		<td class="successColor" style="width: 150px;">DateUnit</td>
	               		<td style="width: 250px;">
	               			<s:select 
								list="lstDateUnitSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.DATE_UNIT.getValue())
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
	<br><br>
	<div id="divResult2">
	    <div id="divResultBorderCustom">
	     
	        <!-- *********************** 1.div title **************************** -->
	        <div id="divTitleCustom">
	            <!-- 
	            	Table title สำหรับวาด table ส่วนของจำนวนรายการที่ค้นพบและ navigate page
	            -->
	            &nbsp;&nbsp;&nbsp;การดึงข้อมูลจาก จาก การ Server 
	        </div>
	        <!-- *********************** 1.div title **************************** -->
	         
	        <!-- *********************** 2.div header **************************** -->
	        <div id="divHeaderCustom">
	            <table id="subHeaderCustom">
	                <tr>
						<th class="order">No.</th>
						<th style="width: 150px;">Caption</th>
						<th style="width: 250px;">Dropdownlist</th>
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
	               		<td class="successColor" style="width: 150px;">Sex</td>
	               		<td style="width: 250px;">
	               			<!-- Select -->
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								selectItemManager.searchSexSelectItem();
							</span>
	               		</td>
	               	</tr>
	               	
	               	<tr>
	               		<td class="order">2</td>
	               		<td class="successColor" style="width: 150px;">CarrierStatus (No Provisional)</td>
	               		<td style="width: 250px;">
	               			<!-- Select -->
	               			<s:select 
								list="lstCarrierStatusNoProvisionalSelectItem"
								headerKey=""
								headerValue=""
								listKey="key"
								listValue="value" 
								cssStyle="width: 200px;" 
								/>
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
								selectItemManager.searchCarrierStatusNotProvisional();
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
	
	
	
	
	
	
	<br><br><br>
</s:form>
</body>
</html>