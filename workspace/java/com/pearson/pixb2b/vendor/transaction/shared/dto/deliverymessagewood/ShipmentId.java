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
 * Title		: 	ShipmentId.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		18 March, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessagewood;

/**
 * ShipmentId is a data transfer object to store the DeliveryMessage Shipment ID details 
 * and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class ShipmentId implements java.io.Serializable {
	private static final long serialVersionUID = -6944412794370432623L;
	
	private String shipmentIdType	= null;
	private String shipmentIdVal 	= null;
	/**
	 * Default constructor.
	 */
	public ShipmentId() {
		super();
	}
	/**
	 * @return the shipmentIdType
	 */
	public String getShipmentIdType() {
		return shipmentIdType;
	}
	/**
	 * @param shipmentIdType the shipmentIdType to set
	 */
	public void setShipmentIdType(String shipmentIdType) {
		this.shipmentIdType = shipmentIdType;
	}
	/**
	 * @return the shipmentIdVal
	 */
	public String getShipmentIdVal() {
		return shipmentIdVal;
	}
	/**
	 * @param shipmentIdVal the shipmentIdVal to set
	 */
	public void setShipmentIdVal(String shipmentIdVal) {
		this.shipmentIdVal = shipmentIdVal;
	}
}