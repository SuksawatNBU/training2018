
(function( $, undefined ) {

	$.widget( "ui.input_ipaddress", {
		version: "0.0.1",
		options: {
		    displayModelInput: "none",
		    inputModelId: "",
		    inputIpaddressId: "",
		    readonly: false,
		    requireInput: false
		},

		// the constructor
		_create: function() {
			//console.info('_create');
			this._createInputIpaddressFormat();
			var that = this;
			var input = this.element;
			var lastIndex = jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK']").length - 1;
			
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK']").each(function(index) {
				// สั่งให้ focus ตำแหน่งสุดท้ายของ value
				jQuery(this).change(
					function() {
						jQuery(input).val(that.ipaddressValue());
					}
				);
				
				jQuery(this).focus(
						function() {
							// กำหนดให้ highlight ทั้งหมด
							this.select();
						}
				);
				
				// event การ key ข้อมูล
				jQuery(this).keypress(
						function(e){
							// ให้กด control ได้
							if (e.ctrlKey) {
								return true;
							}

							var unicode = e.which;
							if (unicode.toString().search(/^(0|9|13)$/) != (-1)){
								return true;
							}
							
							// ถ้าเป็น backspace ให้ลบได้ ถ้าลบหมดแล้วให้ข้ามไปยัง element ก่อนหน้า
							var statusBackspace = that._manageBackspace(this, unicode);
							if (statusBackspace == false) {
								return false;
							} else if (statusBackspace == 'clear') {
								return true;
							}
							
							// ถ้าเป็น dot ให้ข้ามไปยัง element ถัดไป
							var statusDot = that._manageDot(this, unicode);
							if (statusDot == false) {
								return false;
							}
							
							if (index != lastIndex) {
								// ตรวจสอบว่าเป็นตัวเลขเท่านั้น
								if (that._checkIntegerPattern(unicode)) {
									if (that._manageIpaddressValue(this, unicode) == false) {
										return false;
									}
									
									// กำหนดให้ focus ที่ตัวหน้า
									//that._setSelectionRange(this, 0, 1);
								} else {
									return false;
								}
							} else {
								// IP address ช่องสุดท้าย
								// ตรวจสอบว่าเป็นตัวเลขเท่านั้น หรือ *
								if (this.value.trim() === "") {
									// เริ่มต้น กรอกได้ทั้ง ตัวเลข และ *
									if (that._checkIntegerPattern(unicode) || unicode.toString() == "42") {
										if (that._manageIpaddressValue(this, unicode) == false) {
											return false;
										}
									} else {
										return false;
									}
								} else if (this.value.trim() === "*") {
									// ค่าปัจจุบันเป็น * กรอกเพิ่มไม่ได้
									return false;
								} else {
									// ค่าปัจจุบันไม่เป็น * กรอก * ไม่ได้ กรอกได้แต่ตัวเลข
									if (that._checkIntegerPattern(unicode)) {
										if (that._manageIpaddressValue(this, unicode) == false) {
											return false;
										}
									} else {
										return false;
									}
								}
							}
							
						}
				);
				
				jQuery(this).keydown(
						function(e){
							
							// ไม่ให้กด delete
							var unicode = e.which;
							if (unicode.toString().search(/^(46)$/) != (-1)){
								return false;
							}
							
							if (that._getInternetExplorerVersion() != -1) {
								// ถ้าเป็น backspace ให้ลบได้ ถ้าลบหมดแล้วให้ข้ามไปยัง element ก่อนหน้า
								var statusBackspace = that._manageBackspace(this, unicode);
								if (statusBackspace == false) {
									return false;
								} else if (statusBackspace == 'clear') {
									return true;
								}
							}
						}
				);
				
			});
		},

		_createInputIpaddressFormat: function() {
			var that = this;
			var parent = this.element.parent();
			var input = this.element;

			this.options.inputModelId = input.attr("id");
			this.options.inputIpaddressId = this.options.inputModelId + "_IPADDRESS";
			input.css('display', this.options.displayModelInput);			

			var inputIpaddressFormatBlock1 = jQuery("<input id='"+ this.options.inputIpaddressId + "_BLOCK_1' type='text' class='input_ipaddress_block ipaddress_block_left un-read-only'/>");
			inputIpaddressFormatBlock1.attr('maxlength', 3);
			
			var inputIpaddressDot1 = jQuery("<input id='"+ this.options.inputIpaddressId + "_DOT_1' type='text' class='input_ipaddress_dot ipaddress_dot un-read-only' value='.' readonly='readonly' tabindex='-1'/>");

			var inputIpaddressFormatBlock2 = jQuery("<input id='"+ this.options.inputIpaddressId + "_BLOCK_2' type='text' class='input_ipaddress_block ipaddress_block_center un-read-only'/>");
			inputIpaddressFormatBlock2.attr('maxlength', 3);
			
			var inputIpaddressDot2 = jQuery("<input id='"+ this.options.inputIpaddressId + "_DOT_2' type='text' class='input_ipaddress_dot ipaddress_dot un-read-only' value='.' readonly='readonly' tabindex='-1'/>");
			
			var inputIpaddressFormatBlock3 = jQuery("<input id='"+ this.options.inputIpaddressId + "_BLOCK_3' type='text' class='input_ipaddress_block ipaddress_block_center un-read-only'/>");
			inputIpaddressFormatBlock3.attr('maxlength', 3);
			
			var inputIpaddressDot3 = jQuery("<input id='"+ this.options.inputIpaddressId + "_DOT_3' type='text' class='input_ipaddress_dot ipaddress_dot un-read-only' value='.' readonly='readonly' tabindex='-1'/>");
			
			var inputIpaddressFormatBlock4 = jQuery("<input id='"+ this.options.inputIpaddressId + "_BLOCK_4' type='text' class='input_ipaddress_block ipaddress_block_right un-read-only'/>");
			inputIpaddressFormatBlock4.attr('maxlength', 3);

			parent.append(inputIpaddressFormatBlock1);
			parent.append(inputIpaddressDot1);
			parent.append(inputIpaddressFormatBlock2);
			parent.append(inputIpaddressDot2);
			parent.append(inputIpaddressFormatBlock3);
			parent.append(inputIpaddressDot3);
			parent.append(inputIpaddressFormatBlock4);
			
			if (that.options.readonly) {
				this.toDisabled();
			}
			
			if (that.options.requireInput) {
				this.toRequireInput();
			}
			
			this.ipaddressValue(jQuery(input).val());
		},

		_setSelectionRange: function(element, selectionStart, selectionEnd) {
			if (element.setSelectionRange) {
				element.focus();
				element.setSelectionRange(selectionStart, selectionEnd);
			} else if (element.createTextRange) {
			    var range = element.createTextRange();
			    range.collapse(true);
			    range.moveEnd('character', selectionEnd);
			    range.moveStart('character', selectionStart);
			    range.select();
			}
		},

		_getCursorPosition: function(element) {
			// get cursor position start hightlight
			var startPosition = 0;
			if (document.selection) {	// IE Support
				element.focus ();
				if (element.createTextRange) {
					var r = document.selection.createRange().duplicate();
					r.moveEnd('character', element.value.length);
					if (r.text == '') {
						startPosition = element.value.length;
					}
					startPosition = element.value.lastIndexOf(r.text);
				} else {
					startPosition = element.selectionStart;
				}
			} else if (element.selectionStart || element.selectionStart == '0') { // Firefox support
				startPosition = element.selectionStart;
			}
			return startPosition;
		},

		_getCursorPositionForInsert: function(element) {
			// get cursor position start hightlight
			var startPosition = 0;
			if (document.selection) {	// IE Support
				element.focus ();
				if (element.createTextRange) {
					var r = document.selection.createRange().duplicate();
					r.moveEnd('character', element.value.length);
					if (r.text == '') {
						startPosition = element.value.length;
					}
					startPosition = element.value.lastIndexOf(r.text);
				} else {
					startPosition = element.selectionStart;
				}
			} else if (element.selectionStart || element.selectionStart == '0') { // Firefox support
				startPosition = element.selectionStart;
			}

			return startPosition;
		},

		_manageBackspace: function(element, unicode) {
			if (unicode.toString() == "8") {
				if (jQuery(element).val().length > 0) {
					return 'clear';
				}
				
				if (jQuery(element).attr('id') == this.options.inputIpaddressId + "_BLOCK_1") {
					// nothing
				} else if (jQuery(element).attr('id') == this.options.inputIpaddressId + "_BLOCK_2") {
					jQuery("#" + this.options.inputIpaddressId + "_BLOCK_1").focus();
				} else if (jQuery(element).attr('id') == this.options.inputIpaddressId + "_BLOCK_3") {
					jQuery("#" + this.options.inputIpaddressId + "_BLOCK_2").focus();
				} else if (jQuery(element).attr('id') == this.options.inputIpaddressId + "_BLOCK_4") {
					jQuery("#" + this.options.inputIpaddressId + "_BLOCK_3").focus();
				}
				return false;	
			}
		},

		_manageDot: function(element, unicode) {
			var inputChar = String.fromCharCode(unicode);
			if (inputChar == ".") {
				var nextElement = undefined;
				if (jQuery(element).attr('id') == this.options.inputIpaddressId + "_BLOCK_1") {
					nextElement = jQuery("#" + this.options.inputIpaddressId + "_BLOCK_2");
				} else if (jQuery(element).attr('id') == this.options.inputIpaddressId + "_BLOCK_2") {
					nextElement = jQuery("#" + this.options.inputIpaddressId + "_BLOCK_3");
				} else if (jQuery(element).attr('id') == this.options.inputIpaddressId + "_BLOCK_3") {
					nextElement = jQuery("#" + this.options.inputIpaddressId + "_BLOCK_4");
				} else if (jQuery(element).attr('id') == this.options.inputIpaddressId + "_BLOCK_4") {
					// nothing
				}
				
				var inputIpaddress = jQuery(element).val();
				if (trim(inputIpaddress).length == 0) {
					inputIpaddress = '';
				} else if(parseInt(inputIpaddress, 10) > 255) {
					inputIpaddress = 255;
				} else {
					inputIpaddress = parseInt(inputIpaddress, 10);
				}
				jQuery(element).val(inputIpaddress);
				
				if (nextElement != undefined) {
					nextElement.focus();
					nextElement.select();
				}
				
				return false;	
			}
		},
		
		_manageIpaddressValue: function(element, unicode) {
			var startCursorPosition = this._getCursorPositionForInsert(element);
			var inputChar =  String.fromCharCode(unicode);
			var inputIpaddress = jQuery(element).val();
			inputIpaddress = inputIpaddress.substring(0, startCursorPosition) + inputChar + inputIpaddress.substring(startCursorPosition + 1, inputIpaddress.length);
			if (inputIpaddress.length == 3) {
				var nextElement = undefined;
				if (jQuery(element).attr('id') == this.options.inputIpaddressId + "_BLOCK_1") {
					nextElement = jQuery("#" + this.options.inputIpaddressId + "_BLOCK_2");
				} else if (jQuery(element).attr('id') == this.options.inputIpaddressId + "_BLOCK_2") {
					nextElement = jQuery("#" + this.options.inputIpaddressId + "_BLOCK_3");
				} else if (jQuery(element).attr('id') == this.options.inputIpaddressId + "_BLOCK_3") {
					nextElement = jQuery("#" + this.options.inputIpaddressId + "_BLOCK_4");
				} else if (jQuery(element).attr('id') == this.options.inputIpaddressId + "_BLOCK_4") {
					// nothing
				}
				
				if(parseInt(inputIpaddress, 10) > 255) {
					inputIpaddress = 255;
				} else {
					inputIpaddress = parseInt(inputIpaddress, 10);
				}				
				jQuery(element).val(inputIpaddress);
				jQuery(element).change();
				
				if (nextElement != undefined) {
					nextElement.focus();
					nextElement.select();
				}
				return false;
			}
			
			return true;
		},
		
		_manageIpaddress: function (element) {
			// value (0-255)
			if (jQuery(element).val() == "") {
				return;
			} else if (parseInt(jQuery(element).val(), 10) > 255) {
				jQuery(element).val(255);
			}
		},

		_checkIntegerPattern: function (unicode) {
			// /^\d*[0-9]?$/;
			var pattern = /^\d*[0-9]?$/;
			return pattern.test(String.fromCharCode(unicode));
		},

		_getInternetExplorerVersion: function () {
			var rv = -1;
			if (navigator.appName == 'Microsoft Internet Explorer') {
				var ua = navigator.userAgent;
			    var re  = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
			    if (re.exec(ua) != null) {
			    	rv = parseFloat( RegExp.$1 );
			    }
			} else if (navigator.appName == 'Netscape') {
				var ua = navigator.userAgent;
				var re  = new RegExp("Trident/.*rv:([0-9]{1,}[\.0-9]{0,})");
				if (re.exec(ua) != null) {
					rv = parseFloat( RegExp.$1 );
				}
			}
			return rv;
		},

		toEnabled: function () {
			jQuery("input[id^='" + this.options.inputIpaddressId + "_DOT']").each(function() {
				jQuery(this).removeClass('read-only-ipaddress');
			});
			
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK']").each(function() {
				jQuery(this).removeClass('read-only-ipaddress');
				jQuery(this).removeAttr('disabled')
			});
		},
		
		toDisabled: function () {
			jQuery("input[id^='" + this.options.inputIpaddressId + "_DOT']").each(function() {
				jQuery(this).addClass('read-only-ipaddress');
			});
			
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK']").each(function() {
				jQuery(this).addClass('read-only-ipaddress');
				jQuery(this).attr('disabled', 'disabled')
			});
		},
		
		toRequireInput: function () {
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_1']").removeClass('requireIpaddress_block_left_select').removeClass('requireInput').addClass('requireInput');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_2']").removeClass('requireIpaddress_block_center_select').removeClass('requireInput').addClass('requireInput');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_3']").removeClass('requireIpaddress_block_center_select').removeClass('requireInput').addClass('requireInput');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_4']").removeClass('requireIpaddress_block_right_select').removeClass('requireInput').addClass('requireInput');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_DOT_']").each(function() {
				jQuery(this).removeClass('requireIpaddress_dot_select').removeClass('requireInput').addClass('requireInput');
			});
		},
		
		toRequireSelect: function () {
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_1']").removeClass('requireInput').removeClass('requireIpaddress_block_left_select').addClass('requireIpaddress_block_left_select');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_2']").removeClass('requireInput').removeClass('requireIpaddress_block_center_select').addClass('requireIpaddress_block_center_select');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_3']").removeClass('requireInput').removeClass('requireIpaddress_block_center_select').addClass('requireIpaddress_block_center_select');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_4']").removeClass('requireInput').removeClass('requireIpaddress_block_right_select').addClass('requireIpaddress_block_right_select');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_DOT_']").each(function() {
				jQuery(this).removeClass('requireInput').removeClass('requireIpaddress_dot_select').addClass('requireIpaddress_dot_select');	
			});
		},
		
		toNormalInput: function () {
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_1']").removeClass('requireIpaddress_block_left_select').removeClass('requireInput');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_2']").removeClass('requireIpaddress_block_center_select').removeClass('requireInput');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_3']").removeClass('requireIpaddress_block_center_select').removeClass('requireInput');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_4']").removeClass('requireIpaddress_block_right_select').removeClass('requireInput');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_DOT_']").each(function() {
				jQuery(this).removeClass('requireIpaddress_dot_select').removeClass('requireInput');	
			});
		},
		
		toRequireSelectFormat: function () {
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_1']").removeClass('input_ipaddress_block').removeClass('requireIpaddressFormat_block_left_select').addClass('requireIpaddressFormat_block_left_select');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_2']").removeClass('input_ipaddress_block').removeClass('requireIpaddressFormat_block_center_select').addClass('requireIpaddressFormat_block_center_select');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_3']").removeClass('input_ipaddress_block').removeClass('requireIpaddressFormat_block_center_select').addClass('requireIpaddressFormat_block_center_select');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_4']").removeClass('input_ipaddress_block').removeClass('requireIpaddressFormat_block_right_select').addClass('requireIpaddressFormat_block_right_select');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_DOT_']").each(function() {
				jQuery(this).removeClass('input_ipaddress_dot').removeClass('requireIpaddressFormat_dot_select').addClass('requireIpaddressFormat_dot_select');	
			});
		},
		
		toRequireFormat: function () {
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_1']").removeClass('requireIpaddressFormat_block_left_select').removeClass('input_ipaddress_block').addClass('input_ipaddress_block');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_2']").removeClass('requireIpaddressFormat_block_center_select').removeClass('input_ipaddress_block').addClass('input_ipaddress_block');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_3']").removeClass('requireIpaddressFormat_block_center_select').removeClass('input_ipaddress_block').addClass('input_ipaddress_block');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_4']").removeClass('requireIpaddressFormat_block_right_select').removeClass('input_ipaddress_block').addClass('input_ipaddress_block');
			jQuery("input[id^='" + this.options.inputIpaddressId + "_DOT_']").each(function() {
				jQuery(this).removeClass('requireIpaddressFormat_dot_select').removeClass('input_ipaddress_dot').addClass('input_ipaddress_dot');	
			});
		},
		
		validateIpaddressValue: function() {
			var status = true;
			if ((jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_1']") == '') 
					&& (jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_2']") == '')
					&& (jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_3']") == '')
					&& (jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK_4']") == '')) {
				status = true;
			} else {
				jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK']").each(function() {
					var ipaddressValue = jQuery(this).val();
					if (ipaddressValue == '') {
						status = jQuery(this);
						return false;
					} else if (parseInt(ipaddressValue) > 255) {
						status = jQuery(this);
						return false;
					}
				});
			}
			return status;
		},
		
		ipaddressValue: function (newValue) {

			if (newValue == undefined) {
				// get value
				var ipaddress = "";
				jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK']").each(function(index) {
					if ((index >= 1) && (index <= 3)) {
						ipaddress += ".";
					}
					ipaddress += jQuery(this).val();
				});
				
				if (ipaddress == '...') {
					ipaddress = "";
				}
				return ipaddress;
			} else {
				// set value
				if (newValue == "") {
					jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK']").each(function() {
						jQuery(this).val('');	
					});
					jQuery(this.element).val('');
				} else {
					var newValueArray = newValue.split('.');
					if (newValueArray.length == 4) {
						jQuery("input[id^='" + this.options.inputIpaddressId + "_BLOCK']").each(function(index) {
							var newValueInt = parseInt(newValueArray[index], 10);
							if (newValueInt > 255) {
								newValueInt = 255;
							}
							jQuery(this).val(newValueInt);
						});
						jQuery(this.element).val(this.ipaddressValue());
					}
				}
			}
		}
	});

})( jQuery );
