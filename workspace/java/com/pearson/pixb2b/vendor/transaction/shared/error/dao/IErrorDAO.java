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
 * Title		: 	IErrorDAO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	16 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.error.dao;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * IErrorDAO is an interface to process the 
 * B2B Error handling for all Outbound/Inbound Transactions.
 *  
 * @author Yogesh Tyagi
 */
public interface IErrorDAO{
	/**
	 * This method insert Invalid XML error details for all Inbound Transactions in database.
	 * @param transStatusDTO
	 * @param errorList
	 * @param feedType
	 * @param stageType
	 * @param successFlag
	 * @return String
	 */
	public String storeInboundXmlErrorDetails(TransactionStatusDTO transStatusDTO, ArrayList errorList, String feedType, String stageType, String successFlag, PapiNetEnvelopeDTO pneDTO);
	/**
	 * This method insert Invalid XML error details for all Outbound Transactions in database.
	 * @param errorList
	 * @param feedType
	 * @param stageType
	 * @param successFlag
	 * @return String
	 */
	public String storeOutboundXmlErrorDetails(ArrayList errorList, String feedType, String stageType, String successFlag);
    
	/**
	 * This method insert Invalid Invoice XML error details for all Invoice Transactions and update ReadLog status
	 * @param transStatusDTO
	 * @param errorList
	 * @param feedType
	 * @param stageType
	 * @param successFlag
	 * @return String
	 */  
	public String storeInvoiceXmlErrorDetails(TransactionStatusDTO transStatusDTO, ArrayList errorList, String feedType, String stageType, String successFlag);
	
	
	/**
	 * This method insert the usage XML error details for Usage Transactions.
	 * @param transStatusDTO
	 * @param errorList
	 * @param feedType
	 * @param stageType
	 * @param successFlag
	 * @param pneDTO
	 */
	public String storeUsageXmlErrorDetails(TransactionStatusDTO transStatusDTO, ArrayList errorList, String feedType, String stageType, String successFlag, PapiNetEnvelopeDTO pneDTO);

	
	/**
	 * This method insert Invalid GoodsReceipt XML error details for all Invoice Transactions and update ReadLog status
	 * @param transStatusDTO
	 * @param errorList
	 * @param feedType
	 * @param stageType
	 * @param successFlag
	 * @return String
	 */  
	public String storeGRXmlErrorDetails(TransactionStatusDTO transStatusDTO, ArrayList errorList, String feedType, String stageType, String successFlag);

	/**
	 * This method insert the usage XML error details for Inventory Status and Inventory Change Transactions.
	 * @param transStatusDTO
	 * @param errorList
	 * @param feedType
	 * @param stageType
	 * @param successFlag
	 * @param pneDTO
	 * @return String
	 */
	public String storeDAOErrorDetails(TransactionStatusDTO transStatusDTO, ArrayList errorList, String feedType, String stageType, String successFlag, PapiNetEnvelopeDTO pneDTO);
}