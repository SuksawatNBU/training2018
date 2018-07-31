<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<script type="text/javascript" src="<s:url value='/js/jquery/jquery-suggestion.js' />"></script>
	<script type="text/javascript">
		function sf(){
			genCustomTableStyle("divResult",1000,null,null);
			
			//GET DATA IN ACTION
			var jsonSuggestDeparturePort = <s:property value="jsonSuggestDeparturePort" escapeHtml="false"/>;
			/* var jsonSuggestArrival = <s:property value="jsonSuggestArrival" escapeHtml="false"/>;
			var jsonSuggestAirportThai = <s:property value="jsonSuggestAirportThai" escapeHtml="false"/>; */
		
			console.info(jsonSuggestDeparturePort);
			
			//CREATE SUGGESTION
			jQuery(".suggestionDeparture").suggestion({
		    	source: jsonSuggestDeparturePort,
	            minLength: 1,
	            delay: 100,
	            autoFocus: true,
	            select: function( event, ui ) {
	                //console.log(ui.item);
	            }
	        });
			
			
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
						<th style="width: 150px;">Caption</th>
						<th style="width: 250px;">Suggestion</th>
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
	               		<td style="width: 150px;">Departure Port</td>
	               		<td style="width: 250px;">
							<input type="text" placeholder="Departure Port" style="width: 200px;" class="suggestionDeparture">
	               		
	               			<s:textfield  cssStyle="width: 200px;" cssClass="suggestionDeparture" ></s:textfield>
	               		</td>
	               		<td>
		               		<span class="globalTypColor" >
		               			selectItemManager.searchAirportSelectItem(conn, getLocale());
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
	<div style="width:1000px; margin-left: auto; margin-right: auto;">
	    <div class="exCode">
	    	----------------------------------------------------------------------------------------<br>
		    //--- ACTION<br>
		    ----------------------------------------------------------------------------------------<br>
	    	List&#60CommonSelectItem&#62 listPort = selectItemManager.searchDeparturePortSelectItem(conn, getLocale());<br>
			setJsonSuggestDeparture(JSONUtil.serialize(listAirport));&emsp;&emsp;<span class="successColor">//แปลงค่าที่ได้เป็น Json ใส่ไว้ในตัวแปรสตริง</span><br>
			<br><br><br>
			----------------------------------------------------------------------------------------<br>
		    //--- INCLUDE<br>
		    ----------------------------------------------------------------------------------------<br>
		   &#60script type="text/javascript" src="&#60s:url value='/js/jquery/jquery-suggestion.js' /&#62"&#62&#60/script&#62
			<br><br><br>
			----------------------------------------------------------------------------------------<br>
		    //--- JAVASCRIPT<br>
		    ----------------------------------------------------------------------------------------<br>
		    var jsonSuggestDeparturePort = &#60s:property value="jsonSuggestDeparturePort" escapeHtml="false"/&#62;;
		    <span class="successColor">// ดึงค่า Json จากใน action</span><br><br>
		    <span class="successColor">//CREATE SUGGESTION</span><br>
			jQuery(".suggestionDeparture").suggestion({<br>
		    &emsp;&emsp;source: jsonSuggestDeparturePort,&emsp;&emsp;<span class="successColor">//ข้อมูลในรูปแบบ Json</span><br>
	        &emsp;&emsp;minLength: 1, &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<span class="successColor">//จำนวน word การกรอกก่อนทำการ filter</span><br>
	       	&emsp;&emsp;delay: 100,&emsp; &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<span class="successColor">//การหน่วงเวลาก่อนการ filter</span><br>
	        &emsp;&emsp;autoFocus: true,&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;<span class="successColor">//คือการ set highlight เมื่อข้อมูลที่กรอกตรงกับใน list </span><br>
	        &emsp;&emsp;select: function( event, ui ) {<br>
	        &emsp;&emsp;&emsp;&emsp;//console.log(ui.item);<br>
	        &emsp;&emsp;}<br>
	        });<br>
		    <br><br><br><br>
		    ----------------------------------------------------------------------------------------<br>
		    //--- HTML<br>
		    ----------------------------------------------------------------------------------------<br>
		    &#60input type="text" placeholder="Departure airport" style="width: 200px;" class="suggestionDeparture"&#62<br><br>
		</div>
	</div>
</s:form>
</body>
</html>