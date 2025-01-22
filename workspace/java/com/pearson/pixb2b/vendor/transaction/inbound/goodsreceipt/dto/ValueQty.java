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
 * Title		: 	ValueQuantity.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   12 Jan, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto;


/**
 * Value is a data transfer object to store the 
 * Summary Quantity details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ValueQty implements java.io.Serializable {
	private static final long serialVersionUID = 862728867286140589L;
	
	private String qtyUOM = null;
	private String valValue = null;
	
	/**
	 * Default constructor.
	 */
	public ValueQty() {
		super();
	}
	
	/**
	 * @return Returns the qtyUOM.
	 */
	public String getQtyUOM() {
		return qtyUOM;
	}
	/**
	 * @param qtyUOM The qtyUOM to set.
	 */
	public void setQtyUOM(String qtyUOM) {
		this.qtyUOM = qtyUOM;
	}

	/**
	 * @return Returns the valValue.
	 */
	public String getValValue() {
		return valValue;
	}

	/**
	 * @param valValue The valValue to set.
	 */
	public void setValValue(String valValue) {
		this.valValue = valValue;
	}

	}