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
 * Title		: 	TotInfoQtyValue.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		15 Nov, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

/**
 * TotInfoQtyValue is a data transfer object to store the DeliveryMessage BookShipment
 * Summary Total Info Quantity Value details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class TotInfoQtyValue implements java.io.Serializable {
	private static final long serialVersionUID = -8575860078576170171L;
	
	private String qtyValue = null;
	private String uom 		= null;
	/**
	 * Default constructor.
	 */
	public TotInfoQtyValue() {
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