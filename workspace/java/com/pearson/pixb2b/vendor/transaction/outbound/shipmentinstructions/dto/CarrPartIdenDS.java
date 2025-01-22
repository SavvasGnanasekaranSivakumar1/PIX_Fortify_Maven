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
 * Title		: 	CarrPartIdenDS.java
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
 *  CarPartyId is a data transfer object to store the DeliveryMessageBook Header 
 * Delivery Leg Carrier Party Identifier details and transfer the same between classes.
 * 
 * @author Ranu.Sharma
 */
public class CarrPartIdenDS implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7738532801366394754L;
	private String partyIdentifierType = null;
    private String partyIdentifierValue = null;

	/**
	 * Default constructor.
	 */
	public CarrPartIdenDS() {
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
