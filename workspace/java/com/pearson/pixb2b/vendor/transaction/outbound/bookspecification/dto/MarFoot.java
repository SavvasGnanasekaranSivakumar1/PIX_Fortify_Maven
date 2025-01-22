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
 * Title		: 	MarFoot.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	30 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MarFoot is a data transfer object to store the Specification Press Component 
 * Manufacturing Specifications Margin Foot details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MarFoot implements java.io.Serializable {
	private static final long serialVersionUID = -1234672261274023209L;
	
	private String bleed		= null;
	private FValue fValue		= null;
	private FRangeMin fRangeMin	= null;
	private FRangeMax fRangeMax	= null;
	private String addText		= null;
	/**
	 * Default constructor.
	 */
	public MarFoot() {
		super();
	}
	/**
	 * @return the bleed
	 */
	public String getBleed() {
		return bleed;
	}
	/**
	 * @param bleed the bleed to set
	 */
	public void setBleed(String bleed) {
		this.bleed = bleed;
	}
	/**
	 * @return the fValue
	 */
	public FValue getFValue() {
		return fValue;
	}
	/**
	 * @param value the fValue to set
	 */
	public void setFValue(FValue value) {
		fValue = value;
	}
	/**
	 * @return the fRangeMin
	 */
	public FRangeMin getFRangeMin() {
		return fRangeMin;
	}
	/**
	 * @param rangeMin the fRangeMin to set
	 */
	public void setFRangeMin(FRangeMin rangeMin) {
		fRangeMin = rangeMin;
	}
	/**
	 * @return the fRangeMax
	 */
	public FRangeMax getFRangeMax() {
		return fRangeMax;
	}
	/**
	 * @param rangeMax the fRangeMax to set
	 */
	public void setFRangeMax(FRangeMax rangeMax) {
		fRangeMax = rangeMax;
	}
	/**
	 * @return the addText
	 */
	public String getAddText() {
		return addText;
	}
	/**
	 * @param addText the addText to set
	 */
	public void setAddText(String addText) {
		this.addText = addText;
	}
}
