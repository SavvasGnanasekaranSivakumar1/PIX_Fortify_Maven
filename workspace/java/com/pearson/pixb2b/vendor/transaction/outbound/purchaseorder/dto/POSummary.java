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
 * Title		: 	POSummary.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   9 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;

import java.util.ArrayList;

/**
 * POSummary is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class POSummary implements java.io.Serializable {
	private static final long serialVersionUID = -7738381154922877291L;
	
	private String totalNumberOfLineItems 	= null;
	private TotalQuantity totalQty 			= null;
	private TotalAmount totalAmt 			= null;
	private ArrayList termsConditions = null;
	
	/**
	 * Default constructor.
	 */
	public POSummary() {
		super();
	}
	/**
	 * @return the totalNumberOfLineItems
	 */
	public String getTotalNumberOfLineItems() {
		return totalNumberOfLineItems;
	}
	/**
	 * @param totalNumberOfLineItems the totalNumberOfLineItems to set
	 */
	public void setTotalNumberOfLineItems(String totalNumberOfLineItems) {
		this.totalNumberOfLineItems = totalNumberOfLineItems;
	}
	/**
	 * @return the totalQty
	 */
	public TotalQuantity getTotalQty() {
		return totalQty;
	}
	/**
	 * @param totalQty the totalQty to set
	 */
	public void setTotalQty(TotalQuantity totalQty) {
		this.totalQty = totalQty;
	}
	/**
	 * @return the totalAmt
	 */
	public TotalAmount getTotalAmt() {
		return totalAmt;
	}
	/**
	 * @param totalAmt the totalAmt to set
	 */
	public void setTotalAmt(TotalAmount totalAmt) {
		this.totalAmt = totalAmt;
	}
	
	/**
	 * @return Returns the termsConditions.
	 */
	public ArrayList getTermsConditions() {
		return termsConditions;
	}
	/**
	 * @param termsConditions The termsConditions to set.
	 */
	public void setTermsConditions(ArrayList termsConditions) {
		this.termsConditions = termsConditions;
	}
	
}