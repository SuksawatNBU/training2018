/**
 * -------------------------- สร้าง table --------------------------
 */
function tableCreateTableRow(tableId, classArr, elementArr) {
	var rows = jQuery("table#" + tableId + " tbody tr").length;

	var table = document.getElementById(tableId);
	var row = table.insertRow(rows);
	var cell;

	// Order
	cell = row.insertCell(0);
	cell.className = "order";
	cell.innerHTML = rows + 1;

	// Detail
	for ( var i = 0; i < elementArr.length; i++) {
		cell = row.insertCell(i + 1);
		cell.className = classArr[i];
		cell.innerHTML = elementArr[i];
	}
	//update order & set style lineE/lineO
	tableReOrderSetStyleRow('table#' + tableId + ' tbody tr:visible');
}

/**
 * -------------------------- ลบข้อมูลในแถว --------------------------
 *  nameCheckBox: ชื่อ checkbox ของหน้า
 *  tbResultCon : id ตารางที่มีข้อมูล
 */
function  tableDeleteRow(nameCheckBox, tbResultCon, ids) {
	//ตรวจสอบการเลือกและต้องการ row ที่ถูกเลือก
	var status = validateSelectOne(nameCheckBox);

	if (status == false) {
		return false;
	}

	if (confirm(validateMessage.CODE_50016) == false) {
		return false;
	}

	//[1] hidden selected row
	status.hide();

	//[2] update flag delete=Y & disable checkbox
	status.each(function(i){
		var $that = jQuery(this);
		$that.hide();
		$that.find("td > input[name$='.deleteFlag']").val("Y"); //element ที่ชื่อลงท้ายด้วย .deleteFlag ให้ value=Y
		$that.find("td > input[type=checkbox]:checked").prop("checked" ,false).prop("disabled" ,true); //disable & uncheck the checkbox
	});

	var idsComma = "";
	//[3] reOrder & set lineE lineO
	jQuery("table#" + tbResultCon + " tbody > tr:visible").each(function(order) { //loop each row
		order++;
		var thisRow = jQuery(this);
		// reset lineO,lineE class & // update order
		thisRow.removeClass('LINE_EVEN LINE_ODD').find('td.order').text(order);
		//update CSS
		if (order%2 == 0) {
			thisRow.addClass("LINE_ODD");
		} else {
			thisRow.addClass("LINE_EVEN");
		}

		//[4] set ids in textfield
		var id = thisRow.find("td > input[type=checkbox]").val();
		if(idsComma == ""){
			idsComma = id;
		}else{
			idsComma = idsComma + "," + id;
		}
	});

//	Arunya.k แก้ปัญหาใช้ตาราง Template มากว่า 1 ตาราง ในหน้าเดียวกัน
	if (typeof ids === 'object') {
		ids.val(idsComma);
	}else{
		jQuery("#"+ids).val(idsComma);
	}

}

/**
 * -------------------------- การกำหนด style sheet ให้กับ table (lineE/lineO)  --------------------------
 *  trEm: tr ของ table ที่จะ loop หา lineO,lineE
 */
function tableSetStyleRow (trEm) {
	jQuery(trEm).each(function(index) {
		jQuery(this).removeClass('ui-widget-content LINE_EVEN LINE_ODD');
		if ((index + 1) % 2 == 0) {
			jQuery(this).addClass("ui-widget-content LINE_ODD");
		} else {
			jQuery(this).addClass("LINE_EVEN");
		}
	});
}

/**
 * -------------------------- จัดลำดับใหม่ และ set style sheet ให้กับ table (lineE/lineO)  --------------------------
 *  selector: tr ของ table ที่จะ loop หา lineO,lineE - เช่น table#result tr:visible
 */
function tableReOrderSetStyleRow(selector) {
	jQuery(selector).each(function(order) { //loop each row
		order++;
		var thisRow = jQuery(this);
		// reset lineO,lineE class & // update order
		thisRow.removeClass('LINE_EVEN LINE_ODD').find('td.order').text(order);
		//update CSS
		if (order%2 == 0) {
			thisRow.addClass("LINE_ODD");
		} else {
			thisRow.addClass("LINE_EVEN");
		}
	});
	
	setClassStyleAndTheme();
}

/**
 * -------------------------- ล้างค่าตารางหน้า popup  --------------------------
 *  tblResultId 	: ชื่อ id ตารางหน้า popup
 *  checkAllPopup 	: ชื่อ checkbox หน้า popup
 *  elNameChk 		: ...
 */
