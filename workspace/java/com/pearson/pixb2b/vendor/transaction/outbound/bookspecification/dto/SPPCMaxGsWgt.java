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
 * Title		: 	SPPCMaxGsWgt.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	27 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCMaxGsWgt is a data transfer object to store the Spec Packing Pallet Packaging
 * Characteristics Maximum GrossWeight details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCMaxGsWgt implements java.io.Serializable {
	private static final long serialVersionUID = 8900883332822104423L;
	
	private SPPCMaxGWValue value		= null;
	private SPPCMaxGWRangeMin rangeMin	= null;
	private SPPCMaxGWRangeMax rangeMax  = null;
	/**
	 * Default constructor.
	 */
	public SPPCMaxGsWgt() {
		super();
	}
	/**
	 * @return the value
	 */
	public SPPCMaxGWValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(SPPCMaxGWValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public SPPCMaxGWRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(SPPCMaxGWRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public SPPCMaxGWRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(SPPCMaxGWRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
