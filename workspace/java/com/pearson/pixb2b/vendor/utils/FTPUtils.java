/**
 * Copyright 2009 by Pearson,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Pearson ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Pearson.
 *
 * Title		: 	FTPUtils.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	05 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
/**
 * FTPUtils is a utility class to connect with the FTP server 
 * and download/upload files from/in a given directory at FTP server.
 * 
 * @author Yogesh Tyagi
 */
public class FTPUtils {
	private String hostName 	= null;
	private String userName 	= null;
	private String password 	= null;
	
	private int defaultPort		= 21;	
	private String ftpReply 	= null;	
	
	private FTPClient ftpClient = null;
	
	/**
	 * Default constructor
	 */
	public FTPUtils() {
		super();
	}	
	/**
	 * @param hostName
	 * @param userName
	 * @param password
	 */
	public FTPUtils(String hostName, String userName, String password) {
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
	 * @return the ftpReply
	 */
	public String getFtpReply() {
		return ftpReply;
	}
	/**
	 * This method connects the user with FTP server.
	 * @param hostName
	 * @param userName
	 * @param password
	 * @param port
	 * @return boolean
	 * @throws SocketException
	 * @throws IOException
	 */
	public boolean connect(String hostName, String userName, String password, int port) throws SocketException, IOException {
		this.defaultPort = port;
		return connect (hostName, userName, password);
	}
	/**
	 * This method connects the user with FTP server.
	 * @param hostName
	 * @param userName
	 * @param password
	 * @return boolean
	 * @throws SocketException
	 * @throws IOException
	 */
	private boolean connect (String hostName, String userName, String password) throws SocketException, IOException {
		this.hostName = hostName;
		this.userName = userName;
		this.password = password;
		return connect();
	}
	/**
	 * This method connects with the FTP server.
	 * @return boolean
	 * @throws SocketException
	 * @throws IOException
	 */
	public boolean connect() throws SocketException, IOException {
		boolean returnStatus = false;

		ftpClient = new FTPClient();
		ftpClient.connect(hostName);
		ftpReply = ftpClient.getReplyString();

		if (userName != null && password != null)
			ftpClient.login(userName, password);
		returnStatus = ftpClient.isConnected();
		
		if(!returnStatus) 
			throw new IOException("Error connecting to FTP Server [" + hostName + "]. Server Message: " + ftpClient.getReplyString());

		return returnStatus;
	}
	/**
	 * This method checks the connection is still open or closed.
	 * @return boolean
	 */
	public boolean isConnected() {
		boolean connected = false;
		if (ftpClient != null) connected = ftpClient.isConnected();
		return connected;
	}
	/**
	 * This method create a directory at FTP server.
	 * @param dirPath
	 * @return boolean
	 * @throws IOException
	 */
	public boolean makeDir(String dirPath) throws IOException {
		boolean success;		
		success = ftpClient.makeDirectory(dirPath);
		ftpReply = ftpClient.getReplyString();
		return success;
	}
	/**
	 * This method change the server directory.
	 * @param dirPath
	 * @return boolean
	 * @throws IOException
	 */
	public boolean changeServerDir (String dirPath) throws IOException {
		boolean success;
		success = ftpClient.changeWorkingDirectory(dirPath);
		ftpReply = ftpClient.getReplyString();
		return success;
	}
	/**
	 * This method return the file names from FTP server directory.
	 * @return String Array
	 * @throws IOException
	 */
	public String[] listFileNames() throws IOException {
		String[] fileNames = null;
		if(ftpClient != null)
			fileNames = ftpClient.listNames();
		return fileNames;
	}
	/**
	 * This method downloads files from FTP server directory
	 * @param remoteFileName
	 * @param localFileName
	 * @return boolean
	 * @throws IOException
	 */
	public boolean download(String remoteFileName, String localFileName) {
		FileOutputStream localFile = null;
		boolean success = false;
		try {			
			localFile = new FileOutputStream(localFileName);
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			success = ftpClient.retrieveFile(remoteFileName, localFile);
			ftpReply = ftpClient.getReplyString();
			
			localFile.flush();
			localFile.close();
		} catch (FileNotFoundException e) {
			B2BLogger.error("FileNotFoundException :: " + StringUtils.getStackTrace(e));
		} catch (IOException e) {
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
		} finally{
			localFile = null;
		}
		return success;
	}
	/**
	 * This method deletes files from FTP server directory.
	 * @param dirPath
	 * @return boolean
	 * @throws IOException
	 */
	public boolean deleteFile(String dirPath) throws IOException{
		boolean success = false;
		success = ftpClient.deleteFile(dirPath);
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
	 * This method uploads files at FTP server
	 * @param localFileName
	 * @return boolean
	 * @throws IOException
	 */
	public boolean upload(String localFileName) throws IOException {
		return upload(localFileName, parseFileName(localFileName));
	}	
	/**
	 * This method uploads files at FTP server 
	 * @param localFileName
	 * @param remoteFileName
	 * @return boolean
	 * @throws IOException
	 */
	public boolean upload(String localFileName, String remoteFileName) throws IOException {
		boolean success = false;
		FileInputStream inputFile = new FileInputStream(localFileName);
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		success = ftpClient.storeFile(remoteFileName, inputFile);
		ftpReply = ftpClient.getReplyString();
		inputFile.close();
		return success;
	}
	/**
	 * This method disconnect with the FTP server.
	 * @return boolean
	 * @throws IOException
	 */
	public boolean disconnect () throws IOException {
		boolean returnStatus = false;
		if (ftpClient.isConnected()) {
			ftpClient.logout();
			ftpClient.disconnect();
		}
		returnStatus = true;
		return returnStatus;
	}
	/**
	 * Enables / Disables Remote Verification - Used in case of internal redirects 
	 * or in use of firewalls / proxis
	 * @param enable
	 * @throws IOException
	 */
	public void setRemoteVerificationEnabled (boolean enable) throws IOException {
		if (ftpClient != null)
			ftpClient.setRemoteVerificationEnabled(enable);
		else
			throw new IOException ("Must use setRemoteVerificationEnabled(..) after connect()");
	}
	
	/**
	 * sets the transfer type to be ascii or binery
	 * @param transferMode
	 */
	public void setTransferMode(int transferMode) throws IOException  {

		ftpClient.setFileType(transferMode);
	}
	
	
	
	
	/**
	 * This main method is a TEST method for testing the FTP server.
	 * Please comment this method in PreQA/Production environment.
	 * @param args
	 */
	/*public static void main(String[] args) {
		try {
			System.out.println("Initializing ..");
			FTPUtils ftpUtils = new FTPUtils("localhost", "abc", "xyz");
			System.out.println("Message :: " + ftpUtils.getFtpReply());

			System.out.println("Connecting ..");
			System.out.println("ftpUtils.connect() :: " + ftpUtils.connect());
			System.out.println("Message :: " + ftpUtils.getFtpReply());

			System.out.println("Changing current dir ..");
			System.out.println("ftpUtils.changeServerDir(..) :: " + ftpUtils.changeServerDir("/usr/config/base/tmp/"));
			System.out.println("Message :: " + ftpUtils.getFtpReply());

			System.out.println("Downloading ..");
			System.out.println("ftpUtils.download(..) :: " + ftpUtils.download("abc.xml", "D:\\projects\\ftpFiles\\xyz.xml"));
			System.out.println("Message :: " + ftpUtils.getFtpReply());

			System.out.println("Disconnecting ..");
			System.out.println("ftpUtils.disconnect() :: " + ftpUtils.disconnect());
			System.out.println("Message :: " + ftpUtils.getFtpReply());

			System.out.println("Done.");
		} catch (SocketException e) {
			B2BLogger.error("Exception",e);
		} catch (IOException e) {
			B2BLogger.error("Exception",e);
		}
	}*/
}