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
 * Title		: 	SPPCMaxHgt.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCMaxHgt is a data transfer object to store the Spec Packing Pallet
 * Packaging Characteristics MaximumHeight details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCMaxHgt implements java.io.Serializable {
	private static final long serialVersionUID = -1740535341235140307L;
	
	private SPPCMaxHValue value	 	  = null;
	private SPPCMaxHRangeMin rangeMin = null;
	private SPPCMaxHRangeMax rangeMax = null;
	/**
	 * Default constructor.
	 */
	public SPPCMaxHgt() {
		super();
	}
	/**
	 * @return the value
	 */
	public SPPCMaxHValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(SPPCMaxHValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public SPPCMaxHRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(SPPCMaxHRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public SPPCMaxHRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(SPPCMaxHRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
