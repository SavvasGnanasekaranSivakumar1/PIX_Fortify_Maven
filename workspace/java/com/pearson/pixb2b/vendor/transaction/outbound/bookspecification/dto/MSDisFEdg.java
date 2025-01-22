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
 * Title		: 	MSDisFEdg.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	2 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * MSDisFEdg is a data transfer object to store the Specification NonPress Component 
 * Media Slide HolePunch Distance From Edge details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class MSDisFEdg implements java.io.Serializable {
	private static final long serialVersionUID = 4895435378782994524L;
	
	private String edgeType				= null;
	private MSDFEdgValue value			= null;
	private MSDFEdgRangeMin rangeMin	= null;
	private MSDFEdgRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public MSDisFEdg() {
		super();
	}
	/**
	 * @return the edgeType
	 */
	public String getEdgeType() {
		return edgeType;
	}
	/**
	 * @param edgeType the edgeType to set
	 */
	public void setEdgeType(String edgeType) {
		this.edgeType = edgeType;
	}
	/**
	 * @return the value
	 */
	public MSDFEdgValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(MSDFEdgValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public MSDFEdgRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(MSDFEdgRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public MSDFEdgRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(MSDFEdgRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
