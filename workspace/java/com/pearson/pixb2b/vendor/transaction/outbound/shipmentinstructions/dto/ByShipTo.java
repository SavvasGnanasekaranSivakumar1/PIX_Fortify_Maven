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
 * Title		: 	ByShipTo.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   13 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

import java.util.ArrayList;

/**
 * ByShipTo is a helper data transfer object to store the 
 * By ShipTo details.
 * 
 * @author Ashish Agrawal
 */
public class ByShipTo implements java.io.Serializable {
	private static final long serialVersionUID = -8361059479590388413L;
	
	private ArrayList deliveryDateWindow = null;
	/**
	 * Default constructor.
	 */
	public ByShipTo() {
		super();
	}
	/**
	 * @return the deliveryDateWindow
	 */
	public ArrayList getDeliveryDateWindow() {
		return deliveryDateWindow;
	}
	/**
	 * @param deliveryDateWindow the deliveryDateWindow to set
	 */
	public void setDeliveryDateWindow(ArrayList deliveryDateWindow) {
		this.deliveryDateWindow = deliveryDateWindow;
	}
}
