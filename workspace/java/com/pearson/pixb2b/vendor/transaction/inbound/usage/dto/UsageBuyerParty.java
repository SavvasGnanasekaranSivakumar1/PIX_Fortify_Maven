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
 * Title		: 	LocationParty.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   2 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

import java.util.ArrayList;


/**
 * UsageBuyerParty is a data transfer object to store the 
 * usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class UsageBuyerParty implements java.io.Serializable {	
	private static final long serialVersionUID = 210089304788721917L;
	
	private String partyType = null;
	private BuyerPartyIdentifier partyIdentifier = null;
    private BuyerNameAddress nameAddress =  null;
    private BuyerContact contactInfo = null;
    private ArrayList buyerComments = null;	
		
	
	/**
	 * Default constructor.
	 */
	public UsageBuyerParty() {
		super();
	}



	/**
	 * @return Returns the contactInfo.
	 */
	public BuyerContact getContactInfo() {
		return contactInfo;
	}



	/**
	 * @param contactInfo The contactInfo to set.
	 */
	public void setContactInfo(BuyerContact contactInfo) {
		this.contactInfo = contactInfo;
	}



	/**
	 * @return Returns the nameAddress.
	 */
	public BuyerNameAddress getNameAddress() {
		return nameAddress;
	}



	/**
	 * @param nameAddress The nameAddress to set.
	 */
	public void setNameAddress(BuyerNameAddress nameAddress) {
		this.nameAddress = nameAddress;
	}



	/**
	 * @return Returns the partyIdentifier.
	 */
	public BuyerPartyIdentifier getPartyIdentifier() {
		return partyIdentifier;
	}



	/**
	 * @param partyIdentifier The partyIdentifier to set.
	 */
	public void setPartyIdentifier(BuyerPartyIdentifier partyIdentifier) {
		this.partyIdentifier = partyIdentifier;
	}



	/**
	 * @return Returns the partyType.
	 */
	public String getPartyType() {
		return partyType;
	}



	/**
	 * @param partyType The partyType to set.
	 */
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}



	/**
	 * @param buyerComments The buyerComments to set.
	 */
	public void setBuyerComments(ArrayList buyerComments) {
		this.buyerComments = buyerComments;
	}



	/**
	 * @return Returns the buyerComments.
	 */
	public ArrayList getBuyerComments() {
		return buyerComments;
	}



	

}