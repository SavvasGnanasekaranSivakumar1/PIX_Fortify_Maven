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
 * Title		: 	B2BHelper.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi 	14 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.helper;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.*;

import oracle.jdbc.OracleTypes;
import sun.misc.Regexp;
import sun.misc.RegexpTarget;

import com.pearson.pixb2b.vendor.service.report.ReportWriter;
import com.pearson.pixb2b.vendor.service.statusemail.EmailContents;
import com.pearson.pixb2b.vendor.service.xml.XmlValidator;
import com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.GRHeader;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.GRRef;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.PartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.ICHeader;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.ICReference;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.LPPartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto.ISHeader;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto.ISReference;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto.LocationParty;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto.LocationPartyPartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.InvoiceHeader;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.InvoiceRef;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.SupplierParty;
import com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto.OCHeader;
import com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto.OCReference;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.OSHeader;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.OSReference;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.OrderStatusDetail;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.EndPartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.UsageHeader;
import com.pearson.pixb2b.vendor.transaction.inbound.usage.dto.UsageReference;
import com.pearson.pixb2b.vendor.transaction.shared.dto.SupplierPartyPartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DelMesBookHd;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.DelMesRef;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage.SupplierPartyId;
import com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessagewood.DelMesWoodHd;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.TransmissionInfo;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.TransmissionReceiver;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.TransmissionReceiverPI;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.TransmissionSender;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.TransmissionSenderPI;
import com.pearson.pixb2b.vendor.transaction.shared.dto.error.ErrorDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.error.ErrorLogDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.transaction.shared.error.dao.IErrorDAO;
import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.DBUtils;
import com.pearson.pixb2b.vendor.utils.EmailSender;
import com.pearson.pixb2b.vendor.utils.FileUtils;
import com.pearson.pixb2b.vendor.utils.IConfigConstants;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * B2BHelper is a helper class to help other classes to complete 
 * the required operations.
 * 
 * @author Yogesh Tyagi
 */
public class B2BHelper{
	private static final String qry_sel_pix_trans_id_nextval = "SELECT SEQ_PIX_TRANS_LOG.NEXTVAL trans_id_next FROM DUAL";
	
	private static final String qry_sel_error_desp = "SELECT * FROM PIX_ERROR_Code WHERE ERROR_ID= ?";
	
	private static final String qry_sel_poID_poVersion = "SELECT TRANSREF_ID_1 poID, TRANSREF_ID_2 poVersion"
		+" FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_XMLGEN_LOG"
		+" WHERE trans_type = ?"
		+" AND process_type = ?"
		+" AND TRANS_ID = ?";
	
	private static final String qry_sel_POID_POVersion = "SELECT po_id, po_version"
		+" FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_po_list_summary"
		+" WHERE po_no = ?";
	
	private static final String qry_sel_pix_poid = "SELECT po_id"
		+ " FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_po_summary " 
		+ " WHERE po_no = ? "; 	
	
