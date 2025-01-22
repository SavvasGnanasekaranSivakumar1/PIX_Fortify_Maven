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
 * Title		: 	IInventoryChangeDAO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			  Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Abhilasha Nigam 	26 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dao;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
/**
 * IInventoryChangeDAO is an interface to communicate with the PIX database
 * and process the InventoryChange transaction.
 * 
 * @author Abhilasha Nigam
 */
public interface IInventoryChangeDAO {
	/**
	 * This method insert/update InventoryChange transaction details in database.
	 * @param pneDTO
	 * @param transStatusDTO
	 * @param errorList
	 * @param inDirArchiveXmlInvalid
	 * @return String
	 */
	public String storeInventoryChangeDetails(PapiNetEnvelopeDTO pneDTO, TransactionStatusDTO transStatusDTO, ArrayList errorList, String inDirArchiveXmlInvalid);
}