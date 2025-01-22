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
 * Title		: 	BookSpecificationGeneratorImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		8 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.service.xml.XmlWriterCastor;
import com.pearson.pixb2b.vendor.transaction.outbound.OutboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dao.IBookSpecificationDAO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.VendorDTO;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.StringUtils;
/**
 * BookSpecificationGeneratorImpl is an implementation class to generate the 
 * the BookSpecification Outbound Transaction XML for different vendors.
 * 
 * @author Ashish Agrawal
 */
public class BookSpecificationGeneratorImpl implements IBookSpecificationGenerator{
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.IBookSpecificationGenerator#generateBookSpecification(com.pearson.pixb2b.vendor.transaction.outbound.OutboundTransactionFactory, com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.VendorDTO, java.lang.String, java.lang.String)
	 */
	public ArrayList generateBookSpecification(OutboundTransactionFactory outTransFactory, VendorDTO vendorDTO, 
			String outXmlGenerateDir, String outXmlArchiveDir, String outInvalidXmlDir, String transType, 
			String transactionName, String castorMappingFile, String transactionSchema, String papiNetSchema) {
		
		IBookSpecificationDAO bsDAO		= null;
		ArrayList bsTransList 			= null;		
		XmlWriterCastor xmlWriterCastor	= null;
		ArrayList processedBSTransList	= null;	
		
		try {
			B2BLogger.debug("BookSpecificationGeneratorImpl.generateBookSpecification() method called FOR vendorSAN = "+vendorDTO.getVendorSAN()+" & vendorName = "+vendorDTO.getVendorName());
			bsDAO = outTransFactory.getBookSpecificationDAO();
			bsTransList = bsDAO.getBookSpecificationDetails(vendorDTO.getVendorSAN(), vendorDTO.getProcessType(), transactionName);
			
			if(bsTransList != null && bsTransList.size()>0){
				xmlWriterCastor = new XmlWriterCastor();
				processedBSTransList = xmlWriterCastor.generateXml(bsTransList, vendorDTO, outXmlGenerateDir, 
						outXmlArchiveDir, outInvalidXmlDir, transType, transactionName, 
						castorMappingFile, transactionSchema, papiNetSchema, outTransFactory);
			}else{
				B2BLogger.info("BookSpecificationGeneratorImpl.generateBookSpecification() - No "+transactionName+" RECORD FOUND, SO "+transactionName+" XML NOT GENERATED FOR vendorSAN = "+vendorDTO.getVendorSAN()+" & vendorName = "+vendorDTO.getVendorName());
			}
			B2BLogger.debug("BookSpecificationGeneratorImpl.generateBookSpecification() method return FOR vendorSAN = "+vendorDTO.getVendorSAN()+" & vendorName = "+vendorDTO.getVendorName());
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			bsDAO			= null;
			bsTransList 	= null;		
			xmlWriterCastor = null;
		}
		return processedBSTransList;
	}
}