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
 * Title		: 	DocumentDate.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   26 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.envelope;



/**
 * DocumentDate is a data transfer object to store the Document date
 * Information for Inbound and Outbound transactions.
 * 
 * @author Ashish Agrawal
 */
public class DocumentDate implements java.io.Serializable {
	private static final long serialVersionUID = -786589804670069955L;
	
	private DateEnvelope date	= null;
	private String time = null;		
	/**
	 * Default constructor.
	 */
	public DocumentDate() {
		super();
		date = new DateEnvelope();
	}
	/**
	 * @return the date
	 */
	public DateEnvelope getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(DateEnvelope date) {
		this.date = date;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
}
