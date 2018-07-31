package com.cct.trn.core.tutorial.project.service;

import java.util.List;
import java.util.Locale;

import util.database.CCTConnection;

import com.cct.abstracts.AbstractService;
import com.cct.common.CommonUser;
import com.cct.trn.core.config.parameter.domain.SQLPath;
import com.cct.trn.core.tutorial.project.domain.Project;
import com.cct.trn.core.tutorial.project.domain.ProjectSearch;
import com.cct.trn.core.tutorial.project.domain.ProjectSearchCriteria;

public class ProjectService extends AbstractService {

	private ProjectDAO dao = null;

	public ProjectService(CCTConnection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		
		this.dao = new ProjectDAO();
		this.dao.setSqlPath(SQLPath.PROJECT_SQL);
	}
	
	/* การนับจำนวนข้อมูลค้นหาโครงการ */
	protected long countData(ProjectSearchCriteria criteria) throws Exception {
	    try {
	        return  dao.countData(conn, criteria, user, locale);
	    } catch (Exception e) {
	        throw e;
	    }
	}
	
	/* ค้นหาข้อมูลโครงการ */
	protected List<ProjectSearch> search(ProjectSearchCriteria criteria) throws Exception {
		try {
			return dao.search(conn, criteria, user, locale);
		} catch (Exception e) {
	        throw e;
	    }
	}
	
	/* update สถานะผู้ใช้  */
	protected int updateActive(CCTConnection conn, String ids, String activeFlag) throws Exception{
	    try {
	    	// update สถานะผู้ใช้
	        return dao.updateActive(conn, ids, activeFlag, user, locale);
	    } catch (Exception e) {
	        throw e;
	    }
	}
	
	/* ค้นหาข้อมูลโดยระบุ id(แสดงข้อมูลแก้ไข) */
	protected Project searchById(CCTConnection conn,String userId) throws Exception{
	    try {
	    	// ค้นหาข้อมูลโดยระบุ id
	        return dao.searchById(conn, userId, user, locale);
	    } catch (Exception e) {
	        throw e;
	    }
	}
	
	/* ตรวจสอบบันทึกข้อมูลโครงการซ้ำ  */
	protected boolean checkDup(CCTConnection conn, Project obj) throws Exception{
	    try {
	    	// ตรวจสอบบันทึกข้อมูลโครงการซ้ำ
	        return  dao.checkDup(conn, obj, user, locale);
	    } catch (Exception e) {
	        throw e;
	    }
	}
	
	/* เพิ่มข้อมูลโครงการ  */
	protected int addProject(CCTConnection conn, Project obj) throws Exception{
	    try {
	        // เพิ่มข้อมูลผู้ใช้งาน
	        return dao.add(conn, obj, user, locale);
	 
	    } catch (Exception e) {
	        throw e;
	    }
	}
	
	/* แก้ไขข้อมูลโครงการ  */
	protected int edit(Project obj) throws Exception {
		try {
			// แก้ไขข้อมูลโครงการ  
	        return dao.edit(conn, obj, user, locale);
	    } catch (Exception e) {
	        throw e;
	    }
	}
}
