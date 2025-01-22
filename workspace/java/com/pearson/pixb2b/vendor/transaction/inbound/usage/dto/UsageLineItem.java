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
 * Title		: 	UsageLineItem.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   23 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

import java.util.ArrayList;


/**
 * UsageLineItem is a data transfer object to store the 
 * Usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class UsageLineItem implements java.io.Serializable {
	private static final long serialVersionUID = 1898582772783678819L;
	
	private String usageLineItemNo = null;
	private ArrayList usageLineRef 	= null;
	private AggrUsageLineItem aggrLineItem = null;
	private ItemisedUsgLineItem itemzdLineItem = null;
	private ArrayList comments = null;
	
	
	
	/**
	 * Default constructor.
	 */
	public UsageLineItem() {
		super();
	}



	/**
	 * @return Returns the aggrLineItem.
	 */
	public AggrUsageLineItem getAggrLineItem() {
		return aggrLineItem;
	}



	/**
	 * @param aggrLineItem The aggrLineItem to set.
	 */
	public void setAggrLineItem(AggrUsageLineItem aggrLineItem) {
		this.aggrLineItem = aggrLineItem;
	}



	/**
	 * @return Returns the usageLineItemNo.
	 */
	public String getUsageLineItemNo() {
		return usageLineItemNo;
	}



	/**
	 * @param usageLineItemNo The usageLineItemNo to set.
	 */
	public void setUsageLineItemNo(String usageLineItemNo) {
		this.usageLineItemNo = usageLineItemNo;
	}



	/**
	 * @return Returns the usageLineRef.
	 */
	public ArrayList getUsageLineRef() {
		return usageLineRef;
	}



	/**
	 * @param usageLineRef The usageLineRef to set.
	 */
	public void setUsageLineRef(ArrayList usageLineRef) {
		this.usageLineRef = usageLineRef;
	}



	/**
	 * @return Returns the itemzdLineItem.
	 */
	public ItemisedUsgLineItem getItemzdLineItem() {
		return itemzdLineItem;
	}



	/**
	 * @param itemzdLineItem The itemzdLineItem to set.
	 */
	public void setItemzdLineItem(ItemisedUsgLineItem itemzdLineItem) {
		this.itemzdLineItem = itemzdLineItem;
	}



	/**
	 * @return Returns the comments.
	 */
	public ArrayList getComments() {
		return comments;
	}



	/**
	 * @param comments The comments to set.
	 */
	public void setComments(ArrayList comments) {
		this.comments = comments;
	}



	
	
}
