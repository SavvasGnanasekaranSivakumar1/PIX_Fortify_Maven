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
 * Title		: 	BusinessAckProcessorImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	17 Nov,2009 	    Initial version
 * 1.1      Abhilasha Nigam  Jan 2010			Updated version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.common.businessack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;

import com.pearson.pixb2b.vendor.service.xml.XmlReaderCastor;
import com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.inbound.common.businessack.dao.IBusinessAckProcessorDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.OrderStatusDetail;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.ProductIdentifier;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.PurchaseOrderInformation;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.PurchaseOrderReference;
import com.pearson.pixb2b.vendor.transaction.shared.dto.businessack.BusinessAcknowledgementDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.businessack.DocumentError;
import com.pearson.pixb2b.vendor.transaction.shared.dto.businessack.DocumentReference;
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
 * BusinessAckProcessorImpl is an implementation class to process the 
 * the BusinessAcknowledgement Success/Failure transaction details for 
 * all Outbound Transactions sent to vendors.
 * 
 * @author Yogesh Tyagi
 */
public class BusinessAckProcessorImpl implements IBusinessAckProcessor{
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.common.businessack.IBusinessAckProcessor#processBusinessAcknowledgement(com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO)
	 */
	public String processBusinessAcknowledgement(InboundTransactionFactory inTransFactory, String xmlFileNameWithDir, String inDirArchiveXmlValid, String inDirArchiveXmlInvalid, String vendorSAN, TransactionStatusDTO transStatusDTO) {
		Mapping mapping 			= null;
		XmlReaderCastor xmlReaderCastor = null;
		PapiNetEnvelopeDTO pneDTO	= null;		
		IBusinessAckProcessorDAO baDAO = null;
		String status				= null;
		FileUtils fileUtils			= null;
		
		try {
			B2BLogger.debug("BusinessAckProcessorImpl.processBusinessAcknowledgement() method called");
			mapping = new Mapping();
			mapping.loadMapping(IPixB2BConstants.BA_Mapping_XML);
			xmlReaderCastor = new XmlReaderCastor();
			pneDTO = xmlReaderCastor.readXML(mapping, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorSAN, inTransFactory);
			if(pneDTO != null){
				baDAO = inTransFactory.getBusinessAckProcessorDAO();
				status = baDAO.storeBusinessAcknowledgementDetails(pneDTO, transStatusDTO);
				fileUtils = new FileUtils();
				fileUtils.moveFile(xmlFileNameWithDir, inDirArchiveXmlValid);
			}else{
				B2BLogger.info("BusinessAckProcessorImpl.processBusinessAcknowledgement() - pneDTO = "+pneDTO);
			}
			B2BLogger.debug("BusinessAckProcessorImpl.processBusinessAcknowledgement() method return");
		} catch (IOException e) {
			B2BLogger.error("IOException :: ",e);
			
		} catch (MappingException e) {
			B2BLogger.error("MappingException :: ",e);
		} finally{
			mapping = null;
			xmlReaderCastor = null;
			pneDTO	= null;
			baDAO = null;
			fileUtils = null;
		}
		return status;
	}
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.IBusinessAckProcessor#validateDataAndRegisterError(java.lang.String, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory, java.lang.String, java.lang.String)
	 */
	public Boolean validateDataAndRegisterError(String xmlFileNameWithDir, ArrayList validXMLList, String transType, String transName, String transactionSchema, String vendorSAN, String inDirArchiveXmlInvalid, InboundTransactionFactory inTransFactory, String documentNumber, String documentDate) {
		
		BusinessAcknowledgementDTO baDTO 	= null;		
		String baXmlStatus					= null;
		String baTransNameXml				= null;
		String docTransType                 = null;
		String baTransType					= null;
		String baDocumentNo					= null;
		ArrayList documentReferenceList		= null;
		DocumentReference documentReference	= null;
		String xmlTransId					= null;
		ArrayList documentErrorList			= null;
		DocumentError documentError 		= null;				
		int errorId							= -1;
		ArrayList errorIdList				= null;
		ArrayList errorDTOList				= null;
		IErrorDAO errorDAO 					= null;
		TransactionStatusDTO transStatusDTO = null;
		
		B2BHelper b2bHelper					= null;
		FileUtils fileUtils					= null;
		TransStatusHelper transStatusHelper	= null;
		String strFileNameWithExtn			= null;
		String strFileNameWithoutExtn 		= null;
		String[] arrStrFileName 			= null;
		String processStatus				= null;
		String ackStatus					= null;
		String errorCode                    = null;
		String errorDesc					= null;
		Mapping mapping 					= null;
		XmlReaderCastor xmlReaderCastor 	= null;
		PapiNetEnvelopeDTO pneDTO			= null;		
		
		Boolean dataValidationFlag			= Boolean.TRUE;
		Boolean validTransFlag				= Boolean.TRUE;
		
		try {
			mapping = new Mapping();
			mapping.loadMapping(IPixB2BConstants.BA_Mapping_XML);
			xmlReaderCastor = new XmlReaderCastor();
			pneDTO = xmlReaderCastor.readXML(mapping, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorSAN, inTransFactory);
			
			if(null!= pneDTO && null!=validXMLList && validXMLList.size()>0){				
				baDTO = pneDTO.getPayload().getBusinessDocument().getBaDTO();
				if(baDTO != null){
					errorIdList = new ArrayList();
					b2bHelper = new B2BHelper();
					
					baXmlStatus = baDTO.getStatus();		
					
					baTransNameXml = baDTO.getDocument().getDocumentName();						
					if(null!=baTransNameXml || !"".equals(baTransNameXml.trim()))
						{
						validTransFlag = validateTransName(baTransNameXml);
						    if(!validTransFlag){
						    	errorId = IPixB2BConstants.ERROR_ID_85;
						    	errorIdList.add(errorId);
						    	dataValidationFlag = Boolean.FALSE;
						    }
					
						    docTransType = getTransType(baTransNameXml);
						
						}
					
					documentReferenceList = baDTO.getDocument().getDocumentReferenceList();
					if(documentReferenceList != null && documentReferenceList.size()>0){
						for(int i= 0; i<documentReferenceList.size(); i++){
							documentReference = (DocumentReference)documentReferenceList.get(i);
							if(documentReference != null && documentReference.getDocumentReferenceType() != null ){
								if(IPixB2BConstants.TransactionID.equalsIgnoreCase(documentReference.getDocumentReferenceType().trim()))
									xmlTransId = documentReference.getDocumentReferenceValue();				
							}
						} //end for loop - documentReferenceList
					}
					
					if(null == xmlTransId || "".equals(xmlTransId.trim())){
						errorId = IPixB2BConstants.ERROR_ID_87;
						errorIdList.add(errorId);
						dataValidationFlag = Boolean.FALSE;
					}else{   //if(null != xmlTransId || !"".equals(xmlTransId.trim()))
						 int count = b2bHelper.verifyTransID(xmlTransId,docTransType);						
						if(count < 1){
							errorId = IPixB2BConstants.ERROR_ID_84;							
							errorIdList.add(errorId);
							dataValidationFlag = Boolean.FALSE;	
							B2BLogger.info(" BA Trans Id=  "+xmlTransId+ " is INVALID");
						}else{	
							B2BLogger.info(" BA Trans Id=  "+xmlTransId+ " is VALID");
							int counter = b2bHelper.executeQuery(xmlTransId);
							if(counter > 0){
								errorId = IPixB2BConstants.ERROR_ID_88;
								B2BLogger.info(" BA with Trans Id=  "+xmlTransId+ " is already processed");
								errorIdList.add(errorId);
								dataValidationFlag = Boolean.FALSE;
							}
						}
					}
					
					
					if(IPixB2BConstants.Failure.equals(baXmlStatus)){
						documentErrorList = baDTO.getDocumentErrorList();
						if(documentErrorList != null && documentErrorList.size() == 0){
							errorId = IPixB2BConstants.ERROR_ID_91;
							errorIdList.add(errorId);
							dataValidationFlag = Boolean.FALSE;
							/*
							for(int i=0; i<documentErrorList.size(); i++){
								documentError = (DocumentError)documentErrorList.get(i);
								if(null == documentError || "".equals(xmlTransId.trim())){
									errorId = ERROR_ID_91;
									errorIdList.add(errorId);
									dataValidationFlag = Boolean.FALSE;
								}
							}
						*/}
					}			
					
					/*if(Failure.equals(baXmlStatus)){
						documentErrorList = baDTO.getDocumentErrorList();
						if(documentErrorList != null && documentErrorList.size() > 0){
							for(int i=0; i<documentErrorList.size(); i++){
								documentError = (DocumentError)documentErrorList.get(i);
								if(documentError != null){
									errorCode = documentError.getErrorCode();									
									if(null==errorCode || "".equals(errorCode.trim())){
										errorId = ERROR_ID_89;
										errorIdList.add(errorId);
										dataValidationFlag = Boolean.FALSE;	
									}
								}
							}
						}else{
							errorId = ERROR_ID_91;
							errorIdList.add(errorId);
							dataValidationFlag = Boolean.FALSE;
						}
					}			*/		
					
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
					}else                                                //not reqd 4 BA
					{
						processStatus = IPixB2BConstants.processType_INBOUND; 
						ackStatus = IPixB2BConstants.status_KU;
					}
					String transactionId = b2bHelper.genratePIXTransactionId();
					
					errorDTOList = b2bHelper.prepareErrorDTOList(errorIdList, validXMLList, inDirArchiveXmlInvalid, transactionId, IPixB2BConstants.BA_ISSUE_DATE, documentDate);
					
					transStatusDTO = transStatusHelper.setTransactionStatus(processStatus, arrStrFileName[0], arrStrFileName[1],
							transactionId, b2bHelper.getLongTransType(arrStrFileName[2], null), b2bHelper.getTransName(arrStrFileName[2]),  
							strFileNameWithExtn, vendorSAN, inDirArchiveXmlInvalid, fileUtils.getFileSizeKB((String)validXMLList.get(0)), fileUtils.getFileSizeKB((String)validXMLList.get(0)),
							null, IPixB2BConstants.status_RE, IPixB2BConstants.status_FS, IPixB2BConstants.status_AS, IPixB2BConstants.status_MU, ackStatus, documentNumber, documentDate);
					
					//validXMLList.remove(0);    //moved to InboundVendorThread
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
	
	
	//New mtd
	private static Boolean validateTransName(String baTransNameXml){
		
		Boolean validTransFlag	= Boolean.FALSE;
		
		if(IPixB2BConstants.PurchaseOrder.equals(baTransNameXml))
			validTransFlag = Boolean.TRUE;
		else if(IPixB2BConstants.ShippingInstructions.equals(baTransNameXml))
			validTransFlag = Boolean.TRUE;
		else if(IPixB2BConstants.BookSpecification.equals(baTransNameXml))
			validTransFlag = Boolean.TRUE;
		else if(IPixB2BConstants.DeliveryMessage.equals(baTransNameXml))
			validTransFlag = Boolean.TRUE;
		else if(IPixB2BConstants.Plan.equals(baTransNameXml))
			validTransFlag = Boolean.TRUE;
		else 
			validTransFlag = Boolean.FALSE;
		
		return validTransFlag;
	}
	
	
	private static String getTransType(String baTransNameXml){
		
		String baTransType = null;
		
		if(IPixB2BConstants.PurchaseOrder.equals(baTransNameXml))
			baTransType = IPixB2BConstants.transType_POR;
		else if(IPixB2BConstants.ShippingInstructions.equals(baTransNameXml))
			baTransType = IPixB2BConstants.transType_SIP;
		else if(IPixB2BConstants.BookSpecification.equals(baTransNameXml))
			baTransType = IPixB2BConstants.transType_BSP;
		else if(IPixB2BConstants.DeliveryMessage.equals(baTransNameXml))
			baTransType = IPixB2BConstants.transType_DME;
		else if(IPixB2BConstants.Plan.equals(baTransNameXml))
			baTransType = IPixB2BConstants.transType_PLA;		
		
		return baTransType;
	}
	
}