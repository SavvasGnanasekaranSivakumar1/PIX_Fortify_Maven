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
 * Title		: 	ErrorDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	16 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.error.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pearson.pixb2b.global.B2BGlobalData;
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
 * IOutboundBusinessAckDAO is an interface to process the 
 * B2B Error handling for all Outbound/Inbound Transactions.
 * 
 * @author Yogesh Tyagi,Abhilasha Nigam
 */
public class ErrorDAOImpl implements IErrorDAO {
	private static final String qry_sel_pix_log_id_nextval = "SELECT SEQ_PIX_XMLREAD_LOG_ID.NEXTVAL log_id_next FROM DUAL";
	
//	private static final String qry_sel_pix_run_id_nextval = "SELECT SEQ_PIX_FEED_LOG_ID.NEXTVAL run_id_next FROM DUAL";
//	
//	private static final String qry_ins_pix_feed_log = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_FEED_LOG"
//		+" (RUN_ID, FEED_TYPE, STAGE_TYPE, START_DATE_TIME, END_DATE_TIME, SUCCESS_FLAG)"
//		+" VALUES (?, ?, ?, SYSDATE, SYSDATE, ?)";
	
	private static final String qry_ins_pix_xmlread_log = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_XMLREAD_LOG"
		+" (LOG_ID, RUN_ID, FOLDER_NAME, COMPLETE_PATH, FILE_NAME, VENDOR_FILE_SIZE,"
		+" FTP_FILE_SIZE, FTP_FLAG, ACK_FLAG, XML_ARC_FLAG, XML_EMAIL_FLAG, TRANS_ID,"
		+" TRANSREF_ID_1, TRANSREF_ID_2, TRANSREF_LABEL_1, TRANSREF_LABEL_2,"
		+" PROCESS_TYPE, TRANS_TYPE, SAN, PUB_SAN, XML_READ_FLAG,DOCUMENT_NUMBER,DOCUMENT_DATE)"
		+" VALUES (?, ?, ?, ?, ?, ?,"
		+" ?, ?, ?, ?, ?, ?,"
		+" ?, ?, ?, ?,"
		+" ?, ?, ?, ?, ?,?,to_date(?,'MM/DD/YYYY'))";
	
	//Query added by Abhilasha
	private static final String qry_updt_pix_xmlread_log = "UPDATE "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_XMLREAD_LOG SET"
		+" XML_READ_FLAG = 'RE',COMPLETE_PATH = ? WHERE  RUN_ID = ? ";   //AND TRANS_ID = ?
	
	
	private static final String qry_sel_pix_errlog_nextval = "select seq_pix_error_log_id.NEXTVAL error_log_id_next FROM DUAL";	
	
	private static final String qry_insrt_pix_error_log = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_ERROR_LOG"
		+" (ERROR_LOG_ID, RUN_ID, ERROR_ID, SOURCE_NAME, SOURCE_TYPE, DEST_NAME, DEST_TYPE,"
		+" TRANREF_ID_1, TRANREF_ID_2, TRANREF_LABEL_1, TRANREF_LABEL_2, ADDITIONAL_DESC,"
		+" CREATION_DATE_TIME, MOD_DATE_TIME, EMAIL_FLAG, TRANREF_ID_3, TRANREF_LABEL_3)"
		+" VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, SYSDATE, SYSDATE, ?, to_date(?,'MM/DD/YYYY'), ?)";
	

	private static final String qry_insrt_pix_xml_error_log = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_XML_ERROR_LOG"
	+" (ERROR_LOG_ID, TRAN_TYPE, TRANSACTION_HISTORY_NO, DOCUMENT_NO, DOCUMENT_ISSUE_DATE,"
	+" CREATION_DATE_TIME, MOD_DATE_TIME)"
	+" VALUES (?, ?, ?, ?, to_date(?,'MM/DD/YYYY'), SYSDATE, SYSDATE)";
	
	private static final String qry_ins_pix_xml_trans_mapping = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_XML_TRANS_MAPPING"
	+" (PIX_TRANS_ID, VENDOR_TRANS_ID, PROCESS_TYPE, CREATION_DATE_TIME, MOD_DATE_TIME, TRANS_HIST_NO)"
	+" VALUES (?, ?, ?, SYSDATE, SYSDATE, ?)";
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.shared.error.dao.IErrorDAO#storeInboundXmlErrorDetails(com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String storeInboundXmlErrorDetails(TransactionStatusDTO transStatusDTO, ArrayList errorList, String feedType, String stageType, String successFlag, PapiNetEnvelopeDTO pneDTO){
		Connection dbCon 			= null;
		String sqlQuery				= null;
		String qryParams			= null;
		int recsCount				= 0;
		int recsNum					= 0;
		
		PreparedStatement prepStmt1	= null;
		ResultSet resultSet1		= null;
		PreparedStatement prepStmt4	= null;
		PreparedStatement prepStmt5	= null;
		ResultSet resultSet5		= null;
		PreparedStatement prepStmt6	= null;
		PreparedStatement prepStmt7	= null;		
		
		ErrorDTO errorDTO			= null;
		long dbLogIdNext			= -1;
		long dbRunIdNext			= -1;
		B2BHelper b2bHelper			= null;
		String senderSAN			= null; 
		String receiverSAN			= null; 
		long errorLogIdNextDB		= -1;		
		String insStatus 			= IPixB2BConstants.flag_N;
		String xmlTransID 			= null;
		
		String transRefId1			= null;
		String transRefLabel1		= null;	
		String transRefId2			= null;
		String transRefLabel2		= null;

		try {
			B2BLogger.debug("****** ErrorDAOImpl.storeInboundXmlErrorDetails() method ENTERED *******");			
			
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}
					
