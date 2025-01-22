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
 * Title		: 	ByProduct.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   13 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;
/**
 * ByProduct is a helper data transfer object to store the 
 * By Product details.
 * 
 * @author Ashish Agrawal
 */
public class ByProduct implements java.io.Serializable {
	private static final long serialVersionUID = 2494889675234755747L;
	
	private SIPOLineItem siPoLineItem = null;
	private SILineItemByProduct siLineItemByProduct = null;
	/**
	 * Default constructor.
	 */
	public ByProduct() {
		super();
	}
	/**
	 * @return the siPoLineItem
	 */
	public SIPOLineItem getSiPoLineItem() {
		return siPoLineItem;
	}
	/**
	 * @param siPoLineItem the siPoLineItem to set
	 */
	public void setSiPoLineItem(SIPOLineItem siPoLineItem) {
		this.siPoLineItem = siPoLineItem;
	}
	/**
	 * @return the siLineItemByProduct
	 */
	public SILineItemByProduct getSiLineItemByProduct() {
		return siLineItemByProduct;
	}
	/**
	 * @param siLineItemByProduct the siLineItemByProduct to set
	 */
	public void setSiLineItemByProduct(SILineItemByProduct siLineItemByProduct) {
		this.siLineItemByProduct = siLineItemByProduct;
	}
}
