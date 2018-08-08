package com.cct.trn.core.tutorial.employee.service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cct.abstracts.AbstractService;
import com.cct.common.CommonSelectItem;
import com.cct.common.CommonUser;
import com.cct.domain.Transaction;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;
import com.cct.trn.core.config.parameter.domain.SQLPath;
import com.cct.trn.core.tutorial.employee.domain.Employee;
import com.cct.trn.core.tutorial.employee.domain.EmployeeSearch;
import com.cct.trn.core.tutorial.employee.domain.EmployeeSearchCriteria;

import util.calendar.CalendarUtil;
import util.database.CCTConnection;
import util.log.LogUtil;

public class EmployeeService extends AbstractService{
	
	private EmployeeDao dao;
	
	public EmployeeService(CCTConnection conn, CommonUser user, Locale locale) {
		super(conn, user, locale);
		this.dao = new EmployeeDao();
		this.dao.setSqlPath(SQLPath.EMPLOYEE_SQL);
	}
	
	// นับจำนวนข้อมูล
	protected long countData(EmployeeSearchCriteria criteria) throws Exception {
		long totalResult = 0;
	    try {
	        totalResult = dao.countData(conn, criteria, user, locale);
	    } catch (Exception e) {
	        LogUtil.SEC.error("", e);
	        throw e;
	    }
		return totalResult;
	}
	
	//Service ตรวจสอบบันทึกข้อมูลผู้ใช้ซ้ำ
	protected boolean checkDup(CCTConnection conn, Employee obj, CommonUser user, Locale locale) throws Exception {
		boolean isDup = false;
		try {
			isDup = dao.checkDup(conn, obj, user, locale);
			LogUtil.SEC.debug("isDup: " + isDup);
		}catch (Exception e) {
			LogUtil.SEC.error("", e);
		}
		return isDup;
    }
	
	//Service เพิ่มข้อมูลผู้ใช้
	protected int add(CCTConnection conn, Employee obj, CommonUser user, Locale locale) throws Exception {
		try {
			return dao.add(conn, obj, user, locale);
		}catch (Exception e) {
			LogUtil.SEC.error("", e);
			throw e;
		}
	}
	
	protected int edit(CCTConnection conn, Employee obj, CommonUser user, Locale locale) throws Exception {
		try {
	        return dao.edit(conn, obj, user, locale);
		}catch (Exception e) {
			LogUtil.SEC.error("", e);
			throw e;
		}
	}

	protected int delete(CCTConnection conn, String ids, CommonUser user, Locale locale) throws Exception {
		int id = 0;
		try {
			id = dao.delete(conn, ids, user, locale);
		}catch (Exception e) {
			LogUtil.SEC.error("", e);
			throw e;
		}
		return id;
	}
	
	// สืบค้นข้อมูล
	protected List<EmployeeSearch> search(CCTConnection conn, EmployeeSearchCriteria criteria, CommonUser user, Locale locale) throws Exception {
		List<EmployeeSearch> listResult = new ArrayList<EmployeeSearch>();
	    try {
	    	//2.ค้นหาข้อมูล
	        listResult = dao.search(conn, criteria, user, locale);
	        //3.แปลงข้อมูล
	        listResult = convertValue(listResult);
	    } catch (Exception e) {
	        LogUtil.SEC.error("", e);
	        throw e;
	    }
		return listResult;
	}
	
	//Service ค้นหาข้อมูลผู้ใช้ตาม id
	protected Employee searchById(CCTConnection conn, String id, CommonUser user, Locale locale) throws Exception {
		Employee result = new Employee();
		try {
			//1.ค้นหาข้อมูล
		    result = dao.searchById(conn, id, user, locale);	
		    //2.แปลงรูปแบบวันที่
		    result.setStartWorkDate(convertDate(result.getStartWorkDate(), "outSearchById"));
		    if(result.getWorkStatus().equals("W")){
		    	result.setEndWorkDate(convertDate(result.getEndWorkDate(), "outSearchById"));
		    }
		} catch (Exception e) {
			LogUtil.SEC.error("", e);
		    throw e;
		}
		return result;
	}
	
