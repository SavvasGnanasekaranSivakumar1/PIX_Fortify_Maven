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
 * Title		: 	B2BOutboundTransactions.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi 	12 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.pearson.pixb2b.global.B2BGlobalData;
import com.pearson.pixb2b.vendor.OutboundVendorThread;
import com.pearson.pixb2b.vendor.transaction.outbound.OutboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.outbound.common.vendor.dao.IOutboundVendorDAO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.VendorDTO;
import com.pearson.pixb2b.vendor.transaction.shared.helper.B2BHelper;
import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.DBUtils;
import com.pearson.pixb2b.vendor.utils.DateUtils;
import com.pearson.pixb2b.vendor.utils.IConfigConstants;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * B2BOutboundTransactions class initiate the B2B Outbound Transactions 
 * processing of various types of transactions for different vendors.
 *
 * This class get the B2B vendor details for Ounbound  transactions  and 
 * start the separate thread for each vendor for asynchronous processing 
 * of transactions for Outbound case.
 * 
 * @author Yogesh Tyagi
 */
public class B2BOutboundTransactions {
	/**
	 * Main method to start the B2B Outbound Transactions processing.
	 * @param args
	 */
	public static void main(String args[]){
		OutboundTransactionFactory outTransFactory 	= null;
		IOutboundVendorDAO outVendorDAO 			= null;
		VendorDTO vendorDTO 						= null;
		ArrayList outboundVendorsList				= null;
		List<Thread> threadList = new ArrayList<Thread>();
		OutboundVendorThread outboundVendorThread = null;
		long dbRunIdNext = -1;
		int portNO = Integer.parseInt(IConfigConstants.OUTBOUND_SOCKET_PORT);
		if(!ApplicationInstanceManager.registerInstance(portNO)){
			B2BLogger.error("Another instance of this OUTBOUND application is already running.  Exiting.");
			System.exit(0);
		}
		try {
			B2BLogger.info("B2BOutboundTransactions.main() method called - B2B OUTBOUND TRANSACTIONS JOB STARTED ON = "+DateUtils.getCurrentDate(IPixB2BConstants.timestampFormat_MM_dd_yyyy));
			//Start Outbound transactions processing
			B2BLogger.info("Performing sub systems check");
			/*if(checkSubSystems())
			B2BLogger.info("All sub systems working correctly, starting processing...");
			else
			{
				B2BLogger.error("Sub System Error");
				System.exit(-1);
			}	*/
			
			outTransFactory = OutboundTransactionFactory.newInstance();
			outVendorDAO = outTransFactory.getOutboundVendorDAO();
			dbRunIdNext = outVendorDAO.getdbRunIdNext();
			if(dbRunIdNext>0){
				B2BGlobalData globalData = B2BGlobalData.getGlobalDataObject();
				globalData.setRun_Id(dbRunIdNext);
				outVendorDAO.insertFeedLog();
			}else{
				B2BLogger.error("B2BOutboundTransactions.main() - Run Id sequence is not generated correctly");
			}
			outboundVendorsList = getOutboundB2BVendors(IPixB2BConstants.processType_OUTBOUND);
			if(outboundVendorsList != null && outboundVendorsList.size() > 0){
				for(int i = 0; i<outboundVendorsList.size(); i++){
					vendorDTO = (VendorDTO)outboundVendorsList.get(i);
					if(vendorDTO != null && vendorDTO.getVendorSAN() != null && !"".equals(vendorDTO.getVendorSAN().trim())){
						outboundVendorThread = 
						new OutboundVendorThread(IPixB2BConstants.processType_OUTBOUND, vendorDTO.getVendorSAN().trim(), vendorDTO.getVendorName(), IConfigConstants.SCHEMA_papiNetEnvelope);
						threadList.add(new Thread(outboundVendorThread, vendorDTO.getVendorSAN()));
					}else{
						B2BLogger.info("B2BOutboundTransactions.getOutboundB2BVendors() - VendorSAN is null");
					}
				}
			}
			
			for(Thread vendorThread : threadList)
			{
				vendorThread.start();
				Thread.sleep(IPixB2BConstants.TIME_DELAY);
			}
			for(Thread vendorThread : threadList)
			{
				vendorThread.join();
			}
			
			//Email Utility called
			/*B2BHelper b2bHelper = new B2BHelper();
			b2bHelper.sendEmailAndUpdateStatus(DateUtils.getCurrentDate(IPixB2BConstants.timestampFormat_MM_dd_yyyy));*/

			
			List statusList = B2BGlobalData.getGlobalDataObject().getThreadStatus();
			if(null!= statusList && statusList.size()>0){
				outVendorDAO.updateFeedLog(updateOutboundTransactionStatus());
			}
			ApplicationInstanceManager.unRegisterInstance();
		} catch (Exception e) {
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			System.exit(0);
		}
	}
	/**
	 * function to check the sub systems (DB) 
	 * @return
	 */
	private static boolean checkSubSystems() {
		
		Boolean subSystemsStatus =  Boolean.TRUE;//considering all sub systems are up and running
		Connection con = null;
		Statement statement = null;
		String testQuery = "SELECT 1";
		ResultSet resultSet=null;
		
		//check Pearson DB connectivity
		try {
				con  = DBUtils.getDBConnection();if(null==con) throw new B2BException();
				statement =  con.createStatement();if(null==statement) throw new B2BException();
				resultSet = statement.executeQuery(testQuery);if(null==resultSet) throw new B2BException();
				while(resultSet.next())
				{
					//if able to do this, it means DB is working fine
				}
							
		} catch (SQLException e) {
			B2BLogger.error("Unable to connect to Database");
			return Boolean.FALSE;
		} catch (B2BException e) {
			B2BLogger.error("Unable to connect to Database");
			return Boolean.FALSE;
		}
		
		return subSystemsStatus;
	}
	/**
	 * This method get the outbound B2B vendor details and start the thread 
	 * for each vendor for the Outbound transactions processing.
	 * 
	 * @param processType
	 * @param papiNetSchema
	 * @throws B2BException
	 */
	private static ArrayList getOutboundB2BVendors(String processType) throws B2BException{
		OutboundTransactionFactory outTransFactory 	= null;
		IOutboundVendorDAO outVendorDAO			= null;
		ArrayList outboundVendorsList	= null;		

		try {
			B2BLogger.debug("B2BOutboundTransactions.getOutboundB2BVendors() method called");			
			outTransFactory = OutboundTransactionFactory.newInstance();
			outVendorDAO = outTransFactory.getOutboundVendorDAO();
			outboundVendorsList = outVendorDAO.getOutboundB2BVendors(processType);			
			B2BLogger.debug("B2BOutboundTransactions.getOutboundB2BVendors() method return");
		} catch (SQLException e) {
			B2BLogger.error("Exception",e);
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			throw new B2BException("SQLException :: " + StringUtils.getStackTrace(e));
		} finally{
			outTransFactory = null;
			outVendorDAO	= null;
		}
		return outboundVendorsList;
	}
	
	/**
	 * this method returns the Status flag for the Feed log 
	 * @return long
	 */

	private static long updateOutboundTransactionStatus() {
			
			List statusList = B2BGlobalData.getGlobalDataObject().getThreadStatus();
			if((statusList.contains(IPixB2BConstants.STATUS_PASS) && statusList.contains(IPixB2BConstants.STATUS_FAIL))
					|| statusList.contains(IPixB2BConstants.STATUS_PARTIAL))
				{
					return IPixB2BConstants.STATUS_PARTIAL;
				}
			else if(statusList.contains(IPixB2BConstants.STATUS_PASS))
				{
					return IPixB2BConstants.STATUS_PASS;
				}
		    else if(statusList.contains(IPixB2BConstants.STATUS_FAIL))
					{
						return IPixB2BConstants.STATUS_FAIL;
					}
				
			return 2;//code will never reach here
		}
}