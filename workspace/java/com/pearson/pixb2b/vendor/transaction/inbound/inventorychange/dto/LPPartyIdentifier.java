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
 * Title		: 	PartyIdentifier.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   3 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto;


/**
 * LPPartyIdentifier is a data transfer object to store the 
 * IC details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class LPPartyIdentifier implements java.io.Serializable {
	private static final long serialVersionUID = -2343028557711546699L;
	
	private String partyIdentifierType = null;
    private String partyIdentifierValue = null;
    private String agency = null;

	/**
	 * Default constructor.
	 */
	public LPPartyIdentifier() {
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