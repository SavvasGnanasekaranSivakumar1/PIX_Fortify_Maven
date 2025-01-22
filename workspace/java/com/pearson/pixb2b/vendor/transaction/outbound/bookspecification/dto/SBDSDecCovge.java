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
 * Title		: 	SBDSDecCovge.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	25 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SBDSDecCovge is a data transfer object to store the specification Binding Case   
 * Decoration Coverage details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SBDSDecCovge implements java.io.Serializable {
	private static final long serialVersionUID = 5885773732693309575L;
	
	private SBDSValue value	 		= null;
	private SBDSRangeMin rangeMin  	= null;
	private SBDSRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public SBDSDecCovge() {
		super();
	}
	/**
	 * @return the value
	 */
	public SBDSValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(SBDSValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public SBDSRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(SBDSRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public SBDSRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(SBDSRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
