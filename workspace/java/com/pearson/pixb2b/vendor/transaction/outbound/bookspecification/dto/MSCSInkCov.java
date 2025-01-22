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
 * Title		: 	MSCSInkCov.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	2 Dec, 2009		Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MSCSInkCov is a data transfer object to store the Specification NonPress Component 
 * Media Slide ColourSpecs InkCoverage details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MSCSInkCov implements java.io.Serializable {
	private static final long serialVersionUID = -360213202758375047L;
	
	private String inkCovgeType			= null;
	private MSCSValue msCSValue			= null;
	private MSCSRangeMin msCSRangeMin	= null;
	private MSCSRangeMax msCSRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public MSCSInkCov() {
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
	 * @return the msCSValue
	 */
	public MSCSValue getMsCSValue() {
		return msCSValue;
	}
	/**
	 * @param msCSValue the msCSValue to set
	 */
	public void setMsCSValue(MSCSValue msCSValue) {
		this.msCSValue = msCSValue;
	}
	/**
	 * @return the msCSRangeMin
	 */
	public MSCSRangeMin getMsCSRangeMin() {
		return msCSRangeMin;
	}
	/**
	 * @param msCSRangeMin the msCSRangeMin to set
	 */
	public void setMsCSRangeMin(MSCSRangeMin msCSRangeMin) {
		this.msCSRangeMin = msCSRangeMin;
	}
	/**
	 * @return the msCSRangeMax
	 */
	public MSCSRangeMax getMsCSRangeMax() {
		return msCSRangeMax;
	}
	/**
	 * @param msCSRangeMax the msCSRangeMax to set
	 */
	public void setMsCSRangeMax(MSCSRangeMax msCSRangeMax) {
		this.msCSRangeMax = msCSRangeMax;
	}
}