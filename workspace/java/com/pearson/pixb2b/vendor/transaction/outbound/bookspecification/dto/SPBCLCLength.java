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
 * Title		: 	SPBCLCLength.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	26 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPBCLCLength is a data transfer object to store the Spec Packing
 * Box Label Characteristics Length details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPBCLCLength implements java.io.Serializable {
	private static final long serialVersionUID = 2563520012691441691L;
	
	private SPBCLCLValue value	 		= null;
	private SPBCLCLRangeMin rangeMin  	= null;
	private SPBCLCLRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public SPBCLCLength() {
		super();
	}
	/**
	 * @return the value
	 */
	public SPBCLCLValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(SPBCLCLValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public SPBCLCLRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(SPBCLCLRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public SPBCLCLRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(SPBCLCLRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
