/*
* HideColumnX V.0.1
* Author: Thanapat.p 
* Date: 27/05/2014
* how to use: 	
* var hideColumnX = new HideColumnX();
  hideColumnX.start();
*/

/**
 *  @desc: HideColumnX constructor
 *  @type: public
 */
function HideColumnX(){ //Contructor
	//settings default value;
	this.expireCookie = 30; // day of expired Cookie 
	this.cookieName = "HIDECOL_"+window.location.pathname; //cookie name
	this.excludeColumns = ["order", "edit", "checkbox", "view", "CAN_NOT_HIDE"];
	this.titlebarPanel = "Please select columns to show.";
	this.msgAlertCookie = "Your browser seems to have cookies disabled. Make sure cookies are enabled.";
}
/**
 * สำหรับกำหนดวันหมดอายุของ cookie
 * @param newDay - integer example 10 or 30 or 40
 */
HideColumnX.prototype.setExpireCookie = function(newDay){
    this.expireCookie = newDay;
};
/**
 * สำหรับกำหนด cookie สำหรับหน้านั้นๆ
 * @param newCookieName
 */
HideColumnX.prototype.setCookieName = function(newCookieName){
	if(newCookieName!=""){
		this.cookieName = newCookieName;
	}
};
/**
 * เพิ่ม class สำหรับ column ที่ไม่ต้องการให้ ช่อน
 * @param excludeClasses - strings class example item1, item2, ..., itemX
 */
HideColumnX.prototype.addExcludeColumn = function(excludeClasses){
	this.excludeColumns.push(excludeClasses);
};
/**
 * กำหนด title bar 
 * @param newText - String
 */
HideColumnX.prototype.setTitlebar = function(newText){
    this.titlebarPanel = newText;
};
/**
 * กำหนดข้อความเตือน เมื่อมีการปิด cookie
 * @param newMsg - String new warning message if you disable cookie
 */
