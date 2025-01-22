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
 * Title		: 	PCInkCov.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	1 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCInkCov is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications Ink Characteristics Coverage details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCInkCov implements java.io.Serializable {
	private static final long serialVersionUID = -5876201732886687429L;
	
	private String inkCovgeType			= null;
	private PCICValue pcICValue			= null;
	private PCICRangeMin pcICRangeMin	= null;
	private PCICRangeMax pcICRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public PCInkCov() {
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
	 * @return the pcICValue
	 */
	public PCICValue getPcICValue() {
		return pcICValue;
	}
	/**
	 * @param pcICValue the pcICValue to set
	 */
	public void setPcICValue(PCICValue pcICValue) {
		this.pcICValue = pcICValue;
	}
	/**
	 * @return the pcICRangeMin
	 */
	public PCICRangeMin getPcICRangeMin() {
		return pcICRangeMin;
	}
	/**
	 * @param pcICRangeMin the pcICRangeMin to set
	 */
	public void setPcICRangeMin(PCICRangeMin pcICRangeMin) {
		this.pcICRangeMin = pcICRangeMin;
	}
	/**
	 * @return the pcICRangeMax
	 */
	public PCICRangeMax getPcICRangeMax() {
		return pcICRangeMax;
	}
	/**
	 * @param pcICRangeMax the pcICRangeMax to set
	 */
	public void setPcICRangeMax(PCICRangeMax pcICRangeMax) {
		this.pcICRangeMax = pcICRangeMax;
	}
}
