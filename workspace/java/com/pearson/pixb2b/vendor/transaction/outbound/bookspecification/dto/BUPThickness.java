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
 * Title		: 	BUPThickness.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	26 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BUPThickness is a data transfer object to store the Book Spec Packing
 * Unit Characteristics Thickness details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BUPThickness implements java.io.Serializable {
	private static final long serialVersionUID = -5754602902763015984L;
	
	private BUPTValue value			= null;
	private BUPTRangeMin rangeMin	= null;
	private BUPTRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public BUPThickness() {
		super();
	}
	/**
	 * @return the value
	 */
	public BUPTValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(BUPTValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public BUPTRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(BUPTRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public BUPTRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(BUPTRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
