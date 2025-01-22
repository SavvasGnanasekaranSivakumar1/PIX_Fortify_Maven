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
 * Title		: 	UCThickness.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   23 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * UCThickness is a data transfer object to store the Binding Extras Unit 
 * Characteristics Thickness details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class UCThickness implements java.io.Serializable {
	private static final long serialVersionUID = -2112361913059811564L;
	
	private BEUCTValue value		= null;
	private BEUCTRangeMin rangeMin	= null;
	private BEUCTRangeMax rangeMax  = null;
	/**
	 * Default constructor.
	 */
	public UCThickness() {
		super();
	}
	/**
	 * @return the value
	 */
	public BEUCTValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(BEUCTValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public BEUCTRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(BEUCTRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public BEUCTRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(BEUCTRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
