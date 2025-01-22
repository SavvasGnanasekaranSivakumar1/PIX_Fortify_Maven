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
 * Title		: 	COSLength.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * COSLength is a data transfer object to store the specification Binding   
 * CaseOversize Length details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class COSLength implements java.io.Serializable {
	private static final long serialVersionUID = -773519276771467705L;
	
	private COSLValue value	 		= null;
	private COSLRangeMin rangeMin  	= null;
	private COSLRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public COSLength() {
		super();
	}
	/**
	 * @return the value
	 */
	public COSLValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(COSLValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public COSLRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(COSLRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public COSLRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(COSLRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
