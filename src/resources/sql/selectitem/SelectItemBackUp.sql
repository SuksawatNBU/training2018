searchGlobalDataSelectItem{
	SELECT CGD.GLOBAL_DATA_ID
	, CGD.GLOBAL_TYPE_ID
	, CGD.CODE
	, CGD.VALUE
	FROM [OC].CON_GLOBAL_DATA CGD
	INNER JOIN [OC].CON_GLOBAL_TYPE CGT ON CGT.GLOBAL_TYPE_ID = CGD.GLOBAL_TYPE_ID
	AND CGD.ACTIVE = 'Y'
	AND CGD.ACTIVE = 'Y'
}

/**
 * จังหวัด
 */
searchProvinceSelectItem {
	SELECT PRV.PROVINCE_ID, PRV.PROVINCE_CODE, PRV.PROVINCE_NAME
	FROM [OC].STD_PROVINCE_TEMP PRV
	WHERE PRV.ACTIVE = 'Y'
	AND PRV.PROVINCE_NAME LIKE %s '%'
	ORDER BY PRV.PROVINCE_NAME
	LIMIT 0, %s
}

/**
 * อำเภอ
 */
searchDistrictSelectItem {
	SELECT DIS.DISTRICT_ID, DIS.DISTRICT_CODE, DIS.DISTRICT_NAME
	FROM [OC].STD_DISTRICT DIS
	WHERE DIS.ACTIVE = 'Y'
	AND DIS.PROVINCE_ID =  %s /*รับค่าจาก Combo จังหวัด*/
	AND DIS.DISTRICT_NAME LIKE %s '%'
	ORDER BY DIS.DISTRICT_NAME
	LIMIT 0, %s
}

/**
 * ตำบล
 */
searchSubDistrictSelectItem {
	SELECT SUB.SUBDISTRICT_ID, SUB.DISTRICT_ID, SUB.SUBDISTRICT_CODE, SUB.SUBDISTRICT_NAME
	FROM [OC].STD_SUBDISTRICT SUB
	WHERE SUB.ACTIVE = 'Y'
	AND SUB.DISTRICT_ID =  %s /*รับค่าจาก Combo อำเภอ*/
	AND SUBDISTRICT_NAME LIKE %s '%'
	ORDER BY SUB.SUBDISTRICT_NAME
	LIMIT 0, %s
}

/**
 * หน่วยงาน_SQL
 */
searchOrganizationSelectItem{
	SELECT ORG.ORG_ID
	, ORG.ORG_CODE, ORG.ORG_NAME
	FROM [OC].STD_ORGANIZATION ORG
	WHERE ORG.ACTIVE = 'Y'
}

/**
 * โซน
 */
searchZoneSelectItem{
	SELECT ZN.ZONE_ID, ZN.ZONE_CODE
	, ZN.ZONE_NAME_TH, ZN.ZONE_NAME_EN , CONCAT( IFNULL(ZN.ZONE_NAME_TH,'') , ' / ' , IFNULL(ZN.ZONE_NAME_EN,'') ) as ZONE_NAME
	FROM [SCHEMAS].STD_ZONE ZN
	WHERE ZN.ACTIVE = 'Y'
	ORDER BY ZN.ZONE_NAME_EN
	LIMIT 0, %s
}

/**
 * ประตู
 */
searchGateSelectItem{
	SELECT GT.GATE_ID, GT.GATE_NAME
	FROM [SCHEMAS].CNT_GATE GT
	WHERE 1=1
	AND GT.ZONE_ID = %s
	ORDER BY GT.GATE_NAME
	LIMIT 0, %s
}


/**
 * กิจกรรม
 */
searchActivitySelectItem {
	SELECT ACTIVITY_ID, ACTIVITY_CODE, ACTIVITY_NAME_TH, ACTIVITY_NAME_EN
	FROM [SCHEMAS].TIK_ACTIVITY
	WHERE ACTIVE = 'Y'
	ORDER BY ACTIVITY_NAME_TH, ACTIVITY_NAME_EN
}


/**
 * คำนำหน้าชื่อ
 */
