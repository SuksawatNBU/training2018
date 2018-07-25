package util.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import util.database.CCTConnection;
import util.database.CCTConnectionUtil;

import com.cct.common.CommonUser;


public class ReportManagerUtil {
	private CommonUser user;
	private Locale locale;
	
	public ReportManagerUtil(CommonUser user, Locale locale) {
		this.user = user;
		this.locale = locale;
	}
	
	public byte[] runReportXLSDownload(String fileJasper, List lstData, HashMap parameterMap , CCTConnection conn, String reportCode) throws JRException, Exception {
		byte[] bytes = null;
		File rptFile = null;
		try {
			JRDataSource jrDataSource = createDataSource(lstData);
			rptFile = new File(fileJasper);
			// JasperReport rtfReport =
			// (JasperReport)JRLoader.loadObject(rptFile.getPath());
			JasperReport rtfReport = (JasperReport) JRLoader.loadObjectFromFile(rptFile.getPath());
			JasperPrint rtfPrint = null;
			rtfPrint = JasperFillManager.fillReport(rtfReport, parameterMap, jrDataSource);

			JExcelApiExporter exporter = new JExcelApiExporter();
			ByteArrayOutputStream rtfOutput = new ByteArrayOutputStream();

//			exporter.setParameter(JRExporterParameter.JASPER_PRINT, rtfPrint);
//			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, rtfOutput);
//			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);
//			exporter.setParameter(JRXlsAbstractExporterParameter.SHEET_NAMES, new String[] { "POIND_INW", });
//
//			exporter.exportReport();

			JRXlsxExporter exporterXLSXReporter = new JRXlsxExporter();
			exporterXLSXReporter.setParameter(JRXlsExporterParameter.JASPER_PRINT,rtfPrint);
			exporterXLSXReporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,rtfOutput);
			exporterXLSXReporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);
			exporterXLSXReporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, new String[] { "POIND_INW", });

			exporterXLSXReporter.exportReport();

			bytes = rtfOutput.toByteArray();

			bytes = ReportPOIManagerUtil.readFileForLock(bytes , conn, reportCode, user, locale);

			return bytes;
		} catch (JRException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	public byte[] runReportXLSDownload(String fileJasper, List lstData, HashMap parameterMap, String sheetName , CCTConnection conn, String reportCode) throws JRException, Exception {
		byte[] bytes = null;
		File rptFile = null;
		try {
			JRDataSource jrDataSource = createDataSource(lstData);
			rptFile = new File(fileJasper);
			// JasperReport rtfReport =
			// (JasperReport)JRLoader.loadObject(rptFile.getPath());
			JasperReport rtfReport = (JasperReport) JRLoader.loadObjectFromFile(rptFile.getPath());
			JasperPrint rtfPrint = null;
			rtfPrint = JasperFillManager.fillReport(rtfReport, parameterMap, jrDataSource);

			//JExcelApiExporter exporter = new JExcelApiExporter();
			ByteArrayOutputStream rtfOutput = new ByteArrayOutputStream();

			//exporter.setParameter(JRExporterParameter.JASPER_PRINT, rtfPrint);
			//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, rtfOutput);
			//exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);
			//exporter.setParameter(JRXlsAbstractExporterParameter.SHEET_NAMES, new String[] { "" + sheetName + "", });

			//exporter.exportReport();

			JRXlsxExporter exporterXLSXReporter = new JRXlsxExporter();
			exporterXLSXReporter.setParameter(JRXlsExporterParameter.JASPER_PRINT,rtfPrint);
			exporterXLSXReporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,rtfOutput);
			exporterXLSXReporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);
			exporterXLSXReporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			exporterXLSXReporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, new String[] { "" + sheetName + "", });

			exporterXLSXReporter.exportReport();


			bytes = rtfOutput.toByteArray();

			bytes = ReportPOIManagerUtil.readFileForLock(bytes , conn, reportCode, user, locale);


			return bytes;
		} catch (JRException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Generate report to pdf file
	 *
	 * @param fileJasper
	 * @param lstData
	 * @param parameterMap
	 * @param fileName
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public byte[] runReportPDF(String fileJasper, List lstData, HashMap parameterMap) throws Exception {
		byte[] bytes = null;
		File rptFile = null;
		try {
			JRDataSource jrDataSource = createDataSource(lstData);
			rptFile = new File(fileJasper + ".jasper");
			// JasperReport pdfReport =
			// (JasperReport)JRLoader.loadObject(rptFile.getPath());
			JasperReport pdfReport = (JasperReport) JRLoader.loadObjectFromFile(rptFile.getPath());
			JasperPrint pdfPrint = null;
			pdfPrint = JasperFillManager.fillReport(pdfReport, parameterMap, jrDataSource);

			JRPdfExporter exporter = new JRPdfExporter();
			ByteArrayOutputStream pdfOutput = new ByteArrayOutputStream();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, pdfPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, pdfOutput);

			exporter.exportReport();

			bytes = pdfOutput.toByteArray();
			return bytes;
		} catch (Exception e) {
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public JRDataSource createDataSource(Collection reportRows) {
		JRBeanCollectionDataSource dataSource;
		dataSource = new JRBeanCollectionDataSource(reportRows);
		return dataSource;
	}

	public void fillReport(ResultSet resultSet, String jasperFileName) {
		JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);
		try {
			JasperFillManager.fillReportToFile(jasperFileName, new HashMap(), resultSetDataSource);

		} catch (JRException jre) {
			//jre.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public JasperPrint fillReportJasperPrint(ResultSet resultSet, String jasperFileName, HashMap param) throws JRException {
		JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);

		// JasperPrint jasperPrint =
		// JasperFillManager.fillReport(jasperFileName+".jasper",
		// new HashMap(), resultSetDataSource);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFileName + ".jasper", param, resultSetDataSource);

		return jasperPrint;
	}

	/***
	 * Generate Report Excel file
	 *
	 * @param resultSet
	 * @param stmt
	 * @param jasperFileName
	 * @param param
	 * @return
	 * @throws JRException
	 * @throws Exception
	 */
	public byte[] runReportResultXLSDownload(ResultSet resultSet, Statement stmt, String jasperFileName, HashMap param , String sheetName , CCTConnection conn, String reportCode) throws JRException, Exception {
		byte[] bytes = null;
		try {
			JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);

			// JasperPrint jasperPrint =
			// JasperFillManager.fillReport(jasperFileName+".jasper",
			// new HashMap(), resultSetDataSource);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFileName, param, resultSetDataSource);

			//JExcelApiExporter exporter = new JExcelApiExporter();
			ByteArrayOutputStream rtfOutput = new ByteArrayOutputStream();

//			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, rtfOutput);
//			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//			exporter.setParameter(JRXlsAbstractExporterParameter.SHEET_NAMES, new String[] { "" + sheetName + "", });
//
//			exporter.exportReport();


			JRXlsxExporter exporterXLSXReporter = new JRXlsxExporter();
			exporterXLSXReporter.setParameter(JRXlsExporterParameter.JASPER_PRINT,jasperPrint);
			exporterXLSXReporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,rtfOutput);
			exporterXLSXReporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);
			exporterXLSXReporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			exporterXLSXReporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, new String[] { "" + sheetName + "", });

			exporterXLSXReporter.exportReport();

			bytes = rtfOutput.toByteArray();

			bytes = ReportPOIManagerUtil.readFileForLock(bytes, conn, reportCode, user, locale);

			return bytes;
		} catch (JRException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(resultSet, stmt);
		}
	}

	/***
	 * Generate Report Excel file
	 *
	 * @param resultSet
	 * @param stmt
	 * @param jasperFileName
	 * @param param
	 * @return
	 * @throws JRException
	 * @throws Exception
	 */
	public byte[] runReportResultXLSLockSheet(ResultSet resultSet, Statement stmt, String jasperFileName, HashMap param ,String password) throws JRException, Exception {
		byte[] bytes = null;
		try {
			JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(resultSet);

			// JasperPrint jasperPrint =
			// JasperFillManager.fillReport(jasperFileName+".jasper",
			// new HashMap(), resultSetDataSource);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFileName, param, resultSetDataSource);

			JExcelApiExporter exporter = new JExcelApiExporter();
			ByteArrayOutputStream rtfOutput = new ByteArrayOutputStream();

			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, rtfOutput);
			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);
			exporter.setParameter(JRXlsAbstractExporterParameter.SHEET_NAMES, new String[] { "POIND_INW", });
			exporter.setParameter(JRXlsExporterParameter.PASSWORD, password);

			exporter.exportReport();

			bytes = rtfOutput.toByteArray();
			return bytes;
		} catch (JRException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		} finally {
			CCTConnectionUtil.closeAll(resultSet, stmt);
		}
	}

	/**
	 * Generate report to pdf file
	 *
	 * @param fileJasper
	 * @param pdfFile
	 * @return
	 * @throws Exception
	 */
	public void savePDFReport(String pdfFile, JasperPrint jasperPrint) throws Exception {
		JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFile);
	}



	public byte[] runReportListToXLSLockSheet(List<JasperPrint> lstJasperPrint, String[] sheetName, String password)throws Exception{
		try {


			JRXlsExporter exporter = new JRXlsExporter();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, lstJasperPrint);
			//exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, new String[] { "PayIn","PayInDealer" });
			exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, sheetName);

			//exporter.setParameter(JRExporterParameter.JASPER_PRINT, rtfPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE);

			//exporter.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);

			exporter.setParameter(JRXlsExporterParameter.PASSWORD, password);


			exporter.exportReport();

			byte[] bytes = null;
			bytes = output.toByteArray();
			return bytes;

		} catch (JRException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}



	/**
	 *
	 * @param fileJasper
	 * @param lstData
	 * @param parameterMap
	 * @return
	 * @throws Exception
	 */
	public JasperPrint createJasperPrint(String fileJasper, List lstData, HashMap parameterMap)throws Exception{
		File rptFile = null;

		JasperPrint rtfPrint = null;
		try{
			JRDataSource jrDataSource = createDataSource(lstData);
			rptFile = new File(fileJasper + ".jasper");

			JasperReport rtfReport = (JasperReport) JRLoader.loadObjectFromFile(rptFile.getPath());
			rtfPrint = JasperFillManager.fillReport(rtfReport, parameterMap, jrDataSource);

		}catch(Exception e){
			throw e;
		}
		return rtfPrint;
	}

	/**
	 *
	 * Print workbook to servlet
	 */
	public void exportXLS(HSSFWorkbook workbook, String fileName)
			throws Exception {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			/* response.addHeader("Content-Type", "application/excel"); */
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename="+ fileName);
			OutputStream os = response.getOutputStream();

			workbook.write(os);
			os.flush();
			os.close();
		} catch (Exception e) {
			throw e;
		}
	}



}
