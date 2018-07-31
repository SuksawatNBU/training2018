package util.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import util.calendar.CalendarUtil;
import util.file.FileManagerUtil;
import util.log.LogUtil;
import util.string.StringUtil;

import com.cct.domain.FileMeta;
import com.cct.domain.FileMetaForSave;
import com.cct.trn.core.config.parameter.domain.ParameterConfig;

public class BrowseUploadServiceUtil {
	private static final String SEPERATE_DOT = ".";
	private static final String SEPERATE_U_SCORE = "_";

	/**
	 * Manage browse file
	 * 
	 * @param tmpPath
	 * @param file
	 * @return file thumbnail with base64
	 * @throws Exception
	 */
	public static String[] performBrowse(String tmpFilePath, FileMeta file) throws Exception {
		String[] result = null;

		try {
			FileManagerUtil.copy(file.getFileUpload()[0].getAbsolutePath(), tmpFilePath);

			String base64 = createThumbnail(tmpFilePath, file.getFileUploadContentType()[0]);

			String[] fileThumbnail64 = { base64 };
			result = fileThumbnail64;

		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * กรณีไม่ต้องการ thumbnail
	 * 
	 * @param tmpFilePath
	 * @param file
	 * @param createThumbnail
	 * @return
	 * @throws Exception
	 */
	public static String[] performBrowse(String tmpFilePath, FileMeta file, boolean createThumbnail) throws Exception {
		String[] result = null;

		try {
			if (createThumbnail) {
				result = performBrowse(tmpFilePath, file);
			} else {
				FileManagerUtil.copy(file.getFileUpload()[0].getAbsolutePath(), tmpFilePath);
			}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	/**
	 * Manage Upload
	 * 
	 * @param file
	 *            (FileMeta)
	 * @param idPk
	 *            (primaryKey ของแต่ละระบบ)
	 * @param serverPath
	 *            ex.(/opt/pddc/attach/server/ACC_IMPORT/2013-11/)
	 * @return
	 * @throws Exception
	 */
	public static String[] performUpload(FileMeta file, String idPk, String serverPath) throws Exception {
		String[] renameStr = null;

		try {
			// Create folder
			FileManagerUtil.crateDirectoryWithoutOverwrite(serverPath);
			renameStr = new String[file.getFileUploadFileNameTmp().length];
			for (int i = 0; i < file.getFileUploadFileNameTmp().length; i++) {
				if (file.getDeleteFlag()[i].trim().equals("Y")) {
					// Clear temp
					FileManagerUtil.deleteQuietly(ParameterConfig.getParameter().getAttachFile().getTmpPath() + file.getFileUploadFileNameTmp()[i]);
				} else {
					// Rename
					renameStr[i] = renameAttachFile(file.getFileUploadFileNameTmp()[i], idPk, (i + 1));

					// Copy to server
					FileManagerUtil.copy(ParameterConfig.getParameter().getAttachFile().getTmpPath() + file.getFileUploadFileNameTmp()[i], serverPath + renameStr[i]);

					// Delete file temp
					FileManagerUtil.deleteQuietly(ParameterConfig.getParameter().getAttachFile().getTmpPath() + file.getFileUploadFileNameTmp()[i]);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return renameStr;
	}

	/* Arunya.k */
	public static void performUpload(List<FileMetaForSave> lstFile, String idPk, String serverPath) throws Exception {
		String[] renameStr = null;

		try {
			// Create folder
			FileManagerUtil.crateDirectoryWithoutOverwrite(serverPath);
			renameStr = new String[lstFile.size()];
			for (int i = 0; i < lstFile.size(); i++) {
				if (lstFile.get(i).getDeleteFlag() != null && lstFile.get(i).getDeleteFlag().trim().equals("Y")) {

					if (StringUtil.nullToString(lstFile.get(i).getId()).trim().equals("")) {
						// Clear temp
						FileManagerUtil.deleteQuietly(ParameterConfig.getParameter().getAttachFile().getTmpPath() + lstFile.get(i).getFileUploadFileNameTmp());
					} else {
						// Clear server
						FileManagerUtil.deleteQuietly(serverPath + lstFile.get(i).getFileUploadFileName());
					}
				} else if (StringUtil.nullToString(lstFile.get(i).getId()).trim().equals("")) {
					// Rename
					renameStr[i] = renameAttachFile(lstFile.get(i).getFileUploadFileNameTmp(), idPk, (i + 1));

					// Copy to server
					FileManagerUtil.copy(ParameterConfig.getParameter().getAttachFile().getTmpPath() + lstFile.get(i).getFileUploadFileNameTmp(), serverPath + renameStr[i]);

					// Delete file temp
					FileManagerUtil.deleteQuietly(ParameterConfig.getParameter().getAttachFile().getTmpPath() + lstFile.get(i).getFileUploadFileNameTmp());

					lstFile.get(i).setFileUploadFileNameTmp(renameStr[i]);
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/* Watchara.n */
	public static String performUploadReturn(List<FileMetaForSave> lstFile, String serverPath) throws Exception {
		String[] renameStr = null;
		String idPk = String.valueOf(new Random().nextInt(10 - 1 + 1) + 1);

		try {

			// Create folder
			FileManagerUtil.crateDirectoryWithoutOverwrite(serverPath);
			renameStr = new String[lstFile.size()];
			for (int i = 0; i < lstFile.size(); i++) {
				if (lstFile.get(i).getDeleteFlag() != null && lstFile.get(i).getDeleteFlag().trim().equals("D")) {

					if (StringUtil.nullToString(lstFile.get(i).getId()).trim().equals("")) {
						// Clear temp
						FileManagerUtil.deleteQuietly(ParameterConfig.getParameter().getAttachFile().getTmpPath() + lstFile.get(i).getFileUploadFileNameTmp());
					} else {
						// Clear server
						FileManagerUtil.deleteQuietly(serverPath + lstFile.get(i).getFileUploadFileName());
					}
				} else if (lstFile.get(i).getDeleteFlag().trim().equals("N") && !lstFile.get(i).getFileUploadFileNameTmp().equals("")) {
					// Rename
					renameStr[i] = renameAttachFile(lstFile.get(i).getFileUploadFileNameTmp(), idPk, (i + 1));

					// Copy to server
					FileManagerUtil.copy(ParameterConfig.getParameter().getAttachFile().getTmpPath() + lstFile.get(i).getFileUploadFileNameTmp(), serverPath + renameStr[i]);

					// Delete file temp
					FileManagerUtil.deleteQuietly(ParameterConfig.getParameter().getAttachFile().getTmpPath() + lstFile.get(i).getFileUploadFileNameTmp());

					lstFile.get(i).setFileUploadFileNameTmp(renameStr[i]);
				} else {
					renameStr[i] = lstFile.get(i).getFileUploadFileNameTmp();
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return renameStr[0];
	}

	public static Map<String, String> loadMapContentType() throws Exception {
		Map<String, String> mappings = new ConcurrentHashMap<String, String>();
		Properties properties = new Properties();
		InputStream is = null;

		try {
			is = BrowseUploadServiceUtil.class.getClassLoader().getResourceAsStream("/util/image/fileContent.properties");
			properties.load(is);

			Enumeration<?> elements = properties.propertyNames();
			while (elements.hasMoreElements()) {
				String keyProps = (String) elements.nextElement();
				String valProps = properties.getProperty(keyProps);

				// populate the Map
				mappings.put(keyProps, valProps);
			}
		} catch (Exception e) {
			throw e;
		}

		return mappings;
	}

	/**
	 * Rename file
	 * 
	 * @param fileNameTmp
	 * @param idPk
	 * @param indexFile
	 * @return
	 * @throws Exception
	 */
	private static String renameAttachFile(String fileNameTmp, String idPk, int indexFile) throws Exception {
		StringBuilder renameStr = new StringBuilder();

		try {
			// ex. [xxxx, jpg]
			String[] filenameTmpSplit = fileNameTmp.split("\\.");

			// Rename file format (YYYYMMDDHHMMSS_X_Y)
			Calendar cal = Calendar.getInstance(new Locale("en", "EN"));
			cal.setTimeInMillis(Long.parseLong(filenameTmpSplit[0]));

			renameStr.setLength(0);
			renameStr.append(CalendarUtil.getYYYYMMDDHH24MISS(cal));
			renameStr.append(SEPERATE_U_SCORE).append(idPk);
			renameStr.append(SEPERATE_U_SCORE).append(indexFile);
			renameStr.append(SEPERATE_DOT).append(filenameTmpSplit[1]);

		} catch (Exception e) {
			throw e;
		}
		return renameStr.toString();
	}

	public static String createThumbnail(String fileName, String contentType) throws Exception {
		String thumbFile = "";
		String base64 = "";
		try {
			// Check file type
			thumbFile = ParameterConfig.getMapContenType().get(contentType.toLowerCase());

			LogUtil.COMMON.debug("file.getFileUploadContentType()[0] : " + contentType);
			LogUtil.COMMON.debug("map with MapContenType : " + thumbFile);

			if (thumbFile == null) {
				thumbFile = fileName;
			} else {
				thumbFile = ParameterConfig.getParameter().getAttachFile().getImageDefault() + thumbFile;
			}

			File file2 = new File(thumbFile);
			if (!file2.exists()) {
				thumbFile = ParameterConfig.getParameter().getAttachFile().getImageDefault() + "no_image.jpg";
			}

			BufferedImage bufferedImage = ImageUtil.resizeImageWithHint(thumbFile);
			base64 = "data:image/jpg;base64," + ImageUtil.getBase64(bufferedImage);

		} catch (Exception e) {
			throw e;
		}
		return base64;
	}

	public static String displayImage(String fileName, String contentType) throws Exception {
		String thumbFile = "";
		String base64 = "";
		try {
			// Check file type
			thumbFile = ParameterConfig.getMapContenType().get(contentType);

			if (thumbFile == null) {
				thumbFile = fileName;
			} else {
				thumbFile = ParameterConfig.getParameter().getAttachFile().getImageDefault() + thumbFile;
			}

			File file2 = new File(thumbFile);
			if (!file2.exists()) {
				thumbFile = ParameterConfig.getParameter().getAttachFile().getImageDefault() + "no_image.jpg";
				file2 = new File(thumbFile);
			}
			Path path = Paths.get(thumbFile);
			base64 = "data:image/jpg;base64," + ImageUtil.getBase64(Files.readAllBytes(path));

		} catch (Exception e) {
			throw e;
		}
		return base64;
	}
}
