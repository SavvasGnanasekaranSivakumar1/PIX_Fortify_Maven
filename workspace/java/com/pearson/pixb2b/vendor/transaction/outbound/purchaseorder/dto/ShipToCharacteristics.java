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
 * Title		: 	ShipToCharacteristics.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   8 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;

/**
 * ShipToCharacteristics is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ShipToCharacteristics implements java.io.Serializable {
	private static final long serialVersionUID = 6960497403217265947L;
	
	private ShipToParty shipToParty = null;
	/**
	 * Default constructor.
	 */
	public ShipToCharacteristics() {
		super();
	}
	/**
	 * @return the shipToParty
	 */
	public ShipToParty getShipToParty() {
		return shipToParty;
	}
	/**
	 * @param shipToParty the shipToParty to set
	 */
	public void setShipToParty(ShipToParty shipToParty) {
		this.shipToParty = shipToParty;
	}
}