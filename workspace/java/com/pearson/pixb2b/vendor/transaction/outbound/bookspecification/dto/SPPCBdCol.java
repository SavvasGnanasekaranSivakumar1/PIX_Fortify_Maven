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
 * Title		: 	SPPCBdCol.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCBdCol is a data transfer object to store the Spec Packing Pallet Packaging
 * Band Characteristics Colour details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCBdCol implements java.io.Serializable {
	private static final long serialVersionUID = -2494529704783372726L;
	
	private String sppcBdCol = null;
	/**
	 * Default constructor.
	 */
	public SPPCBdCol() {
		super();
	}
	/**
	 * @return the sppcBdCol
	 */
	public String getSppcBdCol() {
		return sppcBdCol;
	}
	/**
	 * @param sppcBdCol the sppcBdCol to set
	 */
	public void setSppcBdCol(String sppcBdCol) {
		this.sppcBdCol = sppcBdCol;
	}
}
