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
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto;

/**
 * ISLineReference is a data transfer object to store the 
 * Inventory Change details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
 public class ISLineReference implements java.io.Serializable {
	private static final long serialVersionUID = 5070145254090603432L;

	private String isLineRefType = null;	
	private String isLineRefValue = null;
	/**
	* Default constructor.
	*/
	public ISLineReference() {
		super();
	}
	/**
	 * @return the isLineRefType
	 */
	public String getIsLineRefType() {
		return isLineRefType;
	}
	/**
	 * @param isLineRefType the isLineRefType to set
	 */
	public void setIsLineRefType(String isLineRefType) {
		this.isLineRefType = isLineRefType;
	}
	/**
	 * @return the isLineRefValue
	 */
	public String getIsLineRefValue() {
		return isLineRefValue;
	}
	/**
	 * @param isLineRefValue the isLineRefValue to set
	 */
	public void setIsLineRefValue(String isLineRefValue) {
		this.isLineRefValue = isLineRefValue;
	}	
}