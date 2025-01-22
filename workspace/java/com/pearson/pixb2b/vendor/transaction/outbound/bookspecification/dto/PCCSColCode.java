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
 * Title		: 	PCCSColCode.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal  	1 Dec, 2009		Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCCSColCode is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications Ink Characteristics ColourSpecs ColourCode details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCCSColCode implements java.io.Serializable {
	private static final long serialVersionUID = -5499153883719332297L;
	
	private String agency	= null;
	private String ccVal	= null;
	/**
	 * Default constructor.
	 */
	public PCCSColCode() {
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
	 * @return the ccVal
	 */
	public String getCcVal() {
		return ccVal;
	}
	/**
	 * @param ccVal the ccVal to set
	 */
	public void setCcVal(String ccVal) {
		this.ccVal = ccVal;
	}
}