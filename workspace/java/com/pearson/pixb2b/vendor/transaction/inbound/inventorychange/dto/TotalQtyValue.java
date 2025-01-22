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
 * Title		: 	SCQuantity.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   26 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto;


/**
 * TotalQtyValue is a data transfer object to store the 
 * Inventory Status details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class TotalQtyValue implements java.io.Serializable {
	private static final long serialVersionUID = 1467966846574907255L;
	
	private String totalValue = null;
	private String totalUOM = null;
	
	/**
	 * Default constructor.
	 */
	public TotalQtyValue() {
		super();
	}

	/**
	 * @return Returns the totalUOM.
	 */
	public String getTotalUOM() {
		return totalUOM;
	}

	/**
	 * @param totalUOM The totalUOM to set.
	 */
	public void setTotalUOM(String totalUOM) {
		this.totalUOM = totalUOM;
	}

	/**
	 * @return Returns the totalValue.
	 */
	public String getTotalValue() {
		return totalValue;
	}

	/**
	 * @param totalValue The totalValue to set.
	 */
	public void setTotalValue(String totalValue) {
		this.totalValue = totalValue;
	}

}