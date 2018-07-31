var table;
var order = new Array();

//HEADER SORT DATA TABLE
var hSelect;
var oSelect;

/*
 * เมื่อ initial datatable mุกครั้งต้อง กำหนด Default order ก่อน ดังนั้นจึงต้อง get order มาจาก headerSortsSelect มาเก็บไว้ก่อนทุกครั้ง
 * criteria.headerSortsSelect จะต้อง hidden ไว้ที่หน้า jsp
 */
jQuery(document).ready(function(){
	/*var hSelect = jQuery("[name='criteria.headerSortsSelect']").val();
	var oSelect = jQuery("[name='criteria.orderSortsSelect']").val();
	if((hSelect != null && hSelect != "")&&(oSelect != null && oSelect != "")){
    	var hs = hSelect.split(",");
    	var os = oSelect.split(",");
    	for ( var i = 0; i < hs.length; i++) {
			order[i] = [hs[i], os[i].toLowerCase()];
		}
    	//console.log(getLanguage());
	}*/
	
	initHeaderSort(false);
});

function initHeaderSort(defaultFromCriteria) {
	
	//STORE HEADER VALUE FROM CRITERIA
	//TRUE WHEN PAGE IS DRAW
	if(hSelect == null && oSelect == null) {
		hSelect = jQuery("[name='criteria.headerSortsSelect']").val();
		oSelect = jQuery("[name='criteria.orderSortsSelect']").val();
	}
	
	//STORE DEFAULT HEADER VALUE WHICH IS THE SAME AS METHOD setDefaultHeaderSorts() IN *SearchCriteria.java
	//TRUE WHEN OPENED FROM MENU
	if(jQuery("[name='criteria.defaultHeaderSortsSelect']").val() == "" && jQuery("[name='criteria.defaultOrderSortsSelect']").val() == ""){
		jQuery("[name='criteria.defaultHeaderSortsSelect']").val(jQuery("[name='criteria.headerSortsSelect']").val());
		jQuery("[name='criteria.defaultOrderSortsSelect']").val(jQuery("[name='criteria.orderSortsSelect']").val());
	}
	
	//SET HEADER SORT TO DEFAULT WHICH IS THE SAME AS METHOD setDefaultHeaderSorts() IN *SearchCriteria.java
	if(defaultFromCriteria) {
		hSelect = jQuery("[name='criteria.defaultHeaderSortsSelect']").val();
		oSelect = jQuery("[name='criteria.defaultOrderSortsSelect']").val();
	}
	
	if((hSelect != null && hSelect != "")&&(oSelect != null && oSelect != "")){
    	var hs = hSelect.split(",");
    	var os = oSelect.split(",");
    	for ( var i = 0; i < hs.length; i++) {
			order[i] = [hs[i], os[i].toLowerCase()];
		}
	}
}

/**
 * dataTable
 * @param context :
 * @param colData : (Object) สำหรับกำหนด data ที่ต้องการแสดง
 * @param aOption : (Object) สำหรับกำหนดส่วนเสริมต่างๆให้ตาราง
 */
