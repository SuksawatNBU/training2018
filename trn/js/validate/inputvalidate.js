function LTrim(str) {
	/*
	 * PURPOSE: Remove leading blanks from our string. IN: str - the string we
	 * want to LTrim
	 */

	var whitespace = new String(" \t\n\r");
	var s = new String(str);
	if (whitespace.indexOf(s.charAt(0)) != -1) {
		// We have a string with leading blank(s)...
		var j = 0, i = s.length;

		// Iterate from the far left of string until we
		// don't have any more whitespace...
		while (j < i && whitespace.indexOf(s.charAt(j)) != -1)
			j++;

		// Get the substring from the first non-whitespace
		// character to the end of the string...
		s = s.substring(j, i);
	}
	return s;
}

function RTrim(str) {
	/*
	 * PURPOSE: Remove trailing blanks from our string. IN: str - the string we
	 * want to RTrim
	 *
	 */

	// We don't want to trip JUST spaces, but also tabs,
	// line feeds, etc. Add anything else you want to
	// "trim" here in Whitespace
	var whitespace = new String(" \t\n\r");
	var s = new String(str);

	if (whitespace.indexOf(s.charAt(s.length - 1)) != -1) {
		// We have a string with trailing blank(s)...
		var i = s.length - 1; // Get length of string

		// Iterate from the far right of string until we
		// don't have any more whitespace...
		while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1)
			i--;

		// Get the substring from the front of the string to
		// where the last non-whitespace character is...
		s = s.substring(0, i + 1);
	}

	return s;
}

function Trim(str) {
	/*
	 * PURPOSE: Remove trailing and leading blanks from our string. IN: str -
	 * the string we want to Trim
	 *
	 * RETVAL: A Trimmed string!
	 */
	return RTrim(LTrim(str));
}

function ReplaceAll(inText, inFindStr, inReplStr, inCaseSensitive) {
	var searchFrom = 0;
	var offset = 0;
	var outText = "";
	var searchText = "";
	if (inCaseSensitive == null) {
		inCaseSensitive = false;
	}

	if (inCaseSensitive) {
		searchText = inText.toLowerCase();
		inFindStr = inFindStr.toLowerCase();
	} else {
		searchText = inText;
	}

	offset = searchText.indexOf(inFindStr, searchFrom);
	while (offset != -1) {
		outText += inText.substring(searchFrom, offset);
		outText += inReplStr;
		searchFrom = offset + inFindStr.length;
		offset = searchText.indexOf(inFindStr, searchFrom);
	}
	outText += inText.substring(searchFrom, inText.length);

	return (outText);
}

function replaceurl(inText) {
	inText = ReplaceAll(inText, '%', '%25');
	inText = ReplaceAll(inText, '&', '%26');
	inText = ReplaceAll(inText, '<', '%3C');
	inText = ReplaceAll(inText, '>', '%3E');
	inText = ReplaceAll(inText, '=', '%3D');
	inText = ReplaceAll(inText, '+', '%2B');
	inText = ReplaceAll(inText, '#', '%23');
	inText = ReplaceAll(inText, '?', '%40');
	outText = inText;
	return (outText);
}

jQuery(document).ready(function() {

    jQuery('textarea[maxlength]').keyup(function(){
        //get the limit from maxlength attribute
        var limit = parseInt($(this).attr('maxlength'));
        //get the current text inside the textarea
        var text = $(this).val();
        //count the number of characters in the text
        var chars = text.length;

        //check if there are more characters then allowed
        if(chars > limit){
            //and if there are use substr to get the text before the limit
            var new_text = text.substr(0, limit);

            //and change the current text with the new text
            $(this).val(new_text);
        }
    });

});