	protected List<CommonSelectItem> searchPrefixSelectItem(CCTConnection conn, Locale locale){
		List<CommonSelectItem> listResult = new ArrayList<CommonSelectItem>();
		try {
			listResult = dao.searchPrefixSelectItem(conn,locale);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listResult;
	}
	
	protected List<EmployeeSearch> searchExportEmployee(CCTConnection conn, EmployeeSearchCriteria criteria, Locale locale) throws Exception{
		
		List<EmployeeSearch> listResult = new ArrayList<EmployeeSearch>();
		try {
			//1. ค้นหาข้อมูลจากฐานข้อมูล
			listResult = dao.searchExportEmployee(conn, criteria, locale);
			//2. ตรวจสอบค่าว่าง
			if (listResult.isEmpty()) {
				return null;
			}
			//3. แปลงข้อมูล
			listResult = convertValue(listResult);
		} catch (Exception e) {
			LogUtil.SEC.error(e);
			throw e;
		}
		return listResult;
	}
	
	protected XSSFWorkbook exportExcelEmployee(List<EmployeeSearch> listResult, EmployeeSearchCriteria criteria){
		//1. Create Excel
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet spreadsheet = workbook.createSheet("รายงานการใช้งานห้องประชุม");
		XSSFRow row;
		XSSFCell cell;
		
		//2. กำหนดรายละเอียดหน้ากระดาษ
		spreadsheet.getPrintSetup().setPaperSize(XSSFPrintSetup.LETTER_PAPERSIZE);
		spreadsheet.getPrintSetup().setLandscape(true);
		spreadsheet.setMargin(Sheet.TopMargin, 0.75);
		spreadsheet.setMargin(Sheet.BottomMargin, 0.75);
		spreadsheet.setMargin(Sheet.RightMargin, 0.7);
		spreadsheet.setMargin(Sheet.LeftMargin, 0.7);
		spreadsheet.setMargin(Sheet.HeaderMargin, 0.3);
		spreadsheet.setMargin(Sheet.FooterMargin, 0.3);
		
		//3. กำหนดความกว้าง cell **วิธีคำนวณ = 276.065 *(หน่วยความยาวใน excel) เช่น 280.1*10 = 2801
		int paramIndex = 0;
		spreadsheet.setColumnWidth(paramIndex++, 2000); // ลำดับ
		spreadsheet.setColumnWidth(paramIndex++, 8000); // ชื่อ-สกุล
		spreadsheet.setColumnWidth(paramIndex++, 2000); // เพศ
		spreadsheet.setColumnWidth(paramIndex++, 4000); // วันที่บันทึกข้อมูล
		spreadsheet.setColumnWidth(paramIndex++, 4000); // ผู้บันทึก
		spreadsheet.setColumnWidth(paramIndex++, 4000); // วันที่แก้ไขข้อมูล
		spreadsheet.setColumnWidth(paramIndex++, 4000); // ผู้แก้ไข
		spreadsheet.setColumnWidth(paramIndex++, 5000); // สถานะ
		spreadsheet.setColumnWidth(paramIndex++, 4000); // วันที่เริ่มงาน
		spreadsheet.setColumnWidth(paramIndex++, 4000); // วันสุดท้ายที่ทำงาน
		spreadsheet.setColumnWidth(paramIndex++, 4000); // หมายเหตุ
		
		//4. กำหนด Font
		XSSFFont font_S16    = createFont(workbook, 16, false, false, 0);
		XSSFFont font_S16_B  = createFont(workbook, 16, true, false, 0);
		XSSFFont font_S14 = createFont(workbook, 14, false, false, 0);
		XSSFFont font_S14_B = createFont(workbook, 14, true, false, 0);

		//5. กำหนด Style
		short none = 0;
		// Style หัวข้อ
		XSSFCellStyle styleTitle = createStyleTitle(workbook, XSSFCellStyle.ALIGN_CENTER, font_S16_B);
		// Style criteria
		XSSFCellStyle styleCriteria = createStyleTitle(workbook, XSSFCellStyle.ALIGN_LEFT, font_S14);
		XSSFCellStyle styleCriteria_B = createStyleTitle(workbook, XSSFCellStyle.ALIGN_RIGHT, font_S14_B);
		// Style วันที่พิมพ์
		XSSFCellStyle stylePrintDate = createStyleTitle(workbook, XSSFCellStyle.ALIGN_LEFT, font_S14);
		// Style หัวตาราง
		XSSFCellStyle styleHead = createStyleBorder(workbook, XSSFCellStyle.BORDER_THIN, XSSFCellStyle.BORDER_DOUBLE, XSSFCellStyle.BORDER_THIN, XSSFCellStyle.BORDER_THIN, font_S14_B);
		styleHead.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		
		//6. กำหนดข้อมูลใน Header and Footer
		Header header = (Header) spreadsheet.getHeader();
		header.setCenter("");
		Footer footer = (Footer) spreadsheet.getFooter();
		footer.setLeft("REP08260001");
				
		//7.กำหนด index ของข้อมูล **หากข้อมูลถูกเปลี่ยนตำแหน่ง จะเปลี่ยนที่นี่แทน
		int index = 0;
		
		// 8. แสดงข้อมูลในเซล
		// 8.1 หัวเรื่อง
		row = spreadsheet.createRow(index);
		mergeCell(spreadsheet, index, 0, 0, 11);
		createCell(row, styleTitle, 0, "รายงานการใช้งานห้องประชุม");
		
		// 8.2 Criteria
		row = spreadsheet.createRow(++index);
		mergeCell(spreadsheet, index, 0, 2, 4);
		mergeCell(spreadsheet, index, 0, 6, 8);
		createCell(row, styleCriteria_B, 1, "คำนำหน้าชื่อ :");
		createCell(row, styleCriteria, 2, criteria.getPrefixId());
		createCell(row, styleCriteria_B, 5, "ชื่อ-สกุล :");
		createCell(row, styleCriteria, 6, criteria.getFullname());
		
		row = spreadsheet.createRow(++index);
		mergeCell(spreadsheet, index, 0, 2, 4);
		mergeCell(spreadsheet, index, 0, 6, 8);
		createCell(row, styleCriteria_B, 1, "ชื่อเล่น :");
		createCell(row, styleCriteria, 2, criteria.getNickname());
		createCell(row, styleCriteria_B, 5, "เพศ :");
		createCell(row, styleCriteria, 6, criteria.getSex());
		
		row = spreadsheet.createRow(++index);
		mergeCell(spreadsheet, index, 0, 2, 4);
		mergeCell(spreadsheet, index, 0, 6, 8);
		createCell(row, styleCriteria_B, 1, "สังกัด :");
		createCell(row, styleCriteria, 2, criteria.getDepartmentDesc());
		createCell(row, styleCriteria_B, 5, "แผนก :");
		createCell(row, styleCriteria, 6, criteria.getPositionDesc());
		
		row = spreadsheet.createRow(++index);
		mergeCell(spreadsheet, index, 0, 2, 4);
		mergeCell(spreadsheet, index, 0, 6, 8);
		createCell(row, styleCriteria_B, 1, "ช่วงวันที่เริ่มงาน ตั้งแต่ :");
		createCell(row, styleCriteria, 2, criteria.getStartWorkDate());
		createCell(row, styleCriteria_B, 5, "ถึง :");
		createCell(row, styleCriteria, 6, criteria.getEndWorkDate());
		
		row = spreadsheet.createRow(++index);
		mergeCell(spreadsheet, index, 0, 2, 4);
		mergeCell(spreadsheet, index, 0, 6, 8);
		createCell(row, styleCriteria_B, 1, "สถานะการทำงาน :");
		createCell(row, styleCriteria, 2, criteria.getWorkStatus());
		
		
		
		return workbook;
	}
	
	
	/*
	 * สำหรับการแปลงข้อมูล เพื่ออที่จะนำไปแสดงผล ---------------------------------------------------------------
	 */
	
	public List<EmployeeSearch> convertValue(List<EmployeeSearch> list) throws Exception{
		List<EmployeeSearch> listResult = new ArrayList<EmployeeSearch>();
		try{
			for(EmployeeSearch employee : list){
	        	EmployeeSearch emp = new EmployeeSearch();
	        	Transaction transaction = new Transaction();
	        	emp.setId(employee.getId());
	        	emp.setFullname(employee.getFullname());
	        	emp.setSex(convertSex(employee.getSex()));	//แปลง
	        	emp.setDepartmentDesc(convertDepartment(employee.getDepartmentDesc()));	//แปลง
	        	emp.setPositionDesc(convertPosition(employee.getPositionDesc()));	//แปลง
	        	emp.setStartWorkDate(convertDate(employee.getStartWorkDate(), "outSearch"));	//แปลง
	        	emp.setEndWorkDate(convertDate(employee.getEndWorkDate(), "outSearch"));	//แปลง
	        	emp.setWorkStatus(convertWorkStatus(employee.getWorkStatus()));		//แปลง
	        	transaction.setCreateUser(employee.getTransaction().getCreateUser());
	        	transaction.setCreateDate(convertDate(employee.getTransaction().getCreateDate(), "outSearch"));	//แปลง
	        	transaction.setUpdateUser(employee.getTransaction().getUpdateUser());
	        	transaction.setUpdateDate(convertDate(employee.getTransaction().getUpdateDate(), "outSearch"));	//แปลง
	        	transaction.setCreateRemark(employee.getTransaction().getCreateRemark());
	        	emp.setTransaction(transaction);
	        	listResult.add(emp);
	        }
		}catch (Exception e) {
			LogUtil.SEC.error("", e);
	        throw e;
		}
		return listResult;
	}
	
	public String convertSex(String sex){
		if(sex.equals("M")) return "ชาย"; 
		else if(sex.equals("F")) return "หญิง";
		else return null;
	}
	
	public String convertDate(String date, String func) throws Exception{
		String parseDate = "";

		// ฟังก์ชันค้นหา
		if(func == "outSearch"){
			if(date.equals(null) || date.equals("")) return null;
			try {
				Calendar calendar = CalendarUtil.getCalendarFromDateString(date, ParameterConfig.getParameter().getDateFormat().getForDisplay(), ParameterConfig.getParameter().getApplication().getDatabaseLocale());
				parseDate = CalendarUtil.getDateStringFromCalendar(calendar, "DD/MM/YYYY");
			} catch (Exception e) {
				throw e;
			}
		}
		//ฟังชันค้นหาข้อมูลตาม ไอดีผู้ใช้
		if(func == "outSearchById"){
			if(date.equals(null) || date.equals("")) return null;
			try {
				Calendar calendar = CalendarUtil.getCalendarFromDateString(date, ParameterConfig.getParameter().getDateFormat().getForDisplay(), ParameterConfig.getParameter().getApplication().getDatabaseLocale());
				parseDate = CalendarUtil.getDateStringFromCalendar(calendar, "DD/MM/YYYY");
			} catch (Exception e) {
				throw e;
			}
		}
		//วันที่ปัจจุบัน
		if(func == "defaultValue"){
			try {
				Format outputEN = new SimpleDateFormat("dd/MM/yyyy", new Locale("en", "EN"));
				Date conDate = new Date();
				parseDate = outputEN.format(conDate);
			} catch (Exception e) {
				throw e;
			}
		}
		return parseDate;
	}
	
	public String convertDepartment(String department){
		if(department.equals("COMMAND CONTROL")) return "คอมมาน คอนโทรล เทคโนโลยี";
		else if(department.equals("SOMAPA IT")) return "โสมาภา อินฟอร์เมชั่น เทคโนโลยี";
		else return null;
	}
	
	public String convertPosition(String position){
		if(position.equals("System Analysis")) return "นักวิเคราะห์ระบบ";
		else if(position.equals("System Design")) return "ซอฟต์แวร์ดีไซน์";
		else if(position.equals("Programer")) return "โปรแกรมเมอร์";
		else if(position.equals("Tester")) return "นักทดสอบระบบ";
		else if(position.equals("Business analysis")) return "นักวิเคราะห์ธุรกิจ";
		else if(position.equals("Call Center")) return "บริการข้อมูลลูกค้า";
		else if(position.equals("Technical Support")) return "นักบริการด้านเทคนิค";
		else return null;
	}
	
	public String convertWorkStatus(String status){
		if(status.equals("T")) return "พนักงานทดลองงาน";
		else if(status.equals("C")) return "พนักงานปัจจุบัน";
		else if(status.equals("R")) return "อดีตพนักงาน";
		else return null;
	}
	
	private void createCell(XSSFRow row, XSSFCellStyle cellStyle, int columnIndex, String cellValue) {
		XSSFCell cell = row.createCell(columnIndex);
		cell.setCellValue(cellValue);
		cell.setCellStyle(cellStyle);
	}
	
	private void createCellRichText(XSSFRow row, XSSFCellStyle cellStyle, int columnIndex, XSSFRichTextString cellValue) {
		XSSFCell cell = row.createCell(columnIndex);
		cell.setCellValue(cellValue);
		cell.setCellStyle(cellStyle);
	}
	
	private XSSFFont createFont(XSSFWorkbook workbook, int fontSize, boolean bold, boolean italic,int underline ) {
		XSSFFont font = workbook.createFont(); 
		font.setFontHeightInPoints((short) fontSize);
		font.setFontName("TH SarabunPSK");
		font.setBold(bold);
		font.setItalic(italic);
		font.setUnderline((byte) underline);
		return font;
	}
	
	private XSSFCellStyle createStyleTitle(XSSFWorkbook workbook, short alignment, XSSFFont font){
		XSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(alignment);
		style.setFont(font);
		return style;
	}
	
	private XSSFCellStyle createStyleBorder(XSSFWorkbook workbook, short borderTop, short borderBottom, short borderLeft, short borderRight, XSSFFont font){
		XSSFCellStyle style = workbook.createCellStyle();
		style.setBorderTop((short)borderTop);
		style.setBorderBottom((short)borderBottom);
		style.setBorderLeft((short)borderLeft);
		style.setBorderRight((short)borderRight);
		style.setFont(font);
		return style;
	}
	
	private void mergeCell(XSSFSheet spreadsheet,int fRow, int lRow, int fCol, int lCol){
		spreadsheet.addMergedRegion(new CellRangeAddress(fRow,lRow,fCol,lCol));
	}
}
