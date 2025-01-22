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
 * Title		: 	TransmissionReceiver.java
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
 * TransmissionReceiver is a data transfer object to store the Transmission Receiver 
 * Information for Inbound and Outbound transactions.
 * 
 * @author Ashish Agrawal
 */
public class TransmissionReceiver implements java.io.Serializable {
	private static final long serialVersionUID = -107498816701596189L;
	
	private ArrayList pIdTransmissionReceiverList = null;
	/**
	 * Default constructor.
	 */
	public TransmissionReceiver() {
		super();
	}
	/**
	 * @return the pIdTransmissionReceiverList
	 */
	public ArrayList getPIdTransmissionReceiverList() {
		return pIdTransmissionReceiverList;
	}
	/**
	 * @param idTransmissionReceiverList the pIdTransmissionReceiverList to set
	 */
	public void setPIdTransmissionReceiverList(ArrayList idTransmissionReceiverList) {
		pIdTransmissionReceiverList = idTransmissionReceiverList;
	}
}
