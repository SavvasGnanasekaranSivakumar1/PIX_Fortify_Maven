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
 * Title		: 	SPBCLength.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	26 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPBCLength is a data transfer object to store the Spec Packing Box
 * Characteristics Length details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPBCLength implements java.io.Serializable {
	private static final long serialVersionUID = -5561080344387827309L;
	
	private SPBCLValue value	 	= null;
	private SPBCLRangeMin rangeMin  = null;
	private SPBCLRangeMax rangeMax  = null;
	/**
	 * Default constructor.
	 */
	public SPBCLength() {
		super();
	}
	/**
	 * @return the value
	 */
	public SPBCLValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(SPBCLValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public SPBCLRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(SPBCLRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public SPBCLRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(SPBCLRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
