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
 * Title		: 	BUPHeight.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	26 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BUPHeight is a data transfer object to store the Book Spec Packing
 * Unit Characteristics Height details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BUPHeight implements java.io.Serializable {
	private static final long serialVersionUID = 4186651296164173095L;
	
	private BUPHValue value		   = null;
	private BUPHRangeMin rangeMin  = null;
	private BUPHRangeMax rangeMax  = null;
	/**
	 * Default constructor.
	 */
	public BUPHeight() {
		super();
	}
	/**
	 * @return the value
	 */
	public BUPHValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(BUPHValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public BUPHRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(BUPHRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public BUPHRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(BUPHRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
