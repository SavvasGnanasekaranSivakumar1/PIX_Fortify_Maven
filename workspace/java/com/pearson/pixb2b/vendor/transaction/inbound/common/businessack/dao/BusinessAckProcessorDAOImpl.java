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
 * Title		: 	BusinessAckProcessorDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal	16 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.common.businessack.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.pearson.pixb2b.global.B2BGlobalData;
import com.pearson.pixb2b.vendor.transaction.shared.dto.businessack.BusinessAcknowledgementDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.businessack.DocumentError;
import com.pearson.pixb2b.vendor.transaction.shared.dto.businessack.DocumentReference;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.transaction.shared.helper.B2BHelper;
import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.DBUtils;
import com.pearson.pixb2b.vendor.utils.IConfigConstants;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * IOutboundBusinessAckDAO is an interface to process the 
 * B2B BusinessAcknowledgement Success/Failure transaction details for 
 * all Outbound Transactions sent to vendors.
 * 
 * @author Yogesh Tyagi
 */
public class BusinessAckProcessorDAOImpl implements IBusinessAckProcessorDAO {
	private static final String qry_sel_pix_run_id_nextval = "select SEQ_PIX_FEED_LOG_ID.nextval run_id_next FROM DUAL";	
	
	private static final String qry_ins_pix_feed_log = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_FEED_LOG"
		+" (RUN_ID, FEED_TYPE, STAGE_TYPE, START_DATE_TIME, END_DATE_TIME, SUCCESS_FLAG)"
		+" VALUES (?, ?, ?, SYSDATE, SYSDATE, ?)";
	
	private static final String qry_ins_pix_xml_ack_log  = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_XML_ACK_LOG"
		+" (TRANS_ID, IN_OUT_TYPE, ACK_FILE, FILE_SIZE, CREATION_DATE_TIME, MOD_DATE_TIME)"
		+" VALUES (?, ?, ?, ?, SYSDATE, SYSDATE)";

	private static final String qry_sel_pix_xmlgen_log = "SELECT TRANSREF_ID_1, TRANSREF_ID_2, TRANSREF_LABEL_1, TRANSREF_LABEL_2"
		+" FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_XMLGEN_LOG"
		+" WHERE trans_type = ?"
		+" AND trans_id = ? AND trans_id is not null";
	
	private static final String qry_sel_pix_errlog_nextval = "select seq_pix_error_log_id.nextval error_log_id_next FROM DUAL";	
	
	private static final String qry_insrt_pix_error_log = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ERROR_LOG"
		+" (ERROR_LOG_ID, RUN_ID, ERROR_ID, SOURCE_NAME, SOURCE_TYPE, DEST_NAME, DEST_TYPE,"
		+" TRANREF_ID_1, TRANREF_ID_2, TRANREF_LABEL_1, TRANREF_LABEL_2, ADDITIONAL_DESC,"
		+" CREATION_DATE_TIME, MOD_DATE_TIME, EMAIL_FLAG)"
		+" VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, SYSDATE, SYSDATE, ?)";
	
	private static final String qry_insrt_pix_email_tran = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_EMAIL_TRAN"
		+" (TRAN_ID, MODULE_TYPE, TRAN_TYPE, EMAIL_TYPE, RUN_ID,"
		+" SUPPLIER_SAN, TRANREF_LABEL_1, TRANREF_LABEL_2, TRANREF_ID_1, TRANREF_ID_2,"
		+" PROCESSED_FLAG, CREATION_DATE_TIME, MOD_DATE_TIME, PUB_UNIT)"
		+" VALUES (?, ?, ?, ?, ?,"
		+" ?, ?, ?, ?, ?,"
		+" ?, SYSDATE, SYSDATE, ?)";
	
