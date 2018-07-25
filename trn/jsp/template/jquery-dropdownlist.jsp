<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<script type="text/javascript">
	<s:include value="/js/dropdownlist/dropdownlist.js"/>
	
	/** 
	* สำหรับจังหวัด - อำเภอ Drop down list โดยใช้ javascript กลางสร้าง Drop down list 
	* และ ajax สำหรัีบ request ข้อมูลผ่านทาง servlet และรับข้อมูล json array กลับมา
	**/
	jQuery(document).ready(function(){
		jQuery('.province_amphur_dropdownlist').removeClass('province_amphur_dropdownlist').each(function() {
			
			if (jQuery(this).attr('id') != undefined) {
				jQuery("#" + jQuery(this).attr('id')).dropdownlist(
						[{	
							url: "<s:url value='/strutsii/provinceSelectItemServlet'/>",
							defaultKey: "",
						    defaultValue: ""
						},{
							inputModelId: jQuery(this).attr('change-id-to'),
							url: "<s:url value='/strutsii/amphurSelectItemServlet'/>",
							postParamsId: {provinceCode: jQuery(this).attr('id')},
							defaultKey: "",
						    defaultValue: ""
						}]
				)
			} else {
				jQuery(this).dropdownlist(
						[{	
							url: "<s:url value='/strutsii/provinceSelectItemServlet'/>",
							defaultKey: "",
						    defaultValue: ""
						},{
							inputModelId: jQuery(this).attr('change-id-to'),
							url: "<s:url value='/strutsii/amphurSelectItemServlet'/>",
							postParamsId: {provinceCode: jQuery(this).attr('id')},
							defaultKey: "",
						    defaultValue: ""
						}]
				)
			}
		});	   

		jQuery('.province_dropdownlist').removeClass('province_dropdownlist').each(function() {
			if (jQuery(this).attr('id') != undefined) {
				jQuery("#" + jQuery(this).attr('id')).dropdownlist([{
					url: "<s:url value='/strutsii/provinceSelectItemServlet'/>",	// url สำหรับ request ข้อมูลจังหวัด	
					postParamsId: {},
					defaultKey: "",
				    defaultValue: ""
				}]);
			} else {
				jQuery(this).dropdownlist([{
					url: "<s:url value='/strutsii/provinceSelectItemServlet'/>",	// url สำหรับ request ข้อมูลจังหวัด	
					postParamsId: {},
					defaultKey: "",
					defaultValue: ""
				}]);
			}
		});	
	});
</script>