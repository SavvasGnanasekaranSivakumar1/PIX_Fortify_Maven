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
 * Title		: 	IOrderConfirmationDAO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Abhilasha Nigam 04 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dao;

import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
/**
 * IOrderConfirmationDAO is an interface to communicate with the PIX database
 * and process the OrderConfirmation transaction.
 * 
 * @author Abhilasha Nigam
 */
public interface IOrderConfirmationDAO {
	/**
	 * This method insert/update OrderConfirmation transaction details in database.
	 * @param pneDTO
	 * @param transStatusDTO
	 * @return String
	 */
	public String storeOrderConfirmationDetails(PapiNetEnvelopeDTO pneDTO, TransactionStatusDTO transStatusDTO);
}