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
 * Title		: 	GRProcessorImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Abhilasha Nigam	14 Jan,2010 	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt;

import java.io.IOException;
import java.util.ArrayList;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;

import com.pearson.pixb2b.vendor.service.xml.XmlReaderCastor;
import com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dao.IGdsRecptDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.GRHeader;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.GRRef;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.GdsRecptDTO;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.PartyIdentifier;
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
 * GRImpl is an implementation class to process the 
 * the goodsreceipt Inbound Transaction XML for different vendors.
 * 
 * @author Abhilasha Nigam
 */
public class GRProcessorImpl implements IGRProcessor{	

	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.usage.IGRProcessor#processGoodsReceipt(com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO)
	 */
	public String processGRDetails(InboundTransactionFactory inTransFactory, String xmlFileNameWithDir, String inDirArchiveXmlValid, String inDirArchiveXmlInvalid, String vendorSAN, TransactionStatusDTO transStatusDTO,ArrayList errorList) {
		Mapping mapping 				= null;
		XmlReaderCastor xmlReaderCastor = null;
		PapiNetEnvelopeDTO pneDTO		= null;		
		IGdsRecptDAO grDAO 				= null;
		String status					= null;
		FileUtils fileUtils				= null;
		
		try {
			B2BLogger.debug("GoodsReceiptProcessorImpl.processUsage() method called");
			mapping = new Mapping();
			mapping.loadMapping(IPixB2BConstants.GR_Mapping_XML);
			xmlReaderCastor = new XmlReaderCastor();
			pneDTO = xmlReaderCastor.readXML(mapping, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorSAN, inTransFactory);
			if(pneDTO != null){
				grDAO = inTransFactory.getGdsRecptDAO() ;
				status = grDAO.processGRDetails(pneDTO, transStatusDTO,errorList);
				if(null!=errorList && errorList.size()==0){
					fileUtils = new FileUtils();
					fileUtils.moveFile(xmlFileNameWithDir, inDirArchiveXmlValid);
				}				
			}else{
				B2BLogger.info("GoodsReceiptProcessorImpl.processGRDetails() - pneDTO = "+pneDTO);
			}
			B2BLogger.debug("GoodsReceiptProcessorImpl.processGRDetails() method return");
		} catch (IOException e) {
			B2BLogger.error("IOException :: ",e);
		} catch (MappingException e) {
			B2BLogger.error("MappingException :: ",e);
		} finally{
			mapping = null;
			xmlReaderCastor = null;
			pneDTO	= null;
			grDAO 	= null;
			fileUtils = null;
		}
		return status;
	}		


	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.IOrderStatusProcessor#validateDataAndRegisterError(java.lang.String, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory, java.lang.String, java.lang.String)
	 */
	public Boolean validateDataAndRegisterError(String xmlFileNameWithDir, ArrayList validXMLList, String transType, String transName, String transactionSchema, String vendorSAN, String inDirArchiveXmlInvalid, InboundTransactionFactory inTransFactory, String documentNumber, String documentDate) {
		
		GdsRecptDTO grDTO 				 		 = null;
		GRHeader xmlGRHdr                		 = null;
		PartyIdentifier shipToParty				 = null;
		ArrayList xmlGRRefList 			 		 = null;
		GRRef xmlGRRef 					 		 = null;			
		String xmlGRTransID						 = null;		
		String xmlSupplierSAN                    = null;
		ArrayList errorDTOList					 = null;
		TransactionStatusDTO transStatusDTO 	 = null;
		
		B2BHelper b2bHelper						= null;
		FileUtils fileUtils						= null;
		TransStatusHelper transStatusHelper		= null;
		String strFileNameWithExtn				= null;
		String strFileNameWithoutExtn 			= null;
		String[] arrStrFileName 				= null;
		String processStatus					= null;
		String ackStatus						= null;		
		String grNo                   			= null;		
		String delMsgNo							= null;
		int errorId								= -1;
		
		ArrayList errorIdList					= null;
		Mapping mapping 						= null;
		XmlReaderCastor xmlReaderCastor 		= null;
		PapiNetEnvelopeDTO pneDTO				= null;		
		IErrorDAO errorDAO 						= null;
		ErrorDTO daoErrorDTO   					= null;
		Boolean dataValidationFlag				= Boolean.TRUE;
		
		try {
			mapping = new Mapping();
			mapping.loadMapping(IPixB2BConstants.GR_Mapping_XML);
			xmlReaderCastor = new XmlReaderCastor();
			pneDTO = xmlReaderCastor.readXML(mapping, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorSAN, inTransFactory);
			
			
			
			if(null!= pneDTO && null!=validXMLList && validXMLList.size()> 0){
				b2bHelper = new B2BHelper();
				errorIdList = new ArrayList();
				grDTO = pneDTO.getPayload().getBusinessDocument().getGrDTO();
				if(null != grDTO){					
					//daoErrorDTO = new ErrorDTO();
					xmlGRHdr = pneDTO.getPayload().getBusinessDocument().getGrDTO().getGrHeader();			
					if(null != xmlGRHdr){						
						grNo = xmlGRHdr.getGrNumber();
						delMsgNo = xmlGRHdr.getDelMsgNo();
					
						shipToParty = new PartyIdentifier();
						shipToParty =  xmlGRHdr.getShipToChar().getShipToParty().getPartyId();
						if(shipToParty != null){
							xmlSupplierSAN = shipToParty.getPartyIdentifierValue();
						}					
						xmlGRRefList = new ArrayList();
						xmlGRRef = new GRRef();
						xmlGRRefList = xmlGRHdr.getGrRef();
						if(xmlGRRefList != null && xmlGRRefList.size() > 0){
							for(int i= 0; i<xmlGRRefList.size(); i++){
								xmlGRRef = (GRRef) xmlGRRefList.get(i);
								if(null!= xmlGRRef  && null!= xmlGRRef.getGrRefType() && IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlGRRef.getGrRefType().trim()))
									xmlGRTransID = xmlGRRef.getGrRefValue();						
							}
						}
					}
				}
					
				if(null==grNo || "".equals(grNo.trim())){
					errorId = IPixB2BConstants.ERROR_ID_100;	
					B2BLogger.info("GR Number is NULL");
					errorIdList.add(errorId);	
					dataValidationFlag = Boolean.FALSE;
				}
				/** Code Commented because query unavailable
				 * else{
					String tableName = "";
					String whereClause = "";
					int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
					if(count<1)
					{
						errorId = ERROR_ID_101;
						B2BLogger.info("Invalid GR Number");
						errorIdList.add(errorId);
						dataValidationFlag = Boolean.FALSE;
					}
				}*/
				
				if(null!=xmlGRTransID || !"".equals(xmlGRTransID.trim())){
					
					/**Query unavailable.....
					errorId = ERROR_ID_102;	
					B2BLogger.info("GR TransId is invalid");
					errorIdList.add(errorId);	
					dataValidationFlag = Boolean.FALSE;					
				}else{*/
					String tableName1 = "pix_xml_trans_mapping pxtm ,pix_xmlread_log pxl";
					String whereClause1 = "pxtm.TRANS_HIST_NO='"+xmlGRTransID+"' and pxtm.process_type='"+IPixB2BConstants.transType_GRE+"' and pxtm.pix_trans_id = pxl.TRANS_ID and replace(pxl.SAN, '-', '')='"+xmlSupplierSAN+"' and pxl.PROCESS_TYPE='"+IPixB2BConstants.transType_GRE+"'";
					int count1 = b2bHelper.checkForRefIntegrity(tableName1, whereClause1);
					if(count1>0)
					{
						errorId = IPixB2BConstants.ERROR_ID_103;
						B2BLogger.info("GR Transaction Id: "+xmlGRTransID+ "is already processed");
						errorIdList.add(errorId);
						dataValidationFlag = Boolean.FALSE;
					}
				}
									
				if(null==delMsgNo || "".equals(delMsgNo.trim())){
						errorId = IPixB2BConstants.ERROR_ID_104;	
						B2BLogger.info("Delivery Message No. is NULL");
						errorIdList.add(errorId);	
						dataValidationFlag = Boolean.FALSE;
					}else{
						String tableName = "PIX_DELIVERY_MSG pixdm";
						String whereClause = "pixdm.MSG_NO='"+delMsgNo+"'";
						int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
						if(count<1)
						{
							errorId = IPixB2BConstants.ERROR_ID_105;
							B2BLogger.info("Invalid Delivery Message Number");
							errorIdList.add(errorId);
							dataValidationFlag = Boolean.FALSE;
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
					
					errorDTOList = b2bHelper.prepareErrorDTOList(errorIdList, validXMLList, inDirArchiveXmlInvalid, transactionId, IPixB2BConstants.GR_ISSUE_DATE, documentDate);
					
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
		IErrorDAO errorDAO 					= null;
		Boolean grErrorFlag					= Boolean.TRUE;
		//String transactionId                = null;
		
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
				
					errorDTOList = b2bHelper.prepareDAOErrorList(errorList, validXMLList, inDirArchiveXmlInvalid, transactionId, IPixB2BConstants.GR_ISSUE_DATE, documentDate);
					
					transStatusDTO = transStatusHelper.setTransactionStatus(processStatus, arrStrFileName[0], arrStrFileName[1],
							transactionId, b2bHelper.getLongTransType(arrStrFileName[2], null), b2bHelper.getTransName(arrStrFileName[2]),  
							strFileNameWithExtn, vendorSAN, inDirArchiveXmlInvalid, fileUtils.getFileSizeKB((String)validXMLList.get(0)), fileUtils.getFileSizeKB((String)validXMLList.get(0)),
							null, IPixB2BConstants.status_RE, IPixB2BConstants.status_FS, IPixB2BConstants.status_AS, IPixB2BConstants.status_MU, ackStatus, documentNumber, documentDate);
					
					validXMLList.remove(0);
				
		
					if(null!=errorDTOList && errorDTOList.size()>0){
						grErrorFlag = Boolean.FALSE;
						errorDAO = inTransFactory.geErrorDAO();
						errorDAO.storeGRXmlErrorDetails(transStatusDTO, errorDTOList, IPixB2BConstants.B2B, IPixB2BConstants.VEN_TO_PIX, IPixB2BConstants.flag_U);
						
					}
				}
			}
		} catch (Exception e) {
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
		} 
		return grErrorFlag;
	}

		


















}