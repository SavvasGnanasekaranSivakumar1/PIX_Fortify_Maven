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
 * Title		: 	PCPMCPPI.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCPMCPPI is a data transfer object to store the Specification Press Component
 * Manufacturing Specifications Printing Material Characteristics PPI 
 * details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCPMCPPI implements java.io.Serializable {
	private static final long serialVersionUID = -3041781489792806611L;
	
	private PCPPIValue pcPPIValue		= null;
	private PCPPIRangeMin pcPPIRangeMin	= null;
	private PCPPIRangeMax pcPPIRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public PCPMCPPI() {
		super();
	}
	/**
	 * @return the pcPPIValue
	 */
	public PCPPIValue getPcPPIValue() {
		return pcPPIValue;
	}
	/**
	 * @param pcPPIValue the pcPPIValue to set
	 */
	public void setPcPPIValue(PCPPIValue pcPPIValue) {
		this.pcPPIValue = pcPPIValue;
	}
	/**
	 * @return the pcPPIRangeMin
	 */
	public PCPPIRangeMin getPcPPIRangeMin() {
		return pcPPIRangeMin;
	}
	/**
	 * @param pcPPIRangeMin the pcPPIRangeMin to set
	 */
	public void setPcPPIRangeMin(PCPPIRangeMin pcPPIRangeMin) {
		this.pcPPIRangeMin = pcPPIRangeMin;
	}
	/**
	 * @return the pcPPIRangeMax
	 */
	public PCPPIRangeMax getPcPPIRangeMax() {
		return pcPPIRangeMax;
	}
	/**
	 * @param pcPPIRangeMax the pcPPIRangeMax to set
	 */
	public void setPcPPIRangeMax(PCPPIRangeMax pcPPIRangeMax) {
		this.pcPPIRangeMax = pcPPIRangeMax;
	}
}