function clearTableResult(tblResultId, elNameChkAll, elNameChk){
	if(typeof (document.getElementsByName(elNameChkAll)[0]) != "undefined"){
		document.getElementsByName(elNameChkAll)[0].checked = false;
		checkboxToggle(elNameChk, false);
	}

	//delete row 1 to length
	var myTable = document.getElementById(tblResultId);
	var rowCount = myTable.rows.length;
	for( var x = rowCount -1; x >= 1; x--){
		myTable.deleteRow(x);
	}

}

/**
 * Auto generate table
 * @param id
 * @param table
 * @param isFoot
 */
function genTable(id, table, isFoot){
	jQuery("div [id ^= 'divResult']").html("");
	jQuery('#'+id).html("");//clear ค่า

	var divResultHeight = jQuery("#"+id).height();
	
	var styleBoxDetail = "";
	var styleResultContent = "width:"+(table.width != null?table.width:"")+"px;" ;
	
	var styleResultBoxHeader = "";
	var styleResultHeader ="width:"+(table.width != null?table.width:"")+"px;" ;
	
	var $footerTable = jQuery('table#footerTable_'+id);
	var $headerTable = jQuery('table#headerTable_'+id);

	//กรณี ไม่มี scoll ไม่ต้องกำหนดความสูให้ div Result
	if(table.overY == false ){
		jQuery("#"+id).css("height","auto");
		styleResultContent="";
		styleResultHeader="";
	}

	if(table.overX || table.overY ){
		//กำหนดความสูง  divDetail ในกรณีมี footer กับ ไม่มี
		if($footerTable.length != 0 && isFoot == true) {
			styleBoxDetail += "height: " + (divResultHeight - 103) + "px;";
		} else {
//			styleBoxDetail += "height: " + (divResultHeight -110 + 43 - (table.overX ? 9 : 0)) + "px;";
			//Nanthanat.l
			styleBoxDetail += "height: " + (divResultHeight -110 + 35 - (table.overX ? 9 : 0)) + "px;";
		}

		//กรณีมีแค่ scoll ล่างอย่างดียว
		if(table.overX==true && table.overY==false){
			styleBoxDetail="";
			styleBoxDetail+="overflow-y: hidden;overflow-x: scroll;";
			styleResultBoxHeader ="overflow: hidden;";
			styleResultContent ="width:"+(table.width != null?table.width:"")+"px;";
			styleResultHeader ="width:"+(table.width != null?table.width:"")+"px;" ;
			
			
		} else if(table.overX==true && table.overY==true){//กรณีมี scoll ทั้งคู่
			styleBoxDetail+="overflow:scroll;";
			styleResultBoxHeader ="width:"+(parseInt(table.width, 10) + 100)+"px;";
			styleResultHeader ="width:"+(parseInt(table.width, 10))+"px;";
		} else if (table.overX == false && table.overY == true) {
			//กรณีมีแค่ scoll ข้าง อย่างดียว
			
			styleResultHeader = "width: " + (table.width != null ? table.width : "") + "px;";
			styleResultBoxHeader = "overflow: hidden; width: 99.8%;";
			
			styleResultContent = "width: " + (table.width != null ? table.width : "") + "px;";
			styleBoxDetail += " overflow-x: hidden; overflow-y: scroll; width: 100%;";
		}
	}

	var html = "";
	//div title table
	if($headerTable.length > 0){
		html += '<div class="RESULT_BOX_TITAL" style="border: none;">'
		+'<table class="TOTAL_RECORD">'
		+$headerTable.html()
		+'</table>'
		+'</div>'
		;
		//ถ้าไม่มี header ไมีต้องใส่ <div class="RESULT_BOX BORDER_COLOR">
		html += '<div class="RESULT_BOX BORDER_COLOR" id="DIV_RESULT_BOX" style="height: '+ (divResultHeight-36) +'px;">';
	}
	
	//div column head table
	if (table.overX && table.overY) {
		html += "<div id='boxHeaderScroll"+id + "' style='overflow: hidden;'>";
	}
		html += '<div id="boxHeader'+id+'" class="ui-state-default" style="'+styleResultBoxHeader+'" >';
		html += '<table class="RESULT_HEADER ui-state-default HIDE_HEADER" style="'+styleResultHeader+'" >';
		html += jQuery('table#model_'+id+' tr.th').html();
		html += '</table>'
		html += '</div>';
	if (table.overX && table.overY) {
		html += "</div>";
	}

		$headerTable.remove();
		jQuery('table#model_'+id+' tr.th').remove();
		//กรณ๊ไม่่มี scoll ล่างและข้าง  จะไม่มี divBoxDetail ครอบ table ไว้
		if(table.overX || table.overY){
			
			html += '<div id="boxDetail'+id+'"  class="RESULT_BOX_DETAIL" style="'+styleBoxDetail+'" >';
		}
		html += '<table  class="RESULT_CONTENT HIDE_CONTENT"  id="tableId_'+id+'"  style="'+styleResultContent+'" >';

		html += jQuery("table#model_"+id+" tr.td:first").parent().html() ? jQuery("table#model_"+id+" tr.td:first").parent().html() : "";
		html += '</table>';

		//กรณ๊ไม่่มี scoll ล่างและข้าง  จะไม่มี divBoxDetail ครอบ table ไว้
		if(table.overX || table.overY){
			html += '</div>';
		}
		if(isFoot){
			if($footerTable.length !=0){
				html += '<div class="RESULT_BOX_TITAL BACKGROUND_COLOR" style="border: none;">';
				html += '<table class="TOTAL_RECORD">';
				html += $footerTable.html();
				html += '</table>';
				html += '</div>';
				$footerTable.remove();
			}
		}
		//ถ้าไม่มี header ไมีต้องใส่ <div class="RESULT_BOX BORDER_COLOR">
		if($headerTable.length > 0 && $footerTable.length > 0){
			html += '</div>';
		}
		html +='</div>'; //end div resule

	    jQuery('#'+id).html(html);//วาดตาราง

	    if (table.overX && table.overY) {
			jQuery('#boxDetail'+id).scroll(function(event) {
				//console.info(0);
				document.getElementById('boxHeaderScroll'+id).scrollLeft = document.getElementById('boxDetail'+id).scrollLeft;
			});
		} else {
			jQuery('#boxDetail'+id).scroll(function(event) {
				document.getElementById('boxHeader'+id).scrollLeft = document.getElementById('boxDetail'+id).scrollLeft;
			});	
		}

		jQuery("table#model_"+id+":hidden").remove(); //ลบตาราง model
}

