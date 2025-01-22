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
 * Title		: 	ICIssueDate.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam  3 dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto;




/**
 * ICIssueDate is a data transfer object to store the 
 * Inventory Change details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
 public class ICIssueDate implements java.io.Serializable {
	private static final long serialVersionUID = 6098243518844127300L;
	
	private ICDate date = null;
	
	
	
	/**
	 * Default constructor.
	 */
	public ICIssueDate() {
		super();
	}



	/**
	 * @return Returns the date.
	 */
	public ICDate getDate() {
		return date;
	}



	/**
	 * @param date The date to set.
	 */
	public void setDate(ICDate date) {
		this.date = date;
	}

}