function validateRowRequireInput(tableId, msg){
	if (msg == undefined) {
		msg = validateMessage.CODE_10002;
	}
	var eles = jQuery("#"+tableId+" tr .requireSelect");
	for(var i=0;i < eles.length;i++){
		if ((eles[i].tagName == 'SPAN') || (eles[i].tagName == 'span') || (eles[i].tagName == 'DIV') || (eles[i].tagName == 'div') || (eles[i].tagName == 'LABEL') || (eles[i].tagName == 'label')) {
			continue;
		}
		if ((eles[i].type == 'radio') || (eles[i].type == 'checkbox')) {
			if (findChecked(eles[i].name).length == 0) {
				alert(msg);
				document.getElementsByName(eles[i].name)[0].focus();
				return false;
			} else {
				document.getElementsByName(eles[i].name)[0].className = document.getElementsByName(eles[i].name)[0].className.replace("requireSelect", "requireInput");
				document.getElementById(eles[i].name + 'GroupId').className = document.getElementById(eles[i].name + 'GroupId').className.replace("requireSelect", "requireGroup");
			}
		} else {
			//alert(eles[i].name + ": " + !eles[i].disabled + " && " + !eles[i].readOnly );
			if(!eles[i].disabled && trim(eles[i].value)==''){
				alert(msg);
				eles[i].focus();
				return false;
			} else if(!eles[i].disabled && (trim(eles[i].value) == '__/__/____')){
				alert(msg);
				eles[i].focus();
				return false;
			} else {
				eles[i].className = eles[i].className.replace("requireSelect", "requireInput");
			}
		}
	}

	eles = jQuery("#"+tableId+" tr .requireInput");
	for(var i=0;i < eles.length;i++){
		if ((eles[i].type == 'radio') || (eles[i].type == 'checkbox')) {
			if (findChecked(eles[i].name).length == 0) {
				alert(msg);
				//alert(jQuery(eles).attr('name'));
				document.getElementsByName(eles[i].name)[0].focus();
				document.getElementsByName(eles[i].name)[0].className = document.getElementsByName(eles[i].name)[0].className.replace("requireInput", "requireSelect");

				document.getElementById(eles[i].name + 'GroupId').className = document.getElementById(eles[i].name + 'GroupId').className.replace("requireGroup","requireSelect");
				return false;
			}
		} else {
			if(!eles[i].disabled && trim(eles[i].value)==''){
				alert(msg);
				eles[i].focus();
				eles[i].className = eles[i].className.replace("requireInput", "requireSelect");
				return false;
			} else if(!eles[i].disabled && (trim(eles[i].value) == '__/__/____')){
				alert(msg);
				eles[i].focus();
				eles[i].className = eles[i].className.replace("requireInput", "requireSelect");
				return false;
			}
		}
	}
	return true;
}

function validateAll() {
	return validateFormIndexAll(0);
}

function validateFormIndexAll(formIndex) {
	var pass = validateFormIndex(formIndex);
	if (pass) {
		return validateInputFormIndex(formIndex);
	} else {
		return false;
	}
}
function validate(msg) {
	return validateFormIndex(0, msg);
}

