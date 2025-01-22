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
 * Title		: 	POReference.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   11 Aug, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;
/**
 * POReference is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class POReference implements java.io.Serializable{
	private static final long serialVersionUID = -2171661382293050603L;
	
	private String assignedBy = null;
	private String poReferenceType = null;
	private String poReferenceValue = null;
	/**
	 * Default constructor.
	 */
	public POReference() {
		super();
	}
	/**
	 * @return the assignedBy
	 */
	public String getAssignedBy() {
		return assignedBy;
	}
	/**
	 * @param assignedBy the assignedBy to set
	 */
	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
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