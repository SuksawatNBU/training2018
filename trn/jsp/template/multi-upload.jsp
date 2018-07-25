<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<script type="text/javascript" src="<s:url value='/js/plupload-2.1.2/js/plupload.full.min.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/plupload-2.1.2/js/jquery.ui.plupload/jquery.ui.plupload.js' />"></script>
<!-- ====================================================== -->

<s:set var="columnName" value='{getText("contentType"), getText("fileName"), getText("fileSize"), getText("thumbnail"), getText("status")}'/>
<s:set var="columnID" value='{"id"}'/> <!-- PK -->
<s:set var="columnData" value='{"fileType", "fileUploadFileName", "fileSize", "thumbnail", ""}'/>
<s:set var="columnCSSClass" value='{"col-width-175px","col-width-400px","col-width-175px","col-width-150px center","col-width-150px"}'/>
<s:set var="hiddenData" value='{"fileUploadFileName","fileUploadedPath"}'/> <!-- domain properties สำหรับดึงข้อมูลมาเก็บเป็น hidden -->
<s:set var="settingTable" value='{"1160:false:true"}'/>  <!-- domain properties สำหรับดึงข้อมูลมาเก็บเป็น hidden -->
<s:include value="/jsp/template/tableAddDeleteRow.jsp"/>	

