/**
 * Copyright 2011 by Pearson,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Pearson ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Pearson.
 *
 * Title		: 	DeliveryLegRef.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ranu Sharma		21 Dec, 2011	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

import java.io.Serializable;

/**
 * @author Ranu.Sharma
 *
 */
public class DeliveryLegRef implements Serializable{

	private static final long serialVersionUID = -4835919351397075327L;

	/**
	 * Default constructor.
	 */
	public DeliveryLegRef() {
		super();
	}
	
	private String deliveryLegRefType = null;
	private String deliveryLegRefValue = null;

	/**
	 * @return the deliveryLegRefType
	 */
	public String getDeliveryLegRefType() {
		return deliveryLegRefType;
	}
	/**
	 * @param deliveryLegRefType the deliveryLegRefType to set
	 */
	public void setDeliveryLegRefType(String deliveryLegRefType) {
		this.deliveryLegRefType = deliveryLegRefType;
	}
	/**
	 * @return the deliveryLegRefValue
	 */
	public String getDeliveryLegRefValue() {
		return deliveryLegRefValue;
	}
	/**
	 * @param deliveryLegRefValue the deliveryLegRefValue to set
	 */
	public void setDeliveryLegRefValue(String deliveryLegRefValue) {
		this.deliveryLegRefValue = deliveryLegRefValue;
	}
	
	
	
}
