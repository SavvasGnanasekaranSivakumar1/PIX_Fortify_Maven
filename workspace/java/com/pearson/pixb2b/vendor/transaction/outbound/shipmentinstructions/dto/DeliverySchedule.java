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
 * Title		: 	DeliverySchedule.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   13 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * DeliverySchedule is a helper data transfer object to store the 
 * Delivery Schedule details.
 * 
 * @author Ashish Agrawal
 */
public class DeliverySchedule implements java.io.Serializable {
	private static final long serialVersionUID = -7672252968063917999L;
	
	private Quantity quantity			= null;
	private String deliveryLineNumber 	= null;
	private ArrayList deliveryLegList	= null;
	private PriceDetailsDS priceDetails		= null;
	private ArrayList deliveryScheduleRef 	= null;
	private ArrayList additionalTextList	= null;
	
	
	
	
	/**
	 * @return the additionalTextList
	 */
	public ArrayList getAdditionalTextList() {
		return additionalTextList;
	}


	/**
	 * @param additionalTextList the additionalTextList to set
	 */
	public void setAdditionalTextList(ArrayList additionalTextList) {
		this.additionalTextList = additionalTextList;
	}


	/**
	 * Default constructor.
	 */
	public DeliverySchedule() {
		super();
	}
	

	/**
	 * @return the deliveryScheduleRef
	 */
	public ArrayList getDeliveryScheduleRef() {
		return deliveryScheduleRef;
	}
	/**
	 * @param deliveryScheduleRef the deliveryScheduleRef to set
	 */
	public void setDeliveryScheduleRef(ArrayList deliveryScheduleRef) {
		this.deliveryScheduleRef = deliveryScheduleRef;
	}


	/**
	 * @return the quantity
	 */
	public Quantity getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Quantity quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the deliveryLineNumber
	 */
	public String getDeliveryLineNumber() {
		return deliveryLineNumber;
	}
	/**
	 * @param deliveryLineNumber the deliveryLineNumber to set
	 */
	public void setDeliveryLineNumber(String deliveryLineNumber) {
		this.deliveryLineNumber = deliveryLineNumber;
	}
	/**
	 * @return the deliveryLegList
	 */
	public ArrayList getDeliveryLegList() {
		return deliveryLegList;
	}
	/**
	 * @param deliveryLegList the deliveryLegList to set
	 */
	public void setDeliveryLegList(ArrayList deliveryLegList) {
		this.deliveryLegList = deliveryLegList;
	}
	/**
	 * @return the priceDetails
	 */
	public PriceDetailsDS getPriceDetails() {
		return priceDetails;
	}
	/**
	 * @param priceDetails the priceDetails to set
	 */
	public void setPriceDetails(PriceDetailsDS priceDetails) {
		this.priceDetails = priceDetails;
	}
}
