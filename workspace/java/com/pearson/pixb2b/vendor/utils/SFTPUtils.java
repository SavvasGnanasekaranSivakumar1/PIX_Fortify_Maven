/**
 * Copyright 2009 by Pearson,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Pearson ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Pearson.
 * Title		: 	SFTPUtils.java
 * Company 		: 	CTS
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Nithya S	29 Sep, 2015	,Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.utils; 

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
 
/**
 * SFTPUtils is a utility class to connect with the SFTP server 
 * and download/upload files from/in a given directory at SFTP server.
 * 
 * @author Nithya CTS - Added for FTP to SFTP Migration
 */
public class SFTPUtils { 
	private String hostName 	= null;
	private String userName 	= null;
	private String password 	= null;
	
	private int defaultPort		= 22;	
	String privateKey = null;
	
	Session session = null;
	Channel channel = null; 
	ChannelSftp channelSftp = null;

	/**
	 * Default constructor
	 */
	public SFTPUtils()throws IOException {		
		
	}	
	
	/**
	 * @param hostName
	 * @param userName
	 * @param password
	 */
	public SFTPUtils(String hostName, String userName, String password) {
		super();
		this.hostName = hostName;
		this.userName = userName;
		this.password = password;
	}
	/**
	 * @return Returns the hostName.
	 */
	public String getFtpHost() {
		return hostName;
	}
	/**
	 * @param hostName The hostName to set.
	 */
	public void setFtpHost(String hostName) {
		this.hostName = hostName;
	}
	/**
	 * @return Returns the port.
	 */
	public int getPort() {
		return defaultPort;
	}
	
