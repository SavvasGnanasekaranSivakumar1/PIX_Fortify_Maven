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
 * Title		: 	BookClassification.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   26 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;


/**
 * BookClassification is a transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class BookClassification implements java.io.Serializable {
	private static final long serialVersionUID = -5377394529848716610L;
	
	private String bookClassificationType = null;
	private BookSubClassification bookSubClassification = null;

	/**
	 * Default constructor.
	 */
	public BookClassification() {
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


	/**
	 * @return Returns the bookSubClassification.
	 */
	public BookSubClassification getBookSubClassification() {
		return bookSubClassification;
	}


	/**
	 * @param bookSubClassification The bookSubClassification to set.
	 */
	public void setBookSubClassification(BookSubClassification bookSubClassification) {
		this.bookSubClassification = bookSubClassification;
	}

	}