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
 * Title		: 	ItemisedUsgLineItem.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   31 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

import java.util.ArrayList;

import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POInformation;
import com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto.POInformationDup;

/**
 * ItemisedUsgLineItem is a data transfer object to store the 
 * Usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ItemisedUsgLineItem implements java.io.Serializable {
	private static final long serialVersionUID = -5267393524946000998L;
	
	private ItemUsgProduct iusgProduct = null;
	private POInformationDup itemPoInfo = null;
	private ItemLineQuantity itemLineQty = null;
	ArrayList itemLineInfoQty = null;
	/**
	 * Default constructor.
	 */
	public ItemisedUsgLineItem() {
		super();
	}
	/**
	 * @return Returns the itemLineInfoQty.
	 */
	public ArrayList getItemLineInfoQty() {
		return itemLineInfoQty;
	}
	/**
	 * @param itemLineInfoQty The itemLineInfoQty to set.
	 */
	public void setItemLineInfoQty(ArrayList itemLineInfoQty) {
		this.itemLineInfoQty = itemLineInfoQty;
	}
	/**
	 * @return Returns the itemLineQty.
	 */
	public ItemLineQuantity getItemLineQty() {
		return itemLineQty;
	}
	/**
	 * @param itemLineQty The itemLineQty to set.
	 */
	public void setItemLineQty(ItemLineQuantity itemLineQty) {
		this.itemLineQty = itemLineQty;
	}
	/**
	 * @return Returns the itemPoInfo.
	 */
	public POInformationDup getItemPoInfo() {
		return itemPoInfo;
	}
	/**
	 * @param itemPoInfo The itemPoInfo to set.
	 */
	public void setItemPoInfo(POInformationDup itemPoInfo) {
		this.itemPoInfo = itemPoInfo;
	}
	/**
	 * @return Returns the iusgProduct.
	 */
	public ItemUsgProduct getIusgProduct() {
		return iusgProduct;
	}
	/**
	 * @param iusgProduct The iusgProduct to set.
	 */
	public void setIusgProduct(ItemUsgProduct iusgProduct) {
		this.iusgProduct = iusgProduct;
	}





	
	
}
