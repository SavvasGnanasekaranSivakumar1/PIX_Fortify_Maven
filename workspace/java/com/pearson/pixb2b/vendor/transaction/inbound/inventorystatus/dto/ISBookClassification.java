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
 * Title		: 	ISBookClassification.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   2 December, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto;


/**
 * ISBookClassification is a transfer object to store the 
 * Inventory Status details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ISBookClassification implements java.io.Serializable {
	private static final long serialVersionUID = 3248184714758155031L;

	private String bookClassificationType = null;
	

	/**
	 * Default constructor.
	 */
	public ISBookClassification() {
		super();
	}
	

	/**
	 * @return Returns the bookClassificationType.
	 */
	public String getBookClassificationType() {
		return bookClassificationType;
	}


	/**
	 * @param bookClassificationType The bookClassificationType to set.
	 */
	public void setBookClassificationType(String bookClassificationType) {
		this.bookClassificationType = bookClassificationType;
	}


	

	}