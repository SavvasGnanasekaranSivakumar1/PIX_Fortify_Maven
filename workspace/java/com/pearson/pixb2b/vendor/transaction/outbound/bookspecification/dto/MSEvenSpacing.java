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
 * Title		: 	MSEvenSpacing.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MSEvenSpacing is a data transfer object to store the Specification NonPress Component 
 * Media Slide Punch Hole Even Spacing details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MSEvenSpacing implements java.io.Serializable {
	private static final long serialVersionUID = -2748877539702386032L;
	
	private MSESValue esValue = null;
	/**
	 * Default constructor.
	 */
	public MSEvenSpacing() {
		super();
	}
	/**
	 * @return the esValue
	 */
	public MSESValue getEsValue() {
		return esValue;
	}
	/**
	 * @param esValue the esValue to set
	 */
	public void setEsValue(MSESValue esValue) {
		this.esValue = esValue;
	}
}
