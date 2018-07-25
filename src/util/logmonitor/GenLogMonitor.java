package util.logmonitor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import util.string.StringUtil;

public class GenLogMonitor {

	public static String fileName = "logMonitor.txt";
	public static DateFormat formatDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
	public static DateFormat formatBackupLog = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.ENGLISH);
	// static double maxFileSize = 20; // MB
	static double maxFileSize = 5; // MB

	public static void writeLogFileBG(String pathLogFileMonitor, String dateStartService, String dateEndService, String processName) throws Exception {

		try {
			File folder = new File(pathLogFileMonitor);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			FileWriter fstream = null;
			File file = new File(pathLogFileMonitor + fileName);
			if (file.exists()) {
				double bytes = file.length();
				double kilobytes = (bytes / 1024);
				double megabytes = (kilobytes / 1024);

				if (megabytes > maxFileSize) {
					/*Calendar cal = Calendar.getInstance(Locale.ENGLISH);
					String dateTime = formatBackupLog.format(cal.getTime());
					File newFileName = new File(pathLogFileMonitor + "logMonitor_" + dateTime + ".txt");
					file.renameTo(newFileName);*/
					fstream = new FileWriter(pathLogFileMonitor + fileName);
				} else {
					fstream = new FileWriter(pathLogFileMonitor + fileName, true);
				}
			} else {
				fstream = new FileWriter(pathLogFileMonitor + fileName, true);
			}

			BufferedWriter out = new BufferedWriter(fstream);
			// Thread1|31/08/2015 14:39:17|end|31/08/2015 14:40:17|start
			out.write(processName + "|" + dateEndService + "|end|" + dateStartService + "|start" + "\r\n");

			// Close the output stream
			out.close();
		} catch (Exception e) {// Catch exception if any

		}
	}

	public static void writeLogFileTrigger(String pathLogFileMonitor, String step, String msg) throws Exception {

		Calendar cal = Calendar.getInstance(Locale.ENGLISH);
		String currentDateTime = formatDateTime.format(cal.getTime());
		try {
			File folder = new File(pathLogFileMonitor);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			FileWriter fstream = null;
			File file = new File(pathLogFileMonitor + fileName);
			if (file.exists()) {
				double bytes = file.length();
				double kilobytes = (bytes / 1024);
				double megabytes = (kilobytes / 1024);

				if (megabytes > maxFileSize) {
					/*String dateTime = formatBackupLog.format(cal.getTime());
					File newFileName = new File(pathLogFileMonitor + "logMonitor_" + dateTime + ".txt");
					file.renameTo(newFileName);*/
					fstream = new FileWriter(pathLogFileMonitor + fileName);
				} else {
					fstream = new FileWriter(pathLogFileMonitor + fileName, true);
				}
			} else {
				fstream = new FileWriter(pathLogFileMonitor + fileName, true);
			}

			BufferedWriter out = new BufferedWriter(fstream);
			// Trigger|31/08/2015 14:40:17|<Trigger Name> ^ Disconnected Queue
			// LOADTEST.OUT
			if (StringUtil.stringToNull(msg) == null) {
				out.write("Trigger|" + currentDateTime + "|" + step + "\r\n");
			} else {
				out.write("Error|" + currentDateTime + "|" + msg + "\r\n");
			}

			// Close the output stream
			out.close();
		} catch (Exception e) {// Catch exception if any

		}
	}

	public static void writeLogFileSchedule(String pathLogFileMonitor, String msg) throws Exception {

		Calendar cal = Calendar.getInstance(Locale.ENGLISH);
		String currentDateTime = formatDateTime.format(cal.getTime());
		try {
			File folder = new File(pathLogFileMonitor);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			FileWriter fstream = null;
			File file = new File(pathLogFileMonitor + fileName);
			if (file.exists()) {
				double bytes = file.length();
				double kilobytes = (bytes / 1024);
				double megabytes = (kilobytes / 1024);

				if (megabytes > maxFileSize) {
					/*String dateTime = formatBackupLog.format(cal.getTime());
					File newFileName = new File(pathLogFileMonitor + "logMonitor_" + dateTime + ".txt");
					file.renameTo(newFileName);*/
					fstream = new FileWriter(pathLogFileMonitor + fileName);
				} else {
					fstream = new FileWriter(pathLogFileMonitor + fileName, true);
				}
			} else {
				fstream = new FileWriter(pathLogFileMonitor + fileName, true);
			}

			BufferedWriter out = new BufferedWriter(fstream);
			out.write("Schedule|" + currentDateTime + "|" + msg + "\r\n");

			// Close the output stream
			out.close();
		} catch (Exception e) {// Catch exception if any

		}
	}
	
	public static void writeLogFileBGMultiThread(String pathLogFileMonitor, String dateStartService, String dateEndService, String threadName) throws Exception {

		try {
			File folder = new File(pathLogFileMonitor);
			if (!folder.exists()) {
				folder.mkdirs();
			}

			FileWriter fstream = null;
			File file = new File(pathLogFileMonitor + threadName + ".txt");
			if (file.exists()) {
				double bytes = file.length();
				double kilobytes = (bytes / 1024);
				double megabytes = (kilobytes / 1024);

				if (megabytes > maxFileSize) {
					/*Calendar cal = Calendar.getInstance(Locale.ENGLISH);
					String dateTime = formatBackupLog.format(cal.getTime());
					File newFileName = new File(pathLogFileMonitor + "logMonitor_" + dateTime + ".txt");
					file.renameTo(newFileName);*/
					fstream = new FileWriter(pathLogFileMonitor + threadName + ".txt");
				} else {
					fstream = new FileWriter(pathLogFileMonitor + threadName + ".txt", true);
				}
			} else {
				fstream = new FileWriter(pathLogFileMonitor + threadName + ".txt", true);
			}

			BufferedWriter out = new BufferedWriter(fstream);
			// Thread1|31/08/2015 14:39:17|end|31/08/2015 14:40:17|start
			out.write(threadName + "|" + dateEndService + "|end|" + dateStartService + "|start" + "\r\n");

			// Close the output stream
			out.close();
		} catch (Exception e) {// Catch exception if any

		}
	}
	
}
