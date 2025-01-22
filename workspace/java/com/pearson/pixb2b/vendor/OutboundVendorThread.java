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
 * Title		: 	OutboundVendorThread.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi 	12 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.pearson.pixb2b.vendor.transaction.outbound.OutboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.IBookSpecificationGenerator;
import com.pearson.pixb2b.vendor.transaction.outbound.common.businessack.IBusinessAckGenerator;
import com.pearson.pixb2b.vendor.transaction.outbound.common.transstatus.dao.IOutboundTransStatusDAO;
import com.pearson.pixb2b.vendor.transaction.outbound.common.vendor.dao.IOutboundVendorDAO;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.IPurchaseOrderGenerator;
import com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.IShippingInstructionsGenerator;
import com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.FTPServerInfo;
import com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.VendorDTO;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.IConfigConstants;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;

/**
 * This class starts a Outbound Vendor Thread for a particular B2B Vendor
 * in an asynchronous way and complete the processing of all transactions 
 * of that vendor parallel to others.
 * 
 * @author Yogesh Tyagi
 */
public class OutboundVendorThread implements Runnable {	
	private String processType 		= null;
	private String vendorSAN 		= null;
	private String vendorName 		= null;	
	private String papiNetSchema 	= null;
	/**
	 * Constructor
	 * @param vendorSAN
	 * @param vendorName
	 */
	public OutboundVendorThread(String processType, String vendorSAN, String vendorName, String papiNetSchema) {
		this.processType  	= processType;
		this.vendorSAN  	= vendorSAN;
		this.vendorName 	= vendorName;
		this.papiNetSchema 	= papiNetSchema;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		OutboundTransactionFactory outTransFactory  = null;
		IOutboundVendorDAO outVendorDAO	= null;
		ArrayList vendorTransList	= null;		
		VendorDTO vendorDTO 		= null;
		FTPServerInfo ftpServerInfo	= null;		
		File fileDir				= null;
		String outDirGeneratedXml	= null;				
		String outDirArchiveXmlValid= null;
		String outDirArchiveXmlInvalid = null;
		IOutboundTransStatusDAO outTransStatusDAO 	= null;
		
		IPurchaseOrderGenerator poGenerator	  		= null;
		ArrayList processedPOTransList				= null;		
		IShippingInstructionsGenerator siGenerator	= null;
		ArrayList processedSITransList				= null;		
		IBookSpecificationGenerator bsGenerator		= null;
		ArrayList processedBSTransList				= null;		
		IBusinessAckGenerator ackGenerator			= null;
		ArrayList processedACKTransList				= null;
		
		try {
			B2BLogger.debug("OutboundVendorThread.run() method called - OutboundVendorThread STARTED FOR vendorSAN = "+vendorSAN+", vendorName = "+vendorName);
			if(vendorSAN != null && vendorSAN != null && !"".equals(vendorSAN.trim())){
				outTransFactory = OutboundTransactionFactory.newInstance();
				outVendorDAO = outTransFactory.getOutboundVendorDAO();
				vendorTransList = outVendorDAO.getOutboundVendorTransInfo(processType, vendorSAN);
				//sort the transactions based on transaction priority so that they are executed similerly.
				Collections.sort(vendorTransList,new Comparator<VendorDTO>()
						{
							public int compare(VendorDTO v1, VendorDTO v2) {
								return v1.getTransactionPriority()-v2.getTransactionPriority();
							};
						 }
				);
				if(vendorTransList != null && vendorTransList.size() > 0){
					for(int i=0; i<vendorTransList.size(); i++){
						vendorDTO = (VendorDTO)vendorTransList.get(i);
						if(vendorDTO != null 
								&& vendorDTO.getVendorSAN() != null && !"".equals(vendorDTO.getVendorSAN().trim()) 
								&& vendorDTO.getProcessType() != null && !"".equals(vendorDTO.getProcessType().trim())){
							
							ftpServerInfo = vendorDTO.getFtpServerInfo();
							if(ftpServerInfo != null){
								//Create required directories if not exist.
								outDirGeneratedXml = IConfigConstants.OUTBOUND_DIR_GENERATED_XML+File.separatorChar+vendorDTO.getVendorSAN();
								fileDir = new File(outDirGeneratedXml);
								if(!fileDir.exists())
									fileDir.mkdirs();
								
								outDirArchiveXmlValid = IConfigConstants.OUTBOUND_DIR_ARCHIVE_XML_VALID+File.separatorChar+vendorDTO.getVendorSAN();							
								fileDir = new File(outDirArchiveXmlValid);
								if(!fileDir.exists())
									fileDir.mkdirs();
								
								outDirArchiveXmlInvalid = IConfigConstants.OUTBOUND_DIR_ARCHIVE_XML_INVALID+File.separatorChar+vendorDTO.getVendorSAN();							
								fileDir = new File(outDirArchiveXmlInvalid);
								if(!fileDir.exists())
									fileDir.mkdirs();
								
								outTransStatusDAO = outTransFactory.getOutboundTransStatusDAO();
								
								if(IPixB2BConstants.transType_POR.equalsIgnoreCase(vendorDTO.getProcessType().trim())){
									//Create required directories if not exist.
									outDirGeneratedXml = outDirGeneratedXml+File.separatorChar+IPixB2BConstants.transType_POR.toLowerCase();
									fileDir = new File(outDirGeneratedXml);
									if(!fileDir.exists())
										fileDir.mkdirs();
									outDirArchiveXmlValid = outDirArchiveXmlValid+File.separatorChar+IPixB2BConstants.transType_POR.toLowerCase();
									fileDir = new File(outDirArchiveXmlValid);
									if(!fileDir.exists())
										fileDir.mkdirs();
									outDirArchiveXmlInvalid = outDirArchiveXmlInvalid+File.separatorChar+IPixB2BConstants.transType_POR.toLowerCase();
									fileDir = new File(outDirArchiveXmlInvalid);
									if(!fileDir.exists())
										fileDir.mkdirs();
									
									poGenerator = outTransFactory.getPurchaseOrderGenerator();
									processedPOTransList = poGenerator.generatePurchaseOrder(outTransFactory, vendorDTO, 
											outDirGeneratedXml, outDirArchiveXmlValid, outDirArchiveXmlInvalid, IPixB2BConstants.transType_POR, 
											IPixB2BConstants.PurchaseOrder, IPixB2BConstants.PO_Mapping_XML, IConfigConstants.SCHEMA_PurchaseOrder, papiNetSchema);
									if(processedPOTransList != null && processedPOTransList.size() >0)
										outTransStatusDAO.updateOutboundTransStatus(processedPOTransList);
								}else if(IPixB2BConstants.transType_SIP.equalsIgnoreCase(vendorDTO.getProcessType().trim())){
									//Create required directories if not exist.
									outDirGeneratedXml = outDirGeneratedXml+File.separatorChar+IPixB2BConstants.transType_SIP.toLowerCase();
									fileDir = new File(outDirGeneratedXml);
									if(!fileDir.exists())
										fileDir.mkdirs();
									outDirArchiveXmlValid = outDirArchiveXmlValid+File.separatorChar+IPixB2BConstants.transType_SIP.toLowerCase();
									fileDir = new File(outDirArchiveXmlValid);
									if(!fileDir.exists())
										fileDir.mkdirs();
									outDirArchiveXmlInvalid = outDirArchiveXmlInvalid+File.separatorChar+IPixB2BConstants.transType_SIP.toLowerCase();
									fileDir = new File(outDirArchiveXmlInvalid);
									if(!fileDir.exists())
										fileDir.mkdirs();
									
									siGenerator = outTransFactory.getShippingInstructionsGenerator();
									processedSITransList = siGenerator.generateShippingInstructions(outTransFactory, vendorDTO, 
											outDirGeneratedXml, outDirArchiveXmlValid, outDirArchiveXmlInvalid, IPixB2BConstants.transType_SIP, 
											IPixB2BConstants.ShippingInstructions, IPixB2BConstants.SI_Mapping_XML, IConfigConstants.SCHEMA_ShippingInstructions, papiNetSchema);
									if(processedSITransList != null && processedSITransList.size() >0)
										outTransStatusDAO.updateOutboundTransStatus(processedSITransList);
								}
								else if(IPixB2BConstants.transType_BSP.equalsIgnoreCase(vendorDTO.getProcessType().trim())){
									//Create required directories if not exist.
									outDirGeneratedXml = outDirGeneratedXml+File.separatorChar+IPixB2BConstants.transType_BSP.toLowerCase();
									fileDir = new File(outDirGeneratedXml);
									if(!fileDir.exists())
										fileDir.mkdirs();									
									outDirArchiveXmlValid = outDirArchiveXmlValid+File.separatorChar+IPixB2BConstants.transType_BSP.toLowerCase();
									fileDir = new File(outDirArchiveXmlValid);
									if(!fileDir.exists())
										fileDir.mkdirs();									
									outDirArchiveXmlInvalid = outDirArchiveXmlInvalid+File.separatorChar+IPixB2BConstants.transType_BSP.toLowerCase();
									fileDir = new File(outDirArchiveXmlInvalid);
									if(!fileDir.exists())
										fileDir.mkdirs();
									
									bsGenerator = outTransFactory.getBookSpecificationGenerator();
									processedBSTransList = bsGenerator.generateBookSpecification(outTransFactory, vendorDTO, 
											outDirGeneratedXml, outDirArchiveXmlValid, outDirArchiveXmlInvalid, IPixB2BConstants.transType_BSP, 
											IPixB2BConstants.BookSpecification, IPixB2BConstants.BS_Mapping_XML, IConfigConstants.SCHEMA_BookSpecification, papiNetSchema);
									if(processedBSTransList != null && processedBSTransList.size() >0)
										outTransStatusDAO.updateOutboundTransStatus(processedBSTransList);
								}
//								else if(transType_DME.equalsIgnoreCase(vendorDTO.getProcessType().trim())){
//									
//								}else
									if(IPixB2BConstants.transType_ACK.equalsIgnoreCase(vendorDTO.getProcessType().trim())){
									//Create required directories if not exist.
									outDirGeneratedXml = outDirGeneratedXml+File.separatorChar+IPixB2BConstants.transType_ACK.toLowerCase();
									fileDir = new File(outDirGeneratedXml);
									if(!fileDir.exists())
										fileDir.mkdirs();									
									outDirArchiveXmlValid = outDirArchiveXmlValid+File.separatorChar+IPixB2BConstants.transType_ACK.toLowerCase();
									fileDir = new File(outDirArchiveXmlValid);
									if(!fileDir.exists())
										fileDir.mkdirs();									
									outDirArchiveXmlInvalid = outDirArchiveXmlInvalid+File.separatorChar+IPixB2BConstants.transType_ACK.toLowerCase();
									fileDir = new File(outDirArchiveXmlInvalid);
									if(!fileDir.exists())
										fileDir.mkdirs();
									
									ackGenerator = outTransFactory.getBusinessAckGenerator();
									/**processedACKTransList = ackGenerator.generateBusinessAcknowledgement(outTransFactory, vendorDTO, 
											outDirGeneratedXml, outDirArchiveXmlValid, outDirArchiveXmlInvalid, transType_ACK, 
											BusinessAcknowledgement, BA_Mapping_XML, IConfigConstants.SCHEMA_BusinessAcknowledgement, papiNetSchema);
									*/
									processedACKTransList = ackGenerator.generateBusinessAckOutbound(outTransFactory, vendorDTO, 
											outDirGeneratedXml, outDirArchiveXmlValid, outDirArchiveXmlInvalid, IPixB2BConstants.transType_ACK, 
											IPixB2BConstants.BusinessAcknowledgement, IPixB2BConstants.BA_Mapping_XML, IConfigConstants.SCHEMA_BusinessAcknowledgement, papiNetSchema);
									
									if(processedACKTransList != null && processedACKTransList.size() >0)
										outTransStatusDAO.updateInboundTransBusinessAckStatus(processedACKTransList);
								}
							}else{
								B2BLogger.info("OutboundVendorThread.run() :: FTP Server details are null");
							}					
						}else{
							B2BLogger.info("OutboundVendorThread.run() : VendorSAN OR ProcessType is null");
						}
					} //end for loop
				}else{
					B2BLogger.info("OutboundVendorThread.run() : vendorTransList is null");
				}
			}else{
				B2BLogger.info("OutboundVendorThread.run() : vendorSAN is null");
			}
			B2BLogger.debug("OutboundVendorThread.run() method return - OutboundVendorThread Thread FINISHED FOR vendorSAN = "+vendorSAN+", vendorName = "+vendorName);
		} catch (RuntimeException e) {			
			B2BLogger.error("RuntimeException :: OutboundVendorThread interrupted FOR vendorSAN = "+vendorSAN+", vendorName = "+vendorName);
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			outTransFactory = null;
			outVendorDAO	= null;
			vendorTransList	= null;		
			vendorDTO 		= null;
			ftpServerInfo	= null;		
			outDirGeneratedXml = null;
			outDirArchiveXmlValid = null;
			outDirArchiveXmlInvalid= null;
			fileDir			= null;	
			outTransStatusDAO = null;
			poGenerator	  	= null;
			processedPOTransList= null;
			siGenerator		= null;
			processedSITransList= null;
			bsGenerator		= null;
			processedBSTransList= null;
			ackGenerator	= null;
			processedACKTransList= null;
		}
    }
}