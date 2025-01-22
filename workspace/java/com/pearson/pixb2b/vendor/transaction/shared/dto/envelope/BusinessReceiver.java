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
 * Title		: 	BusinessReceiver.java
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
 * BusinessReceiver is a data transfer object to store the Business Receiver 
 * Information for Inbound and Outbound transactions.
 * 
 * @author Ashish Agrawal
 */
public class BusinessReceiver implements java.io.Serializable {
	private static final long serialVersionUID = 5994164690713113967L;
	
	private ArrayList pIdBusinessReceiverList = null;
	/**
	 * Default constructor.
	 */
	public BusinessReceiver() {
		super();
	}
	/**
	 * @return the pIdBusinessReceiverList
	 */
	public ArrayList getPIdBusinessReceiverList() {
		return pIdBusinessReceiverList;
	}
	/**
	 * @param idBusinessReceiverList the pIdBusinessReceiverList to set
	 */
	public void setPIdBusinessReceiverList(ArrayList idBusinessReceiverList) {
		pIdBusinessReceiverList = idBusinessReceiverList;
	}
}
