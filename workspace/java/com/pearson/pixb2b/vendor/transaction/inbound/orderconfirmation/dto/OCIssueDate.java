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
package com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto;




/**
 * OCIssueDate is a data transfer object to store the 
 * Order Confirmation details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
 public class OCIssueDate implements java.io.Serializable {
	private static final long serialVersionUID = -6049224614695924237L;
	
	private OCDate ocDate = null;
	private String time = null;	
	
	
	/**
	 * Default constructor.
	 */
	public OCIssueDate() {
		super();
	}

	
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}



	/**
	 * @return Returns the ocDate.
	 */
	public OCDate getOcDate() {
		return ocDate;
	}



	/**
	 * @param ocDate The ocDate to set.
	 */
	public void setOcDate(OCDate ocDate) {
		this.ocDate = ocDate;
	}



	



	
	
}