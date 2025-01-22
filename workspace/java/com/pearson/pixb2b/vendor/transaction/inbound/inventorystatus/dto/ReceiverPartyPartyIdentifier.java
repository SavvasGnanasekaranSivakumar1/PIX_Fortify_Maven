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
 * Title		: 	PartyIdentifier.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   8 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto;


/**
 * ReceiverPartyPartyIdentifier is a data transfer object to store the 
 * IS details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ReceiverPartyPartyIdentifier implements java.io.Serializable {
   private static final long serialVersionUID = -4424219027339621845L;
   
   private String recvPIType = null;
   private String recvPIValue = null;
   private String recvAgency = null;

	/**
	 * Default constructor.
	 */
	public ReceiverPartyPartyIdentifier() {
		super();
	}

	/**
	 * @return Returns the recvAgency.
	 */
	public String getRecvAgency() {
		return recvAgency;
	}

	/**
	 * @param recvAgency The recvAgency to set.
	 */
	public void setRecvAgency(String recvAgency) {
		this.recvAgency = recvAgency;
	}

	/**
	 * @return Returns the recvPIType.
	 */
	public String getRecvPIType() {
		return recvPIType;
	}

	/**
	 * @param recvPIType The recvPIType to set.
	 */
	public void setRecvPIType(String recvPIType) {
		this.recvPIType = recvPIType;
	}

	/**
	 * @return Returns the recvPIValue.
	 */
	public String getRecvPIValue() {
		return recvPIValue;
	}

	/**
	 * @param recvPIValue The recvPIValue to set.
	 */
	public void setRecvPIValue(String recvPIValue) {
		this.recvPIValue = recvPIValue;
	}

	
	
}