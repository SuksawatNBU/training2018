
(function( $, undefined ) {

	$.widget( "ui.input_timeformat", {
		version: "0.0.1",
		options: {
			mask: "hh:mm",
			maskPattern: "_",
			placeholder: "HH:MM",
			placeholderPattern: "HH:MM",
			indexHourBegin: "",
			indexHourEnd: "",
			indexMinuteBegin: "",
			indexMinuteEnd: "",
			indexDelimiters: "",
			cssClass: "",
		    cssStyle: "",
		    timeformat: "hh:mm",
		    displayModelInput: "none",
		    inputModelId: "",
		    inputTimeId: "",
		    readonly: false,
			selectTimeRange: {
				timeFrom: undefined,
				timeTo: undefined
			},
			selectDateRange: {
				dateFrom: undefined,
				dateTo: undefined
			}
		},

		// the constructor
		_create: function() {
			this._createInputTimeIndex();
			this._createInputTimeMask();
			this._createInputTimePlaceholder();
			this.element = this._createInputTimeFormat();
			//this._createInputTimeRemark();
			var that = this;
			var input = this.element;
			
			input.click(
				function(){
					var inputHH = that._getHour(this.value);
					var inputMM = that._getMinute(this.value);
					var startCursorPosition = that._getCursorPosition(this);
					//console.info(inputHH + " - " + inputMM);
					if ((inputHH.length == 0) && (inputMM.length == 0)) {
						startCursorPosition = 0;
					} else {
						if ((startCursorPosition >= that.options.indexHourBegin) && (startCursorPosition <= that.options.indexHourEnd)) {
							//console.info(inputHH);
							if (inputHH.length == 0) {
								startCursorPosition = that.options.indexHourBegin;
							} else {
								startCursorPosition = that.options.indexHourEnd;
							}


						} else if ((startCursorPosition >= that.options.indexMinuteBegin) && (startCursorPosition <= that.options.indexMinuteEnd)) {
							//console.info(inputMM);
							if (inputMM.length == 0) {
								startCursorPosition = that.options.indexMinuteBegin;
							} else {
								startCursorPosition = that.options.indexMinuteEnd;
							}
						}
					}

					that._setSelectionRange(this, startCursorPosition, startCursorPosition);
				}
			);
			input.keypress(
				function(e){

					// ให้กด control ได้
					if (e.ctrlKey) {
						return true;
					}

					var unicode = e.which;
					if (unicode.toString().search(/^(0|9|13)$/) != (-1)){
						return true;
					}

					// ถ้าเป็น backspace ให้เปลื่ยน value เป็น _
					if (that._manageBackspace(this, unicode) == false) {
						return false;
					}

					// ตรวจสอบว่าเป็นตัวเลขเท่านั้น
					if (that._checkIntegerPattern(unicode)) {

						if (that._manageTimeValue(this, unicode) == false) {
							return false;
						}

						var startCursorPosition = that._getCursorPositionForInsert(this);
						//console.info('startCursorPosition-1: ' + startCursorPosition);
						// เลือกตำแหน่งให้ตัวเลขที่กรอกใหม่ลงไปแทน
						that._setSelectionRange(this, startCursorPosition, startCursorPosition + 1);

					} else {
						return false;
					}
				}
			);
			input.keydown(
				function(e){
					// ไม่ให้กด delete
					var unicode = e.which;
					if (unicode.toString().search(/^(46)$/) != (-1)){
						return false;
					}

					if (that._getInternetExplorerVersion() != -1) {
						// ถ้าเป็น backspace ให้เปลื่ยน value เป็น _
						if (that._manageBackspace(this, unicode) == false) {
							return false;
						}
					}
				}
			);

			// TODO: YU Begin
			input.focus(
				function(e) {
					//console.info('focus');
					jQuery(this).removeAttr('placeholder');
					if (this.value == '') {
						this.value = that.options.mask;	
					}
				}
			);
			
			input.blur(
				function(e) {
					//console.info('blur');
					//console.info(this.value);
					if (this.value == that.options.mask) {
						this.value = "";
						jQuery(this).attr('placeholder', that.options.placeholder);	
					}
				}
			);
			// TODO: YU End
			
			var defaultValue = jQuery("#" + this.options.inputModelId).val();
			if ((defaultValue != undefined) && (defaultValue != "")) {
				this.timeValue(defaultValue);
			}
			
		},

		_createInputTimeIndex: function() {
			var hourBegin = "";
			var hourEnd = "";
			var minuteBegin = "";
			var minuteEnd = "";
			var delimiters = "";
			var format = this._getTimeformat().split('');
			//console.info(format);
			for (var i = 0; i < format.length; i++) {
				//console.info(i + ": " + format[i]);
				if (format[i] == 'h') {
					if (hourBegin === "") {
						hourBegin = i + "";
					} else {
						hourEnd = (i + 1) + "";
					}
				} else if (format[i] == 'm') {
					if (minuteBegin === "") {
						minuteBegin = i + "";
					} else {
						minuteEnd = (i + 1) + "";
					}
				} else if (format[i] == ':') {
					if (delimiters !== "") {
						delimiters += ",";
					}
					delimiters += (i + "");
				}
			}
			//console.info(dayBegin + "," + dayEnd);
			if ((hourBegin != "") && (hourEnd != "")) {
				this.options.indexHourBegin = parseInt(hourBegin, 10);
				this.options.indexHourEnd = parseInt(hourEnd, 10);
			}

			if ((minuteBegin != "") && (minuteEnd != "")) {
				this.options.indexMinuteBegin = parseInt(minuteBegin, 10);
				this.options.indexMinuteEnd = parseInt(minuteEnd, 10);
			}

			if (delimiters != "") {
				this.options.indexDelimiters = delimiters;
			}
		},

		_createInputTimeMask: function() {
			var maskformat = this._getTimeformat();
			//console.info('maskformat: ' + this.options.dateformat);
			//console.info(this.options.maskPattern);
			if (this.options.maskPattern == "hm") {
				maskformat = maskformat.replace(/h/g,'h');
				maskformat = maskformat.replace(/m/g,'m');
			} else if (this.options.maskPattern == "DMY") {
				maskformat = maskformat.replace(/h/g,'H');
				maskformat = maskformat.replace(/m/g,'M');
			} else {
				maskformat = maskformat.replace(/h/g,this.options.maskPattern);
				maskformat = maskformat.replace(/m/g,this.options.maskPattern);
			}
			this.options.mask = maskformat;
		},

		// TODO: YU Begin
		_createInputTimePlaceholder: function() {
			var maskformat = this._getTimeformat();
			//placeholderPattern
			//console.info(this.options.placeholderPattern);
			//console.info(maskformat);
			
			if (this.options.placeholderPattern == "HH:MM") {
				maskformat = maskformat.replace(/h/g,'H');
				maskformat = maskformat.replace(/m/g,'M');
			} else {
				maskformat = maskformat.replace(/h/g,this.options.placeholderPattern);
				maskformat = maskformat.replace(/m/g,this.options.placeholderPattern);
			}
			this.options.placeholder = maskformat;
		}
		// TODO: YU End
		,
		_createInputTimeRemark: function () {
			var format = this._getTimeformat().toUpperCase();
			// var comment = jQuery("<div><font class='comment'>" + timeConfig.timeFormat[format]  + "</font></div");
			var comment = jQuery("<font class='comment'>   " + timeConfig.timeFormat[format]  + "</font>");
			comment.css('width', this.element.css('width'));
			comment.css('textAlign', 'center');
			this.element.parent().append(comment);
		},

		_createInputTimeFormat: function() {
			var that = this;
			var parent = this.element.parent();
			var input = this.element;
			if (input.attr("class") != undefined) {
				this.options.cssClass = input.attr("class");
			}

			if (input.attr("style") != undefined) {
				this.options.cssStyle = input.attr("style");
			}

			if ((input.attr("disabled") != undefined) && (input.attr("disabled") != "")) {
				this.options.readonly = true;
			}

			if ((input.attr("readonly") != undefined) && (input.attr("readonly") != "")) {
				this.options.readonly = true;
			}

			this.options.inputModelId = input.attr("id");
			this.options.inputTimeId = this.options.inputModelId + "_" + this.options.timeformat;
			input.attr("class", "");
			input.attr("style", "");
			input.css('display', this.options.displayModelInput);
			
			var inputTimeFormat = jQuery("<input  id='"+ this.options.inputTimeId + "' type='text' class='" + this.options.cssClass + "' style='" + this.options.cssStyle + "'/>");
			if(input.val() != ""){
				inputTimeFormat.val(input.val());
			}else{
				inputTimeFormat.val(this.options.mask);
			}
			
			inputTimeFormat.attr('maxlength', inputTimeFormat.val().length);
			inputTimeFormat.addClass('input_timeformat_' + this.options.timeformat);

			if (this.options.readonly) {
				inputTimeFormat.attr('readonly', this.options.readonly);
			}

			inputTimeFormat.change(function(){
				//console.info('change: ' + that.timeValue());
				jQuery(this).removeClass("requireTimeformat_" + that.options.timeformat + "_select").removeClass("input_timeformat_" + that.options.timeformat).addClass("input_timeformat_" + that.options.timeformat);
				jQuery("#" + that.options.inputModelId).val(that.timeValue());

				that._initTimeRange();
			});


			// TODO: YU Begin
			jQuery(inputTimeFormat).attr('placeholder', this.options.placeholder);
			if (inputTimeFormat.val() == this.options.mask) {
				inputTimeFormat.val("");	
			}
			// TODO: YU End
			
			parent.append(inputTimeFormat);
			return inputTimeFormat;
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

		_getTimeformat: function() {
			return this.options.timeformat.replace(/_cl_/g,':');
		},

		_getInputTimeValueFromElement: function(element) {
			return this._getInputTimeValueFromValue(element.value);
		},

		_getInputTimeValueFromValue: function(value) {
			for (var i = 0; i < 2; i++) {
				value = value.replace(this.options.maskPattern,'');
			}
			return value.replace(/\/|_/g,'').replace(/h|H|m|M/g,'');
		},

		_getInputTimeCursorPosition: function(element) {
			var inputTimeValue = this._getInputTimeValueFromElement(element);
			var inputTimeLength = (inputTimeValue + "").length;
			var inputTimeCursorPosition = inputTimeLength;

			if (inputTimeLength >= 2) {
				inputTimeCursorPosition++;
			}
			return inputTimeCursorPosition;
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

			if (this._isDilimenter(startPosition)) {
				startPosition++;
			}

			return startPosition;
		},

		_manageBackspace: function(element, unicode) {

			if (unicode.toString() == "8") {
				jQuery("#" + this.options.inputModelId).val("");

				var startCursorPosition = this._getCursorPosition(element);

				if (startCursorPosition == 0) {
					return false;
				}

				if (this._isDilimenter(startCursorPosition - 1)) {
					startCursorPosition--;
				}

				//console.info(startCursorPosition);
				if ((startCursorPosition >= this.options.indexHourBegin) && (startCursorPosition <= this.options.indexHourEnd)) {

					this._clearHour();
					startCursorPosition = this.options.indexHourBegin;

				} else if ((startCursorPosition >= this.options.indexMinuteBegin) && (startCursorPosition <= this.options.indexMinuteEnd)) {

					this._clearMinute();
					startCursorPosition = this.options.indexMinuteBegin;

				}
				//console.info(newInputTimeValue);

				this._setSelectionRange(element, startCursorPosition, startCursorPosition);
				
				if (jQuery(element).val() == this.options.mask) {
					jQuery(element).attr('placeholder', this.options.placeholder);	
				}
				
				return false;
			}
		},

		_manageTimeValue: function(element, unicode) {
			//console.info('_manageTimeValue');
			var startCursorPosition = this._getCursorPositionForInsert(element);
			var inputTimeValue = element.value;
			var inputHH = inputTimeValue;
			var inputMM = inputTimeValue;
			var inputChar =  String.fromCharCode(unicode);
			//console.info('startCursorPosition: ' + startCursorPosition);
			//console.info(startCursorPosition + " > " + inputChar + "[" + inputHH.substring(this.options.indexHourBegin, this.options.indexHourEnd) + "/" + inputMM.substring(this.options.indexMinuteBegin, this.options.indexMinuteEnd) + "]");
			//console.info("[" + this.options.indexHourBegin + "] > [" + this.options.indexHourEnd + "]");
			if ((this.options.indexHourBegin !== "") && (this.options.indexHourEnd !== "") && (startCursorPosition >= this.options.indexHourBegin) && (startCursorPosition <= this.options.indexHourEnd)) {
				inputHH = inputHH.substring(this.options.indexHourBegin, startCursorPosition) + inputChar + inputHH.substring(startCursorPosition + 1, this.options.indexHourEnd);
				inputHH = this._getInputTimeValueFromValue(inputHH);
				//console.info('inputHH: ' + inputHH);
				if (inputHH.length == 1) {
					if ((parseInt(inputHH, 10) < 0) || (parseInt(inputHH, 10) > 2)) {
						return false;
					}
				} else if (inputHH.length == 2) {
					if ((parseInt(inputHH, 10) < 0) || (parseInt(inputHH, 10) > 23)) {
						return false;
					}
				}
			} else if ((this.options.indexMinuteBegin !== "") && (this.options.indexMinuteEnd !== "") && (startCursorPosition >= this.options.indexMinuteBegin) && (startCursorPosition <= this.options.indexMinuteEnd)) {
				inputMM = inputMM.substring(this.options.indexMinuteBegin, startCursorPosition) + inputChar + inputMM.substring(startCursorPosition + 1, this.options.indexMinuteEnd);
				inputMM = this._getInputTimeValueFromValue(inputMM);
				//console.info('inputMM: ' + inputMM);
				if (inputMM.length == 1) {
					if ((parseInt(inputMM, 10) < 0) || (parseInt(inputMM, 10) > 5)) {
						return false;
					}
				} else if (inputMM.length == 2) {
					if ((parseInt(inputMM, 10) < 0) || (parseInt(inputMM, 10) > 59)) {
						return false;
					}
				}
			} else {
				return false;
			}
			return true;
		},

		_checkIntegerPattern: function (unicode) {
			// /^\d*[0-9]?$/;
			var pattern = /^\d*[0-9]?$/;
			return pattern.test(String.fromCharCode(unicode));
		},

		_isDilimenter: function (indexOfCursor) {
			var found = false;
			var indexDelimiters = this.options.indexDelimiters.split(",");
			for (var i_delimiter = 0; i_delimiter < indexDelimiters.length; i_delimiter++) {
				if (parseInt(indexDelimiters[i_delimiter], 10) == indexOfCursor) {
					found = true;
					break;
				}
			}
			return found;
		},

		_getHour: function (inputTimeValue) {
			//console.info("hh: " + this.options.indexHourBegin + " - " + this.options.indexHourEnd);
			if ((this.options.indexHourBegin === "") || (this.options.indexHourEnd === "")) {
				return "";
			} else {
				return this._getInputTimeValueFromValue(inputTimeValue.substring(this.options.indexHourBegin, this.options.indexHourEnd));
			}
		},

		_getMinute: function (inputTimeValue) {
			//console.info("mm: " + this.options.indexMinuteBegin + " - " + this.options.indexMinuteEnd);
			if ((this.options.indexMinuteBegin === "") || (this.options.indexMinuteEnd === "")) {
				return "";
			} else {
				return this._getInputTimeValueFromValue(inputTimeValue.substring(this.options.indexMinuteBegin, this.options.indexMinuteEnd));
			}
		},

		_setHour: function (input) {
			if ((this.options.indexHourBegin !== "") && (this.options.indexHourEnd !== "")) {
				var newValue = this.element.val().substring(0, this.options.indexHourBegin);
				newValue += input;
				newValue += this.element.val().substring(this.options.indexHourEnd, this.element.val().length);
				this.element.val(newValue);
			}
		},

		_setMinute: function (input) {
			if ((this.options.indexMinuteBegin !== "") && (this.options.indexMinuteEnd !== "")) {
				var newValue = this.element.val().substring(0, this.options.indexMinuteBegin);
				newValue += input;
				newValue += this.element.val().substring(this.options.indexMinuteEnd, this.element.val().length);
				this.element.val(newValue);
			}
		},

		_clearHour: function () {
			if (this.options.maskPattern == "hm") {
				this._setHour("hh");
			} else if (this.options.maskPattern == "HM") {
				this._setHour("HH");
			} else {
				this._setHour(this.options.maskPattern + this.options.maskPattern);
			}
		},

		_clearMinute: function () {
			if (this.options.maskPattern == "hm") {
				this._setMinute("mm");
			} else if (this.options.maskPattern == "HM") {
				this._setMinute("MM");
			} else {
				this._setMinute(this.options.maskPattern + this.options.maskPattern);
			}
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

		_initTimeRange: function () {
			var selectedTime = null;

			if (this.timeValue() != "") {
				selectedTime = this.timeValue();
			}

			//console.info('selectedTime: ' + selectedTime);
			if ((this.options.selectDateRange.dateTo != undefined) && (this.options.selectDateRange.dateFrom != undefined)) {
				var range = conpareDateFormatById(this.options.selectDateRange.dateFrom, this.options.selectDateRange.dateTo);
				//console.info(range);
				if ((range != undefined) && (range.day == 0)) {
					if ((this.options.selectTimeRange.timeTo != undefined) && (this.options.selectTimeRange.timeFrom == undefined)) {
						if (selectedTime != null) {
							var timeValueTo = jQuery( "#" + this.options.selectTimeRange.timeTo).input_timeformat( "timeValue");
							if ((timeValueTo != "") && (selectedTime > timeValueTo)) {
								jQuery( "#" + this.options.selectTimeRange.timeTo).input_timeformat( "timeValue", selectedTime );
							}
						}
					} else if ((this.options.selectTimeRange.timeTo == undefined) && (this.options.selectTimeRange.timeFrom != undefined)) {
						if (selectedTime != null) {
							var timeValueFrom = jQuery( "#" + this.options.selectTimeRange.timeFrom).input_timeformat( "timeValue");
							if ((timeValueFrom != "") && (timeValueFrom > selectedTime)) {
								jQuery( "#" + this.options.selectTimeRange.timeFrom).input_timeformat( "timeValue", selectedTime );
							}
						}
					}
				}
			} else {
				if ((this.options.selectTimeRange.timeTo != undefined) && (this.options.selectTimeRange.timeFrom == undefined)) {
					//console.info("to --------------------------------");
					if (selectedTime != null) {
						var timeValueTo = jQuery( "#" + this.options.selectTimeRange.timeTo).input_timeformat( "timeValue");
						//console.info(selectedTime + " > " + timeValueTo);

						if ((timeValueTo != "") && (selectedTime > timeValueTo)) {
							jQuery( "#" + this.options.selectTimeRange.timeTo).input_timeformat( "timeValue", selectedTime );
						}
					}
					//console.info("to --------------------------------");
				} else if ((this.options.selectTimeRange.timeTo == undefined) && (this.options.selectTimeRange.timeFrom != undefined)) {
					//console.info("from --------------------------------");
					if (selectedTime != null) {
						var timeValueFrom = jQuery( "#" + this.options.selectTimeRange.timeFrom).input_timeformat( "timeValue");
						//console.info(timeValueFrom + " > " + selectedTime);

						if ((timeValueFrom != "") && (timeValueFrom > selectedTime)) {
							jQuery( "#" + this.options.selectTimeRange.timeFrom).input_timeformat( "timeValue", selectedTime );
						}
					}
					//console.info("from --------------------------------");
				}
			}
		},

		timeValue: function (newValue) {

			if (newValue == undefined) {
				// get value
				if (this.element.val() == this.options.mask) {
					return "";
				} else {
					//console.info(document.getElementById(this.options.inputTimeId));
					//console.info(inputValidateDateFormatValueName(document.getElementById(this.options.inputTimeId)));
					if (inputValidateTimeFormatValueName(document.getElementById(this.options.inputTimeId)) == 'ok') {
						var newValueOther = "";
						if (this._getHour(this.element.val()) != "") {
							newValueOther += this._getHour(this.element.val()) + ":";
						}

						if (this._getMinute(this.element.val()) != "") {
							newValueOther += this._getMinute(this.element.val());
						}
						return newValueOther;
					} else {
						return "";
					}
				}
			} else {
				// set value
				if (newValue == "") {
					this.element.val(this.options.mask);
				} else {
					var inputHH = "";
					var inputMM = "";
					if (newValue.length == 5) {
						inputHH = newValue.substring(0, 2);
						inputMM = newValue.substring(3, 5);
					}

					this._setHour(inputHH);
					this._setMinute(inputMM);

					jQuery("#" + this.options.inputModelId).val(this.timeValue());
				}
			}
		},

		clearValue: function() {
			this._clearHour();
			this._clearMinute();
		}
	});

})( jQuery );

function inputValidateTimeFormatValueName(el) {
	var hourValue = "";
	var minuteValue = "";
	var el_time = el;
	var tempValue = trim(el_time.value);
	var className = el_time.className;
	//console.info('className: ' + className);
	if (className.indexOf('input_timeformat_hh_cl_mm') > -1) {
		if (tempValue == '__:__') {
			return 'ok';
		}
		if (tempValue.length >= 2) {
			hourValue = tempValue.substring(0, 2);
		}
		if (tempValue.length == 5) {
			minuteValue = tempValue.substring(3, 5);
		}
	}

	var chk = "";
	var isHour = false;
	var isMinute = false;

	var h = 0;
	if (hourValue == undefined) {
		isHour = false;
	} else if (hourValue.indexOf('_') > -1) {
		isHour = false;
	} else {
		if (hourValue.length > 0) {
			h = parseInt(hourValue, 10);
			if ((h >= 0) && h <= 23) {
				isHour = true;
			}
		}
	}

	var m = 0;
	if (minuteValue == undefined) {
		isMinute = false;
	} else if (minuteValue.indexOf('_') > -1) {
		isMinute = false;
	} else {
		if (minuteValue.length > 0) {
			m = parseInt(minuteValue, 10);
			if ((m >= 0) && m <= 59) {
				isMinute = true;
			}
		}
	}

	//console.info('isHour:' + hourValue + ", isMinute: " + minuteValue);
	//console.info('isHour:' + isHour + ", isMinute: " + isMinute);
	if (tempValue.length == 0) {
		chk = "empty";
	} else if(!isHour && !isMinute){
		chk = "error all";
	} else if (isHour == false) {
		chk = 'not hour';
	} else if (isMinute == false) {
		chk = 'not minute';
	} else {
		chk = 'ok';
	}
	return chk;
}

function conpareTimeFormatById(elIdFrom, elIdTo) {
	var timeFrom = jQuery("#" + elIdFrom).input_timeformat('timeValue');
	var timeTo = jQuery("#" + elIdTo).input_timeformat('timeValue');
	//console.info(intFrom + " = " + intTo);

	var hFrom = 0;
	var hTo = 0;
	var mFrom = 0;
	var mTo = 0;

	if (((timeFrom + "").length == 5) && ((timeTo + "").length == 5)){
		if (timeFrom == timeTo) {
			return 0;
		} else {
			hFrom = parseInt((timeFrom + "").substring(0, 2), 10);
			hTo = parseInt((timeTo + "").substring(0, 2), 10);
			mFrom = parseInt((timeFrom + "").substring(3, 5), 10);
			mTo = parseInt((timeTo + "").substring(3, 5), 10);

			var hh = hFrom - hTo;
			var mm = mFrom - mTo;
			return ((hh * 60) + mm);
		}
	}
}

function conpareTimeFormatWith2400(elId) {
	var timeFrom = jQuery("#" + elId).input_timeformat('timeValue');
	var timeTo = "24:00";
	//console.info(intFrom + " = " + intTo);

	var hFrom = 0;
	var hTo = 0;
	var mFrom = 0;
	var mTo = 0;

	if (((timeFrom + "").length == 5) && ((timeTo + "").length == 5)){
		if (timeFrom == timeTo) {
			return 0;
		} else {
			hFrom = parseInt((timeFrom + "").substring(0, 2), 10);
			hTo = parseInt((timeTo + "").substring(0, 2), 10);
			mFrom = parseInt((timeFrom + "").substring(3, 5), 10);
			mTo = parseInt((timeTo + "").substring(3, 5), 10);

			var hh = hFrom - hTo;
			var mm = mFrom - mTo;
			return ((hh * 60) + mm);
		}
	}
}

function conpareTimeFormatWith0000(elId) {
	var timeFrom = "00:00";
	var timeTo = jQuery("#" + elId).input_timeformat('timeValue');
	//console.info(intFrom + " = " + intTo);

	var hFrom = 0;
	var hTo = 0;
	var mFrom = 0;
	var mTo = 0;

	if (((timeFrom + "").length == 5) && ((timeTo + "").length == 5)){
		if (timeFrom == timeTo) {
			return 0;
		} else {
			hFrom = parseInt((timeFrom + "").substring(0, 2), 10);
			hTo = parseInt((timeTo + "").substring(0, 2), 10);
			mFrom = parseInt((timeFrom + "").substring(3, 5), 10);
			mTo = parseInt((timeTo + "").substring(3, 5), 10);

			var hh = hFrom - hTo;
			var mm = mFrom - mTo;
			return ((hh * 60) + mm);
		}
	}
}

function conpareTimeFormatWithDateFormatById(elTimeIdFrom, elTimeIdTo, elDateIdFrom, elDateIdTo) {

	// 1 Day = 24 Hr = 1440 Minutes
	var dayCheck = conpareDateFormatById(elDateIdFrom, elDateIdTo);
	var timeCheck = conpareTimeFormatById(elTimeIdFrom, elTimeIdTo);

	if ((dayCheck == undefined) && (timeCheck == undefined)) {
		return undefined;
	} else if ((dayCheck == undefined) && (timeCheck != undefined)) {
		dayCheck = new Object();
		dayCheck.minutes = timeCheck;
	} else if ((dayCheck != undefined) && (timeCheck == undefined)) {
		// not thing
	} else if ((dayCheck != undefined) && (timeCheck != undefined)) {
		var tmpDay = parseInt(dayCheck.day, 10);
		if (timeCheck > 0) {
			//console.info(tmpDay);
			if (tmpDay > 0) {
				tmpDay = tmpDay - 1;
			} else if (tmpDay < 0) {
				tmpDay = tmpDay + 1;
			}

			//(timeFrom > 23:59) + (00:00 > timeTO)
			var timeFrom = conpareTimeFormatWith2400(elTimeIdFrom);
			var timeTo = conpareTimeFormatWith0000(elTimeIdTo);

			//console.info(timeFrom);
			//console.info(timeTo);
			timeCheck = timeFrom + timeTo;
		} else if (timeCheck < 0) {

		}
		dayCheck.day = tmpDay;
		dayCheck.minutes = timeCheck;



	}
	return dayCheck;
}
