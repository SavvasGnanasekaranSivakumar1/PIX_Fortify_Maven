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
 * Title		: 	LineQuantity.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   23 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

/**
 * LineQuantity is a data transfer object to store the 
 * Usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ItemLineQuantity implements java.io.Serializable {
	private static final long serialVersionUID = -5465338530426523108L;

	private String qtyType  = null;
	private String qtyTypeContext = null;
	private ItemLineQtyValue itemQtyValue = null;
	

	/**
	 * Default constructor.
	 */
	public ItemLineQuantity() {
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
	 * @return Returns the itemQtyValue.
	 */
	public ItemLineQtyValue getItemQtyValue() {
		return itemQtyValue;
	}



	/**
	 * @param itemQtyValue The itemQtyValue to set.
	 */
	public void setItemQtyValue(ItemLineQtyValue itemQtyValue) {
		this.itemQtyValue = itemQtyValue;
	}



	


		
		
}