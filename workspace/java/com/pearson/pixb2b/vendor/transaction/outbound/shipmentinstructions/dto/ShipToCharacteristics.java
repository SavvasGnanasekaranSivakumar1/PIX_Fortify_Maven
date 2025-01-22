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
 * 1.0	Ashish Agrawal   13 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

/**
 * ShipToCharacteristics is a helper data transfer object to store the 
 * ShipTo Characteristics details.
 * 
 * @author Ashish Agrawal
 */
public class ShipToCharacteristics implements java.io.Serializable {
	private static final long serialVersionUID = -3804753799447321703L;
	
	private ShipToParty shipToParty = null;
	private TermsOfDelivery termsOfDelivery = null;
	/**
	 * @return the termsOfDelivery
	 */
	public TermsOfDelivery getTermsOfDelivery() {
		return termsOfDelivery;
	}
	/**
	 * @param termsOfDelivery the termsOfDelivery to set
	 */
	public void setTermsOfDelivery(TermsOfDelivery termsOfDelivery) {
		this.termsOfDelivery = termsOfDelivery;
	}
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
