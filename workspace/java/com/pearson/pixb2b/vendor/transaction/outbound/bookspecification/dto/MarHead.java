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
 * Title		: 	MarHead.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	30 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MarHead is a data transfer object to store the Specification Press Component 
 * Manufacturing Specifications Margin Head details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MarHead implements java.io.Serializable {
	private static final long serialVersionUID = -3536165138348942585L;
	
	private String bleed		= null;
	private HValue hValue		= null;
	private HRangeMin hRangeMin	= null;
	private HRangeMax hRangeMax	= null;
	private String addText		= null;
	/**
	 * Default constructor.
	 */
	public MarHead() {
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
	 * @return the hValue
	 */
	public HValue getHValue() {
		return hValue;
	}
	/**
	 * @param value the hValue to set
	 */
	public void setHValue(HValue value) {
		hValue = value;
	}
	/**
	 * @return the hRangeMin
	 */
	public HRangeMin getHRangeMin() {
		return hRangeMin;
	}
	/**
	 * @param rangeMin the hRangeMin to set
	 */
	public void setHRangeMin(HRangeMin rangeMin) {
		hRangeMin = rangeMin;
	}
	/**
	 * @return the hRangeMax
	 */
	public HRangeMax getHRangeMax() {
		return hRangeMax;
	}
	/**
	 * @param rangeMax the hRangeMax to set
	 */
	public void setHRangeMax(HRangeMax rangeMax) {
		hRangeMax = rangeMax;
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
