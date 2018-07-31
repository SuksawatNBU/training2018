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
	               		<td class="successColor">Nationality Standard</td>
	               		<td>
	               		
	               		
	               		
	               		
	               		</td>
	               		<td>
	               			<span class="globalTypColor" >
	               			Servlet : &#60s:url value='/nationalitySTDSelectItemServlet'/&#62;
	               			</span>
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
<br><br><br>
</s:form>
</body>
</html>