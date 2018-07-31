<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<link href="<s:url value='/css/jquery.treeview.css' />" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<s:url value='/js/jquery.treeview.js' />"></script>
<script type="text/javascript">
	jQuery(document).ready(function(){
		//Default button
		jQuery(":button").button();
	
		//ปุ่มและ icon
		jQuery(".btnAdd").button({
		    icons: {
		        primary: "ui-icon-disk"
		    }
		});
	
		jQuery(".btnCancel").button({
		    icons: {
		        primary: "ui-icon-close"
		    }
		});
	});

	var json;
	var cursorX;
	var cursorY;
	
	function initTree(){
// 		var strUrl = jQuery(divPopup + " > input[id='URL']").val();
// 		var strTreeId = jQuery(divPopup + " > input[id='treeId']").val();
// 		var strTreeType = jQuery(divPopup + " > input[id='treeType']").val();
// 		var strEvent = jQuery(divPopup + " > input[id='event']").val();
		
// 		var params = {
// 			id : ids,
//  			treeId :strTreeId, 
// 			treeType:strTreeType,
// 			event: strEvent
// 		};
		
		var params = {
			id : ids,
			treeId : "<s:property value='#treeId[0]'/>",
			treeType: "<s:property value='#treeType[0]'/>",
			event: "<s:property value='#event[0]'/>"
		};
		
		console.log(params);
		
		// FIXME
		var strUrl = "<s:property value='#url[0]' />";
		
 		jQuery.ajax({
			type : "POST",
			url : "${pageContext.request.contextPath}"+"<s:url value='"+strUrl+"' />",
			data : jQuery.param(params),
			async : true,
			dataType: "json",
			success : function(data) {
				if (data.mapTree == null) {
					alert("<s:text name='30004'/>");
				} else {
// 					// เก็บข้อมูล json
// 					json = data.mapTree;

// 					// นำ html ของ tree ไปใส่ใน div
// 					jQuery("#" +strTreeId + "_VIEW").html(data.htmlTree);

// 					// กำหนด ความกว้าง-ความสูงของ tree ให้เท่ากับ dialog
// 					jQuery("#" +strTreeId + "_VIEW").css("width", "100%");
// 					jQuery("#" +strTreeId + "_VIEW").css("height", "80%");

// 					// ผูก event click ที่ checkbox
// 					jQuery("input[name='" +strTreeId +"_CHECKBOX']").click(function() {
// 						dialogCheckNodeTree(this);
// 					});

// 					// set background
// 					//jQuery("ul[id='" +strTreeId +"'] li ul").addClass('ui-widget-content').css("border", "0");
// 					jQuery("ul[id='" +strTreeId +"'] li ul").css("border", "0");
					
// 					// นำมาสร้างเป็น tree
// 					jQuery("#" +strTreeId + "").treeview({
// 						collapsed : false,
// 						control : "#" +strTreeId + "_CONTROL",
// 						treeviewId : strTreeId
// 					});
					
// 					jQuery("." +strTreeId + "_SPAN").each(function() {
// 						/* กรณีที่ไม่มีการ set ค่า attribute description ของ tree ให้ทำการ remove attribute data-tooltip และ data-tooltip-position ออกจาก span tag */
// 						if (jQuery(this).attr("data-tooltip") == null || jQuery(this).attr("data-tooltip") == "null" || jQuery(this).attr("data-tooltip") == "") {
// 							jQuery(this).removeAttr('data-tooltip');
// 							jQuery(this).removeAttr('data-tooltip-position');
// 						}
						
// 						// set style mouse over
// 						jQuery(this).mouseover(function(e) {
// 						    cursorX = e.pageX;
// 						    cursorY = e.pageY;
						    
// 						 	// ย้ายตำแหน่ง tooltip จากด้านขวา เป็น ด้านบน
// 						 	// console.info("divPopup width: " + yWidth+" Cursor at: " + cursorX + ", " + cursorY);
// 						 	if (cursorX > yWidth) {
// 						 		// กรณีที่ตำแหน่งที่แสดง มากกว่า ขนาด dialog ย้ายแสดง tooltip ด้านบน
// 						 		jQuery(this).removeAttr("data-tooltip-position");
// 						 		jQuery(this).attr("data-tooltip-position", "top");
// 						 	}
						 	
// 							jQuery(this).removeClass("hover");
// 							jQuery(this).addClass("ui-corner-all ui-state-hover");
// 							jQuery(".collapsable > ." +strTreeId + "_SPAN").css("cursor", "pointer");

// 							jQuery(this).click(function() {
// 								// Clear all selected states
// 							    //jQuery("." +strTreeId + "_SPAN").removeClass('ui-state-active');

// 							 	// Set current as selected
// 							    //jQuery(this).addClass("ui-state-active");
// 							});
// 						});

// 						// set style mouse out
// 						jQuery(this).mouseout(function() {
// 							jQuery(this).removeClass("ui-state-hover ui-corner-all");
// 						});




// เก็บข้อมูล json
					json = data.mapTree;

					// นำ html ของ tree ไปใส่ใน div
					jQuery("#<s:property value='#treeId[0]'/>_VIEW").html(data.htmlTree);

					// กำหนด ความกว้าง-ความสูงของ tree ให้เท่ากับ dialog
					jQuery("#<s:property value='#treeId[0]'/>_VIEW").css("width", "100%");
					jQuery("#<s:property value='#treeId[0]'/>_VIEW").css("height", "80%");

					// ผูก event click ที่ checkbox
					jQuery("input[name='<s:property value='#treeId[0]'/>_CHECKBOX']").click(function() {
						dialogCheckNodeTree(this);
					});

					// set background
					jQuery("ul[id=<s:property value='#treeId[0]'/>] li ul").addClass('ui-widget-content').css("border", "0");
					
					// นำมาสร้างเป็น tree
					jQuery("#<s:property value='#treeId[0]'/>").treeview({
						collapsed : false,
						control : "#<s:property value='#treeId[0]'/>_CONTROL",
						treeviewId : "<s:property value='#treeId[0]'/>"
					});
					
					jQuery(".<s:property value='#treeId[0]'/>_SPAN").each(function() {
						/* กรณีที่ไม่มีการ set ค่า attribute description ของ tree ให้ทำการ remove attribute data-tooltip และ data-tooltip-position ออกจาก span tag */
						if (jQuery(this).attr("data-tooltip") == null || jQuery(this).attr("data-tooltip") == "null" || jQuery(this).attr("data-tooltip") == "") {
							jQuery(this).removeAttr('data-tooltip');
							jQuery(this).removeAttr('data-tooltip-position');
						}
						
						// set style mouse over
						jQuery(this).mouseover(function(e) {
						    cursorX = e.pageX;
						    cursorY = e.pageY;
						    
						 	// ย้ายตำแหน่ง tooltip จากด้านขวา เป็น ด้านบน
						 	// console.info("divPopup width: " + yWidth+" Cursor at: " + cursorX + ", " + cursorY);
						 	if (cursorX > yWidth) {
						 		// กรณีที่ตำแหน่งที่แสดง มากกว่า ขนาด dialog ย้ายแสดง tooltip ด้านบน
						 		jQuery(this).removeAttr("data-tooltip-position");
						 		jQuery(this).attr("data-tooltip-position", "top");
						 	}
						 	
							jQuery(this).removeClass("hover");
							jQuery(this).addClass("ui-corner-all ui-state-hover");
							jQuery(".collapsable > .<s:property value='#treeId[0]'/>_SPAN").css("cursor", "pointer");

							jQuery(this).click(function() {
								// Clear all selected states
							    jQuery(".<s:property value='#treeId[0]'/>_SPAN").removeClass('ui-state-active');

							 	// Set current as selected
							    jQuery(this).addClass("ui-state-active");
							});
						});
						
						// set style mouse out
						jQuery(this).mouseout(function() {
							jQuery(this).removeClass("ui-state-hover ui-corner-all");
						});
					});
					checkNodeChoose();
				}
			}
		});
	}
	
	function checkNodeChoose(){
		
		var strUrl = jQuery(divPopup + " > input[id='URL']").val();
		var strTreeId = jQuery(divPopup + " > input[id='treeId']").val();
		var strTreeType = jQuery(divPopup + " > input[id='treeType']").val();
		var strEvent = jQuery(divPopup + " > input[id='event']").val();
		
		if (strTreeType == "CHECKBOX") {
			/* ทำการเลือกค่า checkbox ที่อยู่ในตาราง */
			checkboxToggle(strTreeId+ "_CHECKBOX", this.checked);
			if (trim(ids) != "") {
				var idsArray = ids.split(',');
				for (var i = 0; i < idsArray.length; i++) {
					var value = jQuery("input[name='" + strTreeId + "_CHECKBOX'][id='" + idsArray[i] + "']").val();
					if (value == undefined) {
						continue;
					}
					var valueIds = value.split('_');
					for ( var j = valueIds.length - 1; j >= 0; j--) {
						jQuery("input[name='" + strTreeId + "_CHECKBOX'][id='" + valueIds[j]+ "']").prop("checked", true);
					}
				}
			}

			setStyleTree();
		} else {
			if (trim(ids) != "") {
				var idsArray = ids.split(',');
				for(var i = 0; i < idsArray.length; i++){
					var value = jQuery("input[name='HIDDEN_"+idsArray[i]+"']").val();
					if (value == undefined) {
						continue;
					}
					var arrValue = value.split('_');
					for(var j = 0; j < arrValue.length; j++){
						//jQuery("#"+arrValue[j]).addClass("ui-state-active");
					}
				}
			}
		}
	}

	function getParentName(ids) {
		var parentName = "";
		var arrayIds = ids.split('_');
		for (var i = 1; i < arrayIds.length -1; i++) {
			if (parentName.length > 0) {
				parentName += "&nbsp;&rsaquo;&nbsp;";
			}
			parentName += json[arrayIds[i]].label;
		}
		return parentName;
	}

	function setStyleTree(){
		
		var strUrl = jQuery(divPopup + " > input[id='URL']").val();
		var strTreeId = jQuery(divPopup + " > input[id='treeId']").val();
		var strTreeType = jQuery(divPopup + " > input[id='treeType']").val();
		var strEvent = jQuery(divPopup + " > input[id='event']").val();
		
		/* high ligth รายการที่ทำการเลือก */
		jQuery("input[name='" + strTreeId + "_CHECKBOX']").each(function () {
			if (this.checked) {
				// Set current as selected
			    //jQuery(this).next().addClass("ui-state-active");
			} else {
				// Clear all selected states
			    //jQuery(this).next().removeClass('ui-state-active');
			}
			
			jQuery(this).click(function() {
				// checkbox ขึ้น
				var value = jQuery(this).val();
				var valueIds = value.split('_');
				 for ( var j = valueIds.length - 1; j >= 0; j--) {
					 if(jQuery("input[name='" + strTreeId + "_CHECKBOX'][id='" + valueIds[j]+ "']").prop('checked') == true){
						// Set current as selected
						//jQuery("input[name='" + strTreeId + "_CHECKBOX'][id='" + valueIds[j]+ "']").next().addClass("ui-state-active");
					} else {
						// Clear all selected states
					    //jQuery("input[name='" + strTreeId + "_CHECKBOX'][id='" + valueIds[j]+ "']").next().removeClass('ui-state-active');
					}
				}
					
				// checkbox ลง
				 var id = jQuery(this).attr('id');
				 if(jQuery("input[name='" + strTreeId + "_CHECKBOX'][type=checkbox][value*='" + id + "']").prop('checked') == true){
					// Set current as selected
					//jQuery("input[name='" + strTreeId + "_CHECKBOX'][value*='" + id + "']").next().addClass("ui-state-active");
				} else {
					// Clear all selected states
				    //jQuery("input[name='" + strTreeId + "_CHECKBOX'][value*='" + id + "']").next().removeClass('ui-state-active');
				}
			});
		});
	}
