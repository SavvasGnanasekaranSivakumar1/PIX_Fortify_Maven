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

import com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto.TotalQuantity;

/**
 * ICSummary is a data transfer object to store the 
 * Inventory Change details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ICSummary implements java.io.Serializable {	
	private static final long serialVersionUID = -7883681354673690688L;
	
	private String totalNumberOfLineItems = null;
	private TotalQuantity totalQty = null;
	/**
	 * Default constructor.
	 */
	public ICSummary() {
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
