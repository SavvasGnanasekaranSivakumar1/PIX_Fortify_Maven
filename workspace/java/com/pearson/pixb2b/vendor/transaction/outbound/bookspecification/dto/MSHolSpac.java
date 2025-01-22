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
 * Title		: 	MSHolSpac.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MSHolSpac is a data transfer object to store the Specification NonPress Component 
 * Media Slide Punch Hole Spacing details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MSHolSpac implements java.io.Serializable {
	private static final long serialVersionUID = 1721165075426120980L;
	
	private MSEvenSpacing msEvenSpacing	= null;
	private String unevenSpacing 		= null;
	/**
	 * Default constructor.
	 */
	public MSHolSpac() {
		super();
	}
	/**
	 * @return the msEvenSpacing
	 */
	public MSEvenSpacing getMsEvenSpacing() {
		return msEvenSpacing;
	}
	/**
	 * @param msEvenSpacing the msEvenSpacing to set
	 */
	public void setMsEvenSpacing(MSEvenSpacing msEvenSpacing) {
		this.msEvenSpacing = msEvenSpacing;
	}
	/**
	 * @return the unevenSpacing
	 */
	public String getUnevenSpacing() {
		return unevenSpacing;
	}
	/**
	 * @param unevenSpacing the unevenSpacing to set
	 */
	public void setUnevenSpacing(String unevenSpacing) {
		this.unevenSpacing = unevenSpacing;
	}
}
