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
 * Title		: 	ISBookManufacturing.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   2 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto;



/**
 * ISBookManufacturingis a transfer object to store the 
 * Inventory Status details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ISBookManufacturing implements java.io.Serializable {
	private static final long serialVersionUID = -1880372643586782911L;
	
	private ISBookClassification bookClassification = null;
	
	/**
	 * Default constructor.
	 */
	public ISBookManufacturing() {
		super();
	}

	/**
	 * @return Returns the bookClassification.
	 */
	public ISBookClassification getBookClassification() {
		return bookClassification;
	}

	/**
	 * @param bookClassification The bookClassification to set.
	 */
	public void setBookClassification(ISBookClassification bookClassification) {
		this.bookClassification = bookClassification;
	}
	
	
	/**
	 * @return Returns the bookClassification.
	
	public BookClassification getBookClassification() {
		return bookClassification;
	}


	/**
	 * @param bookClassification The bookClassification to set.
	 
	public void setBookClassification(BookClassification bookClassification) {
		this.bookClassification = bookClassification;
	}
	*/

	
}