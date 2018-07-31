/**
 * Auto generate table
 * @param id
 * @param table
 * @param isFoot
 */

var tableY= true;
function genTableForce(id,table,isFoot){
	jQuery("div [id ^= 'divResult']").html("");

	jQuery('#'+id).html("");//clear ค่า

	var divResultHeight = jQuery("#"+id).height();
	var styleBoxDetail ="";
	var styleResultContent = "width:"+(table.width != null?table.width:"")+"px;" ;
	var styleResultBoxHeader = "";
	var styleResultHeader ="width:"+(table.width != null?table.width:"")+"px;" ;
	var $footerTable = jQuery('table#footerTable_'+id);
	var $headerTable = jQuery('table#headerTable_'+id);

	//กรณี ไม่มี scoll ไม่ต้องกำหนดความสูให้ div Result
	if(table.overY==false ){
		jQuery("#"+id).css("height","auto");
		styleResultContent="";
		styleResultHeader="";
	}

	if(table.overX||table.overY ){
		//กำหนดความสูง  divDetail ในกรณีมี footer กับ ไม่มี
		if($footerTable.length!=0 && isFoot==true){
			styleBoxDetail+="height:"+(divResultHeight-136)+"px;";
		}else{
			styleBoxDetail+="height:"+(divResultHeight-143+43-(table.overX?9:0))+"px;";
		}

		//กรณีมีแค่ scoll ล่างอย่างดียว
		if(table.overX==true && table.overY==false){
		//	styleBoxDetail="";
		//	styleBoxDetail+="overflow-y: hidden;overflow-x: scroll;";
			styleResultBoxHeader ="overflow: hidden;";
			styleResultContent ="width:"+(table.width != null?table.width:"")+"px;";
			styleResultHeader ="width:"+(table.width != null?table.width:"")+"px;" ;
		} else if(table.overX==true && table.overY==true){//กรณีมี scoll ทั้งคู่
		//	styleBoxDetail+="overflow:scroll;";
			styleResultBoxHeader ="overflow: hidden;";
		}else if(table.overX==false && table.overY==true){//กรณีมีแค่ scoll ข้าง อย่างดียว
		//	styleBoxDetail+="overflow-x: hidden;overflow-y: scroll;";
			styleResultContent ="";
			styleResultHeader="overflow: hidden;";
		}
	}

	var html = "";
	//ถ้าไม่มี header ไมีต้องใส่ <div class="RESULT_BOX BORDER_COLOR">
	if($headerTable.length > 0 && $footerTable.length > 0){
		html += '<div class="RESULT_BOX BORDER_COLOR ui-widget ui-widget-content" style="background: none;">';
	}

	//div header table
	if($headerTable.length > 0){
		html += '<div class="RESULT_BOX_TITAL ui-widget-header" style="border: none;">'
		+'<table class="TOTAL_RECORD">'
		+$headerTable.html()
		+'</table>'
		+'</div>';
	}


	var htmlOverX = "";
	if(table.overX == true){   //สร้าง หัว force
		var htmlH = jQuery('table#model_'+id+' tr.th');
		jQuery('#'+id).html("<table id='hiddenHeadTable"+id+"' style='display: none;'>"+htmlH.html()+"</table>");//วาดตาราง

		var htmlHC = jQuery("#hiddenHeadTable"+id);
		var checkBokLength = jQuery(jQuery(htmlHC.children().children()).get(0)).children("th.checkbox").length;
		var n = 0;

	    jQuery(jQuery(htmlHC.children().children()).get(0)).children().each(function() {
		    	if((checkBokLength+1)<=jQuery(this).index()){
					jQuery(this).remove();
				}else{
					n++;
				}
		 });

		jQuery(htmlHC.children().find("td")).css("border-left-style","none");

		htmlOverX += '<table id="HEADER_FORCE_TABLE'+id+'" class="RESULT_HEADER BACKGROUND_COLOR" style="width: auto; float: left;">';
		htmlOverX +=  htmlHC.html();
		htmlOverX += '</table>';

	    for(var x=0;x<n;x++){
			jQuery('table#model_'+id+' tr.th').children("th").eq(0).remove();
		}



		//div column head table
		html += '<div id="boxHeader'+id+'" class="RESULT_BOX_HEADER BACKGROUND_COLOR" style="float:left;margin:auto;" >';
		html += htmlOverX;
		html += '<div id="HEADER_SCROLL_DIV'+id+'" style="overflow-x: hidden; overflow-y: hidden;float:left;">';
		html += '<table id="HEADER_SCROLL_TABLE" class="RESULT_HEADER BACKGROUND_COLOR" style="'+styleResultContent+'; float: left;">';
		html += jQuery('table#model_'+id+' tr.th').html()
				+'</table>'
				+'</div>';
		if(table.overY==true){
			html += '<div id="divHidden'+id+'" style="float:right;width: 15px;height:29px;" class="ui-state-default"></div>';
		}
		html +='</div>';


	}else{
		html += '<div id="boxHeader'+id+'" class="RESULT_BOX_HEADER" style="'+styleResultBoxHeader+'" >'
		+'<div class="RESULT_BOX_HEADER BACKGROUND_COLOR" style="position: absolute; margin-left: 634px; height:42px; width: 20px; color: red; z-index: 2;"></div>';
		html += '<table class="RESULT_HEADER BACKGROUND_COLOR HIDE_HEADER" style="'+styleResultHeader+'" >';
		html += jQuery('table#model_'+id+' tr.th').html()
		+'</table>'
		+'</div>';
	}



    var scrollY = "";
    var height  = "";

	if(table.overY == true){
		scrollY = "scroll";
		height  = styleBoxDetail ;
	}else{
		scrollY = "hidden";
		height  = "";
	}
	$headerTable.remove();
	jQuery('table#model_'+id+' tr.th').remove();

	//กรณ๊ไม่่มี scoll ล่างและข้าง  จะไม่มี divBoxDetail ครอบ table ไว้
	if(table.overX || table.overY){
		html += '<div id="divBoxDetail'+id+'" class="RESULT_BOX_DETAIL"  style="'+height+'"overflow-x: hidden; overflow-y:'+scrollY+';" >';
	}

	if(table.overX != true){
		html += '<table  class="RESULT_CONTENT HIDE_CONTENT BALLOON"  id="tableId_'+id+'"  style="'+styleResultContent+'" >';
		html += jQuery("table#model_"+id+" tr.td:first").parent().html() ? jQuery("table#model_"+id+" tr.td:first").parent().html() : "";
		html += '</table>';
	}else{

		var htmlC = jQuery("table#model_"+id+" tr.td:first").parent();

		jQuery('#'+id).append("<table id='hiddenTable"+id+"' style='display: none;'>"+htmlC.html()+"</table>");//วาดตาราง
		var htmlH = jQuery("#hiddenTable"+id);
		var checkBokLength = jQuery(jQuery(htmlH.children().children()).get(0)).children("td.checkbox").length;


        var  j =0;
		jQuery(jQuery(htmlH.children().children())).each(function() {
			jQuery(jQuery(htmlH.children().children()).get(j)).children().each(function() {
				if((checkBokLength+1)<=jQuery(this).index()){
					jQuery(this).remove();
				}
		    });
		 j++;
		});

	    jQuery(htmlH.children().children()).removeAttr("id");
	    jQuery(htmlH.children()).attr("id","RESULT_FORCE_TBODY"+id);
	    jQuery(htmlH.children().find("td")).css("border-left-style","none");
	    html += '<table id="tableId_'+id+'" class="RESULT_CONTENT HIDE_CONTENT BALLOON" style="width: auto; float: left;">';
		html += htmlH.html();
		html += '</table>';

		htmlC.children().children("td.checkbox").remove();
		htmlC.children().children("td.order").remove();

		htmlC.children().children("td").css("border-left-style","none");

		html += '<div id="RESULT_SCROLL_DIV'+id+'" style="overflow-x: hidden; overflow-y: hidden;">';
		html += '<table id="RESULT_SCROLL_TABLE'+id+'" class="RESULT_CONTENT HIDE_CONTENT BALLOON" style="'+styleResultContent+' float: left; ">';
		html += '<tbody id="RESULT_SCROLL_TBODY'+id+'"  >';
		html +=  htmlC.html();
		html += '</tbody>';
		html += '</table>';
		html += '</div>';
		tableY = false;
	}

	//กรณ๊ไม่่มี scoll ล่างและข้าง  จะไม่มี divBoxDetail ครอบ table ไว้
	if(table.overX || table.overY){
		html += '</div>';
	}

	if(table.overX == true){
		html += '<div id="f_scroll'+id+'" style="overflow-x: scroll; overflow-y: hidden;">';
		html += '<div id="f_detail'+id+'" style="height: 1px; width: '+styleResultContent+'">&nbsp;</div></div>';
	}


	if(isFoot){
		if($footerTable.length !=0){
			html += '<div class="RESULT_BOX_TITAL BACKGROUND_COLOR ui-widget-header" style="border: none;">';
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

    jQuery('#'+id).append(html);//วาดตาราง

    jQuery("#divScroll"+id).height(jQuery("#boxHeader"+id).height()-2);
	jQuery("table#model_"+id+":hidden").remove(); //ลบตาราง model
	jQuery("#hiddenHeadTable").remove();
	jQuery("#hiddenTable").remove();


	jQuery('#f_scroll'+id).scroll(function(event) {
		document.getElementById('RESULT_SCROLL_DIV'+id).scrollLeft = document.getElementById('f_scroll'+id).scrollLeft;
		document.getElementById('HEADER_SCROLL_DIV'+id).scrollLeft = document.getElementById('f_scroll'+id).scrollLeft;
	});

	//console.info(id);
  	manageResultTable(id);
}


function manageResultTable(id) {
	jQuery('#f_detail'+id).width(jQuery('#tableId_'+id).width() + jQuery('#RESULT_SCROLL_TABLE'+id).width() + 50);
	if (jQuery('#HEADER_FORCE_TABLE'+id).height() > jQuery('#HEADER_SCROLL_TABLE'+id).height()) {
		jQuery('#HEADER_FORCE_TABLE'+id).height(jQuery('#HEADER_FORCE_TABLE'+id).height());
		jQuery('#HEADER_SCROLL_TABLE'+id).height(jQuery('#HEADER_FORCE_TABLE'+id).height());

	} else if (jQuery('#HEADER_FORCE_TABLE'+id).height() <= jQuery('#HEADER_SCROLL_TABLE'+id).height()) {
		jQuery('#HEADER_SCROLL_TABLE'+id).height(jQuery('#HEADER_SCROLL_TABLE'+id).height());
		jQuery('#HEADER_FORCE_TABLE'+id).height(jQuery('#HEADER_SCROLL_TABLE'+id).height());
	}

	// กำหนดความกว้างของส่วน header ระหว่าง force table กับ scroll table ตาม browser
	if (checkBrowser() == 'IE') {
		jQuery('#HEADER_SCROLL_TABLE'+id).width('+=17');
		jQuery('#HEADER_SCROLL_DIV'+id).width((jQuery('#divBoxDetail'+id).width() - jQuery('#tableId_'+id).width() - 2));
		jQuery('#RESULT_SCROLL_DIV'+id).width((jQuery('#divBoxDetail'+id).width() - jQuery('#tableId_'+id).width() - 19));

	} else {

		jQuery('#divHidden'+id).height(jQuery('#HEADER_SCROLL_DIV'+id).height()-2);
	    jQuery('#HEADER_SCROLL_DIV'+id).width((jQuery('#divBoxDetail'+id).width() - jQuery('#tableId_'+id).width() - 18));
	    jQuery('#RESULT_SCROLL_DIV'+id).width((jQuery('#divBoxDetail'+id).width() - jQuery('#tableId_'+id).width()  -18 ));
	}

	// กำหนดความสูงของส่วน result ระหว่าง force table กับ scroll table
	if (jQuery('#tableId_'+id).height() > jQuery('#RESULT_SCROLL_TABLE'+id).height()) {
		jQuery('#tableId_'+id).height(jQuery('#tableId_'+id).height());
		jQuery('#RESULT_SCROLL_TABLE'+id).height(jQuery('#tableId_'+id).height());

	} else if (jQuery('#tableId_'+id).height() <= jQuery('#RESULT_SCROLL_TABLE'+id).height()) {
		jQuery('#RESULT_SCROLL_TABLE'+id).height(jQuery('#RESULT_SCROLL_TABLE'+id).height());
		jQuery('#tableId_'+id).height(jQuery('#RESULT_SCROLL_TABLE').height());
	}

	// กำหนดความสูงของส่วน result ระหว่าง force tr กับ scroll tr
	jQuery('#tableId_'+id+' tr').each(function( index ) {
		if (jQuery( this ).height() < jQuery('#RESULT_SCROLL_TABLE'+id+' tr').eq(index).height()) {
			jQuery('#RESULT_SCROLL_TABLE'+id+' tr').eq(index).height(jQuery( this ).height());
			jQuery( this ).height(jQuery('#RESULT_SCROLL_TABLE'+id+' tr').eq(index).height());

		} else if (jQuery( this ).height() >= jQuery('#RESULT_SCROLL_TABLE'+id+' tr').eq(index).height()) {
			jQuery( this ).height(jQuery('#RESULT_SCROLL_TABLE'+id+' tr').eq(index).height());
			jQuery('#RESULT_SCROLL_TABLE'+id+' tr').eq(index).height(jQuery( this ).height());

		}
	});
}


/*jQuery( window ).resize(function() {
	manageResultTable();
});*/