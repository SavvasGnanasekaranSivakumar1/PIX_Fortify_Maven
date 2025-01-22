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
 * Title		: 	SuppCompPartyIdentifier.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   26 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;


/**
 * SuppCompPartyIdentifier is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class SCPartyIdentifier implements java.io.Serializable {
	private static final long serialVersionUID = -2055637749218429888L;
	
	private String suppPartyIdentifierType = null;
    private String suppPartyIdentifierValue = null;
  

   	/**
	 * Default constructor.
	 */
	public SCPartyIdentifier() {
		super();
	}
	/**
 * @return Returns the suppPartyIdentifierType.
 */
public String getSuppPartyIdentifierType() {
	return suppPartyIdentifierType;
}


/**
 * @param suppPartyIdentifierType The suppPartyIdentifierType to set.
 */
public void setSuppPartyIdentifierType(String suppPartyIdentifierType) {
	this.suppPartyIdentifierType = suppPartyIdentifierType;
}


/**
 * @return Returns the suppPartyIdentifierValue.
 */
public String getSuppPartyIdentifierValue() {
	return suppPartyIdentifierValue;
}


/**
 * @param suppPartyIdentifierValue The suppPartyIdentifierValue to set.
 */
public void setSuppPartyIdentifierValue(String suppPartyIdentifierValue) {
	this.suppPartyIdentifierValue = suppPartyIdentifierValue;
}
	
}