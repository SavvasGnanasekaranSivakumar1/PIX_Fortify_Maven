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
 * Title		: 	SPPCPalWid.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCPalWid is a data transfer object to store the Spec Packing Pallet
 * Characteristics Width details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCPalWid implements java.io.Serializable {
	private static final long serialVersionUID = 5846164720889226393L;
	
	private SPPCPWValue value	 	 = null;
	private SPPCPWRangeMin rangeMin  = null;
	private SPPCPWRangeMax rangeMax  = null;
	/**
	 * Default constructor.
	 */
	public SPPCPalWid() {
		super();
	}
	/**
	 * @return the value
	 */
	public SPPCPWValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(SPPCPWValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public SPPCPWRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(SPPCPWRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public SPPCPWRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(SPPCPWRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
