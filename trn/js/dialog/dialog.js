var divPopup;
var ids;
var callFunc;
var isMulti;
var tblId;
var isNavigate;
var isShowCloseBtn;
var arrObj = new Array();

/** ************************ Dialog Popup ************************************* */
/*
 * detail: สำหรับการเปิด popup กรณีที่ popup เป็นแบบเลือก 1 รายการ
 * */
function dialogSingleOpen(divId, xWidth, xHeight, isModal, initFunc, callBack, params, isNavi) {
	divPopup = '#' + divId;
	isNavigate = isNavi;
	ids = params;
	isMulti = false;
	callFunc = callBack;
	isShowCloseBtn = false;
	
	if (parseInt(jQuery(window).width(), 10) >= 1500) {
		if (xHeight >= 750) {
			xHeight = 700;
			xWidth = xWidth;
		}
	}
	
	if (initFunc != '') {
		jQuery(divPopup).dialog({
			autoOpen : false,
			height : xHeight,
			width : xWidth,
			modal : isModal,
			//position : { my: "center middle", at: "center middle", of: this },
			resizable: true,
			zIndex: 100,
			
			open : function() {
				 initFunc();
			}, 
			close: function() {
		    	$(this).dialog("destroy");
			}
	    });
	} else {
		jQuery(divPopup).dialog({
			autoOpen : false,
			height : xHeight,
			width : xWidth,
			modal : isModal,
			resizable: true,
			zIndex: 100, 
			close: function() {
		    	$(this).dialog("destroy");
			}
	    });
	}
	
	jQuery(divPopup).dialog('open');
}

/*
 * detail: สำหรับการเปิด popup กรณีที่ popup เป็นแบบเลือกมากกว่า 1 รายการ
 * */
function dialogMultiOpen(divId, xWidth, xHeight, isModal, initFunc, callBack, params, isNavi) {
	divPopup = '#' + divId;
	isNavigate = isNavi;
	ids = params;
	isMulti = true;
	callFunc = callBack;
	isShowCloseBtn = false;

	if (parseInt(jQuery(window).width(), 10) >= 1500) {
		if (xHeight >= 750) {
			xHeight = 700;
			xWidth = xWidth;
		}
	}
	
	if (initFunc != '') {
		jQuery(divPopup).dialog({
			autoOpen : false,
			height : xHeight,
			width : xWidth,
			modal : isModal,
			position : { my: "center middle", at: "center middle", of: window },
			resizable: true,
			zIndex: 100,
			
			open : function() {
				initFunc();
			}, 
			close: function() {
		    	$(this).dialog("destroy");
			}
		});
	} else {
		jQuery(divPopup).dialog({
			autoOpen : false,
			height : xHeight,
			width : xWidth,
			modal : isModal,
			resizable: true,
			zIndex: 100,
			
			position : { my: "center middle", at: "center middle", of: window }, 
			close: function() {
		    	$(this).dialog("destroy");
			}
		});
	}
	
	jQuery(divPopup).dialog('open');
}

function clearPopupResult (tblResultId) {
	// delete row 1 to length
	var myTable = document.getElementById(tblResultId);
	if(myTable != null){
		var rowCount = myTable.rows.length;
		for ( var x = rowCount - 1; x >= 1; x--) {
			myTable.deleteRow(x);
		}	
	}
}

/*
 * detail: สำหรับการเปิด popup แบบ Tree หรือ กรณีที่ต้องการ initial ข้อมูลครั้งเดียวตอนเปิด popup
 * */
var yWidth, yHeight;
function dialogCreate(divId, xWidth, xHeight, isModal, callBack, tableId, params) {
	divPopup = '#' + divId;
	ids = params;
	callFunc = callBack;
	tblId = tableId;
	yWidth = xWidth;
	yHeight = xHeight;
	isShowCloseBtn = true;

	if (parseInt(jQuery(window).width(), 10) >= 1500) {
		if (xHeight >= 750) {
			xHeight = 700;
			xWidth = xWidth;
		}
	}
	
	jQuery(divPopup).dialog({
		autoOpen : false,
		height : xHeight,
		width : xWidth,
		modal : isModal,
		position : { my: "center middle", at: "center middle", of: window },
		resizable: true,
		zIndex: 100,
		
		open : function() {
			initTree();
			checkNodeChoose();
		}, 
		close: function() {
	    	$(this).dialog("destroy");
		}
	});

	jQuery(divPopup).dialog('open');
}

