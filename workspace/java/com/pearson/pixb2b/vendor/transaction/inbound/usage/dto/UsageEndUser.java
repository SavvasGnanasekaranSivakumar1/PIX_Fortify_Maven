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
 * Title		: 	UsageEndUser.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   23 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

import java.util.ArrayList;


/**
 * UsageEndUser is a data transfer object to store the 
 * usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class UsageEndUser implements java.io.Serializable {	
	private static final long serialVersionUID = 210089304788721917L;
	
	private String partyType = null;
	private EndPartyIdentifier partyIdentifier = null;
    private EndNameAddress nameAddress =  null;
    private EndContact contactInfo = null;
    private ArrayList endComments = null;
	
	/**
	 * Default constructor.
	 */
	public UsageEndUser() {
		super();
		partyIdentifier = new EndPartyIdentifier();
	}

	/**
	 * @return Returns the contactInfo.
	 */
	public EndContact getContactInfo() {
		return contactInfo;
	}

	/**
	 * @param contactInfo The contactInfo to set.
	 */
	public void setContactInfo(EndContact contactInfo) {
		this.contactInfo = contactInfo;
	}

	/**
	 * @return Returns the nameAddress.
	 */
	public EndNameAddress getNameAddress() {
		return nameAddress;
	}

	/**
	 * @param nameAddress The nameAddress to set.
	 */
	public void setNameAddress(EndNameAddress nameAddress) {
		this.nameAddress = nameAddress;
	}

	/**
	 * @return Returns the partyIdentifier.
	 */
	public EndPartyIdentifier getPartyIdentifier() {
		return partyIdentifier;
	}

	/**
	 * @param partyIdentifier The partyIdentifier to set.
	 */
	public void setPartyIdentifier(EndPartyIdentifier partyIdentifier) {
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
	 * @param endComments The endComments to set.
	 */
	public void setEndComments(ArrayList endComments) {
		this.endComments = endComments;
	}

	/**
	 * @return Returns the endComments.
	 */
	public ArrayList getEndComments() {
		return endComments;
	}

}