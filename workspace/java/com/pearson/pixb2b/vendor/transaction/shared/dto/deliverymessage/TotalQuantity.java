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
 * Title		: 	TotalQuantity.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * TotalQuantity is a data transfer object to store the DeliveryMessage BookShipment
 * Summary Total Quantity details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class TotalQuantity implements java.io.Serializable {
	private static final long serialVersionUID = -947812047826975173L;
	
	private String qtyType  		= null;
	private TotQtyValue totQtyValue = null;
	/**
	 * Default constructor.
	 */
	public TotalQuantity() {
		super();
	}
	/**
	 * @return the qtyType
	 */
	public String getQtyType() {
		return qtyType;
	}
	/**
	 * @param qtyType the qtyType to set
	 */
	public void setQtyType(String qtyType) {
		this.qtyType = qtyType;
	}
	/**
	 * @return the totQtyValue
	 */
	public TotQtyValue getTotQtyValue() {
		return totQtyValue;
	}
	/**
	 * @param totQtyValue the totQtyValue to set
	 */
	public void setTotQtyValue(TotQtyValue totQtyValue) {
		this.totQtyValue = totQtyValue;
	}
}