function dialogCreateView(divId, xWidth, xHeight, isModal, params) {
	
	divPopup = '#' + divId;
	ids = params;
	yWidth = xWidth;
	yHeight = xHeight;
	isShowCloseBtn = true;

	if (parseInt(jQuery(window).width(), 10) >= 1500) {
		if (xHeight >= 750) {
			xHeight = 700;
			xWidth = xWidth;
		}
	}
	
	jQuery(divPopup).dialog({
		autoOpen : false,
		height : xHeight,
		width : xWidth,
		modal : isModal,
		position : { my: "center middle", at: "center middle", of: window },
		resizable: true,
		zIndex: 100,
		
		create : function() {
			initTree();
		}, 
		close: function() {
	    	$(this).dialog("destroy");
		}
	});

	jQuery(divPopup).dialog('open');
}
/** ************************ Dialog Popup ************************************* */

/*
 * detail: สำหรับแสดงรายละเอียดบนหน้า popup (single record)
 * */
function dialogShowDetail(obj) {
	jQuery(divPopup).loadJSON(obj);
}

/*
 * deatil: สำหรับการวาด css ของ row และ การกำหนด style sheet ในกรณีต่างๆ
 * params :trEm (tr ของ table ที่จะ loop หา lineO,lineE)
 */
function dialogSetStyle(trEm, divElementResult, isView) {
	tableSetStyleRow(trEm);

	arrObj = new Array();
	jQuery(trEm).each(function(index) {
		var obj = JSON.stringify(json[this.rowIndex]);
		arrObj.push(obj);

		if (isView == false) {
			if(isMulti == false){
				jQuery(this).mouseover(function() {
					jQuery(this).addClass("ui-state-highlight");
					jQuery(this).css("cursor", "pointer");
				});
	
				jQuery(this).mouseout(function() {
					jQuery(this).removeClass("ui-state-highlight");
				});
	
				// hide column checkbox
				jQuery(this).find('.checkbox').hide();
	
				// jQuery(this).click(function() {
				jQuery(this).dblclick(function() {
					jQuery(divPopup).dialog('destroy');
				});
	
				// event click row
				// jQuery(this).attr("onclick", callFunc + "(" + arrObj[this.rowIndex] + ");");
				jQuery(this).attr("ondblclick", callFunc + "(" + arrObj[this.rowIndex] + ");");
			}else{
				//remove event remove class
				jQuery(this).off("mouseover mouseout click");
				// jQuery(this).removeClass("ui-state-highlight");
				jQuery(this).css("cursor", "");
				
				// console.log(jQuery(this));
				
				jQuery(this).mouseover(function() {
					jQuery(this).addClass("ui-state-highlight");
				});
	
				jQuery(this).mouseout(function() {
					jQuery(this).removeClass("ui-state-highlight");
				});
				
				// show column checkbox
				jQuery(this).find('.checkbox').show();
				
				// remove event click row
				// jQuery(this).removeAttr("onclick");
				jQuery(this).removeAttr("ondblclick");
			}
		} else {
			// hide column checkbox
			jQuery(this).find('.checkbox').hide();
			
			jQuery(this).mouseover(function() {
				jQuery(this).addClass("ui-state-highlight");
				jQuery(this).css("cursor", "pointer");
			});

			jQuery(this).mouseout(function() {
				jQuery(this).removeClass("ui-state-highlight");
			});
		}
	});

	if(isMulti == true){
		jQuery('#isShow_' + divElementResult).show();
		jQuery('#buttonPopup_' + divElementResult).show();
	} else {
		jQuery('#isShow_' + divElementResult).hide();
		jQuery('#buttonPopup_' + divElementResult).hide();
	}
	
	if (isShowCloseBtn == false) {
		jQuery('#bntCloseDialog_' + divElementResult).hide();
	}
}

