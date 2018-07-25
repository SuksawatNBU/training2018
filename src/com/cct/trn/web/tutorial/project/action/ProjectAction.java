package com.cct.trn.web.tutorial.project.action;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import util.bundle.BundleUtil;
import util.database.CCTConnection;
import util.database.CCTConnectionProvider;
import util.database.CCTConnectionUtil;
import util.log.LogUtil;

import com.cct.common.CommonAction;
import com.cct.common.CommonModel.PageType;
import com.cct.domain.GlobalType;
import com.cct.domain.GlobalVariable;
import com.cct.domain.Transaction;
import com.cct.exception.AuthorizationException;
import com.cct.exception.UCPValidateException;
import com.cct.interfaces.InterfaceAction;
import com.cct.trn.core.config.parameter.domain.DBLookup;
import com.cct.trn.core.security.authorization.domain.PFCode;
import com.cct.trn.core.selectitem.service.SelectItemManager;
import com.cct.trn.core.tutorial.project.domain.Project;
import com.cct.trn.core.tutorial.project.domain.ProjectModel;
import com.cct.trn.core.tutorial.project.domain.ProjectSearch;
import com.cct.trn.core.tutorial.project.domain.ProjectSearchCriteria;
import com.cct.trn.core.tutorial.project.service.ProjectManager;
import com.opensymphony.xwork2.ModelDriven;

