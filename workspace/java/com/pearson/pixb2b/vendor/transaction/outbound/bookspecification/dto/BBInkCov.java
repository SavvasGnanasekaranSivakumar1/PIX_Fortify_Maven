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
 * Title		: 	BBInkCov.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	23 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * BBInkCov is a data transfer object to store the Book Block 
 * EdgeTrim ColourSpecs InkCoverage details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BBInkCov implements java.io.Serializable {
	private static final long serialVersionUID = 8197800414975557559L;
	
	private String inkCovgeType		= null;
	private BBValue bbValue			= null;
	private BBRangeMin bbRangeMin	= null;
	private BBRangeMax bbRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public BBInkCov() {
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
	 * @return the bbValue
	 */
	public BBValue getBbValue() {
		return bbValue;
	}
	/**
	 * @param bbValue the bbValue to set
	 */
	public void setBbValue(BBValue bbValue) {
		this.bbValue = bbValue;
	}
	/**
	 * @return the bbRangeMin
	 */
	public BBRangeMin getBbRangeMin() {
		return bbRangeMin;
	}
	/**
	 * @param bbRangeMin the bbRangeMin to set
	 */
	public void setBbRangeMin(BBRangeMin bbRangeMin) {
		this.bbRangeMin = bbRangeMin;
	}
	/**
	 * @return the bbRangeMax
	 */
	public BBRangeMax getBbRangeMax() {
		return bbRangeMax;
	}
	/**
	 * @param bbRangeMax the bbRangeMax to set
	 */
	public void setBbRangeMax(BBRangeMax bbRangeMax) {
		this.bbRangeMax = bbRangeMax;
	}
}