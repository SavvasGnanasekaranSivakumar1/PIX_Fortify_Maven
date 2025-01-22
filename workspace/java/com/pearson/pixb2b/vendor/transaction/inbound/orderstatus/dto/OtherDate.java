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
package com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto;

import com.pearson.pixb2b.vendor.transaction.shared.dto.Date;

/**
 * OtherDate is a data transfer object to store the 
 * Shipment Requested Date detail and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class OtherDate implements java.io.Serializable {
	private static final long serialVersionUID = -5372843253308874896L;
	
	private String dateType	= null;
	private Date date		= null;
	/**
	 * Default constructor.
	 */
	public OtherDate() {
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
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}
