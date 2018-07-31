jQuery(function() {
	//Example button with icon
	jQuery("#btnIcon").button({
		icons: {
			primary: "ui-icon-locked"
		}
	});


	var ids = jQuery('.jbutton').map(function () {
					return this.id;
			  }).get().join();


	jQuery('.jbutton').each(function () {
	    var classobj = jQuery("#"+this.id).attr("class");
	    var arg = classobj.split(" ");
	    
	    jQuery("#"+this.id).attr('class', 'ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-primary');
	  	jQuery("#"+this.id).button({
			icons: {
				primary: arg[1]
			}
		});
	});


	});