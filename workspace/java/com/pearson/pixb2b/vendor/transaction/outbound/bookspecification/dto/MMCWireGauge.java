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
 * Title		: 	MMCWireGauge.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	25 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MMCWireGauge is a data transfer object to store the Mechanical Material  
 * Characteristics WireGauge details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MMCWireGauge implements java.io.Serializable {
	private static final long serialVersionUID = -4113441344972825384L;
	
	private MMCWGValue value		= null;
	private MMCWGRangeMin rangeMin	= null;
	private MMCWGRangeMax rangeMax  = null;
	/**
	 * Default constructor.
	 */
	public MMCWireGauge() {
		super();
	}
	/**
	 * @return the value
	 */
	public MMCWGValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(MMCWGValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public MMCWGRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(MMCWGRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public MMCWGRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(MMCWGRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
