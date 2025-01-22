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
 * Title		: 	BusinessSenderPartyIdentifier.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   26 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.envelope;

/**
 * BusinessSenderPartyIdentifier is a data transfer object to store the Business Sender 
 * PartyIdentifier Information for Inbound and Outbound transactions.
 * 
 * @author Ashish Agrawal
 */
public class BusinessSenderPI implements java.io.Serializable {
	private static final long serialVersionUID = -3723945771493838171L;
	
	private String partyIdentifierType  = null;
    private String partyIdentifierValue = null;
	/**
	 * Default constructor.
	 */
	public BusinessSenderPI() {
		super();
	}
	/**
	 * @return the partyIdentifierType
	 */
	public String getPartyIdentifierType() {
		return partyIdentifierType;
	}
	/**
	 * @param partyIdentifierType the partyIdentifierType to set
	 */
	public void setPartyIdentifierType(String partyIdentifierType) {
		this.partyIdentifierType = partyIdentifierType;
	}
	/**
	 * @return the partyIdentifierValue
	 */
	public String getPartyIdentifierValue() {
		return partyIdentifierValue;
	}
	/**
	 * @param partyIdentifierValue the partyIdentifierValue to set
	 */
	public void setPartyIdentifierValue(String partyIdentifierValue) {
		this.partyIdentifierValue = partyIdentifierValue;
	}
}