			if(errorList != null && errorList.size()>0){
				dbCon.setAutoCommit(false);
				
				sqlQuery = qry_sel_pix_log_id_nextval;
				prepStmt1 = dbCon.prepareStatement(sqlQuery);
				prepStmt1.clearParameters();		
				resultSet1 = prepStmt1.executeQuery();
				while(resultSet1.next()){
					dbLogIdNext = resultSet1.getLong("log_id_next");
				}
				
				DBUtils.close(prepStmt1);
				DBUtils.close(resultSet1);
				B2BLogger.info("ErrorDAOImpl.storeInboundXmlErrorDetails() - dbLogIdNext = "+dbLogIdNext);
				
				if(dbLogIdNext > 0){
//					sqlQuery = qry_sel_pix_run_id_nextval;
//					prepStmt2 = dbCon.prepareStatement(sqlQuery);
//					prepStmt2.clearParameters();		
//					resultSet2 = prepStmt2.executeQuery();
//					while(resultSet2.next())
//						dbRunIdNext = resultSet2.getLong("run_id_next");
//					B2BLogger.info("ErrorDAOImpl.storeInboundXmlErrorDetails() - dbRunIdNext = "+dbRunIdNext);
					
					B2BGlobalData globalData = B2BGlobalData.getGlobalDataObject();
					dbRunIdNext = globalData.getRun_Id();
					
					if(dbRunIdNext > 0){
						b2bHelper = new B2BHelper();
						if(pneDTO==null)
							senderSAN = b2bHelper.addDashesInSAN(transStatusDTO.getSenderSAN());
						else{
							String trans_type = b2bHelper.getLongTransType(null, pneDTO.getPayloadInfo().getDocument().getDocumentName());
							xmlTransID = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.XML_TRANS_ID ,trans_type);
							senderSAN = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.VENDOR_SAN, trans_type);
							if(null!=senderSAN)
								senderSAN = b2bHelper.addDashesInSAN(senderSAN);
						}
						receiverSAN = b2bHelper.addDashesInSAN(transStatusDTO.getReceiverSAN());

						
						transRefId1	= B2BGlobalData.getGlobalDataObject().getInfoFromContext(Thread.currentThread().getName(), IPixB2BConstants.PO_NO);
						if(null!=transRefId1 && !"".equals(transRefId1)){
							transRefLabel1	= IPixB2BConstants.PO_NO;	
						}
						transRefId2	= B2BGlobalData.getGlobalDataObject().getInfoFromContext(Thread.currentThread().getName(), IPixB2BConstants.PRODUCT_CODE);
						if(null!=transRefId2 && !"".equals(transRefId2)){
							transRefLabel2 = IPixB2BConstants.PRODUCT_CODE;							
						}
//						sqlQuery = qry_ins_pix_feed_log;
//						prepStmt3 = dbCon.prepareStatement(sqlQuery);
//						prepStmt3.clearParameters();
//						prepStmt3.setLong(ONE, dbRunIdNext);
//						prepStmt3.setString(TWO, feedType);
//						prepStmt3.setString(THREE, stageType);
//						prepStmt3.setString(FOUR, successFlag);
//						qryParams = dbRunIdNext+","+feedType+","+stageType+","+successFlag;
//						recsNum = prepStmt3.executeUpdate();
//						recsCount = recsCount + recsNum;
						
						sqlQuery = qry_ins_pix_xmlread_log;
						prepStmt4 = dbCon.prepareStatement(sqlQuery);				
						prepStmt4.clearParameters();					
						prepStmt4.setLong(IPixB2BConstants.ONE, dbLogIdNext);
						prepStmt4.setLong(IPixB2BConstants.TWO, dbRunIdNext);
						prepStmt4.setString(IPixB2BConstants.THREE, transStatusDTO.getFolderName());
						prepStmt4.setString(IPixB2BConstants.FOUR, transStatusDTO.getCompletePath());					
						prepStmt4.setString(IPixB2BConstants.FIVE, transStatusDTO.getFileName());						
						prepStmt4.setString(IPixB2BConstants.SIX, transStatusDTO.getFileSize1());						
						prepStmt4.setString(IPixB2BConstants.SEVEN, transStatusDTO.getFileSize2());
						prepStmt4.setString(IPixB2BConstants.EIGHT, transStatusDTO.getStatusFTP());
						prepStmt4.setString(IPixB2BConstants.NINE, transStatusDTO.getStatusACK());						
						prepStmt4.setString(IPixB2BConstants.TEN, transStatusDTO.getStatusARC());						
						prepStmt4.setString(IPixB2BConstants.ELEVEN, transStatusDTO.getStatusMAIL());						
						prepStmt4.setString(IPixB2BConstants.TWELVE, transStatusDTO.getTransID());
						prepStmt4.setString(IPixB2BConstants.THIRTEEN, transRefId1);
						prepStmt4.setString(IPixB2BConstants.FOURTEEN, transRefId2);
						prepStmt4.setString(IPixB2BConstants.FIFTEEN, transRefLabel1);
						prepStmt4.setString(IPixB2BConstants.SIXTEEN, transRefLabel2);			
						prepStmt4.setString(IPixB2BConstants.SEVENTEEN, transStatusDTO.getTransType());
						prepStmt4.setString(IPixB2BConstants.EIGHTEEN, IPixB2BConstants.B2B);
						prepStmt4.setString(IPixB2BConstants.NINETEEN, senderSAN);
						prepStmt4.setString(IPixB2BConstants.TWENTY, receiverSAN);
						prepStmt4.setString(IPixB2BConstants.TWENTYONE, transStatusDTO.getStatusREAD());
						prepStmt4.setString(IPixB2BConstants.TWENTYTWO, transStatusDTO.getDocumentNumber());
						prepStmt4.setString(IPixB2BConstants.TWENTYTHREE, transStatusDTO.getDocumentDate());
						
						qryParams = dbLogIdNext+","+dbRunIdNext+","+transStatusDTO.getFolderName()+","+transStatusDTO.getCompletePath()+","+transStatusDTO.getFileName()+","+transStatusDTO.getFileSize1()
							+","+transStatusDTO.getFileSize2()+","+transStatusDTO.getStatusFTP()+","+transStatusDTO.getStatusACK()+","+transStatusDTO.getStatusARC()+","+transStatusDTO.getStatusMAIL()+","+transStatusDTO.getTransID()
							+","+null+","+null+","+null+","+null
							+","+transStatusDTO.getTransType()+","+IPixB2BConstants.B2B+","+senderSAN+","+receiverSAN+","+transStatusDTO.getStatusREAD()+","+transStatusDTO.getDocumentNumber()+","+transStatusDTO.getDocumentDate();
						
						recsNum = prepStmt4.executeUpdate();
						recsCount = recsCount + recsNum;
						
						DBUtils.close(prepStmt4);

						sqlQuery = qry_ins_pix_xml_trans_mapping;
						prepStmt6 = dbCon.prepareStatement(sqlQuery);				
						prepStmt6.clearParameters();					
						prepStmt6.setString(IPixB2BConstants.ONE, transStatusDTO.getTransID());          //PIX Trans ID
						prepStmt6.setString(IPixB2BConstants.TWO, transStatusDTO.getDocumentNumber());  //Vendor Trans ID
						prepStmt6.setString(IPixB2BConstants.THREE, transStatusDTO.getTransType());
						prepStmt6.setString(IPixB2BConstants.FOUR, xmlTransID);
						qryParams = transStatusDTO.getTransID()+","+transStatusDTO.getDocumentNumber()+","+transStatusDTO.getTransType()+","+xmlTransID;
						prepStmt6.executeUpdate();
						
						DBUtils.close(prepStmt6);
						
						for(int i=0; i<errorList.size(); i++){
							sqlQuery = qry_sel_pix_errlog_nextval;
							prepStmt5 = dbCon.prepareStatement(sqlQuery);
							prepStmt5.clearParameters();		
							resultSet5 = prepStmt5.executeQuery();
							while(resultSet5.next()){
								errorLogIdNextDB = resultSet5.getLong("error_log_id_next");
							}
							
							DBUtils.close(prepStmt5);
							DBUtils.close(resultSet5);
							B2BLogger.info("ErrorDAOImpl.storeInboundXmlErrorDetails() - errorLogIdNextDB = "+errorLogIdNextDB);
							
							errorDTO = (ErrorDTO)errorList.get(i);
							
							if(errorLogIdNextDB > 0 && errorDTO != null){
								sqlQuery = qry_insrt_pix_error_log;
								prepStmt6 = dbCon.prepareStatement(sqlQuery);
								prepStmt6.clearParameters();						
								prepStmt6.setLong(IPixB2BConstants.ONE, errorLogIdNextDB);
								prepStmt6.setLong(IPixB2BConstants.TWO, dbRunIdNext);
								prepStmt6.setString(IPixB2BConstants.THREE, errorDTO.getErrorID());						
								prepStmt6.setString(IPixB2BConstants.FOUR, errorDTO.getSourceName());
								prepStmt6.setString(IPixB2BConstants.FIVE, errorDTO.getSourceType());						
								prepStmt6.setString(IPixB2BConstants.SIX, errorDTO.getDestName());
								prepStmt6.setString(IPixB2BConstants.SEVEN, errorDTO.getDestType());
								prepStmt6.setString(IPixB2BConstants.EIGHT, errorDTO.getTransRefId_1());
								prepStmt6.setString(IPixB2BConstants.NINE, errorDTO.getTransRefId_2());
								prepStmt6.setString(IPixB2BConstants.TEN, errorDTO.getTransRefLabel_1());
								prepStmt6.setString(IPixB2BConstants.ELEVEN, errorDTO.getTransRefLabel_2());
								prepStmt6.setString(IPixB2BConstants.TWELVE, errorDTO.getErrorDescription());
								prepStmt6.setString(IPixB2BConstants.THIRTEEN, errorDTO.getEmailFlag());
								prepStmt6.setString(IPixB2BConstants.FOURTEEN, errorDTO.getTransRefId_3());
								prepStmt6.setString(IPixB2BConstants.FIFTEEN, errorDTO.getTransRefLabel_3());
								
								qryParams = errorLogIdNextDB+","+dbRunIdNext+","+errorDTO.getErrorID()
									+","+errorDTO.getSourceName()+","+errorDTO.getSourceType()
									+","+errorDTO.getDestName()+","+errorDTO.getDestType()
									+","+errorDTO.getTransRefId_1()+","+errorDTO.getTransRefId_2()
									+","+errorDTO.getTransRefLabel_1()+","+errorDTO.getTransRefLabel_2()
									+","+errorDTO.getErrorDescription()+","+errorDTO.getEmailFlag()
									+","+errorDTO.getTransRefId_3()+","+errorDTO.getTransRefLabel_3();
								
								recsNum = prepStmt6.executeUpdate();
								recsCount = recsCount + recsNum;
								
								DBUtils.close(prepStmt6);
								//verify this check below
								if(IPixB2BConstants.transType_USG.equals(transStatusDTO.getTransType())){
									sqlQuery = qry_insrt_pix_xml_error_log;
									prepStmt7 = dbCon.prepareStatement(sqlQuery);
									prepStmt7.clearParameters();	
									prepStmt7.setLong(IPixB2BConstants.ONE, errorLogIdNextDB);
									prepStmt7.setString(IPixB2BConstants.TWO, "USAGE_XML");
									prepStmt7.setString(IPixB2BConstants.THREE, xmlTransID);		
									prepStmt7.setString(IPixB2BConstants.FOUR, transStatusDTO.getDocumentNumber());
									prepStmt7.setString(IPixB2BConstants.FIVE, transStatusDTO.getDocumentDate());						

									qryParams = errorLogIdNextDB+","+"USAGE_XML"+","+xmlTransID
									+","+transStatusDTO.getDocumentNumber()+","+transStatusDTO.getDocumentDate();
									
									recsNum = prepStmt7.executeUpdate();
									recsCount = recsCount + recsNum;
									DBUtils.close(prepStmt7);
								}

								
							}else{
								B2BLogger.info("ErrorDAOImpl.storeInboundXmlErrorDetails() - errorLogIdNextDB = "+errorLogIdNextDB+" , errorDTO = "+errorDTO);
							}
						} //end for loop - errorList					
					}else{
						B2BLogger.info("ErrorDAOImpl.storeInboundXmlErrorDetails() - dbRunIdNext = "+dbRunIdNext);
					}
					dbCon.commit();
				}else{
					B2BLogger.info("ErrorDAOImpl.storeInboundXmlErrorDetails() - dbLogIdNext = "+dbLogIdNext);
				}				
			}else{
				B2BLogger.info("ErrorDAOImpl.storeInboundXmlErrorDetails() - errorList size = "+(errorList != null ? errorList.size() : errorList));
			}
			
			B2BLogger.info("ErrorDAOImpl.storeInboundXmlErrorDetails() - recsCount = "+recsCount);
			if(recsCount > 0)
				insStatus = IPixB2BConstants.flag_Y;
			
			B2BLogger.debug("**** ErrorDAOImpl.storeInboundXmlErrorDetails() method EXIT *******");
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+ "] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			insStatus = StringUtils.getStackTrace(e);
			if(insStatus != null && insStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				insStatus = insStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}			
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
			insStatus = StringUtils.getStackTrace(e);
			B2BLogger.error("Exception",e);
			if(insStatus != null && insStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				insStatus = insStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}
		} finally{	
			DBUtils.close(prepStmt1);
			DBUtils.close(resultSet1);
			DBUtils.close(prepStmt4);
			DBUtils.close(prepStmt5);
			DBUtils.close(resultSet5);
			DBUtils.close(prepStmt6);
			DBUtils.close(prepStmt7);
			DBUtils.close(dbCon);
			
			sqlQuery	= null;
			qryParams	= null;
			errorDTO	= null;
			b2bHelper	= null;
			senderSAN	= null; 
			receiverSAN	= null; 
		}
		return insStatus;
	}
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.shared.error.dao.IErrorDAO#storeOutboundXmlErrorDetails(java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String storeOutboundXmlErrorDetails(ArrayList errorList, String feedType, String stageType, String successFlag){
		Connection dbCon 			= null;
		String sqlQuery				= null;
		String qryParams			= null;
		int recsCount				= 0;
		int recsNum					= 0;
		PreparedStatement prepStmt3	= null;
		ResultSet resultSet3		= null;
		PreparedStatement prepStmt4	= null;
		
		ErrorDTO errorDTO			= null;
		long runIdNextDB			= -1;
		long errorLogIdNextDB		= -1;		
		String insStatus 			= IPixB2BConstants.flag_N;

		try {
			B2BLogger.debug("****** ErrorDAOImpl.storeOutboundXmlErrorDetails() method ENTERED *******");			
			
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}
					
			if(errorList != null && errorList.size()>0){
				dbCon.setAutoCommit(false);
				
				B2BGlobalData globalData = B2BGlobalData.getGlobalDataObject();
				runIdNextDB = globalData.getRun_Id();
				
//				sqlQuery = qry_sel_pix_run_id_nextval;
//				prepStmt1 = dbCon.prepareStatement(sqlQuery);
//				prepStmt1.clearParameters();		
//				resultSet1 = prepStmt1.executeQuery();
//				while(resultSet1.next())
//					runIdNextDB = resultSet1.getLong("run_id_next");
//				B2BLogger.info("ErrorDAOImpl.storeOutboundXmlErrorDetails() - runIdNextDB = "+runIdNextDB);
				
				if(runIdNextDB > 0){
//					sqlQuery = qry_ins_pix_feed_log;
//					prepStmt2 = dbCon.prepareStatement(sqlQuery);
//					prepStmt2.clearParameters();
//					prepStmt2.setLong(ONE, runIdNextDB);
//					prepStmt2.setString(TWO, feedType);
//					prepStmt2.setString(THREE, stageType);
//					prepStmt2.setString(FOUR, successFlag);
//					qryParams = runIdNextDB+","+feedType+","+stageType+","+successFlag;
//					recsNum = prepStmt2.executeUpdate();
//					recsCount = recsCount + recsNum;
					
					for(int i=0; i<errorList.size(); i++){
						sqlQuery = qry_sel_pix_errlog_nextval;
						prepStmt3 = dbCon.prepareStatement(sqlQuery);
						prepStmt3.clearParameters();		
						resultSet3 = prepStmt3.executeQuery();
						while(resultSet3.next()){
							errorLogIdNextDB = resultSet3.getLong("error_log_id_next");
						}
						DBUtils.close(prepStmt3);
						DBUtils.close(resultSet3);
						B2BLogger.info("ErrorDAOImpl.storeOutboundXmlErrorDetails() - errorLogIdNextDB = "+errorLogIdNextDB);
						
						errorDTO = (ErrorDTO)errorList.get(i);
						
						if(errorLogIdNextDB > 0 && errorDTO != null){
							sqlQuery = qry_insrt_pix_error_log;
							prepStmt4 = dbCon.prepareStatement(sqlQuery);
							prepStmt4.clearParameters();						
							prepStmt4.setLong(IPixB2BConstants.ONE, errorLogIdNextDB);
							prepStmt4.setLong(IPixB2BConstants.TWO, runIdNextDB);
							prepStmt4.setString(IPixB2BConstants.THREE, errorDTO.getErrorID());						
							prepStmt4.setString(IPixB2BConstants.FOUR, errorDTO.getSourceName());
							prepStmt4.setString(IPixB2BConstants.FIVE, errorDTO.getSourceType());						
							prepStmt4.setString(IPixB2BConstants.SIX, errorDTO.getDestName());
							prepStmt4.setString(IPixB2BConstants.SEVEN, errorDTO.getDestType());
							prepStmt4.setString(IPixB2BConstants.EIGHT, errorDTO.getTransRefId_1());
							prepStmt4.setString(IPixB2BConstants.NINE, errorDTO.getTransRefId_2());
							prepStmt4.setString(IPixB2BConstants.TEN, errorDTO.getTransRefLabel_1());
							prepStmt4.setString(IPixB2BConstants.ELEVEN, errorDTO.getTransRefLabel_2());
							prepStmt4.setString(IPixB2BConstants.TWELVE, errorDTO.getErrorDescription());
							prepStmt4.setString(IPixB2BConstants.THIRTEEN, errorDTO.getEmailFlag());
							prepStmt4.setString(IPixB2BConstants.FOURTEEN, errorDTO.getTransRefId_3());
							prepStmt4.setString(IPixB2BConstants.FIFTEEN, errorDTO.getTransRefLabel_3());
							qryParams = errorLogIdNextDB+","+runIdNextDB+","+errorDTO.getErrorID()
								+","+	errorDTO.getSourceName()+","+errorDTO.getSourceType()
								+","+errorDTO.getDestName()+","+errorDTO.getDestType()
								+","+errorDTO.getTransRefId_1()+","+errorDTO.getTransRefId_2()
								+","+errorDTO.getTransRefLabel_1()+","+errorDTO.getTransRefLabel_2()
								+","+errorDTO.getErrorDescription()+","+errorDTO.getEmailFlag();
							recsNum = prepStmt4.executeUpdate();
							recsCount = recsCount + recsNum;
							DBUtils.close(prepStmt4);
						}else{
							B2BLogger.info("ErrorDAOImpl.storeOutboundXmlErrorDetails() - errorLogIdNextDB = "+errorLogIdNextDB+" , errorDTO = "+errorDTO);
						}
					} //end for loop - errorList					
				}else{
					B2BLogger.info("ErrorDAOImpl.storeOutboundXmlErrorDetails() - runIdNextDB = "+runIdNextDB);
				}
				dbCon.commit();
			}else{
				B2BLogger.info("ErrorDAOImpl.storeOutboundXmlErrorDetails() - errorList size = "+(errorList != null ? errorList.size() : errorList));
			}
			
			B2BLogger.info("ErrorDAOImpl.storeOutboundXmlErrorDetails() - recsCount = "+recsCount);
			if(recsCount > 0)
				insStatus = IPixB2BConstants.flag_Y;
			
			B2BLogger.debug("**** ErrorDAOImpl.storeOutboundXmlErrorDetails() method EXIT *******");
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+ "] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			insStatus = StringUtils.getStackTrace(e);
			if(insStatus != null && insStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				insStatus = insStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}			
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
			insStatus = StringUtils.getStackTrace(e);
			B2BLogger.error("Exception",e);
			if(insStatus != null && insStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				insStatus = insStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}
		} finally{	
			DBUtils.close(prepStmt3);
			DBUtils.close(resultSet3);
			DBUtils.close(prepStmt4);
			DBUtils.close(dbCon);
			
			sqlQuery	= null;
			qryParams	= null;
			errorDTO	= null;
		}
		return insStatus;
	}



	//Method added by Abhilasha for Invoice Error Handling

	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.shared.error.dao.IErrorDAO#storeInboundXmlErrorDetails(com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String storeInvoiceXmlErrorDetails(TransactionStatusDTO transStatusDTO, ArrayList errorList, String feedType, String stageType, String successFlag){  //pne argument removed
		Connection dbCon 			= null;
		String sqlQuery				= null;
		String qryParams			= null;
		int recsCount				= 0;
		int recsNum					= 0;		
		PreparedStatement prepStmt	= null;		
		PreparedStatement prepStmt0	= null;
		ResultSet resultSet0		= null;
		PreparedStatement prepStmt1	= null;
		ErrorDTO errorDTO			= null;		
		long dbRunId			= -1;
		B2BHelper b2bHelper			= null;
		String senderSAN			= null; 
		String receiverSAN			= null; 
		long errorLogIdNextDB		= -1;		
		String insStatus 			= IPixB2BConstants.flag_N;
		String xmlTransID 			= null;
		
		try {
			B2BLogger.debug("****** ErrorDAOImpl.storeInboundXmlErrorDetails() method ENTERED *******");			
			
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}
					
			if(errorList != null && errorList.size()>0){
				dbCon.setAutoCommit(false);
					
					B2BGlobalData globalData = B2BGlobalData.getGlobalDataObject();
					dbRunId = globalData.getRun_Id();
					
					if(dbRunId > 0){
						//b2bHelper = new B2BHelper();
						//xmlTransID = transStatusDTO .getTransID();
												
						sqlQuery = qry_updt_pix_xmlread_log;
						prepStmt = dbCon.prepareStatement(sqlQuery);				
						prepStmt.clearParameters();					
						prepStmt.setString(IPixB2BConstants.ONE,transStatusDTO.getCompletePath());
						prepStmt.setLong(IPixB2BConstants.TWO, dbRunId);						
						qryParams = transStatusDTO.getCompletePath()+","+dbRunId;
						recsNum = prepStmt.executeUpdate();
						recsCount = recsCount + recsNum;						
						DBUtils.close(prepStmt);
						
						for(int i=0; i<errorList.size(); i++){	
							
							sqlQuery = qry_sel_pix_errlog_nextval;
							prepStmt0 = dbCon.prepareStatement(sqlQuery);
							prepStmt0.clearParameters();		
							resultSet0 = prepStmt0.executeQuery();
							while(resultSet0.next()){
								errorLogIdNextDB = resultSet0.getLong("error_log_id_next");
							}
							DBUtils.close(prepStmt0);
							DBUtils.close(resultSet0);
							B2BLogger.info("ErrorDAOImpl.storeInvoiceXmlErrorDetails() - errorLogIdNextDB = "+errorLogIdNextDB);
							
							
							errorDTO = (ErrorDTO)errorList.get(i);
							
							if(errorLogIdNextDB > 0 && errorDTO != null){
								sqlQuery = qry_insrt_pix_error_log;
								prepStmt1 = dbCon.prepareStatement(sqlQuery);
								prepStmt1.clearParameters();						
								prepStmt1.setLong(IPixB2BConstants.ONE, errorLogIdNextDB);
								prepStmt1.setLong(IPixB2BConstants.TWO, dbRunId);
								prepStmt1.setString(IPixB2BConstants.THREE, errorDTO.getErrorID());						
								prepStmt1.setString(IPixB2BConstants.FOUR, errorDTO.getSourceName());
								prepStmt1.setString(IPixB2BConstants.FIVE, errorDTO.getSourceType());						
								prepStmt1.setString(IPixB2BConstants.SIX, errorDTO.getDestName());
								prepStmt1.setString(IPixB2BConstants.SEVEN, errorDTO.getDestType());
								prepStmt1.setString(IPixB2BConstants.EIGHT, errorDTO.getTransRefId_1());
								prepStmt1.setString(IPixB2BConstants.NINE, errorDTO.getTransRefId_2());
								prepStmt1.setString(IPixB2BConstants.TEN, errorDTO.getTransRefLabel_1());
								prepStmt1.setString(IPixB2BConstants.ELEVEN, errorDTO.getTransRefLabel_2());
								prepStmt1.setString(IPixB2BConstants.TWELVE, errorDTO.getErrorDescription());
								prepStmt1.setString(IPixB2BConstants.THIRTEEN, errorDTO.getEmailFlag());
								prepStmt1.setString(IPixB2BConstants.FOURTEEN, errorDTO.getTransRefId_3());
								prepStmt1.setString(IPixB2BConstants.FIFTEEN, errorDTO.getTransRefLabel_3());
								
								qryParams = errorLogIdNextDB+","+dbRunId+","+errorDTO.getErrorID()
									+","+errorDTO.getSourceName()+","+errorDTO.getSourceType()
									+","+errorDTO.getDestName()+","+errorDTO.getDestType()
									+","+errorDTO.getTransRefId_1()+","+errorDTO.getTransRefId_2()
									+","+errorDTO.getTransRefLabel_1()+","+errorDTO.getTransRefLabel_2()
									+","+errorDTO.getErrorDescription()+","+errorDTO.getEmailFlag()
									+","+errorDTO.getTransRefId_3()+","+errorDTO.getTransRefLabel_3();
								
								recsNum = prepStmt1.executeUpdate();
								recsCount = recsCount + recsNum;
								DBUtils.close(prepStmt1);
							}else{
								B2BLogger.info("ErrorDAOImpl.storeInvoiceXmlErrorDetails() - errorLogIdNextDB = "+errorLogIdNextDB+" , errorDTO = "+errorDTO);
							}
						} //end for loop - errorList					
					}else{
						B2BLogger.info("ErrorDAOImpl.storeInvoiceXmlErrorDetails() - dbRunId = "+dbRunId);
					}
					dbCon.commit();
								
			}else{
				B2BLogger.info("ErrorDAOImpl.storeInvoiceXmlErrorDetails() - errorList size = "+(errorList != null ? errorList.size() : errorList));
			}
			
			B2BLogger.info("ErrorDAOImpl.storeInvoiceXmlErrorDetails() - recsCount = "+recsCount);
			if(recsCount > 0)
				insStatus = IPixB2BConstants.flag_Y;
			
			B2BLogger.debug("**** ErrorDAOImpl.storeInvoiceXmlErrorDetails() method EXIT *******");
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+ "] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			insStatus = StringUtils.getStackTrace(e);
			if(insStatus != null && insStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				insStatus = insStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}			
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
			insStatus = StringUtils.getStackTrace(e);
			B2BLogger.error("Exception",e);
			if(insStatus != null && insStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				insStatus = insStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}
		} finally{	
			DBUtils.close(prepStmt);			
			DBUtils.close(prepStmt0);
			DBUtils.close(resultSet0);		
			DBUtils.close(prepStmt1);	
			DBUtils.close(dbCon);
			
			sqlQuery	= null;
			qryParams	= null;
			errorDTO	= null;
			b2bHelper	= null;
			senderSAN	= null; 
			receiverSAN	= null; 
		}
		return insStatus;
	}
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.shared.error.dao.IErrorDAO#storeUsageXmlErrorDetails(com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String storeUsageXmlErrorDetails(TransactionStatusDTO transStatusDTO, ArrayList errorList, String feedType, String stageType, String successFlag, PapiNetEnvelopeDTO pneDTO){
		Connection dbCon 			= null;
		String sqlQuery				= null;
		String qryParams			= null;
		int recsCount				= 0;
		int recsNum					= 0;		
		PreparedStatement prepStmt	= null;		
		ResultSet resultSet			= null;
		PreparedStatement prepStmt1	= null;
		PreparedStatement prepStmt2	= null;
		ErrorDTO errorDTO			= null;		
		long dbRunId				= -1;
		B2BHelper b2bHelper			= null;
		long errorLogIdNextDB		= -1;		
		String insStatus 			= IPixB2BConstants.flag_N;
		String xmlTransID			= null;
		
		try {
			B2BLogger.debug("****** ErrorDAOImpl.storeUsageXmlErrorDetails() method ENTERED *******");			
			
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}
					
			if(errorList != null && errorList.size()>0){
				dbCon.setAutoCommit(false);
					
					B2BGlobalData globalData = B2BGlobalData.getGlobalDataObject();
					dbRunId = globalData.getRun_Id();
					
					if(dbRunId > 0){
						b2bHelper = new B2BHelper();
											
						for(int i=0; i<errorList.size(); i++){	
							
							sqlQuery = qry_sel_pix_errlog_nextval;
							prepStmt = dbCon.prepareStatement(sqlQuery);
							prepStmt.clearParameters();		
							resultSet = prepStmt.executeQuery();
							while(resultSet.next()){
								errorLogIdNextDB = resultSet.getLong("error_log_id_next");
							}
							DBUtils.close(prepStmt);
							DBUtils.close(resultSet);
							B2BLogger.info("ErrorDAOImpl.storeUsageXmlErrorDetails() - errorLogIdNextDB = "+errorLogIdNextDB);
							
							errorDTO = (ErrorDTO)errorList.get(i);

							if(errorLogIdNextDB > 0 && errorDTO != null){
								sqlQuery = qry_insrt_pix_error_log;
								prepStmt1 = dbCon.prepareStatement(sqlQuery);
								prepStmt1.clearParameters();						
								prepStmt1.setLong(IPixB2BConstants.ONE, errorLogIdNextDB);
								prepStmt1.setLong(IPixB2BConstants.TWO, dbRunId);
								prepStmt1.setString(IPixB2BConstants.THREE, errorDTO.getErrorID());						
								prepStmt1.setString(IPixB2BConstants.FOUR, errorDTO.getSourceName());
								prepStmt1.setString(IPixB2BConstants.FIVE, errorDTO.getSourceType());						
								prepStmt1.setString(IPixB2BConstants.SIX, errorDTO.getDestName());
								prepStmt1.setString(IPixB2BConstants.SEVEN, errorDTO.getDestType());
								prepStmt1.setString(IPixB2BConstants.EIGHT, errorDTO.getTransRefId_1());
								prepStmt1.setString(IPixB2BConstants.NINE, errorDTO.getTransRefId_2());
								prepStmt1.setString(IPixB2BConstants.TEN, errorDTO.getTransRefLabel_1());
								prepStmt1.setString(IPixB2BConstants.ELEVEN, errorDTO.getTransRefLabel_2());
								prepStmt1.setString(IPixB2BConstants.TWELVE, errorDTO.getErrorDescription());
								prepStmt1.setString(IPixB2BConstants.THIRTEEN, errorDTO.getEmailFlag());
								prepStmt1.setString(IPixB2BConstants.FOURTEEN, errorDTO.getTransRefId_3());
								prepStmt1.setString(IPixB2BConstants.FIFTEEN, errorDTO.getTransRefLabel_3());
								
								qryParams = errorLogIdNextDB+","+dbRunId+","+errorDTO.getErrorID()
									+","+errorDTO.getSourceName()+","+errorDTO.getSourceType()
									+","+errorDTO.getDestName()+","+errorDTO.getDestType()
									+","+errorDTO.getTransRefId_1()+","+errorDTO.getTransRefId_2()
									+","+errorDTO.getTransRefLabel_1()+","+errorDTO.getTransRefLabel_2()
									+","+errorDTO.getErrorDescription()+","+errorDTO.getEmailFlag()
									+","+errorDTO.getTransRefId_3()+","+errorDTO.getTransRefLabel_3();
								
								recsNum = prepStmt1.executeUpdate();
								recsCount = recsCount + recsNum;
								
								DBUtils.close(prepStmt1);
								
								String trans_type = b2bHelper.getLongTransType(null, pneDTO.getPayloadInfo().getDocument().getDocumentName());
								xmlTransID = b2bHelper.getPropertyFromEnvelope(pneDTO, IPixB2BConstants.XML_TRANS_ID ,trans_type);
								
								sqlQuery = qry_insrt_pix_xml_error_log;
								prepStmt2 = dbCon.prepareStatement(sqlQuery);
								prepStmt2.clearParameters();	
								prepStmt2.setLong(IPixB2BConstants.ONE, errorLogIdNextDB);
								prepStmt2.setString(IPixB2BConstants.TWO, "USAGE_XML");
								prepStmt2.setString(IPixB2BConstants.THREE, xmlTransID);		
								prepStmt2.setString(IPixB2BConstants.FOUR, transStatusDTO.getDocumentNumber());
								prepStmt2.setString(IPixB2BConstants.FIVE, transStatusDTO.getDocumentDate());						

								qryParams = errorLogIdNextDB+","+"USAGE_XML"+","+xmlTransID
								+","+transStatusDTO.getDocumentNumber()+","+transStatusDTO.getDocumentDate();
								
								recsNum = prepStmt2.executeUpdate();
								recsCount = recsCount + recsNum;
								DBUtils.close(prepStmt2);
							}else{
								B2BLogger.info("ErrorDAOImpl.storeUsageXmlErrorDetails() - errorLogIdNextDB = "+errorLogIdNextDB+" , errorDTO = "+errorDTO);
							}
						} //end for loop - errorList					
					}else{
						B2BLogger.info("ErrorDAOImpl.storeUsageXmlErrorDetails() - dbRunId = "+dbRunId);
					}
					dbCon.commit();
								
			}else{
				B2BLogger.info("ErrorDAOImpl.storeUsageXmlErrorDetails() - errorList size = "+(errorList != null ? errorList.size() : errorList));
			}
			
			B2BLogger.info("ErrorDAOImpl.storeUsageXmlErrorDetails() - recsCount = "+recsCount);
			if(recsCount > 0)
				insStatus = IPixB2BConstants.flag_Y;
			
			B2BLogger.debug("**** ErrorDAOImpl.storeUsageXmlErrorDetails() method EXIT *******");
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+ "] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			insStatus = StringUtils.getStackTrace(e);
			if(insStatus != null && insStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				insStatus = insStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}			
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
			insStatus = StringUtils.getStackTrace(e);
			B2BLogger.error("Exception",e);
			if(insStatus != null && insStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				insStatus = insStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}
		} finally{	
			DBUtils.close(prepStmt);			
			DBUtils.close(prepStmt1);
			DBUtils.close(prepStmt2);
			DBUtils.close(resultSet);
			DBUtils.close(dbCon);
			
			sqlQuery	= null;
			qryParams	= null;
			errorDTO	= null;
			b2bHelper	= null;
		}
		return insStatus;
	}

