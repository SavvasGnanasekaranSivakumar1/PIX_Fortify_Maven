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
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto;


/**
 * ICOrganisationUnit is a data transfer object to store the 
 * Inventory Change details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ICOrganisationUnit implements java.io.Serializable {
	private static final long serialVersionUID = -3602090644141419917L;
	
	private String orgUnitType = null;
	private String orgUnitName = null;
	private String orgUnitCode = null;
	
	/**
	 * Default constructor.
	 */
	public ICOrganisationUnit() {
		super();
	}

	/**
	 * @return Returns the orgUnitCode.
	 */
	public String getOrgUnitCode() {
		return orgUnitCode;
	}

	/**
	 * @param orgUnitCode The orgUnitCode to set.
	 */
	public void setOrgUnitCode(String orgUnitCode) {
		this.orgUnitCode = orgUnitCode;
	}

	/**
	 * @return Returns the orgUnitName.
	 */
	public String getOrgUnitName() {
		return orgUnitName;
	}

	/**
	 * @param orgUnitName The orgUnitName to set.
	 */
	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}

	/**
	 * @return Returns the orgUnitType.
	 */
	public String getOrgUnitType() {
		return orgUnitType;
	}

	/**
	 * @param orgUnitType The orgUnitType to set.
	 */
	public void setOrgUnitType(String orgUnitType) {
		this.orgUnitType = orgUnitType;
	}
	

	
	
}