/**
 * divId = id ของ divResult
 * divResultWidth = width of divResult (pixcel)
 * hfWidth = แบ่งการแสดงเป็นสองส่วนของ divResult เอา width ทางด้านซ้าย
 * hsWidth = แบ่งการแสดงเป็นสองส่วนของ divResult เอา width ทางด้านขวา
 * tableDetScrollWidth = width ของตารางที่เอาไว้ scroll
 * tableDetScrollHigh = Hight ของตารางที่เอาไว้ scroll
 */
function genCustomTableFreezeStyle(divId, divResultWidth, hfWidth, hsWidth, tableDetScrollWidth, tableDetScrollHigh){
	var _height = "";
	var _width = "";
	var _ofy = "";
	if(tableDetScrollHigh == null){
		_height = "auto;";
		_width = hsWidth + "px;";
		_ofy = "hidden;";
	} else{
		_height = tableDetScrollHigh + "px;";
		_width = (hsWidth-19) + "px;";
		_ofy = "scroll;";
	}
	
	jQuery("#"+divId).addClass("RESULT");
	jQuery("#"+divId).attr("style", "width: "+divResultWidth+"px;");
	var divborder = jQuery("#"+divId).children().next();
	divborder.addClass("RESULT_BOX BORDER_COLOR");

	//Title
	var divtitle = jQuery("#"+ divId + " #divTitleCustom");
	divtitle.attr("style", "width: "+divResultWidth+"px;");
	divtitle.addClass("RESULT_BOX_TITAL");
	divtitle.children("table").addClass("TOTAL_RECORD");

	//Header
	var divheader = jQuery("#"+ divId + " #divHeaderCustom");
	divheader.attr("style", "width: "+divResultWidth+"px;");
	divheader.addClass("RESULT_BOX_HEADER ui-state-default");
	//Header left
	var lHead = divheader.children("table#subFreezeHeaderCustom");
	lHead.addClass("RESULT_HEADER ui-state-default");
	lHead.attr("style", "width: "+hfWidth+"px; height: 35px; float: left;");
	//Header right
	var rHead = divheader.children("div#subScrollHeaderCustom");
	rHead.attr("style", "width: "+hsWidth+"px; overflow: hidden");
	rHead.children("table").attr("style", "width: "+tableDetScrollWidth+"px; height: 35px; float: left;");
	rHead.children("table").addClass("RESULT_HEADER ui-state-default HIDE_HEADER");

	//Detail
	var divdetail = jQuery("#"+ divId + " #divDetailCustom");
	divdetail.addClass("RESULT_BOX_DETAIL");
	divdetail.attr("style", "width: "+divResultWidth+"px; height: "+ _height +" overflow-x: hidden; overflow-y: " + _ofy);
	//Detail left
	var lDet = divdetail.children("div#subDivFreezeDetailCustom");
	lDet.attr("style", "width: "+hfWidth+"px; overflow-x: hidden; overflow-y: hidden; float: left;");
	lDet.children("table").attr("style", "width: "+hfWidth+"px; float: left;");
	lDet.children("table").addClass("RESULT_CONTENT");
	//Detail right
	var rDet = divdetail.children("div#subDivScrollDetailCustom");
	rDet.attr("style", "width: "+ _width +" overflow-x: hidden; overflow-y: hidden;");
	rDet.children("table").attr("style", "width: "+tableDetScrollWidth+"px; float: left;");
	rDet.children("table").addClass("RESULT_CONTENT");

	//Div scroll
	var scrollx = jQuery("#"+ divId + " #divOutScrollCustom");
	scrollx.attr("style", "width: "+divResultWidth+"px; overflow-x: scroll; overflow-y: hidden;");
	scrollx.html("<div id='divInScrollCustom' style='height: 1px; width: "+(hfWidth + tableDetScrollWidth)+"px;'></div>");
	
	jQuery("#"+ divId + " #divOutScrollCustom").scroll(function(){
		jQuery("#"+ divId + " #subDivScrollDetailCustom").scrollLeft(jQuery("#"+ divId + " #divOutScrollCustom").scrollLeft());
		jQuery("#"+ divId + " #subScrollHeaderCustom").scrollLeft(jQuery("#"+ divId + " #divOutScrollCustom").scrollLeft());
	});

	//Footer
	var footer = jQuery("#"+ divId + " #divFooterCustom");
	footer.addClass("RESULT_BOX_TITAL"); 
	footer.children("table").addClass("TOTAL_RECORD");
}

