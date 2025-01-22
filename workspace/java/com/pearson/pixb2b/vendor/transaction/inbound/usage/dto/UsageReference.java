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
 * Title		: 	UsageReference.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   23 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

/**
 * UsageReference is a data transfer object to store the 
 * Usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
 public class UsageReference implements java.io.Serializable {	
	private static final long serialVersionUID = 4155590592938569306L;
	
	private String usageRefType = null;	
	private String usageRefValue = null;


	/**
	* Default constructor.
	*/
		public UsageReference() {
			super();
		}


	/**
	 * @return Returns the usageRefType.
	 */
	public String getUsageRefType() {
		return usageRefType;
	}


	/**
	 * @param usageRefType The usageRefType to set.
	 */
	public void setUsageRefType(String usageRefType) {
		this.usageRefType = usageRefType;
	}


	/**
	 * @return Returns the usageRefValue.
	 */
	public String getUsageRefValue() {
		return usageRefValue;
	}


	/**
	 * @param usageRefValue The usageRefValue to set.
	 */
	public void setUsageRefValue(String usageRefValue) {
		this.usageRefValue = usageRefValue;
	}
	
}