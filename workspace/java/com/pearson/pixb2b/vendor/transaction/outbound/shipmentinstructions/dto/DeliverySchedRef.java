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
 * Title		: 	DeliverySchedRef.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ranu Sharma		20 Dec, 2011	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

import java.io.Serializable;

/**
 * @author Ranu.Sharma
 *
 */
public class DeliverySchedRef implements Serializable{
	
	private static final long serialVersionUID = 2249338238677754772L;
	
	private String deliverySchedType = null;
    private String deliverySchedValue = null;
    
    
	/**
	 * @return the deliverySchedValue
	 */
	public String getDeliverySchedValue() {
		return deliverySchedValue;
	}
	/**
	 * @param deliverySchedValue the deliverySchedValue to set
	 */
	public void setDeliverySchedValue(String deliverySchedValue) {
		this.deliverySchedValue = deliverySchedValue;
	}
	/**
	 * @return the deliverySchedType
	 */
	public String getDeliverySchedType() {
		return deliverySchedType;
	}
	/**
	 * @param deliverySchedType the deliverySchedType to set
	 */
	public void setDeliverySchedType(String deliverySchedType) {
		this.deliverySchedType = deliverySchedType;
	}
}
