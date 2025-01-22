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
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;


/**
 * ItemLineInfoQty is a data transfer object to store the 
 * Usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ItemLineInfoQty implements java.io.Serializable {
	
	private static final long serialVersionUID = 8059194366403475293L;
	private String qtyType  = null;
	private String qtyTypeContext  = null;
	private ItemLineInfoQtyValue itemInfoQtyValue = null;

	

	/**
	 * Default constructor.
	 */
	public ItemLineInfoQty() {
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


	/**
	 * @return Returns the qtyTypeContext.
	 */
	public String getQtyTypeContext() {
		return qtyTypeContext;
	}


	/**
	 * @param qtyTypeContext The qtyTypeContext to set.
	 */
	public void setQtyTypeContext(String qtyTypeContext) {
		this.qtyTypeContext = qtyTypeContext;
	}



	/**
	 * @return Returns the itemInfoQtyValue.
	 */
	public ItemLineInfoQtyValue getItemInfoQtyValue() {
		return itemInfoQtyValue;
	}



	/**
	 * @param itemInfoQtyValue The itemInfoQtyValue to set.
	 */
	public void setItemInfoQtyValue(ItemLineInfoQtyValue itemInfoQtyValue) {
		this.itemInfoQtyValue = itemInfoQtyValue;
	}

	
		
}