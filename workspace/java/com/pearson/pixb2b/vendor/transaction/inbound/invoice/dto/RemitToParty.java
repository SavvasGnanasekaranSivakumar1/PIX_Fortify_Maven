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
 * RemitToParty is a data transfer object to store the 
 * Invoice Details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class RemitToParty implements java.io.Serializable {
	private static final long serialVersionUID = -6751327946088354846L;

	private ArrayList remitPartyIdList		= null;
	private NameAddress remitNameAddr = null;
	private ArrayList remitContactList 		= null;
	/**
	 * @return Returns the remitContactList.
	 */
	public ArrayList getRemitContactList() {
		return remitContactList;
	}
	/**
	 * @param remitContactList The remitContactList to set.
	 */
	public void setRemitContactList(ArrayList remitContactList) {
		this.remitContactList = remitContactList;
	}
	
	/**
	 * @return Returns the remitPartyIdList.
	 */
	public ArrayList getRemitPartyIdList() {
		return remitPartyIdList;
	}
	/**
	 * @param remitPartyIdList The remitPartyIdList to set.
	 */
	public void setRemitPartyIdList(ArrayList remitPartyIdList) {
		this.remitPartyIdList = remitPartyIdList;
	}
	/**
	 * Default constructor.
	 */
	public RemitToParty() {
		super();
	}
	/**
	 * @return Returns the remitNameAddr.
	 */
	public NameAddress getRemitNameAddr() {
		return remitNameAddr;
	}
	/**
	 * @param remitNameAddr The remitNameAddr to set.
	 */
	public void setRemitNameAddr(NameAddress remitNameAddr) {
		this.remitNameAddr = remitNameAddr;
	}
	
}
