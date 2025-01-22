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
 * Title		: 	InventoryChangeDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   24 November, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto;

import java.util.ArrayList;

/**
 * InventoryChangeDTO is a data transfer object to store the 
 * Inventory Chnage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class InventoryChangeDTO implements java.io.Serializable {	
	private static final long serialVersionUID = -7170695455224588266L;

	private ICHeader icHeader = null;
	private ArrayList icLineItem = null;
	private ICSummary icSummary = null;
	/**
	 * Default constructor.
	 */
	public InventoryChangeDTO() {
		super();
		icHeader = new ICHeader();
	}
	/**
	 * @return Returns the icHeader.
	 */
	public ICHeader getIcHeader() {
		return icHeader;
	}
	/**
	 * @param icHeader The icHeader to set.
	 */
	public void setIcHeader(ICHeader icHeader) {
		this.icHeader = icHeader;
	}
	/**
	 * @return Returns the icLineItem.
	 */
	public ArrayList getIcLineItem() {
		return icLineItem;
	}
	/**
	 * @param icLineItem The icLineItem to set.
	 */
	public void setIcLineItem(ArrayList icLineItem) {
		this.icLineItem = icLineItem;
	}
	/**
	 * @return Returns the icSummary.
	 */
	public ICSummary getIcSummary() {
		return icSummary;
	}
	/**
	 * @param icSummary The icSummary to set.
	 */
	public void setIcSummary(ICSummary icSummary) {
		this.icSummary = icSummary;
	}
	
}
