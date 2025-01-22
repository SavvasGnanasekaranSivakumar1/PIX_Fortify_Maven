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
 * ICReference is a data transfer object to store the 
 * Inventory Change details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
 public class ICReference implements java.io.Serializable {
	private static final long serialVersionUID = -4525574958510161335L;
	
	private String icReferenceType = null;	
	private String icReferenceValue = null;


	/**
	* Default constructor.
	*/
		public ICReference() {
			super();
		}


	/**
	 * @return Returns the icReferenceType.
	 */
	public String getIcReferenceType() {
		return icReferenceType;
	}


	/**
	 * @param icReferenceType The icReferenceType to set.
	 */
	public void setIcReferenceType(String icReferenceType) {
		this.icReferenceType = icReferenceType;
	}


	/**
	 * @return Returns the icReferenceValue.
	 */
	public String getIcReferenceValue() {
		return icReferenceValue;
	}


	/**
	 * @param icReferenceValue The icReferenceValue to set.
	 */
	public void setIcReferenceValue(String icReferenceValue) {
		this.icReferenceValue = icReferenceValue;
	}
	
}