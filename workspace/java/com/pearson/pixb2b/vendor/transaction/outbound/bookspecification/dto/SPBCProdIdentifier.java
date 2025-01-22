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
 * Title		: 	SPBCProdIdentifier.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date				Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	26 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SPBCProdIdentifier is a data transfer object to store the Spec Packing Box
 * Characteristics ProductIdentifier details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPBCProdIdentifier implements java.io.Serializable {
	private static final long serialVersionUID = -2696465353196359294L;
	
	private String agency = null;
	private String piType = null;
	private String piVal  = null;
	/**
	 * Default constructor.
	 */
	public SPBCProdIdentifier() {
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
