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
 * Title		: 	ShipToPartyPartyIdentifier.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   8 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto;

/**
 * ShipToPartyPartyIdentifier is a data transfer object to store the 
 * shipping instructions details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ShipToPartyPartyIdentifier implements java.io.Serializable {
	private static final long serialVersionUID = -1455560274599135412L;
	
	private String partyIdentifierType = null;
    private String partyIdentifierValue = null;
    private String agency = null;

	/**
	 * Default constructor.
	 */
	public ShipToPartyPartyIdentifier() {
		super();
	}

	public String getPartyIdentifierType() {
		return partyIdentifierType;
	}

	public void setPartyIdentifierType(String partyIdentifierType) {
		this.partyIdentifierType = partyIdentifierType;
	}

	public String getPartyIdentifierValue() {
		return partyIdentifierValue;
	}

	public void setPartyIdentifierValue(String partyIdentifierValue) {
		this.partyIdentifierValue = partyIdentifierValue;
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency;
	}
	
	
}