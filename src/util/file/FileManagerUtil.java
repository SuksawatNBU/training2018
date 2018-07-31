package util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import util.log.LogUtil;

public class FileManagerUtil {

	public static final boolean RECURSIVE_ON = true;
	public static final boolean RECURSIVE_OFF = false;
	public static final boolean CASE_SENSITIVE_ON = true;
	public static final boolean CASE_SENSITIVE_OFF = false;
	public static final boolean APPEND_ON = true;
	public static final boolean APPEND_OFF = false;
	public static final boolean CREATE_DIRECTORY_ON = true;
	public static final boolean CREATE_DIRECTORY_OFF = false;

	public static final String DELIMITER_BACKSLASH = "\\";
	public static final String DELIMITER_SLASH = "/";
	public static final String DELIMITER_NEWLINE = "\n";
	public static final String DELIMITER_DOT = ".";
	public static final String DELIMITER_GRAVE_ACCENT = "~";

	public static final String EXTENSION_TMP = "tmp";
	public static final String EXTENSION_ZIP = "zip";

	private static Map<String, Double> EXTRA_PERCENTS = new HashMap<String, Double>();

	private static long SIZE_OF_LARGE = 2000000000;

	/**
	 * Reads the contents of a file line by line to a List of Strings using the default encoding for the VM. The file is always closed.
	 *
	 * @param filePath
	 *            - the path of file to read, must not be null
	 * @throws IOException
	 *             in case of an I/O erro
	 */
	public static List<String> readDatas(String filePath) throws Exception {
		List<String> datas = new ArrayList<String>();
		try {
			File file = FileUtils.getFile(filePath);

			datas = readDatas(file);
		} catch (Exception e) {
			throw e;
		}
		return datas;
	}

	/**
	 * Reads the contents of a file line by line to a List of Strings using the default encoding for the VM. The file is always closed.
	 *
	 * @param file
	 *            - the file to read, must not be null
	 * @throws IOException
	 *             in case of an I/O erro
	 */
	public static List<String> readDatas(File file) throws Exception {
		List<String> datas = new ArrayList<String>();
		try {
			datas = FileUtils.readLines(file);
		} catch (Exception e) {
			throw e;
		}
		return datas;
	}

	/**
	 * Reads the contents of a file line by line to a List of Strings. The file is always closed.
	 *
	 * @param filePath
	 *            - the path of file to read, must not be null
	 * @param charset
	 *            - the encoding to use, null means platform default
	 * @return the list of Strings representing each line in the file, never null
	 * @throws IOException
	 *             in case of an I/O erro
	 */
	public static List<String> readDatas(String filePath, Charset charset) throws Exception {
		List<String> datas = new ArrayList<String>();
		try {
			File file = FileUtils.getFile(filePath);

			datas = readDatas(file, charset);
		} catch (Exception e) {
			throw e;
		}
		return datas;
	}

	/**
	 * Reads the contents of a file line by line to a List of Strings. The file is always closed.
	 *
	 * @param file
	 *            - the file to read, must not be null
	 * @param charset
	 *            - the encoding to use, null means platform default
	 * @return the list of Strings representing each line in the file, never null
	 * @throws IOException
	 *             in case of an I/O erro
	 */
	public static List<String> readDatas(File file, Charset charset) throws Exception {
		List<String> datas = new ArrayList<String>();
		try {
			datas = FileUtils.readLines(file, charset);
		} catch (Exception e) {
			throw e;
		}
		return datas;
	}

	/**
	 * Reads the contents of a file line by line to a List of Strings. The file is always closed.
	 *
	 * @param filePath
	 *            - the path of file to read, must not be null
	 * @param encoding
	 *            - the encoding to use, null means platform default
	 * @return the list of Strings representing each line in the file, never null
	 * @throws IOException
	 *             in case of an I/O error
	 * @throws UnsupportedCharsetException
	 *             thrown instead of UnsupportedEncodingException.
	 */
	public static List<String> readDatas(String filePath, String encoding) throws Exception {
		List<String> datas = new ArrayList<String>();
		try {
			File file = FileUtils.getFile(filePath);

			datas = readDatas(file, encoding);
		} catch (Exception e) {
			throw e;
		}
		return datas;
	}

	/**
	 * Reads the contents of a file line by line to a List of Strings. The file is always closed.
	 *
	 * @param file
	 *            - the file to read, must not be null
	 * @param encoding
	 *            - the encoding to use, null means platform default
	 * @return the list of Strings representing each line in the file, never null
	 * @throws IOException
	 *             in case of an I/O error
	 * @throws UnsupportedCharsetException
	 *             thrown instead of UnsupportedEncodingException.
	 */
	public static List<String> readDatas(File file, String encoding) throws Exception {
		List<String> datas = new ArrayList<String>();
		try {
			datas = FileUtils.readLines(file, encoding);
		} catch (Exception e) {
			throw e;
		}
		return datas;
	}