function validateFormIndex(formIndex, msg){
	if (msg == undefined) {
		//msg = validateMessage.CODE_10002;
		msg ="ข้อมูลไม่เพียงพอ";
	}

	var eles = jQuery("#"+document.forms[formIndex].id+" .requireSelect");
	for(var i=0;i < eles.length;i++){
		if ((eles[i].tagName.toLowerCase() == 'span') && jQuery(eles[i]).hasClass('ui-spinner')) {
			if(jQuery(eles[i]).prev().val() == ""){
				alert(msg);
				jQuery(eles[i]).focus();
				return false;
			}else{
				jQuery(eles[i]).removeClass("requireSelect");
				jQuery(eles[i]).children('input').addClass("requireInput");
			}
		} else
		//selectmenu
		if ((eles[i].tagName.toLowerCase() == 'span') && jQuery(eles[i]).hasClass('ui-selectmenu-button')) {
			if(jQuery(eles[i]).prev().val() == ""){
				alert(msg);
				jQuery(eles[i]).focus();
				return false;
			}else{
				jQuery(eles[i]).removeClass("requireSelect");
				jQuery(eles[i]).addClass("requireInput");
			}
		}

		if ((eles[i].tagName == 'SPAN') || (eles[i].tagName == 'span') || (eles[i].tagName == 'DIV') || (eles[i].tagName == 'div') || (eles[i].tagName == 'LABEL') || (eles[i].tagName == 'label')) {
			continue;
		}

		if ((eles[i].type == 'radio') || (eles[i].type == 'checkbox')) {
			if (findChecked(eles[i].name).length == 0) {
				alert(msg);
				document.getElementsByName(eles[i].name)[0].focus();
				return false;
			} else {
				document.getElementsByName(eles[i].name)[0].className = document.getElementsByName(eles[i].name)[0].className.replace("requireSelect", "requireInput");
				document.getElementById(eles[i].name + 'GroupId').className = document.getElementById(eles[i].name + 'GroupId').className.replace("requireSelect", "requireGroup");
			}
		} else {
			//alert(eles[i].name + ": " + !eles[i].disabled + " && " + !eles[i].readOnly );
			if(!eles[i].disabled && trim(eles[i].value)==''){
				alert(msg);
				eles[i].focus();
				return false;
			} else if(!eles[i].disabled && (trim(eles[i].value) == '__/__/____')){
				alert(msg);
				eles[i].focus();
				return false;
			} else if(!eles[i].disabled && (trim(eles[i].value) == '__:__')){
				alert(msg);
				eles[i].focus();
				return false;
			} else {
				eles[i].className = eles[i].className.replace("requireSelect", "requireInput");
			}
		}
	}

	// คืนสถานะของ ip address เป็น require input
	jQuery("#"+document.forms[formIndex].id+" .requireIpaddress_block_left_select").each(function() {
		jQuery("#"+jQuery(this).attr('id').replace("_IPADDRESS_BLOCK_1", "")).input_ipaddress('toRequireInput');
	});

	jQuery("#"+document.forms[formIndex].id+" .requireIpaddress_block_center_select").each(function(index) {
		jQuery("#"+jQuery(this).attr('id').replace("_IPADDRESS_BLOCK_" + index, "")).input_ipaddress('toRequireInput');
	});

	// คืนสถานะของ ip address เป็น require input
	jQuery("#"+document.forms[formIndex].id+" .requireIpaddress_block_right_select").each(function() {
		jQuery("#"+jQuery(this).attr('id').replace("_IPADDRESS_BLOCK_4", "")).input_ipaddress('toRequireInput');
	});

	eles = jQuery("#"+document.forms[formIndex].id+" .requireInput");
	for(var i=0;i < eles.length;i++){
		if ((eles[i].tagName == 'LABEL') || (eles[i].tagName == 'label') || (jQuery(eles[i]).attr('type') == 'hidden') || ((jQuery(eles[i]).css('display') == 'none') && (jQuery(eles[i]).hasClass('flatStyle') == false))) {
			continue;
		}

		if ((eles[i].tagName.toLowerCase() == 'span') && jQuery(eles[i]).hasClass('ui-selectmenu-button')) {
			//selectmenu
			if(jQuery(eles[i]).prev().val() == ""){
				alert(msg);
				jQuery(eles[i]).removeClass("requireInput");
				jQuery(eles[i]).addClass("requireSelect");
				jQuery(eles[i]).focus();
				return false;
			}

		}else if ((eles[i].type == 'radio') || (eles[i].type == 'checkbox')) {
			if (findChecked(eles[i].name).length == 0) {
				alert(msg);
				//alert(jQuery(eles).attr('name'));
				document.getElementsByName(eles[i].name)[0].focus();
				document.getElementsByName(eles[i].name)[0].className = document.getElementsByName(eles[i].name)[0].className.replace("requireInput", "requireSelect");

				document.getElementById(eles[i].name + 'GroupId').className = document.getElementById(eles[i].name + 'GroupId').className.replace("requireGroup","requireSelect");
				return false;
			}
		} else {
			if (!eles[i].disabled && trim(eles[i].value) =='' && (jQuery(eles[i]).hasClass('ipaddress_block_left') || jQuery(eles[i]).hasClass('ipaddress_block_center') || jQuery(eles[i]).hasClass('ipaddress_block_right'))) {
				alert(msg);
				eles[i].focus();
				if (jQuery(eles[i]).hasClass('ipaddress_block_left')) {
					jQuery("#"+jQuery(eles[i]).attr('id').replace("_IPADDRESS_BLOCK_1", "")).input_ipaddress('toRequireSelect');
				} else if (jQuery(eles[i]).hasClass('ipaddress_block_center')) {
					jQuery("#"+jQuery(eles[i]).attr('id').replace("_IPADDRESS_BLOCK_2", "").replace("_IPADDRESS_BLOCK_3", "")).input_ipaddress('toRequireSelect');
				} else if (jQuery(eles[i]).hasClass('ipaddress_block_right')) {
					jQuery("#"+jQuery(eles[i]).attr('id').replace("_IPADDRESS_BLOCK_4", "")).input_ipaddress('toRequireSelect');
				}
				return false;
			} else {
				if(!eles[i].disabled && trim(eles[i].value)==''){
					if ((eles[i].tagName == 'SELECT') || (eles[i].tagName == 'select')) {
//						alert(validateMessage.CODE_10039 + " = " +jQuery(eles[i]).attr('title'));
						alert("ข้อมูลไม่เพียงพอ");
					} else if (jQuery(eles[i]).hasClass('ui-spinner-input')) {
						jQuery(eles[i]).removeClass("requireInput");
						jQuery(eles[i]).parent().addClass("requireSelect");
						jQuery(eles[i]).parent().focus();
						alert(msg);
					} else {
						alert(msg);
					}

					eles[i].focus();
					eles[i].className = eles[i].className.replace("requireInput", "requireSelect");
					return false;
				} else if(!eles[i].disabled && (trim(eles[i].value) == '__/__/____')){
					// validate date
					alert(msg);
					eles[i].focus();
					eles[i].className = eles[i].className.replace("requireInput", "requireSelect");
					return false;
				} else if(!eles[i].disabled && (trim(eles[i].value) == '__:__')){
					// validate time
					alert(msg);
					eles[i].focus();
					eles[i].className = eles[i].className.replace("requireInput", "requireSelect");
					return false;
				}

			}

		}
	}

	return true;
}


