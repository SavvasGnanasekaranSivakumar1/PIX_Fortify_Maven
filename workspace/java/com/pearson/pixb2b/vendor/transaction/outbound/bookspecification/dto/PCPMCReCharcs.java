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
 * Title		: 	PCPMCReCharcs.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCPMCReCharcs is a data transfer object to store the Specification Press Component
 * Manufacturing Specifications Printing Material Recycled Characteristics
 * details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCPMCReCharcs implements java.io.Serializable {
	private static final long serialVersionUID = 9070855469036069085L;
	
	private String recycRequired		= null;
	private PCPMCRecycled pcPMCRecycled	= null;
	/**
	 * Default constructor.
	 */
	public PCPMCReCharcs() {
		super();
	}
	/**
	 * @return the recycRequired
	 */
	public String getRecycRequired() {
		return recycRequired;
	}
	/**
	 * @param recycRequired the recycRequired to set
	 */
	public void setRecycRequired(String recycRequired) {
		this.recycRequired = recycRequired;
	}
	/**
	 * @return the pcPMCRecycled
	 */
	public PCPMCRecycled getPcPMCRecycled() {
		return pcPMCRecycled;
	}
	/**
	 * @param pcPMCRecycled the pcPMCRecycled to set
	 */
	public void setPcPMCRecycled(PCPMCRecycled pcPMCRecycled) {
		this.pcPMCRecycled = pcPMCRecycled;
	}
}
