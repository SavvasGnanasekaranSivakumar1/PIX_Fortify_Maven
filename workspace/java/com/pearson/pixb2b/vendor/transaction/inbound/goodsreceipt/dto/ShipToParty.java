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
 * Title		: 	ShipToParty.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   13 Jan, 2010	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.shared.dto.ShipToPartyNameAddress;
/**
 * ShipToParty is a helper data transfer object to store the 
 * ShipTo Party details.
 * 
 * @author Abhilasha Nigam
 */
public class ShipToParty implements java.io.Serializable {
	private static final long serialVersionUID = 2816034054585698236L;

	private String partyType = null;
	private PartyIdentifier partyId= null;
	private ShipToPartyNameAddress nameAddress = null;;
	private CommonContact commonContact = null;
	
	
	
	/**
	 * Default constructor.
	 */
	public ShipToParty() {
		super();
		partyId = new PartyIdentifier();
	}
	
	/**
	 * @return Returns the commonContact.
	 */
	public CommonContact getCommonContact() {
		return commonContact;
	}
	/**
	 * @param commonContact The commonContact to set.
	 */
	public void setCommonContact(CommonContact commonContact) {
		this.commonContact = commonContact;
	}
	/**
	 * @return Returns the partyId.
	 */
	public PartyIdentifier getPartyId() {
		return partyId;
	}
	/**
	 * @param partyId The partyId to set.
	 */
	public void setPartyId(PartyIdentifier partyId) {
		this.partyId = partyId;
	}
	
	/**
	 * @return the partyType
	 */
	public String getPartyType() {
		return partyType;
	}
	/**
	 * @param partyType the partyType to set
	 */
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}
	
	/**
	 * @return the nameAddress
	 */
	public ShipToPartyNameAddress getNameAddress() {
		return nameAddress;
	}
	/**
	 * @param nameAddress the nameAddress to set
	 */
	public void setNameAddress(ShipToPartyNameAddress nameAddress) {
		this.nameAddress = nameAddress;
	}
	
}