function validateInputFormIndex(formIndex){
	//เคลียร์กรอบแดง
	findAndReplaceClass(formIndex, 'requireEmailSelect', 'input_email');
	findAndReplaceClass(formIndex, 'requireIDCardSelect', 'input_idcard');
	findAndReplaceClass(formIndex, 'requirePasswordFormatSelect', 'input_password_format');
	findAndReplaceClass(formIndex, 'requireIpaddressFormat_block_left_select', 'input_ipaddress_block');
	findAndReplaceClass(formIndex, 'requireIpaddressFormat_block_center_select', 'input_ipaddress_block');
	findAndReplaceClass(formIndex, 'requireIpaddressFormat_block_right_select', 'input_ipaddress_block');
	findAndReplaceClass(formIndex, 'requireIpaddressFormat_dot_select', 'input_ipaddress_dot');

	findAndReplaceClass(formIndex, 'requireDateformat_dd_sl_mm_sl_yyyy_select', 'input_dateformat_dd_sl_mm_sl_yyyy');
	findAndReplaceClass(formIndex, 'requireDateformat_yyyy_sl_mm_sl_dd_select', 'input_dateformat_yyyy_sl_mm_sl_dd');
	findAndReplaceClass(formIndex, 'requireDateformat_mm_sl_dd_sl_yyyy_select', 'input_dateformat_mm_sl_dd_sl_yyyy');
	findAndReplaceClass(formIndex, 'requireDateformat_yyyy_sl_dd_sl_mm_select', 'input_dateformat_yyyy_sl_dd_sl_mm');
	findAndReplaceClass(formIndex, 'requireDateformat_mm_sl_yyyy_select', 'input_dateformat_mm_sl_yyyy');
	findAndReplaceClass(formIndex, 'requireDateformat_yyyy_sl_mm_select', 'input_dateformat_yyyy_sl_mm');
	findAndReplaceClass(formIndex, 'requireDateformat_yyyy_select', 'input_dateformat_yyyy');

	findAndReplaceClass(formIndex, 'requireTimeformat_hh_cl_mm_select', 'input_timeformat_hh_cl_mm');
	findAndReplaceClass(formIndex, 'requireDateofbirth_select', 'input_dateofbirth');
	findAndReplaceClass(formIndex, 'requireDateofbirth_daymonth_select', '');

	// Select class ที่ขึั้นต้น input_ สำหรับตรวจรูปแบบ
	var eles = jQuery("#"+document.forms[formIndex].id + " input[class*='input_'], select[class*='input_']");
	for (var index = 0; index < eles.length; index++) {
		if (jQuery(eles[index]).is('[disabled]')) {
			continue;
		}
		var className = eles[index].className;
		if (className.indexOf('input_email') > -1) {
			if (validateEmailFormatFormIndex(eles[index]) == false) {
				return false;
			}
		} else if (className.indexOf('input_ipaddress_block') > -1) {
			if (validateIpaddressFormatFormIndex(eles[index]) == false) {
				return false;
			}
		}else if (className.indexOf('input_idcard') > -1) {
			if (validateIDCardFormatFormIndex(eles[index]) == false) {
				return false;
			}
		} else if (className.indexOf('input_password_format') > -1) {
			if (validatePasswordFormatFormIndex(eles[index]) == false) {
				return false;
			}
		} else if (className.indexOf('input_dateformat_') > -1) {
			var status = inputValidateDateFormatValueName(eles[index]);
			//console.info('status: ' + status);
			if ((status == 'empty') || status == 'ok') {

			} else {
				//console.info('className: ' + className);
				if (className.indexOf('input_dateformat_dd_sl_mm_sl_yyyy') > -1) {
					eles[index].className = eles[index].className.replace("input_dateformat_dd_sl_mm_sl_yyyy", "requireDateformat_dd_sl_mm_sl_yyyy_select");
				} else if (className.indexOf('input_dateformat_yyyy_sl_mm_sl_dd') > -1) {
					eles[index].className = eles[index].className.replace("input_dateformat_yyyy_sl_mm_sl_dd", "requireDateformat_yyyy_sl_mm_sl_dd_select");
				} else if (className.indexOf('input_dateformat_mm_sl_dd_sl_yyyy') > -1) {
					eles[index].className = eles[index].className.replace("input_dateformat_mm_sl_dd_sl_yyyy", "requireDateformat_mm_sl_dd_sl_yyyy_select");
				} else if (className.indexOf('input_dateformat_yyyy_sl_dd_sl_mm') > -1) {
					eles[index].className = eles[index].className.replace("input_dateformat_yyyy_sl_dd_sl_mm", "requireDateformat_yyyy_sl_dd_sl_mm_select");
				} else if (className.indexOf('input_dateformat_mm_sl_yyyy') > -1) {
					eles[index].className = eles[index].className.replace("input_dateformat_mm_sl_yyyy", "requireDateformat_mm_sl_yyyy_select");
				} else if (className.indexOf('input_dateformat_yyyy_sl_mm') > -1) {
					eles[index].className = eles[index].className.replace("input_dateformat_yyyy_sl_mm", "requireDateformat_yyyy_sl_mm_select");
				} else if (className.indexOf('input_dateformat_yyyy') > -1) {
					eles[index].className = eles[index].className.replace("input_dateformat_yyyy", "requireDateformat_yyyy_select");
				}

				if (status == "error all") {
					alert(validateMessage.CODE_60004);
				} else if (status == "not year") {
					alert(validateMessage.CODE_60007);
				} else if (status == "not month") {
					alert(validateMessage.CODE_60006);
				} else if (status == "not day") {
					alert(validateMessage.CODE_60005);
				}
				eles[index].focus();
				return false;
			}
		} else if (className.indexOf('input_timeformat_') > -1) {
			var status = inputValidateTimeFormatValueName(eles[index]);
			//console.info('status: ' + status);
			if ((status == 'empty') || status == 'ok') {

			} else {
				//console.info('className: ' + className);
				if (className.indexOf('input_timeformat_hh_cl_mm') > -1) {
					eles[index].className = eles[index].className.replace("input_timeformat_hh_cl_mm", "requireTimeformat_hh_cl_mm_select");
				}

				if (status == "error all") {
					alert(validateMessage.CODE_10033);
				} else if (status == "not hour") {
					alert(validateMessage.CODE_10033);
				} else if (status == "not minute") {
					alert(validateMessage.CODE_10033);
				}
				eles[index].focus();
				return false;
			}
		} else if (className.indexOf('input_dateofbirth') > -1) {
			var status = inputValidateDateOfBirthFormatValueName(eles[index]);
			if (status == 'not year') {
				alert(validateMessage.CODE_60004);
				jQuery(eles[index]).removeClass('input_dateofbirth').addClass('requireDateofbirth_select');
				jQuery(eles[index]).focus();
				return false;
			} else if (status == 'not month') {
				jQuery("#" + jQuery(eles[index]).attr("month-id")).addClass('requireDateofbirth_daymonth_select');
				alert(validateMessage.CODE_60006);
				jQuery("#" + jQuery(eles[index]).attr("month-id")).focus();
				return false;
			} else if (status == 'not day') {
				jQuery("#" + jQuery(eles[index]).attr("day-id")).addClass('requireDateofbirth_daymonth_select');
				alert(validateMessage.CODE_60005);
				jQuery("#" + jQuery(eles[index]).attr("day-id")).focus();
				return false;
			} else if (status == 'error all') {
				jQuery(eles[index]).removeClass('input_dateofbirth').addClass('requireDateofbirth_select');
				alert(validateMessage.CODE_60007);
				jQuery(eles[index]).focus();
				return false;
			}
		}
	}
	return true;
}