function dataTable(context, colData, aOption, isClear){
	if (jQuery("[name='criteria.criteriaKey']").val() == "" && isClear == undefined) {
		clearMessageDataTable();
	}

	jQuery("#" + aOption.divResultId).show();

	var lpp = jQuery("[name='criteria.linePerPage']").val();
	var start = 1;
	if(jQuery("[name='criteria.criteriaKey']").val() != ""){
		start = jQuery("[name='criteria.start']").val();
	} else {
		//SET HEADER SORT TO DEFAULT
		initHeaderSort(true);
	}

	jQuery("#"+aOption.tableId).off('draw.dt');

	table = jQuery("#"+aOption.tableId).DataTable({
		autoWidth: false,
		scrollCollapse: false, // ไม่อนุญาติให้ table ลดความสูง
		processing: true,
		serverSide: true,
		scrollY: "300px",
		jQueryUI: true,
		destroy: true,		//Must destroy early crate table.
		dom: "<'top'ip><'H'>rt<'bottom'p><'F'>",	//Set option
		order: order,
		pagingType: "full_numbers",	//to set pagination, default is next and previous only
		pageLength: lpp,
		displayStart: parseInt(start)-1,
		ajax: {
			type: "POST",
			url : aOption.urlSearch + "?" + jQuery('form').serialize(),
			dataSrc : "result"//,	//Default = data
			/*error : function(res){
				submitAction(aOption.urlSearch);
			}*/
		},
		columns: colData,
        createdRow: function( row, data, index ) {	//Row created callback
        	createCommonIcon(context, row, data, index, aOption);
    		if(aOption.createdRowFunc != undefined){
    			window[aOption.createdRowFunc](row, data);
    		}
            return row;
        },
	    initComplete: function (setting, myCriteria) {
	    	//เตรียม serialize grikt5hkdf sort จะไม่ draw table ใหม่
	    	setting.ajax = {
	    		type: "POST",
    			url : aOption.urlSearch + "?" + jQuery('form').serialize(),
    			dataSrc : "result"//,	//Default = data
    			/*error : function(res){
    				submitAction(aOption.urlSearch);
    			}*/
	    	};

	    	manageMessageAjax(aOption, myCriteria);
	    },
		language: getLanguage(),
		drawCallback: function( settings ) {
			initDisplay1600();

			if(aOption.createdTableFunc != undefined){
				window[aOption.createdTableFunc](settings);
			}

			jQuery("div.CRITERIA input:first , div.CRITERIA select:first").not(".hasDatepicker").focus();

			setCssHover(aOption.tableId);
		}
	});

	//Event on drew table
	jQuery("#"+aOption.tableId).on('draw.dt', function (oTable, oData){
		drawnDt(context, aOption, oTable, oData);
	} );

	if(aOption.footerHtml != undefined){
		//Create icon in footer
		jQuery("div.bottom").next().html(aOption.footerHtml);
	}
}

/**
 * fixedColumns
 * @param context :
 * @param colData : (Object) สำหรับกำหนด data ที่ต้องการแสดง
 * @param aOption : (Object) สำหรับกำหนดส่วนเสริมต่างๆให้ตาราง
 */
function fixedColumns(context, colData, aOption, isClear){
	if (jQuery("[name='criteria.criteriaKey']").val() == "" && isClear == undefined) {
		clearMessageDataTable();
	}

	jQuery("#" + aOption.divResultId).show();

	var lpp = jQuery("[name='criteria.linePerPage']").val();
	var start = 1;
	if(jQuery("[name='criteria.criteriaKey']").val() != ""){
		start = jQuery("[name='criteria.start']").val();
	} else {
		//SET HEADER SORT TO DEFAULT
		initHeaderSort(true);
	}

	jQuery("#"+aOption.tableId).off('draw.dt');

	table = jQuery("#"+aOption.tableId).DataTable({
		autoWidth: true,
		scrollCollapse: false, // ไม่อนุญาติให้ table ลดความสูง
		processing: true,
		serverSide: true,
		scrollY: "300px",
        scrollX: true,
		jQueryUI: true,
		destroy: true,		//Must destroy early crate table.
		dom: "<'top'ip><'H'>rt<'bottom'p><'F'>",	//Set option
		order: order,
		pagingType: "full_numbers",	//to set pagination, default is next and previous only
		pageLength: lpp,
		displayStart: parseInt(start)-1,
		pageginationAlertFunc : aOption.pageginationAlertFunc,
		ajax: {
			type: "POST",
			url : aOption.urlSearch + "?" + jQuery('form').serialize(),
			dataSrc : "result"	//Default = data (Object result declare in CommonModel)
			/*error : function(res){
				submitAction(aOption.urlSearch);
			}*/
		},
		columns: colData,
        createdRow: function( row, data, index ) {	//Row cretaed callback
        	createCommonIcon(context, row, data, index, aOption);
    		if(aOption.createdRowFunc != undefined){
    			window[aOption.createdRowFunc](row, data);
    		}
            return row;
        },
	    initComplete: function (setting, myCriteria) {
	    	//เตรียม serialize sort จะไม่ draw table ใหม่
	    	setting.ajax = {
	    		type: "POST",
    			url : aOption.urlSearch + "?" + jQuery('form').serialize(),
    			dataSrc : "result"	//Default = data
    			/*error : function(res){
    				submitAction(aOption.urlSearch);
    			}*/
	    	};

	    	manageMessageAjax(aOption, myCriteria);
	    },
	    language: getLanguage(),
	    drawCallback: function( settings ) {
	    	initDisplay1600();

	    	if(aOption.createdTableFunc != undefined){
	    		window[aOption.createdTableFunc](settings);
	    	}

	    	jQuery("div.CRITERIA input:first , div.CRITERIA select:first").not(".hasDatepicker").focus();

	    	if(aOption.alwayDrawTableFunc != undefined){
	    		window[aOption.alwayDrawTableFunc](settings);  // ทำทุกครั้งที่วาด
	    	}
	    }
	});

	//Event on drew table
	jQuery("#"+aOption.tableId).on('draw.dt', function (oTable, oData){
		drawnDt(context, aOption, oTable, oData);
	} );

	new jQuery.fn.dataTable.FixedColumns(table, {
		leftColumns: aOption.fixedCoumnsLeft,
		rightColumns: aOption.fixedCoumnsRight,
		 fnDrawCallback:function(){
			 setCssHover(aOption.tableId);
        }
	});

	if(aOption.footerHtml != undefined){
		//Create icon in footer
		jQuery("div.bottom").next().html(aOption.footerHtml);
	}
}

