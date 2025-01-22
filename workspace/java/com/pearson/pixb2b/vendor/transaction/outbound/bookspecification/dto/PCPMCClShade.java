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
 * Title		: 	PCPMCClShade.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	1 Dec, 2009		Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCPMCClShade is a data transfer object to store the Specification Press Component
 * Manufacturing Specifications Printing Material Characteristics ColourSpecs
 * ColourShade details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCPMCClShade implements java.io.Serializable {
	private static final long serialVersionUID = 3661832581548015086L;
	
	private String agency	= null;
	private String csVal	= null;
	/**
	 * Default constructor.
	 */
	public PCPMCClShade() {
		super();
	}
	/**
	 * @return the agency
	 */
	public String getAgency() {
		return agency;
	}
	/**
	 * @param agency the agency to set
	 */
	public void setAgency(String agency) {
		this.agency = agency;
	}
	/**
	 * @return the csVal
	 */
	public String getCsVal() {
		return csVal;
	}
	/**
	 * @param csVal the csVal to set
	 */
	public void setCsVal(String csVal) {
		this.csVal = csVal;
	}
}