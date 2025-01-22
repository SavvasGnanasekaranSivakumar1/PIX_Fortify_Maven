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
 * Title		: 	SIIssuedDate.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   13 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

import com.pearson.pixb2b.vendor.transaction.shared.dto.Date;

/**
 * SIIssuedDate is a helper data transfer object to store the 
 * Shipping Instruction Issued date details.
 * 
 * @author Ashish Agrawal
 */
public class SIIssuedDate implements java.io.Serializable {
	private static final long serialVersionUID = 6093577796775820557L;
	
	private Date date = null;
	/**
	 * Default constructor.
	 */
	public SIIssuedDate() {
		super();
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
