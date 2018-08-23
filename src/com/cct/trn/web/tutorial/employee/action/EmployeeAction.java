package com.cct.trn.web.tutorial.employee.action;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cct.common.CommonAction;
import com.cct.common.CommonModel.PageType;
import com.cct.domain.GlobalType;
import com.cct.domain.Transaction;
import com.cct.exception.AuthorizationException;
import com.cct.exception.DuplicateException;
import com.cct.exception.UCPValidateException;
import com.cct.interfaces.InterfaceAction;
import com.cct.trn.core.config.parameter.domain.DBLookup;
import com.cct.trn.core.security.authorization.domain.PFCode;
import com.cct.trn.core.selectitem.service.SelectItemManager;
import com.cct.trn.core.tutorial.employee.domain.Employee;
import com.cct.trn.core.tutorial.employee.domain.EmployeeModel;
import com.cct.trn.core.tutorial.employee.domain.EmployeeSearch;
import com.cct.trn.core.tutorial.employee.domain.EmployeeSearchCriteria;
import com.cct.trn.core.tutorial.employee.service.EmployeeManager;
import com.opensymphony.xwork2.ModelDriven;

import util.database.CCTConnection;
import util.database.CCTConnectionProvider;
import util.database.CCTConnectionUtil;
import util.log.LogUtil;

public class EmployeeAction extends CommonAction  implements ModelDriven<EmployeeModel>, InterfaceAction{

	private static final long serialVersionUID = 1886407726572516676L;
	private EmployeeModel model = new EmployeeModel();
	
	//Constructor
	public EmployeeAction() {
		try {
			getAuthorization(PFCode.TRN_EMAPLOYEE);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public EmployeeModel getModel() {
		return model;
	}

	@Override
	public String init() throws Exception {
		String result = null;
		CCTConnection conn = null;
		try {
			//1.
			result = manageInitial(conn, model, new EmployeeSearchCriteria(), getPF_CODE().getSearchFunction(), PageType.SEARCH);
			
			//2.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());
			
		} catch (Exception e) {
			LogUtil.SEC.error("", e);
		}finally {
			//3.Load combo ทั้งหมดที่ใช้ในหน้าค้นหา เพื่อเตรียม binding เข้ากับ criteria
	        getComboForSearch(conn);
	        
			//4.Close connection หลังเลิกใช้งาน
			CCTConnectionUtil.close(conn);
		}
		return result;
	}

	@Override
	public void getComboForSearch(CCTConnection conn) {
		try {
			EmployeeManager manager = new EmployeeManager(conn, getUser(), getLocale());
			model.setListPrefix(manager.searchPrefixSelectItem(conn, getLocale()));
			
			model.setListSex(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.SEX.getValue()));
			model.setListWorkStatus(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.WORK_STATUS.getValue()));
		} catch (Exception e) {
			LogUtil.SEC.error("", e);
		}
	}