searchPrefixNameSelectItem{
	SELECT TIT.TITLE_ID
	, TIT.TITLE_NAME, TIT.TITLE_TYPE
	FROM [OC].STD_TITLE TIT
	WHERE TIT.ACTIVE = 'Y'
	ORDER BY TIT.TITLE_NAME
}


searchPassSelectItem{
	SELECT CGD.CON_GLOBAL_DATA_ID
	, CGD.CON_GLOBAL_TYPE_ID
	, CGT.GLOBAL_TYPE_CODE
	, CGD.LIST_NO
	, CGD.GLOBAL_DATA_CODE
	, CGD.GLOBAL_DATA_VALUE_EN
	, CGD.GLOBAL_DATA_VALUE_TH
	, CGD.GLOBAL_DATA_REMARK
	, CGD.ACTIVE
	FROM [SCHEMAS].CON_GLOBAL_DATA CGD
	INNER JOIN [SCHEMAS].CON_GLOBAL_TYPE CGT ON CGT.CON_GLOBAL_TYPE_ID = CGD.CON_GLOBAL_TYPE_ID
	AND CGD.ACTIVE = 'Y'
	AND CGD.ACTIVE = 'Y'
	/*AND CGD.CON_GLOBAL_TYPE_ID = 1*/
	ORDER BY CGD.LIST_NO ASC
}


/*
 * ประเภทตัวแทนจำหน่าย
 * */
searchAgentTypeSelectItem{
	SELECT AGENCY_GROUP_ID, AGENCY_GROUP_NAME
	,START_DATE ,EXPIRY_DATE
	FROM [SCHEMAS].CUS_AGENCY_GROUP
	WHERE ACTIVE = 'Y'
	AND ((START_DATE IS NULL AND EXPIRY_DATE IS NULL)
	OR (START_DATE IS NULL AND EXPIRY_DATE >= CURDATE())
	OR (START_DATE <= CURDATE() AND EXPIRY_DATE IS NULL)
	OR (START_DATE <= CURDATE() AND EXPIRY_DATE >= CURDATE()))
}

/*
 * Combo box คำนำหน้าชื่อ_SQL (เฉพาะนิติบุคคล)
 * */
searchTitleType2SelectItem{
	SELECT TITLE_ID, TITLE_NAME
	FROM [SCHEMAS].STD_TITLE
	WHERE TITLE_TYPE = '2'
	AND ACTIVE = 'Y'
}

/*-----------------------------------------------------------------------------------------------------------
SQL : ข้อมูล Combo box หน่วย_SQL
Description : [จัดการข้อมูลกลุ่มและสิทธิประโยชน์สมาชิก › เพิ่ม]
-------------------------------------------------------------------------------------------------------------*/
searchComboPeriod{
	SELECT GLOBAL_DATA_CODE, GLOBAL_DATA_VALUE_TH
	FROM [SCHEMAS].CON_GLOBAL_DATA
	WHERE CON_GLOBAL_TYPE_ID = 5
	AND LIST_NO IN (4, 5, 6)
	AND ACTIVE = 'Y'
	ORDER BY LIST_NO
}


/*-----------------------------------------------------------------------------------------------------------
SQL : Combo ชื่อระบบ_SQL
Description : [ ไม่มี]
-------------------------------------------------------------------------------------------------------------*/
searchSystemName {
	SELECT DISTINCT(OP.GROUP_SYSTEM), SY.LABEL_TH
	FROM [SCHEMAS].SEC_OPERATOR OP
	INNER JOIN SEC_OPERATOR SY ON OP.GROUP_SYSTEM = SY.OPERATOR_ID
	ORDER BY OP.GROUP_SYSTEM
}

/*-----------------------------------------------------------------------------------------------------------
SQL : ข้อมูล Combo box หน่วย_SQL
Description : [จัดการข้อมูลกลุ่มและสิทธิประโยชน์สมาชิก › เพิ่ม]
-------------------------------------------------------------------------------------------------------------*/
searchAgencySelectItem{
	SELECT CUS.AGENCY_ID
		,CONCAT(IFNULL(TIT.TITLE_NAME,''),CUS.NAME) AGENCT_NAME
	FROM [SCHEMAS].CUS_AGENCY CUS
		LEFT JOIN [SCHEMAS].STD_TITLE TIT ON TIT.TITLE_ID = CUS.TITLE_ID
	ORDER BY CUS.NAME
}





