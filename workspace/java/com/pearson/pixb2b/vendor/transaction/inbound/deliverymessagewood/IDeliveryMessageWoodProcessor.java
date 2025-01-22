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
 * Title		: 	IDeliveryMessageWoodProcessor.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		22 March 2010 		Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.deliverymessagewood;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * IDeliveryMessageWoodProcessor provide the interface to initiate 
 * the DeliveryMessageWood Inbound Transaction processing for different Vendors.
 * 
 * @author Ashish Agrawal
 */
public interface IDeliveryMessageWoodProcessor{
	/**
	 * This method process the DeliveryMessageWood XML received from vendor
	 * @param inTransFactory
	 * @param xmlFileNameWithDir
	 * @param inDirArchiveXmlValid
	 * @param inDirArchiveXmlInvalid
	 * @param vendorSAN
	 * @param transStatusDTO
	 * @return String
	 */
	public String processDeliveryMessageWood(InboundTransactionFactory inTransFactory, String xmlFileNameWithDir, String inDirArchiveXmlValid, String inDirArchiveXmlInvalid, String vendorSAN, TransactionStatusDTO transStatusDTO);

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
	 * @return Boolean
	 */
	public Boolean validateDataAndRegisterError(String xmlFileNameWithDir, ArrayList validXMLList, String transName, String transType, String transactionSchema, String vendorSAN, String inDirArchiveXmlInvalid, InboundTransactionFactory inTransFactory, String documentNumber, String documentDate);
}