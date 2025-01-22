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
 * Title		: 	UsageProcessor.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Abhilasha Nigam	24 Dec,2009 	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
/**
 * IUsageProcessor is an implementation class to process the 
 * the Usage Inbound Transaction XML for different vendors.
 * 
 * @author Abhilasha Nigam
 */
public interface IUsageProcessor{
	/**
	 * This method process the Usage XML received from vendor
	 * @param inTransFactory
	 * @param xmlFileNameWithDir
	 * @param inDirArchiveXmlValid
	 * @param inDirArchiveXmlInvalid
	 * @param vendorSAN
	 * @param transStatusDTO
	 * @param errorList
	 * @return String
	 */
	public String processUsageDetails(InboundTransactionFactory inTransFactory, String xmlFileNameWithDir, String inDirArchiveXmlValid, String inDirArchiveXmlInvalid, String vendorSAN, TransactionStatusDTO transStatusDTO, ArrayList errorList);
	/**
	 * This method do the Data Validation for Usage XML received from vendor
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