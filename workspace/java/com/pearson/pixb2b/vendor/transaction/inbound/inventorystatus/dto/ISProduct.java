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
 * Title		: 	Product.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   9 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto;

import java.util.ArrayList;


/**
 * ISProduct is a data transfer object to store the 
 * inventory status details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ISProduct implements java.io.Serializable {
	private static final long serialVersionUID = -6857578297194417655L;

	private ArrayList prodIdentifier = null;
	private ISPaper paper = null;
	private ISBookManufacturing isBook = null;
	
	
	/**
	 * Default constructor.
	 */
	public ISProduct() {
		super();
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


	/**
	 * @return Returns the paper.
	 */
	public ISPaper getPaper() {
		return paper;
	}


	/**
	 * @param paper The paper to set.
	 */
	public void setPaper(ISPaper paper) {
		this.paper = paper;
	}


	/**
	 * @return Returns the isBook.
	 */
	public ISBookManufacturing getIsBook() {
		return isBook;
	}


	/**
	 * @param isBook The isBook to set.
	 */
	public void setIsBook(ISBookManufacturing isBook) {
		this.isBook = isBook;
	}

	
	
	
}