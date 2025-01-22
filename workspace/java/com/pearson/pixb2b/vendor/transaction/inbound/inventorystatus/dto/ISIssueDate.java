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
 * Title		: 	ISIssueDate.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   24 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto;




/**
 * OCIssueDate is a data transfer object to store the 
 * Order Confirmation details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
 public class ISIssueDate implements java.io.Serializable {

	private static final long serialVersionUID = -974157785212292461L;
	private ISDate date = null;
	
	
	
	/**
	 * Default constructor.
	 */
	public ISIssueDate() {
		super();
	}



	/**
	 * @return Returns the date.
	 */
	public ISDate getDate() {
		return date;
	}



	/**
	 * @param date The date to set.
	 */
	public void setDate(ISDate date) {
		this.date = date;
	}



	
	
}