</script>
</head>

<div id="<s:property value='#treeId[0]'/>_CONTROL">
	<s:if test="#treeType[0] == 'CHECKBOX'">
		<input type="checkbox" name="checkAll" onclick="checkboxToggle('<s:property value='#treeId[0]'/>_CHECKBOX', this.checked); setStyleTree();" />&nbsp;<s:text name="all"/>
	</s:if>
	
	<a href="#"><img src="<s:url value='/images/treeview/minus.gif' />" />&nbsp;<s:text name="closeAll"/></a>
	<a href="#"><img src="<s:url value='/images/treeview/plus.gif' />" />&nbsp;<s:text name="openAll"/></a>
</div>

<div id="<s:property value='#treeId[0]'/>_VIEW" style="overflow: hidden; overflow-y: auto;"></div>

<input type="text" style="display:none;" value="<s:property value='#url[0]'/>" id="URL" />
<input type="text" style="display:none;" value="<s:property value='#treeId[0]'/>" id="treeId" />
<input type="text" style="display:none;" value="<s:property value='#treeType[0]'/>" id="treeType" />
<input type="text" style="display:none;" value="<s:property value='#"event"[0]'/>" id="event" />

<s:if test="#treeType[0] == 'CHECKBOX'">
	<!------------------------------------- BUTTON ----------------------------------->
	<table id="buttonPopup_<s:property value='#treeId[0]'/>" style="table-layout: fixed;" class="FORM" >
		<tr>
			<td style="width: 60%; height: 5px;"></td>
			<td style="width: 40%; height: 5px;">
		</tr>
		<tr>
			<td style="width: 60%;"></td>
			<td style="width: 40%;">
				<button id="btnOk"  class="btnAdd" onclick="dialogChooseNodeTree('<s:property value='#treeId[0]'/>_CHECKBOX');">
					<s:text name="ok"/>
				</button>
				<button id="bntCloseDialog_<s:property value='#divresult[0]'/>" class="btnCancel"   type="button" onclick="return dialogClose();" >
					<s:text name="closePage"/>
				</button>
			</td>
		</tr>
	</table>
	<!------------------------------------- BUTTON ------------------------------------>
</s:if>
<s:else>
	<!------------------------------------- BUTTON ----------------------------------->
	<s:include value="/jsp/template/button.jsp">
		<s:param name="buttonType" value="%{'view,enable'}" />
		<s:param name="function" value="%{'dialogClose()'}" />
	</s:include>
	<!------------------------------------- BUTTON ------------------------------------>
</s:else>