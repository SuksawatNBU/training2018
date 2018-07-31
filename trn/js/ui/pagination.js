function naviSubmit(start, range, formIndex, action, criteriaPrefix) {
	//alert(start + "," + range + "," + formName + "," + action);
	var form = document.forms[formIndex];
	form[criteriaPrefix + ".start"].value = start;
	form.action = action;
	//form.submit();
	submitPage(action);
}

function naviAjax (start, range, formIndex, action, criteriaPrefix) {
	jQuery("input[id='" + criteriaPrefix + "_start']").each(function() {
		jQuery(this).val(start);
	});
}