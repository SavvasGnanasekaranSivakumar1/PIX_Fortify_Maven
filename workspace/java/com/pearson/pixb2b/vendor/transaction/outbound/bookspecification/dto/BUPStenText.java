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
 * Title		: 	BUPStenText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	26 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BUPStenText is a data transfer object to store the Book Spec Unit Packing
 * Stencil Text details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BUPStenText implements java.io.Serializable {
	private static final long serialVersionUID = 5108088991100499004L;
	
	private String bupStenText = null;
	/**
	 * Default constructor.
	 */
	public BUPStenText() {
		super();
	}
	/**
	 * @return the bupStenText
	 */
	public String getBupStenText() {
		return bupStenText;
	}
	/**
	 * @param bupStenText the bupStenText to set
	 */
	public void setBupStenText(String bupStenText) {
		this.bupStenText = bupStenText;
	}
}
