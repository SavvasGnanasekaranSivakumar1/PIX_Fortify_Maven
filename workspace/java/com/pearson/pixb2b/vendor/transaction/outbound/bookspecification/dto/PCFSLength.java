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
 * Title		: 	PCFSLength.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	1 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCFSLength is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications Printed Media Specs FlatSize Length details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCFSLength implements java.io.Serializable {
	private static final long serialVersionUID = 6456745705834973381L;
	
	private PCFSLValue pcFSLValue		= null;
	private PCFSLRangeMin pcFSLRangeMin	= null;
	private PCFSLRangeMax pcFSLRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public PCFSLength() {
		super();
	}
	/**
	 * @return the pcFSLValue
	 */
	public PCFSLValue getPcFSLValue() {
		return pcFSLValue;
	}
	/**
	 * @param pcFSLValue the pcFSLValue to set
	 */
	public void setPcFSLValue(PCFSLValue pcFSLValue) {
		this.pcFSLValue = pcFSLValue;
	}
	/**
	 * @return the pcFSLRangeMin
	 */
	public PCFSLRangeMin getPcFSLRangeMin() {
		return pcFSLRangeMin;
	}
	/**
	 * @param pcFSLRangeMin the pcFSLRangeMin to set
	 */
	public void setPcFSLRangeMin(PCFSLRangeMin pcFSLRangeMin) {
		this.pcFSLRangeMin = pcFSLRangeMin;
	}
	/**
	 * @return the pcFSLRangeMax
	 */
	public PCFSLRangeMax getPcFSLRangeMax() {
		return pcFSLRangeMax;
	}
	/**
	 * @param pcFSLRangeMax the pcFSLRangeMax to set
	 */
	public void setPcFSLRangeMax(PCFSLRangeMax pcFSLRangeMax) {
		this.pcFSLRangeMax = pcFSLRangeMax;
	}
}
