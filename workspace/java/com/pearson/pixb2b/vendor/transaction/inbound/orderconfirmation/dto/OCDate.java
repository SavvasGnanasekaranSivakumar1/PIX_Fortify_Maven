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
 * Title		: 	OCDate.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   4 Nov, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderconfirmation.dto;
/**
 * OCDate is a helper data transfer object to store the 
 * Date details for any type of transactions.
 * 
 * @author Abhilasha Nigam
 */
public class OCDate implements java.io.Serializable {
	private static final long serialVersionUID = 4575058727501370026L;
	
	private String year =  null;
	private String month = null;
	private String day =   null;	
	/**
	 * Default constructor.
	 */
	public OCDate() {
		super();
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}
	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}
}