//	Method added by Abhilasha for GoodsReceipt Error Handling

	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.shared.error.dao.IErrorDAO#storeInboundXmlErrorDetails(com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String)
	 */
	public String storeGRXmlErrorDetails(TransactionStatusDTO transStatusDTO, ArrayList errorList, String feedType, String stageType, String successFlag){  //pne argument removed
		Connection dbCon 			= null;
		String sqlQuery				= null;
		String qryParams			= null;
		int recsCount				= 0;
		int recsNum					= 0;		
		PreparedStatement prepStmt	= null;		
		PreparedStatement prepStmt2	= null;
		PreparedStatement prepStmt1	= null;
		ResultSet resultSet1		= null;				
		ErrorDTO errorDTO			= null;		
		long dbRunId			= -1;
		B2BHelper b2bHelper			= null;
		String senderSAN			= null; 
		String receiverSAN			= null; 
		long errorLogIdNextDB		= -1;		
		String insStatus 			= IPixB2BConstants.flag_N;
		
		try {
			B2BLogger.debug("****** ErrorDAOImpl.storeInboundXmlErrorDetails() method ENTERED *******");			
			
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}
					
			if(errorList != null && errorList.size()>0){
				dbCon.setAutoCommit(false);
					
					B2BGlobalData globalData = B2BGlobalData.getGlobalDataObject();
					dbRunId = globalData.getRun_Id();
					
					if(dbRunId > 0){
						//b2bHelper = new B2BHelper();
						//xmlTransID = transStatusDTO.getTransID();
												
						sqlQuery = qry_updt_pix_xmlread_log;
						prepStmt = dbCon.prepareStatement(sqlQuery);				
						prepStmt.clearParameters();					
						prepStmt.setString(IPixB2BConstants.ONE,transStatusDTO.getCompletePath());
						prepStmt.setLong(IPixB2BConstants.TWO, dbRunId);						
						qryParams = transStatusDTO.getCompletePath()+","+dbRunId;
						recsNum = prepStmt.executeUpdate();
						recsCount = recsCount + recsNum;	
						
						DBUtils.close(prepStmt);
						for(int i=0; i<errorList.size(); i++){	
							
							sqlQuery = qry_sel_pix_errlog_nextval;
							prepStmt1 = dbCon.prepareStatement(sqlQuery);
							prepStmt1.clearParameters();		
							resultSet1 = prepStmt1.executeQuery();
							while(resultSet1.next()){
								errorLogIdNextDB = resultSet1.getLong("error_log_id_next");
							}
							DBUtils.close(prepStmt1);
							DBUtils.close(resultSet1);	
							B2BLogger.info("ErrorDAOImpl.storeGRXmlErrorDetails() - errorLogIdNextDB = "+errorLogIdNextDB);
							
							errorDTO = (ErrorDTO)errorList.get(i);
							
							if(errorLogIdNextDB > 0 && errorDTO != null){
								sqlQuery = qry_insrt_pix_error_log;
								prepStmt2 = dbCon.prepareStatement(sqlQuery);
								prepStmt2.clearParameters();						
								prepStmt2.setLong(IPixB2BConstants.ONE, errorLogIdNextDB);
								prepStmt2.setLong(IPixB2BConstants.TWO, dbRunId);
								prepStmt2.setString(IPixB2BConstants.THREE, errorDTO.getErrorID());						
								prepStmt2.setString(IPixB2BConstants.FOUR, errorDTO.getSourceName());
								prepStmt2.setString(IPixB2BConstants.FIVE, errorDTO.getSourceType());						
								prepStmt2.setString(IPixB2BConstants.SIX, errorDTO.getDestName());
								prepStmt2.setString(IPixB2BConstants.SEVEN, errorDTO.getDestType());
								prepStmt2.setString(IPixB2BConstants.EIGHT, errorDTO.getTransRefId_1());
								prepStmt2.setString(IPixB2BConstants.NINE, errorDTO.getTransRefId_2());
								prepStmt2.setString(IPixB2BConstants.TEN, errorDTO.getTransRefLabel_1());
								prepStmt2.setString(IPixB2BConstants.ELEVEN, errorDTO.getTransRefLabel_2());
								prepStmt2.setString(IPixB2BConstants.TWELVE, errorDTO.getErrorDescription());
								prepStmt2.setString(IPixB2BConstants.THIRTEEN, errorDTO.getEmailFlag());
								prepStmt2.setString(IPixB2BConstants.FOURTEEN, errorDTO.getTransRefId_3());
								prepStmt2.setString(IPixB2BConstants.FIFTEEN, errorDTO.getTransRefLabel_3());
																						
								qryParams = errorLogIdNextDB+","+dbRunId+","+errorDTO.getErrorID()
									+","+errorDTO.getSourceName()+","+errorDTO.getSourceType()
									+","+errorDTO.getDestName()+","+errorDTO.getDestType()
									+","+errorDTO.getTransRefId_1()+","+errorDTO.getTransRefId_2()
									+","+errorDTO.getTransRefLabel_1()+","+errorDTO.getTransRefLabel_2()
									+","+errorDTO.getErrorDescription()+","+errorDTO.getEmailFlag()
									+","+errorDTO.getTransRefId_3()+","+errorDTO.getTransRefLabel_3();
								
								recsNum = prepStmt2.executeUpdate();
								recsCount = recsCount + recsNum;
								
								DBUtils.close(prepStmt2);
							}else{
								B2BLogger.info("ErrorDAOImpl.storeGRXmlErrorDetails() - errorLogIdNextDB = "+errorLogIdNextDB+" , errorDTO = "+errorDTO);
							}
						} //end for loop - errorList					
					}else{
						B2BLogger.info("ErrorDAOImpl.storeGRXmlErrorDetails() - dbRunId = "+dbRunId);
					}
					dbCon.commit();
								
			}else{
				B2BLogger.info("ErrorDAOImpl.storeGRXmlErrorDetails() - errorList size = "+(errorList != null ? errorList.size() : errorList));
			}
			
			B2BLogger.info("ErrorDAOImpl.storeGRXmlErrorDetails() - recsCount = "+recsCount);
			if(recsCount > 0)
				insStatus = IPixB2BConstants.flag_Y;
			
			B2BLogger.debug("**** ErrorDAOImpl.storeGRXmlErrorDetails() method EXIT *******");
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+ "] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			insStatus = StringUtils.getStackTrace(e);
			if(insStatus != null && insStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				insStatus = insStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}			
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
			insStatus = StringUtils.getStackTrace(e);
			B2BLogger.error("Exception",e);
			if(insStatus != null && insStatus.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				insStatus = insStatus.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}
		} finally{	
			DBUtils.close(prepStmt);			
			DBUtils.close(prepStmt1);
			DBUtils.close(resultSet1);	
			DBUtils.close(prepStmt2);	
			DBUtils.close(dbCon);
			
			sqlQuery	= null;
			qryParams	= null;
			errorDTO	= null;
			b2bHelper	= null;
			senderSAN	= null; 
			receiverSAN	= null; 
		}
		return insStatus;
	}
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.shared.error.dao.IErrorDAO#storeDAOErrorDetails(com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO, java.util.ArrayList, java.lang.String, java.lang.String, java.lang.String, com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO)
	 */
	public String storeDAOErrorDetails(TransactionStatusDTO transStatusDTO, ArrayList errorList, String feedType, String stageType, String successFlag, PapiNetEnvelopeDTO pneDTO){
		Connection dbCon 			= null;
		String sqlQuery				= null;
		String qryParams			= null;
		int recsCount				= 0;
		int recsNum					= 0;		
		PreparedStatement prepStmt	= null;		
		ResultSet resultSet			= null;
		PreparedStatement prepStmt1	= null;
		ErrorDTO errorDTO			= null;		
		long dbRunId				= -1;
		B2BHelper b2bHelper			= null;
		long errorLogIdNextDB		= -1;		
		String statusFlag 			= IPixB2BConstants.flag_N;
		
		try {
			B2BLogger.debug("****** ErrorDAOImpl.storeDAOErrorDetails() method ENTERED *******");			
			
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}
					
			if(errorList != null && errorList.size()>0){
				dbCon.setAutoCommit(false);
					
					B2BGlobalData globalData = B2BGlobalData.getGlobalDataObject();
					dbRunId = globalData.getRun_Id();
					
					if(dbRunId > 0){
						b2bHelper = new B2BHelper();
											
						for(int i=0; i<errorList.size(); i++){	
							
							sqlQuery = qry_sel_pix_errlog_nextval;
							prepStmt = dbCon.prepareStatement(sqlQuery);
							prepStmt.clearParameters();		
							resultSet = prepStmt.executeQuery();
							while(resultSet.next()){
								errorLogIdNextDB = resultSet.getLong("error_log_id_next");
							}
							DBUtils.close(prepStmt);
							DBUtils.close(resultSet);
							
							B2BLogger.info("ErrorDAOImpl.storeDAOErrorDetails() - errorLogIdNextDB = "+errorLogIdNextDB);
							errorDTO = (ErrorDTO)errorList.get(i);

							if(errorLogIdNextDB > 0 && errorDTO != null){
								sqlQuery = qry_insrt_pix_error_log;
								prepStmt1 = dbCon.prepareStatement(sqlQuery);
								prepStmt1.clearParameters();						
								prepStmt1.setLong(IPixB2BConstants.ONE, errorLogIdNextDB);
								prepStmt1.setLong(IPixB2BConstants.TWO, dbRunId);
								prepStmt1.setString(IPixB2BConstants.THREE, errorDTO.getErrorID());						
								prepStmt1.setString(IPixB2BConstants.FOUR, errorDTO.getSourceName());
								prepStmt1.setString(IPixB2BConstants.FIVE, errorDTO.getSourceType());						
								prepStmt1.setString(IPixB2BConstants.SIX, errorDTO.getDestName());
								prepStmt1.setString(IPixB2BConstants.SEVEN, errorDTO.getDestType());
								prepStmt1.setString(IPixB2BConstants.EIGHT, errorDTO.getTransRefId_1());
								prepStmt1.setString(IPixB2BConstants.NINE, errorDTO.getTransRefId_2());
								prepStmt1.setString(IPixB2BConstants.TEN, errorDTO.getTransRefLabel_1());
								prepStmt1.setString(IPixB2BConstants.ELEVEN, errorDTO.getTransRefLabel_2());
								prepStmt1.setString(IPixB2BConstants.TWELVE, errorDTO.getErrorDescription());
								prepStmt1.setString(IPixB2BConstants.THIRTEEN, errorDTO.getEmailFlag());
								prepStmt1.setString(IPixB2BConstants.FOURTEEN, errorDTO.getTransRefId_3());
								prepStmt1.setString(IPixB2BConstants.FIFTEEN, errorDTO.getTransRefLabel_3());
								
								qryParams = errorLogIdNextDB+","+dbRunId+","+errorDTO.getErrorID()
									+","+errorDTO.getSourceName()+","+errorDTO.getSourceType()
									+","+errorDTO.getDestName()+","+errorDTO.getDestType()
									+","+errorDTO.getTransRefId_1()+","+errorDTO.getTransRefId_2()
									+","+errorDTO.getTransRefLabel_1()+","+errorDTO.getTransRefLabel_2()
									+","+errorDTO.getErrorDescription()+","+errorDTO.getEmailFlag()
									+","+errorDTO.getTransRefId_3()+","+errorDTO.getTransRefLabel_3();
								
								recsNum = prepStmt1.executeUpdate();
								recsCount = recsCount + recsNum;
								DBUtils.close(prepStmt1);
							}else{
								B2BLogger.info("ErrorDAOImpl.storeDAOErrorDetails() - errorLogIdNextDB = "+errorLogIdNextDB+" , errorDTO = "+errorDTO);
							}
						} //end for loop - errorList					
					}else{
						B2BLogger.info("ErrorDAOImpl.storeDAOErrorDetails() - dbRunId = "+dbRunId);
					}
					dbCon.commit();
								
			}else{
				B2BLogger.info("ErrorDAOImpl.storeDAOErrorDetails() - errorList size = "+(errorList != null ? errorList.size() : errorList));
			}
			
			B2BLogger.info("ErrorDAOImpl.storeDAOErrorDetails() - recsCount = "+recsCount);
			if(recsCount > 0)
				statusFlag = IPixB2BConstants.flag_Y;
			
			B2BLogger.debug("**** ErrorDAOImpl.storeDAOErrorDetails() method EXIT *******");
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+ "] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			statusFlag = StringUtils.getStackTrace(e);
			if(statusFlag != null && statusFlag.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				statusFlag = statusFlag.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}			
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
			statusFlag = StringUtils.getStackTrace(e);
			B2BLogger.error("Exception",e);
			if(statusFlag != null && statusFlag.length() > IPixB2BConstants.ERROR_STRING_LENGTH )
				statusFlag = statusFlag.substring(0, IPixB2BConstants.ERROR_STRING_LENGTH);
			try {
				dbCon.rollback();
			} catch (SQLException e1) {
				B2BLogger.error("Exception",e);
			}
		} finally{	
			DBUtils.close(prepStmt);			
			DBUtils.close(prepStmt1);
			DBUtils.close(resultSet);
			DBUtils.close(dbCon);
			
			sqlQuery	= null;
			qryParams	= null;
			errorDTO	= null;
			b2bHelper	= null;
		}
		return statusFlag;
	}
	
}