<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<script type="text/javascript" src="<s:url value='/js/plupload-2.1.2/js/plupload.full.min.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/plupload-2.1.2/js/jquery.ui.plupload/jquery.ui.plupload.js' />"></script>

<script>
	jQuery(function() {
		try{
			var uploader = new plupload.Uploader({
				runtimes : 'html5,flash,silverlight,html4',
				url : '<s:property value="#urlUpload"/>',
				file_data_name:'fileMeta.fileUpload',
				max_file_size : '<s:property value="#properties['maxFileSize']"/>' + 'mb',
				browse_button : '<s:property value="#btnPickfiles"/>', // you can pass in id...
				container: document.getElementById('<s:property value="#divresult[0]"/>'), // ... or DOM Element itself
					
				// Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
				drop_element : '<s:property value="#divresult[0]"/>',
					
				// กรณีที่ทำการ browse single file
				multi_selection: false,	

				resize : {
					width : 50,
					height : 37,
					quality: 70
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
						//log('[Init]', 'Info:', info, 'Features:', up.features);
					},
			 
			        UploadFile: function(up, file) {
			        	//log('[UploadFile]', file);
						 
						// You can override settings before the file is uploaded
			            // up.setOption('url', 'upload.php?id=' + file.id);
			            // up.setOption('multipart_params', {param1 : 'value1', param2 : 'value2'});
					}
				},
				
		        // Post init events, bound after the internal events
				init : {
	            	PostInit: function() {
	                	// Called after initialization is finished and internal event handlers bound
	            		//log('[PostInit]');
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
	            	},
	  
	            	UploadProgress: function(up, file) {
	                	// Called while file is being uploaded
	            	},
	 
	            	FileFiltered: function(up, file) {
	                	// Called when file successfully files all the filters
	            	},
	  
	            	FilesAdded: function(up, files) {
		            	if(files.length > 1){
							alert('<s:text name="10048" />');
						
							jQuery.each(files, function(i, file) {
								up.removeFile(file);
							});
						} else{
							jQuery.each(files, function(i, file) {
								// if(file.size > 3145728 || file.status == 4) !Fix
								if(file.size > 3145728 || file.status == 4){ //ตรวจสอบกรณีที่เลือกหลายๆไฟล์
									//Nothing
								} else{ 
									//TODO Set Display
			
									up.refresh(); // Reposition Flash/Silverlight
									up.start(); // action
								}
							});
						}
	            	},
	  
	            	FilesRemoved: function(up, files) {
	                	// Called when files are removed from queue
	            	},
	  
	            	FileUploaded: function(up, file, response) {
		           		var obj = jQuery.parseJSON(response.response);
						//Set value CallBack
						//callback(obj, file);	//get file
						var cb = '<s:property value="#callback"/>';
						if(cb != ""){
			    			window[cb](obj, file);
			    		}
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
						/*
						Generic error for example if an exception is thrown inside Silverlight.
						code: -100
						message: Generic error.
						
						HTTP transport error. For example if the server produces a HTTP status other than 200.
						code: -200
						message: HTTP Error.
						
						Generic I/O error. For exampe if it wasn't possible to open the file stream on local machine.
						code: -300
						message: IO error.
						
						Generic I/O error. For exampe if it wasn't possible to open the file stream on local machine.
						code: -400
						message: Security error.
						
						Initialization error. Will be triggered if no runtime was initialized.
						code: -500
						message: Init error.
						
						File size error. If the user selects a file that is to large it will be blocked and an error of this type will be triggered.
						code: -600
						message: File size error.
						
						File extension error. If the user selects a file that isn't valid according to the filters setting.
						code: -601
						message: File extension error.
						       
						Runtime will try to detect if image is proper one. Otherwise will throw this error.
						code: -700
						message:
						       
						While working on the image runtime will try to detect if the operation may potentially run out of memeory and will throw this error.
						code: -701code: -200
						message:
						message:
						       
						Each runtime has an upper limit on a dimension of the image it can handle. If bigger, will throw this error.
						code: -702
						message:
						*/
					   var msgErr = "";
		               if(err.code == -601){
		               		msgErr = '<s:text name="10045" />';
		               		msgErr = msgErr.replace("xxx", "<s:property value='#filters' />");
					   }else if(err.code == -600){
					   		msgErr = '<s:text name="10046" />';
					   		msgErr = msgErr.replace("xxx", '<s:property value="#properties['maxFileSize']"/>' + " mb");
					   }
					   
					   alert(msgErr);
		               up.refresh(); // Reposition Flash/Silverlight
	            	}
	        	}
		    });
			
	    	uploader.init();
		}catch(e){
	    
	    }
	});

</script>
