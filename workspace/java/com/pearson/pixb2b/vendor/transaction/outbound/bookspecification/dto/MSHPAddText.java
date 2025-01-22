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
 * Title		: 	MSHPAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MSHPAddText is a data transfer object to store the Specification NonPress Component 
 * Media Slide HolePunch Information AdditionalText details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MSHPAddText implements java.io.Serializable {
	private static final long serialVersionUID = -1571938995349602738L;
	
	private String msHPAddText	= null;
	/**
	 * Default constructor.
	 */
	public MSHPAddText() {
		super();
	}
	/**
	 * @return the msHPAddText
	 */
	public String getMsHPAddText() {
		return msHPAddText;
	}
	/**
	 * @param msHPAddText the msHPAddText to set
	 */
	public void setMsHPAddText(String msHPAddText) {
		this.msHPAddText = msHPAddText;
	}
}