/*-----------------------------------------------------------------------------------------------------------
SQL : Combo ชื่อกลุ่มผู้ใช้_SQL
Description : [ ไม่มี]
-------------------------------------------------------------------------------------------------------------*/
searchComboGroup {
	SELECT DISTINCT(GR.GROUP_ID), GR.GROUP_NAME
	FROM [SCHEMAS].SEC_GROUP GR 
	ORDER BY GR.GROUP_NAME/*xx*/
}


/*-----------------------------------------------------------------------------------------------------------------------------------
SQL : COMBO ผู้ใช้งานผ่านเว็บไซต์_SQL
Description : ไม่มี
------------------------------------------------------------------------------------------------------------------------------------*/
searchAgencyUserSelectItem {
	SELECT CONCAT(TIT.TITLE_NAME,' ', CUS.NAME,'  ',CUS.SURNAME) FULLNAME
		,CUS.AGENCY_USER_ID
	FROM [SCHEMAS].CUS_AGENCY_USER CUS
		, [SCHEMAS].STD_TITLE TIT
	WHERE TIT.TITLE_ID = CUS.TITLE_ID
		AND CUS.AGENCY_ID = %s /*รับค่า AGENCY_ID จาก COMBO ชื่อบริษัท*/
}

/*
 * SQL : Combo ประเภทสมาชิก 6 ประเภท_SQL
 * Description : [ไม่มี]
 * */
searchComMemberGroupTypeSelectItem{
	SELECT COM_MEMBER_GROUP_ID, COM_MEMBER_GROUP_NAME
	FROM [SCHEMAS].CUS_COM_MEMBER_GROUP
	WHERE ACTIVE = 'Y'
	AND ((START_DATE IS NULL AND EXPIRY_DATE IS NULL)
	OR (START_DATE IS NULL AND EXPIRY_DATE >= CURDATE())
	OR (START_DATE <= CURDATE() AND EXPIRY_DATE IS NULL)
	OR (START_DATE <= CURDATE() AND EXPIRY_DATE >= CURDATE()))
}

/*-----------------------------------------------------------------------------------------------------------------------------------
SQL : Combo ประเภทผู้ซื้อ_SQL (ใช้เฉพาะที่ระบบจำหน่ายตั๋ว)
Description : -
------------------------------------------------------------------------------------------------------------------------------------*/
searchBuyerTypeOnlyTicketSelectItem{
	SELECT GLOBAL_DATA_CODE, GLOBAL_DATA_VALUE_TH
	FROM [SCHEMAS].CON_GLOBAL_DATA
	WHERE CON_GLOBAL_TYPE_ID = 7
	AND GLOBAL_DATA_CODE <> 'S'
	AND ACTIVE = 'Y'
	ORDER BY LIST_NO
}

/*-----------------------------------------------------------------------------------------------------------------------------------
SQL : Combo ประเภทการชำระเงิน_SQL (ใช้เฉพาะที่ระบบจำหน่ายตั๋ว)
Description : -
------------------------------------------------------------------------------------------------------------------------------------*/
searchPaymentTypeOnlyTicketSelectItem{
	SELECT GLOBAL_DATA_CODE, GLOBAL_DATA_VALUE_TH
	FROM [SCHEMAS].CON_GLOBAL_DATA
	WHERE CON_GLOBAL_TYPE_ID = 11
	AND GLOBAL_DATA_REMARK like '%T%'
	%s
	AND ACTIVE = 'Y'
	ORDER BY LIST_NO
}

/*-----------------------------------------------------------------------------------------------------------------------------------
SQL : COMBO ชื่อกลุ่มสมาชิก_SQL
Description : -
------------------------------------------------------------------------------------------------------------------------------------*/
searchMemberGroupByBuyerTypeSelectItem{
	SELECT DISTINCT MEMBER_GROUP_NAME
	FROM [SCHEMAS].CUS_ALL_MEMBER
	WHERE TABLE_NAME= %s
	ORDER BY MEMBER_GROUP_NAME
}

