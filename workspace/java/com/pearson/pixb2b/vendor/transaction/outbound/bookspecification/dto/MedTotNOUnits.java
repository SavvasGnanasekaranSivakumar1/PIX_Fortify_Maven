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
 * Title		: 	MedTotNOUnits.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MedTotNOUnits is a data transfer object to store the Specification NonPress Component 
 * Media Total NumberOfUnits details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MedTotNOUnits implements java.io.Serializable {
	private static final long serialVersionUID = -414083314133811057L;
	
	private MTNUValue mTNUValue			= null;
	private MTNURangeMin mTNURangeMin	= null;
	private MTNURangeMax mTNURangeMax	= null;
	/**
	 * Default constructor.
	 */
	public MedTotNOUnits() {
		super();
	}
	/**
	 * @return the mTNUValue
	 */
	public MTNUValue getMTNUValue() {
		return mTNUValue;
	}
	/**
	 * @param value the mTNUValue to set
	 */
	public void setMTNUValue(MTNUValue value) {
		mTNUValue = value;
	}
	/**
	 * @return the mTNURangeMin
	 */
	public MTNURangeMin getMTNURangeMin() {
		return mTNURangeMin;
	}
	/**
	 * @param rangeMin the mTNURangeMin to set
	 */
	public void setMTNURangeMin(MTNURangeMin rangeMin) {
		mTNURangeMin = rangeMin;
	}
	/**
	 * @return the mTNURangeMax
	 */
	public MTNURangeMax getMTNURangeMax() {
		return mTNURangeMax;
	}
	/**
	 * @param rangeMax the mTNURangeMax to set
	 */
	public void setMTNURangeMax(MTNURangeMax rangeMax) {
		mTNURangeMax = rangeMax;
	}
}
