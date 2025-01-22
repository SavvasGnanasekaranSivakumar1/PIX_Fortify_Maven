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
 * Title		: 	IOutboundTransStatusDAO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi 	30 Oct, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.common.transstatus.dao;

import java.util.ArrayList;
/**
 * IOutboundTransStatusDAO provide the interface to update the  
 * the transaction status of B2B Outbound transactions.
 * 
 * @author Yogesh Tyagi
 */
public interface IOutboundTransStatusDAO{	
	/**
	 * This method updates the transaction status of Outbound transactions.
	 * @param outTransList
	 * @return String
	 */
	public int updateOutboundTransStatus(ArrayList outTransList);	
	/**
	 * This method updates the transaction status of Inbound transaction Business Acknowledgement status.
	 * @param transList
	 * @return String
	 */
	public int updateInboundTransBusinessAckStatus(ArrayList transList);
}