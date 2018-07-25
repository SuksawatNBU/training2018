
(function( $, undefined ) {

	$.widget( "ui.input_dateformat", {
		version: "0.0.1",
		options: {
			mask: "dd/mm/yyyy",
			maskPattern: "_",
			// TODO: TAO Begin
			placeholder: "dd/mm/yyyy",
			placeholderPattern: "DMY",
			// TODO: TAO End
			indexDayBegin: "",
			indexDayEnd: "",
			indexMonthBegin: "",
			indexMonthEnd: "",
			indexYearBegin: "",
			indexYearEnd: "",
			indexDelimiters: "",
			cssClass: "",
		    cssStyle: "",
		    dateformat: "dd_sl_mm_sl_yyyy",
		    displayModelInput: "none",
		    inputModelId: "",
		    inputDateId: "",
		    readonly: false,
		    inputdatepicker: true,
		    inputdatepickerDateFormat: "dd/mm/yy",
		    inputdatepickerShowOn: false,
		    inputdatepickerButtonImage: "js/datepicker/image/cal.gif",
		    inputdatepickerButtonImageOnly: true,
			inputdatepickerChangeMonth: true,
			inputdatepickerChangeYear: true,
			inputdatepickerDisabled: false,
			selectDateRange: {
				numberOfMonths: 2,
				dateFrom: undefined,
				dateTo: undefined
			},
			selectTimeRange: {
				timeFrom: undefined,
				timeTo: undefined
			}
		},

		// the constructor
		_create: function() {
			//console.info('_create');
			this._createInputDateIndex();
			this._createInputDateMask();
			// TODO: TAO Begin
			this._createInputDatePlaceholder();
			// TODO: TAO End
			//console.info(this.options);
			this.element = this._createInputDateFormat();
			//this._createInputDateRemark();
			var that = this;
			var input = this.element;

			input.click(
				function(){
					
					
					
					var inputDD = that._getDay(this.value);
					var inputMM = that._getMonth(this.value);
					var inputYYYY = that._getYear(this.value);

					var startCursorPosition = that._getCursorPosition(this);
					//console.info(inputDD + " - " + inputMM + " - " + inputYYYY);
					if ((inputDD.length == 0) && (inputMM.length == 0) && (inputYYYY.length == 0)) {
						startCursorPosition = 0;
					} else {
						if ((startCursorPosition >= that.options.indexDayBegin) && (startCursorPosition <= that.options.indexDayEnd)) {
							//console.info(inputDD);
							if (inputDD.length == 0) {
								startCursorPosition = that.options.indexDayBegin;
							} else {
								startCursorPosition = that.options.indexDayEnd;
							}


						} else if ((startCursorPosition >= that.options.indexMonthBegin) && (startCursorPosition <= that.options.indexMonthEnd)) {
							//console.info(inputMM);
							if (inputMM.length == 0) {
								startCursorPosition = that.options.indexMonthBegin;
							} else {
								startCursorPosition = that.options.indexMonthEnd;
							}


						} else if ((startCursorPosition >= that.options.indexYearBegin) && (startCursorPosition <= that.options.indexYearEnd)) {
							//console.info(inputYYYY);
							if (inputYYYY.length == 0) {
								startCursorPosition = that.options.indexYearBegin;
							} else {
								startCursorPosition = that.options.indexYearEnd;
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

						if (that._manageDateValue(this, unicode) == false) {
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

					//alert(that._getInternetExplorerVersion())
					if (that._getInternetExplorerVersion() != -1) {
						// ถ้าเป็น backspace ให้เปลื่ยน value เป็น _
						if (that._manageBackspace(this, unicode) == false) {
							return false;
						}
					}
				}
			);
			
			// TODO: TAO Begin
			input.focus(
				function(e) {
					//console.info('focus');
					jQuery(this).removeAttr('placeholder');
					if (this.value == '') {
						this.value = that.options.mask; 
						jQuery(this).click();
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
			// TODO: TAO End


			var defaultValue = jQuery("#" + this.options.inputModelId).val();
			if ((defaultValue != undefined) && (defaultValue != "")) {
				this.dateValue(defaultValue);
			}

			if ((this.options.dateformat == "mm_sl_yyyy")
					|| (this.options.dateformat == "yyyy")
					|| (this.options.dateformat == "yyyy_sl_mm")) {
				//mm_sl_yyyy,yyyy,yyyy_sl_mm
				this.options.inputdatepicker = false;
			}

			if (this.options.inputdatepicker) {
				jQuery("#" + this.options.inputDateId).input_datepicker({
					inputdateformatId: this.options.inputModelId,
					dateFormat: this.options.inputdatepickerDateFormat,
					showOn: this.options.inputdatepickerShowOn ? 'button' : undefined,
					buttonImage: datepickerConfig.contextPath + "/" + (this.options.inputdatepickerDisabled ? 'js/datepicker/image/cal-grey.gif' : this.options.inputdatepickerButtonImage),
					buttonImageOnly: this.options.inputdatepickerButtonImageOnly,
					changeMonth: this.options.inputdatepickerChangeMonth,
					changeYear: this.options.inputdatepickerChangeYear,
					disabled: this.options.inputdatepickerDisabled,
					numberOfMonths: that.options.selectDateRange.numberOfMonths,
					defaultDate: "+0d",
					onClose: function() {
					},
					beforeShow: function() {
						//console.info('before show');
						
						/*JAI
						ไม่แสดง datePicker ตอน Load Page กรณีเป็นหน้าค้น
						*/
						if (bControlShowDateFirst==false){
							bControlShowDateFirst=true
							return false;
						}
						
						
						var dateTmp = null;
						if (that.options.selectDateRange.dateFrom != undefined) {
							var dateValuex = jQuery( "#" + that.options.selectDateRange.dateFrom).input_dateformat( "dateValue").dateForDB;
							//console.info(dateValuex);
							if (dateValuex != "") {
								dateTmp = dateValuex;
							}

							//jQuery( "#" + that.options.inputDateId).input_datepicker( "option", "minDate", dateTmp );

						} else if (that.options.selectDateRange.dateTo != undefined) {
							var dateValuex = jQuery( "#" + that.options.selectDateRange.dateTo).input_dateformat( "dateValue").dateForDB;
							//console.info(dateValuex);
							if (dateValuex != "") {
								dateTmp = dateValuex;
							}

							//jQuery( "#" + that.options.inputDateId).input_datepicker( "option", "maxDate", dateTmp );

						}
					}
				});
			}
		},

		_createInputDateIndex: function() {
			var dayBegin = "";
			var dayEnd = "";
			var monthBegin = "";
			var monthEnd = "";
			var yearBegin = "";
			var yearEnd = "";
			var delimiters = "";
			var format = this._getDateformat().split('');
			//console.info(format);
			for (var i = 0; i < format.length; i++) {
				//console.info(i + ": " + format[i]);
				if (format[i] == 'd') {
					if (dayBegin === "") {
						dayBegin = i + "";
					} else {
						dayEnd = (i + 1) + "";
					}
				} else if (format[i] == 'm') {
					if (monthBegin === "") {
						monthBegin = i + "";
					} else {
						monthEnd = (i + 1) + "";
					}
				} else if (format[i] == 'y') {
					if (yearBegin === "") {
						yearBegin = i + "";
					} else {
						yearEnd = (i + 1) + "";
					}
				} else if (format[i] == '/') {
					if (delimiters !== "") {
						delimiters += ",";
					}
					delimiters += (i + "");
				}
			}
			//console.info(dayBegin + "," + dayEnd);
			if ((dayBegin != "") && (dayEnd != "")) {
				this.options.indexDayBegin = parseInt(dayBegin, 10);
				this.options.indexDayEnd = parseInt(dayEnd, 10);
			}

			if ((monthBegin != "") && (monthEnd != "")) {
				this.options.indexMonthBegin = parseInt(monthBegin, 10);
				this.options.indexMonthEnd = parseInt(monthEnd, 10);
			}

			if ((yearBegin != "") && (yearEnd != "")) {
				//console.info("yearindex-x: " + yearBegin + "," + yearEnd);
				this.options.indexYearBegin = parseInt(yearBegin, 10);
				this.options.indexYearEnd = parseInt(yearEnd, 10);
			}

			if (delimiters != "") {
				this.options.indexDelimiters = delimiters;
			}
		},

		_createInputDateMask: function() {
			var maskformat = this._getDateformat();
			//console.info('maskformat: ' + this.options.dateformat);
			//console.info(this.options.maskPattern);
			if (this.options.maskPattern == "dmy") {
				maskformat = maskformat.replace(/d/g,'d');
				maskformat = maskformat.replace(/m/g,'m');
				maskformat = maskformat.replace(/y/g,'y');
			} else if (this.options.maskPattern == "DMY") {
				maskformat = maskformat.replace(/d/g,'D');
				maskformat = maskformat.replace(/m/g,'M');
				maskformat = maskformat.replace(/y/g,'Y');
			} else {
				maskformat = maskformat.replace(/d/g,this.options.maskPattern);
				maskformat = maskformat.replace(/m/g,this.options.maskPattern);
				maskformat = maskformat.replace(/y/g,this.options.maskPattern);
			}
			this.options.mask = maskformat;
		},
		
		// TODO: TAO Begin
		_createInputDatePlaceholder: function() {
			var maskformat = this._getDateformat();
			//placeholderPattern
			//console.info('maskformat: ' + this.options.dateformat);
			//console.info(this.options.maskPattern);
			if (this.options.placeholderPattern == "dmy") {
				maskformat = maskformat.replace(/d/g,'d');
				maskformat = maskformat.replace(/m/g,'m');
				maskformat = maskformat.replace(/y/g,'y');
			} else if (this.options.placeholderPattern == "DMY") {
				maskformat = maskformat.replace(/d/g,'D');
				maskformat = maskformat.replace(/m/g,'M');
				maskformat = maskformat.replace(/y/g,'Y');
			} else {
				maskformat = maskformat.replace(/d/g,this.options.placeholderPattern);
				maskformat = maskformat.replace(/m/g,this.options.placeholderPattern);
				maskformat = maskformat.replace(/y/g,this.options.placeholderPattern);
			}
			this.options.placeholder = maskformat;
		},
		// TODO: TAO End

		_createInputDateRemark: function () {
			var format = this._getDateformat().toUpperCase();
			 var comment = jQuery("<div><font class='comment'>" + datepickerConfig.dateFormat[format]  + "</font></div");
			//var comment = jQuery("<font class='comment'>   " + datepickerConfig.dateFormat[format]  + "</font>");
			comment.css('width', this.element.css('width'));
			comment.css('textAlign', 'center');
			this.element.parent().append(comment);
		},

		_createInputDateFormat: function() {
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
				this.options.inputdatepickerDisabled = true;
			}

			if ((input.attr("readonly") != undefined) && (input.attr("readonly") != "")) {
				this.options.readonly = true;
				this.options.inputdatepickerDisabled = true;
			}

			this.options.inputModelId = input.attr("id");
			this.options.inputDateId = this.options.inputModelId + "_" + this.options.dateformat;
			input.attr("class", "");
			input.attr("style", "");
			input.css('display', this.options.displayModelInput);

			var inputDateFormat = jQuery("<input  id='"+ this.options.inputDateId + "' type='text' class='" + this.options.cssClass + "' style='" + this.options.cssStyle + "'/>");
			inputDateFormat.val(this.options.mask);
			inputDateFormat.attr('maxlength', inputDateFormat.val().length);
			inputDateFormat.addClass('input_dateformat_' + this.options.dateformat);

			if ((input.attr("disabled") != undefined) && (input.attr("disabled") != "")) {
				inputDateFormat.attr('disabled', this.options.readonly);
			}

			if ((input.attr("readonly") != undefined) && (input.attr("readonly") != "")) {
				inputDateFormat.attr('readonly', this.options.readonly);
			}

			inputDateFormat.change(function(){
				//console.info('change: ' + that.dateValue());
				jQuery(this).removeClass("requireDateformat_" + that.options.dateformat + "_select").removeClass("input_dateformat_" + that.options.dateformat).addClass("input_dateformat_" + that.options.dateformat);
				jQuery("#" + that.options.inputModelId).val(that.dateValue().dateForDB);

				that._initDateRange();
			});
			
			// TODO: TAO Begin
			jQuery(inputDateFormat).attr('placeholder', this.options.placeholder);
			if (inputDateFormat.val() == this.options.mask) {
				inputDateFormat.val("");	
			}
			// TODO: TAO End

			parent.append(inputDateFormat);
			return inputDateFormat;
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

		_getDateformat: function() {
			return this.options.dateformat.replace(/_sl_/g,'/');
		},

		_getDateformatForDatepickger: function() {
			return this.options.dateformat.replace(/_sl_/g,'/').replace('yyyy', 'yy');
		},

		_getInputDateValueFromElement: function(element) {
			return this._getInputDateValueFromValue(element.value);
		},

		_getInputDateValueFromValue: function(value) {
			for (var i = 0; i < 4; i++) {
				value = value.replace(this.options.maskPattern,'');
			}
			return value.replace(/\/|_/g,'').replace(/d|D|m|M|y|Y/g,'');
		},

		_getInputDateCursorPosition: function(element) {
			var inputDateValue = this._getInputDateValueFromElement(element);
			var inputDateLength = (inputDateValue + "").length;
			var inputDateCursorPosition = inputDateLength;

			if (inputDateLength >= 2) {
				inputDateCursorPosition++;
			}

			if (inputDateLength >= 4) {
				inputDateCursorPosition++;
			}

			return inputDateCursorPosition;
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

		_manageDayByYear: function(element, year) {
			//	D = 29 2012, 2008, 2004 ,2000 ปีที่หาร 4 ลงตัว ที่เดือน 2 ต้องเป็น ค.ศ.
			// 2012 = 2555
			// 2013 = 2556
			// 2014 = 2557
			// 2015 = 2558
			// 2016 = 2559

			if ((year + "").length != 4) {
				return;
			}

			//console.info("y % 4   === 0 : " + year % 4);
			//console.info("y % 100 !== 0 : " + year % 100);
			//console.info("y % 400 === 0 : " + year % 400);
			if(this._have29DayByYear(year)) {
				// ไม่ต้องทำอะไรเนื่องจาก ถูกจัดการที่ manageDayByMonth เรียบร้อยแล้ว
			} else {
				// ถ้าเป็นเดือน 2 และมีค่าวันมากกว่า 28 ต้องปรับค่าลงเหลือ 28 วัน
				var inputDD = parseInt(this._getDay(element.value), 10);
				var inputMM = parseInt(this._getMonth(element.value), 10);
				if ((parseInt(inputMM, 10) == 2) && (parseInt(inputDD, 10) > 28)) {
					this._setDay(28);
				}
			}
		},

		_manageDayByMonth: function(element, month) {
			//	D = 29 2
			//  D = 31 1, 3, 5, 7, 8, 10, 12
			//  D = 30 4, 6, 9, 11
			//console.info('month: ' + month);
			var inputDD = parseInt(this._getDay(element.value), 10);
			if (this._have31Day(month)) {

			} else if (this._have30Day(month)) {
				if (inputDD > 30) {
					this._setDay(30);
				}
			} else {
				if (this._getYear(element.value) == "") {
					if (this._have29Day(month)){
						if (inputDD > 29) {
							this._setDay(29);
						}
					}
				} else {
					if (month == 2) {
						if(this._have29DayByYear(parseInt(this._getYear(element.value), 10))) {
							if (inputDD > 29) {
								this._setDay(29);
							}
						} else {
							if (inputDD > 28) {
								this._setDay(28);
							}
						}
					}
				}
			}
		},

		_manageBackspace: function(element, unicode) {

			if (unicode.toString() == "8") {
				if (jQuery(element).attr('readonly') != undefined) {
					return false;
				}

				jQuery("#" + this.options.inputModelId).val("");

				var startCursorPosition = this._getCursorPosition(element);

				if (startCursorPosition == 0) {
					return false;
				}

				if (this._isDilimenter(startCursorPosition - 1)) {
					startCursorPosition--;
				}

				//console.info(startCursorPosition);
				if ((startCursorPosition >= this.options.indexDayBegin) && (startCursorPosition <= this.options.indexDayEnd)) {

					this._clearDay();
					startCursorPosition = this.options.indexDayBegin;

				} else if ((startCursorPosition >= this.options.indexMonthBegin) && (startCursorPosition <= this.options.indexMonthEnd)) {

					this._clearMonth();
					startCursorPosition = this.options.indexMonthBegin;

				} else if ((startCursorPosition >= this.options.indexYearBegin) && (startCursorPosition <= this.options.indexYearEnd)) {

					this._clearYear();
					startCursorPosition = this.options.indexYearBegin;

				}
				//console.info(newInputDateValue);

				this._setSelectionRange(element, startCursorPosition, startCursorPosition);
				
				// TODO: TAO Begin
				if (jQuery(element).val() == this.options.mask) {
					jQuery(element).attr('placeholder', this.options.placeholder);	
				}
				// TODO: TAO End
				
				return false;
			}
		},

		_manageDateValue: function(element, unicode) {
			//console.info('_manageDateValue');
			var startCursorPosition = this._getCursorPositionForInsert(element);
			var inputDateValue = element.value;
			var inputDD = inputDateValue;
			var inputMM = inputDateValue;
			var inputYYYY = inputDateValue;
			var inputChar =  String.fromCharCode(unicode);
			//console.info('startCursorPosition: ' + startCursorPosition);
			//console.info(startCursorPosition + " > " + inputChar + "[" + inputYYYY.substring(this.options.indexYearBegin, this.options.indexYearEnd) + "/" + inputMM.substring(this.options.indexMonthBegin, this.options.indexMonthEnd) + "/" + inputDD.substring(this.options.indexDayBegin, this.options.indexDayEnd) + "]");
			//console.info("[" + this.options.indexDayBegin + "] > [" + this.options.indexDayEnd + "]");
			if ((this.options.indexDayBegin !== "") && (this.options.indexDayEnd !== "") && (startCursorPosition >= this.options.indexDayBegin) && (startCursorPosition <= this.options.indexDayEnd)) {
				inputDD = inputDD.substring(this.options.indexDayBegin, startCursorPosition) + inputChar + inputDD.substring(startCursorPosition + 1, this.options.indexDayEnd);
				inputDD = this._getInputDateValueFromValue(inputDD);
				//console.info('inputDD: ' + inputDD);
				if (inputDD.length == 1) {
					//console.info('month-1: ' + this._getMonth(inputDateValue) + " > " + this._have29DayByYear(this._getYear(inputDateValue)));
					if (this._getMonth(inputDateValue) == "") {
						if ((parseInt(inputDD, 10) < 0) || (parseInt(inputDD, 10) > 3)) {
							return false;
						}
					} else if (this._have31Day(this._getMonth(inputDateValue))){
						if ((parseInt(inputDD, 10) < 0) || (parseInt(inputDD, 10) > 3)) {
							return false;
						}
					} else if (this._have30Day(this._getMonth(inputDateValue))){
						if ((parseInt(inputDD, 10) < 0) || (parseInt(inputDD, 10) > 3)) {
							return false;
						}
					} else {
						if (this._getYear(inputDateValue) == "") {
							if (this._have29Day(this._getMonth(inputDateValue))){
								if ((parseInt(inputDD, 10) < 0) || (parseInt(inputDD, 10) > 2)) {
									return false;
								}
							}
						} else {
							if (this._getMonth(inputDateValue) == 2) {
								if ((parseInt(inputDD, 10) < 0) || (parseInt(inputDD, 10) > 2)) {
									return false;
								}
							}
						}
					}

				} else if (inputDD.length == 2) {
					//console.info('month-2: ' + this._getMonth(inputDateValue) + " > " + this._have29DayByYear(this._getYear(inputDateValue)));
					if (this._getMonth(inputDateValue) == "") {
						if ((parseInt(inputDD, 10) < 1) || (parseInt(inputDD, 10) > 31)) {
							return false;
						}
					} else if (this._have31Day(this._getMonth(inputDateValue))){
						if ((parseInt(inputDD, 10) < 1) || (parseInt(inputDD, 10) > 31)) {
							return false;
						}
					} else if (this._have30Day(this._getMonth(inputDateValue))){
						if ((parseInt(inputDD, 10) < 1) || (parseInt(inputDD, 10) > 30)) {
							return false;
						}
					} else {
						if (this._getYear(inputDateValue) == "") {
							if (this._have29Day(this._getMonth(inputDateValue))){
								if ((parseInt(inputDD, 10) < 1) || (parseInt(inputDD, 10) > 29)) {
									return false;
								}
							}
						} else {
							if (this._getMonth(inputDateValue) == 2) {
								if(this._have29DayByYear(this._getYear(inputDateValue))) {
									if ((parseInt(inputDD, 10) < 1) || (parseInt(inputDD, 10) > 29)) {
										return false;
									}
								} else {
									if ((parseInt(inputDD, 10) < 1) || (parseInt(inputDD, 10) > 28)) {
										return false;
									}
								}
							}
						}
					}
				}

			} else if ((this.options.indexMonthBegin !== "") && (this.options.indexMonthEnd !== "") && (startCursorPosition >= this.options.indexMonthBegin) && (startCursorPosition <= this.options.indexMonthEnd)) {
				inputMM = inputMM.substring(this.options.indexMonthBegin, startCursorPosition) + inputChar + inputMM.substring(startCursorPosition + 1, this.options.indexMonthEnd);
				inputMM = this._getInputDateValueFromValue(inputMM);
				//alert('inputMM: ' + inputMM);
				//alert('inputMM.length: ' + inputMM.length);
				//alert('parseInt(inputMM): ' + parseInt(inputMM, 10));
				if (inputMM.length == 1) {
					if ((parseInt(inputMM, 10) < 0) || (parseInt(inputMM, 10) > 1)) {
						return false;
					}
				} else if (inputMM.length == 2) {
					if ((parseInt(inputMM, 10) < 1) || (parseInt(inputMM, 10) > 12)) {
						return false;
					}
				}
				this._manageDayByMonth(element, parseInt(inputMM, 10));
				this._setSelectionRange(element, startCursorPosition, startCursorPosition + 1);

			} else if ((this.options.indexYearBegin !== "") && (this.options.indexYearEnd !== "") && (startCursorPosition >= this.options.indexYearBegin) && (startCursorPosition <= this.options.indexYearEnd)) {
				inputYYYY = inputYYYY.substring(this.options.indexYearBegin, startCursorPosition) + inputChar + inputYYYY.substring(startCursorPosition + 1, this.options.indexYearEnd);
				inputYYYY = this._getInputDateValueFromValue(inputYYYY);
				//console.info('inputYYYY: ' + inputYYYY);
				if (isInputDateLocaleThai()) {
					var maxYearThai = (INPUT_DATE_MAX_YEAR + 543) + "";
					var minYearThai = (INPUT_DATE_MIN_YEAR + 543) + "";
					//console.info(minYearThai + " [" + inputYYYY + "] "+ maxYearThai);
					if ((parseInt(inputYYYY, 10) < parseInt(minYearThai.substring(0, inputYYYY.length), 10)) || (parseInt(inputYYYY, 10) > parseInt(maxYearThai.substring(0, inputYYYY.length), 10))) {
						return false;
					}
				} else {
					var maxYearOther = INPUT_DATE_MAX_YEAR + "";
					var minYearOther = INPUT_DATE_MIN_YEAR + "";
					//console.info(inputYYYY + " > " + minYearOther + " - " + maxYearOther);
					if ((parseInt(inputYYYY, 10) < parseInt(minYearOther.substring(0, inputYYYY.length), 10)) || (parseInt(inputYYYY, 10) > parseInt(maxYearOther.substring(0, inputYYYY.length), 10))) {
						return false;
					}
				}
				this._manageDayByYear(element, parseInt(inputYYYY, 10));
				this._setSelectionRange(element, startCursorPosition, startCursorPosition + 1);
			} else {
				return false;
			}

			//0 - 1	DD
			//3 - 4	MM
			//6 - 9	YYYY
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

		_getDay: function (inputDateValue) {
			//console.info("dd: " + this.options.indexDayBegin + " - " + this.options.indexDayEnd);
			if ((this.options.indexDayBegin === "") || (this.options.indexDayEnd === "")) {
				return "";
			} else {
				return this._getInputDateValueFromValue(inputDateValue.substring(this.options.indexDayBegin, this.options.indexDayEnd));
			}
		},

		_getMonth: function (inputDateValue) {
			//console.info("mm: " + this.options.indexMonthBegin + " - " + this.options.indexMonthEnd);
			if ((this.options.indexMonthBegin === "") || (this.options.indexMonthEnd === "")) {
				return "";
			} else {
				return this._getInputDateValueFromValue(inputDateValue.substring(this.options.indexMonthBegin, this.options.indexMonthEnd));
			}
		},

		_getYear: function (inputDateValue) {
			//console.info(this.options);
			//console.info("yy: " + this.options.indexYearBegin + " - " + this.options.indexYearEnd);
			if ((this.options.indexYearBegin === "") || (this.options.indexYearEnd === "")) {
				return "";
			} else {
				return this._getInputDateValueFromValue(inputDateValue.substring(this.options.indexYearBegin, this.options.indexYearEnd));
			}
		},

		_setDay: function (input) {
			//console.info("_setDay : " + input);
			if ((this.options.indexDayBegin !== "") && (this.options.indexDayEnd !== "")) {
				// TODO: AE Begin
				var tempValue = "";
				if (this.element.val() === "") {
					tempValue = this.options.mask;
				} else {
					tempValue = this.element.val();
				}
				
				var newValue = tempValue.substring(0, this.options.indexDayBegin);
				newValue += input;
				newValue += tempValue.substring(this.options.indexDayEnd, tempValue.length);
				this.element.val(newValue);
				//TODO: AE End
				
				//var newValue = this.element.val().substring(0, this.options.indexDayBegin);
				//newValue += input;
				//newValue += this.element.val().substring(this.options.indexDayEnd, this.element.val().length);
				//this.element.val(newValue);
			}
		},

		_setMonth: function (input) {
			//console.info("_setMonth : " + input);
			if ((this.options.indexMonthBegin !== "") && (this.options.indexMonthEnd !== "")) {
				// TODO: AE Begin
				var tempValue = "";
				if (this.element.val() === "") {
					tempValue = this.options.mask;
				} else {
					tempValue = this.element.val();
				}
				
				var newValue = tempValue.substring(0, this.options.indexMonthBegin);
				newValue += input;
				newValue += tempValue.substring(this.options.indexMonthEnd, tempValue.length);
				this.element.val(newValue);
				//TODO: AE End
				
				//var newValue = this.element.val().substring(0, this.options.indexMonthBegin);
				//newValue += input;
				//newValue += this.element.val().substring(this.options.indexMonthEnd, this.element.val().length);
				//this.element.val(newValue);
			}
		},

		_setYear: function (input) {
			//console.info("_setYear : " + input);
			if ((this.options.indexYearBegin !== "") && (this.options.indexYearEnd !== "")) {
				// TODO: AE Begin
				var tempValue = "";
				if (this.element.val() === "") {
					tempValue = this.options.mask;
				} else {
					tempValue = this.element.val();
				}
				
				var newValue = tempValue.substring(0, this.options.indexYearBegin);
				newValue += input;
				newValue += tempValue.substring(this.options.indexYearEnd, tempValue.length);
				this.element.val(newValue);
				//TODO: AE End
				
				//var newValue = this.element.val().substring(0, this.options.indexYearBegin);
				//newValue += input;
				//newValue += this.element.val().substring(this.options.indexYearEnd, this.element.val().length);
				//this.element.val(newValue);
			}
		},

		_clearDay: function () {
			if (this.options.maskPattern == "dmy") {
				this._setDay("dd");
			} else if (this.options.maskPattern == "DMY") {
				this._setDay("DD");
			} else {
				this._setDay(this.options.maskPattern + this.options.maskPattern);
			}
		},

		_clearMonth: function () {
			if (this.options.maskPattern == "dmy") {
				this._setMonth("mm");
			} else if (this.options.maskPattern == "DMY") {
				this._setMonth("MM");
			} else {
				this._setMonth(this.options.maskPattern + this.options.maskPattern);
			}
		},

		_clearYear: function () {
			if (this.options.maskPattern == "dmy") {
				this._setYear("yyyy");
			} else if (this.options.maskPattern == "DMY") {
				this._setYear("YYYY");
			} else {
				this._setYear(this.options.maskPattern + this.options.maskPattern + this.options.maskPattern + this.options.maskPattern);
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
		
		_have31Day: function (month) {
			//  D = 31 1, 3, 5, 7, 8, 10, 12
			month = parseInt(month, 10);
			if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) {
				return true;
			} else {
				return false;
			}
		},

		_have30Day: function (month) {
			//  D = 30 4, 6, 9, 11
			month = parseInt(month, 10);
			if ((month == 4) || (month == 6) || (month == 9) || (month == 11)) {
				return true;
			} else {
				return false;
			}
		},

		_have29Day: function (month) {
			//	D = 29 2
			month = parseInt(month, 10);
			if (month == 2) {
				return true;
			} else {
				return false;
			}
		},

		_have29DayByYear: function (year) {

			if (isInputDateLocaleThai()) {
				year = year - 543;
			}
			if(year % 4 === 0 && (year % 100 !== 0 || year % 400 === 0)) {
				return true;
			} else {
				return false;
			}
		},

		_initDateRange: function () {
			var selectedDate = null;

			if (this.dateValue() != "") {
				selectedDate = this.dateValue().dateForDB;
			}

			//console.info('selectedDate: ' + selectedDate);
			if ((this.options.selectDateRange.dateTo != undefined) && (this.options.selectDateRange.dateFrom == undefined)) {
				//console.info("to --------------------------------");
				if (this.options.inputdatepicker) {
					//jQuery( "#" + this.options.selectDateRange.dateTo + "_" + this.options.dateformat).input_datepicker( "option", "minDate", selectedDate );
				}

				if (selectedDate != null) {
					var intValueFrom = this.intValue();
					var intValueTo = jQuery( "#" + this.options.selectDateRange.dateTo).input_dateformat( "intValue");
					//console.info(intValueFrom + " > " + intValueTo);

					if ((intValueTo > 0) && (intValueFrom > intValueTo)) {

						jQuery( "#" + this.options.selectDateRange.dateTo).input_dateformat( "dateValue", selectedDate );
						if (this.options.inputdatepicker) {
							// jQuery( "#" + this.options.inputDateId).input_datepicker( "option", "maxDate", selectedDate );
						}
					}

					if ((intValueTo > 0) && (intValueFrom >= intValueTo)) {
						//set new time
						if ((this.options.selectTimeRange.timeTo != undefined) && (this.options.selectTimeRange.timeFrom != undefined)) {
							var range = conpareTimeFormatById(this.options.selectTimeRange.timeFrom, this.options.selectTimeRange.timeTo);
							if ((range != undefined) && (range > 0)) {
								var selectedTime = jQuery( "#" + this.options.selectTimeRange.timeFrom).input_timeformat( "timeValue" );
								jQuery( "#" + this.options.selectTimeRange.timeTo).input_timeformat( "timeValue", selectedTime );
							}
						}
					}
				}
				//console.info("to --------------------------------");
			} else if ((this.options.selectDateRange.dateTo == undefined) && (this.options.selectDateRange.dateFrom != undefined)) {
				//console.info("from --------------------------------");
				if (this.options.inputdatepicker) {
					//jQuery( "#" + this.options.selectDateRange.dateFrom + "_" + this.options.dateformat).input_datepicker( "option", "maxDate", selectedDate );
				}

				if (selectedDate != null) {
					var intValueFrom = jQuery( "#" + this.options.selectDateRange.dateFrom).input_dateformat( "intValue");
					var intValueTo = this.intValue();
					//console.info(intValueFrom + " > " + intValueTo);

					if ((intValueTo > 0) && (intValueFrom > intValueTo)) {
						jQuery( "#" + this.options.selectDateRange.dateFrom).input_dateformat( "dateValue", selectedDate );
						if (this.options.inputdatepicker) {
							//jQuery( "#" + this.options.inputDateId).input_datepicker( "option", "minDate", selectedDate );
						}
					}

					if ((intValueTo > 0) && (intValueFrom >= intValueTo)) {
						//set new time
						if ((this.options.selectTimeRange.timeTo != undefined) && (this.options.selectTimeRange.timeFrom != undefined)) {
							var range = conpareTimeFormatById(this.options.selectTimeRange.timeFrom, this.options.selectTimeRange.timeTo);
							if ((range != undefined) && (range > 0)) {
								var selectedTime = jQuery( "#" + this.options.selectTimeRange.timeTo).input_timeformat( "timeValue" );
								jQuery( "#" + this.options.selectTimeRange.timeFrom).input_timeformat( "timeValue", selectedTime );
							}
						}
					}
				}
				//console.info("from --------------------------------");
			}
		},

		dateValue: function (newValue) {

			if (newValue == undefined) {
				// get value
				if (this.element.val() == this.options.mask) {
					return {dateForDB: "", dateForShow: ""};
				} else {
					if (inputValidateDateFormatValueName(document.getElementById(this.options.inputDateId)) == 'ok') {
						var newValueOther = "";
						if (this._getDay(this.element.val()) != "") {
							newValueOther += this._getDay(this.element.val()) + "/";
						}

						if (this._getMonth(this.element.val()) != "") {
							newValueOther += this._getMonth(this.element.val()) + "/";
						}

						if (isInputDateLocaleThai()) {
							newValueOther += (parseInt(this._getYear(this.element.val()), 10) - 543);
						} else {
							newValueOther += this._getYear(this.element.val());
						}
						
						return {dateForDB: newValueOther, dateForShow: this.element.val()};
					} else {
						return {dateForDB: "", dateForShow: ""};
					}
				}
			} else {
				// set value
				if (newValue == "") {
					this.element.val(this.options.mask);
					jQuery("#" + this.options.inputModelId).val(newValue);
				} else {
					var inputDD = "";
					var inputMM = "";
					var inputYYYY = "";
					if (newValue.length == 4) {
						inputYYYY = newValue.substring(0, 4);

					} else if (newValue.length == 7) {
						inputMM = newValue.substring(0, 2);
						inputYYYY = newValue.substring(3, 7);

					} else if (newValue.length == 10) {
						inputDD = newValue.substring(0, 2);
						inputMM = newValue.substring(3, 5);
						inputYYYY = newValue.substring(6, 10);
					}

					if (isInputDateLocaleThai()) {
						inputYYYY = (parseInt(inputYYYY, 10) + 543) + "";
					}

					this._setDay(inputDD);
					this._setMonth(inputMM);
					this._setYear(inputYYYY);

					if (this.dateValue() != "") {
						jQuery("#" + this.options.inputModelId).val(this.dateValue().dateForDB);
					}
				}
			}
		},

		intValue: function () {

			if (this.element.val() == this.options.mask) {
				return 0;
			} else {
				if (inputValidateDateFormatValueName(document.getElementById(this.options.inputDateId)) == 'ok') {
					var newValueOther = "";
					if (isInputDateLocaleThai()) {
						newValueOther += (parseInt(this._getYear(this.element.val()), 10) - 543);
					} else {
						newValueOther += this._getYear(this.element.val());
					}

					if (this._getMonth(this.element.val()) != "") {
						newValueOther += this._getMonth(this.element.val());
					}

					if (this._getDay(this.element.val()) != "") {
						newValueOther += this._getDay(this.element.val());
					}
					//console.info("newValueOther: " + newValueOther);
					return parseInt(newValueOther, 10);
				} else {
					return 0;
				}
			}
		},

		clearValue: function() {
			console.log("xxxx");
			this._clearDay();
			this._clearMonth();
			this._clearYear();
		}
	});

})( jQuery );










var INPUT_DATE_MIN_YEAR = 1800;
var INPUT_DATE_MAX_YEAR = 2200;
var INPUT_DATE_LOCALE_TH = false;
var INPUT_DATE_INIT = false;
var INPUT_DATE_SCRIPT_TH = "/js/datepicker/datetime/th.js";

function isInputDateLocaleThai() {
	if (INPUT_DATE_INIT) {
		return INPUT_DATE_LOCALE_TH;
	}

	INPUT_DATE_INIT = true;
	INPUT_DATE_LOCALE_TH = false;

    var headerArray = document.getElementsByTagName('head');
    for (var h_index = 0; h_index < headerArray.length; h_index++) {
    	var scriptArray = headerArray[h_index].getElementsByTagName('script');
    	for (var s_index = 0; s_index <  scriptArray.length; s_index++) {
    		if (scriptArray[s_index].src.indexOf(INPUT_DATE_SCRIPT_TH) > 0) {
    			INPUT_DATE_LOCALE_TH = true;
        		break;
        	}
    	}
    }
    //console.info(CONTROL_LOCALE_TH);
    return INPUT_DATE_LOCALE_TH;
}

function inputValidateDateFormatValueName(el) {
	var dayValue = "";
	var monthValue = "";
	var yearValue = "";
	var el_date = el;
	var tempValue = trim(el_date.value);
	var className = el_date.className;
	//console.info('className: ' + className);
	if (className.indexOf('input_dateformat_dd_sl_mm_sl_yyyy') > -1) {
		if (tempValue == '__/__/____') {
			return 'ok';
		}
		if (tempValue.length >= 2) {
			
			dayValue = tempValue.substring(0, 2);
			
			//alert(dayValue);
		}
		if (tempValue.length >= 5) {
			monthValue = tempValue.substring(3, 5);
		}
		if (tempValue.length == 10) {
			yearValue = tempValue.substring(6, 10);
		}
	} else if (className.indexOf('input_dateformat_mm_sl_dd_sl_yyyy') > -1) {
		if (tempValue == '__/__/____') {
			return 'ok';
		}
		if (tempValue.length >= 2) {
			monthValue = tempValue.substring(0, 2);
		}
		if (tempValue.length >= 5) {
			dayValue = tempValue.substring(3, 5);
		}
		if (tempValue.length == 10) {
			yearValue = tempValue.substring(6, 10);
		}
	} else if (className.indexOf('input_dateformat_yyyy_sl_mm_sl_dd') > -1) {
		if (tempValue == '____/__/__') {
			return 'ok';
		}
		if (tempValue.length >= 4) {
			yearValue = tempValue.substring(0, 4);
		}
		if (tempValue.length >= 6) {
			monthValue = tempValue.substring(5, 7);

		}
		if (tempValue.length == 10) {
			dayValue = tempValue.substring(8, 10);
		}
	} else if (className.indexOf('input_dateformat_yyyy_sl_dd_sl_mm') > -1) {
		if (tempValue == '____/__/__') {
			return 'ok';
		}
		if (tempValue.length >= 4) {
			yearValue = tempValue.substring(0, 4);
		}
		if (tempValue.length >= 6) {
			dayValue = tempValue.substring(5, 7);

		}
		if (tempValue.length == 10) {
			monthValue = tempValue.substring(8, 10);
		}
	} else if (className.indexOf('input_dateformat_yyyy_sl_mm') > -1) {
		if (tempValue == '____/__') {
			return 'ok';
		}
		if (tempValue.length >= 4) {
			yearValue = tempValue.substring(0, 4);
		}
		if (tempValue.length == 7) {
			monthValue = tempValue.substring(5, 7);
		}
		dayValue = undefined;
	} else if (className.indexOf('input_dateformat_mm_sl_yyyy') > -1) {
		if (tempValue == '__/____') {
			return 'ok';
		}
		if (tempValue.length >= 2) {
			monthValue = tempValue.substring(0, 2);
		}
		if (tempValue.length == 7) {
			yearValue = tempValue.substring(3, 7);
		}
		dayValue = undefined;
	} else if (className.indexOf('input_dateformat_yyyy') > -1) {
		if (tempValue == '____') {
			return 'ok';
		}
		if (tempValue.length == 4) {
			yearValue = tempValue.substring(0, 4);
		}
		monthValue = undefined;
		dayValue = undefined;
	}

	var chk = "";
	var isYear = false;
	var isMonth = false;
	var isDay = false;
	//console.info("yearValue: " + yearValue);
	var y = 0;
	
	if(yearValue.indexOf("_") != -1){
		
	}else if (yearValue.length > 0) {
		y = parseInt(yearValue, 10);
		if (isInputDateLocaleThai()) {
			y = y - 543;
		}
		if ((y >= INPUT_DATE_MIN_YEAR) && (y <= INPUT_DATE_MAX_YEAR)){
			isYear = true;
		}
	}

	var m = 0;
	if (monthValue == undefined) {
		isMonth = true;
	} else {
		if(monthValue.indexOf("_") != -1){
			
		}else if (monthValue.length > 0) {
			m = parseInt(monthValue, 10);
			if ((m >= 1) && m <= 12) {
				isMonth = true;
			}
		}
	}

//	D = 29 2012, 2008, 2004 ,2000 ปีที่หาร 4 ลงตัว
//  D = 31 1, 3, 5, 7, 8, 10, 12
//  D = 30 4, 6, 9, 11
	var d = 0;
	if (dayValue == undefined) {
		isDay = true;
	} else {
		if (isMonth == true) {
			
			if(dayValue.indexOf("_") != -1){
				
			}else if (dayValue.length > 0) {
				d = parseInt(dayValue, 10);
				if ((m == 4) || (m == 6) || (m == 9) || (m == 11)) {
					if ((d >= 1) && d <= 30) {
						isDay = true;
					}
				} else if ((m == 1) || (m == 3) || (m == 5) || (m == 7) || (m == 8) || (m == 10) || (m == 12)) {
					if ((d >= 1) && d <= 31) {
						isDay = true;
					}
				} else {
					if (isYear == true) {
						if(y % 4 === 0 && (y % 100 !== 0 || y % 400 === 0)) {
							if ((d >= 1) && d <= 29) {
								isDay = true;
							}
						} else {
							if ((d >= 1) && d <= 28) {
								isDay = true;
							}
						}
					}
				}
			}
		}
	}

	//console.info('isYear:' + isYear + ", isMonth: " + isMonth + ", " + isDay);
	if (tempValue.length == 0) {
		chk = "empty";
	} else if(!isYear && !isMonth && !isDay){
		chk = "error all";
	} else if (isYear == false) {
		chk = 'not year';
	} else if (isMonth == false) {
		chk = 'not month';
	} else if (isDay == false) {
		chk = 'not day';
	} else {
		chk = 'ok';
	}
	return chk;
}

function inputValidateDateOfBirthFormatValueName(el) {
	var yearValue = jQuery(el).val();
	var monthValue = jQuery("#" + jQuery(el).attr("month-id")).val();
	var dayValue = jQuery("#" + jQuery(el).attr("day-id")).val();
	if ((yearValue == "") && (monthValue == "") && (dayValue == "")) {
		return "empty";
	}
	var chk = "";
	var isYear = false;
	var isMonth = false;
	var isDay = false;
	//console.info("yearValue: " + yearValue);
	var y = 0;
	if (yearValue.length > 0) {
		y = parseInt(yearValue, 10);
		if (isInputDateLocaleThai()) {
			y = y - 543;
		}
		if ((y >= INPUT_DATE_MIN_YEAR) && (y <= INPUT_DATE_MAX_YEAR)){
			isYear = true;
		}
	}

	//console.info("monthValue: " + monthValue);
	//console.info("dayValue: " + dayValue);
	var m = 0;
	if (monthValue == undefined) {
		isMonth = true;
	} else if ((monthValue == "") && ((dayValue != undefined) && (dayValue != ""))) {
		isMonth = false;
	} else {
		if (monthValue.length > 0) {
			m = parseInt(monthValue, 10);
			if ((m >= 0) && m <= 12) {
				isMonth = true;
			}
		}
	}

//	D = 29 2012, 2008, 2004 ,2000 ปีที่หาร 4 ลงตัว
//  D = 31 1, 3, 5, 7, 8, 10, 12
//  D = 30 4, 6, 9, 11
	var d = 0;
	if (dayValue == undefined) {
		isDay = true;
	} else {
		if (isMonth == true) {
			if (dayValue.length > 0) {
				d = parseInt(dayValue, 10);
				if (m == 0) {
					if ((d >= 0) && d <= 31) {
						isDay = true;
					}
				} else if ((m == 4) || (m == 6) || (m == 9) || (m == 11)) {
					if ((d >= 0) && d <= 30) {
						isDay = true;
					}
				} else if ((m == 1) || (m == 3) || (m == 5) || (m == 7) || (m == 8) || (m == 10) || (m == 12)) {
					if ((d >= 0) && d <= 31) {
						isDay = true;
					}
				} else {
					if (isYear == true) {
						if(y % 4 === 0 && (y % 100 !== 0 || y % 400 === 0)) {
							if ((d >= 0) && d <= 29) {
								isDay = true;
							}
						} else {
							if ((d >= 0) && d <= 28) {
								isDay = true;
							}
						}
					}
				}
			}
		}
	}

	//console.info('isYear:' + isYear + ", isMonth: " + isMonth + ", " + isDay);
	if(!isYear && !isMonth && !isDay){
		chk = "error all";
	} else if (isYear == false) {
		chk = 'not year';
	} else if (isMonth == false) {
		chk = 'not month';
	} else if (isDay == false) {
		chk = 'not day';
	} else {
		chk = 'ok';
	}
	return chk;
}
function conpareDateFormatById(elIdFrom, elIdTo) {
	var intFrom = jQuery("#" + elIdFrom).input_dateformat('intValue');
	var intTo = jQuery("#" + elIdTo).input_dateformat('intValue');
	//console.info(intFrom + " = " + intTo);

	var yFrom = 2000;
	var yTo = 2000;
	var mFrom = 0;
	var mTo = 0;
	var dFrom = 1;
	var dTo = 1;

	if (((intFrom + "").length == 4) && ((intTo + "").length == 4)){
		return {year: intFrom - intTo};

	} else if (((intFrom + "").length >= 6) && ((intTo + "").length >= 6)){
		yFrom = parseInt((intFrom + "").substring(0, 4), 10);
		yTo = parseInt((intTo + "").substring(0, 4), 10);
		mFrom = parseInt((intFrom + "").substring(4, 6), 10) -1;
		mTo = parseInt((intTo + "").substring(4, 6), 10) -1;

		//console.info(mFrom + "," + mTo);
		if (((intFrom + "").length == 6) && ((intTo + "").length == 6) ){
			if (yFrom == yTo) {
				return {month: mFrom - mTo};
			} else {
				if (mFrom == mTo) {
					return {month: (yFrom - yTo) * 12};
				}

				//console.info('month: ' + ((12 - mFrom) + mTo));
				var monthOfYear = 0;
				if (intFrom < intTo) {
					if ((yTo - yFrom) > 1) {
						monthOfYear = ((yTo - yFrom) -1) * 12;
					}
					//console.info('monthOfYear-1: ' + monthOfYear);
					return {month: (((12 - mFrom) + mTo) + monthOfYear) * -1};

				} else if (intFrom > intTo) {
					if ((yFrom - yTo) > 1) {
						monthOfYear = ((yFrom - yTo) -1) * 12;
					}
					//console.info('monthOfYear-1: ' + monthOfYear);
					return {month: ((mFrom + (12 - mTo)) + monthOfYear)};
				}
			}

		} else if (((intFrom + "").length == 8) && ((intTo + "").length == 8)) {
			dFrom = parseInt((intFrom + "").substring(6, 8), 10);
			dTo = parseInt((intTo + "").substring(6, 8), 10);

			var dateFrom = new Date(yFrom, mFrom, dFrom);
			var dateTo = new Date(yTo, mTo, dTo);

			return {day: (dateFrom.getTime() - dateTo.getTime()) / 86400000};
		}
	}
}
