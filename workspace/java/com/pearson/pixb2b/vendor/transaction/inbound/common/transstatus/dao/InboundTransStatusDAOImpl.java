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
 * Title		: 	InboundTransStatusDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi 	30 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.common.transstatus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pearson.pixb2b.global.B2BGlobalData;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.DBUtils;
import com.pearson.pixb2b.vendor.utils.IConfigConstants;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * InboundTransStatusDAOImpl is an implementation class to communicate 
 * with the database and insert/update the transaction log/status of 
 * B2B Inbound transactions.
 * 
 * @author Yogesh Tyagi
 */
public class InboundTransStatusDAOImpl implements IInboundTransStatusDAO {
	private static final String qry_sel_pix_log_id_nextval = "SELECT SEQ_PIX_XMLREAD_LOG_ID.NEXTVAL log_id_next FROM DUAL";
	
	private static final String qry_sel_pix_trans_id_nextval = "SELECT SEQ_PIX_TRANS_LOG.NEXTVAL trans_id_next FROM DUAL";
	
//	private static final String qry_sel_pix_run_id_nextval = "SELECT SEQ_PIX_FEED_LOG_ID.NEXTVAL run_id_next FROM DUAL";
	
//	private static final String qry_ins_pix_feed_log = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_FEED_LOG"
//		+" (RUN_ID, FEED_TYPE, STAGE_TYPE, START_DATE_TIME, END_DATE_TIME, SUCCESS_FLAG)"
//		+" VALUES (?, ?, ?, SYSDATE, SYSDATE, ?)";
	
	private static final String qry_ins_pix_xmlread_log = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_XMLREAD_LOG"
		+" (LOG_ID, RUN_ID, FOLDER_NAME, COMPLETE_PATH, FILE_NAME, VENDOR_FILE_SIZE,"
		+" FTP_FILE_SIZE, FTP_FLAG, ACK_FLAG, XML_ARC_FLAG, XML_EMAIL_FLAG, TRANS_ID,"
		+" TRANSREF_ID_1, TRANSREF_ID_2, TRANSREF_LABEL_1, TRANSREF_LABEL_2,"
		+" PROCESS_TYPE, TRANS_TYPE, SAN, PUB_SAN, XML_READ_FLAG, DOCUMENT_NUMBER,DOCUMENT_DATE)"
		+" VALUES (?, ?, ?, ?, ?, ?,"
		+" ?, ?, ?, ?, ?, ?,"
		+" ?, ?, ?, ?,"
		+" ?, ?, ?, ?, ?,?,to_date(?,'MM/DD/YYYY'))";
	
