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
 * Title		: 	DvdCSInkCov.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	2 Dec, 2009		Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * DvdCSInkCov is a data transfer object to store the Specification NonPress Component Media
 * DVD ColourSpecs InkCoverage details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DvdCSInkCov implements java.io.Serializable {
	private static final long serialVersionUID = -1472985223171251551L;
	
	private String inkCovgeType			= null;
	private DvdCSValue dvdCSValue		= null;
	private DvdCSRangeMin dvdCSRangeMin	= null;
	private DvdCSRangeMax dvdCSRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public DvdCSInkCov() {
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
	 * @return the dvdCSValue
	 */
	public DvdCSValue getDvdCSValue() {
		return dvdCSValue;
	}
	/**
	 * @param dvdCSValue the dvdCSValue to set
	 */
	public void setDvdCSValue(DvdCSValue dvdCSValue) {
		this.dvdCSValue = dvdCSValue;
	}
	/**
	 * @return the dvdCSRangeMin
	 */
	public DvdCSRangeMin getDvdCSRangeMin() {
		return dvdCSRangeMin;
	}
	/**
	 * @param dvdCSRangeMin the dvdCSRangeMin to set
	 */
	public void setDvdCSRangeMin(DvdCSRangeMin dvdCSRangeMin) {
		this.dvdCSRangeMin = dvdCSRangeMin;
	}
	/**
	 * @return the dvdCSRangeMax
	 */
	public DvdCSRangeMax getDvdCSRangeMax() {
		return dvdCSRangeMax;
	}
	/**
	 * @param dvdCSRangeMax the dvdCSRangeMax to set
	 */
	public void setDvdCSRangeMax(DvdCSRangeMax dvdCSRangeMax) {
		this.dvdCSRangeMax = dvdCSRangeMax;
	}
}