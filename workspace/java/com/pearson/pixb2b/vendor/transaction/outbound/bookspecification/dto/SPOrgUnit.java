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
 * Title		: 	SPOrgUnit.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date				Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   	20 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.bookspecification.dto;
/**
 * SPOrgUnit is a data transfer object to store the Sender Party
 * NameAddress OrganisationUnit details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class SPOrgUnit implements java.io.Serializable {
	private static final long serialVersionUID = -9077202589970914712L;
	
	private String orgUnitName 	= null;
	private String orgUnitCode 	= null;
	/**
	 * Default constructor.
	 */
	public SPOrgUnit() {
		super();
	}
	/**
	 * @return the orgUnitName
	 */
	public String getOrgUnitName() {
		return orgUnitName;
	}
	/**
	 * @param orgUnitName the orgUnitName to set
	 */
	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}
	/**
	 * @return the orgUnitCode
	 */
	public String getOrgUnitCode() {
		return orgUnitCode;
	}
	/**
	 * @param orgUnitCode the orgUnitCode to set
	 */
	public void setOrgUnitCode(String orgUnitCode) {
		this.orgUnitCode = orgUnitCode;
	}	
}