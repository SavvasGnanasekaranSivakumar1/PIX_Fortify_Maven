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
 * Title		: 	PCFSProdIdentifier.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	1 Dec, 2009	    	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * PCFSProdIdentifier is a data transfer object to store the Specification Press Component Manufacturing 
 * Specifications FinishSpecs Decoration Product Identifier details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PCFSProdIdentifier implements java.io.Serializable {
	private static final long serialVersionUID = -7943502288951599527L;
	
	private String agency = null;
	private String piType = null;
	private String piVal  = null;
	/**
	 * Default constructor.
	 */
	public PCFSProdIdentifier() {
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
	 * @return the piType
	 */
	public String getPiType() {
		return piType;
	}
	/**
	 * @param piType the piType to set
	 */
	public void setPiType(String piType) {
		this.piType = piType;
	}
	/**
	 * @return the piVal
	 */
	public String getPiVal() {
		return piVal;
	}
	/**
	 * @param piVal the piVal to set
	 */
	public void setPiVal(String piVal) {
		this.piVal = piVal;
	}
}
