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
 * Title		: 	AdvanceShipmentNoticeProcessorImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		17 May,2010 	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.asn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;

import com.pearson.pixb2b.vendor.service.xml.XmlReaderCastor;
import com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.inbound.asn.dao.IAdvanceShipmentNoticeDAO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.ShipToPartyPartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DelMesBookHd;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DelOriginDate;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DeliveryLeg;
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
 * AdvanceShipmentNoticeProcessorImpl is an implementation class to process the 
 * the ASN Inbound Transaction XML for different vendors.
 * 
 * @author Ashish Agrawal
 */
public class AdvanceShipmentNoticeProcessorImpl implements IAdvanceShipmentNoticeProcessor{

	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.asn.IAdvanceShipmentNoticeProcessor#processAdvanceShipmentNotice(com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO, java.util.ArrayList)
	 */
	public String processAdvanceShipmentNotice(InboundTransactionFactory inTransFactory, String xmlFileNameWithDir, String inDirArchiveXmlValid, String inDirArchiveXmlInvalid, String vendorSAN, TransactionStatusDTO transStatusDTO, ArrayList errorList) {
		Mapping mapping 					= null;
		XmlReaderCastor xmlReaderCastor 	= null;
		PapiNetEnvelopeDTO pneDTO			= null;
		IAdvanceShipmentNoticeDAO asnDAO	= null;
		String status						= null;
		FileUtils fileUtils					= null;
		
		try {
			B2BLogger.debug("AdvanceShipmentNoticeProcessorImpl.processAdvanceShipmentNotice() method called");
			mapping = new Mapping();
			mapping.loadMapping(IPixB2BConstants.DM_Mapping_XML);
			xmlReaderCastor = new XmlReaderCastor();
			pneDTO = xmlReaderCastor.readXML(mapping, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorSAN, inTransFactory);
			if(pneDTO != null){
				asnDAO = inTransFactory.getAdvanceShipmentNoticeDAO();
				status = asnDAO.storeAdvanceShipmentNoticeDetails(pneDTO, transStatusDTO, errorList, inDirArchiveXmlInvalid);
				fileUtils = new FileUtils();
				if(null!=errorList && errorList.size()>0){
					fileUtils.moveFile(xmlFileNameWithDir, inDirArchiveXmlInvalid);
				}else{
					fileUtils.moveFile(xmlFileNameWithDir, inDirArchiveXmlValid);
				}
			}else{
				B2BLogger.info("AdvanceShipmentNoticeProcessorImpl.processAdvanceShipmentNotice() - pneDTO = "+pneDTO);
			}
			B2BLogger.debug("AdvanceShipmentNoticeProcessorImpl.processAdvanceShipmentNotice() method return");
		} catch (IOException e) {
			B2BLogger.error("IOException :: ",e);
		} catch (MappingException e) {
			B2BLogger.error("MappingException :: ",e);
		} finally{
			mapping 		= null;
			xmlReaderCastor = null;
			pneDTO			= null;
			asnDAO 			= null;
			fileUtils 		= null;
		}
		return status;
	}
	
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
		DelMesBookHd xmlASNBookHd   		= null;
		ArrayList shipPartyIdList			= null;
		ShipToPartyPartyIdentifier shipPartyId 	= null;
		String shipSAN						= null;
		ArrayList deliveryLegList			= null;
		DeliveryLeg deliveryLeg				= null;
		DelOriginDate delOriginDate 		= null;
		
		try {
			B2BLogger.debug("******* AdvanceShipmentNoticeProcessorImpl.validateDataAndRegisterError() method ENTERED *******");
			
			mapping = new Mapping();
			mapping.loadMapping(IPixB2BConstants.DM_Mapping_XML);
			xmlReaderCastor = new XmlReaderCastor();
			pneDTO = xmlReaderCastor.readXML(mapping, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorSAN, inTransFactory);
			
			if(null!= pneDTO && null!=validXMLList && validXMLList.size()>0){
				errorIdList = new ArrayList();
				b2bHelper = new B2BHelper();
				xmlSupplierSAN = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.VENDOR_SAN, IPixB2BConstants.transType_DMB);
				if(null!=xmlSupplierSAN && !"".equals(xmlSupplierSAN)){
					xmlSupplierSAN = b2bHelper.addDashesInSAN(xmlSupplierSAN);
				}
				xmlDMTransID = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.XML_TRANS_ID, IPixB2BConstants.transType_DMB);
				
