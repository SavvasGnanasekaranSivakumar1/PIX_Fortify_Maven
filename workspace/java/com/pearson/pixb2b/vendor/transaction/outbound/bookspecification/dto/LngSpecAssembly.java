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
 * Title		: 	LngSpecAssembly.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   23 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * LngSpecAssembly is a data transfer object to store the 
 * Specification Assembly Length details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class LngSpecAssembly implements java.io.Serializable {
	private static final long serialVersionUID = 9028180264399820612L;
	
	private LngValue value	 	  = null;
	private LngRangeMin rangeMin  = null;
	private LngRangeMax rangeMax  = null;
	/**
	 * Default constructor.
	 */
	public LngSpecAssembly() {
		super();
	}
	public LngValue getValue() {
		return value;
	}
	public void setValue(LngValue value) {
		this.value = value;
	}
	public LngRangeMin getRangeMin() {
		return rangeMin;
	}
	public void setRangeMin(LngRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	public LngRangeMax getRangeMax() {
		return rangeMax;
	}
	public void setRangeMax(LngRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
