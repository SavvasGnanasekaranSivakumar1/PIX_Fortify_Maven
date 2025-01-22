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
 * Title		: 	PCPMCLength.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCPMCLength is a data transfer object to store the Specification Press Component
 * Manufacturing Specifications Printing Material Characteristics Length 
 * details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCPMCLength implements java.io.Serializable {
	private static final long serialVersionUID = 8096946535733587476L;
	
	private PCPMCLValue pcPMCLValue			= null;
	private PCPMCLRangeMin pcPMCLRangeMin	= null;
	private PCPMCLRangeMax pcPMCLRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public PCPMCLength() {
		super();
	}
	/**
	 * @return the pcPMCLValue
	 */
	public PCPMCLValue getPcPMCLValue() {
		return pcPMCLValue;
	}
	/**
	 * @param pcPMCLValue the pcPMCLValue to set
	 */
	public void setPcPMCLValue(PCPMCLValue pcPMCLValue) {
		this.pcPMCLValue = pcPMCLValue;
	}
	/**
	 * @return the pcPMCLRangeMin
	 */
	public PCPMCLRangeMin getPcPMCLRangeMin() {
		return pcPMCLRangeMin;
	}
	/**
	 * @param pcPMCLRangeMin the pcPMCLRangeMin to set
	 */
	public void setPcPMCLRangeMin(PCPMCLRangeMin pcPMCLRangeMin) {
		this.pcPMCLRangeMin = pcPMCLRangeMin;
	}
	/**
	 * @return the pcPMCLRangeMax
	 */
	public PCPMCLRangeMax getPcPMCLRangeMax() {
		return pcPMCLRangeMax;
	}
	/**
	 * @param pcPMCLRangeMax the pcPMCLRangeMax to set
	 */
	public void setPcPMCLRangeMax(PCPMCLRangeMax pcPMCLRangeMax) {
		this.pcPMCLRangeMax = pcPMCLRangeMax;
	}
}
