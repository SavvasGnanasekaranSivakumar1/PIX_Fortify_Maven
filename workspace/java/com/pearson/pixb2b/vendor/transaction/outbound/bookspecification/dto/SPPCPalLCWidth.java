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
 * Title		: 	SPPCPalLCWidth.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCPalLCWidth is a data transfer object to store the Spec Packing Pallet
 * Label Characteristics Width details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCPalLCWidth implements java.io.Serializable {
	private static final long serialVersionUID = -6319923048208325850L;
	
	private SPPCPalLCWidValue value			= null;
	private SPPCPalLCWidRangeMin rangeMin	= null;
	private SPPCPalLCWidRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public SPPCPalLCWidth() {
		super();
	}
	/**
	 * @return the value
	 */
	public SPPCPalLCWidValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(SPPCPalLCWidValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public SPPCPalLCWidRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(SPPCPalLCWidRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public SPPCPalLCWidRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(SPPCPalLCWidRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
