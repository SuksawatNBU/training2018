
var timeoutID;
function createBalloon(option){
	
	jQuery("body").append("<div id='balloonDiv' class='bubble ui-tooltipx ui-widget ui-corner-all ui-widget-content' style='font-size:12px;display: none;padding: 6px;'></div>");

	jQuery("table.BALLOON  tr[class*='LINE_']").mouseenter(function(e){
		//เนื่องจากมีclass อื่นอยู่ด้วยจึงใช้ *
		var myThis = this;
		timeoutID  = setTimeout(
						function(){
							getDataForBalloon(option, e, myThis);
						},
						option.delay
					);
	});



	//ซ่อน balloon เมื่อนำเม้าส์ออก
	jQuery("table.BALLOON  tr[class*='LINE_']").mouseleave(function(e){
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









//เมื่อนำเม้าส์แตะ  จะโหลดข้อมูลแถวนั้นๆ  เก็บลงใน array
function getDataForBalloon(option,e,varThis){
	
	//ดึงค่าหัวcolumnทั้งหมดมาเก็บไว้ใน array
	var ค่าหัวcolumn = new Array();
	jQuery(".showTooltipH").each(function(i){
		ค่าหัวcolumn[i] = jQuery(this).text();
	});

	var data_in_row = new Array();
	var strContent = "";

	strContent+="<table>";
		
	jQuery(varThis).find(".showTooltip").each(function(e){//ดึงข้อมูลในแต่ละ column ที่มีคลาส showTooltip อยู่

		var current_data = jQuery(this).text();

		data_in_row[e]= current_data  ;
		//console.info(e+" :"+data_in_row);

		strContent+="<tr>";
		strContent+="<td>" ;
		strContent+= ค่าหัวcolumn[e];
		strContent+="</td>";

		strContent+="<td>" ;
		if(data_in_row[e] != ""){ //ตรวจสอบกรณีที่เป็นค่าว่าง คือช่องใดที่เป็นค่าว่างปล่าวให้ใส่ - บน ball loon
			strContent+= ": "+ data_in_row[e];
		} else {
			strContent+= ":  -";
		}
		strContent+="</td>";
		strContent+="</tr>";
	});

	strContent +="</table>";
	drawingBalloon(strContent, e, option);
}






//นำข้อมูลที่ได้ มาวาด balloon
function drawingBalloon(strTag, e, option){
	if (option == undefined) {
		option = {
				delay:1000,
				fadeIn:1000,
				fadeOut:300
		};
	}

	if (option.css == undefined  &&  option.className == undefined){
		//ไม่ได้ระบุ css และ  class เข้ามา
		jQuery("#balloonDiv").html(strTag).css({position:"absolute",left:e.pageX, top:e.pageY}).fadeIn(option.fadeIn);

	} else if (option.css != undefined  &&  option.className == undefined){
		//มีการระบุ css เข้ามาจะต้องเซ็ตใส่  .css();    <<  หากต้องการจะให้เข้าเคสสุดท้าย แต่กับเข้าเคสนี้ก่อน  ถ้าไม่กำหนด option.className == undefined >>
		jQuery("#balloonDiv").html(strTag).css(option.css).css({position:"absolute",left:e.pageX, top:e.pageY}).fadeIn(option.fadeIn);//ที่ใส่ .css() สองครั้งเนื่องจาก .css() ครั้งที่สองจะต้อง set ตำแหน่งเริ่มต้นให้กับ tooltip ด้วย
		
	} else if (option.css == undefined && option.className != undefined){
		//มีการระบุ class เข้ามาจะต้องเซ็ตใส่  .addClass();
		jQuery("#balloonDiv").html(strTag).addClass(option.className).css({position:"absolute",left:e.pageX, top:e.pageY}).fadeIn(option.fadeIn);

	} else if (option.css != undefined  &&  option.className != undefined){
		//มีการระบุ class และ css เข้ามาจะต้องเซ็ตใส่  .addClass() และ .css();
		jQuery("#balloonDiv").html(strTag).addClass(option.className).css(option.css).css({position:"absolute",left:e.pageX, top:e.pageY}).fadeIn(option.fadeIn);
	}
}
/*========================  BALLOON VERSION 2 AREA  ========================*/



// ver.2 โดยการที่ user ทำการกำหนดให้แสดงค่าในแต่ละ column ได้
function balloonVer2(){
	//----------- CONFIGURATION BALLOON HERE ------------//
	var option = {
			delay:1000,
			fadeIn:1000,
			fadeOut:300
	};
	//----------- CONFIGURATION BALLOON HERE ------------//

	jQuery("body").append("<div id='balloonDiv' class='ui-tooltipx ui-widget ui-corner-all ui-widget-content' style='display: none;'></div>");

	if(jQuery(".balloonData").length >0){
						var variableX;//ส่งค่าพิกัดแกน x
						var variableY;//ส่งค่าพิกัดแกนy
						var intoTHflag = false;//ตรวจสอบว่ามีการเข้าที่ th หรือไม่
					jQuery("tr[class*='LINE_']  td:not(.checkbox, .order, .view)").mouseenter(function(e){//เนื่องจากมีclass อื่นอยู่ด้วยจึงใช้ *
							var varThis = this;
							variableX= e.pageX;
							variableY= e.pageY;

							//รับรอง แก้ไข bug  div balloon ไปอยู่ด้านบน
							if(intoTHflag == false){//ไม่ได้นำเม้าส์ไปแตะ หัวตาราง
								variableX= e.pageX;
								variableY= e.pageY-340;
							}else{//เมื่อนำเม้าส์ไปแตะ หัวตาราง
								variableX= e.pageX;
								variableY= e.pageY;
							}
							timeoutID  = setTimeout(function(){getDataForBalloonVer2(option,variableX,variableY,varThis);},option.delay);//ดึงข้อมูล balloon ver.2
					});


					//เข้าเพื่อ รับรอง แก้ไข bug  div balloon ไปอยู่ด้านบน
					jQuery("#boxHeaderdivResult2").mouseenter(function(){
							intoTHflag=true;
					});


					//ซ่อน balloon เมื่อนำเม้าส์ออก
					jQuery("tr[class*='LINE_']").mouseleave(function(e){
							var currentPointer = String(jQuery(e.relatedTarget).attr("id"));//หาว่าตอนที่นำเม้าส์ไปชี้ ได้ชี้ element ที่มี id เป็น  "balloonDiv" หรือไม่ ซึ่งคือ ตัวบอลลูน
							if(currentPointer.indexOf("balloonDiv") != -1){//ถ้าเป็น -1 แสดงว่าไม่ได้ชี้ที่ div balloon
							}else{
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


function getDataForBalloonVer2(option,variableX,variableY,varThis){
	var strContent = "<table style='font-size:12px;padding: 4px;'>" ;
	var header;
	var detail;
	jQuery(varThis).parent().children(".checkbox").children(".balloonData").each(function(i){//ในบรรทัดนี้  varThis จะเป็น td แต่จะต้องดึงเอาคลาส balloonData ซึ่งได้ทำการ .children ไว้แล้ว จึงจำเป็นต้อง ใส่ .parent()อีกครึ่งเพื่อที่จะได้อ้างไปถึง tr แล้วเข้ามาหา td ตามสเต็ป
			header   = jQuery(this).attr("name");
			detail   = jQuery(this).val();

			if(detail!=""){
				strContent +=  "<tr><td><b>"+ header +"</td><td>: "+ detail +"</td></tr>" ; //แสดงชื่อหัวตาราง และข้อมูลในตาราง
			}else{
				strContent +=  "<tr><td><b>"+ header +"</b></td><td>:  -</td></tr>" ; //แสดงชื่อหัวตาราง และข้อมูลในตาราง
			}
		});
		strContent += "</table>" ;
		drawingBalloonVer2(strContent,variableX,variableY,option);//วาด tooltip
}






//นำข้อมูลที่ได้ มาวาด balloon   หมายเหตุ::: เหตุที่มี function นี้ เพิ่มขึ้นมาเนื่องจาก  ตำแหน่งในการแสดง ไม่ตรงกับเม้าส์ที่ชี้   จึงได้แยกออกมาเพื่อ set ค่าให้กับตำแหน่งของ e.pagex,y ใหม่
function drawingBalloonVer2(strTag,variableX,variableY,option){
	//console.info("delay: "+option.delay);
	//console.info("duration: "+option.fadeIn);
	//console.info("durationOut: "+option.fadeOut);
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
/*========================  BALLOON VERSION 2 AREA  ========================*/

//author: thanongsak.b