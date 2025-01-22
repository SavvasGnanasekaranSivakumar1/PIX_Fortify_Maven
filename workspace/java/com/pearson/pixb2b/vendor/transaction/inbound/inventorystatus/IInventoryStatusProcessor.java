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
 * Title		: 	IInventoryStatusProcessor.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	12 Oct,2009 	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * IInventoryStatusProcessor provide the interface to initiate 
 * the InventoryStatus Inbound Transaction processing for different Vendors.
 * 
 * @author Yogesh Tyagi
 */
public interface IInventoryStatusProcessor{
	/**
	 * This method process the InventoryStatus XML received from vendor
	 * @param inTransFactory
	 * @param xmlFileNameWithDir
	 * @param inDirArchiveXmlValid
	 * @param inDirArchiveXmlInvalid
	 * @param vendorSAN
	 * @param transStatusDTO
	 * @param errorList
	 * @return String
	 */
	public String processInventoryStatus(InboundTransactionFactory inTransFactory, String xmlFileNameWithDir, String inDirArchiveXmlValid, String inDirArchiveXmlInvalid, String vendorSAN, TransactionStatusDTO transStatusDTO, ArrayList errorList);
	
	/**
	 * This method do the Data Validation for IS XML received from vendor
	 * @param xmlFileNameWithDir
	 * @param validXMLList
	 * @param transName
	 * @param transType
	 * @param transactionSchema
	 * @param vendorSAN
	 * @param inDirArchiveXmlInvalid
	 * @param inTransFactory
	 * @param documentNumber
	 * @param documentDate
	 * @return Boolean
	 */
	public Boolean validateDataAndRegisterError(String xmlFileNameWithDir, ArrayList validXMLList, String transName, String transType, String transactionSchema, String vendorSAN, String inDirArchiveXmlInvalid, InboundTransactionFactory inTransFactory, String documentNumber, String documentDate);
	
	/**
	 * This method register the errors into the error log table.
	 * @param xmlFileNameWithDir
	 * @param validXMLList
	 * @param transName
	 * @param transType
	 * @param transactionSchema
	 * @param vendorSAN
	 * @param inDirArchiveXmlInvalid
	 * @param inTransFactory
	 * @param documentNumber
	 * @param documentDate
	 * @param errorList
	 * @param transId
	 * @param pneDTO
	 * @return Boolean
	 */
	public Boolean registerError(String xmlFileNameWithDir, ArrayList validXMLList, String transName, String transType, String transactionSchema, String vendorSAN, String inDirArchiveXmlInvalid, InboundTransactionFactory inTransFactory, String documentNumber, String documentDate,ArrayList errorList, String transId, PapiNetEnvelopeDTO pneDTO);
}