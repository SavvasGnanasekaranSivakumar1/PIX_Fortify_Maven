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
 * Title		: 	TotalQuantity.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   12 Jan, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto;

/**
 * TotalQuantity is a data transfer object to store the 
 * Goods Receipt details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class TotalQuantity implements java.io.Serializable {
	private static final long serialVersionUID = -2877414685002567248L;

	private String totalQtyContext = null;	
	private String totalQtyType  = null;
	private ValueQty valueQty = null;
	/**
	 * @return Returns the totalQtyContext.
	 */
	public String getTotalQtyContext() {
		return totalQtyContext;
	}
	/**
	 * @param totalQtyContext The totalQtyContext to set.
	 */
	public void setTotalQtyContext(String totalQtyContext) {
		this.totalQtyContext = totalQtyContext;
	}
	/**
	 * @return Returns the totalQtyType.
	 */
	public String getTotalQtyType() {
		return totalQtyType;
	}
	/**
	 * @param totalQtyType The totalQtyType to set.
	 */
	public void setTotalQtyType(String totalQtyType) {
		this.totalQtyType = totalQtyType;
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
	/**
	 * Default constructor.
	 */
	public TotalQuantity() {
		super();
	}
	
	

	}