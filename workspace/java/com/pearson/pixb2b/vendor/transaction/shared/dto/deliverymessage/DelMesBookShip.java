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
 * Title		: 	DelMesBookShip.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ashish Agrawal		19 Jan, 2009	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.shared.dto.deliverymessage;

import java.util.ArrayList;

/**
 * DelMesBookShip is a data transfer object to store the DeliveryMessage BookShipment
 * details and transfer the same between classes.
 * 
 * @author Ashish Agrawal
 */
public class DelMesBookShip implements java.io.Serializable {
	private static final long serialVersionUID = -3096579885618583190L;
	
	private String deliveryShipmentId			= null;
	private ArrayList delShipBookLineItemList 	= null;
	private ShipmentSummary shipSummary			= null;
	/**
	 * Default constructor.
	 */
	public DelMesBookShip() {
		super();
	}
	/**
	 * @return the deliveryShipmentId
	 */
	public String getDeliveryShipmentId() {
		return deliveryShipmentId;
	}
	/**
	 * @param deliveryShipmentId the deliveryShipmentId to set
	 */
	public void setDeliveryShipmentId(String deliveryShipmentId) {
		this.deliveryShipmentId = deliveryShipmentId;
	}
	/**
	 * @return the delShipBookLineItemList
	 */
	public ArrayList getDelShipBookLineItemList() {
		return delShipBookLineItemList;
	}
	/**
	 * @param delShipBookLineItemList the delShipBookLineItemList to set
	 */
	public void setDelShipBookLineItemList(ArrayList delShipBookLineItemList) {
		this.delShipBookLineItemList = delShipBookLineItemList;
	}
	/**
	 * @return the shipSummary
	 */
	public ShipmentSummary getShipSummary() {
		return shipSummary;
	}
	/**
	 * @param shipSummary the shipSummary to set
	 */
	public void setShipSummary(ShipmentSummary shipSummary) {
		this.shipSummary = shipSummary;
	}
}