/*-----------------------------------------------------------------------------------------------------------
SQL :  Combo เอกสารอ้างอิง_SQL
Description : [ไม่มี]
-------------------------------------------------------------------------------------------------------------*/
searchRefDocSelectItem{
	SELECT GBL.GLOBAL_DATA_CODE, GBL.GLOBAL_DATA_VALUE_TH
	FROM [SCHEMAS].CON_GLOBAL_DATA AS GBL
	WHERE GBL.CON_GLOBAL_TYPE_ID = 4
	AND GBL.ACTIVE = 'Y'
	ORDER BY GBL.LIST_NO
}

/*-----------------------------------------------------------------------------------------------------------------------------------
SQL : Combo COUNTER_SQL
Description : -
------------------------------------------------------------------------------------------------------------------------------------*/
searchCounterSelectItem{
	SELECT COUNTER_ID, COUNTER_NAME
	FROM [SCHEMAS].TIK_COUNTER
	WHERE ACTIVE = 'Y'
	ORDER BY COUNTER_NAME
}

/*-----------------------------------------------------------------------------------------------------------------------------------
SQL : Combo SHIFT_SQL
Description : -
------------------------------------------------------------------------------------------------------------------------------------*/
searchShiftSelectItem{
	SELECT SHIFT_ID, SHIFT_NAME
	FROM [SCHEMAS].TIK_SHIFT
	WHERE ACTIVE = 'Y'
	ORDER BY SHIFT_NAME
}

/*-----------------------------------------------------------------------------------------------------------------------------------
SQL : COMBO สัญชาติ_SQL
Description : -
------------------------------------------------------------------------------------------------------------------------------------*/
searchNationalitySelectItem{
	SELECT COUNTRY_ID, CONCAT(COUNTRY_CODE_ALP3 ,' / ',NAT_NAME_EN) AS NAT_NAME_EN, COUNTRY_CODE_ALP3
	FROM [SCHEMAS].STD_COUNTRY
	WHERE ACTIVE = 'Y'
	ORDER BY LIST_ORDER
}

/*-----------------------------------------------------------------------------------------------------------------------------------
SQL : COMBO สัญชาติต่างชาติ_SQL
Description : -
------------------------------------------------------------------------------------------------------------------------------------*/
searchNationalityForeignCodeSelectItem{
	SELECT COUNTRY_ID, CONCAT(COUNTRY_CODE_ALP3 ,' / ',NAT_NAME_EN) AS NAT_NAME_EN, COUNTRY_CODE_ALP3
	FROM [SCHEMAS].STD_COUNTRY
	WHERE ACTIVE = 'Y'
	AND COUNTRY_CODE_ALP3 <> 'THA'
	ORDER BY LIST_ORDER
}

/*-----------------------------------------------------------------------------------------------------------------------------------
SQL : COMBO สัญชาติต่างชาติ_SQL
Description : -
------------------------------------------------------------------------------------------------------------------------------------*/
searchNationalityForeignSelectItem {
	SELECT COUNTRY_ID, COUNTRY_CODE_ALP3,COUNTRY_NAME_EN,COUNTRY_NAME_TH
	FROM [SCHEMAS].STD_COUNTRY
	WHERE ACTIVE = 'Y'
	AND COUNTRY_CODE_ALP3 <> 'THA'
	ORDER BY LIST_ORDER, COUNTRY_NAME_TH
}

/*-----------------------------------------------------------------------------------------------------------------------------------
SQL : COMBO สัญชาติต่างชาติ_SQL
Description : -
------------------------------------------------------------------------------------------------------------------------------------*/
searchNationalityForeignAjaxSelectItem {
	SELECT COUNTRY_ID, COUNTRY_CODE_ALP3,COUNTRY_NAME_EN,COUNTRY_NAME_TH
	FROM [SCHEMAS].STD_COUNTRY
	WHERE ACTIVE = 'Y'
	AND COUNTRY_CODE_ALP3 <> 'THA'
	AND COUNTRY_NAME_EN LIKE %s '%'
	ORDER BY LIST_ORDER, COUNTRY_NAME_TH
	LIMIT 0, %s
}

