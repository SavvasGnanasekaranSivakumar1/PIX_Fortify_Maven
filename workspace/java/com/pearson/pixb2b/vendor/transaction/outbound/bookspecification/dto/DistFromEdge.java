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
 * Title		: 	DistFromEdge.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	25 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * DistFromEdge is a data transfer object to store the Perforation Distance From Edge
 * details of SpecBinding and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DistFromEdge implements java.io.Serializable {
	private static final long serialVersionUID = -1101175560752337471L;
	
	private String edgeType 		= null;
	private DFEValue value			= null;
	private DFERangeMin rangeMin	= null;
	private DFERangeMax rangeMax  	= null;
	/**
	 * Default constructor.
	 */
	public DistFromEdge() {
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
	public DFEValue getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(DFEValue value) {
		this.value = value;
	}
	/**
	 * @return the rangeMin
	 */
	public DFERangeMin getRangeMin() {
		return rangeMin;
	}
	/**
	 * @param rangeMin the rangeMin to set
	 */
	public void setRangeMin(DFERangeMin rangeMin) {
		this.rangeMin = rangeMin;
	}
	/**
	 * @return the rangeMax
	 */
	public DFERangeMax getRangeMax() {
		return rangeMax;
	}
	/**
	 * @param rangeMax the rangeMax to set
	 */
	public void setRangeMax(DFERangeMax rangeMax) {
		this.rangeMax = rangeMax;
	}
}
