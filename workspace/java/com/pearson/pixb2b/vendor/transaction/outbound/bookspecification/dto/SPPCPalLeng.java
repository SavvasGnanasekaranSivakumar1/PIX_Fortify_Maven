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
 * Title		: 	SPPCPalLeng.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCPalLeng is a data transfer object to store the Spec Packing Pallet
 * Characteristics Length details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCPalLeng implements java.io.Serializable {
	private static final long serialVersionUID = 4121967363240011837L;
	
	private SPPCPLValue value	 	 = null;
	private SPPCPLRangeMin rangeMin  = null;
	private SPPCPLRangeMax rangeMax  = null;
	/**
	 * Default constructor.
	 */
	public SPPCPalLeng() {
		super();
	}
	/**
	 * @return the value
	 */
	public SPPCPLValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(SPPCPLValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public SPPCPLRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(SPPCPLRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public SPPCPLRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(SPPCPLRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
