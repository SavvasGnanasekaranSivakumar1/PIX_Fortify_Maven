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
 * Title		: 	ShippingInstructionsGeneratorImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	12 Oct,2009 	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.service.xml.XmlWriterCastor;
import com.pearson.pixb2b.vendor.transaction.outbound.OutboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dao.IShippingInstructionsDAO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.VendorDTO;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.StringUtils;
/**
 * ShippingInstructionsGeneratorImpl is an implementation class to generate the 
 * the ShippingInstructions Outbound Transaction XML for different vendors.
 * 
 * @author Yogesh Tyagi
 */
public class ShippingInstructionsGeneratorImpl implements IShippingInstructionsGenerator{
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.IShippingInstructionsGenerator#generateShippingInstructions(com.pearson.pixb2b.vendor.transaction.outbound.OutboundTransactionFactory, com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.VendorDTO, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ArrayList generateShippingInstructions(OutboundTransactionFactory outTransFactory, VendorDTO vendorDTO, 
			String outXmlGenerateDir, String outXmlArchiveDir, String outInvalidXmlDir, String transType, 
			String transactionName, String castorMappingFile, String transactionSchema, String papiNetSchema) {
		
		IShippingInstructionsDAO siDAO	= null;
		ArrayList siTransList 			= null;		
		XmlWriterCastor xmlWriterCastor	= null;
		ArrayList processedSITransList 	= null;	
		
		try {
			B2BLogger.debug("ShippingInstructionsGeneratorImpl.generateShippingInstructions() method called FOR vendorSAN = "+vendorDTO.getVendorSAN()+" & vendorName = "+vendorDTO.getVendorName());
			siDAO = outTransFactory.getShippingInstructionsDAO();
			siTransList = siDAO.getShippingInstructionsDetails(vendorDTO.getVendorSAN(), vendorDTO.getProcessType(), transactionName);
			
			if(siTransList != null && siTransList.size()>0){
				xmlWriterCastor = new XmlWriterCastor();
				processedSITransList = xmlWriterCastor.generateXml(siTransList, vendorDTO, outXmlGenerateDir, 
						outXmlArchiveDir, outInvalidXmlDir, transType, transactionName, 
						castorMappingFile, transactionSchema, papiNetSchema, outTransFactory);
			}else{
				B2BLogger.info("ShippingInstructionsGeneratorImpl.generateShippingInstructions() - No "+transactionName+" RECORD FOUND, SO "+transactionName+" XML NOT GENERATED FOR vendorSAN = "+vendorDTO.getVendorSAN()+" & vendorName = "+vendorDTO.getVendorName());
			}
			B2BLogger.debug("ShippingInstructionsGeneratorImpl.generateShippingInstructions() method return FOR vendorSAN = "+vendorDTO.getVendorSAN()+" & vendorName = "+vendorDTO.getVendorName());
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			siDAO = null;
			siTransList = null;		
			xmlWriterCastor = null;
		}
		return processedSITransList;
	}
}