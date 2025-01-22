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
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto;


/**
 * ICLocationParty is a data transfer object to store the 
 * inventory status details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ICLocationParty implements java.io.Serializable {	
	private static final long serialVersionUID = 210089304788721917L;
	
	private LPPartyIdentifier partyIdentifier = null;
    private LPNameAddress nameAddress =  null;
    private LPContact contactInfo = null;
	private String partyType = null;
		
	
	/**
	 * Default constructor.
	 */
	public ICLocationParty() {
		super();
		partyIdentifier = new LPPartyIdentifier();
	}



	/**
	 * @return Returns the contactInfo.
	 */
	public LPContact getContactInfo() {
		return contactInfo;
	}



	/**
	 * @param contactInfo The contactInfo to set.
	 */
	public void setContactInfo(LPContact contactInfo) {
		this.contactInfo = contactInfo;
	}



	/**
	 * @return Returns the nameAddress.
	 */
	public LPNameAddress getNameAddress() {
		return nameAddress;
	}



	/**
	 * @param nameAddress The nameAddress to set.
	 */
	public void setNameAddress(LPNameAddress nameAddress) {
		this.nameAddress = nameAddress;
	}



	/**
	 * @return Returns the partyIdentifier.
	 */
	public LPPartyIdentifier getPartyIdentifier() {
		return partyIdentifier;
	}



	/**
	 * @param partyIdentifier The partyIdentifier to set.
	 */
	public void setPartyIdentifier(LPPartyIdentifier partyIdentifier) {
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

}