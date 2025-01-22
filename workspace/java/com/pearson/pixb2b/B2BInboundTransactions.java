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
 * Title		: 	B2BInboundTransactions.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi 	12 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pearson.pixb2b.global.B2BGlobalData;
import com.pearson.pixb2b.vendor.InboundVendorThread;
import com.pearson.pixb2b.vendor.transaction.inbound.InboundTransactionFactory;
import com.pearson.pixb2b.vendor.transaction.inbound.common.vendor.dao.IInboundVendorDAO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.vendor.VendorDTO;
import com.pearson.pixb2b.vendor.transaction.shared.helper.B2BHelper;
import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.B2BLogger;
import com.pearson.pixb2b.vendor.utils.DateUtils;
import com.pearson.pixb2b.vendor.utils.IConfigConstants;
import com.pearson.pixb2b.vendor.utils.StringUtils;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * B2BInboundTransactions class initiate the B2B Inbound Transactions 
 * processing of various types of transactions for different vendors.

 * This class get the B2B vendor details for Inbound transactions  and 
 * start the separate thread for each vendor for asynchronous processing 
 * of transactions for Inbound case.
 * 
 * @author Yogesh Tyagi
 */
public class B2BInboundTransactions{
	/**
	 * Main method to start the B2B Inbound Transactions processing.
	 * @param args
	 */
	public static void main(String args[]){
		InboundTransactionFactory inTransFactory = null;
		IInboundVendorDAO inVendorDAO = null;
		VendorDTO vendorDTO 		= null;
		ArrayList inboundVendorsList= null;	
		List<Thread> threadList = new ArrayList<Thread>();
		InboundVendorThread inboundVendorThread = null;
		long dbRunIdNext = -1;
		int port = Integer.parseInt(IConfigConstants.INBOUND_SOCKET_PORT);
		if(!ApplicationInstanceManager.registerInstance(port)){
			B2BLogger.error("Another instance of this Inbound application is already running.  Exiting.");
			System.exit(0);
		}
		try {
				B2BLogger.debug("B2BInboundTransactions.main() method called - B2B INBOUND TRANSACTIONS JOB STARTED ON = "+DateUtils.getCurrentDate(IPixB2BConstants.timestampFormat_MM_dd_yyyy));
				//Start Inbound transactions processing
				inTransFactory = InboundTransactionFactory.newInstance();
				inVendorDAO = inTransFactory.geInboundVendorDAO();
				dbRunIdNext = inVendorDAO.getdbRunIdNext();
				if(dbRunIdNext>0){
					B2BGlobalData globalData = B2BGlobalData.getGlobalDataObject();
					globalData.setRun_Id(dbRunIdNext);
					inVendorDAO.insertFeedLog();
				}else{
					B2BLogger.error("B2BInboundTransactions.main() - Run Id sequence is not generated correctly");
				}
				
				inboundVendorsList = getInboundB2BVendors(IPixB2BConstants.processType_INBOUND);
						
				if(inboundVendorsList != null && inboundVendorsList.size() > 0){
					for(int i = 0; i<inboundVendorsList.size(); i++){
						vendorDTO = (VendorDTO)inboundVendorsList.get(i);
						if(vendorDTO != null && vendorDTO.getVendorSAN() != null && !"".equals(vendorDTO.getVendorSAN().trim())){
							inboundVendorThread = new InboundVendorThread(IPixB2BConstants.processType_INBOUND, vendorDTO.getVendorSAN().trim(), vendorDTO.getVendorName(), IConfigConstants.SCHEMA_papiNetEnvelope);
							threadList.add(new Thread(inboundVendorThread, vendorDTO.getVendorSAN()));
						}else{
								B2BLogger.info("B2BInboundTransactions.main() - VendorSAN is null");
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
				b2bHelper.sendEmailAndUpdateOutBndStatus(DateUtils.getCurrentDate(IPixB2BConstants.timestampFormat_MM_dd_yyyy));*/
				
				List statusList = B2BGlobalData.getGlobalDataObject().getThreadStatus();
				if(null!= statusList && statusList.size()>0){
					inVendorDAO.updateFeedLog(updateInboundTransactionStatus());
				}
				ApplicationInstanceManager.unRegisterInstance();
			} catch (Exception e) {
				B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
				B2BLogger.error("Exception",e);
				System.exit(0);
			}
	}
	
	/**
	 * This method get the Inbound B2B vendor details and start the tread 
	 * for each vendor for the Inbound transactions processing.
	 * 
	 * @param processType
	 * @param papiNetSchema
	 * @throws B2BException
	 */
	private static ArrayList getInboundB2BVendors(String processType) throws B2BException{
		InboundTransactionFactory inTransFactory = null;
		IInboundVendorDAO inVendorDAO = null;
		
		try {
				B2BLogger.debug("B2BInboundTransactions.getInboundB2BVendors() method called");
				inTransFactory = InboundTransactionFactory.newInstance();
				inVendorDAO = inTransFactory.geInboundVendorDAO();
				return inVendorDAO.getInboundB2BVendors(processType);
						
		} catch (SQLException e) {
			B2BLogger.error("Exception",e);
			B2BLogger.error("SQLException :: " + StringUtils.getStackTrace(e));
			throw new B2BException("SQLException :: " + StringUtils.getStackTrace(e));
		} catch (B2BException e) {
			B2BLogger.error("Exception :: " + StringUtils.getStackTrace(e));
			B2BLogger.error("Exception",e);
			throw new B2BException("B2BException :: " + StringUtils.getStackTrace(e));
		} finally{
			inTransFactory= null;
			inVendorDAO	= null;
		}
	}
	
	/**
	 * this method returns the Status flag for the Feed log 
	 * @return long
	 */

	private static long updateInboundTransactionStatus() {
			
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