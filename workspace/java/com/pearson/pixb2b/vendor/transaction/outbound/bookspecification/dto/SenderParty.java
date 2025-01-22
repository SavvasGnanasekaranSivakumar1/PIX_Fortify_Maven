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
 * Title		: 	SenderParty.java
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
 * SenderParty is a data transfer object to store the 
 * Sender Party details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SenderParty implements java.io.Serializable {
	private static final long serialVersionUID = 5986459590639419923L;
	
	private String partyType 			= null;
	private ArrayList senderPartyPIList	= null;
	private SenderPartyNA senderPartyNA	= null;
	private ArrayList senderPartyCCList = null;
	/**
	 * Default constructor.
	 */
	public SenderParty() {
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
	 * @return the senderPartyPIList
	 */
	public ArrayList getSenderPartyPIList() {
		return senderPartyPIList;
	}
	/**
	 * @param senderPartyPIList the senderPartyPIList to set
	 */
	public void setSenderPartyPIList(ArrayList senderPartyPIList) {
		this.senderPartyPIList = senderPartyPIList;
	}
	/**
	 * @return the senderPartyNA
	 */
	public SenderPartyNA getSenderPartyNA() {
		return senderPartyNA;
	}
	/**
	 * @param senderPartyNA the senderPartyNA to set
	 */
	public void setSenderPartyNA(SenderPartyNA senderPartyNA) {
		this.senderPartyNA = senderPartyNA;
	}
	/**
	 * @return the senderPartyCCList
	 */
	public ArrayList getSenderPartyCCList() {
		return senderPartyCCList;
	}
	/**
	 * @param senderPartyCCList the senderPartyCCList to set
	 */
	public void setSenderPartyCCList(ArrayList senderPartyCCList) {
		this.senderPartyCCList = senderPartyCCList;
	}
}
