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
 * Title		: 	OrderConfirmationProcessorImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	12 Oct,2009 	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;

import com.pearson.pixb2b.vendor.service.xml.XmlReaderCastor;
import com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dao.IOrderConfirmationDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto.OCHeader;
import com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto.OCLineItem;
import com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto.OCReference;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POReference;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.transaction.shared.error.dao.IErrorDAO;
import com.pearson.pixb2b.vendor.transaction.shared.helper.B2BHelper;
import com.pearson.pixb2b.vendor.transaction.shared.helper.TransStatusHelper;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.FileUtils;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * OrderConfirmationProcessorImpl is an implementation class to process the 
 * the OrderConfirmation Inbound Transaction XML for different vendors.
 * 
 * @author Yogesh Tyagi
 */
public class OrderConfirmationProcessorImpl implements IOrderConfirmationProcessor{
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.IOrderConfirmationProcessor#processOrderConfirmation(com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO)
	 */
	public String processOrderConfirmation(InboundTransactionFactory inTransFactory, String xmlFileNameWithDir, String inDirArchiveXmlValid, String inDirArchiveXmlInvalid, String vendorSAN, TransactionStatusDTO transStatusDTO) {
		Mapping mapping 			= null;
		XmlReaderCastor xmlReaderCastor = null;
		PapiNetEnvelopeDTO pneDTO	= null;		
		IOrderConfirmationDAO ocDAO = null;
		String status				= null;
		FileUtils fileUtils			= null;
		
		try {
			B2BLogger.debug("OrderConfirmationProcessorImpl.processOrderConfirmation() method called");
			mapping = new Mapping();
			mapping.loadMapping(IPixB2BConstants.OC_Mapping_XML);
			xmlReaderCastor = new XmlReaderCastor();
			pneDTO = xmlReaderCastor.readXML(mapping, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorSAN, inTransFactory);
			if(pneDTO != null){
				ocDAO = inTransFactory.getOrderConfirmationDAO();
				status = ocDAO.storeOrderConfirmationDetails(pneDTO, transStatusDTO);
				fileUtils = new FileUtils();
				fileUtils.moveFile(xmlFileNameWithDir, inDirArchiveXmlValid);
			}else{
				B2BLogger.info("OrderConfirmationProcessorImpl.processOrderConfirmation() - pneDTO = "+pneDTO);
			}
			B2BLogger.debug("OrderConfirmationProcessorImpl.processOrderConfirmation() method return");
		} catch (IOException e) {
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} catch (MappingException e) {
			B2BLogger.error("MappingException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			mapping = null;
			xmlReaderCastor = null;
			pneDTO	= null;
			ocDAO 	= null;
			fileUtils = null;
		}	
		return status;
	}
	
	
	public Boolean validateDataAndRegisterError(String xmlFileNameWithDir, ArrayList validXMLList, String transType, String transName, String transactionSchema, String vendorSAN, String inDirArchiveXmlInvalid, InboundTransactionFactory inTransFactory, String documentNumber, String documentDate){
		OCHeader xmlOCHeader		= null;
		String xmlPONumber	  		= null;
		ArrayList xmlPORefList		= null;
		POReference xmlPORef		= null;
		String xmlPOTransId			= null;
		ArrayList xmlOCLineItemList	= null;
		OCLineItem xmlOCLineItem	= null;
		String xmlPOLineNo 			= null;
		ArrayList xmlOCRefList		= null;
		OCReference xmlOCRef		= null;
		String xmlOCTransId			= null;
			
		
		ArrayList errorDTOList				= null;
		TransactionStatusDTO transStatusDTO = null;
		HashMap hmPoIdpoVersion				= null;
		B2BHelper b2bHelper					= null;
		FileUtils fileUtils					= null;
		TransStatusHelper transStatusHelper	= null;
		String strFileNameWithExtn			= null;
		String strFileNameWithoutExtn 		= null;
		String[] arrStrFileName 			= null;
		String processStatus				= null;
		String ackStatus					= null;
		String dbPOId						= null;
		String dbPOVersion					= null;
		String xmlSupplierSAN				= null;
		int errorId							= -1;
		ArrayList errorIdList				= null;
		Mapping mapping 					= null;
		XmlReaderCastor xmlReaderCastor 	= null;
		PapiNetEnvelopeDTO pneDTO			= null;		
		IErrorDAO errorDAO 					= null;
		Boolean dataValidationFlag			= Boolean.TRUE;
		
		try {
			mapping = new Mapping();
			mapping.loadMapping(IPixB2BConstants.OC_Mapping_XML);
			xmlReaderCastor = new XmlReaderCastor();
			pneDTO = xmlReaderCastor.readXML(mapping, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorSAN, inTransFactory);
			
			if(null!= pneDTO && null!=validXMLList && validXMLList.size()>0){
				errorIdList = new ArrayList();
				b2bHelper = new B2BHelper();
				xmlOCHeader = pneDTO.getPayload().getBusinessDocument().getOcDTO().getOcHeader();
				if(null!=xmlOCHeader){
					xmlPONumber = xmlOCHeader.getPoInformation().getPoNumber();
					xmlSupplierSAN = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.VENDOR_SAN, IPixB2BConstants.transType_OCO);
					if(xmlSupplierSAN.contains("-")){
						xmlSupplierSAN = xmlSupplierSAN.replaceAll("-","");
					}
					xmlPORefList = xmlOCHeader.getPoInformation().getPoReference();
					if(null!=xmlPORefList && xmlPORefList.size()>0){
						for(int i=0; i<xmlPORefList.size(); i++){
							xmlPORef = (POReference) xmlPORefList.get(i);  				
							if(null!=xmlPORef && xmlPORef.getPoReferenceType() != null && IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlPORef.getPoReferenceType().trim()))
								xmlPOTransId = xmlPORef.getPoReferenceValue();
						} 
					}
					xmlOCRefList = xmlOCHeader.getOcReference();
					if(null!=xmlOCRefList && xmlOCRefList.size()>0){
						for(int j=0; j<xmlOCRefList.size(); j++){
							xmlOCRef = (OCReference)xmlOCRefList.get(j);
							if(null!=xmlOCRef && xmlOCRef.getOcReferenceType()!= null && IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlOCRef.getOcReferenceType().trim())){
								xmlOCTransId = xmlOCRef.getOcReferenceValue();
							}
						}
					}
				}
				
				if(null==xmlOCTransId || "".equals(xmlOCTransId.trim())){
					errorId = IPixB2BConstants.ERROR_ID_59;
					errorIdList.add(errorId);
					dataValidationFlag = Boolean.FALSE;
				}else{
					String tableName1 = "pix_xml_trans_mapping pxtm ,pix_xmlread_log pxl";
					String whereClause1 = "pxtm.TRANS_HIST_NO='"+xmlOCTransId+"' and pxtm.process_type='"+IPixB2BConstants.transType_OCO+"' and pxtm.pix_trans_id = pxl.TRANS_ID and replace(pxl.SAN, '-', '')='"+xmlSupplierSAN+"' and pxl.PROCESS_TYPE='"+IPixB2BConstants.transType_OCO+"'";
					int count1 = b2bHelper.checkForRefIntegrity(tableName1, whereClause1);
					if(count1>0){
						errorId = IPixB2BConstants.ERROR_ID_1;
						B2BLogger.info("The Transaction Id: "+xmlOCTransId+ "is already processed and stored in PIX_XML_TRANS_MAPPING table");
						errorIdList.add(errorId);
						dataValidationFlag = Boolean.FALSE;
					}else{
						if(null==xmlPONumber || "".equals(xmlPONumber.trim())){
							errorId = IPixB2BConstants.ERROR_ID_74;
							errorIdList.add(errorId);
							dataValidationFlag = Boolean.FALSE;
						}else{
							String tableName = "PIX_PO_HEADER";
							String whereClause = "PO_NO='"+xmlPONumber+"'";
							int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
							if(count<=0){
								errorId = IPixB2BConstants.ERROR_ID_75;
								B2BLogger.info("The PO Number: "+xmlPONumber+ "is not present in PIX_PO_HEADER table");
								errorIdList.add(errorId);
								dataValidationFlag = Boolean.FALSE;
							}
						}
						if(null==xmlPOTransId || "".equals(xmlPOTransId.trim()))
						{
							errorId = IPixB2BConstants.ERROR_ID_76;
							errorIdList.add(errorId);
							dataValidationFlag = Boolean.FALSE;
						}else{
							String tableName = "PIX_XMLGEN_LOG";
							String whereClause = "PROCESS_TYPE='"+IPixB2BConstants.transType_POR+"' and TRANS_TYPE='"+IPixB2BConstants.B2B+"' and TRANS_ID='"+xmlPOTransId+"'";
							int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
							if(count<=0)
							{
								errorId = IPixB2BConstants.ERROR_ID_77;
								B2BLogger.info("The PO Transaction Id: "+xmlPOTransId+ "is not present in PIX_XMLGEN_LOG table");
								errorIdList.add(errorId);
								dataValidationFlag = Boolean.FALSE;
							}else{
								hmPoIdpoVersion = b2bHelper.getPoIdPoVersion(IPixB2BConstants.transType_POR, xmlPOTransId);
								if(hmPoIdpoVersion != null && hmPoIdpoVersion.size()>0){
									dbPOId = (String)hmPoIdpoVersion.get("PO_ID");
									dbPOVersion= (String)hmPoIdpoVersion.get("PO_VERSION");
								}
							}
		 				}
//						if(null==xmlOCTransId || "".equals(xmlOCTransId.trim())){
//							errorId = ERROR_ID_59;
//							errorIdList.add(errorId);
//							dataValidationFlag = Boolean.FALSE;
//						}
						
//						if(null!=dbPOId && !"".equals(dbPOId.trim()) && null!=dbPOVersion && !"".equals(dbPOVersion.trim())){
//							String tableName = "PIX_XML_TRANS_MAPPING";
//							String whereClause = "vendor_trans_id='"+documentNumber+"' AND process_type='"+transType_OCO
//							+"' AND pix_trans_id in (select trans_id from pix_xmlread_log where TRANSREF_LABEL_1='PO_ID' and TRANSREF_LABEL_2= 'PO_VERSION'"
//							+" and TRANSREF_ID_1='"+dbPOId+"' AND TRANSREF_ID_2='"+dbPOVersion+"' AND process_type='"+transType_OCO+"')";
//							String tableName = "pix_xml_trans_mapping pxtm ,pix_xmlread_log pxl";
//							String whereClause = "pxtm.TRANS_HIST_NO='"+xmlOCTransId+"' and pxtm.process_type='"+transType_OCO+"' and pxtm.pix_trans_id = pxl.TRANS_ID and replace(pxl.SAN, '-', '')='"+xmlSupplierSAN+"' and pxl.PROCESS_TYPE='"+transType_OCO+"'";
		//
//							int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
//							if(count>0){
//								errorId = ERROR_ID_1;
//								B2BLogger.info("The Transaction Id: "+xmlOCTransId+ "is already processed and stored in PIX_XML_TRANS_MAPPING table");
//								errorIdList.add(errorId);
//								dataValidationFlag = Boolean.FALSE;
//							}
//						}
						
						xmlOCLineItemList = pneDTO.getPayload().getBusinessDocument().getOcDTO().getOcLineItem();
						if(null!=xmlOCLineItemList &&  xmlOCLineItemList.size()>0){
							for(int i=0; i<xmlOCLineItemList.size(); i++){												
								xmlPOLineNo = null;
								xmlOCLineItem = (OCLineItem)xmlOCLineItemList.get(i);
								if(null!=xmlOCLineItem){
									xmlPOLineNo = xmlOCLineItem.getPoLineItemNo();
								}
								
								if(null==xmlPOLineNo || "".equals(xmlPOLineNo.trim())){
									errorId = IPixB2BConstants.ERROR_ID_78;
									errorIdList.add(errorId);
									dataValidationFlag = Boolean.FALSE;
								}else if(null!=dbPOId && !"".equals(dbPOId.trim()) && null!=dbPOVersion && !"".equals(dbPOVersion.trim())){
									String tableName = "PIX_PO_LINE";
									String whereClause = "PO_ID='"+dbPOId+"' and PO_VERSION='"+dbPOVersion+"' and PO_LINE_NO="+"'"+xmlPOLineNo+"'";
									int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
									if(count<=0){
										errorId = IPixB2BConstants.ERROR_ID_79;
										B2BLogger.info("The PO Line Item Number: "+xmlPOLineNo+ "is not present in PIX_PO_LINE table");
										errorIdList.add(errorId);
										dataValidationFlag = Boolean.FALSE;
									}
								}
							}
						}
					}
				}

				if(!dataValidationFlag){
					fileUtils = new FileUtils();
					transStatusHelper = new TransStatusHelper();			
					b2bHelper = new B2BHelper();
					fileUtils.moveFile((String)validXMLList.get(0), inDirArchiveXmlInvalid);
					
					strFileNameWithExtn = fileUtils.getFileName((String)validXMLList.get(0));
					strFileNameWithoutExtn = fileUtils.removeFileExtension(strFileNameWithExtn);
					arrStrFileName = strFileNameWithoutExtn.split("_");
					if(IPixB2BConstants.transType_ACK.equals(transType)){
						processStatus = IPixB2BConstants.processType_OUTBOUND;
						ackStatus = IPixB2BConstants.status_KE;
					}else
					{
						processStatus = IPixB2BConstants.processType_INBOUND;
						ackStatus = IPixB2BConstants.status_KU;
					}
					String transactionId = b2bHelper.genratePIXTransactionId();
					
					errorDTOList = b2bHelper.prepareErrorDTOList(errorIdList, validXMLList, inDirArchiveXmlInvalid, transactionId, IPixB2BConstants.OC_ISSUE_DATE, documentDate);
					
					transStatusDTO = transStatusHelper.setTransactionStatus(processStatus, arrStrFileName[0], arrStrFileName[1],
							transactionId, b2bHelper.getLongTransType(arrStrFileName[2], null), b2bHelper.getTransName(arrStrFileName[2]),  
							strFileNameWithExtn, vendorSAN, inDirArchiveXmlInvalid, fileUtils.getFileSizeKB((String)validXMLList.get(0)), fileUtils.getFileSizeKB((String)validXMLList.get(0)),
							null, IPixB2BConstants.status_RE, IPixB2BConstants.status_FS, IPixB2BConstants.status_AS, IPixB2BConstants.status_MU, ackStatus, documentNumber, documentDate);
					
					//validXMLList.remove(0);  //moved to InboundVendorThread
				}
				
				if(null!=errorDTOList && errorDTOList.size()>0){
					errorDAO = inTransFactory.geErrorDAO();
					errorDAO.storeInboundXmlErrorDetails(transStatusDTO, errorDTOList, IPixB2BConstants.B2B, IPixB2BConstants.VEN_TO_PIX, IPixB2BConstants.flag_U, pneDTO);
				}
			}else{
				dataValidationFlag = Boolean.FALSE; 
				B2BLogger.info("pneDTO is null");
			}
		} catch (IOException e) {
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
		} catch (MappingException e) {
			B2BLogger.error("MappingException :: " + StringUtils.getStackTrace(e));
		}
		return dataValidationFlag;
	}
}