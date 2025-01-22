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
 * 1.0	Ashish Agrawal   13 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

/**
 * DeliveryDateWindow is a helper data transfer object to store the 
 * Delivery Date Window details.
 * 
 * @author Ashish Agrawal
 */
public class DeliveryDateWindow implements java.io.Serializable {
	private static final long serialVersionUID = -6173321854407303963L;
	
	private String deliveryDateType = null;
	/**
	 * Default constructor.
	 */
	public DeliveryDateWindow() {
		super();
	}
	/**
	 * @return the deliveryDateType
	 */
	public String getDeliveryDateType() {
		return deliveryDateType;
	}
	/**
	 * @param deliveryDateType the deliveryDateType to set
	 */
	public void setDeliveryDateType(String deliveryDateType) {
		this.deliveryDateType = deliveryDateType;
	}
}
