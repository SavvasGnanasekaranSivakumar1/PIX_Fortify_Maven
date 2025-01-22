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
 * Title		: 	OrderConfirmationDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Abhilasha Nigam 04 Nov, 2009	Initial version
 * 2.0		Yogesh Tyagi 	17 Nov, 2009	Reviewed and Modified
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.pearson.pixb2b.vendor.transaction.inbound.common.transstatus.dao.InboundTransStatusDAOImpl;
import com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto.OCHeader;
import com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto.OCLineDate;
import com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto.OCLineItem;
import com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto.OCReference;
import com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto.OtherDate;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POReference;
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
 * OrderConfirmationDAOImpl is a class to communicate with the PIX database
 * and process the B2B Order Confirmation data.
 * 
 * @author Abhilasha Nigam, Yogesh Tyagi
 */
public class OrderConfirmationDAOImpl extends InboundTransStatusDAOImpl implements IOrderConfirmationDAO{
	private static final String qry_updt_order_confirm_header = "UPDATE "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_PO_HEADER"
		+" SET STATUS_ID = ?, ACKNOWLEDGE_FLAG = ?, JOB_NO = ?, MOD_DATE_TIME = SYSDATE, MODIFIED_BY = ?"
		+" WHERE PO_ID = ?"
		+" AND PO_VERSION = ?"; 
	
	private static final String qry_updt_pix_po_party = "UPDATE "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_PO_PARTY"
		+" SET STATUS_ID = ?, DELIVERY_DATE = to_date(?,'MM/DD/YYYY'), COMMENTS = ?"
		+" WHERE PO_ID = ?"
		+" AND PO_VERSION = ?"
		+" AND PARTY_TYPE = ?";
	
