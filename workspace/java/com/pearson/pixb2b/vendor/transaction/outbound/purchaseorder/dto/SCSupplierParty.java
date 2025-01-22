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
 * Title		: 	LineSupplierParty.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   26 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;



/**
 * LineSupplierParty is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class SCSupplierParty implements java.io.Serializable {
	private static final long serialVersionUID = -8111314645982330976L;
	
	private SCPartyIdentifier scPartyIdentifier = null;
    private SCNameAddress scNameAddress =  null;
    private SCCommonContact scCommonContact = null;
    
	/**
	 * Default constructor.
	 */
	public SCSupplierParty() {
		super();
	}

	/**
	 * @return Returns the scNameAddress.
	 */
	public SCNameAddress getScNameAddress() {
		return scNameAddress;
	}

	/**
	 * @param scNameAddress The scNameAddress to set.
	 */
	public void setScNameAddress(SCNameAddress scNameAddress) {
		this.scNameAddress = scNameAddress;
	}

	/**
	 * @return Returns the scPartyIdentifier.
	 */
	public SCPartyIdentifier getScPartyIdentifier() {
		return scPartyIdentifier;
	}

	/**
	 * @param scPartyIdentifier The scPartyIdentifier to set.
	 */
	public void setScPartyIdentifier(SCPartyIdentifier scPartyIdentifier) {
		this.scPartyIdentifier = scPartyIdentifier;
	}

	/**
	 * @return Returns the scCommonContact.
	 */
	public SCCommonContact getScCommonContact() {
		return scCommonContact;
	}

	/**
	 * @param scCommonContact The scCommonContact to set.
	 */
	public void setScCommonContact(SCCommonContact scCommonContact) {
		this.scCommonContact = scCommonContact;
	}

	
	


	
	
		
}