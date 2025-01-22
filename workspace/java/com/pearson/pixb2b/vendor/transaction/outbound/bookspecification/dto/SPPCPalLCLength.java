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
 * Title		: 	SPPCPalLCLength.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCPalLCLength is a data transfer object to store the Spec Packing Pallet
 * Label Characteristics Length details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCPalLCLength implements java.io.Serializable {
	private static final long serialVersionUID = -8018030101686078143L;
	
	private SPPCPalLCLValue value	 	= null;
	private SPPCPalLCLRangeMin rangeMin = null;
	private SPPCPalLCLRangeMax rangeMax = null;
	/**
	 * Default constructor.
	 */
	public SPPCPalLCLength() {
		super();
	}
	/**
	 * @return the value
	 */
	public SPPCPalLCLValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(SPPCPalLCLValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public SPPCPalLCLRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(SPPCPalLCLRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public SPPCPalLCLRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(SPPCPalLCLRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