	private static final String qry_upd_pix_oc_line_items = "UPDATE "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_PO_LINE"
		+" SET SUPPLIER_STATUS_ID = ?, ESTIMATED_DELIVERY_DATE = to_date(?,'MM/DD/YYYY'),"
		+" MOD_DATE_TIME = sysdate, MODIFIED_BY = ?,"
		+" SUPPLIER_COMMENTS = ?" 
		+" WHERE PO_ID = ?"
		+" AND PO_VERSION = ?"
		+" AND PO_LINE_NO = ?";
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dao.IOrderConfirmationDAO#storeOrderConfirmationDetails(com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO, com.pearson.pixb2b.vendor.transaction.shared.dto.status.TransactionStatusDTO)
	 */
	public String storeOrderConfirmationDetails(PapiNetEnvelopeDTO pneDTO, TransactionStatusDTO transStatusDTO) {	
		Connection dbCon 			= null;
		String sqlQuery				= null;
		String qryParams			= null;
		int recsCount				= 0;
		int recsNum					= 0;
		
		PreparedStatement prepStmt1	= null;	
		PreparedStatement prepStmt2	= null;		
		PreparedStatement prepStmt3	= null;
		
		ArrayList transmissionInfoList = null;
		B2BHelper b2bHelper			= null;
		HashMap hmSAN				= null;		
		String senderSAN			= null;		
		String receiverSAN			= null;
		DAOHelper daoHelper			= null;		
		OCHeader  xmlOCHeader		= null;
		ArrayList xmlHeaderNotesList= null;
		String additionalTextHeader	= null;
		String xmlHeaderComments	= "";
		ArrayList xmlPORefList		= null;
		POReference xmlPORef		= null;
		String xmlPOTransId			= null;
		HashMap hmPoIdpoVersion		= null;
		String dbPOId				= null;
		String dbPOVersion			= null;
		long dbTransIdNext 			= -1;		
		//String xmlOCStatusType	= null;
		String xmlOCHeaderStatusType= null;
		int statusIDHeader			= -1;
		int statusIDParty			= -1;
		String xmlOCLineItemStatusType = null;
		int statusIDLine			= -1;
		ArrayList xmlOCRefList		= null;
		OCReference  xmlOCRef		= null;
		String xmlOCRefJobNo		= null;
		String xmlOCTransId			= null;
		ArrayList xmlOtherDateList	= null;
		OtherDate xmlOtherDate		= null;
		String xmlAvailToShipDate 	= null;
		ArrayList xmlOCLineItemList	= null;
		OCLineItem xmlOCLineItem	= null;
		OCLineDate xmlOCLineDate	= null;
		String xmlEstmDeliveryDate	= null;
		ArrayList xmlLineNotesList	= null;
		String additionalTextLine	= null;
		
		String ocInsStatus			= IPixB2BConstants.flag_N;
		String xmlPOLineNo = null;
		String ocNumber = null;
		String ocIssueDate = null;
		
		try {
			B2BLogger.debug("******* OrderConfirmationDAOImpl.storeOrderConfirmationDetails() method ENTERED *******");
			
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");					
			}
			
			if(pneDTO != null){
				transmissionInfoList = pneDTO.getPayloadInfo().getTransmissionInfoList();
				if(transmissionInfoList != null && transmissionInfoList.size()>0){
					b2bHelper = new B2BHelper();
					senderSAN = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.VENDOR_SAN, IPixB2BConstants.transType_OCO);
					hmSAN = b2bHelper.getSendorSANReceiverSAN(transmissionInfoList);
					if(hmSAN != null && hmSAN.size()>0){
						//senderSAN = (String)hmSAN.get("senderSAN");
						receiverSAN = (String)hmSAN.get("receiverSAN");
					}
					if(senderSAN != null && !"".equals(senderSAN.trim()) && receiverSAN != null && !"".equals(receiverSAN.trim())){
						senderSAN = b2bHelper.addDashesInSAN(senderSAN);
						receiverSAN = b2bHelper.addDashesInSAN(receiverSAN);
						
						daoHelper = new DAOHelper();
						
						xmlOCHeader = pneDTO.getPayload().getBusinessDocument().getOcDTO().getOcHeader();
						if(xmlOCHeader != null){
							
							//Read OC Document No and Doc Issue Date
							ocNumber =  xmlOCHeader.getOcNumber();
							
							if(xmlOCHeader.getOcIssueDate() != null){
								ocIssueDate = xmlOCHeader.getOcIssueDate().getOcDate().getMonth()
									+"/"+xmlOCHeader.getOcIssueDate().getOcDate().getDay()
									+"/"+xmlOCHeader.getOcIssueDate().getOcDate().getYear();
							}		
							
							xmlOCRefList = xmlOCHeader.getOcReference();
							if(xmlOCRefList != null && xmlOCRefList.size()>0){
								for(int l=0; l<xmlOCRefList.size(); l++){
									xmlOCRef = (OCReference) xmlOCRefList.get(l);  				
									if(xmlOCRef != null && xmlOCRef.getOcReferenceType() != null && IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlOCRef.getOcReferenceType().trim()))
										xmlOCTransId = xmlOCRef.getOcReferenceValue();
								}
							}
							
							xmlHeaderNotesList = xmlOCHeader.getHeaderNotes();
							if(xmlHeaderNotesList != null && xmlHeaderNotesList.size() > 0){
								for(int i=0; i<xmlHeaderNotesList.size(); i++){
									additionalTextHeader = (String)xmlHeaderNotesList.get(i);
									xmlHeaderComments = xmlHeaderComments +(i == 0 ? "" : " ")+additionalTextHeader;												
								}
							}			
							xmlPORefList = xmlOCHeader.getPoInformation().getPoReference();
							if(xmlPORefList != null && xmlPORefList.size()>0){
								for(int i=0; i<xmlPORefList.size(); i++){
									xmlPORef = (POReference) xmlPORefList.get(i);  				
									if(xmlPORef != null && xmlPORef.getPoReferenceType() != null && IPixB2BConstants.TransactionID.equalsIgnoreCase(xmlPORef.getPoReferenceType().trim()))
										xmlPOTransId = xmlPORef.getPoReferenceValue();
								} //end for loop - xmlPORefList
								B2BLogger.info("OrderConfirmationDAOImpl.storeOrderConfirmationDetails() - xmlPOTransId = "+xmlPOTransId);
								
								if(xmlPOTransId != null && !"".equals(xmlPOTransId.trim())){
									dbCon.setAutoCommit(false);
									
									hmPoIdpoVersion = daoHelper.getPoIdPoVersion(dbCon, IPixB2BConstants.transType_POR, xmlPOTransId);
									if(hmPoIdpoVersion != null && hmPoIdpoVersion.size()>0){
										dbPOId = (String)hmPoIdpoVersion.get("PO_ID");
										dbPOVersion = (String)hmPoIdpoVersion.get("PO_VERSION");
									}
								
									if(dbPOId != null && !"".equals(dbPOId.trim()) && dbPOVersion != null && !"".equals(dbPOVersion.trim())){
										dbTransIdNext = updateInboundTransStatus(dbCon, transStatusDTO, 
												senderSAN, receiverSAN, 
												dbPOId, dbPOVersion, IPixB2BConstants.PO_ID, IPixB2BConstants.PO_VERSION,ocNumber,ocIssueDate, xmlOCTransId);
										B2BLogger.info("OrderConfirmationDAOImpl.storeOrderConfirmationDetails() - dbTransIdNext = "+dbTransIdNext);
										
										if(dbTransIdNext > 0){
											//xmlOCStatusType = pneDTO.getPayload().getBusinessDocument().getOcDTO().getOcStatusType();
											xmlOCHeaderStatusType = xmlOCHeader.getOcHeaderStatusType();
											if(IPixB2BConstants.NoAction.equalsIgnoreCase(xmlOCHeaderStatusType) ||IPixB2BConstants.Pending.equalsIgnoreCase(xmlOCHeaderStatusType))
												statusIDHeader = IPixB2BConstants.status_id_19_Original;
											else
												statusIDHeader = daoHelper.getStatusID(dbCon, xmlOCHeaderStatusType, IPixB2BConstants.table_id_35);
											B2BLogger.info("OrderConfirmationDAOImpl.storeOrderConfirmationDetails() - xmlOCHeaderStatusType = "+xmlOCHeaderStatusType+", statusIDHeader = "+statusIDHeader);
											
											xmlOCRefList = xmlOCHeader.getOcReference();
											if(xmlOCRefList != null && xmlOCRefList.size()>0){
												for(int j=0; j<xmlOCRefList.size(); j++){
													xmlOCRef = (OCReference) xmlOCRefList.get(j);  				
													if(xmlOCRef != null && xmlOCRef.getOcReferenceType() != null && IPixB2BConstants.JobNumber.equalsIgnoreCase(xmlOCRef.getOcReferenceType().trim()))
														xmlOCRefJobNo = xmlOCRef.getOcReferenceValue();
												}
											}			
											B2BLogger.info("OrderConfirmationDAOImpl.storeOrderConfirmationDetails() - xmlOCRefJobNo = "+xmlOCRefJobNo);
											
											sqlQuery = qry_updt_order_confirm_header;
											prepStmt1 = dbCon.prepareStatement(sqlQuery);
											prepStmt1.clearParameters();					
											prepStmt1.setInt(IPixB2BConstants.ONE, statusIDHeader);
											prepStmt1.setString(IPixB2BConstants.TWO, IPixB2BConstants.flag_Y);
											prepStmt1.setString(IPixB2BConstants.THREE, xmlOCRefJobNo);			
											prepStmt1.setString(IPixB2BConstants.FOUR, IPixB2BConstants.JavaB2B);				
											prepStmt1.setString(IPixB2BConstants.FIVE, dbPOId);
											prepStmt1.setString(IPixB2BConstants.SIX, dbPOVersion);
											qryParams = statusIDHeader+","+IPixB2BConstants.flag_Y+","+xmlOCRefJobNo+","+IPixB2BConstants.JavaB2B+","+dbPOId+","+dbPOVersion;
											recsNum = prepStmt1.executeUpdate();				
											recsCount = recsCount + recsNum;
											
											DBUtils.close(prepStmt1);
											
											xmlOtherDateList = xmlOCHeader.getOtherDate();
											if(xmlOtherDateList != null && xmlOtherDateList.size() > 0){
												for(int i=0; i<xmlOtherDateList.size(); i++){
													xmlOtherDate = (OtherDate)xmlOtherDateList.get(i);
													if(xmlOtherDate != null && xmlOtherDate.getDateType() != null){
														if(IPixB2BConstants.AvailableToShipDate.equalsIgnoreCase(xmlOtherDate.getDateType().trim())){
															xmlAvailToShipDate = xmlOtherDate.getShipDate().getMonth()
																+ "/"+xmlOtherDate.getShipDate().getDay() 
																+ "/"+xmlOtherDate.getShipDate().getYear();
														}	
													}
															   	
												}
											}	
											B2BLogger.info("OrderConfirmationDAOImpl.storeOrderConfirmationDetails() - xmlAvailToShipDate = "+xmlAvailToShipDate);

											if(IPixB2BConstants.NoAction.equalsIgnoreCase(xmlOCHeaderStatusType))
												statusIDParty = IPixB2BConstants.status_id_9_New;
											else
												statusIDParty = daoHelper.getStatusID(dbCon, xmlOCHeaderStatusType, IPixB2BConstants.table_id_37);
											B2BLogger.info("OrderConfirmationDAOImpl.storeOrderConfirmationDetails() - xmlOCHeaderStatusType = "+xmlOCHeaderStatusType+", statusIDParty = "+statusIDParty);
											
											sqlQuery = qry_updt_pix_po_party;
											prepStmt2 = dbCon.prepareStatement(sqlQuery);
											prepStmt2.clearParameters();				
											prepStmt2.setInt(IPixB2BConstants.ONE, statusIDParty);
											prepStmt2.setString(IPixB2BConstants.TWO, xmlAvailToShipDate);
											prepStmt2.setString(IPixB2BConstants.THREE, xmlHeaderComments);
											prepStmt2.setString(IPixB2BConstants.FOUR, dbPOId);
											prepStmt2.setString(IPixB2BConstants.FIVE, dbPOVersion);
											prepStmt2.setString(IPixB2BConstants.SIX, IPixB2BConstants.flag_V);
											qryParams = statusIDParty+","+xmlAvailToShipDate+","+xmlHeaderComments+","+dbPOId+","+dbPOVersion+","+IPixB2BConstants.flag_V;
											recsNum = prepStmt2.executeUpdate();
											recsCount = recsCount + recsNum;
											
											DBUtils.close(prepStmt2);
											
											xmlOCLineItemList = pneDTO.getPayload().getBusinessDocument().getOcDTO().getOcLineItem();
											if(xmlOCLineItemList != null &&  xmlOCLineItemList.size()>0){
												sqlQuery = qry_upd_pix_oc_line_items;
												prepStmt3 = dbCon.prepareStatement(sqlQuery);
												
												for(int i=0; i<xmlOCLineItemList.size(); i++){												
														xmlPOLineNo = null;
														xmlEstmDeliveryDate = null;
														String xmlLineComments	= "";
														
														xmlOCLineItem = (OCLineItem)xmlOCLineItemList.get(i);
														
														xmlPOLineNo = xmlOCLineItem.getPoLineItemNo();
														
														xmlOCLineItemStatusType = xmlOCLineItem.getOcLineItemStatusType();
														if(IPixB2BConstants.NoAction.equalsIgnoreCase(xmlOCHeaderStatusType))
															statusIDLine = IPixB2BConstants.status_id_14_New;
														else
															statusIDLine = daoHelper.getStatusID(dbCon, xmlOCLineItemStatusType, IPixB2BConstants.table_id_36);
														B2BLogger.info("OrderConfirmationDAOImpl.storeOrderConfirmationDetails() - xmlOCLineItemStatusType = "+xmlOCLineItemStatusType+", statusIDLine = "+statusIDLine);
														
														xmlOCLineDate = xmlOCLineItem.getOcLineDate();
														if(xmlOCLineDate != null && xmlOCLineDate.getDateType() != null && IPixB2BConstants.AvailableToShipDate.equalsIgnoreCase(xmlOCLineDate.getDateType().trim())){
															xmlEstmDeliveryDate = xmlOCLineDate.getEstDeliveryDate().getLineMonth()
																+"/"+xmlOCLineDate.getEstDeliveryDate().getLineDay()
																+"/"+xmlOCLineDate.getEstDeliveryDate().getLineYear();
														}else{
															xmlEstmDeliveryDate = xmlAvailToShipDate;
														}
														B2BLogger.info("OrderConfirmationDAOImpl.storeOrderConfirmationDetails() - xmlEstmDeliveryDate = "+xmlEstmDeliveryDate);
														
														xmlLineNotesList = xmlOCLineItem.getLineNotes();
														if(xmlLineNotesList != null && xmlLineNotesList.size() > 0){
															for(int j=0; j<xmlLineNotesList.size(); j++){
																additionalTextLine = (String)xmlLineNotesList.get(j);
																xmlLineComments = xmlLineComments +(j == 0 ? "" : " ")+ additionalTextLine;
															}
														}else xmlLineComments = " " ;
																									
														prepStmt3.clearParameters();		
														prepStmt3.setInt(IPixB2BConstants.ONE, statusIDLine);
														prepStmt3.setString(IPixB2BConstants.TWO, xmlEstmDeliveryDate);
														prepStmt3.setString(IPixB2BConstants.THREE, IPixB2BConstants.JavaB2B);
														prepStmt3.setString(IPixB2BConstants.FOUR, xmlLineComments);
														prepStmt3.setString(IPixB2BConstants.FIVE, dbPOId);
														prepStmt3.setString(IPixB2BConstants.SIX, dbPOVersion);
														prepStmt3.setString(IPixB2BConstants.SEVEN, xmlPOLineNo);
														qryParams = statusIDLine+","+xmlEstmDeliveryDate+","+IPixB2BConstants.JavaB2B+","+xmlLineComments+","+dbPOId+","+dbPOVersion+","+xmlOCLineItem.getPoLineItemNo();
														recsNum = prepStmt3.executeUpdate();
														recsCount = recsCount + recsNum;
														
														//DBUtils.close(prepStmt3);
													
												}
											}
											DBUtils.close(prepStmt3);
										}else{
											B2BLogger.info("OrderConfirmationDAOImpl.storeOrderConfirmationDetails() - dbTransIdNext = "+dbTransIdNext+" for xmlTransId = "+transStatusDTO.getTransID());
										}					
									}else{
										B2BLogger.info("OrderConfirmationDAOImpl.storeOrderConfirmationDetails() - dbPOId(TRANSREF_ID_1) = "+dbPOId+" , dbPOVersion(TRANSREF_ID_2) = "+dbPOVersion+" FOR xmlPOTransId = "+xmlPOTransId);
									}
									dbCon.commit();
								}else{
									B2BLogger.info("OrderConfirmationDAOImpl.storeOrderConfirmationDetails() - xmlPOTransId is null or blank");
								}
							}else{
								B2BLogger.info("OrderConfirmationDAOImpl.storeOrderConfirmationDetails() - xmlPORefList is null or blank");
							}
						}else{
							B2BLogger.info("OrderConfirmationDAOImpl.storeOrderConfirmationDetails() - xmlOCHeader is null");
						}
					}else{
						B2BLogger.info("OrderConfirmationDAOImpl.storeOrderConfirmationDetails() - senderSAN = "+senderSAN+" , receiverSAN = "+receiverSAN);
					}
				}else{
					B2BLogger.info("OrderConfirmationDAOImpl.storeOrderConfirmationDetails() - transmissionInfoList is null or blank");
				}
			}else{
				B2BLogger.info("OrderConfirmationDAOImpl.storeOrderConfirmationDetails() - pneDTO is null");
			}
						
			B2BLogger.info("OrderConfirmationDAOImpl.storeOrderConfirmationDetails() - recsCount = "+recsCount+", dbTransIdNext = "+dbTransIdNext);
			if(recsCount > 0)
				ocInsStatus = IPixB2BConstants.flag_Y;		
			
			B2BLogger.debug("******* OrderConfirmationDAOImpl.storeOrderConfirmationDetails() method EXIT *******");
		} catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			ocInsStatus = StringUtils.getStackTrace(e);
			if(ocInsStatus != null && ocInsStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				ocInsStatus = ocInsStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}			
			
		} catch (B2BException e) {			
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
			ocInsStatus = StringUtils.getStackTrace(e);
			B2BLogger.error("Exception",e);
			if(ocInsStatus != null && ocInsStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				ocInsStatus = ocInsStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}
		} finally{			
			DBUtils.close(prepStmt1);			
			DBUtils.close(prepStmt2);			
			DBUtils.close(prepStmt3);
			DBUtils.close(dbCon);
			
			sqlQuery		= null;
			qryParams		= null;
			transmissionInfoList = null;
			b2bHelper		= null;
			hmSAN			= null;		
			senderSAN		= null;		
			receiverSAN		= null;			
			daoHelper		= null;
			xmlOCHeader	= null;
			xmlHeaderNotesList= null;
			additionalTextHeader= null;
			xmlHeaderComments= null;
			xmlPORefList	= null;
			xmlPORef		= null;
			xmlPOTransId	= null;
			hmPoIdpoVersion	= null;
			dbPOId			= null;
			dbPOVersion		= null;
			//xmlOCStatusType	= null;
			xmlOCHeaderStatusType = null;
			xmlOCLineItemStatusType = null;
			xmlOCRefList	= null;
			xmlOCRef		= null;
			xmlOCRefJobNo	= null;
			xmlOtherDateList= null;
			xmlOtherDate	= null;
			xmlAvailToShipDate = null;
			xmlOCLineItemList = null;
			xmlOCLineItem	= null;
			xmlOCLineDate	= null;
			xmlEstmDeliveryDate = null;
			xmlLineNotesList	= null;
			additionalTextLine	= null;
			
		}	
		return ocInsStatus;
	}
}