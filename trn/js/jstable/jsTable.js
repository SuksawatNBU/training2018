var divPopup;
var ids;	//id where not in

/************************** Popup **************************************/
/**
 * params :divId (div id ที่ใช้ include jsp)  
 * params :xWidth (ความกว้างของ popup)  
 * params :xHight (ความสูงของ popup)  
 * params :isModal (กำหนดให้เป็น modal popup?)  
 * params :cbClasssName (check box class name ในตารางผลลัพธ์)  
 */
function openPopup(divId, xWidth, xHight, isModal, cbClasssName){
	ids = "";
	jQuery(cbClasssName).each(function(i){
		ids += "," + jQuery(this).val();
	});
	
	divPopup = '#'+divId;
	jQuery(divPopup).dialog({
		autoOpen : false,
		height : xHight,
		width : xWidth,
		modal : isModal,
		position : [ '', ''],
		create : function(){
			//init();
		}
	});
	
	jQuery(divPopup).dialog('open');
}


/************************** table **************************************/
function createTable(tableId, classArr, elementArr){
	var rows = jQuery("#"+tableId+" tbody tr").length;
	
	var table = document.getElementById(tableId);
	var row = table.insertRow(rows);
	if(parseFloat(rows)%2==0){
		row.className = "LINE_EVEN";
	}else{
		row.className = "ui-widget-content";
	}
	var cell;

	//Order
	cell = row.insertCell(0);
	cell.className = "order";
	cell.innerHTML = rows + 1;

	//Detail
	for(var i=0; i<elementArr.length; i++){
		cell = row.insertCell(i+1);
		cell.className = classArr[i];
		cell.innerHTML = elementArr[i];
	}
}
/**
 * params :trEm (tr ของ table ที่จะ loop หา lineO,lineE)  
 */
function setStyleTable(trEm){ 
	jQuery(trEm).each(function(index){
		if((index+1)%2 == 0){
			jQuery(this).addClass("ui-widget-content");
		}else{
			jQuery(this).addClass("LINE_EVEN");
		}
	});
}