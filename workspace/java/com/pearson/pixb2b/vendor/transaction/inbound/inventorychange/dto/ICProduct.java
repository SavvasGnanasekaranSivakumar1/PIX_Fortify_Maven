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
 * Title		: 	ICProduct.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   3 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto;

import java.util.ArrayList;


/**
 * ICProduct is a data transfer object to store the 
 * Inventory Change details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ICProduct implements java.io.Serializable {
	private static final long serialVersionUID = 3510213022627197687L;

	private ArrayList prodIdentifier = null;
	private ICPaper icPaper = null;
	private ICBookManufacturing icBook = null;
	
	
	/**
	 * Default constructor.
	 */
	public ICProduct() {
		super();
	}


	/**
	 * @return Returns the icBook.
	 */
	public ICBookManufacturing getIcBook() {
		return icBook;
	}


	/**
	 * @param icBook The icBook to set.
	 */
	public void setIcBook(ICBookManufacturing icBook) {
		this.icBook = icBook;
	}


	/**
	 * @return Returns the icPaper.
	 */
	public ICPaper getIcPaper() {
		return icPaper;
	}


	/**
	 * @param icPaper The icPaper to set.
	 */
	public void setIcPaper(ICPaper icPaper) {
		this.icPaper = icPaper;
	}


	/**
	 * @return Returns the prodIdentifier.
	 */
	public ArrayList getProdIdentifier() {
		return prodIdentifier;
	}


	/**
	 * @param prodIdentifier The prodIdentifier to set.
	 */
	public void setProdIdentifier(ArrayList prodIdentifier) {
		this.prodIdentifier = prodIdentifier;
	}


		
	
}