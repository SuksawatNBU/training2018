
/**
 * ใช้สำหรับ submit กรณีเพิ่ม, แก้ไข, แสดง
 * ต้องใช้ร่วมกับไฟล์ inputmethod.js
 * @param action
 * @param emName (hidden name ของ id ใน model)
 * @param valId
 */
function submitAction(action, elName, valId){
	if(valId != null && valId != ""){
		document.getElementsByName(elName)[0].value = valId;
	}
	submitPage(action);
}

/**
 * submitStatus เป็นตัวกลางในการเปลี่ยนสถานะใช้งาน หรือยกเลิก
 * @param action
 * @param active
 * @param msg
 */
function submitStatus(action, active, msg){

	var chk = validateSelect('criteria.selectedIds');		//name ของ checkbox table กลางเป็นคน gen ให้โดยชื่อเดียวกัน
	if (chk == false) {
		return false;
	}

	if(confirm(msg) == false){
		return false;
	}

	document.getElementsByName('criteria.statusForUpdate')[0].value = active;	//ต้องสร้างให้เนื่องจากเป็น attr ที่อยู่ใน CommonDomain
	submitPage(action);
}

function submitAjaxStatus(active, msg){

	var chk = validateSelect('criteria.selectedIds');		//name ของ checkbox table กลางเป็นคน gen ให้โดยชื่อเดียวกัน
	if (chk == false) {
		return false;
	}

	if(confirm(msg) == false){
		return false;
	}

	document.getElementsByName('criteria.statusForUpdate')[0].value = active;	//ต้องสร้างให้เนื่องจากเป็น attr ที่อยู่ใน CommonDomain
	return true;
}