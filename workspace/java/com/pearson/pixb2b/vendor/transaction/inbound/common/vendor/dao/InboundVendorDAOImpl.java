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
 * Title		: 	InboundVendorDAOImpl.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi 	30 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.common.vendor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pearson.pixb2b.global.B2BGlobalData;
import com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.FTPServerInfo;
import com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.VendorDTO;
import com.pearson.pixb2b.vendor.transaction.shared.helper.DAOHelper;
import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.DBUtils;
import com.pearson.pixb2b.vendor.utils.IConfigConstants;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * InboundVendorDAOImpl is an implementation class to communicate with 
 * the database and get the Outbound / Inbound Vendor details from database.
 * 
 * @author Yogesh Tyagi
 */
public class InboundVendorDAOImpl implements IInboundVendorDAO {
	// Nithya CTS - Added FTP_SFTP filter for FTP to SFTP Migration
	private static final String qry_sel_inbound_vendor_ftpInfo = "SELECT DISTINCT SAN, SERVER_NAME, LOGIN, PASSWORD, GET_FOLDER, FTP_SFTP"
		+" FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_SAN_TRANS_MAPPING"
		+" WHERE active = 'Y'"
		+" AND access_method_id = 62"		
		+" AND SAN = ?"
		+" AND IN_OUT_PROCESS_TYPE = ?";

	private static final String qry_sel_pix_run_id_val = "SELECT SEQ_PIX_FEED_LOG_ID.NEXTVAL run_id_next FROM DUAL";
	
	private static final String qry_insert_pix_feed_log = "INSERT INTO "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_FEED_LOG"
	+" (RUN_ID, FEED_TYPE, STAGE_TYPE, START_DATE_TIME, END_DATE_TIME, SUCCESS_FLAG)"
	+" VALUES (?, ?, ?, SYSDATE, SYSDATE, ?)";
	
