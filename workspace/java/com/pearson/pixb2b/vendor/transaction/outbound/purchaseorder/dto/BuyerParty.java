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
 * Title		: 	PurchaseOrderDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   11 Aug, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.shared.dto.BuyerPartyCommonContact;
import com.pearson.pixb2b.vendor.transaction.shared.dto.BuyerPartyNameAddress;
import com.pearson.pixb2b.vendor.transaction.shared.dto.BuyerPartyPartyIdentifier;
/**
 * BuyerParty is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * @author Abhilasha Nigam
 */
public class BuyerParty implements java.io.Serializable {
	private static final long serialVersionUID = 4542498873301851725L;
	
	private String logisticsRole = null;
	//private BuyerPartyPartyIdentifier partyIdentifier = null;
	private ArrayList partyIdentifier = null;
    private BuyerPartyNameAddress nameAddress =  null;
	private String url = null;
	private String urlContext  = null;
	private BuyerPartyCommonContact commonContact = null;	
	/**
	 * Default constructor.
	 */
	public BuyerParty() {
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
	public ArrayList getPartyIdentifier() {
		return partyIdentifier;
	}
	/**
	 * @param partyIdentifier the partyIdentifier to set
	 */
	public void setPartyIdentifier(ArrayList partyIdentifier) {
		this.partyIdentifier = partyIdentifier;
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
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the urlContext
	 */
	public String getUrlContext() {
		return urlContext;
	}
	/**
	 * @param urlContext the urlContext to set
	 */
	public void setUrlContext(String urlContext) {
		this.urlContext = urlContext;
	}
	/**
	 * @return the commonContact
	 */
	public BuyerPartyCommonContact getCommonContact() {
		return commonContact;
	}
	/**
	 * @param commonContact the commonContact to set
	 */
	public void setCommonContact(BuyerPartyCommonContact commonContact) {
		this.commonContact = commonContact;
	}
}