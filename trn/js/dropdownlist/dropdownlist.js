/**
 * feature list --------------
 * 1. โหลดข้อมูลผ่าน ajax
 * 2. set css style ได้
 * 3. set css class ได้
 * 4. ทำ trigger change ได้ โดยรับ parameter แบบ array
 * 4.1. เมื่อ post parameter เป็นว่างจะไม่ยิงขึ้นไปที่ server และ clear dropdownlist
 * 4.2. เมื่อ set value เป็น ว่าง dropdownlist จะ clear dropdownlist ตัวถัดไปให้เลย
 * 4.3. เมื่อ set value ไปที่ dropdownlist จะ load dropdownlist ตัวถัดไปให้เลย
 * 4.4. สามารถ disable และ enable ได้แบบตัวเดียวและ หลายตัว
 * 4.5. สามารถเขียนฟังก์ชั่นควบคุมการทำงานก่อน event change ได้ 
 * 4.6. สามารถเขียนฟังก์ชั่นควบคุมการทำงานหลัง  event change ได้
 */
(function( $, undefined ) {

	$.widget( "ui.dropdownlist", {
		version: "0.2.0",
		options: [{
			seq: 0,
			displayModelInput: "none",
		    inputModelId: undefined,
		    url: undefined,
		    postParamsId: undefined,
		    postParameGenerate: undefined,
		    cssClass: undefined,
		    cssStyle: undefined,
		    defaultKey: undefined,
		    defaultValue: undefined,
		    submitType: "POST",
		    async: false,
		    disabled: false,
			requireInput: false,
		    beforeChangeFunction: undefined,
		    afterChangeFunction: undefined
		}],

		// the constructor
		_create: function() {
			
			this._manageOptions();
			
			this._createDropdownlistAll();
			
			//this._manageChangeFunction();
			
		},

		_manageOptions: function() {
			var input = undefined;
			for (var key in this.options) {
				if (key == 0) {
					input = this.element;	
				} else {
					input = jQuery("#" + this.options[key].inputModelId);
				}
				
				// กำหนดค่า sequence ให้ dropdownlist
				this.options[key].seq = key;
				
				// กำหนดค่า modelId โดยใ้ช้ id จาก input
				if (this.options[key].inputModelId == undefined) {
					this.options[key].inputModelId = jQuery(input).attr('id');
				}
				
				// กำหนดค่า css และ style โดยยึดที่ config เป็นหลัก
				if (this.options[key].cssClass == undefined) {
					this.options[key].cssClass = jQuery(input).attr('class');
				}
				
				// กำหนดค่า css และ style โดยยึดที่ config เป็นหลัก
				if (this.options[key].cssStyle == undefined) {
					this.options[key].cssStyle = jQuery(input).attr('style');
				}

				// กำหนดค่า readonly โดยยึดที่ค่า true เสมอ
				if (this.options[key].disabled == undefined) {
					this.options[key].disabled = false;
				}
				if (this.options[key].disabled == false) {
					if (jQuery(input).attr('disabled')) {
						this.options[key].disabled = true;	
					}
				}

				// กำหนดค่า requireInput โดยยึดที่ค่า true เสมอ
				if (this.options[key].requireInput == undefined) {
					this.options[key].requireInput = false;
				}
				if (this.options[key].requireInput == false) {
					if (jQuery(input).hasClass('requireInput')) {
						this.options[key].requireInput = true;	
					}
				}
				
				// กำหนดค่า post parameter
				if (this.options[key].postParamsId != undefined) {
					//this.options[key].postParameGenerate = generatePostParameters(this.options[key].postParamsId);
				}
				
				if (this.options[key].submitType == undefined) {
					this.options[key].submitType = "POST";
				}

				if (this.options[key].async == undefined) {
					this.options[key].async = false;
				}

				if (this.options[key].displayModelInput == undefined) {
					this.options[key].displayModelInput = "none";
				}
			}
		},
		
		_getLastSeq: function() {
			var lastSeq = 0;
			for (var key in this.options) {
				lastSeq = key;
			}
			return lastSeq;
		},
		
		_manageChangeFunction: function(newSeq) {
			var that = this;
			var options = this.options;
			var lastSeq = this._getLastSeq();
			var seq = 0;
			if (newSeq != undefined) {
				// console.info(seq);
				seq = parseInt(newSeq, 10);
			}
				
			// console.info(seq + " > " + lastSeq);
			var currentOptions = options[seq];
					
			// ผูก event change
			// console.info("#" + currentOptions.inputModelId + "_DROPDOWNLIST");
			jQuery("#" + currentOptions.inputModelId + "_DROPDOWNLIST").change(function() {
				// console.info("#" + currentOptions.inputModelId + "_DROPDOWNLIST");
				var thisSeq = parseInt(jQuery(this).attr('seq'), 10);
				var thisOption = options[thisSeq];
				
				var statusBeforeChange = undefined;
				if (thisOption.beforeChangeFunction != undefined) {
					statusBeforeChange = thisOption.beforeChangeFunction();
					if ((statusBeforeChange != undefined) && (statusBeforeChange == false)) {
						jQuery("#" + thisOption.inputModelId + "_DROPDOWNLIST").val(jQuery("#" + thisOption.inputModelId).val());
						return false;
					}
				}
				
				if (jQuery("#" + thisOption.inputModelId).val() != jQuery(this).val()) {
					jQuery("#" + thisOption.inputModelId).val(jQuery(this).val());
				}
				
				for (; thisSeq <= lastSeq; thisSeq++) {
					var nextOptions = options[thisSeq + 1];
					if (nextOptions != undefined) {
						// console.info(nextOptions);
						jQuery("#" + nextOptions.inputModelId).val("");
						that._createDropdownlist(nextOptions);
						that._manageChangeFunction(nextOptions.seq);
					}
				}
				
				if (thisOption.afterChangeFunction != undefined) {
					thisOption.afterChangeFunction();
				}
				
			});
		},
		
		_createDropdownlist: function(currentOptions) {
			// สร้าง post parameter ก่อน เพื่อไว้ตรวจสอบว่าจะ call ไปยัง servlet หรือไม่
			currentOptions.postParameGenerate = generatePostParameters(currentOptions.postParamsId);
			
			jQuery("#" + currentOptions.inputModelId).css('display', currentOptions.displayModelInput);
			
			if (currentOptions.postParamsId == undefined) {
				// กรณีไม่มี post parameter ให้ load ปกติ			
				this._createDataDropdownlist(currentOptions);
			} else {
				if (haveEmptyPostParameters(currentOptions.postParameGenerate)) {
					// กรณีมี post parameter แต่เป็นค่าว่างตัวใดตัวหนึ่งให้ clear dropdownlist	
					this._createEmptyDropdownlist(currentOptions);
				} else {
					// กรณี post parameter มีค่าครบทุกตัว ให้โหลดข้อมูลได้
					this._createDataDropdownlist(currentOptions);
				}
			}				
		},
		
		_createDropdownlistAll: function() {
			
			for (var key in this.options) {
				this._createDropdownlist(this.options[key]);
				this._manageChangeFunction(key);
			}
		},
		
		_createEmptyDropdownlist: function(options) {
			// ใช้กรณีไม่มีข้อมูล หรือ post parameter มีค่าเป็นว่าง
			drawingDropdownlistFromJsonSelectItem(undefined, options);
		},
		
		_createDataDropdownlist: function(options) {
			// ใช้กรณีมีข้อมูล โดยรับข้อมูลจาก servlet
			if (options.url == undefined) {
				jQuery("#" + options.inputModelId + "_DROPDOWNLIST").val(jQuery("#" + options.inputModelId).val());
			} else {
				jQuery.ajax({
					url: options.url,
				    data: options.postParameGenerate,
				    type: options.submitType,
				    async: options.async,
				    success: function(jsonData) {
				    	 drawingDropdownlistFromJsonSelectItem(jsonData, options);
				    }
				});
			}
		},
		
		dropdownlistValue: function(selectSeq, newValue) {
			selectSeq = parseInt(selectSeq, 10);
			if (newValue == undefined) {
				// return jQuery("#" + this.options[selectSeq].inputModelId).val();
				return jQuery("#" + this.options[selectSeq].inputModelId + "_DROPDOWNLIST").val();
			} else {
				if (newValue == "") {
					jQuery("#" + this.options[selectSeq].inputModelId).val("");
					jQuery("#" + this.options[selectSeq].inputModelId + "_DROPDOWNLIST").val("");
					
					var lastSeq = this._getLastSeq();
					selectSeq++;
					for (; selectSeq <= lastSeq; selectSeq++) {
						this._createEmptyDropdownlist(this.options[selectSeq]);
					}
				} else {
					jQuery("#" + this.options[selectSeq].inputModelId).val(newValue);
					jQuery("#" + this.options[selectSeq].inputModelId + "_DROPDOWNLIST").val(newValue);
					
					var lastSeq = this._getLastSeq();
					for (; selectSeq <= lastSeq; selectSeq++) {
						jQuery("#" + this.options[selectSeq].inputModelId + "_DROPDOWNLIST").change();
					}
				}
			}
		},
		
		toDisabled: function(selectSeq) {
			var firstSeq = 0;
			var thisSeq = this._getLastSeq();
			if (selectSeq != undefined) {
				thisSeq = parseInt(selectSeq, 10);
			}
			
			for (; thisSeq >= firstSeq; thisSeq--) {
				jQuery("#" + this.options[thisSeq].inputModelId + "_DROPDOWNLIST").attr('disabled', true).addClass("readonly").attr('tabindex', -1);
			}
		},

		toEnabled: function(selectSeq) {
			var lastSeq = this._getLastSeq();
			var thisSeq = 0;
			if (selectSeq != undefined) {
				thisSeq = parseInt(selectSeq, 10);
			}
			
			for (; thisSeq <= lastSeq; thisSeq++) {
				if ((jQuery("#" + this.options[thisSeq].inputModelId + "_DROPDOWNLIST option").length == 1) && jQuery("#" + this.options[thisSeq].inputModelId + "_DROPDOWNLIST option")[0].value == "") {
					
				} else {
					jQuery("#" + this.options[thisSeq].inputModelId + "_DROPDOWNLIST").removeAttr('disabled').removeClass("readonly").removeAttr('tabindex');
				}
			}
		}
	});

})( jQuery );

