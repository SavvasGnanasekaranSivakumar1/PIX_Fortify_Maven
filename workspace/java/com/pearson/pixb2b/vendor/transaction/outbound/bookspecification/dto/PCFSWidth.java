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
 * Title		: 	PCFSWidth.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	1 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCFSWidth is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications Printed Media Specs FlatSize Width details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCFSWidth implements java.io.Serializable {
	private static final long serialVersionUID = -5005640365514231963L;
	
	private PCFSWValue pcFSWValue		= null;
	private PCFSWRangeMin pcFSWRangeMin	= null;
	private PCFSWRangeMax pcFSWRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public PCFSWidth() {
		super();
	}
	/**
	 * @return the pcFSWValue
	 */
	public PCFSWValue getPcFSWValue() {
		return pcFSWValue;
	}
	/**
	 * @param pcFSWValue the pcFSWValue to set
	 */
	public void setPcFSWValue(PCFSWValue pcFSWValue) {
		this.pcFSWValue = pcFSWValue;
	}
	/**
	 * @return the pcFSWRangeMin
	 */
	public PCFSWRangeMin getPcFSWRangeMin() {
		return pcFSWRangeMin;
	}
	/**
	 * @param pcFSWRangeMin the pcFSWRangeMin to set
	 */
	public void setPcFSWRangeMin(PCFSWRangeMin pcFSWRangeMin) {
		this.pcFSWRangeMin = pcFSWRangeMin;
	}
	/**
	 * @return the pcFSWRangeMax
	 */
	public PCFSWRangeMax getPcFSWRangeMax() {
		return pcFSWRangeMax;
	}
	/**
	 * @param pcFSWRangeMax the pcFSWRangeMax to set
	 */
	public void setPcFSWRangeMax(PCFSWRangeMax pcFSWRangeMax) {
		this.pcFSWRangeMax = pcFSWRangeMax;
	}
}
