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
 * Title		: 	IBusinessAckProcessor.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	17 Nov,2009 	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.common.businessack;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
/**
 * IBusinessAckProcessor provide the interface to initiate 
 * the BusinessAcknowledgement Success/Failure transaction details for 
 * all Outbound Transactions sent to vendors.
 * 
 * @author Yogesh Tyagi
 */
public interface IBusinessAckProcessor{
	/**
	 * This method process the BusinessAcknowledgement Success XML received from vendor
	 * @param inTransFactory
	 * @param xmlFileNameWithDir
	 * @param inDirArchiveXmlValid
	 * @param inDirArchiveXmlInvalid
	 * @param vendorSAN
	 * @param transStatusDTO
	 * @return String
	 */
	public String processBusinessAcknowledgement(InboundTransactionFactory inTransFactory, String xmlFileNameWithDir, String inDirArchiveXmlValid, String inDirArchiveXmlInvalid, String vendorSAN, TransactionStatusDTO transStatusDTO);

	public Boolean validateDataAndRegisterError(String xmlFileNameWithDir, ArrayList validXMLList, String transName, String transType, String transactionSchema, String vendorSAN, String inDirArchiveXmlInvalid, InboundTransactionFactory inTransFactory, String documentNumber, String documentDate);

}