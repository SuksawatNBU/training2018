package util.ftp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.PropertyConfigurator;

public class FTPUtil {

	public static FTPClient connectFTPClient(String host, int port, String user, String password) throws Exception {
		FTPClient client = null;
		try {
			client = new FTPClient();
			client.connect(host, port);
			client.login(user, password);
			client.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
			client.setFileTransferMode(FTP.BINARY_FILE_TYPE);
			client.enterLocalPassiveMode();

		} catch (Exception e) {
			throw e;
		}
		return client;
	}

	public static void disconnectFTPClient(FTPClient client) {
		try {
			if (client != null) {
				client.logout();
				client.disconnect();
			}
		} catch (Exception e) {

		}
	}

	public static boolean moveFile(FTPClient client, String sourcePath, String destinationPath, String fileName, String newFileName) throws Exception {
		boolean moveSuccess = false;
		boolean mkdirSuccess = true;

		try {

			if (!checkDirectoryExists(client, destinationPath)) {
				mkdirSuccess = client.makeDirectory(destinationPath);
			}

			if (mkdirSuccess) {
				moveSuccess = client.rename(sourcePath + fileName, destinationPath + (newFileName != null && !newFileName.equals("") ? newFileName : fileName));
			}

		} catch (Exception e) {
			throw e;
		}
		return moveSuccess;
	}

	public static boolean removeFile(FTPClient client, String destinationPath, String fileName) throws Exception {
		boolean removeSuccess = false;

		try {
			removeSuccess = client.deleteFile(destinationPath + fileName);
		} catch (Exception e) {
			throw e;
		}
		return removeSuccess;
	}

	public static boolean downloadFile(FTPClient client, String serverPath, String localPath, String fileName) throws Exception {
		boolean success = false;

		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(localPath + fileName);
			success = client.retrieveFile(serverPath + fileName, fos);
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				fos.close();
			} catch (Exception e) {

			}
		}
		return success;
	}

	public static boolean uploadFile(FTPClient client, String localPath, String serverPath, String fileName) throws Exception {

		boolean uploadSuccess = false;
		boolean mkdirSuccess = true;

		try {

			if (!checkDirectoryExists(client, serverPath)) {
				mkdirSuccess = client.makeDirectory(serverPath);
			}

			if (mkdirSuccess) {

				FileInputStream fis = null;

				fis = new FileInputStream(localPath + fileName);

				uploadSuccess = client.storeFile(serverPath + fileName, fis);

				fis.close();

			}

		} catch (Exception e) {
			throw e;
		}
		return uploadSuccess;
	}

	public static boolean uploadFile(FTPClient client, InputStream is, String serverPath, String fileName) throws Exception {
		// LogUtil.SENDEMR.info("uploadFile");

		boolean uploadSuccess = false;
		boolean mkdirSuccess = true;

		try {

			if (!checkDirectoryExists(client, serverPath)) {
				mkdirSuccess = client.makeDirectory(serverPath);
				// LogUtil.SENDEMR.debug("Create directory " + serverPath + " : success = " + mkdirSuccess);
			}

			if (mkdirSuccess) {

				uploadSuccess = client.storeFile(serverPath + fileName, is);

			}

		} catch (Exception e) {
			throw e;
		}
		return uploadSuccess;
	}

	public static boolean checkDirectoryExists(FTPClient client, String dirPath) throws Exception {
		// LogUtil.SENDEMR.info("checkDirectoryExists");

		boolean directoryExists = false;

		try {

			client.changeWorkingDirectory(dirPath);
			int replyCode = client.getReplyCode();
			if (replyCode == 550) {
				// LogUtil.SENDEMR.debug("Directory doesn't exist.");
			} else {
				directoryExists = true;
				// LogUtil.SENDEMR.debug("Directory is existed.");
			}

		} catch (Exception e) {
			throw e;
		}
		return directoryExists;
	}

	public static boolean checkFileExists(FTPClient client, String filePath) throws Exception {
		// LogUtil.SENDEMR.info("checkFileExists");

		boolean fileExists = false;

		try {

			InputStream inputStream = client.retrieveFileStream(filePath);
			int replyCode = client.getReplyCode();
			if (inputStream == null || replyCode == 550) {
				// LogUtil.SENDEMR.debug("File doesn't exist.");
			} else {
				fileExists = true;
				// LogUtil.SENDEMR.debug("File is existed.");
			}

			if (inputStream != null) {
				inputStream.close();
				client.completePendingCommand();
			}

		} catch (Exception e) {
			throw e;
		}
		return fileExists;
	}

	public static FTPFile[] listFiles(FTPClient client, String ftpPath, String filter) {
		FTPFile[] ftpFileArray = null;

		try {
			if (ftpPath == null) {
				ftpPath = "";
			}
					
			if ((filter != null) && (!filter.isEmpty())) {
				ftpFileArray = client.listFiles(ftpPath, new FTPFileFilter(filter));
			} else {
				ftpFileArray = client.listFiles(ftpPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ftpFileArray;
	}

	public static void main(String[] args) {
		PropertyConfigurator.configure("mfapassportprocess-log4j.properties");
		FTPClient client = null;
		try {

			client = FTPUtil.connectFTPClient("10.100.70.166", 21, "epaymentftp", "FTPepayment2012");

			
			FTPFile[] ftpFileArray = FTPUtil.listFiles(client, "xyz", "xyz.*");
			System.out.println(ftpFileArray.length);
			for (FTPFile ftpFile : ftpFileArray) {
				System.out.println(ftpFile);
				System.out.println(ftpFile.getSize());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}