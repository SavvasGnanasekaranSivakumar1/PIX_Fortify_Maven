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
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto;


/**
 * ICQuantity is a data transfer object to store the 
 * Inventory Change details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ICQuantity implements java.io.Serializable {
	private static final long serialVersionUID = -6294124425035947304L;

	private String qtyType  = null;
	private ICQtyValue icQtyValue = null;

	

	/**
	 * Default constructor.
	 */
	public ICQuantity() {
		super();
	}



	/**
	 * @return Returns the icQtyValue.
	 */
	public ICQtyValue getIcQtyValue() {
		return icQtyValue;
	}



	/**
	 * @param icQtyValue The icQtyValue to set.
	 */
	public void setIcQtyValue(ICQtyValue icQtyValue) {
		this.icQtyValue = icQtyValue;
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