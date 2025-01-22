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
 * Title		: 	MSWidth.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MSWidth is a data transfer object to store the Specification NonPress Component 
 * Media Slide Width details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MSWidth implements java.io.Serializable {
	private static final long serialVersionUID = -1790975726242457826L;
	
	private MSWValue value		 = null;
	private MSWRangeMin rangeMin = null;
	private MSWRangeMax rangeMax = null;
	/**
	 * Default constructor.
	 */
	public MSWidth() {
		super();
	}
	/**
	 * @return the value
	 */
	public MSWValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(MSWValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public MSWRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(MSWRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public MSWRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(MSWRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
