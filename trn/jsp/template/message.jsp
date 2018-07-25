<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<script type="text/javascript">
	/* แสดง message แล้วจางหายไป */
	$(document).ready(function(){
		// หมายเหตุ:: เหตุผลที่ใช้ fadeIn ในกรณีนี้ไม่ได้เนื่องจาก หากใช้ fadeIn แล้วก่อนที่จะ fadeIn เข้ามานั้นจะต้องทำการ display: hidden; ในส่วนนั้นก่อน  ซึ่งเมื่อทำ fadeIn เสร็จแล้วยรรทัด message ก็จหายไป
		//	  	 -แก้ไข : โดยการใน animate แทน ซึ่งจะทำการ  set opacity แทนการใช้ display: hidden; ซึ่งจะไม่ทำการซ่อนบรร่ทัด message แต่เป็นการ set ความเรือนรางแทน
			
		var $messFedSel = jQuery('.messFed')

		$messFedSel.animate({opacity: 1},900,"","");
				
		<s:if test='getAlertMaxExceed().equals("A")'>
			alert(getMessageResponse("<s:property value='ActionMessages[0]' escapeHtml='false'/>"));
		</s:if>
		<s:elseif test='getAlertMaxExceed().equals("C")'>
			showConfrimMessage();
		</s:elseif>
		<s:else>
			<s:if test='%{ActionMessages.size() == 1}'>
				var msgx = getMessageResponse("<s:property value='ActionMessages[0]' escapeHtml='false'/>");
				if (msgx != '') {
					alert(msgx);
				}
			</s:if>
		</s:else>
	});
</script>
<style type="text/css">
	.messFed {
		opacity: 0;
		padding-bottom: 7px; 
		padding-top: 7px;
		border-collapse: collapse;
		height: 30px;
	}
</style>
<div class="messFed">
	<s:if test='%{getAlertMaxExceed().equals("A")}'>
		<table class="FORM">
			<tr>
				<td class="MESSAGE">&nbsp;</td>
			</tr>
		</table>
	</s:if> 

	<s:elseif test='%{getAlertMaxExceed().equals("C")}'>
		<table class="FORM">
			<tr>
				<td class="MESSAGE">&nbsp;</td>
			</tr>
		</table>
	</s:elseif> 

	<s:elseif test='%{getAlertMaxExceed().equals("N")}'>
		<s:if test='%{ActionMessages[0] == "E"}'>
			<table class="FORM ui-state-highlight ui-corner-all" style="table-layout: fixed;width: 100%" id="tblMessage">
				<tr>
					<td class="MESSAGE ERROR" style="text-align: center;">
						<span class="ui-icon ui-icon-alert"></span>
						<s:property value="ActionMessages[1]" escapeHtml="false"/>
						<s:if test='%{ActionMessages[2] != null}'>
							<a class="LINK" href="javascript:void(0);" onclick="showErrorDetail('messageDetail')">detail</a>	
								<div id='messageDetail' style="display: none;"><s:property  value="ActionMessages[2]" escapeHtml="false"/></div>
						</s:if>
					</td>
				</tr>
			</table>
		</s:if>
		<s:elseif test='%{ActionMessages[0] == "W"}'>
			<table class="FORM ui-state-highlight ui-corner-all" style="table-layout: fixed;width: 100%" id="tblMessage">
				<tr>
					<td class="MESSAGE WARING" style="text-align: center;">
						<span class="ui-icon ui-icon-info"></span>
						<s:property value="ActionMessages[1]" escapeHtml="false"/>
						<s:if test='%{ActionMessages[2] != null}'>
							<a class="LINK" href="javascript:void(0);" onclick="showErrorDetail('messageDetail')">detail</a>	
								<div id='messageDetail' style="display: none;"><s:property  value="ActionMessages[2]" escapeHtml="false"/></div>
						</s:if>
					</td>
				</tr>
			</table>
		</s:elseif>
		<s:elseif test='%{ActionMessages[0] == "S"}'>
			<table class="FORM ui-state-highlight ui-corner-all" style="table-layout: fixed;width: 100%" id="tblMessage">
				<tr>
					<td class="MESSAGE SUCCESS" style="text-align: center;">
						<span class="ui-icon ui-icon-circle-check"></span>
						<s:property value="ActionMessages[1]" escapeHtml="false"/>
						<s:if test='%{ActionMessages[2] != null}'>
							<a class="LINK" href="javascript:void(0);" onclick="showErrorDetail('messageDetail')">detail</a>	
								<div id='messageDetail' style="display: none;"><s:property  value="ActionMessages[2]" escapeHtml="false"/></div>
						</s:if>
					</td>
				</tr>
			</table>
		</s:elseif>
		<s:else>
			<table class="FORM" style="table-layout: fixed;" id="tblMessage">
				<tr>
					<td class="MESSAGE">&nbsp;</td>
				</tr>
			</table>
		</s:else>
		
		<table id="tblMessageEmpty" class="FORM" style="table-layout: fixed; display: none" >
			<tr>
				<td class="MESSAGE">&nbsp;</td>
			</tr>
		</table>
	</s:elseif>

	<s:else>
		<table class="FORM">
			<tr>
				<td class="MESSAGE">&nbsp;</td>
			</tr>
		</table>
	</s:else> 
</div>