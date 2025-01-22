package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;
/**
 * Copyright 2011 by Pearson,
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Pearson ("Confidential Information").
 * You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement
 * you entered into with Pearson.
 *
 * Title		: 	DeliveryLegDS.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ranu Sharma   15 Dec, 2011	    Initial version
 * -----------------------------------------------------------------
 */
import java.io.Serializable;
import java.util.ArrayList;

/**
 * DeliveryLegDS is a helper data transfer object to store the 
 * DeliveryLeg Information details.
 * 
 * @author Ranu Sharma
 */
public class DeliveryLegDS implements Serializable {

	private static final long serialVersionUID = 4632210949142609493L;

	private String delLegSeqNumber	= null;
	private DelOriginDS delOrigin		= null;
	private CarrierPartyDS carParty 	= null;
	private ArrayList otherParty = null;
	private ArrayList otherPartyNameAddress = null;
	
	
	/**
	 * @return the otherPartyNameAddress
	 */
	public ArrayList getOtherPartyNameAddress() {
		return otherPartyNameAddress;
	}
	/**
	 * @param otherPartyNameAddress the otherPartyNameAddress to set
	 */
	public void setOtherPartyNameAddress(ArrayList otherPartyNameAddress) {
		this.otherPartyNameAddress = otherPartyNameAddress;
	}
	
	/**
	 * @return the delOrigin
	 */
	public DelOriginDS getDelOrigin() {
		return delOrigin;
	}
	/**
	 * @param delOrigin the delOrigin to set
	 */
	public void setDelOrigin(DelOriginDS delOrigin) {
		this.delOrigin = delOrigin;
	}
	/**
	 * @return the carParty
	 */
	public CarrierPartyDS getCarParty() {
		return carParty;
	}
	/**
	 * @param carParty the carParty to set
	 */
	public void setCarParty(CarrierPartyDS carParty) {
		this.carParty = carParty;
	}
	
	/**
	 * @return the delLegSeqNumber
	 */
	public String getDelLegSeqNumber() {
		return delLegSeqNumber;
	}
	/**
	 * @param delLegSeqNumber the delLegSeqNumber to set
	 */
	public void setDelLegSeqNumber(String delLegSeqNumber) {
		this.delLegSeqNumber = delLegSeqNumber;
	}
	/**
	 * @return the otherParty
	 */
	public ArrayList getOtherParty() {
		return otherParty;
	}
	/**
	 * @param otherParty the otherParty to set
	 */
	public void setOtherParty(ArrayList otherParty) {
		this.otherParty = otherParty;
	}
}
