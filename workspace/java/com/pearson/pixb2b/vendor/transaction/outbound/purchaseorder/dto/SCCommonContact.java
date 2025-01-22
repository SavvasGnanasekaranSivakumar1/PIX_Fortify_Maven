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
 * Title		: 	TermsOfPayment.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   8 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;


/**
 * TermsOfPayment is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class SCCommonContact implements java.io.Serializable {
	private static final long serialVersionUID = -8013862769940058841L;
	
	private String scContactType = null;
	private String scContactName = null;
	private String scPhone = null;
	private String scMobile = null;
	private String scFax = null;
	private String scEmail = null;

	/**
	 * Default constructor.
	 */
	public SCCommonContact() {
		super();
	}

	/**
	 * @return Returns the scContactName.
	 */
	public String getScContactName() {
		return scContactName;
	}

	/**
	 * @param scContactName The scContactName to set.
	 */
	public void setScContactName(String scContactName) {
		this.scContactName = scContactName;
	}

	/**
	 * @return Returns the scContactType.
	 */
	public String getScContactType() {
		return scContactType;
	}

	/**
	 * @param scContactType The scContactType to set.
	 */
	public void setScContactType(String scContactType) {
		this.scContactType = scContactType;
	}

	/**
	 * @return Returns the scEmail.
	 */
	public String getScEmail() {
		return scEmail;
	}

	/**
	 * @param scEmail The scEmail to set.
	 */
	public void setScEmail(String scEmail) {
		this.scEmail = scEmail;
	}

	/**
	 * @return Returns the scFax.
	 */
	public String getScFax() {
		return scFax;
	}

	/**
	 * @param scFax The scFax to set.
	 */
	public void setScFax(String scFax) {
		this.scFax = scFax;
	}

	/**
	 * @return Returns the scMobile.
	 */
	public String getScMobile() {
		return scMobile;
	}

	/**
	 * @param scMobile The scMobile to set.
	 */
	public void setScMobile(String scMobile) {
		this.scMobile = scMobile;
	}

	/**
	 * @return Returns the scPhone.
	 */
	public String getScPhone() {
		return scPhone;
	}

	/**
	 * @param scPhone The scPhone to set.
	 */
	public void setScPhone(String scPhone) {
		this.scPhone = scPhone;
	}
	
	
}