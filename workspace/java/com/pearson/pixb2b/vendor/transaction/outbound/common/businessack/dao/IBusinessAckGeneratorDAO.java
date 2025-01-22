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
 * Title		: 	IBusinessAckGeneratorDAO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi 	17 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.common.businessack.dao;

import java.util.ArrayList;
/**
 * IBusinessAckGeneratorDAO is an interface to process the 
 * BusinessAcknowledgement Success/Failure details for all 
 * Inbound Transactions received from vendors.
 * 
 * @author Yogesh Tyagi
 */
public interface IBusinessAckGeneratorDAO {
	/**
	 * This method returns BusinessAcknowledgement transactions list.
	 * @param vendorSAN
	 * @param transactionType
	 * @param transactionName
	 * @return ArrayList
	 */
	
	public ArrayList getBAOutboundDetails(String vendorSAN, String transactionType, String transactionName);
	public ArrayList getBusinessAcknowledgementDetails(String vendorSAN, String transactionType, String transactionName);
}