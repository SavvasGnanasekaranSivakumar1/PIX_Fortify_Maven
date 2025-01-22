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
 * Title		: 	BuyerParty.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   13 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.shared.dto.BuyerPartyNameAddress;


/**
 * BuyerParty is a helper data transfer object to store the 
 * Buyer Party details.
 * 
 * @author Ashish Agrawal
 */
public class BuyerParty implements java.io.Serializable {
	private static final long serialVersionUID = 1511800396788141837L;
	
	private ArrayList partyIdentifierList = null;
	private BuyerPartyNameAddress nameAddress= null;
	/**
	 * Default constructor.
	 */
	public BuyerParty() {
		super();
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
	public BuyerPartyNameAddress getNameAddress() {
		return nameAddress;
	}
	/**
	 * @param nameAddress the nameAddress to set
	 */
	public void setNameAddress(BuyerPartyNameAddress nameAddress) {
		this.nameAddress = nameAddress;
	}
}