	public static final String proc_sel_inbound_vendor_transInfo = "{call "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_B2B_VEN_TRANS_DETAIL_PROC(?,?,?,?)}";
	
	private static final String qry_sel_po_error = "SELECT (SELECT PO_NO FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_PO_SUMMARY WHERE PO_ID= pel.tranref_id_1)PO_NO, DEST_NAME, ADDITIONAL_DESC"
		+" FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ERROR_LOG pel"
		+" WHERE (SOURCE_TYPE='POR' or SOURCE_TYPE='SIP') and EMAIL_FLAG='N'";
		
	private static final String qry_upd_errorlog_emailflag = "UPDATE "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ERROR_LOG pel"
		+" set EMAIL_FLAG='Y' WHERE (SOURCE_TYPE='POR' or SOURCE_TYPE='SIP') and EMAIL_FLAG='N'";
	/**
	 * This method returns Transaction Name based on the Transaction Type.
	 * @param transmissionName
	 * @return String
	 */
	public String getTransName(String transType){
		String transName = null;
		
		if(IPixB2BConstants.transType_PO.equals(transType) || IPixB2BConstants.transType_POR.equals(transType))
			transName = IPixB2BConstants.PurchaseOrder;
		else if(IPixB2BConstants.transType_SI.equals(transType) || IPixB2BConstants.transType_SIP.equals(transType))
			transName = IPixB2BConstants.ShippingInstructions;
		else if(IPixB2BConstants.transType_BS.equals(transType) || IPixB2BConstants.transType_BSP.equals(transType))
			transName = IPixB2BConstants.BookSpecification;
		else if(IPixB2BConstants.transType_DM.equals(transType) || IPixB2BConstants.transType_DME.equals(transType))
			transName = IPixB2BConstants.DeliveryMessage;
		else if(IPixB2BConstants.transType_OS.equals(transType) || IPixB2BConstants.transType_OST.equals(transType))
			transName = IPixB2BConstants.OrderStatus;
		else if(IPixB2BConstants.transType_OC.equals(transType) || IPixB2BConstants.transType_OCO.equals(transType))
			transName = IPixB2BConstants.OrderConfirmation;
		else if(IPixB2BConstants.transType_IC.equals(transType) || IPixB2BConstants.transType_INC.equals(transType))
			transName = IPixB2BConstants.InventoryChange;
		else if(IPixB2BConstants.transType_IS.equals(transType) || IPixB2BConstants.transType_INV.equals(transType))
			transName = IPixB2BConstants.InventoryStatus;
		else if(IPixB2BConstants.transType_UN.equals(transType) || IPixB2BConstants.transType_USG.equals(transType))
			transName = IPixB2BConstants.Usage;
		else if(IPixB2BConstants.transType_GR.equals(transType) || IPixB2BConstants.transType_GRE.equals(transType))
			transName = IPixB2BConstants.GoodsReceipt;
		else if(IPixB2BConstants.transType_DMB.equals(transType) || IPixB2BConstants.transType_DMB.equals(transType))
			transName = IPixB2BConstants.ShipmentStatus;
		else if(IPixB2BConstants.transType_BA.equals(transType) || IPixB2BConstants.transType_ACK.equals(transType))
			transName = IPixB2BConstants.BusinessAcknowledgement;
		else if(IPixB2BConstants.transType_INVO.equals(transType) || IPixB2BConstants.transType_INVO.equals(transType))
			transName = IPixB2BConstants.Invoice;
		return transName;
	}
	/**
	 * This method returns Short Transaction Type based on the Long Transaction Type or Transaction Name.
	 * @param longTransType
	 * @param transName
	 * @return String
	 */
	public String getShortTransType(String longTransType, String transName){
		String transType = null;
		
		if(IPixB2BConstants.transType_POR.equals(longTransType) || IPixB2BConstants.PurchaseOrder.equals(transName))
			transType = IPixB2BConstants.transType_PO;
		else if(IPixB2BConstants.transType_SIP.equals(longTransType) || IPixB2BConstants.ShippingInstructions.equals(transName))
			transType = IPixB2BConstants.transType_SI;
		else if(IPixB2BConstants.transType_BSP.equals(longTransType) || IPixB2BConstants.BookSpecification.equals(transName))
			transType = IPixB2BConstants.transType_BS;
		else if(IPixB2BConstants.transType_DME.equals(longTransType) || IPixB2BConstants.DeliveryMessage.equals(transName) 
				|| IPixB2BConstants.DeliveryMessageWood.equals(transName))
			transType = IPixB2BConstants.transType_DM;
		else if(IPixB2BConstants.transType_OST.equals(longTransType) || IPixB2BConstants.OrderStatus.equals(transName))
			transType = IPixB2BConstants.transType_OS;
		else if(IPixB2BConstants.transType_OCO.equals(longTransType) || IPixB2BConstants.OrderConfirmation.equals(transName))
			transType = IPixB2BConstants.transType_OC;
		else if(IPixB2BConstants.transType_INC.equals(longTransType) || IPixB2BConstants.InventoryChange.equals(transName))
			transType = IPixB2BConstants.transType_IC;
		else if(IPixB2BConstants.transType_INV.equals(longTransType) || IPixB2BConstants.InventoryStatus.equals(transName))
			transType = IPixB2BConstants.transType_IS;
		else if(IPixB2BConstants.transType_USG.equals(longTransType) || IPixB2BConstants.Usage.equals(transName))
			transType = IPixB2BConstants.transType_UN;
		else if(IPixB2BConstants.transType_GRE.equals(longTransType) || IPixB2BConstants.GoodsReceipt.equals(transName))
			transType = IPixB2BConstants.transType_GR;
		else if(IPixB2BConstants.transType_DMB.equals(longTransType) || IPixB2BConstants.ShipmentStatus.equals(transName))
			transType = IPixB2BConstants.transType_DMB;
		else if(IPixB2BConstants.transType_ACK.equals(longTransType) || IPixB2BConstants.BusinessAcknowledgement.equals(transName))
			transType = IPixB2BConstants.transType_BA;
		else if(IPixB2BConstants.transType_INVO.equals(longTransType) || IPixB2BConstants.Invoice.equals(transName))
			transType = IPixB2BConstants.transType_INVO;
		return transType;
	}
	/**
	 * This method returns Long Transaction Type based on the Short Transaction Type or Transaction Name.
	 * @param shortTransType
	 * @param transName
	 * @return String
	 */
	public String getLongTransType(String shortTransType, String transName){
		String transType = null;
		
		if(IPixB2BConstants.transType_PO.equals(shortTransType) || IPixB2BConstants.PurchaseOrder.equals(transName))
			transType = IPixB2BConstants.transType_POR;
		else if(IPixB2BConstants.transType_SI.equals(shortTransType) || IPixB2BConstants.ShippingInstructions.equals(transName))
			transType = IPixB2BConstants.transType_SIP;
		else if(IPixB2BConstants.transType_BS.equals(shortTransType) || IPixB2BConstants.BookSpecification.equals(transName))
			transType = IPixB2BConstants.transType_BSP;
		else if(IPixB2BConstants.transType_DM.equals(shortTransType) || IPixB2BConstants.DeliveryMessage.equals(transName)
				|| IPixB2BConstants.DeliveryMessageWood.equals(transName))
			transType = IPixB2BConstants.transType_DME;
		else if(IPixB2BConstants.transType_OS.equals(shortTransType) || IPixB2BConstants.OrderStatus.equals(transName))
			transType = IPixB2BConstants.transType_OST;
		else if(IPixB2BConstants.transType_OC.equals(shortTransType) || IPixB2BConstants.OrderConfirmation.equals(transName))
			transType = IPixB2BConstants.transType_OCO;
		else if(IPixB2BConstants.transType_IC.equals(shortTransType) || IPixB2BConstants.InventoryChange.equals(transName))
			transType = IPixB2BConstants.transType_INC;
		else if(IPixB2BConstants.transType_IS.equals(shortTransType) || IPixB2BConstants.InventoryStatus.equals(transName))
			transType = IPixB2BConstants.transType_INV;
		else if(IPixB2BConstants.transType_UN.equals(shortTransType) || IPixB2BConstants.Usage.equals(transName))
			transType = IPixB2BConstants.transType_USG;
		else if(IPixB2BConstants.transType_GR.equals(shortTransType) || IPixB2BConstants.GoodsReceipt.equals(transName))
			transType = IPixB2BConstants.transType_GRE;
		else if(IPixB2BConstants.transType_DMB.equals(shortTransType) || IPixB2BConstants.ShipmentStatus.equals(transName))
			transType = IPixB2BConstants.transType_DMB;
		else if(IPixB2BConstants.transType_BA.equals(shortTransType) || IPixB2BConstants.BusinessAcknowledgement.equals(transName))
			transType = IPixB2BConstants.transType_ACK;
		else if(IPixB2BConstants.transType_INVO.equals(shortTransType)|| IPixB2BConstants.Invoice.equals(transName))
			transType = IPixB2BConstants.transType_INVO;		
		return transType;
	}
	/**
	 * This method adds DASHES in SAN if not present.
	 * @param SAN
	 * @return String
	 */
	public String addDashesInSAN(String SAN){
		String sanWithDashes = null;
		
		if(SAN.indexOf('-') != -1){
			sanWithDashes = SAN;
		}else{		
			if(SAN.length() <=3)
				sanWithDashes = SAN;
			else if(SAN.length() <= 7)
				sanWithDashes = SAN.substring(0, 3)+"-"+SAN.substring(3, SAN.length());
			else if(SAN.length() > 7)
				sanWithDashes = SAN.substring(0, 3)+"-"+SAN.substring(3, 7)+"-"+SAN.substring(7, SAN.length());
		}
		return sanWithDashes;
	}
	/**
	 * This method return Transaction Tag start string without Transaction Schema.
	 * @param transactionName
	 * @return String
	 */
	public String getTransTagWithoutSchema(String transactionName){
		return "<"+transactionName;
	}
	/**
	 * This method return Transaction Tag start string with Transaction Schema appended in it.
	 * @param transactionName
	 * @param transactionSchema
	 * @return String
	 */
	public String getTransTagWithSchema(String transactionName, String transactionSchema){
		return "<"+transactionName+" "+IPixB2BConstants.transactionSchemaPath+transactionSchema+"\"";
	}
	/**
	 * This method return  XmlString from given XML File.
	 * @param xmlFileNameWithDir
	 * @return String
	 */
	public String getXmlStringFromXmlFile(String xmlFileNameWithDir){
		FileUtils fileUtils	= null;
		String xmlString 	= null;		
		try {
			fileUtils = new FileUtils();
			xmlString = fileUtils.readFile(new File(xmlFileNameWithDir).getAbsolutePath());
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			fileUtils = null;
		}
		return xmlString;
	}
	/**
	 * This method add the transaction local schema into transaction root tag 
	 * in middle of the XML that is required for the validation of XML at runtime.
	 * 
	 * @param xmlString
	 * @param transactionName
	 * @param transactionSchema
	 * @return String
	 */
	public String addTransSchemaIntoXml(String xmlString, String transactionName, String transactionSchema){
		B2BHelper b2bHelper	= null;
		try {			
			b2bHelper = new B2BHelper();
			xmlString = xmlString.replaceAll("[^\u0000-\u007F]", "");
			xmlString = xmlString.replaceFirst(b2bHelper.getTransTagWithoutSchema(transactionName), b2bHelper.getTransTagWithSchema(transactionName, transactionSchema));
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			b2bHelper	= null;
		}
		return xmlString;
	}
	/**
	 * This method remove the transaction local schema from transaction root tag 
	 * in middle of the XML that is not required by the vendors.
	 *  
	 * @param xmlFileNameWithDir
	 * @param transactionName
	 * @param transactionSchema
	 * @return boolean
	 */
	public boolean removeTransSchemaFromXml(String xmlFileNameWithDir, String transactionName, String transactionSchema){
		B2BHelper b2bHelper		= null;
		FileUtils fileUtils 	= null;
		String xmlString 		= null;		
		boolean xmlModified 	= false;
		
		try {
			b2bHelper = new B2BHelper();
			fileUtils = new FileUtils();			
			xmlString = getXmlStringFromXmlFile(xmlFileNameWithDir);
			xmlString = xmlString.replaceFirst(b2bHelper.getTransTagWithSchema(transactionName, transactionSchema), b2bHelper.getTransTagWithoutSchema(transactionName));
			fileUtils.writeFile(xmlFileNameWithDir, xmlString);			
			xmlModified = true;
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			b2bHelper	= null;
			fileUtils 	= null;
			xmlString 	= null;			
		}
		return xmlModified;
	}
	/**
	 * This method validates the XML file list and returns the valid and invalid XML files list map. 
	 * @param xmlFileList
	 * @param transactionName
	 * @param papiNetSchema
	 * @param vendorSAN
	 * @param vendorName
	 * @return HashMap
	 */
	public HashMap validateXmlFiles(ArrayList xmlFileList, String transactionName, String papiNetSchema, String vendorSAN, String vendorName) {
		XmlValidator xmlValidator	= null;
		HashMap hmValidatedXmlList	= null;		
		try {
			xmlValidator = new XmlValidator();
			hmValidatedXmlList = xmlValidator.validateXml(xmlFileList, transactionName, papiNetSchema, vendorSAN, vendorName);
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			xmlValidator = null;
		}		
		return hmValidatedXmlList;
	}
	/**
	 * This method return the Vendor's Valid (validation success) XML status.
	 * @param validXmlList
	 * @param transID
	 * @param transType
	 * @param transName
	 * @param vendorSAN
	 * @param vendorName
	 * @param inDirArchiveXmlValid
	 * @return TransactionStatusDTO
	 */
	public TransactionStatusDTO getVendorsValidXmlStatus(ArrayList validXmlList, String transID, String transType, String transName, String vendorSAN, String vendorName, String inDirArchiveXmlValid){
		String xmlFileNameWithDir			= null;		
		TransStatusHelper transStatusHelper = null;
		B2BHelper b2bHelper					= null;
		FileUtils fileUtils					= null;
		String strFileNameWithExtn			= null;
		String strFileNameWithoutExtn 		= null;
		String[] arrStrFileName 			= null;
		TransactionStatusDTO transStatusDTO = null;
		String processStatus				= null;
		String ackStatus					= null;
		
		try {
			if(validXmlList != null && validXmlList.size()>0){				
				transStatusHelper = new TransStatusHelper();
				b2bHelper = new B2BHelper();
				fileUtils = new FileUtils();
				
				for(int i=0; i < validXmlList.size(); i++){
					xmlFileNameWithDir = (String)validXmlList.get(i);
					
					strFileNameWithExtn = fileUtils.getFileName(xmlFileNameWithDir);
					strFileNameWithoutExtn = fileUtils.removeFileExtension(strFileNameWithExtn);
					arrStrFileName = strFileNameWithoutExtn.split("_");
					
					if(IPixB2BConstants.transType_ACK.equals(transType)){
						processStatus = IPixB2BConstants.processType_OUTBOUND;
						ackStatus = IPixB2BConstants.status_KS;
					}else{
						processStatus = IPixB2BConstants.processType_INBOUND;
						ackStatus = IPixB2BConstants.status_KU;
					}
					
					transStatusDTO = transStatusHelper.setTransactionStatus(processStatus, arrStrFileName[0], arrStrFileName[1],
  							arrStrFileName[3], b2bHelper.getLongTransType(arrStrFileName[2], null), b2bHelper.getTransName(arrStrFileName[2]),  
  							strFileNameWithExtn, vendorSAN, inDirArchiveXmlValid, fileUtils.getFileSizeKB(xmlFileNameWithDir), fileUtils.getFileSizeKB(xmlFileNameWithDir),
  							null, IPixB2BConstants.status_RS, IPixB2BConstants.status_FS, IPixB2BConstants.status_AS, IPixB2BConstants.status_MU, ackStatus,transID,null); 
				} //end for loop - validXmlList
			} //end if - validXmlList
		} catch (NumberFormatException e) {
			B2BLogger.error("NumberFormatException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			xmlFileNameWithDir	= null;		
			transStatusHelper 	= null;
			fileUtils			= null;
			processStatus		= null;
			ackStatus			= null;
		}
		return transStatusDTO;
	}
	/**
	 * This method process the Vendor's Invalid (validation error/failure) XML.
	 * @param invalidXmlList
	 * @param inDirArchiveXmlInvalid
	 * @param transName
	 * @param transactionSchema
	 * @param vendorSAN
	 * @param inTransFactory
	 * @param documentNumber 
	 */
	public void processVendorsInvalidXml(ArrayList invalidXmlList, String inDirArchiveXmlInvalid, String transType, String transName, String transactionSchema, String vendorSAN, InboundTransactionFactory inTransFactory, String documentNumber,String documentDate, PapiNetEnvelopeDTO pneDTO, String tranrefId3){
		HashMap hmXmlWithError 		= null;
		Iterator itr 				= null;
		String xmlFileNameWithDir	= null;
		B2BHelper b2bHelper			= null;
		FileUtils fileUtils			= null;
		ArrayList errorList 		= null;
		String errorStr				= null;
		String strFileNameWithExtn	= null;
		String strFileNameWithoutExtn = null;
		String[] arrStrFileName 	= null;
		TransStatusHelper transStatusHelper = null;
		TransactionStatusDTO transStatusDTO = null;
		ErrorDTO errorDTO			= null;
		ArrayList errorListNew		= null;
		IErrorDAO errorDAO 			= null;
		String processStatus		= null;
		String ackStatus			= null;
		String dbTransIdNext     = null;
		
		try {
			if(invalidXmlList != null && invalidXmlList.size()>0){
				transStatusHelper = new TransStatusHelper();			
				b2bHelper = new B2BHelper();
				fileUtils = new FileUtils();
				
				for(int i=0; i < invalidXmlList.size(); i++){
					
					
					hmXmlWithError 	= (HashMap)invalidXmlList.get(i);
					if(hmXmlWithError != null && hmXmlWithError.size() > 0){
						itr = hmXmlWithError.keySet().iterator();
						while (itr.hasNext()) {
							xmlFileNameWithDir = (String) itr.next();
							
							b2bHelper.removeTransSchemaFromXml(xmlFileNameWithDir, transName, transactionSchema);
							fileUtils.moveFile(xmlFileNameWithDir, inDirArchiveXmlInvalid);
							B2BLogger.info("B2BHelper.processVendorsInvalidXml() :: VENDOR XML "+xmlFileNameWithDir+" IS INVALID FOR vendorSAN = "+vendorSAN);
							
							errorList = (ArrayList)hmXmlWithError.get(xmlFileNameWithDir);
							if(errorList != null && errorList.size()>0){
								dbTransIdNext = genratePIXTransactionId();
								errorListNew = new ArrayList();
								for(int j=0; j<errorList.size(); j++){
			      					errorStr = (String)errorList.get(j);			      					
			      					
			      					strFileNameWithExtn = fileUtils.getFileName(xmlFileNameWithDir);
			      					strFileNameWithoutExtn = fileUtils.removeFileExtension(strFileNameWithExtn);
			      					arrStrFileName = strFileNameWithoutExtn.split("_");
			      					
			      					if(IPixB2BConstants.transType_ACK.equals(transType)){
			    						processStatus = IPixB2BConstants.processType_OUTBOUND;
			    						ackStatus = IPixB2BConstants.status_KE;
			    					}else{
			    						processStatus = IPixB2BConstants.processType_INBOUND;
			    						ackStatus = IPixB2BConstants.status_KU;
			    					}
			      					
			    						//dbTransIdNext = genratePIXTransactionId();
			    						
			    						
			      				/**	transStatusDTO = transStatusHelper.setTransactionStatus(processStatus, arrStrFileName[0], arrStrFileName[1],
			      							arrStrFileName[3], b2bHelper.getLongTransType(arrStrFileName[2], null), b2bHelper.getTransName(arrStrFileName[2]),  
			      							strFileNameWithExtn, vendorSAN, inDirArchiveXmlInvalid, fileUtils.getFileSizeKB(xmlFileNameWithDir), fileUtils.getFileSizeKB(xmlFileNameWithDir),
			      							null, status_RE, status_FS, status_AS, status_MU, ackStatus);*/
			      					
				      				transStatusDTO = transStatusHelper.setTransactionStatus(processStatus, arrStrFileName[0], arrStrFileName[1],
				      						    dbTransIdNext, b2bHelper.getLongTransType(arrStrFileName[2], null), b2bHelper.getTransName(arrStrFileName[2]),  
				      							strFileNameWithExtn, vendorSAN, inDirArchiveXmlInvalid, fileUtils.getFileSizeKB(xmlFileNameWithDir), fileUtils.getFileSizeKB(xmlFileNameWithDir),
				      							null, IPixB2BConstants.status_RE, IPixB2BConstants.status_FS, IPixB2BConstants.status_AS, IPixB2BConstants.status_MU, ackStatus,documentNumber,documentDate);
				      			
			      					errorDTO = b2bHelper.setInvalidXmlErrorDetails(""+IPixB2BConstants.ERROR_ID_72, fileUtils.getFileName(xmlFileNameWithDir), IPixB2BConstants.XML, 
			      							IPixB2BConstants.B2B, IPixB2BConstants.VEN_TO_PIX, 
			      							transStatusDTO.getTransID(), IPixB2BConstants.TRANS_ID, inDirArchiveXmlInvalid, IPixB2BConstants.FILE_PATH,
			      							errorStr, IPixB2BConstants.flag_N, documentDate, tranrefId3);       //Abhilasha: Change VendorSAN,VENDOR_SAN to TransId and Trans_ID
			      					errorListNew.add(errorDTO);
			      				}
								errorDAO = inTransFactory.geErrorDAO();
		      					errorDAO.storeInboundXmlErrorDetails(transStatusDTO, errorListNew, IPixB2BConstants.B2B, IPixB2BConstants.VEN_TO_PIX, IPixB2BConstants.flag_U, pneDTO);
							}							
						} //end while loop							
					}
				} //end for loop - invalidXmlList
			} //end if - invalidXmlList
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			hmXmlWithError 		= null;
			itr 				= null;
			xmlFileNameWithDir	= null;
			b2bHelper			= null;
			fileUtils			= null;
			errorList 			= null;
			errorStr			= null;
			errorDAO 			= null;
			errorDTO			= null;
			errorListNew		= null;
			processStatus		= null;
			ackStatus			= null;
		}
	}
	/**
	 * This method set the Invalid XML error details in ErrorDTO 
	 * for Outbound/Inbound transactions and return the same to caller.
	 * @param errorId
	 * @param sourceName
	 * @param sourceType
	 * @param destName
	 * @param destType
	 * @param transRefId1
	 * @param transRefLabel1
	 * @param transRefId2
	 * @param transRefLabel2
	 * @param errorDesc
	 * @param emailFlag
	 * @return ErrorDTO
	 */
	public ErrorDTO setInvalidXmlErrorDetails(String errorId, String sourceName, String sourceType, 
			String destName, String destType, 
			String transRefId1, String transRefLabel1, String transRefId2, String transRefLabel2,
			String errorDesc, String emailFlag, String transRefId3, String transRefLabel3 ){
		
		ErrorDTO errorDTO 	= null;		
		try {
			errorDTO = new ErrorDTO();			
			errorDTO.setErrorID(errorId);
			errorDTO.setSourceName(sourceName);
			errorDTO.setSourceType(sourceType);			
			errorDTO.setDestName(destName);
			errorDTO.setDestType(destType);
			errorDTO.setTransRefId_1(transRefId1);
			errorDTO.setTransRefLabel_1(transRefLabel1);
			errorDTO.setTransRefId_2(transRefId2);
			errorDTO.setTransRefLabel_2(transRefLabel2);
			errorDTO.setErrorDescription((errorDesc != null && errorDesc.length() > IPixB2BConstants.ERROR_STRING_LENGTH ) ? errorDesc.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH) : errorDesc);
			errorDTO.setEmailFlag(emailFlag);
			errorDTO.setTransRefId_3(transRefId3);
			errorDTO.setTransRefLabel_3(transRefLabel3);
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			errorDTO = null;
		}
		return errorDTO;
	}
	/**
	 * This method break string into substrings and set all these substrings into an ArrayList.
	 * @param inString
	 * @return ArrayList
	 */

	public ArrayList breakStringInSubstrgs(String inString){	
		ArrayList strTextList 	= null;
		String[] arrStringWords = null;
		String tempStr 			= "";
		int index 				= 0;
		StringBuffer stringToAdd= null; 
		final int strLen = 72;
		
		try {			 
			strTextList = new ArrayList();			
			arrStringWords = inString.split(" ");		
			stringToAdd = new StringBuffer();
			
			while(true) {
				tempStr = arrStringWords[index]+" ";
				if(tempStr.length()>strLen){
	                  index++;	                  
	                  if(index >= arrStringWords.length)
	                  break;
	                  else
	                  continue;
	            }
				if(stringToAdd.toString().length() + tempStr.length() >= strLen){
					strTextList.add(stringToAdd.toString());
					stringToAdd = null; 
					stringToAdd = new StringBuffer();
					tempStr = null;
					//index++;
					continue;
				}else{
					stringToAdd.append(tempStr);
				}
				if(index >= arrStringWords.length-1){
					if(null != stringToAdd && stringToAdd.length()>0)
						strTextList.add(stringToAdd.toString());
						break;
				}
				index++;
			}			
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			arrStringWords  = null;
			tempStr			= null;
			stringToAdd		= null; 
		}
		return strTextList;
	}	
	
	public ArrayList breakStringInSubstrings(String inString){	
		ArrayList strTextList 	= null;
		String[] arrStringWords = null;
		String tempStr 			= "";
		int index 				= 0;
		StringBuffer stringToAdd= null; 
		final int strLen = 256;
		
		try {			 
			strTextList = new ArrayList();			
			arrStringWords = inString.split(" ");		
			stringToAdd = new StringBuffer();
			
			while(true) {
				tempStr = arrStringWords[index]+" ";
				if(tempStr.length()>strLen){
	                  index++;	                  
	                  if(index >= arrStringWords.length)
	                  break;
	                  else
	                  continue;
	            }
				if(stringToAdd.toString().length() + tempStr.length() >= strLen){
					strTextList.add(stringToAdd.toString());
					stringToAdd = null; 
					stringToAdd = new StringBuffer();
					tempStr = null;
					//index++;
					continue;
				}else{
					stringToAdd.append(tempStr);
				}
				if(index >= arrStringWords.length-1){
					if(null != stringToAdd && stringToAdd.length()>0)
						strTextList.add(stringToAdd.toString());
						break;
				}
				index++;
			}			
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			arrStringWords  = null;
			tempStr			= null;
			stringToAdd		= null; 
		}
		return strTextList;
	}
	/**
	 * This method returns HashMap of senderSAN and receiverSAN
	 * @param transmissionInfoList
	 * @return HashMap
	 */
	public HashMap getSendorSANReceiverSAN(ArrayList transmissionInfoList){
		HashMap hmSAN								= null;
		TransmissionInfo transmissionInfo 			= null;		
		
		TransmissionSender transmissionSender 		= null;		
		ArrayList senderPartyIdentifierList 		= null;
		TransmissionSenderPI transmissionSenderPI 	= null;
		String senderSAN							= null;		
		
		TransmissionReceiver transmissionReceiver 	= null;
		ArrayList receiverPartyIdentifierList 		= null;
		TransmissionReceiverPI transmissionReceiverPI = null;
		String receiverSAN							= null;		
		
		try {
			if(transmissionInfoList != null && transmissionInfoList.size()>0){
				for(int i= 0; i<transmissionInfoList.size(); i++){
					transmissionInfo = (TransmissionInfo)transmissionInfoList.get(i);
					if(transmissionInfo != null){
						transmissionSender = transmissionInfo.getTransmissionSender();
						if(transmissionSender != null){
							senderPartyIdentifierList = transmissionSender.getPIdTransmissionSenderList();
							if(senderPartyIdentifierList != null && senderPartyIdentifierList.size()>0){
								for(int j= 0; j<senderPartyIdentifierList.size(); j++){
									transmissionSenderPI = (TransmissionSenderPI)senderPartyIdentifierList.get(j);
									if(transmissionSenderPI != null && transmissionSenderPI.getPartyIdentifierType() != null && IPixB2BConstants.StandardAddressNumber.equalsIgnoreCase(transmissionSenderPI.getPartyIdentifierType().trim()))
										senderSAN = transmissionSenderPI.getPartyIdentifierValue();
								}
							}
						}
						transmissionReceiver = transmissionInfo.getTransmissionReceiver();
						if(transmissionReceiver != null){
							receiverPartyIdentifierList = transmissionReceiver.getPIdTransmissionReceiverList();
							if(receiverPartyIdentifierList != null && receiverPartyIdentifierList.size()>0){
								for(int j= 0; j<receiverPartyIdentifierList.size(); j++){
									transmissionReceiverPI = (TransmissionReceiverPI)receiverPartyIdentifierList.get(j);
									if(transmissionReceiverPI != null && transmissionReceiverPI.getPartyIdentifierType() != null && IPixB2BConstants.StandardAddressNumber.equalsIgnoreCase(transmissionSenderPI.getPartyIdentifierType().trim()))
										receiverSAN = transmissionReceiverPI.getPartyIdentifierValue();
								}
							}
						}
					}
				} //end for loop - transmissionInfoList
				
				if(senderSAN != null && !"".equals(senderSAN.trim()) && receiverSAN != null && !"".equals(receiverSAN.trim())){
					hmSAN = new HashMap();
					hmSAN.put("senderSAN", senderSAN);
					hmSAN.put("receiverSAN", receiverSAN);
				}
			}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			transmissionInfo 		= null;
			transmissionSender 		= null;		
			senderPartyIdentifierList= null;
			transmissionSenderPI 	= null;
			senderSAN				= null;
			transmissionReceiver 	= null;
			receiverPartyIdentifierList = null;
			transmissionReceiverPI 	= null;
			receiverSAN				= null;
		}
		return hmSAN;
	}
	
	public String getPropertyFromEnvelope(PapiNetEnvelopeDTO pneDTO, String property, String objectType){
		OSHeader  xmlOSHeader							= null;
		OSReference  xmlOSRef							= null;
		ArrayList xmlOSRefList							= null;
		
		ArrayList xmlOSDetailList 						= null;
		OrderStatusDetail xmlOSDetail 					= null;
		ArrayList partyIdentifierList 					= null;
		SupplierPartyPartyIdentifier spPartyIdentifier 	= null;
		String retValue 								= null;
		InvoiceHeader invoHdr 							= null;
		ArrayList xmlInvoRefList 						= null;
		InvoiceRef xmlInvoRef 							= null;
		ArrayList partyIdList 							= null;		
		SupplierParty  suppParty 						= null;
		OCReference  xmlOCRef							= null;
		ArrayList xmlOCRefList							= null;
		OCHeader  xmlOCHeader							= null;
		ArrayList partyIdentList						= null;
		SupplierPartyPartyIdentifier partyIdentifier 	= null;
		EndPartyIdentifier partyIdentifierUsg			= null;
		UsageHeader usgHdr 								= null;
		ArrayList usgRefList 							= null;
		UsageReference xmlUsgRef 						= null;
		GRHeader grHdr                                  = null;
		PartyIdentifier shipToParty						= null;
		GRRef xmlGrRef 									= null;	
		ArrayList grRefList 							= null;		
		ICHeader xmlICHeader 							= null;
		ArrayList xmlIcRefList 							= null;
		ICReference xmlICRef 							= null;
		LPPartyIdentifier icPartyIdentifier 			= null;
		ISHeader xmlISHeader							= null;
		ArrayList xmlIsRefList 							= null;
		ISReference xmlISRef 							= null;
		LocationParty locationParty 					= null;
		LocationPartyPartyIdentifier isPartyIdentifier 	= null;
		
		DelMesBookHd xmlDMBookHd   						= null;
		ArrayList xmlDMRefList							= null;
		DelMesRef delMesRef								= null;
		ArrayList supplierPartyIdList					= null;
		SupplierPartyId supplierPartyId 				= null; 
		
		DelMesWoodHd delMesWoodHd  						= null;
		ArrayList suppPartyIdWoodList					= null;
		ArrayList xmlDMRefWoodList						= null;
		DelMesRef delMesWoodRef							= null;
		SupplierPartyId supPartyIdWood 					= null;
		
		if(IPixB2BConstants.transType_OST.equalsIgnoreCase(objectType)){
			if(IPixB2BConstants.VENDOR_SAN.equalsIgnoreCase(property)){
				xmlOSDetailList = pneDTO.getPayload().getBusinessDocument().getOsDTO().getOsDetailList();
				if(xmlOSDetailList != null && xmlOSDetailList.size() > 0){
					for(int i= 0; i<xmlOSDetailList.size(); i++){
						xmlOSDetail = (OrderStatusDetail) xmlOSDetailList.get(i);
						if(xmlOSDetail != null){
							partyIdentifierList = xmlOSDetail.getSupplierParty().getPartyIdentifierList();
							if(partyIdentifierList != null && partyIdentifierList.size() > 0){
								for(int j = 0; j<partyIdentifierList.size(); j++){
									spPartyIdentifier = (SupplierPartyPartyIdentifier) partyIdentifierList.get(j);
									if(spPartyIdentifier != null && null!=spPartyIdentifier.getPartyIdentifierType()
											&& IPixB2BConstants.StandardAddressNumber.equalsIgnoreCase(spPartyIdentifier.getPartyIdentifierType().trim())){
										retValue = spPartyIdentifier.getPartyIdentifierValue();}
								}
							}
						}
					}
				}
			}
			else if(IPixB2BConstants.XML_TRANS_ID.equalsIgnoreCase(property)){
				xmlOSHeader = pneDTO.getPayload().getBusinessDocument().getOsDTO().getOsHeader();
				if(null!=xmlOSHeader){
					xmlOSRefList = xmlOSHeader.getOsReferenceList();
					if(null!=xmlOSRefList && xmlOSRefList.size()>0){
						for(int l=0; l<xmlOSRefList.size(); l++){
							xmlOSRef = (OSReference) xmlOSRefList.get(l);  				
							if(null!=xmlOSRef && null!=xmlOSRef.getOsReferenceType() 
									&& IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlOSRef.getOsReferenceType().trim())){
								retValue = xmlOSRef.getOsReferenceValue();
							}
						}
					}
				}
			}
		}
		
		else if(IPixB2BConstants.transType_OCO.equalsIgnoreCase(objectType)){
			if(IPixB2BConstants.VENDOR_SAN.equalsIgnoreCase(property)){
				xmlOCHeader = pneDTO.getPayload().getBusinessDocument().getOcDTO().getOcHeader();
				if(xmlOCHeader != null){
					partyIdentList = xmlOCHeader.getOcSupplier().getPartyIdentifier();
					if(partyIdentList != null && partyIdentList.size()>0){
						for(int count=0; count<partyIdentList.size(); count++){
							partyIdentifier = (SupplierPartyPartyIdentifier) partyIdentList.get(count); 
							if(null!=partyIdentifier && null!=partyIdentifier.getPartyIdentifierType()
									&& IPixB2BConstants.StandardAddressNumber.equalsIgnoreCase(partyIdentifier.getPartyIdentifierType().trim()))
								retValue = partyIdentifier.getPartyIdentifierValue();
						}
					}
					//partyIdentifier = xmlOCHeader.getOcSupplier().getPartyIdentifier();
					//if(null!=partyIdentifier)
						//retValue = partyIdentifier.getPartyIdentifierValue();
				}
				
			}
			else if(IPixB2BConstants.XML_TRANS_ID.equalsIgnoreCase(property)){
				xmlOCHeader = pneDTO.getPayload().getBusinessDocument().getOcDTO().getOcHeader();
				if(xmlOCHeader != null){
					xmlOCRefList = xmlOCHeader.getOcReference();
					if(xmlOCRefList != null && xmlOCRefList.size()>0){
						for(int l=0; l<xmlOCRefList.size(); l++){
							xmlOCRef = (OCReference) xmlOCRefList.get(l);  				
							if(xmlOCRef != null && xmlOCRef.getOcReferenceType() != null && IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlOCRef.getOcReferenceType().trim()))
								retValue = xmlOCRef.getOcReferenceValue();
						}
					}
				}
			}
		}
		
		else if(IPixB2BConstants.transType_USG.equalsIgnoreCase(objectType)){
			if(IPixB2BConstants.VENDOR_SAN.equalsIgnoreCase(property)){
				usgHdr = pneDTO.getPayload().getBusinessDocument().getUsgDTO().getUsageHeader();
				if(usgHdr != null){
					partyIdentifierUsg = usgHdr.getUsageEndUser().getPartyIdentifier();
					if(null!=partyIdentifierUsg)
						retValue = partyIdentifierUsg.getPartyIdentifierValue();
				}
			}
			else if(IPixB2BConstants.XML_TRANS_ID.equalsIgnoreCase(property)){
				usgHdr = pneDTO.getPayload().getBusinessDocument().getUsgDTO().getUsageHeader();
				if(usgHdr != null){
					usgRefList = usgHdr.getUsageRef();
					if(usgRefList != null && usgRefList.size()>0){
						for(int j=0;j<usgRefList.size();j++){
							xmlUsgRef = (UsageReference)usgRefList.get(j);
							if(xmlUsgRef != null && xmlUsgRef.getUsageRefType()!= null ){
								if(IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlUsgRef.getUsageRefType().trim()))
									retValue = xmlUsgRef.getUsageRefValue();
							}
						}		
					}
				}
			}
		}
		
		else if(IPixB2BConstants.transType_INVO.equalsIgnoreCase(objectType)){ 
			if(IPixB2BConstants.VENDOR_SAN.equalsIgnoreCase(property)){
				invoHdr = pneDTO.getPayload().getBusinessDocument().getInvoDTO().getInvHeader();
				if(invoHdr != null){
					spPartyIdentifier = new SupplierPartyPartyIdentifier();
					partyIdList =  invoHdr.getSuppParty().getSuppPartyIdList();
					if(partyIdList != null && partyIdList.size() > 0){
						for(int j = 0; j<partyIdList.size(); j++){
							spPartyIdentifier = (SupplierPartyPartyIdentifier) partyIdList.get(j);
							if(spPartyIdentifier != null){
								retValue = spPartyIdentifier.getPartyIdentifierValue();	
							}
						}
					}
				}
			}
			else if(IPixB2BConstants.XML_TRANS_ID.equalsIgnoreCase(property)){
				invoHdr = pneDTO.getPayload().getBusinessDocument().getInvoDTO().getInvHeader();
				if(invoHdr != null){
					xmlInvoRefList = invoHdr.getInvRef();
					if(xmlInvoRefList != null && xmlInvoRefList.size() > 0){
						for(int i= 0; i<xmlInvoRefList.size(); i++){
							xmlInvoRef = (InvoiceRef) xmlInvoRefList.get(i);
							if(xmlInvoRef != null && xmlInvoRef.getInvRefType() != null && IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlInvoRef.getInvRefType().trim()))
								retValue = xmlInvoRef.getInvRefValue();						
						}
					}
				}
			}
		}
		
		else if(IPixB2BConstants.transType_ACK.equalsIgnoreCase(objectType)){
			if(IPixB2BConstants.VENDOR_SAN.equalsIgnoreCase(property)){
				retValue = Thread.currentThread().getName();
			}
			else if(IPixB2BConstants.XML_TRANS_ID.equalsIgnoreCase(property)){
				retValue = null;
			}
		}
		 
		else if(IPixB2BConstants.transType_GRE.equalsIgnoreCase(objectType)){ 
			if(IPixB2BConstants.VENDOR_SAN.equalsIgnoreCase(property)){
				grHdr = pneDTO.getPayload().getBusinessDocument().getGrDTO().getGrHeader();
				if(grHdr != null){
					shipToParty = new PartyIdentifier();
					shipToParty =  grHdr.getShipToChar().getShipToParty().getPartyId();
					if(shipToParty != null){
								retValue = shipToParty.getPartyIdentifierValue();
					}					
				}
			}
			else if(IPixB2BConstants.XML_TRANS_ID.equalsIgnoreCase(property)){
				grHdr = pneDTO.getPayload().getBusinessDocument().getGrDTO().getGrHeader();
				if(grHdr != null){
					grRefList = grHdr.getGrRef();
					if(grRefList != null && grRefList.size()>0){
						for(int j=0;j<grRefList.size();j++){
							xmlGrRef = (GRRef)grRefList.get(j);
							if(xmlGrRef != null && xmlGrRef.getGrRefType()!= null ){
								if(IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlGrRef.getGrRefType().trim()))
									retValue = xmlGrRef.getGrRefValue();
							}
						}		
					}			
				}
			}
		}
		
		else if(IPixB2BConstants.transType_INC.equalsIgnoreCase(objectType)){
			if(IPixB2BConstants.VENDOR_SAN.equalsIgnoreCase(property)){
				xmlICHeader = pneDTO.getPayload().getBusinessDocument().getIcDTO().getIcHeader();
				if(null!=xmlICHeader){
					icPartyIdentifier = xmlICHeader.getLocationParty().getPartyIdentifier();
					if(null!=icPartyIdentifier){
						retValue = icPartyIdentifier.getPartyIdentifierValue();
					}
				}
			}
			else if(IPixB2BConstants.XML_TRANS_ID.equalsIgnoreCase(property)){
				xmlICHeader = pneDTO.getPayload().getBusinessDocument().getIcDTO().getIcHeader();
				if(null!=xmlICHeader){
					xmlIcRefList = xmlICHeader.getIcRef();
					if(null!=xmlIcRefList && xmlIcRefList.size()>0){
						for(int l= 0; l<xmlIcRefList.size(); l++){
							xmlICRef = (ICReference) xmlIcRefList.get(l);
							if(null!=xmlICRef && null!=xmlICRef.getIcReferenceType() && IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlICRef.getIcReferenceType().trim())){
								retValue = xmlICRef.getIcReferenceValue();
							}
						}
					}
				}
			}
		}
		
		else if(IPixB2BConstants.transType_INV.equalsIgnoreCase(objectType)){
			if(IPixB2BConstants.VENDOR_SAN.equalsIgnoreCase(property)){
				xmlISHeader = pneDTO.getPayload().getBusinessDocument().getIsDTO().getIsHeader();
				if(null!=xmlISHeader){
					locationParty = xmlISHeader.getLocationParty();
					if(null!=locationParty){
						isPartyIdentifier = locationParty.getPartyIdentifier();
						if(null!=isPartyIdentifier){
							retValue = isPartyIdentifier.getPartyIdentifierValue();
						}
					}
				}
			}
			else if(IPixB2BConstants.XML_TRANS_ID.equalsIgnoreCase(property)){
				xmlISHeader = pneDTO.getPayload().getBusinessDocument().getIsDTO().getIsHeader();
				if(null!=xmlISHeader){
					xmlIsRefList = xmlISHeader.getIsRef();
					if(null!=xmlIsRefList && xmlIsRefList.size()>0){
						for(int l= 0; l<xmlIsRefList.size(); l++){
							xmlISRef = (ISReference) xmlIsRefList.get(l);
							if(null!=xmlISRef && null!=xmlISRef.getIsReferenceType() && IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlISRef.getIsReferenceType().trim())){
								retValue = xmlISRef.getIsReferenceValue();
							}
						}
					}
				}
			}
		}
		
		else if(IPixB2BConstants.transType_DME.equalsIgnoreCase(objectType)){
			if(IPixB2BConstants.DeliveryMessageWood.equalsIgnoreCase(pneDTO.getPayloadInfo().getDocument().getDocumentName())){
				if(IPixB2BConstants.VENDOR_SAN.equalsIgnoreCase(property)){
					delMesWoodHd = pneDTO.getPayload().getBusinessDocument().getDmwDTO().getDelMesWoodHd();
					if(null!=delMesWoodHd){
						suppPartyIdWoodList = delMesWoodHd.getSupParty().getSupplierPartyIdList();
						if(null!=suppPartyIdWoodList && suppPartyIdWoodList.size()>0){
							for(int i = 0; i<suppPartyIdWoodList.size(); i++){
								supPartyIdWood = (SupplierPartyId)suppPartyIdWoodList.get(i);
								if(null!=supPartyIdWood){
									retValue = supPartyIdWood.getPiValue();
								}
							}
						}
					}
				}
				else if(IPixB2BConstants.XML_TRANS_ID.equalsIgnoreCase(property)){
					delMesWoodHd = pneDTO.getPayload().getBusinessDocument().getDmwDTO().getDelMesWoodHd();
					if(null!=delMesWoodHd){
						xmlDMRefWoodList = delMesWoodHd.getDelMesRefList();							
						if(null!=xmlDMRefWoodList && xmlDMRefWoodList.size()>0){
							for(int j = 0; j<xmlDMRefWoodList.size(); j++){
								delMesWoodRef = (DelMesRef)xmlDMRefWoodList.get(j);
								if(null!=delMesWoodRef && null!=delMesWoodRef.getDelMesRefType() && IPixB2BConstants.TransactionID.equalsIgnoreCase(delMesWoodRef.getDelMesRefType().trim())){
									retValue = delMesWoodRef.getDelMesRefVal();
								}
							}
						}
					}
				}
			}else if(IPixB2BConstants.DeliveryMessage.equalsIgnoreCase(pneDTO.getPayloadInfo().getDocument().getDocumentName())){
				if(IPixB2BConstants.VENDOR_SAN.equalsIgnoreCase(property)){
					xmlDMBookHd = pneDTO.getPayload().getBusinessDocument().getDmDTO().getDelMesBookHd();
					if(null!=xmlDMBookHd){
						supplierPartyIdList = xmlDMBookHd.getSupParty().getSupplierPartyIdList();
						if(null!=supplierPartyIdList && supplierPartyIdList.size()>0){
							for(int i = 0; i<supplierPartyIdList.size(); i++){
								supplierPartyId = (SupplierPartyId)supplierPartyIdList.get(i);
								if(null!=supplierPartyId){
									retValue = supplierPartyId.getPiValue();
								}
							}
						}
					}
				}
				else if(IPixB2BConstants.XML_TRANS_ID.equalsIgnoreCase(property)){
					xmlDMBookHd = pneDTO.getPayload().getBusinessDocument().getDmDTO().getDelMesBookHd();
					if(null!=xmlDMBookHd){
						xmlDMRefList = xmlDMBookHd.getDelMesRefList();							
						if(null!=xmlDMRefList && xmlDMRefList.size()>0){
							for(int j = 0; j<xmlDMRefList.size(); j++){
								delMesRef = (DelMesRef)xmlDMRefList.get(j);
								if(null!=delMesRef && null!=delMesRef.getDelMesRefType() && IPixB2BConstants.TransactionID.equalsIgnoreCase(delMesRef.getDelMesRefType().trim())){
									retValue = delMesRef.getDelMesRefVal();
								}
							}
						}
					}
				}
			}

		}
		
		else if(IPixB2BConstants.transType_DMB.equalsIgnoreCase(objectType)){
			if(IPixB2BConstants.VENDOR_SAN.equalsIgnoreCase(property)){
				xmlDMBookHd = pneDTO.getPayload().getBusinessDocument().getDmDTO().getDelMesBookHd();
				if(null!=xmlDMBookHd){
					supplierPartyIdList = xmlDMBookHd.getSupParty().getSupplierPartyIdList();
					if(null!=supplierPartyIdList && supplierPartyIdList.size()>0){
						for(int i = 0; i<supplierPartyIdList.size(); i++){
							supplierPartyId = (SupplierPartyId)supplierPartyIdList.get(i);
							if(null!=supplierPartyId){
								retValue = supplierPartyId.getPiValue();
							}
						}
					}
				}
			}
			else if(IPixB2BConstants.XML_TRANS_ID.equalsIgnoreCase(property)){
				xmlDMBookHd = pneDTO.getPayload().getBusinessDocument().getDmDTO().getDelMesBookHd();
				if(null!=xmlDMBookHd){
					xmlDMRefList = xmlDMBookHd.getDelMesRefList();							
					if(null!=xmlDMRefList && xmlDMRefList.size()>0){
						for(int j = 0; j<xmlDMRefList.size(); j++){
							delMesRef = (DelMesRef)xmlDMRefList.get(j);
							if(null!=delMesRef && null!=delMesRef.getDelMesRefType() && IPixB2BConstants.TransactionID.equalsIgnoreCase(delMesRef.getDelMesRefType().trim())){
								retValue = delMesRef.getDelMesRefVal();
							}
						}
					}
				}
			}
		}		
		return retValue;	
	}
	
	/**
	 * To generate pix transaction id
	 * @return
	 */
	public String genratePIXTransactionId()
	{		
		Connection dbCon 			= null;
		String sqlQuery				= null;
		String qryParams			= null;
		String dbTransIdNext     	= null;
		PreparedStatement prepStmt	= null;
		ResultSet resultSet			= null;
		try{
				dbCon = DBUtils.getDBConnection();
				if (dbCon == null){
					B2BLogger.error("Error in database connection creation");
				}				      					
				sqlQuery = qry_sel_pix_trans_id_nextval;
				prepStmt = dbCon.prepareStatement(sqlQuery);
				prepStmt.clearParameters();		
				resultSet = prepStmt.executeQuery();
				while(resultSet.next())
					dbTransIdNext = resultSet.getLong("trans_id_next")+"";
			
				DBUtils.close(prepStmt);
				DBUtils.close(resultSet);
			}catch (SQLException e){
				B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
				B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
				dbTransIdNext = null;
			} catch (B2BException e) {
				B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
				dbTransIdNext = null;
			} finally{
				DBUtils.close(prepStmt);
				DBUtils.close(resultSet);			
				DBUtils.close(dbCon);
			
				sqlQuery	= null;
				qryParams	= null;
			}
	
			return dbTransIdNext;
	}

	public String getErrorDesp(int errorId) {
		Connection dbCon 			= null;
		String sqlQuery				= null;
		String qryParams			= null;
		String errorDesp			= null;
		PreparedStatement prepStmt1	= null;
		ResultSet resultSet1		= null;
		try{
				dbCon = DBUtils.getDBConnection();
				if (dbCon == null){
					B2BLogger.error("Error in database connection creation");
				}				      					
				sqlQuery = qry_sel_error_desp;
				prepStmt1 = dbCon.prepareStatement(sqlQuery);
				prepStmt1.clearParameters();
				prepStmt1.setInt(IPixB2BConstants.ONE, errorId);
				resultSet1 = prepStmt1.executeQuery();
				while(resultSet1.next())
					errorDesp = resultSet1.getString("ERROR_DESC");
				
				DBUtils.close(prepStmt1);
				DBUtils.close(resultSet1);
				
			}catch (SQLException e){
				B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
				B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
				errorDesp = null;
			}catch (B2BException e) {
				B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
				errorDesp = null;
			}finally{
				DBUtils.close(prepStmt1);
				DBUtils.close(resultSet1);			
				DBUtils.close(dbCon);
			
				sqlQuery	= null;
				qryParams	= null;
			}
			return errorDesp;
	}
	


	public int checkForRefIntegrity(String tableName, String whereClause) {
		Connection dbCon 			= null;
		String sqlQuery				= null;
		String qryParams			= null;
		int count					= -1;
		PreparedStatement prepStmt2	= null;
		ResultSet resultSet2		= null;
		try{
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
			}				      					
			sqlQuery = "SELECT COUNT(1) FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+"."+tableName+" WHERE "+whereClause;
			prepStmt2 = dbCon.prepareStatement(sqlQuery);
			prepStmt2.clearParameters();			
			resultSet2 = prepStmt2.executeQuery();
			while(resultSet2.next())
				count = resultSet2.getInt("COUNT(1)");
			
			DBUtils.close(prepStmt2);
			DBUtils.close(resultSet2);
			
		}catch (SQLException e){
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
		}finally{
			DBUtils.close(prepStmt2);
			DBUtils.close(resultSet2);			
			DBUtils.close(dbCon);
		
			sqlQuery	= null;
			qryParams	= null;
		}
		return count;
	}
	
	//Methods added by Abhilasha for verifying BA Transaction Id
	
	public int verifyTransID(String xmlTransId,String docTransType) {
		Connection dbCon 			= null;
		String sqlQuery				= null;
		String qryParams			= null;
		int count					= -1;
		PreparedStatement prepStmt	= null;
		ResultSet resultSet		= null;
		try{
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
			}				      					
			sqlQuery = "SELECT COUNT(1) FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_xmlgen_log " 
			+"WHERE trans_id = ? AND trans_type = 'B2B' AND process_type = ? " ;   
			prepStmt = dbCon.prepareStatement(sqlQuery);
			prepStmt.clearParameters();
			prepStmt.setString(IPixB2BConstants.ONE,xmlTransId);
			prepStmt.setString(IPixB2BConstants.TWO,docTransType);
			qryParams = xmlTransId+","+docTransType;
			resultSet = prepStmt.executeQuery();
			while(resultSet.next())
				count = resultSet.getInt("COUNT(1)");
			
			DBUtils.close(prepStmt);
			DBUtils.close(resultSet);	
			
		}catch (SQLException e){
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
		}finally{
			DBUtils.close(prepStmt);
			DBUtils.close(resultSet);			
			DBUtils.close(dbCon);
		
			sqlQuery	= null;
			qryParams	= null;
		}
		return count;
	}
	
	public int executeQuery(String xmlTransId) {
		Connection dbCon 			= null;
		String sqlQuery				= null;
		String qryParams			= null;
		int count					= -1;
		PreparedStatement prepStmt	= null;
		ResultSet resultSet		= null;
		try{
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
			}				      					
			sqlQuery = "SELECT COUNT(1) FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_xml_ack_log  pxal,"+IConfigConstants.PIX_DB_SCHEMA_NAME+".Pix_xmlgen_log pxl " 
			+" WHERE pxal.trans_id = ? AND pxal.trans_id = pxl.trans_id AND pxl.ACK_STATUS_FLAG ='KS' " ;
			prepStmt = dbCon.prepareStatement(sqlQuery);
			prepStmt.clearParameters();
			prepStmt.setString(IPixB2BConstants.ONE,xmlTransId);
			qryParams = xmlTransId;
			resultSet = prepStmt.executeQuery();
			while(resultSet.next())
				count = resultSet.getInt("COUNT(1)");
			
			DBUtils.close(prepStmt);
			DBUtils.close(resultSet);	
			
		}catch (SQLException e){
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
		}finally{
			DBUtils.close(prepStmt);
			DBUtils.close(resultSet);			
			DBUtils.close(dbCon);
		
			sqlQuery	= null;
			qryParams	= null;
		}
		return count;
	}
	
	//Mtd added for INVOICE Trans ID	
	
	public int getTransId(Long dbRunId) {
		Connection dbCon 			= null;
		String sqlQuery				= null;
		String qryParams			= null;
		int count					= -1;
		PreparedStatement prepStmt	= null;
		ResultSet resultSet		= null;
		try{
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
			}				      					
			sqlQuery = "SELECT TRANS_ID FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_xmlread_log " 
			+" WHERE run_id = ?  " ;
			prepStmt = dbCon.prepareStatement(sqlQuery);
			prepStmt.clearParameters();
			prepStmt.setLong(IPixB2BConstants.ONE,dbRunId);
			//qryParams = dbRunId;
			resultSet = prepStmt.executeQuery();
			while(resultSet.next())
				count = resultSet.getInt("COUNT(1)");
			
			DBUtils.close(prepStmt);
			DBUtils.close(resultSet);
		}catch (SQLException e){
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
		}finally{
			DBUtils.close(prepStmt);
			DBUtils.close(resultSet);			
			DBUtils.close(dbCon);
		
			sqlQuery	= null;
			qryParams	= null;
		}
		return count;
	}
	
	
	public HashMap getPoIdPoVersion(String transType_POR, String xmlPOTransId) {
		Connection dbCon 			= null;
		PreparedStatement prepStmt3	= null;
		ResultSet resultSet3		= null;
		String qryParams			= null;
		String sqlQuery				= null;
		String poId					= null;
		String poVersion			= null;
		HashMap hmPoIdpoVersion		= null;
		
		try {
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
			}				      		
			if(xmlPOTransId != null && !"".equals(xmlPOTransId.trim())){
				sqlQuery = qry_sel_poID_poVersion;
				prepStmt3 = dbCon.prepareStatement(sqlQuery);
				prepStmt3.clearParameters();
				prepStmt3.setString(IPixB2BConstants.ONE, IPixB2BConstants.B2B);
				prepStmt3.setString(IPixB2BConstants.TWO, transType_POR);
				prepStmt3.setString(IPixB2BConstants.THREE, xmlPOTransId);
				qryParams = IPixB2BConstants.B2B+","+transType_POR+","+xmlPOTransId;
				resultSet3 = prepStmt3.executeQuery();
				while(resultSet3.next()){
					poId	 = resultSet3.getString("poID");
					poVersion= resultSet3.getString("poVersion");
				}
				DBUtils.close(prepStmt3);
				DBUtils.close(resultSet3);
				if(poId != null && !"".equals(poId.trim()) && poVersion != null && !"".equals(poVersion.trim())){
					hmPoIdpoVersion = new HashMap();
					hmPoIdpoVersion.put("PO_ID", poId);
					hmPoIdpoVersion.put("PO_VERSION", poVersion);					
				}
				B2BLogger.info("B2BHelper.getPoIdPoVersion() - PO_ID(TRANSREF_ID_1) = "+poId+" , PO_VERSION(TRANSREF_ID_2) = "+poVersion+" FOR xmlPOTransId = "+xmlPOTransId);
			}else{
				B2BLogger.info("B2BHelper.getPoIdPoVersion() - poTransIdXml = "+xmlPOTransId);
			}
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+qry_sel_poID_poVersion+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} catch (Exception e) {
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{			
			DBUtils.close(prepStmt3);
			DBUtils.close(resultSet3);
			DBUtils.close(dbCon);
			
			qryParams	= null;
			sqlQuery	= null;
			poId		= null;
			poVersion	= null;
		}
		return hmPoIdpoVersion;
	}
	
	/**
	 * This method prepares the ErrorDTO List for all the data validation error present in a single XML.
	 * @param errorIdList
	 * @param validXMLList
	 * @param inDirArchiveXmlInvalid
	 * @param transactionId
	 * @return ArrayList
	 */ 
	public ArrayList prepareErrorDTOList(ArrayList errorIdList, ArrayList validXMLList, String inDirArchiveXmlInvalid, String transactionId, String tranrefId3, String documentDate)
	{
		FileUtils fileUtils					= null;
		B2BHelper b2bHelper					= null;
		ErrorDTO errorDTO					= null;
		ArrayList errorDTOList 				= null;
		String errorDesp 					= null;
		
		try {
			b2bHelper = new B2BHelper();
			fileUtils = new FileUtils();
			errorDTOList = new ArrayList();
		
			if(null!=errorIdList && errorIdList.size()>0)
			{
				for(int i=0; i<errorIdList.size(); i++){
					int errorId = (Integer)errorIdList.get(i);
					errorDesp = b2bHelper.getErrorDesp(errorId);
					errorDTO = b2bHelper.setInvalidXmlErrorDetails(""+errorId, fileUtils.getFileName((String)validXMLList.get(0)), IPixB2BConstants.XML, 
							IPixB2BConstants.B2B, IPixB2BConstants.VEN_TO_PIX, transactionId, IPixB2BConstants.TRANS_ID, inDirArchiveXmlInvalid, IPixB2BConstants.FILE_PATH,
							errorDesp, IPixB2BConstants.flag_N, documentDate, tranrefId3);
					errorDTOList.add(errorDTO);
				}
			}
		}catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			errorDTOList = null;
		}finally{
			fileUtils	= null;
			b2bHelper	= null;
			errorDTO	= null;
			errorDesp	= null;
		}
		return errorDTOList;
	}
	
	public ArrayList prepareErrorDTOListEx(ArrayList errorIdList, ArrayList validXMLList, String inDirArchiveXmlInvalid, String transactionId, String tranrefId3, String documentDate)
	{
		FileUtils fileUtils					= null;
		B2BHelper b2bHelper					= null;
		ErrorDTO errorDTO					= null;
		ArrayList errorDTOList 				= null;
		String errorDesp 					= null;
		ErrorDTO partErrorDTO				= null;
		try {
			b2bHelper = new B2BHelper();
			fileUtils = new FileUtils();
			errorDTOList = new ArrayList();
		
			if(null!=errorIdList && errorIdList.size()>0)
			{
				for(int i=0; i<errorIdList.size(); i++){
					partErrorDTO = (ErrorDTO)errorIdList.get(i);
					String errorId = partErrorDTO.getErrorID();
					errorDesp = b2bHelper.getErrorDesp(Integer.parseInt(errorId))+":"+partErrorDTO.getErrorDescription();
					errorDTO = b2bHelper.setInvalidXmlErrorDetails(""+errorId, fileUtils.getFileName((String)validXMLList.get(0)), IPixB2BConstants.XML, 
							IPixB2BConstants.B2B, IPixB2BConstants.VEN_TO_PIX, transactionId, IPixB2BConstants.TRANS_ID, inDirArchiveXmlInvalid, IPixB2BConstants.FILE_PATH,
							errorDesp, IPixB2BConstants.flag_N, documentDate, tranrefId3);
					errorDTOList.add(errorDTO);
				}
			}
		}catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			errorDTOList = null;
		}finally{
			fileUtils	= null;
			b2bHelper	= null;
			errorDTO	= null;
			errorDesp	= null;
		}
		return errorDTOList;
	}
	
	public ArrayList prepareDAOErrorList(ArrayList errorList, ArrayList validXMLList, String inDirArchiveXmlInvalid, String transactionId, String tranrefId3, String documentDate)
	{
		FileUtils fileUtils					= null;
		B2BHelper b2bHelper					= null;
		ErrorDTO errorDTO					= null;
		ErrorDTO errorDTO1					= null;
		ArrayList errorDTOList 				= null;		
		String errorId                      = null;
		String errorDesc 					= null;		
		
		try {
			b2bHelper = new B2BHelper();
			fileUtils = new FileUtils();
			errorDTOList = new ArrayList();			
			errorDTO1 = new ErrorDTO();
		
			if(null!=errorList && errorList.size()>0)
			{
				for(int i=0; i<errorList.size(); i++){
					errorDTO1 = (ErrorDTO)errorList.get(i);
					errorId = errorDTO1.getErrorID();
					errorDesc = errorDTO1.getErrorDescription();
					/**if(null == errorDesc || "".equals(errorDesc.trim())){
						errorDesc = b2bHelper.getErrorDesp(Integer.parseInt(errorId));
					}*/
					errorDTO = new ErrorDTO();						
					errorDTO = b2bHelper.setInvalidXmlErrorDetails(""+errorId, fileUtils.getFileName((String)validXMLList.get(0)), IPixB2BConstants.XML, 
							IPixB2BConstants.B2B, IPixB2BConstants.VEN_TO_PIX, transactionId, IPixB2BConstants.TRANS_ID, inDirArchiveXmlInvalid, IPixB2BConstants.FILE_PATH,
							errorDesc, IPixB2BConstants.flag_N, documentDate, tranrefId3);
					errorDTOList.add(errorDTO);
					}
			}			
		}catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
			errorDTOList = null;
		}finally{
			fileUtils	= null;
			b2bHelper	= null;
			errorDTO	= null;
			errorDTO1   = null;
			errorId     = null;
			errorDesc	= null;
		}
		return errorDTOList;
	}
	
	public String getPoId(String xmlPONumber) {
		Connection dbCon 			= null;
		PreparedStatement prepStmt4	= null;
		ResultSet resultSet4		= null;
		String qryParams			= null;
		String sqlQuery				= null;
		String poId					= null;
		try {
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
			}				      		
				sqlQuery = qry_sel_pix_poid;
				prepStmt4 = dbCon.prepareStatement(sqlQuery);
				prepStmt4.clearParameters();
				prepStmt4.setString(IPixB2BConstants.ONE, xmlPONumber);
				qryParams = xmlPONumber;
				resultSet4 = prepStmt4.executeQuery();
				while(resultSet4.next()){
					poId = resultSet4.getString("po_id");
				}
				DBUtils.close(prepStmt4);
				DBUtils.close(resultSet4);
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+qry_sel_pix_poid+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} catch (Exception e) {
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{			
			DBUtils.close(prepStmt4);
			DBUtils.close(resultSet4);
			DBUtils.close(dbCon);
			
			qryParams	= null;
			sqlQuery	= null;
		}
		return poId;
	}
