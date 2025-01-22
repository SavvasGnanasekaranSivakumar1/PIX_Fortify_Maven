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
 * Title		: 	MMCThickness.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	25 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MMCThickness is a data transfer object to store the Mechanical Plastic Material  
 * Characteristics Thickness details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MMCThickness implements java.io.Serializable {
	private static final long serialVersionUID = -7397095732106092410L;
	
	private MMCTValue value			= null;
	private MMCTRangeMin rangeMin	= null;
	private MMCTRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public MMCThickness() {
		super();
	}
	/**
	 * @return the value
	 */
	public MMCTValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(MMCTValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public MMCTRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(MMCTRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public MMCTRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(MMCTRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
