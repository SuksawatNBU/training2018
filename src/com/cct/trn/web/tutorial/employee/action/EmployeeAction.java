package com.cct.trn.web.tutorial.employee.action;

import java.util.List;

import com.cct.domain.Transaction;
import com.cct.interfaces.InterfaceAction;
import com.cct.trn.core.tutorial.employee.domain.EmployeeModel;
import com.cct.trn.core.tutorial.employee.domain.EmployeeSearch;
import com.cct.trn.core.tutorial.employee.service.EmployeeManager;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import util.database.CCTConnection;
import util.database.CCTConnectionProvider;
import util.database.CCTConnectionUtil;

public class EmployeeAction extends ActionSupport implements ModelDriven<EmployeeModel>, InterfaceAction{

	private static final long serialVersionUID = 1886407726572516676L;
	private EmployeeModel model = new EmployeeModel();

	@Override
	public EmployeeModel getModel() {
		return model;
	}

	@Override
	public String init() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getComboForSearch(CCTConnection conn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getComboForAddEdit(CCTConnection conn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String search() throws Exception {
		System.out.println("Action");
		CCTConnection conn = null;
		try {
			// [1] สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
//			conn = new CCTConnectionProvider().getConnection(conn, getDbLookup());

			// [2] ตรวจสอบสิทธิ์�?ารใช้งาน �?ละจัด�?ารเงือนไข�?ารค้นหา
//			result = manageSearchAjax(conn, getModel(), getModel().getCriteria(), getPF_CODE().getSearchFunction());

			// [3] ค้นหาข้อมูล
			EmployeeManager manager = new EmployeeManager(conn, null, getLocale());
			List<EmployeeSearch> listResult = manager.search(model.getCriteria());

			// [4] จัด�?ารผลลัพธ์�?ละข้อความถ้าไม่พบข้อมูล
//			manageSearchResult(conn, listResult);

		} catch (Exception e) {
			
		} finally {
			CCTConnectionUtil.close(conn);
		}
		return SUCCESS;
	}

	@Override
	public String gotoAdd() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String add() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String edit() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String gotoEdit() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String gotoView() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateActive() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String export() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		
	}
}
