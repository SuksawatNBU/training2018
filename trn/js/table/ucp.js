

	// Set hitStyle  table scoll ทั่วไป
	// tableId = "tableDetail_divResult";  : id table  (table ทั้วไป
	// tableId = ["tableDetailFreeze_divResult","tableDetailScroll_divResult"]; table freeze
	// inputName = ["blacklist","stoplist"]; ชื่อ input ที่ต้องการ จับค่า มาตรวจสอบ
	// valueHit  : ค่าที่ต้องการ เอามา compare กับค่าที่อยู่ใน table  : "Y"
	function setHitStyleForEach(tableId ,inputName ,valueHit){
		
		jQuery("#"+tableId[0]+" > tbody > tr").each(function(index) {
			
			var chekStyleHit = "";
			for(var i = 0; i < inputName.length ; i++){
				
				var chekHit = jQuery(this).find("input[name='"+inputName[i]+"']").val();
				
				if(chekHit == valueHit.toString()){
					chekStyleHit = "Y";
					break;
				}else{
					chekStyleHit = "N";
				}
			}
			
		});
		
	}
	
	
	/* Set Hit Style
	 * param1 : trClass : ชื่อ  class ของ <tr> ใน   table    ex: trClass = ["tableDetailFreeze_divResult_tr","tableDetailScroll_divResult_tr"] 
	 * รองรับ กรณี  เป็น tableFreeze
	 * param2 : index : ของ row 
	 *  
	 */
	//function setHitStyle(tableId ,index){
	function setHitStyle(trClass ,index){
		for(var i = 0; i < trClass.length ; i++){
			jQuery('tr.'+trClass[i]+':eq(' + index + ')').addClass("hit-style");
			jQuery('tr.'+trClass[i]+':eq(' + index + ')').removeClass("LINE_EVEN");
			jQuery('tr.'+trClass[i]+':eq(' + index + ')').removeClass("LINE_ODD");
		}
	}
	
	
	/* addImageIcon
	 * className : ชื่อ class ของ <td> ที่ต้องการ ใส่  icon    ex : "test1"  (ส่งได้ทีละ 1)
	 * rowIndex : (index) การวน  loop tabl
 	 * icon : ชื่อ icon ที่ต้องการใส่   ได้มากกว่า 1 เป็น array    ex : icon = ["i_stop_list.png","i_black_list.png"] 
 	 * tooltip : tooltip ที่ต้อง show เมื่อใช้เมาส์ชี้ที่ icon  ให้ arry เท่ากับ icon  ex : tooltip = ["black_list" ,"stop_list"];
 	 * func : function ที่ต้องการผูกไว้กับ  icon  ex :  func = "dd(this);"  
 	 * หมายเหคุ  1. Array ของ icon tooltip และ  func ต้องมี ขนาดเท่าากัน  (กรณี ที่ส่งเข้ามา)  ค่าใน Array ต้องจับคู่ให้ตรงกัน
 	 * 
 	*/
	function addImageIcon(className , rowIndex , icon , tooltip ,func){
		
		//console.info("className :"+className +" rowIndex :" + rowIndex +" icon :"+ icon +" tooltip :" +tooltip  +" func :"+func);
		
		var html ="";
		var funcSet = "";
		for(var i = 0; i < icon.length ; i++){
			var url = 	urlContext+icon[i];
			
			var htmlTmp ="";
			var funcSet = "";
			var tooltipSet = "";
			
			// Add tooltip
			if(tooltip != undefined && tooltip.length != 0 && tooltip[i] != undefined){
				tooltipSet = " title = '"+tooltip[i]+"' "; 
			}
			
			//Add Function
			if (func != undefined && func.length != 0 && func[i] != undefined){
				funcSet = "  onclick = '"+func[i]+"' class ='cursor' "; 
			}
			
			htmlTmp =  "<img src='"+url+"'" + tooltipSet + funcSet +" />";
			
			html += htmlTmp;
			//console.info("addImageIcon :"+html);
		}
		
		
		//console.info(jQuery("."+replaceDot(className)).eq(rowIndex));
		
		jQuery("."+replaceDot(className)).eq(rowIndex).html(html);
		jQuery('[title]').tooltip();
		//jQuery("."+replaceDot(className[i])).tooltip();
	}
	
	
	/* Add Tooltip ให้กับ column
	 * param1 :  trClass : ชื่อ  class ของ <tr> ใน   table    ex: trClass = ["tableDetailFreeze_divResult_tr","tableDetailScroll_divResult_tr"]
	 * param2 : className ชื่อ class ของ <td> ที่ต้องการ ใส่ tooltip  ex : culumnName =["test3","test4"]; 
	 * param3 : rowIndex  (index) การวน  loop table
	 * param4 : tooltip ที่ต้องการให้ โชว์  arry ต้องมีขนาดเท่ากับจำนวน columnName   ex : tooltip = ["test1","test2"];
	 * ex : <span title='test44444'> test44444 </span>
	 * 
	 * หมายเหตุ  : Array ของ   className และ tooltip ต้องมีขนาดเท่ากัน  และ ค่าใน Array ต้องจับคู่ให้ตรงกัน
	 * */
	function addTooltip(trClass ,className , index , tooltip){
		
		var html ="";
		//console.info(className.length);
		
		// ตรวจสอบ param 
		if(tooltip != undefined && tooltip.length > 0){
		
			for(var i = 0; i < className.length ; i++){
					// loop trClass ที่ส่งเข้ามาเพื่อเก็บ value เดิมไว้ก่อน
					for(var j = 0; j < trClass.length ; j++){
						var obj = 'tr.'+trClass[j]+':eq(' + index + ') td.'+replaceDot(className[i]);
						
						// เช็คไว้ ถ้าเจอค่า ใน  tr class แรกแล้ว ก็ไม่ต้องหา ใน   tr class  ถัดไป
						var value = "";
						if(jQuery(obj).text() !=""){
							value = jQuery(obj).text();
							break;
						}
						//console.info(value);
					}
					
				//ตรวจสอบค่า ถ้า ส่งค่าเข้ามาไม่คบ ไม่ต้อง set title ตัวต่อไป
				var tooltipSet ="";
				if(tooltip[i] != undefined){
					html = "<span title='"+tooltip[i]+"'> "+value+" </span>";
					jQuery("."+replaceDot(className[i])).eq(index).html(html);
					jQuery("."+replaceDot(className[i])).tooltip();
				}
				
	//			jQuery('[title]').tooltip();
				
			}
		}
    		
	}
	
	
	function addFunctionJS(trClass ,className , index , tooltip){
		
		var html ="";
		//console.info(className.length);
		
		// ตรวจสอบ param 
		if(tooltip != undefined && tooltip.length > 0){
		
			for(var i = 0; i < className.length ; i++){
					// loop trClass ที่ส่งเข้ามาเพื่อเก็บ value เดิมไว้ก่อน
					for(var j = 0; j < trClass.length ; j++){
						var obj = 'tr.'+trClass[j]+':eq(' + index + ') td.'+replaceDot(className[i]);
						
						// เช็คไว้ ถ้าเจอค่า ใน  tr class แรกแล้ว ก็ไม่ต้องหา ใน   tr class  ถัดไป
						var value = "";
						if(jQuery(obj).text() !=""){
							value = jQuery(obj).text();
							break;
						}
						//console.info(value);
					}
					
				//ตรวจสอบค่า ถ้า ส่งค่าเข้ามาไม่คบ ไม่ต้อง set title ตัวต่อไป
				var tooltipSet ="";
				if(tooltip[i] != undefined){
					html = "<span title='"+tooltip[i]+"'> "+value+" </span>";
					jQuery("."+replaceDot(className[i])).eq(index).html(html);
					jQuery("."+replaceDot(className[i])).tooltip();
				}
				
	//			jQuery('[title]').tooltip();
				
			}
		}
    		
	}
	
	
	/**
	 * สำหรับแปลง  ชื่อ class มี . ติดมาด้วย   เนื่องจาก jQuery ไม่สามารถใช้ได้
	 * @param value  ex : active.code
	 * @returns valueReturn   active\\.code 
	 */
	function replaceDot(value){
		
		var  valueReturn ="" ;
		
		if(value.indexOf(".") != -1){
			valueReturn = value.replace(".", "\\.");
		}else{
			valueReturn = value;
		}
		
		return valueReturn;
	}
	
	/*var tToolTip ="";
	for(var j = 0; j < tooltip.length ; j++){
		tToolTip = "title = '"+tooltip[i]+"'";
	}*/
	
	
	/* Add Tooltip ให้กับ column
	 * param1 : $(this) object (tr) ทั้งหมด
	 * param2 : culumnName ชื่อ class ของ <td> ที่ต้องการ ใส่ tooltip  ex : culumnName =["test3","test4"]; 
	 * param3 : rowIndex  (index) การวน  loop table
	 * param4 : tooltip ที่ต้องการให้ โชว์  arry ต้องมีขนาดเท่ากับจำนวน columnName   ex : tooltip = ["test1","test2"];
	 * ex : <span title='test44444'> test44444 </span>
	 * */
	/*function addTooltip(obj ,culumnName , rowIndex , tooltip){
		
		var html ="";
		for(var i = 0; i < culumnName.length ; i++){
			var culumn =   jQuery(obj).children("."+culumnName[i]).text();
			
			for(var j = 0; j < tooltip.length ; j++){
				html = "<span title='"+tooltip[i]+"'> "+culumn+" </span>";
			}
			
			jQuery("."+culumnName[i]).eq(rowIndex).html(html);
		}
    		
	}*/
	
	
	/*/function addImageIcon(className , rowIndex , icon , tooltip ,func){
		
		console.info("className :"+className +" rowIndex :" + rowIndex +" icon :"+ icon +" tooltip :" +tooltip  +" func :"+func);
		
		var html ="";
		var funcSet = "";
		for(var i = 0; i < icon.length ; i++){
			var url = 	urlContext+icon[i];
			
			var htmlTmp ="";
			var funcSet = "";
			var tooltipSet = "";
			
			// Add tooltip
			if(tooltip != undefined && tooltip.length != 0 && tooltip[i] != undefined){
				tooltipSet = " title = '"+tooltip[i]+"' "; 
			}
			
			//Add Function
			if (func != undefined && func.length != 0 && func[i] != undefined){
				funcSet = "  onclick = '"+func[i]+"' class ='cursor' "; 
			}
			
			
			htmlTmp =  "<img src='"+url+"'" + tooltipSet + funcSet +" />";
			
			/Add Tooltip และ  function javascripts
			if((tooltip != undefined && tooltip.length != 0) && (func != undefined && func.length != 0)){
				
				if(func[i] != undefined){
					funcSet = "  onclick = '"+func[i]+"' class ='cursor' "; 
				}
				
				if(tooltip[i] != undefined){
					tooltipSet = " title = '"+tooltip[i]+"' "; 
				}
				
				htmlTmp =  "<img src='"+url+"'" + tooltipSet + funcSet +" />";
				//htmlTmp =  "<img src='"+url+"' title = '"+tooltip[i]+"' onclick='"+func[i]+"' class ='cursor' />";
			
			// Add function javascripts
			}else if(tooltip == undefined || tooltip.length == 0){
				
				if(func[i] != undefined){
					funcSet = " onclick = '"+func[i]+"' class ='cursor' "; 
				}
				
				htmlTmp =  "<img src='"+url+"' "+ funcSet+"  />";
//				htmlTmp =  "<img src='"+url+"'  onclick='"+func[i]+"' class ='cursor'/>";
			
			// Add Tooltip
			}else if(func == undefined || func.length == 0){
				
				if(tooltip[i] != undefined){
					tooltipSet = " title = '"+tooltip[i]+"' "; 
				}
				htmlTmp =  "<img src='"+url+"'"+ tooltipSet +" />";
//				htmlTmp =  "<img src='"+url+"'  title = '"+tooltip[i]+"' />";
				
			// Add แค่ icon
			}else{
				htmlTmp =  "<img src='"+url+"' />";
			}
			
			html += htmlTmp;
			console.info("addImageIcon :"+html);
		}
		
		
		console.info(jQuery("."+replaceDot(className)).eq(rowIndex));
		
		jQuery("."+replaceDot(className)).eq(rowIndex).html(html);
		jQuery('[title]').tooltip();
		//jQuery("."+replaceDot(className[i])).tooltip();
	}*/
	
	
	