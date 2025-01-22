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
 * Title		: 	PCPMCInkCov.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	1 Dec, 2009		Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCPMCInkCov is a data transfer object to store the Specification Press Component
 * Manufacturing Specifications Printing Material Characteristics ColourSpecs
 * InkCoverage details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCPMCInkCov implements java.io.Serializable {
	private static final long serialVersionUID = 2618796871437047806L;
	
	private String inkCovgeType			= null;
	private PCPMCValue pcPMCValue		= null;
	private PCPMCRangeMin pcPMCRangeMin	= null;
	private PCPMCRangeMax pcPMCRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public PCPMCInkCov() {
		super();
	}
	/**
	 * @return the inkCovgeType
	 */
	public String getInkCovgeType() {
		return inkCovgeType;
	}
	/**
	 * @param inkCovgeType the inkCovgeType to set
	 */
	public void setInkCovgeType(String inkCovgeType) {
		this.inkCovgeType = inkCovgeType;
	}
	/**
	 * @return the pcPMCValue
	 */
	public PCPMCValue getPcPMCValue() {
		return pcPMCValue;
	}
	/**
	 * @param pcPMCValue the pcPMCValue to set
	 */
	public void setPcPMCValue(PCPMCValue pcPMCValue) {
		this.pcPMCValue = pcPMCValue;
	}
	/**
	 * @return the pcPMCRangeMin
	 */
	public PCPMCRangeMin getPcPMCRangeMin() {
		return pcPMCRangeMin;
	}
	/**
	 * @param pcPMCRangeMin the pcPMCRangeMin to set
	 */
	public void setPcPMCRangeMin(PCPMCRangeMin pcPMCRangeMin) {
		this.pcPMCRangeMin = pcPMCRangeMin;
	}
	/**
	 * @return the pcPMCRangeMax
	 */
	public PCPMCRangeMax getPcPMCRangeMax() {
		return pcPMCRangeMax;
	}
	/**
	 * @param pcPMCRangeMax the pcPMCRangeMax to set
	 */
	public void setPcPMCRangeMax(PCPMCRangeMax pcPMCRangeMax) {
		this.pcPMCRangeMax = pcPMCRangeMax;
	}
}