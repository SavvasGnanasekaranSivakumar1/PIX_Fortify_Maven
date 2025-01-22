/**
 * Copyright 2010 by Pearson,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Pearson ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Pearson.
 *
 * Title		: 	OSReference.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal   	10 June, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto;

/**
 * OSReference is a data transfer object to store the Order Status
 * Transaction Id details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
 public class OSReference implements java.io.Serializable {
	private static final long serialVersionUID = 8858345627027207384L;
	
	private String osReferenceType = null;
	private String assignedBy      = null;
	private String osReferenceValue= null;
	/**
	 * Default constructor.
	 */
	public OSReference() {
		super();
	}
	/**
	 * @return the osReferenceType
	 */
	public String getOsReferenceType() {
		return osReferenceType;
	}
	/**
	 * @param osReferenceType the osReferenceType to set
	 */
	public void setOsReferenceType(String osReferenceType) {
		this.osReferenceType = osReferenceType;
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
	 * @return the osReferenceValue
	 */
	public String getOsReferenceValue() {
		return osReferenceValue;
	}
	/**
	 * @param osReferenceValue the osReferenceValue to set
	 */
	public void setOsReferenceValue(String osReferenceValue) {
		this.osReferenceValue = osReferenceValue;
	}
}