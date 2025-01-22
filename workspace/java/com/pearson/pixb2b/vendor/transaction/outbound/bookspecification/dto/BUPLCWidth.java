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
 * Title		: 	BUPLCWidth.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	26 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BUPLCWidth is a data transfer object to store the Book Spec Unit Packing
 * Label Characteristics Width details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BUPLCWidth implements java.io.Serializable {
	private static final long serialVersionUID = 4656816524060303708L;
	
	private BUPLCWidValue value			= null;
	private BUPLCWidRangeMin rangeMin	= null;
	private BUPLCWidRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public BUPLCWidth() {
		super();
	}
	/**
	 * @return the value
	 */
	public BUPLCWidValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(BUPLCWidValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public BUPLCWidRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(BUPLCWidRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public BUPLCWidRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(BUPLCWidRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
