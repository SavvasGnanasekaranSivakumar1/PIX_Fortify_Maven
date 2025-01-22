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
 * Title		: 	IInventoryStatusDAO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			  Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Abhilasha Nigam 	26 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dao;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
/**
 * IOrderStatusDAO is an interface to communicate with the PIX database
 * and process the OrderStatus transaction.
 * 
 * @author Abhilasha Nigam
 */
public interface IInventoryStatusDAO {
	/**
	 * This method insert/update OrderStatus transaction details in database.
	 * @param pneDTO
	 * @param transStatusDTO
	 * @param errorList
	 * @param inDirArchiveXmlInvalid
	 * @return String
	 */
	public String storeInventoryStatusDetails(PapiNetEnvelopeDTO pneDTO, TransactionStatusDTO transStatusDTO, ArrayList errorList, String inDirArchiveXmlInvalid);
}