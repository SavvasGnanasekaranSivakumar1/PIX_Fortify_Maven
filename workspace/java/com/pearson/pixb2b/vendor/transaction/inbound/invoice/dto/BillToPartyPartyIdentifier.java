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
 * Title		: 	BillToPartyPartyIdentifier.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   8 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto;


/**
 * BillToPartyPartyIdentifier is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class BillToPartyPartyIdentifier implements java.io.Serializable {
   private static final long serialVersionUID = -2789230898313574727L;

   private String partyIdentifierType = null;
   private String partyIdentifierValue = null;
   private String agency = null;

	/**
	 * Default constructor.
	 */
	public BillToPartyPartyIdentifier() {
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