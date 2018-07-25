package com.cct.trn.core.tutorial.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import util.database.CCTConnection;
import util.log.LogUtil;

import com.cct.abstracts.AbstractManager;
import com.cct.common.CommonUser;
import com.cct.exception.DuplicateException;
import com.cct.exception.MaxExceedException;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;
import com.cct.trn.core.tutorial.project.domain.Project;
import com.cct.trn.core.tutorial.project.domain.ProjectSearch;
import com.cct.trn.core.tutorial.project.domain.ProjectSearchCriteria;

public class ProjectManager extends AbstractManager<ProjectSearchCriteria, ProjectSearch, Project, CommonUser, Locale> {

	private ProjectService service = null;
	
	public ProjectManager(CCTConnection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		this.service = new ProjectService(conn, user, locale);
	}
	
	public ProjectService getService() {
		return service;
	}

	public void setService(ProjectService service) {
		this.service = service;
	}
	
	/* ค้นหาข้อมูลโครงการ */
	@Override
	public List<ProjectSearch> search(ProjectSearchCriteria criteria) throws Exception{
		LogUtil.TRAINING.debug("search");
		
		List<ProjectSearch> listResult = new ArrayList<>();
		try {
			//1.นับจำนวนรายการที่ค้นพบ
			criteria.setTotalResult(service.countData(criteria));
			LogUtil.TRAINING.debug("COUNT DATA [" + criteria.getTotalResult() + "] record.");

			//2.ตรวจสอบจำนวนที่ค้นพบเกินจำนวนที่ระบบกำหนดหรือไม่ ?
	        if ((criteria.isCheckMaxExceed()) && (criteria.getTotalResult() > ParameterConfig.getParameter().getApplication().getMaxExceed())) {
	            //2.1.ถ้าใช่ ให้ confirm ถามเพื่อยืนยันการค้นหา
	            throw new MaxExceedException();
	        } else {
	            //2.2.ถ้าไม่ใช่ให้ค้นหาข้อมูล
	            listResult = service.search(criteria);
	        }
		} catch (Exception e) {
			throw e;
		}
		return listResult;
	}
	
	/*ค้นหาโดยระบุid (แสดงข้อมูลที่แก้ไข)*/
	@Override
	public Project searchById(String id) throws Exception {
		return service.searchById(conn, id);
	}
	
	/*บันทึกข้อมูลโครงการ*/
	@Override
	public int add(Project obj) throws Exception {
		try {
	        
	        //1.Begin transaction
	        conn.setAutoCommit(false);
	        
	        //2.ตรวจสอบบันทึกข้อมูลผู้ใช้ซ้ำ
	        if (service.checkDup(conn, obj)) {
	            throw new DuplicateException(); //Throw DuplicateException ถ้าพบว่าข้อมูลซ้ำ
	        }
	        
	        //3.เพิ่มข้อมูลผู้ใช้งาน
			service.addProject(conn, obj);
			
			//รอดำเนินการ. ...การเพิ่มข้อมูล  path
			
			//4. Commit transaction
	        conn.commit();
	 
	    } catch (Exception e) {
	    	//5. Rollback transaction เมื่อเกิด Error
	        conn.rollback();
	        throw e;
	    } finally {
	        //6. Set AutoCommit กลับคืนเป็น True
	        conn.setAutoCommit(true);
	    }
	    return 0;
	}
	
	/*แก้ไขข้อมูลโครงการ*/
	@Override
	public int edit(Project obj) throws Exception {
		try {
	        //1.ตรวจสอบบันทึกข้อมูลผู้ใช้ซ้ำ
			if (service.checkDup(conn, obj)) {
	            throw new DuplicateException(); //Throw DuplicateException ถ้าพบว่าข้อมูลซ้ำ
	        }
	 
	        //2.แก้ไขข้อมูลผู้ใช้งาน
	        service.edit(obj);
	 
	 
	    } catch (Exception e) {
	        throw e;
	    }
	    return 0;
	}

	@Override
	public int delete(String ids) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateActive(String ids, String activeFlag) throws Exception {
		return service.updateActive(conn, ids, activeFlag);
	}
	
}
