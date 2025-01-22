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
 * Title		: 	DvdJBCSInkCov.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	2 Dec, 2009		Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * DvdJBCSInkCov is a data transfer object to store the Specification NonPress Component Media
 * DVD JewelBox ColourSpecs InkCoverage details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DvdJBCSInkCov implements java.io.Serializable {
	private static final long serialVersionUID = 2488749444036228716L;
	
	private String inkCovgeType				= null;
	private DvdJBCSValue jbCSValue			= null;
	private DvdJBCSRangeMin jbCSRangeMin	= null;
	private DvdJBCSRangeMax jbCSRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public DvdJBCSInkCov() {
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
	 * @return the jbCSValue
	 */
	public DvdJBCSValue getJbCSValue() {
		return jbCSValue;
	}
	/**
	 * @param jbCSValue the jbCSValue to set
	 */
	public void setJbCSValue(DvdJBCSValue jbCSValue) {
		this.jbCSValue = jbCSValue;
	}
	/**
	 * @return the jbCSRangeMin
	 */
	public DvdJBCSRangeMin getJbCSRangeMin() {
		return jbCSRangeMin;
	}
	/**
	 * @param jbCSRangeMin the jbCSRangeMin to set
	 */
	public void setJbCSRangeMin(DvdJBCSRangeMin jbCSRangeMin) {
		this.jbCSRangeMin = jbCSRangeMin;
	}
	/**
	 * @return the jbCSRangeMax
	 */
	public DvdJBCSRangeMax getJbCSRangeMax() {
		return jbCSRangeMax;
	}
	/**
	 * @param jbCSRangeMax the jbCSRangeMax to set
	 */
	public void setJbCSRangeMax(DvdJBCSRangeMax jbCSRangeMax) {
		this.jbCSRangeMax = jbCSRangeMax;
	}
}