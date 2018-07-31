/* ************ function ในการควบคุุม user event ******************/

/**
 *  flag keep alert status [true=alert ได้ , false= ไม่ต้อง alert]
 *  เนื่องจากเวลา alert แล้ว สั่ง ให้ไป focus จะมองเป็น onblur อีก ทำให้ alert ซ้ำมากกว่า 1 ครั้ง
 */
var dateControlAlertFlag = true;
var isIE = document.all? true : false;

var accordionHeight;

/**
 * ป้อนได้เฉพาะตัวเลข กัน user copy วาง
 */
function NumberControl(digitLength, pointLength){

	var prefixSelector = "input.input_number";
	var postfixSelector = "";

	if ((digitLength == undefined) && (pointLength == undefined)) {
		jQuery(prefixSelector).keypress(
			function(e){
				return validateIntegerPositiveOnKeypress(e, this);
			}
		);

		jQuery(prefixSelector).keydown(
			function(e){
				
			}
		);

		return false;
	}

	if ((digitLength != undefined) && (digitLength > 0)) {
		if (postfixSelector.length == 0) {
			postfixSelector = "_" + digitLength;
		} else {
			postfixSelector += "_" + digitLength;
		}
	} else {
		digitLength = 10;
	}

	if ((pointLength != undefined) && (pointLength > 0)) {
		if (postfixSelector.length == 0) {
			postfixSelector = "_" + pointLength;
		} else {
			postfixSelector += "_" + pointLength;
		}
	} else {
		pointLength = 0;
	}

	jQuery(prefixSelector + postfixSelector).css( "text-align",  "right");
	jQuery(prefixSelector + postfixSelector).each(function() {
		var tmpValue = this.value;
		if(tmpValue != undefined && trim(tmpValue)=='-'){
			this.value = "0";
			this.value = increasePoint(this.value, pointLength);
		} else if(tmpValue != undefined && trim(tmpValue)!=''){
			this.value = increasePoint(this.value, pointLength);
		}
	});

	//console.info(prefixSelector + postfixSelector);
	jQuery(prefixSelector + postfixSelector).keypress(
		function(e){
			if (pointLength == 0) {
				return validateIntegerOnKeypress(e, this, digitLength);
			} else {
				return validateDoubleOnKeypress(e, this, digitLength, pointLength);
			}
		}
	);

	jQuery(prefixSelector + postfixSelector).keydown(
		function(e){

		}
	);

	jQuery(prefixSelector + postfixSelector).blur(
		function(){
			if(trim(this.value)=='-'){
				this.value = "0";
				this.value = increasePoint(this.value, pointLength);
			} else if(trim(this.value)!=''){
				this.value = increasePoint(this.value, pointLength);
			}
		}
	);


	jQuery(prefixSelector + postfixSelector).focus(
		function(){
			if(trim(this.value)=='-'){
				this.value = "0";
				this.value = increasePoint(this.value, pointLength);
			} else if(trim(this.value)!=''){
				this.value = increasePoint(this.value, pointLength);
			}
			this.select();
		}
	);


	jQuery(prefixSelector + postfixSelector).bind('paste', null, function(e) {
	    if(!e.keyCode){
		      return false;
		}
	});
}

/**
 * ป้อนจำนวนเงินได้ เป็นตัวเลบ และใส่ comma ให้ตอน onblur กัน user copy วาง
 */
function CurrencyControl(digitLength, pointLength){

	var prefixSelector = "input.input_currency";
	var postfixSelector = "";

	if ((digitLength != undefined) && (digitLength > 0)) {
		if (postfixSelector.length == 0) {
			postfixSelector = "_" + digitLength;
		} else {
			postfixSelector += "_" + digitLength;
		}
	} else {
		digitLength = 10;
	}

	if ((pointLength != undefined) && (pointLength > 0)) {
		if (postfixSelector.length == 0) {
			postfixSelector = "_" + pointLength;
		} else {
			postfixSelector += "_" + pointLength;
		}
	} else {
		pointLength = 0;
	}

	jQuery(prefixSelector + postfixSelector).css( "text-align",  "right");
	jQuery(prefixSelector + postfixSelector).each(function() {
		var tmpValue = this.value;
		if(tmpValue != undefined && trim(tmpValue)=='-'){
			this.value = "0";
			this.value = currencyFormat(this.value);
		} else if(tmpValue != undefined && trim(tmpValue)!=''){
			this.value = increasePoint(this.value, pointLength);
			this.value = currencyFormat(this.value);
		}
	});

	//console.info(prefixSelector + postfixSelector);
	jQuery(prefixSelector + postfixSelector).keypress(
		function(e){
			if (pointLength == 0) {
				return validateIntegerOnKeypress(e, this, digitLength);
			} else {
				return validateDoubleOnKeypress(e, this, digitLength, pointLength);
			}
		}
	);

	jQuery(prefixSelector + postfixSelector).keydown(
		function(e){

		}
	);

	jQuery(prefixSelector + postfixSelector).blur(
		function(){
			if(trim(this.value)=='-'){
				this.value = "0";
				this.value = increasePoint(this.value, pointLength);
				this.value = currencyFormat(this.value);
			} else if(trim(this.value)!=''){
				this.value = increasePoint(this.value, pointLength);
				this.value = currencyFormat(this.value);
			}
		}
	);

	jQuery(prefixSelector + postfixSelector).focus(
		function(){
			if (jQuery(this).is('[readonly]') || jQuery(this).is('[disabled]')) {
				return;
			}

			if(trim(this.value)=='-'){
				this.value = "0";
				this.value = increasePoint(this.value, pointLength);
				this.value = currencyUnFormat(this.value);
			} else if(trim(this.value)!=''){
				this.value = increasePoint(this.value, pointLength);
				this.value = currencyUnFormat(this.value);
			}
			this.select();
		}
	);

	jQuery(prefixSelector + postfixSelector).bind('paste', null, function(e) {
	    if(!e.keyCode){
		      return false;
		}
	});
}

/**
 * สำหรับตรวจสอบ input type ที่เป็นตัวเลข (integer) สำหรับ firefox
 * โดยสามารถตรวจสอบได้ทั้งค่าบวกและค่าลบ
 * @create sittipol.m 20140423
 * @param e คือ event จาก input นั้นๆ
 * @param element คือ input html นั้นๆ
 * @param digitLength คือ ความยาวของตัวเลขที่กรอกได้
 * @returns เป็น true เมื่อถูกต้อง, เป็น false เมื่อผิด
 */
