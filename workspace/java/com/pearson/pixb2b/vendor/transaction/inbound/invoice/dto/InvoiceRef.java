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
 * Title		: 	InvoiceReference.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   8 Feb, 2010	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto;

/**
 * InvoiceReference is a data transfer object to store the 
 * Invoice details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
 public class InvoiceRef implements java.io.Serializable {
	private static final long serialVersionUID = 733136954324424976L;

	private String assignedBy      = null;
	private String invRefType = null;	
	private String invRefValue = null;


	 /**
		 * Default constructor.
		 */
		public InvoiceRef() {
			super();
		}


	/**
	 * @return Returns the assignedBy.
	 */
	public String getAssignedBy() {
		return assignedBy;
	}


	/**
	 * @param assignedBy The assignedBy to set.
	 */
	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}


	/**
	 * @return Returns the invRefType.
	 */
	public String getInvRefType() {
		return invRefType;
	}


	/**
	 * @param invRefType The invRefType to set.
	 */
	public void setInvRefType(String invRefType) {
		this.invRefType = invRefType;
	}


	/**
	 * @return Returns the invRefValue.
	 */
	public String getInvRefValue() {
		return invRefValue;
	}


	/**
	 * @param invRefValue The invRefValue to set.
	 */
	public void setInvRefValue(String invRefValue) {
		this.invRefValue = invRefValue;
	}

	
}