/*
 * detail: function เมื่อ click ปุ่มตกลงที่หน้า popup ในกรณีที่เป็น popup เลือกมากกว่า 1 รายการ
 *           โดยจะทำการตรวจสอบการเลือกรายการ, custom รายการที่เลือกเข้า arrString และดำเนินการปิด popup
 * */
function dialogChooseMultiRecord(elName) {
	if (divPopup != "" && jQuery(divPopup) !== undefined && jQuery(divPopup).css("display") !== undefined && jQuery(divPopup).css("display") !== 'none') {
		if (jQuery(divPopup).parent() !== undefined && jQuery(divPopup).parent().css("display") !== undefined && jQuery(divPopup).parent().css("display") !== 'none') {
			// ตรวจสอบการเลือกแบบธรรม
			if (validateSelect(elName) == false) {
				return false;
			}
		
			var index = 0;
			var obj = new Array();
			jQuery('input[name=' + elName + ']').each(function() {
				if (this.checked) {
					obj.push(JSON.parse(arrObj[index]));
				}
				index++;
			});
		
			callFunc(obj);
		
			jQuery(divPopup).dialog('destroy');
		}
	}
}

/*
 * detail: function เมื่อ click ปุ่มตกลงที่หน้า popup ในกรณีที่เป็น popup เลือกมากกว่า 1 รายการ ในรูปแบบ dialog ที่มี navigate
 *           โดยจะทำการตรวจสอบการเลือกรายการ, custom รายการที่เลือกเข้า arrString และดำเนินการปิด popup
 * */
function dialogChooseMultiRecordForNavi(urlAction) {
	if (divPopup != "" && jQuery(divPopup) !== undefined && jQuery(divPopup).css("display") !== undefined && jQuery(divPopup).css("display") !== 'none') {
		if (jQuery(divPopup).parent() !== undefined && jQuery(divPopup).parent().css("display") !== undefined && jQuery(divPopup).parent().css("display") !== 'none') {
			getIdCheckBox();
			
			// ตรวจสอบการเลือกแบบธรรม
			// if (document.getElementById('criteriaChooseIds').value == "") {
			if (jQuery(divPopup + ' #criteriaChooseIds').val() == "") {
				alert(validateMessage.CODE_10006);
				return false;
			}
			
			jQuery(divPopup + ' #criteriaChooseIds').val(jQuery(divPopup + ' #criteriaChooseIds').val().substring(1));
	
			var params = {
				id : jQuery(divPopup + ' #criteriaChooseIds').val()
			};
			
			jQuery.ajax({
				type : "POST",
				// url : "<s:property value='#urlPopupAction' />",
				url : urlAction,
				data : jQuery.param(params),
				async : true,
				dataType: "json",
				success : function(data) {
					callFunc(data);
				}
			});
			jQuery(divPopup).dialog('destroy');
		}
	}

}

/*
 * detail: function เมื่อ click ปุ่มตกลงที่หน้า popup ในกรณีที่เป็น popup แบบ Tree
 *           โดยจะทำการตรวจสอบการเลือกรายการ และดำเนินการปิด popup
 * */
function dialogChooseNodeTree(elName) {
	if (divPopup != "" && jQuery(divPopup) !== undefined && jQuery(divPopup).css("display") !== undefined && jQuery(divPopup).css("display") !== 'none') {
		if (jQuery(divPopup).parent() !== undefined && jQuery(divPopup).parent().css("display") !== undefined && jQuery(divPopup).parent().css("display") !== 'none') {
			// ตรวจสอบการเลือกแบบธรรม
			if (validateSelect(elName) == false) {
				return false;
			}
			// clear table result
			jQuery('#' + tblId).empty();
			jQuery(divPopup + " #idsSelectedRow").val('');
		
			// function call back
			callFunc(elName, json);
		
			jQuery(divPopup).dialog('destroy');	
		}
	}
}

/*
 * detail: สำหรับ check box ในรูปแบบ Tree การสร้าง check box - ให้ทำการ กำหนด id ของ
 *            check box เป็น value id ของตัวเอง - ค่า value ของ check box ให้ทำการ ค่า id
 *            ของ parent node concat กันด้วย comma จนถึง id ตัวเอง ex: clickNodeTree (this)
 */
