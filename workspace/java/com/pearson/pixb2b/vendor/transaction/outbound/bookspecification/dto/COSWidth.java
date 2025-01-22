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
 * Title		: 	COSWidth.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	24 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * COSWidth is a data transfer object to store the specification Binding 
 * CaseOversize Width details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class COSWidth implements java.io.Serializable {
	private static final long serialVersionUID = -5478838678523637156L;
	
	private COSWValue value			= null;
	private COSWRangeMin rangeMin	= null;
	private COSWRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public COSWidth() {
		super();
	}
	/**
	 * @return the value
	 */
	public COSWValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(COSWValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public COSWRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(COSWRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public COSWRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(COSWRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
