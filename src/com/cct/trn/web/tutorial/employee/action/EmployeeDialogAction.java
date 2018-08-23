package com.cct.trn.web.tutorial.employee.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.cct.common.CommonDialogAction;
import com.cct.common.CommonDomain;
import com.cct.domain.SearchCriteria;
import com.cct.trn.core.config.parameter.domain.DBLookup;
import com.cct.trn.core.security.authorization.domain.PFCode;
import com.cct.trn.core.tutorial.employee.domain.EmployeeModel;
import com.cct.trn.core.tutorial.employee.domain.EmployeeSearch;
import com.cct.trn.core.tutorial.employee.domain.EmployeeSearchCriteria;
import com.cct.trn.core.tutorial.employee.service.EmployeeManager;

import util.database.CCTConnection;
import util.database.CCTConnectionProvider;
import util.database.CCTConnectionUtil;
import util.log.LogUtil;

public class EmployeeDialogAction extends CommonDialogAction {
	
	private static final long serialVersionUID = 2502870609009877454L;
	private EmployeeModel model = new EmployeeModel();
	List<EmployeeSearch> popupResult = new ArrayList<EmployeeSearch>();
	
//	Function --------------------------------------------------------------------------
	public EmployeeDialogAction() {
		try {
			/*getAuthorization(PFCode.TRN_EMAPLOYEE);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<CommonDomain> search(CCTConnection conn) throws Exception {
		System.out.println("List<CommonDomain> search");
		return null;
		/*return new EmployeeManager(conn, getUser(), getLocale()).searchPopup((EmployeeSearchCriteria) getModel().getCriteriaPopup()) ;*/
	}
	
	
	public String searchPopup() throws Exception {
		
		String result = null;
		CCTConnection conn = null;
		try {
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());
			
			result = "search";
			
			System.out.println("getCriteriaPopup --> " + (EmployeeSearchCriteria) getModel().getCriteriaPopup() );
			
			//3.การค้นหา(ของแต่ละระบบ) ตัวอย่าง.ค้นหาข้อมูลผู้ใช้
			EmployeeManager manager = new EmployeeManager(conn, getUser(), getLocale());
			List<EmployeeSearch> listResult = manager.searchPopup((EmployeeSearchCriteria) getModel().getCriteriaPopup());
			setPopupResult(listResult);
		    
		} catch (Exception e) {
			
		} finally {
			CCTConnectionUtil.close(conn);
		}
		return result;
	}
	
	public List<CommonDomain> searchById(CCTConnection conn) throws Exception {
		return null;
		
	}
	
	protected Logger loggerInititial() {
        // กำหนดค่า Log ใช้งานเริ่มต้น
        return LogUtil.SEC;
    }
	
	public SearchCriteria initSearchCriteria() {
        return new EmployeeSearchCriteria();
    }
	
//	Getter and Setter -----------------------------------------------------------------
	@Override
	public EmployeeModel getModel() {
		return model;
	}
	public void setModel(EmployeeModel model) {
		this.model = model;
	}

	public List<EmployeeSearch> getPopupResult() {
		return popupResult;
	}


	public void setPopupResult(List<EmployeeSearch> popupResult) {
		this.popupResult = popupResult;
	}
	
	
	

}
