<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<link type="text/css" rel="stylesheet" href="<s:property value='#session.themeActive' />"/>
<link type="text/css" rel="stylesheet" href="<s:url value='/css/theme/jquery-ui-theme-custom.css' />"/>
<link type="text/css" rel="stylesheet" href="<s:url value='/css/ui/pagination.css' />"/>
<link type="text/css" rel="stylesheet" href="<s:url value='/css/ui/headersorts.css' />"/>

<!--[if lte IE 8]>
	<link href="<s:url value='/css/default-ie.css' />" rel="stylesheet" type="text/css"/>
	<style type="text/css">
		.THEAD_SCROLL_Y {
			position: absolute !important; 
			margin-left: -0.5px !important;
		}
		.TABLE_SCROLL_Y {
		
		}
	</style>
<![endif]-->

<!--[if gte IE 9]>
	<link href="<s:url value='/css/default-ie.css' />" rel="stylesheet" type="text/css"/>
	<style type="text/css">
		.THEAD_SCROLL_Y {
			position: absolute !important; 
			margin-left: -0.5px !important;
		}
		.TABLE_SCROLL_Y {
			margin-left: -0.5px !important;
		}
	</style>	
<![endif]-->		

<!--[if !IE]><!-->
	<link href="<s:url value='/css/default-not-ie.css' />" rel="stylesheet" type="text/css"/>
	<link href="<s:url value='/css/style-apps.css' />" rel="stylesheet" type="text/css"/>
	<style type="text/css">
		.THEAD_SCROLL_Y {
			position: absolute !important; 
			margin-left: -1px !important;
		}
		.TABLE_SCROLL_Y {
		
		}
	</style>
<!--<![endif]-->

<link href="<s:url value='/css/css1600.css' />" rel="stylesheet" type="text/css"/>
<link type="text/css" rel="stylesheet" href="<s:url value='/css/datatable/jquery.dataTables.css' />"/>
<link type="text/css" rel="stylesheet" href="<s:url value='/css/datatable/jquery.dataTables_themeroller.css' />"/>