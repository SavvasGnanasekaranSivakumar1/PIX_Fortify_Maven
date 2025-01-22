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
 * Title		: 	GoodsRecptDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Abhilasha Nigam  14 Jan, 2010	Initial version
 *
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.pearson.pixb2b.vendor.transaction.inbound.common.transstatus.dao.InboundTransStatusDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.BuyerContact;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.BuyerNameAddress;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.BuyerParty;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.GRArrivalDate;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.GRHeader;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.GRIssueDate;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.GRLineItem;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.GRRef;
import com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto.LineQuantity;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.error.ErrorDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.transaction.shared.helper.B2BHelper;
import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.DBUtils;
import com.pearson.pixb2b.vendor.utils.IConfigConstants;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * GoodsRecptDAOImpl is a class to communicate with the PIX database
 * and process the B2B Usage transaction data.
 * 
 * @author Abhilasha Nigam
 */
public class GoodsRecptDAOImpl extends InboundTransStatusDAOImpl implements IGdsRecptDAO {
	
	private static final String qry_sel_msg_id = "SELECT msg_id,po_id,po_version FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_delivery_msg "
	+ "WHERE msg_no = ?"; 			
	
	
	private static final String qry_sel_release_no = "SELECT  release_no FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_po_list_summary "
	+ "WHERE po_id = ? AND po_version = ? " ;
	
	
	private static final String qry_sel_party_info = "SELECT count(*) records_count FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_po_party"
	+ " WHERE po_id = ? AND san = ? AND po_version = ? AND party_line_no = '3' " ;
	
	
	private static final String  qry_sel_party_contact_info = "SELECT count(*) records_count FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_po_party_contact"
	+ " WHERE po_id =? AND po_version = ? AND san=? AND party_line_no='3'";
	
	private static final String qry_sel_pix_recpt_id_nextval = "SELECT SEQ_PIX_GOOD_RCPT_ID.NEXTVAL v_recpt_id FROM DUAL";
	
	private static final String qry_sel_status_id = "SELECT status_id FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_status_code"
	+ " WHERE STATUS_DESCRIPTION = ? AND table_id ='27' ";
	
	private static final String qry_sel_ref_id = "SELECT ref_id FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_ref"
	+ " WHERE group_code='GR_ACCEPTANCE' AND description =? ";
	
	private static final String qry_sel_header_ref_id ="SELECT ref_id FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_ref"
	+ " WHERE group_code='GR_ACCEPTANCE' AND description =? ";
	
	private static final String qry_ins_pix_good_receipt = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_GOOD_RECEIPT "
	+ "(RECEIPT_ID,RECEIPT_NO,MSG_ID,PO_ID,PO_VERSION,ACCEPTANCE_ID,TRANSACTION_HISTORY_NO,HEADER_ACCEPTANCE_ID,"
	+ "STATUS_ID,CREATED_BY,MODIFIED_BY,MOD_DATE_TIME,CREATION_DATE_TIME,VENDOR_RECEIPT_NO)"
	+ "VALUES(?,?,?,?,?,?,?,?,?,'JAVAB2B','JAVAB2B',SYSDATE,SYSDATE,?)";
	
	
	private static final String qry_sel_uom_id = "SELECT uom_id FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_uom_code "
	+ " WHERE  xuom = ? ";
	
	private static final String qry_sel_msg_id_line = "SELECT msg_id FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_delivery_msg "
	+ "WHERE msg_no = ?"; 
	
	private static final String qry_validate_DMLineNo = "SELECT count(1) count FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_DELIVERY_MSG a,"
	+ "pix_delivery_msg_line b WHERE b.MSG_LINE_NO = ? AND a.MSG_ID = ? AND a.MSG_ID = b.MSG_ID";
	
	
	private static final String qry_sel_msg_lineNo = "SELECT msg_line_no,po_line_no,po_id,po_version FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_delivery_msg_line"
	+ " WHERE msg_id=? and msg_line_no=?";
	
	
	private static final String qry_sel_req_arrv_date = "SELECT TO_CHAR(REQUESTED_DELIVERY_DATE,'MM/DD/YYYY')REQUESTED_DELIVERY_DATE FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_po_line "
	+" WHERE po_id = ? and po_version = ? and po_line_no =?" ;
	
