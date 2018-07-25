<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<link rel="stylesheet" type="text/css" href="<s:url value='/css/style/shadow/shadowStyle.css' />"> 
<link rel="stylesheet" type="text/css" href="<s:url value='/css/style/flat/flatStyle.css' />"> 
<link rel="stylesheet" type="text/css" href="<s:url value='/css/style/tamjai/tamjaiStyle.css' />"> 

<script>
	var styleClassRadio,styleClassCheckbox,styleClassTextbox,styleClassButton,styleClassSelect,styleClassAutocomplete,styleClassSuggestion;
	var $colorTheme = jQuery(".ui-state-default").css("background-color");
	var $colorThemeCheckboxRadio = jQuery("#dood_color_ui_state_active").css("background-color");
	var $colorHover;
	var $colorFont;
	var $colorBorder;
	var colorShade;
	var colorShadeBorder;

	var widthArray = []; //เก็บค่าความกว้างไว้ใส่ใน select

	var styleRadio,styleCheckbox,styleText,styleButton,styleSelect,styleAutocomplete,styleSuggestion;
	var chkAjaxComplete = false; //ถ้าเข้า  ajax complete ให้ = true (ไม่ให้ไปเซท autocomplete ซำ้)
	var countAjaxComplete = 0;   //เวลาโหลด autocomplete ajax จังหวัด อำเภอ จะเข้า ajaxComplete ก่อน set class เลยมีปัญหา เวลาเข้าครั้งแรกเลยไม่ให้เข้า ajaxComplete ครับ
							 
	var cssStyle = "flatStyle";

	jQuery(document).ajaxComplete(function(){
		var url = window.location.href;
		if(url.indexOf("StyleAndTheme.action") > 0){
			//ท้าอยู่หน้า Style and Theme  ajax complete ไม่ต้องทำอะไร
			styleClassAutocomplete = styleAutocomplete;
		}else{
			if(countAjaxComplete > 0){
				chkAjaxComplete = true;
				setClassAll();
			}
		}
	});

	/**
	 * SET CLASS PAGE STYLE AND THEME
	 */
	function setClassStyleAndTheme(){
		countAjaxComplete = 1;
		/* styleRadio 		= "<s:property value='#session.changeStyle[\"radio\"]' />";
		styleCheckbox 	= "<s:property value='#session.changeStyle[\"checkbox\"]' />"; */
		styleRadio 		= cssStyle;
		styleCheckbox 	= cssStyle;
		styleText 		= "<s:property value='#session.changeStyle[\"textbox\"]' />";
		styleButton		= "<s:property value='#session.changeStyle[\"button\"]' />";
		styleSelect		= "<s:property value='#session.changeStyle[\"selectitem\"]' />";
		styleAutocomplete	= "<s:property value='#session.changeStyle[\"autocomplete\"]' />";
		styleSuggestion		= "<s:property value='#session.changeStyle[\"suggestion\"]' />";
		
		changeRadioType();
		chageCheckboxType();
		
		// สำหรับ table ที่มี checkbox
		if ((styleCheckbox != 'classicStyle' ) && (styleCheckbox != 'tamjaiStyle')) {
			if(jQuery(".RESULT_HEADER > tbody > tr > th > input[type='checkbox']:visible").length > 0) {
				jQuery(".RESULT_HEADER > tbody > tr > th > input[type='checkbox']:visible").each( function() {
					jQuery(".RESULT").css('height', '+=4px;');
				});
				
				jQuery(".LINE_ODD > td > input[type='checkbox']:visible").each( function() {
					jQuery(".RESULT").css('height', '+=4px;');
				});
				jQuery(".LINE_EVEN > td > input[type='checkbox']:visible").each( function() {
					jQuery(".RESULT").css('height', '+=4px;');
				});	
			}
		} else if (styleCheckbox == 'tamjaiStyle') {
			if(jQuery(".RESULT_HEADER > tbody > tr > th > input[type='checkbox']:visible").length > 0) {
				jQuery(".RESULT_HEADER > tbody > tr > th > input[type='checkbox']:visible").each( function() {
					jQuery(".RESULT").css('height', '-=2px;');
				});
				
				jQuery(".LINE_ODD > td > input[type='checkbox']:visible").each( function() {
					jQuery(".RESULT").css('height', '-=2px;');
				});
				jQuery(".LINE_EVEN > td > input[type='checkbox']:visible").each( function() {
					jQuery(".RESULT").css('height', '-=2px;');
				});	
			}
		}
		
		//INCLUDE CSS AND JS FROM DROPDOWN
		if(styleSelect != ""){
			var jsUrl = "<s:url value='/js/easydropdown/jquery.easydropdown.js' />";
			var jsFile = "<script src='"+jsUrl+"' />";
			jQuery("head").append(jsFile);
		
			jQuery("<link/>", {
				   rel: "stylesheet",
				   type: "text/css",
				   href: "<s:url value='/css/style/easydropdown/"+styleSelect+".css' />"
			}).appendTo("head");
	
		}
	
		//ADD CLASS TO RADIO
		jQuery("input[type=radio].styleTest").each(function(index){
			jQuery(this).addClass(styleRadio);
		});
	
		//ADD CLASS TO CHECKBOX
		jQuery("input[type=checkbox]").each(function(index){
			jQuery(this).addClass(styleCheckbox);
		});
	
		//ADD CLASS TO TEXTBBOX
		jQuery("input[type=text]").not(".custom-combobox-input").not(".suggestionClient").each(function(index){
			jQuery(this).addClass(styleText);
		});
	
		jQuery("input[type=password]").each(function(index){
			jQuery(this).addClass(styleText);
		});
	
		//ADD CLASS TO DROPDOWN
		jQuery("select").not(".cmd-hidden").each(function(index){
			var width = jQuery(this).css("width");
			if(typeof width != "undefined" && width != ""){
				widthArray.push(width);
			}else{
				widthArray.push("180px");
			}
			jQuery(this).addClass(styleSelect);
		});
	
		//ADD CLASS TO BUTTON
		jQuery("button.styleTest").each(function(index){
			jQuery(this).addClass(styleButton);
		});
	
		jQuery("input[type=button].styleTest").each(function(index){
			jQuery(this).addClass(styleButton);
		});
	
		//ADD CLASS TO AUTO COMPLETE
		jQuery("span.custom-combobox").each(function(index){
			jQuery(this).addClass(styleAutocomplete);
		});
	
		jQuery("input[type=text].custom-combobox-input").each(function(index){
			jQuery(this).addClass(styleAutocomplete);
		});
	
		//ADD CLASS TO SUGGESTION
		jQuery("input[type=text].suggestionClient").each(function(index){
			jQuery(this).addClass(styleSuggestion);
		});
	
		//SET DROPDOWN USE PLUGIN easyDropDown
		if(styleSelect == "demo2" || styleSelect == "shadowStyle" || styleSelect == "flatStyle" || styleSelect == "tamjaiStyle"){
			jQuery('select.demo2 ,select.shadowStyle, select.flatStyle, select.tamjaiStyle').easyDropDown({
				cutOff: 10
			});
		}
	
		//SET STYLE TO COMPONENT
		setStyle();
	}

	/**
	 * SET CLASS ALL PAGE
	 */
	function setClassAll(){
		countAjaxComplete = 1;
	
		/* styleRadio 		= "<s:property value='#session.changeStyleAll[\"radio\"]' />";
		styleCheckbox 	= "<s:property value='#session.changeStyleAll[\"checkbox\"]' />"; */
		styleRadio 		= cssStyle;
		styleCheckbox 	= cssStyle;
		styleText 		= "<s:property value='#session.changeStyleAll[\"textbox\"]' />";
		styleButton		= "<s:property value='#session.changeStyleAll[\"button\"]' />";
		styleSelect		= "<s:property value='#session.changeStyleAll[\"selectitem\"]' />";
		styleAutocomplete		= "<s:property value='#session.changeStyleAll[\"autocomplete\"]' />";
		styleSuggestion		= "<s:property value='#session.changeStyleAll[\"suggestion\"]' />";
		
		changeRadioType();
		chageCheckboxType();
	
		jQuery('[title]').tooltip();
		
		// สำหรับ table ที่มี checkbox
		if ((styleCheckbox == undefined ) || (styleCheckbox == '' ) ) {
			return;
		}
		
		if ((styleCheckbox != 'classicStyle' ) && (styleCheckbox != 'tamjaiStyle')) {
			if(jQuery(".RESULT_HEADER > tbody > tr > th > input[type='checkbox']:visible").length > 0) {
	
			}
		} else if (styleCheckbox == 'tamjaiStyle') {
			if(jQuery(".RESULT_HEADER > tbody > tr > th > input[type='checkbox']:visible").length > 0) {
	
			}
		}
		
		//INCLUDE CSS AND JS FROM DROPDOWN
		if(styleSelect != ""){
			var jsUrl = "<s:url value='/js/easydropdown/jquery.easydropdown.js' />";
			var jsFile = "<script src='"+jsUrl+"' />";
			jQuery("head").append(jsFile);
	
			jQuery("<link/>", {
				   rel: "stylesheet",
				   type: "text/css",
				   href: "<s:url value='/css/style/easydropdown/"+styleSelect+".css' />"
			}).appendTo("head");
		}
	
		//ADD CLASS TO RADIO
		jQuery("input[type=radio]").each(function(index){
			jQuery(this).addClass(styleRadio);
		});
	
		//ADD CLASS TO CHECKBOX
		jQuery("input[type=checkbox]").each(function(index){
			jQuery(this).addClass(styleCheckbox);
		});
	
		//ADD CLASS TO TEXTBOX
		jQuery("input[type=text]").not(".custom-combobox-input").not(".suggestionClient").each(function(index){
			jQuery(this).addClass(styleText);
		});
	
		jQuery("input[type=password]").each(function(index){
			jQuery(this).addClass(styleText);
		});
	
		//ADD CLASS TO DROPDOWN
		jQuery("select").not(".cmd-hidden").each(function(index){
			var width = jQuery(this).css("width");
			if(typeof width != "undefined" && width != ""){
				widthArray.push(width);
			}else{
				widthArray.push("180px");
			}
			jQuery(this).addClass(styleSelect);
		});
	
		//ADD CLASS TO BUTTON
		jQuery("button").each(function(index){
			jQuery(this).addClass(styleButton);
		});
	
	
		jQuery("input[type=button]").each(function(index){
			jQuery(this).addClass(styleButton);
		});
	
		//ADD CLASS TO AUTO COMPLETE
		jQuery("span.custom-combobox").each(function(index){
			jQuery(this).addClass(styleAutocomplete);
		});
	
		jQuery("input[type=text].custom-combobox-input").each(function(index){
			jQuery(this).addClass(styleAutocomplete);
		});
	
		//ADD CLASS TO SUGGESTION
		jQuery("input[type=text].suggestionClient").each(function(index){
			jQuery(this).addClass(styleSuggestion);
		});
	
		//SET DROPDOWN USE PLUGIN easyDropDown
		if(styleSelect == "demo2" || styleSelect == "shadowStyle" || styleSelect == "flatStyle" || styleSelect == "tamjaiStyle"){
			jQuery('select.demo2 ,select.shadowStyle, select.flatStyle, select.tamjaiStyle').easyDropDown({
				cutOff: 10
			});
				
		}
	
		//SET STYLE TO COMPONENT
		setStyle();
	}

	/**
	 * SET STYLE TO COMPONENT
	 */
	function setStyle(){
		//GET LAST CLASS FROM RAIDO , CHECKBOX
		$colorTheme 	= jQuery(".ui-state-default").css("background-color");
		$colorThemeCheckboxRadio = jQuery(".ui-state-active").css("background-color");
		$colorThemeBorder = jQuery(".ui-state-default").css("border-top-color");
		$colorThemeBorderCheckboxRadio = jQuery("#dood_color_ui_state_active").css("border-top-color");
		$colorThemeTextCheckboxRadio = jQuery(".ui-state-active").css("color");
		$colorFont 		= jQuery(".ui-state-default").css("color");
		$colorHover 	= jQuery(".ui-state-hover").css("background-color");
		$colorHoverBorder 	= jQuery(".ui-state-hover").css("border-top-color");
		$bgImage 		= jQuery(".ui-state-hover").css("background-image");
		$bgRepeat		= jQuery(".ui-state-hover").css("background-repeat");
		$bgPosition		= jQuery(".ui-state-hover").css("background-position");
	
		colorShade = shadeRGBColor($colorTheme, -0.7);
		colorShadeBorder = shadeRGBColor($colorTheme, -0.3);
	
		var classStr = jQuery("input[type='radio']").attr('class');
		if(typeof classStr != "undefined" && classStr != ""){
			styleClassRadio = classStr.substr(classStr.lastIndexOf(' ') + 1);
			setRadioStyle();
		}
		
		classStr = jQuery("input[type='checkbox']").attr('class');
		if(typeof classStr != "undefined" && classStr != ""){
			styleClassCheckbox = classStr.substr(classStr.lastIndexOf(' ') + 1);
			setCheckboxStyle(); 
		}
		
		classStr = jQuery("input[type=text]").attr('class');
		if(typeof classStr != "undefined" && classStr != ""){
			styleClassTextbox = classStr.substr(classStr.lastIndexOf(' ') + 1);
			setTextboxStyle(); 
		}
	
		var checkBtn = false;
		classStr = jQuery("button").attr('class');
		if(typeof classStr != "undefined" && classStr != ""){
			 checkBtn = true;
			 styleClassButton = classStr.substr(classStr.lastIndexOf(' ') + 1);
			 setButtonStyle(); 
		}
	
		if(!checkBtn){
			classStr = jQuery("input[type=button]").attr('class');
			if(typeof classStr != "undefined" && classStr != ""){
				 styleClassButton = classStr.substr(classStr.lastIndexOf(' ') + 1);
				 setButtonStyle(); 
			}
		}
	
		classStr = jQuery("select").not(".cmd-hidden").attr('class');
		if(typeof classStr != "undefined" && classStr != ""){
			 //var abccd = classStr.split(' ');
			 //styleClassSelect = abccd[0];
			 if(!chkAjaxComplete){
				 styleClassSelect = classStr.substr(classStr.lastIndexOf(' ') + 1);
				 setSelectStyle(); 
			 }
		}
	
		if(!chkAjaxComplete){
			styleClassAutocomplete = styleAutocomplete;
			chkAjaxComplete = false;
			setAutocomplete();
		}
	}

	/**
	 * SET RAIDO STYLE
	 */
	function setRadioStyle(){
		if(styleClassRadio == "shadowStyle"){
			var colorShade2 = shadeRGBColor($colorThemeCheckboxRadio, -0.2);
			//≠≠SET COLOR DEFAULT [SHADOW_STYLE]
			jQuery("input[type='radio'].shadowStyle + label").css({
					"background" 	: "",
					"border" 		: "1px solid "+$colorThemeCheckboxRadio
				});
	
			//≠≠SET COLOR DISABLE [SHADOW_STYLE]
			jQuery("input[type='radio'].shadowStyle:disabled + label").css({
					"background" 	: colorShade2,
					"border" 		: "1px solid "+$colorThemeCheckboxRadio
				});
			
			//≠≠SET COLOR CHECKED [SHADOW_STYLE]
			jQuery("input[type='radio'].shadowStyle:checked + label").css({
					"background" 	:  "",
					"border" 		: "1px solid "+$colorThemeCheckboxRadio,
					"color"			: $colorTheme
				});
	
			//
			//≠≠SET EVENT ON CHANGE
			//
			jQuery("input[type='radio'].shadowStyle").change(function() {
	
				//≠≠SET COLOR DEFAULT [SHADOW_STYLE]
				jQuery("input[type='radio'].shadowStyle + label").css({
						"background" 	: "",
						"border" 		: "1px solid "+$colorThemeCheckboxRadio
					});
	
				//≠≠SET COLOR DISABLE [SHADOW_STYLE]
				jQuery("input[type='radio'].shadowStyle:disabled + label").css({
						"background" 	: colorShade2,
						"border" 		: "1px solid "+$colorThemeCheckboxRadio
					});
				
				//≠≠SET COLOR CHECKED [SHADOW_STYLE]
				jQuery("input[type='radio'].shadowStyle:checked + label").css({
						"background" 	:  "",
						"border" 		: "1px solid "+$colorThemeCheckboxRadio,
						"color"			: $colorTheme
					});
			});
		}else if(styleClassRadio == "flatStyle"){
			//≠≠SET COLOR DEFAULT STYLE [FLAT_STYLE]
			jQuery("input[type='radio'].flatStyle + label").css({
					"background" 	: "",
					"border" 		: "1px solid "+$colorThemeBorderCheckboxRadio
				});
	
			//≠≠SET COLOR DISABLE [FLAT_STYLE]
			jQuery("input[type='radio'].flatStyle:disabled + label").css({
					"background" 	: "",
					"border" 		: "1px solid "+$colorThemeBorderCheckboxRadio
				});
			
			//≠≠SET COLOR CHECKED [FLAT_STYLE]
			jQuery("input[type='radio'].flatStyle:checked + label").css({
					"background" 	: $colorThemeCheckboxRadio,
					"border" 		: "1px solid "+$colorThemeBorderCheckboxRadio,
					"color"			: $colorThemeTextCheckboxRadio
				});
			
			jQuery("input[type='radio'].flatStyle:checked + label").after().css({
					"color"			: $colorThemeTextCheckboxRadio
			});
			
	
			//
			//≠≠SET EVENT ON CHANGE
			//
			jQuery("input[type='radio'].flatStyle").change(function() {
	
				//≠≠SET COLOR DEFAULT STYLE [FLAT_STYLE]
				jQuery("input[type='radio'].flatStyle + label").css({
						"background" 	: "",
						"border" 		: "1px solid "+$colorThemeBorderCheckboxRadio
					});
	
				//≠≠SET COLOR DISABLE [FLAT_STYLE]
				jQuery("input[type='radio'].flatStyle:disabled + label").css({
						"background" 	: "",
						"border" 		: "1px solid "+$colorThemeBorderCheckboxRadio
					});
				
				//≠≠SET COLOR CHECKED [FLAT_STYLE]
				jQuery("input[type='radio'].flatStyle:checked + label").css({
						"background" 	: $colorThemeCheckboxRadio,
						"border" 		: "1px solid "+$colorThemeBorderCheckboxRadio,
						"color"			: $colorThemeTextCheckboxRadio
					});
				
				jQuery("input[type='radio'].flatStyle:checked + label").after().css({
						"color"			: $colorThemeTextCheckboxRadio
				});
			});
			
		}else{
			var $borderColorDis = jQuery("input[type='radio']."+styleClassRadio+":disabled + label").css("borderTopColor");	//Disabled Color
			var $bgColorDis = jQuery("input[type='radio']."+styleClassRadio+":disabled + label").css("background-color");	//Disabled Color
	
			jQuery("input[type='radio']."+styleClassRadio+" + label").css("background-color" , "#fafafa");
			jQuery("input[type='radio']."+styleClassRadio+" + label").css("border" , "1px solid #cacece");
	
			//≠≠ SET COLOR DISABLE
			jQuery("input[type='radio']."+styleClassRadio+":disabled + label").css("background-color" , $bgColorDis);
			jQuery("input[type='radio']."+styleClassRadio+":disabled + label").css("border" , "1px solid "+$borderColorDis);
			
			//≠≠ SET COLOR CHECKED
			jQuery("input[type='radio']."+styleClassRadio+":checked + label").css("background-color" , $colorTheme);
			jQuery("input[type='radio']."+styleClassRadio+":checked + label").css("border" , "1px solid "+$colorThemeCheckboxRadio);
			
				
			//SET EVENT ON CHANGE
			jQuery("input[type='radio']."+styleClassRadio+"").change(function() {
				jQuery("input[type='radio']."+styleClassRadio+" + label").css("background-color" , "#fafafa");
				jQuery("input[type='radio']."+styleClassRadio+" + label").css("border" , "1px solid #cacece");
	
				//≠≠ SET COLOR DISABLE
				jQuery("input[type='radio']."+styleClassRadio+":disabled + label").css("background-color" , $bgColorDis);
				jQuery("input[type='radio']."+styleClassRadio+":disabled + label").css("border" , "1px solid "+$borderColorDis);
	
				//≠≠ SET COLOR CHECKED
				jQuery("input[type='radio']."+styleClassRadio+":checked + label").css("background-color" , $colorTheme);
				jQuery("input[type='radio']."+styleClassRadio+":checked + label").css("border" , "1px solid "+$colorThemeCheckboxRadio);
			});
	
		}
	}

	/**
	 * SET CHECKBOX STYLE
	 */
	function setCheckboxStyle(){
		if(styleClassCheckbox == "shadowStyle"){
			var colorShade2 = shadeRGBColor($colorThemeCheckboxRadio, -0.2);
			//Set color default style
			jQuery("input[type='checkbox'].shadowStyle + label").css({
					"background" 	: "",
					"border" 		: "1px solid "+$colorThemeCheckboxRadio
				});
	
			//Set color disable style
			jQuery("input[type='checkbox'].shadowStyle:disabled + label").css({
					"background" 	: colorShade2,
					"border" 		: "1px solid "+$colorThemeCheckboxRadio
				});
			
			//Set color theme
			jQuery("input[type='checkbox'].shadowStyle:checked + label").css({
					"background" 	:  "",
					"border" 		: "1px solid "+$colorThemeCheckboxRadio,
					"color"			: $colorThemeCheckboxRadio
				});
	
			jQuery("input[type='checkbox'].shadowStyle").change(function() {
				//Set color default style
				jQuery("input[type='checkbox'].shadowStyle + label").css({
						"background" 	: "",
						"border" 		: "1px solid "+$colorThemeCheckboxRadio
					});
	
				//Set color disable style
				jQuery("input[type='checkbox'].shadowStyle:disabled + label").css({
						"background" 	: colorShade2,
						"border" 		: "1px solid "+$colorThemeCheckboxRadio
					});
				
				//Set color theme
				jQuery("input[type='checkbox'].shadowStyle:checked + label").css({
						"background" 	:  "",
						"border" 		: "1px solid "+$colorThemeCheckboxRadio,
						"color"			: $colorThemeCheckboxRadio
					});
			});
		}else if(styleClassCheckbox == "flatStyle"){
			//≠≠ SET COLOR DEFAULT [FLAT_STYLE]
			jQuery("input[type='checkbox'].flatStyle + label").css({
					"background" 	: "",
					"border" 		: "1px solid "+$colorThemeBorderCheckboxRadio
				});
	
			//≠≠ SET COLOR DISABLE [FLAT_STYLE]
			jQuery("input[type='checkbox'].flatStyle:disabled + label").css({
					"background" 	: "",
					"border" 		: "1px solid "+$colorThemeBorderCheckboxRadio
				});
			
			//≠≠ SET COLOR CHECKED [FLAT_STYLE]
			jQuery("input[type='checkbox'].flatStyle:checked + label").css({
					"background" 	: $colorThemeCheckboxRadio,
					"border" 		: "1px solid "+$colorThemeBorderCheckboxRadio,
					"color"			: $colorThemeTextCheckboxRadio
				});
			
			jQuery("input[type='checkbox'].flatStyle:checked + label").after().css({
					"color"			: $colorThemeTextCheckboxRadio
				});
			
			
			//
			//≠≠ ONCHANGE [FLAT_STYLE]
			//
			jQuery("input[type='checkbox'].flatStyle").change(function() {
				//≠≠ SET COLOR DEFAULT [FLAT_STYLE]
				jQuery("input[type='checkbox'].flatStyle + label").css({
						"background" 	: "",
						"border" 		: "1px solid "+$colorThemeBorderCheckboxRadio
					});
	
				//≠≠ SET COLOR DISABLE [FLAT_STYLE]
				jQuery("input[type='checkbox'].flatStyle:disabled + label").css({
						"background" 	: "",
						"border" 		: "1px solid "+$colorThemeBorderCheckboxRadio
					});
				
				//≠≠ SET COLOR CHECKED [FLAT_STYLE]
				jQuery("input[type='checkbox'].flatStyle:checked + label").css({
						"background" 	: $colorThemeCheckboxRadio,
						"border" 		: "1px solid "+$colorThemeBorderCheckboxRadio,
						"color"			: $colorThemeTextCheckboxRadio
					});
				
				jQuery("input[type='checkbox'].flatStyle:checked + label").after().css({
					"color"			: $colorThemeTextCheckboxRadio
				});
			});
			
		}else{
			var $borderColorDis = jQuery("input[type='checkbox']."+styleClassCheckbox+":disabled + label").css("borderTopColor");	//Disabled Color
			var $bgColorDis = jQuery("input[type='checkbox']."+styleClassCheckbox+":disabled + label").css("background-color");		//Disabled Color
	
			jQuery("input[type='checkbox']."+styleClassCheckbox+" + label").css("background" , "#FAFAFA");
			jQuery("input[type='checkbox']."+styleClassCheckbox+" + label").css("border" , "1px solid #CACECE");
	
			//≠≠ SET COLOR DISABLE
			jQuery("input[type='checkbox']."+styleClassCheckbox+":disabled + label").css("background-color" , $bgColorDis);
			jQuery("input[type='checkbox']."+styleClassCheckbox+":disabled + label").css("border" , "1px solid "+$borderColorDis);
	
			//≠≠ SET COLOR THEME
			if(styleClassCheckbox == "tamjaiStyle"){
				jQuery("input[type='checkbox']."+styleClassCheckbox+":checked + label").css("color" , $colorThemeCheckboxRadio);
				jQuery("input[type='checkbox']."+styleClassCheckbox+":checked + label").css("border" , "1px solid "+$colorThemeCheckboxRadio);
			}else{
				if(styleClassCheckbox == "shadowStyle"){
					
				}else{
					//≠≠FLAT STYLE AND OTHER STYLE
					jQuery("input[type='checkbox']."+styleClassCheckbox+":checked + label").css("background" , $colorThemeCheckboxRadio);
					jQuery("input[type='checkbox']."+styleClassCheckbox+":checked + label").css("border" , "1px solid "+$colorThemeCheckboxRadio);
				}
				
			}
	
			//≠≠SET EVENT ON CHANGE
			jQuery("input[type='checkbox']."+styleClassCheckbox+"").change(function() {
				//Set color default style
				jQuery("input[type='checkbox']."+styleClassCheckbox+" + label").css("background-color" , "#FAFAFA");
				jQuery("input[type='checkbox']."+styleClassCheckbox+" + label").css("border" , "1px solid #CACECE");
	
				//Set color disable style
				jQuery("input[type='checkbox']."+styleClassCheckbox+":disabled + label").css("background-color" , $bgColorDis);
				jQuery("input[type='checkbox']."+styleClassCheckbox+":disabled + label").css("border" , "1px solid "+$borderColorDis);
	
				//Set color theme
				if(styleClassCheckbox == "tamjaiStyle"){
					jQuery("input[type='checkbox']."+styleClassCheckbox+":checked + label").css("color" , $colorThemeCheckboxRadio);
					jQuery("input[type='checkbox']."+styleClassCheckbox+":checked + label").css("border" , "1px solid "+$colorThemeCheckboxRadio);
				}else{
					if(styleClassCheckbox == "shadow"){
	
					}else{
						//≠≠FLAT STYLE AND OTHER STYLE
						jQuery("input[type='checkbox']."+styleClassCheckbox+":checked + label").css("background" , $colorThemeCheckboxRadio);
						jQuery("input[type='checkbox']."+styleClassCheckbox+":checked + label").css("border" , "1px solid "+$colorThemeCheckboxRadio);
					}
				}
			});
		}
	}



	/**
	 * SET TEXTBOX STYLE
	 */
	function setTextboxStyle(){
		if(styleClassTextbox == "flatStyle"){
			jQuery("input[type='text'].flatStyle").each(function(index){
				jQuery(this).focus(function(){
					jQuery(this).css("box-shadow" , "0 0 5px "+$colorTheme);
					jQuery(this).css("border" , "1px solid "+$colorTheme);
				});
	
				jQuery(this).focusout(function(){
					jQuery(this).css("box-shadow" , "");
					jQuery(this).css("border" , "");
				});
	
				jQuery("input[type='text'].flatStyle").hover(function(){
					jQuery(this).css("box-shadow" , "0 0 5px "+$colorTheme);
					jQuery(this).css("border" , "1px solid "+$colorTheme);
				},function(){
					jQuery(this).css("box-shadow" , "");
					jQuery(this).css("border" , "");
				});
			});
	
			jQuery("input[type='password'].flatStyle").each(function(index){
				jQuery(this).focus(function(){
					jQuery(this).css("box-shadow" , "0 0 5px "+$colorTheme);
					jQuery(this).css("border" , "1px solid "+$colorTheme);
				});
	
				jQuery(this).focusout(function(){
					jQuery(this).css("box-shadow" , "");
					jQuery(this).css("border" , "");
				});
	
				jQuery("input[type='password'].flatStyle").hover(function(){
					jQuery(this).css("box-shadow" , "0 0 5px "+$colorTheme);
					jQuery(this).css("border" , "1px solid "+$colorTheme);
				},function(){
					jQuery(this).css("box-shadow" , "");
					jQuery(this).css("border" , "");
				});
			});
			
		}else{
	
	
		}
		
	}

	/**
	 * SET BUTTON STYLE
	 */
	function setButtonStyle(){
		if(styleClassButton == "shadowStyle"){
			jQuery("button , input[type=button]").css({
				"background" 	: $colorTheme,
				"color"			: $colorFont,
				"box-shadow"		: "inset 0px 0px 0px "+colorShade+", 0px 5px 0px 0px "+colorShade+", 0px 10px 5px #999"
			});
	
			jQuery("button , input[type=button]").hover(function(){
				jQuery(this).css({
					"background" 	: $colorHover+" "+$bgImage+" "+$bgPosition+" "+$bgRepeat,
					"color"			: $colorFont
				});
			},function(){
				jQuery(this).css({
					"background" : $colorTheme,
					"color"			:  $colorFont
				});
			});
			
		}else if(styleClassButton == "flatStyle"){
			jQuery("button , input[type=button]").css({
					"background" 	: $colorTheme,
					"border"		: "1px solid "+$colorThemeBorder,
					"color"			: $colorFont
				});
	
			jQuery("button , input[type=button]").hover(function(){
				jQuery(this).css({
					"background" 	: $colorHover+" "+$bgImage+" "+$bgPosition+" "+$bgRepeat,
					"border"		: "1px solid "+$colorHoverBorder,
					"color"			: $colorFont
				});
			},function(){
				jQuery(this).css({
					"background-color" : $colorTheme,
					"background-image" : "",
					"border"		: "1px solid "+$colorThemeBorder,
					"color"			: $colorFont
				});
			});
			
		}else if(styleClassButton == "tamjaiStyle"){
			jQuery("button , input[type=button]").css({
					"background-color" 	: $colorTheme,
					"border"		: "1px solid "+$colorThemeBorder,
					"color"			: $colorFont
				});
	
			jQuery("button , input[type=button]").hover(function(){
				jQuery(this).css({
					"background" 	: $colorHover+" "+$bgImage+" "+$bgPosition+" "+$bgRepeat,
					"border"		: "1px solid "+$colorHoverBorder,
					"color"			: $colorFont
				});
			},function(){
				jQuery(this).css({
					"background-color" : $colorTheme,
					"background-image" : "",
					"border"		: "1px solid "+$colorThemeBorder,
					"color"			: $colorFont
				});
			});
			
		}else{
			
	
		}
		
	}

	/**
	 * SET SELECT , DROPDOWN STYLE
	 */
	function setSelectStyle(){
		if(styleClassSelect == "shadowStyle"){
			jQuery(".dropdown  li").hover(function(){
				jQuery(this).css({
					"background" 	: $colorHover+" "+$bgImage+" "+$bgPosition+" "+$bgRepeat,
					"color"			: $colorFont
				});
			},function(){
				jQuery(this).css({
					"background" : $colorTheme,
					"color"		: $colorFont
				});
			});
			//SET WIDTH
			jQuery(".dropdown").each(function(index){
				jQuery(this).css({
					"background" 	: $colorTheme,
					"color"			: $colorFont,
					"width"			: widthArray[index]
				});
			});
	
			jQuery(".selected ").css({
				"color"			: $colorFont
			});
	
			jQuery(".dropdown li").css({
				"background" 	: $colorTheme,
				"color"		: $colorFont
			});
	
			
		}//shadowStyle
	
		if(styleClassSelect == "demo2"){
			/*console.info('demo2 : '+jQuery(".dropdown > li.active"));*/
			jQuery(".dropdown > li.active").css({
				"color"			: "#FF0000"
			});
	
		}//demo2
	
	
		if(styleClassSelect == "flatStyle"){
	
			/*console.info('flatStyle');*/
			jQuery(".dropdown li").hover(function(){
				jQuery(this).css({
					"background" 	: $colorHover+" "+$bgImage+" "+$bgPosition+" "+$bgRepeat,
					"color"			: $colorFont
				});
			},function(){
				jQuery(this).css({
					"background" 	: "",
					"color"			: ""
				});
			});
			
			//SET WIDTH
			jQuery(".dropdown").each(function(index){
				jQuery(this).css({
					"background" 	: $colorTheme,
					"color"			: $colorFont,
					"width"			: widthArray[index]
				});
			});
	
			jQuery(".selected ").css({
				"color"			: $colorFont
			});
	
	
			jQuery(".dropdown div").css({
				"background" 	: $colorTheme,
				"color"			: $colorFont
			});
	
			jQuery(".dropdown li").css({
				"border-bottom" 	: "1px solid "+colorShadeBorder,
				"width"			: "212px"
			});
	
		}//flatStyle
	
	
		
		if(styleClassSelect == "tamjaiStyle"){
			/*console.info('tamjaiStyle');*/
			jQuery(".dropdown li").hover(function(){
				jQuery(this).css({
					"background" 	: $colorTheme+" "+$bgImage+" "+$bgPosition+" "+$bgRepeat,
					"color"			: $colorFont,
					"border-left"	: "5px solid "+colorShadeBorder
				});
			},function(){
				jQuery(this).css({
					"background" 	: "",
					"color"			: "",
					"border-left"	: ""
				});
			});
	
	
			//SET WIDTH
			jQuery(".dropdown").each(function(index){
				jQuery(this).css({
					"background" 	: $colorTheme,
					"color"			: $colorFont,
					"border-left"	: "5px solid "+colorShadeBorder,
					"width"			: widthArray[index]
				});
			});
	
			jQuery(".selected ").css({
				"color"			: $colorFont
			});
	
	
			jQuery(".dropdown div").css({
				"background" 	: $colorTheme,
				"color"			: $colorFont
			});
	
			jQuery(".dropdown li").css({
				"border-bottom" 	: "1px solid "+colorShadeBorder
			});
	
		}//tamjai
		
	}
	
	
	/**
	 * SET AUTOCOMPLETE STYLE
	 ----------------------------------
	 * STATUS : OPEN 
	 *	 		CLOSE
	 * set status มาจาก javascript class กลาง
	 */
	function autocompleteStyle(status){
		//กดเปิด  autocomplete
		if(status == "open"){
			if(styleClassAutocomplete == "shadowStyle"){
				jQuery("span.custom-combobox").find("span.ui-icon").removeClass().addClass("ui-button-icon-primary ui-icon ui-icon-circle-triangle-w");
				jQuery("span.custom-combobox a").css({
					"box-shadow" : "1px 1px 3px 1px #ccc"
				});
			}
	
			if(styleClassAutocomplete == "flatStyle"){
				jQuery("span.custom-combobox a").css({
					"box-shadow" : "0 0 5px "+$colorTheme,
					"border"	 : "1px solid "+$colorTheme
				});
	
			}
		
		}
	
		//กดปิด autocomplete
		if(status == "close"){
			if(styleClassAutocomplete == "shadowStyle"){
				jQuery("span.custom-combobox").find("span.ui-icon").removeClass().addClass("ui-button-icon-primary ui-icon ui-icon-circle-triangle-s");
				jQuery("span.custom-combobox a").css({
					"box-shadow" : ""
				});
			}
	
			if(styleClassAutocomplete == "flatStyle"){
				jQuery("span.custom-combobox a").css({
					"box-shadow" : "",
					"border"	 : ""
				});
			}
		}
	}
	
	
	function setAutocomplete(){
		if(styleClassAutocomplete == "shadowStyle"){
			jQuery("span.custom-combobox").find("span.ui-icon").removeClass().addClass("ui-button-icon-primary ui-icon ui-icon-circle-triangle-s");
		}
		
	}
	
	
	/**
	 * CHENG RADIO
	-------------------------------
	 * เปลี่ยน radio ให้รองรับ style : flat , shadow , tamjai 
	 */
	function changeRadioType(){
		if(styleRadio == "shadowStyle" || styleRadio == "flatStyle" || styleRadio == "tamjaiStyle"){
			jQuery("input[type=radio]").each(function(index){
				if(jQuery(this).next("label:first").length > 0){
					//radio gen จาก strut2
					var txtRadio = jQuery(this).next("label:first").text();
					jQuery(this).next("label:first").text("");
					jQuery(this).next("label:first").after(txtRadio+""); //ตัด  &nbsp; ออก  เนื่องจาก หลังจากเรียก auto complete แล้วมีการบอกช่องว่างเพิ่ม  
				}else{
					//input type radio ปกติ
					var idRadio = jQuery(this).attr("id");
					if(typeof idRadio == "undefined" || idRadio == ""){
						jQuery(this).attr("id" , "radioGen"+(index+1));
						idRadio = "radioGen"+(index+1);				
					}
					jQuery(this).after("<label for='"+idRadio+"'></label>&nbsp;");
				}
			});
		}
	}
	
	/**
	 * CHENG CHECKBOX
	-------------------------------
	 * เปลี่ยน checkbox ให้รองรับ style : flat , shadow , tamjai 
	 */
	function chageCheckboxType(){
		if(styleCheckbox == "shadowStyle" || styleCheckbox == "flatStyle" || styleCheckbox == "tamjaiStyle"){
			var array = [];
			var i = 0;
			jQuery("input[type=checkbox]").each(function(index){
				var idCheckbox = jQuery(this).attr("id");
				//ถ้า checkbox ไม่มี id ให้สร้าง id ให้ 
				//เพราะ style ที่ออกแบบต้องมี label ต่อท้าย checkbox ที่สร้างมาและใช้  for id ของ checkbox
				if(typeof idCheckbox == "undefined" || idCheckbox == ""){
					jQuery(this).attr("id" , "checkboxGen"+(index+1));
					idCheckbox = "checkboxGen"+(index+1);			
				}else{
					//ถ้ามีไอดีอยู่แล้วม่าเช็คว่าไอดีซ้ำไหม ซ้ำให้สร้างไอดีใหม่ให้
					for(i = 0 ; i < array.length; i++){
						if(array[i] == idCheckbox){
							//console.log("duplicate : "+idCheckbox+" : "+array[i]);
							jQuery(this).attr("id" , "checkboxGen"+(index+1));
							idCheckbox = "checkboxGen"+(index+1);
							break;
						}
					}
	
				}
	
				array.push(idCheckbox);
	
				//มี label อยู่แล้วตรวจสอบว่า for ตรงกับ id ไหมเปลี่ยนให้ตรงกัน
				if(jQuery(this).next("label:first").length > 0){
					if(jQuery(this).next("label:first").attr('for') != idCheckbox){
						jQuery(this).next("label:first").attr('for' , idCheckbox);
					}
				}else{
					jQuery(this).after("<label for='"+idCheckbox+"'></label>&nbsp;");
				}
			});
			
		}
		
	}
	
	/**
	 * หน้าที่กด edit แถวในตารางได้
	 */
	function setClassSelectEdit(){
		jQuery("table > tbody > tr > td > div > select.cmd-hidden").each(function(index){
			var width = jQuery(this).css("width");
			if(typeof width != "undefined" && width != ""){
				widthArray.push(width);
			}else{
				widthArray.push("180px");
			}
			jQuery(this).addClass(styleSelect);
		});
	
		jQuery('select.demo2 ,select.shadowStyle, select.flatStyle, select.tamjaiStyle').easyDropDown("destroy");
		jQuery('select.demo2 ,select.shadowStyle, select.flatStyle, select.tamjaiStyle').not(".Xready").easyDropDown({
			cutOff: 10
		});
	
		
		styleClassSelect = styleSelect;
		//load color theme
		setSelectStyle();
	
		
	}
	
	/**
	 * ไว้เรียกหลังจากที่หน้าเพจ สร้าง radio มาใหม่ให้เรียก function นี้เพื่อ set class และเปลี่ยน radio ให้รองรับ style
	 */
	function setClassRadio(){
		if(styleRadio != "classicStyle"){
			//add class
			var classStr = "";
			jQuery("input[type=radio]").each(function(index){
				classStr = jQuery(this).attr('class');
				if(typeof classStr != "undefined"){
					jQuery(this).addClass(styleRadio);
					
				}else{
					jQuery(this).addClass(styleRadio);
				}
			});
	
			//change style
			changeRadioType();
			//load color theme
			setRadioStyle();
		}
	}
	
	
	/**
	 * ไว้เรียกหลังจากที่หน้าเพจ สร้าง textbox มาใหม่ให้เรียก function นี้เพื่อ set class และเปลี่ยน textbox ให้รองรับ style
	 */
	function setClassTextbox(){
		if(styleText != "classicStyle"){
			jQuery("input[type=text]").not(".custom-combobox-input").each(function(index){
				jQuery(this).addClass(styleText);
			});
			
			//load color theme
			setTextboxStyle();
	
		}
		
	}
	
	/**
	 * ไว้เรียกหลังจากที่หน้าเพจ สร้าง checkbox มาใหม่ให้เรียก function นี้เพื่อ set class และเปลี่ยน checkbox ให้รองรับ style
	 */
	function setClassCheckbox(){
		if(styleCheckbox != "classicStyle"){
			//add class
			var classStr = "";
			jQuery("input[type=checkbox]").each(function(index){
				classStr = jQuery(this).attr('class');
				if(typeof classStr != "undefined"){
					jQuery(this).addClass(styleCheckbox);
				}else{
					jQuery(this).addClass(styleCheckbox);
				}
			});
			//change style
			chageCheckboxType();
			//load color theme
			setCheckboxStyle();
		}
	}
	
	/**
	 * ไว้เรียกหลังจากที่หน้าเพจ สร้าง button มาใหม่ให้เรียก function นี้เพื่อ set class และเปลี่ยน checkbox ให้รองรับ button
	 */
	function setClassButton(){
		if(styleButton != "classicStyle"){
			styleClassButton = styleButton;
			setButtonStyle();
		}
	}
	
	/**
	 * ไล่เฉดสี
	 */
	function shadeRGBColor(color, percent) {
	    var f=color.split(","),t=percent<0?0:255,p=percent<0?percent*-1:percent,R=parseInt(f[0].slice(4)),G=parseInt(f[1]),B=parseInt(f[2]);
	    return "rgb("+(Math.round((t-R)*p)+R)+","+(Math.round((t-G)*p)+G)+","+(Math.round((t-B)*p)+B)+")";
	}
</script>
