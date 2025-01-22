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
 * Title		: 	IAdvanceShipmentNoticeDAO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		17 May,2010 	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.asn.dao;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.shared.dto.envelope.PapiNetEnvelopeDTO;
import com.pearson.pixb2b.vendor.transaction.shared.dto.transstatus.TransactionStatusDTO;
/**
 * IAdvanceShipmentNoticeDAO is an interface to communicate with the PIX database
 * and process the ASN transaction.
 * 
 * @author Ashish Agrawal
 */
public interface IAdvanceShipmentNoticeDAO {
	/**
	 * This method insert/update ASN transaction details in database.
	 * @param pneDTO
	 * @param transStatusDTO
	 * @param errorList
	 * @param inDirArchiveXmlInvalid
	 * @return String
	 */
	public String storeAdvanceShipmentNoticeDetails(PapiNetEnvelopeDTO pneDTO, TransactionStatusDTO transStatusDTO, ArrayList errorList, String inDirArchiveXmlInvalid);
}