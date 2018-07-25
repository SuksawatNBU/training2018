package util.sftp;

import java.util.Vector;

import util.cryptography.DecryptionUtil;
import util.cryptography.EncryptionUtil;
import util.sftp.SSHUtils;
import util.type.CrytographyType.DecType;
import util.type.CrytographyType.EncType;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.ChannelSftp.LsEntry;

public class TestSFTP {
	
 	public static String user = "root";
 	public static String password = "RedBird2013";
 	public static String host = "10.100.70.79";
 	public static int port=22;	
 	public static String key="D:\\Develop_doc\\APPS\\Cofig Dev\\aot_sftp1";	
    
	public static void main(String[] args) {
//		new TestSFTP().checkDirWithPrivateKey();
//		new TestSFTP().checkDir();
//		new TestSFTP().createDir();
//		new TestSFTP().uploadFile();
//		new TestSFTP().checkFile();
//		new TestSFTP().listFile();
//		new TestSFTP().downloadFile();
//		new TestSFTP().removeFile();
//		new TestSFTP().removeDirectory();
//		new TestSFTP().moveDirectory();
//		new TestSFTP().createSessionKey();
		new TestSFTP().genkey();
	}
	
	private  void createSessionKey()  {
		Session session = null;
		
		Channel channel = null;
		ChannelSftp sftp = null;
		
		String serverPath = "/opt/apps/sftp";
		String localPath = "C:\\opt\\ftp\\mis\\aotreproc\\";
		String fileName = "a.txt";
		try {
			JSch jsch = new JSch();
			
			String privateKey = key;
            String passphrase = password;

            jsch.addIdentity(privateKey,passphrase);
			
//			jsch.addIdentity(key);
//			jsch.setKnownHosts(key);
			
			java.util.Properties config = new java.util.Properties(); 
			config.put("StrictHostKeyChecking", "no");
			
			session = jsch.getSession(user, host, port);
			session.setPassword(password);
			session.setConfig(config);
			session.connect();
			
			System.out.println(session);
			
			
			channel = SSHUtils.createChanel(session);
			sftp = SSHUtils.createChannelSFTP(channel);
			
			 boolean checkDir = SSHUtils.checkDirectoryExists(sftp, serverPath);
		     System.out.println("checkDir: " + serverPath + " [" + checkDir + "]");
		     
		     
		     if(checkDir == false){
	        	SSHUtils.createDirectorys(sftp, serverPath);
	        }
		        
		        
		    SSHUtils.uploadFile(sftp, localPath, serverPath, fileName);
		    
		    System.out.println("UPLOAD COMPLETE");
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			SSHUtils.disconnectAll(session, channel, sftp);
		}
	}

	private void checkDirWithPrivateKey() {
		String host = "";
		int port = 22;
		String user =  "root";
		String password = "RedBird2013";
		String privateKeyPath = "D:/Desktop/2015/app-demo-doc/aot2.ppk";
		Session session = null;
        Channel channel = null;
        ChannelSftp sftp = null;
		String serverPath = "/root/Public/a/";
		
		try {
	        session = SSHUtils.createSession(host, port, user, password, privateKeyPath ,password);
	        channel = SSHUtils.createChanel(session);
	        sftp = SSHUtils.createChannelSFTP(channel);
	        
	        boolean checkDir = SSHUtils.checkDirectoryExists(sftp, serverPath);
	        System.out.println("checkDir: " + serverPath + " [" + checkDir + "]");
	        
	        
	        
	        
	        
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SSHUtils.disconnectAll(session, channel, sftp);
		}
	}
	
	private void checkDir() {
		Session session = null;
        Channel channel = null;
        ChannelSftp sftp = null;
		String serverPath = "/root/Public/a/";
		try {
	        session = SSHUtils.createSession(host, port, user, password);
	        channel = SSHUtils.createChanel(session);
	        sftp = SSHUtils.createChannelSFTP(channel);
	        
	        boolean checkDir = SSHUtils.checkDirectoryExists(sftp, serverPath);
	        System.out.println("checkDir: " + serverPath + " [" + checkDir + "]");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SSHUtils.disconnectAll(session, channel, sftp);
		}
	}
	
	private void createDir() {
		Session session = null;
        Channel channel = null;
        ChannelSftp sftp = null;
		String serverPath = "/tao/1/2/3/4/5";
		try {
	        session = SSHUtils.createSession(host, port, user, password);
	        channel = SSHUtils.createChanel(session);
	        sftp = SSHUtils.createChannelSFTP(channel);
	        
	        SSHUtils.createDirectorys(sftp, serverPath);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SSHUtils.disconnectAll(session, channel, sftp);
		}
	}
	
