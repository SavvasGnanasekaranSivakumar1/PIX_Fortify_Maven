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
 * Title		: 	TotInfoQty.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		15 Nov, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * TotInfoQty is a data transfer object to store the DeliveryMessage BookShipment
 * Summary Total Info Quantity details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class TotInfoQty implements java.io.Serializable {
	private static final long serialVersionUID = -690711804348199251L;
	
	private String qtyType  				= null;
	private TotInfoQtyValue totInfoQtyValue = null;
	/**
	 * Default constructor.
	 */
	public TotInfoQty() {
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
	 * @return the totInfoQtyValue
	 */
	public TotInfoQtyValue getTotInfoQtyValue() {
		return totInfoQtyValue;
	}
	/**
	 * @param totInfoQtyValue the totInfoQtyValue to set
	 */
	public void setTotInfoQtyValue(TotInfoQtyValue totInfoQtyValue) {
		this.totInfoQtyValue = totInfoQtyValue;
	}
}