function validateIntegerOnKeypress(e, element, digitLength){
	var unicode = e.which;
	//console.info(e.which);
	if (unicode.toString().search(/^(0|8|9|13)$/) != (-1)){
		return true;
	}

	var cursorStart = controlCursorPositionStart(element);
	var cursorEnd = controlCursorPositionEnd(element);
	var char = String.fromCharCode(unicode);
	if (char == '-') {
		if ((cursorStart == 0) && (cursorEnd > cursorStart)) {
			return true;
		} else if ((cursorStart == 0) && (trim(element.value).length == 0)) {
			return true;
		} else if ((cursorStart == 0) && (trim(element.value).indexOf(char) == -1)) {
			return true;
		}
	}
//	console.info(trim(element.value).length + " >= " + digitLength);
	if ((cursorStart == 0) && (cursorEnd == 1) && (trim(element.value).indexOf('-') == 0)) {
		if (trim(element.value).length < digitLength) {
			return checkIntegerPattern(unicode);
		} else {
			return false;
		}
	} else {
		
		if (trim(element.value).indexOf('-') <= -1) {
			if (trim(element.value).length >= digitLength) {
				if (cursorEnd > cursorStart) {
					return checkIntegerPattern(unicode);
				} else {
					return false;
				}
			}
		} else {
			//alert(cursorStart + ' > ' + cursorEnd + ' = ' + element.value.indexOf('-'));
			if ((cursorStart == 0) && (cursorEnd == 0) && (element.value.indexOf('-') == 0)) {
				return false;
			} else if (trim(element.value).length > digitLength) {
				if (cursorEnd > cursorStart) {
					return checkIntegerPattern(unicode);
				} else {
					return false;
				}
			}
		}
	}

	return checkIntegerPattern(unicode);
}

function validateIntegerPositiveOnKeypress(e, element){
	var unicode = e.which;
	//console.info(e.which);
	if (unicode.toString().search(/^(0|8|9|13)$/) != (-1)){
		return true;
	}


	var cursorStart = controlCursorPositionStart(element);
	var cursorEnd = controlCursorPositionEnd(element);
	var char = String.fromCharCode(unicode);
	if (char == '-') {
		return false;
	}

	return checkIntegerPattern(unicode);
	
}

/**
 * สำหรับตรวจสอบ input type ที่เป็นตัวเลข (double) สำหรับ firefox
 * โดยสามารถตรวจสอบได้ทั้งค่าบวกและค่าลบ
 * @create sittipol.m 20140423
 * @param e คือ event จาก input นั้นๆ
 * @param element คือ input html นั้นๆ
 * @param digitLength คือ ความยาวของตัวเลขที่กรอกได้
 * @param pointLength คือ ความยาวของทศนิยมที่กรอกได้
 * @returns เป็น true เมื่อถูกต้อง, เป็น false เมื่อผิด
 */
