function orderByHeaderWithEvent(myIndex, prifix, form, action, event) {
	if (event.ctrlKey == 1) {
		multipleOrderByHeader(myIndex, prifix);
	} else {
		singleOrderByHeader(myIndex, prifix);
	}

	form.action = action;
	form.submit();
}

// function multipleOrderByHeader(myIndex, prifix, form, action) {
function multipleOrderByHeader(myIndex, prifix) {
	var size = document.getElementsByName(prifix + '.headerSortsSize')[0].value;
	var myTemp = document.getElementsByName(prifix + '.headerSortsSelect')[0];
	myTemp.value = '';
	
	changeSeqOwner(prifix + '.headerSorts[' + myIndex + '].seq', myIndex);
	for (var i = 0; i < size; i++) {
		var elHtml = document.getElementsByName(prifix + '.headerSorts[' + i + '].seq')[0];
		if (elHtml !== undefined) { 
			var seq = elHtml.value;
			if (seq == '0') {
				if (myTemp.value.length == 0) {
					myTemp.value = i;
				} else {
					myTemp.value = myTemp.value + "," + i;
				}
			}
		}
	}
	changeOrderOwner(prifix + '.headerSorts[' + myIndex + '].order', myIndex);
}

function singleOrderByHeader(myIndex, prifix) {
	var size = document.getElementsByName(prifix + '.headerSortsSize')[0].value;
	var myTemp = document.getElementsByName(prifix + '.headerSortsSelect')[0];
	myTemp.value = myIndex;
	
	for (var i = 0; i < size; i++) {
		if (myTemp.value != i) {
			clearOrderOwner(prifix + '.headerSorts[' + i + '].order', i);
			clearSeqOwner(prifix + '.headerSorts[' + i + '].seq', i);
		}
	}
	changeOrderOwner(prifix + '.headerSorts[' + myTemp.value + '].order', myTemp.value);
	changeSeqOwner(prifix + '.headerSorts[' + myTemp.value + '].seq', myTemp.value);
}

function orderByHeader(myIndex, prifix, form, action) {
	var elName = prifix;
	var size = document.getElementsByName(elName + 'Size')[0].value;
	var myTemp = document.getElementsByName(elName + 'Select')[0];
	//alert(myTemp.value.indexOf(','));
	if (myTemp.value.length == 0) {
		myTemp.value = myIndex;
	} else if (myTemp.value.length == ((size * 2) - 1)) {
		var myTempArr = myTemp.value.split(',');
		var myTempArrIndex = -1;
		for (var i = 0; i < myTempArr.length; i++) {
			if (myTempArr[i] == myIndex) {
				myTempArrIndex = i;
				break;
			}
		}

		myTemp.value = myIndex;
		if (myTempArrIndex == -1) {
			for (var i = 0; i < size - 1; i++) {
				myTemp.value = myTemp.value + "," + myTempArr[i];
			}
		} else {
			for (var i = 0; i < myTempArr.length; i++) {
				if (i == myTempArrIndex) {
					continue;
				} else {
					myTemp.value = myTemp.value + "," + myTempArr[i];
				}
			}
		}
	} else {
		var index = myTemp.value.indexOf(',');
		if ((index == -1) && (myTemp.value == myIndex)) {
			myTemp.value = myIndex;
		} else {
			var myTempArr = myTemp.value.split(',');
			var myTempArrIndex = -1;
			for (var i = 0; i < myTempArr.length; i++) {
				if (myTempArr[i] == myIndex) {
					myTempArrIndex = i;
					break;
				}
			}

			if (myTempArrIndex == -1) {
				myTemp.value = myIndex + "," + myTemp.value;
			} else {
				myTemp.value = myIndex;
				for (var i = 0; i < myTempArr.length; i++) {
					if (i == myTempArrIndex) {
						continue;
					} else {
						myTemp.value = myTemp.value + "," + myTempArr[i];
					}
				}
			}
		}
	}

	for (var i = 0; i < size; i++) {
		document.getElementsByName(elName + '[' + i + '].seq')[0].value = '';
	}

	var index = myTemp.value.indexOf(',');
	if (index == -1) {
		document.getElementsByName(elName + '[' + myTemp.value + '].seq')[0].value = 0;
		if  (myTemp.value == myIndex) {
			changeOrderOwner(elName + '[' + myTemp.value + '].order', myTemp.value);
		}
	} else {
		var myTempArr = myTemp.value.split(',');
		for (var i = 0; i < myTempArr.length; i++) {
			document.getElementsByName(elName + '[' + myTempArr[i] + '].seq')[0].value = i;
			if  (myTempArr[i] == myIndex) {
				changeOrderOwner(elName + '[' + myTempArr[i] + '].order', myTempArr[i]);
			}
		}
	}
	form.action = action;
	form.submit();
}

