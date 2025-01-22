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
 * Title		: 	PurchaseOrderDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   11 Aug, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;


/**
 * POInformationDup is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class POInformationDup implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String poNumber 		= null;
	/**
	 * Default constructor.
	 */
	public POInformationDup() {
		super();
	}
	/**
	 * @return Returns the poNumber.
	 */
	public String getPoNumber() {
		return poNumber;
	}
	/**
	 * @param poNumber The poNumber to set.
	 */
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
}