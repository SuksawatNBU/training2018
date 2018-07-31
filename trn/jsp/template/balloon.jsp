<!-- 
	Description: Balloon is show content block in result table when mouse over each record.(configuration this file)
	since: 17/06/2014 
	author: thaongsak.b
-->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>


<!-- เขียนไว้หลัง body เนื่องจากรอให้ตารางเสร็จก่อน ซึ่งจะอยู่หลัง body  -->
<script type="text/javascript">

//----------- CONFIGURATION BALLOON HERE ------------//
var option = {
		delay:1000,
		fadeIn:1000,
		fadeOut:300
		//css:{position: "absolute",width: "200px",height: "145px",background: "green",border: "#F7B303 solid 5px", borderRadius: "30px"}// css jquery style
		//,class:"bubble2"
};
//----------- CONFIGURATION BALLOON HERE ------------//



	jQuery(document).ready(function(){
				//console.info("print a: "+jQuery("table[class*='BALLOON']").length);
				createBalloon(option);//เรียกใช้งาน function ที่อยู่ใน balloon.js 
	});
		

	<s:include value="/js/balloon.js"/>	
</script>




<style type="text/css">
	
	.bubble2 
	{
		position: relative;
		padding: 0px;
		background: yellow;
		-webkit-border-radius: 11px;
		-moz-border-radius: 11px;
		border-radius: 11px;
	}
	
	
	
	/*
	.bubble 
	{
		position: relative;
		padding: 0px;
		/*background: #000000;*/
		-webkit-border-radius: 11px;
		-moz-border-radius: 11px;
		border-radius: 11px;
	}
	*/
	/*
	.bubble:after 
	{
		content: "";
		position: absolute;
		bottom: -15px;
		left: 24px;
		border-style: solid;
		border-width: 15px 12px 0;
		border-color: #000000 transparent;
		display: block;
		width: 0;
		z-index: 1;
	}
	*/
</style>
