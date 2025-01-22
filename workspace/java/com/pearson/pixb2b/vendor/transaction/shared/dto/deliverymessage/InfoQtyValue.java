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
 * Title		: 	InfoQtyValue.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		10 May, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * InfoQtyValue is a data transfer object to store the Delivery Shipment BookLineItem
 * Informational Quantity Value details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class InfoQtyValue implements java.io.Serializable {
	private static final long serialVersionUID = -6907142081977335107L;
	
	private String qtyValue = null;
	private String uom 		= null;
	/**
	 * Default constructor.
	 */
	public InfoQtyValue() {
		super();
	}
	/**
	 * @return the qtyValue
	 */
	public String getQtyValue() {
		return qtyValue;
	}
	/**
	 * @param qtyValue the qtyValue to set
	 */
	public void setQtyValue(String qtyValue) {
		this.qtyValue = qtyValue;
	}
	/**
	 * @return the uom
	 */
	public String getUom() {
		return uom;
	}
	/**
	 * @param uom the uom to set
	 */
	public void setUom(String uom) {
		this.uom = uom;
	}
}