/**
 * dataTable Row Child
 * @param context :
 * @param colData : (Object) สำหรับกำหนด data ที่ต้องการแสดง
 * @param aOption : (Object) สำหรับกำหนดส่วนเสริมต่างๆให้ตาราง
 */
function rowChild(context, colData, aOption, isClear){
	if (jQuery("[name='criteria.criteriaKey']").val() == "" && isClear == undefined) {
		clearMessageDataTable();
	}

	jQuery("#" + aOption.divResultId).show();

	var lpp = jQuery("[name='criteria.linePerPage']").val();
	var start = 1;
	if(jQuery("[name='criteria.criteriaKey']").val() != ""){
		start = jQuery("[name='criteria.start']").val();
	} else {
		//SET HEADER SORT TO DEFAULT
		initHeaderSort(true);
	}

	jQuery("#"+aOption.tableId).off('draw.dt');

	table = jQuery("#"+aOption.tableId).DataTable ({
		processing: true,
		serverSide: true,
		jQueryUI: true,
		scrollY: "300px",
		destroy: true,		//Must destroy early crate table.
		dom: "<'top'ip><'H'>rt<'bottom'p><'F'>",	//Set option
		order: order,
		pagingType: "full_numbers",	//to set pagination, default is next and previous only
		pageLength: lpp,
		displayStart: parseInt(start)-1,
		ajax: {
			type: "POST",
			url : aOption.urlSearch + "?" + jQuery('form').serialize(),
			dataSrc : "result"	//Default = data
			/*error : function(res){
				submitAction(aOption.urlSearch);
			}*/
		},
		columns: colData,
        createdRow: function( row, data, index ) {	//Row created callback
        	createCommonIcon(context, row, data, index, aOption);
    		if(aOption.createdRowFunc != undefined){
    			window[aOption.createdRowFunc](row, data);
    		}
            return row;
        },
	    initComplete: function (setting, myCriteria) {
	    	//เตรียม serialize grikt5hkdf sort จะไม่ draw table ใหม่
	    	setting.ajax = {
	    		type: "POST",
    			url : aOption.urlSearch + "?" + jQuery('form').serialize(),
    			dataSrc : "result"	//Default = data
    			/*error : function(res){
    				submitAction(aOption.urlSearch);
    			}*/
	    	};

	    	manageMessageAjax(aOption, myCriteria);
	    },
	    language: getLanguage(),
	    drawCallback: function( settings ) {
	    	initDisplay1600();

	    	if(aOption.createdTableFunc != undefined){
				window[aOption.createdTableFunc](settings);
			}

	    	jQuery("div.CRITERIA input:first , div.CRITERIA select:first").not(".hasDatepicker").focus();

	    	setCssHover(aOption.tableId);
	    }
	});

	//Event on drew table
	jQuery("#"+aOption.tableId).on('draw.dt', function (oTable, oData){
		drawnDt(context, aOption, oTable, oData);
	} );

	if(aOption.footerHtml != undefined){
		//Create icon in footer
		jQuery("div.bottom").next().html(aOption.footerHtml);
	}
}

