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
 * Title		: 	IOutboundVendorDAO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi 	12 Jul, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.common.vendor.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * IOutboundVendorDAO provide the interface to access 
 * the Outbound / Inbound Vendor details from database.
 * 
 * @author Yogesh Tyagi
 */
public interface IOutboundVendorDAO{	
	/**
	 * This method returns Outbound Vendors list.
	 * @param processType
	 * @return ArrayList
	 * @throws SQLException
	 * @throws B2BException
	 */
	public ArrayList getOutboundB2BVendors(String processType) throws SQLException, B2BException;
	/**
	 * This method returns Outbound Vendor Transactions details including FTP server info.
	 * @param processType
	 * @param vendorSAN
	 * @return ArrayList
	 */
	public ArrayList getOutboundVendorTransInfo(String processType, String vendorSAN);
	
	/**
	 * This method returns the new run Id for feed log entry.
	 * @return long
	 */
	public long getdbRunIdNext();

	/**
	 * This method insert into the Feed log with the start of Outbound cron job.
	 * @return boolean
	 */
	public boolean insertFeedLog();
	
	/**
	 * This method updates the Feed log after the Outbound cron job gets completed.
	 * @param updateInboundTransactionStatus
	 * @return boolean
	 */
	public boolean updateFeedLog(long updateInboundTransactionStatus);
}