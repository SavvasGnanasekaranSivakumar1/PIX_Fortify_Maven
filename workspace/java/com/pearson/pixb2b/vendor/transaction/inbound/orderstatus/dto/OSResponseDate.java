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
 * Title		: 	OSResponseDate.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   29 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto;


/**
 * OSResponseDate is a data transfer object to store the 
 * Order Status Response Date details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class OSResponseDate implements java.io.Serializable {
	private static final long serialVersionUID = -3707354218311146566L;
	
	private OSDate osDate	= null;
	private String time		= null;
	/**
	 * Default constructor.
	 */
	public OSResponseDate() {
		super();
	}
	/**
	 * @return the osDate
	 */
	public OSDate getOsDate() {
		return osDate;
	}
	/**
	 * @param osDate the osDate to set
	 */
	public void setOsDate(OSDate osDate) {
		this.osDate = osDate;
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