function clearMessageDataTable() {

	// console.log(jQuery("div.messFed table#tblMessage"));

	/** 20160909 แก้ไขการ clear style ของ message ทุกประเภท เนื่องจากแสดง message ค้างไว้ */
//	jQuery("div.messFed table#tblMessage").removeClass("ui-state-error ui-corner-all").addClass("FORM");
//	jQuery("div.messFed table#tblMessage tbody tr td.ERROR").remove();
//
//	jQuery("div.messFed table#tblMessage tbody tr").html("<td class='MESSAGE'>&nbsp;</td>");
	
	//checkboxToggle("chkall",false);
	
	jQuery("div.messFed table#tblMessage").removeAttr("class").addClass("FORM");
	jQuery("div.messFed table#tblMessage tbody tr td").remove();
	jQuery("div.messFed table#tblMessage tbody tr").html("<td class='MESSAGE'>&nbsp;</td>");
}

function drawnDt(context, aOption, oTable, oData){
	//Keep criteria key
	jQuery("[name='criteria.criteriaKey']").val(oData.json.criteria.criteriaKey);
	jQuery("[name='criteria.headerSortsSelect']").val(oData.json.criteria.headerSortsSelect);
	jQuery("[name='criteria.orderSortsSelect']").val(oData.json.criteria.orderSortsSelect);
	jQuery("[name='criteria.start']").val(oData.json.criteria.start);
	
	jQuery("[name='criteria.checkMaxExceed']").val(oData.json.criteria.checkMaxExceed);
	jQuery("[name='criteria.alertMaxExceed']").val(oData.json.criteria.alertMaxExceed);

	// default ค่า checkbox all ที่อยู่ตรงหัวคอลัมน์ของ dataTable
	checkboxToggle("chkall",false);
	
    var start = oData._iDisplayStart;
	for ( var i=0; i < oData.aiDisplay.length; i++ ){	//loop ตามจำนวนแถว
		//Counter order number
        jQuery('table#'+aOption.tableId+' tbody tr:eq('+i+') td:eq(0)').html(parseInt(start) + (i+1));
    }
}

