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
 * Title		: 	SFTPClientB2B.java
 * Company 		: 	CTS
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Nithya S	30 Sep, 2015		Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.service.sftp;

import java.io.File;
import java.util.ArrayList;

import com.jcraft.jsch.JSchException;
import com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.FTPServerInfo;
import com.pearson.pixb2b.vendor.transaction.shared.helper.B2BHelper;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.FileUtils;
import com.pearson.pixb2b.vendor.utils.SFTPUtils;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * SFTPClientB2B class upload/download files 
 * in/from a given directory at SFTP server.
 * 
 * @author Nithya CTS Added for FTP to SFTP Migration
 */
public class SFTPClientB2B {
	private static final String SFTP_DIR_NOT_EXIST	= "No such file or directory";
	
	/**
	 * This method uploads files in a given directory at SFTP Server
	 * @param fileList
	 * @param ftpServerInfo
	 * @param outDirArchiveXmlValid
	 * @return String
	 */
	
	public String uploadFiles(ArrayList fileList, FTPServerInfo ftpServerInfo, 
			String transactionName, String transactionSchema, String outDirArchiveXmlValid, String outDirArchiveXmlInvalid) {
		
		String sftpFileUploadDir	= null;
		SFTPUtils sftpUtils 	= null;
		B2BHelper b2bHelper		= null;
		FileUtils fileUtils		= null;
		String xmlFileNameWithDir = null;
		boolean xmlModified 	= false;
		String sftpReply 		= null;		
		
		try {
			B2BLogger.debug("SFTPClientB2B.uploadFiles() method called");
			if(fileList != null && fileList.size() > 0){
				sftpFileUploadDir = ftpServerInfo.getFtpPutDir();				
				if(sftpFileUploadDir != null && !"".equals(sftpFileUploadDir.trim())){
					sftpUtils = new SFTPUtils(ftpServerInfo.getFtpHostName(), ftpServerInfo.getFtpUserName(), ftpServerInfo.getFtpPwd());
				    sftpUtils.connect();   
				   
				    
				    boolean changeDir = sftpUtils.changeServerDir(sftpFileUploadDir);				    
				   if(!changeDir){
				    	sftpUtils.makeDir(sftpFileUploadDir);
				    	sftpUtils.changeServerDir(sftpFileUploadDir);
				    }
				    
				    
				    B2BLogger.info("SFTPClientB2B.uploadFiles() - FILES UPLOAD AT SFTP SERVER STARTED : fileList size = "+fileList.size());
				    b2bHelper = new B2BHelper();
				    fileUtils = new FileUtils();
				    
				    for(int i=0; i < fileList.size(); i++){
					 xmlFileNameWithDir = (String)fileList.get(i);						
						xmlModified = b2bHelper.removeTransSchemaFromXml(xmlFileNameWithDir, transactionName, transactionSchema);
						if(xmlModified){
							
							boolean uploadSuccess =  sftpUtils.upload(xmlFileNameWithDir);
							if(uploadSuccess){
								
								if(ftpServerInfo.getFtpOrSftp().equalsIgnoreCase("SFTP")){
									B2BLogger.debug("Moving File : "+ xmlFileNameWithDir+"to archive");
									fileUtils.moveFile(xmlFileNameWithDir, outDirArchiveXmlValid);
								}
							}
						}else{
							fileUtils.moveFile(xmlFileNameWithDir, outDirArchiveXmlInvalid);
						}
					}			    
					sftpUtils.disconnect();	
					sftpReply ="Success";
					B2BLogger.info("SFTPClientB2B.uploadFiles() - FILE UPLOAD AT SFTP SERVER COMPLETED");
				}else{
					B2BLogger.error("SFTPClientB2B.uploadFiles() - SFTP SERVER DIRECTORY PATH IS NULL :  = "+sftpFileUploadDir);					
				}
			}else{
				B2BLogger.info("SFTPClientB2B.uploadFiles() - fileList = "+fileList);				
			}
			B2BLogger.debug("SFTPClientB2B.uploadFiles() method return");
		} catch(JSchException e) {			
			sftpReply ="Failure";
			B2BLogger.error("JSchException :: " + StringUtils.getStackTrace(e));						
		} finally{
			sftpFileUploadDir= null;
			sftpUtils 	= null;
			b2bHelper	= null;
			fileUtils	= null;
			xmlFileNameWithDir = null;
		}
		return sftpReply;
	}
	/**
	 * This method downloads files from a given directory at SFTP Server
	 * @param ftpServerInfo
	 * @param xmlDownloadDir
	 */
	public boolean downloadFiles(FTPServerInfo ftpServerInfo, String xmlDownloadDir) {
		String sftpFileDir		= null;
		SFTPUtils sftpUtils 	= null;
		ArrayList<String> fileList   = null;
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
			sftpFileDir = ftpServerInfo.getFtpGetDir();
			B2BLogger.debug("SFTPClientB2B.downloadFiles() at : "+sftpFileDir+" Started");
			B2BLogger.info("SFTP downloading files for undor xmlDownloadDir" +xmlDownloadDir);
			if(sftpFileDir != null && !"".equals(sftpFileDir.trim())){
				b2bHelper = new B2BHelper();
				sftpUtils = new SFTPUtils(ftpServerInfo.getFtpHostName(), ftpServerInfo.getFtpUserName(), ftpServerInfo.getFtpPwd());
			    sftpUtils.connect();
			   
			    sftpUtils.changeServerDir(sftpFileDir);	 	
			    
			  
			    fileList = sftpUtils.listFileNames(sftpFileDir);
			  
			    B2BLogger.info("SFTPClientB2B.downloadFiles() - FILES DOWNLOAD FROM SFTP SERVER STARTED : fileList size = "+(fileList != null ? fileList.size() : fileList));
        		vendorSAN = Thread.currentThread().getName();
        		
        		validTranList = b2bHelper.getValidTransactionList(vendorSAN);      
        		B2BLogger.info("validTranList :"+validTranList.toString());
			    if(fileList != null && fileList.size() > 0){                
			    	localDir = new File(xmlDownloadDir);
			    	 for(int i=0; i<fileList.size();i++){
						if(!localDir.exists()){
							boolean dirCreated = localDir.mkdirs();
							if(!dirCreated)
								localDir.mkdir();
						}						
	            		fileName = fileList.get(i);	            		
	            		validFileExtFlag = b2bHelper.checkValidFileExt(fileName, IPixB2BConstants.fileExtn_XML);	            		
	            		if(!validFileExtFlag){
	            			continue;
	            		}else{
							arrStrFileNam = fileName.split("_");
							if(arrStrFileNam.length==4){
								transType= b2bHelper.getLongTransType(arrStrFileNam[2], null);
								
			            		if(IPixB2BConstants.transType_ACK.equals(transType) || validTranList.contains(transType)){
			            			B2BLogger.info("validTranList :"+validTranList.toString());
				            		downloadFlag = sftpUtils.download(fileName,xmlDownloadDir+File.separatorChar+fileName);
				                	if(downloadFlag)
				                	{				                		
				                			sftpUtils.deleteFile(fileName);				                		
				                	}				                	
			            		}
							}else{
								B2BLogger.info("SFTPClientB2B.downloadFiles() - Not DownLoading the file as the Filename::"+fileName+" - does not contain Valid number of tokens");
							}
	            		}
	                }
	            }
			    filesDownloded = Boolean.TRUE;
			    sftpUtils.disconnect();				
				
				B2BLogger.info("SFTPClientB2B.downloadFiles() - FILE DOWNLOAD FROM SFTP SERVER COMPLETED");
			}else{
				B2BLogger.error("SFTPClientB2B.downloadFiles() - SFTP SERVER DIRECTORY PATH IS NULL : sftpFileDir = "+sftpFileDir);
			}
			B2BLogger.debug("SFTPClientB2B.downloadFiles() method return");		
		} catch (Exception e) {			
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			
		} finally{
			sftpFileDir	 = null;
			sftpUtils 	= null;
			fileList    = null;
			fileName 	= null;
			localDir	= null;
			ftpReply	= null;
		}
	return filesDownloded;
	}
}