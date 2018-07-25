package util.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.PageOrder;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import util.TRNUtil;
import util.bundle.BundleUtil;
import util.database.CCTConnection;
import util.date.DateUtil;
import util.file.ExcelManagerUtil;
import util.log.LogUtil;
import util.string.RandomUtil;
import util.type.DatabaseType.DbType;
import util.web.SessionUtil;

import com.cct.common.CommonUser;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;

public class ReportPOIManagerUtil {

	/* KM292 ส่วนบน */
	private static String fontReport = "TH SarabunPSK";
	private static String sdate = "";
	private static String stime = "";
	private static String sn = "";
	private static String sprinter = "";

	private static String reportCode = "Report code : ";
	private static String printUser = "Print user : ";
	private static String page = "Page ";
	private static String of = " of ";

	private static int rowReportHeader = 1;

	private static final Properties properties = buildProperties();

	private static Properties buildProperties() {
		Properties prop = new Properties();
		InputStream is = null;
		try {

			is = ReportManagerUtil.class.getResourceAsStream("/util/report/report.properties");
			prop.load(is);

			// get the property value and print it out
			fontReport = prop.getProperty("fontname");
			sdate = prop.getProperty("date");
			stime = prop.getProperty("time");
			sn = prop.getProperty("n");
			sprinter = prop.getProperty("printer");

			return prop;
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (Exception e) {
			}
		}
	}

	public static Properties getProperties() {
		return properties;
	}

	public static int createHeader(XSSFWorkbook wb, XSSFSheet sheet, String reportName, LinkedHashMap<String, String> criteria, int cellStart, int cellEnd) throws Exception {

		try {
			/* สร้างชื่อรายงาน */
			Row row = sheet.createRow((short) rowReportHeader);
			Cell cell = row.createCell(cellStart);
			sheet.addMergedRegion(new CellRangeAddress(rowReportHeader, rowReportHeader, cellStart, cellEnd));

			cell.setCellStyle(createHeaderStyle(wb));
			cell.setCellValue(reportName);

			int iLoop = 0;
			XSSFRichTextString richStri = null;
			String seperlate = " : ";

			if (criteria != null) {
				Iterator it = criteria.entrySet().iterator();
				while (it.hasNext()) {
					iLoop++;

					Map.Entry pairs = (Map.Entry) it.next();
					LogUtil.UTIL.debug(pairs.getKey() + " = " + pairs.getValue());

					row = sheet.createRow((short) rowReportHeader + iLoop);
					cell = row.createCell(cellStart);
					/* mearge */
					sheet.addMergedRegion(new CellRangeAddress(rowReportHeader + iLoop, rowReportHeader + iLoop, cellStart, cellEnd));

					richStri = new XSSFRichTextString(pairs.getKey() + seperlate + pairs.getValue());
					richStri.applyFont(0, pairs.getKey().toString().length() + seperlate.length(), createCriteriaLabelStyle(wb).getFontIndex());
					richStri.applyFont(pairs.getKey().toString().length() + seperlate.length(), richStri.length(), createCriteriaValueStyle(wb).getFontIndex());

					cell.setCellValue(richStri);
					cell.setCellStyle(createCriteriaStyle(wb));

				}
			}

			iLoop++;

			/* Create Row ว่างตามมาตรฐาน Template */
			createBlankRow(sheet, cellStart, cellEnd, rowReportHeader + iLoop);

			return rowReportHeader + iLoop;
		} catch (Exception e) {
			throw e;
		}

	}

