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
 * Title		: 	TransmissionSender.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   26 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.envelope;

import java.util.ArrayList;

/**
 * TransmissionSender is a data transfer object to store the Transmission Sender 
 * Information for Inbound and Outbound transactions.
 * 
 * @author Ashish Agrawal
 */
public class TransmissionSender implements java.io.Serializable {
	private static final long serialVersionUID = 5471720437973186821L;
	
	private ArrayList pIdTransmissionSenderList = null;
	/**
	 * Default constructor.
	 */
	public TransmissionSender() {
		super();
	}
	/**
	 * @return the pIdTransmissionSenderList
	 */
	public ArrayList getPIdTransmissionSenderList() {
		return pIdTransmissionSenderList;
	}
	/**
	 * @param idTransmissionSenderList the pIdTransmissionSenderList to set
	 */
	public void setPIdTransmissionSenderList(ArrayList idTransmissionSenderList) {
		pIdTransmissionSenderList = idTransmissionSenderList;
	}
}
