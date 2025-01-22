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
 * Title		: 	UCWidth.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   23 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * UCWidth is a data transfer object to store the Binding Extras Unit 
 * Characteristics Width details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class UCWidth implements java.io.Serializable {
	private static final long serialVersionUID = -7077986558747181588L;
	
	private BEUCWidthValue value		= null;
	private BEUCWidthRangeMin rangeMin	= null;
	private BEUCWidthRangeMax rangeMax  = null;
	/**
	 * Default constructor.
	 */
	public UCWidth() {
		super();
	}
	/**
	 * @return the value
	 */
	public BEUCWidthValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(BEUCWidthValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public BEUCWidthRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(BEUCWidthRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public BEUCWidthRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(BEUCWidthRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
