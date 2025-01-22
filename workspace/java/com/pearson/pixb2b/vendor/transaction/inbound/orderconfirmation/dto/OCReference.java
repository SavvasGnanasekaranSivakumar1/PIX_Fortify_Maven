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
package com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto;




/**
 * OCReference is a data transfer object to store the 
 * Order Confirmation details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
 public class OCReference implements java.io.Serializable {
	private static final long serialVersionUID = 2753240930039379725L;
	
	 private String ocReferenceType = null;
	 private String assignedBy      = null;
	 private String ocReferenceValue = null;


	 /**
		 * Default constructor.
		 */
		public OCReference() {
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
	 * @return Returns the ocReferenceType.
	 */
	public String getOcReferenceType() {
		return ocReferenceType;
	}


	/**
	 * @param ocReferenceType The ocReferenceType to set.
	 */
	public void setOcReferenceType(String ocReferenceType) {
		this.ocReferenceType = ocReferenceType;
	}


	/**
	 * @return Returns the ocReferenceValue.
	 */
	public String getOcReferenceValue() {
		return ocReferenceValue;
	}


	/**
	 * @param ocReferenceValue The ocReferenceValue to set.
	 */
	public void setOcReferenceValue(String ocReferenceValue) {
		this.ocReferenceValue = ocReferenceValue;
	}
	
	
}