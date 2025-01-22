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
 * Title		: 	DAOHelper.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi 	14 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.helper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import oracle.jdbc.OracleTypes;

import com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.VendorDTO;
import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.DBUtils;
import com.pearson.pixb2b.vendor.utils.IConfigConstants;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * DAOHelper is a helper class to help DAO classes to complete 
 * the required operations.
 * 
 * @author Yogesh Tyagi
 */
public class DAOHelper{
	public static final String proc_sel_outbound_vendor_transInfo = "{call "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_B2B_VEN_TRANS_DETAIL_PROC(?,?,?,?)}";
	
	private static final String qry_sel_pix_status_code_STATUS_ID = "SELECT STATUS_ID"
		+" FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".pix_status_code"
		+" WHERE TABLE_ID = ?"
		+" AND STATUS_DESCRIPTION = ?";
	
	private static final String qry_sel_poID_poVersion = "SELECT TRANSREF_ID_1 poID, TRANSREF_ID_2 poVersion"
		+" FROM "+IConfigConstants.PIX_DB_SCHEMA_NAME+".PIX_XMLGEN_LOG"
		+" WHERE trans_type = ?"
		+" AND process_type = ?"
		+" AND TRANS_ID = ?";
	
	/**
	 * This method returns Outbound / Inbound Vendors list.
	 * @param processType
	 * @return ArrayList
	 * @throws SQLException
	 * @throws B2BException
	 */
	public ArrayList getB2BVendors(String processType) throws SQLException, B2BException{
		Connection dbCon 		= null;
		CallableStatement cs    = null;
		ResultSet resultSet     = null;
		String qryParams		= null;
		
		ArrayList b2bVendorsList= null;
		VendorDTO vendorDTO		= null;
		
		try {
			dbCon = DBUtils.getDBConnection();
			if (dbCon == null){
				B2BLogger.error("Error in database connection creation");
				return null; 
			}	
			dbCon.setAutoCommit(false);
			
			cs = dbCon.prepareCall(proc_sel_outbound_vendor_transInfo);
			cs.setString(IPixB2BConstants.ONE, null);
			cs.setString(IPixB2BConstants.TWO, IPixB2BConstants.SAN);
			cs.setString(IPixB2BConstants.THREE, processType);
			cs.registerOutParameter(IPixB2BConstants.FOUR, OracleTypes.CURSOR);
			qryParams = null+","+IPixB2BConstants.SAN+","+processType;
			cs.execute();
			resultSet = (ResultSet) cs.getObject(IPixB2BConstants.FOUR);
			
			b2bVendorsList = new ArrayList();
			
			while(resultSet.next()) {
				vendorDTO = new VendorDTO();
				vendorDTO.setVendorSAN(resultSet.getString("SAN") != null ? resultSet.getString("SAN").trim() : "");
				vendorDTO.setVendorName(resultSet.getString("name_1") != null ? resultSet.getString("name_1").trim() : "");
				b2bVendorsList.add(vendorDTO);
			}
			DBUtils.close(cs);
			DBUtils.close(resultSet);
			dbCon.commit();
		} catch (SQLException e) {
			B2BLogger.error("FAILED SQL -> ["+proc_sel_outbound_vendor_transInfo+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			throw new B2BException("SQLException :: " + StringUtils.getStackTrace(e));
		} catch (Exception e) {
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			throw new B2BException("B2BException :: " + StringUtils.getStackTrace(e));
		} finally{
			DBUtils.close(resultSet);
			DBUtils.close(cs);
			DBUtils.close(dbCon);
			
			qryParams = null;
			vendorDTO = null;
		}		
		return b2bVendorsList;
	}
	/**
	 * This method returns status ID from PIX_STATUS_CODE based on statusCode provided
	 * @param dbCon
	 * @param tableID
	 * @param statusDescription
	 * @return integer
	 */
	public int getStatusID(Connection dbCon, String statusDescription, long tableID){
		PreparedStatement prepStmt	= null;
		ResultSet resultSet			= null;
		String qryParams			= null;
		int statusID				= -1;

		try {
			if(statusDescription != null && !"".equals(statusDescription.trim())){
				prepStmt = dbCon.prepareStatement(qry_sel_pix_status_code_STATUS_ID);
				prepStmt.clearParameters();
				prepStmt.setLong(IPixB2BConstants.ONE, tableID);
				prepStmt.setString(IPixB2BConstants.TWO, statusDescription);
				qryParams = tableID+","+statusDescription;
				resultSet = prepStmt.executeQuery();
				while(resultSet.next()){
					statusID = resultSet.getInt("status_id");
				}
				DBUtils.close(prepStmt);
				DBUtils.close(resultSet);
				B2BLogger.info("DAOHelper.getStatusID() - statusID = "+statusID);
			}else{
				B2BLogger.info("DAOHelper.getStatusID() - statusCode = "+statusDescription);
			}
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+qry_sel_pix_status_code_STATUS_ID+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} catch (Exception e) {
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{			
			DBUtils.close(prepStmt);
			DBUtils.close(resultSet);
			
			qryParams = null;
		}
		return statusID;
	}	
	/**
	 * This method return PO_ID(TRANSREF_ID_1) and PO_VERSION(TRANSREF_ID_2) for Inbound Transaction ID
	 * @param dbCon
	 * @param transType
	 * @param xmlPOTransId
	 * @return HashMap
	 */
	public HashMap getPoIdPoVersion(Connection dbCon, String transType, String xmlPOTransId){
		PreparedStatement prepStmt	= null;
		ResultSet resultSet			= null;
		String qryParams			= null;
		String poId					= null;
		String poVersion			= null;
		HashMap hmPoIdpoVersion		= null;

		try {
			if(xmlPOTransId != null && !"".equals(xmlPOTransId.trim())){
				prepStmt = dbCon.prepareStatement(qry_sel_poID_poVersion);
				prepStmt.clearParameters();
				prepStmt.setString(IPixB2BConstants.ONE, IPixB2BConstants.B2B);
				prepStmt.setString(IPixB2BConstants.TWO, transType);
				prepStmt.setString(IPixB2BConstants.THREE, xmlPOTransId);
				qryParams = IPixB2BConstants.B2B+","+transType+","+xmlPOTransId;
				resultSet = prepStmt.executeQuery();
				while(resultSet.next()){
					poId	 = resultSet.getString("poID");
					poVersion= resultSet.getString("poVersion");
				}
				DBUtils.close(prepStmt);
				DBUtils.close(resultSet);
				if(poId != null && !"".equals(poId.trim()) && poVersion != null && !"".equals(poVersion.trim())){
					hmPoIdpoVersion = new HashMap();
					hmPoIdpoVersion.put("PO_ID", poId);
					hmPoIdpoVersion.put("PO_VERSION", poVersion);					
				}
				B2BLogger.info("DAOHelper.getPoIdPoVersion() - PO_ID(TRANSREF_ID_1) = "+poId+" , PO_VERSION(TRANSREF_ID_2) = "+poVersion+" FOR xmlPOTransId = "+xmlPOTransId);
			}else{
				B2BLogger.info("DAOHelper.getPoIdPoVersion() - poTransIdXml = "+xmlPOTransId);
			}
		} catch (SQLException e) {			
			B2BLogger.error("FAILED SQL -> ["+qry_sel_poID_poVersion+"] ["+qryParams+"]");
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} catch (Exception e) {
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
		} finally{			
			DBUtils.close(prepStmt);
			DBUtils.close(resultSet);
			
			qryParams = null;
		}
		return hmPoIdpoVersion;
	}
}