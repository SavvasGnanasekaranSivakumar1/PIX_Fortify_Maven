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
 * Title		: 	OCReference.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   6 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

/**
 * UsageLineReference is a data transfer object to store the 
 * Usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
 public class UsageLineRef implements java.io.Serializable {
	private static final long serialVersionUID = 5070145254090603432L;

	private String usLineRefType = null;	
	private String usLineRefValue = null;
	/**
	 * @return Returns the usLineRefType.
	 */
	public String getUsLineRefType() {
		return usLineRefType;
	}
	/**
	 * @param usLineRefType The usLineRefType to set.
	 */
	public void setUsLineRefType(String usLineRefType) {
		this.usLineRefType = usLineRefType;
	}
	/**
	 * @return Returns the usLineRefValue.
	 */
	public String getUsLineRefValue() {
		return usLineRefValue;
	}
	/**
	 * @param usLineRefValue The usLineRefValue to set.
	 */
	public void setUsLineRefValue(String usLineRefValue) {
		this.usLineRefValue = usLineRefValue;
	}
	/**
	* Default constructor.
	*/
	public UsageLineRef() {
		super();
	}
	
}