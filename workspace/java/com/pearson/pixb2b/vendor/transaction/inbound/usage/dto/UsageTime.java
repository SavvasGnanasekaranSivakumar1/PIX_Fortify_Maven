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
 * Title		: 	UsageTime.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam  23 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

/**
 * UsageTime is a data transfer object to store the 
 * Usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
 public class UsageTime implements java.io.Serializable {
	
	private static final long serialVersionUID = 1620143563206255964L;
	private UsageTimeDate usageTimeDate = null;
	
	
	
	/**
	 * Default constructor.
	 */
	public UsageTime() {
		super();
	}



	/**
	 * @return Returns the usageTimeDate.
	 */
	public UsageTimeDate getUsageTimeDate() {
		return usageTimeDate;
	}



	/**
	 * @param usageTimeDate The usageTimeDate to set.
	 */
	public void setUsageTimeDate(UsageTimeDate usageTimeDate) {
		this.usageTimeDate = usageTimeDate;
	}


	


}