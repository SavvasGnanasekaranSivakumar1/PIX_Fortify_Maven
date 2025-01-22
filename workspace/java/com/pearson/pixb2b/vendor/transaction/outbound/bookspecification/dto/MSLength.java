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
 * Title		: 	MSLength.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MSLength is a data transfer object to store the Specification NonPress Component 
 * Media Slide Length details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MSLength implements java.io.Serializable {
	private static final long serialVersionUID = 3679508534013369076L;
	
	private MSLValue value	 		= null;
	private MSLRangeMin rangeMin  	= null;
	private MSLRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public MSLength() {
		super();
	}
	/**
	 * @return the value
	 */
	public MSLValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(MSLValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public MSLRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(MSLRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public MSLRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(MSLRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