	//For BusinessAcknowledgement received from vendor
	private static final String qry_upd_pix_xmlgen_log  = "UPDATE "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_XMLGEN_LOG"
		+" SET ACK_STATUS_FLAG = ?"
		+" WHERE TRANS_ID = ?"
		+" AND TRANS_TYPE = ?";
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.common.businessack.dao.IInboundBusinessAckDAO#storeBusinessAcknowledgementDetails(com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO, com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO)
	 */
	public String storeBusinessAcknowledgementDetails(PapiNetEnvelopeDTO pneDTO, TransactionStatusDTO transStatusDTO){
		Connection dbCon 			= null;
		String sqlQuery				= null;
		String qryParams			= null;
		int recsCount				= 0;
		int recsNum					= 0;
		
		/*PreparedStatement prepStmt1	= null;
		ResultSet resultSet1		= null;
		PreparedStatement prepStmt2	= null;*/
		PreparedStatement prepStmt3	= null;		
		PreparedStatement prepStmt4	= null;
		ResultSet resultSet4		= null;
		PreparedStatement prepStmt5	= null;
		ResultSet resultSet5		= null;
		PreparedStatement prepStmt6	= null;
		PreparedStatement prepStmt8	= null;
		
		ArrayList transmissionInfoList = null;
		B2BHelper b2bHelper			= null;
		HashMap hmSAN				= null;		
		String senderSAN			= null;		
		String receiverSAN			= null;	
		BusinessAcknowledgementDTO baDTO 	= null;
		String baStatusXml			= null;
		String baTransNameXml		= null;
		String baTransType			= null;
		ArrayList documentReferenceList		= null;
		DocumentReference documentReference	= null;
		String transIDXml			= null;
		ArrayList documentErrorList	= null;
		DocumentError documentError = null;
		long runIdNextDB			= -1;
		long errorLogIdNextDB		= -1;		
		String trans_ref_id_1		= null;
		String trans_ref_id_2		= null;
		String trans_ref_label_1	= null;
		String trans_ref_label_2	= null;
		String baInsStatus 			= IPixB2BConstants.flag_N;

		try {
			B2BLogger.debug("****** BusinessAckProcessorDAOImpl.storeBusinessAcknowledgementDetails() method ENTERED *******");			
			
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}
			
			if(pneDTO != null){
				transmissionInfoList = pneDTO.getPayloadInfo().getTransmissionInfoList();
				if(transmissionInfoList != null && transmissionInfoList.size()>0){
					b2bHelper = new B2BHelper();
					hmSAN = b2bHelper.getSendorSANReceiverSAN(transmissionInfoList);
					if(hmSAN != null && hmSAN.size()>0){
						senderSAN = (String)hmSAN.get("senderSAN");
						receiverSAN = (String)hmSAN.get("receiverSAN");
					}
					if(senderSAN != null && !"".equals(senderSAN.trim()) && receiverSAN != null && !"".equals(receiverSAN.trim())){
						senderSAN = b2bHelper.addDashesInSAN(senderSAN);
						receiverSAN = b2bHelper.addDashesInSAN(receiverSAN);
						
						baDTO = pneDTO.getPayload().getBusinessDocument().getBaDTO();
						
						if(baDTO != null){
							baStatusXml = baDTO.getStatus();				
							baTransNameXml = baDTO.getDocument().getDocumentName();
							if(IPixB2BConstants.PurchaseOrder.equals(baTransNameXml))
								baTransType = IPixB2BConstants.transType_POR;
							else if(IPixB2BConstants.ShippingInstructions.equals(baTransNameXml))
								baTransType = IPixB2BConstants.transType_SIP;
							else if(IPixB2BConstants.BookSpecification.equals(baTransNameXml))
								baTransType = IPixB2BConstants.transType_BSP;
							else if(IPixB2BConstants.DeliveryMessage.equals(baTransNameXml))
								baTransType = IPixB2BConstants.transType_DME;
							else if(IPixB2BConstants.Plan.equals(baTransNameXml))
								baTransType = IPixB2BConstants.transType_PLA;
							
							documentReferenceList = baDTO.getDocument().getDocumentReferenceList();
							if(documentReferenceList != null && documentReferenceList.size()>0){
								for(int i= 0; i<documentReferenceList.size(); i++){
									documentReference = (DocumentReference)documentReferenceList.get(i);
									if(documentReference != null && documentReference.getDocumentReferenceType() != null ){
										if(IPixB2BConstants.TransactionID.equalsIgnoreCase(documentReference.getDocumentReferenceType().trim()))
											transIDXml = documentReference.getDocumentReferenceValue();				
									}
								} //end for loop - documentReferenceList
							}
							
							if(transIDXml != null && !"".equals(transIDXml.trim())){
								dbCon.setAutoCommit(false);
								
								/*sqlQuery = qry_sel_pix_run_id_nextval;
								prepStmt1 = dbCon.prepareStatement(sqlQuery);
								prepStmt1.clearParameters();		
								resultSet1 = prepStmt1.executeQuery();
								while(resultSet1.next()){
									runIdNextDB = resultSet1.getLong("run_id_next");
								}
								DBUtils.close(prepStmt1);
								DBUtils.close(resultSet1);	
								B2BLogger.info("BusinessAckProcessorDAOImpl.storeBusinessAcknowledgementDetails() - runIdNextDB = "+runIdNextDB);*/
								B2BGlobalData globalData = B2BGlobalData.getGlobalDataObject();
								runIdNextDB = globalData.getRun_Id();
								
								if(runIdNextDB > 0){
									/*sqlQuery = qry_ins_pix_feed_log;
									prepStmt2 = dbCon.prepareStatement(sqlQuery);
									prepStmt2.clearParameters();
									prepStmt2.setLong(IPixB2BConstants.ONE, runIdNextDB);
									prepStmt2.setString(IPixB2BConstants.TWO, IPixB2BConstants.B2B);
									prepStmt2.setString(IPixB2BConstants.THREE, IPixB2BConstants.VEN_TO_PIX);
									prepStmt2.setString(IPixB2BConstants.FOUR, IPixB2BConstants.flag_S);
									qryParams = runIdNextDB+","+IPixB2BConstants.B2B+","+IPixB2BConstants.VEN_TO_PIX+","+IPixB2BConstants.flag_S;
									recsNum = prepStmt2.executeUpdate();
									recsCount = recsCount + recsNum;
									DBUtils.close(prepStmt2);*/
									
									sqlQuery = qry_ins_pix_xml_ack_log;
									prepStmt3 = dbCon.prepareStatement(sqlQuery);
									prepStmt3.clearParameters();
									prepStmt3.setString(IPixB2BConstants.ONE, transIDXml);
									prepStmt3.setString(IPixB2BConstants.TWO, transStatusDTO.getProcessType());
									prepStmt3.setString(IPixB2BConstants.THREE, transStatusDTO.getFileName());
									prepStmt3.setString(IPixB2BConstants.FOUR, transStatusDTO.getFileSize1());
									qryParams = transIDXml+","+transStatusDTO.getProcessType()+","+transStatusDTO.getFileName()+","+transStatusDTO.getFileSize1();
									recsNum = prepStmt3.executeUpdate();
									recsCount = recsCount + recsNum;
									DBUtils.close(prepStmt3);
									
									sqlQuery = qry_sel_pix_xmlgen_log;
									prepStmt4 = dbCon.prepareStatement(sqlQuery);
									prepStmt4.clearParameters();
									prepStmt4.setString(IPixB2BConstants.ONE, IPixB2BConstants.B2B);
									prepStmt4.setString(IPixB2BConstants.TWO, transIDXml);
									qryParams = IPixB2BConstants.B2B+","+transIDXml;
									resultSet4 = prepStmt4.executeQuery();
									while(resultSet4.next()){
										trans_ref_id_1 	  = resultSet4.getString("TRANSREF_ID_1");
										trans_ref_id_2 	  = resultSet4.getString("TRANSREF_ID_2");
										trans_ref_label_1 = resultSet4.getString("TRANSREF_LABEL_1");
										trans_ref_label_2 = resultSet4.getString("TRANSREF_LABEL_2");
									}
									DBUtils.close(prepStmt4);
									DBUtils.close(resultSet4);	
									
									if(IPixB2BConstants.Failure.equals(baStatusXml)){
										documentErrorList = baDTO.getDocumentErrorList();
										if(documentErrorList != null && documentErrorList.size() > 0){
											for(int i=0; i<documentErrorList.size(); i++){
												documentError = (DocumentError)documentErrorList.get(i);
												if(documentError != null){
													sqlQuery = qry_sel_pix_errlog_nextval;
													prepStmt5 = dbCon.prepareStatement(sqlQuery);
													prepStmt5.clearParameters();		
													resultSet5 = prepStmt5.executeQuery();
													while(resultSet5.next()){
														errorLogIdNextDB = resultSet5.getLong("error_log_id_next");
													}
													DBUtils.close(prepStmt5);
													DBUtils.close(resultSet5);	
													B2BLogger.info("BusinessAckProcessorDAOImpl.storeBusinessAcknowledgementDetails() - errorLogIdNextDB = "+errorLogIdNextDB);
													
													if(errorLogIdNextDB != -1){
														sqlQuery = qry_insrt_pix_error_log;
														prepStmt6 = dbCon.prepareStatement(sqlQuery);
														prepStmt6.clearParameters();						
														prepStmt6.setLong(IPixB2BConstants.ONE, errorLogIdNextDB);
														prepStmt6.setLong(IPixB2BConstants.TWO, runIdNextDB);
														prepStmt6.setInt(IPixB2BConstants.THREE, IPixB2BConstants.ERROR_ID_0);
														prepStmt6.setString(IPixB2BConstants.FOUR, baTransNameXml);
														prepStmt6.setString(IPixB2BConstants.FIVE, baTransType);
														prepStmt6.setString(IPixB2BConstants.SIX, transStatusDTO.getFileName());
														prepStmt6.setString(IPixB2BConstants.SEVEN, IPixB2BConstants.XML);
														prepStmt6.setString(IPixB2BConstants.EIGHT, trans_ref_id_1);
														prepStmt6.setString(IPixB2BConstants.NINE, trans_ref_id_2);
														prepStmt6.setString(IPixB2BConstants.TEN, trans_ref_label_1);
														prepStmt6.setString(IPixB2BConstants.ELEVEN, trans_ref_label_2);
														prepStmt6.setString(IPixB2BConstants.TWELVE, documentError.getErrorDescription());
														prepStmt6.setString(IPixB2BConstants.THIRTEEN, IPixB2BConstants.flag_N);
														qryParams = errorLogIdNextDB+","+runIdNextDB+","+IPixB2BConstants.ERROR_ID_0+","+baTransNameXml
															+","+baTransType+","+transStatusDTO.getFileName()+","+IPixB2BConstants.XML
															+","+trans_ref_id_1+","+trans_ref_id_2+","+trans_ref_label_1+","+trans_ref_label_2+","+documentError.getErrorDescription()+","+IPixB2BConstants.flag_N;
														recsNum = prepStmt6.executeUpdate();
														recsCount = recsCount + recsNum;
														DBUtils.close(prepStmt6);
													}
												}else{
													B2BLogger.info("BusinessAckProcessorDAOImpl.storeBusinessAcknowledgementDetails() - documentError is null");
												}
											} //end for loop - documentErrorList
										}else{
											B2BLogger.info("BusinessAckProcessorDAOImpl.storeBusinessAcknowledgementDetails() - documentErrorList is null or blank");
										}										
									}
									/** Commentd by Abhilasha
									sqlQuery = qry_insrt_pix_email_tran;
									prepStmt7 = dbCon.prepareStatement(sqlQuery);
									prepStmt7.clearParameters();				
									prepStmt7.setString(ONE, transIDXml);
									prepStmt7.setString(TWO, baTransType);
									prepStmt7.setString(THREE, transType_ACK);
									prepStmt7.setString(FOUR, B2B);
									prepStmt7.setLong(FIVE, runIdNextDB);
									prepStmt7.setString(SIX, senderSAN);
									prepStmt7.setString(SEVEN, trans_ref_label_1);
									prepStmt7.setString(EIGHT, trans_ref_label_2);
									prepStmt7.setString(NINE, trans_ref_id_1);
									prepStmt7.setString(TEN, trans_ref_id_2);
									prepStmt7.setString(ELEVEN, flag_N);
									prepStmt7.setString(TWELVE, receiverSAN);						
									qryParams = transIDXml+","+baTransType+","+transType_ACK+","+B2B
										+","+runIdNextDB+","+senderSAN+","+trans_ref_label_1+","+trans_ref_label_2
										+","+trans_ref_id_1+","+trans_ref_id_2+","+flag_N+","+receiverSAN;									
									recsNum = prepStmt7.executeUpdate();									
									recsCount = recsCount + recsNum;
									*/
									
									sqlQuery = qry_upd_pix_xmlgen_log;
									prepStmt8 = dbCon.prepareStatement(sqlQuery);
									prepStmt8.clearParameters();
									prepStmt8.setString(IPixB2BConstants.ONE, IPixB2BConstants.status_KS);
									prepStmt8.setString(IPixB2BConstants.TWO, transIDXml);
									prepStmt8.setString(IPixB2BConstants.THREE, IPixB2BConstants.B2B);
									qryParams = IPixB2BConstants.status_KS+","+transIDXml+","+IPixB2BConstants.B2B;
									recsNum = prepStmt8.executeUpdate();
									recsCount = recsCount + recsNum;
									DBUtils.close(prepStmt8);
								}else{
									B2BLogger.info("BusinessAckProcessorDAOImpl.storeBusinessAcknowledgementDetails() - runIdNextDB is null");
								}
								dbCon.commit();
							}else{
								B2BLogger.info("BusinessAckProcessorDAOImpl.storeBusinessAcknowledgementDetails() - transIDXml is null or blank");
							}
						}else{
							B2BLogger.info("BusinessAckProcessorDAOImpl.storeBusinessAcknowledgementDetails() - baDTO is null");
						}
					}else{
						B2BLogger.info("BusinessAckProcessorDAOImpl.storeBusinessAcknowledgementDetails() - senderSAN = "+senderSAN+" , receiverSAN = "+receiverSAN);
					}
				}else{
					B2BLogger.info("BusinessAckProcessorDAOImpl.storeBusinessAcknowledgementDetails() - transmissionInfoList is null or blank");
				}
			}else{
				B2BLogger.info("BusinessAckProcessorDAOImpl.storeBusinessAcknowledgementDetails() - pneDTO is null");
			}
			
			B2BLogger.info("BusinessAckProcessorDAOImpl.storeBusinessAcknowledgementDetails() - recsCount = "+recsCount);
			if(recsCount > 0)
				baInsStatus = IPixB2BConstants.flag_Y;
			
			B2BLogger.debug("**** BusinessAckProcessorDAOImpl.storeBusinessAcknowledgementDetails() method EXIT *******");
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+ "] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			
			baInsStatus = StringUtils.getStackTrace(e);
			if(baInsStatus != null && baInsStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				baInsStatus = baInsStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}			
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: ",e);
			if(baInsStatus != null && baInsStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				baInsStatus = baInsStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("B2BException :: ",e1);
			}
		} finally{	
			/*DBUtils.close(prepStmt1);
			DBUtils.close(resultSet1);		
			DBUtils.close(prepStmt2);*/
			DBUtils.close(prepStmt3);
			DBUtils.close(prepStmt4);
			DBUtils.close(resultSet4);
			DBUtils.close(prepStmt5);
			DBUtils.close(resultSet5);
			DBUtils.close(prepStmt6);
			DBUtils.close(prepStmt8);
			DBUtils.close(dbCon);
			
			sqlQuery			= null;
			qryParams			= null;			
			transmissionInfoList= null;
			b2bHelper			= null;
			hmSAN				= null;
			senderSAN			= null;		
			receiverSAN			= null;			
			baDTO 				= null;
			baStatusXml			= null;
			baTransNameXml		= null;
			baTransType			= null;
			documentReferenceList= null;
			documentReference	= null;
			transIDXml			= null;
			documentErrorList	= null;
			documentError 		= null; 
			trans_ref_id_1		= null;
			trans_ref_id_2		= null;
			trans_ref_label_1	= null;
			trans_ref_label_2	= null;
		}
		return baInsStatus;
	}
}