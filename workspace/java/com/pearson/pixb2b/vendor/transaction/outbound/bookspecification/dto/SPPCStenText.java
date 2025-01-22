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
 * Title		: 	SPPCStenText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCStenText is a data transfer object to store the Spec Packing Pallet
 * Packaging Characteristics Stencil Text details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCStenText implements java.io.Serializable {
	private static final long serialVersionUID = 8435174244195990195L;
	
	private String sppcStenText = null;
	/**
	 * Default constructor.
	 */
	public SPPCStenText() {
		super();
	}
	/**
	 * @return the sppcStenText
	 */
	public String getSppcStenText() {
		return sppcStenText;
	}
	/**
	 * @param sppcStenText the sppcStenText to set
	 */
	public void setSppcStenText(String sppcStenText) {
		this.sppcStenText = sppcStenText;
	}
}
