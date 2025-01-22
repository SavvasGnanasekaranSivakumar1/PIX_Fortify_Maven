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
 * Title		: 	MarGutter.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	30 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MarGutter is a data transfer object to store the Specification Press Component 
 * Manufacturing Specifications Margin Gutter details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MarGutter implements java.io.Serializable {
	private static final long serialVersionUID = -249562843210276735L;
	
	private String bleed		= null;
	private GValue gValue		= null;
	private GRangeMin gRangeMin	= null;
	private GRangeMax gRangeMax	= null;
	private String addText		= null;
	/**
	 * Default constructor.
	 */
	public MarGutter() {
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
	 * @return the gValue
	 */
	public GValue getGValue() {
		return gValue;
	}
	/**
	 * @param value the gValue to set
	 */
	public void setGValue(GValue value) {
		gValue = value;
	}
	/**
	 * @return the gRangeMin
	 */
	public GRangeMin getGRangeMin() {
		return gRangeMin;
	}
	/**
	 * @param rangeMin the gRangeMin to set
	 */
	public void setGRangeMin(GRangeMin rangeMin) {
		gRangeMin = rangeMin;
	}
	/**
	 * @return the gRangeMax
	 */
	public GRangeMax getGRangeMax() {
		return gRangeMax;
	}
	/**
	 * @param rangeMax the gRangeMax to set
	 */
	public void setGRangeMax(GRangeMax rangeMax) {
		gRangeMax = rangeMax;
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
