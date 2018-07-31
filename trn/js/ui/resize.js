
jQuery( document ).ready(function() {
	resizeBodyCss();
	resizeResultCss();
	jQuery("#lastTH").width(jQuery("#lastTD").width());
	jQuery("#firstTRofTbody").height(jQuery("#firstTRofThead").height());

	var genarateFormHeight = jQuery(window).height() - (2 + jQuery("#BODY_TR_BANNER").height() + jQuery("#BODY_TR_HEADER").height() + jQuery("#BODY_TR_FOOTER").height());
	var currentFormHeight = jQuery("#BODY_TR_CONTENT").height();
	if (currentFormHeight < genarateFormHeight) {
		jQuery("#BODY_TD_CONTENT").height(genarateFormHeight);
	}
});

jQuery( window ).resize(function() {
	resizeBodyCss();
	resizeResultCss();
	jQuery("#lastTH").width(jQuery("#lastTD").width());
	jQuery("#firstTRofTbody").height(jQuery("#firstTRofThead").height());

});

function resizeBodyCss() {
	var el = jQuery("table[class*='body_']");
	var windowWidth = jQuery( window ).width();
	if (windowWidth <= 1024) {
		el.removeClass();
		el.addClass('body body_1024');

	} else if (windowWidth <= 1152) {
		el.removeClass();
		el.addClass('body body_1152');

	} else if (windowWidth <= 1280) {
		el.removeClass();
		el.addClass('body body_1280');

	} else if (windowWidth <= 1366) {
		el.removeClass();
		el.addClass('body body_1366');

	} else if (windowWidth <= 1400) {
		el.removeClass();
		el.addClass('body body_1400');

	} else if (windowWidth <= 1440) {
		el.removeClass();
		el.addClass('body body_1440');

	} else if (windowWidth <= 1600) {
		el.removeClass();
		el.addClass('body body_1600');

	} else if (windowWidth <= 1680) {
		el.removeClass();
		el.addClass('body body_1680');

	} else if (windowWidth <= 1920) {
		el.removeClass();
		el.addClass('body body_1920');

	} else if (windowWidth <= 2048) {
		el.removeClass();
		el.addClass('body body_2048');

	} else if (windowWidth <= 2560) {
		el.removeClass();
		el.addClass('body body_2560');

	} else {
		el.removeClass();
		el.addClass('body body_2560');
	}
}

function resizeResultCss() {
	var el = jQuery("div[class*='RESULT RESULT_']");
	var windowWidth = jQuery( window ).width();
	if (windowWidth <= 1024) {
		el.removeClass();
		el.addClass('RESULT RESULT_1024');

	} else if (windowWidth <= 1152) {
		el.removeClass();
		el.addClass('RESULT RESULT_1152');

	} else if (windowWidth <= 1280) {
		el.removeClass();
		el.addClass('RESULT RESULT_1280');

	} else if (windowWidth <= 1366) {
		el.removeClass();
		el.addClass('RESULT RESULT_1366');

	} else if (windowWidth <= 1400) {
		el.removeClass();
		el.addClass('RESULT RESULT_1400');

	} else if (windowWidth <= 1440) {
		el.removeClass();
		el.addClass('RESULT RESULT_1440');

	} else if (windowWidth <= 1600) {
		el.removeClass();
		el.addClass('RESULT RESULT_1600');

	} else if (windowWidth <= 1680) {
		el.removeClass();
		el.addClass('RESULT RESULT_1680');

	} else if (windowWidth <= 1920) {
		el.removeClass();
		el.addClass('RESULT RESULT_1920');

	} else if (windowWidth <= 2048) {
		el.removeClass();
		el.addClass('RESULT RESULT_2048');

	} else if (windowWidth <= 2560) {
		el.removeClass();
		el.addClass('RESULT RESULT_2560');

	} else {
		el.removeClass();
		el.addClass('RESULT RESULT_2560');
	}
}