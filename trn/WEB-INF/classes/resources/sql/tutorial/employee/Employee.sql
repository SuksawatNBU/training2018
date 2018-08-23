/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : COMBOBOX_PREFIX_SQL
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchPrefixSelectItem {
	SELECT 
	  PREFIX_ID 
	  , PREFIX_NAME
	FROM TRN_PREFIX
	ORDER BY PREFIX_NAME
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : ตรวจสอบข้อมูลพนักงานซ้ำ_SQL
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
checkDupEmployee {
	SELECT COUNT(1) AS TOT
	FROM TRN_EMPLOYEE
	WHERE ACTIVE = 'Y'
	AND NAME = %s
	AND SURNAME = %s
	AND NICK_NAME = %s
	AND SEX = %s
	AND POSITION_ID = %s
	AND EMPLOYEE_ID <> %s
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : เพิ่มข้อมูลพนักงาน_SQL
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
insertEmployee {
	INSERT INTO TRN_EMPLOYEE(
	  NAME
	  ,SURNAME
	  ,NICK_NAME
	  ,PREFIX_ID
	  ,SEX
	  ,POSITION_ID
	  ,START_WORK_DATE
	  ,WORK_STATUS
	  ,REMARK
	  ,ACTIVE
	  ,CREATE_DATE
	  ,CREATE_USER
	) VALUES (
		%s
	  , %s
	  , %s
	  , %s
	  , %s
	  , %s
	  , CONCAT(DATE_FORMAT(%s, '%Y-%m-%d'), ' ', '00:00:00')
	  , %s
	  , %s
	  ,'Y'
	  ,CURRENT_TIMESTAMP -- CREATE_DATE - IN DATETIME
	  , %s
	 )
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : แก้ไขข้อมูลพนักงาน_SQL
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
editEmployee {
	UPDATE TRN_EMPLOYEE
	SET
	  NAME = %s
	  ,SURNAME = %s
	  ,NICK_NAME = %s
	  ,PREFIX_ID = %s
	  ,POSITION_ID = %s
	  ,START_WORK_DATE = CONCAT(%s, ' 00:00:00')
	  ,END_WORK_DATE = CONCAT(%s, ' 00:00:00')
	  ,WORK_STATUS = %s
	  ,REMARK = %s
	  ,UPDATE_DATE = CURRENT_TIMESTAMP
	  ,UPDATE_USER = %s
	WHERE EMPLOYEE_ID = %s
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : ลบข้อมูลพนักงาน_SQL
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
deleteEmployee {
	UPDATE TRN_EMPLOYEE SET
		 ACTIVE = 'N'
		,UPDATE_DATE = CURRENT_TIMESTAMP
		,UPDATE_USER = %s
	WHERE EMPLOYEE_ID IN (%s)
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : นับจำนวนข้อมูลพนักงานตามรหัสพนักงาน_SQL
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchCountIdEmployee {
	SELECT COUNT(1) AS TOT
	FROM TRN_EMPLOYEE EMP
	LEFT JOIN TRN_POSITION POS ON POS.POSITION_ID = EMP.POSITION_ID
	LEFT JOIN TRN_DEPARTMENT DEP ON DEP.DEPARTMENT_ID = POS.DEPARTMENT_ID
	LEFT JOIN TRN_PREFIX PRE ON EMP.PREFIX_ID = PRE.PREFIX_ID
	WHERE 1=1
	AND EMP.ACTIVE = 'Y'
	AND EMP.EMPLOYEE_ID = %s
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : นับจำนวนข้อมูลพนักงาน_SQL
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchCountEmployee {
	SELECT COUNT(1) AS TOT
	FROM TRN_EMPLOYEE EMP
	LEFT JOIN TRN_POSITION POS ON POS.POSITION_ID = EMP.POSITION_ID
	LEFT JOIN TRN_DEPARTMENT DEP ON DEP.DEPARTMENT_ID = POS.DEPARTMENT_ID
	LEFT JOIN TRN_PREFIX PRE ON EMP.PREFIX_ID = PRE.PREFIX_ID
	WHERE 1=1
	AND EMP.ACTIVE = 'Y'
	AND PRE.PREFIX_ID = %s
	AND CONCAT(NAME, ' ', SURNAME) LIKE CONCAT(%s , '%')
	AND NICK_NAME = %s
	AND EMP.SEX = %s
	AND DEP.DEPARTMENT_ID =%s
	AND POS.POSITION_ID = %s
	AND EMP.START_WORK_DATE >= %s		/* START_DATE '2017-01-20 00:00:00' */
	AND EMP.START_WORK_DATE <= %s		/* END_DATE '2017-02-25 00:00:00' */
	AND EMP.WORK_STATUS = %s
}

searchCountEmployeePopup {
	SELECT COUNT(1) AS TOT
	FROM TRN_EMPLOYEE EMP
	LEFT JOIN TRN_POSITION POS ON POS.POSITION_ID = EMP.POSITION_ID
	LEFT JOIN TRN_DEPARTMENT DEP ON DEP.DEPARTMENT_ID = POS.DEPARTMENT_ID
	LEFT JOIN TRN_PREFIX PRE ON EMP.PREFIX_ID = PRE.PREFIX_ID
	WHERE EMP.EMPLOYEE_ID NOT IN (%s)
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : ค้นหาข้อมูลพนักงานตามรหัสพนักงาน_SQL
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchByIdEmployee {
	SELECT EMP.EMPLOYEE_ID
	, PRE.PREFIX_ID
	, PRE.PREFIX_NAME
	, EMP.NAME
	, EMP.SURNAME
	, EMP.NICK_NAME
	, EMP.SEX
	, DEP.DEPARTMENT_ID
	, DEP.DEPARTMENT_NAME
	, POS.POSITION_ID
	, POS.POSITION_NAME
	, DATE_FORMAT(EMP.START_WORK_DATE ,'%d/%m/%Y') AS START_WORK_DATE
	, DATE_FORMAT(EMP.END_WORK_DATE ,'%d/%m/%Y') AS END_WORK_DATE
	, EMP.WORK_STATUS
	, EMP.REMARK
	, EMP.CREATE_DATE
	, EMP.CREATE_USER
	, EMP.UPDATE_DATE
	, EMP.UPDATE_USER
	FROM TRN_EMPLOYEE EMP
	LEFT JOIN TRN_POSITION POS ON POS.POSITION_ID = EMP.POSITION_ID
	LEFT JOIN TRN_DEPARTMENT DEP ON DEP.DEPARTMENT_ID = POS.DEPARTMENT_ID
	LEFT JOIN TRN_PREFIX PRE ON EMP.PREFIX_ID = PRE.PREFIX_ID
	WHERE 1=1
	AND EMP.ACTIVE = 'Y'
	AND EMPLOYEE_ID = %s
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : ค้นหาข้อมูลพนักงาน_SQL
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchEmployee {
	SELECT EMP.EMPLOYEE_ID
	, PRE.PREFIX_ID
	, PRE.PREFIX_NAME
	, CONCAT(NAME, ' ', SURNAME, ' (', NICK_NAME , ')') AS FULLNAME
	, EMP.SEX
	, DEP.DEPARTMENT_ID
	, DEP.DEPARTMENT_NAME
	, POS.POSITION_ID
	, POS.POSITION_NAME
	, DATE_FORMAT(EMP.START_WORK_DATE ,'%d/%m/%Y') AS START_WORK_DATE
	, DATE_FORMAT(EMP.END_WORK_DATE ,'%d/%m/%Y') AS END_WORK_DATE
	, EMP.WORK_STATUS
	, DATE_FORMAT(EMP.CREATE_DATE ,'%d/%m/%Y') AS CREATE_DATE
	, EMP.CREATE_USER 
	, DATE_FORMAT(EMP.UPDATE_DATE ,'%d/%m/%Y') AS UPDATE_DATE
	, EMP.UPDATE_USER
	, EMP.REMARK
	FROM TRN_EMPLOYEE EMP
	LEFT JOIN TRN_POSITION POS ON POS.POSITION_ID = EMP.POSITION_ID
	LEFT JOIN TRN_DEPARTMENT DEP ON DEP.DEPARTMENT_ID = POS.DEPARTMENT_ID
	LEFT JOIN TRN_PREFIX PRE ON EMP.PREFIX_ID = PRE.PREFIX_ID
	WHERE 1=1
	AND EMP.ACTIVE = 'Y'
	AND PRE.PREFIX_ID = %s
	AND CONCAT(NAME, ' ', SURNAME) LIKE CONCAT(%s, '%')
	AND NICK_NAME = %s
	AND EMP.SEX = %s
	AND DEP.DEPARTMENT_ID = %s
	AND POS.POSITION_ID = %s
	AND EMP.START_WORK_DATE >= %s	          	/* START_DATE '2017-01-20 00:00:00' */
	AND EMP.START_WORK_DATE <= %s				/* END_DATE '2017-02-25 00:00:00' */
	AND EMP.WORK_STATUS = %s
	
	LIMIT %s
	, %s
}

searchEmployee2 {
	SELECT EMP.EMPLOYEE_ID
	, PRE.PREFIX_ID
	, PRE.PREFIX_NAME
	, CONCAT(NAME, ' ', SURNAME, ' (', NICK_NAME , ')') AS FULLNAME
	, EMP.SEX
	, DEP.DEPARTMENT_ID
	, DEP.DEPARTMENT_NAME
	, POS.POSITION_ID
	, POS.POSITION_NAME
	, DATE_FORMAT(EMP.START_WORK_DATE ,'%d/%m/%Y') AS START_WORK_DATE
	, DATE_FORMAT(EMP.END_WORK_DATE ,'%d/%m/%Y') AS END_WORK_DATE
	, EMP.WORK_STATUS
	, DATE_FORMAT(EMP.CREATE_DATE ,'%d/%m/%Y') AS CREATE_DATE
	, EMP.CREATE_USER 
	, DATE_FORMAT(EMP.UPDATE_DATE ,'%d/%m/%Y') AS UPDATE_DATE
	, EMP.UPDATE_USER
	, EMP.REMARK
	FROM TRN_EMPLOYEE EMP
	LEFT JOIN TRN_POSITION POS ON POS.POSITION_ID = EMP.POSITION_ID
	LEFT JOIN TRN_DEPARTMENT DEP ON DEP.DEPARTMENT_ID = POS.DEPARTMENT_ID
	LEFT JOIN TRN_PREFIX PRE ON EMP.PREFIX_ID = PRE.PREFIX_ID
	WHERE 1=1
	AND EMP.ACTIVE = 'Y'
	AND PRE.PREFIX_ID = %s
	AND CONCAT(NAME, ' ', SURNAME) LIKE CONCAT(%s, '%')
	AND NICK_NAME = %s
	AND EMP.SEX = %s
	AND DEP.DEPARTMENT_ID = %s
	AND POS.POSITION_ID = %s
	AND EMP.START_WORK_DATE >= %s	          	/* START_DATE '2017-01-20 00:00:00' */
	AND EMP.START_WORK_DATE <= %s				/* END_DATE '2017-02-25 00:00:00' */
	AND EMP.WORK_STATUS = %s
}

searchEmployeePopup {
	SELECT EMP.EMPLOYEE_ID
	, PRE.PREFIX_ID
	, PRE.PREFIX_NAME
	, CONCAT(NAME, ' ', SURNAME, ' (', NICK_NAME , ')') AS FULLNAME
	, EMP.SEX
	, DEP.DEPARTMENT_ID
	, DEP.DEPARTMENT_NAME
	, POS.POSITION_ID
	, POS.POSITION_NAME
	, DATE_FORMAT(EMP.START_WORK_DATE ,'%d/%m/%Y') AS START_WORK_DATE
	, DATE_FORMAT(EMP.END_WORK_DATE ,'%d/%m/%Y') AS END_WORK_DATE
	, EMP.WORK_STATUS
	, DATE_FORMAT(EMP.CREATE_DATE ,'%d/%m/%Y') AS CREATE_DATE
	, EMP.CREATE_USER 
	, DATE_FORMAT(EMP.UPDATE_DATE ,'%d/%m/%Y') AS UPDATE_DATE
	, EMP.UPDATE_USER
	, EMP.REMARK
	FROM TRN_EMPLOYEE EMP
	LEFT JOIN TRN_POSITION POS ON POS.POSITION_ID = EMP.POSITION_ID
	LEFT JOIN TRN_DEPARTMENT DEP ON DEP.DEPARTMENT_ID = POS.DEPARTMENT_ID
	LEFT JOIN TRN_PREFIX PRE ON EMP.PREFIX_ID = PRE.PREFIX_ID
	WHERE EMP.EMPLOYEE_ID NOT IN (%s) 
	
	LIMIT %s
	, %s
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : ออกรายงานข้อมูลพนักงาน_SQL
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchExportEmployee {
	SELECT EMP.EMPLOYEE_ID
	, PRE.PREFIX_ID
	, PRE.PREFIX_NAME
	, CONCAT(NAME, ' ', SURNAME, ' (', NICK_NAME , ')') AS FULLNAME
	, EMP.SEX
	, DEP.DEPARTMENT_ID
	, DEP.DEPARTMENT_NAME
	, POS.POSITION_ID
	, POS.POSITION_NAME
	, DATE_FORMAT(EMP.START_WORK_DATE ,'%d/%m/%Y') AS START_WORK_DATE
	, DATE_FORMAT(EMP.END_WORK_DATE ,'%d/%m/%Y') AS END_WORK_DATE
	, EMP.WORK_STATUS
	, DATE_FORMAT(EMP.CREATE_DATE ,'%d/%m/%Y') AS CREATE_DATE
	, EMP.CREATE_USER 
	, DATE_FORMAT(EMP.UPDATE_DATE ,'%d/%m/%Y') AS UPDATE_DATE
	, EMP.UPDATE_USER
	, EMP.REMARK
	FROM TRN_EMPLOYEE EMP
	LEFT JOIN TRN_POSITION POS ON POS.POSITION_ID = EMP.POSITION_ID
	LEFT JOIN TRN_DEPARTMENT DEP ON DEP.DEPARTMENT_ID = POS.DEPARTMENT_ID
	LEFT JOIN TRN_PREFIX PRE ON EMP.PREFIX_ID = PRE.PREFIX_ID
	WHERE 1=1
	AND EMP.ACTIVE = 'Y'
	AND PRE.PREFIX_ID = %s
	AND CONCAT(NAME, ' ', SURNAME) LIKE CONCAT(%s, '%')
	AND NICK_NAME = %s
	AND EMP.SEX = %s
	AND DEP.DEPARTMENT_ID = %s
	AND POS.POSITION_ID = %s
	AND EMP.START_WORK_DATE >= %s
	AND EMP.START_WORK_DATE <= %s
	AND EMP.WORK_STATUS = %s
	ORDER BY DEP.DEPARTMENT_NAME ASC, POS.POSITION_NAME ASC
}