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
 * 1.0	Abhilasha Nigam   8 FEb,2010	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.shared.dto.BillToPartyNameAddress;
import com.pearson.pixb2b.vendor.transaction.shared.dto.BillToPartyPartyIdentifier;
import com.pearson.pixb2b.vendor.transaction.shared.dto.BillToPartyCommonContact;

/**
 * BuyerParty is a data transfer object to store the 
 * Buyer Party Details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class BillToParty implements java.io.Serializable {
	private static final long serialVersionUID = -3280250296819718337L;
	
	private String logisticsRole              = null;
	private ArrayList billPartyIdList		  = null;
	private BillToPartyNameAddress billNameAddr = null;
	private ArrayList billContactList 		   = null;
	/**
	 * @return Returns the billContactList.
	 */
	public ArrayList getBillContactList() {
		return billContactList;
	}
	/**
	 * @param billContactList The billContactList to set.
	 */
	public void setBillContactList(ArrayList billContactList) {
		this.billContactList = billContactList;
	}
	
	/**
	 * @return Returns the billNameAddr.
	 */
	public BillToPartyNameAddress getBillNameAddr() {
		return billNameAddr;
	}
	/**
	 * @param billNameAddr The billNameAddr to set.
	 */
	public void setBillNameAddr(BillToPartyNameAddress billNameAddr) {
		this.billNameAddr = billNameAddr;
	}
	/**
	 * @return Returns the billPartyIdList.
	 */
	public ArrayList getBillPartyIdList() {
		return billPartyIdList;
	}
	/**
	 * @param billPartyIdList The billPartyIdList to set.
	 */
	public void setBillPartyIdList(ArrayList billPartyIdList) {
		this.billPartyIdList = billPartyIdList;
	}
	/**
	 * Default constructor.
	 */
	public BillToParty() {
		super();
	}
	/**
	 * @return Returns the logisticsRole.
	 */
	public String getLogisticsRole() {
		return logisticsRole;
	}
	/**
	 * @param logisticsRole The logisticsRole to set.
	 */
	public void setLogisticsRole(String logisticsRole) {
		this.logisticsRole = logisticsRole;
	}
	
	
	
}
