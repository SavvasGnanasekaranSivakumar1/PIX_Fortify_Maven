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
 * Title		: 	OrderStatusDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal 	4 Nov, 2009		Initial version
 * 2.0		Yogesh Tyagi 	17 Nov, 2009	Reviewed and Modified
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.pearson.pixb2b.vendor.transaction.inbound.common.transstatus.dao.InboundTransStatusDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.OSHeader;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.OrderStatusDetail;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.OtherDate;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.PurchaseOrderInformation;
import com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto.PurchaseOrderReference;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
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
 * OrderStatusDAOImpl is a class to communicate with the PIX database
 * and process the B2B OrderStatus transaction data.
 * 
 * @author Ashish Agrawal, Yogesh Tyagi
 */
public class OrderStatusDAOImpl extends InboundTransStatusDAOImpl implements IOrderStatusDAO{
	private static final String qry_sel_pix_order_status_id_nextval = "SELECT SEQ_PIX_ORDER_STATUS_ID.nextval order_status_id_next FROM DUAL";
	
	private static final String qry_sel_release_no ="SELECT RELEASE_NO FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_PO_HEADER"
		+" WHERE PO_ID = ?"
		+" and PO_VERSION = ?";	
	
	private static final String qry_ins_order_status_header = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ORDER_STATUS_HEADER"
		+" (ORDER_STATUS_ID, PO_ID, PO_VERSION, STATUS_NO, TRANSACTION_HISTORY_NO, "
		+" CREATED_BY, MODIFIED_BY, MOD_DATE_TIME, CREATION_DATE_TIME)"
		+" VALUES (?, ?, ?, ?, ?, ?, ?, SYSDATE, SYSDATE)";
	
	private static final String qry_ins_order_status = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ORDER_STATUS"
		+" (ORDER_STATUS_ID, PO_ID, PO_VERSION, PO_LINE_NO, TIMELINE_ID, TIMELINE_DATE, ESTIMATED_DATE,COMMENTS,"
		+" STATUS_ID, CREATED_BY, MODIFIED_BY, MOD_DATE_TIME, CREATION_DATE_TIME)"
		+" VALUES (?, ?, ?, ?, ?, to_date(?,'MM/DD/YYYY'), to_date(?,'MM/DD/YYYY'),?,?, ?, ?, SYSDATE, SYSDATE)";
	
	private static final String qry_updt_JOB_NO = "UPDATE "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_PO_HEADER"
	+" SET JOB_NO = ?, MOD_DATE_TIME = SYSDATE, MODIFIED_BY = ?"
	+" WHERE PO_ID = ?"
	+" AND PO_VERSION = ?"; 
	
