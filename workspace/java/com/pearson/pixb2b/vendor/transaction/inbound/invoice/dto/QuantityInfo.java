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
package com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto;


/**
 * QuantityInformation is a data transfer object to store the 
 * Invoice details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class QuantityInfo implements java.io.Serializable {
	private static final long serialVersionUID = -5640977877564739464L;

	private Quantity qty = null;
	
	/**
	 * @return Returns the qty.
	 */
	public Quantity getQty() {
		return qty;
	}

	/**
	 * @param qty The qty to set.
	 */
	public void setQty(Quantity qty) {
		this.qty = qty;
	}

	/**
	 * Default constructor.
	 */
	public QuantityInfo() {
		super();
	}
	

	}