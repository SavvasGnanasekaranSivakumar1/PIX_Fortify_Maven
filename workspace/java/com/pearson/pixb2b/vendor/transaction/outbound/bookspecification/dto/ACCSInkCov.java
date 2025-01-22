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
 * Title		: 	ACCSInkCov.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	2 Dec, 2009		Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * ACCSInkCov is a data transfer object to store the Specification NonPress Component Audio Cassette 
 * Label Characteristics ColourSpecs InkCoverage details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ACCSInkCov implements java.io.Serializable {
	private static final long serialVersionUID = 6828592595816211981L;
	
	private String inkCovgeType			= null;
	private ACCSValue acCSValue			= null;
	private ACCSRangeMin acCSRangeMin	= null;
	private ACCSRangeMax acCSRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public ACCSInkCov() {
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
	 * @return the acCSValue
	 */
	public ACCSValue getAcCSValue() {
		return acCSValue;
	}
	/**
	 * @param acCSValue the acCSValue to set
	 */
	public void setAcCSValue(ACCSValue acCSValue) {
		this.acCSValue = acCSValue;
	}
	/**
	 * @return the acCSRangeMin
	 */
	public ACCSRangeMin getAcCSRangeMin() {
		return acCSRangeMin;
	}
	/**
	 * @param acCSRangeMin the acCSRangeMin to set
	 */
	public void setAcCSRangeMin(ACCSRangeMin acCSRangeMin) {
		this.acCSRangeMin = acCSRangeMin;
	}
	/**
	 * @return the acCSRangeMax
	 */
	public ACCSRangeMax getAcCSRangeMax() {
		return acCSRangeMax;
	}
	/**
	 * @param acCSRangeMax the acCSRangeMax to set
	 */
	public void setAcCSRangeMax(ACCSRangeMax acCSRangeMax) {
		this.acCSRangeMax = acCSRangeMax;
	}
}