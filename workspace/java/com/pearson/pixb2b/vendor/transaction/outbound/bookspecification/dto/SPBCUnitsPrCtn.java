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
 * Title		: 	SPBCUnitsPrCtn.java
 * Company 		: 	HCL Technologies
  *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	26 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPBCUnitsPrCtn is a data transfer object to store the Spec Packing Box
 * Characteristics Units Per Carton details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPBCUnitsPrCtn implements java.io.Serializable {
	private static final long serialVersionUID = -8263935654485304828L;
	
	private SPBCUPCValue value			= null;
	private SPBCUPCRangeMin rangeMin	= null;
	private SPBCUPCRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public SPBCUnitsPrCtn() {
		super();
	}
	/**
	 * @return the value
	 */
	public SPBCUPCValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(SPBCUPCValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public SPBCUPCRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(SPBCUPCRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public SPBCUPCRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(SPBCUPCRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
