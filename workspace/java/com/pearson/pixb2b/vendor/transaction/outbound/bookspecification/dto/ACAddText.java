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
 * Title		: 	ACAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * ACAddText is a data transfer object to store the Specification NonPress Component 
 * Media Audio Cassette AdditionalText details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ACAddText implements java.io.Serializable {
	private static final long serialVersionUID = 745207591286218375L;
	
	private String acAddText	= null;
	/**
	 * Default constructor.
	 */
	public ACAddText() {
		super();
	}
	/**
	 * @return the acAddText
	 */
	public String getAcAddText() {
		return acAddText;
	}
	/**
	 * @param acAddText the acAddText to set
	 */
	public void setAcAddText(String acAddText) {
		this.acAddText = acAddText;
	}
}
