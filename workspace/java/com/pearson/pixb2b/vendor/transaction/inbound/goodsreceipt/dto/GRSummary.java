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
 * Title		: 	GRSummary.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   11 Jan, 2010    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto;

/**
 * GRSummary is a data transfer object to store the 
 * GRSummary details  between classes.
 * 
 * @author Abhilasha Nigam
 */
public class GRSummary implements java.io.Serializable {	
	private static final long serialVersionUID = -7883681354673690688L;
	
	private String totalNumberOfLineItems = null;
	private TotalQuantity totalQty = null;
	
	
	/**
	 * Default constructor.
	 */
	public GRSummary() {
		super();
	}
	/**
	 * @return Returns the totalNumberOfLineItems.
	 */
	public String getTotalNumberOfLineItems() {
		return totalNumberOfLineItems;
	}
	/**
	 * @param totalNumberOfLineItems The totalNumberOfLineItems to set.
	 */
	public void setTotalNumberOfLineItems(String totalNumberOfLineItems) {
		this.totalNumberOfLineItems = totalNumberOfLineItems;
	}
	/**
	 * @return Returns the totalQty.
	 */
	public TotalQuantity getTotalQty() {
		return totalQty;
	}
	/**
	 * @param totalQty The totalQty to set.
	 */
	public void setTotalQty(TotalQuantity totalQty) {
		this.totalQty = totalQty;
	}
		
}
