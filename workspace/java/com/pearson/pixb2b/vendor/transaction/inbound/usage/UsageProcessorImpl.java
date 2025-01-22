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
 * Title		: 	UsageProcessorImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Abhilasha Nigam	24 Dec,2009 	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage;

import java.io.IOException;
import java.util.ArrayList;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;

import com.pearson.pixb2b.global.B2BGlobalData;
import com.pearson.pixb2b.vendor.InboundVendorThread;
import com.pearson.pixb2b.vendor.service.xml.XmlReaderCastor;
import com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dao.IUsageDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.EndPartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.ItemUsgProduct;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.UsageHeader;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.UsageLineItem;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.UsageProdIdentifier;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.UsageProduct;
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
 * UsageProcessorImpl is an implementation class to process the 
 * the Usage Inbound Transaction XML for different vendors.
 * 
 * @author Abhilasha Nigam
 */
public class UsageProcessorImpl implements IUsageProcessor{
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.usage.IUsageProcessor#processUsage(com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO)
	 */
	public String processUsageDetails(InboundTransactionFactory inTransFactory, String xmlFileNameWithDir, String inDirArchiveXmlValid, String inDirArchiveXmlInvalid, String vendorSAN, TransactionStatusDTO transStatusDTO, ArrayList errorList) {
		Mapping mapping 				= null;
		XmlReaderCastor xmlReaderCastor = null;
		PapiNetEnvelopeDTO pneDTO		= null;		
		IUsageDAO usageDAO 				= null;
		String status					= null;
		FileUtils fileUtils				= null;
		
		try {
			B2BLogger.debug("UsageProcessorImpl.processUsage() method called");
			mapping = new Mapping();
			mapping.loadMapping(IPixB2BConstants.US_Mapping_XML);
			xmlReaderCastor = new XmlReaderCastor();
			pneDTO = xmlReaderCastor.readXML(mapping, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorSAN, inTransFactory);
			if(pneDTO != null){
				usageDAO = inTransFactory.getUsageDAO();
				status = usageDAO.storeUsageDetails(pneDTO, transStatusDTO, errorList, inDirArchiveXmlInvalid);
				fileUtils = new FileUtils();
				if(null!=errorList && errorList.size()>0)
					fileUtils.moveFile(xmlFileNameWithDir, inDirArchiveXmlInvalid);
				else
					fileUtils.moveFile(xmlFileNameWithDir, inDirArchiveXmlValid);
			}else{
				B2BLogger.info("UsageProcessorImpl.processUsage() - pneDTO = "+pneDTO);
			}
			B2BLogger.debug("UsageProcessorImpl.processUsage() method return");
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
			usageDAO 	= null;
			fileUtils = null;
		}
		return status;
	}
	
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.usage.IUsageProcessor#validateDataAndRegisterError(java.lang.String, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory, java.lang.String, java.lang.String)
	 */
	public Boolean validateDataAndRegisterError(String xmlFileNameWithDir, ArrayList validXMLList, String transType, String transName, String transactionSchema, String vendorSAN, String inDirArchiveXmlInvalid, InboundTransactionFactory inTransFactory, String documentNumber, String documentDate) {
		UsageHeader usgHdr 					= null;
		String xmlSupplierSAN 				= null;
		String xmlUsgTransID 				= null;
		ArrayList usgLineItemList  			= null;
		UsageLineItem usgLineItem 			= null;
		String xmlProdCode 					= null;
		String xmlPONumber	  				= null;
		ArrayList xmlPONumberDup			= null;
		UsageProduct usgProd 				= null;
		ArrayList xmlProdIdentfList 		= null;
		ItemUsgProduct itemUsgProd 			= null;
		UsageProdIdentifier xmlProdIdentf 	= null;
		String xmlPOTransId					= null;
		EndPartyIdentifier partyIdentifier 	= null;
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
		String dbPOId						= null;
		int errorId							= -1;
		ArrayList errorIdList				= null;
		Mapping mapping 					= null;
		XmlReaderCastor xmlReaderCastor 	= null;
		PapiNetEnvelopeDTO pneDTO			= null;		
		IErrorDAO errorDAO 					= null;
		Boolean dataValidationFlag			= Boolean.TRUE;
		ArrayList poNumberFlag				= null;
		ErrorDTO partialErrorDTO			= null;
		StringBuffer poString				= null;
		Boolean poNOFlag					= Boolean.FALSE;
		
		try {
			mapping = new Mapping();
			mapping.loadMapping(IPixB2BConstants.US_Mapping_XML);
			xmlReaderCastor = new XmlReaderCastor();
			pneDTO = xmlReaderCastor.readXML(mapping, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorSAN, inTransFactory);
			
			if(null!= pneDTO && null!=validXMLList && validXMLList.size()>0){
				errorIdList = new ArrayList();
				b2bHelper = new B2BHelper();
				usgHdr = pneDTO.getPayload().getBusinessDocument().getUsgDTO().getUsageHeader();						
				if(null!=usgHdr){
					xmlSupplierSAN = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.VENDOR_SAN, IPixB2BConstants.transType_USG);
					if(xmlSupplierSAN.contains("-")){
						xmlSupplierSAN = xmlSupplierSAN.replaceAll("-","");
					}
					xmlUsgTransID = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.XML_TRANS_ID, IPixB2BConstants.transType_USG);
				}
				if(null==xmlUsgTransID || "".equals(xmlUsgTransID.trim())){
					partialErrorDTO = new ErrorDTO();
					partialErrorDTO.setErrorID(IPixB2BConstants.ERROR_ID_97+"");
					//errorId = ERROR_ID_97;
					//errorIdList.add(errorId);
					errorIdList.add(partialErrorDTO);
					dataValidationFlag = Boolean.FALSE;
				}else{
					String tableName1 = "pix_xml_trans_mapping pxtm ,pix_xmlread_log pxl";
					String whereClause1 = "pxtm.TRANS_HIST_NO='"+xmlUsgTransID+"' and pxtm.process_type='"+IPixB2BConstants.transType_USG+"' and pxtm.pix_trans_id = pxl.TRANS_ID and replace(pxl.SAN, '-', '')='"+xmlSupplierSAN+"' and pxl.PROCESS_TYPE='"+IPixB2BConstants.transType_USG+"'";
					int count1 = b2bHelper.checkForRefIntegrity(tableName1, whereClause1);
					if(count1>0)
					{
						partialErrorDTO = new ErrorDTO();
						partialErrorDTO.setErrorID(IPixB2BConstants.ERROR_ID_1+"");
						partialErrorDTO.setErrorDescription(xmlUsgTransID);
//						errorId = ERROR_ID_1;
						B2BLogger.info("The Transaction Id: "+xmlUsgTransID+ "is already processed and stored in PIX_XML_TRANS_MAPPING table");
						//errorIdList.add(errorId);
						errorIdList.add(partialErrorDTO);
						dataValidationFlag = Boolean.FALSE;
					}else{
						usgLineItemList = pneDTO.getPayload().getBusinessDocument().getUsgDTO().getUsageLineItem();
						if(null!=usgLineItemList && usgLineItemList.size()>0){
							poNumberFlag = new ArrayList();
							xmlPONumberDup = new ArrayList();
							poString 	= new StringBuffer();
							for(int k=0;k<usgLineItemList.size();k++){
								usgLineItem = (UsageLineItem)usgLineItemList.get(k);
								if(null!=usgLineItem){
									xmlPONumber	= null;
									if(usgLineItem.getAggrLineItem()!= null && usgLineItem.getAggrLineItem().getPoInfo() != null){													
										xmlPONumber = usgLineItem.getAggrLineItem().getPoInfo().getPoNumber();
									}else if(usgLineItem.getItemzdLineItem()!= null && usgLineItem.getItemzdLineItem().getItemPoInfo() != null){
										xmlPONumber = usgLineItem.getItemzdLineItem().getItemPoInfo().getPoNumber();
									}
									
//									xmlProdCode = null;	
//									usgProd = usgLineItem.getAggrLineItem().getUsgProduct();
//									if(null!=usgProd){
//										xmlProdIdentfList = usgProd.getProdIdentifier();
//									}else{
//										itemUsgProd = usgLineItem.getItemzdLineItem().getIusgProduct();
//										xmlProdIdentfList = itemUsgProd.getIProdIdentifier();
//									}
									
//									if(null!=xmlProdIdentfList && xmlProdIdentfList.size()> 0){
//										for(int n=0; n<xmlProdIdentfList.size(); n++){
//											xmlProdIdentf = (UsageProdIdentifier)xmlProdIdentfList.get(n);
//											if(xmlProdIdentf != null && xmlProdIdentf.getProductAgency() != null && Buyer.equalsIgnoreCase(xmlProdIdentf.getProductAgency().trim()))
//												xmlProdCode = xmlProdIdentf.getProductIdValue();									   
//										}
//									}
								}
								if(null==xmlPONumber || "".equals(xmlPONumber)){
									poNumberFlag.add(Boolean.FALSE);
								}else{
									poNOFlag = Boolean.TRUE;
									String tableName = "PIX_PO_SUMMARY";
									String whereClause = "PO_NO='"+xmlPONumber+"'";
									int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
									if(count<=0){
										poString.append(xmlPONumber+IPixB2BConstants.COMMA);
										poNumberFlag.add(Boolean.FALSE);
									}else{
										poNumberFlag.add(Boolean.TRUE);
										dbPOId = b2bHelper.getPoId(xmlPONumber);
									}
								}
//								if(null!=xmlPONumber && !"".equals(xmlPONumber)){
//									errorId = ERROR_ID_74;
//									errorIdList.add(errorId);
//									dataValidationFlag = Boolean.FALSE;
//								}else{
//									String tableName = "PIX_PO_SUMMARY";
//									String whereClause = "PO_NO='"+xmlPONumber+"'";
//									int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
//									if(count<=0){
//										errorId = ERROR_ID_30;
//										B2BLogger.info("The PO Number: "+xmlPONumber+ "is not present in PIX_PO_SUMMARY table");
//										errorIdList.add(errorId);
//										dataValidationFlag = Boolean.FALSE;
//									}else{
//										dbPOId = b2bHelper.getPoId(xmlPONumber);
//									}
									
//								}
//								if(null==xmlUsgTransID || "".equals(xmlUsgTransID.trim())){
//									errorId = ERROR_ID_97;
//									errorIdList.add(errorId);
//									dataValidationFlag = Boolean.FALSE;
//								}else if(null!=dbPOId && !"".equals(dbPOId.trim())) {
//									String tableName = "PIX_USAGE";
//									String whereClause = "PO_ID='"+dbPOId+"' and TRANSACTION_HISTORY_NO='"+xmlUsgTransID+"'";
//									int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
//									String tableName1 = "pix_xml_trans_mapping pxtm ,pix_xmlread_log pxl";
//									String whereClause1 = "pxtm.vendor_trans_id='"+documentNumber+"' and pxtm.process_type='"+transType_USG+"' and pxtm.pix_trans_id = pxl.TRANS_ID and replace(pxl.SAN, '-', '')='"+xmlSupplierSAN+"' and pxl.PROCESS_TYPE='"+transType_USG+"' and pxl.XML_READ_FLAG='RS'";
//									int count1 = b2bHelper.checkForRefIntegrity(tableName1, whereClause1);
//									
//									String tableName1 = "pix_xml_trans_mapping pxtm ,pix_xmlread_log pxl";
//									String whereClause1 = "pxtm.TRANS_HIST_NO='"+xmlUsgTransID+"' and pxtm.process_type='"+transType_USG+"' and pxtm.pix_trans_id = pxl.TRANS_ID and replace(pxl.SAN, '-', '')='"+xmlSupplierSAN+"' and pxl.PROCESS_TYPE='"+transType_USG+"'";
//									int count = b2bHelper.checkForRefIntegrity(tableName1, whereClause1);

//									if(count>0 || count1>0)
//									if(count>0)
//									{
//										errorId = ERROR_ID_1;
//										B2BLogger.info("The Transaction Id: "+xmlUsgTransID+ "is already processed and stored in PIX_XML_TRANS_MAPPING table");
//										errorIdList.add(errorId);
//										dataValidationFlag = Boolean.FALSE;
//										break;
//									}
//								}
//								if(null!=dbPOId && !"".equals(dbPOId.trim())
//										&& null!=xmlProdCode && !"".equals(xmlProdCode.trim())){
//									String tableName = "PIX_PO_USAGE";
//									String whereClause = "PO_ID='"+dbPOId+"' AND decode((case when instr(product_code, '-', 1, 1) > 0 then 0 WHEN instr(product_code, '-', 1, 1) = 0 then 1 end), 0,substr(product_code, 4, 5),ltrim(product_code, '0')) = ltrim('"+xmlProdCode+"', '0')";
//									int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
//									if(count<=0){
//										errorId = ERROR_ID_12;
//										B2BLogger.info("The Product code: "+xmlProdCode+ "is not present in PIX_PO_USAGE table");
//										errorIdList.add(errorId);
//										dataValidationFlag = Boolean.FALSE;
//									}
//								}
								if(null!=dbPOId && !"".equals(dbPOId.trim()) && !xmlPONumberDup.contains(xmlPONumber)
										&& null!=xmlSupplierSAN && !"".equals(xmlSupplierSAN.trim())){
									String tableName = "pix_po_summary p";
									String whereClause = "PO_ID='"+dbPOId+"' and replace(p.supplier_san, '-', '')='"+xmlSupplierSAN+"'";
									int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
									if(count<=0){
										partialErrorDTO = new ErrorDTO();
										partialErrorDTO.setErrorID(IPixB2BConstants.ERROR_ID_8+"");
										partialErrorDTO.setErrorDescription(xmlSupplierSAN);
										//errorId = ERROR_ID_8;
										B2BLogger.info("The Supplier SAN: "+xmlSupplierSAN+ "is not present in pix_po_summary table");
										//errorIdList.add(errorId);
										errorIdList.add(partialErrorDTO);
										dataValidationFlag = Boolean.FALSE;
									}
									xmlPONumberDup.add(xmlPONumber);
								}
								
							}
							if(null!=poNumberFlag && poNumberFlag.size()>0 && !poNumberFlag.contains(Boolean.TRUE)){
								partialErrorDTO = new ErrorDTO();
								if(!poNOFlag){
									partialErrorDTO.setErrorID(IPixB2BConstants.ERROR_ID_74+"");
									errorIdList.add(partialErrorDTO);
								}
								else{
									partialErrorDTO.setErrorID(IPixB2BConstants.ERROR_ID_30+"");
									String poStr = poString.toString();
									partialErrorDTO.setErrorDescription(poStr.substring(0, poStr.length()-1));
									B2BGlobalData.getGlobalDataObject().addInfoToFileContext(Thread.currentThread().getName(), IPixB2BConstants.PO_NO, poStr.substring(0, poStr.length()-1));
									//errorId = ERROR_ID_30;
									B2BLogger.info("All the PO Number is either invalid or null");
									//errorIdList.add(errorId);
									errorIdList.add(partialErrorDTO);
								}
								dataValidationFlag = Boolean.FALSE;
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
					
					errorDTOList = b2bHelper.prepareErrorDTOListEx(errorIdList, validXMLList, inDirArchiveXmlInvalid, transactionId, IPixB2BConstants.USAGE_ISSUE_DATE, documentDate);
					
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
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.usage.IUsageProcessor#registerError(java.lang.String, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory, java.lang.String, java.lang.String, java.util.ArrayList)
	 */
	public Boolean registerError(String xmlFileNameWithDir, ArrayList validXMLList, String transType, String transName, String transactionSchema, String vendorSAN, String inDirArchiveXmlInvalid, InboundTransactionFactory inTransFactory, String documentNumber, String documentDate,ArrayList errorList, String transactionId, PapiNetEnvelopeDTO pneDTO){
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
//		PapiNetEnvelopeDTO pneDTO			= null;		
		IErrorDAO errorDAO 					= null;
		Boolean usageErrorFlag			= Boolean.TRUE;
		
		try {
			if(null!=validXMLList && validXMLList.size()> 0){
				if(errorList != null && errorList.size() > 0){
					
					fileUtils = new FileUtils();
					transStatusHelper = new TransStatusHelper();			
					b2bHelper = new B2BHelper();
//					fileUtils.moveFile((String)validXMLList.get(0), inDirArchiveXmlInvalid);
						
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
					errorDTOList = b2bHelper.prepareDAOErrorList(errorList, validXMLList, inDirArchiveXmlInvalid, transactionId, IPixB2BConstants.USAGE_ISSUE_DATE, documentDate);
					
					transStatusDTO = transStatusHelper.setTransactionStatus(processStatus, arrStrFileName[0], arrStrFileName[1],
							transactionId, b2bHelper.getLongTransType(arrStrFileName[2], null), b2bHelper.getTransName(arrStrFileName[2]),  
							strFileNameWithExtn, vendorSAN, inDirArchiveXmlInvalid, fileUtils.getFileSizeKB((String)validXMLList.get(0)), fileUtils.getFileSizeKB((String)validXMLList.get(0)),
							null, IPixB2BConstants.status_RE, IPixB2BConstants.status_FS, IPixB2BConstants.status_AS, IPixB2BConstants.status_MU, ackStatus, documentNumber, documentDate);
						
					validXMLList.remove(0);
					
					if(null!=errorDTOList && errorDTOList.size()>0){
						usageErrorFlag = Boolean.FALSE;
						errorDAO = inTransFactory.geErrorDAO();
						errorDAO.storeUsageXmlErrorDetails(transStatusDTO, errorDTOList, IPixB2BConstants.B2B, IPixB2BConstants.VEN_TO_PIX, IPixB2BConstants.flag_U, pneDTO);
					}
				}
			}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
		}
		return usageErrorFlag;
	}
	
}