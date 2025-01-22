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
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto;


/**
 * ISQtyValue is a data transfer object to store the 
 * Inventory Status details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ISQtyValue implements java.io.Serializable {
	private static final long serialVersionUID = -3391036047119167260L;
	
	private String qtyValue = null;
	private String UOM = null;
	
	/**
	 * Default constructor.
	 */
	public ISQtyValue() {
		super();
	}
	

	/**
	 * @return Returns the uOM.
	 */
	public String getUOM() {
		return UOM;
	}


	/**
	 * @param uom The uOM to set.
	 */
	public void setUOM(String uom) {
		UOM = uom;
	}



	/**
	 * @return Returns the qtyValue.
	 */
	public String getQtyValue() {
		return qtyValue;
	}



	/**
	 * @param qtyValue The qtyValue to set.
	 */
	public void setQtyValue(String qtyValue) {
		this.qtyValue = qtyValue;
	}

}