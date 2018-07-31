<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<script type="text/javascript">
	/* แสดง message แล้วจางหายไป */
	// หมายเหตุ:: เหตุผลที่ใช้ fadeIn ในกรณีนี้ไม่ได้เนื่องจาก หากใช้ fadeIn แล้วก่อนที่จะ fadeIn เข้ามานั้นจะต้องทำการ display: hidden; ในส่วนนั้นก่อน  ซึ่งเมื่อทำ fadeIn เสร็จแล้ว บรรทัด message ก็จะหายไป
	//	  	 -แก้ไข : โดยการใช้ animate แทน โดยจะทำการ  set opacity แทนการใช้ display: hidden; ซึ่งจะไม่ทำการซ่อนบรรทัด message แต่เป็นการ set ความเรือนรางแทน
	
	function setMessagePopup (divIdPopup, msg) {
		//var $messPopupFedSel = jQuery('.messPopup')
		var msgDesc = msg.split("::");
		
		// set message
		//jQuery(divIdPopup + " #spanMsgPopup").html(msgDesc[1]);

		if (msgDesc[0] != "ERROR") {
			// ถ้าไม่ใช่ Error ให้ FadeIn และ FadeOut หายไป
			//$messPopupFedSel.animate({opacity: 1},900,"",function(){
				//$(this).delay(2500).animate({opacity: 0},1200,"");
			//});
			
			if (msgDesc[0] == "WARING") {
				// set style
				//jQuery(divIdPopup + " #tblMessagePopup").addClass('ui-state-highlight')
				//jQuery(divIdPopup + " #spanMsgPopupIcon").addClass('ui-icon ui-icon-info')
				//jQuery(divIdPopup + " .MESSAGE").addClass("WARING");
				
				var msg = '<td class="MESSAGE WARING" style="width: 40%; text-align: right;"><span class="ui-icon ui-icon-info" style="float: right; margin-right: .3em;"></td>';
				msg += '<td class="MESSAGE WARING" style="width: 60%; text-align: left;">' +  msgDesc[1] + '</td>';

				jQuery(divIdPopup +"> table.BUTTON >tbody >tr >td >div.messFed > table#tblMessage").addClass('ui-state-highlight ui-corner-all');
				jQuery(divIdPopup +"> table.BUTTON >tbody >tr >td >div.messFed #tblMessage > tbody > tr").html(msg);
				
			} else if (msgDesc[0] == "SUCCESS") {
				// set style
				//jQuery(divIdPopup + " #tblMessagePopup").addClass('ui-state-highlight')
				//jQuery(divIdPopup + " #spanMsgPopupIcon").addClass('ui-icon ui-icon-circle-check')
				//jQuery(divIdPopup + " .MESSAGE").addClass("SUCCESS");
				
				
				var msg = '<td class="MESSAGE SUCCESS" style="width: 40%; text-align: right;"><span class="ui-icon ui-icon-circle-check" style="float: right; margin-right: .3em;"></td>';
				msg += '<td class="MESSAGE SUCCESS" style="width: 60%; text-align: left;">' +  myCriteria.messageAjax.message + '</td>';

				jQuery(divIdPopup +"> table.BUTTON >tbody >tr >td >div.messFed > table#tblMessage").addClass('ui-state-highlight ui-corner-all');
				jQuery(divIdPopup +"> table.BUTTON >tbody >tr >td >div.messFed #tblMessage > tbody > tr").html(msg);
				
			} else {
				
			}
		} else {
			// ถ้ามี Error ให้ message นั้นค้างไว้ เพื่อสามารถดู Detail ที่ error ได้ 
			//$messPopupFedSel.animate({opacity: 1},900,"","");
			
			// set msg detail
			//jQuery(divIdPopup + " #messageDetailPopup").html(msgDesc[2]);
			//jQuery(divIdPopup + " #msgLinkDetailPopup").show();
			
			// set style
			//jQuery(divIdPopup + " #tblMessagePopup").addClass('ui-state-error')
			//jQuery(divIdPopup + " #spanMsgPopupIcon").addClass('ui-icon ui-icon-alert')
			//jQuery(divIdPopup + " .MESSAGE").addClass("ERROR");
			
			
			jQuery(divIdPopup +"> table.BUTTON >tbody >tr >td >div.messFed > table#tblMessage").addClass('ui-state-error ui-corner-all');
			jQuery(divIdPopup +"> table.BUTTON >tbody >tr >td >div.messFed .MESSAGE").addClass("ERROR").html(
					"<span class='ui-icon ui-icon-alert' style='margin-right: .6em;'></span>"
					+ msgDesc[1]
					+ "&nbsp;&nbsp;<a class='LINK' href='javascript:void(0);' onclick='showErrorDetail(\"messageDetail\")'>detail</a>"
					+ "<div id='messageDetail' style='display: none;'>"+msgDesc[2]+"</div>"
					);
		}
	}
</script>

<style type="text/css">
	.messPopup {
		opacity: 0;
		padding-bottom: 7px;
		padding-top: 7px;
		background: #EAEAEA;
	}
</style>

<div class="messPopup">
	<table class="ui-corner-all" style="table-layout: fixed;width: 100%;" id="tblMessagePopup">
		<tr>
			<td class="MESSAGE" style="width: 40%; text-align: right;">
				<span id="spanMsgPopupIcon" style="float: right; margin-right: .3em;"></span>
			</td>
			<td class="MESSAGE" style="width: 60%; text-align: left;">
				<span id="spanMsgPopup" style="float: left; margin-right: .3em;"></span>
				<a id="msgLinkDetailPopup" class="LINK" href="javascript:void(0);" onclick="showErrorPopupDetail('<s:property value='#divresult[0]'/>_messageDetailPopup')" style="display: none;">detail</a>	
				<div id='<s:property value='#divresult[0]'/>_messageDetailPopup' style="display: none;"></div>
			</td>
		</tr>
	</table>
</div>