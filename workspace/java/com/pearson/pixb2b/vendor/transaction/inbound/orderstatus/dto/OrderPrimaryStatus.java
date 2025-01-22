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
 * Title		: 	OrderPrimaryStatus.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   29 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto;

/**
 * OrderPrimaryStatus is a data transfer object to store the 
 * Order Primary Status detail and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class OrderPrimaryStatus implements java.io.Serializable {
	private static final long serialVersionUID = -6412924307543701108L;
	
	private String orderStatusCode = null;
	private String opStatusVal	   = null;
	/**
	 * Default constructor.
	 */
	public OrderPrimaryStatus() {
		super();
	}
	/**
	 * @return the orderStatusCode
	 */
	public String getOrderStatusCode() {
		return orderStatusCode;
	}
	/**
	 * @param orderStatusCode the orderStatusCode to set
	 */
	public void setOrderStatusCode(String orderStatusCode) {
		this.orderStatusCode = orderStatusCode;
	}
	/**
	 * @return the opStatusVal
	 */
	public String getOpStatusVal() {
		return opStatusVal;
	}
	/**
	 * @param opStatusVal the opStatusVal to set
	 */
	public void setOpStatusVal(String opStatusVal) {
		this.opStatusVal = opStatusVal;
	}
}
