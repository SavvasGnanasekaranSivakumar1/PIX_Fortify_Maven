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
 * Title		: 	OCHeader.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   3 November, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.ICProduct;
import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.ICQuantity;

/**
 * ICLineItem is a data transfer object to store the 
 * Inventory Change  details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ICLineItem implements java.io.Serializable {
	private static final long serialVersionUID = 1898582772783678819L;
	
	private String icLineItemNumber = null;
	private ICProduct icProduct 	= null;
	private ArrayList icLineRef 	= null;
	private ICQuantity icQty 		= null;
	/**
	 * Default constructor.
	 */
	public ICLineItem() {
		super();
	}
	/**
	 * @return the icLineItemNumber
	 */
	public String getIcLineItemNumber() {
		return icLineItemNumber;
	}
	/**
	 * @param icLineItemNumber the icLineItemNumber to set
	 */
	public void setIcLineItemNumber(String icLineItemNumber) {
		this.icLineItemNumber = icLineItemNumber;
	}
	/**
	 * @return the icProduct
	 */
	public ICProduct getIcProduct() {
		return icProduct;
	}
	/**
	 * @param icProduct the icProduct to set
	 */
	public void setIcProduct(ICProduct icProduct) {
		this.icProduct = icProduct;
	}
	/**
	 * @return the icLineRef
	 */
	public ArrayList getIcLineRef() {
		return icLineRef;
	}
	/**
	 * @param icLineRef the icLineRef to set
	 */
	public void setIcLineRef(ArrayList icLineRef) {
		this.icLineRef = icLineRef;
	}
	/**
	 * @return the icQty
	 */
	public ICQuantity getIcQty() {
		return icQty;
	}
	/**
	 * @param icQty the icQty to set
	 */
	public void setIcQty(ICQuantity icQty) {
		this.icQty = icQty;
	}
}
