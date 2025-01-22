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
 * Title		: 	IGRProcessor.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Abhilasha Nigam	14 Jan,2010	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * IGRProcessor is an implementation class to process the 
 * the goodsreceipt Inbound Transaction XML for different vendors.
 * 
 * @author Abhilasha Nigam
 */
public interface IGRProcessor{
	/**
	 * This method process the GR XML received from vendor
	 * @param inTransFactory
	 * @param xmlFileNameWithDir
	 * @param inDirArchiveXmlValid
	 * @param inDirArchiveXmlInvalid
	 * @param vendorSAN
	 * @param transStatusDTO
	 * @return String
	 */
	public String processGRDetails(InboundTransactionFactory inTransFactory, String xmlFileNameWithDir, String inDirArchiveXmlValid, String inDirArchiveXmlInvalid, String vendorSAN, TransactionStatusDTO transStatusDTO,ArrayList errorLIst);
	
	public Boolean validateDataAndRegisterError(String xmlFileNameWithDir, ArrayList validXMLList, String transName, String transType, String transactionSchema, String vendorSAN, String inDirArchiveXmlInvalid, InboundTransactionFactory inTransFactory, String documentNumber, String documentDate);

	public Boolean registerError(String xmlFileNameWithDir, ArrayList validXMLList, String transName, String transType, String transactionSchema, String vendorSAN, String inDirArchiveXmlInvalid, InboundTransactionFactory inTransFactory, String documentNumber, String documentDate,ArrayList errorList,String transId);


}