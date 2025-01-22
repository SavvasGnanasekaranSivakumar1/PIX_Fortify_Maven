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
 * Title		: 	ShipToInformation.java
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
 * ShipToInformation is a helper data transfer object to store the 
 * ShipTo Information details.
 * 
 * @author Ashish Agrawal
 */
public class ShipToInformation implements java.io.Serializable {
	private static final long serialVersionUID = 204623790214941185L;
	
	private ShipToCharacteristics shipToCharacteristics = null;
	private ArrayList deliverySchedule = null;
	/**
	 * Default constructor.
	 */
	public ShipToInformation() {
		super();
	}
	/**
	 * @return the shipToCharacteristics
	 */
	public ShipToCharacteristics getShipToCharacteristics() {
		return shipToCharacteristics;
	}
	/**
	 * @param shipToCharacteristics the shipToCharacteristics to set
	 */
	public void setShipToCharacteristics(ShipToCharacteristics shipToCharacteristics) {
		this.shipToCharacteristics = shipToCharacteristics;
	}
	/**
	 * @return the deliverySchedule
	 */
	public ArrayList getDeliverySchedule() {
		return deliverySchedule;
	}
	/**
	 * @param deliverySchedule the deliverySchedule to set
	 */
	public void setDeliverySchedule(ArrayList deliverySchedule) {
		this.deliverySchedule = deliverySchedule;
	}
}
