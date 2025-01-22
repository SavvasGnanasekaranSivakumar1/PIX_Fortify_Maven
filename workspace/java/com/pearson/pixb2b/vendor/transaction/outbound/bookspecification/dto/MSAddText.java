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
 * Title		: 	MSAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MSAddText is a data transfer object to store the Specification NonPress Component 
 * Media Slide AdditionalText details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MSAddText implements java.io.Serializable {
	private static final long serialVersionUID = 4912373666275273372L;
	
	private String msAddText	= null;
	/**
	 * Default constructor.
	 */
	public MSAddText() {
		super();
	}
	/**
	 * @return the msAddText
	 */
	public String getMsAddText() {
		return msAddText;
	}
	/**
	 * @param msAddText the msAddText to set
	 */
	public void setMsAddText(String msAddText) {
		this.msAddText = msAddText;
	}
}
