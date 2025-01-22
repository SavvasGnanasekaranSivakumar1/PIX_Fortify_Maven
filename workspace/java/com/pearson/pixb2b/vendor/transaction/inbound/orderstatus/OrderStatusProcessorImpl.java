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
 * Title		: 	OrderStatusProcessorImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	12 Oct,2009 	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderstatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;

import com.pearson.pixb2b.vendor.service.xml.XmlReaderCastor;
import com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dao.IOrderStatusDAO;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.OrderStatusDetail;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.OrderStatusInformation;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.ProductIdentifier;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.PurchaseOrderInformation;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.PurchaseOrderReference;
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
 * OrderStatusProcessorImpl is an implementation class to process the 
 * the OrderStatus Inbound Transaction XML for different vendors.
 * 
 * @author Yogesh Tyagi
 */
public class OrderStatusProcessorImpl implements IOrderStatusProcessor{
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.IOrderStatusProcessor#processOrderStatus(com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO)
	 */
	public String processOrderStatus(InboundTransactionFactory inTransFactory, String xmlFileNameWithDir, String inDirArchiveXmlValid, String inDirArchiveXmlInvalid, String vendorSAN, TransactionStatusDTO transStatusDTO) {
		Mapping mapping 			= null;
		XmlReaderCastor xmlReaderCastor = null;
		PapiNetEnvelopeDTO pneDTO	= null;		
		IOrderStatusDAO osDAO 		= null;
		String status				= null;
		FileUtils fileUtils			= null;
		
		try {
			B2BLogger.debug("OrderStatusProcessorImpl.processOrderStatus() method called");
			mapping = new Mapping();
			mapping.loadMapping(IPixB2BConstants.OS_Mapping_XML);
			xmlReaderCastor = new XmlReaderCastor();
			pneDTO = xmlReaderCastor.readXML(mapping, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorSAN, inTransFactory);
			if(pneDTO != null){
				osDAO = inTransFactory.getOrderStatusDAO();
				status = osDAO.storeOrderStatusDetails(pneDTO, transStatusDTO);
				fileUtils = new FileUtils();
				fileUtils.moveFile(xmlFileNameWithDir, inDirArchiveXmlValid);
			}else{
				B2BLogger.info("OrderStatusProcessorImpl.processOrderStatus() - pneDTO = "+pneDTO);
			}
			B2BLogger.debug("OrderStatusProcessorImpl.processOrderStatus() method return");
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
			osDAO 	= null;
			fileUtils = null;
		}
		return status;
	}

	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.IOrderStatusProcessor#validateDataAndRegisterError(java.lang.String, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory, java.lang.String, java.lang.String)
	 */
	public Boolean validateDataAndRegisterError(String xmlFileNameWithDir, ArrayList validXMLList, String transType, String transName, String transactionSchema, String vendorSAN, String inDirArchiveXmlInvalid, InboundTransactionFactory inTransFactory, String documentNumber, String documentDate) {
		ArrayList xmlOSDetailList			= null;
		OrderStatusDetail xmlOSDetail		= null;
		PurchaseOrderInformation xmlPOInfo 	= null;
		String xmlPONumber	  				= null;
		ArrayList xmlPORefList				= null;
		PurchaseOrderReference xmlPORef		= null;
		String xmlPOTransId					= null;
		String poLineItemNumber				= null;
		ArrayList productIdentifierList		= null;
		ProductIdentifier productIdentifier	= null;
		String productId					= null;
		OrderStatusInformation osInformation= null;
		String orderStatusCode				= null;
		String xmlOSTransId					= null;
		
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
		int cnt								= -1;
		int cnt1							= -1;
		int cnt2							= -1;
		int cnt3							= -1;
		int counter							= -1;
		try {
			mapping = new Mapping();
			mapping.loadMapping(IPixB2BConstants.OS_Mapping_XML);
			xmlReaderCastor = new XmlReaderCastor();
			pneDTO = xmlReaderCastor.readXML(mapping, xmlFileNameWithDir, inDirArchiveXmlInvalid, vendorSAN, inTransFactory);
			
			errorIdList = new ArrayList();
			b2bHelper = new B2BHelper();
			if(null!= pneDTO && null!=validXMLList && validXMLList.size()>0){
				xmlSupplierSAN = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.VENDOR_SAN, IPixB2BConstants.transType_OST);
				if(null!=xmlSupplierSAN && !"".equals(xmlSupplierSAN.trim())){
					//xmlSupplierSAN = xmlSupplierSAN.replaceAll("-","");
					xmlSupplierSAN = b2bHelper.addDashesInSAN(xmlSupplierSAN);
				}
				String tableNam6 = "pix_san_trans_mapping";
				String whereClaus6 = "SAN='"+xmlSupplierSAN+"' and process_id=7 and ACTIVE='Y' and IS_SAN_SPECIFIC ='Y'" ;
				counter = b2bHelper.checkForRefIntegrity(tableNam6, whereClaus6);
				
				xmlOSTransId = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.XML_TRANS_ID, IPixB2BConstants.transType_OST);
				if(null!=xmlOSTransId && !"".equals(xmlOSTransId)){
					if(counter>0){
						String tableNam2 = "pix_xml_trans_mapping pxtm ,pix_xmlread_log pxl";
						String whereClaus2 = "pxtm.TRANS_HIST_NO='"+xmlOSTransId+"' and pxtm.process_type='"+IPixB2BConstants.transType_OST+"' and pxtm.pix_trans_id = pxl.TRANS_ID and pxl.SAN in (SELECT SAN FROM PIX_SAN_TRANS_MAPPING WHERE ACTIVE='Y' and IS_SAN_SPECIFIC ='Y' and PROCESS_ID=7)"+" and pxl.PROCESS_TYPE='"+IPixB2BConstants.transType_OST+"'";
						cnt2 = b2bHelper.checkForRefIntegrity(tableNam2, whereClaus2);
						if(cnt2>0){
							errorId = IPixB2BConstants.ERROR_ID_1;
							B2BLogger.info("The Transaction Id: "+xmlOSTransId+ "is already processed and stored in PIX_XML_TRANS_MAPPING table");
							errorIdList.add(errorId);
							dataValidationFlag = Boolean.FALSE;
						}
					}
					else
					{
					String tableNam = "pix_xml_trans_mapping pxtm ,pix_xmlread_log pxl";
					String whereClaus = "pxtm.TRANS_HIST_NO='"+xmlOSTransId+"' and pxtm.process_type='"+IPixB2BConstants.transType_OST+"' and pxtm.pix_trans_id = pxl.TRANS_ID and pxl.SAN='"+xmlSupplierSAN+"' and pxl.PROCESS_TYPE='"+IPixB2BConstants.transType_OST+"'";
					cnt = b2bHelper.checkForRefIntegrity(tableNam, whereClaus);
					if(cnt>0){
						errorId = IPixB2BConstants.ERROR_ID_1;
						B2BLogger.info("The Transaction Id: "+xmlOSTransId+ "is already processed and stored in PIX_XML_TRANS_MAPPING table");
						errorIdList.add(errorId);
						dataValidationFlag = Boolean.FALSE;
					}
					}
				}else{
					if(counter>0){
						String tableNam4 = "pix_xml_trans_mapping pxtm ,pix_xmlread_log pxl";
						String whereClaus4 = "pxtm.vendor_trans_id='"+documentNumber+"' and pxtm.process_type='"+IPixB2BConstants.transType_OST+"' and pxtm.pix_trans_id = pxl.TRANS_ID and pxl.SAN in (SELECT SAN FROM PIX_SAN_TRANS_MAPPING WHERE ACTIVE='Y' and IS_SAN_SPECIFIC ='Y' and PROCESS_ID=7)"+" and pxl.PROCESS_TYPE='"+IPixB2BConstants.transType_OST+"' and pxl.XML_READ_FLAG='RS'";
						cnt2 = b2bHelper.checkForRefIntegrity(tableNam4, whereClaus4);
						if(cnt2>0){
							errorId = IPixB2BConstants.ERROR_ID_1;
							B2BLogger.info("The Vendor Trans id: "+documentNumber+ "is already present in PIX_XML_TRANS_MAPPING table");
							errorIdList.add(errorId);
							dataValidationFlag = Boolean.FALSE;
						}
					}
					else
					{
					String tableName1 = "pix_xml_trans_mapping pxtm ,pix_xmlread_log pxl";
					String whereClause1 = "pxtm.vendor_trans_id='"+documentNumber+"' and pxtm.process_type='"+IPixB2BConstants.transType_OST+"' and pxtm.pix_trans_id = pxl.TRANS_ID and pxl.SAN='"+xmlSupplierSAN+"' and pxl.PROCESS_TYPE='"+IPixB2BConstants.transType_OST+"' and pxl.XML_READ_FLAG='RS'";
					cnt = b2bHelper.checkForRefIntegrity(tableName1, whereClause1);
					if(cnt>0){
						errorId = IPixB2BConstants.ERROR_ID_1;
						B2BLogger.info("The Vendor Trans id: "+documentNumber+ "is already present in PIX_XML_TRANS_MAPPING table");
						errorIdList.add(errorId);
						dataValidationFlag = Boolean.FALSE;
					}
				}
				}
				
				if(cnt==0 || cnt2==0){
					//else{
					xmlOSDetailList = pneDTO.getPayload().getBusinessDocument().getOsDTO().getOsDetailList();			
					if(null!=xmlOSDetailList && xmlOSDetailList.size() > 0){

						for(int i= 0; i<xmlOSDetailList.size(); i++){
							xmlOSDetail = (OrderStatusDetail) xmlOSDetailList.get(i);
							if(null!=xmlOSDetail){
								poLineItemNumber = xmlOSDetail.getPoLineItemNumber();
								productIdentifierList = xmlOSDetail.getProduct().getProductIdentifierList();
								if(null!=productIdentifierList && productIdentifierList.size() > 0){
									for(int j = 0; j<productIdentifierList.size(); j++){
										productIdentifier = (ProductIdentifier)productIdentifierList.get(j);
										if(null!=productIdentifier){
											productId = productIdentifier.getProductIdentifierVal();
										}
									}
								}
								osInformation = xmlOSDetail.getOsInformation();
								if(null!=osInformation){
									orderStatusCode = osInformation.getOrderPrimaryStatus().getOrderStatusCode();	
								}
								xmlPOInfo = xmlOSDetail.getPoInformation();
								if(null!=xmlPOInfo){
									xmlPONumber = xmlPOInfo.getPoNumber();
									xmlPORefList = xmlPOInfo.getPoReferenceList();
									if(xmlPORefList != null && xmlPORefList.size() > 0){
										for(int k = 0; k<xmlPORefList.size(); k++){
											xmlPORef = (PurchaseOrderReference) xmlPORefList.get(k);
											if(null!=xmlPORef && xmlPORef.getPoReferenceType() != null && IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlPORef.getPoReferenceType().trim()))
												xmlPOTransId = xmlPORef.getPoReferenceValue();
										}
									}
								}
							}
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
//							if(null==poLineItemNumber || "".equals(poLineItemNumber.trim())){
//								errorId = ERROR_ID_78;
//								errorIdList.add(errorId);
//								dataValidationFlag = Boolean.FALSE;
//							}
							if(null!=dbPOId && !"".equals(dbPOId.trim()) && null!=dbPOVersion && !"".equals(dbPOVersion.trim())){
								String tableName = "PIX_PO_LINE";
								String whereClause = "PO_ID='"+dbPOId+"' and PO_VERSION='"+dbPOVersion+"' and PO_LINE_NO="+"'"+poLineItemNumber+"'";
								int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
								if(count<=0){
									errorId = IPixB2BConstants.ERROR_ID_79;
									B2BLogger.info("The PO Line Item Number: "+poLineItemNumber+ "is not present in PIX_PO_LINE table");
									errorIdList.add(errorId);
									dataValidationFlag = Boolean.FALSE;
								}
							}
							if(null!=dbPOId && !"".equals(dbPOId.trim()) && null!=dbPOVersion && !"".equals(dbPOVersion.trim())){
								if(counter>0){
									String tableName = "PIX_PO_HEADER pph, PIX_BOOK_SPEC_LINE pbsl, PIX_PO_LIST_SUMMARY ppls";
									String whereClause ="pph.spec_id= pbsl.spec_id and pph.spec_version = pbsl.spec_version"
									+" and pph.PO_ID='"+dbPOId+"' and pph.PO_VERSION='"+dbPOVersion+"' and pph.po_id=ppls.po_id"
									+" and ppls.SUPPLIER_SAN in (SELECT SAN FROM PIX_SAN_TRANS_MAPPING WHERE ACTIVE='Y' and IS_SAN_SPECIFIC ='Y' and PROCESS_ID=7)"+" and pbsl.product_code='"+productId+"'";
									int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
									if(count<=0){
										errorId = IPixB2BConstants.ERROR_ID_82;
										B2BLogger.info("This OrderStatus XML does not contain valid Product Code information");
										errorIdList.add(errorId);
										dataValidationFlag = Boolean.FALSE;
									}	
								}
								else
								{
								String tableName = "PIX_PO_HEADER pph, PIX_BOOK_SPEC_LINE pbsl, PIX_PO_LIST_SUMMARY ppls";
								String whereClause ="pph.spec_id= pbsl.spec_id and pph.spec_version = pbsl.spec_version"
								+" and pph.PO_ID='"+dbPOId+"' and pph.PO_VERSION='"+dbPOVersion+"' and pph.po_id=ppls.po_id"
								+" and ppls.SUPPLIER_SAN='"+xmlSupplierSAN+"' and pbsl.finished_good_flag='Y' and pbsl.product_code='"+productId+"'";
								int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
								if(count<=0){
									errorId = IPixB2BConstants.ERROR_ID_82;
									B2BLogger.info("This OrderStatus XML does not contain FINISHED GOOD line item Status");
									errorIdList.add(errorId);
									dataValidationFlag = Boolean.FALSE;
								}
								}

							}
							if(null==orderStatusCode || "".equals(orderStatusCode.trim())){
								errorId = IPixB2BConstants.ERROR_ID_81;
								errorIdList.add(errorId);
								dataValidationFlag = Boolean.FALSE;
							}
//							else{
//								String tableName = "PIX_STATUS_CODE";
//								String whereClause = "TABLE_ID='"+table_id_50+"' and STATUS_DESCRIPTION='"+orderStatusCode+"'";
//								int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
//								if(count<=0){
//									errorId = ERROR_ID_80;
//									B2BLogger.info("The Order Status Code: "+orderStatusCode+ "is not present in PIX_STATUS_CODE table");
//									errorIdList.add(errorId);
//									dataValidationFlag = Boolean.FALSE;
//								}
//							}
						}
					}
//					if(null!=dbPOId && !"".equals(dbPOId.trim())){
//						String tableName = "PIX_XML_TRANS_MAPPING";
//						String whereClause = "vendor_trans_id='"+documentNumber+"' AND process_type='"+transType_OST
//						+"' AND pix_trans_id in (select trans_id from pix_xmlread_log where TRANSREF_LABEL_1='PO_ID'"
//						+" and TRANSREF_ID_1='"+dbPOId+"' AND process_type='"+transType_OST+"')";
	//
//						int count = b2bHelper.checkForRefIntegrity(tableName, whereClause);
//						if(count>0){
//							errorId = ERROR_ID_1;
//							B2BLogger.info("The Vendor Trans id: "+documentNumber+ "is already present in PIX_XML_TRANS_MAPPING table");
//							errorIdList.add(errorId);
//							dataValidationFlag = Boolean.FALSE;
//						}
//					}
//				}
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
					
					errorDTOList = b2bHelper.prepareErrorDTOList(errorIdList, validXMLList, inDirArchiveXmlInvalid, transactionId, IPixB2BConstants.OS_ISSUE_DATE, documentDate);
					
					transStatusDTO = transStatusHelper.setTransactionStatus(processStatus, arrStrFileName[0], arrStrFileName[1],
							transactionId, b2bHelper.getLongTransType(arrStrFileName[2], null), b2bHelper.getTransName(arrStrFileName[2]),  
							strFileNameWithExtn, vendorSAN, inDirArchiveXmlInvalid, fileUtils.getFileSizeKB((String)validXMLList.get(0)), fileUtils.getFileSizeKB((String)validXMLList.get(0)),
							null, IPixB2BConstants.status_RE, IPixB2BConstants.status_FS, IPixB2BConstants.status_AS, IPixB2BConstants.status_MU, ackStatus, documentNumber, documentDate);
					
					//validXMLList.remove(0);   //moved to InboundVendorThread
				}
				
				if(null!=errorDTOList && errorDTOList.size()>0){
					errorDAO = inTransFactory.geErrorDAO();
					errorDAO.storeInboundXmlErrorDetails(transStatusDTO, errorDTOList, IPixB2BConstants.B2B, IPixB2BConstants.VEN_TO_PIX, IPixB2BConstants.flag_U, pneDTO);
				}
			}else{
				dataValidationFlag = Boolean.FALSE; 
				B2BLogger.info("pneDTO is null");  //validxmlList will never be null
			}
		} catch (IOException e) {
			B2BLogger.error("IOException :: " + StringUtils.getStackTrace(e));
		} catch (MappingException e) {
			B2BLogger.error("MappingException :: " + StringUtils.getStackTrace(e));
		}
		return dataValidationFlag;
	}
}