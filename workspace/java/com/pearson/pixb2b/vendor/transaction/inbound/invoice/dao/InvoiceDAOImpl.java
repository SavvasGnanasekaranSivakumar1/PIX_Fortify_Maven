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
 * Title		: 	InvoiceDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Abhilasha Nigam 	8 Feb, 2009		Initial version
 * 
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.invoice.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import com.pearson.pixb2b.vendor.transaction.inbound.common.transstatus.dao.InboundTransStatusDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.ChargeAmount;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.ChargeInfo;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.InvoiceHeader;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.InvoiceLineItem;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.InvoiceRef;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.InvoiceSummary;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.PartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.RemitToParty;
import com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto.SupplierParty;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.GLAccount;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POReference;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.PriceDetails;
import com.pearson.pixb2b.vendor.transaction.shared.dto.SupplierPartyPartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.error.ErrorDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.transaction.shared.helper.B2BHelper;
import com.pearson.pixb2b.vendor.transaction.shared.helper.DAOHelper;
import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.DBUtils;
import com.pearson.pixb2b.vendor.utils.IConfigConstants;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * InvoiceDAOImpl is a class to communicate with the PIX database
 * and process the B2B Invoice transaction data.
 * 
 * @author Abhilasha Nigam
 */
public class InvoiceDAOImpl extends InboundTransStatusDAOImpl implements IInvoiceDAO{
	
	private static final String qry_sel_pix_invoice_id_nextval = "SELECT SEQ_INV_HEADER_INVID.nextval invoice_id_next FROM DUAL" ;
	
	private static final String qry_sel_poID_poVersion = "SELECT PO_ID, PO_VERSION "
		+" FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_PO_LIST_SUMMARY"
		+" WHERE PO_NO = ? ";	
	
	private static final String qry_sel_valid_invoice = "SELECT count(1) record_count FROM "
		+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_po_list_summary " 
		+ "WHERE po_id = ? AND isbn10 = ? AND print_no = ? AND STATUS_ID != '21' ";  //Cancel PO Id = 21
	
	private static final String qry_sel_gl_code = "SELECT GL_CODE FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_GLCODE_BISAC_MAPPING WHERE BISAC_CODE = ? ";
	
	private static final String qry_validate_glCode = "SELECT count(1) count FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_price_detail "
	+ "WHERE po_id = ? and po_version = ? and po_line_no = ? and gl_code = ?";
	
	private static final String qry_validate_requested_qty = "SELECT count(1) record_count FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_po_line"
	+ " WHERE po_id = ? AND  po_version = ? AND po_line_no = ? AND (requested_quantity + (requested_quantity " 
	+ " * nvl((select tolerance_percentage from pix_tolerance_limit WHERE san = ?),0)/100)) >= ? ";
	
	private static final String qry_ins_invoice_header = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_INVOICE_HEADER"            
	+ "(INVOICE_ID,INVOICE_NO,INVOICE_DATE,TRANSACTION_HIST_NO,TOTAL_QUANTITY,"
	+ " TOTAL_AMOUNT,SUPPLIER_SAN,REMIT_SAN,BLOCKED_FLAG,CREATION_DATE,MODIFIED_DATE,COMMENTS) VALUES"
	+ "(?,?,to_date(?,'MM/DD/YYYY'),?,?,?,?,?,?,sysdate,sysdate,?)";
	
	private static final String qry_ins_invoice_line = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_INVOICE_LINE "
	+ "(INVOICE_ID,PO_ID,PO_VERSION,PO_LINE_NO,INVOICE_LINE_NO,QUANTITY,QUANTITY_UOM_ID,COST_DESC,GL_CODE,"
	+ "CHARGED_AMOUNT,CURRENCY,CREATION_DATE,MODIFIED_DATE,COMMENTS) VALUES (?,?,?,?,?,?,"
	+ "(SELECT UOM_ID FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_UOM_CODE WHERE XUOM = ? AND rownum = 1),"
	+ "?,?,?,?,SYSDATE,SYSDATE,?)";
	
	
	private static final String qry_updt_invoice_header = "UPDATE "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_INVOICE_HEADER SET "
	+ "BLOCKED_FLAG = 'Y' WHERE  INVOICE_ID = ? " ;	
	
