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
 * Title		: 	WidSpecAssembly.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   23 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * WidSpecAssembly is a data transfer object to store the 
 * Specification Assembly Width details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class WidSpecAssembly implements java.io.Serializable {
	private static final long serialVersionUID = -2027392670110981801L;
	
	private WidValue value		  = null;
	private WidRangeMin rangeMin  = null;
	private WidRangeMax rangeMax  = null;
	/**
	 * Default constructor.
	 */
	public WidSpecAssembly() {
		super();
	}
	public WidValue getValue() {
		return value;
	}
	public void setValue(WidValue value) {
		this.value = value;
	}
	public WidRangeMin getRangeMin() {
		return rangeMin;
	}
	public void setRangeMin(WidRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	public WidRangeMax getRangeMax() {
		return rangeMax;
	}
	public void setRangeMax(WidRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
