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
 * ISReference is a data transfer object to store the 
 * Inventory Status details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
 public class ISReference implements java.io.Serializable {
	private static final long serialVersionUID = -4525574958510161335L;
	
	private String isReferenceType = null;	
	private String isReferenceValue = null;	
	/**
	 * Default constructor.
	 */
	public ISReference() {
		super();
	}
	/**
	 * @return the isReferenceType
	 */
	public String getIsReferenceType() {
		return isReferenceType;
	}
	/**
	 * @param isReferenceType the isReferenceType to set
	 */
	public void setIsReferenceType(String isReferenceType) {
		this.isReferenceType = isReferenceType;
	}
	/**
	 * @return the isReferenceValue
	 */
	public String getIsReferenceValue() {
		return isReferenceValue;
	}
	/**
	 * @param isReferenceValue the isReferenceValue to set
	 */
	public void setIsReferenceValue(String isReferenceValue) {
		this.isReferenceValue = isReferenceValue;
	}		
}