function validateRow(tableId) {

	var pass = validateRowRequireInput(tableId);
	if (pass) {
		return validateRowInput(tableId);
	} else {
		return false;
	}
}

function validateRowInput(tableId){
	//เคลียร์กรอบแดง
	findAndReplaceClassByRowTable(tableId, 'requireEmailSelect', 'input_email');
	findAndReplaceClassByRowTable(tableId, 'requireIDCardSelect', 'input_idcard');
	findAndReplaceClassByRowTable(tableId, 'requirePasswordFormatSelect', 'input_password_format');

	findAndReplaceClassByRowTable(tableId, 'requireDateformat_dd_sl_mm_sl_yyyy_select', 'input_dateformat_dd_sl_mm_sl_yyyy');
	findAndReplaceClassByRowTable(tableId, 'requireDateformat_yyyy_sl_mm_sl_dd_select', 'input_dateformat_yyyy_sl_mm_sl_dd');
	findAndReplaceClassByRowTable(tableId, 'requireDateformat_mm_sl_dd_sl_yyyy_select', 'input_dateformat_mm_sl_dd_sl_yyyy');
	findAndReplaceClassByRowTable(tableId, 'requireDateformat_yyyy_sl_dd_sl_mm_select', 'input_dateformat_yyyy_sl_dd_sl_mm');
	findAndReplaceClassByRowTable(tableId, 'requireDateformat_mm_sl_yyyy_select', 'input_dateformat_mm_sl_yyyy');
	findAndReplaceClassByRowTable(tableId, 'requireDateformat_yyyy_sl_mm_select', 'input_dateformat_yyyy_sl_mm');
	findAndReplaceClassByRowTable(tableId, 'requireDateformat_yyyy_select', 'input_dateformat_yyyy');

	findAndReplaceClassByRowTable(tableId, 'requireTimeformat_hh_cl_mm_select', 'input_timeformat_hh_cl_mm');
	// Select class ที่ขึั้นต้น input_ สำหรับตรวจรูปแบบ
	var eles = jQuery("#"+tableId+" tr input[class*='input_']");
	for (var index = 0; index < eles.length; index++) {
		if (jQuery(eles[index]).is('[disabled]')) {
			continue;
		}
		var className = eles[index].className;
		if (className.indexOf('input_email') > -1) {
			if (validateEmailFormatFormIndex(eles[index]) == false) {
				return false;
			}
		} else if (className.indexOf('input_idcard') > -1) {
			if (validateIDCardFormatFormIndex(eles[index]) == false) {
				return false;
			}
		} else if (className.indexOf('input_password_format') > -1) {
			if (validatePasswordFormatFormIndex(eles[index]) == false) {
				return false;
			}
		} else if (className.indexOf('input_dateformat_') > -1) {
			var status = inputValidateDateFormatValueName(eles[index]);
			//console.info('status: ' + status);
			if ((status == 'empty') || status == 'ok') {

			} else {
				//console.info('className: ' + className);
				if (className.indexOf('input_dateformat_dd_sl_mm_sl_yyyy') > -1) {
					eles[index].className = eles[index].className.replace("input_dateformat_dd_sl_mm_sl_yyyy", "requireDateformat_dd_sl_mm_sl_yyyy_select");
				} else if (className.indexOf('input_dateformat_yyyy_sl_mm_sl_dd') > -1) {
					eles[index].className = eles[index].className.replace("input_dateformat_yyyy_sl_mm_sl_dd", "requireDateformat_yyyy_sl_mm_sl_dd_select");
				} else if (className.indexOf('input_dateformat_mm_sl_dd_sl_yyyy') > -1) {
					eles[index].className = eles[index].className.replace("input_dateformat_mm_sl_dd_sl_yyyy", "requireDateformat_mm_sl_dd_sl_yyyy_select");
				} else if (className.indexOf('input_dateformat_yyyy_sl_dd_sl_mm') > -1) {
					eles[index].className = eles[index].className.replace("input_dateformat_yyyy_sl_dd_sl_mm", "requireDateformat_yyyy_sl_dd_sl_mm_select");
				} else if (className.indexOf('input_dateformat_mm_sl_yyyy') > -1) {
					eles[index].className = eles[index].className.replace("input_dateformat_mm_sl_yyyy", "requireDateformat_mm_sl_yyyy_select");
				} else if (className.indexOf('input_dateformat_yyyy_sl_mm') > -1) {
					eles[index].className = eles[index].className.replace("input_dateformat_yyyy_sl_mm", "requireDateformat_yyyy_sl_mm_select");
				} else if (className.indexOf('input_dateformat_yyyy') > -1) {
					eles[index].className = eles[index].className.replace("input_dateformat_yyyy", "requireDateformat_yyyy_select");
				}

				if (status == "error all") {
					alert(validateMessage.CODE_60004);
				} else if (status == "not year") {
					alert(validateMessage.CODE_60007);
				} else if (status == "not month") {
					alert(validateMessage.CODE_60006);
				} else if (status == "not day") {
					alert(validateMessage.CODE_60005);
				}
				eles[index].focus();
				return false;
			}
		} else if (className.indexOf('input_timeformat_') > -1) {
			var status = inputValidateTimeFormatValueName(eles[index]);
			//console.info('status: ' + status);
			if ((status == 'empty') || status == 'ok') {

			} else {
				//console.info('className: ' + className);
				if (className.indexOf('input_timeformat_hh_cl_mm') > -1) {
					eles[index].className = eles[index].className.replace("input_timeformat_hh_cl_mm", "requireTimeformat_hh_cl_mm_select");
				}

				if (status == "error all") {
					alert(validateMessage.CODE_10033);
				} else if (status == "not hour") {
					alert(validateMessage.CODE_10033);
				} else if (status == "not minute") {
					alert(validateMessage.CODE_10033);
				}
				eles[index].focus();
				return false;
			}
		}
	}
	return true;
}