	/**
	 * @param port The port to set.
	 */
	public void setPort(int port) {
		this.defaultPort = port;
	}
	/**
	 * This method connects the user with SFTP server.
	 * @param hostName
	 * @param userName
	 * @param password
	 * @param port
	 * @return boolean
	 * @throws JSchException
	 * @throws IOException
	 */
	public boolean connect(String hostName, String userName, String password, int port) throws JSchException {
		this.defaultPort = port;
		return connect (hostName, userName, password);
	}
	/**
	 * This method connects the user with SFTP server.
	 * @param hostName
	 * @param userName
	 * @param password
	 * @return boolean
	 * @throws JSchException
	 * @throws IOException
	 */
	private boolean connect (String hostName, String userName, String password) throws JSchException {
		this.hostName = hostName;
		this.userName = userName;
		this.password = password;
		return connect();
	}
	/**
	 * This method connects with the SFTP server.
	 * @return boolean
	 * @throws JSchException	 
	 */
	public boolean connect() throws JSchException {
		boolean returnStatus = false;		
			JSch jsch = new JSch();
			//try{
			if (userName != null && password != null){				
				String pcsHome = System.getenv("HOME");						
				privateKey = pcsHome+"/.ssh/id_rsa";
				//String knownHosts = pcsHome+"/.ssh/known_hosts"; 							
				jsch.addIdentity(privateKey);
				//jsch.setKnownHosts(knownHosts);
				B2BLogger.debug(privateKey + "Identity added");
				session = jsch.getSession(userName,hostName,defaultPort); 				
				//session.setPassword(password);
				java.util.Properties config = new java.util.Properties();
				config.put("StrictHostKeyChecking", "no");
				B2BLogger.debug("StrictHostKeyChecking = no");
				session.setConfig(config); 
				session.connect();
				channel = session.openChannel("sftp");
				channel.connect();
				channelSftp = (ChannelSftp)channel;				
				returnStatus = session.isConnected();			
			if(!returnStatus) 
			throw new JSchException("Error connecting to SFTP Server [" + hostName +"]");
			}
			//}catch(IOException exception){
				//B2BLogger.error("IOException "+exception.getMessage());
			//}
		return returnStatus;
	}
	/**
	 * This method checks the connection is still open or closed.
	 * @return boolean
	 */
	public boolean isConnected() {
		boolean connected = false;     
		if (session != null) connected = session.isConnected();
		return connected;
	}
	/**
	 * This method create a directory at SFTP server.
	 * @param dirPath
	 * @return boolean
	 * @throws SftpException
	 */
	public boolean makeDir(String dirPath){	
		boolean success = false;
		try{
			//B2BLogger.debug("SFTPUtil.mkdir "+dirPath);									
			String[] folders  = dirPath.split("/");		
			/*for ( String folder : folders ) {
				B2BLogger.debug("*"+folder);
			}*/
			if(dirPath.startsWith("/")){
				folders[1]="/"+folders[1];
			}	
			
			for ( String folder : folders ) {
			    if ( folder.length() > 0 ) {
			        try {				        	
			        	channelSftp.cd( folder );			    
			        }catch ( SftpException e ) {
			        	B2BLogger.debug("SFTPUtil.makdir "+folder);
			        	channelSftp.mkdir( folder );
			        	if(folder.startsWith("/")){
			        		B2BLogger.error("SFTPUtil.mkdir()- cannot create directory: "+folder+" Permission denied");
						}		        	
			        	channelSftp.cd( folder );
			        }
			    }
			}
			success = true;			
		}catch(SftpException e){
			B2BLogger.error("SFTPUtil.makeDir() "+e.getMessage());
		}
		return success;	
	}
	/**
	 * This method change the server directory.
	 * @param dirPath
	 * @return boolean
	 * @throws SftpException
	 */
	public boolean changeServerDir (String dirPath)  {
		boolean success = false;
		try{	
			B2BLogger.debug("SFTPUtil.cd "+dirPath);
			channelSftp.cd(dirPath);
			success = true;			
		}catch(SftpException e){
			 B2BLogger.error("SFTPUtil.changeServerDir() "+e.getMessage());
		}
		return success;
	}
	/**
	 * This method return the file names from SFTP server directory.
	 * @return String Array
	 * @throws SftpException
	 */
	public ArrayList<String> listFileNames(String path) throws SftpException {
		Vector<ChannelSftp.LsEntry> list = channelSftp.ls("*.xml");
		ArrayList<String> fileNames = new ArrayList<String>();	
		if(channelSftp != null){
			for(ChannelSftp.LsEntry entry : list) {	
				
				fileNames.add(entry.getFilename());
			}
			
		}
		return fileNames;
	}
	/**
	 * This method downloads files from SFTP server directory
	 * @param remoteFileName
	 * @param localFileName
	 * @return boolean
	 * @throws IOException
	 */
	public boolean download(String remoteFileName, String localFileName){
		FileOutputStream localFile = null;
		boolean success = false;
		try {			
			localFile = new FileOutputStream(localFileName);	
			//B2BLogger.debug("Local file name:: " + localFileName);			
			//B2BLogger.debug("Remote file :: " + remoteFileName);
			channelSftp.get(remoteFileName,localFile);	
			B2BLogger.info("File : "+ remoteFileName+" Downloaded Successfully");			
			localFile.flush();
			localFile.close();
			success = true;
		} catch (FileNotFoundException e) {
			B2BLogger.error("FileNotFoundException :: " + StringUtils.getStackTrace(e));
		}catch(SftpException e){
			B2BLogger.error("SftpException :: " + StringUtils.getStackTrace(e));
		}catch (IOException e) {
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
		} finally{
			localFile = null;
		}
		return success;
	}
	/**
	 * This method deletes files from SFTP server directory.
	 * @param dirPath
	 * @return boolean
	 * @throws IOException
	 */
	public boolean deleteFile(String dirPath){
		boolean success = false;
		try{
			B2BLogger.debug("SFTPUtil.rm "+dirPath);
			channelSftp.rm(dirPath);
			success = true;			
		}catch(SftpException e){
			 B2BLogger.error("SFTPUtil.deleteFile() "+e.getMessage());
		}
		return success;
	}
	/**
	 * Parses and returns short-file name from fully qualified path & file name
	 * @param fullPathFileName
	 * @return String
	 */
	public static String parseFileName (String fullPathFileName) {
		String shortFileName = "";
		int pos1 = fullPathFileName.lastIndexOf("/");
		int pos2 = fullPathFileName.lastIndexOf("\\");
		if (pos2 > pos1) pos1 = pos2;

		pos2 = fullPathFileName.lastIndexOf(":");
		if (pos2 > pos1) pos1 = pos2;

		shortFileName = fullPathFileName.substring(pos1+1);
		return shortFileName;
	}
	/**
	 * This method uploads files at SFTP server
	 * @param localFileName
	 * @return boolean
	 * @throws IOException
	 */
	public boolean upload(String localFileName){
		return upload(localFileName, parseFileName(localFileName));
	}	
	/**
	 * This method uploads files at SFTP server 
	 * @param localFileName
	 * @param remoteFileName
	 * @return boolean
	 * @throws IOException
	 */
	public boolean upload(String localFileName, String remoteFileName) {
		boolean success = false;
		try{
			FileInputStream inputFile = new FileInputStream(localFileName);		
			//B2BLogger.info("Local file name:: " + localFileName);			
			//B2BLogger.info("Remote file :: " + remoteFileName);
			channelSftp.put(inputFile,remoteFileName);
			B2BLogger.info("File : "+ remoteFileName+"Uploaded Successfully");			
			inputFile.close();
			success = true;
		} catch (FileNotFoundException e) {
			B2BLogger.error("FileNotFoundException :: " + StringUtils.getStackTrace(e));
		}catch(SftpException e){
			B2BLogger.error("SftpException :: " + StringUtils.getStackTrace(e));
		}catch (IOException e) {
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
		}
		return success;
		
	}
	/**
	 * This method disconnect with the SFTP server.
	 * @return boolean
	 * @throws IOException
	 */
	public boolean disconnect (){
		boolean returnStatus = false;
		try{
			if (channelSftp.isConnected()){
				channelSftp.disconnect();
				channel.disconnect();
			    session.disconnect();
			}
		returnStatus = true;
		}catch(Exception e){
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));			
		}
		return returnStatus;
	}	
	
	/**
	 * This main method is a TEST method for testing the SFTP server.
	 * Please comment this method in PreQA/Production environment.
	 * @param args
	 */
 /*public static void main(String[] args) {
		try {
			System.out.println("Initializing ..");
			SFTPUtils sftpUtils = new SFTPUtils("casper.prenhall.com", "pcsdev", "pcsdev");
			

			System.out.println("Connecting ..");
			System.out.println("sftpUtils.connect() :: " + sftpUtils.connect());
			

			System.out.println("Changing current dir ..");
			System.out.println("sftpUtils.changeServerDir(..) :: "  + sftpUtils.changeServerDir("/home/pcsdev/pixb2b/b2b/test"));
			

			System.out.println("Downloading ..");
			System.out.println("sftpUtils.download() :: " + sftpUtils.download("abc.xml", "D:\\Nithya\\Project\\FTP_SFTP\\downloads\\rbc"));
			
						
			System.out.println("sftpUtils.disconnect() :: " + sftpUtils.disconnect());			
			System.out.println("Done.");		
		}catch (JSchException e) {
			System.out.println(e);
		} catch(Exception e){
			System.out.println(e);
		}	
	}*/ 
}