HideColumnX.prototype.setMsgAlertCookie = function(newMsg){
    this.msgAlertCookie = newMsg;
};
/**
* Start HideColumnX 
* @type: public
**/
HideColumnX.prototype.start = function(){
	var HidColumn = this;
	jQuery(function(){ //DOM ready
		
		//1. Hiding Column Onload page
		var cookie = HidColumn.getCookieValue(HidColumn.cookieName);
		if(cookie != ""){
			cookie = JSON.parse(cookie);
			jQuery.each(cookie, function(tableIndex, columnArray){ //fetch object cookie
				jQuery.each(columnArray, function(key, colNo){
					HidColumn.doHideShow(tableIndex, colNo, false);
				});
			});
		}
		
		//2. Create Panel 
		jQuery("table.HIDE_HEADER").each(function(tableIndex){ //loop สร้าง Panel ตามตารางที่มี
			var myPanel = jQuery('<div id="panel_hide_table_'+tableIndex+'" class="panel_hide_table_" title="'+HidColumn.titlebarPanel+'"></div>').appendTo('body');
			//loop สร้าง checkbox ชื่อรายการที่จาก header
			jQuery("table.HIDE_HEADER:eq("+tableIndex+")").find('th').each(function(colNo, element){
				colNo++;
				var that = jQuery(this);
				var text = jQuery.trim(that.text());
				if(text=='')return; //continue loop
				//check column can not hide
				for (var i=0; i<HidColumn.excludeColumns.length; i++) {
					if(that.hasClass(HidColumn.excludeColumns[i])){
						return; //continue loop สร้าง checkbox
					}
				}
				//create list checkbox 
				var myCheckbox = jQuery('<input type="checkbox" class="selected_hide_col" id="checkbox_hide_col_'+colNo+'" name="hideShow'+colNo+'" value="'+colNo+'">');
				if(that.is(':hidden')){
					myCheckbox.prop("checked", false);
				}else{
					myCheckbox.prop("checked", true);
				}
				//add to div panel
				myPanel.append(myCheckbox).append('<label for="'+myCheckbox.attr("id")+'">'+text+'</label><br/>');
				//bind event checkbox to doHideShow() column
				myCheckbox.click(function(event){
					HidColumn.doHideShow(tableIndex, colNo, this.checked);
					HidColumn.saveToCookie(tableIndex);
				});
			});
			//set to dialog
			jQuery( '#panel_hide_table_'+tableIndex ).dialog({
				autoOpen: false,
				resizable: false,
				close: function( e, ui ) {
					jQuery("table.HIDE_HEADER").tooltip('enable');
				},
				show: "blind", // effect
	            hide: "blind" // effect
			});
		});

		//3. Create tooltip in HEADER Column
		jQuery('table.HIDE_HEADER').tooltip({ 
			items: "table.HIDE_HEADER th:not('.checkbox ')", //selector indicating which items should show tooltips.
			tooltipClass: "icon-hide-column-x", //add custom css
			show: null, // show immediately
			position: { my: 'left+1 top+1', at: 'left top' },
			open: function(event, ui){
		        if (typeof(event.originalEvent) === 'undefined') {
		            return false;
		        }
		        var $id = jQuery(ui.tooltip).attr('id');
		        // close any lingering tooltips
		        jQuery('div.ui-tooltip').not('#' + $id).remove();
		    },
		    close: function(event, ui) {
		        ui.tooltip.hover(function(){
		        	jQuery(this).stop(true).fadeTo(400, 1); 
		        }, function(){
		        	jQuery(this).fadeOut('400', function(){
		        		jQuery(this).remove();
		            });
		        });
		    }
		}).each(function(tableIndex){
			var that = this;
			//add span icon for open dialog
			jQuery(that).tooltip('option', 'content', '<span id="iconHideColumn_'+tableIndex+'" class="ui-icon ui-icon-wrench icon-hide-column"></span>');
			//bind event to do openPanel() method
			jQuery("body").on("click", "span#iconHideColumn_"+tableIndex, function(event){
				HidColumn.openPanel(tableIndex, event);
			});
		});
		
	}); //end DOM ready
};
/**
* ซ่อน/แสดง Column
* @param tableIndex - index table ที่ตัองการจะซ่อน เริ่มต้นด้วย 0
* @param colNo - index Column ที่ตัองการจะซ่อน เริ่มต้นด้วย 1
* @param doShow - true=แสดง, false=ซ่อน
**/
HideColumnX.prototype.doHideShow = function(tableIndex, colNo, doShow){
	var columnHead = jQuery('table.HIDE_HEADER:eq('+tableIndex+') tr > :nth-child('+colNo+'):not(table)');
	var columnBody = jQuery('table.HIDE_CONTENT:eq('+tableIndex+') tr > :nth-child('+colNo+'):not(table)');
	if(doShow){
		columnHead.show();
		columnBody.show();
	}else{
		columnHead.hide();
		columnBody.hide();
	}
	//update width last column if has scroll y
	var scrollY = jQuery('table.HIDE_CONTENT:eq('+tableIndex+')').parent().css('overflow-y');
	if (scrollY == 'scroll'){ //มี scroll สอง table
		var columnHeadLast = jQuery('table.HIDE_HEADER:eq('+tableIndex+') tr > th:visible:last'); //column สุดท้ายของปัจจุบัน
		var columnBodyLast = jQuery('table.HIDE_CONTENT:eq('+tableIndex+') tr > :nth-child('+(columnHeadLast.index()+1)+'):not(table)');
		jQuery('table.HIDE_HEADER:eq('+tableIndex+') tr > th').css('width', ''); //reset width
		jQuery('table.HIDE_CONTENT:eq('+tableIndex+') tr > td').css('width', ''); //reset width
		columnHeadLast.css('width', 'auto');
		columnBodyLast.css('width', 'auto');
	}
};
/**
* ดึงค่า cookie
* @param cname - ชื่อ cookie
* @return cookie string
**/
HideColumnX.prototype.getCookieValue = function (cname){
	var name = cname + "=";
	var ca = document.cookie.split(";");
	for(var i = 0; i < ca.length; i++){
		var c = trim(ca[i]);
		if (c.indexOf(name) ==0 ) {
			return c.substring(name.length,c.length);
		}
	}
	return "";
};
/**
* เก็บค่าลง cookie
* @param tableIndex - index table ที่ต้องการจะซ่อน เริ่มต้นด้วย 0
**/
HideColumnX.prototype.saveToCookie = function(tableIndex){
	var HideColumn = this;
	//ดึงค่าcolum ที่ถูกซ่อนจาก checkbox มาในรูปแบบ array
	var columnHide = jQuery.map(jQuery("div#panel_hide_table_"+tableIndex+" > input.selected_hide_col:not(:checked)"), function(a){ 
		return parseInt(a.value); //return array integer 
	});
	//ดึงค่าเก่าใน cookie 
	var cookie = HideColumn.getCookieValue(HideColumn.cookieName); 
	if(cookie!=""){
		cookie = JSON.parse(cookie);
		cookie[tableIndex] = columnHide;
	}else{
		cookie = JSON.parse("{}");
		cookie[tableIndex] = columnHide;
	}
	//write cookie
	var expiredDay = HideColumn.expireCookie;
	var d = new Date();
	d.setTime(d.getTime()+(expiredDay*24*60*60*1000));
	var expires = "expires="+d.toGMTString();
	document.cookie = HideColumn.cookieName + '=' + JSON.stringify(cookie) + ';' + expires;
};

HideColumnX.prototype.openPanel = function(tableIndex, event){
	//กันไม่ได้เกิด event ของ element ข้างหลัง
	event.stopPropagation(); 
	//are Cookies enabled ?
	if(!navigator.cookieEnabled){
		alert(this.msgAlertCookie);
	}
	//disable tooltip
	jQuery("table.HIDE_HEADER").tooltip('disable'); 
	//open dialog at position mouse click
	jQuery( '#panel_hide_table_'+tableIndex ).dialog('option', 'position', {my: 'left top', at: 'right bottom', of: event}).dialog( "open" );
};