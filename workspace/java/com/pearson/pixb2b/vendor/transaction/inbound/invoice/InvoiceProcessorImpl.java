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
 * Title		: 	InvoiceProcessorImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	 Abhilasha Nigam 	8 Feb,2010 	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.invoice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;

import com.pearson.pixb2b.global.B2BGlobalData;
import com.pearson.pixb2b.vendor.service.xml.XmlReaderCastor;
import com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dao.IInvoiceDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dao.InvoiceDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.InvoiceDTO;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.InvoiceHeader;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.InvoiceLineItem;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.InvoiceRef;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.SupplierParty;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.OrderStatusDetail;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.ProductIdentifier;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.PurchaseOrderInformation;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.PurchaseOrderReference;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POReference;
import com.pearson.pixb2b.vendor.transaction.shared.dto.SupplierPartyPartyIdentifier;
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
 * InvoiceProcessorImpl is an implementation class to process the 
 * the Invoice Inbound Transaction XML for different vendors.
 * 
 * @author Abhilasha Nigam
 */
public class InvoiceProcessorImpl implements IInvoiceProcessor{
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.Invoice.IInvoiceProcessor#processInvoice(com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO)
	 */
	public String processInvoice(InboundTransactionFactory inTransFactory, String xmlFileNameWithDir, String inDirArchiveXmlValid, String inDirArchiveXmlInvalid, String vendorSAN, TransactionStatusDTO transStatusDTO, ArrayList errorList) {
		Mapping mapping 				= null;
		XmlReaderCastor xmlReaderCastor = null;
		PapiNetEnvelopeDTO pneDTO	 	= null;		
		IInvoiceDAO invDAO 				= null;
		String status					= null;
		FileUtils fileUtils				= null;
		
		try {
			B2BLogger.debug("InvoiceProcessorImpl.processInvoice() method called");
			mapping = new Mapping();
			mapping.loadMapping(IPixB2BConstants.INVO_Mapping_XML);
			xmlReaderCastor = new XmlReaderCastor();
			pneDTO = xmlReaderCastor.readXML(mapping, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorSAN, inTransFactory);
			if(pneDTO != null){
				invDAO = inTransFactory.getInvoiceDAO();				
				status = invDAO.storeInvoiceDetails(pneDTO, transStatusDTO,errorList); 				
				if(null!=errorList && errorList.size()==0){
					fileUtils = new FileUtils();
					fileUtils.moveFile(xmlFileNameWithDir, inDirArchiveXmlValid);
				}
			}else{
				B2BLogger.info("InvoiceProcessorImpl.processInvoice() - pneDTO = "+pneDTO);
			}
			B2BLogger.debug("InvoiceProcessorImpl.processInvoice() method return");
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
			invDAO 	= null;
			fileUtils = null;
		}
		return status;
	}
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.IOrderStatusProcessor#validateDataAndRegisterError(java.lang.String, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory, java.lang.String, java.lang.String)
	 */
	public Boolean validateDataAndRegisterError(String xmlFileNameWithDir, ArrayList validXMLList, String transType, String transName, String transactionSchema, String vendorSAN, String inDirArchiveXmlInvalid, InboundTransactionFactory inTransFactory, String documentNumber, String documentDate) {
		
		InvoiceDTO invoDTO 					     = null;
		InvoiceHeader xmlInvoHdr                 = null;		
		ArrayList xmlInvoRefList 				 = null;
		InvoiceRef xmlInvoRef 					 = null;			
		String xmlInvoiceTransID				 = null;
		String xmlSupplierSAN 					 = null;
		String supplierSAN                       = null;
		ArrayList supplierPartyList 			 = null;		
		SupplierPartyPartyIdentifier suppPartyID = null;
		
		ArrayList errorDTOList					= null;
		TransactionStatusDTO transStatusDTO 	= null;
		
		B2BHelper b2bHelper					= null;
		FileUtils fileUtils					= null;
		TransStatusHelper transStatusHelper	= null;
		String strFileNameWithExtn			= null;
		String strFileNameWithoutExtn 		= null;
		String[] arrStrFileName 			= null;
		String processStatus				= null;
		String ackStatus					= null;		
		String invoiceNo                    = null;		
		int errorId							= -1;
		
		ArrayList errorIdList				= null;
		Mapping mapping 					= null;
		XmlReaderCastor xmlReaderCastor 	= null;
		PapiNetEnvelopeDTO pneDTO			= null;		
		IErrorDAO errorDAO 					= null;
		ErrorDTO daoErrorDTO   				= null;
		Boolean dataValidationFlag			= Boolean.TRUE;
		
		try {
			mapping = new Mapping();
			mapping.loadMapping(IPixB2BConstants.INVO_Mapping_XML);
			xmlReaderCastor = new XmlReaderCastor();
			pneDTO = xmlReaderCastor.readXML(mapping, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorSAN, inTransFactory);
			
				
			if(null!= pneDTO && null!=validXMLList && validXMLList.size()> 0){
				errorIdList = new ArrayList();
				b2bHelper = new B2BHelper();
				invoDTO = pneDTO.getPayload().getBusinessDocument().getInvoDTO();
				if(null != invoDTO){					
					//daoErrorDTO = new ErrorDTO();
					xmlInvoHdr = pneDTO.getPayload().getBusinessDocument().getInvoDTO().getInvHeader();			
					if(null != xmlInvoHdr){						
						invoiceNo = xmlInvoHdr.getInvNumber();
						
						supplierPartyList = new ArrayList();
						
						suppPartyID = new SupplierPartyPartyIdentifier();
						
						supplierPartyList = xmlInvoHdr.getSuppParty().getSuppPartyIdList();
						if(supplierPartyList != null && supplierPartyList.size() > 0){
							for(int j=0; j<supplierPartyList.size(); j++){
								suppPartyID = (SupplierPartyPartyIdentifier) supplierPartyList.get(j);
								if(suppPartyID != null && suppPartyID.getPartyIdentifierType() != null && IPixB2BConstants.StandardAddressNumber.equalsIgnoreCase(suppPartyID.getPartyIdentifierType().trim())){
									xmlSupplierSAN = suppPartyID.getPartyIdentifierValue();
									if(xmlSupplierSAN.contains("-")){
										supplierSAN = xmlSupplierSAN;
										xmlSupplierSAN = xmlSupplierSAN.replaceAll("-","");
									}else if(null!=xmlSupplierSAN){
										supplierSAN = b2bHelper.addDashesInSAN(xmlSupplierSAN);	
									}
								}
							}
						}
						
						xmlInvoRefList = new ArrayList();
						xmlInvoRef = new InvoiceRef();
						xmlInvoRefList = xmlInvoHdr.getInvRef();
						if(xmlInvoRefList != null && xmlInvoRefList.size() > 0){
							for(int i= 0; i<xmlInvoRefList.size(); i++){
								xmlInvoRef = (InvoiceRef) xmlInvoRefList.get(i);
								if(null!= xmlInvoRef  && null!= xmlInvoRef.getInvRefType() && IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlInvoRef.getInvRefType().trim()))
									xmlInvoiceTransID = xmlInvoRef.getInvRefValue();						
							}
						}
					}
				}
						
				if(null==xmlInvoiceTransID || "".equals(xmlInvoiceTransID.trim())){
					errorId = IPixB2BConstants.ERROR_ID_94;	
					errorIdList.add(errorId);	
					dataValidationFlag = Boolean.FALSE;
				}else{
					String tableName1 = "pix_xml_trans_mapping pxtm ,pix_xmlread_log pxl";
					String whereClause1 = "pxtm.TRANS_HIST_NO='"+xmlInvoiceTransID+"' and pxtm.process_type='"+IPixB2BConstants.transType_INVO+"' and pxtm.pix_trans_id = pxl.TRANS_ID and replace(pxl.SAN, '-', '')='"+xmlSupplierSAN+"' and pxl.PROCESS_TYPE='"+IPixB2BConstants.transType_INVO+"'";
					int count1 = b2bHelper.checkForRefIntegrity(tableName1, whereClause1);
					if(count1>0)
					{
						errorId = IPixB2BConstants.ERROR_ID_1;
						B2BLogger.info("The Transaction Id: "+xmlInvoiceTransID+ "is already processed and stored in PIX_XML_TRANS_MAPPING table");
						errorIdList.add(errorId);
						dataValidationFlag = Boolean.FALSE;
					}else{
						if(null!=invoiceNo || !"".equals(invoiceNo.trim())){
							/**errorId = ERROR_ID_93;
							errorIdList.add(errorId);
							dataValidationFlag = Boolean.FALSE;  */
							//daoErrorDTO.setErrorID(errorId+"");
							//errorIdList.add(daoErrorDTO);							
														
							String tableName = "pix_invoice_header";  
							String whereClause = "INVOICE_NO= '"+invoiceNo+"' AND SUPPLIER_SAN= '"+supplierSAN+"' AND blocked_flag='N' ";
							int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
							 if(count > 0){
								errorId = IPixB2BConstants.ERROR_ID_92;
								errorIdList.add(errorId);
								dataValidationFlag = Boolean.FALSE;
								B2BLogger.info("The Invoice Number: "+invoiceNo+ " ALREADY EXISTS");
								//daoErrorDTO.setErrorID(errorId+"");
								//errorIdList.add(daoErrorDTO);	
								
							 }
						}
								
								
//						xmlInvoRefList = new ArrayList();
//						xmlInvoRef = new InvoiceRef();
//						xmlInvoRefList = xmlInvoHdr.getInvRef();
//						if(xmlInvoRefList != null && xmlInvoRefList.size() > 0){
//							for(int i= 0; i<xmlInvoRefList.size(); i++){
//								xmlInvoRef = (InvoiceRef) xmlInvoRefList.get(i);
//								if(null!= xmlInvoRef  && null!= xmlInvoRef.getInvRefType() && TransactionID.equalsIgnoreCase(xmlInvoRef.getInvRefType().trim()))
//									xmlInvoiceTransID = xmlInvoRef.getInvRefValue();						
//							}
//						}
//								
//						if(null==xmlInvoiceTransID || "".equals(xmlInvoiceTransID.trim())){
//							errorId = ERROR_ID_94;	
//							errorIdList.add(errorId);	
//							dataValidationFlag = Boolean.FALSE;
//							//daoErrorDTO.setErrorID(errorId+"");
//							//errorIdList.add(daoErrorDTO);	
//						}
						
//					}					
//							
//				}
					}
				}
				
				if(!dataValidationFlag){
					fileUtils = new FileUtils();
					transStatusHelper = new TransStatusHelper();			
					
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
					
					errorDTOList = b2bHelper.prepareErrorDTOList(errorIdList, validXMLList, inDirArchiveXmlInvalid, transactionId, IPixB2BConstants.INVO_ISSUE_DATE, documentDate);
					
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
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.IOrderStatusProcessor#registerError(java.lang.String, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory, java.lang.String, java.lang.String)
	 */
	public Boolean registerError(String xmlFileNameWithDir, ArrayList validXMLList, String transType, String transName, String transactionSchema, String vendorSAN, String inDirArchiveXmlInvalid, InboundTransactionFactory inTransFactory, String documentNumber, String documentDate,ArrayList errorList,String transactionId) {
			
		ArrayList errorDTOList					= null;
		TransactionStatusDTO transStatusDTO 	= null;
		B2BHelper b2bHelper					= null;
		FileUtils fileUtils					= null;
		TransStatusHelper transStatusHelper	= null;
		String strFileNameWithExtn			= null;
		String strFileNameWithoutExtn 		= null;
		String[] arrStrFileName 			= null;
		String processStatus				= null;
		String ackStatus					= null;			
		Mapping mapping 					= null;
		XmlReaderCastor xmlReaderCastor 	= null;
		PapiNetEnvelopeDTO pneDTO			= null;		
		IErrorDAO errorDAO 					= null;
		Boolean invoiceErrorFlag			= Boolean.TRUE;
		//String transactionId                = null;
		long dbRunId			= -1;
		
		try {
			
			//xmlReaderCastor = new XmlReaderCastor();
			//pneDTO = xmlReaderCastor.readXML(mapping, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorSAN, inTransFactory);
			
			if(null!=validXMLList && validXMLList.size()> 0){
				if(errorList != null && errorList.size() > 0){
					
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
					 //transactionId = b2bHelper.genratePIXTransactionId(); //new trans id next generated which is passed to error_log
				
					errorDTOList = b2bHelper.prepareDAOErrorList(errorList, validXMLList, inDirArchiveXmlInvalid, transactionId, IPixB2BConstants.INVO_ISSUE_DATE, documentDate);
					
					transStatusDTO = transStatusHelper.setTransactionStatus(processStatus, arrStrFileName[0], arrStrFileName[1],
							transactionId, b2bHelper.getLongTransType(arrStrFileName[2], null), b2bHelper.getTransName(arrStrFileName[2]),  
							strFileNameWithExtn, vendorSAN, inDirArchiveXmlInvalid, fileUtils.getFileSizeKB((String)validXMLList.get(0)), fileUtils.getFileSizeKB((String)validXMLList.get(0)),
							null, IPixB2BConstants.status_RE, IPixB2BConstants.status_FS, IPixB2BConstants.status_AS, IPixB2BConstants.status_MU, ackStatus, documentNumber, documentDate);
					
					validXMLList.remove(0);
				
		
					if(null!=errorDTOList && errorDTOList.size()>0){
						invoiceErrorFlag = Boolean.FALSE;
						errorDAO = inTransFactory.geErrorDAO();
						errorDAO.storeInvoiceXmlErrorDetails(transStatusDTO, errorDTOList, IPixB2BConstants.B2B, IPixB2BConstants.VEN_TO_PIX, IPixB2BConstants.flag_U);
						
					}
				}
			}
		} catch (Exception e) {
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
		} 
		return invoiceErrorFlag;
	}

		
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.IOrderStatusProcessor#validateDataAndRegisterError(java.lang.String, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory, java.lang.String, java.lang.String)
	 */
	/**public Boolean validateDataAndRegisterError(String xmlFileNameWithDir, ArrayList validXMLList, String transType, String transName, String transactionSchema, String vendorSAN, String inDirArchiveXmlInvalid, InboundTransactionFactory inTransFactory, String documentNumber, String documentDate) {
		
		InvoiceDTO invoDTO 					     = null;
		InvoiceHeader xmlInvoHdr                 = null;		
		ArrayList xmlInvoRefList 				 = null;
		InvoiceRef xmlInvoRef 					 = null;			
		String xmlInvoiceTransID				 = null;
		String xmlSupplierSAN 					 = null;
		String supplierSAN                       = null;
		ArrayList supplierPartyList 			 = null;		
		SupplierPartyPartyIdentifier suppPartyID = null;
		
		ArrayList errorDTOList					= null;
		TransactionStatusDTO transStatusDTO 	= null;
		
		B2BHelper b2bHelper					= null;
		FileUtils fileUtils					= null;
		TransStatusHelper transStatusHelper	= null;
		String strFileNameWithExtn			= null;
		String strFileNameWithoutExtn 		= null;
		String[] arrStrFileName 			= null;
		String processStatus				= null;
		String ackStatus					= null;		
		String invoiceNo                    = null;		
		int errorId							= -1;
		ArrayList errorIdList				= null;
		Mapping mapping 					= null;
		XmlReaderCastor xmlReaderCastor 	= null;
		PapiNetEnvelopeDTO pneDTO			= null;		
		IErrorDAO errorDAO 					= null;
		Boolean dataValidationFlag			= Boolean.TRUE;
		
		try {
			mapping = new Mapping();
			mapping.loadMapping(INVO_Mapping_XML);
			xmlReaderCastor = new XmlReaderCastor();
			pneDTO = xmlReaderCastor.readXML(mapping, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorSAN, inTransFactory);
			
			if(null!= pneDTO && null!=validXMLList && validXMLList.size()> 0){
				invoDTO = pneDTO.getPayload().getBusinessDocument().getInvoDTO();
				if(null != invoDTO){
					errorIdList = new ArrayList();
					b2bHelper = new B2BHelper();
					
					xmlInvoHdr = pneDTO.getPayload().getBusinessDocument().getInvoDTO().getInvHeader();			
					if(null != xmlInvoHdr){						
						invoiceNo = xmlInvoHdr.getInvNumber();
						
						supplierPartyList = new ArrayList();
						
						suppPartyID = new SupplierPartyPartyIdentifier();
						
						supplierPartyList = xmlInvoHdr.getSuppParty().getSuppPartyIdList();
						if(supplierPartyList != null && supplierPartyList.size() > 0){
							for(int j=0; j<supplierPartyList.size(); j++){
								suppPartyID = (SupplierPartyPartyIdentifier) supplierPartyList.get(j);
								if(suppPartyID != null && suppPartyID.getPartyIdentifierType() != null && StandardAddressNumber.equalsIgnoreCase(suppPartyID.getPartyIdentifierType().trim())){
									xmlSupplierSAN = suppPartyID.getPartyIdentifierValue();	
									supplierSAN = b2bHelper.addDashesInSAN(xmlSupplierSAN);
								}
							}
						}
						
						System.out.println("supplierSAN = " + supplierSAN);
						if(null==invoiceNo || "".equals(invoiceNo.trim())){
							errorId = ERROR_ID_93;
							errorIdList.add(errorId);
							dataValidationFlag = Boolean.FALSE;
						}else{
							String tableName = "pix_invoice_header";  
							String whereClause = "INVOICE_NO= '"+invoiceNo+"' AND SUPPLIER_SAN= '"+supplierSAN+"'";
							int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
							 if(count > 0){
								errorId = ERROR_ID_92;
								B2BLogger.info("The Invoice Number: "+invoiceNo+ " ALREADY EXISTS");
								errorIdList.add(errorId);
								dataValidationFlag = Boolean.FALSE;
							}
						}
						
						
						
						xmlInvoRefList = new ArrayList();
						xmlInvoRef = new InvoiceRef();
						xmlInvoRefList = xmlInvoHdr.getInvRef();
						if(xmlInvoRefList != null && xmlInvoRefList.size() > 0){
							for(int i= 0; i<xmlInvoRefList.size(); i++){
								xmlInvoRef = (InvoiceRef) xmlInvoRefList.get(i);
								if(null!= xmlInvoRef  && null!= xmlInvoRef.getInvRefType() && TransactionID.equalsIgnoreCase(xmlInvoRef.getInvRefType().trim()))
									xmlInvoiceTransID = xmlInvoRef.getInvRefValue();						
							}
						}
						
						if(null==xmlInvoiceTransID || "".equals(xmlInvoiceTransID.trim())){
							errorId = ERROR_ID_94;
							errorIdList.add(errorId);
							dataValidationFlag = Boolean.FALSE;
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
					if(transType_ACK.equals(transType)){
						processStatus = processType_OUTBOUND;
						ackStatus = status_KE;
					}else
					{
						processStatus = processType_INBOUND;
						ackStatus = status_KU;
					}
					String transactionId = b2bHelper.genratePIXTransactionId();
					
					errorDTOList = b2bHelper.prepareErrorDTOList(errorIdList, validXMLList, inDirArchiveXmlInvalid, transactionId, INVO_ISSUE_DATE, documentDate);
					
					transStatusDTO = transStatusHelper.setTransactionStatus(processStatus, arrStrFileName[0], arrStrFileName[1],
							transactionId, b2bHelper.getLongTransType(arrStrFileName[2], null), b2bHelper.getTransName(arrStrFileName[2]),  
							strFileNameWithExtn, vendorSAN, inDirArchiveXmlInvalid, fileUtils.getFileSizeKB((String)validXMLList.get(0)), fileUtils.getFileSizeKB((String)validXMLList.get(0)),
							null, status_RE, status_FS, status_AS, status_MU, ackStatus, documentNumber, documentDate);
					
					validXMLList.remove(0);
				}
				
				if(null!=errorDTOList && errorDTOList.size()>0){
					errorDAO = inTransFactory.geErrorDAO();
					errorDAO.storeInboundXmlErrorDetails(transStatusDTO, errorDTOList, B2B, VEN_TO_PIX, flag_U, pneDTO);
				}
			}
		} catch (IOException e) {
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
		} catch (MappingException e) {
			B2BLogger.error("MappingException :: " + StringUtils.getStackTrace(e));
		}
		return dataValidationFlag;
	} */
	
}