	@Override
	public void getComboForAddEdit(CCTConnection conn) {
		try {
			EmployeeManager manager = new EmployeeManager(conn, getUser(), getLocale());
			model.setListPrefix(manager.searchPrefixSelectItem(conn, getLocale()));
			model.setListSex(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.SEX.getValue()));
			model.setListWorkStatus(SelectItemManager.getMapGlobalData().get(getLocale()).get(GlobalType.WORK_STATUS.getValue()));
		} catch (Exception e) {
			LogUtil.SEC.error("", e);
		}
	}

	@Override
	public String search() throws Exception {
		String result = null;
		CCTConnection conn = null;
		try {
			//1.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());
			
			//2.
			result = manageSearchAjax(conn, model, model.getCriteria(), getPF_CODE().getSearchFunction());
			
			//3.การค้นหา(ของแต่ละระบบ) ตัวอย่าง.ค้นหาข้อมูลผู้ใช้
			EmployeeManager manager = new EmployeeManager(conn, getUser(), getLocale());
			List<EmployeeSearch> lstResult = manager.search(getModel().getCriteria());
			
			//4.จัดการผลลัพธ์และข้อความ ถ้าไม่พบข้อมูล
		    manageSearchResult(getModel(), lstResult);
		    
		} catch (Exception e) {
			//5.จัดการ exception กรณีที่มี exception เกิดขึ้นในระบบ
	        manageException(conn, getPF_CODE().getSearchFunction(), this, e, getModel());
	        
	        //6.Load combo ทั้งหมดที่ใช้ในหน้าแก้ไข
	        getComboForAddEdit(conn);
		} finally {
			//7.Load combo ทั้งหมดที่ใช้ในหน้าค้นหา เพื่อเตรียม binding เข้ากับ criteria
			getComboForSearch(conn);
			
			//8.Close connection หลังเลิกใช้งาน
			CCTConnectionUtil.close(conn);
		}
		return result;
	}
	
	public String searchDialog() throws Exception {
		String result = null;
		CCTConnection conn = null;
		try {
			//1.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());
			
			//2.
			result = ReturnType.SEARCHDIALOG.getResult(); /*manageSearch(conn, model, model.getCriteria(), getPF_CODE().getSearchFunction());*/
			
			//3.การค้นหา(ของแต่ละระบบ) ตัวอย่าง.ค้นหาข้อมูลผู้ใช้
			EmployeeManager manager = new EmployeeManager(conn, getUser(), getLocale());
			List<EmployeeSearch> lstResult = manager.search2(getModel().getCriteria());
			model.setListResult(lstResult);
		    
		} catch (Exception e) {
			//5.จัดการ exception กรณีที่มี exception เกิดขึ้นในระบบ
	        manageException(conn, getPF_CODE().getSearchFunction(), this, e, getModel());
	        
	        //6.Load combo ทั้งหมดที่ใช้ในหน้าแก้ไข
	        getComboForAddEdit(conn);
		} finally {
			//7.Load combo ทั้งหมดที่ใช้ในหน้าค้นหา เพื่อเตรียม binding เข้ากับ criteria
			getComboForSearch(conn);
			
			//8.Close connection หลังเลิกใช้งาน
			CCTConnectionUtil.close(conn);
		}
		return result;
	}

	/**
	 * click ปุ่มเพิ่ม return addEdit เพื่อเข้าหน้าเพิ่ม
	 * ถ้ามี error จะ return addEdit เพื่อแสดง error ที่หน้าเพิ่ม
	 * ยกเว้นตรวจสอบสิทธิ์ไม่ผ่าน ให้กลับไปที่หน้า login
	 */
	@Override
	public String gotoAdd() throws Exception {
		String result = null;
	    CCTConnection conn = null;
	    try {
	        //1.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
	        conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());
	 
	        //2.ตรวจสอบสิทธิ์ หน้าเพิ่ม
	        result = manageGotoAdd(conn, model);
	        
	        // Default Value
	        EmployeeManager manager = new EmployeeManager(conn, getUser(), getLocale());
	        model.setEmployee(manager.defaultValue());
	 
	    } catch (Exception e) {
	        //3.จัดการ exception กรณีที่มี exception เกิดขึ้นในระบบ
	        manageException(conn, PF_CODE.getAddFunction(), this, e, model);
	    } finally {
	        //4.Load combo ทั้งหมดที่ใช้ในหน้าเพิ่ม
	        getComboForAddEdit(conn);
	         
	        //5.Close connection หลังเลิกใช้งาน
	        CCTConnectionUtil.close(conn);
	    }
	    return result;	//6.return "addEdit"
	}

	/**
	 * click ปุ่มบันทึกเพิ่ม จะบันทึกข้อมูลเพิ่ม, เคลียร์ค่าออก และ return addEdit เพื่อเข้าหน้าเพิ่มใหม่
	 * ถ้ามี error จะ return addEdit เพื่อแสดง error ที่หน้าเพิ่ม
	 * ยกเว้นตรวจสอบสิทธิ์ไม่ผ่าน ให้กลับไปที่หน้า login
	 */
	@Override
	public String add() throws Exception {
		String result = null;
	    CCTConnection conn = null;
	    try {
	        //1.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
	        conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());
	        
	        //2.ตรวจสอบสิทธิ์ หน้าเพิ่ม
	        result = manageAdd(conn, model);
	        
	        EmployeeManager manager = new EmployeeManager(conn, getUser(), getLocale());
	        
	        //3.บันทึกเพิ่มข้อมูลผู้ใช้
	        manager.add(model.getEmployee());
	        
	        //4.เคลียร์ค่าหน้าเพิ่มทั้งหมด
	        model.setEmployee(new Employee());
	        
	    } catch (Exception e) {
	        //5.จัดการ exception กรณีที่มี exception เกิดขึ้นในระบบ
	        manageException(conn, PF_CODE.getAddFunction(), this, e, model);
	        setMessage(MessageType.ERROR, getText("30007"), ResultType.BASIC);
	    } finally {
	        //6.Load combo ทั้งหมดที่ใช้ในหน้าเพิ่ม
	        getComboForAddEdit(conn);
	        
	        //7.Close connection หลังเลิกใช้งาน
	        CCTConnectionUtil.close(conn);
	    }
	    return result;	//8.return "addEdit"
	}

	/**
	 * click ปุ่มแก้ไข return addEdit เพื่อเข้าหน้าแก้ไข
	 * ถ้ามี error จะ return addEdit เพื่อแสดง error ที่หน้าแก้ไข
	 * ยกเว้นตรวจสอบสิทธิ์ไม่ผ่าน ให้กลับไปที่หน้า login
	 */
	@Override
	public String gotoEdit() throws Exception {
		String result = null;
	    CCTConnection conn = null;
	 
	    try {
	        //1.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
	        conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());
	 
	        //2.ตรวจสอบสิทธิ์ หน้าแก้ไข
	        result = manageGotoEdit(conn, model);
	 
	        //3.ค้นหาข้อมูลผู้ใช้ ตาม id ที่เลือกมาจากหน้าจอ
	        EmployeeManager manager = new EmployeeManager(conn, getUser(), getLocale());
	        LogUtil.SEC.debug("Edit id: " + model.getEmployee().getId());
	        Employee employee = manager.searchById(model.getEmployee().getId());
	        model.setEmployee(employee);
	 
	        //4.กำหนดให้แสดง user transaction
	        showTransaction(model.getEmployee().getTransaction());
	 
	    } catch (Exception e) {
	        //5.จัดการ exception กรณีที่มี exception เกิดขึ้นในระบบ
	        manageException(conn, PF_CODE.getEditFunction(), this, e, model);
	    } finally {
	        //6.Load combo ทั้งหมดที่ใช้ในหน้าแก้ไข
	        getComboForAddEdit(conn);
	         
	        //7.Close connection หลังเลิกใช้งาน
	        CCTConnectionUtil.close(conn);
	    }
	    return result;	//8.return "addEdit"
	}
	
	/**
	 * click ปุ่มบันทึกแก้ไข จะบันทึกข้อมูลแก้ไข  และ return searchDo เพื่อกลับไปค้นหาและแสดงผลการค้นหาใหม่ที่หน้าค้นหา
	 * ถ้ามี error จะ return addEdit เพื่อแสดง error ที่หน้าแก้ไข
	 * ยกเว้นตรวจสอบสิทธิ์ไม่ผ่าน ให้กลับไปที่หน้า login
	 */
	@Override
	public String edit() throws Exception {
		String result = ReturnType.SEARCH.getResult();
	    CCTConnection conn = null;
	 
	    try {
	        //1.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
	        conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());
	 
	        //2.ตรวจสอบสิทธิ์ หน้าแก้ไข
	        manageEdit(conn, model);
	 
	        //3.บันทึกแก้ไขข้อมูล
	        EmployeeManager manager = new EmployeeManager(conn, getUser(), getLocale());
	        manager.edit(model.getEmployee());
	 
	    } catch (DuplicateException e) {
	    	//4.จัดการ exception กรณีที่มี exception เกิดขึ้นในระบบ
	        manageException(conn, PF_CODE.getEditFunction(), this, e, model);
	        
	        //5.กรณีที่เกิด exception ขึ้นในระบบ จะต้องแสดง message error และคงข้อมูลที่กรอกไว้ในหน้าแก้ไขเช่นเดิม
	        result = ReturnType.ADD_EDIT.getResult();
	        getComboForAddEdit(conn);
	    } catch (Exception e) {
	    	//4.จัดการ exception กรณีที่มี exception เกิดขึ้นในระบบ
	    	 setMessage(MessageType.ERROR, getText("30008"), ResultType.BASIC);
	    	 
	    	//5.กรณีที่เกิด exception ขึ้นในระบบ จะต้องแสดง message error และคงข้อมูลที่กรอกไว้ในหน้าแก้ไขเช่นเดิม
	        result = ReturnType.ADD_EDIT.getResult();
	        getComboForAddEdit(conn);
	    } finally {
	        //6.Close connection หลังเลิกใช้งาน
	        CCTConnectionUtil.close(conn);
	    }
	    return result;  //7.return "searchDo"
	}

	/**
	* click ปุ่มแสดง return addEdit เพื่อเข้าหน้าแสดง ถ้ามี error จะ return addEdit 
	* เพื่อแสดง error ที่หน้าแสดง ยกเว้นตรวจสอบสิทธิ์ไม่ผ่าน ให้กลับไปที่หน้า login
	*/
	@Override
	public String gotoView() throws Exception {
		String result = null;
	    CCTConnection conn = null;
	 
	    try {
	        //1.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
	        conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());
	 
	        //2.ตรวจสอบสิทธิ์ หน้าแสดง
	        result = manageGotoView(conn, model);
	        
	        //3.ค้นหาข้อมูลผู้ใช้โดยใช้ ตาม id ที่เลือกมาจากหน้าจอ
	        EmployeeManager manager = new EmployeeManager(conn, getUser(), getLocale());
	        LogUtil.SEC.debug("Edit id: " + model.getEmployee().getId());
	        Employee userData = manager.searchById(model.getEmployee().getId());
	        model.setEmployee(userData);
	 
	        //4.กำหนดให้แสดง user transaction
	        showTransaction(model.getEmployee().getTransaction());
	 
	    } catch (Exception e) {
	        //5.จัดการ exception กรณีที่มี exception เกิดขึ้นในระบบ
	        manageException(conn, PF_CODE.getViewFunction(), this, e, model);
	    } finally {
	        //6.Load combo ทั้งหมดที่ใช้ในหน้าแสดง
	        getComboForAddEdit(conn);
	         
	        //7.Close connection หลังเลิกใช้งาน
	        CCTConnectionUtil.close(conn);
	    }
	    return result;	//8.return "addEdit"
	}

	@Override
	public String updateActive() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * return searchDo เพื่อกลับไปค้นหาและแสดงผลการค้นหาใหม่ที่หน้าค้นหา
	 * ถ้ามี error จะ return searchDo เพื่อแสดง error ที่ด้วยผลการค้นหาเดิม
	 * ยกเว้นตรวจสอบสิทธิ์ไม่ผ่าน ให้กลับไปที่หน้า login
	 */
	@Override
	public String delete() throws Exception {
		String result = ReturnType.SEARCH.getResult();
	    CCTConnection conn = null;
		try {
			//1.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
	        conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());
	        
	        //2.ตรวจสอบสิทธิ์
	        manageDelete(conn, model);
	        
	        //3.Delete process
	        EmployeeManager manager = new EmployeeManager(conn, getUser(), getLocale());
	        manager.delete(model.getCriteria().getSelectedIds());
	        
	        setMessage(MessageType.SUCCESS, getText("30005"), ResultType.BASIC);
	        
		} catch (Exception e) {
			//4.จัดการ exception กรณีที่มี exception เกิดขึ้นในระบบ
	        manageException(conn, PF_CODE.getDeleteFunction(), this, e, model);
	        setMessage(MessageType.ERROR, getText("30009"), ResultType.BASIC); // ajax data table
		}finally {
			//5.Close connection หลังเลิกใช้งาน
	        CCTConnectionUtil.close(conn);
	    }
		return result;
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
	
	public String cancel() throws AuthorizationException, UCPValidateException {
		String result = null;
		CCTConnection conn = null;
		try {
			//1.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());

			//2.
			manageSearchAjax(conn, model, model.getCriteria(), getPF_CODE().getSearchFunction());
			result = ReturnType.SEARCH.getResult();

		} catch (Exception e) {
			LogUtil.TRAINING.error("", e);
			getComboForAddEdit(conn);
			//3.
			manageException(conn, PF_CODE.getEditFunction(), this, e, model);
		} finally {
			getComboForSearch(conn);
			//4.
			CCTConnectionUtil.close(conn);
		}

		return result;
	}
	
	public String exportReport() throws Exception{
		
		String result = null;
		CCTConnection conn = null;
		try {
			//1.สร้าง connection โดยจะต้องระบุ lookup ที่ใช้ด้วย
			conn = new CCTConnectionProvider().getConnection(conn, DBLookup.MYSQL_TRAINING.getLookup());
			
			//2.ตรวจสอบสิทธิ์การ export
	        result = manageExport(conn, model);
	        
	        //3. ค้นหาข้อมูล
	        EmployeeManager manager = new EmployeeManager(conn, getUser(), getLocale());
			XSSFWorkbook workbook = manager.exportExcelEmployee(conn, model.getCriteria(), getLocale());
			
			//4. ถ้าไม่เป็นค่าว่าง ออกรายงาน
			if(workbook != null){
				try {
					exportExcelFile(workbook, "RLREP.xlsx");
				} catch (Exception e) {
					LogUtil.TRAINING.error(e);
				}
			}else{
				result = ReturnType.SEARCH.getResult();
			}
		}catch (Exception e) {
			//5. จัดการ exception กรณีที่มี exception เกิดขึ้นในระบบ
	        manageException(conn, PF_CODE.getChangeFunction(), this, e, model);
		}finally{
			//6. Close connection หลังเลิกใช้งาน
	        CCTConnectionUtil.close(conn);
		}
		return result;
		
	}
	
	public String readReport() throws Exception{
		try {
			
		}catch (Exception e) {
			
		}
		
		return null;
		
	}
}
