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
 * Title		: 	MarThumb.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	30 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MarThumb is a data transfer object to store the Specification Press Component 
 * Manufacturing Specifications Margin Thumb details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MarThumb implements java.io.Serializable {
	private static final long serialVersionUID = -1358446660006844972L;
	
	private String bleed		= null;
	private TValue tValue		= null;
	private TRangeMin tRangeMin	= null;
	private TRangeMax tRangeMax	= null;
	private String addText		= null;
	/**
	 * Default constructor.
	 */
	public MarThumb() {
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
	 * @return the tValue
	 */
	public TValue getTValue() {
		return tValue;
	}
	/**
	 * @param value the tValue to set
	 */
	public void setTValue(TValue value) {
		tValue = value;
	}
	/**
	 * @return the tRangeMin
	 */
	public TRangeMin getTRangeMin() {
		return tRangeMin;
	}
	/**
	 * @param rangeMin the tRangeMin to set
	 */
	public void setTRangeMin(TRangeMin rangeMin) {
		tRangeMin = rangeMin;
	}
	/**
	 * @return the tRangeMax
	 */
	public TRangeMax getTRangeMax() {
		return tRangeMax;
	}
	/**
	 * @param rangeMax the tRangeMax to set
	 */
	public void setTRangeMax(TRangeMax rangeMax) {
		tRangeMax = rangeMax;
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
