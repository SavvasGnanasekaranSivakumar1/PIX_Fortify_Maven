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
 * Title		: 	VCCSInkCov.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	2 Dec, 2009		Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * VCCSInkCov is a data transfer object to store the Specification NonPress Component Video Cassette 
 * Label Characteristics ColourSpecs InkCoverage details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class VCCSInkCov implements java.io.Serializable {
	private static final long serialVersionUID = 7186144319629178410L;
	
	private String inkCovgeType			= null;
	private VCCSValue vcCSValue			= null;
	private VCCSRangeMin vcCSRangeMin	= null;
	private VCCSRangeMax vcCSRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public VCCSInkCov() {
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
	 * @return the vcCSValue
	 */
	public VCCSValue getVcCSValue() {
		return vcCSValue;
	}
	/**
	 * @param vcCSValue the vcCSValue to set
	 */
	public void setVcCSValue(VCCSValue vcCSValue) {
		this.vcCSValue = vcCSValue;
	}
	/**
	 * @return the vcCSRangeMin
	 */
	public VCCSRangeMin getVcCSRangeMin() {
		return vcCSRangeMin;
	}
	/**
	 * @param vcCSRangeMin the vcCSRangeMin to set
	 */
	public void setVcCSRangeMin(VCCSRangeMin vcCSRangeMin) {
		this.vcCSRangeMin = vcCSRangeMin;
	}
	/**
	 * @return the vcCSRangeMax
	 */
	public VCCSRangeMax getVcCSRangeMax() {
		return vcCSRangeMax;
	}
	/**
	 * @param vcCSRangeMax the vcCSRangeMax to set
	 */
	public void setVcCSRangeMax(VCCSRangeMax vcCSRangeMax) {
		this.vcCSRangeMax = vcCSRangeMax;
	}
}