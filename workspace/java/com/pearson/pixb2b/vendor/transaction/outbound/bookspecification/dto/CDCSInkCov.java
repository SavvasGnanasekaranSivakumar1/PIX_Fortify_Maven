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
 * Title		: 	CDCSInkCov.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	2 Dec, 2009		Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * CDCSInkCov is a data transfer object to store the Specification NonPress Component Media
 * CD ColourSpecs InkCoverage details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class CDCSInkCov implements java.io.Serializable {
	private static final long serialVersionUID = 8656125125514119973L;
	
	private String inkCovgeType			= null;
	private CDCSValue cdCSValue			= null;
	private CDCSRangeMin cdCSRangeMin	= null;
	private CDCSRangeMax cdCSRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public CDCSInkCov() {
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
	 * @return the cdCSValue
	 */
	public CDCSValue getCdCSValue() {
		return cdCSValue;
	}
	/**
	 * @param cdCSValue the cdCSValue to set
	 */
	public void setCdCSValue(CDCSValue cdCSValue) {
		this.cdCSValue = cdCSValue;
	}
	/**
	 * @return the cdCSRangeMin
	 */
	public CDCSRangeMin getCdCSRangeMin() {
		return cdCSRangeMin;
	}
	/**
	 * @param cdCSRangeMin the cdCSRangeMin to set
	 */
	public void setCdCSRangeMin(CDCSRangeMin cdCSRangeMin) {
		this.cdCSRangeMin = cdCSRangeMin;
	}
	/**
	 * @return the cdCSRangeMax
	 */
	public CDCSRangeMax getCdCSRangeMax() {
		return cdCSRangeMax;
	}
	/**
	 * @param cdCSRangeMax the cdCSRangeMax to set
	 */
	public void setCdCSRangeMax(CDCSRangeMax cdCSRangeMax) {
		this.cdCSRangeMax = cdCSRangeMax;
	}
}