	private static final String func_validate_reqQty = "{? = call "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_INVOICE_BLOCK_FLAG_FUNC.testcall(?)}";
	
	/**(non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.Invoice.dao.IInvoiceDAO#storeInvoiceDetails(com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO)*/
	
	public String storeInvoiceDetails(PapiNetEnvelopeDTO pneDTO, TransactionStatusDTO transStatusDTO,ArrayList errorList){
		Connection dbCon 			= null;
		String sqlQuery				= null;
		String qryParams			= null;
		int recsCount				= 0;
		int recsNum					= 0;
		
		PreparedStatement prepStmt1	= null;
		ResultSet resultSet1		= null;
		PreparedStatement prepStmt2	= null;
		PreparedStatement prepStmt3	= null;
		ResultSet resultSet3		= null;
		PreparedStatement prepStmt4	= null;
		ResultSet resultSet4		= null;
		PreparedStatement prepStmt5	= null;
		ResultSet resultSet5		= null;
		PreparedStatement prepStmt6	= null;
		ResultSet resultSet6		= null;
		PreparedStatement prepStmt7	= null;
		ResultSet resultSet7		= null;
		PreparedStatement prepStmt8 = null;
		PreparedStatement prepStmt9	= null;
		CallableStatement cstmt     = null;
		
		ArrayList transmissionInfoList = null;
		B2BHelper b2bHelper			= null;
		HashMap hmSAN				= null;		
		String senderSAN			= null;		
		String receiverSAN			= null;
		String remitSAN				= null;
		DAOHelper daoHelper			= null;
		ArrayList xmlInvoiceDetailList	= null;	
		InvoiceLineItem xmlInvoiceDetail = null;
		String xmlPONumber	  		= null;
		ArrayList xmlPORefList		= null;		
		String xmlPOTransId			= null;
		String dbPOId				= null;
		String dbPOVersion			= null;
		HashMap hmPoIdpoVersion		= null;
		String dbInvoiceIdNext	= null;
		long dbTransIdNext 			= -1;
		long dbReleaseNo	 		= -1;
		String xmlInvoiceCode	= null;
		int statusID	 			= -1;
		String xmlAvailToShipDate	= null;
		ArrayList xmlOtherDateList	= null;
		InvoiceHeader xmlInvoiceHeader = null;
		String xmlOnPressDate   = null;
		String invoiceStatus 			= IPixB2BConstants.flag_N;
		String osNumber = null;
		String osResponseDate = null;		
		ArrayList xmlLineNotesList	= null;
		String additionalTextLine	= null;
		String xmlLineComments		= "";
		ArrayList xmlHeaderNotesList	= null;
		String additionalTextHeader	= null;
		String xmlHeaderComments	= "";
		String dbInvoiceId = null;
		String xmlInvoiceNo = null;
		ArrayList xmlInvoRefList = null;
		InvoiceRef xmlInvoRef = null;
		String invoiceTransID = null;
		String xmlInvoiceDate = null;
		String xmlTotalQty = null;
		String xmlTotalAmt = null;
		String xmlSupplierSAN = null;
		String xmlRemitSAN  = null;
		ArrayList supplierPartyList = null;
		SupplierParty supplierParty = null;
		SupplierPartyPartyIdentifier suppPartyID = null;
		RemitToParty remitParty = null;
		ArrayList remitPartyList = null;
		PartyIdentifier remitPartyID = null;
		InvoiceSummary invoSummary = null;
		String xmlISBN10 = null;
		String xmlPrintNo = null;
		String xmlGLCode = null;
		String xmlGLType = null;
		PriceDetails priceDtls = null;
		ChargeInfo chargeInfo = null;
		ChargeAmount chrgAmnt = null;
		GLAccount glAccount 	= null;
		
		ArrayList chargeInfoList = null;
		String dbGLCode = null;
		String xmlPOLineNo = null;
		String xmlReqQty = null;
		String blockFlag = null;
		String xmlInvLineNo = null;
		String xmlQtyUOM = null;
		String xmlCostDesc = null;
		String xmlChrgAmt = null;
		String xmlCurrencyType = null;
		POReference xmlPORef = null;
		ArrayList poInfoList = null;
		int errorId			= -1 ;
		long errorLogIdNextDB		= -1;	
		String flagValue = null;
		ErrorDTO daoErrorDTO    = null;
		ErrorDTO newErrorDTO = null;
		
		try {
			B2BLogger.debug("******* InvoiceDAOImpl.storeInvoiceDetails() method ENTERED *******");
			
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}
			
			if(pneDTO != null){
				transmissionInfoList = pneDTO.getPayloadInfo().getTransmissionInfoList();
				if(transmissionInfoList != null && transmissionInfoList.size()>0){
					b2bHelper = new B2BHelper();
					senderSAN = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.VENDOR_SAN, IPixB2BConstants.transType_INVO);
					hmSAN = b2bHelper.getSendorSANReceiverSAN(transmissionInfoList);
					if(hmSAN != null && hmSAN.size()>0){
						//senderSAN = (String)hmSAN.get("senderSAN");
						receiverSAN = (String)hmSAN.get("receiverSAN");
					}
					if(senderSAN != null && !"".equals(senderSAN.trim()) && receiverSAN != null && !"".equals(receiverSAN.trim())){
						senderSAN = b2bHelper.addDashesInSAN(senderSAN);
						receiverSAN = b2bHelper.addDashesInSAN(receiverSAN);
						
						daoHelper = new DAOHelper();					
						xmlInvoiceHeader = pneDTO.getPayload().getBusinessDocument().getInvoDTO().getInvHeader();
						if(xmlInvoiceHeader != null){
							xmlInvoiceNo = xmlInvoiceHeader.getInvNumber();	
							
							if(xmlInvoiceHeader.getInvoiceDate() != null){
								xmlInvoiceDate = xmlInvoiceHeader.getInvoiceDate().getInvDate().getMonth()
								+"/"+xmlInvoiceHeader.getInvoiceDate().getInvDate().getDay()
								+"/"+xmlInvoiceHeader.getInvoiceDate().getInvDate().getYear();
							}	
							
							xmlInvoRefList = new ArrayList();
							xmlInvoRef = new InvoiceRef();
							xmlInvoRefList = xmlInvoiceHeader.getInvRef();
							if(xmlInvoRefList != null && xmlInvoRefList.size() > 0){
								for(int i= 0; i<xmlInvoRefList.size(); i++){
									xmlInvoRef = (InvoiceRef) xmlInvoRefList.get(i);
									if(xmlInvoRef != null && xmlInvoRef.getInvRefType() != null && IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlInvoRef.getInvRefType().trim()))
										invoiceTransID = xmlInvoRef.getInvRefValue();						
								}
							}
							
							supplierPartyList = new ArrayList();
							supplierParty = new SupplierParty();
							suppPartyID = new SupplierPartyPartyIdentifier();
							
							supplierPartyList = xmlInvoiceHeader.getSuppParty().getSuppPartyIdList();
							if(supplierPartyList != null && supplierPartyList.size() > 0){
								for(int j=0; j<supplierPartyList.size(); j++){
									suppPartyID = (SupplierPartyPartyIdentifier) supplierPartyList.get(j);
									if(suppPartyID != null && suppPartyID.getPartyIdentifierType() != null && IPixB2BConstants.StandardAddressNumber.equalsIgnoreCase(suppPartyID.getPartyIdentifierType().trim()))
										xmlSupplierSAN = suppPartyID.getPartyIdentifierValue();									
								}
							}
							
							remitParty = new RemitToParty();
							remitPartyList = new ArrayList();
							remitPartyID = new PartyIdentifier();
							remitParty = xmlInvoiceHeader.getRemitParty(); 
							if(remitParty != null){
								remitPartyList = remitParty.getRemitPartyIdList();
								if(remitPartyList != null && remitPartyList.size() > 0){
									for(int k=0; k<remitPartyList.size(); k++){
										remitPartyID = (PartyIdentifier) remitPartyList.get(k);
										if(remitPartyID != null && remitPartyID.getPartyIdentifierType() != null && IPixB2BConstants.StandardAddressNumber.equalsIgnoreCase(remitPartyID.getPartyIdentifierType().trim())){
											xmlRemitSAN = remitPartyID.getPartyIdentifierValue();
											remitSAN = b2bHelper.addDashesInSAN(xmlRemitSAN);
										}
									}
								}
							}
							
							//Read Header Comments
							xmlHeaderNotesList = xmlInvoiceHeader.getHeaderComments();
							if(xmlHeaderNotesList != null && xmlHeaderNotesList.size() > 0){
								for(int m=0; m<xmlHeaderNotesList.size(); m++){
									additionalTextHeader = (String)xmlHeaderNotesList.get(m);
									xmlHeaderComments = xmlHeaderComments +(m == 0 ? "" : " ")+additionalTextHeader;												
								}
							}	
							
						}	
						
						invoSummary = pneDTO.getPayload().getBusinessDocument().getInvoDTO().getInvSummary();
						if(invoSummary != null){													
							xmlTotalAmt = invoSummary.getTotalAmt().getSummCurrency().getScurrencyValue();
							xmlTotalQty = invoSummary.getTotalQty().getValueQty().getValValue();
						}
						
						
						sqlQuery = qry_sel_pix_invoice_id_nextval;
						prepStmt1 = dbCon.prepareStatement(sqlQuery);
						prepStmt1.clearParameters();		
						resultSet1 = prepStmt1.executeQuery();
						while(resultSet1.next()){
							dbInvoiceId = resultSet1.getString("invoice_id_next");
							B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - dbInvoiceId = " + dbInvoiceId);
						}
						DBUtils.close(prepStmt1);
						DBUtils.close(resultSet1);	
						
						if(dbInvoiceId != null && !"".equals(dbInvoiceId.trim())){
							dbTransIdNext = updateInboundTransStatus(dbCon, transStatusDTO,senderSAN, receiverSAN, 
									dbInvoiceId,xmlInvoiceNo,IPixB2BConstants.INVOICE_ID,IPixB2BConstants.INVOICE_NUMBER ,xmlInvoiceNo,xmlInvoiceDate, invoiceTransID);
							B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - dbTransIdNext = "+dbTransIdNext);
							
							String transID = Long.toString(dbTransIdNext);
							
							transStatusDTO.setTransID(transID);
							
							if(dbTransIdNext > 0){
								dbCon.setAutoCommit(false);
								
								sqlQuery = qry_ins_invoice_header;
								prepStmt2 = dbCon.prepareStatement(sqlQuery);
								prepStmt2.clearParameters();
								prepStmt2.setString(IPixB2BConstants.ONE, dbInvoiceId);
								prepStmt2.setString(IPixB2BConstants.TWO, xmlInvoiceNo);
								prepStmt2.setString(IPixB2BConstants.THREE, xmlInvoiceDate);
								prepStmt2.setLong(IPixB2BConstants.FOUR, dbTransIdNext);
								prepStmt2.setString(IPixB2BConstants.FIVE, xmlTotalQty);
								prepStmt2.setString(IPixB2BConstants.SIX, xmlTotalAmt);
								prepStmt2.setString(IPixB2BConstants.SEVEN, senderSAN);
								prepStmt2.setString(IPixB2BConstants.EIGHT, remitSAN);
								prepStmt2.setString(IPixB2BConstants.NINE, IPixB2BConstants.block_N);
								prepStmt2.setString(IPixB2BConstants.TEN, xmlHeaderComments);
								qryParams = dbInvoiceId+","+xmlInvoiceNo+","+xmlInvoiceDate+","+dbTransIdNext
								+xmlTotalQty+","+xmlTotalAmt+","+senderSAN+","+remitSAN;
								recsNum = prepStmt2.executeUpdate();							
								recsCount = recsCount + recsNum;
								DBUtils.close(prepStmt2);
								
								xmlInvoiceDetailList = pneDTO.getPayload().getBusinessDocument().getInvoDTO().getInvoiceLineItem();			
								if(xmlInvoiceDetailList != null && xmlInvoiceDetailList.size() > 0){
									
									xmlPORef = new POReference();
									poInfoList = new ArrayList();
									
									for(int i= 0; i<xmlInvoiceDetailList.size(); i++){
										xmlInvoiceDetail = (InvoiceLineItem) xmlInvoiceDetailList.get(i);
										if(xmlInvoiceDetail != null){	
											daoErrorDTO = new ErrorDTO();
											xmlLineComments = "";
											xmlISBN10 = null;
											xmlPrintNo = null;
											
											xmlPONumber = xmlInvoiceDetail.getPoInfo().getPoNumber();
											xmlPOLineNo = xmlInvoiceDetail.getPoLineNo();
											xmlInvLineNo = xmlInvoiceDetail.getInvoiceLineNo();
											
											//Read LineComments
											xmlLineNotesList = xmlInvoiceDetail.getLineComments();
											if(xmlLineNotesList != null && xmlLineNotesList.size() > 0){
												for(int j=0; j<xmlLineNotesList.size(); j++){
													additionalTextLine = (String)xmlLineNotesList.get(j);
													xmlLineComments = xmlLineComments +(j == 0 ? "" : " ")+ additionalTextLine;
												}
											}			
											
											poInfoList = xmlInvoiceDetail.getPoInfo().getPoReference();
											if(poInfoList != null && poInfoList.size() > 0){
												for(int k= 0; k<poInfoList.size(); k++){
													xmlPORef = (POReference)poInfoList.get(k);														
													if(xmlPORef != null && xmlPORef.getPoReferenceType()!= null && IPixB2BConstants.ISBN10.equalsIgnoreCase(xmlPORef.getPoReferenceType().trim())){
														xmlISBN10 = xmlPORef.getPoReferenceValue();
													}else if(xmlPORef != null && xmlPORef.getPoReferenceType() != null && IPixB2BConstants.PrintingNumber.equalsIgnoreCase(xmlPORef.getPoReferenceType())){
														xmlPrintNo = xmlPORef.getPoReferenceValue();
													}
												}
											}
											if(xmlPONumber != null && !"".equals(xmlPONumber.trim())){										
												sqlQuery = qry_sel_poID_poVersion ;
												prepStmt3 = dbCon.prepareStatement(sqlQuery);
												prepStmt3.clearParameters();
												prepStmt3.setString(IPixB2BConstants.ONE, xmlPONumber);
												qryParams = xmlPONumber;
												resultSet3 = prepStmt3.executeQuery();
												while(resultSet3.next()){
													dbPOId = resultSet3.getString("PO_ID");
													dbPOVersion = resultSet3.getString("PO_VERSION");
													B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - dbPOId = " + dbPOId + "dbPOVersion = " + dbPOVersion);
												}
												DBUtils.close(prepStmt3);
												DBUtils.close(resultSet3);	
												
												sqlQuery = qry_sel_valid_invoice;
												prepStmt4 = dbCon.prepareStatement(sqlQuery);
												prepStmt4.clearParameters();
												prepStmt4.setString(IPixB2BConstants.ONE, dbPOId);
												prepStmt4.setString(IPixB2BConstants.TWO, xmlISBN10);
												prepStmt4.setString(IPixB2BConstants.THREE, xmlPrintNo);
												resultSet4 = prepStmt4.executeQuery();												
												if(resultSet4.next()&& resultSet4.getLong("record_count") > 0){
													chargeInfo = new ChargeInfo();
													chrgAmnt = new ChargeAmount(); 													
													priceDtls = new PriceDetails();	
													glAccount = new GLAccount();
													
													chargeInfoList = xmlInvoiceDetail.getChargeInfo();
													if(null != chargeInfoList  && chargeInfoList.size() > 0){
														for(int j=0; j<chargeInfoList.size(); j++){														
															chargeInfo = (ChargeInfo) chargeInfoList.get(j);
															if(chargeInfo != null){																
																xmlCostDesc = null;															
																xmlReqQty = null;
																xmlQtyUOM = null;
//																xmlCurrencyType = null;
//																xmlChrgAmt = null;
																//xmlGLType = null;
																//dbGLCode = null;
																
																xmlCostDesc = chargeInfo.getCostComp().getCostComponentDesc();
																xmlQtyUOM = chargeInfo.getQtyInfo().getQty().getValueQty().getQtyUOM();
																xmlReqQty = chargeInfo.getQtyInfo().getQty().getValueQty().getValValue(); 															
																
																chrgAmnt = chargeInfo.getChrgAmnt();
																if(chrgAmnt != null){
																	xmlChrgAmt = chrgAmnt.getCurrency().getCurrValue();
																	xmlCurrencyType = chrgAmnt.getCurrency().getCurrType();
																}else{
																	B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - ChargeAmount is null");
																}
																
																
																priceDtls = chargeInfo.getPriceDetail();
																if(null!= priceDtls){
																	glAccount = priceDtls.getGlAccount();
																	if(null!= glAccount){
																		xmlGLType = glAccount.getGlAgency();
																		xmlGLCode = glAccount.getGlValue(); 
																	}else{
																		B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - GL Account is null");
																	}																
																}else{
																	B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - PriceDetail is null");
																}						
																
																if(null!= xmlGLCode && !"".equals(xmlGLCode.trim())){
																	if(xmlGLType != null && xmlGLType.equalsIgnoreCase(IPixB2BConstants.BUYER)){																	
																		dbGLCode = xmlGLCode;
																	}else if(xmlGLType != null && xmlGLType.equalsIgnoreCase(IPixB2BConstants.BISAC)){																																
																		continue;
																		/*sqlQuery = qry_sel_gl_code;
																		 prepStmt5 = dbCon.prepareStatement(sqlQuery);
																		 prepStmt5.clearParameters();
																		 prepStmt5.setString(ONE, xmlGLCode);													
																		 resultSet5 = prepStmt5.executeQuery();	
																		 while(resultSet5.next()){
																		 dbGLCode = resultSet5.getString("GL_CODE");	
																		 }*/
																		//the above code is commented after discussion with Manish
																		//Bisac code will not be considered for the time being
																		//has to be cleared functionally
																	}
																	
																	sqlQuery = qry_validate_glCode;
																	prepStmt6 = dbCon.prepareStatement(sqlQuery);
																	prepStmt6.clearParameters();
																	prepStmt6.setString(IPixB2BConstants.ONE, dbPOId);	
																	prepStmt6.setString(IPixB2BConstants.TWO, dbPOVersion);	
																	prepStmt6.setString(IPixB2BConstants.THREE, xmlPOLineNo);	
																	prepStmt6.setString(IPixB2BConstants.FOUR, dbGLCode);													
																	resultSet6 = prepStmt6.executeQuery();	
																	if(resultSet6.next()&& resultSet6.getLong("count") > 0){		
																		
																		if(xmlReqQty != null  && !"".equals(xmlReqQty.trim())){													
																			
																			/**sqlQuery = qry_validate_requested_qty;
																			 prepStmt7 = dbCon.prepareStatement(sqlQuery);
																			 prepStmt7.clearParameters();
																			 prepStmt7.setString(ONE, dbPOId);	
																			 prepStmt7.setString(TWO, dbPOVersion);	
																			 prepStmt7.setString(THREE, xmlPOLineNo);	
																			 prepStmt7.setString(FOUR, senderSAN);
																			 prepStmt7.setString(FIVE, xmlReqQty);
																			 resultSet7 = prepStmt7.executeQuery();
																			 if(resultSet7.next()&& resultSet7.getLong("record_count") > 0){
																			 blockFlag = block_Y; */
																			
																			sqlQuery = qry_ins_invoice_line ;
																			prepStmt8 = dbCon.prepareStatement(sqlQuery);
																			prepStmt8.clearParameters();
																			prepStmt8.setString(IPixB2BConstants.ONE, dbInvoiceId);	
																			prepStmt8.setString(IPixB2BConstants.TWO, dbPOId);	
																			prepStmt8.setString(IPixB2BConstants.THREE, dbPOVersion);	
																			prepStmt8.setString(IPixB2BConstants.FOUR, xmlPOLineNo);
																			prepStmt8.setString(IPixB2BConstants.FIVE, xmlInvLineNo);	
																			prepStmt8.setString(IPixB2BConstants.SIX, xmlReqQty);
																			prepStmt8.setString(IPixB2BConstants.SEVEN,xmlQtyUOM);
																			prepStmt8.setString(IPixB2BConstants.EIGHT,xmlCostDesc);
																			prepStmt8.setString(IPixB2BConstants.NINE,dbGLCode);
																			prepStmt8.setString(IPixB2BConstants.TEN,xmlChrgAmt);
																			prepStmt8.setString(IPixB2BConstants.ELEVEN,xmlCurrencyType);
																			prepStmt8.setString(IPixB2BConstants.TWELVE,xmlLineComments);																			
																			qryParams = dbInvoiceId+","+dbPOId+","+dbPOVersion+","+xmlPOLineNo+","+xmlInvLineNo
																			+xmlReqQty+","+xmlQtyUOM+","+xmlCostDesc+","+dbGLCode+","+xmlChrgAmt+","+xmlCurrencyType+","+xmlLineComments;
																			recsNum = prepStmt8.executeUpdate();							
																			recsCount = recsCount + recsNum;																	
																			DBUtils.close(prepStmt8);
																			
																			dbCon.commit();
																			
																			//Call db Function 
																			cstmt = dbCon.prepareCall(func_validate_reqQty);																			
																			cstmt.registerOutParameter(1, Types.BOOLEAN);
																			//cstmt.setString(2, "hello.......");
																			cstmt.executeUpdate();
																			flagValue = cstmt.getString(1);
																			System.out.println("FLAG VALUE = "  + flagValue);
																			if(IPixB2BConstants.block_S.equals(flagValue)){
																				blockFlag = IPixB2BConstants.block_Y;
																				errorId = IPixB2BConstants.ERROR_ID_109;
																				daoErrorDTO.setErrorID(errorId+"");
																				daoErrorDTO.setErrorDescription(IPixB2BConstants.ERROR_DESC_109.concat(" For PO Number = ").concat(xmlPONumber));
																				errorList.add(daoErrorDTO);
																				B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - Requested Quantity is invalid");
																			}
																			
																			/**}else{
																			 B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - Requested Quantity is invalid");
																			 }	*/												
																		}else{
																			blockFlag = IPixB2BConstants.block_Y;   
																			B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - Requested Quantity is null" + xmlReqQty);
																		}	
																	}else{
																		blockFlag = IPixB2BConstants.block_Y;   
																		//error: Extra GL Code.. not to be processed
																		errorId = IPixB2BConstants.ERROR_ID_96;
																		daoErrorDTO.setErrorID(errorId+"");
																		daoErrorDTO.setErrorDescription(IPixB2BConstants.ERROR_DESC_96.concat(" For PO Number = ").concat(xmlPONumber));
																		errorList.add(daoErrorDTO);
																		B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - GL Code"+ dbGLCode + " does not exist in PIX_PRICE_DETAIL");
																	}						
																	DBUtils.close(prepStmt6);
																	DBUtils.close(resultSet6);	
																}else{
																	errorId = IPixB2BConstants.ERROR_ID_99;
																	daoErrorDTO.setErrorID(errorId+"");
																	daoErrorDTO.setErrorDescription(IPixB2BConstants.ERROR_DESC_99.concat(" For PO Number = ").concat(xmlPONumber));
																	errorList.add(daoErrorDTO);
																	B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - GL Code is null");
																}
																
																
																
															}else{
																B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - ChargeInfo is null");
															}	
														}
													}else{
														B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - ChargeInfoList is null");
													}	
												}else{
													blockFlag = IPixB2BConstants.block_Y;												
													errorId = IPixB2BConstants.ERROR_ID_95;
													daoErrorDTO.setErrorID(errorId+"");
													daoErrorDTO.setErrorDescription(IPixB2BConstants.ERROR_DESC_95.concat(" For PO Number = ").concat(xmlPONumber));
													errorList.add(daoErrorDTO);												
													B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails()- Invalid ISBN,Print_No or PO Cancelled");
												}											
												DBUtils.close(prepStmt4);
												DBUtils.close(resultSet4);	
											}else{
												errorId = IPixB2BConstants.ERROR_ID_74;
												daoErrorDTO.setErrorID(errorId+"");
												daoErrorDTO.setErrorDescription(IPixB2BConstants.ERROR_DESC_74);
												errorList.add(daoErrorDTO);				
												B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - xmlPONumber is null or blank");
											}								
											
										}else{
											B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - xmlInvoiceLineItem is null");
										}
									} //end for loop - xmlInvoiceDetailList
									
								}else{
									B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - xmlInvoiceDetailList is null or blank");
								}
								
								if(null!=errorList && errorList.size()>0){
									blockFlag = IPixB2BConstants.block_Y;
								}
								
								//Update Invoice Hdr table again  
								if(IPixB2BConstants.block_Y.equals(blockFlag)){
									sqlQuery = qry_updt_invoice_header ;
									prepStmt9 = dbCon.prepareStatement(sqlQuery);
									prepStmt9.clearParameters();
									prepStmt9.setString(IPixB2BConstants.ONE, dbInvoiceId);	
									qryParams = dbInvoiceId;
									recsNum = prepStmt9.executeUpdate();							
									recsCount = recsCount + recsNum;
									DBUtils.close(prepStmt9);
								}					
								
								dbCon.commit();
							} //dbTransId end if
						}else{
							B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - dbInvoiceId is null " + dbInvoiceId );
						}						
					}else{
						B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - senderSAN = "+senderSAN+" , receiverSAN = "+receiverSAN);
					}
				}else{
					B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - transmissionInfoList is null or blank");
				}
			}else{
				B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - pneDTO is null");
			}
			
			B2BLogger.info("InvoiceDAOImpl.storeInvoiceDetails() - recsCount = "+recsCount+", dbTransIdNext = "+dbTransIdNext);
			if(recsCount > 0)
				invoiceStatus = IPixB2BConstants.flag_Y;					
			B2BLogger.debug("******* InvoiceDAOImpl.storeInvoiceDetails() method EXIT *******");
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			invoiceStatus = StringUtils.getStackTrace(e);
			if(invoiceStatus != null && invoiceStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				invoiceStatus = invoiceStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}			
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			invoiceStatus = StringUtils.getStackTrace(e);
			if(invoiceStatus != null && invoiceStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				invoiceStatus = invoiceStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}
		} finally{			
			DBUtils.close(prepStmt1);
			DBUtils.close(resultSet1);			
			DBUtils.close(prepStmt2);			
			DBUtils.close(prepStmt3);
			DBUtils.close(resultSet3);
			DBUtils.close(prepStmt4);
			DBUtils.close(resultSet4);
			DBUtils.close(prepStmt5);
			DBUtils.close(resultSet5);
			DBUtils.close(prepStmt6);
			DBUtils.close(resultSet6);
			DBUtils.close(prepStmt7);
			DBUtils.close(resultSet7);
			DBUtils.close(prepStmt8);
			DBUtils.close(prepStmt9);
			DBUtils.close(cstmt);
			DBUtils.close(dbCon);
			
			sqlQuery		= null;
			qryParams		= null;
			transmissionInfoList = null;
			b2bHelper		= null;
			hmSAN			= null;		
			senderSAN		= null;		
			receiverSAN		= null;
			remitSAN        = null;
			daoHelper		= null;
			xmlInvLineNo    = null;
			xmlPONumber	  	= null;
			xmlPORefList	= null;			
			xmlPOTransId	= null;
			dbPOId			= null;
			dbPOVersion		= null;
			dbInvoiceId		= null;			
			hmPoIdpoVersion	= null;
			dbInvoiceIdNext	= null;
			xmlInvoiceCode	= null;	
			xmlReqQty       = null;
			xmlQtyUOM 		= null;
			xmlCostDesc 	= null;
			xmlChrgAmt      = null;
			xmlCurrencyType	= null;
			xmlRemitSAN		= null;
			xmlSupplierSAN  = null;
			xmlGLType      = null;
			xmlHeaderComments = null;
			xmlLineComments  = null;
		}
		return invoiceStatus;
	}
}
