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
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto;

/**
 * ICLineReference is a data transfer object to store the 
 * Inventory Change details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
 public class ICLineReference implements java.io.Serializable {
	private static final long serialVersionUID = 5070145254090603432L;

	private String icLineRefType = null;	
	private String icLineRefValue = null;
	/**
	* Default constructor.
	*/
	public ICLineReference() {
		super();
	}
	/**
	 * @return the icLineRefType
	 */
	public String getIcLineRefType() {
		return icLineRefType;
	}
	/**
	 * @param icLineRefType the icLineRefType to set
	 */
	public void setIcLineRefType(String icLineRefType) {
		this.icLineRefType = icLineRefType;
	}
	/**
	 * @return the icLineRefValue
	 */
	public String getIcLineRefValue() {
		return icLineRefValue;
	}
	/**
	 * @param icLineRefValue the icLineRefValue to set
	 */
	public void setIcLineRefValue(String icLineRefValue) {
		this.icLineRefValue = icLineRefValue;
	}
}