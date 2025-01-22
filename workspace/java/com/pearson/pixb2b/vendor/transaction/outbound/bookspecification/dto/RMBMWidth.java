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
 * Title		: 	RMBMWidth.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	25 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * RMBMWidth is a data transfer object to store the Ribbon Width 
 * details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class RMBMWidth implements java.io.Serializable {
	private static final long serialVersionUID = 1702293292217352054L;
	
	private RMWValue value			= null;
	private RMWRangeMin rangeMin	= null;
	private RMWRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public RMBMWidth() {
		super();
	}
	/**
	 * @return the value
	 */
	public RMWValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(RMWValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public RMWRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(RMWRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public RMWRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(RMWRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
