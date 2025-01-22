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
 * Title		: 	BillToParty.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   8 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;

import com.pearson.pixb2b.vendor.transaction.shared.dto.BillToPartyCommonContact;
import com.pearson.pixb2b.vendor.transaction.shared.dto.BillToPartyNameAddress;
import com.pearson.pixb2b.vendor.transaction.shared.dto.BillToPartyPartyIdentifier;
/**
 * BillToParty is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class BillToParty implements java.io.Serializable {
	private static final long serialVersionUID = -1222610897983924878L;
	
	private String logisticsRole = null;
	private BillToPartyPartyIdentifier partyIdentifier = null;
	private BillToPartyNameAddress nameAddress = null;
	private BillToPartyCommonContact commonContact = null;
	/**
	 * Default constructor.
	 */
	public BillToParty() {
		super();
	}
	/**
	 * @return the logisticsRole
	 */
	public String getLogisticsRole() {
		return logisticsRole;
	}
	/**
	 * @param logisticsRole the logisticsRole to set
	 */
	public void setLogisticsRole(String logisticsRole) {
		this.logisticsRole = logisticsRole;
	}
	/**
	 * @return the partyIdentifier
	 */
	public BillToPartyPartyIdentifier getPartyIdentifier() {
		return partyIdentifier;
	}
	/**
	 * @param partyIdentifier the partyIdentifier to set
	 */
	public void setPartyIdentifier(BillToPartyPartyIdentifier partyIdentifier) {
		this.partyIdentifier = partyIdentifier;
	}
	/**
	 * @return the nameAddress
	 */
	public BillToPartyNameAddress getNameAddress() {
		return nameAddress;
	}
	/**
	 * @param nameAddress the nameAddress to set
	 */
	public void setNameAddress(BillToPartyNameAddress nameAddress) {
		this.nameAddress = nameAddress;
	}
	/**
	 * @return the commonContact
	 */
	public BillToPartyCommonContact getCommonContact() {
		return commonContact;
	}
	/**
	 * @param commonContact the commonContact to set
	 */
	public void setCommonContact(BillToPartyCommonContact commonContact) {
		this.commonContact = commonContact;
	}	
}