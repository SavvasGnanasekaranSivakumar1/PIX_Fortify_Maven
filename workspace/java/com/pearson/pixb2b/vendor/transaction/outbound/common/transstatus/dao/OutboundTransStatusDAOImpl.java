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
 * Title		: 	OutboundTransStatusDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi 	30 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.common.transstatus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pearson.pixb2b.global.B2BGlobalData;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.DBUtils;
import com.pearson.pixb2b.vendor.utils.IConfigConstants;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * OutboundTransStatusDAOImpl is an implementation class to communicate 
 * with the database and update the transaction status of 
 * B2B Outbound transactions.
 * 
 * @author Yogesh Tyagi
 */
public class OutboundTransStatusDAOImpl implements IOutboundTransStatusDAO {
	private static final String qry_updt_pix_xmlgen_log = "UPDATE "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_XMLGEN_LOG"
		+" SET RUN_ID = ?, GEN_STATUS_FLAG = ?, GEN_FILE_NAME = ?, GEN_FILE_SIZE = ?,"
		+" FTP_STATUS_FLAG = ?,"
		+" ARC_FILE_SIZE = ?, ARC_STATUS_FLAG = ?,"
		+" MAIL_STATUS_FLAG = ?,"
		+" ACK_STATUS_FLAG = ?,"
		+" MOD_DATE_TIME = sysdate"
		+" WHERE TRANS_ID = ?"
		+" AND TRANS_TYPE = ?";
	
	private static final String qry_updt_pix_xmlread_log = "UPDATE "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_XMLREAD_LOG"
		+" SET ACK_FLAG = ?"
		+" WHERE TRANS_ID = ?"
		+" AND TRANS_TYPE = ?";
	
