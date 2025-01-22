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
 * Title		: 	BusinessAckGeneratorImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	17 Nov,2009 	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.common.businessack;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.service.xml.XmlWriterCastor;
import com.pearson.pixb2b.vendor.transaction.outbound.OutboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.outbound.common.businessack.dao.IBusinessAckGeneratorDAO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.VendorDTO;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.StringUtils;
/**
 * BusinessAckGeneratorImpl is an implementation class to generate the 
 * the BusinessAcknowledgement Success/Failure transaction details for 
 * all Inbound Transactions received from vendors.
 * 
 * @author Yogesh Tyagi
 */
public class BusinessAckGeneratorImpl implements IBusinessAckGenerator{
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.outbound.common.businessack.IBusinessAckGenerator#generateBusinessAcknowledgement(com.pearson.pixb2b.vendor.transaction.outbound.OutboundTransactionFactory, com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.VendorDTO, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	
	 /**public ArrayList generateBusinessAcknowledgement(OutboundTransactionFactory outTransFactory, VendorDTO vendorDTO, 
			String outXmlGenerateDir, String outXmlArchiveDir, String outInvalidXmlDir, String transType, 
			String transactionName, String castorMappingFile, String transactionSchema, String papiNetSchema)*/
	 
	 
	 public ArrayList generateBusinessAckOutbound(OutboundTransactionFactory outTransFactory, VendorDTO vendorDTO, 
				String outXmlGenerateDir, String outXmlArchiveDir, String outInvalidXmlDir, String transType, 
				String transactionName, String castorMappingFile, String transactionSchema, String papiNetSchema){

		IBusinessAckGeneratorDAO ackDAO	= null;
		ArrayList ackTransList 			= null;		
		XmlWriterCastor xmlWriterCastor	= null;
		ArrayList processedACKTransList	= null;	
		
		try {
			B2BLogger.debug("BusinessAckGeneratorImpl.generateBusinessAckOutbound() method called FOR vendorSAN = "+vendorDTO.getVendorSAN()+" & vendorName = "+vendorDTO.getVendorName());
			ackDAO = outTransFactory.getBusinessAckGeneratorDAO();
			//ackTransList = ackDAO.getBusinessAcknowledgementDetails(vendorDTO.getVendorSAN(), vendorDTO.getProcessType(), transactionName);
			ackTransList = ackDAO.getBAOutboundDetails(vendorDTO.getVendorSAN(), vendorDTO.getProcessType(), transactionName);
			if(ackTransList != null && ackTransList.size()>0){
				xmlWriterCastor = new XmlWriterCastor();
				processedACKTransList = xmlWriterCastor.generateXml(ackTransList, vendorDTO, outXmlGenerateDir, 
						outXmlArchiveDir, outInvalidXmlDir, transType, transactionName, 
						castorMappingFile, transactionSchema, papiNetSchema, outTransFactory);
			}else{
				B2BLogger.info("BusinessAckGeneratorImpl.generateBusinessAckOutbound() - No "+transactionName+" RECORD FOUND, SO "+transactionName+" XML NOT GENERATED FOR vendorSAN = "+vendorDTO.getVendorSAN()+" & vendorName = "+vendorDTO.getVendorName());
			}
			B2BLogger.debug("BusinessAckGeneratorImpl.generateBusinessAckOutbound() method return FOR vendorSAN = "+vendorDTO.getVendorSAN()+" & vendorName = "+vendorDTO.getVendorName());
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			ackDAO			= null;
			ackTransList 	= null;		
			xmlWriterCastor = null;
		}
		return processedACKTransList;
	}
}