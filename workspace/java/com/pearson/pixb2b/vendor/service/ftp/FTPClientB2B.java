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
 * Title		: 	FTPClientB2B.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	29 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.service.ftp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import sun.net.ftp.FtpClient;
import sun.net.ftp.FtpDirEntry;
import sun.net.ftp.FtpDirParser;
import sun.net.ftp.FtpProtocolException;
import sun.net.ftp.FtpReplyCode;

import com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.FTPServerInfo;
import com.pearson.pixb2b.vendor.transaction.shared.helper.B2BHelper;
import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.FTPUtils;
import com.pearson.pixb2b.vendor.utils.FileUtils;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * FTPClientB2B class upload/download files 
 * in/from a given directory at FTP server.
 * 
 * @author Yogesh Tyagi
 */
public class FTPClientB2B extends FtpClient {
	private static final String FTP_DIR_NOT_EXIST	= "No such file or directory";
	
	/**
	 * This method uploads files in a given directory at FTP Server
	 * @param fileList
	 * @param ftpServerInfo
	 * @param outDirArchiveXmlValid
	 * @return String
	 */
	public String uploadFiles(ArrayList fileList, FTPServerInfo ftpServerInfo, 
			String transactionName, String transactionSchema, String outDirArchiveXmlValid, String outDirArchiveXmlInvalid) {
		
		String ftpFileUploadDir	= null;
		FTPUtils ftpUtils 		= null;
		B2BHelper b2bHelper		= null;
		FileUtils fileUtils		= null;
		String xmlFileNameWithDir = null;
		boolean xmlModified 	= false;
		String ftpReply 		= null;		
		
		try {
			B2BLogger.debug("FTPClientB2B.uploadFiles() method called");
			if(fileList != null && fileList.size() > 0){
				ftpFileUploadDir = ftpServerInfo.getFtpPutDir();				
				if(ftpFileUploadDir != null && !"".equals(ftpFileUploadDir.trim())){
					ftpUtils = new FTPUtils(ftpServerInfo.getFtpHostName(), ftpServerInfo.getFtpUserName(), ftpServerInfo.getFtpPwd());
				    ftpUtils.connect();				    
				    ftpUtils.setRemoteVerificationEnabled(false);
				    
				    ftpUtils.changeServerDir(ftpFileUploadDir);				    
				    if(ftpUtils.getFtpReply() != null && (ftpUtils.getFtpReply().trim()).indexOf(FTP_DIR_NOT_EXIST) != -1){
				    	ftpUtils.makeDir(ftpFileUploadDir);
				    	ftpUtils.changeServerDir(ftpFileUploadDir);
				    }
				    
				    B2BLogger.info("FTPClientB2B.uploadFiles() - FILES UPLOAD AT FTP SERVER STARTED : fileList size = "+fileList.size());
				    b2bHelper = new B2BHelper();
				    fileUtils = new FileUtils();
				    
				    for(int i=0; i < fileList.size(); i++){
						xmlFileNameWithDir = (String)fileList.get(i);					
						xmlModified = b2bHelper.removeTransSchemaFromXml(xmlFileNameWithDir, transactionName, transactionSchema);
						if(xmlModified){
							ftpUtils.upload(xmlFileNameWithDir);
							fileUtils.moveFile(xmlFileNameWithDir, outDirArchiveXmlValid);
						}else{
							fileUtils.moveFile(xmlFileNameWithDir, outDirArchiveXmlInvalid);
						}
					}			    
					ftpUtils.disconnect();
					ftpReply = ftpUtils.getFtpReply();
					B2BLogger.info("FTPClientB2B.uploadFiles() - FILE UPLOAD AT FTP SERVER COMPLETED, FTP SERVER REPLY IS = "+ftpReply);
				}else{
					B2BLogger.error("FTPClientB2B.uploadFiles() - FTP SERVER DIRECTORY PATH IS NULL : ftpDirUpload = "+ftpFileUploadDir);
				}
			}else{
				B2BLogger.info("FTPClientB2B.uploadFiles() - fileList = "+fileList);
			}
			B2BLogger.debug("FTPClientB2B.uploadFiles() method return");
		} catch (SocketException e) {			
			B2BLogger.error("SocketException :: " + StringUtils.getStackTrace(e));
			try {
				if(ftpUtils != null)
					ftpUtils.disconnect();
			} catch (IOException e1) {
				B2BLogger.error("Error in FTP client: ",e1);				
			}
		} catch (Exception e) {			
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			try {
				if(ftpUtils != null)
					ftpUtils.disconnect();
			} catch (IOException e1) {
				B2BLogger.error("Error in FTP client: ",e1);
			}
		} finally{
			ftpFileUploadDir= null;
			ftpUtils 	= null;
			b2bHelper	= null;
			fileUtils	= null;
			xmlFileNameWithDir = null;
		}
		return ftpReply;
	}
	/**
	 * This method downloads files from a given directory at FTP Server
	 * @param ftpServerInfo
	 * @param xmlDownloadDir
	 */
	public boolean downloadFiles(FTPServerInfo ftpServerInfo, String xmlDownloadDir) {
		String ftpFileDir		= null;
		FTPUtils ftpUtils 		= null;
		String[] fileList 		= null;
		String fileName 		= null;
		File localDir			= null;
		boolean downloadFlag 	= false;
		String ftpReply 		= null;		
		boolean deleteFlag      = Boolean.FALSE;
		boolean filesDownloded 	= Boolean.FALSE;
		Boolean validFileExtFlag= Boolean.TRUE;
		B2BHelper b2bHelper		= null;
		String vendorSAN		= null;
		String transType		= null;
		String[] arrStrFileNam	= null;
		ArrayList validTranList	= null;
		try {
			B2BLogger.debug("FTPClientB2B.downloadFiles() method called");			
			ftpFileDir = ftpServerInfo.getFtpGetDir();
			if(ftpFileDir != null && !"".equals(ftpFileDir.trim())){
				b2bHelper = new B2BHelper();
				ftpUtils = new FTPUtils(ftpServerInfo.getFtpHostName(), ftpServerInfo.getFtpUserName(), ftpServerInfo.getFtpPwd());
			    ftpUtils.connect();
			    ftpUtils.setRemoteVerificationEnabled(false);
			    if(!ftpUtils.changeServerDir(ftpFileDir))
			    	throw new B2BException("The system cannot find the specified directory path");
			    
			    //ftpUtils.setTransferMode(FTPClient.BINARY_FILE_TYPE);
			    fileList = ftpUtils.listFileNames();
			    
			    B2BLogger.info("FTPClientB2B.downloadFiles() - FILES DOWNLOAD FROM FTP SERVER STARTED : fileList size = "+(fileList != null ? fileList.length : fileList));
        		vendorSAN = Thread.currentThread().getName();
        		validTranList = b2bHelper.getValidTransactionList(vendorSAN);
			    if(fileList != null && fileList.length > 0){                
			    	localDir = new File(xmlDownloadDir);
					for(int i = 0 ; i < fileList.length ; i++){
						if(!localDir.exists()){
							boolean dirCreated = localDir.mkdirs();
							if(!dirCreated)
								localDir.mkdir();
						}						
	            		fileName = fileList[i];
	            		validFileExtFlag = b2bHelper.checkValidFileExt(fileName, IPixB2BConstants.fileExtn_XML);
	            		if(!validFileExtFlag){
	            			continue;
	            		}else{
							arrStrFileNam = fileName.split("_");
							if(arrStrFileNam.length==4){
								transType= b2bHelper.getLongTransType(arrStrFileNam[2], null);
			            		if(IPixB2BConstants.transType_ACK.equals(transType) || validTranList.contains(transType)){
				            		downloadFlag = ftpUtils.download(fileName,xmlDownloadDir+File.separatorChar+fileName);
				                	if(downloadFlag)
				                	{
				                		deleteFlag = ftpUtils.deleteFile(fileName);
				                	}
				                	if(!deleteFlag)//try to delete once more
				                		ftpUtils.deleteFile(fileName);
			            		}
							}else{
								B2BLogger.info("FTPClientB2B.downloadFiles() - Not DownLoading the file as the Filename::"+fileName+" - does not contain Valid number of tokens");
							}
	            		}
	                }
	            }
			    filesDownloded = Boolean.TRUE;
			    ftpUtils.disconnect();
				ftpReply = ftpUtils.getFtpReply();
				
				B2BLogger.info("FTPClientB2B.downloadFiles() - FILE DOWNLOAD FROM FTP SERVER COMPLETED, FTP SERVER REPLY IS = "+ftpReply);
			}else{
				B2BLogger.error("FTPClientB2B.downloadFiles() - FTP SERVER DIRECTORY PATH IS NULL : ftpFileDir = "+ftpFileDir);
			}
			B2BLogger.debug("FTPClientB2B.downloadFiles() method return");
		} catch (SocketException e) {			
			B2BLogger.error("SocketException :: " + StringUtils.getStackTrace(e));
			try {
				if(ftpUtils != null)
					ftpUtils.disconnect();
			} catch (IOException e1) {
				B2BLogger.error("Exception",e);				
			}
		} catch (Exception e) {			
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			try {
				if(ftpUtils != null)
					ftpUtils.disconnect();
			} catch (IOException e1) {
				B2BLogger.error("Exception",e);
			}
		} finally{
			ftpFileDir	 = null;
			ftpUtils 	= null;
			fileList= null;
			fileName 	= null;
			localDir	= null;
			ftpReply	= null;
		}
	return filesDownloded;
	}
	@Override
	public FtpClient abort() throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient allocate(long arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient appendFile(String arg0, InputStream arg1) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient changeDirectory(String arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient changeToParentDirectory() throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public FtpClient completePending() throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient connect(SocketAddress arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient connect(SocketAddress arg0, int arg1) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient deleteFile(String arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient enablePassiveMode(boolean arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient endSecureSession() throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getConnectTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<String> getFeatures() throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient getFile(String arg0, OutputStream arg1) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public InputStream getFileStream(String arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getHelp(String arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getLastFileName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Date getLastModified(String arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpReplyCode getLastReplyCode() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getLastResponseString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getLastTransferSize() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Proxy getProxy() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getReadTimeout() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public SocketAddress getServerAddress() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getSize(String arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getStatus(String arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getSystem() throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getWelcomeMsg() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getWorkingDirectory() throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isConnected() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isLoggedIn() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isPassiveModeEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public InputStream list(String arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Iterator<FtpDirEntry> listFiles(String arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient login(String arg0, char[] arg1) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient login(String arg0, char[] arg1, String arg2) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient makeDirectory(String arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public InputStream nameList(String arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient noop() throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient putFile(String arg0, InputStream arg1, boolean arg2) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public OutputStream putFileStream(String arg0, boolean arg1) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient reInit() throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient removeDirectory(String arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient rename(String arg0, String arg1) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient setConnectTimeout(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient setDirParser(FtpDirParser arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient setProxy(Proxy arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient setReadTimeout(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient setRestartOffset(long arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient setType(TransferType arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient siteCmd(String arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient startSecureSession() throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient structureMount(String arg0) throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public FtpClient useKerberos() throws FtpProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
}