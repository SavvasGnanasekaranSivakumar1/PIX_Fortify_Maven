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
 * Title		: 	OtherPartyIdenDS.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ranu Sharma		21 Dec, 2011	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

import java.io.Serializable;

/**
 * @author Ranu.Sharma
 *
 */
public class OtherPartyIdenDS implements Serializable{
	
	private static final long serialVersionUID = 719503203607462809L;
	
	private String partyIdentifierType = null;
    private String partyIdentifierValue = null;

	/**
	 * Default constructor.
	 */
	public OtherPartyIdenDS() {
		super();
	}

	public String getPartyIdentifierType() {
		return partyIdentifierType;
	}

	public void setPartyIdentifierType(String partyIdentifierType) {
		this.partyIdentifierType = partyIdentifierType;
	}

	public String getPartyIdentifierValue() {
		return partyIdentifierValue;
	}

	public void setPartyIdentifierValue(String partyIdentifierValue) {
		this.partyIdentifierValue = partyIdentifierValue;
	}

}
