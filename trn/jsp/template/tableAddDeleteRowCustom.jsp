<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://util.paginationtagiii" prefix="pagination-tag"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<html>
<head>
<!--
   param ใช้ส่งมาต่อ 1 column =  {"nameColumn:type:class:maxlength:nameList:valueList"}

   nameColumn คือ ชื่อ attribute ของ object ที่อยู่ใน list เพื่อจะนำมาแสดง

   type ที่จะเอา มา สร้าง input ต่างๆ
      xxxx
      I = inner text ปกติ
      T = textfield
      A = textarea
      C = combo
      R = radio
      D = datepicker
      CH = checkbok
      B = browse

   class คือ สิ่งที่กำหนดให้ทำอะไร เช่น input_number requireInput ให้พิมพ์ได้แค่ตัวเลข และ มีการ จำเป็นต้องกรอก

   maxlength คือ ขนาดจำนวนที่ input พิมได้ ใส่ ถ้าไม่ต้องการ กำหนด maxlength ให้ใส่ null มา

   nameList คือ  ชื่อ list ที่ต้องการ load มาใช้ใน  combo,radio,checkbok

   valueList คือ  value จะ set index ของ list กรณีที่ต้องการแก้ไขข้อมูล
 -->
 
<script type="text/javascript" src="<s:url value='/js/jquery-ui-initial.js' />"></script>

<!-- ====================================================== 15/01/2558 UPDATA BANK -->
<script type="text/javascript" src="<s:url value='/js/plupload-2.1.2/js/plupload.full.min.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/plupload-2.1.2/js/jquery.ui.plupload/jquery.ui.plupload.js' />"></script>
<!-- ====================================================== -->