/**
 * SQL : Combo ประเภทสมาชิก_SQL
 * Description : [ไม่มี]
 */
searchMemberGroupTypeSelectItem{
	SELECT MEMBER_GROUP_ID, MEMBER_GROUP_NAME, GROUP_CODE, AGE_OF_GROUP
	FROM [SCHEMAS].CUS_MEMBER_GROUP
	WHERE ACTIVE = 'Y'
	AND ((START_DATE IS NULL AND EXPIRY_DATE IS NULL)
	OR (START_DATE IS NULL AND EXPIRY_DATE >= CURDATE())
	OR (START_DATE <= CURDATE() AND EXPIRY_DATE IS NULL)
	OR (START_DATE <= CURDATE() AND EXPIRY_DATE >= CURDATE()))
}

/*-----------------------------------------------------------------------------------------------------------------------------------
SQL : Combo สถานะปิดยอด_SQL
Description : -
------------------------------------------------------------------------------------------------------------------------------------*/
searchSummaryStatusSelectItem{
	SELECT GLOBAL_DATA_CODE, GLOBAL_DATA_VALUE_TH
	FROM [SCHEMAS].CON_GLOBAL_DATA
	WHERE CON_GLOBAL_TYPE_ID = 29
	AND ACTIVE = 'Y'
	AND GLOBAL_DATA_REMARK = 'Y'
	ORDER BY LIST_NO
}

/*-----------------------------------------------------------------------------------------------------------------------------------
SQL : Combo ชื่อเจ้าหน้าที่_SQL
Description : -
------------------------------------------------------------------------------------------------------------------------------------*/
searchStaffNameSelectItem{
	SELECT USER_ID, CONCAT(FORENAME, '  ',SURNAME) AS FULL_NAME
	FROM [SCHEMAS].SEC_USER
	WHERE 1=1 
	ORDER BY FORENAME
}


/**
 * SQL : Combo ประเทศและสัญชาติ_SQL
 * Description : ไม่มี
 */
searchNationalityMemberSelectItem {
	SELECT CT.COUNTRY_ID
	, CT.COUNTRY_CODE_ALP2, CT.COUNTRY_CODE_ALP3
	, CT.COUNTRY_NAME_TH, CT.COUNTRY_NAME_EN
	, CT.NAT_NAME_TH, CT.NAT_NAME_EN
	, CT.LIST_ORDER
	FROM [SCHEMAS].STD_COUNTRY CT
	WHERE CT.ACTIVE = 'Y'
	ORDER BY CT.LIST_ORDER, CT.COUNTRY_NAME_TH
}

/*-----------------------------------------------------------------------------------------------------------
SQL : Combo ประเภทบัตร_SQL
Description : -
-----------------------------------------------------------------------------------------------------------*/
searchPrefixCard{	
	SELECT C.CARD_TYPE_ID, CON.PREFIX_CARD_NAME
	FROM [SCHEMAS].PRE_CARD_TYPE C
	INNER JOIN [SCHEMAS].CON_PREFIX_BARCODE CON ON CON.PREFIX_BARCODE_ID = C.PREFIX_BARCODE_ID
	ORDER BY CON.PREFIX_CARD_NAME
}

/*-----------------------------------------------------------------------------------------------------------
SQL : Combo จุดบริการเติมเงิน/คืนเงิน_SQL
Description : -
-----------------------------------------------------------------------------------------------------------*/
searchPrepaidService{
	SELECT PREPAID_SERVICE_ID, CONCAT(SERV_NAME , ':', SERV_CODE) AS SERV_NAME
	FROM [SCHEMAS].PRE_PREPAID_SERVICE
	WHERE ACTIVE = 'Y'
	ORDER BY SERV_NAME
}

/*-----------------------------------------------------------------------------------------------------------
SQL : Combo จุดบริการรับชำระ_SQL
Description : -
-----------------------------------------------------------------------------------------------------------*/
searchCounterService{
	SELECT COUNTER_SERVICE_ID, SERV_NAME
	FROM [SCHEMAS].PAY_COUNTER_SERVICE
	WHERE ACTIVE = 'Y'
}

