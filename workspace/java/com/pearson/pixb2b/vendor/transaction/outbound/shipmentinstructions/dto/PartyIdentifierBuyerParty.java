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
 * Title		: 	PartyIdentifierBuyerParty.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   13 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

/**
 * PartyIdentifierBuyerParty is a helper data transfer object to store the 
 * Party Identifier BuyerParty details.
 * 
 * @author Ashish Agrawal
 */
public class PartyIdentifierBuyerParty implements java.io.Serializable {
	private static final long serialVersionUID = -772117146920541907L;
	
	private String piType = null;
	private String piValue = null;
	/**
	 * Default constructor.
	 */
	public PartyIdentifierBuyerParty() {
		super();
	}
	/**
	 * @return the piType
	 */
	public String getPiType() {
		return piType;
	}
	/**
	 * @param piType the piType to set
	 */
	public void setPiType(String piType) {
		this.piType = piType;
	}
	/**
	 * @return the piValue
	 */
	public String getPiValue() {
		return piValue;
	}
	/**
	 * @param piValue the piValue to set
	 */
	public void setPiValue(String piValue) {
		this.piValue = piValue;
	}
}
