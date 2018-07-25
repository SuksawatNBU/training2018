<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.cct.common.CommonAction"%>
<%@ page import="com.cct.trn.core.config.parameter.domain.ParameterConfig"%>

<script type="text/javascript" src="<s:url value='/js/jquery/jquery-1.12.4.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/jquery/jquery-ui-1.12.1.custom.min.js' />"></script>

<script type="text/javascript" src="<s:url value='/js/ui/pagination.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/ui/headersorts.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/ui/inputmethod.js' />"></script>

<script type="text/javascript" src="<s:url value='/js/validate/inputvalidate.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/control/control.js' />"></script>

<script type="text/javascript" src="<s:url value='/js/jquery.loadJSON.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/ajax-loader.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/dialog/dialog.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/table/table.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/table/tableForce.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/table/ucp.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/submit/submit.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/jquery/jquery-button.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/jquery/jquery-suggestion.js' />"></script>

<script type="text/javascript" src="<s:url value='/js/charactor/checkCharactor.js' />"></script>

<script type="text/javascript" src="<s:url value='/js/datatable/jquery.dataTables.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/datatable/datatables.js' />"></script>
<script type="text/javascript" src="<s:url value='/js/datatable/dataTables.fixedColumns.js' />"></script>

<script type="text/javascript">
	/* กำหนดค่าการกรอกข้อมูลอย่างน้อย ก่อนค้นหาของ autocomplete*/
	var fillAtLeast  =  "<s:property value='@com.cct.trn.core.config.parameter.domain.ParameterConfig@getParameter().getApplication().getFillAtLeast()'/>";
	
	/* ค่า Default liperpage*/
	var defaultLinePerpage  =  "<s:property value='@com.cct.trn.core.config.parameter.domain.ParameterConfig@getParameter().getApplication().getDefaultLpp()'/>";
	
	var useGlobalTab = true;	
	var useGlobalFocus = true;
	var urlContext = "<s:url value='/images/icon/'/>"; //สำหรับไฟล์ js ดึงไปใช้
	var validateMessage = {
			CODE_10001: "<s:text name='10001'/>",  // xxx is invalid. Must be letters (A-Z) or number (0-9).
			CODE_10002: "<s:text name='10002'/>",  // xxx is invalid. Must be letters (A-Z), space, hyphen (-) or apostrophe (').
			CODE_10003: "<s:text name='10003'/>",  // xxx cannot be blank.
			CODE_10004: "<s:text name='10004'/>", // xxx is invalid.
			CODE_10005: "<s:text name='10005'/>", // Invalid Email format.
			CODE_10006: "<s:text name='10006'/>", // You must select at least one item.
			CODE_10007: "<s:text name='10007'/>", // Service already exists.
			CODE_10008: "<s:text name='10008'/>", // Schedule already exists. 
			CODE_10009: "<s:text name='10009'/>", // xxx must be selected from the list.  
			CODE_10010: "<s:text name='10010'/>", // Port not a valid port code. 
			CODE_10011: "<s:text name='10011'/>", // Date out of range.
			CODE_10012: "<s:text name='10012'/>", // Invalid flight number. Must in the format AA(X)NNNN(X) where : ‘AA’ will be IATA CODE, ‘(X)’ is optional alphabet and ‘N is mandatory numeric.
			CODE_10013: "<s:text name='10013'/>", // xxx shall not be more than &LIMIT_TIME hours before departure date.
			CODE_10014: "<s:text name='10014'/>", // Arrival Port or Departure Port cannot be same as Transborder Port.
			CODE_10015: "<s:text name='10015'/>", // Transborder date/time shall not be after the arrival date/time.  
			CODE_10017: "<s:text name='10017'/>", // xxx is invalid. Must be letters (A-Z) or space.
			CODE_10018: "<s:text name='10018'/>", // xxx is invalid. Must be letters (A-Z).
			CODE_10019: "<s:text name='10019'/>", // xxx is invalid. Must be number (0-9) or +. 
			CODE_10020: "<s:text name='10020'/>", // xxx is invalid. Must be number (0-9).
			CODE_10021: "<s:text name='10021'/>", // Please enter at least 3 characters.
			CODE_10022: "<s:text name='10022'/>", // Aircraft Call Sign Must be minimum 3 characters long. 
			CODE_10023: "<s:text name='10023'/>", // - at least xxx to yyy characters long.
			CODE_10024: "<s:text name='10024'/>", // - at least one digit (0 to 9).
			CODE_10025: "<s:text name='10025'/>", // - at least one upper case letter (A to Z).
			CODE_10026: "<s:text name='10026'/>", // - at least one lower case...
			CODE_10027: "<s:text name='10027'/>", // - at least one punctuation character (!@#$% etc).
			CODE_10028: "<s:text name='10028'/>", // xxx is invalid. Must be letters (A-Z or a-z) or number (0-9).
			CODE_10029: "<s:text name='10029'/>", // xxx must select at least one item.
			CODE_10036: "<s:text name='10036'/>", // Account expiry date must be after current date.
			CODE_10037: "<s:text name='10037'/>", // xxx is invalid. Must be less than or equal to yyy.
			CODE_10038: "<s:text name='10038'/>", // You must select one item.
			CODE_10039: "<s:text name='10039'/>", //  xxx is invalid. Must be letters (A-Z) 
			CODE_10043: "<s:text name='10043'/>", // Date range  From - To can not over 90 days.
			CODE_10044: "<s:text name='10044'/>", // Unable to reset password for inactive user.
			CODE_10045: "<s:text name='10045'/>", // File type is invalid, allowed extensions are: xxx
			CODE_10046: "<s:text name='10046'/>", // File size exceeded allowed. Maximum size is xxx
			CODE_10047: "<s:text name='10047'/>", // File size should be more than 0 bytes
			CODE_10071: "<s:text name='10071'/>", // Two entered passwords are not the same.
			
			CODE_30002: "<s:text name='30002'/>", // Process is not complete. Please try after some time.
			CODE_30003: "<s:text name='30003'/>", // Data cannot be found.Data has been saved.
			CODE_30004: "<s:text name='30004'/>", // No Record found.
			CODE_30006: "<s:text name='30006'/>", // Update is successful.
			CODE_30007: "<s:text name='30007'/>", // Service cannot be deleted. Please close any open instances of this service and try again.
			CODE_30008: "<s:text name='30008'/>", // Record has been deleted.
			CODE_30009: "<s:text name='30009'/>", // Carrier and administrator are saved to the database.
			CODE_30010: "<s:text name='30010'/>", // Password changed because prompted
			CODE_30011: "<s:text name='30011'/>", // Register not complete.
			CODE_30013: "<s:text name='30013'/>", // Number of search result = xxx information. Too many records were found. Do you want to display information ?
			CODE_30033: "<s:text name='30033'/>", // Invalid password.
			CODE_30063: "<s:text name='30063'/>", // Invalid Mininum Password.
			CODE_30064: "<s:text name='30064'/>", // Invalid Maximum Password.
			CODE_30065: "<s:text name='30065'/>", // Invalid User account connection timeout.
			CODE_30067: "<s:text name='30067'/>", // Invalid data format.
			CODE_50016: "<s:text name='50016'/>", // Are you sure you want to delete data?
			CODE_50017: "<s:text name='50017'/>", // Do you want to exit page?
			CODE_numberOfSearch: "<s:text name='numberOfSearch'/>",
			CODE_order: "<s:text name='order'/>",
			CODE_EDIT: "<s:text name='edit'/>",
			CODE_VIEW: "<s:text name='view'/>",
			CODE_ACTIVE: "<s:text name='active'/>",
			CODE_INACTIVE: "<s:text name='inactive'/>",
	};

	$( document ).ready(function() {
		// console.log( "ready!" );
		if (typeof(document.getElementsByName("urlEdit")[0]) == "undefined"){
			$("#reload").hide();
		}
		
		// disable drag and drop on webpage: nanthaporn.p 20150508
		window.addEventListener("dragover",function(e){
		  e = e || event;
		  e.preventDefault();
		},false);
		
		window.addEventListener("drop",function(e){
		  e = e || event;
		  e.preventDefault();
		},false);
		// end disable drag and drop on webpage: nanthaporn.p 20150508
	});

	
	document.onkeydown = function(e) { 
		e = e || window.event;
		var keyCode = e.keyCode || e.which;
		if(keyCode == 112) {
			if(e.ctrlKey) {
				if (typeof F1 == 'function') { 
					F1(); 
				}
			}
		}		
		
		if(keyCode == 113) {
			if(e.ctrlKey) {
				if (typeof F2 == 'function') { 
					F2(); 
				}
			}
		}
		
		if(keyCode == 114) {
			if(e.ctrlKey) {
				if (typeof F3 == 'function') { 
					F3(); 
				}
			}
		}
		
		if(keyCode == 115) {
			if(e.ctrlKey) {
				if (typeof F4 == 'function') { 
					F4(); 
				}
			}
		}
		
		if(keyCode == 117) {
			if(e.ctrlKey) {
				if (typeof F6 == 'function') { 
					F6(); 
				}
			}
		}
		
		if(keyCode == 118) {
			if(e.ctrlKey) {
				if (typeof F7 == 'function') { 
					F7(); 
				}
			}
		}
		
		if(keyCode == 119) {
			if(e.ctrlKey) {
				if (typeof F8 == 'function') { 
					F8(); 
				}
			}
		}
		
		if(keyCode == 120) {
			if(e.ctrlKey) {
				if (typeof F9 == 'function') { 
					F9(); 
				}
			}
		}
		
		if(keyCode == 121) {
			if(e.ctrlKey) {
				if (typeof F10 == 'function') { 
					F10(); 
				}
			}
		}
		
		if(keyCode == 122) {
			if(e.ctrlKey) {
				if (typeof F11 == 'function') { 
					F11(); 
				}
			}
		}
		
		if(keyCode == 123) {
			if(e.ctrlKey) {
				if (typeof F12 == 'function') { 
					F12(); 
				}
			}
		}
		
		if(keyCode == 27) {
			if(e.ctrlKey) {
				if (typeof ESC == 'function') { 
					ESC(); 
				}
			}
		}
	}
	
	function profile() {
		submitPage("<s:url value='/jsp/security/initProfile.action' />");
	}

	/** กำหนดขนาดของ LoaderStatus 
		- ความยาว/ความกว้าง
		- ตำแหน่งของรูปภาพ ที่ต้องอยู่ตรงกลางของหน้าจอ  
	**/
	function showLoaderStatus(){

		// Create the overlay
		var overlay = jQuery('<div></div>').css({
				'background-color': '#fff',
				'opacity':0.7,
				'width':document.body.scrollWidth,
				'height':document.body.scrollHeight,
				'position':'absolute',
				'top':'0px',
				'left':'0px',
				'z-index':99999
		}).addClass('ajax_overlay');

		jQuery("body").append(overlay.append(
				jQuery('<div></div>').addClass('ajax_loader')
			).fadeIn(200)
		);
	}
	
	function hideLoaderStatus(){
		jQuery("div.ajax_overlay").remove();
    }

    function reloadEditPage(){
		if (typeof(document.getElementsByName("urlEdit")[0]) != "undefined"){
			var url =document.getElementsByName("urlEdit")[0].value ;
			submitPage(url);
		}

	}  

    /**
    * initialWidget
    */
	function initialWidget(){
		// สร้าง selectmenu 
		jQuery("select:visible").not(".ui-datepicker-month").not(".ui-datepicker-year").selectmenu();

		jQuery('span.ui-selectmenu-button').each(function(){
			if(jQuery(this).prev().hasClass("requireInput")){
				jQuery(this).addClass('requireInput');
			}
			
			// กำหนดขนาด selectmenu
			attrId = jQuery(this).attr("id").replace("-button", "");
			attrWidth = jQuery("#"+attrId).width();
			jQuery(this).css("width", attrWidth);
		});
		
		// Clear all accordion state except current page if "Search" or "Print" page
		var page = jQuery(document).find("input[name $= 'page']").val();
		if (page == "SEARCH" || page == "search" || page == "PRINT" || page == "print" || page == undefined) {
			initAccordionState();
		}

		// หา class readonly เพื่อกำหนด border: none และ background: transparent
		jQuery(".readonly").each(function() {
			// console.log(jQuery(this));
    		jQuery(this).css("border","none").css("background","transparent").attr("disabled", "true");
    	});
	}
</script>
<style>
	/* กำหนดความสูงให้ selectmenu */
	.ui-selectmenu-menu .ui-menu {max-height: 150px;}
</style>