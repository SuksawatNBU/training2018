<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
	String userAgent = request.getHeader("user-agent");
	// System.out.println(userAgent);
	if (userAgent.indexOf("MSIE") > -1) {
			
	} else {
%>
		<link type="text/css" rel="stylesheet" href="<s:url value='/css/syntaxhighlighter/shCoreDefault.css' />"/>
		<script type="text/javascript" src="<s:url value='/js/syntaxhighlighter/shCore.js' />"></script>
		<script type="text/javascript" src="<s:url value='/js/syntaxhighlighter/shBrushJScript.js' />"></script>
		<script type="text/javascript" src="<s:url value='/js/syntaxhighlighter/shBrushCss.js' />"></script>
		<script type="text/javascript" src="<s:url value='/js/syntaxhighlighter/shBrushJava.js' />"></script>
		<script type="text/javascript" src="<s:url value='/js/syntaxhighlighter/shBrushXml.js' />"></script>
		<script type="text/javascript">
			SyntaxHighlighter.defaults['toolbar'] = false;
			SyntaxHighlighter.all();
		</script>
<%		
	}
%>

<script type="text/javascript">
	jQuery( document ).ready(function() {
		resizeSyntaxHighlighterCss();
	});
	
	jQuery( window ).resize(function() {
		resizeSyntaxHighlighterCss();
	});

	function resizeSyntaxHighlighterCss() {
		var el = jQuery("div[class*='SyntaxHighlighter_']");
		var windowWidth = jQuery( window ).width();
		if (windowWidth <= 1024) {
			el.removeClass();
			el.addClass('SyntaxHighlighter SyntaxHighlighter_1024');

		} else if (windowWidth <= 1152) {
			el.removeClass();
			el.addClass('SyntaxHighlighter SyntaxHighlighter_1152');

		} else if (windowWidth <= 1280) {
			el.removeClass();
			el.addClass('SyntaxHighlighter SyntaxHighlighter_1280');

		} else if (windowWidth <= 1366) {
			el.removeClass();
			el.addClass('SyntaxHighlighter SyntaxHighlighter_1366');

		} else if (windowWidth <= 1400) {
			el.removeClass();
			el.addClass('SyntaxHighlighter SyntaxHighlighter_1400');

		} else if (windowWidth <= 1440) {
			el.removeClass();
			el.addClass('SyntaxHighlighter SyntaxHighlighter_1440');

		} else if (windowWidth <= 1600) {
			el.removeClass();
			el.addClass('SyntaxHighlighter SyntaxHighlighter_1600');

		} else if (windowWidth <= 1680) {
			el.removeClass();
			el.addClass('SyntaxHighlighter SyntaxHighlighter_1680');

		} else if (windowWidth <= 1920) {
			el.removeClass();
			el.addClass('SyntaxHighlighter SyntaxHighlighter_1920');

		} else if (windowWidth <= 2048) {
			el.removeClass();
			el.addClass('SyntaxHighlighter SyntaxHighlighter_2048');

		} else if (windowWidth <= 2560) {
			el.removeClass();
			el.addClass('SyntaxHighlighter SyntaxHighlighter_2560');

		} else {
			el.removeClass();
			el.addClass('SyntaxHighlighter SyntaxHighlighter_2560');
		}
	}
</script>
<style type="text/css">
	DIV.SyntaxHighlighter {
		margin: auto;
		margin-top: 0;
		overflow: auto; 
	}
	
	DIV.SyntaxHighlighter_1024 {
		width: 605px;
	}
	
	DIV.SyntaxHighlighter_1152 {
		width: 695px;
	}
	
	DIV.SyntaxHighlighter_1280 {
		/*Arunya.k*/
		/*width: 790px;*/
		width: 1000px;
	}
	
	DIV.SyntaxHighlighter_1366 {
		width: 845px;
	}
	
	DIV.SyntaxHighlighter_1400 {
		width: 870px;
	}
	
	DIV.SyntaxHighlighter_1440 {
		width: 900px;
	}
	
	DIV.SyntaxHighlighter_1600 {
		width: 1010px;
	}
	
	DIV.SyntaxHighlighter_1680 {
		width: 1065px;
	}
	
	DIV.SyntaxHighlighter_1920 {
		width: 1230px;
	}
	
	DIV.SyntaxHighlighter_2048 {
		width: 1320px;
	}
	
	DIV.SyntaxHighlighter_2560 {
		width: 1670px;
	}
	
	UL.NAVIGATE {
		padding-bottom: 10px;
	}
	
	UL.NAVIGATE LI {
		padding-top: 5px;
		padding-bottom: 5px;	
		padding-left: 0px;
		margin-left: -15px;
	}
	
	H4 A.LINK {
		font-weight: bold;
		color: blue;
	}
	
	A.LINK {
		color: blue;
	}
	
	FONT.HIGHLIGHT {
		font-weight: bold;
		color: red;
	}
</style>