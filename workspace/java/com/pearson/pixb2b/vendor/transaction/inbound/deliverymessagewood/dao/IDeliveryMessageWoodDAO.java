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
 * Title		: 	IDeliveryMessageWoodDAO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		22 March 2010 		Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.deliverymessagewood.dao;

import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
/**
 * IDeliveryMessageWoodDAO is an interface to communicate with the PIX database
 * and process the DeliveryMessageWood transaction.
 * 
 * @author Ashish Agrawal
 */
public interface IDeliveryMessageWoodDAO {
	/**
	 * This method insert/update DeliveryMessageWood transaction details in database.
	 * @param pneDTO
	 * @param transStatusDTO
	 * @return String
	 */
	public String storeDeliveryMessageWoodDetails(PapiNetEnvelopeDTO pneDTO, TransactionStatusDTO transStatusDTO);
}