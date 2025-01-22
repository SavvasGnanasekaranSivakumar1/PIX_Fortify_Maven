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
 * Title		: 	SPBCWidth.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	26 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPBCWidth is a data transfer object to store the Spec Packing Box
 * Characteristics Width details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPBCWidth implements java.io.Serializable {
	private static final long serialVersionUID = -5300134571567794937L;
	
	private SPBCWidValue value			= null;
	private SPBCWidRangeMin rangeMin	= null;
	private SPBCWidRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public SPBCWidth() {
		super();
	}
	/**
	 * @return the value
	 */
	public SPBCWidValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(SPBCWidValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public SPBCWidRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(SPBCWidRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public SPBCWidRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(SPBCWidRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
