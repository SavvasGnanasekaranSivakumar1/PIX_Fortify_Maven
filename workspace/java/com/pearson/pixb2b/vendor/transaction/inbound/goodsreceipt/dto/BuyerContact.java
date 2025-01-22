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
 * Title		: 	BuyerContact.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   13 Jan 2010	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto;


/**
 * BuyerContact is a data transfer object to store the 
 * goodsreceipt details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class BuyerContact implements java.io.Serializable {
	private static final long serialVersionUID = -3578801851096708516L;

	private String contactId = null;
	private String contactType = null;
	private String contactFirstName = null;	
	private String contactLastName = null;	
	private String mobile = null;
	private String contactName = null;
	private String phone = null;
	private String fax = null;
	private String email = null;
	
	
	/**
	 * Default constructor.
	 */
	public BuyerContact() {
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


	/**
	 * @return Returns the contactId.
	 */
	public String getContactId() {
		return contactId;
	}


	/**
	 * @param contactId The contactId to set.
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}


	/**
	 * @return Returns the fax.
	 */
	public String getFax() {
		return fax;
	}


	/**
	 * @param fax The fax to set.
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}


	/**
	 * @return Returns the phone.
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * @param phone The phone to set.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
	 * @return Returns the email.
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}