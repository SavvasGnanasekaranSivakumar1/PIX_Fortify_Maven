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
 * Title		: 	InventoryStatusProcessorImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	12 Oct,2009 	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus;

import java.io.IOException;
import java.util.ArrayList;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;

import com.pearson.pixb2b.vendor.service.xml.XmlReaderCastor;
import com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dao.IInventoryStatusDAO;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.LineQuantity;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POInformation;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DelMesBookShip;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DelShipBookLineItem;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.error.ErrorDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.transaction.shared.error.dao.IErrorDAO;
import com.pearson.pixb2b.vendor.transaction.shared.helper.B2BHelper;
import com.pearson.pixb2b.vendor.transaction.shared.helper.TransStatusHelper;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.FileUtils;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * InventoryStatusProcessorImpl is an implementation class to process the 
 * the InventoryStatus Inbound Transaction XML for different vendors.
 * 
 * @author Yogesh Tyagi
 */
public class InventoryStatusProcessorImpl implements IInventoryStatusProcessor{
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.IInventoryStatusProcessor#processInventoryStatus(com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO)
	 */
	public String processInventoryStatus(InboundTransactionFactory inTransFactory, String xmlFileNameWithDir, String inDirArchiveXmlValid, String inDirArchiveXmlInvalid, String vendorSAN, TransactionStatusDTO transStatusDTO, ArrayList errorList) {
		Mapping mapping 			= null;
		XmlReaderCastor xmlReaderCastor = null;
		PapiNetEnvelopeDTO pneDTO	= null;		
		IInventoryStatusDAO isDAO 	= null;
		String status				= null;
		FileUtils fileUtils			= null;
				
		try {
			B2BLogger.debug("InventoryStatusProcessorImpl.processInventoryStatus() method called");
			mapping = new Mapping();
			mapping.loadMapping(IPixB2BConstants.IS_Mapping_XML);
			xmlReaderCastor = new XmlReaderCastor();
			pneDTO = xmlReaderCastor.readXML(mapping, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorSAN, inTransFactory);
			if(pneDTO != null){
				isDAO = inTransFactory.getInventoryStatusDAO();
				status = isDAO.storeInventoryStatusDetails(pneDTO, transStatusDTO, errorList, inDirArchiveXmlInvalid);
				fileUtils = new FileUtils();
				if(null!=errorList && errorList.size()>0)
					fileUtils.moveFile(xmlFileNameWithDir, inDirArchiveXmlInvalid);
				else
					fileUtils.moveFile(xmlFileNameWithDir, inDirArchiveXmlValid);
			}else{
				B2BLogger.info("InventoryStatusProcessorImpl.processInventoryStatus() - pneDTO = "+pneDTO);
			}
			B2BLogger.debug("InventoryStatusProcessorImpl.processInventoryStatus() method return");
		} catch (IOException e) {
			B2BLogger.error("IOException :: ",e);
		} catch (MappingException e) {
			B2BLogger.error("MappingException :: " ,e);
		} finally{
			mapping = null;
			xmlReaderCastor = null;
			pneDTO	= null;
			isDAO = null;
			fileUtils = null;
		}
		return status;
	}
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.IInventoryStatusProcessor#validateDataAndRegisterError(java.lang.String, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory, java.lang.String, java.lang.String)
	 */
	public Boolean validateDataAndRegisterError(String xmlFileNameWithDir, ArrayList validXMLList, String transType, String transName, String transactionSchema, String vendorSAN, String inDirArchiveXmlInvalid, InboundTransactionFactory inTransFactory, String documentNumber, String documentDate) {
		String xmlSupplierSAN 				= null;
		String xmlDMTransID 				= null;
		ArrayList errorDTOList				= null;
		TransactionStatusDTO transStatusDTO = null;
		B2BHelper b2bHelper					= null;
		FileUtils fileUtils					= null;
		TransStatusHelper transStatusHelper	= null;
		String strFileNameWithExtn			= null;
		String strFileNameWithoutExtn 		= null;
		String[] arrStrFileName 			= null;
		String processStatus				= null;
		String ackStatus					= null;
		ArrayList errorIdList				= null;
		Mapping mapping 					= null;
		XmlReaderCastor xmlReaderCastor 	= null;
		PapiNetEnvelopeDTO pneDTO			= null;		
		IErrorDAO errorDAO 					= null;
		Boolean dataValidationFlag			= Boolean.TRUE;
		ErrorDTO partialErrorDTO			= null;
		
		try {
			mapping = new Mapping();
			mapping.loadMapping(IPixB2BConstants.IS_Mapping_XML);
			xmlReaderCastor = new XmlReaderCastor();
			pneDTO = xmlReaderCastor.readXML(mapping, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorSAN, inTransFactory);
			
			if(null!= pneDTO && null!=validXMLList && validXMLList.size()>0){
				errorIdList = new ArrayList();
				b2bHelper = new B2BHelper();
				xmlSupplierSAN = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.VENDOR_SAN, IPixB2BConstants.transType_INV);
				if(null!=xmlSupplierSAN && !"".equals(xmlSupplierSAN)){
					xmlSupplierSAN = b2bHelper.addDashesInSAN(xmlSupplierSAN);
				}
				xmlDMTransID = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.XML_TRANS_ID, IPixB2BConstants.transType_INV);
				
				if(null==xmlDMTransID || "".equals(xmlDMTransID.trim())){
					partialErrorDTO = new ErrorDTO();
					partialErrorDTO.setErrorID(IPixB2BConstants.ERROR_ID_116+"");
					errorIdList.add(partialErrorDTO);
					dataValidationFlag = Boolean.FALSE;
				}else{
					String tableName1 = "pix_xml_trans_mapping pxtm ,pix_xmlread_log pxl";
					String whereClause1 = "pxtm.TRANS_HIST_NO='"+xmlDMTransID+"' and pxtm.process_type='"+IPixB2BConstants.transType_INV+"' and pxtm.pix_trans_id = pxl.TRANS_ID and pxl.SAN='"+xmlSupplierSAN+"' and pxl.PROCESS_TYPE='"+IPixB2BConstants.transType_INV+"'";
					int count1 = b2bHelper.checkForRefIntegrity(tableName1, whereClause1);
					if(count1>0)
					{
						partialErrorDTO = new ErrorDTO();
						partialErrorDTO.setErrorID(IPixB2BConstants.ERROR_ID_117+"");
						partialErrorDTO.setErrorDescription(xmlDMTransID);
						B2BLogger.info("The Transaction Id: "+xmlDMTransID+ "is already processed and stored in PIX_XML_TRANS_MAPPING table");
						errorIdList.add(partialErrorDTO);
						dataValidationFlag = Boolean.FALSE;
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
					
					errorDTOList = b2bHelper.prepareErrorDTOListEx(errorIdList, validXMLList, inDirArchiveXmlInvalid, transactionId, IPixB2BConstants.IS_ISSUE_DATE, documentDate);
					
					transStatusDTO = transStatusHelper.setTransactionStatus(processStatus, arrStrFileName[0], arrStrFileName[1],
							transactionId, b2bHelper.getLongTransType(arrStrFileName[2], null), b2bHelper.getTransName(arrStrFileName[2]),  
							strFileNameWithExtn, vendorSAN, inDirArchiveXmlInvalid, fileUtils.getFileSizeKB((String)validXMLList.get(0)), fileUtils.getFileSizeKB((String)validXMLList.get(0)),
							null, IPixB2BConstants.status_RE, IPixB2BConstants.status_FS, IPixB2BConstants.status_AS, IPixB2BConstants.status_MU, ackStatus, documentNumber, documentDate);
				}
				if(null!=errorDTOList && errorDTOList.size()>0){
					errorDAO = inTransFactory.geErrorDAO();
					errorDAO.storeInboundXmlErrorDetails(transStatusDTO, errorDTOList, IPixB2BConstants.B2B, IPixB2BConstants.VEN_TO_PIX, IPixB2BConstants.flag_U, pneDTO);
				}
			}//end if - validXmlList
			else{
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
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.IInventoryStatusProcessor#registerError(java.lang.String, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory, java.lang.String, java.lang.String, java.util.ArrayList, java.lang.String, com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO)
	 */
	public Boolean registerError(String xmlFileNameWithDir, ArrayList validXMLList, String transType, String transName, String transactionSchema, String vendorSAN, String inDirArchiveXmlInvalid, InboundTransactionFactory inTransFactory, String documentNumber, String documentDate,ArrayList errorList, String transactionId, PapiNetEnvelopeDTO pneDTO){
		ArrayList errorDTOList				= null;
		TransactionStatusDTO transStatusDTO = null;
		B2BHelper b2bHelper					= null;
		FileUtils fileUtils					= null;
		TransStatusHelper transStatusHelper	= null;
		String strFileNameWithExtn			= null;
		String strFileNameWithoutExtn 		= null;
		String[] arrStrFileName 			= null;
		String processStatus				= null;
		String ackStatus					= null;			
		IErrorDAO errorDAO 					= null;
		Boolean invStatusErrorFlag			= Boolean.TRUE;
		
		try {
			if(null!=validXMLList && validXMLList.size()> 0){
				if(errorList != null && errorList.size() > 0){
					
					fileUtils = new FileUtils();
					transStatusHelper = new TransStatusHelper();			
					b2bHelper = new B2BHelper();
						
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
					errorDTOList = b2bHelper.prepareDAOErrorList(errorList, validXMLList, inDirArchiveXmlInvalid, transactionId, IPixB2BConstants.IS_ISSUE_DATE, documentDate);
					
					transStatusDTO = transStatusHelper.setTransactionStatus(processStatus, arrStrFileName[0], arrStrFileName[1],
							transactionId, b2bHelper.getLongTransType(arrStrFileName[2], null), b2bHelper.getTransName(arrStrFileName[2]),  
							strFileNameWithExtn, vendorSAN, inDirArchiveXmlInvalid, fileUtils.getFileSizeKB((String)validXMLList.get(0)), fileUtils.getFileSizeKB((String)validXMLList.get(0)),
							null, IPixB2BConstants.status_RE, IPixB2BConstants.status_FS, IPixB2BConstants.status_AS, IPixB2BConstants.status_MU, ackStatus, documentNumber, documentDate);
						
					validXMLList.remove(0);
					
					if(null!=errorDTOList && errorDTOList.size()>0){
						invStatusErrorFlag = Boolean.FALSE;
						errorDAO = inTransFactory.geErrorDAO();
						errorDAO.storeDAOErrorDetails(transStatusDTO, errorDTOList, IPixB2BConstants.B2B, IPixB2BConstants.VEN_TO_PIX, IPixB2BConstants.flag_U, pneDTO);
					}
				}
			}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
		}
		return invStatusErrorFlag;
	}
}