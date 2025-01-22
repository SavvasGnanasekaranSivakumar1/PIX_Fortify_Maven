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
 * Title		: 	ReceiverParty.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   20 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;

import java.util.ArrayList;

/**
 * ReceiverParty is a data transfer object to store the 
 * Receiver Party details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ReceiverParty implements java.io.Serializable {
	private static final long serialVersionUID = -3960093868629229650L;
	
	private String partyType 				= null;
	private ArrayList receiverPartyPIList	= null;
	private ReceiverPartyNA receiverPartyNA	= null;
	private ArrayList receiverPartyCCList	= null;
	/**
	 * Default constructor.
	 */
	public ReceiverParty() {
		super();
	}
	/**
	 * @return the partyType
	 */
	public String getPartyType() {
		return partyType;
	}
	/**
	 * @param partyType the partyType to set
	 */
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}
	/**
	 * @return the receiverPartyPIList
	 */
	public ArrayList getReceiverPartyPIList() {
		return receiverPartyPIList;
	}
	/**
	 * @param receiverPartyPIList the receiverPartyPIList to set
	 */
	public void setReceiverPartyPIList(ArrayList receiverPartyPIList) {
		this.receiverPartyPIList = receiverPartyPIList;
	}
	/**
	 * @return the receiverPartyNA
	 */
	public ReceiverPartyNA getReceiverPartyNA() {
		return receiverPartyNA;
	}
	/**
	 * @param receiverPartyNA the receiverPartyNA to set
	 */
	public void setReceiverPartyNA(ReceiverPartyNA receiverPartyNA) {
		this.receiverPartyNA = receiverPartyNA;
	}
	/**
	 * @return the receiverPartyCCList
	 */
	public ArrayList getReceiverPartyCCList() {
		return receiverPartyCCList;
	}
	/**
	 * @param receiverPartyCCList the receiverPartyCCList to set
	 */
	public void setReceiverPartyCCList(ArrayList receiverPartyCCList) {
		this.receiverPartyCCList = receiverPartyCCList;
	}
}
