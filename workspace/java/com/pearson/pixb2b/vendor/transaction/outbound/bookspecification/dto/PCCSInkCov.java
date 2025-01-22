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
 * Title		: 	PCCSInkCov.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	1 Dec, 2009		Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCCSInkCov is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications Ink Characteristics ColourSpecs InkCoverage details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCCSInkCov implements java.io.Serializable {
	private static final long serialVersionUID = 1744058058496318883L;
	
	private String inkCovgeType			= null;
	private PCCSValue pcCSValue			= null;
	private PCCSRangeMin pcCSRangeMin	= null;
	private PCCSRangeMax pcCSRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public PCCSInkCov() {
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
	 * @return the pcCSValue
	 */
	public PCCSValue getPcCSValue() {
		return pcCSValue;
	}
	/**
	 * @param pcCSValue the pcCSValue to set
	 */
	public void setPcCSValue(PCCSValue pcCSValue) {
		this.pcCSValue = pcCSValue;
	}
	/**
	 * @return the pcCSRangeMin
	 */
	public PCCSRangeMin getPcCSRangeMin() {
		return pcCSRangeMin;
	}
	/**
	 * @param pcCSRangeMin the pcCSRangeMin to set
	 */
	public void setPcCSRangeMin(PCCSRangeMin pcCSRangeMin) {
		this.pcCSRangeMin = pcCSRangeMin;
	}
	/**
	 * @return the pcCSRangeMax
	 */
	public PCCSRangeMax getPcCSRangeMax() {
		return pcCSRangeMax;
	}
	/**
	 * @param pcCSRangeMax the pcCSRangeMax to set
	 */
	public void setPcCSRangeMax(PCCSRangeMax pcCSRangeMax) {
		this.pcCSRangeMax = pcCSRangeMax;
	}
}