//Manage message
function manageMessageAjax(aOption, myCriteria){

	var fact = "Y";
	if(myCriteria.messageAjax.messageType != null){
		if(myCriteria.messageAjax.messageType == "C"){
			if(confirm(myCriteria.messageAjax.message)){
				jQuery("[name='criteria.checkMaxExceed']").val(false);
				
				jQuery("[name='criteria.criteriaKey']").val("");
				jQuery("[name='criteria.alertMaxExceed']").val(true);
				
				searchAjax();
				fact = "Y";
			} else{
				fact = "N";
			}
		} else if(myCriteria.messageAjax.messageType == "W"){
			var msg = '<td class="MESSAGE WARING" style="width: 40%; text-align: right;"><span class="ui-icon ui-icon-info" style="float: right; margin-right: .3em;"></td>';
			msg += '<td class="MESSAGE WARING" style="width: 60%; text-align: left;">' +  myCriteria.messageAjax.message + '</td>';

			jQuery("#tblMessage").addClass('ui-state-highlight ui-corner-all');
			jQuery('.messFed #tblMessage > tbody > tr').html(msg);
			/* 20160909: ปิดการ fadein/out ของ message
			jQuery('.messFed').animate({opacity: 1} ,2500 ,"" ,function(){
				jQuery(this).animate({opacity: 0} ,900 ,"",function(){
					jQuery('.messFed #tblMessage > tbody > tr').html('<td class="MESSAGE">&nbsp;</td>');
				});
			});*/
			fact = "N";
		}
		 else if(myCriteria.messageAjax.messageType == "D"){
				var msg = '<td class="MESSAGE WARING" style="width: 40%; text-align: right;"><span class="ui-icon ui-icon-info" style="float: right; margin-right: .3em;"></td>';
				msg += '<td class="MESSAGE WARING" style="width: 60%; text-align: left;">' +  myCriteria.messageAjax.message + '</td>';

				jQuery("#tblMessage").addClass('ui-state-highlight ui-corner-all');
				jQuery('.messFed #tblMessage > tbody > tr').html(msg);
				/* 20160909: ปิดการ fadein/out ของ message
				jQuery('.messFed').animate({opacity: 1} ,2500 ,"" ,function(){
					jQuery(this).animate({opacity: 0} ,900 ,"",function(){
						jQuery('.messFed #tblMessage > tbody > tr').html('<td class="MESSAGE">&nbsp;</td>');
					});
				});*/
				fact = "Y";
		}else if(myCriteria.messageAjax.messageType == "S"){
			var msg = '<td class="MESSAGE SUCCESS" style="width: 40%; text-align: right;"><span class="ui-icon ui-icon-circle-check" style="float: right; margin-right: .3em;"></td>';
			msg += '<td class="MESSAGE SUCCESS" style="width: 60%; text-align: left;">' +  myCriteria.messageAjax.message + '</td>';

			jQuery("#tblMessage").addClass('ui-state-active ui-corner-all');
			jQuery('.messFed #tblMessage > tbody > tr').html(msg);
			/* 20160909: ปิดการ fadein/out ของ message
			jQuery('.messFed').animate({opacity: 1} ,2500 ,"" ,function(){
				jQuery(this).animate({opacity: 0} ,900 ,"",function(){
					jQuery('.messFed #tblMessage > tbody > tr').html('<td class="MESSAGE">&nbsp;</td>');
				});
			});*/
			
			fact = "Y";
			
		}else if(myCriteria.messageAjax.messageType == "A"){
			alert(myCriteria.messageAjax.message);
			fact = "N";
			
		} else {
			jQuery("#tblMessage").addClass('ui-state-error ui-corner-all');
			jQuery('.messFed .MESSAGE').addClass("ERROR").html(
					"<span class='ui-icon ui-icon-alert' style='margin-right: .6em;'></span>"
					+ myCriteria.messageAjax.message
					+ "&nbsp;&nbsp;<a class='LINK' href='javascript:void(0);' onclick='showErrorDetail(\"messageDetail\")'>detail</a>"
					+ "<div id='messageDetail' style='display: none;'>"+myCriteria.messageAjax.messageDetail+"</div>"
					);
			if(aOption.urlSearch.indexOf("/search") > -1){
				fact = "N";
			}
		}
	}
	if(fact === "N"){
		jQuery("#" + aOption.divResultId).hide();
	}
	return;
}

function getLanguage(){
	var oLanguage = {
			zeroRecords: validateMessage.CODE_30012,
	        info: "&nbsp;<b>"+validateMessage.CODE_numberOfSearch+" _TOTAL_ "+validateMessage.CODE_order+"<b/>",	//รายการที่ค้นพบ xxx รายการ
	        infoEmpty: "&nbsp;<b>"+validateMessage.CODE_numberOfSearch+" _TOTAL_ "+validateMessage.CODE_order+"<b/>",	//รายการที่ค้นพบ xxx รายการ
	        paginate: {
		        first: "‹‹",
		        last: "››",
		        next: "›",
		        previous: "‹"
		    }
	};
	return oLanguage;
}

