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
 * Title		: 	SCAddText.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SCAddText is a data transfer object to store the Specification Component 
 * AdditionalText details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SCAddText implements java.io.Serializable {
	private static final long serialVersionUID = -4896365367118545396L;
	
	private String scAddtext	= null;
	/**
	 * Default constructor.
	 */
	public SCAddText() {
		super();
	}
	/**
	 * @return the scAddtext
	 */
	public String getScAddtext() {
		return scAddtext;
	}
	/**
	 * @param scAddtext the scAddtext to set
	 */
	public void setScAddtext(String scAddtext) {
		this.scAddtext = scAddtext;
	}
}
