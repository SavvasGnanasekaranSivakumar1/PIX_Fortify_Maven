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
 * Title		: 	OCSummary.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   3 November, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto;


/**
 * OCSummary is a data transfer object to store the 
 * Order Confirmation details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class OCSummary implements java.io.Serializable {
	
	private static final long serialVersionUID = -1198671228296530262L;
	
	private String totalLineItems = null;
	
	/**
	 * Default constructor.
	 */
	public OCSummary() {
		super();
	}
	
	/**
	 * @return Returns the totalLineItems.
	 */
	public String getTotalLineItems() {
		return totalLineItems;
	}
	
	/**
	 * @param totalLineItems The totalLineItems to set.
	 */
	public void setTotalLineItems(String totalLineItems) {
		this.totalLineItems = totalLineItems;
	}
	
	
	
}
