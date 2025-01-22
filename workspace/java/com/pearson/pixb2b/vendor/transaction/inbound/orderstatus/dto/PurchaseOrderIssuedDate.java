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
 * Title		: 	PurchaseOrderIssuedDate.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   29 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto;


/**
 * PurchaseOrderIssuedDate is a data transfer object to store the 
 * Purchase Order Issued Date details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class PurchaseOrderIssuedDate implements java.io.Serializable {
	private static final long serialVersionUID = 2269876445282506672L;
	
	private PODate poDate = null;
	/**
	 * Default constructor.
	 */
	public PurchaseOrderIssuedDate() {
		super();
	}
	/**
	 * @return the poDate
	 */
	public PODate getPoDate() {
		return poDate;
	}
	/**
	 * @param poDate the poDate to set
	 */
	public void setPoDate(PODate poDate) {
		this.poDate = poDate;
	}
}