<script>
	jQuery( document ).ready(function() {
		jQuery('#tableId_<s:property value="#divresult[0]"/>').wrap("<div id='<s:property value='#divresult[0]'/>_drop-target'></div><div id='<s:property value='#divresult[0]'/>_debug'></div>");
		
		// enable area for drag & drop event
		// jQuery("#<s:property value='#divresult[0]'/>_drop-target").draggable();
		jQuery("#<s:property value='#divresult[0]'/>_drop-target").droppable ({
		    drop: function() { alert('drop'); }
		});
		
		jQuery("#<s:property value='#divresult[0]'/> .LEFT:first").html("<s:property value='#tblHeader'/>");
		jQuery("#<s:property value='#divresult[0]'/> .RIGHT:first").html("<div id='<s:property value='#divContainer'/>'><a href='javascript:void(0);' id='<s:property value='#btnPickfiles'/>'><img src='<s:url value='/images/icon/i_add.png' />'>&nbsp;<s:text name='addFiles' /></a></div>");
		
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
	});

	var tmpPathUrl = "";
	jQuery(function() {
		tmpPathUrl = "<s:property value='@com.cct.app.core.config.parameter.domain.ParameterConfig@getAttachFile().getTmpPath()' />";
		maxFileAdd = parseInt('<s:property value="#properties['maxFileCount']"/>');
		
		var icon = "<img src='<s:url value='/images/icon/i_denied.png' />'>   ";
		try{
			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				url : '<s:property value="#urlUpload"/>',
				file_data_name:'fileMeta.fileUpload',
				max_file_size : '<s:property value="#properties['maxFileSize']"/>' + 'kb',
				browse_button : '<s:property value="#btnPickfiles"/>', // you can pass in id...
				container: document.getElementById('<s:property value="#divContainer"/>'), // ... or DOM Element itself
					
				// Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
				drop_element : 'boxDetail<s:property value="#divresult[0]"/>',
					
				// กรณีที่ทำการ browse multi file
				max_file_count : maxFileAdd,
				multiple_queues : true,
	      		multi_selection: true,
	      		// chunk_size: '30kb', // for png

	         	// Views to activate
	            views: {
	                list: true,
	                thumbs: true, // Show thumbs
	                active: 'thumbs'
	            },
	            
			   	filters : [
		            {title : "files", extensions : "<s:property value='#filters' />"}
		        ],
		        
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
	 
	            	Browse: function(up) {
	                	// Called when file picker is clicked
	            	},
	 
	            	Refresh: function(up) {
	                	// Called when the position or dimensions of the picker change
	            	},
	  
	            	StateChanged: function(up) {
	                	// Called when the state of the queue is changed
	            	},
	  
	            	QueueChanged: function(up) {
	                	// Called when queue is changed by adding or removing files
	            	},
	 
	            	OptionChanged: function(up, name, value, oldValue) {
		                // Called when one of the configuration options is changed
	    	        },
	 
	            	BeforeUpload: function(up, file) {
	                	// Called right before the upload for a given file starts, can be used to cancel it if required
	                	divLoader = new ajaxLoader(jQuery("body"), {bgColor: '#000', opacity: '0.3'});
	                	jQuery('#percent_' + file.id).html(file.percent + "%   " + icon);
	            	},
	  
	            	UploadProgress: function(up, file) {
	                	// Called while file is being uploaded
	    				// console.info(file.name + " > " + file.percent + "% ");
	                	if (file.percent == 100) {
	                		icon = "<img src='<s:url value='/images/icon/i_expect.png' />'>   ";
	                	}
	                    jQuery('#percent_' + file.id).html(file.percent + "%   " + icon);
	            	},
	 
	            	FileFiltered: function(up, file) {
	                	// Called when file successfully files all the filters
	            	},
	  
	            	FilesAdded: function(up, files) {
	            		var i = up.files.length
						jQuery.each(files, function(i, file) {
							// if(file.size > 3145728 || file.status == 4) !Fix
							if(up.settings.max_file_count && i >= up.settings.max_file_count){ //ตรวจสอบกรณีที่เลือกหลายๆไฟล์
								//Nothing
								setTimeout(function(){ up.removeFile(file); }, 50);
							} else{ 
								//TODO Set Display
								createRow(file);	//get file
								
								up.refresh(); // Reposition Flash/Silverlight
								up.start(); // action
							}
						});
	            	},
	  
	            	FilesRemoved: function(up, files) {
	                	// Called when files are removed from queue
	            	},
	  
	            	FileUploaded: function(up, file, response) {
		           		var obj = jQuery.parseJSON(response.response);
		           		
		           		var tagImg = "<embed style='width:50px; height:37px;'>"
							+ "<a onclick='viewer(\""+ tmpPathUrl +obj.fileUploadFileNameTmp[0] +"\",\"" + obj.fileUploadFileName[0] + "\");' >"
							+ "<img border='0' id='img" + file.id + "' src='" + obj.fileThumbnail[0] + "' style='cursor: pointer;'></img>"
							+ "</a></embed>";
			
							jQuery('#thumb_' + file.id).html(tagImg);
		           		
						//Set value CallBack
						callbackPluploadMulti(obj, file);	//get file
						
						// createData(file, obj, '<s:property value="#funcCallBack"/>');	//get file
						
						divLoader.remove();
	            	},
	  
	            	ChunkUploaded: function(up, file, info) {
		                // Called when file chunk has finished uploading
		            },
	 
	 	           UploadComplete: function(up, files) {
	    	            // Called when all files are either uploaded or failed
	        	    },
	 
	            	Destroy: function(up) {
	                	// Called when uploader is destroyed
	            	},
	  
		            Error: function(up, err) {
						if(err.code == -601){
							alert('<s:text name="10060" />');
					   	}else if(err.code == -600){
							alert('<s:text name="10021" /> : ' + plupload.formatSize(err.file.size));
					   	}
		
		               up.refresh(); // Reposition Flash/Silverlight
	            	}
	        	}
		    });
			
	    	uploader.init();
		}catch(e){
	    
	    }
	});
	
	function createRow(file){
		var index = jQuery('table#tableId_<s:property value="#divresult[0]"/>'+' tbody tr').length;
		
		var clArr = ["checkbox", "col-width-175px", "col-width-400px", "col-width-175px right", "col-width-150px center", "col-width-150px right"];
		var elmArr = new Array();

		var listName = "<s:property value='#listAddEditName'/>";
		
		var strPlupload = "<input type='hidden' id='fileUploadFileName_"+file.id+"' name='"+listName+"["+index+"]."+ fileMetaForSave +".fileUploadFileName' value = '' />";
		strPlupload += "<input type='hidden' id='fileUploadFileNameTmp_"+file.id+"' name='"+listName+"["+index+"]."+ fileMetaForSave +".fileUploadFileNameTmp' value = '' />";
	 	strPlupload += "<input type='hidden' id='fileUploadContentType_"+file.id+"' name='"+listName+"["+index+"]."+ fileMetaForSave +".fileUploadContentType' value = '' />";
	 	strPlupload += "<input type='hidden' id='fileUploadedPath_"+file.id+"' name='"+listName+"["+index+"]."+ fileMetaForSave +".fileUploadedPath' value = '' />";
	 	strPlupload += "<input type='hidden' id='fileThumbnail_"+file.id+"' name='"+listName+"["+index+"]."+ fileMetaForSave +".fileThumbnail' value = '' />";
	 	
		//checkbox & hidden value column
		elmArr[0] = "<input type='checkbox' name='cnkColumnId' value=''/>"
			+ "<input type='hidden' id='deleteFlag_"+file.id+"' name='" + listName + "["+ index + "]"  + ".deleteFlag' value=''/>"
			+ strPlupload;
			
		//other column 1, 2, 3, ..., n
		elmArr[1] = file.type;
		elmArr[2] = file.name;
		elmArr[3] = plupload.formatSize(file.size);
		elmArr[4] = "<div id='thumb_" + file.id + "'></div>";
		elmArr[5] = "<div id='percent_" + file.id + "'></div>";

		//2. create row	
		tableCreateTableRow('tableId_<s:property value="#divresult[0]"/>', clArr, elmArr);
	}
	
	function callbackPluploadMulti(obj, files){
		jQuery("#fileUploadFileName_"+files.id).val(obj.fileUploadFileName[0]);
		jQuery("#fileUploadFileNameTmp_"+files.id).val(obj.fileUploadFileNameTmp[0]);
		jQuery("#fileUploadContentType_"+files.id).val(obj.fileUploadContentType[0]);
		
		jQuery("#fileThumbnail_"+files.id).val(obj.fileThumbnail[0]);
		jQuery("#deleteFlag_"+files.id).val("N");
		
		jQuery("#fileUploadedPath_"+files.id).val(tmpPathUrl);
	}
</script>
