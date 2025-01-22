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
 * Title		: 	SGAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   	19 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SGAddText is a data transfer object to store the General 
 * Specification AdditionalText details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SGAddText implements java.io.Serializable {
	private static final long serialVersionUID = 4642668871575748572L;
	
	private String sgAddText = null;
	/**
	 * Default constructor.
	 */
	public SGAddText() {
		super();
	}
	/**
	 * @return the sgAddText
	 */
	public String getSgAddText() {
		return sgAddText;
	}
	/**
	 * @param sgAddText the sgAddText to set
	 */
	public void setSgAddText(String sgAddText) {
		this.sgAddText = sgAddText;
	}
}