	private void uploadFile() {
		Session session = null;
        Channel channel = null;
        ChannelSftp sftp = null;
		String serverPath = "/tao/2";
		String fileName = "CEI_aotreproc.txt";
		try {
	        session = SSHUtils.createSession(host, port, user, password);
	        channel = SSHUtils.createChanel(session);
	        sftp = SSHUtils.createChannelSFTP(channel);
	        
	        SSHUtils.uploadFile(sftp, "c:/", serverPath, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SSHUtils.disconnectAll(session, channel, sftp);
		}
	}
	
	private void checkFile() {
		Session session = null;
        Channel channel = null;
        ChannelSftp sftp = null;
		String serverPath = "/tao/hkt.txtx";
		try {
	        session = SSHUtils.createSession(host, port, user, password);
	        channel = SSHUtils.createChanel(session);
	        sftp = SSHUtils.createChannelSFTP(channel);
	        
	        boolean checkFile = SSHUtils.checkFileExists(sftp, serverPath);
	        System.out.println("checkFile: " + serverPath + " [" + checkFile + "]");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SSHUtils.disconnectAll(session, channel, sftp);
		}
	}
	
	private void listFile() {
		Session session = null;
        Channel channel = null;
        ChannelSftp sftp = null;
		String serverPath = "/tao/";
		String filter = "*.txt";
		try {
	        session = SSHUtils.createSession(host, port, user, password);
	        channel = SSHUtils.createChanel(session);
	        sftp = SSHUtils.createChannelSFTP(channel);
	        
	        Vector<LsEntry> lsEntrys = SSHUtils.listFiles(sftp, serverPath, filter);
	        for (LsEntry lsEntry : lsEntrys) {
	        	 System.out.println("listFile: " + lsEntry.getFilename() + " [" + lsEntry.getAttrs().getSize() + "]");
	        }

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SSHUtils.disconnectAll(session, channel, sftp);
		}
	}

	private void downloadFile() {
		Session session = null;
        Channel channel = null;
        ChannelSftp sftp = null;
		String serverPath = "/tao/";
		String clientPath = "c:/tao/";
		String fileName = "z.txt";
		try {
	        session = SSHUtils.createSession(host, port, user, password);
	        channel = SSHUtils.createChanel(session);
	        sftp = SSHUtils.createChannelSFTP(channel);
	        
	        boolean complete = SSHUtils.downloadFile(sftp, serverPath, clientPath, fileName);
	        System.out.println("Download: " + serverPath + fileName + " > " + clientPath + fileName + " [" + complete + "]");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SSHUtils.disconnectAll(session, channel, sftp);
		}
	}
	
	private void removeFile() {
		Session session = null;
        Channel channel = null;
        ChannelSftp sftp = null;
		String serverPath = "/tao/";
		String fileName = "z.txt";
		try {
	        session = SSHUtils.createSession(host, port, user, password);
	        channel = SSHUtils.createChanel(session);
	        sftp = SSHUtils.createChannelSFTP(channel);
	        
	        boolean complete = SSHUtils.removeFile(sftp, serverPath, fileName);
	        System.out.println("Delete: " + serverPath + fileName + " [" + complete + "]");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SSHUtils.disconnectAll(session, channel, sftp);
		}
	}
	
	private void removeDirectory() {
		Session session = null;
        Channel channel = null;
        ChannelSftp sftp = null;
		String serverPath = "/tao/ax";
		try {
	        session = SSHUtils.createSession(host, port, user, password);
	        channel = SSHUtils.createChanel(session);
	        sftp = SSHUtils.createChannelSFTP(channel);
	        
	        boolean complete = SSHUtils.removeDirectory(sftp, serverPath);
	        System.out.println("Delete: " + serverPath + " [" + complete + "]");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SSHUtils.disconnectAll(session, channel, sftp);
		}
	}
	
	private void moveDirectory() {
		Session session = null;
        Channel channel = null;
        ChannelSftp sftp = null;
		String sourcePath = "/tao/";
		String sourceName = "a.txt";
		String destinationPath = "/tao/a2/";
		String destinationName = "a2.txt";
		try {
	        session = SSHUtils.createSession(host, port, user, password);
	        channel = SSHUtils.createChanel(session);
	        sftp = SSHUtils.createChannelSFTP(channel);
	        
	        boolean complete = SSHUtils.moveFile(sftp, sourcePath, sourceName, destinationPath, destinationName);
	        System.out.println("Move: " + sourcePath + sourceName + " > " + destinationPath + destinationName + " [" + complete + "]");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SSHUtils.disconnectAll(session, channel, sftp);
		}
	}
	
	private void genkey(){
		
		try{
			
		
		String c = "RedBird2013";
		
		String d = EncryptionUtil.doEncrypt(c,EncType.AES128);
		System.out.println(d);
		
		
		System.out.println(DecryptionUtil.doDecrypt(d, DecType.AES128));
		
		
		
		}
catch (Exception e){
	
}
		
		
	}
}