	private static final String qry_sel_lineRef_id = "SELECT ref_id FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_ref"
	+ " WHERE group_code='GR_ACCEPTANCE' AND description =? ";
	
	private static final String qry_ins_pix_receipt_line = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_GOOD_RECEIPT_LINE "
	+ "(RECEIPT_ID,RECEIPT_LINE_NO,MSG_ID,MSG_LINE_NO,PO_ID,PO_VERSION,PO_LINE_NO,ACCEPTANCE_ID,"
	+ "ACTUAL_ARRIVAL_DATE,REQUESTED_ARRIVAL_DATE,RECEIVED_QUANTITY,BALANCE_QUANTITY,UOM_ID,"
	+ "CREATED_BY,MODIFIED_BY,MOD_DATE_TIME,CREATION_DATE_TIME)"
	+ " VALUES(SEQ_PIX_GOOD_RCPT_ID.CURRVAL,?,?,?,?,?,?,?,to_date(?,'MM/DD/YYYY'),to_date(?,'MM/DD/YYYY'),?,'0',?,'JAVAB2B','JAVAB2B',SYSDATE,SYSDATE)";
	
	
	public String processGRDetails(PapiNetEnvelopeDTO pneDTO, TransactionStatusDTO transStatusDTO,ArrayList errorList){
		
		Connection dbCon 			= null;
		String sqlQuery				= null;
		String qryParams			= null;
		int recsCount				= 0;
		int recsNum					= 0;
		
		PreparedStatement prepStmt1	= null;
		ResultSet resultSet1		= null;
		PreparedStatement prepStmt2	= null;
		ResultSet resultSet2		= null;		
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
		PreparedStatement prepStmt8	= null;
		PreparedStatement prepStmt9	= null;
		ResultSet resultSet9		= null;
		PreparedStatement prepStmt10= null;
		ResultSet resultSet10	= null;
		PreparedStatement prepStmt11= null;
		ResultSet resultSet11	= null;
		PreparedStatement prepStmt12= null;
		ResultSet resultSet12	= null;
		PreparedStatement prepStmt13= null;
		ResultSet resultSet13	= null;
		PreparedStatement prepStmt14= null;
		
		GRHeader grHdr = null;
		GRLineItem  grLineItem = null;
		ArrayList transmissionInfoList = null;
		B2BHelper b2bHelper			= null;
		HashMap hmSAN				= null;		
		String senderSAN			= null;		
		String receiverSAN			= null;
		String dbMsgId              = null;
		String dbPOId				= null;
		String dbPOVersion			= null;
		String lineMsgId            = null;
		long dbTransIdNext 			= -1;
		String goodsReceiptStatus 	= IPixB2BConstants.flag_N;
		boolean flag_party = false;
		boolean flag_party_contact = false;			
		ArrayList grLineItemList  = null;
		GRRef xmlGrRef = null;
		GRIssueDate grIssueDate = null;
		GRArrivalDate grArrivalDate = null;
		BuyerParty xmlBuyerParty = null;
		BuyerNameAddress xmlBuyerInfo = null;
		BuyerContact buyerContact = null;
		ArrayList grRefList = null;		
		String xmlGrTransID = null;
		String xmlGRNo   = null;		
		String xmlGrStatus = null;		
		String xmlBuyerSAN = null;		
		String xmlLineQty = null;		
		LineQuantity lineQty = null;		
		String lineItemNo = null;		
		String xmlIssueDate = null;	
		String xmlArrivalDate = null;
		String reqArrivalDate = null;
		String uomId = null;
		String uomDesc = null;
		String recvdQty = null;
		String qtyValue = null;
		String qtyUOM = null;
		String buyerName1 = null;
		String buyerName2 = null;
		String buyerName3 = null;
		String buyerOrgCode = null;
		String buyerOrgName = null;
		String buyerAddr1 = null;
		String buyerAddr2 = null;
		String buyerAddr3 = null;
		String buyerAddr4= null;
		String buyerCity = null;
		String buyerState = null;
		String buyerPCode = null;
		String buyerComments = "";
		String buyerConType = null;
		String buyerConName = null;
		String buyerConPhone = null;
		String buyerConMobile = null;
		String buyerConFax = null;
		String buyerConEmail = null;
		String xmlLineItemNo = null;
		String xmlHdrDMNo = null;
		String xmlLineDMNo = null;
		String releaseNo = null;
		String shipToSAN = null;
		String xmlHdrAcceptance = null;
		String xmlGrAcceptance = null;
		String refId = null;
		String hdrRefId = null;
		String lineRefId = null;
		String recptId = null;
		String recptNo = null;
		String goodrecpt = "GR";
		String xmlQtyContext = null;
		String xmlPONo = null;
		String xmlLineAcceptance = null;
		String xmlUOM = null;
		String statusId = null;
		String msgLineNo = null;
		String poLineNo = null;
		String poId = null;
		String poVersion = null;
		String grLineNo = null;
		String reqArrvDate = null;
		String arrivalDate = null;
		int errorId				= -1;
		ErrorDTO daoErrorDTO    = null;
		
		try {
			B2BLogger.debug("******* GoodsRecptDAOImpl.processGRDetails() method ENTERED *******");
			
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}
			
			if(pneDTO != null){
				transmissionInfoList = pneDTO.getPayloadInfo().getTransmissionInfoList();
				if(transmissionInfoList != null && transmissionInfoList.size()>0){
					b2bHelper = new B2BHelper();					
					senderSAN = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.VENDOR_SAN, IPixB2BConstants.transType_GRE);					
					hmSAN = b2bHelper.getSendorSANReceiverSAN(transmissionInfoList);
					if(hmSAN != null && hmSAN.size()>0){
						//senderSAN = (String)hmSAN.get("senderSAN");						
						receiverSAN = (String)hmSAN.get("receiverSAN");
						
					}
					if(senderSAN != null && !"".equals(senderSAN.trim()) && receiverSAN != null && !"".equals(receiverSAN.trim())){
						senderSAN = b2bHelper.addDashesInSAN(senderSAN);
						receiverSAN = b2bHelper.addDashesInSAN(receiverSAN);						
						
						xmlGrStatus = pneDTO.getPayload().getBusinessDocument().getGrDTO().getGrStatusType(); //status 					
						xmlGrAcceptance = pneDTO.getPayload().getBusinessDocument().getGrDTO().getGrAcceptance();
						grHdr = pneDTO.getPayload().getBusinessDocument().getGrDTO().getGrHeader();						
						if(grHdr != null){							
							xmlGRNo = grHdr.getGrNumber();   //GoodsReceiptNumber							
							xmlHdrAcceptance = grHdr.getGrAcceptType();
							grIssueDate = grHdr.getGrIssueDate(); //xmlIssueDate
							if(grIssueDate != null){	
								xmlIssueDate = grIssueDate.getGrIDate().getMonth()
								+ "/"+grIssueDate.getGrIDate().getDay() 
								+ "/"+grIssueDate.getGrIDate().getYear();										
							}								
							B2BLogger.info("GoodsRecptDAOImpl.processGRDetails() - GRIssueDate = "+ xmlIssueDate);
							
							
							grArrivalDate = grHdr.getGrArrvDate() ; //xmlArrivalDate
							if(grArrivalDate != null){	
								xmlArrivalDate = grArrivalDate.getGrADate().getMonth()
								+ "/"+grArrivalDate.getGrADate().getDay() 
								+ "/"+grArrivalDate.getGrADate().getYear();										
							}								
							B2BLogger.info("GoodsRecptDAOImpl.processGRDetails() - GRArrivalDate = "+ xmlArrivalDate);
							
							//Read GR Transaction ID
							grRefList = grHdr.getGrRef();
							if(grRefList != null && grRefList.size()>0){
								for(int j=0;j<grRefList.size();j++){
									xmlGrRef = (GRRef)grRefList.get(j);
									if(xmlGrRef != null && xmlGrRef.getGrRefType()!= null ){
										if(IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlGrRef.getGrRefType().trim()))
											xmlGrTransID = xmlGrRef.getGrRefValue();
									}
								}		
							}
							
							
							//Read Delivery Msg No at Header
							xmlHdrDMNo = grHdr.getDelMsgNo();														
							xmlBuyerSAN = grHdr.getBuyerParty().getPartyIdentifier().getPartyIdentifierValue();							
							xmlBuyerParty = grHdr.getBuyerParty();
							if(xmlBuyerParty != null){
								xmlBuyerInfo = xmlBuyerParty.getNameAddress();								
								if(xmlBuyerInfo != null){
									buyerName1 = xmlBuyerInfo.getName1();									
									buyerName2 = xmlBuyerInfo.getName2();
									buyerName3 = xmlBuyerInfo.getName3();
									if(xmlBuyerInfo.getBuyerOrgUnit() != null){
										buyerOrgCode = xmlBuyerInfo.getBuyerOrgUnit().getOrgUnitCode();
										buyerOrgName = xmlBuyerInfo.getBuyerOrgUnit().getOrgUnitName();
									}
									buyerAddr1 = xmlBuyerInfo.getAddress1();
									buyerAddr2 = xmlBuyerInfo.getAddress2();
									buyerAddr3 = xmlBuyerInfo.getAddress3();
									buyerAddr4 = xmlBuyerInfo.getAddress4();
									buyerCity = xmlBuyerInfo.getCity();
									buyerState = xmlBuyerInfo.getStateOrProvince();
									buyerPCode = xmlBuyerInfo.getPostalCode();
								}
								
								buyerContact = grHdr.getBuyerParty().getContactInfo();
								if(buyerContact != null){
									buyerConType = buyerContact.getContactType();
									buyerConName = buyerContact.getContactFirstName();
									buyerConPhone = buyerContact.getPhone();
									buyerConMobile = buyerContact.getMobile();
									buyerConFax = buyerContact.getFax();
									buyerConEmail = buyerContact.getEmail();
								}
							}
							
							shipToSAN = grHdr.getShipToChar().getShipToParty().getPartyId().getPartyIdentifierValue();	
							
						}	
						
						//Read Linelevel Info
						grLineItemList = pneDTO.getPayload().getBusinessDocument().getGrDTO().getGrLineItem();
						if(grLineItemList != null && grLineItemList.size()>0){
							for(int k=0;k<grLineItemList.size();k++){
								grLineItem = (GRLineItem)grLineItemList.get(k);
								if(grLineItem != null){	
									xmlPONo = grLineItem.getPoInfo().getPoNumber();									
								}		
							}
						}
						
						if(xmlHdrDMNo != null && !"".equals(xmlHdrDMNo.trim())){					
							dbCon.setAutoCommit(false);		
							
							sqlQuery = qry_sel_msg_id;
							prepStmt1 = dbCon.prepareStatement(sqlQuery);
							prepStmt1.clearParameters();	
							prepStmt1.setString(IPixB2BConstants.ONE,xmlHdrDMNo);
							qryParams = xmlHdrDMNo;
							resultSet1 = prepStmt1.executeQuery();
							while(resultSet1.next()){
								dbMsgId = resultSet1.getString("msg_id");
								dbPOId = resultSet1.getString("po_id");
								dbPOVersion = resultSet1.getString("po_version");
							}						
							DBUtils.close(prepStmt1);
							DBUtils.close(resultSet1);	
							
							sqlQuery = qry_sel_release_no ;
							prepStmt2 = dbCon.prepareStatement(sqlQuery);
							prepStmt2.clearParameters();
							prepStmt2.setString(IPixB2BConstants.ONE,dbPOId);
							prepStmt2.setString(IPixB2BConstants.TWO,dbPOVersion);	
							qryParams = dbPOId+","+dbPOVersion ;
							resultSet2 = prepStmt2.executeQuery();
							while(resultSet2.next()){
								releaseNo = resultSet2.getString("release_no");
							}
							DBUtils.close(prepStmt2);
							DBUtils.close(resultSet2);
							
							sqlQuery = qry_sel_party_info;
							prepStmt3 = dbCon.prepareStatement(sqlQuery);
							prepStmt3.clearParameters();
							prepStmt3.setString(IPixB2BConstants.ONE,dbPOId);
							prepStmt3.setString(IPixB2BConstants.TWO,shipToSAN);
							prepStmt3.setString(IPixB2BConstants.THREE,dbPOVersion);
							qryParams = dbPOId+","+shipToSAN+","+dbPOVersion;
							resultSet3 = prepStmt3.executeQuery();
							if(resultSet3.next() && resultSet3.getLong("records_count") == 0){
								flag_party = true;
							}
							DBUtils.close(prepStmt3);
							DBUtils.close(resultSet3);	
							
							sqlQuery = qry_sel_party_contact_info;
							prepStmt4 = dbCon.prepareStatement(sqlQuery);
							prepStmt4.clearParameters();
							prepStmt4.setString(IPixB2BConstants.ONE,dbPOId);
							prepStmt4.setString(IPixB2BConstants.TWO,dbPOVersion);
							prepStmt4.setString(IPixB2BConstants.THREE,shipToSAN);
							qryParams = dbPOId+","+dbPOVersion+","+shipToSAN;
							resultSet4 = prepStmt4.executeQuery();
							if(resultSet4.next() && resultSet4.getLong("records_count") == 0){
								flag_party_contact = true;
							}
							DBUtils.close(prepStmt4);
							DBUtils.close(resultSet4);	
							
							if (flag_party == true && flag_party_contact == true){  //process xml								
								
								sqlQuery = qry_sel_status_id;
								prepStmt5 = dbCon.prepareStatement(sqlQuery);
								prepStmt5.clearParameters();	
								prepStmt5.setString(IPixB2BConstants.ONE,xmlGrStatus);
								qryParams = xmlGrStatus;
								resultSet5 = prepStmt5.executeQuery();
								while(resultSet5.next()){
									statusId = resultSet5.getString("status_id");
								}								
								DBUtils.close(prepStmt5);
								DBUtils.close(resultSet5);	
								
								sqlQuery = qry_sel_ref_id;
								prepStmt6 = dbCon.prepareStatement(sqlQuery);
								prepStmt6.clearParameters();	
								prepStmt6.setString(IPixB2BConstants.ONE,xmlGrAcceptance);
								qryParams = xmlGrAcceptance;
								resultSet6 = prepStmt6.executeQuery();
								while(resultSet6.next()){
									refId = resultSet6.getString("ref_id");
								}
								DBUtils.close(prepStmt6);
								DBUtils.close(resultSet6);	
								
								sqlQuery = qry_sel_header_ref_id;
								prepStmt6 = dbCon.prepareStatement(sqlQuery);
								prepStmt6.clearParameters();	
								prepStmt6.setString(IPixB2BConstants.ONE,xmlHdrAcceptance);
								qryParams = xmlHdrAcceptance;
								resultSet6 = prepStmt6.executeQuery();
								while(resultSet6.next()){
									hdrRefId = resultSet6.getString("ref_id");
								}
								DBUtils.close(prepStmt6);
								DBUtils.close(resultSet6);	
								
								sqlQuery = qry_sel_pix_recpt_id_nextval;
								prepStmt7 = dbCon.prepareStatement(sqlQuery);
								prepStmt7.clearParameters();	
								resultSet7 = prepStmt7.executeQuery();
								while(resultSet7.next()){
									recptId = resultSet7.getString("v_recpt_id");
								}
								DBUtils.close(prepStmt7);
								DBUtils.close(resultSet7);	
								
								if(recptId != null || !"".equals(recptId.trim())){
									recptNo = goodrecpt+"_"+xmlPONo+"_"+releaseNo+"_"+recptId;										
								}
								
								
								sqlQuery = qry_ins_pix_good_receipt;
								prepStmt8 = dbCon.prepareStatement(sqlQuery);
								prepStmt8.clearParameters();
								prepStmt8.setString(IPixB2BConstants.ONE,recptId);
								prepStmt8.setString(IPixB2BConstants.TWO,recptNo);
								prepStmt8.setString(IPixB2BConstants.THREE,dbMsgId);
								prepStmt8.setString(IPixB2BConstants.FOUR,dbPOId);
								prepStmt8.setString(IPixB2BConstants.FIVE,dbPOVersion);
								prepStmt8.setString(IPixB2BConstants.SIX,refId);
								prepStmt8.setString(IPixB2BConstants.SEVEN,xmlGrTransID);
								prepStmt8.setString(IPixB2BConstants.EIGHT,hdrRefId);
								prepStmt8.setString(IPixB2BConstants.NINE,statusId);
								prepStmt8.setString(IPixB2BConstants.TEN,xmlGRNo);								
								qryParams = recptId+","+recptNo+","+dbMsgId+","+dbPOId+","+dbPOVersion+","+refId
								+","+xmlGrTransID+","+hdrRefId+","+statusId+","+xmlGRNo;
								recsNum = prepStmt8.executeUpdate();
								recsCount = recsCount + recsNum;
								
								DBUtils.close(prepStmt8);
								//Process LinelevelQueries
								grLineItemList = pneDTO.getPayload().getBusinessDocument().getGrDTO().getGrLineItem();
								if(grLineItemList != null && grLineItemList.size()>0){
									for(int k=0;k<grLineItemList.size();k++){
										grLineItem = (GRLineItem)grLineItemList.get(k);
										if(grLineItem != null){	
											daoErrorDTO = new ErrorDTO();
											xmlQtyContext = null;
											xmlUOM = null;
											recvdQty= null;
											uomId = null;
											msgLineNo = null;
											lineRefId = null;
											xmlLineDMNo = null;
											
											grLineNo = grLineItem.getGrLineItemNo();																				
											
											xmlLineDMNo = grLineItem.getDmLineNo();											
											
											xmlLineAcceptance = grLineItem.getGrAcceptance();
											
											lineQty = grLineItem.getLineQty();
											if(lineQty != null){
												xmlQtyContext = lineQty.getQtyTypeContext();
												xmlUOM = lineQty.getGrQtyValue().getUOM();
												recvdQty = lineQty.getGrQtyValue().getQtyValue();												
											}							
											
											
											if(xmlLineDMNo != null){
												
												//validate DM LineItem Number	...Commenting code as validation query not confirmed											
												/**
												 sqlQuery = qry_validate_DMLineNo;
												 prepStmt10 = dbCon.prepareStatement(sqlQuery);
												 prepStmt10.clearParameters();	
												 prepStmt10.setString(ONE,xmlLineDMNo);
												 prepStmt10.setString(TWO,dbMsgId);		
												 qryParams = xmlLineDMNo+ ","+ dbMsgId;
												 resultSet10 = prepStmt10.executeQuery();
												 if(resultSet10.next()&& resultSet10.getLong("count") > 0){ */
												
												sqlQuery = qry_sel_msg_lineNo;
												prepStmt11 = dbCon.prepareStatement(sqlQuery);
												prepStmt11.clearParameters();	
												prepStmt11.setString(IPixB2BConstants.ONE,dbMsgId);	
												prepStmt11.setString(IPixB2BConstants.TWO,xmlLineDMNo);
												qryParams = dbMsgId + ","+ xmlLineDMNo;
												resultSet11 = prepStmt11.executeQuery();
												while(resultSet11.next()){
													msgLineNo = resultSet11.getString("msg_line_no");
													poLineNo=resultSet11.getString("po_line_no");
													poVersion=resultSet11.getString("po_version");
													poId = resultSet11.getString("po_id");
												}
												DBUtils.close(prepStmt11);
												DBUtils.close(resultSet11);	
												/**}else{
												 errorId = ERROR_ID_107;
												 daoErrorDTO.setErrorID(errorId+"");
												 daoErrorDTO.setErrorDescription(ERROR_DESC_107);
												 errorList.add(daoErrorDTO);		
												 B2BLogger.info("DM LineItem Number is invalid.... ");  
												 }*/
												
												
												sqlQuery = qry_sel_req_arrv_date;   
												prepStmt12 = dbCon.prepareStatement(sqlQuery);
												prepStmt12.clearParameters();	
												prepStmt12.setString(IPixB2BConstants.ONE,poId);
												prepStmt12.setString(IPixB2BConstants.TWO,poVersion);
												prepStmt12.setString(IPixB2BConstants.THREE,poLineNo);
												qryParams = poId+ ","+ poVersion+ ","+ poLineNo ;
												resultSet12 = prepStmt12.executeQuery();
												while(resultSet12.next()){
													reqArrivalDate = resultSet12.getString("REQUESTED_DELIVERY_DATE");
												}	
												DBUtils.close(prepStmt12);
												DBUtils.close(resultSet12);	
												
												sqlQuery = qry_sel_lineRef_id;
												prepStmt13 = dbCon.prepareStatement(sqlQuery);
												prepStmt13.clearParameters();	
												prepStmt13.setString(IPixB2BConstants.ONE,xmlLineAcceptance);
												qryParams = xmlLineAcceptance ;
												resultSet13 = prepStmt13.executeQuery();
												while(resultSet13.next()){
													lineRefId = resultSet13.getString("ref_id");
												}	
												DBUtils.close(prepStmt13);
												DBUtils.close(resultSet13);	
												
												if((xmlQtyContext != null && IPixB2BConstants.Delivered.equalsIgnoreCase(xmlQtyContext.trim())) 
														|| (xmlQtyContext == null && recvdQty != null)){
													
													sqlQuery = qry_sel_uom_id;
													prepStmt9 = dbCon.prepareStatement(sqlQuery);
													prepStmt9.clearParameters();	
													prepStmt9.setString(IPixB2BConstants.ONE,xmlUOM);
													qryParams = xmlUOM;
													resultSet9 = prepStmt9.executeQuery();
													while(resultSet9.next()){
														uomId = resultSet9.getString("uom_id");
													}												
													DBUtils.close(prepStmt9);
													DBUtils.close(resultSet9);	
													
													sqlQuery = qry_ins_pix_receipt_line;
													prepStmt14 = dbCon.prepareStatement(sqlQuery);
													prepStmt14.clearParameters();									
													prepStmt14.setString(IPixB2BConstants.ONE,grLineNo);
													prepStmt14.setString(IPixB2BConstants.TWO,dbMsgId);									
													prepStmt14.setString(IPixB2BConstants.THREE,msgLineNo);
													prepStmt14.setString(IPixB2BConstants.FOUR,poId);
													prepStmt14.setString(IPixB2BConstants.FIVE,poVersion);
													prepStmt14.setString(IPixB2BConstants.SIX,poLineNo);
													prepStmt14.setString(IPixB2BConstants.SEVEN,lineRefId);
													prepStmt14.setString(IPixB2BConstants.EIGHT,xmlArrivalDate); //actualArrvDate 4m GR xml
													prepStmt14.setString(IPixB2BConstants.NINE,reqArrivalDate); //reqArrvDate 4m PO
													prepStmt14.setString(IPixB2BConstants.TEN,recvdQty);
													prepStmt14.setString(IPixB2BConstants.ELEVEN,uomId);									
													qryParams = grLineNo+","+lineMsgId+","+msgLineNo+","+poId+","+poVersion
													+","+poLineNo+","+lineRefId+","+xmlArrivalDate+","+reqArrivalDate+","+recvdQty+","+uomId;
													recsNum = prepStmt14.executeUpdate();
													recsCount = recsCount + recsNum;
													
													DBUtils.close(prepStmt14);
													dbCon.commit();
													
												}else if(recvdQty == null || IPixB2BConstants.Balanced.equalsIgnoreCase(xmlQtyContext.trim())){
													errorId = IPixB2BConstants.ERROR_ID_109;
													daoErrorDTO.setErrorID(errorId+"");
													daoErrorDTO.setErrorDescription(IPixB2BConstants.ERROR_DESC_109);
													errorList.add(daoErrorDTO);		
													B2BLogger.info("Received Quantity is invalid... ");	//throw Invalid Quantity error
												}
											}else{
												errorId = IPixB2BConstants.ERROR_ID_106;
												daoErrorDTO.setErrorID(errorId+"");
												daoErrorDTO.setErrorDescription(IPixB2BConstants.ERROR_DESC_106);
												errorList.add(daoErrorDTO);		
												B2BLogger.info("DM LineItem Number is null.... ");  
											}
										}
									}								
								}//close LineItem
								//insert in transaction log table
								if(recsCount > 0 && dbPOId != null && dbPOId.length()>0 && recptId != null && recptId.length()>0){								
									dbTransIdNext = updateInboundTransStatus(dbCon,transStatusDTO,senderSAN,receiverSAN, 
											dbPOId,recptId,IPixB2BConstants.PO_ID,IPixB2BConstants.GOODSRECEIPT_ID,xmlGRNo,xmlIssueDate,xmlGrTransID);
								}	
							}else{
								B2BLogger.error("Transaction already processed error");							
							}					
							dbCon.commit();
						}else										
							B2BLogger.info("GoodsRecptDAOImpl.processGRDetails() - xmlDeliveryMsgNo is null or blank");				
						
					}else
						B2BLogger.info("GoodsRecptDAOImpl.processGRDetails() - senderSAN = "+senderSAN+" , receiverSAN = "+receiverSAN);
				} else
					B2BLogger.info("GoodsRecptDAOImpl.processGRDetails() - transmissionInfoList is null or blank");
				
			}else{
				B2BLogger.info("GoodsRecptDAOImpl.processGRDetails() - pneDTO is null");
			}			
			B2BLogger.info("GoodsRecptDAOImpl.processGRDetails() - recsCount = "+recsCount);
			if(dbTransIdNext > 0)
				goodsReceiptStatus = IPixB2BConstants.flag_Y;				
			B2BLogger.debug("******* GoodsRecptDAOImpl.processGRDetails() method EXIT *******");
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: ",e);
			goodsReceiptStatus = StringUtils.getStackTrace(e);
			if(goodsReceiptStatus != null && goodsReceiptStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				goodsReceiptStatus = goodsReceiptStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("B2BException :: ",e1);
			}			
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: ",e);
			goodsReceiptStatus = StringUtils.getStackTrace(e);
			if(goodsReceiptStatus != null && goodsReceiptStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				goodsReceiptStatus = goodsReceiptStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}
		} finally{				
			DBUtils.close(prepStmt1);
			DBUtils.close(resultSet1);	
			DBUtils.close(prepStmt2);
			DBUtils.close(resultSet2);	
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
			DBUtils.close(resultSet9);
			DBUtils.close(prepStmt10);
			DBUtils.close(resultSet10);	
			DBUtils.close(prepStmt11);
			DBUtils.close(resultSet11);	
			DBUtils.close(prepStmt12);
			DBUtils.close(resultSet12);	
			DBUtils.close(prepStmt13);
			DBUtils.close(resultSet13);	
			DBUtils.close(prepStmt14);
			DBUtils.close(dbCon);			
			sqlQuery		= null;
			qryParams		= null;
			transmissionInfoList = null;
			b2bHelper		= null;
			hmSAN			= null;		
			senderSAN		= null;		
			receiverSAN		= null;		
		}
		return goodsReceiptStatus;
	}
}
