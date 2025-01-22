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
 * Title		: 	IShippingInstructionsGenerator.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	12 Oct,2009 	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.outbound.OutboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.VendorDTO;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * IShippingInstructionsGenerator provide the interface to initiate 
 * the ShippingInstructions Outbound Transaction processing for different Vendors.
 * 
 * @author Yogesh Tyagi
 */
public interface IShippingInstructionsGenerator{
	/**
	 * This method generate and validate the ShippingInstructions XML.
	 * @param outTransFactory
	 * @param vendorDTO
	 * @param outXmlGenerateDir
	 * @param outXmlArchiveDir
	 * @param outInvalidXmlDir
	 * @param transType
	 * @param transactionName
	 * @param castorMappingFile
	 * @param transactionSchema
	 * @param papiNetSchema
	 * @return ArrayList
	 */
	public ArrayList generateShippingInstructions(OutboundTransactionFactory outTransFactory, VendorDTO vendorDTO, 
			String outXmlGenerateDir, String outXmlArchiveDir, String outInvalidXmlDir, String transType, 
			String transactionName, String castorMappingFile, String transactionSchema, String papiNetSchema);
}