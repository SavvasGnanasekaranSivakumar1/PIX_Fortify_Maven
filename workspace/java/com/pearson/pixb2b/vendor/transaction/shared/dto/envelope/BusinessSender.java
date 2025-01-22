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
 * Title		: 	BusinessSender.java
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
 * BusinessSender is a data transfer object to store the Business Sender 
 * Information for Inbound and Outbound transactions.
 * 
 * @author Ashish Agrawal
 */
public class BusinessSender implements java.io.Serializable {
	private static final long serialVersionUID = 6575247029923508259L;
	
	private ArrayList pIdBusinessSenderList = null;
	/**
	 * Default constructor.
	 */
	public BusinessSender() {
		super();
	}
	/**
	 * @return the pIdBusinessSenderList
	 */
	public ArrayList getPIdBusinessSenderList() {
		return pIdBusinessSenderList;
	}
	/**
	 * @param idBusinessSenderList the pIdBusinessSenderList to set
	 */
	public void setPIdBusinessSenderList(ArrayList idBusinessSenderList) {
		pIdBusinessSenderList = idBusinessSenderList;
	}
}
