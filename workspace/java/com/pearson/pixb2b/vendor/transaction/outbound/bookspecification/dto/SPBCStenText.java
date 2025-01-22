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
 * Title		: 	SPBCStenText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	26 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPBCStenText is a data transfer object to store the Spec Packing
 * Box Stencil Characteristics Text details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPBCStenText implements java.io.Serializable {
	private static final long serialVersionUID = 3605009934317350246L;
	
	private String spbcStenText = null;
	/**
	 * Default constructor.
	 */
	public SPBCStenText() {
		super();
	}
	/**
	 * @return the spbcStenText
	 */
	public String getSpbcStenText() {
		return spbcStenText;
	}
	/**
	 * @param spbcStenText the spbcStenText to set
	 */
	public void setSpbcStenText(String spbcStenText) {
		this.spbcStenText = spbcStenText;
	}
}