function changeOrderOwner(elName, index) {
	var elHtml = document.getElementsByName(elName)[0];
	if (elHtml !== undefined) {
		if (elHtml.value == '') {
			elHtml.value = 'ASC';
		} else if (elHtml.value == 'ASC') {
			elHtml.value = 'DESC';
		} else if (elHtml.value == 'DESC') {
			elHtml.value = 'ASC';
		}
	}
}

function changeSeqOwner(elName, index) {
	var elHtml = document.getElementsByName(elName)[0];
	if (elHtml !== undefined) {
		elHtml.value = '0';
	}
}

function clearOrderOwner(elName, index) {
	var elHtml = document.getElementsByName(elName)[0];
	if (elHtml !== undefined) {
		elHtml.value = 'ASC';
	}
}

function clearSeqOwner(elName, index) {
	var elHtml = document.getElementsByName(elName)[0];
	if (elHtml !== undefined) {
		elHtml.value = '';	
	}
}

function orderByHeaderWithEventForDialog(myIndex, prifix, event, owner) {
	owner = jQuery("#" + owner).parent().attr('id');
	if (event.ctrlKey == 1) {
		multipleOrderByHeaderForDialog(myIndex, prifix, owner);
	} else {
		singleOrderByHeaderForDialog(myIndex, prifix, owner);
	}
}

function multipleOrderByHeaderForDialog(myIndex, prifix, owner) {
	var size = jQuery("#" + owner + " #" + prifix + '_headerSortsSize').val();
	var myTemp = jQuery("#" + owner + " #" + prifix + '_headerSortsSelect');
	myTemp.val('');
	
	changeSeqOwnerForDialog(owner, myIndex);
	for (var i = 0; i < size; i++) {
		var seq = jQuery("#" + owner + " #seq_" + i).val();
		if (seq == '0') {
			if (myTemp.val().length == 0) {
				myTemp.val(i);
			} else {
				myTemp.val(myTemp.val() + "," + i);
			}
		}
	}
	changeOrderOwnerForDialog(owner, myIndex);
}

function singleOrderByHeaderForDialog(myIndex, prifix, owner) {
	var size = jQuery("#" + owner + " #" + prifix + '_headerSortsSize').val();
	var myTemp = jQuery("#" + owner + " #" + prifix + '_headerSortsSelect');
	myTemp.val(myIndex);
	for (var i = 0; i < size; i++) {
		if (myTemp.val() != i) {
			clearOrderOwnerForDialog(owner, i);
			clearSeqOwnerForDialog(owner, i);
		}
	}
	changeOrderOwnerForDialog(owner, myIndex);
	changeSeqOwnerForDialog(owner, myIndex);
}

function clearOrderOwnerForDialog(owner, index) {
	jQuery("#" + owner + " #order_" + index).val('ASC');
	jQuery("#" + owner + " #orderActive_" + index).removeClass().addClass("ORDER_INACTIVE showTooltipH");
}

function clearSeqOwnerForDialog(owner, index) {
	jQuery("#" + owner + " #seq_" + index).val('');
	jQuery("#" + owner + " #i_triangle_" + index).removeClass().addClass("DataTables_sort_icon css_right ui-icon ui-icon-triangle-1-n");
}

function changeOrderOwnerForDialog(owner, index) {
	var elHtml = jQuery("#" + owner + " #order_" + index);
	if (elHtml !== undefined) {
		if (elHtml.val() == '') {
			elHtml.val('ASC');
			jQuery("#" + owner + " #i_triangle_" + index).removeClass().addClass("DataTables_sort_icon css_right ui-icon ui-icon-triangle-1-n");
		} else if (elHtml.val() == 'ASC') {
			elHtml.val('DESC');
			jQuery("#" + owner + " #i_triangle_" + index).removeClass().addClass("DataTables_sort_icon css_right ui-icon ui-icon-triangle-1-s");
		} else if (elHtml.val() == 'DESC') {
			elHtml.val('ASC');
			jQuery("#" + owner + " #i_triangle_" + index).removeClass().addClass("DataTables_sort_icon css_right ui-icon ui-icon-triangle-1-n");
		}
	}
}

function changeSeqOwnerForDialog(owner, index) {
	jQuery("#" + owner + " #seq_" + index).val('0');
	jQuery("#" + owner + " #orderActive_" + index).removeClass().addClass("ORDER_ACTIVE showTooltipH");
}