//	public String getUsageTransID(PapiNetEnvelopeDTO pneDTO) {
//		UsageHeader usgHdr 					= null;
//		ArrayList usgRefList 				= null;
//		UsageReference xmlUsgRef 			= null;
//		String xmlUsgTransID 				= null;
//		
//		try {
//			usgHdr = pneDTO.getPayload().getBusinessDocument().getUsgDTO().getUsageHeader();						
//			if(null!=usgHdr){				
//				usgRefList = usgHdr.getUsageRef();
//				if(null!=usgRefList && usgRefList.size()>0){
//					for(int j=0;j<usgRefList.size();j++){
//						xmlUsgRef = (UsageReference)usgRefList.get(j);
//						if(null!=xmlUsgRef && null!=xmlUsgRef.getUsageRefType()){
//							if(TransactionID.equalsIgnoreCase(xmlUsgRef.getUsageRefType().trim()))
//								xmlUsgTransID = xmlUsgRef.getUsageRefValue();
//						}
//					}		
//				}						
//			}
//		} catch (RuntimeException e) {
//			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
//			xmlUsgTransID = null;
//		} finally{
//			usgHdr 		= null;
//			usgRefList 	= null;
//			xmlUsgRef 	= null;
//		}
//		return xmlUsgTransID;
//	}
	
	/**
	 * 
	 */
	public boolean validateFileName(String xmlFileNameWithDir, String inDirArchiveXmlInvalid) {
		FileUtils fileUtils					= null;
		String strFileNameWithExtn			= null;
		String strFileNameWithoutExtn 		= null;
		String[] arrStrFileName 			= null;
		Boolean validFileNameFlag			= Boolean.TRUE;
		
		try {
			fileUtils = new FileUtils();

			strFileNameWithExtn = fileUtils.getFileName(xmlFileNameWithDir);
			strFileNameWithoutExtn = fileUtils.removeFileExtension(strFileNameWithExtn);
			arrStrFileName = strFileNameWithoutExtn.split("_");
			if(arrStrFileName.length==4){
				if(!(checkValidTransName(arrStrFileName[2]))){
					validFileNameFlag = Boolean.FALSE;
				}
			}else{
				validFileNameFlag = Boolean.FALSE;
			}
			if(!validFileNameFlag){
				fileUtils.moveFile((String)xmlFileNameWithDir, inDirArchiveXmlInvalid);
			}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
		}
		return validFileNameFlag;
	}
	
	/**
	 * 
	 * @param transType
	 * @return
	 */
	public boolean checkValidTransName(String transType){
		Boolean transTypeFlag	= Boolean.FALSE;
		
		if(IPixB2BConstants.transType_DM.equals(transType))
			transTypeFlag = Boolean.TRUE;
		else if(IPixB2BConstants.transType_OS.equals(transType))
			transTypeFlag = Boolean.TRUE;
		else if(IPixB2BConstants.transType_OC.equals(transType))
			transTypeFlag = Boolean.TRUE;
		else if(IPixB2BConstants.transType_IC.equals(transType))
			transTypeFlag = Boolean.TRUE;
		else if(IPixB2BConstants.transType_IS.equals(transType))
			transTypeFlag = Boolean.TRUE;
		else if(IPixB2BConstants.transType_UN.equals(transType))
			transTypeFlag = Boolean.TRUE;
		else if(IPixB2BConstants.transType_GR.equals(transType))
			transTypeFlag = Boolean.TRUE;
		else if(IPixB2BConstants.transType_DMB.equals(transType))
			transTypeFlag = Boolean.TRUE;
		else if(IPixB2BConstants.transType_BA.equals(transType))
			transTypeFlag = Boolean.TRUE;
		else if(IPixB2BConstants.transType_INVO.equals(transType))
			transTypeFlag = Boolean.TRUE;
		
		return transTypeFlag;
	}
	
	/**
	 * 
	 * @param fileName
	 * @param fileExtn
	 * @return
	 */
	public Boolean checkValidFileExt(String fileName, String fileExtn) {
		FileUtils fileUtils			= null;
		String strFileExtn 			= null;
		Boolean validFileExtFlag	= Boolean.TRUE;
		
		try {
			fileUtils = new FileUtils();
			strFileExtn = fileUtils.getFileExtension(fileName);
			if(!fileExtn.equals(strFileExtn)){
				validFileExtFlag = Boolean.FALSE;
			}
		} catch (RuntimeException e) {
			B2BLogger.error("RuntimeException :: " + StringUtils.getStackTrace(e));
		}
		return validFileExtFlag;
	}
	
	/**
	 * 
	 * @param currentDate
	 */
	public void sendEmailAndUpdateStatus(String currentDate) {
		EmailContents emailContents = null;
		StringBuffer sbBodyContent	= null;
		HashMap hmMailData 			= null;		
		EmailSender emailSender 	= null;
		ReportWriter reportWriter 	= null;
		String ftpServerDir 		= null;
		ArrayList reportEntryList 	= null;
		ArrayList finalInValidXmlList= null;
		int successXmlCount			= 0;
		int failureXmlCount			= 0;
		int invalidsuccessXmlCount	= 0;
		int invalidfailureXmlCount	= 0;
		int totCount 				= 0;	
		
		emailContents 	= new EmailContents();
		emailSender  	= new EmailSender();
		reportEntryList = new ArrayList();
		
		int emailStatus = IPixB2BConstants.EMAIL_SEND_FAILED;
		
		successXmlCount = getCountAsPerStatus("KS", "RS");
		failureXmlCount = getCountAsPerStatus("KS", "RE");
		if(successXmlCount>0 || failureXmlCount>0){
			//Sending BA XML processing email of valid XMLs
			ftpServerDir = IConfigConstants.FTP_SERVER_URL+IConfigConstants.FTP_SERVER_DIR;
			sbBodyContent = emailContents.getEmailContents(IPixB2BConstants.EMAIL_BA_XML_JOB_RUN, currentDate.toUpperCase(), ftpServerDir, successXmlCount, failureXmlCount, null, null);
			hmMailData = new HashMap();
			hmMailData.put(IPixB2BConstants.FROM_ADDRESS, IConfigConstants.PIX_USAGE_SUCCESS_EMAIL_ID_FROM);
			hmMailData.put(IPixB2BConstants.TO_ADDRESS, IConfigConstants.PIX_USAGE_SUCCESS_EMAIL_ID_TO);
			hmMailData.put(IPixB2BConstants.CC_ADDRESS, IConfigConstants.PIX_USAGE_SUCCESS_EMAIL_ID_CC);
			hmMailData.put(IPixB2BConstants.BCC_ADDRESS, IConfigConstants.PIX_USAGE_SUCCESS_EMAIL_ID_BCC);
			hmMailData.put(IPixB2BConstants.SUBJECT, IConfigConstants.PIX_USAGE_SUCCESS_EMAIL_SUBJECT);
			hmMailData.put(IPixB2BConstants.BODY_CONTENTS, sbBodyContent.toString());						
			emailStatus = emailSender.sendEmail(hmMailData);
			if(emailStatus == IPixB2BConstants.EMAIL_SEND_SUCCESS){
				updateEmailFlag("MS", "KS");
				B2BLogger.info("B2BHelper.sendEmailAndUpdateStatus() - Email Status = "+emailStatus+", BA XML processing email sent successfully");
//				reportEntryList = pixUsageHelper.updateReportEntry(reportEntryList, IPixB2BConstants.Success, IPixB2BConstants.FLAG_YES, IPixB2BConstants.FLAG_YES);
			}else{
				B2BLogger.info("B2BHelper.sendEmailAndUpdateStatus() - Email Status = "+emailStatus+", BA XML processing email not sent");					
//				reportEntryList = pixUsageHelper.updateReportEntry(reportEntryList, IPixB2BConstants.Success, IPixB2BConstants.FLAG_YES, IPixB2BConstants.FLAG_NO);
			}
		}


		//Sending BA XML validation failure email of invalid XMLs
//		if(validationFailed){
		
		
//		finalInValidXmlList = new ArrayList();
//		invalidsuccessXmlCount = getCountAsPerStatus("KE", "RS");
//		invalidfailureXmlCount = getCountAsPerStatus("KE", "RE");
//		totCount = successXmlCount+failureXmlCount;
//		finalInValidXmlList = getInvalidBAList();
//		
//		if(totCount>0)
//		sbBodyContent = emailContents.getEmailContents(IPixB2BConstants.EMAIL_BA_XML_VALIDATION_FAILURE, currentDate.toUpperCase(), null, invalidsuccessXmlCount, invalidfailureXmlCount, IConfigConstants.DIR_VALIDATION_FAILURE_XML, finalInValidXmlList);
//			hmMailData = new HashMap();
//			hmMailData.put(IPixB2BConstants.FROM_ADDRESS, IConfigConstants.PIX_USAGE_FAILURE_EMAIL_ID_FROM);
//			hmMailData.put(IPixB2BConstants.TO_ADDRESS, IConfigConstants.PIX_USAGE_FAILURE_EMAIL_ID_TO);
//			hmMailData.put(IPixB2BConstants.CC_ADDRESS, IConfigConstants.PIX_USAGE_FAILURE_EMAIL_ID_CC);
//			hmMailData.put(IPixB2BConstants.BCC_ADDRESS, IConfigConstants.PIX_USAGE_FAILURE_EMAIL_ID_BCC);
//			hmMailData.put(IPixB2BConstants.SUBJECT, IConfigConstants.PIX_USAGE_FAILURE_EMAIL_SUBJECT);
//			hmMailData.put(IPixB2BConstants.BODY_CONTENTS, sbBodyContent.toString());
//			emailStatus = emailSender.sendEmail(hmMailData);
//			if(emailStatus == IPixB2BConstants.EMAIL_SEND_SUCCESS){
//				B2BLogger.info("B2BHelper.sendEmailAndUpdateStatus() - Email Status = "+emailStatus+", BA XML validation failure email sent successfully");
//				updateEmailFlag("MS", "KE");
////				reportEntryList = pixUsageHelper.updateReportEntry(reportEntryList, IPixB2BConstants.Failure, IPixB2BConstants.FLAG_NO, IPixB2BConstants.FLAG_YES);
//			}else{
//				B2BLogger.info("B2BHelper.sendEmailAndUpdateStatus() - Email Status = "+emailStatus+", BA XML validation failure email not sent");
////				reportEntryList = pixUsageHelper.updateReportEntry(reportEntryList, IPixB2BConstants.Failure, IPixB2BConstants.FLAG_NO, IPixB2BConstants.FLAG_NO);
//			}
////		}
		
		//Generating report log
//		reportWriter = new ReportWriter();
//		reportWriter.createReportFile();
//		if(reportEntryList != null && reportEntryList.size()>0)
//			reportWriter.printReportEntries(reportEntryList);
//		reportWriter.closePrintStream();
		
	}
	