function validateDoubleOnKeypress(e, element, digitLength, pointLength){
	var unicode = e.which;
	var keynum = (e.which || e.keyCode);
	//console.info(e.which + ' = ' + keynum);

	var cursorStart = controlCursorPositionStart(element);
	var cursorEnd = controlCursorPositionEnd(element);
	var indexOfPoint = trim(element.value).indexOf('.');
	//console.info(cursorStart + " == " + cursorEnd + " == " + indexOfPoint);
	if ((e.which == 0) && (keynum == 46)) {
		if (indexOfPoint == trim(element.value).length -1) {
			return true;
		} else if ((cursorStart == cursorEnd) && (cursorStart == indexOfPoint)) {
			return false;
		} else if ((cursorStart <= indexOfPoint) && (indexOfPoint < cursorEnd)) { // cursor อยู่ครอบจุด
			var countDel = cursorEnd - cursorStart; //นับจำนวที่ถูกลบ
			var totalBeforeDel = trim(element.value).length; //จำนวนก่อนลบต้องบวกหนึ่งเพราะมีจุด
			var totalAfterDel = totalBeforeDel - countDel;
			if (trim(element.value).indexOf('-') <= -1) {
				if (totalAfterDel <= digitLength) {
					return true;
				} else {
					return false;
				}
			} else {
				//console.info((totalAfterDel - 1) + ' <= ' + digitLength);
				if ((totalAfterDel - 1) <= digitLength) {
					return true;
				} else {
					return false;
				}
			}
		}
	} else if ((e.which == 8) && (keynum == 8)) {
		//console.info('indexOfPoint ' + indexOfPoint);
		//console.info(trim(element.value).length -1);
		//console.info(indexOfPoint + ' = ' +  (trim(element.value).length -1));

		if (indexOfPoint == trim(element.value).length -1) {
			//console.info('del');
			return true;
		} else if ((cursorStart == cursorEnd) && ((cursorEnd - 1) == indexOfPoint)) {
			return false;
		} else if ((cursorStart <= indexOfPoint) && (indexOfPoint < cursorEnd)) { // cursor อยู่ครอบจุด
			var countDel = cursorEnd - cursorStart; //นับจำนวที่ถูกลบ
			var totalBeforeDel = trim(element.value).length; //จำนวนก่อนลบต้องบวกหนึ่งเพราะมีจุด
			var totalAfterDel = totalBeforeDel - countDel;
			if (trim(element.value).indexOf('-') <= -1) {
				if (totalAfterDel <= digitLength) {
					return true;
				} else {
					return false;
				}
			} else {
				//console.info((totalAfterDel - 1) + ' <= ' + digitLength);
				if ((totalAfterDel - 1) <= digitLength) {
					return true;
				} else {
					return false;
				}
			}
		}
	}


	if (unicode.toString().search(/^(0|8|9|13)$/) != (-1)){
		return true;
	}


	var char = String.fromCharCode(unicode);
	if (char == '-') { // ตรวจสอบค่าลบ
		if ((cursorStart == 0) && (cursorEnd > cursorStart)) {
			return true;
		} else if ((cursorStart == 0) && (trim(element.value).length == 0)) {
			return true;
		} else if ((cursorStart == 0) && (trim(element.value).indexOf(char) == -1)) {
			return true;
		}
	}

	if (char == '.') { // ตรวจสอบจุด
		if (indexOfPoint > -1) {
			if ((cursorStart <= indexOfPoint) && (indexOfPoint < cursorEnd)) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	if ((cursorStart == 0) && (cursorEnd == 0) && (element.value.indexOf('-') == 0)) {
		return false;
	}
	
	if ((cursorStart == 0) && (cursorEnd == 1) && (trim(element.value).indexOf('-') == 0)) {
		if (trim(element.value).length < digitLength) {
			return checkIntegerPattern(unicode);
		} else {
			return false;
		}
	} else {
		if (indexOfPoint == -1) { // ไม่มีจุดอยู่กรอกได้เฉพาะส่วนหน้า
			if (trim(element.value).indexOf('-') <= -1) {
				if (trim(element.value).length >= digitLength) {
					if (cursorEnd > cursorStart) {
						return checkIntegerPattern(unicode);
					} else {
						return false;
					}
				}
			} else {
				if (trim(element.value).length > digitLength) {
					if (cursorEnd > cursorStart) {
						return checkIntegerPattern(unicode);
					} else {
						return false;
					}
				}
			}
		} else {
			var doubleValue = element.value.split('.');
			if ((cursorStart <= indexOfPoint) && (indexOfPoint < cursorEnd)) { // cursor อยู่ครอบจุด
				//console.info('cen: ' + cursorStart + " < " + indexOfPoint + " < " + cursorEnd);
				var countDelete = cursorEnd - cursorStart; //นับจำนวที่ถูกลบ
				var totalBeforeDelete = trim(element.value).length; //จำนวนก่อนลบต้องบวกหนึ่งเพราะมีจุด
				var totalAfterDelete = totalBeforeDelete - countDelete;
				//console.info('cen del: ' + totalAfterDelete + " >= " + digitLength);
				if (trim(element.value).indexOf('-') <= -1) {
					if (totalAfterDelete < digitLength) {
						return checkIntegerPattern(unicode);
					} else {
						return false;
					}
				} else {
					if (totalAfterDelete <= digitLength) {
						return checkIntegerPattern(unicode);
					} else {
						return false;
					}
				}
			} else if (cursorStart <= indexOfPoint) { // cursor อยู่หน้าจุดตรวจตัวหน้า
//				console.info('left: ' + cursorStart + " <= " + indexOfPoint);
//				console.info('left-2: ' + trim(doubleValue[0]).length + " > " + digitLength);
				if (trim(element.value).indexOf('-') <= -1) {
					if (trim(doubleValue[0]).length >= digitLength) {
						if (cursorEnd > cursorStart) {
							return checkIntegerPattern(unicode);
						} else {
							return false;
						}
					}
				} else {
					if (trim(doubleValue[0]).length > digitLength) {
						if (cursorEnd > cursorStart) {
							return checkIntegerPattern(unicode);
						} else {
							return false;
						}
					}
				}
			} else if (cursorStart > indexOfPoint) { // cursor อยู่หลังจุดตรวจตัวหลัง
//				console.info('rigth: ' + cursorStart + " > " + indexOfPoint);
//				console.info('rigth-1: ' + trim(doubleValue[1]).length + " > " + pointLength);
				if (trim(doubleValue[1]).length >= pointLength) {
					if (cursorEnd > cursorStart) {
						return checkIntegerPattern(unicode);
					} else {
						return false;
					}
				}
			}
		}
	}
	return checkIntegerPattern(unicode);
}

/**
 * สำหรับตรวจสอบ input type ที่เป็นตัวเลข (integer) บน ie เท่านั้น
 * โดยสามารถตรวจสอบได้ทั้งค่าบวกและค่าลบ
 * @create sittipol.m 20140423
 * @param e คือ event จาก input นั้นๆ
 * @param element คือ input html นั้นๆ
 * @parm digitLength คือ ความยาวของตัวเลขที่กรอกได้
 * @returns เป็น true เมื่อถูกต้อง, เป็น false เมื่อผิด
 */
function validateIntegerOnKeydown(e, element, digitLength){
	var unicode = e.which;
	var keynum = e.charCode ? e.charCode : e.keyCode;//(e.which || e.keyCode);
	//document.getElementById('output').innerHTML = e.which + ' = ' + keynum + "<br>" + document.getElementById('output').innerHTML;

	if (unicode.toString().search(/^(0|8|9|45|46|35|36|37|39|13)$/) != (-1)){
		return true;
	}

	var cursorStart = controlCursorPositionStart(element);
	var cursorEnd = controlCursorPositionEnd(element);
	var char = '';//String.fromCharCode(unicode);
	if (unicode == 189) {
		char = '-';
	} else if (unicode == 190) {
		char = '.';
	}

	if (char == '-') {
		if ((cursorStart == 0) && (cursorEnd > cursorStart)) {
			return true;
		} else if ((cursorStart == 0) && (trim(element.value).length == 0)) {
			return true;
		} else if ((cursorStart == 0) && (trim(element.value).indexOf(char) == -1)) {
			return true;
		}
	}

//	console.info(trim(element.value).length + " >= " + digitLength);
	if ((cursorStart == 0) && (cursorEnd == 1) && (trim(element.value).indexOf('-') == 0)) {
		if (trim(element.value).length < digitLength) {
			return checkIntegerPatternKeydown(unicode);
		} else {
			return false;
		}
	} else {
		if (trim(element.value).indexOf('-') <= -1) {
			if (trim(element.value).length >= digitLength) {
				if (cursorEnd > cursorStart) {
					return checkIntegerPatternKeydown(unicode);
				} else {
					return false;
				}
			}
		} else {
			if (trim(element.value).length > digitLength) {
				if (cursorEnd > cursorStart) {
					return checkIntegerPatternKeydown(unicode);
				} else {
					return false;
				}
			}
		}
	}

	return checkIntegerPatternKeydown(unicode);
}

function validateIntegerPositiveOnKeydown(e, element){
	var unicode = e.which;
	if (unicode.toString().search(/^(0|8|9|45|46|35|36|37|39|13)$/) != (-1)){
		return true;
	}

	return checkIntegerPatternKeydown(unicode);
}
/**
 * สำหรับตรวจสอบ input type ที่เป็นตัวเลข (double) บน ie เท่านั้น
 * โดยสามารถตรวจสอบได้ทั้งค่าบวกและค่าลบ
 * @create sittipol.m 20140423
 * @param e คือ event จาก input นั้นๆ
 * @param element คือ input html นั้นๆ
 * @parm digitLength คือ ความยาวของตัวเลขที่กรอกได้
 * @parm pointLength คือ ความยาวของทศนิยมที่กรอกได้
 * @returns เป็น true เมื่อถูกต้อง, เป็น false เมื่อผิด
 */
function validateDoubleOnKeydown(e, element, digitLength, pointLength){
	var unicode = e.which;
	var keynum = e.charCode ? e.charCode : e.keyCode;//(e.which || e.keyCode);
	//document.getElementById('output').innerHTML = e.which + ' = ' + keynum + "<br>" + document.getElementById('output').innerHTML;

	var cursorStart = controlCursorPositionStart(element);
	var cursorEnd = controlCursorPositionEnd(element);
	var indexOfPoint = trim(element.value).indexOf('.');
	//console.info(cursorStart + " == " + cursorEnd + " == " + indexOfPoint);
	if ((e.which == 46) && (keynum == 46)) {
		if (indexOfPoint == trim(element.value).length -1) {
			return true;
		} else if ((cursorStart == cursorEnd) && (cursorStart == indexOfPoint)) {
			return false;
		} else if ((cursorStart <= indexOfPoint) && (indexOfPoint < cursorEnd)) { // cursor อยู่ครอบจุด
			var countDel = cursorEnd - cursorStart; //นับจำนวที่ถูกลบ
			var totalBeforeDel = trim(element.value).length; //จำนวนก่อนลบต้องบวกหนึ่งเพราะมีจุด
			var totalAfterDel = totalBeforeDel - countDel;
			if (trim(element.value).indexOf('-') <= -1) {
				if (totalAfterDel <= digitLength) {
					return true;
				} else {
					return false;
				}
			} else {
				//console.info((totalAfterDel - 1) + ' <= ' + digitLength);
				if ((totalAfterDel - 1) <= digitLength) {
					return true;
				} else {
					return false;
				}
			}
		}
	} else if ((e.which == 8) && (keynum == 8)) {
		//console.info(indexOfPoint + ' = ' +  trim(element.value).length -1);
		if (indexOfPoint == trim(element.value).length -1) {
			return true;
		} else if ((cursorStart == cursorEnd) && ((cursorEnd - 1) == indexOfPoint)) {
			return false;
		} else if ((cursorStart <= indexOfPoint) && (indexOfPoint < cursorEnd)) { // cursor อยู่ครอบจุด
			var countDel = cursorEnd - cursorStart; //นับจำนวที่ถูกลบ
			var totalBeforeDel = trim(element.value).length; //จำนวนก่อนลบต้องบวกหนึ่งเพราะมีจุด
			var totalAfterDel = totalBeforeDel - countDel;
			if (trim(element.value).indexOf('-') <= -1) {
				if (totalAfterDel <= digitLength) {
					return true;
				} else {
					return false;
				}
			} else {
				//console.info((totalAfterDel - 1) + ' <= ' + digitLength);
				if ((totalAfterDel - 1) <= digitLength) {
					return true;
				} else {
					return false;
				}
			}
		}
	}


	if (unicode.toString().search(/^(0|8|9|45|46|35|36|37|39|13)$/) != (-1)){
		return true;
	}


	var char = ''; //String.fromCharCode(unicode);
	if (unicode == 189) {
		char = '-';
	} else if (unicode == 190) {
		char = '.';
	}

	if (char == '-') { // ตรวจสอบค่าลบ
		if ((cursorStart == 0) && (cursorEnd > cursorStart)) {
			return true;
		} else if ((cursorStart == 0) && (trim(element.value).length == 0)) {
			return true;
		} else if ((cursorStart == 0) && (trim(element.value).indexOf(char) == -1)) {
			return true;
		}
	}



	if (char == '.') { // ตรวจสอบจุด
		if (indexOfPoint > -1) {
			if ((cursorStart <= indexOfPoint) && (indexOfPoint < cursorEnd)) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}


	if ((cursorStart == 0) && (cursorEnd == 1) && (trim(element.value).indexOf('-') == 0)) {
		if (trim(element.value).length < digitLength) {
			return checkIntegerPatternKeydown(unicode);
		} else {
			return false;
		}
	} else {
		if (indexOfPoint == -1) { // ไม่มีจุดอยู่กรอกได้เฉพาะส่วนหน้า
			if (trim(element.value).indexOf('-') <= -1) {
				if (trim(element.value).length >= digitLength) {
					if (cursorEnd > cursorStart) {
						return checkIntegerPatternKeydown(unicode);
					} else {
						return false;
					}
				}
			} else {
				if (trim(element.value).length > digitLength) {
					if (cursorEnd > cursorStart) {
						return checkIntegerPatternKeydown(unicode);
					} else {
						return false;
					}
				}
			}
		} else {
			var doubleValue = element.value.split('.');
			if ((cursorStart <= indexOfPoint) && (indexOfPoint < cursorEnd)) { // cursor อยู่ครอบจุด
				//console.info('cen: ' + cursorStart + " < " + indexOfPoint + " < " + cursorEnd);
				var countDelete = cursorEnd - cursorStart; //นับจำนวที่ถูกลบ
				var totalBeforeDelete = trim(element.value).length; //จำนวนก่อนลบต้องบวกหนึ่งเพราะมีจุด
				var totalAfterDelete = totalBeforeDelete - countDelete;
				//console.info('cen del: ' + totalAfterDelete + " >= " + digitLength);
				if (trim(element.value).indexOf('-') <= -1) {
					if (totalAfterDelete < digitLength) {
						return checkIntegerPatternKeydown(unicode);
					} else {
						return false;
					}
				} else {
					if (totalAfterDelete <= digitLength) {
						return checkIntegerPatternKeydown(unicode);
					} else {
						return false;
					}
				}
			} else if (cursorStart <= indexOfPoint) { // cursor อยู่หน้าจุดตรวจตัวหน้า
//				console.info('left: ' + cursorStart + " <= " + indexOfPoint);
//				console.info('left-2: ' + trim(doubleValue[0]).length + " > " + digitLength);
				if (trim(element.value).indexOf('-') <= -1) {
					if (trim(doubleValue[0]).length >= digitLength) {
						if (cursorEnd > cursorStart) {
							return checkIntegerPatternKeydown(unicode);
						} else {
							return false;
						}
					}
				} else {
					if (trim(doubleValue[0]).length > digitLength) {
						if (cursorEnd > cursorStart) {
							return checkIntegerPatternKeydown(unicode);
						} else {
							return false;
						}
					}
				}
			} else if (cursorStart > indexOfPoint) { // cursor อยู่หลังจุดตรวจตัวหลัง
//				console.info('rigth: ' + cursorStart + " > " + indexOfPoint);
//				console.info('rigth-1: ' + trim(doubleValue[1]).length + " > " + pointLength);
				if (trim(doubleValue[1]).length >= pointLength) {
					if (cursorEnd > cursorStart) {
						return checkIntegerPatternKeydown(unicode);
					} else {
						return false;
					}
				}
			}
		}
	}

	return checkIntegerPatternKeydown(unicode);
}

/**
 * สำรับตรวจสอบ char 0-9
 */
function checkIntegerPattern(unicode) {
//	/^\d*[0-9]?$/;
	var pattern = /^\d*[0-9]?$/;
	return pattern.test(String.fromCharCode(unicode));
}

/**
 * สำรับตรวจสอบ char 0-9
 */
function checkIntegerPatternKeydown(unicode) {
	//48-57 (0-9)
	return checkIntegerPattern(unicode);
}

function controlCursorPositionStart(ctrl) {
	// get cursor position start hightlight
	var CaretPos = 0;
	if (document.selection) {	// IE Support
		ctrl.focus ();
		if (ctrl.createTextRange) {
			var r = document.selection.createRange().duplicate();
			r.moveEnd('character', ctrl.value.length);
			if (r.text == '') {
				pos = ctrl.value.length;
			}
			pos = ctrl.value.lastIndexOf(r.text);
		} else {
			pos = ctrl.selectionStart;
		}
		CaretPos= pos;
	} else if (ctrl.selectionStart || ctrl.selectionStart == '0') { // Firefox support
		CaretPos = ctrl.selectionStart;
	}
	return (CaretPos);
}

function controlCursorPositionEnd(ctrl) {
	// get cursor position end hightlight
	var CaretPos = 0;
	if (document.selection) {	// IE Support
		ctrl.focus ();
		var Sel = document.selection.createRange ();
		Sel.moveStart ('character', -ctrl.value.length);
		CaretPos = Sel.text.length;
	} else if (ctrl.selectionStart || ctrl.selectionStart == '0') { // Firefox support
		CaretPos = ctrl.selectionEnd;
	}
	return (CaretPos);
}

function currencyUnFormat(value) {
	return value.replace(/,/g, '');
}

function unZeroFormat(value) {
	var returnValue = "";
	var i = 0;

	var minusValue = "";
	if (value.indexOf('-') == 0) {
		minusValue = "-";
		value = value.substring(1, value.length);
	}

	var foundZero = false;
	for (; i < value.length; i++) {
		if (value.substring(i, i + 1) == '0') {
			foundZero = true;
		} else {
			break;
		}
	}

	if (foundZero) {
		if (i == value.length) {
			returnValue = '0';
		} else {
			returnValue = value.substring(i, value.length);
		}
	} else {
		returnValue = value;
	}

	if (minusValue.length > 0) {
		returnValue = minusValue + returnValue;
	}
	return returnValue;
}


function currencyFormat(value) {
	var returnValue = "";
	if (value.length == 0) {
		return returnValue;
	}

	var minusValue = "";
	if (value.indexOf('-') == 0) {
		minusValue = "-";
		value = value.substring(1, value.length);
	}

	var numberValue = "";
	var pointValue = "";
	if (value.indexOf('.') == -1) {
		numberValue = currencyUnFormat(value);
	} else {
		numberValue = currencyUnFormat(value.split('.')[0]);
		pointValue = value.split('.')[1];
	}

	if (numberValue.length <= 3) {
		returnValue = numberValue;
	} else {
		var numberLength = 3;
		for (var index = numberValue.length; index >= 0; ) {
			//console.info('indexin: ' + index);


			//console.info(numberValue.substring(index - numberLength, index));
			if (returnValue.length != 0) {
				returnValue = ',' + returnValue;
			}
			returnValue = numberValue.substring(index - numberLength, index) + returnValue;
			if (index <= 0) {
				break;
			}

			index = index - 3;
			//console.info('indexout: ' + index);
			if (index == 0) {
				break;
			}
		}
	}

	if (pointValue.length > 0) {
		returnValue += '.' + pointValue;
	}

	if (minusValue.length > 0) {
		returnValue = minusValue + returnValue;
	}

	return returnValue;
}

function increasePoint(value, pointLength) {

	var returnValue = "";
	if (value == undefined) {
		return returnValue;
	}

	if (value.length == 0) {
		return returnValue;
	}

	value = unZeroFormat(value);

	if (pointLength == 0) {
		return value;
	}

	var indexStart = 0;
	if (value.indexOf('.') == -1) {
		returnValue = value + '.';
	} else if (value.indexOf('.') == value.length -1) {
		returnValue = value;
	} else {
		returnValue = value;
		indexStart = value.length - (value.indexOf('.') + 1);
	}

	for (; indexStart < pointLength; indexStart++) {
		returnValue += '0';
	}

	if (returnValue.indexOf('.') == 0) {
		returnValue = '0' + returnValue;
	} else if ((returnValue.indexOf('.') == 1) && (returnValue.indexOf('-') == 0)) {
		returnValue = '-0.' + returnValue.substring(2, returnValue.length);
	}
	return returnValue;
}

/*ลบ  checkbox ที่อยู่ใน table ออก*/
function removeActiveAndCheckbox(){
	jQuery(".action-link").remove();
	jQuery("#divBoxHeader > table > tbody > tr > th:nth-child(2) ").remove();
	jQuery("#divBoxDetail > table > tbody > tr > td:nth-child(2) ").remove();
}


/** Begin Accordion **/
// Create accordion and bind event
function loadAccordion() {
	
	jQuery("div.accordion").accordion({
		active: false,
		collapsible: true,
		heightStyle: 'content',
		beforeActivate: function(event, ui) {
			// The accordion believes a panel is being opened
			if (ui.newHeader[0]) {
				var currHeader  = ui.newHeader;
				var currContent = currHeader.next('.ui-accordion-content');
				// The accordion believes a panel is being closed
			} else {
				var currHeader  = ui.oldHeader;
				var currContent = currHeader.next('.ui-accordion-content');
			}
			// Since we've changed the default behavior, this detects the actual status
			var isPanelSelected = currHeader.attr('aria-selected') == 'true';
			
			// Toggle the panel's header
			currHeader.toggleClass('ui-corner-all',isPanelSelected).toggleClass('accordion-header-active ui-state-active ui-corner-top',!isPanelSelected).attr('aria-selected',((!isPanelSelected).toString()));
			
			// Toggle the panel's content
			if (isPanelSelected) {
				currContent.slideUp();
				
				// AE add
				jQuery(currContent).find("[name ^= 'criteria']").each(function() {
//					console.log(jQuery(this).attr("name") + " : " + jQuery(this).val());
					jQuery(this).attr("disabled","disabled");
				});
				
			} else {
				currContent.slideDown();
				
				// AE add
				jQuery(currContent).find("[name ^= 'criteria']").each(function() {
//					console.log(jQuery(this).attr("name") + " : " + jQuery(this).val());
					jQuery(this).removeAttr("disabled");
				});
			}
			
			// Save accordion state
			saveAccordionState();
			
			return false; // Cancels the default action
		}
	});
	
	// Auto load accordion state (comment this, if no need auto load state and then call "loadAccordionState()" where you need.)
	loadAccordionState();
	
	//แก้ไข style element ที่อยู่ใน accordion
	addStyleAccordion();
}

// Save accordion state
function saveAccordionState() {
	var keyItem = jQuery(document).find("input[id $= 'F_CODE']").val();
	
	// Check browser support
	if (hasStorage()) {
		// 1. Check accordion state
		var accState = "";
		jQuery("div.accordion > .ui-accordion-header").each(function () {
			accState += "," + jQuery(this).closest("div").hasClass("accordion-header-active")
		});
		accState = accState.substring(1);
		
	    // 2. Store accordion state
	    sessionStorage.setItem(keyItem, accState);
	    
	    var accordionHeightx = 0;
	    accordionHeightx = jQuery(".accordion").height(); 
	    sessionStorage.setItem("accordionHeight", accordionHeightx);
	   // console.info("accordionHeight open :"+accordionHeight);
	} else {
		// Browser does not support Web Storage...
	}
	
	
}

// Load accordion state
function loadAccordionState() {
	var keyItem = jQuery(document).find("input[id $= 'F_CODE']").val();
	
	// Check browser support
	if (hasStorage()) {
	    // 1. Retrieve
	    var accState = sessionStorage.getItem(keyItem);
	    
	    // 2. Load load accordion state
	    if (accState != "" && accState != "null" && accState != null) {
		    var arrState = accState.split(",");
	    	jQuery("div.accordion > .ui-accordion-header").each(function (index) {
	    		if (arrState[index] == "true") {
	    			jQuery(this).closest("div").removeClass("ui-corner-all")
	    				.addClass("accordion-header-active ui-state-active ui-corner-top")
	    				.attr('aria-selected',arrState[index])
	    				.next('.ui-accordion-content').slideDown();
	    		} else {
	    			jQuery(this).closest("div").removeClass("accordion-header-active ui-state-active ui-corner-top")
	    				.addClass("ui-corner-all")
	    				.attr('aria-selected',arrState[index])
	    				.next('.ui-accordion-content').slideUp();
	    		}
	    	});
	    } else {
	    	// จะเกิดขึ้นในกรณีเข้าหน้านั้นๆ มาครั้งแรก (ยังไม่มีการกด accordion)
	    	// set default (collap accordion)
	    	var defaultState = false;
	    	jQuery("div.accordion > .ui-accordion-header").each(function (index) {
    			jQuery(this).closest("div").removeClass("accordion-header-active ui-state-active ui-corner-top")
    				.addClass("ui-corner-all")
    				.attr('aria-selected', defaultState)
    				.next('.ui-accordion-content').slideUp();
	    	});
	    	
	    	jQuery(".ui-accordion-content").find("[name ^= 'criteria']").each(function () {
	    		jQuery(this).attr("disabled","disabled");
	    	});
	    }
	    
	} else {
		// Browser does not support Web Storage...
	}
}

// Clear all accordion state except current page
function initAccordionState() {
	if (hasStorage()) {
		var keyItem = jQuery(document).find("input[id $= 'F_CODE']").val();
		// 1. Retrieve
		var accState = sessionStorage.getItem(keyItem);
		var criteria1600State = sessionStorage.getItem("stateDisplay1600");
		var criteria400_1600State = sessionStorage.getItem("stateDisplay400_1600");
		
		// 2. Clear all session storage
		sessionStorage.clear();
		
		// 3. Save back current session storage
		sessionStorage.setItem(keyItem, accState);
		sessionStorage.setItem("stateDisplay1600", criteria1600State);
		sessionStorage.setItem("stateDisplay400_1600", criteria400_1600State);
	}
}

// Clear current accordion state
function clearAccordionState() {
	if (hasStorage()) {
		var keyItem = jQuery(document).find("input[id $= 'F_CODE']").val();
		// Clear current session storage
		sessionStorage.setItem(keyItem, "");
	}
	
	accordionHeight = jQuery(".accordion").height(); 
	sessionStorage.setItem("accordionHeight", accordionHeight);
//	console.info("accordionHeight close :"+accordionHeight);
}

// Clear all accordion state
function clearAllAccordionState() {
	if (hasStorage()) {
		// Clear all session storage
		//sessionStorage.clear();
	}
}

// Check Browser support Web Storage?
function hasStorage() {  
    return typeof(Storage) !== "undefined" ? true : false;
}  
/** End Accordion **/


/**
 * ใส่ค่า enddate  = startDate + spinner
 */
function checkEndDate(startDate, endDate, days) {
	if(endDate == "" && startDate != "" && days != ""){
		var temp = startDate.split("/");
		var result = new Date(temp[2]+"/"+temp[1]+"/"+temp[0]);
		result.setTime(result.getTime() +  (days * 24 * 60 * 60 * 1000));
	    return ("0"+result.getDate()).slice(-2)+"/"+("0" +(result.getMonth()+1)).slice(-2)+"/"+result.getFullYear();
	}else{
		return endDate;
	}
}


function calendarAndSpiner(idForm, idTo, idSpiner){
	//≠ เลือกปฎิทิน  + สปินเนอร์ เอาค่าไปใส่ ปฎิทินด้านหลัง
	jQuery("#"+idForm).on("change", function() {
		var value = jQuery(this).val().replace(/_/g, '');
		if(value.length == 10){
			if(jQuery(this).val() != "" && jQuery("#"+idSpiner).val() != ""){
				if(parseInt(maxDay) >= parseInt(jQuery("#"+idSpiner).val())){
					plueDay(idForm, idTo , jQuery("#"+idSpiner).val());
				}
			}else{
				jQuery("#"+idSpiner).val(maxDay);
				plueDay(idForm, idTo , jQuery("#"+idSpiner).val());
			}
		}
	});
	
	
	jQuery("#"+idTo).on("change", function() {
		if(jQuery(this).val() != "" && jQuery("#"+idForm).val() != ""){
			var value = jQuery(this).val().replace(/_/g, '');
			var valueForm = jQuery("#"+idForm).val().replace(/_/g, '');
			if(value.length == 10 && valueForm.length == 10){
				var daySpiner = betweenDate(idForm, idTo, idSpiner);
				// console.info(daySpiner);
				if(parseInt(daySpiner) > parseInt(maxDay)){
				//	alert(validateMessage.CODE_10036);
					jQuery("#"+idSpiner).val(maxDay);
				//	jQuery("#"+idSpiner).focus();
					plueDay2(idForm, idTo , maxDay);
				//	return false;
				}else{
					jQuery("#"+idSpiner).val(daySpiner);
				}
			}else{
				jQuery("#"+idSpiner).val(0);
			}
		}
	});
	
	
	
	//≠ เลือกปฎิทิน  + สปินเนอร์ เอาค่าไปใส่ ปฎิทินด้านหลัง
	jQuery("#"+idSpiner).on("blur", function() {
		if(jQuery("#"+idForm).val() != "" && jQuery(this).val() != ""){
			var value = jQuery("#"+idForm).val().replace(/_/g, '');
			if(value.length == 10){
				if(parseInt(maxDay) >= parseInt(jQuery(this).val())){
					plueDay(idForm, idTo , jQuery(this).val());
				}
			}
		}
	});
	
	if(jQuery("#"+idForm).val() != "" && jQuery("#"+idSpiner).val() != ""){
		var value = jQuery("#"+idForm).val().replace(/_/g, '');
		if(value.length == 10){
			if(parseInt(maxDay) >= parseInt(jQuery("#"+idSpiner).val())){
				plueDay(idForm, idTo , jQuery("#"+idSpiner).val());
			}
		}
	}
}


function calendarAndSpinerAll(idForm, idTo, idSpiner ,msg ,maxDayDate){
	
	//≠ เลือกปฎิทิน  + สปินเนอร์ เอาค่าไปใส่ ปฎิทินด้านหลัง
	jQuery("#"+idForm).on("change", function() {
		var value = jQuery(this).val().replace(/_/g, '');
		if(value.length == 10){
			if(jQuery(this).val() != "" && jQuery("#"+idSpiner).val() != ""){
				if(parseInt(maxDayDate) >= parseInt(jQuery("#"+idSpiner).val())){
					plueDay(idForm, idTo , jQuery("#"+idSpiner).val());
				}
			}else{
				jQuery("#"+idSpiner).val(maxDayDate);
				plueDay(idForm, idTo , jQuery("#"+idSpiner).val());
			}
		}
	});
	
	
	jQuery("#"+idTo).on("change", function() {
		if(jQuery(this).val() != "" && jQuery("#"+idForm).val() != ""){
			var value = jQuery(this).val().replace(/_/g, '');
			var valueForm = jQuery("#"+idForm).val().replace(/_/g, '');
			if(value.length == 10 && valueForm.length == 10){
				var daySpiner = betweenDate(idForm, idTo, idSpiner);
				if(parseInt(daySpiner) > parseInt(maxDayDate)){
					alert(msg);
					jQuery("#"+idSpiner).val(maxDayDate);
					jQuery("#"+idSpiner).focus();
					plueDay(idForm, idTo , maxDayDate);
					return false;
				}else{
					jQuery("#"+idSpiner).val(daySpiner);
				}
			}else{
				jQuery("#"+idSpiner).val(0);
			}
		}
	});
	
	
	
	//≠ เลือกปฎิทิน  + สปินเนอร์ เอาค่าไปใส่ ปฎิทินด้านหลัง
	jQuery("#"+idSpiner).on("blur", function() {
		if(jQuery("#"+idForm).val() != "" && jQuery(this).val() != ""){
			var value = jQuery("#"+idForm).val().replace(/_/g, '');
			if(value.length == 10){
				if(parseInt(maxDayDate) >= parseInt(jQuery(this).val())){
					plueDay(idForm, idTo , jQuery(this).val());
				}
			}
		}
	});
	
	if(jQuery("#"+idForm).val() != "" && jQuery("#"+idSpiner).val() != ""){
		var value = jQuery("#"+idForm).val().replace(/_/g, '');
		if(value.length == 10){
			if(parseInt(maxDayDate) >= parseInt(jQuery("#"+idSpiner).val())){
				plueDay(idForm, idTo , jQuery("#"+idSpiner).val());
			}
		}
	}
}


//validate วันที่เริ่มต้น + spinner + วันที่สิ้นสด   ** default spinner 31 วัน
function calendarAndSpiner31All(idForm, idTo, idSpiner ,msg ,maxDayDate){
	
	//≠ เลือกปฎิทิน  + สปินเนอร์ เอาค่าไปใส่ ปฎิทินด้านหลัง
	jQuery("#"+idForm).on("change", function() {
		var value = jQuery(this).val().replace(/_/g, '');
		if(value.length == 10){
			if(jQuery(this).val() != "" && jQuery("#"+idSpiner).val() != ""){
				if(parseInt(maxDay) >= parseInt(jQuery("#"+idSpiner).val())){
					plueDay(idForm, idTo , jQuery("#"+idSpiner).val());
				}
			}else{
				jQuery("#"+idSpiner).val(maxDay);
				plueDay(idForm, idTo , jQuery("#"+idSpiner).val());
			}
		}
	});
	
	
	jQuery("#"+idTo).on("change", function() {
		if(jQuery(this).val() != "" && jQuery("#"+idForm).val() != ""){
			var value = jQuery(this).val().replace(/_/g, '');
			var valueForm = jQuery("#"+idForm).val().replace(/_/g, '');
			if(value.length == 10 && valueForm.length == 10){
				var daySpiner = betweenDate(idForm, idTo, idSpiner);
				if(parseInt(daySpiner) > parseInt(maxDayDate)){
					alert(msg);
					jQuery("#"+idSpiner).val(maxDayDate);
					jQuery("#"+idSpiner).focus();
					plueDay(idForm, idTo , maxDayDate);
					return false;
				}else{
					jQuery("#"+idSpiner).val(daySpiner);
				}
			}else{
				jQuery("#"+idSpiner).val(0);
			}
		}
	});
	
	
	
	//≠ เลือกปฎิทิน  + สปินเนอร์ เอาค่าไปใส่ ปฎิทินด้านหลัง
	jQuery("#"+idSpiner).on("blur", function() {
		if(jQuery("#"+idForm).val() != "" && jQuery(this).val() != ""){
			var value = jQuery("#"+idForm).val().replace(/_/g, '');
			if(value.length == 10){
				if(parseInt(maxDayDate) >= parseInt(jQuery(this).val())){
					plueDay(idForm, idTo , jQuery(this).val());
				}
			}
		}
	});
	
	if(jQuery("#"+idForm).val() != "" && jQuery("#"+idSpiner).val() != ""){
		var value = jQuery("#"+idForm).val().replace(/_/g, '');
		if(value.length == 10){
			if(parseInt(maxDayDate) >= parseInt(jQuery("#"+idSpiner).val())){
				plueDay(idForm, idTo , jQuery("#"+idSpiner).val());
			}
		}
	}
}

//หา dayDiff กับ maxDayDate ถ้า dayDiff มากกว่า return true ** suriya.s**
function checkMaxDay(idForm, idTo ,maxDayDate){
	var isMore = false;
	if(jQuery("#" +idForm).val() != "" && jQuery("#" +idTo).val() != ""){
		var dayDiff = betweenDate(idForm, idTo);
		if(dayDiff > maxDayDate){
			isMore = true;
		}
	}
	return isMore;
}

//ตรวจสอบ element ที่มี class hasValue มีค่าหรือไม่ ถ้าไม่มีจะสั่ง focus, alert msg และ return false ** suriya.s**
function hasValues(msg){
	var hasValue = false;
	var focusOn = new Array();
	
	jQuery(".hasValue").each(function(i) {
		if(jQuery(this).val().trim() != ""){
			hasValue = true;
		}else{
			focusOn.push(jQuery(this));
		}
	});
	
	if(!hasValue){
		jQuery(focusOn[0]).focus();
		alert(msg);
	}
	
	return hasValue;
}



function plueDay(idForm , idTo , days){
	var temp = jQuery("#"+idForm).val().split("/");
	var result = new Date(temp[2]+"/"+temp[1]+"/"+temp[0]);
	result.setTime(result.getTime() +  (days * 24 * 60 * 60 * 1000));
    //jQuery("#"+idTo).val( ("0"+result.getDate()).slice(-2)+"/"+("0" +(result.getMonth()+1)).slice(-2)+"/"+result.getFullYear() );
    var value = ("0"+result.getDate()).slice(-2)+"/"+("0" +(result.getMonth()+1)).slice(-2)+"/"+result.getFullYear();
    //jQuery("#"+idTo).val(value);
	//phronphun.s แก้ไขวิธีการ set ค่าให้ date_picker
    jQuery("#"+idTo.replace("_dd_sl_mm_sl_yyyy","")).input_dateformat('dateValue', value);
}

function plueDay2(idForm , idTo , days){
	var temp = jQuery("#"+idTo).val().split("/");
	var result = new Date(temp[2]+"/"+temp[1]+"/"+temp[0]);
	result.setTime(result.getTime() -  (days * 24 * 60 * 60 * 1000));
    //jQuery("#"+idTo).val( ("0"+result.getDate()).slice(-2)+"/"+("0" +(result.getMonth()+1)).slice(-2)+"/"+result.getFullYear() );
    var value = ("0"+result.getDate()).slice(-2)+"/"+("0" +(result.getMonth()+1)).slice(-2)+"/"+result.getFullYear();
    // console.info(value);
    //jQuery("#"+idTo).val(value);
	//phronphun.s แก้ไขวิธีการ set ค่าให้ date_picker
    jQuery("#"+idForm.replace("_dd_sl_mm_sl_yyyy","")).input_dateformat('dateValue', value);
}

function betweenDate(idForm , idTo){
	var tempForm = jQuery("#"+idForm).val().split("/");
	var tempTo = jQuery("#"+idTo).val().split("/");
	var dateForm = new Date(tempForm[2]+"/"+tempForm[1]+"/"+tempForm[0]);
	var dateTo = new Date(tempTo[2]+"/"+tempTo[1]+"/"+tempTo[0]);
	
	var timeDiff = Math.abs(dateForm.getTime() - dateTo.getTime());
	var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
	return diffDays;
}

/**
 * ตรวจสอบ length spinner
 * @param ele
 * @returns {Boolean}
 */
function checkSpinnerAll(ele , minSpinner , maxSpinner){
	if(parseInt(ele.value) < parseInt(minSpinner) || parseInt(ele.value) > parseInt(maxSpinner)){
		alert(validateMessage.CODE_30074);
		jQuery("#"+ele.id).val(minSpinner);
		ele.focus();
		return false;
	}
	
}

/**
 * ตรวจสอบ length spinner
 * @param ele
 * @returns {Boolean}
 */
function checkSpinnerAllMessage(ele , minSpinner , maxSpinner ,msg){
	if(parseInt(ele.value) < parseInt(minSpinner) || parseInt(ele.value) > parseInt(maxSpinner)){
		alert(msg);
		jQuery("#"+ele.id).val(minSpinner);
		ele.focus();
		return false;
	}
	
}

/**
 * ตรวจสอบ spinner ของ date spinner
 * @param ele
 * @param max
 * @returns {Boolean}
 */
function checkSpinner(ele){
	if(parseInt(ele.value) > parseInt(maxDay)){
		alert(validateMessage.CODE_10036);
		jQuery("#"+ele.id).val(maxDay);
		ele.focus();
		return false;
	}
	
}


/**
 * สำหรับ แก้ไข  font style ของ element ที่อยู่ใน accordion ให้มีขนาดเท่ากับ element ที่อยู่นอก accordion
 * หมายเหตุ เนื่องจาก element ที่อยู่ใน accordion จะไปโดน style ของ accordion ทำให้ขนาดของ element ไม่เท่ากัน
 */
function addStyleAccordion(){
	jQuery(".accordion").removeClass("ui-widget");
	jQuery("div.ui-accordion-header").css({"font-family":"Verdana,Arial,sans-serif","font-size":"1em"});
	jQuery("span.ui-spinner").removeClass("ui-widget");	
}


