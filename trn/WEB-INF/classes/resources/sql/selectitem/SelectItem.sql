searchGlobalDataSelectItem {
	SELECT
	  CGD.CON_GLOBAL_DATA_ID
	  , CGD.CON_GLOBAL_TYPE_ID
	  , CGD.GLOBAL_DATA_CODE
	  , CGD.GLOBAL_DATA_VALUE
	  , CGD.GLOBAL_DATA_REMARK
	  , CGD.LIST_NO
	  , CGD.ACTIVE
	  , CGT.GLOBAL_TYPE_NAME
	  , CGT.ACTIVE
	  , CGT.GLOBAL_TYPE_CODE
	FROM [SCHEMAS].con_global_data CGD
	    LEFT OUTER JOIN [SCHEMAS].con_global_type CGT ON (CGD.CON_GLOBAL_TYPE_ID = CGT.CON_GLOBAL_TYPE_ID)
	WHERE 1 = 1
	  AND CGT.ACTIVE = 'Y'
	  AND CGD.ACTIVE = 'Y'
	ORDER BY CGT.GLOBAL_TYPE_CODE, CGD.CON_GLOBAL_TYPE_ID, CGD.LIST_NO ASC
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : Autocomplete ผู้ใช้งานระบบ QA (GM, SA, SD, PG และ QA)
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchAllQAUserAutoSelectItem{
	SELECT U.USER_ID, U.FULL_NAME
		FROM(
			SELECT SU.USER_ID, SD.DEPARTMENT_ID, CONCAT(SD.DEPARTMENT_CODE, '-', SU.NAME, ' ', SU.SURNAME, ' (', SU.NICKNAME , ')') AS FULL_NAME
	  		FROM [SCHEMAS].sec_user SU
	  	  	LEFT JOIN [SCHEMAS].std_department SD ON SD.DEPARTMENT_ID = SU.DEPARTMENT_ID
	  		WHERE SU.DELETED = 'N'
	  	  	AND SD.DEPARTMENT_ID IN ('2','3','4','5','6')
		)U
	WHERE U.FULL_NAME LIKE UPPER(CONCAT('%', %s , '%'))
	ORDER BY U.department_id, U.FULL_NAME
}


/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : Autocomplete โครงการ (project)
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchProjectsAutoSelectItem{
	SELECT PRO.PROJECT_ID
	  , PRO.PROJECT_NAME
	FROM (
		SELECT PROJECT_ID
			, CONCAT(PROJECT_ABBR, '-', PROJECT_NAME ) AS PROJECT_NAME
		FROM [SCHEMAS].std_project
	) PRO
	WHERE PRO.PROJECT_NAME LIKE UPPER(REPLACE(CONCAT('%', %s , '%'),' ',''))
	ORDER BY PRO.PROJECT_NAME
}


/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : Autocomplete ระบบ (system)
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchSystemsAutoSelectItem{
	SELECT SYS.SYSTEM_ID
		, SYS.SYSTEM_NAME
	FROM (
	    SELECT SYSTEM_ID
	  		, CONCAT(SYSTEM_CODE, '-', SYSTEM_NAME ) AS SYSTEM_NAME
	  	FROM [SCHEMAS].qa_system
	  	WHERE PROJECT_ID = %s
	) SYS
	WHERE SYS.SYSTEM_NAME LIKE UPPER(REPLACE(CONCAT('%', %s , '%'),' ',''))
	ORDER BY SYS.SYSTEM_NAME
}


/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : Autocomplete ระบบย่อย (sub system)
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchSubSystemsAutoSelectItem{
	SELECT SUB.SUB_SYSTEM_ID
	    , SUB.SUB_SYSTEM_NAME
	FROM (
	    SELECT SUB_SYSTEM_ID
	  		, CONCAT(SUB_SYSTEM_CODE, '-', SUB_SYSTEM_NAME ) AS SUB_SYSTEM_NAME
	  	FROM [SCHEMAS].qa_sub_system
	  	WHERE SYSTEM_ID = %s
	  ) SUB
	WHERE SUB.SUB_SYSTEM_NAME LIKE UPPER(REPLACE(CONCAT('%', %s , '%'),' ',''))
	ORDER BY SUB.SUB_SYSTEM_NAME
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : Autocomplete ระบบย่อย (sub system)
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchDepartmentAutoSelectItem {
	SELECT DEPARTMENT_ID
		, DEPARTMENT_NAME
	FROM [SCHEMAS].STD_DEPARTMENT
	WHERE 1=1
		AND DEPARTMENT_NAME LIKE CONCAT(%s, '%')		/* DEPARTMENT_NAME */
	LIMIT %s
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : autocomplete ชื่อ-นามสกุล
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchUserAutoSelectItem {
	SELECT USER_ID
		, CONCAT(TITLE_NAME, ' ', NAME, ' ', SURNAME, ' (', NICKNAME , ')') AS FULLNAME
	FROM [SCHEMAS].SEC_USER
	WHERE 1=1
		AND DELETED = 'N'
		AND DEPARTMENT_ID = %s		/* DEPARTMENT_ID */
		AND CONCAT(NAME, ' ', SURNAME, ' (', NICKNAME , ')') LIKE CONCAT(%s, '%')	/* FULLNAME */
	LIMIT %s	
}