//	private ArrayList getInvalidBAList() {
//		Connection dbCon 			= null;
//		PreparedStatement prepStmt7	= null;
//		ResultSet resultSet7		= null;
//		PreparedStatement prepStmt8	= null;
//		ResultSet resultSet8		= null;
//		String qryParams			= null;
//		String sqlQuery				= null;
//		ArrayList ashishList		= null;
//		ArrayList errorList			= null;
//		String transId				= null;
//		
//		try {
//			dbCon = DBUtils.getDBConnection();
//			if (dbCon == null){
//				B2BLogger.error("Error in database connection creation");
//				return null;
//			}	
//			
//			sqlQuery = "select trans_id from pix_xmlread_log where (xml_read_flag='RS'or xml_read_flag='RE')  and ack_flag='KE' and xml_email_flag='MU'";
//			prepStmt7 = dbCon.prepareStatement(sqlQuery);
//			prepStmt7.clearParameters();
//			resultSet7 = prepStmt7.executeQuery();
//			while(resultSet7.next()){
//				transId = resultSet7.getString("trans_id");
//				if(null!=transId && !"".equals(transId)){
//					sqlQuery = "select source_name, additional_desc from pix_error_log where trans_id='"+transId+"'";
//					prepStmt8 = dbCon.prepareStatement(sqlQuery);
//					prepStmt8.clearParameters();
//					resultSet8 = prepStmt8.executeQuery();
//					errorList = new ArrayList();
//					while(resultSet8.next()){
//						String fileName=resultSet8.getString("source_name");
//						String errorDesp = resultSet8.getString("additional_desc");
//						if(null!=errorDesp && !"".equals(errorDesp)){
//							errorList.add(errorDesp);
//						}
//					}
//				}
//			}
//			
//			
//		} catch (SQLException e) {			
//			B2BLogger.error("FAILED SQL -> ["+qry_sel_pix_poid+"] ["+qryParams+"]");
//			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
//			B2BLogger.error("Exception",e);
//		} catch (Exception e) {
//			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
//			B2BLogger.error("Exception",e);
//		} finally{			
//			DBUtils.close(prepStmt7);
//			DBUtils.close(resultSet7);
//			DBUtils.close(prepStmt8);
//			DBUtils.close(resultSet8);
//			DBUtils.close(dbCon);
//			
//			qryParams	= null;
//			sqlQuery	= null;
//		}
//		return ashishList;
//	}
	private void updateEmailFlag(String emailFlag, String ackStatus) {
		Connection dbCon 			= null;
		PreparedStatement prepStmt5	= null;
		String qryParams			= null;
		String sqlQuery				= null;
		int count					= -1;
		try {
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
			}
			dbCon.setAutoCommit(false);
			
			sqlQuery = "update pix_xmlread_log set xml_email_flag='"+emailFlag+"' where trans_id in (select trans_id from pix_xmlread_log where (xml_read_flag='RS'or xml_read_flag='RE') and ack_flag='"+ackStatus+"' and xml_email_flag='MU' and trans_id is not null)" ;
			prepStmt5 = dbCon.prepareStatement(sqlQuery);
			prepStmt5.clearParameters();
			prepStmt5.executeUpdate();
				
			DBUtils.close(prepStmt5);
			
			dbCon.commit();
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+qry_sel_pix_poid+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("B2BException :: ",e1);
			}
		} catch (Exception e) {
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("B2BException :: ",e1);
			}
		} finally{			
			DBUtils.close(prepStmt5);
			DBUtils.close(dbCon);
			
			qryParams	= null;
			sqlQuery	= null;
		}
	}
		
	private int getCountAsPerStatus(String ackStatus, String readStatus) {
		
		Connection dbCon 			= null;
		PreparedStatement prepStmt6	= null;
		ResultSet resultSet6		= null;
		String qryParams			= null;
		String sqlQuery				= null;
		int count					= -1;
		try {
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
			}	
			
			sqlQuery = "select count(1) from pix_xmlread_log where xml_read_flag='"+readStatus+"' and ack_flag='"+ackStatus+"' and xml_email_flag='MU' and trans_id is not null";
			prepStmt6 = dbCon.prepareStatement(sqlQuery);
			prepStmt6.clearParameters();
			resultSet6 = prepStmt6.executeQuery();
			while(resultSet6.next()){
				count = resultSet6.getInt("COUNT(1)");
			}
			DBUtils.close(prepStmt6);
			DBUtils.close(resultSet6);
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+qry_sel_pix_poid+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} catch (Exception e) {
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{			
			DBUtils.close(prepStmt6);
			DBUtils.close(resultSet6);
			DBUtils.close(dbCon);
			
			qryParams	= null;
			sqlQuery	= null;
		}
		return count;
	}
	public synchronized void createDirectory(File fileDir) {
		if(!fileDir.exists())
			fileDir.mkdirs();
	}
	
	public ArrayList getValidTransactionList(String vendorSAN) {
		Connection dbCon 			= null;
		CallableStatement cs      	= null;
		ResultSet resultSet         = null;
		String qryParams			= null;
		
		String transType 			= null;
		ArrayList vendorTransList	= null;
		
		try {
			B2BLogger.debug("B2BHelper.getValidTransactionList() method called");			

			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}	
			//dbCon.setAutoCommit(false);
			
			cs = dbCon.prepareCall(proc_sel_inbound_vendor_transInfo);
			cs.setString(IPixB2BConstants.ONE, vendorSAN);
			cs.setString(IPixB2BConstants.TWO, "TRANSACTION");
			cs.setString(IPixB2BConstants.THREE, IPixB2BConstants.processType_INBOUND );
			cs.registerOutParameter(IPixB2BConstants.FOUR, OracleTypes.CURSOR);
			qryParams = vendorSAN+","+"TRANSACTION"+","+IPixB2BConstants.processType_INBOUND;
			cs.execute();
			resultSet = (ResultSet) cs.getObject(IPixB2BConstants.FOUR);
			
			vendorTransList = new ArrayList();
			
			while(resultSet.next()) {
				transType = resultSet.getString("PROCESS_TYPE") != null ? resultSet.getString("PROCESS_TYPE").trim() : "";
				if(!"".equals(transType)){
					vendorTransList.add(transType);
				}else{
					B2BLogger.info("B2BHelper.getValidTransactionList() : PROCESS_TYPE = "+transType);
				}
			} //end while loop
			DBUtils.close(cs);
			DBUtils.close(resultSet);
			
			//dbCon.commit();
			B2BLogger.debug("B2BHelper.getValidTransactionList() method return");
		} catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+proc_sel_inbound_vendor_transInfo+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			vendorTransList = null;			
		} catch (B2BException e) {			
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));			
			B2BLogger.error("Exception",e);
			vendorTransList = null;
		} finally{
			DBUtils.close(resultSet);
			DBUtils.close(cs);
			DBUtils.close(dbCon);
			
			qryParams		= null;
			transType 		= null;
		}
		return vendorTransList;
	}
	public HashMap getPoIdPoVersion(Connection dbCon, String xmlPONumr) {
		PreparedStatement prepStmt3	= null;
		ResultSet resultSet3		= null;
		String qryParams			= null;
		String sqlQuery				= null;
		String poId					= null;
		String poVersion			= null;
		HashMap hmPoIdpoVersion		= null;
		
		try {
//			dbCon = DBUtils.getDBConnection();
//			if (dbCon == null){
//				B2BLogger.error("Error in database connection creation");
//			}				      		
			if(null!=xmlPONumr && !"".equals(xmlPONumr.trim())){
				sqlQuery = qry_sel_POID_POVersion;
				prepStmt3 = dbCon.prepareStatement(sqlQuery);
				prepStmt3.clearParameters();
				prepStmt3.setString(IPixB2BConstants.ONE, xmlPONumr);
				qryParams = xmlPONumr;
				resultSet3 = prepStmt3.executeQuery();
				while(resultSet3.next()){
					poId	 = resultSet3.getString("po_id");
					poVersion= resultSet3.getString("po_version");
				}
				if(null!=poId && !"".equals(poId.trim()) && null!=poVersion && !"".equals(poVersion.trim())){
					hmPoIdpoVersion = new HashMap();
					hmPoIdpoVersion.put("PO_ID", poId);
					hmPoIdpoVersion.put("PO_VERSION", poVersion);					
				}
				DBUtils.close(prepStmt3);
				DBUtils.close(resultSet3);
				B2BLogger.info("B2BHelper.getPoIdPoVersion() - PO_ID = "+poId+" , PO_VERSION = "+poVersion+" FOR PO NUMBER = "+xmlPONumr);
			}else{
				B2BLogger.info("B2BHelper.getPoIdPoVersion() - PO NUMBER = "+xmlPONumr);
			}
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+qry_sel_POID_POVersion+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} catch (Exception e) {
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{			
			DBUtils.close(prepStmt3);
			DBUtils.close(resultSet3);
			
			qryParams	= null;
			sqlQuery	= null;
			poId		= null;
			poVersion	= null;
		}
		return hmPoIdpoVersion;
	}
	
	/**
	 * 
	 * @param currentDate
	 */
	public void sendEmailAndUpdateOutBndStatus(String currentDate) {
		EmailContents emailContents = null;
		StringBuffer sbBodyContent	= null;
		HashMap hmMailData 			= null;		
		EmailSender emailSender 	= null;
		String ftpServerDir 		= null;

		ArrayList errorList			= null;
		emailContents 	= new EmailContents();
		emailSender  	= new EmailSender();
		
		int emailStatus = IPixB2BConstants.EMAIL_SEND_FAILED;
		
		errorList = new ArrayList();
		errorList = getErrorPOList();
		if(null!=errorList && errorList.size()>0){
			//Sending BA XML processing email of valid XMLs

			ftpServerDir = IConfigConstants.FTP_SERVER_URL+IConfigConstants.FTP_SERVER_DIR;
			sbBodyContent = emailContents.getErrorEmailContents(IPixB2BConstants.EMAIL_BA_PO_REPORT_JOB_RUN, currentDate.toUpperCase(), errorList);
			hmMailData = new HashMap();
			hmMailData.put(IPixB2BConstants.FROM_ADDRESS, IConfigConstants.PIX_USAGE_SUCCESS_EMAIL_ID_FROM);
			hmMailData.put(IPixB2BConstants.TO_ADDRESS, IConfigConstants.PIX_USAGE_SUCCESS_EMAIL_ID_TO);
			hmMailData.put(IPixB2BConstants.CC_ADDRESS, IConfigConstants.PIX_USAGE_SUCCESS_EMAIL_ID_CC);
			hmMailData.put(IPixB2BConstants.BCC_ADDRESS, IConfigConstants.PIX_USAGE_SUCCESS_EMAIL_ID_BCC);
			//hmMailData.put(IPixB2BConstants.SUBJECT, IConfigConstants.PIX_USAGE_SUCCESS_EMAIL_SUBJECT);
			hmMailData.put(IPixB2BConstants.SUBJECT, IConfigConstants.PIX_OUTBOUND_ERROR_LOG_EMAIL_SUBJECT);
			hmMailData.put(IPixB2BConstants.BODY_CONTENTS, sbBodyContent.toString());						
			emailStatus = emailSender.sendEmail(hmMailData);
			if(emailStatus == IPixB2BConstants.EMAIL_SEND_SUCCESS){
				updateErrorLogEmailFlag();
				B2BLogger.info("B2BHelper.sendEmailAndUpdateStatus() - Email Status = "+emailStatus+", BA XML processing email sent successfully");
			}else{
				B2BLogger.info("B2BHelper.sendEmailAndUpdateStatus() - Email Status = "+emailStatus+", BA XML processing email not sent");					
			}
		}
		
	}
	private void updateErrorLogEmailFlag() {
		Connection dbCon 			= null;
		PreparedStatement prepStmt	= null;
		String qryParams			= null;
		String sqlQuery				= null;
		try {
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
			}
			dbCon.setAutoCommit(false);
			
			sqlQuery = qry_upd_errorlog_emailflag;
			prepStmt = dbCon.prepareStatement(sqlQuery);
			prepStmt.clearParameters();
			prepStmt.executeUpdate();
			
			DBUtils.close(prepStmt);
			
			dbCon.commit();
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+qry_sel_pix_poid+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("B2BException :: ",e1);
			}
		} catch (Exception e) {
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("B2BException :: ",e1);
			}
		} finally{			
			DBUtils.close(prepStmt);
			DBUtils.close(dbCon);
			
			qryParams	= null;
			sqlQuery	= null;
		}
	}
	private ArrayList getErrorPOList() {
		Connection dbCon 			= null;
		PreparedStatement prepStmt	= null;
		ResultSet resultSet			= null;
		String qryParams			= null;
		String sqlQuery				= null;
		ErrorLogDTO errorLogDTO		= null;
		ArrayList errorList			= null;	
		
		try {
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
			}	
			sqlQuery = qry_sel_po_error;
			prepStmt = dbCon.prepareStatement(sqlQuery);
			prepStmt.clearParameters();
			resultSet = prepStmt.executeQuery();
			errorList = new ArrayList(); 
			while(resultSet.next()){
				errorLogDTO = new ErrorLogDTO();
				errorLogDTO.setErrorDescription(resultSet.getString("ADDITIONAL_DESC"));
				errorLogDTO.setFileName(resultSet.getString("DEST_NAME"));
				errorLogDTO.setPoNumber(resultSet.getString("PO_NO"));
				errorList.add(errorLogDTO);
			}
			DBUtils.close(prepStmt);
			DBUtils.close(resultSet);
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} catch (Exception e) {
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{			
			DBUtils.close(prepStmt);
			DBUtils.close(resultSet);
			DBUtils.close(dbCon);
		
			qryParams	= null;
			sqlQuery	= null;
		}
		return errorList;
	}
	
}