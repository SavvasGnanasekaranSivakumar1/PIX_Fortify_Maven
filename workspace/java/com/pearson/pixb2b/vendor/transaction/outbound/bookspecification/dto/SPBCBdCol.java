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
 * Title		: 	SPBCBdCol.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	26 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPBCBdCol is a data transfer object to store the Spec Packing
 * Box Band Colour Characteristics details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPBCBdCol implements java.io.Serializable {
	private static final long serialVersionUID = -2808696820407426530L;
	
	private String spbcBdCol = null;
	/**
	 * Default constructor.
	 */
	public SPBCBdCol() {
		super();
	}
	/**
	 * @return the spbcBdCol
	 */
	public String getSpbcBdCol() {
		return spbcBdCol;
	}
	/**
	 * @param spbcBdCol the spbcBdCol to set
	 */
	public void setSpbcBdCol(String spbcBdCol) {
		this.spbcBdCol = spbcBdCol;
	}
}