				if(null==xmlDMTransID || "".equals(xmlDMTransID.trim())){
					partialErrorDTO = new ErrorDTO();
					partialErrorDTO.setErrorID(IPixB2BConstants.ERROR_ID_128+"");
					errorIdList.add(partialErrorDTO);
					dataValidationFlag = Boolean.FALSE;
					B2BLogger.info("AdvanceShipmentNoticeProcessorImpl.validateDataAndRegisterError() - TRANSACTION ID IS NULL");
				}else{
					String tableName1 = "pix_xml_trans_mapping pxtm ,pix_xmlread_log pxl";
					String whereClause1 = "pxtm.TRANS_HIST_NO='"+xmlDMTransID+"' and pxtm.process_type='"+IPixB2BConstants.transType_DMB+"' and pxtm.pix_trans_id = pxl.TRANS_ID and pxl.SAN='"+xmlSupplierSAN+"' and pxl.PROCESS_TYPE='"+IPixB2BConstants.transType_DMB+"'";
					int count1 = b2bHelper.checkForRefIntegrity(tableName1, whereClause1);
					if(count1>0)
					{
						partialErrorDTO = new ErrorDTO();
						partialErrorDTO.setErrorID(IPixB2BConstants.ERROR_ID_1+"");
						partialErrorDTO.setErrorDescription(xmlDMTransID);
						B2BLogger.info("AdvanceShipmentNoticeProcessorImpl.validateDataAndRegisterError() - The Transaction Id: "+xmlDMTransID+ "is already processed and stored in PIX_XML_TRANS_MAPPING table");
						errorIdList.add(partialErrorDTO);
						dataValidationFlag = Boolean.FALSE;
					}else{
						xmlASNBookHd = pneDTO.getPayload().getBusinessDocument().getDmDTO().getDelMesBookHd();
						shipPartyIdList = xmlASNBookHd.getShipToCharacteristics().getShipToParty().getPartyIdentifierList();
						String sipToParType = xmlASNBookHd.getShipToCharacteristics().getShipToParty().getPartyType();
						if(null!= sipToParType && !sipToParType.equals("") && sipToParType.equals(IPixB2BConstants.Warehouse)){
						if(null!=shipPartyIdList && shipPartyIdList.size()>0){
							for(int cnt5=0; cnt5<shipPartyIdList.size(); cnt5++){
							shipPartyId = (ShipToPartyPartyIdentifier)shipPartyIdList.get(cnt5);
								if(null!=shipPartyId && "AssignedByBuyer".equalsIgnoreCase(shipPartyId.getPartyIdentifierType())){
									shipSAN = shipPartyId.getPartyIdentifierValue();
									Pattern patt = Pattern.compile("[a-zA-Z]{3}");
									Matcher match = patt.matcher(shipSAN);
									Boolean result = match.find();
									/*if(!result){
										partialErrorDTO = new ErrorDTO();
										partialErrorDTO.setErrorID(IPixB2BConstants.ERROR_ID_124+"");
										partialErrorDTO.setErrorDescription(shipSAN);
										errorIdList.add(partialErrorDTO);
										dataValidationFlag = Boolean.FALSE;
										B2BLogger.info("AdvanceShipmentNoticeProcessorImpl.validateDataAndRegisterError() - PARTY IDENTIFIER FOR SHIPTO PARTY IS INVALID: "+shipSAN);
									}*/
								}else{
									partialErrorDTO = new ErrorDTO();
									partialErrorDTO.setErrorID(IPixB2BConstants.ERROR_ID_123+"");
									errorIdList.add(partialErrorDTO);
									dataValidationFlag = Boolean.FALSE;
									B2BLogger.info("AdvanceShipmentNoticeProcessorImpl.validateDataAndRegisterError() - PARTY IDENTIFIER FOR SHIPTO PARTY IS NULL");
								}
							}
						}else{
							partialErrorDTO = new ErrorDTO();
							partialErrorDTO.setErrorID(IPixB2BConstants.ERROR_ID_123+"");
							errorIdList.add(partialErrorDTO);
							dataValidationFlag = Boolean.FALSE;
							B2BLogger.info("AdvanceShipmentNoticeProcessorImpl.validateDataAndRegisterError() - PARTY IDENTIFIER FOR SHIPTO PARTY IS NULL");
						}
					}
						deliveryLegList = xmlASNBookHd.getDeliveryLegList();
						if(null!=deliveryLegList && deliveryLegList.size()>0){
							for(int cnt7=0; cnt7<deliveryLegList.size(); cnt7++){
								delOriginDate = null;
								deliveryLeg = (DeliveryLeg)deliveryLegList.get(cnt7);
								delOriginDate = deliveryLeg.getDelOrigin().getDelOriginDate();
								if(null==delOriginDate){
									partialErrorDTO = new ErrorDTO();
									partialErrorDTO.setErrorID(IPixB2BConstants.ERROR_ID_122+"");
									errorIdList.add(partialErrorDTO);
									dataValidationFlag = Boolean.FALSE;
									B2BLogger.info("AdvanceShipmentNoticeProcessorImpl.validateDataAndRegisterError() - SHIP DATE IS NULL");
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
					
					errorDTOList = b2bHelper.prepareErrorDTOListEx(errorIdList, validXMLList, inDirArchiveXmlInvalid, transactionId, IPixB2BConstants.DM_ISSUE_DATE, documentDate);
					
					transStatusDTO = transStatusHelper.setTransactionStatus(processStatus, arrStrFileName[0], arrStrFileName[1],
							transactionId, b2bHelper.getLongTransType(arrStrFileName[2], null), b2bHelper.getTransName(arrStrFileName[2]),  
							strFileNameWithExtn, vendorSAN, inDirArchiveXmlInvalid, fileUtils.getFileSizeKB((String)validXMLList.get(0)), fileUtils.getFileSizeKB((String)validXMLList.get(0)),
							null, IPixB2BConstants.status_RE, IPixB2BConstants.status_FS, IPixB2BConstants.status_AS, IPixB2BConstants.status_MU, ackStatus, documentNumber, documentDate);
				}
				if(null!=errorDTOList && errorDTOList.size()>0){
					errorDAO = inTransFactory.geErrorDAO();
					errorDAO.storeInboundXmlErrorDetails(transStatusDTO, errorDTOList, IPixB2BConstants.B2B, IPixB2BConstants.VEN_TO_PIX, IPixB2BConstants.flag_U, pneDTO);
				}
			} //end if - validXmlList
			else{
				dataValidationFlag = Boolean.FALSE; 
				B2BLogger.info("pneDTO is null");
			}
			
			B2BLogger.debug("******* AdvanceShipmentNoticeProcessorImpl.validateDataAndRegisterError() method EXIT *******");
		} catch (IOException e) {
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
		} catch (MappingException e) {
			B2BLogger.error("MappingException :: " + StringUtils.getStackTrace(e));
		}
		return dataValidationFlag;
	}
	
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
		IErrorDAO errorDAO 					= null;
		Boolean asnErrorFlag				= Boolean.TRUE;
		
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
					errorDTOList = b2bHelper.prepareDAOErrorList(errorList, validXMLList, inDirArchiveXmlInvalid, transactionId, IPixB2BConstants.ASN_ISSUE_DATE, documentDate);
					
					transStatusDTO = transStatusHelper.setTransactionStatus(processStatus, arrStrFileName[0], arrStrFileName[1],
							transactionId, b2bHelper.getLongTransType(arrStrFileName[2], null), b2bHelper.getTransName(arrStrFileName[2]),  
							strFileNameWithExtn, vendorSAN, inDirArchiveXmlInvalid, fileUtils.getFileSizeKB((String)validXMLList.get(0)), fileUtils.getFileSizeKB((String)validXMLList.get(0)),
							null, IPixB2BConstants.status_RE, IPixB2BConstants.status_FS, IPixB2BConstants.status_AS, IPixB2BConstants.status_MU, ackStatus, documentNumber, documentDate);
						
					validXMLList.remove(0);
					
					if(null!=errorDTOList && errorDTOList.size()>0){
						asnErrorFlag = Boolean.FALSE;
						errorDAO = inTransFactory.geErrorDAO();
						errorDAO.storeDAOErrorDetails(transStatusDTO, errorDTOList, IPixB2BConstants.B2B, IPixB2BConstants.VEN_TO_PIX, IPixB2BConstants.flag_U, pneDTO);
					}
				}
			}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
		}
		return asnErrorFlag;
	}
}
