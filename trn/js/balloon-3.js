var timeoutID;

// ver.3 โดยการที่ user ทำการกำหนดให้แสดงค่าในแต่ละ column ได้
function balloonVer3(){
	//----------- CONFIGURATION BALLOON HERE ------------//
	var option = {
			delay:1000,
			fadeIn:1000,
			fadeOut:300
	};
	//----------- CONFIGURATION BALLOON HERE ------------//

	jQuery("body").append("<div id='balloonDiv' class='ui-tooltipx ui-widget ui-corner-all ui-widget-content' style='display: none;'></div>");

	if(jQuery(".balloonData").length > 0){
		
		jQuery("tr[class*='LINE_'] td:not(.checkbox, .order, .view)").mouseenter(function(e){//เนื่องจากมีclass อื่นอยู่ด้วยจึงใช้ *
			var varThis = this;
			variableX= e.pageX;
			variableY= e.pageY;

			timeoutID  = setTimeout(function(){
								getDataForBalloonVer3(option, e.pageX, e.pageY, varThis);
							},
							option.delay);
		});

		//ซ่อน balloon เมื่อนำเม้าส์ออก
		jQuery("tr[class*='LINE_']").mouseleave(function(e) {
			var currentPointer = String(jQuery(e.relatedTarget).attr("id"));//หาว่าตอนที่นำเม้าส์ไปชี้ ได้ชี้ element ที่มี id เป็น  "balloonDiv" หรือไม่ ซึ่งคือ ตัวบอลลูน
			if(currentPointer.indexOf("balloonDiv") != -1){//ถ้าเป็น -1 แสดงว่าไม่ได้ชี้ที่ div balloon
	
			} else {
				window.clearTimeout(timeoutID);
				jQuery("#balloonDiv").fadeOut(option.fadeOut);
			}
		});

		//กรณีที่ลาดเม้าส์ออกด้านข้าง
		jQuery("#balloonDiv").mouseleave(function(){
			jQuery("#balloonDiv").fadeOut(option.fadeOut);
		});
	}
}


function getDataForBalloonVer3(option, variableX, variableY, varThis){
	var strContent = "<table style='font-size:12px;padding: 4px;'>" ;
	var header;
	var detail;
	jQuery(varThis).parent().children(".checkbox").children(".balloonData").each(function(i){//ในบรรทัดนี้  varThis จะเป็น td แต่จะต้องดึงเอาคลาส balloonData ซึ่งได้ทำการ .children ไว้แล้ว จึงจำเป็นต้อง ใส่ .parent()อีกครึ่งเพื่อที่จะได้อ้างไปถึง tr แล้วเข้ามาหา td ตามสเต็ป
		header   = jQuery(this).attr("name");
		detail   = jQuery(this).val();

		if (detail != "") {
			strContent +=  "<tr><td><b>"+ header +"</td><td>: "+ detail +"</td></tr>" ; //แสดงชื่อหัวตาราง และข้อมูลในตาราง
		} else {
			strContent +=  "<tr><td><b>"+ header +"</b></td><td>:  -</td></tr>" ; //แสดงชื่อหัวตาราง และข้อมูลในตาราง
		}
	});
	strContent += "</table>" ;
	drawingBalloonVer3(strContent, variableX, variableY, option);//วาด tooltip
}

//นำข้อมูลที่ได้ มาวาด balloon   หมายเหตุ::: เหตุที่มี function นี้ เพิ่มขึ้นมาเนื่องจาก  ตำแหน่งในการแสดง ไม่ตรงกับเม้าส์ที่ชี้   จึงได้แยกออกมาเพื่อ set ค่าให้กับตำแหน่งของ e.pagex,y ใหม่
function drawingBalloonVer3(strTag, variableX, variableY, option){
	if (option == undefined) {
		var option = {
				delay:1000,
				fadeIn:1000,
				fadeOut:300
		};
	}
	
	if(option.css == undefined  &&  option.className == undefined){
		//ไม่ได้ระบุ css และ  class เข้ามา
		jQuery("#balloonDiv").html(strTag).css({position:"absolute",left:variableX/*+350*/, top:variableY}).fadeIn(option.fadeIn);
	
	} else if (option.css != undefined  &&  option.className == undefined){
		//มีการระบุ css เข้ามาจะต้องเซ็ตใส่  .css();    <<  หากต้องการจะให้เข้าเคสสุดท้าย แต่กับเข้าเคสนี้ก่อน  ถ้าไม่กำหนด option.className == undefined >>
		jQuery("#balloonDiv").html(strTag).css(option.css).css({position:"absolute",left:variableX, top:variableY}).fadeIn(option.fadeIn);//ที่ใส่ .css() สองครั้งเนื่องจาก .css() ครั้งที่สองจะต้อง set ตำแหน่งเริ่มต้นให้กับ tooltip ด้วย

	} else if (option.css == undefined && option.className != undefined){
		//มีการระบุ class เข้ามาจะต้องเซ็ตใส่  .addClass();
		jQuery("#balloonDiv").html(strTag).addClass(option.className).css({position:"absolute",left:variableX, top:variableY}).fadeIn(option.fadeIn);

	} else if(option.className != undefined && option.className != undefined){
		//มีการระบุ class และ css เข้ามาจะต้องเซ็ตใส่  .addClass() และ .css();
		jQuery("#balloonDiv").html(strTag).addClass(option.className).css(option.css).css({position:"absolute",left:variableX, top:variableY}).fadeIn(option.fadeIn);
	}
}