jQuery(function() {
	$colorTheme = jQuery(".ui-state-active").css("background-color");
	$colorBorderDefault = jQuery(".ui-state-default").css("background-color");

	var id;
	jQuery.fn.fullmenu = function() {
		id = this.selector;
		
		// if mouse hover at menu with addclass hilight color
		jQuery('.fg-button').hover(
			function() {
				jQuery(this).removeClass('ui-state-default').addClass('ui-state-focus');
			},
		
			function() {
				jQuery(this).removeClass('ui-state-focus').addClass('ui-state-default');
			}
		);

		jQuery('.fgButton > li > a').hover(
			function() {
				jQuery(this).parent().removeClass('ui-state-hover').addClass('ui-state-hover');
			},
			function() {
				jQuery(this).parent().removeClass('ui-state-hover');
			}
		);
		
	};

	// new Table by loop td
	var i = 1;
	var newTable = "";
	var count = jQuery(".tableStyle td").length;

	// get value P_CODE by at hilight color system by working
	var pCode = document.getElementsByName("P_CODE").length;

	if (pCode > 0) {
		/*Arunya.k*/
		/*jQuery("a[id*='" + document.getElementsByName("P_CODE")[0].value + "']").parent().addClass("ui-state-focus");*/
		jQuery("a[id*='" + document.getElementsByName("P_CODE")[0].value + "']").parent().addClass("ui-state-active");
	}

	// check event click focus popupMenu if click at focusIn No event popup eles
	// click focusOut with hidden popup
	jQuery(document).on("click", function(event) {
		var formId = id.substring(1, id.length);

		if (event.target.id == formId || event.target.id == "ui-cilck-popup") {
			hoverPopup();
		} else {
			var x = event.pageX;
			var y = event.pageY;

			var width = jQuery(".menuFullTest").width();
			var height = jQuery(".menuFullTest").height() + 130;

			if ((x > 23 && x < width && y > 128 && y < height)) {

			} else {
				// KAM remove สี
				jQuery("#flyout").css({
					"border" : "1px solid " + $colorBorderDefault + "",
					"border-bottom-right-radius" : "none",
					"border-bottom-right-radius" : "none"
				});

				jQuery(id).removeClass('ui-state-active');
				// jQuery(".menuFullTest").addClass('hidden');
				jQuery(".menuFullTest").hide(500);
			}
		}
	});

	jQuery("#flat").click(function() {

		// KAM remove สี
		jQuery("#flyout").css({
			"border" : "1px solid " + $colorBorderDefault + "",
			"border-bottom-right-radius" : "none",
			"border-bottom-right-radius" : "none"
		});

		jQuery(id).removeClass('ui-state-active');
		// jQuery(".menuFullTest").addClass('hidden');
		jQuery(".menuFullTest").hide(500);

	});

	/*
	 * check event click button menu(MenuFull System) by with check popup that
	 * have class hidden? if have with add class "ui-state-active" at button and
	 * remove class popup "hidden" at with show popup
	 */

	function hoverPopup() {

		if (jQuery(".menuFullTest").is(':hidden')) {
			jQuery(id).addClass('ui-state-active');
			jQuery(".menuFullTest").show(500);

			// KAM Add boder color
			$colorTheme = jQuery(".ui-state-active").css("background-color");

			jQuery(".ui-state-active").css({
				"border" : "1px solid " + $colorTheme + " ",
				"" : ""
			});
			jQuery(".ui-state-active").css({
				"border-bottom-right-radius" : "0px",
				"" : ""
			});
			jQuery(".ui-state-active").css({
				"border-bottom-left-radius" : "0px",
				"" : ""
			});

			jQuery(".menuFullTest").css({
				"border" : "5px solid " + $colorTheme + " ",
				"opacity" : "1px",
				"border-radius" : "5px",
				"border-top-left-radius" : "0px"
			});

			/*
			 */
		} else {

			// KAM remove สี
			jQuery("#flyout").css({
				"border" : "1px solid " + $colorBorderDefault + "",
				"border-bottom-right-radius" : "none",
				"border-bottom-right-radius" : "none"
			});

			jQuery(id).removeClass('ui-state-active');
			jQuery(".menuFullTest").hide(500);

		}

	}
});
