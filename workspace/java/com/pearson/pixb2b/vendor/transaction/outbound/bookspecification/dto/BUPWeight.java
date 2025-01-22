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
 * Title		: 	BUPWeight.java
 * Company 		: 	HCL Technologies
  *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	26 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BUPWeight is a data transfer object to store the Book Spec Packing
 * Unit Characteristics Weight and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BUPWeight implements java.io.Serializable {
	private static final long serialVersionUID = 7696905825177811809L;
	
	private BUPWgtValue value			= null;
	private BUPWgtRangeMin rangeMin		= null;
	private BUPWgtRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public BUPWeight() {
		super();
	}
	/**
	 * @return the value
	 */
	public BUPWgtValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(BUPWgtValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public BUPWgtRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(BUPWgtRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public BUPWgtRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(BUPWgtRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
