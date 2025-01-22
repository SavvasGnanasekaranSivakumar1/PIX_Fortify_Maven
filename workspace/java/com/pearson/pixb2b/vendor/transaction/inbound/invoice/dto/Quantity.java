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
public class Quantity implements java.io.Serializable {	
	private static final long serialVersionUID = -28760792123206879L;
	
	private String qtyType  = null;
	private ValueQty valueQty = null;
	/**
	 * Default constructor.
	 */
	public Quantity() {
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
	 * @return Returns the valueQty.
	 */
	public ValueQty getValueQty() {
		return valueQty;
	}
	/**
	 * @param valueQty The valueQty to set.
	 */
	public void setValueQty(ValueQty valueQty) {
		this.valueQty = valueQty;
	}

	}