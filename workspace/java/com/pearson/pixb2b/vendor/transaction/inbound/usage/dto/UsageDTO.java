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
 * Title		: 	UsageDTO.java
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
 * usageDTO is a data transfer object to store the 
 * Usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class UsageDTO implements java.io.Serializable {	
	private static final long serialVersionUID = 64191448118154093L;

	private String usgStatusType = null;
	private UsageHeader usageHeader = null;
	private ArrayList usageLineItem = null;
	private UsageSummary usageSummary = null;
	/**
	 * Default constructor.
	 */
	public UsageDTO() {
		super();
		usageHeader = new UsageHeader();
	}
	/**
	 * @return Returns the usageHeader.
	 */
	public UsageHeader getUsageHeader() {
		return usageHeader;
	}
	/**
	 * @param usageHeader The usageHeader to set.
	 */
	public void setUsageHeader(UsageHeader usageHeader) {
		this.usageHeader = usageHeader;
	}
	/**
	 * @return Returns the usageLineItem.
	 */
	public ArrayList getUsageLineItem() {
		return usageLineItem;
	}
	/**
	 * @param usageLineItem The usageLineItem to set.
	 */
	public void setUsageLineItem(ArrayList usageLineItem) {
		this.usageLineItem = usageLineItem;
	}
	/**
	 * @return Returns the usageSummary.
	 */
	public UsageSummary getUsageSummary() {
		return usageSummary;
	}
	/**
	 * @param usageSummary The usageSummary to set.
	 */
	public void setUsageSummary(UsageSummary usageSummary) {
		this.usageSummary = usageSummary;
	}
	/**
	 * @return Returns the usgStatusType.
	 */
	public String getUsgStatusType() {
		return usgStatusType;
	}
	/**
	 * @param usgStatusType The usgStatusType to set.
	 */
	public void setUsgStatusType(String usgStatusType) {
		this.usgStatusType = usgStatusType;
	}
	
	
}
