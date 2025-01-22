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
 * Title		: 	Quantity.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   9 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto;


/**
 * ISQuantity is a data transfer object to store the 
 * Inventory Status details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ISQuantity implements java.io.Serializable {
	private static final long serialVersionUID = -8237500511972861159L;
	
	private String qtyType  = null;
	private ISQtyValue isQtyValue = null;

	/**
	 * @return Returns the isQtyValue.
	 */
	public ISQtyValue getIsQtyValue() {
		return isQtyValue;
	}


	/**
	 * @param isQtyValue The isQtyValue to set.
	 */
	public void setIsQtyValue(ISQtyValue isQtyValue) {
		this.isQtyValue = isQtyValue;
	}


	/**
	 * Default constructor.
	 */
	public ISQuantity() {
		super();
	}

	
	/**
	 * @return Returns the qtyType.
	 */
	public String getQtyType() {
		return qtyType;
	}

	/**
	 * @param qtyType The qtyType to set.
	 */
	public void setQtyType(String qtyType) {
		this.qtyType = qtyType;
	}







	
	
	
}