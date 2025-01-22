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
 * 1.0	Ashish Agrawal   29 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.shared.dto.BuyerPartyNameAddress;

/**
 * BuyerParty is a data transfer object to store the 
 * Buyer Party Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class BuyerParty implements java.io.Serializable {
	private static final long serialVersionUID = -3280250296819718337L;
	
	private ArrayList partyIdentifierList		= null;
	private BuyerPartyNameAddress nameAddress= null;
	private ArrayList commonContactList 		= null;
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
	/**
	 * @return the commonContactList
	 */
	public ArrayList getCommonContactList() {
		return commonContactList;
	}
	/**
	 * @param commonContactList the commonContactList to set
	 */
	public void setCommonContactList(ArrayList commonContactList) {
		this.commonContactList = commonContactList;
	}
}
