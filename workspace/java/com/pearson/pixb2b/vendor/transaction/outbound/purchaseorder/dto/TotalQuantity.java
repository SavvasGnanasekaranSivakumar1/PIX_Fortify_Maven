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
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;


/**
 * TotalQuantity is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class TotalQuantity implements java.io.Serializable {
	private static final long serialVersionUID = 2686500799909552444L;
	
	private String qtyValue = null;
	private String qtyUOM = null;
	private String qtyType  = null;
	private ValueQty valueQty = null;
	/**
	 * Default constructor.
	 */
	public TotalQuantity() {
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