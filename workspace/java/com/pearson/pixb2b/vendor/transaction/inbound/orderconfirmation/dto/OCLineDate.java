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

import com.pearson.pixb2b.vendor.transaction.shared.dto.LineShipDate;
/**
 * OtherDate is a helper data transfer object to store the 
 * Date details for any type of transactions.
 * 
 * @author Abhilasha Nigam
 */
public class OCLineDate implements java.io.Serializable {
	private static final long serialVersionUID = 1983732451611184455L;
	
	private LineShipDate estDeliveryDate = null;
	private String dateType = null;
	/**
	 * Default constructor.
	 */
	public OCLineDate() {
		super();
	}
	/**
	 * @return the estDeliveryDate
	 */
	public LineShipDate getEstDeliveryDate() {
		return estDeliveryDate;
	}
	/**
	 * @param estDeliveryDate the estDeliveryDate to set
	 */
	public void setEstDeliveryDate(LineShipDate estDeliveryDate) {
		this.estDeliveryDate = estDeliveryDate;
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
}