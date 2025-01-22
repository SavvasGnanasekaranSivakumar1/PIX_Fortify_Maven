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
 * Title		: 	PCPDisFrmEdge.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCPDisFrmEdge is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications Perforation Distance FromEdge details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCPDisFrmEdge implements java.io.Serializable {
	private static final long serialVersionUID = -1301581998935848983L;
	
	private String edgeType 			= null;
	private PCPDValue pcPDValue			= null;
	private PCPDRangeMin pcPDRangeMin	= null;
	private PCPDRangeMax pcPDRangeMax	= null;
	/**
	 * Default constructor.
	 */
	public PCPDisFrmEdge() {
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
	 * @return the pcPDValue
	 */
	public PCPDValue getPcPDValue() {
		return pcPDValue;
	}
	/**
	 * @param pcPDValue the pcPDValue to set
	 */
	public void setPcPDValue(PCPDValue pcPDValue) {
		this.pcPDValue = pcPDValue;
	}
	/**
	 * @return the pcPDRangeMin
	 */
	public PCPDRangeMin getPcPDRangeMin() {
		return pcPDRangeMin;
	}
	/**
	 * @param pcPDRangeMin the pcPDRangeMin to set
	 */
	public void setPcPDRangeMin(PCPDRangeMin pcPDRangeMin) {
		this.pcPDRangeMin = pcPDRangeMin;
	}
	/**
	 * @return the pcPDRangeMax
	 */
	public PCPDRangeMax getPcPDRangeMax() {
		return pcPDRangeMax;
	}
	/**
	 * @param pcPDRangeMax the pcPDRangeMax to set
	 */
	public void setPcPDRangeMax(PCPDRangeMax pcPDRangeMax) {
		this.pcPDRangeMax = pcPDRangeMax;
	}
}
