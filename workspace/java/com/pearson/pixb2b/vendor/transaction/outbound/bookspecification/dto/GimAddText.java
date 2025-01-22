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
 * Title		: 	GimAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * GimAddText is a data transfer object to store the Specification NonPress Component 
 * Gimmick AdditionalText details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class GimAddText implements java.io.Serializable {
	private static final long serialVersionUID = 7217543587622138220L;
	
	private String gimAddText	= null;
	/**
	 * Default constructor.
	 */
	public GimAddText() {
		super();
	}
	/**
	 * @return the gimAddText
	 */
	public String getGimAddText() {
		return gimAddText;
	}
	/**
	 * @param gimAddText the gimAddText to set
	 */
	public void setGimAddText(String gimAddText) {
		this.gimAddText = gimAddText;
	}
}
