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
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto;

/**
 * ICBookManufacturingis a transfer object to store the 
 * Inventory Change details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ICBookManufacturing implements java.io.Serializable {
	private static final long serialVersionUID = -6449367797757020487L;
	
	private ICBookClassification bookClassification = null;
	
	/**
	 * Default constructor.
	 */
	public ICBookManufacturing() {
		super();
	}

	/**
	 * @return Returns the bookClassification.
	 */
	public ICBookClassification getBookClassification() {
		return bookClassification;
	}

	/**
	 * @param bookClassification The bookClassification to set.
	 */
	public void setBookClassification(ICBookClassification bookClassification) {
		this.bookClassification = bookClassification;
	}

	
	
	}