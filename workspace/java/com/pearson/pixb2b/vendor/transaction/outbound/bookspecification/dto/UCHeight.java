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
 * Title		: 	UCHeight.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   23 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * UCHeight is a data transfer object to store the Binding Extras Unit 
 * Characteristics Height details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class UCHeight implements java.io.Serializable {
	private static final long serialVersionUID = -4405695367053769751L;
	
	private BEUCHValue value		= null;
	private BEUCHRangeMin rangeMin  = null;
	private BEUCHRangeMax rangeMax  = null;
	/**
	 * Default constructor.
	 */
	public UCHeight() {
		super();
	}
	/**
	 * @return the value
	 */
	public BEUCHValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(BEUCHValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public BEUCHRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(BEUCHRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public BEUCHRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(BEUCHRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