public class ProjectAction extends CommonAction implements InterfaceAction, ModelDriven<ProjectModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4504815638300199540L;
	
	private ProjectModel model = new ProjectModel();
	
	public ProjectAction() {
		try {
			 //กำหนดค่าสิทธิ์การใช้งานเริ่มต้น
			getAuthorization(PFCode.TUR_PROJECTS);
			
		} catch (Exception e) {
			LogUtil.TRAINING.error("", e);
		}
		
	}

	@Override
	public ProjectModel getModel() {
		return model;
	}

	@Override
	public String init() throws AuthorizationException, UCPValidateException {
		CCTConnection conn = null;
		String result = null;
		try {
			LogUtil.TRAINING.debug("init");
			
			//1.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());

			//2.ตรวจสอบสิทธิ์การใช้งาน และกำหนดค่าเริ่มต้นให้กับหน้าค้นหาของระบบ
			result = manageInitial(conn, model, new ProjectSearchCriteria(), PF_CODE.getSearchFunction(), PageType.SEARCH);
			
		} catch (Exception e) {
			LogUtil.TRAINING.error("", e);
			//3.จัดการ exception กรณีที่มี exception เกิดขึ้นในระบบ
	        manageException(conn, PF_CODE.getSearchFunction(), this, e, model);
		}finally{
			//4.Load combo ทั้งหมดที่ใช้ในหน้าค้นหา เพื่อเตรียม binding เข้ากับ criteria
	        getComboForSearch(conn);
	         
	        //5.Close connection หลังเลิกใช้งาน
	        CCTConnectionUtil.close(conn);
		}
		return result;
	}

	@Override
	public void getComboForSearch(CCTConnection conn) {
		try {
			// load combo Active status
			model.setLstActiveStatus(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.ACTIVE_STATUS.getValue()));
			// load combo SPM
			//SelectItemManager manager = new SelectItemManager(conn, getUser(), getLocale());
//			model.setLstAllUser(manager.searchAllQAUserSelectItem(conn, getLocale())); 
		} catch (Exception e) {
			LogUtil.TRAINING.error("", e);
		}
	}

	@Override
	public void getComboForAddEdit(CCTConnection conn) {
		try {
			// load combo Active status
			model.setLstActiveStatus(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.ACTIVE_STATUS.getValue()));
			// load combo SPM 
			//SelectItemManager manager = new SelectItemManager(conn, getUser(), getLocale());
			//model.setLstAllUser(manager.searchAllQAUserSelectItem(conn, getLocale()));
			  
		} catch (Exception e) {
			LogUtil.TRAINING.error("", e);
		}	
	}

	@Override
	public String search() throws AuthorizationException, UCPValidateException {
		String result 		= null;
		CCTConnection conn 	= null;
		ProjectManager manager = null;
		try {
			LogUtil.TRAINING.debug(getUser());
			
			//[1] สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());

			//[2] ตรวจสอบสิทธิ์การใช้งาน และจัดการเงือนไขการค้นหา
			result = manageSearchAjax(conn, model, model.getCriteria(), PF_CODE.getSearchFunction());
			
			//[3] ค้นหาข้อมูล
			manager = new ProjectManager(conn, getUser(), getLocale());
			List<ProjectSearch> lstResult = manager.search(model.getCriteria());
			
			//[4] จัดการผลลัพธ์และข้อความถ้าไม่พบข้อมูล
			manageSearchResult(model, lstResult);
			
		} catch (Exception e) {
			LogUtil.TRAINING.error("", e);
			manageException(conn, PF_CODE.getSearchFunction(), this, e, model);
			
		} finally {
			getComboForSearch(conn);
			CCTConnectionUtil.close(conn);
		}

		LogUtil.TRAINING.debug("[ ###RESULT ] : " + result);
		return result;
	}

	@Override
	public String gotoAdd() throws AuthorizationException, UCPValidateException {
		String result = null;
		CCTConnection conn = null;

		try {
			// 1.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());

			// 2.ตรวจสอบสิทธิ์ หน้าเพิ่ม
			result = manageGotoAdd(conn, model);
			
			model.setProject(new Project());
			
			// กำหนดค่า checkbox ให้เป็น active (checked)
			model.getProject().getActive().setCode(GlobalVariable.FLAG_ACTIVE);
			ResourceBundle bundle = BundleUtil.getBundle("resources.bundle.tutorial.MessageProject", getLocale());
			model.getProject().getActive().setDesc(bundle.getString("pro.activeDesc"));
			

		} catch (Exception e) {
			LogUtil.TRAINING.error("", e);
			// 3.จัดการ exception กรณีที่มี exception เกิดขึ้นในระบบ
			manageException(conn, PF_CODE.getAddFunction(), this, e, model);
		} finally {
			getComboForAddEdit(conn);
			// 5.Close connection หลังเลิกใช้งาน
			CCTConnectionUtil.close(conn);
		}
		
		return result;
	}

	@Override
	public String add() throws AuthorizationException, UCPValidateException {
		String result = null;

		CCTConnection conn = null;

		try {
			//1.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());

			//2.ตรวจสอบสิทธิ์ หน้าเพิ่ม
			result = manageAdd(conn, model);
			
			//3.save Data
			ProjectManager manager = new ProjectManager(conn, getUser(), getLocale());
			manager.add(model.getProject());
			
			model.setProject(new Project());
			
			// กำหนดค่า checkbox ให้เป็น active (checked)
			model.getProject().getActive().setCode(GlobalVariable.FLAG_ACTIVE);
			ResourceBundle bundle = BundleUtil.getBundle("resources.bundle.tutorial.MessageProject", getLocale());
			model.getProject().getActive().setDesc(bundle.getString("pro.activeDesc"));

		} catch (Exception e) {
			LogUtil.TRAINING.error("", e);
			//5.จัดการ exception กรณีที่มี exception เกิดขึ้นในระบบ
			manageException(conn, PF_CODE.getAddFunction(), this, e, model);
		} finally {
			//6.Load combo ทั้งหมดที่ใช้ในหน้าเพิ่ม
			getComboForAddEdit(conn);

			//7.Close connection หลังเลิกใช้งาน
			CCTConnectionUtil.close(conn);
		}

		return result;
	}


	@Override
	public String gotoEdit() throws AuthorizationException, UCPValidateException {
		String result = null;
		CCTConnection conn = null;

		try {
			//1.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());

			//2.ตรวจสอบสิทธิ์ หน้าแก้ไข
			result = manageGotoEdit(conn, model);
			
			//3.ค้นหาข้อมูลผู้ใช้ ตาม id ที่เลือกมาจากหน้าจอ
	        ProjectManager manager = new ProjectManager(conn, getUser(), getLocale());
	        LogUtil.TRAINING.debug("Edit id: " + model.getProject().getId());
	        Project projects = manager.searchById(model.getProject().getId());
	        model.setProject(projects);
	        
			//4.กำหนดให้แสดง user transaction
			showTransaction(model.getProject().getTransaction());
			
		} catch (Exception e) {
			LogUtil.TRAINING.error("", e);
			//5.จัดการ exception กรณีที่มี exception เกิดขึ้นในระบบ
			manageException(conn, PF_CODE.getEditFunction(), this, e, model);
		} finally {
			//6.Load combo ทั้งหมดที่ใช้ในหน้าเพิ่ม
			getComboForAddEdit(conn);
			
			//7.Close connection หลังเลิกใช้งาน
			CCTConnectionUtil.close(conn);
		}

		return result;
	}
	
	@Override
	public String edit() throws AuthorizationException, UCPValidateException {
		String result = null;

		CCTConnection conn = null;

		try {
			//1.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());

			//2.ตรวจสอบสิทธิ์ หน้าแก้ไข
	        result = manageEdit(conn, model,ResultType.BASIC);
	        
	        //3.บันทึกแก้ไขข้อมูล
	        ProjectManager manager = new ProjectManager(conn, getUser(), getLocale());
			manager.edit(model.getProject());
	        
	        //model.setProjects(new Projects());
			manageSearchAjax(conn, model, model.getCriteria(), getPF_CODE().getSearchFunction());
	        result = ReturnType.SEARCH.getResult();
			
		} catch (Exception e) {
			LogUtil.TRAINING.error("", e);
			//4.จัดการ exception กรณีที่มี exception เกิดขึ้นในระบบ
			manageException(conn, PF_CODE.getEditFunction(), this, e, model);
			
			//5.กรณีที่เกิด exception ขึ้นในระบบ จะต้องแสดง message error และคงข้อมูลที่กรอกไว้ในหน้าแก้ไขเช่นเดิม
	        result = ReturnType.ADD_EDIT.getResult();
	        
		} finally {
			getComboForAddEdit(conn);
			CCTConnectionUtil.close(conn);
		}

		return result;
	}
	
	public String cancel() throws AuthorizationException, UCPValidateException {
		String result = null;

		CCTConnection conn = null;

		try {
			//1.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());

			//2.เคลียร์ค่าหน้าเพิ่มทั้งหมด
			manageSearchAjax(conn, model, model.getCriteria(), getPF_CODE().getSearchFunction());
			result = ReturnType.SEARCH.getResult();
			

		} catch (Exception e) {
			LogUtil.TRAINING.error("", e);
			getComboForAddEdit(conn);
			//3.จัดการ exception กรณีที่มี exception เกิดขึ้นในระบบ
			manageException(conn, PF_CODE.getEditFunction(), this, e, model);
		} finally {
			getComboForSearch(conn);
			//4.Close connection หลังเลิกใช้งาน
			CCTConnectionUtil.close(conn);
		}

		return result;
	}

	@Override
	public String gotoView() throws AuthorizationException, UCPValidateException {
		String result = null;
		CCTConnection conn = null;

		try {
			//1.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());

			//2.ตรวจสอบสิทธิ์ 
			result = manageGotoView(conn, model);
			
			//3.ค้นหาข้อมูลผู้ใช้ ตาม id ที่เลือกมาจากหน้าจอ
	        ProjectManager manager = new ProjectManager(conn, getUser(), getLocale());
	        LogUtil.TRAINING.debug("View id: " + model.getProject().getId());
	        Project projects = manager.searchById(model.getProject().getId());
	        model.setProject(projects);
	        
			//4.กำหนดให้แสดง user transaction
			showTransaction(model.getProject().getTransaction());
			
		} catch (Exception e) {
			LogUtil.TRAINING.error("", e);
			//5.จัดการ exception กรณีที่มี exception เกิดขึ้นในระบบ
			manageException(conn, PF_CODE.getViewFunction(), this, e, model);
		} finally {
			
			//6.Close connection หลังเลิกใช้งาน
			CCTConnectionUtil.close(conn);
		}

		return result;
	}
	
	/*
	 * อัพเดทสถานะการใช้งาน
	 */
	@Override
	public String updateActive() throws AuthorizationException, UCPValidateException {
	    String result = null;
	    CCTConnection conn = null;
	 
	    try {
	        //1.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
	        conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());
	         
	        //2.ตรวจสอบสิทธิ์
	        manageUpdateActive(conn, model, ResultType.BASIC);
	        result = ReturnType.SEARCH.getResult();
	        
	        //3.Update active process
	        ProjectManager manager = new ProjectManager(conn, getUser(), getLocale());
	        LogUtil.SEC.debug("Change active id...[" + model.getCriteria().getSelectedIds() + "]");
	        manager.updateActive(model.getCriteria().getSelectedIds(), model.getCriteria().getStatusForUpdate());
	        
	        /*  message ใน manageUpdateActive ยังไม่ได้มีการกำหนดว่าจะใช้อะไร (30001) 
	         *  จึงกำหนด message ขึ้นมาก่อน เมื่อมีการ update active */
	        // FIXME ยกเลิกใช้งาน
//	        if (model.getCriteria().getStatusForUpdate().equals(GlobalVariable.FLAG_ACTIVE)) {
//				setMessage(CommonAction.MessageType.SUCCESS, getText("30068"), ResultType.BASIC);	
//			}else {
//				setMessage(CommonAction.MessageType.SUCCESS, getText("30069"), ResultType.BASIC);	
//			}
	        
	    } catch (Exception e) {
	    	LogUtil.TRAINING.error("", e);
	        //4.จัดการ exception กรณีที่มี exception เกิดขึ้นในระบบ
	        manageException(conn, PF_CODE.getChangeFunction(), this, e, model);
	    } finally {
	    	getComboForSearch(conn);
	    	
	        //5.Close connection หลังเลิกใช้งาน
	        CCTConnectionUtil.close(conn);
	    }
	    return result;      
	}

	@Override
	public String delete() throws AuthorizationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String export() throws AuthorizationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showTransaction(Transaction transaction) {
		getModel().setTransaction(transaction);
		
	}

	@Override
	protected Logger loggerInititial() {
		return LogUtil.TRAINING;
	}

}
