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
 * Title		: 	IInboundTransStatusDAO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi 	30 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.common.transstatus.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.utils.B2BException;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * IInboundTransStatusDAO provide the interface to update the  
 * the transaction status of B2B Inbound transactions.
 * 
 * @author Yogesh Tyagi
 */
public interface IInboundTransStatusDAO{	
	/**
	 * This method update the transaction status in database.
	 * @param dbCon
	 * @param transStatusDTO
	 * @param senderSAN
	 * @param receiverSAN
	 * @param transrefId1
	 * @param transrefId2
	 * @param transrefLabel1
	 * @param transrefLabel2
	 * @return integer
	 * @throws SQLException
	 * @throws B2BException
	 */
	public long updateInboundTransStatus(Connection dbCon, TransactionStatusDTO transStatusDTO, 
			String senderSAN, String receiverSAN, 
			String transrefId1, String transrefId2, String transrefLabel1, String transrefLabel2,String docNumber,String docDate, String vendorTransId) throws SQLException, B2BException;
}