	private static final String qry_ins_pix_xml_ack_log = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_XML_ACK_LOG"
		+" (TRANS_ID, IN_OUT_TYPE, ACK_FILE, FILE_SIZE, CREATION_DATE_TIME, MOD_DATE_TIME)"
		+" VALUES (?, ?, ?, ?, SYSDATE, SYSDATE)";
			
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.outbound.common.transstatus.dao.IOutboundTransStatusDAO#updateOutboundTransStatus(java.util.ArrayList)
	 */
	public int updateOutboundTransStatus(ArrayList outTransList){
		Connection dbCon 			= null;
		PreparedStatement prepStmt 	= null;
		String qryParams			= null;
		TransactionStatusDTO transStatusDTO = null;
		int recsCount				= 0;
		int recsNum					= 0;
		long dbRunIdNext			= -1;

		try {
			B2BLogger.debug("OutboundTransStatusDAOImpl.updateOutboundTransStatus() method called");			
			if(outTransList != null && outTransList.size() > 0){
				dbCon = DBUtils.getDBConnection();			
				if (dbCon == null){
					B2BLogger.error("Error in database connection creation");
					return -1; 
				}
				dbCon.setAutoCommit(false);				
				
				prepStmt = dbCon.prepareStatement(qry_updt_pix_xmlgen_log);
				
				B2BGlobalData globalData = B2BGlobalData.getGlobalDataObject();
				dbRunIdNext = globalData.getRun_Id();
				
				for(int i=0; i<outTransList.size(); i++){
					prepStmt.clearParameters();
					transStatusDTO = (TransactionStatusDTO)outTransList.get(i);
					
					prepStmt.setLong(IPixB2BConstants.ONE, dbRunIdNext);
					prepStmt.setString(IPixB2BConstants.TWO, transStatusDTO.getStatusGEN());
					prepStmt.setString(IPixB2BConstants.THREE, transStatusDTO.getFileName());
					prepStmt.setString(IPixB2BConstants.FOUR, transStatusDTO.getFileSize1());					
					prepStmt.setString(IPixB2BConstants.FIVE, transStatusDTO.getStatusFTP());
					prepStmt.setString(IPixB2BConstants.SIX, transStatusDTO.getFileSize2());
					prepStmt.setString(IPixB2BConstants.SEVEN, transStatusDTO.getStatusARC());					
					prepStmt.setString(IPixB2BConstants.EIGHT, transStatusDTO.getStatusMAIL());
					prepStmt.setString(IPixB2BConstants.NINE, transStatusDTO.getStatusACK());
					prepStmt.setLong(IPixB2BConstants.TEN, Long.valueOf(transStatusDTO.getTransID()).longValue());
					prepStmt.setString(IPixB2BConstants.ELEVEN, IPixB2BConstants.B2B);
					
					qryParams = transStatusDTO.getStatusGEN()+","+transStatusDTO.getFileName()
						+","+transStatusDTO.getFileSize1()+","+transStatusDTO.getStatusFTP()
						+","+transStatusDTO.getFileSize2()+","+transStatusDTO.getStatusARC()
						+","+transStatusDTO.getStatusMAIL()+","+transStatusDTO.getStatusACK()
						+","+transStatusDTO.getTransID()+","+IPixB2BConstants.B2B;
					
					recsNum = prepStmt.executeUpdate();
					recsCount = recsCount + recsNum;
					
					//DBUtils.close(prepStmt);
					
					dbCon.commit();
				}
				DBUtils.close(prepStmt);
			}
			B2BLogger.debug("OutboundTransStatusDAOImpl.updateOutboundTransStatus() method return");
		} catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+qry_updt_pix_xmlgen_log+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			DBUtils.close(prepStmt);
			DBUtils.close(dbCon);
			
			transStatusDTO = null;			
			qryParams = null;
		}
		return recsCount;
	}
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.outbound.common.transstatus.dao.IOutboundTransStatusDAO#updateInboundTransBusinessAckStatus(java.util.ArrayList)
	 */
	public int updateInboundTransBusinessAckStatus(ArrayList transList){
		Connection dbCon 			= null;
		PreparedStatement prepStmt1 = null;
		PreparedStatement prepStmt2 = null;
		String sqlQuery				= null;
		String qryParams			= null;
		int recsCount				= 0;
		int recsNum					= 0;
		
		TransactionStatusDTO transStatusDTO = null;

		try {
			B2BLogger.debug("OutboundTransStatusDAOImpl.updateInboundTransBusinessAckStatus() method called");			
			if(transList != null && transList.size() > 0){
				dbCon = DBUtils.getDBConnection();			
				if (dbCon == null){
					B2BLogger.error("Error in database connection creation");
					return -1; 
				}
				dbCon.setAutoCommit(false);		
				
				for(int i=0; i<transList.size(); i++){
					transStatusDTO = (TransactionStatusDTO)transList.get(i);
					
					sqlQuery = qry_updt_pix_xmlread_log;
					prepStmt1 = dbCon.prepareStatement(sqlQuery);
					prepStmt1.clearParameters();
					prepStmt1.setString(IPixB2BConstants.ONE, transStatusDTO.getStatusACK());
					prepStmt1.setString(IPixB2BConstants.TWO, transStatusDTO.getTransID());
					prepStmt1.setString(IPixB2BConstants.THREE, IPixB2BConstants.B2B);
					qryParams = transStatusDTO.getStatusACK()+","+transStatusDTO.getTransID()+","+IPixB2BConstants.B2B;					
					recsNum = prepStmt1.executeUpdate();
					recsCount = recsCount + recsNum;
					DBUtils.close(prepStmt1);
					
					sqlQuery = qry_ins_pix_xml_ack_log;
					prepStmt2 = dbCon.prepareStatement(sqlQuery);
					prepStmt2.clearParameters();
					prepStmt2.setString(IPixB2BConstants.ONE, transStatusDTO.getTransID());
					prepStmt2.setString(IPixB2BConstants.TWO, transStatusDTO.getProcessType());
					prepStmt2.setString(IPixB2BConstants.THREE, transStatusDTO.getFileName());
					prepStmt2.setString(IPixB2BConstants.FOUR, transStatusDTO.getFileSize1());
					qryParams = transStatusDTO.getTransID()+","+transStatusDTO.getProcessType()+","+transStatusDTO.getFileName()+","+transStatusDTO.getFileSize1();
					recsNum = prepStmt2.executeUpdate();
					recsCount = recsCount + recsNum;
					DBUtils.close(prepStmt2);
					dbCon.commit();					
				}
			}
			B2BLogger.debug("OutboundTransStatusDAOImpl.updateInboundTransBusinessAckStatus() method return");
		} catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{
			DBUtils.close(prepStmt1);
			DBUtils.close(prepStmt2);
			DBUtils.close(dbCon);
			
			sqlQuery	= null;
			qryParams 	= null;
			transStatusDTO = null;
		}
		return recsCount;
	}
}