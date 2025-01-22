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
 * Title		: 	BUPLCLength.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	26 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BUPLCLength is a data transfer object to store the Book Spec Unit Packing
 * Label Characteristics Length details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BUPLCLength implements java.io.Serializable {
	private static final long serialVersionUID = -1694454377697469330L;
	
	private BUPLCLValue value	 		= null;
	private BUPLCLRangeMin rangeMin  	= null;
	private BUPLCLRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public BUPLCLength() {
		super();
	}
	/**
	 * @return the value
	 */
	public BUPLCLValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(BUPLCLValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public BUPLCLRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(BUPLCLRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public BUPLCLRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(BUPLCLRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