/*-----------------------------------------------------------------------------------------------------------------------------------
SQL : Combo ชนิดบัตร_SQL
Description : ไม่มี
------------------------------------------------------------------------------------------------------------------------------------*/
searchTicket{
	SELECT TICKET_ID
	, TICKET_CODE						/*ชนิดบัตร*/
	, TICKET_PRICE						/*ราคา*/				
	FROM [SCHEMAS].TIK_TICKET
	WHERE ACTIVE = 'Y'
	ORDER BY LIST_NO
}

/**
 * โซน EN
 */
searchZoneENSelectItem{
	SELECT ZN.ZONE_ID
	, ZN.ZONE_NAME_EN 
	FROM [SCHEMAS].STD_ZONE ZN
	WHERE ZN.ACTIVE = 'Y'
	ORDER BY ZN.ZONE_NAME_EN
}

/*-----------------------------------------------------------------------------------------------------------
SQL : Combo Month_SQL
Description : [ ไม่มี]
-------------------------------------------------------------------------------------------------------------*/
searchMonthSelectItem{
	SELECT MNT.MNT_FULL_TH , MNT.MONTH_CODE
	FROM [SCHEMAS].CON_MONTH MNT
	ORDER BY MNT.MONTH_ID
}


/*-----------------------------------------------------------------------------------------------------------
SQL : Combo ประเภทผู้เข้าชม_SQL รายงานการซื้อบัตรในแต่ละประเภท
Description : [ ไม่มี]
-------------------------------------------------------------------------------------------------------------*/
searchVistorTypeSelectItem{
	SELECT GLOBAL_DATA_CODE AS VISITOR_CODE,
	    GLOBAL_DATA_VALUE_TH AS VISITOR_TYPE
	FROM [SCHEMAS].CON_GLOBAL_DATA
	WHERE CON_GLOBAL_TYPE_ID = 6
	AND ACTIVE = 'Y'
}


/*-----------------------------------------------------------------------------------------------------------
SQL : Combo ประเภทผู้ซื้อ_SQL รายงานการซื้อบัตรในแต่ละประเภท
Description : [ ไม่มี]
-------------------------------------------------------------------------------------------------------------*/
searchBuyerTypeSelectItem{
	SELECT GLOBAL_DATA_CODE AS BUYER_CODE,
	    GLOBAL_DATA_VALUE_TH AS BUYER_TYPE
	FROM [SCHEMAS].CON_GLOBAL_DATA
	WHERE CON_GLOBAL_TYPE_ID = 7
	AND ACTIVE = 'Y'
	AND GLOBAL_DATA_CODE IN ('M','A')
}

/*-----------------------------------------------------------------------------------------------------------------------------------
SQL : Combo ประเภทสมาชิก_SQL
Description : ประเภทสมาชิก 6 ประเภท
------------------------------------------------------------------------------------------------------------------------------------*/
searchMemberTypeSelectItem{
	SELECT GROUP_CODE
	, COM_MEMBER_GROUP_NAME
	FROM [SCHEMAS].CUS_COM_MEMBER_GROUP
	WHERE ACTIVE = 'Y'
	ORDER BY COM_MEMBER_GROUP_NAME
}

/*-----------------------------------------------------------------------------------------------------------
SQL : Combo จุดบริการรับชำระ_SQL
Description : -
-----------------------------------------------------------------------------------------------------------*/
searchCounterServiceType {
	SELECT C.COUNTER_SERVICE_ID
		, CONCAT(CON.GLOBAL_DATA_VALUE_TH,'-', C.SERV_NAME) AS SERV_NAME
	FROM [SCHEMAS].PAY_COUNTER_SERVICE C
		INNER JOIN [SCHEMAS].CON_GLOBAL_DATA CON ON CON.CON_GLOBAL_TYPE_ID = 25 AND CON.GLOBAL_DATA_CODE = C.COUNTER_SERV_TYPE
	WHERE C.ACTIVE = 'Y'
	ORDER BY C.SERV_NAME
}

