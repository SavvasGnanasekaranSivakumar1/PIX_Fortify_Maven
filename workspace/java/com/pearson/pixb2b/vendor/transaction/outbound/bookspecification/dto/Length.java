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
 * Title		: 	Length.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	20 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * Length is a data transfer object to store the 
 * Length details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class Length implements java.io.Serializable {
	private static final long serialVersionUID = 8481139621535408181L;
	
	private LengthVal value			= null;
	private LenRangeMin lenRangeMin = null;
	private LenRangeMax lenRangeMax = null;
	/**
	 * Default constructor.
	 */
	public Length() {
		super();
	}
	/**
	 * @return the value
	 */
	public LengthVal getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(LengthVal value) {
		this.value = value;
	}
	/**
	 * @return the lenRangeMin
	 */
	public LenRangeMin getLenRangeMin() {
		return lenRangeMin;
	}
	/**
	 * @param lenRangeMin the lenRangeMin to set
	 */
	public void setLenRangeMin(LenRangeMin lenRangeMin) {
		this.lenRangeMin = lenRangeMin;
	}
	/**
	 * @return the lenRangeMax
	 */
	public LenRangeMax getLenRangeMax() {
		return lenRangeMax;
	}
	/**
	 * @param lenRangeMax the lenRangeMax to set
	 */
	public void setLenRangeMax(LenRangeMax lenRangeMax) {
		this.lenRangeMax = lenRangeMax;
	}
}