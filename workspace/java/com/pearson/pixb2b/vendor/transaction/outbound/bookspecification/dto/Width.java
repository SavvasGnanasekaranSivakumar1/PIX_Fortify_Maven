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
 * Title		: 	Width.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	20 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * Width is a data transfer object to store the 
 * Width details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class Width implements java.io.Serializable {
	private static final long serialVersionUID = 3438765546061159866L;
	
	private WidthVal value		= null;
	private WidRanMin widRanMin = null;
	private WidRanMax widRanMax = null;
	/**
	 * Default constructor.
	 */
	public Width() {
		super();
	}
	/**
	 * @return the value
	 */
	public WidthVal getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(WidthVal value) {
		this.value = value;
	}
	/**
	 * @return the widRanMin
	 */
	public WidRanMin getWidRanMin() {
		return widRanMin;
	}
	/**
	 * @param widRanMin the widRanMin to set
	 */
	public void setWidRanMin(WidRanMin widRanMin) {
		this.widRanMin = widRanMin;
	}
	/**
	 * @return the widRanMax
	 */
	public WidRanMax getWidRanMax() {
		return widRanMax;
	}
	/**
	 * @param widRanMax the widRanMax to set
	 */
	public void setWidRanMax(WidRanMax widRanMax) {
		this.widRanMax = widRanMax;
	}
}