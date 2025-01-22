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
 * Title		: 	OMAddtext.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * OMAddtext is a data transfer object to store the Specification Component 
 * Placement InOrder Of Matter AdditionalText details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class OMAddtext implements java.io.Serializable {
	private static final long serialVersionUID = -6135739198478750339L;
	
	private String omAddtext	= null;
	/**
	 * Default constructor.
	 */
	public OMAddtext() {
		super();
	}
	/**
	 * @return the omAddtext
	 */
	public String getOmAddtext() {
		return omAddtext;
	}
	/**
	 * @param omAddtext the omAddtext to set
	 */
	public void setOmAddtext(String omAddtext) {
		this.omAddtext = omAddtext;
	}
}