function findAndReplaceClass(formIndex, classForFind, classForReplace) {
	var elesSelect = jQuery("#"+document.forms[formIndex].id + " ." + classForFind);
	for(var i = 0; i < elesSelect.length; i++){
		elesSelect[i].className = elesSelect[i].className.replace(classForFind, classForReplace);
	}
}

function findAndReplaceClassByRowTable(tableId, classForFind, classForReplace) {
	var elesSelect = jQuery("#"+tableId+" tr ." + classForFind);
	for(var i = 0; i < elesSelect.length; i++){
		elesSelect[i].className = elesSelect[i].className.replace(classForFind, classForReplace);
	}
}
/**
* ตรวจสอบการเลือกข้อมูล
* elName คือ ชื่อของ input ที่ต้องการตรวจสอบ
* return false เมื่อไม่มีการเลือกข้อมูล
* return tr ขอข้อมูลที่ถูกเลือก
*/
function validateSelectOne(elName) {

	//	Arunya.k  รองรับกรณีส่ง element มา
	if (typeof elName === 'object') {
		var arrayChecked = elName;
	}else{
		var arrayChecked = findChecked(elName);
	}



	if (arrayChecked.length == 0){
		alert(validateMessage.CODE_10006);
		return false;
	}

	var arrayCheckedTr = null;
	var notFoundTr = true;
	var limit = 10;
	var checked = arrayChecked[0];
	while (true) {
		if ((checked.tagName == 'TR') || (checked.tagName == 'tr')) {
			notFoundTr = false;
			break;
		} else {
			if (arrayCheckedTr == null) {
				arrayCheckedTr = arrayChecked.parent();
			} else {
				arrayCheckedTr = arrayCheckedTr.parent();
			}
			checked = arrayCheckedTr[0];
		}

		limit--;
		if (limit < 0) {
			break;
		};
	}

	if (notFoundTr){
		alert(validateMessage.CODE_10001);
		return false;
	}

	return arrayCheckedTr;
}