function dialogCheckNodeTree(checkboxElement) {
	// check ลง
	jQuery("input[name='" + checkboxElement.name + "'][type=checkbox][value*='" + checkboxElement.id + "']").prop("checked", checkboxElement.checked);

	// check ขึ้น ด้วยเงือนไขถ้าไม่มีแม่ตัวไหนไม่มีลูก check ให้ uncheck ทิ้งไป
	var valueIds = (checkboxElement.value.split('-')[0]).split('_');
	for ( var i = valueIds.length - 1; i >= 0; i--) {
		var queryIds = "";
		if (checkboxElement.checked) {
			queryIds = "[id='" + valueIds[i] + "']";
		} else {
			if (jQuery("input[name='" + checkboxElement.name + "'][type=checkbox][value*='" + valueIds[i] + "']:checked[id!='" + valueIds[i] + "']").length == 0) {
				queryIds = "[id='" + valueIds[i] + "']";
			}
		}

		if (queryIds.length > 0) {
			jQuery("input[type=checkbox]" + queryIds).prop("checked", checkboxElement.checked);
		}
	}
}

function getIdCheckBox () {
	var index = 0;
	var chooseIds = jQuery('div' + divPopup + ' #criteriaChooseIds').val();
	// loop เก็บข้อมูล id ที่ทำการ checked
	jQuery('div' + divPopup + ' input[name="idPopup"][type="checkbox"]').each(function() {
		if (this.checked) {
			if (chooseIds == "") {
				chooseIds = "," + jQuery('div' + divPopup + ' input[name="idPopupVal"]')[index].value;
			} else {
				chooseIds += "," + jQuery('div' + divPopup + ' input[name="idPopupVal"]')[index].value;
			} 
		}
		index++;
	});
	
	jQuery('div' + divPopup + ' #criteriaChooseIds').val(chooseIds);
}

function setChecked () {
	var chooseIds = jQuery('div' + divPopup + ' #criteriaChooseIds').val();
	
	// กรณีที่ค่า chooseIds ไม่เท่ากับค่าว่างให้ทำการ split comma เพื่อ checked ที่ checkboox
	if (chooseIds !== undefined && chooseIds != "undefined" && chooseIds != "") {
		var arrIds = chooseIds.split(",");
		for ( var i = 0; i < arrIds.length; i++) {
			// jQuery("div" + divPopup + " input[name='idPopup'][type='checkbox'][id='checkbox" + arrIds[i] + "']").prop("checked", true);
			jQuery("div" + divPopup + " input[name='idPopupVal'][value='" + arrIds[i] + "']").prev().prop("checked", true);
		}
	}
}

/* ลบ id ที่ทำการ uncheck ออกจาก chooseIds */
function delAllIdsUncheck(chkbox){
	jQuery('input[name="idPopup"][type="checkbox"]').each(function() {
		if (chkbox.checked == false) {
			// get id name of el and replace checkbox to '' (check15 >> 15)'
			var id = this.id.replace('checkbox', '');
			
			var regex = new RegExp(","+id, 'g');
			var chooseIds = document.getElementById('criteriaChooseIds').value;
			chooseIds = chooseIds.replace(regex, '');
			document.getElementById('criteriaChooseIds').value = chooseIds;
		}
	});
}

function delIdsUncheck(el){
	if (el.checked == false) {
		// convert el obj to string 
		var str = el.getAttribute('id').toString();
		
		// get id name of el and replace checkbox to '' (check15 >> 15)'
		var id = str.replace('checkbox', '');
		
		var regex = new RegExp(","+id, 'g');
		var chooseIds = document.getElementById('criteriaChooseIds').value;
		chooseIds = chooseIds.replace(regex, '');
		document.getElementById('criteriaChooseIds').value = chooseIds;
	}
}

function dialogClose() {
	if(confirm(validateMessage.CODE_50017) == false){
		return false;
	}
	
	//jQuery(divPopup).dialog('close');
	jQuery(divPopup).dialog('destroy');
}
