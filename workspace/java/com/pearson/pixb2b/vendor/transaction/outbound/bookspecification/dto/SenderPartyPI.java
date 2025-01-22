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
 * Title		: 	SenderPartyPI.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   	20 Nov, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

/**
 * SenderPartyPI is a data transfer object to store the 
 * Sender Party Party Identifier details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SenderPartyPI implements java.io.Serializable {
	private static final long serialVersionUID = 7210233727206508013L;
	
	private String piType = null;
    private String piValue = null;
    private String agency = null;
	/**
	 * Default constructor.
	 */
	public SenderPartyPI() {
		super();
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
	 * @return the piValue
	 */
	public String getPiValue() {
		return piValue;
	}
	/**
	 * @param piValue the piValue to set
	 */
	public void setPiValue(String piValue) {
		this.piValue = piValue;
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
}