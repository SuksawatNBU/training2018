<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<script type="text/javascript">
	var initLogin = "N";
	var fristElement = undefined;
	var lastedElement = undefined;
	var is1600 = false;
	var contentHeight;
	var formHeight;
	jQuery( document ).ready(function() {
		documentReady();
		
		reloadStyleAndTheme();
		if (useGlobalTab) {
			documentTab();	
		}
		
		if(initLogin=='N'){
			initFormHeight();
		}
		initDisplay1600();
		
		initialWidget();
		
		documentFocus();
		
		/** edit APPS2015-804, APPS2015-805, APPS2015-806, APPS2015-807, APPS2015-808, APPS2015-809 */
		if(jQuery("input[name='page']").val() == "SEARCH"){
			jQuery("div.CRITERIA").keydown(function(e) {
				//console.info(jQuery("div.ui-dialog:visible").length);
				// console.info("ul.ui-autocomplete >> " + jQuery("ul.ui-autocomplete:visible").length);
				// ต้องไม่มี Dialog เปิดอยู่ และไม่มี Autocomplete ที่ยังไม่ได้เลือกเปิดอยู่
				if(jQuery("div.ui-dialog:visible").length == 0 && jQuery("ul.ui-autocomplete:visible").length == 0){
				    if(e.which == 13) {
				    	//check auto complete ถ้า input เป็นค่าว่าง  clear ค่า value code-of เป็นค่าว่าง 
				    	jQuery( ".ui-autocomplete-input" ).each(function( index ) {
						    var id_Autocomplete = jQuery( this ).parent().prev().attr('id');
							if(jQuery( this ).val() == ""){
								jQuery( this ).parent().prev().parent().find(":input[code-of='"+id_Autocomplete+"']").val("");
							}
			    		});
				    	
				        searchPage();
				    }
				}
			});
		}
		/** End APPS2015-804, APPS2015-805, APPS2015-806, APPS2015-807, APPS2015-808, APPS2015-809 */
	});
	
	function initFormHeight() {
		var genarateFormHeight = jQuery(window).height() - (2 + jQuery("#BODY_TR_BANNER").height() + jQuery("#BODY_TR_HEADER").height());
		var currentFormHeight = jQuery("#BODY_TR_CONTENT").height();
		contentHeight = jQuery("#BODY_TR_CONTENT").height();
		//console.log(genarateFormHeight);
		//console.log(currentFormHeight);
		if (currentFormHeight < genarateFormHeight) {
			jQuery("#BODY_TD_CONTENT").height(genarateFormHeight - 20);
			formHeight = jQuery("#BODY_TD_CONTENT").height();
		}
		
	}

	function documentTab() {
		jQuery( document ).keydown(function(e) {
			if ((e.shiftKey) && (e.which == 9)) {
				if ((fristElement == undefined) || (lastedElement == undefined)) {
					return false;
				}
				if ((e.target == fristElement)) {
					lastedElement.focus();
					return false;
				}
			} else if (e.which == 9) {
				if ((fristElement == undefined) || (lastedElement == undefined)) {
					return false;
				}
				if ((e.target == lastedElement)) {
					fristElement.focus();
					return false;
				}
			}
		});
	}
	
	function documentFocus(withoutStyle) {
		if (withoutStyle == undefined) {
			// โหลดหน้าแล้วเจอ input ที่เป็น readonly ให้ใส่ css read-only ให้เลย
			jQuery("*[readonly='readonly']").each(function() {
				if (jQuery(this).hasClass('un-read-only')) {
					return;
				};
				jQuery(this).removeClass("readonly").addClass("readonly").attr('tabindex', -1);
			});
			
			jQuery("*[disabled='disabled']").each(function() {
				if (jQuery(this).hasClass('un-read-only')) {
					return;
				};
				jQuery(this).removeClass("readonly").addClass("readonly").attr('tabindex', -1);
			});
			
			//jQuery(":hidden").attr('tabindex', -1);
			jQuery("br").removeAttr('tabindex');
			
	
			//console.info(jQuery("input:visible,select:visible,button:visible").length);
			jQuery("a[class*='navigateColor-']").each(function(i) {
				jQuery(this).attr('id', 'navigateColor_' + i);
			});
		}
		
		//ชวลิต เพิ่มจับ link ที่ visible 10/3/2558
		//var selector = jQuery("input:visible, select:visible, button:visible, textarea:visible, a.action-link, a[class*='navigateColor-']");
		var selector = jQuery("input:visible,.ui-selectmenu-button, select:visible, button:visible, textarea:visible, a.action-link:visible, a[class*='navigateColor-']");
		if (selector.length > 0) {
			selector.each(function(i) {
				if ((jQuery(this).attr('disabled') == undefined) && (jQuery(this).attr('readonly') == undefined)) {
					fristElement = selector[i];
					return false;
				}
			});

			for (var i = selector.length - 1; i > 0; i--) {
				if ((jQuery(selector[i]).attr('disabled') == undefined) && (jQuery(selector[i]).attr('readonly') == undefined)) {
					lastedElement = selector[i];
					break;
				}
			}

			//Arunya.k 20150605
			//กรณีที่ element แรกของหน้าจอเป็น datepicker ไม่ต้องการให้ Focus เพราะการ Focus จะทำให้ปฏิธินแสดงขึ้นมา
			//มาจากโครงการ APPS
 			/* if (fristElement != undefined) {
				if (useGlobalFocus) {
					fristElement.focus();
				}
			} */
			
			if (fristElement != undefined && ((jQuery(fristElement).attr('class') != undefined)&&(jQuery(fristElement).attr('class').search('hasDatepicker') == -1))) {
				if (useGlobalFocus) {
					fristElement.focus();
				}
			}
			//////////////////////////////////////////////////////
		}
	}
	
	function documentReady() {
		// โหลดหน้าแล้วเจอ input_idcard ให้ใส่ input_number_13 เพิ่มให้เลย
		jQuery(" .input_idcard").each(function() {
			jQuery(this).removeClass('input_number').addClass('input_number').attr('maxlength', 13);
		});
		new NumberControl();
		
		if (parseInt(jQuery(window).width(), 10) >= 1500) {
			is1600 = true;
		}
	}

	function documentReadyWithParent(elParentId) {
		var selector = jQuery("#" + elParentId).find("input:visible, select:visible, button:visible, textarea:visible, a.action-link, a[class*='navigateColor-']");
		if (selector.length > 0) {
			selector.each(function(i) {
				//console.info(selector[i].tagName);
				if ((jQuery(this).attr('disabled') == undefined) && (jQuery(this).attr('readonly') == undefined)) {
					if ((selector[i].tagName != 'A') && (selector[i].tagName != 'a')){
						fristElement = selector[i];
						return false;
					}
				}
			});

			for (var i = selector.length - 1; i > 0; i--) {
				if ((jQuery(selector[i]).attr('disabled') == undefined) && (jQuery(selector[i]).attr('readonly') == undefined)) {
					lastedElement = selector[i];
					break;
				}
			}

			if (fristElement != undefined) {
				fristElement.focus();
			}
		}
	}
	
	function reloadStyleAndTheme() {
		var url = window.location.href;
		if(url.indexOf("StyleAndTheme.action") > 0){
			//TRUE = อยู่หน้า style and theme
			setClassStyleAndTheme();
		}else{
			//FALSE = อยู่หน้าอื่น
			setClassAll();
		}
	}
	
	function swapHorizontalLayout(divCriteriaForm) {
		var rule_h_r = parseInt(jQuery("#"+ divCriteriaForm + "_TableHorizontal").attr("rule-h-r"), 10);
		var rule_h_c = parseInt(jQuery("#"+ divCriteriaForm + "_TableHorizontal").attr("rule-h-c"), 10);
		for (var hr = 0; hr < rule_h_r; hr++) {
			if (jQuery("#"+ divCriteriaForm + "_TableHorizontal" + " > tbody tr:eq(" + hr + ")").length == 0) {
				jQuery("#"+ divCriteriaForm + "_TableHorizontal" + " > tbody").append("<tr></tr>");
			}
			
			for (var hc = 0; hc < rule_h_c; hc++) {
				if (jQuery("#"+ divCriteriaForm + "_TableHorizontal" + " > tbody tr:eq(" + hr + ") td:eq(" + hc + ")").length == 0) {
					jQuery("#"+ divCriteriaForm + "_TableVertical" + " td[rule-hp-r='" + hr + "'][rule-hp-c='" + hc + "']").removeAttr("colspan");
					jQuery("#"+ divCriteriaForm + "_TableHorizontal" + " > tbody > tr:eq(" + hr + ")").append(jQuery("#"+ divCriteriaForm + "_TableVertical" + " td[rule-hp-r='" + hr + "'][rule-hp-c='" + hc + "']"));	
				} else {
					jQuery("#"+ divCriteriaForm + "_TableVertical" + " td[rule-hp-r='" + hr + "'][rule-hp-c='" + hc + "']").removeAttr("colspan");
					jQuery("#"+ divCriteriaForm + "_TableVertical" + " td[rule-hp-r='" + hr + "'][rule-hp-c='" + hc + "']").insertBefore(jQuery("#"+ divCriteriaForm + "_TableHorizontal" + " > tbody tr:eq(" + hr + ") td:eq(" + hc + ")"));
				}
			}
		}
		
		jQuery("#"+ divCriteriaForm + "_llp-caption-h").append(jQuery("#"+ divCriteriaForm + "_llp-caption-v").children());
		jQuery("#"+ divCriteriaForm + "_llp-input-h").append(jQuery("#"+ divCriteriaForm + "_llp-input-v").children());
		
		jQuery("#"+ divCriteriaForm + "_TableHorizontal").css("display", "");
		jQuery("#"+ divCriteriaForm + "_TableVertical").css("display", "none");
		jQuery(".accordion-ext").removeClass("accordion-ext").addClass("accordion-fake");
	}
	
	function swapVerticalLayout(divCriteriaForm) {
		var rule_v_r = parseInt(jQuery("#" + divCriteriaForm + "_TableVertical").attr("rule-v-r"), 10);
		var rule_v_c = parseInt(jQuery("#" + divCriteriaForm + "_TableVertical").attr("rule-v-c"), 10);
		
		for (var vr = 0; vr < rule_v_r; vr++) {
			if (jQuery("#"+ divCriteriaForm + "_TableVertical" + " > tbody tr:eq(" + vr + ")").length == 0) {
				jQuery("#"+ divCriteriaForm + "_TableVertical" + " > tbody").append("<tr></tr>");
			}
			
			for (var vc = 0; vc < rule_v_c; vc++) {
				if (jQuery("#"+ divCriteriaForm + "_TableVertical" + " > tbody tr:eq(" + vr + ") td:eq(" + vc + ")").length == 0) {
					if (jQuery("#"+ divCriteriaForm + "_TableHorizontal" + " td[rule-vp-r='" + vr + "'][rule-vp-c='" + vc + "']").length == 0) {
						
						if (jQuery("#"+ divCriteriaForm + "_TableVertical" + " > tbody > tr:eq(" + vr + ") td:eq(" + (vc - 1) + ")").attr("colspan") == undefined) {
							jQuery("#"+ divCriteriaForm + "_TableVertical" + " > tbody > tr:eq(" + vr + ") td:eq(" + (vc - 1) + ")").attr("colspan", "2");
						} else {
							
						}
					} else {
						jQuery("#"+ divCriteriaForm + "_TableVertical" + " > tbody > tr:eq(" + vr + ")").append(jQuery("#"+ divCriteriaForm + "_TableHorizontal" + " td[rule-vp-r='" + vr + "'][rule-vp-c='" + vc + "']"));
					}
				} else {
					jQuery("#"+ divCriteriaForm + "_TableHorizontal" + " td[rule-vp-r='" + vr + "'][rule-vp-c='" + vc + "']").insertBefore(jQuery("#"+ divCriteriaForm + "_TableVertical" + " > tbody tr:eq(" + vr + ") td:eq(" + vc + ")"));
				}
			}
		}
		
		jQuery("#"+ divCriteriaForm + "_llp-caption-v").append(jQuery("#"+ divCriteriaForm + "_llp-caption-h").children());
		jQuery("#"+ divCriteriaForm + "_llp-input-v").append(jQuery("#"+ divCriteriaForm + "_llp-input-h").children());
		jQuery("#"+ divCriteriaForm + "_TableVertical").css("display", "");
		jQuery("#"+ divCriteriaForm + "_TableHorizontal").css("display", "none");
		jQuery(".accordion-fake").removeClass("accordion-fake").addClass("accordion-ext");
	}
	
	function initDisplay160020150807() {
		var stateDisplay1600 = "";
		var stateDisplay400_1600 = "";
		if (hasStorage()) {
			stateDisplay1600 = sessionStorage.getItem("stateDisplay1600");
			if (stateDisplay1600 == null) {
				stateDisplay1600 = "";
			}
			stateDisplay400_1600 = sessionStorage.getItem("stateDisplay400_1600");
			if (stateDisplay400_1600 == null) {
				stateDisplay400_1600 = "";
			}
		}
		
		if (is1600) {
			if (stateDisplay1600 == "") {
				stateDisplay1600 = "MAX";
				stateDisplay400_1600 = "MAX_OPEN";
			}
			
			if (stateDisplay400_1600 == "") {
				stateDisplay400_1600 = "MAX_OPEN";
			}
		} else {
			stateDisplay1600 = "";
			if (stateDisplay400_1600 == "") {
				stateDisplay400_1600 = "MAX_OPEN";
			}
		}
		
		// แก้ไข state ที่ผิด
		if (hasStorage()) {
			sessionStorage.setItem("stateDisplay1600", stateDisplay1600);
			sessionStorage.setItem("stateDisplay400_1600", stateDisplay400_1600);
	   	}
		
		//alert(stateDisplay1600 + " > " + stateDisplay400_1600);
		if (jQuery(".CRITERIA").length > 0) {
			jQuery(".accordion").removeClass("accordion-fake").addClass("accordion-fake");	
		}
		
		var divIdArray = jQuery(".STYLE_CRITERIA_1600");
		var divResult = jQuery(".STYLE_RESULT_1600");
		
		if (is1600) {
			// ถ้าหน้าจอ 1600 ให้เปลื่ยน css จาก 1280 เป็น 1600
			jQuery(".body_1280").removeClass("body_1280").removeClass("body_1600").addClass("body_1600");
			
			if ((stateDisplay1600 == "MIN") && (stateDisplay400_1600 == "MIN_OPEN")) {
				// แสดง Criteria เล็ก
				jQuery(".CRITERIA_1600").removeClass("CRITERIA_1600").removeClass("CRITERIA_400").addClass("CRITERIA_400");
				jQuery(".CRITERIA_1280").removeClass("CRITERIA_1280").removeClass("CRITERIA_400").addClass("CRITERIA_400");
				
				if(jQuery("input[name='page']").val() == 'SEARCH') {
					jQuery(".LEFT_80").removeClass("LEFT_80").addClass("LEFT_0");
					jQuery(".RIGHT_20").removeClass("RIGHT_20").addClass("RIGHT_100");
				}
				
				jQuery(".RESULT_1600").removeClass("RESULT_1600").removeClass("RESULT_1150").addClass("RESULT_1150");
				jQuery(".RESULT_1280").removeClass("RESULT_1280").removeClass("RESULT_1150").addClass("RESULT_1150");
				
				// set icon ให้เป็นขยาย เพื่อให้เรียกใช้ function returnFullCriteria จัดการกลับไปเป็นแบบย่อให้
				jQuery("#" + jQuery(divIdArray[0]).attr("id") + " #span-icon-expand-1600").removeClass("ui-icon-extlink").removeClass("ui-icon-newwin").addClass("ui-icon-newwin");
				returnFullCriteria();
				adjustDatatableHeight();
			} else if ((stateDisplay1600 == "MAX") && (stateDisplay400_1600 == "MAX_OPEN")) {
				// แสดง Criteria ใหญ่
				jQuery(".CRITERIA_400").removeClass("CRITERIA_400").removeClass("CRITERIA_1600").addClass("CRITERIA_1600");
				jQuery(".CRITERIA_1280").removeClass("CRITERIA_1280").removeClass("CRITERIA_1600").addClass("CRITERIA_1600");
				
				jQuery(".LEFT_0").removeClass("LEFT_0").addClass("LEFT_80");
				jQuery(".RIGHT_100").removeClass("RIGHT_100").addClass("RIGHT_20");
				
				jQuery(".RESULT_1150").removeClass("RESULT_1150").removeClass("RESULT_1600").addClass("RESULT_1600");
				jQuery(".RESULT_1280").removeClass("RESULT_1280").removeClass("RESULT_1600").addClass("RESULT_1600");
				
				// set icon ให้เป็นย่อ เพื่อให้เรียกใช้ function returnFullCriteria จัดการกลับไปเป็นแบบขยายให้
				jQuery("#" + jQuery(divIdArray[0]).attr("id") + " #span-icon-expand-1600").removeClass("ui-icon-newwin").removeClass("ui-icon-extlink").addClass("ui-icon-extlink");
				returnFullCriteria();
				restoreBasicHeight();
			} else {
				var divIdArray = jQuery(".STYLE_CRITERIA_1600");
				var divResult = jQuery(".STYLE_RESULT_1600");
				if ((stateDisplay1600 == "MIN") && (stateDisplay400_1600 == "MIN_CLOSE")) {
				
					jQuery(".CRITERIA_1600").removeClass("CRITERIA_1600").removeClass("CRITERIA_400").addClass("CRITERIA_400");
					jQuery(".CRITERIA_1280").removeClass("CRITERIA_1280").removeClass("CRITERIA_400").addClass("CRITERIA_400");
					
					if(jQuery("input[name='page']").val() == 'SEARCH') {
						jQuery(".LEFT_80").removeClass("LEFT_80").addClass("LEFT_0");
						jQuery(".RIGHT_20").removeClass("RIGHT_20").addClass("RIGHT_100");
					}
					
					jQuery(".RESULT_1600").removeClass("RESULT_1600").removeClass("RESULT_1150").addClass("RESULT_1150");
					jQuery(".RESULT_1280").removeClass("RESULT_1280").removeClass("RESULT_1150").addClass("RESULT_1150");
	
					for (var index = 0; index < divIdArray.length; index++ ) {
						swapVerticalLayout(jQuery(divIdArray[index]).attr("id"));	
					}
					
					jQuery("#" + jQuery(divIdArray[0]).attr("id") + " #span-icon-expand-1600").removeClass("ui-icon-newwin").removeClass("ui-icon-extlink").addClass("ui-icon-extlink");
					
					if (hasStorage()) {
						sessionStorage.setItem("stateDisplay1600", "MIN");
						sessionStorage.setItem("stateDisplay400_1600", "MIN_OPEN");
					}
	
					jQuery("div.ex_highlight_row").css("float", "left");
					jQuery("div.dataTables_wrapper").each(function() {
						jQuery(this).css("margin", "").css("float", "left");
					});
					adjustDatatableHeight();
					adjustDatatableWidth();
					hideSearchForm();
				} else if ((stateDisplay1600 == "MAX") && (stateDisplay400_1600 == "MAX_CLOSE")) {
					jQuery(".CRITERIA_400").removeClass("CRITERIA_400").removeClass("CRITERIA_1600").addClass("CRITERIA_1600");
					jQuery(".CRITERIA_1280").removeClass("CRITERIA_1280").removeClass("CRITERIA_1600").addClass("CRITERIA_1600");
					
					jQuery(".LEFT_0").removeClass("LEFT_0").addClass("LEFT_80");
					jQuery(".RIGHT_100").removeClass("RIGHT_100").addClass("RIGHT_20");
					
					jQuery(".RESULT_1150").removeClass("RESULT_1150").removeClass("RESULT_1600").addClass("RESULT_1600");
					jQuery(".RESULT_1280").removeClass("RESULT_1280").removeClass("RESULT_1600").addClass("RESULT_1600");
					
					for (var index = 0; index < divIdArray.length; index++ ) {
						swapHorizontalLayout(jQuery(divIdArray[index]).attr("id"));	
					}

					jQuery("#" + jQuery(divIdArray[0]).attr("id") + " #span-icon-expand-1600").removeClass("ui-icon-extlink").removeClass("ui-icon-newwin").addClass("ui-icon-newwin");
					
					if (hasStorage()) {
						sessionStorage.setItem("stateDisplay1600", "MAX");
						sessionStorage.setItem("stateDisplay400_1600", "MAX_OPEN");
					}
					
					jQuery("div.ex_highlight_row").css("float", "");
					jQuery("div.dataTables_wrapper").each(function() {
						jQuery(this).css("margin", "auto").css("float", "");	
					});
					restoreBasicHeight();
					restoreBasicWidth();
					hideSearchForm();
				}
			}
		} else {
			// ถ้าหน้าจอ 1280 กำหนดให้เป็น 1280
			jQuery(".body_1600").removeClass("body_1600").removeClass("body_1280").addClass("body_1280");
			if (stateDisplay400_1600 == "MAX_CLOSE") {
				//alert("ซ่อน Criteria");
				hideSearchForm();
			} else {
				//alert("แสดง Criteria แบบ 1280");
				showSearchForm();
			}
			
			jQuery("#" + jQuery(divIdArray[0]).attr("id") + " #span-icon-expand-1600").remove();
		}
	}
	
	function initDisplay1600() {
		
		initDisplay160020150807();
		return;
	}
	
	function returnFullCriteria20150807() {
				
		if (is1600) {
			var divIdArray = jQuery(".STYLE_CRITERIA_1600");
			var divResult = jQuery(".STYLE_RESULT_1600");

			if (jQuery("#" + jQuery(divIdArray[0]).attr("id") + " #span-icon-expand-1600").hasClass("ui-icon-extlink")) {
				//alert("ขยาย Criteria");
				jQuery(".CRITERIA_400").removeClass("CRITERIA_400").removeClass("CRITERIA_1600").addClass("CRITERIA_1600");
				jQuery(".CRITERIA_1280").removeClass("CRITERIA_1280").removeClass("CRITERIA_1600").addClass("CRITERIA_1600");
				
				jQuery(".LEFT_0").removeClass("LEFT_0").addClass("LEFT_80");
				jQuery(".RIGHT_100").removeClass("RIGHT_100").addClass("RIGHT_20");
				
				jQuery(".RESULT_1150").removeClass("RESULT_1150").removeClass("RESULT_1600").addClass("RESULT_1600");
				jQuery(".RESULT_1280").removeClass("RESULT_1280").removeClass("RESULT_1600").addClass("RESULT_1600");
				
				for (var index = 0; index < divIdArray.length; index++ ) {
					swapHorizontalLayout(jQuery(divIdArray[index]).attr("id"));	
				}

				jQuery("#" + jQuery(divIdArray[0]).attr("id") + " #span-icon-expand-1600").removeClass("ui-icon-extlink").removeClass("ui-icon-newwin").addClass("ui-icon-newwin");
				
				if (hasStorage()) {
					sessionStorage.setItem("stateDisplay1600", "MAX");
					sessionStorage.setItem("stateDisplay400_1600", "MAX_OPEN");
				}
				
				jQuery("div.ex_highlight_row").css("float", "");
				jQuery("div.dataTables_wrapper").each(function() {
					jQuery(this).css("margin", "auto").css("float", "");	
				});
				restoreBasicHeight();
				restoreBasicWidth();
				
			} else if (jQuery("#" + jQuery(divIdArray[0]).attr("id") + " #span-icon-expand-1600").hasClass("ui-icon-newwin")) {
				//alert("ย่อ Criteria");
				jQuery(".CRITERIA_1600").removeClass("CRITERIA_1600").removeClass("CRITERIA_400").addClass("CRITERIA_400");
				jQuery(".CRITERIA_1280").removeClass("CRITERIA_1280").removeClass("CRITERIA_400").addClass("CRITERIA_400");
				
				jQuery(".LEFT_80").removeClass("LEFT_80").addClass("LEFT_0");
				jQuery(".RIGHT_20").removeClass("RIGHT_20").addClass("RIGHT_100");
				
				jQuery(".RESULT_1600").removeClass("RESULT_1600").removeClass("RESULT_1150").addClass("RESULT_1150");
				jQuery(".RESULT_1280").removeClass("RESULT_1280").removeClass("RESULT_1150").addClass("RESULT_1150");

				for (var index = 0; index < divIdArray.length; index++ ) {
					swapVerticalLayout(jQuery(divIdArray[index]).attr("id"));	
				}
				
				jQuery("#" + jQuery(divIdArray[0]).attr("id") + " #span-icon-expand-1600").removeClass("ui-icon-newwin").removeClass("ui-icon-extlink").addClass("ui-icon-extlink");
				
				if (hasStorage()) {
					sessionStorage.setItem("stateDisplay1600", "MIN");
					sessionStorage.setItem("stateDisplay400_1600", "MIN_OPEN");
				}

				jQuery("div.ex_highlight_row").css("float", "left");
				jQuery("div.dataTables_wrapper").each(function() {
					jQuery(this).css("margin", "").css("float", "left");
				});
				adjustDatatableHeight();
				adjustDatatableWidth();
			}  else {
				//alert("Criteria ปกติ");
				jQuery(".CRITERIA_400").removeClass("CRITERIA_400").removeClass("CRITERIA_1600").addClass("CRITERIA_1600");
				jQuery(".CRITERIA_1280").removeClass("CRITERIA_1280").removeClass("CRITERIA_1600").addClass("CRITERIA_1600");
				
				jQuery(".LEFT_0").removeClass("LEFT_0").addClass("LEFT_80");
				jQuery(".RIGHT_100").removeClass("RIGHT_100").addClass("RIGHT_20");
				
				jQuery(".RESULT_1150").removeClass("RESULT_1150").removeClass("RESULT_1600").addClass("RESULT_1600");
				jQuery(".RESULT_1280").removeClass("RESULT_1280").removeClass("RESULT_1600").addClass("RESULT_1600");
				
				for (var index = 0; index < divIdArray.length; index++ ) {
					swapHorizontalLayout(jQuery(divIdArray[index]).attr("id"));	
				}

				jQuery("#" + jQuery(divIdArray[0]).attr("id") + " #span-icon-expand-1600").removeClass("ui-icon-extlink").removeClass("ui-icon-newwin").addClass("ui-icon-newwin");
				
				if (hasStorage()) {
					sessionStorage.setItem("stateDisplay1600", "MAX");
					sessionStorage.setItem("stateDisplay400_1600", "MAX_OPEN");
				}
				
				jQuery("div.ex_highlight_row").css("float", "");
				jQuery("div.dataTables_wrapper").each(function() {
					jQuery(this).css("margin", "auto").css("float", "");	
				});
				
				restoreBasicHeight();
				restoreBasicWidth();
			}
		}
	}
	
	
	function returnFullCriteria() {
		
		returnFullCriteria20150807();
		return;
	}
	
	function hideSearchForm() {

		 hideSearchForm20150807();
		 return;
	}
	 
	 
	function hideSearchForm20150807() {
		var divSearchForm = jQuery(".CRITERIA").attr("id");
			
		jQuery("#" + divSearchForm).fadeOut("fast");
		jQuery("#div-icon-show").css("display", "");
		   	
		if (is1600) {
			if (jQuery(".CRITERIA_400").length > 0) {
		   		//alert("ซ่อนจาก form เล็ก");
		   		jQuery(".RESULT_1150").fadeOut("fast").removeClass("RESULT_1150").addClass("RESULT_1600").fadeIn("fast");
			   	jQuery(".RESULT_1600").css('margin-left', '25px');
			   		
				jQuery("div.dataTables_wrapper").each(function() {
					jQuery(this).css("margin", "auto").css("float", "").css("width", "1500px");	
				});
				
				if (hasStorage()) {
		   			sessionStorage.setItem("stateDisplay1600", "MIN");
		   			sessionStorage.setItem("stateDisplay400_1600", "MIN_CLOSE");	
				}
		   	} else if (jQuery(".CRITERIA_1600").length > 0) {
		   		//alert("ซ่อนจาก form ใหญ่");
		   		if (hasStorage()) {
		   			sessionStorage.setItem("stateDisplay1600", "MAX");
		   			sessionStorage.setItem("stateDisplay400_1600", "MAX_CLOSE");	 
				}
		   	}
		} else {
			if (hasStorage()) {
				sessionStorage.setItem("stateDisplay1600", "");
				sessionStorage.setItem("stateDisplay400_1600", "MAX_CLOSE");
		   	}
		}
		   	
		/******
		* Arunya.k : resize height of datable	   	
		****/
		adjustDatatableHeight(); 
		restoreBasicWidth();
		/***********/	   	
		   	
		documentFocus(false);
	}
	
	function showSearchForm20150807() {
		var divSearchForm = jQuery(".CRITERIA").attr("id");
		
	  	jQuery("#" + divSearchForm).fadeIn("fast");
	   	jQuery("#div-icon-show").css("display", "none");
	   	
		if (is1600) {
			var stateDisplay1600 = sessionStorage.getItem("stateDisplay1600");
			var stateDisplay400_1600 = sessionStorage.getItem("stateDisplay400_1600");
			if ((stateDisplay1600 == "MAX") && (stateDisplay400_1600 == "MAX_CLOSE")) {
				//alert("กลับไปแสดง form ใหญ่");
				restoreBasicHeight();
				restoreBasicWidth();
				
				if (hasStorage()) {
					sessionStorage.setItem("stateDisplay1600", "MAX");
					sessionStorage.setItem("stateDisplay400_1600", "MAX_OPEN");
		   		}
			} else if ((stateDisplay1600 == "MIN") && (stateDisplay400_1600 == "MIN_CLOSE")) {
				//alert("กลับไปแสดง form เล็ก");
				jQuery("div.ex_highlight_row").css("float", "left");
				jQuery("div.dataTables_wrapper").each(function() {
					jQuery(this).css("margin", "").css("float", "left");
				});
				adjustDatatableHeight();
				adjustDatatableWidth();
				
				if (hasStorage()) {
					sessionStorage.setItem("stateDisplay1600", "MIN");
					sessionStorage.setItem("stateDisplay400_1600", "MIN_OPEN");
		   		}
			} else {
				//alert("กลับไปแสดง form ใหญ่");
				restoreBasicHeight();
				restoreBasicWidth();
				
				if (hasStorage()) {
					sessionStorage.setItem("stateDisplay1600", "MAX");
					sessionStorage.setItem("stateDisplay400_1600", "MAX_OPEN");
		   		}
			}
			
		} else {
			//alert("กลับไปแสดง form ใหญ่");
			restoreBasicHeight();
			restoreBasicWidth();
			
			if (hasStorage()) {
				sessionStorage.setItem("stateDisplay1600", "");
				sessionStorage.setItem("stateDisplay400_1600", "MAX_OPEN");
		   	}
			
		}
		documentFocus(false);
	}
	
	function showSearchForm(notFix) {
		showSearchForm20150807();
		return;
	}
	
	function restoreBasicHeight() {
		
		//console.info(jQuery(".dataTables_scrollBody").length);
		if (jQuery(".dataTables_scrollBody").length != 1) {
			return;
		}
		// new line = 44 พอดีอยูแล้ว
		// row span = 54
		// row เดียว 27
		var headerHeight = jQuery(".dataTables_scrollHeadInner").height();
		var defaultHeight = 44;
		var headerH = 0;
		if (headerHeight > defaultHeight) {
			headerH = defaultHeight - headerHeight -5;
		} else if (headerHeight < defaultHeight) {
			//headerH = defaultHeight - headerHeight;
		}
		//console.info('y: ' + headerH);
		var divSearchForm = jQuery(".CRITERIA").attr("id");
		var divCriteriaHeight = jQuery("#" + divSearchForm).height();
		// console.info('divCriteriaHeight-1: ' + divCriteriaHeight);
		var genH = formHeight - 60 - 120 - divCriteriaHeight;
		if (genH < 280) {
			genH = 280
		}
		jQuery(".dataTables_scrollBody").height(genH + headerH);
		jQuery(".dataTables_scrollBody").css("height", (genH + headerH) + "px");
		if (headerH < 0) {
			headerH = headerH * -1;
		}
		jQuery(".DTFC_ScrollWrapper").height(jQuery(".dataTables_scrollBody").height() + 45  + headerH);
		jQuery(".DTFC_LeftBodyLiner").height(jQuery(".dataTables_scrollBody").height() - 17);
		jQuery(".DTFC_LeftBodyWrapper").height(jQuery(".DTFC_LeftBodyLiner").height());
	}
	
	function adjustDatatableHeight() {
		//console.info(jQuery(".dataTables_scrollBody").length);
		if (jQuery(".dataTables_scrollBody").length != 1) {
			return;
		}

		var headerHeight = jQuery(".dataTables_scrollHeadInner").height();
		//console.info('headerHeight: ' + headerHeight);
		var defaultHeight = 44;
		var headerH = 0;
		if (headerHeight > defaultHeight) {
			headerH = defaultHeight - headerHeight - 15;
		} else if (headerHeight < defaultHeight) {
			//headerH = defaultHeight - headerHeight;
		}
		//console.info('x: ' + headerH);
		jQuery(".dataTables_scrollBody").height(formHeight - 60 - 120 + headerH);
		if (headerH < 0) {
			headerH = headerH * -1;
		}
		//jQuery(".dataTables_scrollBody").css("overflow", "").css("overflow-y", "scroll").css("overflow-x", "hidden");
		jQuery(".DTFC_ScrollWrapper").height(jQuery(".dataTables_scrollBody").height() + 45 + headerH);
		jQuery(".DTFC_LeftBodyLiner").height(jQuery(".dataTables_scrollBody").height() - 17);
		jQuery(".DTFC_LeftBodyWrapper").height(jQuery(".DTFC_LeftBodyLiner").height());
	}

	function restoreBasicWidthPrefixSelector(prefixSelector) {
		var tableHeight = jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollBody > table.dataTable");
		var divHeight = jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollBody");
		var divWidthNormalArray = [];
		var divWidthScrollArray = [];
		for (var ii = 0; ii < tableHeight.length; ii ++) {
			var divWidthNormal = 1175;
			var divWidthScroll = 1175;
			if (is1600) {
				divWidthNormal = 1500;
				divWidthScroll = 1500;
			}
			
			//console.info(jQuery(tableHeight[ii]).height() + " >= " +  jQuery(divHeight[ii]).height());
			if (jQuery(tableHeight[ii]).height() > jQuery(divHeight[ii]).height()) {
				divWidthScroll = parseInt(divWidthScroll, 10) - 17;
			}
			divWidthNormalArray[ii] = divWidthNormal + "px";
			divWidthScrollArray[ii] = divWidthScroll + "px";
		}
		
		jQuery(prefixSelector).each(function(iii) {	
			if (is1600) {
				jQuery(this).css("width", "1500px");				
			} else {
				jQuery(this).css("width", "1175px");	
			}
		});
		
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollHead > div.dataTables_scrollHeadInner").each(function(iii) {
			jQuery(this).css("width", divWidthScrollArray[iii]);
		});
		
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollHead > div.dataTables_scrollHeadInner > table.dataTable").each(function(iii) {
			jQuery(this).css("width", divWidthScrollArray[iii]);
		});
		
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollBody").each(function(iii) {
			jQuery(this).css("width", divWidthNormalArray[iii]);
		});
		
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollBody > table.dataTable").each(function(iii) {
			jQuery(this).css("width", divWidthScrollArray[iii]);
		});
		
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollHead > div.dataTables_scrollHeadInner > table.dataTable > thead > tr > th.col-width-auto").each(function() {
			jQuery(this).css("width", "auto");
		});
		
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollBody > table.dataTable > thead > tr > th.col-width-auto").each(function() {
			jQuery(this).css("width", "auto");
		});
		
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollBody > table.dataTable > tbody > tr > td.col-width-auto").each(function() {
			jQuery(this).css("width", "auto");
		});
	}
	
	function restoreBasicWidth(tableId) {
		if (tableId == undefined) {
			tableId = "div";
		} else {
			tableId = "#" + tableId;
		}
		
		restoreBasicWidthPrefixSelector(tableId + ".dataTables_wrapper:not(.FIXWIDTH)");
	}
	
	
	function adjustDatatableWidth(tableId) {
		if (tableId == undefined) {
			tableId = "div";
		} else {
			tableId = "#" + tableId;
		}
		
		adjustDatatableWidthPrefixSelector(tableId + ".dataTables_wrapper:not(.FIXWIDTH)");
	}
	
	function adjustDatatableWidthPrefixSelector(prefixSelector) {
		var tableHeight = jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollBody > table.dataTable");
		var divHeight = jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollBody");
		var divWidthNormalArray = [];
		var divWidthScrollArray = [];
		for (var ii = 0; ii < tableHeight.length; ii ++) {
			var divWidthNormal = 1150;
			var divWidthScroll = 1150;
			//console.info(prefixSelector + " > " + jQuery(tableHeight[ii]).height() + " >= " +  jQuery(divHeight[ii]).height());
			if (jQuery(tableHeight[ii]).height() > jQuery(divHeight[ii]).height()) {
				divWidthScroll = parseInt(divWidthScroll, 10) - 17;
			}
			divWidthNormalArray[ii] = divWidthNormal + "px";
			divWidthScrollArray[ii] = divWidthScroll + "px";
		}

		jQuery(prefixSelector).each(function(iii) {
			jQuery(this).css("width", "1150px");
		});
		
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollHead > div.dataTables_scrollHeadInner").each(function(iii) {
			jQuery(this).css("width", divWidthScrollArray[iii]);
		});
				
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollHead > div.dataTables_scrollHeadInner > table.dataTable").each(function(iii) {
			jQuery(this).css("width", divWidthScrollArray[iii]);
		});
				
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollBody").each(function(iii) {
			jQuery(this).css("width", divWidthNormalArray[iii]);
		});
				
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollBody > table.dataTable").each(function(iii) {
			jQuery(this).css("width", divWidthScrollArray[iii]);
		});
	
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollHead > div.dataTables_scrollHeadInner > table.dataTable > thead > tr > th.col-width-auto").each(function() {
			jQuery(this).css("width", "auto");
		});
			
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollBody > table.dataTable > thead > tr > th.col-width-auto").each(function() {
			jQuery(this).css("width", "auto");
		});
	
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollBody > table.dataTable > tbody > tr > td.col-width-auto").each(function() {
			jQuery(this).css("width", "auto");
		});
	}
	
	function setDisplay1600(divResultId, areaWidth) {
		if (parseInt(jQuery(window).width(), 10) >= 1500) {
			is1600 = true;
		}
	
		if (is1600) {
			if (areaWidth == undefined) {
				areaWidth = 30;
			} else {
				areaWidth = parseInt(areaWidth) + 40;
			}
			
			var divWidth = 1500 - areaWidth;
			
			jQuery("#"+divResultId).css("width", divWidth);
					
			jQuery("#"+divResultId).find("div").not(jQuery("#"+divResultId).find("div[id^='drawEdit']")).css("width",  divWidth);
			jQuery("#"+divResultId).find("div[id^='thumb']").css("width",  "");
			jQuery("#"+divResultId).find("table").css("width", divWidth);

			// jQuery("#"+divResultId).find(".col-width-60px").css("width", "100px");
			
			jQuery("#"+divResultId).css("align", "center");
		}
	}
	
	function initDisplay1600OtherWithDT() {
		if (is1600) {
			jQuery(".WIDTH_1150:not(.NOT1600)").removeClass("WIDTH_1150").addClass("WIDTH_1600");
			var tableHeight = jQuery("div.dataTables_wrapper:not(.NOT1600) > div.dataTables_scroll > div.dataTables_scrollBody > table.dataTable");
			var divHeight = jQuery("div.dataTables_wrapper:not(.NOT1600) > div.dataTables_scroll > div.dataTables_scrollBody");
			var divWidthNormalArray = [];
			var divWidthScrollArray = [];
			for (var ii = 0; ii < tableHeight.length; ii ++) {
				var divWidthNormal = "1500px";
				var divWidthScroll = "1500px";
				//console.info(jQuery(tableHeight[ii]).height() + " >= " +  jQuery(divHeight[ii]).height());
				if (jQuery(tableHeight[ii]).height() > jQuery(divHeight[ii]).height()) {
					divWidthScroll = "1483px";
				}
				divWidthNormalArray[ii] = divWidthNormal;
				divWidthScrollArray[ii] = divWidthScroll;
			}
			
			jQuery("div.dataTables_wrapper:not(.NOT1600)").each(function() {
				jQuery(this).css("margin", "auto").css("float", "").css("width", "1500px");	
			});
			
			jQuery("div.dataTables_wrapper:not(.NOT1600) > div.dataTables_scroll > div.dataTables_scrollHead > div.dataTables_scrollHeadInner").each(function(iii) {
				jQuery(this).css("width", divWidthScrollArray[iii]);
			});
			
			jQuery("div.dataTables_wrapper:not(.NOT1600) > div.dataTables_scroll > div.dataTables_scrollHead > div.dataTables_scrollHeadInner > table.dataTable").each(function(iii) {
				jQuery(this).css("width", divWidthScrollArray[iii]);
			});
			
			jQuery("div.dataTables_wrapper:not(.NOT1600) > div.dataTables_scroll > div.dataTables_scrollBody").each(function(iii) {
				jQuery(this).css("width", divWidthNormalArray[iii]);
			});
			
			jQuery("div.dataTables_wrapper:not(.NOT1600) > div.dataTables_scroll > div.dataTables_scrollBody > table.dataTable").each(function(iii) {
				jQuery(this).css("width", divWidthScrollArray[iii]);
			});
			
			jQuery("div.dataTables_wrapper:not(.NOT1600) > div.dataTables_scroll > div.dataTables_scrollHead > div.dataTables_scrollHeadInner > table.dataTable > thead > tr > th.col-width-auto").each(function() {
				jQuery(this).css("width", "auto");
			});
			
			jQuery("div.dataTables_wrapper:not(.NOT1600) > div.dataTables_scroll > div.dataTables_scrollBody > table.dataTable > thead > tr > th.col-width-auto").each(function() {
				jQuery(this).css("width", "auto");
			});
		}
	}
	
	function manualSetWidth(prefixSelector, fixWidth) {
		var tableHeight = jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollBody > table.dataTable").height();
		var divHeight = jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollBody").height()
		var divWidthNormal = fixWidth;
		var divWidthScroll = fixWidth;
		if (tableHeight > divHeight) {
			divWidthScroll = parseInt(divWidthScroll, 10) - 17;
		}
			
		divWidthNormal = divWidthNormal + "px";
		divWidthScroll = divWidthScroll + "px";

		jQuery(prefixSelector).each(function() {
			jQuery(this).css("margin", "auto").css("float", "").css("width", divWidthNormal);	
		});
			
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollHead > div.dataTables_scrollHeadInner").each(function(iii) {
			jQuery(this).css("width", divWidthScroll);
		});
			
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollHead > div.dataTables_scrollHeadInner > table.dataTable").each(function(iii) {
			jQuery(this).css("width", divWidthScroll);
		});
			
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollBody").each(function(iii) {
			jQuery(this).css("width", divWidthNormal);
		});
			
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollBody > table.dataTable").each(function(iii) {
			jQuery(this).css("width", divWidthScroll);
		});
			
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollHead > div.dataTables_scrollHeadInner > table.dataTable > thead > tr > th.col-width-auto").each(function() {
			jQuery(this).css("width", "auto");
		});
			
		jQuery(prefixSelector + " > div.dataTables_scroll > div.dataTables_scrollBody > table.dataTable > thead > tr > th.col-width-auto").each(function() {
			jQuery(this).css("width", "auto");
		});
	}
	
	function manualSetHeight(tableId, height) {
		var dataTables_scrollBodyH = height
		jQuery("#" + tableId + ".dataTables_wrapper > div.DTFC_ScrollWrapper > div.dataTables_scroll > div.dataTables_scrollBody").height(dataTables_scrollBodyH);
		jQuery("#" + tableId + ".dataTables_wrapper > div.DTFC_ScrollWrapper").height(dataTables_scrollBodyH + 80);
		jQuery("#" + tableId + ".dataTables_wrapper > div.DTFC_ScrollWrapper > div.DTFC_LeftWrapper > div.DTFC_LeftBodyWrapper > div.DTFC_LeftBodyLiner").height(dataTables_scrollBodyH - 17);
		jQuery("#" + tableId + ".dataTables_wrapper > div.DTFC_ScrollWrapper > div.DTFC_LeftWrapper > div.DTFC_LeftBodyWrapper").height(dataTables_scrollBodyH - 30);
		 		
		var tableHeight = jQuery("#" + tableId + ".dataTables_wrapper > div.DTFC_ScrollWrapper > div.dataTables_scroll > div.dataTables_scrollBody  > table.dataTable").height();
		var divHeight = jQuery("#" + tableId + ".dataTables_wrapper > div.DTFC_ScrollWrapper > div.dataTables_scroll > div.dataTables_scrollBody").height();
		//console.info(tableId + " > " + tableHeight + " >= " +  divHeight);
		if (tableHeight >= divHeight) {
			var hhh = jQuery("#" + tableId + ".dataTables_wrapper > div.DTFC_ScrollWrapper > div.dataTables_scroll > div.dataTables_scrollHead > div.dataTables_scrollHeadInner").width();
			jQuery("#" + tableId + ".dataTables_wrapper > div.DTFC_ScrollWrapper > div.dataTables_scroll > div.dataTables_scrollHead > div.dataTables_scrollHeadInner").width(hhh + 17);
		}
	}
	
	// สำหรับดัก error จาก ajax ให้กลับไปที่ url ที่เรียกเข้ามา
	jQuery( document ).ajaxError(function(  jqXHR, textStatus, errorThrown) {
		submitPage(window.location.pathname);
	});
	
	
	//สำหรับแก่ไขขนาดตัวอักษรของ spinner ให้มีขนาดเท่ากับ element อื่นๆ 
    jQuery( window ).load(function() {
    	jQuery("span.ui-spinner").removeClass("ui-widget");	
    });
</script>
<style type="text/css">
	.ui-dialog {
		z-index:103 !important;
	}
	
	.ui-widget-overlay{
		z-index:102;
	}
</style>