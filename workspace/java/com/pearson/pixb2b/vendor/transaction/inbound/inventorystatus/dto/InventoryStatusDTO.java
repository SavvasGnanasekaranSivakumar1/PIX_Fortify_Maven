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
 * 1.0	Abhilasha Nigam   24 November, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto;

import java.util.ArrayList;

/**
 * InventoryStatusDTO is a data transfer object to store the 
 * Inventory Status details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class InventoryStatusDTO implements java.io.Serializable {	
	private static final long serialVersionUID = -8006654326464222413L;

	private ISHeader isHeader = null;
	private ArrayList isLineItem = null;
	private ISSummary isSummary = null;
	/**
	 * Default constructor.
	 */
	public InventoryStatusDTO() {
		super();
		isHeader = new ISHeader();
	}
	/**
	 * @return Returns the isHeader.
	 */
	public ISHeader getIsHeader() {
		return isHeader;
	}
	/**
	 * @param isHeader The isHeader to set.
	 */
	public void setIsHeader(ISHeader isHeader) {
		this.isHeader = isHeader;
	}
	
	/**
	 * @return Returns the isSummary.
	 */
	public ISSummary getIsSummary() {
		return isSummary;
	}
	/**
	 * @param isSummary The isSummary to set.
	 */
	public void setIsSummary(ISSummary isSummary) {
		this.isSummary = isSummary;
	}
	/**
	 * @return Returns the isLineItem.
	 */
	public ArrayList getIsLineItem() {
		return isLineItem;
	}
	/**
	 * @param isLineItem The isLineItem to set.
	 */
	public void setIsLineItem(ArrayList isLineItem) {
		this.isLineItem = isLineItem;
	}
	
	
		
	
}