	private static final String qry_ins_pix_xml_trans_mapping = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_XML_TRANS_MAPPING"
		+" (PIX_TRANS_ID, VENDOR_TRANS_ID, PROCESS_TYPE, CREATION_DATE_TIME, MOD_DATE_TIME, TRANS_HIST_NO)"
		+" VALUES (?, ?, ?, SYSDATE, SYSDATE, ?)";
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.common.transstatus.dao.IInboundTransStatusDAO#updateInboundTransStatus(java.sql.Connection, com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public long updateInboundTransStatus(Connection dbCon, TransactionStatusDTO transStatusDTO, 
			String senderSAN, String receiverSAN, 
			String transrefId1, String transrefId2, String transrefLabel1, String transrefLabel2,
			String docNumber,String docDate, String vendorTransId) throws SQLException, B2BException{
		
		PreparedStatement prepStmt1	= null;
		ResultSet resultSet1		= null;
		PreparedStatement prepStmt3	= null;
		ResultSet resultSet3		= null;
		PreparedStatement prepStmt5	= null;
		PreparedStatement prepStmt6	= null;
		String sqlQuery				= null;
		String qryParams			= null;
		
		long dbLogIdNext			= -1;
		long dbRunIdNext			= -1;
		long dbTransIdNext			= -1;
		
		try {
			B2BLogger.debug("InboundTransStatusDAOImpl.updateInboundTransStatus() method called");
			
			sqlQuery = qry_sel_pix_log_id_nextval;
			prepStmt1 = dbCon.prepareStatement(sqlQuery);
			prepStmt1.clearParameters();		
			resultSet1 = prepStmt1.executeQuery();
			while(resultSet1.next()){
				dbLogIdNext = resultSet1.getLong("log_id_next");
			}
			DBUtils.close(prepStmt1);
			DBUtils.close(resultSet1);
			B2BLogger.info("InboundTransStatusDAOImpl.updateInboundTransStatus() - dbLogIdNext = "+dbLogIdNext);
			
			if(dbLogIdNext > 0){
//				sqlQuery = qry_sel_pix_run_id_nextval;
//				prepStmt2 = dbCon.prepareStatement(sqlQuery);
//				prepStmt2.clearParameters();		
//				resultSet2 = prepStmt2.executeQuery();
//				while(resultSet2.next())
//					dbRunIdNext = resultSet2.getLong("run_id_next");
//				B2BLogger.info("InboundTransStatusDAOImpl.updateInboundTransStatus() - dbRunIdNext = "+dbRunIdNext);
				
				B2BGlobalData globalData = B2BGlobalData.getGlobalDataObject();
				dbRunIdNext = globalData.getRun_Id();
				
				if(dbRunIdNext > 0){
					sqlQuery = qry_sel_pix_trans_id_nextval;
					prepStmt3 = dbCon.prepareStatement(sqlQuery);
					prepStmt3.clearParameters();		
					resultSet3 = prepStmt3.executeQuery();
					while(resultSet3.next()){
						dbTransIdNext = resultSet3.getLong("trans_id_next");
					}
					DBUtils.close(prepStmt3);
					DBUtils.close(resultSet3);
					B2BLogger.info("InboundTransStatusDAOImpl.updateInboundTransStatus() - dbTransIdNext = "+dbTransIdNext);
					
					if(dbTransIdNext > 0){
//						sqlQuery = qry_ins_pix_feed_log;
//						prepStmt4 = dbCon.prepareStatement(sqlQuery);
//						prepStmt4.clearParameters();
//						prepStmt4.setLong(ONE, dbRunIdNext);
//						prepStmt4.setString(TWO, B2B);
//						prepStmt4.setString(THREE, VEN_TO_PIX);
//						prepStmt4.setString(FOUR, flag_S);
//						qryParams = dbRunIdNext+","+B2B+","+VEN_TO_PIX+","+flag_S;
//						prepStmt4.executeUpdate();						
						
						sqlQuery = qry_ins_pix_xmlread_log;
						prepStmt5 = dbCon.prepareStatement(sqlQuery);				
						prepStmt5.clearParameters();					
						prepStmt5.setLong(IPixB2BConstants.ONE, dbLogIdNext);
						prepStmt5.setLong(IPixB2BConstants.TWO, dbRunIdNext);
						prepStmt5.setString(IPixB2BConstants.THREE, transStatusDTO.getFolderName());
						prepStmt5.setString(IPixB2BConstants.FOUR, transStatusDTO.getCompletePath());					
						prepStmt5.setString(IPixB2BConstants.FIVE, transStatusDTO.getFileName());						
						prepStmt5.setString(IPixB2BConstants.SIX, transStatusDTO.getFileSize1());						
						prepStmt5.setString(IPixB2BConstants.SEVEN, transStatusDTO.getFileSize2());
						prepStmt5.setString(IPixB2BConstants.EIGHT, transStatusDTO.getStatusFTP());
						prepStmt5.setString(IPixB2BConstants.NINE, transStatusDTO.getStatusACK());						
						prepStmt5.setString(IPixB2BConstants.TEN, transStatusDTO.getStatusARC());						
						prepStmt5.setString(IPixB2BConstants.ELEVEN, transStatusDTO.getStatusMAIL());						
						prepStmt5.setLong(IPixB2BConstants.TWELVE, dbTransIdNext);
						prepStmt5.setString(IPixB2BConstants.THIRTEEN, transrefId1);
						prepStmt5.setString(IPixB2BConstants.FOURTEEN, transrefId2);
						prepStmt5.setString(IPixB2BConstants.FIFTEEN, transrefLabel1);
						prepStmt5.setString(IPixB2BConstants.SIXTEEN, transrefLabel2);						
						prepStmt5.setString(IPixB2BConstants.SEVENTEEN, transStatusDTO.getTransType());
						prepStmt5.setString(IPixB2BConstants.EIGHTEEN, IPixB2BConstants.B2B);
						prepStmt5.setString(IPixB2BConstants.NINETEEN, senderSAN);
						prepStmt5.setString(IPixB2BConstants.TWENTY, receiverSAN);
						prepStmt5.setString(IPixB2BConstants.TWENTYONE, transStatusDTO.getStatusREAD());
						prepStmt5.setString(IPixB2BConstants.TWENTYTWO,docNumber);
						prepStmt5.setString(IPixB2BConstants.TWENTYTHREE, docDate);
						
						qryParams = dbLogIdNext+","+dbRunIdNext+","+transStatusDTO.getFolderName()+","+transStatusDTO.getCompletePath()+","+transStatusDTO.getFileName()+","+transStatusDTO.getFileSize1()
							+","+transStatusDTO.getFileSize2()+","+transStatusDTO.getStatusFTP()+","+transStatusDTO.getStatusACK()+","+transStatusDTO.getStatusARC()+","+transStatusDTO.getStatusMAIL()+","+dbTransIdNext
							+","+transrefId1+","+transrefId2+","+transrefLabel1+","+transrefLabel2
							+","+transStatusDTO.getTransType()+","+IPixB2BConstants.B2B+","+senderSAN+","+receiverSAN+","+transStatusDTO.getStatusREAD()+","+docNumber+","+docDate;
						
						prepStmt5.executeUpdate();					
						DBUtils.close(prepStmt5);
						
						sqlQuery = qry_ins_pix_xml_trans_mapping;
						prepStmt6 = dbCon.prepareStatement(sqlQuery);				
						prepStmt6.clearParameters();					
						prepStmt6.setLong(IPixB2BConstants.ONE, dbTransIdNext);
						prepStmt6.setString(IPixB2BConstants.TWO, transStatusDTO.getDocumentNumber());
						prepStmt6.setString(IPixB2BConstants.THREE, transStatusDTO.getTransType());
						prepStmt6.setString(IPixB2BConstants.FOUR, vendorTransId);
						qryParams = dbTransIdNext+","+transStatusDTO.getDocumentNumber()+","+transStatusDTO.getTransType()+","+vendorTransId;
						prepStmt6.executeUpdate();	
						DBUtils.close(prepStmt6);
					}else{
						B2BLogger.info("InboundTransStatusDAOImpl.updateInboundTransStatus() - dbTransIdNext = "+dbTransIdNext);
					}
				}else{
					B2BLogger.info("InboundTransStatusDAOImpl.updateInboundTransStatus() - dbRunIdNext = "+dbRunIdNext);
				}
			}else{
				B2BLogger.info("InboundTransStatusDAOImpl.updateInboundTransStatus() - dbLogIdNext = "+dbLogIdNext);
			}
			
			B2BLogger.debug("InboundTransStatusDAOImpl.updateInboundTransStatus() method return");
		} catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: ",e);
			throw new B2BException("SQLException :: " + StringUtils.getStackTrace(e));
		} catch (Exception e) {
			B2BLogger.error("Exception :: ",e);
			throw new B2BException("B2BException :: " + StringUtils.getStackTrace(e));
		} finally{
			DBUtils.close(resultSet1);
			DBUtils.close(prepStmt1);
			DBUtils.close(resultSet3);
			DBUtils.close(prepStmt3);
			DBUtils.close(prepStmt5);
			DBUtils.close(prepStmt6);
			
			sqlQuery = null;
			qryParams= null;
		}
		return dbTransIdNext;
	}
}