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
 * Title		: 	BUPWidth.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	26 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BUPWidth is a data transfer object to store the Book Spec Packing
 * Unit Characteristics Width details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BUPWidth implements java.io.Serializable {
	private static final long serialVersionUID = -7892876446864923869L;
	
	private BUPWidthValue value			= null;
	private BUPWidthRangeMin rangeMin	= null;
	private BUPWidthRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public BUPWidth() {
		super();
	}
	/**
	 * @return the value
	 */
	public BUPWidthValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(BUPWidthValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public BUPWidthRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(BUPWidthRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public BUPWidthRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(BUPWidthRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
