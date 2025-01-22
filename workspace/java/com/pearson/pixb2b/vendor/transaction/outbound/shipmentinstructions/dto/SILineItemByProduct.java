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
 * Title		: 	SILineItemByProduct.java
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
 * SILineItemByProduct is a helper data transfer object to store the 
 * Shipping Instructions LineItem ByProduct details.
 * 
 * @author Ashish Agrawal
 */
public class SILineItemByProduct implements java.io.Serializable {
	private static final long serialVersionUID = -632986953314589408L;
	
	private String siLineItemStatusType = null;
	private String siLineItemNumber = null;
	private ArrayList shipToInformation = null;
	/**
	 * Default constructor.
	 */
	public SILineItemByProduct() {
		super();
	}
	/**
	 * @return the siLineItemStatusType
	 */
	public String getSiLineItemStatusType() {
		return siLineItemStatusType;
	}
	/**
	 * @param siLineItemStatusType the siLineItemStatusType to set
	 */
	public void setSiLineItemStatusType(String siLineItemStatusType) {
		this.siLineItemStatusType = siLineItemStatusType;
	}
	/**
	 * @return the siLineItemNumber
	 */
	public String getSiLineItemNumber() {
		return siLineItemNumber;
	}
	/**
	 * @param siLineItemNumber the siLineItemNumber to set
	 */
	public void setSiLineItemNumber(String siLineItemNumber) {
		this.siLineItemNumber = siLineItemNumber;
	}
	/**
	 * @return the shipToInformation
	 */
	public ArrayList getShipToInformation() {
		return shipToInformation;
	}
	/**
	 * @param shipToInformation the shipToInformation to set
	 */
	public void setShipToInformation(ArrayList shipToInformation) {
		this.shipToInformation = shipToInformation;
	}
}