	private static final String qry_sel_fg_po_line_no ="SELECT PPL.PO_LINE_NO PO_LINE_NO FROM PIX_PO_HEADER PPH, PIX_PO_LINE PPL, PIX_BOOK_SPEC_LINE PBSL"
	+" WHERE PPH.PO_ID = ? AND PPH.PO_VERSION = ? AND PPH.PO_ID= PPL.PO_ID AND PPH.PO_VERSION = PPL.PO_VERSION"
    +" AND PBSL.SPEC_ID = PPH.SPEC_ID AND PBSL.SPEC_VERSION = PPH.SPEC_VERSION AND PBSL.PRODUCT_CODE = PPL.PRODUCT_CODE"
    +" AND FINISHED_GOOD_FLAG  = 'Y'"; 

	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dao.IOrderStatusDAO#storeOrderStatusDetails(com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO)
	 */
	public String storeOrderStatusDetails(PapiNetEnvelopeDTO pneDTO, TransactionStatusDTO transStatusDTO){
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
		PreparedStatement prepStmt4	= null;
		PreparedStatement prepStmt5	= null;

		PreparedStatement prepStmt6	= null;
		ResultSet resultSet6		= null;
		
		ArrayList transmissionInfoList = null;
		B2BHelper b2bHelper			= null;
		HashMap hmSAN				= null;		
		String senderSAN			= null;		
		String receiverSAN			= null;
		DAOHelper daoHelper			= null;
		ArrayList xmlOSDetailList	= null;
		OrderStatusDetail xmlOSDetail= null;
		OSHeader xmlOSHeader = null;
		PurchaseOrderInformation xmlPOInfo = null;
		String xmlPONumber	  		= null;
		ArrayList xmlPORefList		= null;
		PurchaseOrderReference xmlPORef= null;
		String xmlPOTransId			= null;
		String xmlJobNumber			= null;
		String dbPOId				= null;
		String dbPOVersion			= null;
		HashMap hmPoIdpoVersion		= null;
		String dbOrderStatusIdNext	= null;
		long dbTransIdNext 			= -1;
		long dbReleaseNo	 		= -1;
		String xmlOrderStatusCode	= null;
		int statusID	 			= -1;
		String xmlAvailToShipDate	= null;
		ArrayList xmlOtherDateList	= null;
		OtherDate xmlOtherDate		= null;
		String xmlOnPressDate   	= null;
		String osInsStatus 			= IPixB2BConstants.flag_N;
		String osNumber 			= null;
		String osResponseDate 		= null;		
		ArrayList xmlLineNotesList	= null;
		String additionalTextLine	= null;
		String xmlLineComments		= "";
		String xmlOSTransId			= null;	
		String poLineItemNumber		= null;
		try {
			B2BLogger.debug("******* OrderStatusDAOImpl.storeOrderStatusDetails() method ENTERED *******");
			
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}
			
			if(pneDTO != null){
				transmissionInfoList = pneDTO.getPayloadInfo().getTransmissionInfoList();
				if(transmissionInfoList != null && transmissionInfoList.size()>0){
					b2bHelper = new B2BHelper();
					senderSAN = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.VENDOR_SAN, IPixB2BConstants.transType_OST);
					hmSAN = b2bHelper.getSendorSANReceiverSAN(transmissionInfoList);
					if(hmSAN != null && hmSAN.size()>0){
						//senderSAN = (String)hmSAN.get("senderSAN");
						receiverSAN = (String)hmSAN.get("receiverSAN");
					}
					if(senderSAN != null && !"".equals(senderSAN.trim()) && receiverSAN != null && !"".equals(receiverSAN.trim())){
						senderSAN = b2bHelper.addDashesInSAN(senderSAN);
						receiverSAN = b2bHelper.addDashesInSAN(receiverSAN);
						
						xmlOSTransId = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.XML_TRANS_ID, IPixB2BConstants.transType_OST);
						daoHelper = new DAOHelper();
						xmlOSHeader = pneDTO.getPayload().getBusinessDocument().getOsDTO().getOsHeader();
						if(xmlOSHeader != null){
							osNumber = xmlOSHeader.getOsNumber();
							if(xmlOSHeader.getOsResponseDate() != null){
								osResponseDate = xmlOSHeader.getOsResponseDate().getOsDate().getMonth()
									+"/"+xmlOSHeader.getOsResponseDate().getOsDate().getDay()
									+"/"+xmlOSHeader.getOsResponseDate().getOsDate().getYear();
							}								
						}
						
						xmlOSDetailList = pneDTO.getPayload().getBusinessDocument().getOsDTO().getOsDetailList();			
						if(xmlOSDetailList != null && xmlOSDetailList.size() > 0){
							for(int i= 0; i<xmlOSDetailList.size(); i++){
								xmlOSDetail = (OrderStatusDetail) xmlOSDetailList.get(i);
								if(xmlOSDetail != null){
									xmlPOInfo = xmlOSDetail.getPoInformation();
									if(xmlPOInfo != null){
										xmlPONumber = xmlPOInfo.getPoNumber();
										B2BLogger.info("OrderStatusDAOImpl.storeOrderStatusDetails() - xmlPONumber = "+xmlPONumber);
										xmlPORefList = xmlPOInfo.getPoReferenceList();
										if(xmlPORefList != null && xmlPORefList.size() > 0){
											for(int j = 0; j<xmlPORefList.size(); j++){
												xmlPORef = (PurchaseOrderReference) xmlPORefList.get(j);
												if(xmlPORef != null && xmlPORef.getPoReferenceType() != null && IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlPORef.getPoReferenceType().trim()))
													xmlPOTransId = xmlPORef.getPoReferenceValue();
												if(xmlPORef != null && xmlPORef.getPoReferenceType() != null && IPixB2BConstants.JobNumber.equalsIgnoreCase(xmlPORef.getPoReferenceType().trim()))
													xmlJobNumber = xmlPORef.getPoReferenceValue();
											}
										}
									}								
								}
							} //end for loop - osDetailList
							B2BLogger.info("OrderStatusDAOImpl.storeOrderStatusDetails() - xmlPOTransId = "+xmlPOTransId);
							
							if(xmlPOTransId != null && !"".equals(xmlPOTransId.trim())){					
								dbCon.setAutoCommit(false);
								
								hmPoIdpoVersion = daoHelper.getPoIdPoVersion(dbCon, IPixB2BConstants.transType_POR, xmlPOTransId);
								if(hmPoIdpoVersion != null && hmPoIdpoVersion.size()>0){
									dbPOId = (String)hmPoIdpoVersion.get("PO_ID");
									dbPOVersion= (String)hmPoIdpoVersion.get("PO_VERSION");
								}
							
								if(dbPOId != null && !"".equals(dbPOId.trim()) && dbPOVersion != null && !"".equals(dbPOVersion.trim())){
									sqlQuery = qry_sel_pix_order_status_id_nextval;
									prepStmt1 = dbCon.prepareStatement(sqlQuery);
									prepStmt1.clearParameters();		
									resultSet1 = prepStmt1.executeQuery();
									while(resultSet1.next()){
										dbOrderStatusIdNext = resultSet1.getString("order_status_id_next");
									}
									DBUtils.close(prepStmt1);
									DBUtils.close(resultSet1);
									B2BLogger.info("OrderStatusDAOImpl.storeOrderStatusDetails() - dbOrderStatusIdNext = "+dbOrderStatusIdNext);
									
									if(dbOrderStatusIdNext != null && !"".equals(dbOrderStatusIdNext.trim())){
										dbTransIdNext = updateInboundTransStatus(dbCon, transStatusDTO, 
												senderSAN, receiverSAN, 
												dbPOId, dbOrderStatusIdNext, IPixB2BConstants.PO_ID, IPixB2BConstants.ORDER_STATUS_ID,osNumber,osResponseDate,xmlOSTransId);
										B2BLogger.info("OrderStatusDAOImpl.storeOrderStatusDetails() - dbTransIdNext = "+dbTransIdNext);
										
										if(dbTransIdNext > 0){
											sqlQuery = qry_sel_release_no;
											prepStmt2 = dbCon.prepareStatement(sqlQuery);
											prepStmt2.clearParameters();
											prepStmt2.setString(IPixB2BConstants.ONE, dbPOId);
											prepStmt2.setString(IPixB2BConstants.TWO, dbPOVersion);
											qryParams = dbPOId+","+dbPOVersion;
											resultSet2 = prepStmt2.executeQuery();
											while(resultSet2.next()){
												dbReleaseNo = resultSet2.getLong("RELEASE_NO");
											}
											DBUtils.close(prepStmt2);
											DBUtils.close(resultSet2);
											
											B2BLogger.info("OrderStatusDAOImpl.storeOrderStatusDetails() - dbReleaseNo = "+dbReleaseNo);
											
											if(null!=xmlJobNumber && !"".equals(xmlJobNumber)){
												sqlQuery = qry_updt_JOB_NO;
												prepStmt5 = dbCon.prepareStatement(sqlQuery);
												prepStmt5.clearParameters();					
												prepStmt5.setString(IPixB2BConstants.ONE, xmlJobNumber);
												prepStmt5.setString(IPixB2BConstants.TWO, IPixB2BConstants.JavaB2B);
												prepStmt5.setString(IPixB2BConstants.THREE, dbPOId);			
												prepStmt5.setString(IPixB2BConstants.FOUR, dbPOVersion);				
												qryParams = xmlJobNumber+","+IPixB2BConstants.JavaB2B+","+dbPOId+","+dbPOVersion;
												recsNum = prepStmt5.executeUpdate();				
												recsCount = recsCount + recsNum;
												
												DBUtils.close(prepStmt5);
											}
											
											sqlQuery = qry_ins_order_status_header;
											prepStmt3 = dbCon.prepareStatement(sqlQuery);			
											prepStmt3.clearParameters();		
											prepStmt3.setString(IPixB2BConstants.ONE, dbOrderStatusIdNext);
											prepStmt3.setString(IPixB2BConstants.TWO, dbPOId);
											prepStmt3.setString(IPixB2BConstants.THREE, dbPOVersion);
											prepStmt3.setString(IPixB2BConstants.FOUR, IPixB2BConstants.transType_OST+"_"+xmlPONumber+"_"+dbReleaseNo+"_"+dbOrderStatusIdNext);
											prepStmt3.setLong(IPixB2BConstants.FIVE, dbTransIdNext);
											prepStmt3.setString(IPixB2BConstants.SIX, IPixB2BConstants.JavaB2B);
											prepStmt3.setString(IPixB2BConstants.SEVEN, IPixB2BConstants.JavaB2B);
											qryParams = dbOrderStatusIdNext+","+dbPOId+","+dbPOVersion+","+IPixB2BConstants.transType_OST+"_"+xmlPONumber+"_"+dbReleaseNo+"_"+dbOrderStatusIdNext+","+dbTransIdNext+","+IPixB2BConstants.JavaB2B+","+IPixB2BConstants.JavaB2B;
											recsNum = prepStmt3.executeUpdate();							
											recsCount = recsCount + recsNum;
											
											DBUtils.close(prepStmt3);
											
											sqlQuery = qry_ins_order_status;
											prepStmt4 = dbCon.prepareStatement(sqlQuery);								
											
											for(int i= 0; i<xmlOSDetailList.size(); i++){
												xmlAvailToShipDate = null;
												xmlOnPressDate = null;
												
												xmlOSDetail = (OrderStatusDetail) xmlOSDetailList.get(i);									
												if(xmlOSDetail.getDelDateWindow() != null){
													xmlAvailToShipDate = xmlOSDetail.getDelDateWindow().getAvailableToShipDate().getMonth()
														+"/"+xmlOSDetail.getDelDateWindow().getAvailableToShipDate().getDay()
														+"/"+xmlOSDetail.getDelDateWindow().getAvailableToShipDate().getYear();
												}									
												xmlOtherDateList = xmlOSDetail.getOtherDateList();
												if(xmlOtherDateList != null && xmlOtherDateList.size() > 0){
													for(int k=0; k<xmlOtherDateList.size(); k++){
														xmlOtherDate = (OtherDate) xmlOtherDateList.get(k);
														if(xmlOtherDate != null && xmlOtherDate.getDateType() != null && IPixB2BConstants.OnPressDate.equalsIgnoreCase(xmlOtherDate.getDateType().trim())){
															xmlOnPressDate = xmlOtherDate.getDate().getMonth()
																+"/"+xmlOtherDate.getDate().getDay()
																+"/"+xmlOtherDate.getDate().getYear();
														}
													}
												}									
												xmlOrderStatusCode = xmlOSDetail.getOsInformation().getOrderPrimaryStatus().getOrderStatusCode();
												statusID = daoHelper.getStatusID(dbCon, xmlOrderStatusCode, IPixB2BConstants.table_id_50);
												B2BLogger.info("OrderStatusDAOImpl.storeOrderStatusDetails() - xmlOrderStatusCode = "+xmlOrderStatusCode+", statusID = "+statusID);
												
//												Read LineComments
												xmlLineNotesList = xmlOSDetail.getOsLineNotes();
												if(xmlLineNotesList != null && xmlLineNotesList.size() > 0){
													for(int j=0; j<xmlLineNotesList.size(); j++){
														additionalTextLine = (String)xmlLineNotesList.get(j);
														xmlLineComments = xmlLineComments +(j == 0 ? "" : " ")+ additionalTextLine;
													}
												}											
												
												sqlQuery = qry_sel_fg_po_line_no;
												prepStmt6 = dbCon.prepareStatement(sqlQuery);
												prepStmt6.clearParameters();		
												prepStmt6.setString(IPixB2BConstants.ONE, dbPOId);
												prepStmt6.setString(IPixB2BConstants.TWO, dbPOVersion);
												resultSet6 = prepStmt6.executeQuery();
												while(resultSet6.next()){
													poLineItemNumber = resultSet6.getString("PO_LINE_NO");
												}
												DBUtils.close(prepStmt6);
												DBUtils.close(resultSet6);
												
												if (null==poLineItemNumber){
													poLineItemNumber=xmlOSDetail.getPoLineItemNumber();
												}
												
												prepStmt4.clearParameters();		
												prepStmt4.setString(IPixB2BConstants.ONE, dbOrderStatusIdNext);
												prepStmt4.setString(IPixB2BConstants.TWO, dbPOId);
												prepStmt4.setString(IPixB2BConstants.THREE, dbPOVersion);
												prepStmt4.setString(IPixB2BConstants.FOUR, poLineItemNumber);
												prepStmt4.setString(IPixB2BConstants.FIVE, ""+IPixB2BConstants.ONE);
												prepStmt4.setString(IPixB2BConstants.SIX, xmlOnPressDate);// new value-on Pressdate - before it was xmlAvailToShipDate
												prepStmt4.setString(IPixB2BConstants.SEVEN, xmlAvailToShipDate);//xmlAvailToShipDate - before it was xmlShipmentReqDate
												prepStmt4.setString(IPixB2BConstants.EIGHT,xmlLineComments);
												prepStmt4.setInt(IPixB2BConstants.NINE, statusID);
												prepStmt4.setString(IPixB2BConstants.TEN, IPixB2BConstants.JavaB2B);
												prepStmt4.setString(IPixB2BConstants.ELEVEN, IPixB2BConstants.JavaB2B);
												qryParams = dbOrderStatusIdNext+","+dbPOId+","+dbPOVersion+","+xmlOSDetail.getPoLineItemNumber()+","+IPixB2BConstants.ONE+","+xmlAvailToShipDate+","+xmlOnPressDate+","+xmlLineComments+","+statusID+","+IPixB2BConstants.JavaB2B+","+IPixB2BConstants.JavaB2B;
												recsNum = prepStmt4.executeUpdate();
												recsCount = recsCount + recsNum;
												
												//DBUtils.close(prepStmt4);
											} //end for loop - osDetailList
											DBUtils.close(prepStmt4);
										}else{
											B2BLogger.info("OrderStatusDAOImpl.storeOrderStatusDetails() - dbTransIdNext = "+dbTransIdNext+" for xmlTransId = "+transStatusDTO.getTransID());
										}
									}else{
										B2BLogger.info("OrderStatusDAOImpl.storeOrderStatusDetails() - dbOrderStatusIdNext = "+dbOrderStatusIdNext);
									}
								}else{
									B2BLogger.info("OrderStatusDAOImpl.storeOrderStatusDetails() - dbPOId(TRANSREF_ID_1) = "+dbPOId+" , dbPOVersion(TRANSREF_ID_2) = "+dbPOVersion+" FOR xmlPOTransId = "+xmlPOTransId);
								}
								dbCon.commit();
							}else{
								B2BLogger.info("OrderStatusDAOImpl.storeOrderStatusDetails() - xmlPOTransId is null or blank");
							}
						}else{
							B2BLogger.info("OrderStatusDAOImpl.storeOrderStatusDetails() - xmlOSDetailList is null or blank");
						}
					}else{
						B2BLogger.info("OrderStatusDAOImpl.storeOrderStatusDetails() - senderSAN = "+senderSAN+" , receiverSAN = "+receiverSAN);
					}
				}else{
					B2BLogger.info("OrderStatusDAOImpl.storeOrderStatusDetails() - transmissionInfoList is null or blank");
				}
			}else{
				B2BLogger.info("OrderStatusDAOImpl.storeOrderStatusDetails() - pneDTO is null");
			}
			
			B2BLogger.info("OrderStatusDAOImpl.storeOrderStatusDetails() - recsCount = "+recsCount+", dbTransIdNext = "+dbTransIdNext);
			if(recsCount > 0)
				osInsStatus = IPixB2BConstants.flag_Y;			
			
			B2BLogger.debug("******* OrderStatusDAOImpl.storeOrderStatusDetails() method EXIT *******");
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			osInsStatus = StringUtils.getStackTrace(e);
			if(osInsStatus != null && osInsStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				osInsStatus = osInsStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}			
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			osInsStatus = StringUtils.getStackTrace(e);
			if(osInsStatus != null && osInsStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				osInsStatus = osInsStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
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
			DBUtils.close(prepStmt4);
			DBUtils.close(prepStmt5);
			DBUtils.close(prepStmt6);
			DBUtils.close(resultSet6);
			DBUtils.close(dbCon);
			
			sqlQuery		= null;
			qryParams		= null;
			transmissionInfoList = null;
			b2bHelper		= null;
			hmSAN			= null;		
			senderSAN		= null;		
			receiverSAN		= null;
			daoHelper		= null;
			xmlOSDetailList	= null;
			xmlOSDetail		= null;
			xmlPOInfo 		= null;
			xmlPONumber	  	= null;
			xmlPORefList	= null;
			xmlPORef		= null;
			xmlPOTransId	= null;
			xmlJobNumber	= null;
			dbPOId			= null;
			dbPOVersion		= null;
			hmPoIdpoVersion	= null;
			dbOrderStatusIdNext	= null;
			xmlOrderStatusCode	= null;
			xmlAvailToShipDate	= null;
			xmlOtherDateList	= null;
			xmlOtherDate		= null;
			xmlOnPressDate  	= null;
			poLineItemNumber	= null;
		}
		return osInsStatus;
	}
}