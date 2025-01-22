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
 * Title		: 	PCFSDecCov.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCFSDecCov is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications FinishSpecs Decoration Coverage details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCFSDecCov implements java.io.Serializable {
	private static final long serialVersionUID = 609085124541769511L;
	
	private PCFSDCValue pcFSDCValue			= null;
	private PCFSDCRangeMin pcFSDCRangeMin	= null;
	private PCFSDCRangeMax pcFSDCRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public PCFSDecCov() {
		super();
	}
	/**
	 * @return the pcFSDCValue
	 */
	public PCFSDCValue getPcFSDCValue() {
		return pcFSDCValue;
	}
	/**
	 * @param pcFSDCValue the pcFSDCValue to set
	 */
	public void setPcFSDCValue(PCFSDCValue pcFSDCValue) {
		this.pcFSDCValue = pcFSDCValue;
	}
	/**
	 * @return the pcFSDCRangeMin
	 */
	public PCFSDCRangeMin getPcFSDCRangeMin() {
		return pcFSDCRangeMin;
	}
	/**
	 * @param pcFSDCRangeMin the pcFSDCRangeMin to set
	 */
	public void setPcFSDCRangeMin(PCFSDCRangeMin pcFSDCRangeMin) {
		this.pcFSDCRangeMin = pcFSDCRangeMin;
	}
	/**
	 * @return the pcFSDCRangeMax
	 */
	public PCFSDCRangeMax getPcFSDCRangeMax() {
		return pcFSDCRangeMax;
	}
	/**
	 * @param pcFSDCRangeMax the pcFSDCRangeMax to set
	 */
	public void setPcFSDCRangeMax(PCFSDCRangeMax pcFSDCRangeMax) {
		this.pcFSDCRangeMax = pcFSDCRangeMax;
	}
}
