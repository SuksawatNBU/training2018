<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<script type="text/javascript" src="<s:url value='/js/jquery/jquery-circleGraphic.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery/jquery-canvas.js' />"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">

function showProgress(timeKey){
	
	var progress = 0;
    var flagprocess = true; 
    
 jQuery("#progressbar").show();
    jQuery.ajax({
        type: "POST",
        url : "<s:url value='/runningProgressBar.action' />"+"?functioncode=000&keyBar="+timeKey,
        dataType : 'json',
        async: false,
        global: false,
        success: function(data){
            progress = parseInt(data.progress);
            progressTxt = data.progressTxt;
            
                jQuery("#per").val(progress);
                jQuery("#flagprocess").val( data.flagProcess);
            
            flagprocess = data.flagProcess;
 			angle = progress;
            if (flagprocess == false) {
                clearInterval(intervalProgress2);
            	jQuery('.circleGraphic').circleGraphic({'percentage':0,'progressTxt':''});
                jQuery("#progressbar").hide();
            }else{
				jQuery('.circleGraphic').circleGraphic({'percentage':progress,'progressTxt':progressTxt});
            }
        }
    });
}
	
</script>
<style type="text/css">
	.circleGraphic{
		    position: absolute;
		    left: 0px;
		    top: 0px;
		    z-index: 1;
		    
		    top: 50%;
		    left: 50%;
		    width:30em;
		    height:10em;
		    margin-top: -9em; /*set to a negative number 1/2 of your height*/
		    margin-left: -15em; /*set to a negative number 1/2 of your width*/
		}
		
</style>
<div id="progressbar" class="circleGraphic" style="display: none;"></div>
<s:hidden  id="per"/>
<s:hidden  id="flagprocess"/>
