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
 * Title		: 	OrderStatusInformation.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   29 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto;


/**
 * OrderStatusInformation is a data transfer object to store the 
 * Order Status Information and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class OrderStatusInformation implements java.io.Serializable {
	private static final long serialVersionUID = 6864644862175647057L;
	
	private OrderPrimaryStatus orderPrimaryStatus = null;
	/**
	 * Default constructor.
	 */
	public OrderStatusInformation() {
		super();
	}
	/**
	 * @return the orderPrimaryStatus
	 */
	public OrderPrimaryStatus getOrderPrimaryStatus() {
		return orderPrimaryStatus;
	}
	/**
	 * @param orderPrimaryStatus the orderPrimaryStatus to set
	 */
	public void setOrderPrimaryStatus(OrderPrimaryStatus orderPrimaryStatus) {
		this.orderPrimaryStatus = orderPrimaryStatus;
	}
}
