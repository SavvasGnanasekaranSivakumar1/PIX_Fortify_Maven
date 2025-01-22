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
 * Title		: 	OrderStatusDTO.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   29 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.orderstatus.dto;

import java.util.ArrayList;

/**
 * OrderStatusDTO is a data transfer object to store the 
 * Order Status details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class OrderStatusDTO implements java.io.Serializable {
	private static final long serialVersionUID = -7896502021089435315L;
	
	private String orderStatusType	= null;
	private OSHeader osHeader		= null;
	private ArrayList osDetailList	= null;
	/**
	 * Default constructor.
	 */
	public OrderStatusDTO() {
		super();
		osDetailList = new ArrayList();
		osHeader = new OSHeader();
	}
	/**
	 * @return the orderStatusType
	 */
	public String getOrderStatusType() {
		return orderStatusType;
	}
	/**
	 * @param orderStatusType the orderStatusType to set
	 */
	public void setOrderStatusType(String orderStatusType) {
		this.orderStatusType = orderStatusType;
	}
	/**
	 * @return the osHeader
	 */
	public OSHeader getOsHeader() {
		return osHeader;
	}
	/**
	 * @param osHeader the osHeader to set
	 */
	public void setOsHeader(OSHeader osHeader) {
		this.osHeader = osHeader;
	}
	/**
	 * @return the osDetailList
	 */
	public ArrayList getOsDetailList() {
		return osDetailList;
	}
	/**
	 * @param osDetailList the osDetailList to set
	 */
	public void setOsDetailList(ArrayList osDetailList) {
		this.osDetailList = osDetailList;
	}
}
