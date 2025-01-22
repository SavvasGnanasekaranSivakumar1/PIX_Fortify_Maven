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
 * Title		: 	SPPCLCLength.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCLCLength is a data transfer object to store the Spec Packing Pallet Packaging
 * Label Characteristics Length details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCLCLength implements java.io.Serializable {
	private static final long serialVersionUID = 5664370450959942664L;
	
	private SPPCLCLValue value	 		= null;
	private SPPCLCLRangeMin rangeMin  	= null;
	private SPPCLCLRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public SPPCLCLength() {
		super();
	}
	/**
	 * @return the value
	 */
	public SPPCLCLValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(SPPCLCLValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public SPPCLCLRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(SPPCLCLRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public SPPCLCLRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(SPPCLCLRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
