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
 * Title		: 	BookManufacturing.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   9 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;

import java.util.ArrayList;


/**
 * BookManufacturingis a transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class BookManufacturing implements java.io.Serializable {
	private static final long serialVersionUID = -1024008127374031127L;
	
	
	//private String bookClassificationType = null;
	private ArrayList SuppliedComponentInfo = null;
	private ArrayList bookClassification = null;
	
	/**
	 * Default constructor.
	 */
	public BookManufacturing() {
		super();
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

	
	/**
	 * @return Returns the suppliedComponentInfo.
	 */
	public ArrayList getSuppliedComponentInfo() {
		return SuppliedComponentInfo;
	}


	/**
	 * @param suppliedComponentInfo The suppliedComponentInfo to set.
	 */
	public void setSuppliedComponentInfo(ArrayList suppliedComponentInfo) {
		SuppliedComponentInfo = suppliedComponentInfo;
	}


	/**
	 * @return Returns the bookClassification.
	 */
	public ArrayList getBookClassification() {
		return bookClassification;
	}


	/**
	 * @param bookClassification The bookClassification to set.
	 */
	public void setBookClassification(ArrayList bookClassification) {
		this.bookClassification = bookClassification;
	}


	


	
}