<script type="text/javascript">
	var varbRow = "";
	var varbColumn = "";
	var varbFornId = "";

	// แก้ไข path temp ให้เปลี่ยนจากการรับค่าเป็น ดึงค่าจาก parameter config: nanthaporn.p 20150513
	var tmpPathUrl = "";
	var uploader = "";
	var delFile = "";
	
	jQuery().ready(function(){
		tmpPathUrl = "<s:property value='@com.cct.app.core.config.parameter.domain.ParameterConfig@getAttachFile().getTmpPath()' />";
		delFile = '<s:property value="#delFile"/>';
		
		//ใช้ alert ตอน browse ไฟล์เกินขนาดที่กำหนด
  		var fileSizeMsg = '<s:property value="#properties['maxFileSize']"/>';
		if (fileSizeMsg != "" && parseInt(fileSizeMsg) >= 1024) {
			fileSizeMsg = parseInt(fileSizeMsg)/1024 + "MB";
		} else {
			fileSizeMsg = fileSizeMsg + "KB";
		}
		
		if($("li[role='tab']").length > 0){
			$("li[role='tab']").click(function(event) {
	 			jQuery("#container").find("div[id$='_container']").eq(0).remove();
	 			var uploader = new plupload.Uploader({
					runtimes : 'html5,flash,silverlight,html4',
					url : '<s:property value="#urlUpload"/>',
					file_data_name:'fileMeta.fileUpload',
					max_file_size : '<s:property value="#properties['maxFileSize']"/>' + 'kb', // nanthaporn.p 20150513: เปลี่ยนหน่อยจาก mb เป็น kb
					browse_button : 'pickfiles', // you can pass in id...
					container: document.getElementById('container'), // ... or DOM Element itself

					// nanthaporn.p 20150513: สำหรับการ browse file แบบ drag & drop from explorer
					// Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
					drop_element : '<s:property value="#divresult[0]"/>_drop-target',
					
					// nanthaporn.p 20150513: browse single file
					multi_selection: false,
					max_file_count : 1,
					
				   	filters : [
						{title : "files", extensions : "<s:property value='#filters' />"}
			        ],
			        
			     	// nanthaporn.p 20150513: แก้ไข version plupload
			        // Flash settings
			      	flash_swf_url : '<s:url value="/js/plupload-2.1.2/js/Moxie.swf" />',
					silverlight_xap_url : '<s:url value="/js/plupload-2.1.2/js/Moxie.xap" />',
		        
			        // PreInit events, bound before any internal events
			        preinit : {
						Init: function(up, info) {
							log('[Init]', 'Info:', info, 'Features:', up.features);
			            },
			 
						UploadFile: function(up, file) {
			            	log('[UploadFile]', file);
			 
			                // You can override settings before the file is uploaded
			                // up.setOption('url', 'upload.php?id=' + file.id);
			                // up.setOption('multipart_params', {param1 : 'value1', param2 : 'value2'});
			            }
		        	},
		        	
			         // Post init events, bound after the internal events
		        	init : {
		            	PostInit: function() {
		                	// Called after initialization is finished and internal event handlers bound
		                	log('[PostInit]');
		            	},
		  
			            FilesAdded: function(up, files) {
		    	            up.refresh(); // Reposition Flash/Silverlight
		        	    	up.start(); // action
		            	},
		  
		        	    FileUploaded: function(up, file, response) {
		           			var obj = jQuery.parseJSON(response.response);
			           		
							//jQuery('#' + file.id + " b").html(tagImg);
							//alert(""+varbRow+""+varbColumn);
							
							if (jQuery("#tr"+varbRow+" #td"+varbColumn+" #pickfiles").length > 0) {
								var tagImg = "<embed style='width:50px; height:37px;'>"
												+ "<a onclick='viewer(\""+ tmpPathUrl +obj.fileUploadFileNameTmp[0] +"\",\"" + obj.fileUploadFileName[0] + "\");' >"
												+ "<img border='0' id='img" + file.id + "' src='" + obj.fileThumbnail[0] + "' style='cursor: pointer;'></img>"
												+ "</a></embed>";
												
								if (delFile == 'true') {
									tagImg += "<a onclick='deleteImg("+varbRow+","+ varbColumn +")' id='deleteImg'>"
												+ "<img src='<s:url value='/images/icon/i_del.png' />' class='removeImage' style=' width: 15px; height: 15px;cursor: pointer;' alt='Remove this image' align='top'></a>";
								}

								jQuery('#thumb_'+varbRow+varbColumn).empty();
								jQuery('#thumb_'+varbRow+varbColumn).prepend(tagImg);
									
								callbackPlupload(obj);
							}
							
			                // Called when file has finished uploading
		    	            log('[FileUploaded] File:', file, "response:", response);
		        	    },
		  
			            Error: function(up, args) {
		    	            // Called when error occurs
		    	            
		    	            //ไฟล์ไม่รองรับ
		 					if(args.code == "-601") {
		 			     		// alert('ไม่รองรับประเภทไฟล์ดังกล่าว กรุณาตรวจสอบไฟล์');
		 						alert('<s:text name="10013" />');
		 			  		} 
		 					
		    	            //ขนาดไฟล์เกิน
		 					else if (args.code == "-600") {
		 			     		// alert('ขนาดไฟล์เกินกว่าที่ระบบอนุญาต กรุณาตรวจสอบไฟล์');
		 			     		     		
		 						alert('<s:text name="10054" />'.replace("xxx", fileSizeMsg));
		 					}
		    	            
		        	        log('[Error] ', args);
		            	}
		        	}
				});

		 		uploader.init();
			});
		}else{
			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				url : '<s:property value="#urlUpload"/>',
				file_data_name:'fileMeta.fileUpload',
				max_file_size : '<s:property value="#properties['maxFileSize']"/>' + 'kb', // nanthaporn.p 20150513: เปลี่ยนหน่วยจาก mb เป็น kb
				browse_button : 'pickfiles', // you can pass in id...
				container: document.getElementById('container'), // ... or DOM Element itself
					
				// nanthaporn.p 20150513: สำหรับ drag & drop from explorer
				// Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
				drop_element : '<s:property value="#divresult[0]"/>_drop-target',
					
				// nanthaporn.p 20150513: สำหรับ browse single file
				multi_selection: false,	
				
				filters : [
					{title : "files", extensions : "<s:property value='#filters' />"}
				],
			        
				// nanthaporn.p 20150513: เปลี่ยน version plupload
			    // Flash settings
			    flash_swf_url : '<s:url value="/js/plupload-2.1.2/js/Moxie.swf" />',
				silverlight_xap_url : '<s:url value="/js/plupload-2.1.2/js/Moxie.xap" />',
			        
			    // PreInit events, bound before any internal events
			    preinit : {
					Init: function(up, info) {
						log('[Init]', 'Info:', info, 'Features:', up.features);
					},
			 
					UploadFile: function(up, file) {
			        	log('[UploadFile]', file);
			 
						// You can override settings before the file is uploaded
			            // up.setOption('url', 'upload.php?id=' + file.id);
			            // up.setOption('multipart_params', {param1 : 'value1', param2 : 'value2'});
					}
				},
			    
				// Post init events, bound after the internal events
		        init : {
		        	PostInit: function() {
		            	// Called after initialization is finished and internal event handlers bound
		            	log('[PostInit]');
					},
		  
					FilesAdded: function(up, files) {
		    	    	up.refresh(); // Reposition Flash/Silverlight
		        	    up.start(); // action
					},
		            	
		        	FileUploaded: function(up, file, response) {
		           		var obj = jQuery.parseJSON(response.response);
			           		
						//jQuery('#' + file.id + " b").html(tagImg);
						if (jQuery("#tr"+varbRow+" #td"+varbColumn+" #pickfiles").length > 0) {
							var tagImg = "<embed style='width:50px; height:37px;'>"
											+ "<a onclick='viewer(\""+ tmpPathUrl +obj.fileUploadFileNameTmp[0] +"\",\"" + obj.fileUploadFileName[0] + "\");' >"
											+ "<img border='0' id='img" + file.id + "' src='" + obj.fileThumbnail[0] + "' style='cursor: pointer;'></img>"
											+ "</a></embed>";
											
							if (delFile == 'true') {
								tagImg += "<a onclick='deleteImg("+varbRow+","+ varbColumn +")' id='deleteImg'>"
											+ "<img src='<s:url value='/images/icon/i_del.png' />' class='removeImage' style=' width: 15px; height: 15px;cursor: pointer;' alt='Remove this image' align='top'></a>";
							}
							
							jQuery('#thumb_'+varbRow+varbColumn).empty();
							jQuery('#thumb_'+varbRow+varbColumn).prepend(tagImg);
								
							callbackPlupload(obj);
						}
		            		
			            // Called when file has finished uploading
		    	        log('[FileUploaded] File:', file, "response:", response);
					},
		  
			        Error: function(up, args) {
			        	//console.info('error');
			            // console.info(args.file.type);
			            // console.info(up);
		    	        
			            // Called when error occurs
		    	        //ไฟล์ไม่รองรับ
		 				if(args.code == "-601") {
							// alert('ไม่รองรับประเภทไฟล์ดังกล่าว กรุณาตรวจสอบไฟล์');
		 					alert('<s:text name="10013" />');
						} 
		 					
		    	        //ขนาดไฟล์เกิน
		 				else if (args.code == "-600") {
		 					// alert('ขนาดไฟล์เกินกว่าที่ระบบอนุญาต กรุณาตรวจสอบไฟล์');
								 			     		
		 					alert('<s:text name="10054" />'.replace("xxx", fileSizeMsg));
						}
		    	            
		        	    log('[Error] ', args);
					}
				}
			});
				
			uploader.init();
		}
	});
	
	function log() {
       	var str = "";

        plupload.each(arguments, function(arg) {
   	    	//console.info(arguments)
       		if (arg instanceof plupload.File) {
       			//console.info(arg)
			}
		});
	}
		
	function deleteImg(row,column){
		try{
			jQuery("#thumb_"+row+column).find("a[onclick*='viewer']").remove();
		
			if(jQuery("input[id='listData"+row+column+"'][name$='fileUploadFileName']").length > 0){

				jQuery("#tableId_<s:property value='#divresult[0]'/> #fileUploadFileName"+row).val("");
				jQuery("#tableId_<s:property value='#divresult[0]'/> #fileUploadFileNameTmp"+row).val("");
				jQuery("#tableId_<s:property value='#divresult[0]'/> #fileUploadContentType"+row).val("");
				jQuery("#tableId_<s:property value='#divresult[0]'/> #fileUploadedPath"+row).val("");
				jQuery("#tableId_<s:property value='#divresult[0]'/> #fileThumbnail"+row).val("");
				
				jQuery("#tableId_<s:property value='#divresult[0]'/> #deleteFlag"+row).val("Y");
				jQuery("#tableId_<s:property value='#divresult[0]'/> #deleteImg").remove();
			} else {
				jQuery("#tableId_<s:property value='#divresult[0]'/> #deleteImg").remove();
				jQuery("#tableId_<s:property value='#divresult[0]'/> #fileUploadFileName"+row).val("");
				jQuery("#tableId_<s:property value='#divresult[0]'/> #fileUploadFileNameTmp"+row).val("");
				jQuery("#tableId_<s:property value='#divresult[0]'/> #fileUploadContentType"+row).val("");
				jQuery("#tableId_<s:property value='#divresult[0]'/> #fileUploadedPath"+row).val("");
				jQuery("#tableId_<s:property value='#divresult[0]'/> #fileThumbnail"+row).val("");
			}
		}catch(e){
			// console.info(e)
		}
	}

	var Map = {};
	function editPage(id,url){
		//alert(id);
		//alert(url);
	}
	
	function viewPage(id,url){
		//alert(id);
		//alert(url);
	}

	function deleteRow(){
		//เรียก function กลาง ลบแถว
		//param1 : ชื่อ checkbox ของหน้าหลัก
		//param2 : id ตารางของหน้าหลัก
		//param3 : id ที่เก็บค่า ids ไว้  where not in ที่หน้า popup
		tableDeleteRow("cnkColumnId","tableId_<s:property value='#divresult[0]'/>","idsSelectedRow");

		//uncheck checkbox all
		jQuery("div#"+div_elname +" input[name='checkAll']").prop('checked', false);

		jQuery( "div#"+div_elname +" input[id^='btnEdit']" ).removeAttr('disabled');
		jQuery( "div#"+div_elname +" input[id^='btnDelete']" ).removeAttr('disabled');
		jQuery( "div#"+div_elname +" #btnAddRow" ).attr("onclick","addRow('<s:property value='#divresult[0]'/>');");
		
		setClassAll();
	}
	
	function deleteByRow(idTR,div_elname){
		var tableId = 'tableId_'+div_elname;
		
		if (confirm('<s:text name="50005" />') == false) {
			return false;
		}
		
		var status = jQuery("table#" + tableId + " #tr"+idTR);
		// console.info(status);
		
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
		jQuery("table#" + tableId + " tbody > tr:visible").each(function(order) { //loop each row
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
		
		//uncheck checkbox all
		jQuery("div#"+div_elname +" input[name='checkAll']").prop('checked', false);
		
		jQuery("div#"+div_elname +" input[id^='btnEdit']" ).removeAttr('disabled');
		jQuery("div#"+div_elname +" input[id^='btnDelete']" ).removeAttr('disabled');
		jQuery("div#"+div_elname +" #btnAddRow" ).attr("onclick","addRow(\'"+div_elname+"\');");
		jQuery("div#"+div_elname +" #btnDeleteRow" ).attr("onclick","deleteRow_by_elname_custom(\'"+div_elname+"\','" + tableId +"');");
		
		setClassAll();
	}

	// nanthaporn.p 20150513: edit function name
	function deleteRow_by_elname_custom(div_elname,tableId){
		// เรียก function กลาง ลบแถว
		// param1 : ชื่อ checkbox ของหน้าหลัก
		// param2 : id ตารางของหน้าหลัก
		// param3 : id ที่เก็บค่า ids ไว้  where not in ที่หน้า popup
		// Arunya.k		
		/* tableDeleteRow("cnkColumnId", "tableId_<s:property value='#divresult[0]'/>", "idsSelectedRow"); */
		
		// nanthaporn.p 20150513: แก้ไขการอ้างอิง checkbox จาก id เป็น name
		tableDeleteRow( jQuery('div#' + div_elname + ' input[name=\'cnkColumnId\']:checked'), tableId,jQuery('div#' + div_elname + ' #idsSelectedRow'));
	
		//uncheck checkbox all
		jQuery("div#"+div_elname +" input[name='checkAll']").prop('checked', false);
		jQuery("div#"+div_elname +" input[id^='btnEdit']" ).removeAttr('disabled');
		jQuery("div#"+div_elname +" input[id^='btnDelete']" ).removeAttr('disabled');
		jQuery("div#"+div_elname +" #btnAddRow" ).attr("onclick","addRow(\'"+div_elname+"\');");
		
		setClassAll();
	}

	var fileMetaForSave = "<s:property value='#fileMetaForSave'/>";
	function editRow(idTR,numTd,tableId,preSubmitFunc){
		var requst = "";
		var valueColume = new Array() ;
		var type = new Array();
		var classControl = new Array();
		var nameList = new Array();
		var maxlength = new Array();
		var valueList = new Array();

	    var status = jQuery("div#"+tableId +" #btnEdit"+idTR).attr("status");
	    var tempValueColunm  = new Array;

	    /*กดปุ่ม ตกลง เมื่อแก้ไขข้อมูลเสร็จ*/
	    if(status=="true"){
		    if(preSubmitFunc != null){
			    if(!preSubmitFunc("tableId_"+tableId, idTR)){
		    		return false;
		    	}
		    }
	    }
		
		for(var i = 0; i<=numTd;i++){
	    	valueColume[i]  = jQuery("div#"+tableId+ " tr#tr"+ idTR  +" #listData"+idTR+i).val();
	    	valueList[i] = jQuery("div#"+tableId + " tr#tr"+ idTR  +" #valueList"+idTR+i).val();
	    	type[i]  = jQuery("#"+tableId+"_typeX"+i).val();
	    	classControl[i]  = jQuery("#"+tableId+"_classX"+i).val();
	    	nameList[i]  = jQuery("#"+tableId+"_nameListX"+i).val();
	    	maxlength[i] = jQuery("#"+tableId+"_maxlengthX"+i).val();

	    	if(classControl[i] == "null"){
	    		classControl[i] = "";
			}
	    	
	    	if(status=="false"){ /*กดปุ่ม แก้ไข เพื่อต้องการแก้ข้อมูล*/
	    		tempValueColunm[i]  = jQuery("div#"+tableId + " tr#tr"+ idTR  +" #drawEdit"+idTR+i).html();
		        if(type[i]=="T"){
		        	jQuery("div#"+tableId + " tr#tr"+ idTR  +" #drawEdit"+idTR+i).html(jQuery("#itemTxtF").html());
		           	jQuery("div#"+tableId + " tr#tr"+ idTR  +" #drawEdit"+idTR+i).children().attr("id","itemTxtF"+idTR+i);
		           	jQuery("div#"+tableId + " tr#tr"+ idTR  +" #drawEdit"+idTR+i).children().val(valueColume[i]);
		           	jQuery("div#"+tableId + " tr#tr"+ idTR  +" #drawEdit"+idTR+i).children().addClass(classControl[i]);
		           	jQuery("div#"+tableId + " tr#tr"+ idTR  +" #drawEdit"+idTR+i).children().attr("maxLength",maxlength[i]);
		           	jQuery("div#"+tableId + " tr#tr"+ idTR  +" #drawEdit"+idTR+i).children().addClass(requst);
		           	
		        }else  if(type[i]=="A"){
		           	jQuery("div#"+tableId +" #drawEdit"+idTR+i).html(jQuery("#itemTxtA").html());
		           	jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().attr("id","itemTxtA"+idTR+i);
		           	jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().val(valueColume[i]);
		           	jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().attr("class",classControl[i]+" "+requst);
		           	jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().attr("maxLength",maxlength[i]);

		        }else  if(type[i]=="C"){
		           	jQuery("div#"+tableId +" #drawEdit"+idTR+i).html(jQuery("#itemC"+nameList[i]).html());
		           	jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().attr("id","itemC"+idTR+i);
		           	jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().val(valueList[i]);
		           	jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().addClass(classControl[i]);
		           	jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().addClass(requst);

		        }else  if(type[i]=="D"){
		           	jQuery("div#"+tableId +" #drawEdit"+idTR+i).html(jQuery("<input class ='input_datepicker ' />").attr("id",tableId+"_itemDatePicker"+idTR+i));
		           	reloadInputDatePicker();
		           	jQuery("#"+tableId+"_itemDatePicker"+idTR+i).input_dateformat("dateValue",valueColume[i]);
		           	jQuery(jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().get(1)).addClass(classControl[i]);
       		    	jQuery(jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().get(1)).css("width","120px");
		            
		        }else  if(type[i]=="CH"){
		           	jQuery("div#"+tableId +" #drawEdit"+idTR+i).html(jQuery("#itemCH"+nameList[i]).html());
			    	jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().attr("name",tableId+"_itemCH"+idTR+i+"GroupId");
		            jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().attr("id",tableId+"_itemCH"+idTR+i+"GroupId");
		            jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().children().attr("name",tableId+"_itemCH"+idTR+i);
		            jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().children().addClass(classControl[i]);
		            jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().children().addClass(requst);
		            
		            var data = valueList[i].split(",");
	            	jQuery("div#"+tableId +" input[name='"+tableId+"_itemCH"+idTR+i+"']").each(function( index){
		            	  for(var x=0;x<data.length;x++){
                              if(jQuery(this).val()==data[x]){
                            	 jQuery(this).attr('checked', true);
                              }
		            	  }
	            	});

	            }else  if(type[i]=="R"){
	            	jQuery("div#"+tableId +" #drawEdit"+idTR+i).html(jQuery("#itemR"+nameList[i]).html());
	            	jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().children().attr("name",tableId+"_itemR"+idTR+i);
	            	jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().attr("name",tableId+"_itemR"+idTR+i+"GroupId");
             		jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().attr("id",tableId+"_itemR"+idTR+i+"GroupId");
	            	jQuery("div#"+tableId +" input[name='"+tableId+"_itemR"+idTR+i+"']").each(function( index){
						if(jQuery(this).val()==valueList[i]){
                        	jQuery(this).attr('checked', true);
                            return false;
						}
					});
		            
	            	jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().children().addClass(classControl[i]);
		            jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().children().addClass(requst);
		            
	            }else  if(type[i]=="B"){
					jQuery("div#"+tableId +" #drawEdit"+idTR+i).append(jQuery("#container"));
					jQuery("#pickfiles").attr("onClick","browseFile('"+idTR+"','"+i+"','"+tableId+"')");
					
					browseFile(idTR,i,tableId);
					
					jQuery("#container").find("div[id$='_container']").eq(1).remove();
					jQuery("div#"+tableId +" #drawEdit"+idTR+i).find("a[onclick*='viewer']").removeAttr("style");
						
					if(jQuery("div#"+tableId +" #drawEdit"+idTR+i).find("input[name='info.listContract[" + idTR + "].fileMeta.fileUploadFileName']").val() != ""){
						jQuery("#deleteImg").remove();

						if (delFile == 'true') {
							jQuery("div#"+tableId +" #drawEdit"+idTR+i).find("#thumb_"+idTR+i).append("<a onclick='deleteImg("+idTR+","+ i +")' id='deleteImg'><img src='<s:url value='/images/icon/i_del.png' />' class='removeImage' style=' width: 15px; height: 15px;cursor: pointer;' alt='Remove this image' align='top'></a>");	
						}
					}
	            }

	            if(classControl[i].indexOf("requireInput")!=-1){
	    			jQuery("div#"+tableId +" #drawEdit"+idTR+i).prepend("<em style='float:left;'>*&nbsp;</em>");
		    		if(type[i]=="T"){
		    			jQuery(jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().get(1)).css("width","85%");
					}
				}
	      	    
	            controlInput(classControl[i]);

	         	// nanthaporn.p 20150513: เพิ่ม class highlight สำหรับ row ที่ active
	    		jQuery("div#"+tableId +" #tr"+idTR).addClass("ui-state-highlight");
	    	}else{
	    		/*status=="true"*/ /*กดปุ่ม ตกลง เมื่อแก้ไขข้อมูลเสร็จ*/
	        	jQuery(".requireInput"+idTR).addClass("requireInput");
	        	if(!checkDupData("tableId_"+tableId, idTR, i)){
	        		return false;
	        	};
		        	
	        	if(!validateRow(tableId)){
	                return false;
	            }

	        	if(type[i]=="T"){
	        		jQuery("div#"+tableId + " tr#tr"+ idTR  +" #listData"+idTR+i).val(jQuery("div#"+tableId +" #itemTxtF"+idTR+i).val());
	        		jQuery("div#"+tableId + " tr#tr"+ idTR  +" #drawEdit"+idTR+i).html(jQuery("div#"+tableId +" #itemTxtF"+idTR+i).val());
	        		jQuery("div#"+tableId + " tr#tr"+ idTR  +" #drawEdit"+idTR+i).children().removeClass("requireInput");

	        	}else  if(type[i]=="A"){
		           	jQuery("div#"+tableId +" #listData"+idTR+i).val(jQuery("div#"+tableId +" #itemTxtA"+idTR+i).val());
		        	jQuery("div#"+tableId +" #drawEdit"+idTR+i).html(jQuery("div#"+tableId +" #itemTxtA"+idTR+i).val());
		        	jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().removeClass("requireInput");
		        
	        	}else  if(type[i]=="C"){
		           	jQuery("div#"+tableId +" #listData"+idTR+i).val(jQuery("div#"+tableId +" #itemC"+idTR+i).val());
		           	jQuery("div#"+tableId +" #listData"+idTR+i+"n").val(jQuery("div#"+tableId +" #itemC"+idTR+i+" option:selected").text());
		           	jQuery("div#"+tableId +" #valueList"+idTR+i).val(jQuery("div#"+tableId +" #itemC"+idTR+i).val());
		        	jQuery("div#"+tableId +" #drawEdit"+idTR+i).html(jQuery("div#"+tableId +" #itemC"+idTR+i+" option:selected").text());
		        	jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().removeClass("requireInput");
		        
	        	}else  if(type[i]=="D"){
		           	jQuery("div#"+tableId +" #listData"+idTR+i).val(jQuery("#"+tableId+"_itemDatePicker"+idTR+i).val());
		           // jQuery("div#"+tableId +" #drawEdit"+idTR+i).html(jQuery("#"+tableId+"_itemDatePicker"+idTR+i).val());
	        		jQuery("div#"+tableId +" #drawEdit"+idTR+i).html(jQuery("#"+tableId+"_itemDatePicker"+idTR+i).input_dateformat('dateValue').dateForShow);
	        		jQuery(jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().get(1)).removeClass("requireInput");

	        	}else  if(type[i]=="CH"){
			    	var chkTemp="";
			        var dataTemp ="";
		            jQuery("div#"+tableId +" input[name='"+tableId+"_itemCH"+idTR+i+"']:checked").each(function( index){
			           	chk = jQuery(this).val();
			           	data = jQuery(this).attr("data");
			           	chkTemp += chk+",";
			           	dataTemp += data+",";
		            });
	            	chkTemp = chkTemp.substring(0,chkTemp.length-1);
	            	dataTemp = dataTemp.substring(0,dataTemp.length-1);

	            	jQuery("div#"+tableId +" #drawEdit"+idTR+i).html(dataTemp);
		            jQuery("div#"+tableId +" #valueList"+idTR+i).val(chkTemp);
		            jQuery("div#"+tableId +" #listData"+idTR+i).val(chkTemp);
		            jQuery("div#"+tableId +" #listData"+idTR+i+"n").val(dataTemp);
		            jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().removeClass("requireInput");
		            
	        	}else  if(type[i]=="R"){
		           	jQuery("div#"+tableId +" input[name='"+tableId+"_itemR"+idTR+i+"']:radio:checked").each(function( index){
		           		jQuery("div#"+tableId +" #valueList"+idTR+i).val(jQuery(this).val());
		           		jQuery("div#"+tableId +" #drawEdit"+idTR+i).html(jQuery(this).attr("data"));
		           		jQuery("div#"+tableId +" #listData"+idTR+i).val(jQuery(this).val());
		           		jQuery("div#"+tableId +" #listData"+idTR+i+"n").val(jQuery(this).attr("data"));
		           	});
		           	jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().removeClass("requireInput");
		        
	        	}else  if(type[i]=="B"){
          			//jQuery("#divTableAdd").append(jQuery("#container"));
          			jQuery("#item").append(jQuery("#container"));
	          			
           			//jQuery("#container").attr("style","display:none");
           			jQuery("#container").find("div[id$='_container']").eq(1).remove();
           			jQuery("div#"+tableId +" #drawEdit"+idTR+i +" #thumb_"+idTR+i).removeAttr("style");
	           			
           			var listName = jQuery("#"+tableId+"_listTableData").val();

           			// nanthaporn.p 20150513: แก้ไขให้ดึงค่า value โดยอ้างอิงตาม div table 
           			jQuery("input[name='"+listName+"["+idTR+"]."+ fileMetaForSave +".fileUploadFileName"+"']").val(jQuery("div#"+tableId +" #fileUploadFileName"+idTR).val());
		           	jQuery("input[name='"+listName+"["+idTR+"]."+ fileMetaForSave +".fileUploadFileNameTmp"+"']").val(jQuery("div#"+tableId +" #fileUploadFileNameTmp"+idTR).val());
		           	jQuery("input[name='"+listName+"["+idTR+"]."+ fileMetaForSave +".fileUploadContentType"+"']").val(jQuery("div#"+tableId +" #fileUploadContentType"+idTR).val());
		           	jQuery("input[name='"+listName+"["+idTR+"]."+ fileMetaForSave +".fileUploadedPath"+"']").val(jQuery("div#"+tableId +" #fileUploadedPath"+idTR).val());
		           	jQuery("input[name='"+listName+"["+idTR+"]."+ fileMetaForSave +".fileThumbnail"+"']").val(jQuery("div#"+tableId +" #fileThumbnail"+idTR).val());
		           	jQuery("input[name='"+listName+"["+idTR+"]."+ fileMetaForSave +".deleteFlag"+"']").val(jQuery("div#"+tableId +" #deleteFlag"+idTR).val());
		           			
          			jQuery("div#"+tableId +" #deleteImg").remove();
				}
	        	
	        	// nanthaporn.p 20150513: ลบ class highlight สำหรับ row ที่ active
	    		jQuery("div#"+tableId +" #tr"+idTR).removeClass("ui-state-highlight");
			}
		}
	        
        if(status=="false"){
        	/*กดปุ่ม แก้ไข เพื่อต้องการแก้ข้อมูล*/
	   		jQuery("div#"+tableId +" #spanCancel"+idTR).css("display","");
	   		jQuery("div#"+tableId +" #btnEdit"+idTR).css("width","35%");
            jQuery("div#"+tableId +" #btnEdit"+idTR).val("<s:text name="ok"/>");
     	    jQuery("div#"+tableId +" #btnEdit"+idTR).attr("status","true");
     	    jQuery("div#"+tableId +" input[id^='btnEdit']").not("div#"+tableId +" #btnEdit"+idTR).attr("disabled", "disabled");
     	    jQuery("div#"+tableId +" #btnAddRow").removeAttr("onclick");
   			jQuery("div#"+tableId +" #spanDelete"+idTR).css("display","none");
   			jQuery("div#"+tableId +" #btnDelete"+idTR).attr("status","true");
   			jQuery("div#"+tableId +" input[id^='btnDelete']").not("div#"+tableId +" #btnDelete"+idTR).attr("disabled", "disabled");
		
        }else{
        	jQuery("#id"+idTR).val("");
			jQuery("div#"+tableId +" #spanCancel"+idTR).css("display","none");
			jQuery("div#"+tableId +" #btnEdit"+idTR).css("width","35%");
			jQuery("div#"+tableId +" #btnEdit"+idTR).attr("status","false");
			jQuery("div#"+tableId +" #btnEdit"+idTR).val("<s:text name="edit"/>");
			jQuery("div#"+tableId +" input[id^='btnEdit']" ).removeAttr('disabled');
			jQuery("div#"+tableId +" #btnAddRow" ).attr("onclick","addRow('" + tableId + "');");
			jQuery("div#"+tableId +" #btnAddRow" ).next().attr("onclick","deleteRow_by_elname_custom('"+tableId+"','"+"tableId_"+tableId+"')");
			jQuery("div#"+tableId +" #spanDelete"+idTR).css("display","none");

			var chkId = jQuery("div#"+tableId +" #"+idTR).attr("value");
			if (chkId === undefined || chkId == "") {
				// กรณีไม่มี id
				jQuery("div#"+tableId +" #spanDelete"+idTR).css("display","");	
				jQuery("div#"+tableId +" #btnDelete"+idTR).attr("status","false");
				jQuery("div#"+tableId +" input[id^='btnDelete']" ).removeAttr('disabled');
			} else {
				jQuery("div#"+tableId +" #spanDelete"+idTR).css("display","none");
				jQuery("div#"+tableId +" #btnDelete"+idTR).attr("status","false");
				jQuery("div#"+tableId +" input[id^='btnDelete']" ).removeAttr('disabled');
			}
			
			jQuery("div#"+tableId +" .checkbox input[name$='.edited']").attr("value", true);
        }

	    //  new NumberControl(8,2);
	    //  new NumberControl(8,3);

	    Map[tableId] = tempValueColunm;
	}

	function checkDupData(tableId, idRow, idCol) {
		var fact = true;
		var focusColor;
		
		var idDivResult = "<s:property value='#divresult[0]'/>";	
    	var columnCheckDup = jQuery("#"+idDivResult+"_columnCheckDup").val();
   		var caseCheckDup = jQuery("#"+idDivResult+"_caseCheckDup").val();
   	
    	var colArr = columnCheckDup.split(",");
		if (colArr.length > 0) {
			// jQuery("#" + tableId ).find("tr").not("tr[id='tr"+idRow+"']").each(function (rowIndex) {
			jQuery("#" + tableId ).find("tr:visible").not("tr[id='tr"+idRow+"']").each(function (rowIndex) {
				var cou = 0;
				for (var k = 0; k < colArr.length; k++) {
					//var newData = jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("input[id^='listData']").val();	
					var dataInRow = jQuery(this).find("td[id='td"+colArr[k]+"']").find("input[id^='listData']").val();	
					var dataValue = "";
					
					if(jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("div").children("select").length > 0){
	      				dataValue = jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("div").find("select[id*='itemC']").select().val();
	      				focusColor = jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("div").find("select[id*='itemC']").select();
	      			
					}else if(jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("div").children("div[id*='itemCH']").length >0 ){
      					jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("div > div[id*='itemCH']").find("input[name*='itemCH']").each(function(i){
      						if(jQuery(this).is(':checked')){
      							dataValue += ","+jQuery(this).val();
      						};
      					});
      					dataValue = dataValue.substring(1);
	     				focusColor = jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("div > div[id*='itemCH']");	
	     			
					}else if(jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("div").children("div[id*='itemR']").length >0 ){
    					jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("div > div[id*='itemR']").find("input[name*='itemR']").each(function(i){
      						if(jQuery(this).is(':checked')){
      							dataValue = jQuery(this).val();
      						};
      					});
	      				focusColor	= jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("div > div[id*='itemR']");
	     			
					}else if(jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("div").children("input[id*='_itemDatePicker']").length >0 ){
						dataValue = jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("div").find("input[id*='_itemDatePicker']").val();
	     				focusColor = jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("div").find("input[id*='_itemDatePicker']");	
	     			
					}else if(jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("div").children("input[id^='itemTxtF']").length >0 ){       					
	     				dataValue = jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("div").find("input[id^='itemTxtF']").val();
	     				focusColor = jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("div").find("input[id^='itemTxtF']");		
	     			
					}else if(jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("div").children("textarea[id^='itemTxtA']").length >0 ){       					
	     				dataValue = jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("div").find("textarea[id^='itemTxtA']").val();
	     				focusColor = jQuery("#" + tableId ).find("tr[id='tr"+idRow+"']").find("td[id='td"+colArr[k]+"']").find("div").find("textarea[id^='itemTxtA']");
	     			
					}
	     		
					if (jQuery.trim(dataInRow) == jQuery.trim(dataValue)) {
						//console.info("jQuery.trim(dataInRow) : "+ jQuery.trim(dataInRow) + "       jQuery.trim(dataValue) "+dataValue)
						//console.info("xx");
						cou += 1;
					}
				}
			
				if((caseCheckDup=="and" || caseCheckDup =="AND")&&(cou == colArr.length)){
					alert('<s:text name="10003" />');
					jQuery(focusColor).focus();
					fact = false;
					return false;
					
				} else if((caseCheckDup=="or" || caseCheckDup =="OR")&&(cou > 0)){
					alert('<s:text name="10003" />');
					jQuery(focusColor).focus();
					fact = false;
					return false;
				}
			});
		}
		
		return fact;
	}

	/*เพิ่มบรรทัดใหม่*/
	function addRow(tableId){
		//console.info(tableId);
		var d = new Array();
		var ch = 0;
		var r = 0;
		var b = i;
    	var idD = 0;
		var requstCh = "";
		var requstR = "";
		var requstD = "";

		var valueColume = new Array ;
		var type = new Array;
		var classControl = new Array;
		var nameList = new Array;
		var maxlength = new Array;
		var valueList = new Array;

		//var columnLength = "<s:property value='#columnData.size()' />"; /*จำนวน Column ในตาราง ไม่รวม ลำดับ กับ Checkbok*/
		var columnLength = jQuery("#"+tableId +"_columnDataSize").val(); /*จำนวน Column ในตาราง ไม่รวม ลำดับ กับ Checkbok*/
		
		var tableID = "tableId_"+tableId;
	    var id = jQuery("#"+tableID+" tbody tr:last").attr("id");/*เก็บค่า id ของ tr ตัวสุดท้าย*/
    
	    var colL = parseInt(columnLength);
	    var idTR = 0;
	
	    /*กำหนด id ของ tr ที่กำลังจะเพิ่ม*/
	    if(id!=null){
	    	//idTR =  id.substring(2,3);
	    	//idTR = parseInt(idTR)+1;
	    	
	    	// panthapat.t 02/06/2015
	    	idTR = parseInt(id.replace("tr",""))+1;
		}
		var idTrLast  ="tr"+((parseInt(idTR)));
		/*****************************/

		var clArr = new Array();
		var elmArr = new Array();
	    var listName = jQuery("#"+tableId+"_nameLits").val();

		elmArr[0] = "<input type='checkbox' name='cnkColumnId'  id=''/> "+
			"<input type='hidden' name='"+listName+"["+(idTR)+"].deleteFlag' />" +
			"<input type='hidden' name='"+listName+"["+(idTR)+"].edited' value='false' />" +
			"<input type='hidden' name='"+listName+"["+(idTR)+"].id' value='' id='id"+(idTR)+"'/>"
			;
		clArr[0] = "checkbox";

		var classAlign = "";
		var requst = "";
		var j = 0;
		for(var i = 0; i<=colL-1;i++){
			valueColume[i]  = jQuery("#"+tableId+"_listDataX"+i).val();
    		type[i]  = jQuery("#"+tableId+"_typeX"+i).val();
    		classControl[i]  = jQuery("#"+tableId+"_classX"+i).val();
    		nameList[i]  = jQuery("#"+tableId+"_nameListX"+i).val();
    		maxlength[i] = jQuery("#"+tableId+"_maxlengthX"+i).val();

    		if(classControl[i].indexOf("number")>0||classControl[i].indexOf("currency")>0){
    			classAlign =  "alignRight";
    		}else if(classControl[i].indexOf("date")>0||type[i]=="D"||type[i]=="B"){
    			classAlign = "alignCenter";
        	}else{
        		classAlign = "alignLeft";
            }

			elmArr[i+1] = "<div id=\"drawEdit"+idTR+i+"\" class="+classAlign+">";

			clArr[i+1] = jQuery("div#"+tableId +" #classColumn"+i).val();
			
    	 	/*   jQuery(".thHeadRequst").each(function( index){
                  if(jQuery(this).attr("data")==i){
                	  requst = "requireInput"+idTR;
                	  return false;
	              }else{
	            	  requst ="";
		              }
    	    });*/

    		if(classControl[i] == "null"){
    			classControl[i] = "";
	        }

   		    if(type[i]=="T"){
				var strHtml = jQuery("#itemTxtF").html(); /*Selector component ที่ hidden ไว้*/
                var $strHtml = jQuery(strHtml);
                	$strHtml.attr("id","itemTxtF"+idTR+i);
                    $strHtml.addClass(classControl[i]);
                    $strHtml.attr("maxLength",maxlength[i]);
                    $strHtml.addClass(requst);
				elmArr[i+1] +=  $strHtml.get(0).outerHTML;
            
   		    }else if(type[i]=="A"){
           		var strHtml = jQuery("#itemTxtA").html(); /*Selector component ที่ hidden ไว้*/
                var $strHtml = jQuery(strHtml);
                	$strHtml.attr("id","itemTxtA"+idTR+i);
                    $strHtml.attr("maxLength",maxlength[i]);
                    $strHtml.attr("class",classControl[i]+" "+requst);
				elmArr[i+1] +=  $strHtml.get(0).outerHTML;

   		    }else if(type[i]=="C"){
           		var strHtml = jQuery("#itemC"+nameList[i]).html(); /*Selector component ที่ hidden ไว้*/
                var $strHtml = jQuery(strHtml);
                	$strHtml.attr("id","itemC"+idTR+i);
                    $strHtml.addClass(classControl[i]);
                    $strHtml.addClass(requst);
				elmArr[i+1] +=  $strHtml.get(0).outerHTML;
            
   		    }else  if(type[i]=="D"){
            	strHtml = "<input class ='input_datepicker ' />"; /*Selector component ที่ hidden ไว้*/
	         	elmArr[i+1] +=  strHtml;
	         	d[j] = i;
	         	idD = idTR;
	         	requstD = requst;
	         	j++;
            
   		    }else  if(type[i]=="CH"){
	           	strHtml = jQuery("#itemCH"+nameList[i]).html(); /*Selector component ที่ hidden ไว้*/
	           	ch = i;
	           	requstCh = requst;
	           	jQuery(document).delegate("div#"+tableId +" #btnAddRow", "click", function(){
			    	jQuery("div#"+tableId +" #drawEdit"+idTR+ch).children().children().attr("name",tableId+"_itemCH"+idTR+ch);
			        if(classControl[ch].indexOf("requireInput")!=-1){
						jQuery(jQuery("div#"+tableId +" #drawEdit"+idTR+ch).children().get(1)).attr("name",tableId+"_itemCH"+idTR+ch+"GroupId");
			            jQuery(jQuery("div#"+tableId +" #drawEdit"+idTR+ch).children().get(1)).attr("id",tableId+"_itemCH"+idTR+ch+"GroupId");
			            jQuery(jQuery("div#"+tableId +" #drawEdit"+idTR+ch).children().children().get(1)).addClass(classControl[ch]);
			               	
			        }else{
			        	jQuery("div#"+tableId +" #drawEdit"+idTR+ch).children().attr("name",tableId+"_itemCH"+idTR+ch+"GroupId");
			            jQuery("div#"+tableId +" #drawEdit"+idTR+ch).children().attr("id",tableId+"_itemCH"+idTR+ch+"GroupId");
			            jQuery("div#"+tableId +" #drawEdit"+idTR+ch).children().children().addClass(classControl[ch]);
	
			        }
			        
			        jQuery("div#"+tableId +" #drawEdit"+idTR+ch).children().children().addClass(requstCh);
				});
	           	elmArr[i+1] +=  strHtml;
            
   		    }else  if(type[i]=="R"){
				strHtml = jQuery("#itemR"+nameList[i]).html(); /*Selector component ที่ hidden ไว้*/
	            r = i;
	            requstR = requst;
	            
	            jQuery(document).delegate("div#"+tableId +" #btnAddRow", "click", function(){
					jQuery("div#"+tableId +" #drawEdit"+idTR+r).children().children().attr("name",tableId+"_itemR"+idTR+r);
	             	if(classControl[r].indexOf("requireInput")!=-1){
		            	jQuery(jQuery("div#"+tableId +" #drawEdit"+idTR+r).children().get(1)).attr("name",tableId+"_itemR"+idTR+r+"GroupId");
		               	jQuery(jQuery("div#"+tableId +" #drawEdit"+idTR+r).children().get(1)).attr("id",tableId+"_itemR"+idTR+r+"GroupId");
		               	jQuery(jQuery("div#"+tableId +" #drawEdit"+idTR+r).children().children().get(1)).addClass(classControl[r]);
		               	
	             	}else{
		            	jQuery("div#"+tableId +" #drawEdit"+idTR+r).children().attr("name",tableId+"_itemR"+idTR+r+"GroupId");
		            	jQuery("div#"+tableId +" #drawEdit"+idTR+r).children().attr("id",tableId+"_itemR"+idTR+r+"GroupId");
		               	jQuery("div#"+tableId +" #drawEdit"+idTR+r).children().children().addClass(classControl[r]);
			             
	             	}

		            jQuery("div#"+tableId +" #drawEdit"+idTR+r).children().children().addClass(requstR);

	            });
	        	elmArr[i+1] +=  strHtml;
	        	// ============================================= 15/01/2558 Browse file BANK	  	
            
   		    }else if(type[i] == "B"){
            	b = i;
              	var strPlupload = "<input type='hidden' id='fileUploadFileName"+idTR+"' name='"+listName+"["+idTR+"]."+ fileMetaForSave +".fileUploadFileName' value = '' />";
					strPlupload += "<input type='hidden' id='fileUploadFileNameTmp"+idTR+"' name='"+listName+"["+idTR+"]."+ fileMetaForSave +".fileUploadFileNameTmp' value = '' />";
				 	strPlupload += "<input type='hidden' id='fileUploadContentType"+idTR+"' name='"+listName+"["+idTR+"]."+ fileMetaForSave +".fileUploadContentType' value = '' />";
				 	strPlupload += "<input type='hidden' id='fileUploadedPath"+idTR+"' name='"+listName+"["+idTR+"]."+ fileMetaForSave +".fileUploadedPath' value = '' />";
				 	strPlupload += "<input type='hidden' id='fileThumbnail"+idTR+"' name='"+listName+"["+idTR+"]."+ fileMetaForSave +".fileThumbnail' value = '' />";
				 	strPlupload += "<input type='hidden' id='deleteFlag"+idTR+"' name='"+listName+"["+idTR+"]."+ fileMetaForSave +".deleteFlag' value = '' />";

				elmArr[i+1] +=  "<div id='thumb_"+idTR+i+"' ></div>";
               	elmArr[i+1] += strPlupload;

               	jQuery(document).delegate("div#"+tableId +" #btnAddRow", "click", function(){
					jQuery("div#"+tableId +" #drawEdit"+idTR+b).append(jQuery("#container"));
					 jQuery("#pickfiles").attr("onClick","browseFile('"+idTR+"','"+b+"','"+tableId+"')");
	           		 browseFile(idTR,b,tableId);
					//jQuery("#thumb_"+idTR+b).attr("style","float:left;margin-left: 50px");
	            });
            }

   		    if(idTR==0){
   		    	if (jQuery("#"+tableId+"_listDataX"+i).attr("name") !== undefined) {
   		    		lstName = jQuery("#"+tableId+"_listDataX"+i).attr("name").replace("[X]", "["+idTR+"]");	
   		    	}

   		    }else{
   	   	    	if (jQuery("div#"+tableId +" #listData"+(idTR-1)+i).attr("name") !== undefined) {
   	   	    		lstName = jQuery("div#"+tableId +" #listData"+(idTR-1)+i).attr("name").replace("["+(idTR-1)+"]", "["+idTR+"]");
   	   	    	}
   	   	   	}
   		
   		    elmArr[i+1] += "</div>";
   		    if(type[i] != "B"){
	 			elmArr[i+1] += "<input type='hidden' name='"+lstName +"' id='listData"+(idTR)+i+"' />";
	 		}
   		    
   		    if(type[i] == "C" || type[i] == "CH" || type[i] == "R"){
	   		    if(idTR==0){
	   		    	if (jQuery("#"+tableId+"_listDataX"+i).attr("name") !== undefined) {
	   		    		lstName = jQuery("#"+tableId+"_listDataX"+i).next().attr("name").replace("[X]", "["+idTR+"]");	
	   		    	}
	   	   	    }else{
	   	   	    	if (jQuery("div#"+tableId +" #listData"+(idTR-1)+i).attr("name") !== undefined) {
	   	   	    		lstName = jQuery("div#"+tableId +" #listData"+(idTR-1)+i).next().attr("name").replace("["+(idTR-1)+"]", "["+idTR+"]");
	   	   	    	}
	   	   	   	} 
   		 	}

   		    //console.info(lstName);
	   		if(type[i] == "C" || type[i] == "CH" || type[i] == "R"){
		 		elmArr[i+1] += "<input type='hidden' name='"+lstName +"' id='listData"+(idTR)+i+"n' />";
		 	}
			elmArr[i+1] += "<input type='hidden' id='valueList"+(idTR)+i+"'/>";
		}

		clArr[colL+1] = jQuery("div#"+tableId +" #classColumn"+colL).val();
		if(colL == i){
        	clArr[colL+1] = "col-width-225px";
		}

	    elmArr[colL+1] = "<input id='btnEdit"+idTR+"'  type='button' value='<s:text name='ok' />' "+
			"onclick=\"editRow("+idTR+","+(columnLength-1)+",'"+tableId+"',"+ jQuery("#" + tableId + "_function").val() +")\" status='true' style='width: 35%'/>"+
		 	"<span style='display:none;width: 35%' id='spanCancel"+idTR+"'>&nbsp;<input id='btnCancel"+idTR+"' type='button' value='<s:text name='cancel' />'"+
		 	"onclick=\"cancelRow("+idTR+","+(columnLength-1)+",'"+tableId+"')\" status='false' style='width: 35%'> </span>"+
		 	"<span style='width: 35%' id='spanDelete"+idTR+"'>&nbsp;<input id='btnDelete"+idTR+"' type='button' value='<s:text name='delete' />'"+
		 	"onclick=\"deleteByRow("+idTR+",'"+tableId+"')\" status='false' style='width: 35%'> </span>"
		 	;
		 
		tableCreateTableRow(tableID, clArr, elmArr,idTrLast);

		for(var i = 0; i<d.length;i++){
			jQuery("div#"+tableId +" #drawEdit"+idD+d[i]).html(jQuery("<input class ='input_datepicker'/>").attr("id",tableId+"_itemDatePicker"+idD+d[i]));
        	
			reloadInputDatePicker();
        	
			jQuery(jQuery("div#"+tableId +" #drawEdit"+idD+d[i]).children().get(1)).addClass(classControl[d[i]]);
        	if(classControl[d[i]].indexOf("requireInput")!=-1){
        		//jQuery("div#"+tableId +" #drawEdit"+idD+d).prepend("<em style='float:left;'>*&nbsp;</em>");
         		jQuery(jQuery("div#"+tableId +" #drawEdit"+idD+d[i]).children().get(1)).css("width","120px");
        	}else{
        		jQuery(jQuery("div#"+tableId +" #drawEdit"+idD+d[i]).children().get(1)).css("width","130px");
         	}
		}

		jQuery("div#"+tableId +" #btnAddRow").removeAttr("onclick");
		
		//ลบ onclick ของ button delete ที่ footer
		jQuery("div#"+tableId +" #btnAddRow").next().removeAttr("onclick");

		for(var i = 0; i<=colL-1;i++){
			controlInput(classControl[i]);
		}

		jQuery("div#"+tableId +" input[id^='btnEdit']" ).not("#btnEdit"+idTR).attr("disabled", "disabled");
		jQuery("div#"+tableId +" input[id^='btnDelete']" ).not("#btnDelete"+idTR).attr("disabled", "disabled");
		jQuery(":button").button();
		jQuery(".spinner").spinner();
		jQuery( ".combobox" ).combobox();
		jQuery("div .radioset").buttonset();
		jQuery("div .accordion").accordion();
		jQuery(document).tooltip();

		jQuery("div .slider").slider({
			range : true,
			values : [ 17, 67 ]
		});

		for(var i = 0; i<=colL-1;i++){
			if(classControl[i].indexOf("requireInput")!=-1){
  				jQuery("div#"+tableId +" #drawEdit"+idTR+i).prepend("<em style='float:left;'>*&nbsp;</em>");
			
  			 
  				if(type[i]=="T"){
  					jQuery(jQuery("div#"+tableId +" #drawEdit"+idTR+i).children().get(1)).css("width","85%");
  			 	}
			}
		}
		
		// nanthaporn.p 20150513: เพิ่ม class highlight สำหรับ row ที่ active
		jQuery("div#"+tableId +" #tr"+idTR).addClass("ui-state-highlight");
	}

	function browseFile(bRow,bColumn,bFornId){
		//console.log("browseFile");
		this.varbRow = bRow;
		this.varbColumn = bColumn;
		this.varbFornId = bFornId;
	}

	function callbackPlupload(files){
		// console.info(files);
		
		jQuery("#fileUploadFileName"+varbRow).val(files.fileUploadFileName[0]);
		jQuery("#fileUploadFileNameTmp"+varbRow).val(files.fileUploadFileNameTmp[0]);
		jQuery("#fileUploadContentType"+varbRow).val(files.fileUploadContentType[0]);
		
		jQuery("#fileThumbnail"+varbRow).val(files.fileThumbnail[0]);
		jQuery("#deleteFlag"+varbRow).val("N");
		
		//var tmpPath = "<s:property value='@com.cct.strutsii.core.config.parameter.domain.ParameterConfig@getAttachFile().getTmpPath()' />";
		jQuery("#fileUploadedPath"+varbRow).val(tmpPathUrl);
	}

	function viewer(srcFile, fileName){ 
		/*
		//var tmpPath = "<s:property value='@com.cct.strutsii.core.config.parameter.domain.ParameterConfig@getAttachFile().getTmpPath()' />";
		var url="<s:url value='/DownloadStreamServlet?srcFile='/>"+srcFile + "&fileName=" + fileName;
		//console.info(url)
		window.open(url);
		*/
		jQuery("#fileNameTmpForViewer").val(srcFile);
		jQuery("#fileNameForViewer").val(fileName);
		window.open('<s:url value="/jsp/template/viewer.jsp" />');
	}

	function cancelRow(idTR,numTd,tableId){
		 jQuery("#item").append(jQuery("#container"));
		 jQuery("div#"+tableId +" #drawEdit"+idTR+i).find("#container").attr("style","display:none");
		// jQuery("#container").find("div[class='plupload html5']").eq(1).remove();
		
		var tempValueColunm = Map[tableId];
		for(var i = 0; i<=numTd;i++){
			jQuery("div#"+tableId + " tr#tr"+ idTR  +" #drawEdit"+idTR+i).html(tempValueColunm[i]);
		}
		
		jQuery("div#"+tableId +" #spanCancel"+idTR).css("display","none");
		jQuery("div#"+tableId +" #btnEdit"+idTR).css("width","35%");
		jQuery("div#"+tableId +" #btnEdit"+idTR).attr("status","false");
		jQuery("div#"+tableId +" #btnEdit"+idTR).val("<s:text name="edit"/>");
		jQuery("div#"+tableId +" input[id^='btnEdit']" ).removeAttr('disabled');
		jQuery("div#"+tableId +" #btnAddRow" ).attr("onclick","addRow('"+tableId+"');");
		
		var chkId = jQuery("div#"+tableId +" #"+idTR).attr("value");
		if (chkId === undefined || chkId == "") {
			// กรณีไม่มี id
			jQuery("div#"+tableId +" #spanDelete"+idTR).css("display","");	
			jQuery("div#"+tableId +" #btnDelete"+idTR).attr("status","false");
			jQuery("div#"+tableId +" input[id^='btnDelete']" ).removeAttr('disabled');
		} else {
			jQuery("div#"+tableId +" #spanDelete"+idTR).css("display","none");
			jQuery("div#"+tableId +" #btnDelete"+idTR).attr("status","false");
			jQuery("div#"+tableId +" input[id^='btnDelete']" ).removeAttr('disabled');
		}
		
	}
	
	function controlInput(classControl){
		var newControlStr = "";
		var newControl;
		if(classControl!="null"&&classControl!=""){
			if(classControl.indexOf("requireInput")>0){
				newControlStr = classControl.replace("requireInput","");
			}else{
				newControlStr = classControl;
		    }
	
			newControl = newControlStr.split("_");
		    if(classControl.indexOf("currency")>0){
		   		if(newControl.length == 4 ){
		   			new CurrencyControl(parseInt(newControl[2]),parseInt(newControl[3]));
		        }else if(newControl.length == 3){
		        	new CurrencyControl(parseInt(newControl[2]));
		        }else{
		        	new CurrencyControl();
		        }
	
		    }else if(classControl.indexOf("number")>0){
		    	if(newControl.length == 4 ){
		   			new NumberControl(parseInt(newControl[2]),parseInt(newControl[3]));
		        }else if(newControl.length == 3){
		        	new NumberControl(parseInt(newControl[2]));
		        }else{
		        	new NumberControl();
		        }
		    }
		}
	}

	function tableCreateTableRow(tableId, classArr, elementArr,idLast) {
		var rows = jQuery("table#" + tableId + " tbody tr").length;
		var table = document.getElementById(tableId);
		var row = table.insertRow(rows);
	
		jQuery("table#" + tableId + " tbody tr:last").attr("id",idLast);
	
		var cell;
		// Order
		cell = row.insertCell(0);
		cell.className = "order";
		cell.innerHTML = rows + 1;
	
		// Detail
		for ( var i = 0; i < elementArr.length; i++) {
			cell = row.insertCell(i + 1);
			cell.className = classArr[i];
			if(i!=0){
				cell.id = "td"+(i-1);
			}
			if(i==elementArr.length-1){
				cell.style.textAlign = "center";
		    }
			cell.innerHTML = elementArr[i];
		}
		
		//update order & set style lineE/lineO
		tableReOrderSetStyleRow('table#' + tableId + ' tbody tr:visible');
	}
</script>
<style type="text/css">
	.alignLeft {
		text-align: left;
	}
	
	.alignRight {
		text-align: right;
	}
	
	.alignCenter {
		text-align: center;
	}
	
	.dragover {
        background: rgba(255, 255, 255, 0.4);
    	border-color: green;
	}
</style>
</head>
<!-- ******************************* DIV RESULT ********************************* -->

<!-- ******************************* HEADER ********************************* -->
<s:if test="%{#preSubmitFunction==null}">
	<s:set var="preSubmitFunct" value="%{'null'}" />
</s:if>
<s:else>
	<s:set var="preSubmitFunct" value="#preSubmitFunction[0]" />
</s:else>

<input type="hidden" id="<s:property value='#divresult[0]'/>_function" value="<s:property value='#preSubmitFunct'/>" />
<input type="hidden" id="<s:property value='#divresult[0]'/>_columnCheckDup" value="<s:property value='#columnCheckDup'/>" />
<input type="hidden" id="<s:property value='#divresult[0]'/>_caseCheckDup" value="<s:property value='#caseCheckDup'/>" />
<input type="hidden" id="<s:property value='#divresult[0]'/>_listTableData" value="<s:property value='#listTableData'/>" />
<input type="hidden" id="<s:property value='#divresult[0]'/>_caseCheckDup" value="<s:property value='#caseCheckDup'/>" />
<input type="hidden" id="<s:property value='#divresult[0]'/>_columnDataSize" value="<s:property value='#columnData.size()' />" />
<input type="hidden" id="<s:property value='#divresult[0]'/>_delFile" value="<s:property value='#delFile' />" />

<input type="hidden" id="fileNameTmpForViewer" />
<input type="hidden" id="fileNameForViewer" />

<div id="uploader" style="display: none;"></div>

<table class="TOTAL_RECORD" id="headerTable_<s:property value='#divresult[0]'/>" style="display: none">
	<tr>
		<td class="LEFT"></td>
		<td class="RIGHT"></td>
	</tr>
</table>

<table id="model_<s:property value='#divresult[0]'/>" style="display: none;">
	<!-- ******************************* COLUMN HEADER ********************************* -->
	<tr class="th">
		<th class="order ui-state-default"><s:text name="no" /></th>
		<th class="checkbox ui-state-default" style="width: 25px;">
			<input type="checkbox" name="checkAll" onClick="checkboxToggle(jQuery('div#<s:property value="#divresult[0]"/> input[name=\'cnkColumnId\']'),this.checked)" />
		</th>

		<s:iterator value="columnName" status="statusH" var="column_name">
			<th id="th<s:property value="#statusH.index" />" class="<s:property value="#columnCSSClass[#statusH.index]" /> ui-state-default"><s:property value="#column_name" /></th>
			<input type="hidden" id="classColumn<s:property value="#statusH.index"/>" value="<s:property value="#columnCSSClass[#statusH.index]" />" />
		</s:iterator>
		<th class="ui-state-default" style="width: 225px;"></th>
	</tr>

	<!-- Loop get object from string name -->
	<s:set var="listAddEditNameArray" value='%{#listTableData.split("[.]")}' />
	<s:iterator value="listAddEditNameArray" status="statusAddEdit" var="listAddEdit">
		<s:if test="#statusAddEdit.index == 0">
			<s:set var="listAddEditData" value="[#listAddEdit]" />
		</s:if>
		<s:else>
			<s:set var="listAddEditData" value="#listAddEditData[#listAddEdit]" />
		</s:else>
	</s:iterator>
	
	<!-- ******************************* COLUMN DETAIL ********************************* -->
	<s:iterator value="#listAddEditData" status="statusRow" var="domain">
		<s:if test="#statusRow.even == true">
			<s:set var="tabclass" value="%{'LINE_ODD'}" />
		</s:if>
		<s:else>
			<s:set var="tabclass" value="%{'LINE_EVEN'}" />
		</s:else>
		
		<s:if test='#domain.deleteFlag == "Y"'>
			<tr class="td <s:property value='tabclass'/>" id="tr<s:property value="#statusRow.index"/>" style="display: none;">
		</s:if>
		<s:else>
			<tr class="td <s:property value='tabclass' />" id="tr<s:property value="#statusRow.index"/>">
		</s:else>
		
			<td class="order"><s:property value="%{#statusRow.index+1}" /></td>
			<td class="checkbox" style="width: 25px;">
				<input type="checkbox" name="cnkColumnId" value="<s:property value="#domain.id"/>" id="<s:property value='#statusRow.index ' />" />
				<input type='hidden' name='<s:property value="#listTableData"/>[<s:property value='#statusRow.index ' />].deleteFlag' value='<s:property value="#domain.deleteFlag"/>' />
				<input type='hidden' name='<s:property value="#listTableData"/>[<s:property value='#statusRow.index ' />].edited' value='<s:property value="#domain.edited"/>' />
				<input type='hidden' name='<s:property value="#listTableData"/>[<s:property value="#statusRow.index"/>].id' value='<s:property value="#domain.id"/>' /> 
				
				<!-- hiddenData -->
				<s:iterator value="hiddenData" status="statusHidd" var="hidden">
					<s:set var="hiddens" value='%{#hidden.split("[.]")}' />
					<s:iterator value="hiddens" status="status" var="hiddenss">
						<s:if test="#status.index == 0">
							<s:set var="subHidd" value="#domain[#hiddenss]" />
						</s:if>
						<s:else>
							<s:set var="subHidd" value="#subHidd[#hiddenss]" />
						</s:else>
					</s:iterator>
					<input type="hidden" name="<s:property value="#listTableData"/>[<s:property value="#statusRow.index"/>].<s:property value="#hidden"/>" value="<s:property value="#subHidd"/>" />
				</s:iterator>
			</td>

			<!-- columnData -->
			<s:iterator value="columnData" status="status" var="key">
				<s:set var="hiddenParam" value='%{#key.split("[:]")}' />
				<s:set var="keys" value='%{#hiddenParam[0].split("[.]")}' />
				<!-- check attribute that single or subAttribute -->
				<s:iterator value="keys" status="status" var="keyss">
					<s:if test='!#hiddenParam[1].equals("B")'>
						<s:if test="#status.index == 0">
							<s:set var="indexs" value="[#keyss]" />
						</s:if>
						<s:else>
							<s:set var="indexs" value="#indexs[#keyss]" />
						</s:else>
					</s:if>
					<s:else>
						<!-- If be Type B or Browseload -->
						<s:set var="indexsBrowsefileUploadFileName" value="[#keyss].fileUploadFileName" />
						<s:set var="indexsBrowsefileThumbnail" value="[#keyss].fileThumbnail" />
						<s:set var="indexsBrowsefileUploadedPath" value="[#keyss].fileUploadedPath" />
						<s:set var="indexsBrowseFileUploadFileNameTmp" value="[#keyss].FileUploadFileNameTmp" />
						<s:set var="indexsBrowsefileUploadContentType" value="[#keyss].fileUploadContentType" />
						<s:set var="indexsBrowsedeleteFlag" value="[#keyss].deleteFlag" />
					</s:else>
					<s:if test='!#hiddenParam[5].equals("null")'>
						<s:set var="keysL" value='%{#hiddenParam[5].split("[.]")}' />
						<s:iterator value="keysL" status="status" var="keysLL">
							<s:if test="#status.index == 0">
								<s:set var="indexsL" value="[#keysLL]" />
							</s:if>
							<s:else>
								<s:set var="indexsL" value="#indexsL[#keysLL]" />
							</s:else>
						</s:iterator>
					</s:if>
					<s:else>
						<s:set var="indexsL" value="" />
					</s:else>
				</s:iterator>
				
				<s:if test='#hiddenParam[2].indexOf("number")>0 || #hiddenParam[2].indexOf("currency")>0 '>
					<s:set var="cssAlign" value="%{'alignRight'}" />
				</s:if>
				<s:elseif test='#hiddenParam[2].indexOf("date")>0 || #hiddenParam[1].equals("D")'>
					<s:set var="cssAlign" value="%{'alignCenter'}" />
				</s:elseif>
				<s:else>
					<s:set var="cssAlign" value="%{'alignLeft'}" />
				</s:else>
				<!-- ======================= upload 15/01/2558 -->
				<s:if test='!#hiddenParam[1].equals("B")'>
					<td class="<s:property value="#columnCSSClass[#status.index]" />" id="td<s:property value="#status.index" />">
						<div id="drawEdit<s:property value="#statusRow.index"/><s:property value="#status.index"/>" class="<s:property value="#cssAlign"/>">
							<s:if test='#hiddenParam[1].equals("D")'>
								<s:property value='@util.CNSUtil@convertDateForDisplay(#indexs)' />
							</s:if>
							<s:else>
								<s:property value="#indexs" />
							</s:else>
						</div>
						
						<s:if test='!#hiddenParam[5].equals("null")'>
							<input type="hidden"
								name="<s:property value="#listTableData"/>[<s:property value="#statusRow.index"/>].<s:property value="#hiddenParam[5]" />"
								value="<s:property value="#indexsL"/>"
								id="listData<s:property value="#statusRow.index"/><s:property value="#status.index"/>" />

							<s:if test='#hiddenParam[1].equals("C") || #hiddenParam[1].equals("R") || #hiddenParam[1].equals("CH")'>
								<input type="hidden"
									name="<s:property value="#listTableData"/>[<s:property value="#statusRow.index"/>].<s:property value="#hiddenParam[0]" />"
									id="listData<s:property value="#statusRow.index"/><s:property value="#status.index"/>n"
									value="<s:property value="#indexs"/>" />
							</s:if>
						</s:if>
						<s:else>
							<input type="hidden"
								name="<s:property value="#listTableData"/>[<s:property value="#statusRow.index"/>].<s:property value="#hiddenParam[0]" />"
								value="<s:property value="#indexs"/>"
								id="listData<s:property value="#statusRow.index"/><s:property value="#status.index"/>" />
						</s:else>
						
						<input type="hidden" id="valueList<s:property value="#statusRow.index"/><s:property value="#status.index"/>" value="<s:property value="#indexsL"/>" /> 
						<s:set var="indexColume" value="#status.index" />
					</td>
				</s:if>
				<s:else>
					<td class="<s:property value="#columnCSSClass[#status.index]" /> image" style="text-align: center;" id="td<s:property value="#status.index" />">
						<div id="drawEdit<s:property value="#statusRow.index"/><s:property value="#status.index"/>" class="">
							<div id="thumb_<s:property value="#statusRow.index"/><s:property value="#status.index"/>">
								<embed style='width: 50px; height: 50px;'>
									<a onclick="viewer('<s:property value="#indexsBrowsefileUploadedPath"/><s:property value='#indexsBrowseFileUploadFileNameTmp'/>','<s:property value="#indexsBrowsefileUploadFileName"/>')">
										<img style='cursor:pointer;' src="<s:property value='#indexsBrowsefileThumbnail'/>" />
									</a>
								</embed>
							</div>

							<!-- Hidden value Browse -->
							<input type="hidden"
								name="<s:property value="#listTableData"/>[<s:property value="#statusRow.index"/>].<s:property value="#hiddenParam[0]" />.fileUploadFileName"
								value="<s:property value="#indexsBrowsefileUploadFileName"/>"
								id="listData<s:property value="#statusRow.index"/><s:property value="#status.index"/>" />

							<input type="hidden"
								name="<s:property value="#listTableData"/>[<s:property value="#statusRow.index"/>].<s:property value="#hiddenParam[0]" />.fileThumbnail"
								value="<s:property value="#indexsBrowsefileThumbnail"/>"
								id="listData<s:property value="#statusRow.index"/><s:property value="#status.index"/>" />

							<input type="hidden"
								name="<s:property value="#listTableData"/>[<s:property value="#statusRow.index"/>].<s:property value="#hiddenParam[0]" />.fileUploadContentType"
								value="<s:property value="#indexsBrowsefileUploadContentType"/>"
								id="listData<s:property value="#statusRow.index"/><s:property value="#status.index"/>" />

							<input type="hidden"
								name="<s:property value="#listTableData"/>[<s:property value="#statusRow.index"/>].<s:property value="#hiddenParam[0]" />.fileUploadedPath"
								value="<s:property value="#indexsBrowsefileUploadedPath"/>"
								id="listData<s:property value="#statusRow.index"/><s:property value="#status.index"/>" />

							<input type="hidden"
								name="<s:property value="#listTableData"/>[<s:property value="#statusRow.index"/>].<s:property value="#hiddenParam[0]" />.fileUploadFileNameTmp"
								value="<s:property value="#indexsBrowseFileUploadFileNameTmp"/>"
								id="listData<s:property value="#statusRow.index"/><s:property value="#status.index"/>" />

							<input type="hidden"
								name="<s:property value="#listTableData"/>[<s:property value="#statusRow.index"/>].<s:property value="#hiddenParam[0]" />.deleteFlag"
								value="<s:property value="#indexsBrowseDeleteFlag"/>"
								id="listData<s:property value="#statusRow.index"/><s:property value="#status.index"/>" />
								
							<input type="hidden"
								name="fileUploadFileName<s:property value="#statusRow.index"/>"
								id="fileUploadFileName<s:property value="#statusRow.index"/>"
								value="<s:property value="#indexsBrowsefileUploadFileName"/>">
								
							<input type="hidden"
								name="fileUploadFileNameTmp<s:property value="#statusRow.index"/>"
								id="fileUploadFileNameTmp<s:property value="#statusRow.index"/>"
								value="<s:property value="#indexsBrowsefileUploadFileNameTmp"/>">
								
							<input type="hidden"
								name="fileUploadContentType<s:property value="#statusRow.index"/>"
								id="fileUploadContentType<s:property value="#statusRow.index"/>"
								value="<s:property value="#indexsBrowsefileUploadedPath"/>">
								
							<input type="hidden"
								name="fileUploadedPath<s:property value="#statusRow.index"/>"
								id="fileUploadedPath<s:property value="#statusRow.index"/>"
								value="<s:property value="#indexsBrowsefileUploadedPath"/>">

							<input type="hidden"
								name="fileThumbnail<s:property value="#statusRow.index"/>"
								id="fileThumbnail<s:property value="#statusRow.index"/>"
								value="<s:property value="#indexsBrowsefileThumbnail"/>">

							<input type="hidden"
								name="deleteFlag<s:property value="#statusRow.index"/>"
								id="deleteFlag<s:property value="#statusRow.index"/>"
								value="<s:property value="#indexsBrowseDeleteFlag"/>">
							
							<input type="hidden" id="valueList<s:property value="#statusRow.index"/><s:property value="#status.index"/>" value="<s:property value="#indexsL"/>" />
							<s:set var="indexColume" value="#status.index" />
						</div>
					</td>
				</s:else>
			</s:iterator>

			<td style="text-align: center; width: 225px;">
				<input id="btnEdit<s:property value="#statusRow.index"/>" type="button" value="<s:text name="edit" />"
					onclick="editRow(<s:property value="#statusRow.index"/>,<s:property value="#indexColume" />,'<s:property value='#divresult[0]'/>',<s:property value='#preSubmitFunct'/>)"
					status="false" style="width: 35%">
				</input>
				
				<span style="display: none; width: 35%" id="spanCancel<s:property value="#statusRow.index"/>">
					<input id="btnCancel<s:property value="#statusRow.index"/>" type="button"
						value="<s:text name="cancel" />"
						onclick="cancelRow(<s:property value="#statusRow.index"/>,<s:property value="#indexColume" />,'<s:property value='#divresult[0]'/>')"
						status="false" style="width: 35%">
					</input>
				</span>
				
				<s:if test='!#domain.id.equals("")'>
					<span style="display: none; width: 35%" id="spanDelete<s:property value="#statusRow.index"/>">
						<input id="btnDelete<s:property value="#statusRow.index"/>" type="button"
							value="<s:text name="delete" />"
							onclick="deleteByRow(<s:property value="#statusRow.index"/>,'<s:property value='#divresult[0]'/>')"
							status="false" style="width: 35%" />
					</span>
				</s:if>
				<s:else>
					<span style="width: 35%" id="spanDelete<s:property value="#statusRow.index"/>">
						<input id="btnDelete<s:property value="#statusRow.index"/>" type="button"
							value="<s:text name="delete" />"
							onclick="deleteByRow(<s:property value="#statusRow.index"/>,'<s:property value='#divresult[0]'/>')"
							status="false" style="width: 35%" />
					</span>
				</s:else>
			</td>
		</tr>
	</s:iterator>
</table>

<!-- ******************************* FOOTER ********************************* -->
<table class="RESULT_FOOTER" id="footerTable_<s:property value='#divresult[0]'/>" style="display: none;">
	<tr>
		<td class="LEFT">
			<a href="javascript:void(0);" id="btnAddRow" onclick="addRow('<s:property value='#divresult[0]'/>');">
				<img src="<s:url value='/images/icon/i_add.png' />">&nbsp;<s:text name="add" />
			</a> &nbsp;&nbsp;
			<a href="javascript:void(0);" id="btnDeleteRow" onclick="deleteRow_by_elname_custom('<s:property value="#divresult[0]"/>','tableId_<s:property value='#divresult[0]'/>');">
				<img src="<s:url value='/images/icon/i_del.png' />">&nbsp;<s:text name="deleteSelect" />
			</a>
			<s:hidden id="idsSelectedRow" />
		</td>
	</tr>
</table>

<s:set var="stTable" value='%{#settingTable[0].split("[:]")}' />
<script>
	<!--crate table -->
	var table = new Object();
		table.width = "<s:property value='#stTable[0]'/>";
	var tOverX = "<s:property value='#stTable[1]'/>";
    var tOverY = "<s:property value='#stTable[2]'/>";

	if(tOverX == "true"){
		table.overX = true;
	}else{
    	table.overX = false;
	}

	if(tOverY == "true"){
		table.overY = true;
    }else{
    	table.overY = false;
	}

	//1. apply style table
	genTable('<s:property value="#divresult[0]"/>',table,true);
	
	//2. ถ้ามี tr ที่ถูกลบให้ update order & style 
	if(jQuery('table#'+'tableId_<s:property value="#divresult[0]"/>'+' tr[style*="display: none;"]').length>0){
		tableReOrderSetStyleRow('table#'+'tableId_<s:property value="#divresult[0]"/>'+' tr:not([style*="display: none;"])'); 
	}
	
	jQuery('div#<s:property value="#divresult[0]"/>').ready(function(){
		var columnLength = "<s:property value='#columnData.size()' />"; /*จำนวน Column ในตาราง ไม่รวม ลำดับ กับ Checkbok*/
		var colL = parseInt(columnLength);
		var tableId = "<s:property value='#divresult[0]'/>";
		for(var i = 0; i<=colL-1;i++){
			classControl  = jQuery("#"+tableId+"_classX"+i).val();
            if(classControl.indexOf("requireInput")!=-1){
            	jQuery("div#"+tableId +" #th"+i).append("<em>*</em>");
			}
		}
	});
	
	jQuery('#tableId_<s:property value="#divresult[0]"/>').wrap("<div id='<s:property value='#divresult[0]'/>_drop-target'></div><div id='<s:property value='#divresult[0]'/>_debug'></div>");
	
	var filter = '<s:property value="#filters"/>';
	if (filter != "") {
		filter = "<s:text name='supportExtensionFile' /> " + filter;
	}
	
	var fileSize = '<s:property value="#properties['maxFileSize']"/>';
	if (fileSize != "" && parseInt(fileSize) >= 1024) {
		fileSize = " <s:text name='supportFileSize' /> " + parseInt(fileSize)/1024 + "MB";
	} else {
		fileSize = " <s:text name='supportFileSize' /> " + fileSize + "KB";
	}
	
	var comment = '<div>&nbsp;&nbsp;&nbsp;<font class="comment">'+filter + fileSize+'</font></div>';
	if (comment != "") {
		jQuery('#<s:property value="#divresult[0]"/>').append(comment);
	}
</script>

<div id="item" style="display: none;">
	<div id="itemTxtF"><s:textfield cssStyle="width: 93%;" /></div>
	<div id="itemTxtA"><textarea style="resize: none; width: 93%;"></textarea></div>
	<!-- ========= 15/01/2558 Browse file BANK -->


	<s:iterator value="columnData" status="status" var="key">
		<s:set var="isDup" value="%{false}" />
		<s:set var="hiddenParam" value='%{#key.split("[:]")}' />
		<s:set var="keys" value='%{#hiddenParam[0].split("[.]")}' />
		<s:iterator value="keys" status="status" var="keyss">
			<s:if test="#status.index == 0">
				<s:set var="indexs" value="[#keyss]" />
			</s:if>
			<s:else>
				<s:set var="indexs" value="#indexs[#keyss]" />
			</s:else>

			<s:if test='!#hiddenParam[5].equals("null")'>
				<s:set var="keysL" value='%{#hiddenParam[5].split("[.]")}' />
				<s:iterator value="keysL" status="status" var="keysLL">
					<s:if test="#status.index == 0">
						<s:set var="indexsL" value="[#keysLL]" />
					</s:if>
					<s:else>
						<s:set var="indexsL" value="#indexsL[#keysLL]" />
					</s:else>
				</s:iterator>
				<s:set var="keysNList" value='%{#hiddenParam[4].split("[.]")}' />
				<s:iterator value="keysNList" status="status" var="keysNLL">
					<s:if test="#status.index == 0">
						<s:set var="indexsNL" value="[#keysNLL]" />
					</s:if>
					<s:else>
						<s:set var="indexsNL" value="#indexsNL[#keysNLL]" />
					</s:else>
				</s:iterator>
			</s:if>
			<s:else>
				<s:set var="indexsL" value="" />
			</s:else>
		</s:iterator>

		<s:set var="nameList" value='%{#hiddenParam[4].replaceAll("[.]", "_")}' />
		<div id="td<s:property value="#status.index" />">
			<s:if test='!#hiddenParam[5].equals("null")'>
				<input type="hidden"
					name="<s:property value="#listTableData"/>[X].<s:property value="#hiddenParam[5]" />"
					value="<s:property value="#indexs"/>"
					id="<s:property value="#divresult[0]"/>_listDataX<s:property value="#status.index"/>" />
					
				<input type="hidden"
					name="<s:property value="#listTableData"/>[X].<s:property value="#hiddenParam[0]" />"
					value="<s:property value="#indexs"/>"
					id="<s:property value="#divresult[0]"/>_listDataX<s:property value="#status.index"/>" />
			</s:if>
			<s:else>
				<input type="hidden"
					name="<s:property value="#listTableData"/>[X].<s:property value="#hiddenParam[0]" />"
					value="<s:property value="#indexs"/>"
					id="<s:property value="#divresult[0]"/>_listDataX<s:property value="#status.index"/>" />
			</s:else>

			<input type="hidden" value="<s:property value="#listTableData"/>" id="<s:property value="#divresult[0]"/>_nameLits" />
			<input type="hidden" id="<s:property value="#divresult[0]"/>_typeX<s:property value="#status.index"/>" value="<s:property value="#hiddenParam[1]"/>" />
			<input type="hidden" id="<s:property value="#divresult[0]"/>_classX<s:property value="#status.index"/>" value="<s:property value="#hiddenParam[2]"/>" />
			<input type="hidden" id="<s:property value="#divresult[0]"/>_maxlengthX<s:property value="#status.index"/>" value="<s:property value="#hiddenParam[3]"/>" />
			<input type="hidden" id="<s:property value="#divresult[0]"/>_nameListX<s:property value="#status.index"/>" value="<s:property value="#nameList"/>" />
			<input type="hidden" id="<s:property value="#divresult[0]"/>_valueListX<s:property value="#status.index"/>" value="<s:property value="#indexsL"/>" />
		</div>

		<s:if test="#status.index !=0">
			<s:iterator value="columnData" status="statuss" var="s">
				<!-- รอบแรก ไม่เทียบ เพราะถือว่าไม่ซำ้ -->
				<!-- รอบถัดมาจะทำการเทียบ-->
				<s:if test="#status.index >= #statuss.index">
					<!-- เทียบ index ก่อนหน้า index ตัวเอง -->
					<s:set var="hiddenParam_compare" value='%{#s.split("[:]")}' />
					<s:if test="#hiddenParam_compare[4].equals(#hiddenParam[4]) && #status.index > #statuss.index && #hiddenParam_compare[1].equals(#hiddenParam[1])">
						<!-- ซ้ำ -->
						<s:set var="isDup" value="%{true}" />
					</s:if>
					<s:elseif test="#status.index == #statuss.index && #isDup == false">
						<s:if test='#hiddenParam[1].equals("C")'>
							<div id="itemC<s:property value="#nameList"/>">
								<s:select list="#indexsNL" id="%{#nameList}" headerKey="" headerValue="" listKey="key" listValue="value" cssStyle="width: 100%;" cssClass="cmd-hidden" />
							</div>
						</s:if>
						<s:elseif test='#hiddenParam[1].equals("B")'>
							<div id="container" >
								<input type="button" id="pickfiles" value="Select files"/>
							</div>
						</s:elseif>
						<s:elseif test='#hiddenParam[1].equals("R")'>
							<div id="itemR<s:property value="#nameList"/>">
								<div class="requireGroup">
									<s:iterator value="#indexsNL" status="statusR" var="keyR">
										<s:if test="#statusR.index == 0">
											<input type="radio"
												name="radio<s:property value="#hiddenParam[4]"/>"
												value="<s:property value="#keyR.key"/>"
												data="<s:property value="#keyR.value"/>" checked="checked" />
											<s:property value="#keyR.value" />
										</s:if>
										<s:else>
											<input type="radio"
												name="radio<s:property value="#hiddenParam[4]"/>"
												value="<s:property value="#keyR.key"/>"
												data="<s:property value="#keyR.value"/>" />
											<s:property value="#keyR.value" />
										</s:else>
									</s:iterator>
								</div>
							</div>
						</s:elseif>
						<s:elseif test='#hiddenParam[1].equals("CH")'>
							<div id="itemCH<s:property value="#nameList"/>">
								<div class="requireGroup">
									<s:iterator value="#indexsNL" status="statusCH" var="keyCH">
										<input type="checkbox"
											name="checkbox<s:property value="#hiddenParam[4]"/>"
											value="<s:property value="#keyCH.key"/>"
											data="<s:property value="#keyCH.value"/>" />
										<s:property value="#keyCH.value" />
									</s:iterator>
								</div>
							</div>
						</s:elseif>
					</s:elseif>
				</s:if>
			</s:iterator>
		</s:if>
		<s:else>
			<s:if test='#hiddenParam[1].equals("C")'>
				<div id="itemC<s:property value="#nameList"/>">
					<s:select list="#indexsNL" id="%{#nameList}" headerKey="" headerValue="" listKey="key" listValue="value" cssStyle="width: 100%;" cssClass="cmd-hidden" />
				</div>
			</s:if>
			<s:elseif test='#hiddenParam[1].equals("B")'>
					<div id="container" >
						<input type="button" id="pickfiles" value="Select files"/>
					</div>
			</s:elseif>
			<s:elseif test='#hiddenParam[1].equals("R")'>
				<div id="itemR<s:property value="#nameList"/>">
					<div class="requireGroup">
						<s:iterator value="#indexsNL" status="statusR" var="keyR">
							<s:if test="#statusR.index == 0">
								<input type="radio"
									name="radio<s:property value="#hiddenParam[4]"/>"
									value="<s:property value="#keyR.key"/>"
									data="<s:property value="#keyR.value"/>" checked="checked" />
								<s:property value="#keyR.value" />
							</s:if>
							<s:else>
								<input type="radio"
									name="radio<s:property value="#hiddenParam[4]"/>"
									value="<s:property value="#keyR.key"/>"
									data="<s:property value="#keyR.value"/>" />
								<s:property value="#keyR.value" />
							</s:else>
						</s:iterator>
					</div>
				</div>
			</s:elseif>
			<s:elseif test='#hiddenParam[1].equals("CH")'>
				<div id="itemCH<s:property value="#nameList"/>">
					<div class="requireGroup">
						<s:iterator value="#indexsNL" status="statusCH" var="keyCH">
							<input type="checkbox"
								name="checkbox<s:property value="#hiddenParam[4]"/>"
								value="<s:property value="#keyCH.key"/>"
								data="<s:property value="#keyCH.value"/>" />
							<s:property value="#keyCH.value" />
						</s:iterator>
					</div>
				</div>
			</s:elseif>
		</s:else>
	</s:iterator>
</div>

<s:set var="preSubmitFunction" value="" />
<s:set var="columnCheckDup" value="" />
<s:set var="caseCheckDup" value="" />
<s:set var="listTableData" value="" />
<s:set var="urlUpload" value="" />
<s:token />
</html>