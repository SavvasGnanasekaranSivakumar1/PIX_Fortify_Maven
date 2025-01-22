/**
 * Copyright 2011 by Pearson,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Pearson ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Pearson.
 *
 * Title		: 	LocationPartyPartyIdentifierDS.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ranu Sharma		20 Dec, 2011	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

import java.io.Serializable;

/**
 * LocationPartyPartyIdentifierDS is a data transfer object to store the 
 * IS details and transfer the same between classes.
 * @author Ranu.Sharma
 *
 */
public class LocationPartyPartyIdentifierDS implements Serializable{

	private static final long serialVersionUID = 8438307712880720527L;
	
	private String partyIdentifierType = null;
	private String partyIdentifierValue = null;
	
	/**
	 * @return the partyIdentifierType
	 */
	public String getPartyIdentifierType() {
		return partyIdentifierType;
	}
	/**
	 * @param partyIdentifierType the partyIdentifierType to set
	 */
	public void setPartyIdentifierType(String partyIdentifierType) {
		this.partyIdentifierType = partyIdentifierType;
	}
	/**
	 * @return the partyIdentifierValue
	 */
	public String getPartyIdentifierValue() {
		return partyIdentifierValue;
	}
	/**
	 * @param partyIdentifierValue the partyIdentifierValue to set
	 */
	public void setPartyIdentifierValue(String partyIdentifierValue) {
		this.partyIdentifierValue = partyIdentifierValue;
	}
}
