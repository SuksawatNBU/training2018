
/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : ค้นหาจำนวนข้อมูล (project)
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchCount {
	SELECT COUNT(1) AS TOT
	FROM [SCHEMAS].STD_PROJECT PJ
		LEFT JOIN [SCHEMAS].SEC_USER US ON US.USER_ID = PJ.SPM_ID
		LEFT JOIN [SCHEMAS].STD_POSITION PS on PS.POSITION_ID = US.POSITION_ID
		LEFT JOIN [SCHEMAS].STD_DEPARTMENT DP on DP.DEPARTMENT_ID = PS.DEPARTMENT_ID
	WHERE 1=1
		AND PJ.CREATE_DATE >= %s			          	/* START_DATE '2017-01-20 00:00:00' */
		AND PJ.CREATE_DATE <= %s				        /* END_DATE '2017-02-25 23:59:59' */
		AND PJ.PROJECT_ABBR LIKE %s '%'				  	/* PROJECT_CODE */
		AND PJ.PROJECT_NAME LIKE '%' %s '%'				/* PROJECT_NAME */
		AND PJ.SPM_ID = %s						        /* SPM_ID */
		AND PJ.ACTIVE = %s							    /* ACTIVE */
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : ค้นหาข้อมูล (project)
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchProject{
	SELECT PJ.PROJECT_ID
		, PJ.PROJECT_ABBR AS PROJECT_CODE
		, PJ.PROJECT_NAME
		, CONCAT(DP.DEPARTMENT_CODE ,'-' ,US.NAME,' ',US.SURNAME,' (',US.NICKNAME,')')as SPM_NAME
		, DATE_FORMAT(PJ.CREATE_DATE, '%d/%m/%Y') AS START_DATE
		, PJ.ACTIVE
	FROM [SCHEMAS].STD_PROJECT PJ 
		LEFT JOIN [SCHEMAS].SEC_USER US on PJ.SPM_ID = US.USER_ID 
		LEFT JOIN [SCHEMAS].STD_POSITION PS on PS.POSITION_ID = US.POSITION_ID
		LEFT JOIN [SCHEMAS].STD_DEPARTMENT DP on DP.DEPARTMENT_ID = PS.DEPARTMENT_ID
	WHERE 1=1 
		AND PJ.CREATE_DATE >= %s			          	/* START_DATE '2017-01-20 00:00:00' */
		AND PJ.CREATE_DATE <= %s				        /* END_DATE '2017-02-25 00:00:00' */
		AND PJ.PROJECT_ABBR LIKE %s '%'				  	/* PROJECT_CODE */
		AND PJ.PROJECT_NAME LIKE '%' %s '%'				/* PROJECT_NAME */
		AND PJ.SPM_ID = %s						        /* SPM_ID */
		AND PJ.ACTIVE = %s							    /* ACTIVE */
	ORDER BY
		%s
		LIMIT %s
		, %s
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : ค้นหาข้อมูลซ้ำ (project)
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchCountDup{
	SELECT COUNT(1) AS TOT
	FROM [SCHEMAS].std_project
	WHERE 1=1
	AND PROJECT_ABBR = REPLACE(UPPER(%s), ' ', '')		/* PROJECT_ABBR */
 	AND PROJECT_ID <> %s								/* PROJECT_ID */
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : บึนทึกข้อมูลโครงการ (project)
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
insertProject{
	INSERT INTO [SCHEMAS].std_project(
	  PROJECT_ABBR
	  , PROJECT_CODE
	  , SPM_ID
	  , PROJECT_NAME
	  , PROJECT_PATH
	  , ACTIVE
	  , CREATE_DATE
	  , CREATE_USER
	) VALUES (
	   %s					/* PROJECT_ABBR */
	  ,%s					/* PROJECT_CODE */
	  ,%s					/* SPM_ID */
	  ,%s					/* PROJECT_NAME */
	  ,%s					/* PROJECT_PATH */
	  ,%s					/* ACTIVE */
	  ,CURRENT_TIMESTAMP	/* CREATE_DATE */
	  ,%s					/* CREATE_USER */
	)	
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : บึนทึกแก้ไขข้อมูลโครงการ (project)
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
updateProject{	
	UPDATE [SCHEMAS].std_project SET
	   SPM_ID = %s						/* SPM_ID */
	  ,PROJECT_NAME = %s				/* PROJECT_NAME */
	  ,ACTIVE = %s						/* ACTIVE */
	  ,UPDATE_DATE = CURRENT_TIMESTAMP	/* UPDATE_DATE */
	  ,UPDATE_USER = %s					/* UPDATE_USER */
	WHERE 1=1
	AND PROJECT_ID = %s					/* PROJECT_ID */
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : แสดงข้อมูลแก้ไข (project)
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
searchById{	
	SELECT 
		PJ.PROJECT_ID
		, PJ.PROJECT_ABBR AS PROJECT_CODE
		, PJ.SPM_ID
		, PJ.PROJECT_NAME
		, PJ.ACTIVE
		, DATE_FORMAT(PJ.UPDATE_DATE, '%d/%m/%Y %H:%i:%S')UPDATE_DATE
		, PJ.UPDATE_USER
		, DATE_FORMAT(PJ.CREATE_DATE, '%d/%m/%Y %H:%i:%S')CREATE_DATE
		, PJ.CREATE_USER
		, SD.DEPARTMENT_ID
		, SD.DEPARTMENT_NAME
		, CONCAT(SD.DEPARTMENT_CODE, '-', US.NAME, ' ', US.SURNAME, ' (', US.NICKNAME , ')') AS SPM_NAME
	FROM [SCHEMAS].STD_PROJECT PJ 
		LEFT JOIN [SCHEMAS].SEC_USER US ON PJ.SPM_ID = US.USER_ID 
		LEFT JOIN [SCHEMAS].STD_DEPARTMENT SD ON SD.DEPARTMENT_ID = US.DEPARTMENT_ID
	WHERE 1=1 
		AND PJ.PROJECT_ID = %s				/* PROJECT_ID */
}

/*-----------------------------------------------------------------------------------------------------------------------------------------------------------
SQL : ปรับปรุงสถานะใช้งาน (project)
Description : 
------------------------------------------------------------------------------------------------------------------------------------------------------------*/
setActiveStatus{
	UPDATE [SCHEMAS].std_project SET
		 ACTIVE = %s						/* ACTIVE */
		,UPDATE_DATE = CURRENT_TIMESTAMP	/* UPDATE_DATE */
		,UPDATE_USER = %s					/* UPDATE_USER */
	WHERE PROJECT_ID IN (%s)				/* PROJECT_ID */
}