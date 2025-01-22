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
 * Title		: 	BUPAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	26 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BUPAddText is a data transfer object to store the Book Spec Unit Packing  
 * Additional Text details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BUPAddText implements java.io.Serializable {
	private static final long serialVersionUID = -5743136855279962796L;
	
	private String bupAddText = null;
	/**
	 * Default constructor.
	 */
	public BUPAddText() {
		super();
	}
	/**
	 * @return the bupAddText
	 */
	public String getBupAddText() {
		return bupAddText;
	}
	/**
	 * @param bupAddText the bupAddText to set
	 */
	public void setBupAddText(String bupAddText) {
		this.bupAddText = bupAddText;
	}
}