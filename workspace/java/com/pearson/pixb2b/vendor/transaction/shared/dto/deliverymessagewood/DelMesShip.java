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
 * Title		: 	DelMesShip.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		18 March, 2010	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessagewood;

import java.util.ArrayList;

/**
 * DelMesShip is a data transfer object to store the DeliveryMessage Shipment details 
 * and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DelMesShip implements java.io.Serializable {
	private static final long serialVersionUID = -7478480855051176252L;
	
	private ShipmentId shipmentId			= null;
	private ArrayList delMessProdGroupList 	= null;
	/**
	 * Default constructor.
	 */
	public DelMesShip() {
		super();
	}
	/**
	 * @return the shipmentId
	 */
	public ShipmentId getShipmentId() {
		return shipmentId;
	}
	/**
	 * @param shipmentId the shipmentId to set
	 */
	public void setShipmentId(ShipmentId shipmentId) {
		this.shipmentId = shipmentId;
	}
	/**
	 * @return the delMessProdGroupList
	 */
	public ArrayList getDelMessProdGroupList() {
		return delMessProdGroupList;
	}
	/**
	 * @param delMessProdGroupList the delMessProdGroupList to set
	 */
	public void setDelMessProdGroupList(ArrayList delMessProdGroupList) {
		this.delMessProdGroupList = delMessProdGroupList;
	}
}