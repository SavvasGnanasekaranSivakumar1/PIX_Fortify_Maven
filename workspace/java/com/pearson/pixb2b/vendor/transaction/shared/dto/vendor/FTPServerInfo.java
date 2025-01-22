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
 * Title		: 	FTPServerInfo.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi   	8 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.vendor;

import java.util.ArrayList;
/**
 * FTPServerInfo is a helper DTO to encapsulate the FTP server details.
 * 
 * @author Yogesh Tyagi
 */
public class FTPServerInfo implements java.io.Serializable {
	private static final long serialVersionUID = -7057016066624076640L;
	
	private String ftpHostName 	= null;
	private String ftpUserName 	= null;
	private String ftpPwd 		= null;
	private String ftpGetDir 	= null;
	private String ftpPutDir 	= null;
	private String ftpReply 	= null;
	private ArrayList ftpFileList = null;
	private String ftpOrSftp 	= null; // Nithya CTS - Added for FTP to SFTP Migration
	/**
	 * Default constructor.
	 */
	public FTPServerInfo() {
		super();
	}
	
	
	public FTPServerInfo(String ftpHostName,String ftpUserName,String ftpPwd) {
		this.ftpHostName = ftpHostName;
		this.ftpUserName = ftpUserName;
		this.ftpPwd = ftpPwd;		
	}
	/**
	 * @return the ftpHostName
	 */
	public String getFtpHostName() {
		return ftpHostName;
	}
	/**
	 * @param ftpHostName the ftpHostName to set
	 */
	public void setFtpHostName(String ftpHostName) {
		this.ftpHostName = ftpHostName;
	}
	/**
	 * @return the ftpUserName
	 */
	public String getFtpUserName() {
		return ftpUserName;
	}
	/**
	 * @param ftpUserName the ftpUserName to set
	 */
	public void setFtpUserName(String ftpUserName) {
		this.ftpUserName = ftpUserName;
	}
	/**
	 * @return the ftpPwd
	 */
	public String getFtpPwd() {
		return ftpPwd;
	}
	/**
	 * @param ftpPwd the ftpPwd to set
	 */
	public void setFtpPwd(String ftpPwd) {
		this.ftpPwd = ftpPwd;
	}
	/**
	 * @return the ftpGetDir
	 */
	public String getFtpGetDir() {
		return ftpGetDir;
	}
	/**
	 * @param ftpGetDir the ftpGetDir to set
	 */
	public void setFtpGetDir(String ftpGetDir) {
		this.ftpGetDir = ftpGetDir;
	}
	/**
	 * @return the ftpPutDir
	 */
	public String getFtpPutDir() {
		return ftpPutDir;
	}
	/**
	 * @param ftpPutDir the ftpPutDir to set
	 */
	public void setFtpPutDir(String ftpPutDir) {
		this.ftpPutDir = ftpPutDir;
	}
	/**
	 * @return the ftpReply
	 */
	public String getFtpReply() {
		return ftpReply;
	}
	/**
	 * @param ftpReply the ftpReply to set
	 */
	public void setFtpReply(String ftpReply) {
		this.ftpReply = ftpReply;
	}
	/**
	 * @return the ftpFileList
	 */
	public ArrayList getFtpFileList() {
		return ftpFileList;
	}
	/**
	 * @param ftpFileList the ftpFileList to set
	 */
	public void setFtpFileList(ArrayList ftpFileList) {
		this.ftpFileList = ftpFileList;
	}
	/* Nithya FTP - SFTP Migration Starts */
	/**
	 * @return the ftpSftp
	 */
	public String getFtpOrSftp() {
		return ftpOrSftp;
	}
	/**
	 * @param ftpSftp - Mode of transfer ftp or sftp
	 */
	public void setFtpOrSftp(String ftpOrSftp) {
		this.ftpOrSftp = ftpOrSftp;
	}
	/* Nithya FTP - SFTP Migration Ends */
}