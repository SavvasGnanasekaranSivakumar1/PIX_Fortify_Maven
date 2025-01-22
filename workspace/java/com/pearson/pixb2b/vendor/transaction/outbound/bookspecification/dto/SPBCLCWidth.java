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
 * Title		: 	SPBCLCWidth.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	26 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPBCLCWidth is a data transfer object to store the Spec Packing
 * Box Label Characteristics Width details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPBCLCWidth implements java.io.Serializable {
	private static final long serialVersionUID = 1649883959387366718L;
	
	private SPBCLCWidValue value		= null;
	private SPBCLCWidRangeMin rangeMin	= null;
	private SPBCLCWidRangeMax rangeMax  = null;
	/**
	 * Default constructor.
	 */
	public SPBCLCWidth() {
		super();
	}
	/**
	 * @return the value
	 */
	public SPBCLCWidValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(SPBCLCWidValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public SPBCLCWidRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(SPBCLCWidRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public SPBCLCWidRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(SPBCLCWidRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
