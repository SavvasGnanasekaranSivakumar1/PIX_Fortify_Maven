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
 * Title		: 	OtherParty.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ranu Sharma		21 Dec, 2011	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Ranu.Sharma
 *
 */
public class OtherParty implements Serializable{
	private static final long serialVersionUID = 6542333380627517988L;

	private ArrayList otherPartyIdList					= null;
	private OtherPartyNameAddress otherPartyNameAddress = null;
	private String otherPartyType 						= null;
	/**
	 * @return the otherPartyIdList
	 */
	public ArrayList getOtherPartyIdList() {
		return otherPartyIdList;
	}
	/**
	 * @param otherPartyIdList the otherPartyIdList to set
	 */
	public void setOtherPartyIdList(ArrayList otherPartyIdList) {
		this.otherPartyIdList = otherPartyIdList;
	}
	/**
	 * @return the otherPartyNameAddress
	 */
	public OtherPartyNameAddress getOtherPartyNameAddress() {
		return otherPartyNameAddress;
	}
	/**
	 * @param otherPartyNameAddress the otherPartyNameAddress to set
	 */
	public void setOtherPartyNameAddress(OtherPartyNameAddress otherPartyNameAddress) {
		this.otherPartyNameAddress = otherPartyNameAddress;
	}
	/**
	 * @return the otherPartyType
	 */
	public String getOtherPartyType() {
		return otherPartyType;
	}
	/**
	 * @param otherPartyType the otherPartyType to set
	 */
	public void setOtherPartyType(String otherPartyType) {
		this.otherPartyType = otherPartyType;
	}
	
	
	
	
}
