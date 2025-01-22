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
 * Title		: 	ProdIdSpecBinding.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   23 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;


/**
 * ProdIdSpecBinding is a data transfer object to store the 
 * Specification Binding ProductIdentifier details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ProdIdSpecBinding implements java.io.Serializable {
	private static final long serialVersionUID = -8718972659039268141L;
	
	private String agency = null;
	private String piType = null;
	private String piVal  = null;
	/**
	 * Default constructor.
	 */
	public ProdIdSpecBinding() {
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