/**
* ตรวจสอบการเลือกข้อมูล
* elName คือ ชื่อของ input ที่ต้องการตรวจสอบ
* return 0 เมื่อไม่มีการเลือกข้อมูล
* return >= 1  เมื่อเลือกข้อมูล
*/
function findChecked(checkboxName) {
		return jQuery("input[name='" + checkboxName + "']:checked");
}

/**
* ตรวจสอบการเลือกข้อมูล
* elName คือ ชื่อของ input ที่ต้องการตรวจสอบ
* return false เมื่อไม่พบการเลือก
* return true เมื่อเลือกข้อมูล
*/
function validateSelect(elName){
	if (findChecked(elName).length == 0) {
		if(document.getElementsByName(elName)[0].type == 'radio'){
			alert(validateMessage.CODE_10038);
		}else{
			alert(validateMessage.CODE_10006);
		}
		// alert("คุณต้องทำการเลือกอย่างน้อย 1 รายการ");
		return false;
	} else {
		return true;
	}
}

// ตรวจสอบรูปแบบ ip address
function validateIpaddressFormatFormIndex(el, msg) {
	if (msg == undefined) {
		msg = validateMessage.CODE_10002;
	}

	var elHtml = el;
	// คืนสถานะของ ip address เป็น require input
	jQuery("#"+jQuery(elHtml).attr('id').replace("_IPADDRESS_BLOCK_1", "").replace("_IPADDRESS_BLOCK_2", "").replace("_IPADDRESS_BLOCK_3", "").replace("_IPADDRESS_BLOCK_4", "")).input_ipaddress('toRequireFormat');
	var status = checkIpaddressFormat(elHtml);
	if(!elHtml.disabled && (trim(elHtml.value) != '') && (status != true)){
		alert(msg);
		status.focus();
		jQuery("#"+jQuery(elHtml).attr('id').replace("_IPADDRESS_BLOCK_1", "").replace("_IPADDRESS_BLOCK_2", "").replace("_IPADDRESS_BLOCK_3", "").replace("_IPADDRESS_BLOCK_4", "")).input_ipaddress('toRequireSelectFormat');
		return false;
	}
	return true;
}

function checkIpaddressFormat(elHtml){
	return jQuery("#"+jQuery(elHtml).attr('id').replace("_IPADDRESS_BLOCK_1", "").replace("_IPADDRESS_BLOCK_2", "").replace("_IPADDRESS_BLOCK_3", "").replace("_IPADDRESS_BLOCK_4", "")).input_ipaddress('validateIpaddressValue');
}

/*
 * ตรวจสอบรูปแบบ email
 */
function validateEmailFormat(elementIds, msg) {

	var elArray = elementIds.split(',');
	for (var index in elementIds.split(',')) {
		var el = document.getElementById(trim(elArray[index]));
		if (validateEmailFormatFormIndex(el, msg) == false) {
			return false;
		}
	}
	return true;
}

/*
 * ตรวจสอบรูปแบบ บัตรประชาชน
 */
