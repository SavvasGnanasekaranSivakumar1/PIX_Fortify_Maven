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
 * Title		: 	DateOther.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   8 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto;
/**
 * DateOther is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class DateOther implements java.io.Serializable {
	private static final long serialVersionUID = -424344973967658909L;
	
	private String dateType = null;
	private String date = null;
        private ShipDate shipDate = null;
	/**
	 * Default constructor.
	 */
	public DateOther() {
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
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the shipDate
	 */
	public ShipDate getShipDate() {
		return shipDate;
	}
	/**
	 * @param shipDate the shipDate to set
	 */
	public void setShipDate(ShipDate shipDate) {
		this.shipDate = shipDate;
	}
}