package util.sftp;

import java.io.File;
import java.io.FileInputStream;
import java.util.Vector;

import util.log.LogUtil;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public class SshUtils_temp {
	
	/**
	 * 1.open session
	 * @param user
	 * @param host
	 * @param port
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static Session newSession (String user, String host ,int port ,String password) throws Exception{
		
		JSch jsch = new JSch();
		Session session = null;
		try{
			java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            
            session = jsch.getSession(user, host, port);
            session.setConfig(config);
            session.setPassword(password);
            session.connect();
	        
		}catch (Exception e){
			throw e;
		}
		
		return session;
	}
	
	/**
	 * 2. open chennel
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public static Channel newChanel (Session session) throws Exception{
		
		Channel channel = null;
		try{
			channel = session.openChannel("sftp");
            channel.connect();
			
		}catch (Exception e){
			throw e;
		}
		
		return channel;
	}
	
	/**
	 * 3. disconnect
	 * @param session
	 * @param channel
	 * @throws Exception
	 */
	public static void disconnectSFTP(Session session,Channel channel) throws Exception {

		try {

			channel.disconnect();
            session.disconnect();

		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * connect Channel SFTP
	 * @param channel
	 * @return
	 * @throws Exception
	 */
	public static ChannelSftp connectChannelSftp (Channel channel) throws Exception {

		ChannelSftp sftp = null;
		try {

			sftp = (ChannelSftp) channel;

		} catch (Exception e) {
			throw e;
		}
		
		return sftp;
	}
	
	/**
	 * uploadFile
	 * @param user
	 * @param host
	 * @param port
	 * @param password
	 * @param localPath
	 * @param sftpPath
	 * @param fileName
	 * @param newFileName
	 * @return
	 * @throws Exception
	 */
	public static boolean uploadFile(String user, String host ,int port ,String password , String localPath, String sftpPath, String fileName, String newFileName) throws Exception {

		boolean uploadFile = false;

		Channel channel = null;
		Session session = null;
		try {
			
			/* Connect Session*/
			session = SshUtils_temp.newSession(user, host, port, password);
    		
			/* Connect channel*/
    		channel = SshUtils_temp.newChanel(session);
    		
    		/* Connect UploadFile*/
    		uploadFile = SshUtils_temp.uploadFile(channel, localPath, sftpPath, fileName, newFileName);
    		
    		/* Disconnect Connect*/
    		SshUtils_temp.disconnectSFTP(session, channel);
			

		} catch (Exception e) {
			throw e;
		}
		return uploadFile;
	}
	
	
	/**
	 * upload file
	 * @param channel
	 * @param localPath
	 * @param sftpPath
	 * @param fileName
	 * @param newFileName
	 * @return
	 * @throws Exception
	 */
	public static boolean uploadFile(Channel channel, String localPath, String sftpPath, String fileName, String newFileName) throws Exception {

		boolean uploadFile = false;
		boolean mkdirSuccess = true;

		SftpATTRS attrs=null;
		try {
			
	        
			ChannelSftp sftp = connectChannelSftp(channel);
	        
	        /* Check Directory Exist */
	        try {
			    attrs = sftp.stat(sftpPath);
			} catch (Exception e) {
			    LogUtil.UTIL.debug(sftpPath +": not found");
			}

	        /* Create Directory*/
			if (attrs != null) {
			    LogUtil.UTIL.debug("Directory exists IsDir="+attrs.isDir());
			} else {
				
				try{
					LogUtil.UTIL.debug("Creating dir "+sftpPath);
				    sftp.mkdir(sftpPath);
				    mkdirSuccess = true;
				    
				}catch (Exception e){
					mkdirSuccess = false;
					 LogUtil.UTIL.debug(sftpPath +": not found");
				}
			   
			}
			
			 if (mkdirSuccess) {
				 sftp.cd(sftpPath);
		            
	             File f1 = new File(localPath+fileName);
	             sftp.put(new FileInputStream(f1), newFileName);
	            
	             uploadFile = true;
			 }

		} catch (Exception e) {
			throw e;
		}
		return uploadFile;
	}
	
	/**
	 * Remove file
	 * @param sftp
	 * @param sftpPath
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static boolean removeFile(ChannelSftp sftp, String sftpPath, String fileName) throws Exception {
		//LogUtil.SENDEMR.info("moveFile");

		boolean removeSuccess = false;

		try {
			
			sftp.cd(sftpPath); 
			sftp.rmdir(fileName);
			
			removeSuccess = true;
			
		} catch (Exception e) {
			throw e;
		}
		return removeSuccess;
	}

	
	/**
	 * 
	 * @param sftp
	 * @param dirPath
	 * @return
	 * @throws Exception
	 */
	public static boolean checkDirectoryExists(ChannelSftp sftp, String dirPath) throws Exception {
		//LogUtil.SENDEMR.info("checkDirectoryExists");

		boolean directoryExists = false;
		SftpATTRS attrs=null;
		try {

			 /* Check Directory Exist */
	        try {
			    attrs = sftp.stat(dirPath);
			    
			    directoryExists = true;
			    
			} catch (Exception e) {
			    LogUtil.UTIL.debug(dirPath +": not found");
			}
	        

			 

		} catch (Exception e) {
			throw e;
		}
		return directoryExists;
	}
	
	
	/**
	 * 
	 * @param sftp
	 * @param dirPath
	 * @return
	 * @throws Exception
	 */
	public static boolean createDir(ChannelSftp sftp, String dirPath) throws Exception {
		//LogUtil.SENDEMR.info("checkDirectoryExists");

		boolean directoryExists = false;
		SftpATTRS attrs=null;
		try {

			String[] folders = dirPath.split("/");
			  for (String folder : folders) {
			    if (folder.length() > 0 && !folder.contains(".")) {
			      // This is a valid folder:
			      try {
			    	  sftp.cd(folder);
			      } catch (SftpException e) {
			        // No such folder yet:
			    	  sftp.mkdir(folder);
			    	  sftp.cd(folder);
			      }
			    }
			  }
			 

		} catch (Exception e) {
			throw e;
		}
		return directoryExists;
	}
	
	
	public static Vector<ChannelSftp.LsEntry> listFile(ChannelSftp sftp, String dirPath) throws Exception {
		//LogUtil.SENDEMR.info("checkDirectoryExists");
		
		SftpATTRS attrs=null;
		Vector<ChannelSftp.LsEntry> list  = new Vector<>();
		
		try {
			
			sftp.cd(dirPath);
			list = sftp.ls("*.txt");
			
			
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	

	public static void main(String[] args) {
		 JSch jsch = new JSch();
        Session session = null;
        Channel channel = null;
        
        
        String user = "root";
        String password = "nsurex2012";
        String host = "10.100.70.159";
        int port=22;
        String directory = "/root/Public/a";

        String remoteFile="C:/opt/ftp/mis/aotreproc/CEI_aotreproc.txt";
        String remoteFile1="C:/opt/ftp/mis/aotreproc/HKT_aotreproc.txt";
		try{
			java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            
            session = jsch.getSession(user, host, port);
            session.setConfig(config);
            session.setPassword(password);
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
     
            ChannelSftp sftp = (ChannelSftp) channel;
            sftp.cd(directory);
            File f1 = new File(remoteFile);
            sftp.put(new FileInputStream(f1), f1.getName());
            File f2 = new File(remoteFile1);
            sftp.put(new FileInputStream(f2), f2.getName());

     
            channel.disconnect();
            session.disconnect();
		    
			String s = "a.txt";
			System.out.println(s.split("\\.", -1)[0]);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}