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
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;


/**
 * Quantity is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class Quantity implements java.io.Serializable {
	private static final long serialVersionUID = -8630253340611044292L;
	
	private String qty_value = null;
	private String qty_UOM = null;

	/**
	 * Default constructor.
	 */
	public Quantity() {
		super();
	}

	public String getQty_UOM() {
		return qty_UOM;
	}

	public void setQty_UOM(String qty_UOM) {
		this.qty_UOM = qty_UOM;
	}

	public String getQty_value() {
		return qty_value;
	}

	public void setQty_value(String qty_value) {
		this.qty_value = qty_value;
	}
	
	
	
}