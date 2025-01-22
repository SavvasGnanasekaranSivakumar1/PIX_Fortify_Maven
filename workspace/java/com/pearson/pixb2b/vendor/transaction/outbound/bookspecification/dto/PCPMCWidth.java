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
 * Title		: 	PCPMCWidth.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCPMCWidth is a data transfer object to store the Specification Press Component
 * Manufacturing Specifications Printing Material Characteristics Width 
 * details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCPMCWidth implements java.io.Serializable {
	private static final long serialVersionUID = 4810999121470091610L;
	
	private PCPMCWValue pcPMCWValue			= null;
	private PCPMCWRangeMin pcPMCWRangeMin	= null;
	private PCPMCWRangeMax pcPMCWRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public PCPMCWidth() {
		super();
	}
	/**
	 * @return the pcPMCWValue
	 */
	public PCPMCWValue getPcPMCWValue() {
		return pcPMCWValue;
	}
	/**
	 * @param pcPMCWValue the pcPMCWValue to set
	 */
	public void setPcPMCWValue(PCPMCWValue pcPMCWValue) {
		this.pcPMCWValue = pcPMCWValue;
	}
	/**
	 * @return the pcPMCWRangeMin
	 */
	public PCPMCWRangeMin getPcPMCWRangeMin() {
		return pcPMCWRangeMin;
	}
	/**
	 * @param pcPMCWRangeMin the pcPMCWRangeMin to set
	 */
	public void setPcPMCWRangeMin(PCPMCWRangeMin pcPMCWRangeMin) {
		this.pcPMCWRangeMin = pcPMCWRangeMin;
	}
	/**
	 * @return the pcPMCWRangeMax
	 */
	public PCPMCWRangeMax getPcPMCWRangeMax() {
		return pcPMCWRangeMax;
	}
	/**
	 * @param pcPMCWRangeMax the pcPMCWRangeMax to set
	 */
	public void setPcPMCWRangeMax(PCPMCWRangeMax pcPMCWRangeMax) {
		this.pcPMCWRangeMax = pcPMCWRangeMax;
	}
}
