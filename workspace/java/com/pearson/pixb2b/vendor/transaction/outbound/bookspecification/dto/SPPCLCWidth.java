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
 * Title		: 	SPPCLCWidth.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCLCWidth is a data transfer object to store the Spec Packing Pallet Packaging
 * Label Characteristics Width details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCLCWidth implements java.io.Serializable {
	private static final long serialVersionUID = 1061772497383793874L;
	
	private SPPCLCWidValue value		= null;
	private SPPCLCWidRangeMin rangeMin	= null;
	private SPPCLCWidRangeMax rangeMax  = null;
	/**
	 * Default constructor.
	 */
	public SPPCLCWidth() {
		super();
	}
	/**
	 * @return the value
	 */
	public SPPCLCWidValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(SPPCLCWidValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public SPPCLCWidRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(SPPCLCWidRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public SPPCLCWidRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(SPPCLCWidRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