/**
 * divId = id ของ divResult
 * divResultWidth = width of divResult (pixcel)
 * tableDetScrollWidth = width ของตารางที่เอาไว้ scroll
 * tableDetScrollHigh = Hight ของตารางที่เอาไว้ scroll
 */
function genCustomTableStyle(divId, divResultWidth, tableDetScrollWidth, tableDetScrollHigh){
	var _height = "";
	var _width = "";
	var _ofx = "";
	var _ofy = "";
	if(tableDetScrollHigh == null){
		_height = "auto;";
		_ofy = "hidden;";
	} else{
		_height = tableDetScrollHigh + "px;";
		_ofy = "scroll;";
	}
	
	if(tableDetScrollWidth == null){
		_width = divResultWidth + "px;";
		_ofx = "hidden;";
	} else{
		_width = tableDetScrollWidth + "px;";
		_ofx = "scroll;";
	}
	
	jQuery("#"+divId).addClass("RESULT");
	jQuery("#"+divId).attr("style", "width: "+divResultWidth+"px;");

	var length = jQuery("#"+divId).children().length;
	var divborder;
	
	if(length == 1) {
		divborder = jQuery("#"+divId).children();
	} else {
		divborder = jQuery("#"+divId).children().next();
	}
	
	divborder.addClass("RESULT_BOX BORDER_COLOR");

	//Title
	var divtitle = jQuery("#"+ divId + " #divTitleCustom");
	divtitle.attr("style", "width: "+divResultWidth+"px;");
	divtitle.addClass("RESULT_BOX_TITAL");
	divtitle.children("table").addClass("TOTAL_RECORD");
	
	//Header
	var divheader = jQuery("#"+ divId + " #divHeaderCustom");
	divheader.attr("style", "width: "+divResultWidth+"px; overflow: hidden");
	divheader.addClass("RESULT_BOX_HEADER ui-state-default");
	
	var head = divheader.children("table#subHeaderCustom");
	head.addClass("RESULT_HEADER ui-state-default");
	head.attr("style", "width: "+_width);

	//Detail
	var divdetail = jQuery("#"+ divId + " #divDetailCustom");
	divdetail.addClass("RESULT_BOX_DETAIL");
	divdetail.attr("style", "width: "+divResultWidth+"px; height: "+ _height +" overflow-x: "+ _ofx +" overflow-y: "+ _ofy);
	
	var det = divdetail.children("table");
	det.attr("style", "width: "+_width);
	det.addClass("RESULT_CONTENT");

	jQuery("#"+ divId + " #divDetailCustom").scroll(function(){
		jQuery("#"+ divId + " #divHeaderCustom").scrollLeft(jQuery("#"+ divId + " #divDetailCustom").scrollLeft());
	});
	
	// set style mouse over row
	jQuery("#"+ divId + " #divDetailCustom > table > tbody > tr").each(function () {
		jQuery(this).mouseover(function() {
			jQuery(this).addClass("ui-state-highlight");
		});

		jQuery(this).mouseout(function() {
			jQuery(this).removeClass("ui-state-highlight");
		});
	});
	
	//Footer
	var footer = jQuery("#"+ divId + " #divFooterCustom");
	footer.addClass("RESULT_BOX_TITAL"); 
	footer.children("table").addClass("TOTAL_RECORD");
}