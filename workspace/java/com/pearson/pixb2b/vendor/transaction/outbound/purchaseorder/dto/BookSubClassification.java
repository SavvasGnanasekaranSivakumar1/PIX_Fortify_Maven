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
 * Title		: 	BookSubClassification.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   23 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;


/**
 * BookSubClassification is a transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class BookSubClassification implements java.io.Serializable {
	private static final long serialVersionUID = -6590263617074774012L;
	
	private String bookSubClassificationType = null;
	

	/**
	 * Default constructor.
	 */
	public BookSubClassification() {
		super();
	}


	/**
	 * @return Returns the bookSubClassificationType.
	 */
	public String getBookSubClassificationType() {
		return bookSubClassificationType;
	}


	/**
	 * @param bookSubClassificationType The bookSubClassificationType to set.
	 */
	public void setBookSubClassificationType(String bookSubClassificationType) {
		this.bookSubClassificationType = bookSubClassificationType;
	}
}