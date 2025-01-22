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
 * Title		: 	PurchaseOrderReference.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   29 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto;
/**
 * PurchaseOrderReference is a data transfer object to store the 
 * Purchase Order Reference details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PurchaseOrderReference implements java.io.Serializable {
	private static final long serialVersionUID = 2769357796552400924L;
	
	private String poReferenceType = null;
	private String poReferenceValue = null;	
	/**
	 * Default constructor.
	 */
	public PurchaseOrderReference() {
		super();
	}
	/**
	 * @return the poReferenceType
	 */
	public String getPoReferenceType() {
		return poReferenceType;
	}
	/**
	 * @param poReferenceType the poReferenceType to set
	 */
	public void setPoReferenceType(String poReferenceType) {
		this.poReferenceType = poReferenceType;
	}
	/**
	 * @return the poReferenceValue
	 */
	public String getPoReferenceValue() {
		return poReferenceValue;
	}
	/**
	 * @param poReferenceValue the poReferenceValue to set
	 */
	public void setPoReferenceValue(String poReferenceValue) {
		this.poReferenceValue = poReferenceValue;
	}
}
