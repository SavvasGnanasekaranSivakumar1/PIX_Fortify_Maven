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
 * Title		: 	PurchaseOrderDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   11 Aug, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;

import com.pearson.pixb2b.vendor.transaction.shared.dto.Date;


/**
 * PurchaseOrderDTO is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
 public class POIssueDate implements java.io.Serializable {
	private static final long serialVersionUID = 5204331017249748868L;

	private Date date = null;
	private String time = null;	
	
	
	/**
	 * Default constructor.
	 */
	public POIssueDate() {
		super();
	}

	
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}



	/**
	 * @return Returns the date.
	 */
	public Date getDate() {
		return date;
	}



	/**
	 * @param date The date to set.
	 */
	public void setDate(Date date) {
		this.date = date;
	}



	
	
}