	public static XSSFCellStyle createHeaderStyle(XSSFWorkbook wb) throws Exception {
		
		XSSFCellStyle style;
		try {
			XSSFFont font = createFontB16(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	/* Style criteria * */
	public static XSSFCellStyle createCriteriaStyle(XSSFWorkbook wb) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontN14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		} catch (Exception e) {
			throw e;
		}
		return style;
	}

	public static XSSFCellStyle createCriteriaBoldStyle(XSSFWorkbook wb) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontB14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		} catch (Exception e) {
			throw e;
		}
		return style;
	}

	/* Style ข้อมูล label criteria * */
	public static XSSFCellStyle createCriteriaLabelStyle(XSSFWorkbook wb) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontB14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		} catch (Exception e) {
			throw e;
		}
		return style;
	}

	/* Style ข้อมูล value criteria * */
	public static XSSFCellStyle createCriteriaValueStyle(XSSFWorkbook wb) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontN14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	/* วาดวันที่ส่วนที่พิมพ์ * */
	public static void createPrintDatetime(Connection conn, XSSFWorkbook wb, XSSFSheet sheet, int rows, int cells, Locale locale) throws Exception {

		ResourceBundle bundle = BundleUtil.getBundle("resources.bundle.common.Message", locale);
		try {
			Row row = sheet.createRow((short) rows);
			Cell cell = row.createCell(cells);
			cell.setCellStyle(printDateTimeStyle(wb));
			cell.setCellValue(bundle.getString("printDate") + " " + getPrintDate(conn) +" "+bundle.getString("n"));
		} catch (Exception e) {
			throw e;
		}
	}

	/* วาดวันที่ส่วนที่พิมพ์ * แบบมี  "เวลา ... น." */
	public static void createPrintDatetimeMinute(Connection conn, XSSFWorkbook wb, XSSFSheet sheet, int rows, int cells, Locale locale) throws Exception {
		ResourceBundle bundle = BundleUtil.getBundle("resources.bundle.common.Message", locale);

		String[] timePrint = getPrintDate(conn).split(" ");
		try {
			Row row = sheet.createRow((short) rows);
			Cell cell = row.createCell(cells);
			cell.setCellStyle(printDateTimeStyle(wb));
			cell.setCellValue(bundle.getString("printDate") + " " + timePrint[0]+ " "+ bundle.getString("time") + " " + timePrint[1]+ " " + bundle.getString("minute"));
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * วาดวันที่พิมพ์ (Print date/time : 07/04/2012  17:24)
	 * @param conn
	 * @param wb
	 * @param sheet
	 * @param rows
	 * @param cells
	 * @param locale
	 * @throws Exception
	 */
	public static void createPrintDateSlashTime(Connection conn, XSSFWorkbook wb, XSSFSheet sheet, int rows, int cells, Locale locale) throws Exception {
		ResourceBundle bundle = BundleUtil.getBundle("resources.bundle.common.Message", locale);
		try {
			Row row = sheet.createRow((short) rows);
			Cell cell = row.createCell(cells);
			cell.setCellStyle(printDateTimeStyle(wb));
			cell.setCellValue(bundle.getString("printDateTime") + " : " + getPrintDate(conn));
		} catch (Exception e) {
			throw e;
		}
	}

	/* Style table header */
	public static XSSFCellStyle createTableHeaderStyle(XSSFWorkbook wb) throws Exception {

		XSSFCellStyle style;
		short border = 1;
		try {
			XSSFFont font = createFontB14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			style.setBorderLeft(border);
			style.setBorderRight(border);
			style.setBorderTop(border);
			style.setBorderBottom(border);
		} catch (Exception e) {
			throw e;
		}
		return style;
	}

	/* ส่วน ข้อมูลรายละเอียดรายงาน ที่เป็นตัวเลข */
	public static XSSFCellStyle createNumberResultStyle(XSSFWorkbook wb, String format, boolean topBorder, boolean bottomBorder, boolean leftBorder, boolean rightBorder)
			throws Exception {

		XSSFCellStyle style;
		short border = 1;
		try {
			XSSFFont font = createFontN14(wb);

			DataFormat dataFormat = wb.createDataFormat();

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
			style.setDataFormat(dataFormat.getFormat(format));
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

			if (bottomBorder) {
				style.setBorderBottom(border);
			}
			if (topBorder) {
				style.setBorderTop(border);
			}
			if (leftBorder) {
				style.setBorderLeft(border);
			}
			if (rightBorder) {
				style.setBorderRight(border);
			}
		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	/* ส่วน ข้อมูลรายละเอียดรายงาน ที่เป็นตัวอักษร */
	public static XSSFCellStyle createStringResultStyle(XSSFWorkbook wb, String format, boolean topBorder, boolean bottomBorder, boolean leftBorder, boolean rightBorder)
			throws Exception {

		XSSFCellStyle style;
		short border = 1;
		try {
			XSSFFont font = createFontN14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

			if (bottomBorder) {
				style.setBorderBottom(border);
			}
			if (topBorder) {
				style.setBorderTop(border);
			}
			if (leftBorder) {
				style.setBorderLeft(border);
			}
			if (rightBorder) {
				style.setBorderRight(border);
			}
		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	/* ส่วน ข้อมูลรายละเอียดรายงาน ที่เป็นตัวอักษร */
	public static XSSFCellStyle createCenterStringResultStyle(XSSFWorkbook wb, String format, boolean topBorder, boolean bottomBorder, boolean leftBorder, boolean rightBorder)
			throws Exception {

		XSSFCellStyle style;
		short border = 1;
		try {
			XSSFFont font = createFontN14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

			if (bottomBorder) {
				style.setBorderBottom(border);
			}
			if (topBorder) {
				style.setBorderTop(border);
			}
			if (leftBorder) {
				style.setBorderLeft(border);
			}
			if (rightBorder) {
				style.setBorderRight(border);
			}
		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	/* Style ส่วนผลรวมส่วนหัวผลลัพธ์ */
	public static XSSFCellStyle createTableSummaryLable(XSSFWorkbook wb, boolean topBorder, boolean bottomBorder, boolean leftBorder, boolean rightBorder) throws Exception {
		
		XSSFCellStyle style;
		short border = 1;
		try {
			XSSFFont font = createFontB14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			if (bottomBorder) {
				style.setBorderBottom(border);
			}
			if (topBorder) {
				style.setBorderTop(border);
			}
			if (leftBorder) {
				style.setBorderLeft(border);
			}
			if (rightBorder) {
				style.setBorderRight(border);
			}
		} catch (Exception e) {
			throw e;
		}
		return style;
	}

	/* Style center */
	public static XSSFCellStyle createTextCenter(XSSFWorkbook wb, boolean topBorder, boolean bottomBorder, boolean leftBorder, boolean rightBorder) throws Exception {
		XSSFCellStyle style;

		short border = 1;
		try {
			XSSFFont font = createFontN14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			if (bottomBorder) {
				style.setBorderBottom(border);
			}
			if (topBorder) {
				style.setBorderTop(border);
			}
			if (leftBorder) {
				style.setBorderLeft(border);
			}
			if (rightBorder) {
				style.setBorderRight(border);
			}
		} catch (Exception e) {
			throw e;
		}
		return style;
	}

	/* Style ส่วนผลรวมส่วนหัวค่าผลลัพธ์ */
	public static XSSFCellStyle createTableSummaryValue(XSSFWorkbook wb, String format, boolean topBorder, boolean bottomBorder, boolean leftBorder, boolean rightBorder) throws Exception {

		XSSFCellStyle style;
		DataFormat dataFormat = wb.createDataFormat();
		short border = 1;
		try {
			XSSFFont font = createFontB14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setDataFormat(dataFormat.getFormat(format));
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			if (bottomBorder) {
				style.setBorderBottom(border);
			}
			if (topBorder) {
				style.setBorderTop(border);
			}
			if (leftBorder) {
				style.setBorderLeft(border);
			}
			if (rightBorder) {
				style.setBorderRight(border);
			}
		} catch (Exception e) {
			throw e;
		}
		return style;
	}

	/* Style ส่วนแสดง footer report */
	public static void createFooter(String code, CommonUser user, XSSFWorkbook wb, XSSFSheet sheet, int rowStart, int cellsCode, int cellsUser) throws Exception {
	
		try {
			createBlankRow(sheet, 0, 10, rowStart);

			rowStart++;
			Row row = sheet.createRow((short) rowStart);
			Cell cell = row.createCell(cellsCode);
			cell.setCellStyle(createResultLeft(wb));
			cell.setCellValue(code);
			Cell cell2 = row.createCell(cellsUser);
			cell2.setCellStyle(createResultRight(wb));
			cell2.setCellValue(user.getFullName() + " " + sprinter);

		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * วันที่พิมพ์
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	private static String getPrintDate(Connection conn) throws Exception {
		String result = "";
		try {
			String currentDate = DateUtil.getCurrentDateDB(conn, "DD/MM/YYYY HH24:MI", DbType.ORA);
			result = TRNUtil.convertDateTimeForDisplayHHMM(currentDate.substring(0, 16));

		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	private static XSSFCellStyle printDateTimeStyle(XSSFWorkbook wb) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontN14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		} catch (Exception e) {
			throw e;
		}
		return style;
	}

	public static XSSFFont createFontN14(XSSFWorkbook wb) throws Exception {
		XSSFFont font;
		try {
			font = wb.createFont();
			font.setFontName(fontReport);
			font.setFontHeightInPoints((short) 14);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
		} catch (Exception e) {
			throw e;
		}
		return font;
	}
	
	public static XSSFFont createFontNU14(XSSFWorkbook wb) throws Exception {
		XSSFFont font;
		try {
			font = wb.createFont();
			font.setFontName(fontReport);
			font.setFontHeightInPoints((short) 14);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);
			font.setUnderline(XSSFFont.U_SINGLE);
		} catch (Exception e) {
			throw e;
		}
		return font;
	}

	private static XSSFFont createFontB14(XSSFWorkbook wb) throws Exception {
		XSSFFont font;
		try {
			font = wb.createFont();
			font.setFontName(fontReport);
			font.setFontHeightInPoints((short) 14);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		} catch (Exception e) {
			throw e;
		}
		return font;
	}

	private static XSSFFont createFontB16(XSSFWorkbook wb) throws Exception {
		XSSFFont font;
		try {
			font = wb.createFont();
			font.setFontName(fontReport);
			font.setFontHeightInPoints((short) 16);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
		} catch (Exception e) {
			throw e;
		}
		return font;
	}

	public static XSSFCellStyle createTableResultCenterStyle(XSSFWorkbook wb) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontN14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	private static XSSFCellStyle createResultLeft(XSSFWorkbook wb) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontN14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	public static XSSFCellStyle createResultRight(XSSFWorkbook wb) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontN14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	private static void createBlankRow(XSSFSheet sheet, int cellStart, int cellEnd, int rowStart) {

		Row row = sheet.createRow((short) rowStart);
		Cell cell = row.createCell(cellStart);
		sheet.addMergedRegion(new CellRangeAddress(rowStart, rowStart, cellStart, cellEnd));
		cell.setCellValue("");

	}

	public static void sumColumn(int row, int columnStart, int columnEnd, Cell cell) {

		row++;
		String cellStartLetter = CellReference.convertNumToColString(columnStart);
		String cellEndLetter = CellReference.convertNumToColString(columnEnd);

		String strFormula = "SUM(" + cellStartLetter + row + ":" + cellEndLetter + row + ")";
		cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		cell.setCellFormula(strFormula);
	}

	public static void sumRows(int colum, int rowStart, int rowEnd, Cell cell) {
		String columLetter = CellReference.convertNumToColString(colum);

		String strFormula = "SUM(" + columLetter + rowStart + ":" + columLetter + rowEnd + ")";
		cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
		cell.setCellFormula(strFormula);
	}

	public void exportExcelFile(XSSFWorkbook workbook, String fileName) throws Exception {

		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			OutputStream os = response.getOutputStream();
			workbook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * @description สร้าง style อักษร หนา ขนาด 14 จัดกลาง กำหนดขนาดของกรอบได้ (0
	 *              = ไม่มี , 1 = เส้นบาง, 2 = เส้นหนา)
	 * @param wb
	 * @param topBorderWieght
	 *            ความหนาของกรอบบน
	 * @param bottomBorderWieght
	 *            ขวามหนาของกรอบล่าง
	 * @param leftBorderWieght
	 *            ความหนาของกรอบซ้าย
	 * @param rightBorderWieght
	 *            ความหนาของกรอบขวา
	 * @return style
	 * @throws Exception
	 */
	public static XSSFCellStyle createStyleCB14(XSSFWorkbook wb, int topBorderWieght, int bottomBorderWieght, int leftBorderWieght, int rightBorderWieght) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontB14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

			style.setBorderTop((short) topBorderWieght);
			style.setBorderBottom((short) bottomBorderWieght);
			style.setBorderLeft((short) leftBorderWieght);
			style.setBorderRight((short) rightBorderWieght);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	/**
	 * @description สร้าง style อักษร หนา ขนาด 14 จัดกลาง กำหนดขนาดของกรอบได้  (0 = ไม่มี , 1 = เส้นบาง, 2 = เส้นหนา)
	 * มีการตัดข้อความ/ตัดบรรทัด (WrapText)
	 * @param wb
	 * @param topBorderWieght
	 * @param bottomBorderWieght
	 * @param leftBorderWieght
	 * @param rightBorderWieght
	 * @return
	 * @throws Exception
	 */
	public static XSSFCellStyle createStyleCBW14(XSSFWorkbook wb, int topBorderWieght, int bottomBorderWieght, int leftBorderWieght, int rightBorderWieght) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontB14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

			style.setBorderTop((short) topBorderWieght);
			style.setBorderBottom((short) bottomBorderWieght);
			style.setBorderLeft((short) leftBorderWieght);
			style.setBorderRight((short) rightBorderWieght);
			style.setWrapText(true);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	public static XSSFCellStyle createStyleNoBorderCB14(XSSFWorkbook wb, int topBorderWieght, int bottomBorderWieght, int leftBorderWieght, int rightBorderWieght) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontB14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

			style.setBorderTop((short) topBorderWieght);
			style.setBorderBottom((short) bottomBorderWieght);
			style.setBorderLeft((short) leftBorderWieght);
			style.setBorderRight((short) rightBorderWieght);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	public static XSSFCellStyle createStyleRB14(XSSFWorkbook wb, int topBorderWieght, int bottomBorderWieght, int leftBorderWieght, int rightBorderWieght) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontB14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

			style.setBorderTop((short) topBorderWieght);
			style.setBorderBottom((short) bottomBorderWieght);
			style.setBorderLeft((short) leftBorderWieght);
			style.setBorderRight((short) rightBorderWieght);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}
	
	
	/*
	 * ขนาดอักษาร 14 ชิดขวา ตัวหนา กำหนด Format ได้ กำหนดเส้นขอบได้
	 */
	public static XSSFCellStyle createStyleRB14Format(XSSFWorkbook wb, String format, int topBorderWieght, int bottomBorderWieght, int leftBorderWieght, int rightBorderWieght) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontB14(wb);
			DataFormat dataFormat = wb.createDataFormat();
			
			style = wb.createCellStyle();
			style.setFont(font);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			style.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			style.setDataFormat(dataFormat.getFormat(format));

			style.setBorderTop((short) topBorderWieght);
			style.setBorderBottom((short) bottomBorderWieght);
			style.setBorderLeft((short) leftBorderWieght);
			style.setBorderRight((short) rightBorderWieght);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}
	public static XSSFCellStyle createStyleLB14(XSSFWorkbook wb, int topBorderWieght, int bottomBorderWieght, int leftBorderWieght, int rightBorderWieght) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontB14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

			style.setBorderTop((short) topBorderWieght);
			style.setBorderBottom((short) bottomBorderWieght);
			style.setBorderLeft((short) leftBorderWieght);
			style.setBorderRight((short) rightBorderWieght);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}



	/* Style table header */
	public static XSSFCellStyle createStyleTableHeader(XSSFWorkbook wb) throws Exception {

		XSSFCellStyle style;
		short border = 1;
		try {
			XSSFFont font = createFontB14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_TOP);
			style.setBorderLeft(border);
			style.setBorderRight(border);
			style.setBorderTop(border);
			style.setBorderBottom(border);
			style.setWrapText(true);
		} catch (Exception e) {
			throw e;
		}
		return style;
	}

	private static HSSFFont createFontCB14(HSSFWorkbook wb) throws Exception {
	
		HSSFFont font;
		try {
			font = wb.createFont();
			font.setFontName(fontReport);
			font.setFontHeightInPoints((short) 14);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		} catch (Exception e) {
			throw e;
		}
		return font;
	}

	private static XSSFFont createFontCB14(XSSFWorkbook wb) throws Exception {
		XSSFFont font;
		try {
			font = wb.createFont();
			font.setFontName(fontReport);
			font.setFontHeightInPoints((short) 14);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		} catch (Exception e) {
			throw e;
		}
		return font;
	}

	public static HSSFCellStyle createHeaderNStyle(HSSFWorkbook wb) throws Exception {
		
		HSSFCellStyle style;
		try {
			HSSFFont font = createFontN16(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		} catch (Exception e) {
			throw e;
		}
		return style;
	}

	public static XSSFCellStyle createHeaderNStyle(XSSFWorkbook wb) throws Exception {
		
		XSSFCellStyle style;
		try {
			XSSFFont font = createFontN16(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
		} catch (Exception e) {
			throw e;
		}
		return style;
	}

	private static HSSFFont createFontN16(HSSFWorkbook wb) throws Exception {
		
		HSSFFont font;
		try {
			font = wb.createFont();
			font.setFontName(fontReport);
			font.setFontHeightInPoints((short) 16);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		} catch (Exception e) {
			throw e;
		}
		return font;
	}

	private static XSSFFont createFontN16(XSSFWorkbook wb) throws Exception {
		
		XSSFFont font;
		try {
			font = wb.createFont();
			font.setFontName(fontReport);
			font.setFontHeightInPoints((short) 16);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		} catch (Exception e) {
			throw e;
		}
		return font;
	}


	public static XSSFCellStyle createHeaderRightStyle(XSSFWorkbook wb) throws Exception {
		
		XSSFCellStyle style;
		try {
			XSSFFont font = createFontB12(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

		} catch (Exception e) {
			throw e;
		}

		return style;
	}


	public static XSSFCellStyle createHeaderLeftStyle(XSSFWorkbook wb) throws Exception {
		
		XSSFCellStyle style;
		try {
			XSSFFont font = createFontB12(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	public static XSSFCellStyle createFontLB14(XSSFWorkbook wb) throws Exception {
		
		XSSFCellStyle style;
		try {
			XSSFFont font = createFontB14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	public static XSSFCellStyle createFontLN14(XSSFWorkbook wb) throws Exception {
		
		XSSFCellStyle style;
		try {
			XSSFFont font = createFontN14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	public static XSSFCellStyle createFontRB14(XSSFWorkbook wb) throws Exception {
		
		XSSFCellStyle style;
		try {
			XSSFFont font = createFontB14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	public static XSSFCellStyle createFontRN14(XSSFWorkbook wb) throws Exception {
		
		XSSFCellStyle style;
		try {
			XSSFFont font = createFontN14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	private static XSSFFont createFontB12(XSSFWorkbook wb) throws Exception {
		
		XSSFFont font;
		try {
			font = wb.createFont();
			font.setFontName(fontReport);
			font.setFontHeightInPoints((short) 12);
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		} catch (Exception e) {
			throw e;
		}
		return font;
	}

	/**
	 * @description สร้าง style อักษร ปรกติ ขนาด 14 ชิดซ้าย กำหนดขนาดของกรอบได้
	 *              (0 = ไม่มี , 1 = เส้นบาง, 2 = หนา)
	 * @param wb
	 * @param topBorderWieght
	 *            ความหนาของกรอบบน
	 * @param bottomBorderWieght
	 *            ขวามหนาของกรอบล่าง
	 * @param leftBorderWieght
	 *            ความหนาของกรอบซ้าย
	 * @param rightBorderWieght
	 *            ความหนาของกรอบขวา
	 * @return style
	 * @throws Exception
	 */

	public static XSSFCellStyle createStyleLN14(XSSFWorkbook wb, int topBorderWieght, int bottomBorderWieght, int leftBorderWieght, int rightBorderWieght) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontN14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

			style.setBorderTop((short) topBorderWieght);
			style.setBorderBottom((short) bottomBorderWieght);
			style.setBorderLeft((short) leftBorderWieght);
			style.setBorderRight((short) rightBorderWieght);
			style.setWrapText(true);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	/**
	 * @description สร้าง style อักษร ปรกติ ขนาด 14 ชิดซ้าย กำหนดขนาดของกรอบได้
	 *              (0 = ไม่มี , 1 = เส้นบาง, 2 = หนา)
	 * @param wb
	 * @param topBorderWieght
	 *            ความหนาของกรอบบน
	 * @param bottomBorderWieght
	 *            ขวามหนาของกรอบล่าง
	 * @param leftBorderWieght
	 *            ความหนาของกรอบซ้าย
	 * @param rightBorderWieght
	 *            ความหนาของกรอบขวา
	 * @return style
	 * @throws Exception
	 */
	public static XSSFCellStyle createStyleCN14(XSSFWorkbook wb, int topBorderWieght, int bottomBorderWieght, int leftBorderWieght, int rightBorderWieght) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontN14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

			style.setBorderTop((short) topBorderWieght);
			style.setBorderBottom((short) bottomBorderWieght);
			style.setBorderLeft((short) leftBorderWieght);
			style.setBorderRight((short) rightBorderWieght);
			style.setWrapText(true);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	
	/**
	 * @description สร้าง style อักษร ปรกติ ขนาด 14 ชิดซ้าย กำหนด format ของตัวเลขได้ กำหนดขนาดของกรอบได้
	 *              (0 = ไม่มี , 1 = เส้นบาง, 2 = หนา)
	 * @param wb
	 * @param topBorderWieght
	 *            ความหนาของกรอบบน
	 * @param bottomBorderWieght
	 *            ขวามหนาของกรอบล่าง
	 * @param leftBorderWieght
	 *            ความหนาของกรอบซ้าย
	 * @param rightBorderWieght
	 *            ความหนาของกรอบขวา
	 * @return style
	 * @throws Exception
	 */
	public static XSSFCellStyle createStyleCN14Format(XSSFWorkbook wb, String format, int topBorderWieght, int bottomBorderWieght, int leftBorderWieght, int rightBorderWieght) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontN14(wb);
			DataFormat dataFormat = wb.createDataFormat();

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			style.setDataFormat(dataFormat.getFormat(format));
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

			style.setBorderTop((short) topBorderWieght);
			style.setBorderBottom((short) bottomBorderWieght);
			style.setBorderLeft((short) leftBorderWieght);
			style.setBorderRight((short) rightBorderWieght);
			style.setWrapText(true);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}
	
	

	/**
	 * @description สร้าง style อักษร ปรกติ ขนาด 14 ชิดซ้าย กำหนดขนาดของกรอบได้
	 *              (0 = ไม่มี , 1 = เส้นบาง, 2 = หนา)
	 * @param wb
	 * @param topBorderWieght
	 *            ความหนาของกรอบบน
	 * @param bottomBorderWieght
	 *            ขวามหนาของกรอบล่าง
	 * @param leftBorderWieght
	 *            ความหนาของกรอบซ้าย
	 * @param rightBorderWieght
	 *            ความหนาของกรอบขวา
	 * @return style
	 * @throws Exception
	 */
	public static XSSFCellStyle createStyleRN14(XSSFWorkbook wb, int topBorderWieght, int bottomBorderWieght, int leftBorderWieght, int rightBorderWieght) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontN14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

			style.setBorderTop((short) topBorderWieght);
			style.setBorderBottom((short) bottomBorderWieght);
			style.setBorderLeft((short) leftBorderWieght);
			style.setBorderRight((short) rightBorderWieght);
			style.setWrapText(true);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	
	/**
	 * @description สร้าง style อักษร ปรกติ ขนาด 14 ชิดขวา กำหนด format ได้  กำหนดขนาดของกรอบได้
	 *              (0 = ไม่มี , 1 = เส้นบาง, 2 = หนา)
	 * @param wb
	 * @param topBorderWieght
	 *            ความหนาของกรอบบน
	 * @param bottomBorderWieght
	 *            ขวามหนาของกรอบล่าง
	 * @param leftBorderWieght
	 *            ความหนาของกรอบซ้าย
	 * @param rightBorderWieght
	 *            ความหนาของกรอบขวา
	 * @return style
	 * @throws Exception
	 */
	public static XSSFCellStyle createStyleRN14Format(XSSFWorkbook wb, String format, int topBorderWieght, int bottomBorderWieght, int leftBorderWieght, int rightBorderWieght) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontN14(wb);
			DataFormat dataFormat = wb.createDataFormat();
			
			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
			style.setDataFormat(dataFormat.getFormat(format));
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

			style.setBorderTop((short) topBorderWieght);
			style.setBorderBottom((short) bottomBorderWieght);
			style.setBorderLeft((short) leftBorderWieght);
			style.setBorderRight((short) rightBorderWieght);
			style.setWrapText(true);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}
	
	public static XSSFCellStyle createStyleRB14SumTotal(XSSFWorkbook wb, String format, int topBorderWieght, int bottomBorderWieght, int leftBorderWieght, int rightBorderWieght) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontN14(wb);

			DataFormat dataFormat = wb.createDataFormat();

			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			style.setDataFormat(dataFormat.getFormat(format));
			style.setFillForegroundColor(new XSSFColor(new java.awt.Color(217, 217, 217)));
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);


			style.setBorderTop((short) topBorderWieght);
			style.setBorderBottom((short) bottomBorderWieght);
			style.setBorderLeft((short) leftBorderWieght);
			style.setBorderRight((short) rightBorderWieght);
			style.setWrapText(true);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	public static XSSFCellStyle createStyleRN14SumTotal(XSSFWorkbook wb, String format, int topBorderWieght, int bottomBorderWieght, int leftBorderWieght, int rightBorderWieght) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontN14(wb);

			DataFormat dataFormat = wb.createDataFormat();

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			style.setDataFormat(dataFormat.getFormat(format));
			style.setFillForegroundColor(new XSSFColor(new java.awt.Color(217, 217, 217)));
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);


			style.setBorderTop((short) topBorderWieght);
			style.setBorderBottom((short) bottomBorderWieght);
			style.setBorderLeft((short) leftBorderWieght);
			style.setBorderRight((short) rightBorderWieght);
			style.setWrapText(true);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	public static XSSFCellStyle createStyleCB14SumTotal(XSSFWorkbook wb, int topBorderWieght, int bottomBorderWieght, int leftBorderWieght, int rightBorderWieght) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontN14(wb);

			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			style.setFillForegroundColor(new XSSFColor(new java.awt.Color(217, 217, 217)));
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);

			style.setBorderTop((short) topBorderWieght);
			style.setBorderBottom((short) bottomBorderWieght);
			style.setBorderLeft((short) leftBorderWieght);
			style.setBorderRight((short) rightBorderWieght);
			style.setWrapText(true);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}


	public static XSSFCellStyle createStyleCN14SumTotal(XSSFWorkbook wb, int topBorderWieght, int bottomBorderWieght, int leftBorderWieght, int rightBorderWieght) throws Exception {

		XSSFCellStyle style;

		try {
			XSSFFont font = createFontN14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
			style.setFillForegroundColor(new XSSFColor(new java.awt.Color(217, 217, 217)));
			style.setFillPattern(CellStyle.SOLID_FOREGROUND);

			style.setBorderTop((short) topBorderWieght);
			style.setBorderBottom((short) bottomBorderWieght);
			style.setBorderLeft((short) leftBorderWieght);
			style.setBorderRight((short) rightBorderWieght);
			style.setWrapText(true);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}

	/**
	 * @description สร้าง style อักษร ปรกติ ขนาด 14 ชิดซ้าย กำหนดขนาดของกรอบได้
	 *              (0 = ไม่มี , 1 = เส้นบาง, 2 = หนา)
	 * @param wb
	 * @param topBorderWieght
	 *            ความหนาของกรอบบน
	 * @param bottomBorderWieght
	 *            ขวามหนาของกรอบล่าง
	 * @param leftBorderWieght
	 *            ความหนาของกรอบซ้าย
	 * @param rightBorderWieght
	 *            ความหนาของกรอบขวา
	 * @return style
	 * @throws Exception
	 */
	public static XSSFCellStyle createStyleBR14(XSSFWorkbook wb, int topBorderWieght, int bottomBorderWieght, int leftBorderWieght, int rightBorderWieght) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontB14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

			style.setBorderTop((short) topBorderWieght);
			style.setBorderBottom((short) bottomBorderWieght);
			style.setBorderLeft((short) leftBorderWieght);
			style.setBorderRight((short) rightBorderWieght);
			style.setWrapText(true);
		} catch (Exception e) {
			throw e;
		}

		return style;
	}



	/**
	 * วาด footer report
	 * @param sheet
	 * @param reportCode
	 * @param printUser
	 * @throws Exception
	 */
	public static void createPrintPageOfNumberFooter(CCTConnection conn, XSSFSheet sheet, String reportCode, CommonUser user, Locale locale) throws Exception {
		ResourceBundle messageBundle = BundleUtil.getBundle("resources.bundle.common.Message", locale);

		Footer footer = sheet.getFooter();
		footer.setLeft(HeaderFooter.font(fontReport, "Normal")
				+ HeaderFooter.fontSize((short) 14)
				+ reportCode
				);

		footer.setRight(HeaderFooter.font(fontReport, "Normal")
				+ HeaderFooter.fontSize((short) 14)
				+ messageBundle.getString("printUser")
				+ " : "
				+ user.getFullName()
				+ ""
				+ "\n"
				+ messageBundle.getString("page") + " "
				+ HeaderFooter.page()
				+ " " +messageBundle.getString("of") + " "
				+ HeaderFooter.numPages()
				);

	}

	public static String searchOrgName(CCTConnection conn, String orgId) throws Exception{
		return null;
	}

	public static XSSFWorkbook lockAll(XSSFWorkbook workbookx , CCTConnection conn) throws Exception{
		/***** write file temp จาก workbookx เก็บไว้ที่   Server  *****/
		String filePath = ParameterConfig.getParameter().getAttachFile().getTmpPath()+"TEMP/";
		File pathTmp = new File(filePath);
		if(!pathTmp.exists()){
			pathTmp.mkdirs();
		}

		long millisStart = Calendar.getInstance().getTimeInMillis();

		long LOWER_RANGE = 0; //assign lower range value
		long UPPER_RANGE = millisStart; //assign upper range value
		Random random = new Random();

		long randomValue = LOWER_RANGE + (long)(random.nextDouble()*(UPPER_RANGE - LOWER_RANGE));

		FileOutputStream f = new FileOutputStream(filePath+ randomValue+".xlsx");
		workbookx.write(f);
		f.close();
		/***********************************************/

		//แทรก การ insert watermark
		//1. สร้างภาพ creatWaterMarkPicture

		//call method สำหรับเพิ่ม  waterMark ใน Excel
		CommonUser user = (CommonUser) SessionUtil.get(CommonUser.DEFAULT_SESSION_ATTRIBUTE);
		XSSFWorkbook workbookx2 = new ExcelManagerUtil().addWaterMark(user.getFullName(), "00000001", filePath+ randomValue+".xlsx" , conn);

		//lock sheet
		String password ="";
		if(ParameterConfig.getParameter().getReport().getPasswordReport()!=null&&!ParameterConfig.getParameter().getReport().getPasswordReport().equals("")){
			password= ParameterConfig.getParameter().getReport().getPasswordReport();
		}else{
			password = RandomUtil.getRandomString(6, true, true, false);
		}
		byte[] pwdBytes = null;

		for (int i = 0; i < workbookx2.getNumberOfSheets(); i++){
			Sheet s =  workbookx2.getSheetAt(i);

			XSSFSheet sheet = ((XSSFSheet)s);

			sheet.setDisplayGridlines(false);

			sheet.getPrintSetup().setPageOrder(PageOrder.OVER_THEN_DOWN);
		    sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);
			sheet.protectSheet(password);
			sheet.lockDeleteColumns();
			sheet.lockDeleteRows();
			sheet.lockFormatCells();
			sheet.lockFormatColumns();
			sheet.lockFormatRows();
			sheet.lockInsertColumns();
			sheet.lockInsertRows();
			sheet.lockObjects();
			sheet.lockSelectLockedCells();
			sheet.lockSelectUnlockedCells();

			sheet.enableLocking();
		}

		workbookx2.lockStructure();

		return workbookx2;
	}

	public static byte[] readFileForLock(byte[] file , CCTConnection conn, String reportCode, CommonUser user, Locale locale) throws Exception{
		
		byte[] result = null;
		try{
			long millisStart = Calendar.getInstance().getTimeInMillis();

			long LOWER_RANGE = 0; //assign lower range value
			long UPPER_RANGE = millisStart; //assign upper range value
			Random random = new Random();

			long randomValue = LOWER_RANGE + (long)(random.nextDouble()*(UPPER_RANGE - LOWER_RANGE));

			String filePath = ParameterConfig.getParameter().getReport().getPathfile()+"TEMP/";
			File pathTmp = new File(filePath);
			if(!pathTmp.exists()){
				pathTmp.mkdirs();
			}

			File tmpImageFile = new File(filePath+ randomValue+".xlsx");

		    FileOutputStream tmpOutputStream = null;

		    tmpOutputStream = new FileOutputStream(tmpImageFile);
		    tmpOutputStream.write(file);
		    tmpOutputStream.close();

		    FileInputStream workbookFile = new FileInputStream(new File(filePath + randomValue+".xlsx"));

		    LogUtil.UTIL.debug("read file-----------------------------------");
			XSSFWorkbook workbook = new XSSFWorkbook(workbookFile);
			LogUtil.UTIL.debug("read workbook-------------------------------");
			XSSFSheet sheet = workbook.getSheetAt(0);
			ReportPOIManagerUtil.createPrintPageOfNumberFooter(conn, sheet, reportCode, user, locale);
			//Lock Sheet
			workbook = ReportPOIManagerUtil.lockAll(workbook,conn);
			LogUtil.UTIL.debug("lock sheet-------------------------------");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            workbook.write(baos);
            result = baos.toByteArray();
            workbookFile.close();
            baos.close();

			LogUtil.UTIL.debug("write workbook-------------------------------");
            File tmpImageFile2 = new File(filePath+ randomValue+".xlsx");

            boolean fdel = tmpImageFile2.delete();
            LogUtil.UTIL.debug("del : "+ fdel);

            LogUtil.UTIL.debug("delete file TEMP-------------------------------");
		}catch(Exception e){
			throw e;
		}

		return result;
	}

	public static void setMargin(XSSFSheet sheet){
	    sheet.setMargin(XSSFSheet.LeftMargin, 0.25);
		sheet.setMargin(XSSFSheet.RightMargin, 0.25);
		sheet.setMargin(XSSFSheet.TopMargin, 0.75);
		sheet.setMargin(XSSFSheet.BottomMargin, 0.80);
	}

	/* Style Label underline * */
	public static XSSFCellStyle createLabelUnderlineStyle(XSSFWorkbook wb) throws Exception {

		XSSFCellStyle style;
		try {
			XSSFFont font = createFontNU14(wb);

			style = wb.createCellStyle();
			style.setFont(font);
			style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);

		} catch (Exception e) {
			throw e;
		}

		return style;
	}

}
