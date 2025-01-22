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
 * Title		: 	HtSpecAssembly.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   23 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * HtSpecAssembly is a data transfer object to store the 
 * Specification Assembly Height details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class HtSpecAssembly implements java.io.Serializable {
	private static final long serialVersionUID = -6413441123312693639L;
	
	private HtValue value		 = null;
	private HtRangeMin rangeMin  = null;
	private HtRangeMax rangeMax  = null;
	/**
	 * Default constructor.
	 */
	public HtSpecAssembly() {
		super();
	}
	public HtValue getValue() {
		return value;
	}
	public void setValue(HtValue value) {
		this.value = value;
	}
	public HtRangeMin getRangeMin() {
		return rangeMin;
	}
	public void setRangeMin(HtRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	public HtRangeMax getRangeMax() {
		return rangeMax;
	}
	public void setRangeMax(HtRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
