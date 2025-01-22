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
 * Title		: 	IBusinessAckProcessorDAO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Yogesh Tyagi	16 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.common.businessack.dao;

import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
import com.pearson.pixb2b.vendor.utils.constants.IPixB2BConstants;
/**
 * IBusinessAckProcessorDAO is an interface to process the 
 * B2B BusinessAcknowledgement Success/Failure transaction details for 
 * all Outbound Transactions sent to vendors.
 * 
 * @author Yogesh Tyagi
 */
public interface IBusinessAckProcessorDAO{
	/**
	 * This method insert/update BusinessAcknowledgement Success transaction details in database.
	 * @param pneDTO
	 * @param transStatusDTO
	 * @return String
	 */
	public String storeBusinessAcknowledgementDetails(PapiNetEnvelopeDTO pneDTO, TransactionStatusDTO transStatusDTO);
}