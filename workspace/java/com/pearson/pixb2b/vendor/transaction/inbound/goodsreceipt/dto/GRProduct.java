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
 * Title		: 	GRProduct.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   13 Jan, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto;

import java.util.ArrayList;


/**
 *GRProduct is a data transfer object to store the 
 *goodsreceipt details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class GRProduct implements java.io.Serializable {
	private static final long serialVersionUID = -7296831350213096749L;

	private ArrayList prodIdentifier = null;
	private GRPaper grPaper = null;
	
	
	
	/**
	 * Default constructor.
	 */
	public GRProduct() {
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
	 * @return Returns the grPaper.
	 */
	public GRPaper getGrPaper() {
		return grPaper;
	}



	/**
	 * @param grPaper The grPaper to set.
	 */
	public void setGrPaper(GRPaper grPaper) {
		this.grPaper = grPaper;
	}


}