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
 * Title		: 	MedTotRunLngth.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MedTotRunLngth is a data transfer object to store the Specification NonPress Component 
 * Media Total Running Length details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MedTotRunLngth implements java.io.Serializable {
	private static final long serialVersionUID = 5751431478450295470L;
	
	private String quanType				= null;
	private String quanTypeContext		= null;
	private String adjustType			= null;
	private MTRLValue mTRLValue			= null;
	private MTRLRangeMin mTRLRangeMin	= null;
	private MTRLRangeMax mTRLRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public MedTotRunLngth() {
		super();
	}
	/**
	 * @return the quanType
	 */
	public String getQuanType() {
		return quanType;
	}
	/**
	 * @param quanType the quanType to set
	 */
	public void setQuanType(String quanType) {
		this.quanType = quanType;
	}
	/**
	 * @return the quanTypeContext
	 */
	public String getQuanTypeContext() {
		return quanTypeContext;
	}
	/**
	 * @param quanTypeContext the quanTypeContext to set
	 */
	public void setQuanTypeContext(String quanTypeContext) {
		this.quanTypeContext = quanTypeContext;
	}
	/**
	 * @return the adjustType
	 */
	public String getAdjustType() {
		return adjustType;
	}
	/**
	 * @param adjustType the adjustType to set
	 */
	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}
	/**
	 * @return the mTRLValue
	 */
	public MTRLValue getMTRLValue() {
		return mTRLValue;
	}
	/**
	 * @param value the mTRLValue to set
	 */
	public void setMTRLValue(MTRLValue value) {
		mTRLValue = value;
	}
	/**
	 * @return the mTRLRangeMin
	 */
	public MTRLRangeMin getMTRLRangeMin() {
		return mTRLRangeMin;
	}
	/**
	 * @param rangeMin the mTRLRangeMin to set
	 */
	public void setMTRLRangeMin(MTRLRangeMin rangeMin) {
		mTRLRangeMin = rangeMin;
	}
	/**
	 * @return the mTRLRangeMax
	 */
	public MTRLRangeMax getMTRLRangeMax() {
		return mTRLRangeMax;
	}
	/**
	 * @param rangeMax the mTRLRangeMax to set
	 */
	public void setMTRLRangeMax(MTRLRangeMax rangeMax) {
		mTRLRangeMax = rangeMax;
	}
}
