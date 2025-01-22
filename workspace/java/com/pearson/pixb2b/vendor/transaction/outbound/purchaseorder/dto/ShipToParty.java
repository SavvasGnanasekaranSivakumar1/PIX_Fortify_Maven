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
 * 1.0	Abhilasha Nigam   13 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.shared.dto.ShipToPartyNameAddress;
/**
 * ShipToParty is a helper data transfer object to store the 
 * ShipTo Party details.
 * 
 * @author Abhilasha Nigam
 */
public class ShipToParty implements java.io.Serializable {
	private static final long serialVersionUID = -7768105205888239528L;
	
	private String partyType = null;
	private ArrayList partyIdentifierList= null;
	private ShipToPartyNameAddress nameAddress = null;;
	private ArrayList commonContact = null;
	/**
	 * Default constructor.
	 */
	public ShipToParty() {
		super();
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
	 * @return the partyIdentifierList
	 */
	public ArrayList getPartyIdentifierList() {
		return partyIdentifierList;
	}
	/**
	 * @param partyIdentifierList the partyIdentifierList to set
	 */
	public void setPartyIdentifierList(ArrayList partyIdentifierList) {
		this.partyIdentifierList = partyIdentifierList;
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
	/**
	 * @return the commonContact
	 */
	public ArrayList getCommonContact() {
		return commonContact;
	}
	/**
	 * @param commonContact the commonContact to set
	 */
	public void setCommonContact(ArrayList commonContact) {
		this.commonContact = commonContact;
	}
}