/**
 * วาด list ของ json selectitem เป็น Dropdownlist
 */
function drawingDropdownlistFromJsonSelectItem(jsonData, options) {

	var dropdownId = options.inputModelId + "_DROPDOWNLIST";
	
	// 1. เคลียร์ dropdownlist ออกก่อน
	jQuery("#" + dropdownId).remove();

	// 2.1. สร้าง tag select
	var html = "<select id='" + dropdownId + "' seq='" + options.seq + "' name='" + options.inputModelName + "'>";
	if ((options.defaultKey != undefined) && (options.defaultValue != undefined)) {
		html += "<option value='" + options.defaultKey + "'>" + options.defaultValue + "</option>";
	}
	
	// 2.2. วนสร้าง tag option
	if (jsonData != undefined) {
		for (var index = 0; index < jsonData.length; index++) {
			//if (msieversion() >= 8) {
			//	html += "<option value='" + jsonData[index].key + "' json='" + JSON.stringify(jsonData[index]) + "'>" + jsonData[index].value + "</option>";
			//} else {
				html += "<option value='" + jsonData[index].key + "'>" + jsonData[index].value + "</option>";			
			//}
		}
	}
	
	// 2.3. ปิด tag
	html += "</select>";

	// นำ dropdownlist วาดลงใน tag แม่
	jQuery("#" + options.inputModelId).parent().append(html);
	
	html = jQuery("#" + dropdownId);	
	
	// ใส่ class
	if (options.cssClass != undefined) {
		jQuery(html).addClass(options.cssClass);
	}
	
	// ใส่ style
	if (options.cssStyle != undefined) {
		jQuery(html).attr('style', options.cssStyle);
	}
	
	// disabled เมื่อไม่มีข้อมูล
	if ((jsonData == undefined) || (jsonData.length == 0)) {
		jQuery(html).removeAttr('disabled').removeClass('readonly').attr('disabled', 'disabled').addClass('readonly');
	}
	
	// disabled เมื่อถูกกำหนดให้เป็น disable
	if (options.disabled) {
		jQuery(html).removeAttr('disabled').removeClass('readonly').attr('disabled', 'disabled').addClass('readonly');
	}
	
	// กรณีเป็น require input ให้ใส่ class requireInput ด้วย
	if (options.requireInput) {
		jQuery(html).removeClass('requireInput').addClass('requireInput');
	}
	
	// กรณีมีค่าอยู่ใน model แล้วให้ set ลง dropdownlist ด้วย
	jQuery(html).val(jQuery("#" + options.inputModelId).val());
	
	// hide input textbox
	jQuery("#" + options.inputModelId).hide();
}

/**
 * ดึงข้อมูล json จาก Drop down list
 */
function getJsonDataFromDropdownlist(modelKeyId) {

	var dropdownlist = jQuery("#" + modelKeyId + "_DROPDOWNLIST");
	var optionElArray = dropdownlist.prop('options');
	var optionEl = optionElArray[dropdownlist.prop('selectedIndex')];
	if (jQuery(optionEl).attr('json') == undefined) {
		return "";
	} else {
		if (msieversion() >= 8) {
			return JSON.parse(jQuery(optionEl).attr('json'));
		} else {
			return "";			
		}
	}
}