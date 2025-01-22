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
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto;


/**
 * ReceiverParty is a data transfer object to store the 
 * inventory status details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ReceiverParty implements java.io.Serializable {
	private static final long serialVersionUID = 4399874505476518975L;
	
	private ReceiverPartyPartyIdentifier recvPID = null;
    private ReceiverPartyNameAddress recvNameAddress =  null;
	private String recvPartyType = null;
	
	/**
	 * @return Returns the recvNameAddress.
	 */
	public ReceiverPartyNameAddress getRecvNameAddress() {
		return recvNameAddress;
	}

	/**
	 * @param recvNameAddress The recvNameAddress to set.
	 */
	public void setRecvNameAddress(ReceiverPartyNameAddress recvNameAddress) {
		this.recvNameAddress = recvNameAddress;
	}

	/**
	 * @return Returns the recvPartyType.
	 */
	public String getRecvPartyType() {
		return recvPartyType;
	}

	/**
	 * @param recvPartyType The recvPartyType to set.
	 */
	public void setRecvPartyType(String recvPartyType) {
		this.recvPartyType = recvPartyType;
	}

	/**
	 * @return Returns the recvPID.
	 */
	public ReceiverPartyPartyIdentifier getRecvPID() {
		return recvPID;
	}

	/**
	 * @param recvPID The recvPID to set.
	 */
	public void setRecvPID(ReceiverPartyPartyIdentifier recvPID) {
		this.recvPID = recvPID;
	}

	public ReceiverParty() {
		super();
	}

	

	
	
	
}