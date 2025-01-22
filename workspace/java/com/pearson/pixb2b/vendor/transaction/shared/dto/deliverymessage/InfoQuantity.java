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
 * Title		: 	InfoQuantity.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * InfoQuantity is a data transfer object to store the Delivery Shipment BookLineItem
 * Informational Quantity details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class InfoQuantity implements java.io.Serializable {
	private static final long serialVersionUID = 6643237189308185878L;
	
	private String qtyType  			= null;
	private InfoQtyValue infoQtyValue 	= null;
	/**
	 * Default constructor.
	 */
	public InfoQuantity() {
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
	 * @return the infoQtyValue
	 */
	public InfoQtyValue getInfoQtyValue() {
		return infoQtyValue;
	}
	/**
	 * @param infoQtyValue the infoQtyValue to set
	 */
	public void setInfoQtyValue(InfoQtyValue infoQtyValue) {
		this.infoQtyValue = infoQtyValue;
	}
}