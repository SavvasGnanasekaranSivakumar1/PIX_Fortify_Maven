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
 * Title		: 	TransmissionReceiverPartyIdentifier.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   26 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.envelope;


/**
 * TransmissionReceiverPartyIdentifier is a data transfer object to store the Transmission Receiver 
 * PartyIdentifier Information for Inbound and Outbound transactions.
 * 
 * @author Ashish Agrawal
 */
public class TransmissionReceiverPI implements java.io.Serializable {
	private static final long serialVersionUID = 5021740712605493667L;
	
	private String partyIdentifierType  = null;
    private String partyIdentifierValue = null;
	/**
	 * Default constructor.
	 */
	public TransmissionReceiverPI() {
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
