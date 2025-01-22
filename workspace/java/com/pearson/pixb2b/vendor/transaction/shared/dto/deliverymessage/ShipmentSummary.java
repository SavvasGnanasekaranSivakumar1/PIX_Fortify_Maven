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
 * Title		: 	ShipmentSummary.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * ShipmentSummary is a data transfer object to store the DeliveryMessage BookShipment
 * Summary details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ShipmentSummary implements java.io.Serializable {
	private static final long serialVersionUID = -5359468557468539526L;
	
	private TotalQuantity totQuantity = null;
	/**
	 * Default constructor.
	 */
	public ShipmentSummary() {
		super();
	}
	/**
	 * @return the totQuantity
	 */
	public TotalQuantity getTotQuantity() {
		return totQuantity;
	}
	/**
	 * @param totQuantity the totQuantity to set
	 */
	public void setTotQuantity(TotalQuantity totQuantity) {
		this.totQuantity = totQuantity;
	}
}