	/**
	 * Writes a String to a file creating the file if it does not exist using the default encoding for the VM.
	 *
	 * @param filePath
	 *            - the path of file to write
	 * @param data
	 *            - the content to write to the file
	 * @param append
	 *            - if true, then the String will be added to the end of the file rather than overwriting
	 * @return the list of Strings representing each line in the file, never null
	 * @throws IOException
	 *             in case of an I/O error
	 */
	public static void writeData(String filePath, String data, boolean append) throws Exception {
		try {
			File file = FileUtils.getFile(filePath);

			writeData(file, data, append);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Writes a String to a file creating the file if it does not exist using the default encoding for the VM.
	 *
	 * @param file
	 *            - the file to write
	 * @param data
	 *            - the content to write to the file
	 * @param append
	 *            - if true, then the String will be added to the end of the file rather than overwriting
	 * @return the list of Strings representing each line in the file, never null
	 * @throws IOException
	 *             in case of an I/O error
	 */
	public static void writeData(File file, String data, boolean append) throws Exception {
		try {
			if (append == APPEND_ON) {
				FileUtils.writeStringToFile(file, data, append);
			} else {
				FileUtils.writeStringToFile(file, data);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Writes a String to a file creating the file if it does not exist using the default encoding for the VM.
	 *
	 * @param filePath
	 *            - the path of file to write
	 * @param data
	 *            - the content to write to the file
	 * @param charset
	 *            - the encoding to use, null means platform default
	 * @param append
	 *            - if true, then the String will be added to the end of the file rather than overwriting
	 * @return the list of Strings representing each line in the file, never null
	 * @throws IOException
	 *             in case of an I/O error
	 * @throws UnsupportedEncodingException
	 *             if the encoding is not supported by the VM
	 */
	public static void writeData(String filePath, String data, Charset charset, boolean append) throws Exception {
		try {
			File file = FileUtils.getFile(filePath);

			writeData(file, data, charset, append);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Writes a String to a file creating the file if it does not exist using the default encoding for the VM.
	 *
	 * @param file
	 *            - the file to write
	 * @param data
	 *            - the content to write to the file
	 * @param charset
	 *            - the encoding to use, null means platform default
	 * @param append
	 *            - if true, then the String will be added to the end of the file rather than overwriting
	 * @return the list of Strings representing each line in the file, never null
	 * @throws IOException
	 *             in case of an I/O error
	 * @throws UnsupportedEncodingException
	 *             if the encoding is not supported by the VM
	 */
	public static void writeData(File file, String data, Charset charset, boolean append) throws Exception {
		try {
			if (append == APPEND_ON) {
				FileUtils.writeStringToFile(file, data, charset, append);
			} else {
				FileUtils.writeStringToFile(file, data, charset);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Writes a String to a file creating the file if it does not exist. NOTE: As from v1.3, the parent directories of the file will be created if they do not exist.
	 *
	 * @param filePath
	 *            - the path of file to write
	 * @param data
	 *            - the content to write to the file
	 * @param encoding
	 *            - the encoding to use, null means platform default
	 * @param append
	 *            - if true, then the String will be added to the end of the file rather than overwriting
	 * @return the list of Strings representing each line in the file, never null
	 * @throws IOException
	 *             in case of an I/O error
	 * @throws UnsupportedEncodingException
	 *             if the encoding is not supported by the VM
	 */
	public static void writeData(String filePath, String data, String encoding, boolean append) throws Exception {
		try {
			File file = FileUtils.getFile(filePath);

			writeData(file, data, encoding, append);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Writes a String to a file creating the file if it does not exist. NOTE: As from v1.3, the parent directories of the file will be created if they do not exist.
	 *
	 * @param file
	 *            - the file to write
	 * @param data
	 *            - the content to write to the file
	 * @param encoding
	 *            - the encoding to use, null means platform default
	 * @param append
	 *            - if true, then the String will be added to the end of the file rather than overwriting
	 * @return the list of Strings representing each line in the file, never null
	 * @throws IOException
	 *             in case of an I/O error
	 * @throws UnsupportedEncodingException
	 *             if the encoding is not supported by the VM
	 */
	public static void writeData(File file, String data, String encoding, boolean append) throws Exception {
		try {
			if (append == APPEND_ON) {
				FileUtils.writeStringToFile(file, data, encoding, append);
			} else {
				FileUtils.writeStringToFile(file, data, encoding);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Writes the toString() value of each item in a collection to the specified File line by line. The default VM encoding and the default line ending will be used.
	 *
	 * @param filePath
	 *            - the path of file to write
	 * @param lines
	 *            - the lines to write, null entries produce blank lines
	 * @param append
	 *            - if true, then the String will be added to the end of the file rather than overwriting
	 * @return the list of Strings representing each line in the file, never null
	 * @throws IOException
	 *             in case of an I/O error
	 */
	public static void writeLines(String filePath, List<String> lines, boolean append) throws Exception {
		try {
			File file = FileUtils.getFile(filePath);

			writeLines(file, lines, append);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Writes the toString() value of each item in a collection to the specified File line by line. The default VM encoding and the default line ending will be used.
	 *
	 * @param file
	 *            - the file to write
	 * @param lines
	 *            - the lines to write, null entries produce blank lines
	 * @param append
	 *            - if true, then the String will be added to the end of the file rather than overwriting
	 * @return the list of Strings representing each line in the file, never null
	 * @throws IOException
	 *             in case of an I/O error
	 */
	public static void writeLines(File file, List<String> lines, boolean append) throws Exception {
		try {
			if (append == APPEND_ON) {
				FileUtils.writeLines(file, lines, append);
			} else {
				FileUtils.writeLines(file, lines);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Writes the toString() value of each item in a collection to the specified File line by line. The specified character encoding and the default line ending will be used. NOTE: As from v1.3, the
	 * parent directories of the file will be created if they do not exist.
	 *
	 * @param filePath
	 *            - the path of file to write
	 * @param lines
	 *            - the lines to write, null entries produce blank lines
	 * @param encoding
	 *            - the encoding to use, null means platform default
	 * @param append
	 *            - if true, then the String will be added to the end of the file rather than overwriting
	 * @return the list of Strings representing each line in the file, never null
	 * @throws IOException
	 *             in case of an I/O error
	 * @throws UnsupportedEncodingException
	 *             if the encoding is not supported by the VM
	 */
	public static void writeLines(String filePath, List<String> lines, String encoding, boolean append) throws Exception {
		try {
			File file = FileUtils.getFile(filePath);

			writeLines(file, lines, encoding, append);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Writes the toString() value of each item in a collection to the specified File line by line. The specified character encoding and the default line ending will be used. NOTE: As from v1.3, the
	 * parent directories of the file will be created if they do not exist.
	 *
	 * @param file
	 *            - the file to write
	 * @param lines
	 *            - the lines to write, null entries produce blank lines
	 * @param encoding
	 *            - the encoding to use, null means platform default
	 * @param append
	 *            - if true, then the String will be added to the end of the file rather than overwriting
	 * @return the list of Strings representing each line in the file, never null
	 * @throws IOException
	 *             in case of an I/O error
	 * @throws UnsupportedEncodingException
	 *             if the encoding is not supported by the VM
	 */
	public static void writeLines(File file, List<String> lines, String encoding, boolean append) throws Exception {
		try {
			if (append == APPEND_ON) {
				FileUtils.writeLines(file, encoding, lines, append);
			} else {
				FileUtils.writeLines(file, encoding, lines);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Writes bytes from a byte[] to an OutputStream.
	 *
	 * @param filePath
	 *            - the path of file to write
	 * @param datas
	 *            - the lines to write, null entries produce blank lines
	 * @throws IOException
	 *             in case of an I/O error
	 */
	public static void writeByteArrayToFile(String filePath, byte[] datas) throws Exception {
		try {
			File file = FileUtils.getFile(filePath);

			writeByteArrayToFile(file, datas);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Writes bytes from a byte[] to an OutputStream.
	 *
	 * @param file
	 *            - the file to write
	 * @param datas
	 *            - the lines to write, null entries produce blank lines
	 * @throws IOException
	 *             in case of an I/O error
	 */
	public static void writeByteArrayToFile(File file, byte[] datas) throws Exception {
		OutputStream output = null;
		try {
			output = new FileOutputStream(file);

			IOUtils.write(datas, output);
		} catch (Exception e) {
			throw e;
		} finally {
			if (output != null) {
				output.close();
			}
		}
	}

	/**
	 * Gets the contents of an file as a byte[].
	 *
	 * @param filePath
	 *            - the path of file to read
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             in case of an I/O error
	 */
	public static byte[] readFileToByteArray(String filePath) throws Exception {
		byte[] buffer = new byte[0];
		try {
			File file = FileUtils.getFile(filePath);

			buffer = readFileToByteArray(file);
		} catch (Exception e) {
			throw e;
		}
		return buffer;
	}

	/**
	 * Gets the contents of an file as a byte[].
	 *
	 * @param file
	 *            - the file to read
	 * @throws NullPointerException
	 *             if the input is null
	 * @throws IOException
	 *             in case of an I/O error
	 */
	public static byte[] readFileToByteArray(File file) throws Exception {
		InputStream input = null;
		byte[] buffer = new byte[0];
		try {
			input = new FileInputStream(file);

			buffer = IOUtils.toByteArray(input);
		} catch (Exception e) {
			throw e;
		} finally {
			if (input != null) {
				input.close();
			}
		}
		return buffer;
	}

	public static long copy(String srcFilePath, String destFilePath) throws Exception {
		long length;
		try {
			File srcFile = FileUtils.getFile(srcFilePath);
			File destFile = FileUtils.getFile(destFilePath);

			length = copy(srcFile, destFile);
		} catch (Exception e) {
			throw e;
		}
		return length;
	}

	/**
	 * Copies bytes from an InputStream to an OutputStream. This method buffers the input internally, so there is no need to use a BufferedInputStream.
	 *
	 * @param srcFile
	 *            - the source file to read
	 * @param destFile
	 *            - the destination file to write
	 * @return the number of bytes copied
	 * @throws NullPointerException
	 *             if the input or output is null
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public static long copy(File srcFile, File destFile) throws Exception {
		long length;
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			fileInputStream = new FileInputStream(srcFile);
			fileOutputStream = new FileOutputStream(destFile);

			if (FileUtils.sizeOf(srcFile) > SIZE_OF_LARGE) {
				length = IOUtils.copyLarge(fileInputStream, fileOutputStream);
			} else {
				length = IOUtils.copy(fileInputStream, fileOutputStream);
			}

			if (FileUtils.sizeOf(srcFile) != FileUtils.sizeOf(destFile)) {
				throw new Exception("Size file is not match!!");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		}
		return length;
	}

	/**
	 * Copies bytes from an InputStream to an OutputStream.
	 *
	 * @param srcFilePath
	 *            - the path of source file to read
	 * @param destFilePath
	 *            - the path of destination file to write
	 * @param buffer
	 *            - size of buffer for read and write
	 * @throws NullPointerException
	 *             if the input or output is null
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	public void copyExtra(String srcFilePath, String destFilePath, long buffer) throws Exception {
		File srcFile = FileUtils.getFile(srcFilePath);
		File destFile = FileUtils.getFile(destFilePath);
		long offset = 0;
		long size = FileUtils.sizeOf(srcFile);
		if (size < buffer) {
			buffer = size;
		}

		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			long newoffset;
			fileInputStream = new FileInputStream(srcFile);
			fileOutputStream = new FileOutputStream(destFile);

			while (size >= (offset + buffer)) {
				// System.out.println("size >= offset + buffer (total)");
				// System.out.print(size + " >= " + offset + " + " + buffer + " (" + (offset + buffer) + ")");

				IOUtils.copyLarge(fileInputStream, fileOutputStream, 0, buffer);
				newoffset = offset + buffer;
				if ((newoffset + buffer) > size) {
					buffer = (size - newoffset);
				}

				if (buffer == 0) {
					break;
				}
				offset = newoffset;
			}

			if (FileUtils.sizeOf(srcFile) != FileUtils.sizeOf(destFile)) {
				throw new Exception("Size file is not match!!");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		}
	}

	/**
	 * Copies a whole directory to a new location preserving the file dates with overwrite
	 *
	 * @param srcDirPath
	 *            - the path of source directory
	 * @param destDirPath
	 *            - the path of destination directory
	 * @throws NullPointerException
	 *             if the input or output is null
	 * @throws IOException
	 *             if an I/O error occurs, if an IO error occurs during copying
	 */
	public static void copyDirectoryWithOverwrite(String srcDirPath, String destDirPath) throws Exception {
		try {
			File srcDir = FileUtils.getFile(srcDirPath);
			File destDir = FileUtils.getFile(destDirPath);

			copyDirectoryWithOverwrite(srcDir, destDir);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Copies a whole directory to a new location preserving the file dates with overwrite
	 *
	 * @param srcDirPath
	 *            - the source directory
	 * @param destDirPath
	 *            - the destination directory
	 * @throws NullPointerException
	 *             if the input or output is null
	 * @throws IOException
	 *             if an I/O error occurs, if an IO error occurs during copying
	 */
	public static void copyDirectoryWithOverwrite(File srcDir, File destDir) throws Exception {
		try {
			FileUtils.deleteDirectory(destDir);
			FileUtils.copyDirectory(srcDir, destDir);

			if (FileUtils.sizeOf(srcDir) != FileUtils.sizeOf(destDir)) {
				throw new Exception("Size directory is not match!!");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Copies a whole directory to a new location preserving the file dates
	 *
	 * @param srcDirPath
	 *            - the path of source directory
	 * @param destDirPath
	 *            - the path of destination directory
	 * @throws NullPointerException
	 *             if the input or output is null
	 * @throws IOException
	 *             if an I/O error occurs, if an IO error occurs during copying
	 */
	public static void copyDirectoryWithoutOverwrite(String srcDirPath, String destDirPath) throws Exception {
		try {
			File srcDir = FileUtils.getFile(srcDirPath);
			File destDir = FileUtils.getFile(destDirPath);

			copyDirectoryWithoutOverwrite(srcDir, destDir);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Copies a whole directory to a new location preserving the file dates
	 *
	 * @param srcDir
	 *            - the source directory
	 * @param destDir
	 *            - the destination directory
	 * @throws NullPointerException
	 *             if the input or output is null
	 * @throws IOException
	 *             if an I/O error occurs, if an IO error occurs during copying
	 */
	public static void copyDirectoryWithoutOverwrite(File srcDir, File destDir) throws Exception {
		try {
			FileUtils.copyDirectory(srcDir, destDir);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Moves a directory. When the destination directory is on another file system, do a "copy and delete".
	 *
	 * @param srcDirPath
	 *            - the path of source directory to be moved
	 * @param destDirPath
	 *            - the path of destination directory
	 * @return the file contents, never null
	 * @throws FileExistsException
	 *             if the destination directory exists
	 * @throws NullPointerException
	 *             if source or destination is null
	 * @throws IOException
	 *             if source or destination is invalid, if an IO error occurs moving the file
	 */
	public static void moveDirectory(String srcDirPath, String destDirPath) throws Exception {
		try {
			File srcDir = FileUtils.getFile(srcDirPath);
			File destDir = FileUtils.getFile(destDirPath);

			moveDirectory(srcDir, destDir);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Moves a directory. When the destination directory is on another file system, do a "copy and delete".
	 *
	 * @param srcDir
	 *            - the source directory to be moved
	 * @param destDir
	 *            - the destination directory
	 * @return the file contents, never null
	 * @throws FileExistsException
	 *             if the destination directory exists
	 * @throws NullPointerException
	 *             if source or destination is null
	 * @throws IOException
	 *             if source or destination is invalid, if an IO error occurs moving the file
	 */
	public static void moveDirectory(File srcDir, File destDir) throws Exception {
		try {
			FileUtils.moveDirectory(srcDir, destDir);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Moves a directory to another directory.
	 *
	 * @param srcDirPath
	 *            - the path of source directory to be moved
	 * @param destDirPath
	 *            - the path of destination directory
	 * @param createDestDir
	 *            - If true create the destination directory, otherwise if false throw an IOException
	 * @throws NullPointerException
	 *             if source or destination is null
	 * @throws FileExistsException
	 *             if the directory exists in the destination directory
	 * @throws IOException
	 *             if source or destination is invalid, if an IO error occurs moving the file
	 */
	public static void moveDirectoryToDirectory(String srcDirPath, String destDirPath, boolean createDestDir) throws Exception {
		try {
			File srcDir = FileUtils.getFile(srcDirPath);
			File destDir = FileUtils.getFile(destDirPath);

			moveDirectoryToDirectory(srcDir, destDir, createDestDir);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Moves a directory to another directory.
	 *
	 * @param srcDir
	 *            - the source directory to be moved
	 * @param destDir
	 *            - the destination directory
	 * @param createDestDir
	 *            - If true create the destination directory, otherwise if false throw an IOException
	 * @throws NullPointerException
	 *             if source or destination is null
	 * @throws FileExistsException
	 *             if the directory exists in the destination directory
	 * @throws IOException
	 *             if source or destination is invalid, if an IO error occurs moving the file
	 */
	public static void moveDirectoryToDirectory(File srcDir, File destDir, boolean createDestDir) throws Exception {
		try {
			FileUtils.moveDirectoryToDirectory(srcDir, destDir, createDestDir);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Moves a file. When the destination file is on another file system, do a "copy and delete".
	 *
	 * @param srcFilePath
	 *            - the path of source file to be moved
	 * @param destFilePath
	 *            - the path of destination file
	 * @throws NullPointerException
	 *             if source or destination is null
	 * @throws FileExistsException
	 *             if the destination file exists
	 * @throws IOException
	 *             if source or destination is invalid, if an IO error occurs moving the file
	 */
	public static void moveFile(String srcFilePath, String destFilePath) throws Exception {
		try {
			File srcFile = FileUtils.getFile(srcFilePath);
			File destFile = FileUtils.getFile(destFilePath);

			moveFile(srcFile, destFile);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Moves a file. When the destination file is on another file system, do a "copy and delete".
	 *
	 * @param srcFile
	 *            - the source file to be moved
	 * @param destFile
	 *            - the destination file
	 * @throws NullPointerException
	 *             if source or destination is null
	 * @throws FileExistsException
	 *             if the destination file exists
	 * @throws IOException
	 *             if source or destination is invalid, if an IO error occurs moving the file
	 */
	public static void moveFile(File srcFile, File destFile) throws Exception {
		try {
			FileUtils.moveFile(srcFile, destFile);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Moves a file to a directory.
	 *
	 * @param srcFilePath
	 *            - the path of source file to be moved
	 * @param destDirPath
	 *            - the path of destination directory
	 * @param createDestDir
	 *            - If true create the destination directory, otherwise if false throw an IOException
	 * @throws NullPointerException
	 *             if source or destination is null
	 * @throws FileExistsException
	 *             if source or destination is invalid
	 * @throws IOException
	 *             if source or destination is invalid, if an IO error occurs moving the file
	 */
	public static void moveFileToDirectory(String srcFilePath, String destDirPath, boolean createDestDir) throws Exception {
		try {
			File srcFile = FileUtils.getFile(srcFilePath);
			File destDir = FileUtils.getFile(destDirPath);

			moveFileToDirectory(srcFile, destDir, createDestDir);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Moves a file to a directory.
	 *
	 * @param srcFile
	 *            - the source file to be moved
	 * @param destDir
	 *            - the destination file
	 * @param createDestDir
	 *            - If true create the destination directory, otherwise if false throw an IOException
	 * @throws NullPointerException
	 *             if source or destination is null
	 * @throws FileExistsException
	 *             if source or destination is invalid
	 * @throws IOException
	 *             if source or destination is invalid, if an IO error occurs moving the file
	 */
	public static void moveFileToDirectory(File srcFile, File destDir, boolean createDestDir) throws Exception {
		try {
			FileUtils.moveFileToDirectory(srcFile, destDir, createDestDir);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Moves a file or directory to the destination directory. When the destination is on another file system, do a "copy and delete".
	 *
	 * @param everythingPath
	 *            - the path of file or path of directory to be moved
	 * @param destDirPath
	 *            - the path of destination directory
	 * @param createDestDir
	 *            - If true create the destination directory, otherwise if false throw an IOException
	 * @throws NullPointerException
	 *             if source or destination is null
	 * @throws FileExistsException
	 *             if the directory or file exists in the destination directory
	 * @throws IOException
	 *             if source or destination is invalid, if an IO error occurs moving the file
	 */
	public static void moveEverythingToDirectory(String everythingPath, String destDirPath, boolean createDestDir) throws Exception {
		try {
			File everything = FileUtils.getFile(everythingPath);
			File destDir = FileUtils.getFile(destDirPath);

			moveEverythingToDirectory(everything, destDir, createDestDir);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Moves a file or directory to the destination directory. When the destination is on another file system, do a "copy and delete".
	 *
	 * @param everything
	 *            - the file or directory to be moved
	 * @param destDir
	 *            - the destination directory
	 * @param createDestDir
	 *            - If true create the destination directory, otherwise if false throw an IOException
	 * @throws NullPointerException
	 *             if source or destination is null
	 * @throws FileExistsException
	 *             if the directory or file exists in the destination directory
	 * @throws IOException
	 *             if source or destination is invalid, if an IO error occurs moving the file
	 */
	public static void moveEverythingToDirectory(File everything, File destDir, boolean createDestDir) throws Exception {
		try {
			FileUtils.moveToDirectory(everything, destDir, createDestDir);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Deletes a file, never throwing an exception. If file is a directory, delete it and all sub-directories. The difference between File.delete() and this method are: A directory to be deleted does
	 * not have to be empty. No exceptions are thrown when a file or directory cannot be deleted.
	 *
	 * @param everythingPath
	 *            - path of file or path of directory to delete, can be null
	 * @return true if the file or directory was deleted, otherwise false
	 */
	public static boolean deleteQuietly(String everythingPath) throws Exception {
		boolean status = false;
		try {
			File everything = FileUtils.getFile(everythingPath);

			status = deleteQuietly(everything);
		} catch (Exception e) {
			throw e;
		}
		return status;
	}

	/**
	 * Deletes a file, never throwing an exception. If file is a directory, delete it and all sub-directories. The difference between File.delete() and this method are: A directory to be deleted does
	 * not have to be empty. No exceptions are thrown when a file or directory cannot be deleted.
	 *
	 * @param everything
	 *            - file or directory to delete, can be null
	 * @return true if the file or directory was deleted, otherwise false
	 */
	public static boolean deleteQuietly(File everything) throws Exception {
		boolean status = false;
		try {
			status = FileUtils.deleteQuietly(everything);
		} catch (Exception e) {
			throw e;
		}
		return status;
	}

	/**
	 * Deletes a file. If file is a directory, delete it and all sub-directories. The difference between File.delete() and this method are: A directory to be deleted does not have to be empty. You get
	 * exceptions when a file or directory cannot be deleted. (java.io.File methods returns a boolean)
	 *
	 * @param everythingPath
	 *            - path of file or path of directory to delete, must not be null
	 * @throws NullPointerException
	 *             if the directory is null
	 * @throws FileNotFoundException
	 *             if the file was not found
	 * @throws IOException
	 *             in case deletion is unsuccessfu
	 */
	public static void forceDelete(String everythingPath) throws Exception {
		File everything = FileUtils.getFile(everythingPath);
		try {
			forceDelete(everything);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Deletes a file. If file is a directory, delete it and all sub-directories. The difference between File.delete() and this method are: A directory to be deleted does not have to be empty. You get
	 * exceptions when a file or directory cannot be deleted. (java.io.File methods returns a boolean)
	 *
	 * @param everything
	 *            - file or directory to delete, must not be null
	 * @throws NullPointerException
	 *             if the directory is null
	 * @throws FileNotFoundException
	 *             if the file was not found
	 * @throws IOException
	 *             in case deletion is unsuccessfu
	 */
	public static void forceDelete(File everything) throws Exception {
		try {
			FileUtils.forceDelete(everything);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Finds files within a given directory (and optionally its subdirectories) which match an array of extensions.
	 *
	 * @param directoryPath
	 *            - the path of directory to search in
	 * @param extensions
	 *            - an array of extensions, ex. {"java","xml"}. If this parameter is null, all files are returned.
	 * @param recursive
	 *            - if true all subdirectories are searched as well
	 * @return an array of java.io.File with the matching files
	 * @throws Exception
	 */
	public static File[] listFiles(String directoryPath, String[] extensions, boolean recursive) throws Exception {
		File[] files = new File[0];
		try {
			File directory = FileUtils.getFile(directoryPath);

			files = listFiles(directory, extensions, recursive);
		} catch (Exception e) {
			throw e;
		}
		return files;
	}

	/**
	 * Finds files within a given directory (and optionally its subdirectories) which match an array of extensions.
	 *
	 * @param directory
	 *            - the directory to search in
	 * @param extensions
	 *            - an array of extensions, ex. {"java","xml"}. If this parameter is null, all files are returned.
	 * @param recursive
	 *            - if true all subdirectories are searched as well
	 * @return an array of java.io.File with the matching files
	 * @throws Exception
	 */
	public static File[] listFiles(File directory, String[] extensions, boolean recursive) throws Exception {
		File[] files = new File[0];
		Collection<File> fileCollection = null;
		try {
			fileCollection = FileUtils.listFiles(directory, extensions, recursive);
			files = fileCollection.toArray(files);
		} catch (Exception e) {
			throw e;
		}
		return files;
	}

	/**
	 * Finds files within a given directory (and optionally its subdirectories) which match an string of wildcard.
	 *
	 * @param directoryPath
	 *            - the path of directory to search in
	 * @param wildcard
	 *            - an string of wildcard, ex. "123*.zip".
	 * @return an array of java.io.File with the matching files
	 * @throws Exception
	 */
	public static File[] listFiles(String directoryPath, String wildcard) throws Exception {
		File[] files = new File[0];
		try {
			File directory = FileUtils.getFile(directoryPath);

			files = listFiles(directory, wildcard);
		} catch (Exception e) {
			throw e;
		}
		return files;
	}

	/**
	 * Finds files within a given directory (and optionally its subdirectories) which match an string of wildcard.
	 *
	 * @param directory
	 *            - the directory to search in
	 * @param wildcard
	 *            - an string of wildcard, ex. "123*.zip".
	 * @return an array of java.io.File with the matching files
	 * @throws Exception
	 */
	public static File[] listFiles(File directory, String wildcard) throws Exception {
		File[] files = new File[0];
		Collection<File> fileCollection = null;
		try {
			WildcardFileFilter wildcardFileFilter = new WildcardFileFilter(wildcard);
			fileCollection = FileUtils.listFiles(directory, wildcardFileFilter, FileFilterUtils.directoryFileFilter());
			files = fileCollection.toArray(files);
		} catch (Exception e) {
			throw e;
		}
		return files;
	}

	/**
	 * Finds files within a given directory (and optionally its subdirectories) which match an string of wildcard.
	 *
	 * @param directoryPath
	 *            - the path of directory to search in
	 * @param wildcard
	 *            - an string of wildcard, ex. "aa" or "a*".
	 * @return an array of java.io.File with the matching files
	 * @throws Exception
	 */
	public static File[] listDirectorys(String directoryPath, String wildcard) throws Exception {
		File[] directorys = new File[0];
		try {
			File directory = FileUtils.getFile(directoryPath);

			directorys = listDirectorys(directory, wildcard);
		} catch (Exception e) {
			throw e;
		}
		return directorys;
	}

	/**
	 * Finds files within a given directory (and optionally its subdirectories) which match an string of wildcard.
	 *
	 * @param directory
	 *            - the directory to search in
	 * @param wildcard
	 *            - an string of wildcard, ex. "aa" or "a*".
	 * @return an array of java.io.File with the matching files
	 * @throws Exception
	 */
	public static File[] listDirectorys(File directory, String wildcard) throws Exception {
		File[] directorys = new File[0];
		Collection<File> directoryCollection = null;
		try {
			WildcardFileFilter wildcardFileFilter = new WildcardFileFilter(wildcard);
			// IOFileFilter directoryFilter = FileFilterUtils.and(FileFilterUtils.directoryFileFilter(), wildcardFileFilter);
			directoryCollection = FileUtils.listFilesAndDirs(directory, FileFilterUtils.falseFileFilter(), wildcardFileFilter);
			if (directoryCollection.size() > 1) {
				directorys = directoryCollection.toArray(directorys);
			}
		} catch (Exception e) {
			throw e;
		}
		return directorys;
	}

	/**
	 * Finds files and directory within a given directory (and optionally its subdirectories).
	 *
	 * @param directoryPath
	 *            - the path of directory to search in
	 * @return an array of java.io.File in the directory
	 * @throws Exception
	 */
	public static File[] listEverything(String directoryPath) throws Exception {
		File[] files = new File[0];
		try {
			File directory = FileUtils.getFile(directoryPath);

			files = listEverything(directory);
		} catch (Exception e) {
			throw e;
		}
		return files;
	}

	/**
	 * Finds files and directory within a given directory (and optionally its subdirectories).
	 *
	 * @param directory
	 *            - the directory to search in
	 * @return an array of java.io.File in the directory
	 * @throws Exception
	 */
	public static File[] listEverything(File directory) throws Exception {
		File[] files = new File[0];
		Collection<File> fileCollection = null;
		try {
			IOFileFilter fileFilter = FileFilterUtils.fileFileFilter();
			IOFileFilter dirFilter = FileFilterUtils.directoryFileFilter();

			fileCollection = FileUtils.listFilesAndDirs(directory, fileFilter, dirFilter);
			if (fileCollection.size() > 1) {
				files = fileCollection.toArray(files);
			}
		} catch (Exception e) {
			throw e;
		}
		return files;
	}

	/**
	 * Create the directory without overwrite.
	 *
	 * @param directoryPath
	 *            - the path of directory to crate
	 * @throws Exception
	 */
	public static void crateDirectoryWithoutOverwrite(String directoryPath) throws Exception {
		try {
			File directory = FileUtils.getFile(directoryPath);

			crateDirectoryWithoutOverwrite(directory);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Create the directory without overwrite.
	 *
	 * @param directory
	 *            - the directory to crate
	 * @throws Exception
	 */
	public static void crateDirectoryWithoutOverwrite(File directory) throws Exception {
		try {
			if (directory.exists() == false) {
				FileUtils.forceMkdir(directory);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Create the directory with overwrite.
	 *
	 * @param directoryPath
	 *            - the path of directory to crate
	 * @throws Exception
	 */
	public static void crateDirectoryWithOverwrite(String directoryPath) throws Exception {
		try {
			File directory = FileUtils.getFile(directoryPath);

			crateDirectoryWithOverwrite(directory);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Create the directory with overwrite.
	 *
	 * @param directory
	 *            - the directory to crate
	 * @throws Exception
	 */
	public static void crateDirectoryWithOverwrite(File directory) throws Exception {
		long start = System.currentTimeMillis();
		try {
			FileUtils.deleteDirectory(directory);
			FileUtils.forceMkdir(directory);
		} catch (Exception e) {
			throw e;
		}
		long elapsed = System.currentTimeMillis() - start;
		LogUtil.UTIL.debug("Final time :- " + convertTime(elapsed));
	}

	/**
	 * Create the empty file without overwrite.
	 *
	 * @param filePath
	 *            - the path of file to crate
	 * @param size
	 *            - size of file to crate
	 * @throws Exception
	 */
	public static void crateEmptyFileWithoutOverwrite(String filePath, long size) throws Exception {
		try {
			File file = FileUtils.getFile(filePath);

			crateEmptyFileWithoutOverwrite(file, size);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Create the empty file without overwrite.
	 *
	 * @param file
	 *            - the file to crate
	 * @param size
	 *            - size of file to crate
	 * @throws Exception
	 */
	public static void crateEmptyFileWithoutOverwrite(File file, long size) throws Exception {
		RandomAccessFile raf = null;
		try {
			if (file.exists() == false) {
				file.createNewFile();

				raf = new RandomAccessFile(file, "rw");
				raf.setLength(size);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (raf != null) {
				raf.close();
			}
		}
	}

	/**
	 * Create the empty file with overwrite.
	 *
	 * @param filePath
	 *            - the path of file to crate
	 * @param size
	 *            - size of file to crate
	 * @throws Exception
	 */
	public static void crateEmptyFileWithOverwrite(String filePath, long size) throws Exception {
		try {
			File file = FileUtils.getFile(filePath);

			crateEmptyFileWithOverwrite(file, size);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Create the empty file with overwrite.
	 *
	 * @param file
	 *            - the file to crate
	 * @param size
	 *            - size of file to crate
	 * @throws Exception
	 */
	public static void crateEmptyFileWithOverwrite(File file, long size) throws Exception {
		RandomAccessFile raf = null;
		try {
			FileUtils.deleteQuietly(file);
			file.createNewFile();

			raf = new RandomAccessFile(file, "rw");
			raf.setLength(size);
		} catch (Exception e) {
			throw e;
		} finally {
			if (raf != null) {
				raf.close();
			}
		}
	}

	/**
	 * Read byte from file with offset.
	 *
	 * @param filePath
	 *            - the path of file for read
	 * @param offset
	 *            - start byte for read
	 * @param length
	 *            - length of data for read
	 * @return the array of byte
	 * @throws Exception
	 */
	public static byte[] readBlock(String filePath, long offset, int length) throws Exception {
		byte[] bytes = new byte[length];
		try {
			File file = FileUtils.getFile(filePath);

			bytes = readBlock(file, offset, length);
		} catch (Exception e) {
			throw e;
		}
		return bytes;
	}

	/**
	 * Read byte from file with offset.
	 *
	 * @param file
	 *            - the file for read
	 * @param offset
	 *            - start byte for read
	 * @param length
	 *            - length of data for read
	 * @return the array of byte
	 * @throws Exception
	 */
	public static byte[] readBlock(File file, long offset, int length) throws Exception {
		byte[] bytes = new byte[length];
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "r");
			raf.seek(offset);
			raf.read(bytes);
		} catch (Exception e) {
			throw e;
		} finally {
			if (raf != null) {
				raf.close();
			}
		}
		return bytes;
	}

	/**
	 * Write byte to file with offset.
	 *
	 * @param filePath
	 *            - the path of file to write
	 * @param offset
	 *            - start byte to write
	 * @param bytes
	 *            - data to write
	 * @throws Exception
	 */
	public static void writeBlock(String filePath, long offset, byte[] bytes) throws Exception {
		try {
			File file = FileUtils.getFile(filePath);

			writeBlock(file, offset, bytes);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Write byte to file with offset.
	 *
	 * @param file
	 *            - the file to write
	 * @param offset
	 *            - start byte to write
	 * @param bytes
	 *            - data to write
	 * @throws Exception
	 */
	public static void writeBlock(File file, long offset, byte[] bytes) throws Exception {
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "rw");
			raf.seek(offset);
			raf.write(bytes);
		} catch (Exception e) {
			throw e;
		} finally {
			if (raf != null) {
				raf.close();
			}
		}
	}

	/**
	 * Write byte to file with offset and report percent
	 *
	 * @param srcFilePath
	 *            - the file to read
	 * @param destFilePath
	 *            - the file to write
	 * @param offset
	 *            - start byte to write
	 * @param buffer
	 *            - size of buffer
	 * @throws Exception
	 */
	public void writeBlockExtra(String srcFilePath, String destFilePath, long offset, int buffer) throws Exception {
		try {
			File srcFile = FileUtils.getFile(srcFilePath);
			File destFile = FileUtils.getFile(destFilePath);

			writeBlockExtra(srcFile, destFile, offset, buffer);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Write byte to file with offset and report percent
	 *
	 * @param srcFile
	 *            - the file to read
	 * @param destFile
	 *            - the file to write
	 * @param offset
	 *            - start byte to write
	 * @param buffer
	 *            - size of buffer
	 * @throws Exception
	 */
	public void writeBlockExtra(File srcFile, File destFile, long offset, int buffer) throws Exception {
		String key = srcFile.getAbsolutePath() + destFile.getAbsolutePath();
		setExtraPercent(key, 0.0);
		try {
			long srcSize = getSizeOfFile(srcFile);
			if (isFile(destFile)) {
				long destSize = getSizeOfFile(destFile);
				if (srcSize != destSize) {
					FileUtils.deleteQuietly(destFile);
				}
			}
			crateEmptyFileWithoutOverwrite(destFile, srcSize);

			int length = buffer;
			if (srcSize < length) {
				length = (int) srcSize;
			}

			double percent;
			byte[] bytes;
			long newoffset;
			while (srcSize >= (offset + length)) {
				// System.out.println("size >= offset + buffer (total)");

				bytes = readBlock(srcFile, offset, length);
				writeBlock(destFile, offset, bytes);

				// System.out.println(srcSize + " >= " + offset + " + " + length + " (" + (offset + length) + ")");
				percent = ((double) (offset + buffer) / (double) srcSize) * 100;
				setExtraPercent(key, percent);

				newoffset = offset + length;
				if ((newoffset + length) > srcSize) {
					length = (int) (srcSize - newoffset);
				}

				offset = newoffset;

				if (length == 0) {
					break;
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			Thread.sleep(500);
			removeExtraPercent(key);
		}
	}

	/**
	 * Zip file or directory
	 *
	 * @param selectPath
	 *            - the path of file or path of directory to zip
	 * @param resultPath
	 *            - the path of output zip
	 * @param resultName
	 *            - the name of zip file
	 * @param bufferSize
	 *            - size of buffer
	 * @throws Exception
	 */
	public static void doZip(String selectPath, String resultPath, String resultName, int bufferSize) throws Exception {
		try {
			if (isDirectory(selectPath)) {
				doZipDirectory(selectPath, resultPath, resultName, bufferSize);
			} else {
				doZipFile(selectPath, resultPath, bufferSize);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Zip directory
	 *
	 * @param selectPath
	 *            - the path of file or path of directory to zip
	 * @param resultPath
	 *            - the path of output zip
	 * @param resultName
	 *            - the name of zip file
	 * @param bufferSize
	 *            - size of buffer
	 * @throws Exception
	 */
	public static void doZipDirectory(String selectPath, String resultPath, String resultName, int bufferSize) throws Exception {
		try {
			selectPath = defaultPath(selectPath);
			resultPath = defaultPath(resultPath, CREATE_DIRECTORY_ON);
			resultName = defaultName(selectPath, resultName);

//			System.out.println(" selectPath [" + selectPath + "]");
//			System.out.println(" resultPath [" + resultPath + "]");
//			System.out.println(" resultName [" + resultName + "]");

			String selectName = defaultName(selectPath, null);
			String resultTmp = resultPath + DELIMITER_SLASH + resultName + DELIMITER_DOT + EXTENSION_TMP;
			String resultZip = resultPath + DELIMITER_SLASH + resultName + DELIMITER_DOT + EXTENSION_ZIP;

			deleteQuietly(resultTmp);
			deleteQuietly(resultZip);
//			System.out.printf("Zip %s >> %s" + DELIMITER_NEWLINE, selectPath, resultZip);

			File[] files = listEverything(selectPath);
			if (files.length == 0) {
				files = new File[1];
				files[0] = new File(selectPath);
			}
//			displayFilesPath(files);

			byte[] buffer = new byte[bufferSize];
			int length;
			FileOutputStream zipWriter = null;
			ZipOutputStream zip = null;
			try {
				zipWriter = new FileOutputStream(resultTmp);
				zip = new ZipOutputStream(zipWriter);
				String zipEntryName;
				for (File file : files) {
					zipEntryName = file.getAbsolutePath().replace(selectPath, selectName);
					// System.out.println("Add :- " + zipEntryName + " to " + resultTmp);
					FileInputStream in = null;
					try {
						if (isDirectory(file) == false) {
							zip.putNextEntry(new ZipEntry(zipEntryName));
							in = new FileInputStream(file);
							while ((length = in.read(buffer)) > 0) {
								zip.write(buffer, 0, length);
								zip.flush();
							}
						} else {
							zip.putNextEntry(new ZipEntry(zipEntryName + DELIMITER_SLASH));
						}
					} catch (Exception e) {
						throw e;
					} finally {
						if (in != null) {
							in.close();
						}
						zip.closeEntry();
					}
				}
			} catch (Exception e) {
				throw e;
			} finally {
				if (zip != null) {
					zip.close();
				}
				if (zipWriter != null) {
					zipWriter.close();
				}
			}
			moveFile(resultTmp, resultZip);
		} catch (Exception e) {
			throw e;
		}
	}

	public static void doZipAllInDirectory(String selectPath, String resultPath, String resultName, int bufferSize) throws Exception {
		try {
			selectPath = defaultPath(selectPath);
			resultPath = defaultPath(resultPath, CREATE_DIRECTORY_ON);
			resultName = defaultName(selectPath, resultName);

//			System.out.println(" selectPath [" + selectPath + "]");
//			System.out.println(" resultPath [" + resultPath + "]");
//			System.out.println(" resultName [" + resultName + "]");

			String selectName = "";
			String resultTmp = resultPath + DELIMITER_SLASH + resultName + DELIMITER_DOT + EXTENSION_TMP;
			String resultZip = resultPath + DELIMITER_SLASH + resultName + DELIMITER_DOT + EXTENSION_ZIP;

			deleteQuietly(resultTmp);
			deleteQuietly(resultZip);
//			System.out.printf("Zip %s >> %s" + DELIMITER_NEWLINE, selectPath, resultZip);

			File[] files = listEverything(selectPath);
			if (files.length > 0) {
				File[] filesWithoutRootPath = new File[files.length - 1];
				System.arraycopy(files, 1, filesWithoutRootPath, 0, filesWithoutRootPath.length);
				files = filesWithoutRootPath;
			}
//			displayFilesPath(files);

			byte[] buffer = new byte[bufferSize];
			int length;
			FileOutputStream zipWriter = null;
			ZipOutputStream zip = null;
			try {
				zipWriter = new FileOutputStream(resultTmp);
				zip = new ZipOutputStream(zipWriter);
				String zipEntryName;
				for (File file : files) {
					zipEntryName = file.getAbsolutePath().replace(selectPath, selectName).substring(1);
//					System.out.println("Add :- " + zipEntryName + " to " + resultTmp);
					FileInputStream in = null;
					try {
						if (isDirectory(file) == false) {
							zip.putNextEntry(new ZipEntry(zipEntryName));
							in = new FileInputStream(file);
							while ((length = in.read(buffer)) > 0) {
								zip.write(buffer, 0, length);
								zip.flush();
							}
						} else {
							zip.putNextEntry(new ZipEntry(zipEntryName + DELIMITER_SLASH));
						}
					} catch (Exception e) {
						throw e;
					} finally {
						if (in != null) {
							in.close();
						}
						zip.closeEntry();
					}
				}
			} catch (Exception e) {
				throw e;
			} finally {
				if (zip != null) {
					zip.close();
				}
				if (zipWriter != null) {
					zipWriter.close();
				}
			}
			moveFile(resultTmp, resultZip);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Zip file
	 *
	 * @param selectPath
	 *            - the path of file or path of directory to zip
	 * @param resultPath
	 *            - the path of output zip
	 * @param bufferSize
	 *            - size of buffer
	 * @throws Exception
	 */
	public static void doZipFile(String selectPath, String resultPath, int bufferSize) throws Exception {
		try {
			File selectFile = FileUtils.getFile(selectPath);
			File resultFile = FileUtils.getFile(resultPath);

			// selectPath = selectPath.replace(selectFile.getName(), "");
			resultPath = resultPath.replace(resultFile.getName(), "");

//			System.out.println(" selectPath [" + selectPath + "]");
//			System.out.println(" resultPath [" + resultPath + "]");

			// String selectName = defaultName(selectPath, null);
			String resultTmp = resultPath + DELIMITER_GRAVE_ACCENT + resultFile.getName();
			String resultZip = resultPath + resultFile.getName();

			deleteQuietly(resultTmp);
			deleteQuietly(resultZip);
//			System.out.printf("Zip %s >> %s" + DELIMITER_NEWLINE, selectPath, resultZip);

			byte[] buffer = new byte[bufferSize];
			int length;
			FileOutputStream zipWriter = null;
			ZipOutputStream zip = null;
			try {
				zipWriter = new FileOutputStream(resultTmp);
				zip = new ZipOutputStream(zipWriter);

				String zipEntryName = selectFile.getName();
				// System.out.println("Add :- " + zipEntryName + " to " + resultTmp);

				FileInputStream in = null;
				try {
					zip.putNextEntry(new ZipEntry(zipEntryName));
					in = new FileInputStream(selectFile);
					while ((length = in.read(buffer)) > 0) {
						zip.write(buffer, 0, length);
						zip.flush();
					}
				} catch (Exception e) {
					throw e;
				} finally {
					if (in != null) {
						in.close();
					}
					zip.closeEntry();
				}
			} catch (Exception e) {
				throw e;
			} finally {
				if (zip != null) {
					zip.close();
				}
				if (zipWriter != null) {
					zipWriter.close();
				}
			}
			moveFile(resultTmp, resultZip);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Unzip
	 *
	 * @param selectPath
	 *            - the path of zip
	 * @param resultPath
	 *            - the path of output
	 * @param bufferSize
	 *            - size of buffer
	 * @throws Exception
	 */
	public static void doUnzip(String selectPath, String resultPath, int bufferSize) throws Exception {
		try {
			selectPath = defaultPath(selectPath);
			resultPath = defaultPath(resultPath, CREATE_DIRECTORY_ON);

			LogUtil.UTIL.debug(" selectPath [" + selectPath + "]");
			LogUtil.UTIL.debug(" resultPath [" + resultPath + "]");

			LogUtil.UTIL.debug("Unzip %s >> %s" + DELIMITER_NEWLINE  + ":" +  selectPath + ":" + resultPath);

			byte[] buffer = new byte[bufferSize];
			int length;

			FileInputStream zipReader = null;
			ZipInputStream zip = null;
			try {
				zipReader = new FileInputStream(selectPath);
				zip = new ZipInputStream(zipReader);

				while (true) {
					ZipEntry zipEntry = zip.getNextEntry();
					if (zipEntry == null) {
						break;
					}
					// String zipEntryName = zipEntry.getName();
					String unzipPath = resultPath + DELIMITER_SLASH + zipEntry.getName();
					// System.out.println("Extrac :- " + zipEntryName + " to " + unzipPath);
					FileOutputStream out = null;
					try {
						if (zipEntry.isDirectory() == false) {
							out = new FileOutputStream(unzipPath);
							while ((length = zip.read(buffer)) > 0) {
								out.write(buffer, 0, length);
								out.flush();
							}
						} else {
							crateDirectoryWithoutOverwrite(unzipPath);
						}
					} catch (Exception e) {
						throw e;
					} finally {
						if (out != null) {
							out.close();
						}
						zip.closeEntry();
					}
				}
			} catch (Exception e) {
				throw e;
			} finally {
				if (zip != null) {
					zip.close();
				}
				if (zipReader != null) {
					zipReader.close();
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public static String defaultPath(String path, boolean createDirectory) throws Exception {
		String defaultPath = "";
		try {
			defaultPath = defaultPath(path);
			if (CREATE_DIRECTORY_ON) {
				FileUtils.forceMkdir(FileUtils.getFile(defaultPath));
			}
		} catch (Exception e) {
			throw e;
		}
		return defaultPath;
	}

	public static String defaultPath(String path) throws Exception {
		String defaultPath = "";
		try {
			if ((path == null) || (path.trim().isEmpty())) {
				defaultPath = FileUtils.getTempDirectory().getAbsolutePath();
			} else {
				defaultPath = FileUtils.getFile(path).getAbsolutePath();
			}
		} catch (Exception e) {
			throw e;
		}
		return defaultPath;
	}

	public static String defaultName(String path, String name) throws Exception {
		String defaultName = "";
		try {
			File directory = FileUtils.getFile(path);
			if ((name == null) || (name.trim().isEmpty())) {
				defaultName = directory.getName();
			} else {
				defaultName = name;
			}
		} catch (Exception e) {
			throw e;
		}
		return defaultName;
	}

	/**
	 * Sort file name order by ascending.
	 *
	 * @param files
	 *            - array of file to sort
	 * @param directory
	 *            - the directory to crate
	 * @param caseSensitive
	 *            - true is sensitive, false not sensitive
	 * @throws Exception
	 */
	public static void sortAscendingByName(File[] files, boolean caseSensitive) throws Exception {
		try {
			if (caseSensitive == CASE_SENSITIVE_ON) {
				Arrays.sort(files, NameFileComparator.NAME_COMPARATOR);
			} else {
				Arrays.sort(files, NameFileComparator.NAME_INSENSITIVE_COMPARATOR);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Sort file name order by descending.
	 *
	 * @param files
	 *            - array of file to sort
	 * @param directory
	 *            - the directory to crate
	 * @param caseSensitive
	 *            - true is sensitive, false not sensitive
	 * @throws Exception
	 */
	public static void sortDescendingByName(File[] files, boolean caseSensitive) throws Exception {
		try {
			if (caseSensitive == CASE_SENSITIVE_ON) {
				Arrays.sort(files, NameFileComparator.NAME_REVERSE);
			} else {
				Arrays.sort(files, NameFileComparator.NAME_INSENSITIVE_REVERSE);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * Returns the size of the specified file or directory. If the provided File is a regular file, then the file's length is returned. If the argument is a directory, then the size of the directory
	 * is calculated recursively. If a directory or subdirectory is security restricted, its size will not be included.
	 *
	 * @param file
	 *            - the path of regular file or directory to return the size of (must not be null).
	 * @return the length of the file, or recursive size of the directory, provided (in bytes).
	 * @throws Exception
	 */
	public static long getSizeOfFile(String filePath) throws Exception {
		long size = 0;
		try {
			File file = FileUtils.getFile(filePath);

			size = getSizeOfFile(file);
		} catch (Exception e) {
			throw e;
		}
		return size;
	}

	/**
	 * Returns the size of the specified file or directory. If the provided File is a regular file, then the file's length is returned. If the argument is a directory, then the size of the directory
	 * is calculated recursively. If a directory or subdirectory is security restricted, its size will not be included.
	 *
	 * @param file
	 *            - the regular file or directory to return the size of (must not be null).
	 * @return the length of the file, or recursive size of the directory, provided (in bytes).
	 * @throws Exception
	 */
	public static long getSizeOfFile(File file) throws Exception {
		long size = 0;
		try {
			size = FileUtils.sizeOf(file);
		} catch (Exception e) {
			throw e;
		}
		return size;
	}

	/**
	 * Counts the size of a directory recursively (sum of the length of all files).
	 *
	 * @param directory
	 *            - path of directory to inspect, must not be null
	 * @return size of directory in bytes, 0 if directory is security restricted, a negative number when the real total is greater than Long.MAX_VALUE.
	 * @throws Exception
	 */
	public static long getSizeOfDirectory(String directoryPath) throws Exception {
		long size = 0;
		try {
			File file = FileUtils.getFile(directoryPath);

			size = getSizeOfDirectory(file);
		} catch (Exception e) {
			throw e;
		}
		return size;
	}

	/**
	 * Counts the size of a directory recursively (sum of the length of all files).
	 *
	 * @param directory
	 *            - directory to inspect, must not be null
	 * @return size of directory in bytes, 0 if directory is security restricted, a negative number when the real total is greater than Long.MAX_VALUE.
	 * @throws Exception
	 */
	public static long getSizeOfDirectory(File directory) throws Exception {
		long size = 0;
		try {
			size = FileUtils.sizeOfDirectory(directory);
		} catch (Exception e) {
			throw e;
		}
		return size;
	}

	public static int getProcessStatusOfFile(String srcFilePath, String destFilePath) throws Exception {
		int percent = 0;
		try {
			File srcFile = FileUtils.getFile(srcFilePath);
			File destFile = FileUtils.getFile(destFilePath);

			percent = getProcessStatusOfFile(srcFile, destFile);
		} catch (Exception e) {
			throw e;
		}
		return percent;
	}

	public static int getProcessStatusOfFile(File srcFile, File destFile) throws Exception {
		int percent = 0;
		try {
			long x = 100;
			long srcFileLength = FileUtils.sizeOf(srcFile);
			long destFileLength = FileUtils.sizeOf(destFile);
			// System.out.println(destFileLength + " / " + srcFileLength);
			double tmp = (((double) destFileLength) / ((double) srcFileLength)) * ((double) x);
			String tmpd = (tmp + "");
			tmpd = tmpd.substring(0, tmpd.indexOf("."));

			percent = Integer.parseInt(tmpd);
		} catch (Exception e) {
			throw e;
		}
		return percent;
	}

	public static int getProcessStatusOfDirectory(String srcDirPath, String destDirPath) throws Exception {
		int percent = 0;
		try {
			File srcDir = FileUtils.getFile(srcDirPath);
			File destDir = FileUtils.getFile(destDirPath);

			percent = getProcessStatusOfDirectory(srcDir, destDir);
		} catch (Exception e) {
			throw e;
		}
		return percent;
	}

	public static int getProcessStatusOfDirectory(File srcDir, File destDir) throws Exception {
		int percent = 0;
		try {

			long x = 100;
			long srcDirLength = FileUtils.sizeOfDirectory(srcDir);
			long destDirLength = FileUtils.sizeOfDirectory(destDir);
			// System.out.println(destDirLength + " / " + srcDirLength);
			double tmp = (((double) destDirLength) / ((double) srcDirLength)) * ((double) x);
			String tmpd = (tmp + "");
			tmpd = tmpd.substring(0, tmpd.indexOf("."));

			percent = Integer.parseInt(tmpd);
		} catch (Exception e) {
			throw e;
		}
		return percent;
	}

	public int getExtraPercent(String srcFilePath, String destFilePath) {
		String key = FileUtils.getFile(srcFilePath).getAbsolutePath() + FileUtils.getFile(destFilePath).getAbsolutePath();
		Double tmp = EXTRA_PERCENTS.get(key);
		int percent = 0;
		if (tmp != null) {
			String p_tmp = String.valueOf(tmp);
			p_tmp = p_tmp.substring(0, p_tmp.indexOf("."));
			percent = Integer.parseInt(p_tmp);
		}
		return percent;
	}

	private void setExtraPercent(String key, double percent) {
		EXTRA_PERCENTS.put(key, percent);
	}

	private void removeExtraPercent(String key) {
		EXTRA_PERCENTS.remove(key);
	}

	public static boolean isDirectory(String directoryPath) throws Exception {
		boolean isDirectory = false;
		try {
			isDirectory = FileUtils.getFile(directoryPath).isDirectory();
		} catch (Exception e) {
			throw e;
		}
		return isDirectory;
	}

	public static boolean isDirectory(File directory) throws Exception {
		boolean isDirectory = false;
		try {
			isDirectory = directory.isDirectory();
		} catch (Exception e) {
			throw e;
		}
		return isDirectory;
	}

	public static boolean isFile(String filePath) throws Exception {
		boolean isFile = false;
		try {
			isFile = FileUtils.getFile(filePath).isFile();
		} catch (Exception e) {
			throw e;
		}
		return isFile;
	}

	public static boolean isFile(File file) throws Exception {
		boolean isFile = false;
		try {
			isFile = file.isFile();
		} catch (Exception e) {
			throw e;
		}
		return isFile;
	}

	public static boolean doZipTest(String zipFilePath) throws Exception {
		boolean isZip = false;
		try {
			File zipFile = FileUtils.getFile(zipFilePath);
			isZip = doZipTest(zipFile);
		} catch (Exception e) {
		}
		return isZip;
	}

	public static boolean doZipTest(File zipFile) throws Exception {
		boolean isZip = false;
		ZipFile zipfile = null;
		try {
			zipfile = new ZipFile(zipFile);
			isZip = true;
		} catch (Exception e) {
		} finally {
			if (zipfile != null) {
				zipfile.close();
			}
		}
		return isZip;
	}

	public static void displayLines(List<String> lines) {
		for (String line : lines) {
			LogUtil.UTIL.debug("line: " + line);
		}
	}

	public static void displayFiles(File[] files) {
		for (File file : files) {
			LogUtil.UTIL.debug("File: %-20s Last Modified:" + new Date(file.lastModified()) + "\n" + file.getName());
		}
	}

	public static void displayFilesPath(File[] files) {
		for (File file : files) {
			LogUtil.UTIL.debug("File: " + file.getAbsolutePath());
		}
	}

	public static String convertTime(long elapsed) {
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+0"));
		return formatter.format(new Date(elapsed));
	}
	
	/**
	 * 
	 * @param pathFile : C://testreport//
	 * @param fileName  : sss.xlsx
	 * @param workbook
	 * @throws Exception
	 */
	public static void writeDataXSSFWorkbook(String pathFile ,String fileName, XSSFWorkbook workbook  ) throws Exception {
		try {
			
			FileOutputStream fileOut = new FileOutputStream(pathFile+fileName);
			
			workbook.write(fileOut);
			fileOut.flush();
			fileOut.close();
			
		} catch (Exception e) {
			throw e;
		}finally{
			
		}
	}
}
