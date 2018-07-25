//eng (a-z), (A-Z), backspace, tab, enter, shift, ctrl, space, left arrow, right arrow, delete, home, end
function chkEngInput(e) {
	var keynum = (e.keyCode || e.which);
	//console.log(keynum);
	
	if ((keynum >= 65 && keynum <=  90) || (keynum >= 97 && keynum <=  122) || keynum == 8 || keynum == 9 || keynum == 13  || keynum == 16 || keynum == 17 || keynum == 32){
    	return true;
	}else{
		// alert("กรุณาระบุข้อมูลเป็นภาษาอังกฤษ");
		return false;
	}
}

//eng (a-z), (A-Z), backspace, tab, enter, shift, ctrl, left arrow, right arrow, delete, home, end
function chkEngNotSpaceInput(e) {
	var keynum = (e.keyCode || e.which);
	// console.log(keynum);
	
	if ((keynum >= 65 && keynum <=  90) || (keynum >= 97 && keynum <=  122) || keynum == 8 || keynum == 9 || keynum == 13  || keynum == 16 || keynum == 17){
    	return true;
	}else{
		// alert("กรุณาระบุข้อมูลเป็นภาษาอังกฤษ");
		return false;
	}
}

// 0-9, +, backspace, tab, enter, shift, ctrl, left arrow, right arrow, delete, home, end
function chkNumberAndPlus(event){
	var keynum = (event.keyCode || event.which);
	// console.log(keynum);
	if ((keynum>=48 && keynum<=57) || keynum == 43 || keynum == 8 || keynum == 9 || keynum == 13  || (keynum >= 16 && keynum <= 20)){
		return true;
	 }else{
	    return false;
	 }
}

//0-9, backspace, tab, enter, shift, ctrl, left arrow, right arrow, delete, home, end
function chkNumber(event){
	var keynum = (event.keyCode || event.which);
	if ((keynum>=48 && keynum<=57) || keynum == 8 || keynum == 9 || keynum == 13  || (keynum >= 16 && keynum <= 20)){
    	    return true;
	 }else{
	    return false;
	 }
}
//for key 0-9, a-z,A-Z, delete, backspace, ctrl, end, home, tab, left arrow, right arrow, enter, home, end
function chkCharAndNumber(event){
	var keynum = (event.keyCode || event.which);
	// console.log(keynum);
	
	if ((keynum>=48 && keynum<=57) || (keynum>=65 && keynum<=90) || (keynum>=97 && keynum<=122)||(keynum == 8)||(keynum == 17) || (keynum == 9) || (keynum == 13)){
		return true;
	 }else{
	    return false;
	 }
}

function trim(str) {
	if(str != undefined) {
		return str.replace(/^\s+|\s+$/g,"");
	}
}

//eng (a-z), (A-Z), space, hyphen, apostrophe, backspace, tab, enter, shift, ctrl, left arrow, right arrow, delete, home, end
function chkEngSpaceApostropheHyphenInput(e) {
	var keynum = (e.keyCode || e.which);
	// console.log(keynum);
	
	if ((keynum >= 65 && keynum <=  90) || keynum == 32 || keynum == 45 || keynum == 39 || (keynum >= 37 && keynum <= 40)|| (keynum >= 97 && keynum <=  122) || keynum == 8 || keynum == 9 || keynum == 13  || keynum == 16 || keynum == 17){
	return true;
	}else{
		// alert("กรุณาระบุข้อมูลเป็นภาษาอังกฤษ");
		return false;
	}
}
