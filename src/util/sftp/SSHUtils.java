package util.sftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Collections;
import java.util.Vector;

import util.cryptography.DecryptionUtil;
import util.file.FileManagerUtil;
import util.type.CrytographyType.DecType;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SSHUtils {

	public enum Delimeter {
		DOT("."), BLANK(" "), EMPTY(""), MINUS("-"), PLUS("+"), COMMA(","), COLON(":"), EQUAL("="), SLASH("/"), BACKSLASH("\\"), STAR("*"), SHARP("#");

		private String value;

		private Delimeter(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 1. Create session (Complete)
	 * 
	 * @param host
	 * @param port
	 * @param user
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static Session createSession(String host, int port, String user, String password) throws Exception {
		Session session = null;
		try {
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");

			JSch jsch = new JSch();
			session = jsch.getSession(user, host, port);
			session.setConfig(config);
			session.setPassword(password);
			session.connect();
		} catch (Exception e) {
			throw e;
		}
		return session;
	}
	
	/**
	 * 1. Create session ยังทดสอบไม่ได้
	 * @param host
	 * @param port
	 * @param user
	 * @param password
	 * @param privateKeyPath //Key Gen OpenSSH  (Putty genkey (keyเดิม)--> export OpenSSH) 
	 * @param passphrase  // password Key (key เดียวกับ pass)
	 * @return
	 * @throws Exception
	 */
	public static Session createSession(String host, int port, String user, String password, String privateKeyPath ,String passphrase) throws Exception {
		Session session = null;
		try {
			
			passphrase = DecryptionUtil.doDecrypt(passphrase, DecType.AES128);
			
			JSch jsch = new JSch();
			jsch.addIdentity(privateKeyPath,passphrase);
			
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			
			session = jsch.getSession(user, host, port);
			session.setPassword(password);
			session.setConfig(config);
			session.connect();
		} catch (Exception e) {
			throw e;
		}
		return session;
	}

	/**
	 * 2. Create chennel (Complete)
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static Channel createChanel(Session session) throws Exception {
		Channel channel = null;
		try {
			channel = session.openChannel("sftp");
			channel.connect();
		} catch (Exception e) {
			throw e;
		}
		return channel;
	}

	/**
	 * 3. Create SFTP with channel (Complete)
	 * 
	 * @param channel
	 * @return
	 * @throws Exception
	 */
	public static ChannelSftp createChannelSFTP(Channel channel) throws Exception {
		ChannelSftp sftp = null;
		try {
			sftp = (ChannelSftp) channel;
		} catch (Exception e) {
			throw e;
		}
		return sftp;
	}

	/**
	 * Disconnect (Complete)
	 * 
	 * @param session
	 * @param channel
	 * @throws Exception
	 */
	public static void disconnectAll(Session session, Channel channel, ChannelSftp sftp) {
		disconnectSFTP(sftp);
		disconnectChannel(channel);
		disconnectSession(session);
	}

	/**
	 * Disconnect SFTP (Complete)
	 * 
	 * @param sftp
	 */
	public static void disconnectSFTP(ChannelSftp sftp) {
		try {
			if (sftp != null) {
				sftp.disconnect();
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Disconnect Channel (Complete)
	 * 
	 * @param channel
	 */
	public static void disconnectChannel(Channel channel) {
		try {
			if (channel != null) {
				channel.disconnect();
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Disconnect Session (Complete)
	 * 
	 * @param session
	 */
	public static void disconnectSession(Session session) {
		try {
			if (session != null) {
				session.disconnect();
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Move file (Complete)
	 * @param sftp
	 * @param sourcePath
	 * @param sourceName
	 * @param destinationPath
	 * @param destinationName
	 * @return
	 */
	public static boolean moveFile(ChannelSftp sftp, String sourcePath, String sourceName, String destinationPath, String destinationName) {
		
		boolean success = false;
		
		try {
			if (SSHUtils.checkDirectoryExists(sftp, destinationPath) == false) {
				SSHUtils.createDirectorys(sftp, destinationPath);
			}
			
			sftp.cd(sourcePath);
			if (sftp.get( sourceName ) != null){
			    sftp.rename(sourcePath + sourceName, destinationPath + destinationName );
			}
			
			success = true;
		} catch (Exception e) {
			// e.printStackTrace();
		}
		
		return success;
	}

	/**
	 * ลบไฟล์ (Complete)
	 * @param sftp
	 * @param serverPath
	 * @param fileName
	 * @return
	 */
	public static boolean removeFile(ChannelSftp sftp, String serverPath, String fileName) {
		
		boolean success = false;
		
        try {
        	
        	// change directory ที่ SFTP
    		sftp.cd(serverPath);
    		
    		// ลบไฟล์
        	sftp.rm(fileName);
        	
        	success = true;
        } catch (Exception ex) {
        	// ex.printStackTrace();
        } finally {

        }
        
        return success;
	}
	
	/**
	 * ลบ Directory<br>
	 * ยังไม่สามารลบ Directory ที่มี Sub Directory ได้
	 * @param sftp
	 * @param serverPath
	 * @param fileName
	 * @return
	 */
	public static boolean removeDirectory(ChannelSftp sftp, String serverPath) {
		
		boolean success = false;
		
        try {
        	
        	// change directory ที่ SFTP
    		sftp.cd(serverPath);
    		
    		// ลบไฟล์
        	sftp.rmdir(serverPath);
        	
        	success = true;
        } catch (Exception ex) {
        	// ex.printStackTrace();
        } finally {

        }
        
        return success;
	}

	/**
	 * Download file to client (Complete)
	 * @param sftp
	 * @param serverPath
	 * @param clientPath
	 * @param fileName
	 */
	public static boolean downloadFile(ChannelSftp sftp, String serverPath, String clientPath, String fileName) {
		
		boolean success = false;
		
        FileOutputStream clientInputStream = null;
        try {
        	
        	// สร้าง directory ฝั่ง client
        	FileManagerUtil.crateDirectoryWithoutOverwrite(clientPath);
    		
        	// change directory ที่ SFTP
    		sftp.cd(serverPath);
    		
    		// download file to client
        	clientInputStream = new FileOutputStream(new File(clientPath + fileName));
        	sftp.get(fileName, clientInputStream);
        	
        	success = true;
        } catch (Exception ex) {
        	// ex.printStackTrace();
        } finally {
        	try {
				clientInputStream.close();
			} catch (Exception e) {
				// e.printStackTrace();
			}	
        }
        
        return success;
	}

	/**
	 * List file in directory (Complete)
	 * @param sftp
	 * @param serverPath
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Vector<LsEntry> listFiles(ChannelSftp sftp, String serverPath, String filter) {
		
		Vector<LsEntry> vEntry = new Vector<LsEntry>();
		
		try {
			if (serverPath == null) {
				serverPath = Delimeter.EMPTY.getValue();
			}
			
			if (filter == null) {
				filter = Delimeter.EMPTY.getValue();
			}
			
			vEntry = sftp.ls(serverPath + filter);
			Collections.sort(vEntry);
		} catch (Exception e) {

		}
		return vEntry;
	}

	/**
	 * Upload file (Complete)
	 * 
	 * @param sftp
	 * @param localPath
	 * @param serverPath
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static boolean uploadFile(ChannelSftp sftp, String localPath, String serverPath, String fileName) throws Exception {

		boolean uploadSuccess = false;
		boolean mkdirSuccess = true;

		try {

			if (SSHUtils.checkDirectoryExists(sftp, serverPath) == false) {
				// ไม่มี Directory ให้สร้าง
				SSHUtils.createDirectorys(sftp, serverPath);
				mkdirSuccess = true;
			}

			if (mkdirSuccess) {
				// change directory
				sftp.cd(serverPath);

				FileInputStream clientInputStream = null;
				try {
					// upload file to sftp
					clientInputStream = new FileInputStream(localPath + fileName);
					sftp.put(clientInputStream, fileName);

					uploadSuccess = true;
				} catch (Exception e) {
					throw e;
				} finally {
					try {
						clientInputStream.close();
					} catch (Exception ex) {

					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return uploadSuccess;
	}

	/**
	 * สร้าง Directory (Complete)
	 * 
	 * @param sftp
	 * @param serverPath
	 * @throws Exception
	 */
	public static void createDirectorys(ChannelSftp sftp, String serverPath) throws Exception {
		String[] serverPathArray = serverPath.split(Delimeter.SLASH.getValue());
		String createPath = Delimeter.EMPTY.getValue();
		for (String path : serverPathArray) {
			if (path.trim().isEmpty()) {
				continue;
			}
			createPath += Delimeter.SLASH.getValue() + path;
			if (SSHUtils.checkDirectoryExists(sftp, createPath) == false) {
				sftp.mkdir(createPath);
			}
		}
	}

	/**
	 * ตรวจสอบ Directory (Complete)
	 * 
	 * @param sftp
	 * @param serverPath
	 * @return
	 * @throws Exception
	 */
	public static boolean checkDirectoryExists(ChannelSftp sftp, String serverPath) {
		boolean directoryExists = false;
		try {
			if (sftp.stat(serverPath) != null) {
				directoryExists = true;
			}
		} catch (Exception e) {

		}
		return directoryExists;
	}

	/**
	 * ตรวจสอบ File (Complete)
	 * 
	 * @param sftp
	 * @param serverPath
	 * @return
	 */
	public static boolean checkFileExists(ChannelSftp sftp, String serverPath) {
		boolean fileExists = false;
		try {
			if (sftp.stat(serverPath) != null) {
				fileExists = true;
			}
		} catch (Exception e) {

		}
		return fileExists;
	}

	/*
	 * public static void main(String[] args) { JSch jsch = new JSch(); Session session = null; Channel channel = null;
	 * 
	 * 
	 * String user = "root"; String password = "nsurex2012"; String host = "10.100.70.159"; int port=22; String directory = "/root/Public/a";
	 * 
	 * String remoteFile="C:/opt/ftp/mis/aotreproc/CEI_aotreproc.txt"; String remoteFile1="C:/opt/ftp/mis/aotreproc/HKT_aotreproc.txt"; try{
	 * 
	 * 
	 * 
	 * 
	 * java.util.Properties config = new java.util.Properties(); config.put("StrictHostKeyChecking", "no");
	 * 
	 * session = jsch.getSession(user, host, port); session.setConfig(config); session.setPassword(password); session.connect(); channel = session.openChannel("sftp"); channel.connect();
	 * 
	 * ChannelSftp sftp = (ChannelSftp) channel; sftp.cd(directory); File f1 = new File(remoteFile); sftp.put(new FileInputStream(f1), f1.getName()); File f2 = new File(remoteFile1); sftp.put(new
	 * FileInputStream(f2), f2.getName());
	 * 
	 * 
	 * channel.disconnect(); session.disconnect();
	 * 
	 * String s = "a.txt"; System.out.println(s.split("\\.", -1)[0]); }catch (Exception e) { e.printStackTrace(); } }
	 */

}