	private static final String qry_update_pix_feed_log = "UPDATE "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_FEED_LOG"
	+" SET END_DATE_TIME = SYSDATE, SUCCESS_FLAG= ? WHERE RUN_ID = ?";
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.shared.dao.vendor.IVendorDAO#getInboundB2BVendors()
	 */
	public ArrayList getInboundB2BVendors(String processType) throws SQLException, B2BException{
		DAOHelper daoHelper		= null;
		ArrayList b2bVendorsList= null;		
		
		try {
			B2BLogger.debug("InboundVendorDAOImpl.getInboundB2BVendors() method called");
			daoHelper = new DAOHelper();
			b2bVendorsList = daoHelper.getB2BVendors(processType);
			B2BLogger.debug("InboundVendorDAOImpl.getInboundB2BVendors() method return");
		} catch (SQLException e) {
			B2BLogger.error("SQLException :: ",e);
			throw new B2BException("SQLException :: " + StringUtils.getStackTrace(e));
		} catch (Exception e) {
			B2BLogger.error("Exception :: ",e);
			throw new B2BException("B2BException :: " + StringUtils.getStackTrace(e));
		} finally{
			daoHelper = null;
		}		
		return b2bVendorsList;
	}	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.shared.dao.vendor.IVendorDAO#getInboundVendorFTPInfo(java.lang.String)
	 */
	public VendorDTO getInboundVendorFTPInfo(String processType, String vendorSAN) {
		Connection dbCon 			= null;
		PreparedStatement prepStmt	= null;
		ResultSet resultSet			= null;
		String qryParams 			= null;
		
		String ftpHostName 			= null;
		String ftpUserName 			= null;
		String ftpPwd 				= null;
		String ftpGetDir 			= null;
		String ftpOrSftp			= null;// Nithya CTS - Added for FTP to SFTP Migration
		VendorDTO vendorDTO			= null;
		FTPServerInfo ftpServerInfo	= null;
		
		try {
			B2BLogger.debug("InboundVendorDAOImpl.getInboundVendorFTPInfo() method called");			

			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}	
			dbCon.setAutoCommit(false);
			
			prepStmt = dbCon.prepareStatement(qry_sel_inbound_vendor_ftpInfo);
			prepStmt.clearParameters();
			prepStmt.setString(IPixB2BConstants.ONE, vendorSAN);
			prepStmt.setString(IPixB2BConstants.TWO, processType);
			qryParams = vendorSAN+","+processType;
			resultSet = prepStmt.executeQuery();
			
			while(resultSet.next()) {
				vendorSAN 	= resultSet.getString("SAN") != null ? resultSet.getString("SAN").trim() : "";
				ftpHostName = resultSet.getString("SERVER_NAME") != null ? resultSet.getString("SERVER_NAME").trim() : "";
				ftpUserName = resultSet.getString("LOGIN") != null ? resultSet.getString("LOGIN").trim() : "";
				ftpPwd 		= resultSet.getString("PASSWORD") != null ? resultSet.getString("PASSWORD").trim() : "";
				ftpGetDir 	= resultSet.getString("GET_FOLDER") != null ? resultSet.getString("GET_FOLDER").trim() : "";
				ftpOrSftp	= resultSet.getString("FTP_SFTP") != null ? resultSet.getString("FTP_SFTP").trim() : ""; // Nithya CTS - Added for FTP to SFTP Migration
				
				if(!"".equals(vendorSAN) && !"".equals(ftpHostName) 
						&& !"".equals(ftpUserName) && !"".equals(ftpPwd) && !"".equals(ftpGetDir)){
					
					vendorDTO = new VendorDTO();
					ftpServerInfo = new FTPServerInfo();
					
					vendorDTO.setVendorSAN(vendorSAN);
					ftpServerInfo.setFtpHostName(ftpHostName);
					ftpServerInfo.setFtpUserName(ftpUserName);
					ftpServerInfo.setFtpPwd(ftpPwd);
					ftpServerInfo.setFtpGetDir(ftpGetDir);
					ftpServerInfo.setFtpOrSftp(ftpOrSftp); // Nithya CTS - Added for FTP to SFTP Migration
					vendorDTO.setFtpServerInfo(ftpServerInfo);
				}else{
					B2BLogger.info("InboundVendorDAOImpl.getOutboundVendorTransInfo() : SAN = "+vendorSAN
							+", SERVER_NAME = "+ftpHostName
							+", GET_FOLDER = "+ftpGetDir);
				}
			} //end while loop
			DBUtils.close(prepStmt);
			DBUtils.close(resultSet);
			
			dbCon.commit();
			B2BLogger.debug("InboundVendorDAOImpl.getInboundVendorFTPInfo() method return");
		} catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+qry_sel_inbound_vendor_ftpInfo+"] ["+qryParams+"]");			
			B2BLogger.error("SQLException :: ",e);
			
			ftpServerInfo = null;
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: ",e);
			ftpServerInfo = null;
		} finally{
			DBUtils.close(prepStmt);
			DBUtils.close(resultSet);
			DBUtils.close(dbCon);			

			qryParams		= null;
			ftpHostName 	= null;
			ftpUserName 	= null;
			ftpPwd 			= null;
			ftpGetDir 		= null;
			ftpServerInfo	= null;
		}
		return vendorDTO;
	}
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.common.vendor.dao.IInboundVendorDAO#getdbRunIdNext()
	 */
	public long getdbRunIdNext(){
		Connection dbCon			= null;
		PreparedStatement prepStmt1	= null;
		ResultSet resultSet1		= null;
		String sqlQuery				= null;
		long dbRunIdNext = -1;	
		try {
			B2BLogger.debug("InboundVendorDAOImpl.getdbRunIdNext() method called");
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return -1; 
			}	
			dbCon.setAutoCommit(false);
			
			sqlQuery = qry_sel_pix_run_id_val;
			prepStmt1 = dbCon.prepareStatement(sqlQuery);
			prepStmt1.clearParameters();		
			resultSet1 = prepStmt1.executeQuery();
			while(resultSet1.next()){
				dbRunIdNext = resultSet1.getLong("run_id_next");
			}
			DBUtils.close(prepStmt1);
			DBUtils.close(resultSet1);
			B2BLogger.debug("InboundVendorDAOImpl.getdbRunIdNext() method return");
		} catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"]");			
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		}finally{
			DBUtils.close(prepStmt1);
			DBUtils.close(resultSet1);
			DBUtils.close(dbCon);
			
			sqlQuery = null;
		}
		return dbRunIdNext;
	}
	
	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.common.vendor.dao.IInboundVendorDAO#insertFeedLog()
	 */
	public boolean insertFeedLog(){
		Connection dbCon			= null;
		PreparedStatement prepStmt	= null;
		String sqlQuery				= null;
		String qryParams			= null;
		
		long dbRunIdNext = B2BGlobalData.getGlobalDataObject().getRun_Id();
		try {
			B2BLogger.debug("InboundVendorDAOImpl.insertFeedLog() method called");
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return Boolean.FALSE; 
			}	
			dbCon.setAutoCommit(false);
			
			sqlQuery = qry_insert_pix_feed_log;
			prepStmt = dbCon.prepareStatement(sqlQuery);
			prepStmt.clearParameters();
			prepStmt.setLong(IPixB2BConstants.ONE, dbRunIdNext);
			prepStmt.setString(IPixB2BConstants.TWO, IPixB2BConstants.B2B);
			prepStmt.setString(IPixB2BConstants.THREE, IPixB2BConstants.VEN_TO_PIX);
			prepStmt.setString(IPixB2BConstants.FOUR, IPixB2BConstants.flag_S);
			qryParams = dbRunIdNext+","+IPixB2BConstants.B2B+","+IPixB2BConstants.VEN_TO_PIX+","+IPixB2BConstants.flag_S;
			prepStmt.executeUpdate();
			
			DBUtils.close(prepStmt);
			
			dbCon.commit();
			
			B2BLogger.debug("InboundVendorDAOImpl.insertFeedLog() method return");
		} catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			return Boolean.FALSE;
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
			return Boolean.FALSE;
		}finally{
			DBUtils.close(prepStmt);
			DBUtils.close(dbCon);
			
			qryParams = null;
			sqlQuery = null;
		}
		return Boolean.TRUE;
	}

	/* (non-Javadoc)
	 * @see com.pearson.pixb2b.vendor.transaction.inbound.common.vendor.dao.IInboundVendorDAO#updateFeedLog(long)
	 */
	public boolean updateFeedLog(long status){
		Connection dbCon			= null;
		PreparedStatement prepStmt	= null;
		String sqlQuery				= null;
		String qryParams			= null;
		String sStatus				= null;
		String currentDate 			= null;
		
		if(status == -1)
			sStatus = "U";
		else if(status == 0)
			sStatus = "P";
		else if(status == 1)
			sStatus = "S";
		
		long dbRunIdNext = B2BGlobalData.getGlobalDataObject().getRun_Id();
		try {
			B2BLogger.debug("InboundVendorDAOImpl.updateFeedLog() method called");
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return Boolean.FALSE; 
			}	
			dbCon.setAutoCommit(false);
			
			sqlQuery = qry_update_pix_feed_log;
			prepStmt = dbCon.prepareStatement(sqlQuery);
			prepStmt.clearParameters();
			prepStmt.setString(IPixB2BConstants.ONE, sStatus);
			prepStmt.setLong(IPixB2BConstants.TWO, dbRunIdNext);
			qryParams = currentDate+","+","+sStatus+","+dbRunIdNext;
			prepStmt.executeUpdate();
			
			DBUtils.close(prepStmt);
			dbCon.commit();
			
			B2BLogger.debug("InboundVendorDAOImpl.updateFeedLog() method return");
		} catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+sqlQuery+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			return Boolean.FALSE;
		} catch (B2BException e) {
			B2BLogger.error("B2BException :: " + StringUtils.getStackTrace(e));
			return Boolean.FALSE;
		}finally{
			DBUtils.close(prepStmt);
			DBUtils.close(dbCon);
			
			qryParams = null;
			sqlQuery = null;
		}
		return Boolean.TRUE;
	}
}