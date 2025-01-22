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
 * Title		: 	PurchaseOrderGeneratorImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	12 Oct,2009 	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.service.xml.XmlWriterCastor;
import com.pearson.pixb2b.vendor.transaction.outbound.OutboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dao.IPurchaseOrderDAO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.VendorDTO;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.StringUtils;
/**
 * PurchaseOrderGeneratorImpl is an implementation class to generate the 
 * the PurchaseOrder Outbound Transaction XML for different vendors.
 * 
 * @author Yogesh Tyagi
 */
public class PurchaseOrderGeneratorImpl implements IPurchaseOrderGenerator{
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.IPurchaseOrderGenerator#generatePurchaseOrder(com.pearson.pixb2b.vendor.transaction.outbound.OutboundTransactionFactory, com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.VendorDTO, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ArrayList generatePurchaseOrder(OutboundTransactionFactory outTransFactory, VendorDTO vendorDTO, 
			String outXmlGenerateDir, String outXmlArchiveDir, String outInvalidXmlDir, String transType, 
			String transactionName, String castorMappingFile, String transactionSchema, String papiNetSchema) {
		
		IPurchaseOrderDAO poDAO			= null;
		ArrayList poTransList 			= null;		
		XmlWriterCastor xmlWriterCastor	= null;
		ArrayList processedPOTransList	= null;	
		
		try {
			B2BLogger.debug("PurchaseOrderGeneratorImpl.generatePurchaseOrder() method called FOR vendorSAN = "+vendorDTO.getVendorSAN()+" & vendorName = "+vendorDTO.getVendorName());
			poDAO = outTransFactory.getPurchaseOrderDAO();
			poTransList = poDAO.getPurchaseOrderDetails(vendorDTO.getVendorSAN(), vendorDTO.getProcessType(), transactionName);
			
			if(poTransList != null && poTransList.size()>0){
				xmlWriterCastor = new XmlWriterCastor();
				processedPOTransList = xmlWriterCastor.generateXml(poTransList, vendorDTO, outXmlGenerateDir, 
						outXmlArchiveDir, outInvalidXmlDir, transType, transactionName, 
						castorMappingFile, transactionSchema, papiNetSchema, outTransFactory);
			}else{
				B2BLogger.info("PurchaseOrderGeneratorImpl.generatePurchaseOrder() - No "+transactionName+" RECORD FOUND, SO "+transactionName+" XML NOT GENERATED FOR vendorSAN = "+vendorDTO.getVendorSAN()+" & vendorName = "+vendorDTO.getVendorName());
			}
			B2BLogger.debug("PurchaseOrderGeneratorImpl.generatePurchaseOrder() method return FOR vendorSAN = "+vendorDTO.getVendorSAN()+" & vendorName = "+vendorDTO.getVendorName());
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			poDAO = null;
			poTransList = null;		
			xmlWriterCastor = null;
		}
		return processedPOTransList;
	}
}