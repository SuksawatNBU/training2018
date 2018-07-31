package util;

import java.io.File;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import util.bundle.BundleUtil;
import util.calendar.CalendarUtil;
import util.cryptography.EncryptionUtil;
import util.cryptography.AES256.AESCipherHybridService;
import util.date.DateUtil;
import util.file.FileManagerUtil;
import util.log.LogUtil;
import util.type.CrytographyType.EncType;
import util.type.DatabaseType.DbType;
import util.web.MenuTreeUtil;
import util.web.MenuUtil;

import com.cct.domain.GlobalVariable;
import com.cct.domain.Operator;
import com.cct.domain.Validation;
import com.cct.exception.UCPValidateException;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;

public class TRNUtil {

	public static String HTML_BR = "<br>";
	public static String HTML_RSAQUO = "&nbsp;&rsaquo;&nbsp;";

	public enum DefaultSecound {
		START(":00"), END(":59"), NONE(""), NOW(":" + Calendar.getInstance(ParameterConfig.getParameter().getApplication().getDatabaseLocale()).get(Calendar.SECOND));

		private String value;

		private DefaultSecound(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * แปลงวันที่ที่ได้รับจาก database เป็นวันที่ที่ใช้ในการแสดงบนหน้าเว็บ เช่น
	 * 31/12/2015 > 31/12/2558
	 *
	 * @param dateValue
	 * @param locale
	 *            (get from session)
	 * @return
	 * @throws Exception
	 */
	public static String convertDateForDisplay(String dateValue, Locale locale) throws Exception {
		String toFormat = ParameterConfig.getParameter().getDateFormat().getForDisplay();
		String fromFormat = null;
		if ((dateValue == null) || dateValue.isEmpty()) {
			return "";
		} else {
			if (dateValue.length() == ParameterConfig.getParameter().getDateFormat().getForDatabaseSelect().length()) {
				fromFormat = ParameterConfig.getParameter().getDateFormat().getForDatabaseSelect();
			} else if (dateValue.length() == ParameterConfig.getParameter().getDateFormat().getForDatabaseSelectHHMMSS().length()) {
				fromFormat = ParameterConfig.getParameter().getDateFormat().getForDatabaseSelectHHMMSS();
			}
		}
		return CalendarUtil.convertDateString(dateValue, fromFormat, ParameterConfig.getParameter().getApplication().getDatabaseLocale(), toFormat, locale);
	}

	/**
	 * แปลงวันที่ที่ได้รับจาก database เป็นวันที่ที่ใช้ในการแสดงบนหน้าเว็บ เช่น
	 * 31/12/2015 23:59:59 > 31/12/2558 23:59:59
	 *
	 * @param dateValue
	 * @return
	 * @throws Exception
	 */
	public static String convertDateTimeForDisplay(String dateValue) throws Exception {
		String toFormat = ParameterConfig.getParameter().getDateFormat().getForDisplayHHMMSS();
		String fromFormat = null;
		if ((dateValue == null) || dateValue.isEmpty()) {
			return "";
		} else {
			if (dateValue.length() == ParameterConfig.getParameter().getDateFormat().getForDatabaseSelect().length()) {
				fromFormat = ParameterConfig.getParameter().getDateFormat().getForDatabaseSelect();
			} else if (dateValue.length() == ParameterConfig.getParameter().getDateFormat().getForDatabaseSelectHHMMSS().length()) {
				fromFormat = ParameterConfig.getParameter().getDateFormat().getForDatabaseSelectHHMMSS();
			}
		}
		return CalendarUtil.convertDateString(dateValue, fromFormat, ParameterConfig.getParameter().getApplication().getDatabaseLocale(), toFormat, ParameterConfig.getParameter()
				.getApplication().getDatetimeLocale());
	}

	/**
	 * 
	 * แปลงวันที่ที่ได้รับจาก database เป็นวันที่ที่ใช้ในการแสดงบนหน้าเว็บ เช่น
	 * 2016-06-20 23:59:59 > 31/12/2015 23:59:59
	 * 
	 * @param dateValue
	 * @return
	 * @throws Exception
	 */
	public static String convertDateTimeForDisplayYYYY_MM_DD_HHMMSS(String dateValue) throws Exception {
		String fromFormat = "YYYY-MM-DD HH:mm:ss";
		String toFormat = ParameterConfig.getParameter().getDateFormat().getForDisplayHHMMSS();
		if ((dateValue == null) || dateValue.isEmpty()) {
			return "";
		} else {
			if (dateValue.length() == ParameterConfig.getParameter().getDateFormat().getForDatabaseSelect().length()) {
				fromFormat = "YYYY-MM-DD";
			} else if (dateValue.length() == ParameterConfig.getParameter().getDateFormat().getForDatabaseSelectHHMMSS().length()) {
				fromFormat = "YYYY-MM-DD HH:mm:ss";
			}
		}

		return CalendarUtil.convertDateString(dateValue, fromFormat, ParameterConfig.getParameter().getApplication().getDatabaseLocale(), toFormat, ParameterConfig.getParameter()
				.getApplication().getDatetimeLocale());
	}

	/**
	 * 
	 * แปลงวันที่ที่ได้รับจาก database เป็นวันที่ที่ใช้ในการแสดงบนหน้าเว็บ เช่น
	 * 31/12/2015 23:59:59 > 2015123123595959
	 * 
	 * @param dateValue
	 * @return
	 * @throws Exception
	 */
	public static String convertDateTimeForDisplayYYYYMMDDHHMMSS(String dateValue) throws Exception {
		String toFormat = "YYYYMMDDHHmmss";
		String fromFormat = null;
		if ((dateValue == null) || dateValue.isEmpty()) {
			return "";
		} else {
			if (dateValue.length() == ParameterConfig.getParameter().getDateFormat().getForDatabaseSelect().length()) {
				fromFormat = ParameterConfig.getParameter().getDateFormat().getForDatabaseSelect();
			} else if (dateValue.length() == ParameterConfig.getParameter().getDateFormat().getForDatabaseSelectHHMMSS().length()) {
				fromFormat = ParameterConfig.getParameter().getDateFormat().getForDatabaseSelectHHMMSS();
			}
		}
		return CalendarUtil.convertDateString(dateValue, fromFormat, ParameterConfig.getParameter().getApplication().getDatabaseLocale(), toFormat, ParameterConfig.getParameter()
				.getApplication().getDatetimeLocale());
	}

	/**
	 * 
	 * แปลงวันที่ที่ได้รับจาก datepicker เพื่อ insert 31/12/2015 > 20151231
	 * 
	 * @param dateValue
	 * @return
	 * @throws Exception
	 */
	public static String convertDateTimeForInsertYYYYMMDD(String dateValue) throws Exception {
		String toFormat = "YYYYMMDD";
		String fromFormat = null;
		if ((dateValue == null) || dateValue.isEmpty()) {
			return "";
		} else {
			if (dateValue.length() == ParameterConfig.getParameter().getDateFormat().getForDatabaseSelect().length()) {
				fromFormat = ParameterConfig.getParameter().getDateFormat().getForDatabaseSelect();
			} else if (dateValue.length() == ParameterConfig.getParameter().getDateFormat().getForDatabaseSelect().length()) {
				fromFormat = ParameterConfig.getParameter().getDateFormat().getForDatabaseSelect();
			}
		}
		return CalendarUtil.convertDateString(dateValue, fromFormat, ParameterConfig.getParameter().getApplication().getDatabaseLocale(), toFormat, ParameterConfig.getParameter()
				.getApplication().getDatetimeLocale());
	}

	/**
	 * แปลงวันที่จาก 20151231 > 31/12/2015
	 * 
	 * @param dateValue
	 * @return
	 * @throws Exception
	 */
	public static String convertDateFromYYYYMMDDToDDslMMslYYYY(String dateValue) throws Exception {
		String toFormat = "DD/MM/YYYY";
		String fromFormat = "YYYYMMDD";
		if ((dateValue == null) || dateValue.isEmpty()) {
			return "";
		}

		return CalendarUtil.convertDateString(dateValue, fromFormat, ParameterConfig.getParameter().getApplication().getDatabaseLocale(), toFormat, ParameterConfig.getParameter()
				.getApplication().getDatetimeLocale());
	}

	/**
	 * แปลงวันที่ที่ได้รับจาก database เป็นวันที่ที่ใช้ในการแสดงบนหน้าเว็บ เช่น
	 * 31/12/2015 23:59 > 20151231235959
	 * 
	 * @param dateValue
	 * @return
	 * @throws Exception
	 */
	public static String convertDateTimeForDisplayYYYYMMDDHHMM(String dateValue) throws Exception {
		String toFormat = "YYYYMMDDHHmm";
		String fromFormat = null;
		if ((dateValue == null) || dateValue.isEmpty()) {
			return "";
		} else {
			if (dateValue.length() == ParameterConfig.getParameter().getDateFormat().getForDatabaseSelect().length()) {
				fromFormat = ParameterConfig.getParameter().getDateFormat().getForDatabaseSelect();
			} else if (dateValue.length() == ParameterConfig.getParameter().getDateFormat().getForDatabaseSelectHHMM().length()) {
				fromFormat = ParameterConfig.getParameter().getDateFormat().getForDatabaseSelectHHMM();
			} else if (dateValue.length() == ParameterConfig.getParameter().getDateFormat().getForDatabaseSelectHHMMSS().length()) {
				fromFormat = ParameterConfig.getParameter().getDateFormat().getForDatabaseSelectHHMMSS();

			}
		}
		return CalendarUtil.convertDateString(dateValue, fromFormat, ParameterConfig.getParameter().getApplication().getDatabaseLocale(), toFormat, ParameterConfig.getParameter()
				.getApplication().getDatetimeLocale());
	}

	/**
	 * แปลงวันที่ที่ได้รับจาก database เป็นวันที่ที่ใช้ในการแสดงบนหน้าเว็บ เช่น
	 * 31/12/2015 23:59:00 > 20151231235959
	 * 
	 * @param dateValue
	 * @return
	 * @throws Exception
	 */
	public static String convertDateTimeForYYYYMMDDHHMMSS(String dateValue) throws Exception {
		String toFormat = "YYYYMMDDHHmmss";
		String fromFormat = null;
		if ((dateValue == null) || dateValue.isEmpty()) {
			return "";
		} else if (dateValue.length() == ParameterConfig.getParameter().getDateFormat().getForDatabaseSelectHHMMSS().length()) {
			fromFormat = ParameterConfig.getParameter().getDateFormat().getForDatabaseSelectHHMMSS();

		}
		return CalendarUtil.convertDateString(dateValue, fromFormat, ParameterConfig.getParameter().getApplication().getDatabaseLocale(), toFormat, ParameterConfig.getParameter()
				.getApplication().getDatetimeLocale());
	}

	/**
	 * แปลงวันที่ที่ได้รับจาก database เป็นวันที่ที่ใช้ในการแสดงบนหน้าเว็บ เช่น
	 * 31/12/2015 23:59 > 31/12/2558 23:59
	 *
	 * @param dateValue
	 * @return
	 * @throws Exception
	 */
	public static String convertDateTimeForDisplayHHMM(String dateValue) throws Exception {
		String toFormat = ParameterConfig.getParameter().getDateFormat().getForDisplayHHMM();
		String fromFormat = null;
		if ((dateValue == null) || dateValue.isEmpty()) {
			return "";
		} else {
			if (dateValue.length() == ParameterConfig.getParameter().getDateFormat().getForDatabaseSelect().length()) {
				fromFormat = ParameterConfig.getParameter().getDateFormat().getForDatabaseSelect();
			} else if (dateValue.length() == ParameterConfig.getParameter().getDateFormat().getForDatabaseSelectHHMM().length()) {
				fromFormat = ParameterConfig.getParameter().getDateFormat().getForDatabaseSelectHHMM();
			}
		}
		return CalendarUtil.convertDateString(dateValue, fromFormat, ParameterConfig.getParameter().getApplication().getDatabaseLocale(), toFormat, ParameterConfig.getParameter()
				.getApplication().getDatetimeLocale());
	}

	/**
	 * ใช้สำหรับแปลงค่า String of long ให้เป็น Long เพื่อใช้ในการ execute query
	 *
	 * @param value
	 * @return
	 */
	public static Long convertLongValue(String value) {
		if ((value == null) || value.trim().isEmpty()) {
			return null;
		} else {
			return Long.parseLong(value);
		}
	}

	/**
	 * ใช้สำหรับแปลงค่า String of double ให้เป็น Double เพื่อใช้ในการ execute
	 * query
	 *
	 * @param value
	 * @return
	 */
	public static Double convertDoubleValue(String value) {
		if ((value == null) || value.trim().isEmpty()) {
			return null;
		} else {
			return Double.parseDouble(value);
		}
	}

	/**
	 * get Current Date(dd/mm/yyyy) From Database
	 *
	 *
	 * */
	public static String getCurrentDateDB(Connection conn) throws Exception {
		String curDate = "";
		try {
			curDate = DateUtil.getCurrentDateDB(conn, ParameterConfig.getParameter().getDateFormat().getForDatabaseSelect(), DbType.ORA);
			return curDate;
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	/**
	 * get Current DateTime(dd/mm/yyyy hh:mi:sss) From Database
	 *
	 *
	 * */
	public static String getCurrentDateTimeDB(Connection conn) throws Exception {
		String curDate = "";
		try {
			curDate = DateUtil.getCurrentDateDB(conn, "dd/mm/yyyy hh24:mi:ss", DbType.ORA);
			return curDate;
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	/**
	 * ตัด Format "," ออก
	 *
	 *
	 * */
	public static String unformatCurrency(String value) {
		String returnValue = null;
		if ((value != null) && !value.trim().isEmpty()) {
			returnValue = value.replace(",", "");
		}
		return returnValue;
	}

	/**
	 * Format ที่เป็นค่า ทศนิยม
	 *
	 * param : 100000 , 2 return : 100000.00
	 *
	 * */

	public static String formatDouble(String value, int digit) {

		String returnValue = "";
		if ((value != null) && !value.trim().isEmpty()) {
			String patten = "###0";
			if (digit > 0) {
				patten += ".";
			}

			for (int i = 0; i < digit; i++) {
				patten += "0";
			}

			returnValue = new DecimalFormat(patten).format(Double.parseDouble(value));
		}
		return returnValue;
	}

	/**
	 * Format ที่เป็นค่าเงิน
	 *
	 * param : 100000 , 2 return : 100,000.00
	 *
	 * */
	public static String formatCurrency(String value, int digit) {

		String returnValue = "";
		if ((value != null) && !value.trim().isEmpty()) {
			String patten = "#,##0";
			if (digit > 0) {
				patten += ".";
			}

			for (int i = 0; i < digit; i++) {
				patten += "0";
			}

			returnValue = new DecimalFormat(patten).format(Double.parseDouble(value));
		}
		return returnValue;
	}

	/**
	 * ดึงค่าเดือน เช่น มกราคม, กุมภาพันธ์ ...
	 * 
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public static String getMonthName(String month) throws Exception {
		String monthReturn = "";
		try {

			ResourceBundle bundle = BundleUtil.getBundle("resources.bundle.common.Message", ParameterConfig.getParameter().getApplication().getApplicationLocale());
			int numMonth = Integer.parseInt(month);
			switch (numMonth) {
			case 1:
				monthReturn = bundle.getString("month01");
				break;
			case 2:
				monthReturn = bundle.getString("month02");
				break;
			case 3:
				monthReturn = bundle.getString("month03");
				break;
			case 4:
				monthReturn = bundle.getString("month04");
				break;
			case 5:
				monthReturn = bundle.getString("month05");
				break;
			case 6:
				monthReturn = bundle.getString("month06");
				break;
			case 7:
				monthReturn = bundle.getString("month07");
				break;
			case 8:
				monthReturn = bundle.getString("month08");
				break;
			case 9:
				monthReturn = bundle.getString("month09");
				break;
			case 10:
				monthReturn = bundle.getString("month10");
				break;
			case 11:
				monthReturn = bundle.getString("month11");
				break;
			case 12:
				monthReturn = bundle.getString("month12");
				break;

			default:
				break;
			}

		} catch (Exception e) {
			throw e;
		}
		return monthReturn;
	}

	/**
	 * แปลงเลขอารบิกเป็นเลขไทย => 1 > ๑
	 */
	public static String getThaiNumber(String value) {
		String result = null;
		try {
			if (value != null) {
				result = value.replaceAll("1", "๑").replaceAll("2", "๒").replaceAll("3", "๓").replaceAll("4", "๔").replaceAll("5", "๕").replaceAll("6", "๖").replaceAll("7", "๗")
						.replaceAll("8", "๘").replaceAll("9", "๙").replaceAll("0", "๐");
			}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	public static Object[] getListOperatorIdFromListOperator(List<Operator> listOperator) {
		Map<String, String> mapOperator = new LinkedHashMap<String, String>();
		if (listOperator != null) {
			for (Operator operator : listOperator) {
				String[] parentIdArray = operator.getParentIds().split(MenuTreeUtil.DELIMITER_FUNCTION);
				if (operator.getDeleteFlag().equals(GlobalVariable.FLAG_DELETED)) {
					continue;
				}
				for (String id : parentIdArray) {
					mapOperator.put(id, id);
				}
			}
		}
		return (Object[]) (mapOperator.values().toArray());
	}
	
	public static List<Operator> generateOperatorResult(Map<String, Operator> mapProgram) {
		Object[] operators = (Object[]) (mapProgram.values().toArray());

		Map<String, Operator> mapMenuLevel = new LinkedHashMap<String, Operator>();
		if (operators.length > 0) {
			int minLevel = ((Operator) operators[0]).getMinLevel();
			int maxLevel = ((Operator) operators[0]).getMaxLevel();
			for (int currentLevel = minLevel; currentLevel <= maxLevel; currentLevel++) {
				for (int index = 0; index <= operators.length - 1; index++) {
					Operator operator = (Operator) operators[index];

					if (operator.getCurrentLevel() == currentLevel) {
						if (operator.getCurrentLevel() == 1) {
							mapMenuLevel.put(operator.getOperatorId(), operator);
						} else {
							String parentOperatorIds = searchParentOperator(operator.getParentId(), operators);
							parentOperatorIds += MenuUtil.DELIMITER_FUNCTION + operator.getOperatorId();
							if (operator.getOperatorType().equals(MenuUtil.OPERATOR_TYPE_FUNCTION)) {
								operator.setParentIds(parentOperatorIds);
								String fullPath = MenuUtil.searchLabel(mapProgram, operator.getCurrentId());
								operator.setSystemName(fullPath.substring(0, fullPath.indexOf(HTML_BR)));
								// LogUtil.SEC.debug("fullPath: " + fullPath);
								if (fullPath.lastIndexOf(HTML_RSAQUO) > -1) {
									operator.setMenuName(fullPath.substring(fullPath.indexOf(HTML_BR) + HTML_BR.length(), fullPath.lastIndexOf(HTML_RSAQUO)));
									operator.setFunctionName(fullPath.substring(fullPath.lastIndexOf(HTML_RSAQUO) + HTML_RSAQUO.length(), fullPath.length()));
								} else {
									operator.setMenuName("");
									operator.setFunctionName(fullPath.substring(fullPath.lastIndexOf(HTML_BR) + HTML_BR.length(), fullPath.length()));
								}
							}
							updateOperator(mapProgram, mapMenuLevel, parentOperatorIds, parentOperatorIds);
						}
					}
				}
			}
		}

		return convertMapOperatorToListOperator(mapMenuLevel);
	}

	private static List<Operator> convertMapOperatorToListOperator(Map<String, Operator> mapOperator) {

		List<Operator> listOperator = new ArrayList<Operator>();
		for (String key : mapOperator.keySet()) {
			if (mapOperator.get(key).getOperatorType().equals(MenuUtil.OPERATOR_TYPE_FUNCTION)) {
				Operator operator = mapOperator.get(key);
				operator.setParentIds(operator.getParentIds().replaceAll(MenuUtil.DELIMITER_FUNCTION, MenuTreeUtil.DELIMITER_FUNCTION));
				listOperator.add(operator);
			}
			if (mapOperator.get(key).getMapOperator().size() > 0) {
				listOperator.addAll(convertMapOperatorToListOperator(mapOperator.get(key).getMapOperator()));
			}
		}
		return listOperator;
	}

	private static void updateOperator(Map<String, Operator> mapMenu, Map<String, Operator> mapMenuLevel, String parentIds, String groupParentIds) {
		if (parentIds.indexOf(MenuUtil.DELIMITER_FUNCTION) > -1) {
			String[] operatorIds = parentIds.split(MenuUtil.DELIMITER_FUNCTION);
			String parentOperatorId = operatorIds[0];
			String currentOperatorId = operatorIds[1];
			String groupOperatorId = groupParentIds.substring(0, groupParentIds.indexOf(currentOperatorId) + currentOperatorId.length());

			Operator operator = mapMenu.get(currentOperatorId);
			operator.setParentOperatorIds(groupOperatorId);
			if (mapMenuLevel.get(parentOperatorId) != null) {
				mapMenuLevel.get(parentOperatorId).getMapOperator().put(operator.getOperatorId(), operator);
			}

			parentIds = parentIds.substring(parentIds.indexOf(MenuUtil.DELIMITER_FUNCTION) + MenuUtil.DELIMITER_FUNCTION.length(), parentIds.length());

			if (mapMenuLevel.get(parentOperatorId) != null) {
				updateOperator(mapMenu, mapMenuLevel.get(parentOperatorId).getMapOperator(), parentIds, groupParentIds);
			}
		}
	}

	private static String searchParentOperator(String parentId, Object[] listOperator) {
		String parentIds = "";

		if ((parentId != null) && (parentId.trim().length() > 0)) {

			for (Object operatorObject : listOperator) {

				Operator operator = (Operator) operatorObject;

				if (operator.getOperatorId().equals(parentId)) {
					parentIds = parentIds + MenuUtil.DELIMITER_FUNCTION;
					parentIds = operator.getOperatorId() + parentIds;
					String pppId = searchParentOperator(operator.getParentId(), listOperator);
					if (pppId.length() > 0) {
						parentIds = pppId + MenuUtil.DELIMITER_FUNCTION + parentIds;
					}
				}
			}
			if (parentIds.length() > 0) {
				parentIds = parentIds.substring(0, parentIds.length() - 1);
			}

		}
		return parentIds;
	}

	/**
	 * converse form 2558 >> 2015
	 * 
	 * @param engYYYY
	 * @return thaiYYYY
	 */
	public static String converseYearYYYY(String formatYYYY) throws Exception {
		String yyyy = "";
		yyyy = CalendarUtil.convertDateString(formatYYYY, "YYYY", ParameterConfig.getParameter().getApplication().getDatetimeLocale(), "YYYY", ParameterConfig.getParameter()
				.getApplication().getDatabaseLocale());
		return yyyy;
	}

	/**
	 * converse form 2016 >> 2559 (ตาม Locale)
	 * 
	 * @param engYYYY
	 * @return thaiYYYY
	 */
	public static String converseYear(String formatYYYY, Locale locale) throws Exception {
		String yyyy = "";
		yyyy = CalendarUtil.convertDateString(formatYYYY, "YYYY", ParameterConfig.getParameter().getApplication().getDatabaseLocale(), "YYYY", locale);
		return yyyy;
	}

	public static String currentDateTest() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String passwordEncrypt(String password) throws Exception {
		AESCipherHybridService aes = new AESCipherHybridService("Isb5Plse$%!dsl0[sd{sd,xpW>ztF.;e");
		return aes.encrypt(password);
	}

	public static String passwordDecrypt(String password) throws Exception {
		AESCipherHybridService aes = new AESCipherHybridService("Isb5Plse$%!dsl0[sd{sd,xpW>ztF.;e");
		return aes.decrypt(password);
	}

	/**
	 * ONE-Way encrypt (decrypt ไม่ได้)
	 * 
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static String passwordEncryptOneWay(String password) throws Exception {
		return EncryptionUtil.doEncrypt(password, EncType.SHA256);
	}

	/**
	 * ดึงค่าเดือน เช่น ม.ค., ก.พ ...
	 * 
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public static String getMonthMMName(String month) throws Exception {
		String monthReturn = "";
		try {

			ResourceBundle bundle = BundleUtil.getBundle("resources.bundle.common.Message", ParameterConfig.getParameter().getApplication().getApplicationLocale());
			int numMonth = Integer.parseInt(month);
			switch (numMonth) {
			case 1:
				monthReturn = bundle.getString("mon01");
				break;
			case 2:
				monthReturn = bundle.getString("mon02");
				break;
			case 3:
				monthReturn = bundle.getString("mon03");
				break;
			case 4:
				monthReturn = bundle.getString("mon04");
				break;
			case 5:
				monthReturn = bundle.getString("mon05");
				break;
			case 6:
				monthReturn = bundle.getString("mon06");
				break;
			case 7:
				monthReturn = bundle.getString("mon07");
				break;
			case 8:
				monthReturn = bundle.getString("mon08");
				break;
			case 9:
				monthReturn = bundle.getString("mon09");
				break;
			case 10:
				monthReturn = bundle.getString("mon10");
				break;
			case 11:
				monthReturn = bundle.getString("mon11");
				break;
			case 12:
				monthReturn = bundle.getString("mon12");
				break;

			default:
				break;
			}

		} catch (Exception e) {
			throw e;
		}
		return monthReturn;
	}

	/*
	 * dateCompare คือ วันที่ - ย้อนหลัง x วัน dateCheck คือ
	 * ตัวที่นำมาเปรียบเทียบ (วันที่ file) เช่น return true หมายถึง dateCheck
	 * น้อยกว่าเท่ากับ dateCompare
	 */
	public static boolean compareLessEqualDate(String dateCheck) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
		Date dd = new Date();
		Calendar cc = Calendar.getInstance(Locale.ENGLISH);
		cc.setTime(dd);
		cc.add(Calendar.DATE, -2);
		dd.setTime(cc.getTime().getTime());

		String dateCompare = sdf.format(dd);

		return Integer.parseInt(dateCheck) <= Integer.parseInt(dateCompare);

	}

	/*
	 * dateCompare คือ วันที่ - ย้อนหลัง x วัน dateCheck คือ
	 * ตัวที่นำมาเปรียบเทียบ (วันที่ file) เช่น return true หมายถึง dateCheck
	 * น้อยกว่าเท่ากับ dateCompare
	 */
	public static void checkFileForDelete(String pathFile, String fileName) throws Exception {

		File[] files = FileManagerUtil.listFiles(pathFile, fileName + "*.xlsx");

		for (File f : files) {
			String ff = f.getName();
			String fff[] = ff.split("_");
			if (fff.length > 1) {

				String fDate = fff[1].substring(0, 8);

				if (TRNUtil.compareLessEqualDate(fDate)) {
					FileManagerUtil.deleteQuietly(f);
				}
			}// end if length
		}// end for

	}
	
	/**
	 * Validate ค่าว่าง
	 * @param object
	 * @param locale
	 * @throws UCPValidateException
	 */
	public static void checkRequire(String object,Locale locale) throws UCPValidateException {
		ResourceBundle bundle = null;
		try {
			bundle = BundleUtil.getBundle("resources.bundle.common.MessageAlert", locale);
		} catch (Exception e) {
			LogUtil.UTIL.error("",e);
		}
		if(object==null||object.trim().equalsIgnoreCase("")){
			throw new UCPValidateException(bundle.getString("10003").replaceAll("xxx", ""));
		}
	}
	
	/**
	 * Validate userId format
	 * @param userId
	 * @param locale
	 * @throws UCPValidateException
	 */
	public static void checkUserId(String userId, Locale locale) throws UCPValidateException {
		ResourceBundle bundle = null;
		try {
			bundle = BundleUtil.getBundle("resources.bundle.common.MessageAlert", locale);
		} catch (Exception e) {
			LogUtil.UTIL.error("",e);
		}
		boolean invalid = true;
		String patternUserId = Validation.MAP_OF_VALIDATION_FORMAT.get(Validation.ValidationAttr.USER_ID.getAttr());
		invalid = userId.matches(patternUserId);
		if(!invalid){
			throw new UCPValidateException(bundle.getString("10004").replaceAll("xxx", ""));
		}
	}
	
	/**
	 * Validate familyName format
	 * @param familyName
	 * @param locale
	 * @throws UCPValidateException
	 */
	public static void checkFamilyName(String familyName, Locale locale) throws UCPValidateException {
		ResourceBundle bundle = null;
		try {
			bundle = BundleUtil.getBundle("resources.bundle.common.MessageAlert", locale);
		} catch (Exception e) {
			LogUtil.UTIL.error("",e);
		}
		boolean invalid = true;
		String patternFamilyName = Validation.MAP_OF_VALIDATION_FORMAT.get(Validation.ValidationAttr.FAMILY_NAME_USER.getAttr());
		invalid = familyName.matches(patternFamilyName);
		if(!invalid){
			throw new UCPValidateException(bundle.getString("10004").replaceAll("xxx", ""));
		}
	}

	/**
	 * Validate givenName format
	 * @param givenName
	 * @param locale
	 * @throws UCPValidateException
	 */
	public static void checkGivenName(String givenName, Locale locale) throws UCPValidateException {
		ResourceBundle bundle = null;
		try {
			bundle = BundleUtil.getBundle("resources.bundle.common.MessageAlert", locale);
		} catch (Exception e) {
			LogUtil.UTIL.error("",e);
		}
		boolean invalid = true;
		String pattennGivenName = Validation.MAP_OF_VALIDATION_FORMAT.get(Validation.ValidationAttr.GIVEN_NAME_USER.getAttr());
		invalid = givenName.matches(pattennGivenName);
		if(!invalid){
			throw new UCPValidateException(bundle.getString("10004").replaceAll("xxx", ""));
		}
	}
	
	/**
	 * Validate emailAddress format
	 * @param emailAddress
	 * @param locale
	 * @throws UCPValidateException
	 */
	public static void checkEmailAddress(String emailAddress, Locale locale) throws UCPValidateException{
		ResourceBundle bundle = null;
		try {
			bundle = BundleUtil.getBundle("resources.bundle.common.MessageAlert", locale);
		} catch (Exception e) {
			LogUtil.UTIL.error("",e);
		}
		String patternEmail = Validation.MAP_OF_VALIDATION_FORMAT.get(Validation.ValidationAttr.EMAIL.getAttr());

		boolean invalid = true;
		invalid = emailAddress.matches(patternEmail);
		if(!invalid){
			throw new UCPValidateException(bundle.getString("10004").replaceAll("xxx", ""));
		}
	}
	
	public static void checkCarrierCode(String carrierCode, Locale locale) throws UCPValidateException {
		ResourceBundle bundle = null;
		try {
			bundle = BundleUtil.getBundle("resources.bundle.common.MessageAlert", locale);
		} catch (Exception e) {
			LogUtil.UTIL.error("",e);;
		}
		boolean invalid = true;;
		String patternCarrierCode = Validation.MAP_OF_VALIDATION_FORMAT.get(Validation.ValidationAttr.CARRIER_CODE.getAttr());
		invalid = carrierCode.matches(patternCarrierCode);
		if(!invalid){
			throw new UCPValidateException(bundle.getString("10004").replaceAll("xxx", ""));
		}
	}
	
	/**
	 * Validate telephone contact format
	 * @param telephone
	 * @param locale
	 * @throws UCPValidateException
	 */
	public static void checkTelephoneContact(String telephone, Locale locale) throws UCPValidateException {
		ResourceBundle bundle = null;
		try {
			bundle = BundleUtil.getBundle("resources.bundle.common.MessageAlert", locale);
		} catch (Exception e) {
			LogUtil.UTIL.error("",e);
		}
		boolean invalid = true;
		String patternTelephone = Validation.MAP_OF_VALIDATION_FORMAT.get(Validation.ValidationAttr.TELEPHONE_CONTACT.getAttr());
		invalid = telephone.matches(patternTelephone);
		if(!invalid){
			throw new UCPValidateException(bundle.getString("10004").replaceAll("xxx", ""));
		}
	}
	
	/**
	 * Validate ext. format
	 * @param telephone
	 * @param locale
	 * @throws UCPValidateException
	 */
	public static void checkExt(String ext, Locale locale) throws UCPValidateException {
		ResourceBundle bundle = null;
		try {
			bundle = BundleUtil.getBundle("resources.bundle.common.MessageAlert", locale);
		} catch (Exception e) {
			LogUtil.UTIL.error("",e);
		}
		boolean invalid = true;
		String patternExt = Validation.MAP_OF_VALIDATION_FORMAT.get(Validation.ValidationAttr.EXT.getAttr());
		invalid = ext.matches(patternExt);
		if(!invalid){
			throw new UCPValidateException(bundle.getString("10004").replaceAll("xxx", ""));
		}
	}
	
	/**
	 * Validate fax format
	 * @param telephone
	 * @param locale
	 * @throws UCPValidateException
	 */
	public static void checkFax(String fax, Locale locale) throws UCPValidateException {
		ResourceBundle bundle = null;
		try {
			bundle = BundleUtil.getBundle("resources.bundle.common.MessageAlert", locale);
		} catch (Exception e) {
			LogUtil.UTIL.error("",e);
		}
		boolean invalid = true;
		String patternFax = Validation.MAP_OF_VALIDATION_FORMAT.get(Validation.ValidationAttr.FACSIMILE_NUMBER.getAttr());
		invalid = fax.matches(patternFax);
		if(!invalid){
			throw new UCPValidateException(bundle.getString("10004").replaceAll("xxx", ""));
		}
	}
	
	/**
	 * Validate carrier code (Air, General Aviation)
	 * @param carrierType
	 * @param carrierCode
	 * @param locale
	 * @throws UCPValidateException
	 */
	public static void checkCarrierCode(String carrierType, String carrierCode, Locale locale) throws UCPValidateException {
		ResourceBundle bundle = null;
		try {
			bundle = BundleUtil.getBundle("resources.bundle.common.MessageAlert", locale);
		} catch (Exception e) {
			LogUtil.UTIL.error("",e);;
		}
		
		boolean invalid = true;
		if (carrierType != null) {
			int minLength = 0;
			int maxLength = 0;
			
			if (carrierType.equalsIgnoreCase(GlobalVariable.CarrierType.AIR.getValue())) {
				minLength = Integer.valueOf(Validation.MAP_OF_VALIDATION_LENGTH.get(Validation.ValidationLength.MIN_AIR_CARRIER_CODE.getAttr()));
				maxLength = Integer.valueOf(Validation.MAP_OF_VALIDATION_LENGTH.get(Validation.ValidationLength.MAX_AIR_CARRIER_CODE.getAttr()));
				
			} else if (carrierType.equalsIgnoreCase(GlobalVariable.CarrierType.GENERAL_AVIATION.getValue())) {
				minLength = Integer.valueOf(Validation.MAP_OF_VALIDATION_LENGTH.get(Validation.ValidationLength.MIN_GENERAL_AVIATION_CARRIER_CODE.getAttr()));
				maxLength = Integer.valueOf(Validation.MAP_OF_VALIDATION_LENGTH.get(Validation.ValidationLength.MAX_GENERAL_AVIATION_CARRIER_CODE.getAttr()));
				
			} else {
				
			}
			
			// Validate carrier code length
			if (carrierCode.length() < minLength || carrierCode.length() > maxLength) {
				invalid = false;
			}
		}
		
		if (invalid) {
			// Validate carrier code format
			String patternCarrierCode = Validation.MAP_OF_VALIDATION_FORMAT.get(Validation.ValidationAttr.CARRIER_CODE.getAttr());
			invalid = carrierCode.matches(patternCarrierCode);
		}
		
		if(!invalid){
			throw new UCPValidateException(bundle.getString("10004").replaceAll("xxx", ""));
		}
	}
	
	/**
	 * Validate airport code IATA format
	 * @param airportCode
	 * @param locale
	 * @throws UCPValidateException
	 */
	public static void checkAirportCodeIata(String airportCode, Locale locale) throws UCPValidateException {
		ResourceBundle bundle = null;
		try {
			bundle = BundleUtil.getBundle("resources.bundle.common.MessageAlert", locale);
		} catch (Exception e) {
			LogUtil.UTIL.error("",e);
		}
		boolean invalid = true;
		String patternUserId = Validation.MAP_OF_VALIDATION_FORMAT.get(Validation.ValidationAttr.AIRPORT_CODE_IATA.getAttr());
		invalid = airportCode.matches(patternUserId);
		if(!invalid){
			throw new UCPValidateException(bundle.getString("10004").replaceAll("xxx", ""));
		}
	}
	
	/**
	 * Validate number. format
	 * @param inputNumber
	 * @param locale
	 * @throws UCPValidateException
	 */
	public static void checkNumber(String inputNumber, Locale locale) throws UCPValidateException {
		ResourceBundle bundle = null;
		try {
			bundle = BundleUtil.getBundle("resources.bundle.common.MessageAlert", locale);
		} catch (Exception e) {
			LogUtil.UTIL.error("",e);
		}
		boolean invalid = true;
		String patternNumber = Validation.MAP_OF_VALIDATION_FORMAT.get(Validation.ValidationAttr.INPUT_NUMBER.getAttr());
		invalid = inputNumber.matches(patternNumber);
		if(!invalid){
			throw new UCPValidateException(bundle.getString("30067"));
		}
	}
}
