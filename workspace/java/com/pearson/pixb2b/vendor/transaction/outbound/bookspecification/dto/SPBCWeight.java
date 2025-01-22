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
 * Title		: 	SPBCWeight.java
 * Company 		: 	HCL Technologies
  *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	26 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPBCWeight is a data transfer object to store the Spec Packing Box
 * Characteristics Weight details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPBCWeight implements java.io.Serializable {
	private static final long serialVersionUID = -1170932938656517749L;
	
	private SPBCWgtValue value			= null;
	private SPBCWgtRangeMin rangeMin	= null;
	private SPBCWgtRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public SPBCWeight() {
		super();
	}
	/**
	 * @return the value
	 */
	public SPBCWgtValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(SPBCWgtValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public SPBCWgtRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(SPBCWgtRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public SPBCWgtRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(SPBCWgtRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
