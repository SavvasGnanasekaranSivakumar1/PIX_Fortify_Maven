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
 * Title		: 	SPPCMxHgt.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	27 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPPCMxHgt is a data transfer object to store the Spec Packing Pallet
 * Characteristics Maximum Height details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPPCMxHgt implements java.io.Serializable {
	private static final long serialVersionUID = -4859106641975283854L;
	
	private SPPCPMHValue value	 	  = null;
	private SPPCPMHRangeMin rangeMin  = null;
	private SPPCPMHRangeMax rangeMax  = null;
	/**
	 * Default constructor.
	 */
	public SPPCMxHgt() {
		super();
	}
	/**
	 * @return the value
	 */
	public SPPCPMHValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(SPPCPMHValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public SPPCPMHRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(SPPCPMHRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public SPPCPMHRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(SPPCPMHRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
