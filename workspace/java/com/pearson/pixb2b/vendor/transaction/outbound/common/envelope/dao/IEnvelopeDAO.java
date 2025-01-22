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
 * Title		: 	IEnvelopeDAO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi 	12 Jul, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.common.envelope.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * IEnvelopeDAO provide the interface to access the Envelope 
 * details for a particular transaction from database.
 * 
 * @author Yogesh Tyagi
 */
public interface IEnvelopeDAO{
	/**
	 * This method returns Envelope PayloadInfo for multiple transactions.
	 * @param dbCon
	 * @param vendorSAN
	 * @param transactionType
	 * @param transactionName
	 * @return ArrayList
	 * @throws SQLException
	 * @throws B2BException
	 */
	public ArrayList getEnvelopePayloadInfo(Connection dbCon, String vendorSAN, String transactionType, String transactionName) throws SQLException, B2BException;
}