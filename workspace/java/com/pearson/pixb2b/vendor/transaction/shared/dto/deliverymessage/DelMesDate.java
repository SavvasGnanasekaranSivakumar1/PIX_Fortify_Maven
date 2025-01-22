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
 * Title		: 	DelMesDate.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		18 Jan, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * DelMesDate is a data transfer object to store the DeliveryMessage Date and Time details 
 * and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DelMesDate implements java.io.Serializable {
	private static final long serialVersionUID = 2024841896805479459L;
	
	private DMDate dmDate 	= null;
	private String time		= null;
	/**
	 * Default constructor.
	 */
	public DelMesDate() {
		super();
	}
	/**
	 * @return the dmDate
	 */
	public DMDate getDmDate() {
		return dmDate;
	}
	/**
	 * @param dmDate the dmDate to set
	 */
	public void setDmDate(DMDate dmDate) {
		this.dmDate = dmDate;
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