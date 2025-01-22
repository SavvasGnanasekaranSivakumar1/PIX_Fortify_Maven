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
 * Title		: 	DeliveryDateWindow.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   29 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto;


/**
 * DeliveryDateWindow is a data transfer object to store the 
 * Delivery Date Window detail and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DeliveryDateWindow implements java.io.Serializable {
	private static final long serialVersionUID = 2340521522378869429L;
	
	private String delDateType 						= null;	
	private AvailableToShipDate availableToShipDate = null;
	/**
	 * Default constructor.
	 */
	public DeliveryDateWindow() {
		super();
	}
	/**
	 * @return the delDateType
	 */
	public String getDelDateType() {
		return delDateType;
	}
	/**
	 * @param delDateType the delDateType to set
	 */
	public void setDelDateType(String delDateType) {
		this.delDateType = delDateType;
	}
	/**
	 * @return the availableToShipDate
	 */
	public AvailableToShipDate getAvailableToShipDate() {
		return availableToShipDate;
	}
	/**
	 * @param availableToShipDate the availableToShipDate to set
	 */
	public void setAvailableToShipDate(AvailableToShipDate availableToShipDate) {
		this.availableToShipDate = availableToShipDate;
	}
}
