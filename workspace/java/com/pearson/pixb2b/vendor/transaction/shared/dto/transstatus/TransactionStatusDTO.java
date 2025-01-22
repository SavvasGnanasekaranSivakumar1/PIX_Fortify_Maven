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
 * Title		: 	TransactionStatusDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	30 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus;


/**
 * TransactionStatusDTO is a data transfer object to store the 
 * transaction status details for B2B Outbound and Inbound transactions.
 * 
 * @author Yogesh Tyagi
 */
public class TransactionStatusDTO implements java.io.Serializable {
	private static final long serialVersionUID = 5581982401372109134L;

	private String processType	= null; //OUT for Outbound / IN for Inbound
	private String senderSAN	= null;
	private String receiverSAN	= null;
	
	private String transID		= null;
	private String transType	= null;
	private String transName	= null;
	
	private String fileName 	= null;
	private String folderName 	= null;
	private String completePath	= null;
	private String fileSize1 	= null; //Generated file size for Outbound transaction / Vendor file size for Inbound transaction
	private String fileSize2 	= null; //Archive file size for Outbound transaction / FTP file size for Inbound transaction
	
	private String statusGEN 	= null; //XML Generation Status
	private String statusREAD 	= null; //XML Reading Status
	private String statusFTP 	= null;	//XML FTP Status
	private String statusARC 	= null;	//XML Archive Status
	private String statusMAIL 	= null;	//XML Email Status
	private String statusACK 	= null; //XML Acknowledgement Status
	private String documentNumber = null;
	private String documentDate = null;
	
	
	/**
	 * @return Returns the documentDate.
	 */
	public String getDocumentDate() {
		return documentDate;
	}
	/**
	 * @param documentDate The documentDate to set.
	 */
	public void setDocumentDate(String documentDate) {
		this.documentDate = documentDate;
	}
	/**
	 * Default constructor.
	 */
	public TransactionStatusDTO() {
		super();
	}
	/**
	 * @return the processType
	 */
	public String getProcessType() {
		return processType;
	}
	/**
	 * @param processType the processType to set
	 */
	public void setProcessType(String processType) {
		this.processType = processType;
	}
	/**
	 * @return the senderSAN
	 */
	public String getSenderSAN() {
		return senderSAN;
	}
	/**
	 * @param senderSAN the senderSAN to set
	 */
	public void setSenderSAN(String senderSAN) {
		this.senderSAN = senderSAN;
	}
	/**
	 * @return the receiverSAN
	 */
	public String getReceiverSAN() {
		return receiverSAN;
	}
	/**
	 * @param receiverSAN the receiverSAN to set
	 */
	public void setReceiverSAN(String receiverSAN) {
		this.receiverSAN = receiverSAN;
	}
	/**
	 * @return the transID
	 */
	public String getTransID() {
		return transID;
	}
	/**
	 * @param transID the transID to set
	 */
	public void setTransID(String transID) {
		this.transID = transID;
	}
	/**
	 * @return the transType
	 */
	public String getTransType() {
		return transType;
	}
	/**
	 * @param transType the transType to set
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}
	/**
	 * @return the transName
	 */
	public String getTransName() {
		return transName;
	}
	/**
	 * @param transName the transName to set
	 */
	public void setTransName(String transName) {
		this.transName = transName;
	}
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the folderName
	 */
	public String getFolderName() {
		return folderName;
	}
	/**
	 * @param folderName the folderName to set
	 */
	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	/**
	 * @return the completePath
	 */
	public String getCompletePath() {
		return completePath;
	}
	/**
	 * @param completePath the completePath to set
	 */
	public void setCompletePath(String completePath) {
		this.completePath = completePath;
	}
	/**
	 * @return the fileSize1
	 */
	public String getFileSize1() {
		return fileSize1;
	}
	/**
	 * @param fileSize1 the fileSize1 to set
	 */
	public void setFileSize1(String fileSize1) {
		this.fileSize1 = fileSize1;
	}
	/**
	 * @return the fileSize2
	 */
	public String getFileSize2() {
		return fileSize2;
	}
	/**
	 * @param fileSize2 the fileSize2 to set
	 */
	public void setFileSize2(String fileSize2) {
		this.fileSize2 = fileSize2;
	}
	/**
	 * @return the statusGEN
	 */
	public String getStatusGEN() {
		return statusGEN;
	}
	/**
	 * @param statusGEN the statusGEN to set
	 */
	public void setStatusGEN(String statusGEN) {
		this.statusGEN = statusGEN;
	}
	/**
	 * @return the statusREAD
	 */
	public String getStatusREAD() {
		return statusREAD;
	}
	/**
	 * @param statusREAD the statusREAD to set
	 */
	public void setStatusREAD(String statusREAD) {
		this.statusREAD = statusREAD;
	}
	/**
	 * @return the statusFTP
	 */
	public String getStatusFTP() {
		return statusFTP;
	}
	/**
	 * @param statusFTP the statusFTP to set
	 */
	public void setStatusFTP(String statusFTP) {
		this.statusFTP = statusFTP;
	}
	/**
	 * @return the statusARC
	 */
	public String getStatusARC() {
		return statusARC;
	}
	/**
	 * @param statusARC the statusARC to set
	 */
	public void setStatusARC(String statusARC) {
		this.statusARC = statusARC;
	}
	/**
	 * @return the statusMAIL
	 */
	public String getStatusMAIL() {
		return statusMAIL;
	}
	/**
	 * @param statusMAIL the statusMAIL to set
	 */
	public void setStatusMAIL(String statusMAIL) {
		this.statusMAIL = statusMAIL;
	}
	/**
	 * @return the statusACK
	 */
	public String getStatusACK() {
		return statusACK;
	}
	/**
	 * @param statusACK the statusACK to set
	 */
	public void setStatusACK(String statusACK) {
		this.statusACK = statusACK;
	}
	/**
	 * @return Returns the documentNumber.
	 */
	public String getDocumentNumber() {
		return documentNumber;
	}
	/**
	 * @param documentNumber The documentNumber to set.
	 */
	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}
}