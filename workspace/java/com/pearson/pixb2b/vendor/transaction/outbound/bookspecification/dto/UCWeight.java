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
 * Title		: 	UCWeight.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   23 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * UCWeight is a data transfer object to store the Binding Extras Unit 
 * Characteristics Width Weight and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class UCWeight implements java.io.Serializable {
	private static final long serialVersionUID = -5901608280204100778L;
	
	private BEUCWgtValue value			= null;
	private BEUCWgtRangeMin rangeMin	= null;
	private BEUCWgtRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public UCWeight() {
		super();
	}
	/**
	 * @return the value
	 */
	public BEUCWgtValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(BEUCWgtValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public BEUCWgtRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(BEUCWgtRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public BEUCWgtRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(BEUCWgtRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