function createCommonIcon(context, row, data, index, aOption){
	//มี checkbox?
	jQuery('.d_checkbox', row).html('<input type="checkbox" name="criteria.selectedIds" value="'+data.id+'">');	//Checkbox
	//มี Radio?
	jQuery('.d_radio', row).html('<input type="radio" name="criteria.selectedIds" value="'+data.id+'">');	//Radio
	
    //มี view?
    if(aOption.urlView != undefined){
    	var viewHtml;
    	if(aOption.urlView.authen === 'true'){
    		viewHtml = "<img onclick='submitAction(\""+aOption.urlView.url+"\", \""+aOption.pk+"\", \""+data.id+"\");' class='cursor' title='"+ validateMessage.CODE_VIEW + "' src='"+context+"/images/icon/i_view.png'>";	//icon view
    	} else{
    		viewHtml = "<img class='cursor' title='"+ validateMessage.CODE_VIEW + "' src='"+context+"/images/icon/i_view_dis.png'>";//icon disable view

    	}
    	jQuery('.d_view', row).html(viewHtml);
    }

    //มี edit?
    if(aOption.urlEdit != undefined){
    	var editHtml;
    	if(aOption.urlEdit.authen === 'true'){
    		editHtml = "<img onclick='submitAction(\""+aOption.urlEdit.url+"\", \""+aOption.pk+"\", \""+data.id+"\");' class='cursor' title='"+ validateMessage.CODE_EDIT + "' src='"+context+"/images/icon/i_edit.png'>";	 //icon edit
    	} else{
    		editHtml = "<img class='cursor' title='"+ validateMessage.CODE_EDIT + "' src='"+context+"/images/icon/i_edit_dis.png'>";	 //icon disable edit
    	}
    	jQuery('.d_edit', row).html(editHtml);
    }

    /* Set icon active or inactive */
	if(data.active.code == "Y"){
		jQuery('.d_status', row).html( '<img title="'+validateMessage.CODE_ACTIVE+'" src="'+context+'/images/icon/i_open.png">' );
	} else{
		jQuery('.d_status', row).html( '<img title="'+validateMessage.CODE_INACTIVE+'" src="'+context+'/images/icon/i_close.png">' );
	}

    // มี child row?
    if (aOption.childRow != undefined && aOption.childRow == "Y") {
    	// Add event listener for opening and closing details
    	jQuery('.details-control', row).click(function () {
        	var tr = $(this).closest('tr');
            var row = table.row( tr );

            if ( row.child.isShown() ) {
            	// This row is already open - close it
            	row.child.hide();
            	tr.removeClass('shown');
            	$("div.slider", row.child()).hide( "slow" );
            } else {
                // Open this row
                row.child( createDataTableRowChild(row), 'no-padding' ).show();
                tr.addClass('shown');
                $("div.slider", row.child()).show( "slow" );

                var ele = jQuery(row.child().children(".no-padding").children(".slider").children(".display"));
                tableId = jQuery(ele).attr("id");
                setCssHover(tableId)
            }
        });
    }
}

function setCssHover(tableId){
	jQuery('table#'+tableId+' tbody tr').each(function (i) {
		jQuery(this).mouseover(function() {
			jQuery(this).addClass("ui-state-highlight");
			jQuery(this).parent().parent().parent().parent().parent().children(".DTFC_LeftWrapper").children(".DTFC_LeftBodyWrapper").children(".DTFC_LeftBodyLiner").children(".DTFC_Cloned").find("tbody > tr:eq("+i+")").addClass("ui-state-highlight");
		});

		jQuery(this).mouseout(function() {
			jQuery(this).removeClass("ui-state-highlight");
			jQuery(this).parent().parent().parent().parent().parent().children(".DTFC_LeftWrapper").children(".DTFC_LeftBodyWrapper").children(".DTFC_LeftBodyLiner").children(".DTFC_Cloned").find("tbody > tr:eq("+i+")").removeClass("ui-state-highlight");
		});
	});

	jQuery('.DTFC_Cloned tbody tr').each(function(nRow){
		jQuery(this).mouseover(function() {
			jQuery(this).addClass("ui-state-highlight");
			jQuery(this).parent().parent().parent().parent().parent().parent().children(".dataTables_scroll").children(".dataTables_scrollBody").children(".display").find("tbody > tr:eq("+nRow+")").addClass("ui-state-highlight");
		});

		jQuery(this).mouseout(function() {
			jQuery(this).removeClass("ui-state-highlight");
			jQuery(this).parent().parent().parent().parent().parent().parent().children(".dataTables_scroll").children(".dataTables_scrollBody").children(".display").find("tbody > tr:eq("+nRow+")").removeClass("ui-state-highlight");
		});
    });

}
