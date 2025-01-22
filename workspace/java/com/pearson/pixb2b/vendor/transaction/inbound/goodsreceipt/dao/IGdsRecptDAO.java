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
 * Title		: 	IGdsRecptDAO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Abhilasha Nigam 14 Jan,2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dao;
import java.util.ArrayList;
import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
/**
 * IGdsRecptDAO is an interface to communicate with the PIX database
 * and process the GR transaction.
 * 
 * @author Abhilasha Nigam
 */
public interface IGdsRecptDAO {
	/**
	 * This method insert/update Usage transaction details in database.
	 * @param pneDTO
	 * @param transStatusDTO
	 * @return String
	 */
	public String processGRDetails(PapiNetEnvelopeDTO pneDTO, TransactionStatusDTO transStatusDTO,ArrayList errorList);
}