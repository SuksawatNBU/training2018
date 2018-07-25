/**
 * Standard button
 */

function initButton(){
	//Default button
	jQuery(":button").button();

	//ปุ่มและ icon
	jQuery(".btnSearch").button({
		icons: {
			primary: "ui-icon-search"
	    }
	});

	jQuery(".btnRefresh").button({
	    icons: {
	        primary: "ui-icon-refresh"
	    }
	});

	jQuery(".btnEdit").button({
	    icons: {
	        primary: "ui-icon-disk"
	    }
	});

	jQuery(".btnCancel").button({
	    icons: {
	        primary: "ui-icon-close"
	    }
	});

	jQuery(".btnAdd").button({
	    icons: {
	        primary: "ui-icon-disk"
	    }
	});

	jQuery(".btnPrint").button({
	    icons: {
	        primary: "ui-icon-print"
	    }
	});

	jQuery(".btnClear").button({
	    icons: {
	        primary: "ui-icon-close"
	    }
	});
	
	jQuery(".btnClose").button({
	    icons: {
	        primary: "ui-icon-close"
	    }
	});
	
	jQuery(".btnSave").button({
	    icons: {
	        primary: "ui-icon-disk"
	    }
	});
	
	jQuery(".btnTest").button({
		icons: {
			primary: "ui-icon-power"
		}
	});
}