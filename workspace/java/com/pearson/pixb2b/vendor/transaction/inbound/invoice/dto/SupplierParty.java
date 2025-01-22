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
import com.pearson.pixb2b.vendor.transaction.shared.dto.SupplierPartyNameAddress;

/**
 * BuyerParty is a data transfer object to store the 
 * Buyer Party Details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SupplierParty implements java.io.Serializable {
	private static final long serialVersionUID = -1945859793381845937L;

	private ArrayList suppPartyIdList		= null;
	private SupplierPartyNameAddress suppNameAddr = null;
	private ArrayList suppContactList 		= null;
	/**
	 * Default constructor.
	 */
	public SupplierParty() {
		super();
		suppPartyIdList = new ArrayList();
	}
	/**
	 * @return Returns the suppContactList.
	 */
	public ArrayList getSuppContactList() {
		return suppContactList;
	}
	/**
	 * @param suppContactList The suppContactList to set.
	 */
	public void setSuppContactList(ArrayList suppContactList) {
		this.suppContactList = suppContactList;
	}
	
	/**
	 * @return Returns the suppPartyIdList.
	 */
	public ArrayList getSuppPartyIdList() {
		return suppPartyIdList;
	}
	/**
	 * @param suppPartyIdList The suppPartyIdList to set.
	 */
	public void setSuppPartyIdList(ArrayList suppPartyIdList) {
		this.suppPartyIdList = suppPartyIdList;
	}
	/**
	 * @return Returns the suppNameAddr.
	 */
	public SupplierPartyNameAddress getSuppNameAddr() {
		return suppNameAddr;
	}
	/**
	 * @param suppNameAddr The suppNameAddr to set.
	 */
	public void setSuppNameAddr(SupplierPartyNameAddress suppNameAddr) {
		this.suppNameAddr = suppNameAddr;
	}
	
}
