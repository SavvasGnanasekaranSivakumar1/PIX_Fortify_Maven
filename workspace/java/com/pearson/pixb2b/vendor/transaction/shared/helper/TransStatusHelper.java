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
 * Title		: 	TransStatusHelper.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi 	14 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.helper;

import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * TransStatusHelper is a helper class to set the transaction status
 * to complete the required operations.
 * 
 * @author Yogesh Tyagi
 */
public class TransStatusHelper{	
	/**
	 * This method set the Outbound/Inbound Transaction Status details for a particular transaction.
	 * @param processType
	 * @param vendorSAN
	 * @param transID
	 * @param transType
	 * @param transName
	 * @param fileName
	 * @param folderName
	 * @param completePath
	 * @param fileSize1
	 * @param fileSize2
	 * @param statusGEN
	 * @param statusREAD
	 * @param statusFTP
	 * @param statusARC
	 * @param statusMAIL
	 * @param statusACK
	 * @return TransactionStatusDTO
	 */
	public TransactionStatusDTO setTransactionStatus(String processType, String senderSAN, String receiverSAN,
			String transID, String transType, String transName,  
			String fileName, String folderName, String completePath, String fileSize1, String fileSize2,
			String statusGEN, String statusREAD, String statusFTP, String statusARC, String statusMAIL, String statusACK,String documentNumber,String documentDate){
		
		TransactionStatusDTO transStatusDTO = null;
		try {
			transStatusDTO = new TransactionStatusDTO();
			
			transStatusDTO.setProcessType(processType);
			transStatusDTO.setSenderSAN(senderSAN);
			transStatusDTO.setReceiverSAN(receiverSAN);			
			
			transStatusDTO.setTransID(transID);
			transStatusDTO.setTransType(transType);
			transStatusDTO.setTransName(transName);
				
			transStatusDTO.setFileName(fileName);
			transStatusDTO.setFolderName(folderName);
			transStatusDTO.setCompletePath(completePath);
			transStatusDTO.setFileSize1(fileSize1);
			transStatusDTO.setFileSize2(fileSize2);
			
			transStatusDTO.setStatusGEN(statusGEN); //This is for Outbound so for Inbound its value should be null
			transStatusDTO.setStatusREAD(statusREAD); //This is for Inbound so for Outbound its value should be null
			transStatusDTO.setStatusFTP(statusFTP);
			transStatusDTO.setStatusARC(statusARC);
			transStatusDTO.setStatusMAIL(statusMAIL);
			transStatusDTO.setStatusACK(statusACK);
			transStatusDTO.setDocumentNumber(documentNumber);
			transStatusDTO.setDocumentDate(documentDate);
			
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			transStatusDTO = null;
		}
		return transStatusDTO;
	}	
}