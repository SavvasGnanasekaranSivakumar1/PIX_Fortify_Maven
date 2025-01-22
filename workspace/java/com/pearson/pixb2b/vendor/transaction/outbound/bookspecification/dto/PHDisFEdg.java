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
 * Title		: 	PHDisFEdg.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	24 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PHDisFEdg is a data transfer object to store the specification Binding   
 * PunchedHole Distance From Edge details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PHDisFEdg implements java.io.Serializable {
	private static final long serialVersionUID = -5217049868160545368L;
	
	private String edgeType				= null;
	private DisFEdgValue value			= null;
	private DisFEdgRangeMin rangeMin	= null;
	private DisFEdgRangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public PHDisFEdg() {
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
	public DisFEdgValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(DisFEdgValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public DisFEdgRangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(DisFEdgRangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public DisFEdgRangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(DisFEdgRangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
