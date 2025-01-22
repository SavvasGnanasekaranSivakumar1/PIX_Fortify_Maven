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
 * Title		: 	JBCSInkCov.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	2 Dec, 2009		Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * JBCSInkCov is a data transfer object to store the Specification NonPress Component Media
 * CD JewelBox ColourSpecs InkCoverage details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class JBCSInkCov implements java.io.Serializable {
	private static final long serialVersionUID = 1261617222692582889L;
	
	private String inkCovgeType			= null;
	private JBCSValue jbCSValue			= null;
	private JBCSRangeMin jbCSRangeMin	= null;
	private JBCSRangeMax jbCSRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public JBCSInkCov() {
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
	public JBCSValue getJbCSValue() {
		return jbCSValue;
	}
	/**
	 * @param jbCSValue the jbCSValue to set
	 */
	public void setJbCSValue(JBCSValue jbCSValue) {
		this.jbCSValue = jbCSValue;
	}
	/**
	 * @return the jbCSRangeMin
	 */
	public JBCSRangeMin getJbCSRangeMin() {
		return jbCSRangeMin;
	}
	/**
	 * @param jbCSRangeMin the jbCSRangeMin to set
	 */
	public void setJbCSRangeMin(JBCSRangeMin jbCSRangeMin) {
		this.jbCSRangeMin = jbCSRangeMin;
	}
	/**
	 * @return the jbCSRangeMax
	 */
	public JBCSRangeMax getJbCSRangeMax() {
		return jbCSRangeMax;
	}
	/**
	 * @param jbCSRangeMax the jbCSRangeMax to set
	 */
	public void setJbCSRangeMax(JBCSRangeMax jbCSRangeMax) {
		this.jbCSRangeMax = jbCSRangeMax;
	}
}