/*-----------------------------------------------------------------------------------------------------------
SQL : Combo ประเภทบัตร_SQL
Description : -
-----------------------------------------------------------------------------------------------------------*/
searchPrefixCardType {	
	SELECT C.CARD_TYPE_ID, CON.PREFIX_CARD_NAME
	FROM [SCHEMAS].PRE_CARD_TYPE C
		INNER JOIN [SCHEMAS].CON_PREFIX_BARCODE CON ON CON.PREFIX_BARCODE_ID = C.PREFIX_BARCODE_ID
	ORDER BY CON.PREFIX_CARD_NAME
}

/*-----------------------------------------------------------------------------------------------------------
SQL : Combo จุดบริการเติมเงิน_SQL
Description : -
-----------------------------------------------------------------------------------------------------------*/
searchPrepaidServiceForRep {
	SELECT PREPAID_SERVICE_ID, SERV_NAME
	FROM [SCHEMAS].PRE_PREPAID_SERVICE
	WHERE ACTIVE = 'Y'
	ORDER BY SERV_NAME
}

/*-----------------------------------------------------------------------------------------------------------
SQL : Combo ประเภทสินค้าและบริการ_SQL
Description : [ ไม่มี]
-------------------------------------------------------------------------------------------------------------*/
searchCategory{
	SELECT GROUP_CATEGORY_CODE, CATNAME
	FROM [SCHEMAS].PAY_CATEGORY
	WHERE PARENT = 0
	AND LEVEL = 1
	ORDER BY CATNAME
}

/*------------------------------------------------------------------------------------------------------------
SQL : Combo RelayประตูSQL
Description : ไม่มี
-------------------------------------------------------------------------------------------------------------*/
searchRelayGate{
	SELECT GD.GLOBAL_DATA_VALUE_EN , GD.GLOBAL_DATA_CODE
	FROM [SCHEMAS].CON_GLOBAL_DATA GD 
	WHERE GD.CON_GLOBAL_TYPE_ID = 37
	ORDER BY GD.LIST_NO 
}

/**
 *SQL : COMBO ประเภทรายการ_SQL
 *Description : ไม่มี
 */
searchTransactionType{
	SELECT
	 GLOBAL_DATA_CODE
	 , GLOBAL_DATA_VALUE_TH
	FROM [SCHEMAS].CON_GLOBAL_DATA
	WHERE CON_GLOBAL_TYPE_ID =35
	AND ACTIVE = 'Y'
	ORDER BY LIST_NO
}


/*-----------------------------------------------------------------------------------------------------------
SQL : Combo ช่องทางจำหน่ายบัตร_SQL
Description : -
-----------------------------------------------------------------------------------------------------------*/
searchSaleType {
	SELECT GLOBAL_DATA_CODE
	, GLOBAL_DATA_VALUE_TH
	, GLOBAL_DATA_VALUE_EN
	FROM [SCHEMAS].CON_GLOBAL_DATA
	WHERE CON_GLOBAL_TYPE_ID = 39 /*FIX ช่องทางจำหน่ายบัตร*/
	AND ACTIVE = 'Y'
	ORDER BY LIST_NO
}

/*-----------------------------------------------------------------------------------------------------------
SQL : Combo_Counter_SQL
Description : เพิ่มใหม่ 04/08/2558
- แก้ไขให้ดึงจาก TIK_COUNTER 22/09/2558
-----------------------------------------------------------------------------------------------------------*/
searchCounterShiftOnSelectItem {
	SELECT COUNTER_ID, COUNTER_NAME
	FROM [SCHEMAS].TIK_COUNTER
	WHERE  ACTIVE = 'Y'
	ORDER BY COUNTER_ID
}

/*-----------------------------------------------------------------------------------------------------------------------------------
SQL : Combo_Shift_SQL
Description : เพิ่มใหม่ 04/08/2558
- แก้ไขให้ดึงจาก TIK_SHIFT 22/09/2558
------------------------------------------------------------------------------------------------------------------------------------*/
searchShiftShiftOnSelectItem {
	SELECT SHIFT_ID, SHIFT_NAME
	FROM [SCHEMAS].TIK_SHIFT
	WHERE ACTIVE = 'Y'
	ORDER BY SHIFT_ID
}
