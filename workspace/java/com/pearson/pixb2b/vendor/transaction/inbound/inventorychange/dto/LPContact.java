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
 * Title		: 	LPContact.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   3 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto;


/**
 * LPContact is a data transfer object to store the 
 * Inventory Change details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class LPContact implements java.io.Serializable {
	private static final long serialVersionUID = -6416647424432324299L;
	
	private String contactType = null;
	private String contactFirstName = null;	
	private String contactLastName = null;	
	private String mobile = null;
	private String contactName = null;
	
	
	/**
	 * Default constructor.
	 */
	public LPContact() {
		super();
	}
	

	/**
	 * @return Returns the contactFirstName.
	 */
	public String getContactFirstName() {
		return contactFirstName;
	}


	/**
	 * @param contactFirstName The contactFirstName to set.
	 */
	public void setContactFirstName(String contactFirstName) {
		this.contactFirstName = contactFirstName;
	}


	/**
	 * @return Returns the contactLastName.
	 */
	public String getContactLastName() {
		return contactLastName;
	}


	/**
	 * @param contactLastName The contactLastName to set.
	 */
	public void setContactLastName(String contactLastName) {
		this.contactLastName = contactLastName;
	}


	/**
	 * @return Returns the contactType.
	 */
	public String getContactType() {
		return contactType;
	}


	/**
	 * @param contactType The contactType to set.
	 */
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}


	/**
	 * @return Returns the mobile.
	 */
	public String getMobile() {
		return mobile;
	}


	/**
	 * @param mobile The mobile to set.
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	/**
	 * @return Returns the contactName.
	 */
	public String getContactName() {
		return contactName;
	}


	/**
	 * @param contactName The contactName to set.
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	
}