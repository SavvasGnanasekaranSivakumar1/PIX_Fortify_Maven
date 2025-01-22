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
 * Title		: 	OtherDate.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   29 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto;



/**
 * InvoiceDate is a data transfer object to store the 
 * Shipment Requested Date detail and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class InvoiceDate implements java.io.Serializable {
	private static final long serialVersionUID = 3876124277415225938L;
	
	private String dateType	= null;
	private Date invDate	= null;
	/**
	 * @return Returns the invDate.
	 */
	public Date getInvDate() {
		return invDate;
	}
	/**
	 * @param invDate The invDate to set.
	 */
	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}
	/**
	 * Default constructor.
	 */
	public InvoiceDate() {
		super();
	}
	/**
	 * @return the dateType
	 */
	public String getDateType() {
		return dateType;
	}
	/**
	 * @param dateType the dateType to set
	 */
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	
}