function validateIDCardFormat(elementIds, msg) {

	var elArray = elementIds.split(',');
	for (var index in elementIds.split(',')) {
		var el = document.getElementById(trim(elArray[index]));
		if (validateIDCardFormatFormIndex(el, msg) == false) {
			return false;
		}
	}
	return true;
}

function validateIDCardFormatFormIndex(el, msg) {
	if (msg == undefined) {
		msg = validateMessage.CODE_10034.replace('xx',el.value);
	}
	var elHtml = el;
	elHtml.className = elHtml.className.replace("requireIDCardSelect", "input_idcard");
	if(!elHtml.disabled && (trim(elHtml.value) != '') && !checkIDCardFormat(trim(elHtml.value))){
		alert(msg);
		elHtml.focus();
		elHtml.className = elHtml.className.replace("input_idcard", "requireIDCardSelect");
		return false;
	}
	return true;
}

//*** ตรวจสอบหมายเลขบัตรประชาชน  กรณีที่เลือกประเภทเอกสารอ้างอิง เป็นบัตรประชาชน ***
function checkIDCardFormat(id){
	if(id.length != 13) return false;
    for(i=0, sum=0; i < 12; i++)
        sum += parseFloat(id.charAt(i))*(13-i);
    if((11-sum%11)%10!=parseFloat(id.charAt(12))) return false;
    return true;
}

function msieversion() {
    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE ");
    if (msie > 0) {      // If Internet Explorer, return version number
        //alert(parseInt(ua.substring(msie + 5, ua.indexOf(".", msie))));
        return parseInt(ua.substring(msie + 5, ua.indexOf(".", msie)));
    } else {               // If another browser, return 0
        //alert('otherbrowser');
        return 999;
    }
}

/**
 * ตรวจสอบ รูปแบบ วัน เดือน เกิด แยกช่อง text
 * @param eleDD : id text วัน
 * @param eleMM : id text เดือน
 * @param eleYYYY : id text ปี
 */
function checkDateInput(eleDD ,eleMM ,eleYYYY){

	 jQuery("#"+eleDD).blur(function() {
		  	var dd = jQuery(this).val();

		  	if(dd != '') {

			  	if(dd >= 1 && dd <= 31 || parseInt(dd) == 0) {
			  		if(dd.length == 1) {
			  			jQuery(this).val("0" + dd);
			  		}
			  	} else {
			  		jQuery(this).val("");
			  	}
		  	} else {
		  		jQuery(this).val("");
		  	}

		  	validateDate(eleDD,eleMM);
	 });

	jQuery("#"+eleMM).blur(function() {
	  	var mm = jQuery(this).val();

	  	if(mm != '') {

		  	if(mm >= 1 && mm <= 12 || parseInt(mm) == 0) {
		  		if(mm.length == 1) {
		  			jQuery(this).val("0" + mm);
		  		}
		  	} else {
		  		jQuery(this).val("");
		  	}
	  	} else {
	  		jQuery(this).val("");
	  	}

	  	validateDate(eleDD,eleMM);
	});

	jQuery("#"+eleYYYY).blur(function() {
	  	var yyyy = jQuery(this).val();

	  	if(yyyy.length != 4) {
	  		jQuery(this).val("");
	  	}
	});
}

function validateDate(eleDD ,eleMM) {
	var dd = jQuery("#"+eleDD).val();
	var mm = jQuery("#"+eleMM).val();

	if(dd != '' && mm != '') {

		var month = parseInt(mm);
		var day = parseInt(dd);

		if(parseInt(dd) > 0 && parseInt(mm) > 0) {

			if(month == 4 || month == 6 || month == 9 || month == 11) {

				if(day > 30) {
					jQuery("#"+eleDD).val("30");
				}
			} else	if (month == 2) {
				if(day > 29) {
					jQuery("#"+eleDD).val("29");
				}

			} else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				if(day > 31) {
					jQuery("#"+eleDD).val("31");
				}
			}
		}
	}
}

function validateEmailFormatFormIndex(el) {
	if(!checkEmailFormat(trim(jQuery(el).val()))){
		return false;
	}
	return true;
}

function checkEmailFormat(elementValue){
	  var emailPattern = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	  return emailPattern.test(elementValue);
}


function checkGetherCurrentDate(val){
	var date = val.substring(0, 2);
	var month = val.substring(3, 5);
	var year = val.substring(6, 10);

	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!

	var yyyy = today.getFullYear();
	if(dd<10){
	    dd='0'+dd
	}
	if(mm<10){
	    mm='0'+mm
	}
	var todayStr = yyyy+""+mm+""+dd;
	var dateToCompare = year+month+date;

	if (dateToCompare > todayStr) {
	    return true;
	}
	else {
	    return false;
	}

}


