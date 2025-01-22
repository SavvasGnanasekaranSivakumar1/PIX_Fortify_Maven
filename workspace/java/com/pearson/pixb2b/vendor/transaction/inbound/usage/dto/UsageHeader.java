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
 * Title		: 	UsageHeader.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   23 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

import java.util.ArrayList;
/**
 * UsageHeader is a data transfer object to store the 
 * Usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam, 
 */
public class UsageHeader implements java.io.Serializable {
	private static final long serialVersionUID = -4272912786498650522L;	
	
    private String usageNumber 	= null;
    private ArrayList usageRef 		= null;
    private UsageIssueDate usageDate 		= null;
    private UsageTime   usageTime   = null;
    private UsageBuyerParty usageBuyerParty = null;    
    private UsageEndUser usageEndUser = null;
   
	/**
	 * Default constructor.
	 */
	public UsageHeader() {
		super();
		usageEndUser = new UsageEndUser();
		usageRef = new ArrayList();
	}

	/**
	 * @return Returns the usageBuyerParty.
	 */
	public UsageBuyerParty getUsageBuyerParty() {
		return usageBuyerParty;
	}

	/**
	 * @param usageBuyerParty The usageBuyerParty to set.
	 */
	public void setUsageBuyerParty(UsageBuyerParty usageBuyerParty) {
		this.usageBuyerParty = usageBuyerParty;
	}

	/**
	 * @return Returns the usageDate.
	 */
	public UsageIssueDate getUsageDate() {
		return usageDate;
	}

	/**
	 * @param usageDate The usageDate to set.
	 */
	public void setUsageDate(UsageIssueDate usageDate) {
		this.usageDate = usageDate;
	}

	/**
	 * @return Returns the usageEndUser.
	 */
	public UsageEndUser getUsageEndUser() {
		return usageEndUser;
	}

	/**
	 * @param usageEndUser The usageEndUser to set.
	 */
	public void setUsageEndUser(UsageEndUser usageEndUser) {
		this.usageEndUser = usageEndUser;
	}

	/**
	 * @return Returns the usageNumber.
	 */
	public String getUsageNumber() {
		return usageNumber;
	}

	/**
	 * @param usageNumber The usageNumber to set.
	 */
	public void setUsageNumber(String usageNumber) {
		this.usageNumber = usageNumber;
	}

	/**
	 * @return Returns the usageRef.
	 */
	public ArrayList getUsageRef() {
		return usageRef;
	}

	/**
	 * @param usageRef The usageRef to set.
	 */
	public void setUsageRef(ArrayList usageRef) {
		this.usageRef = usageRef;
	}

	/**
	 * @return Returns the usageTime.
	 */
	public UsageTime getUsageTime() {
		return usageTime;
	}

	/**
	 * @param usageTime The usageTime to set.
	 */
	public void setUsageTime(UsageTime usageTime) {
		this.usageTime = usageTime;
	}
	
}
