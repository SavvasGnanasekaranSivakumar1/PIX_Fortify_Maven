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
 * Title		: 	TermsOfDelivery.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ranu Sharma   15 Dec, 2011	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Ranu.Sharma
 *
 */
public class TermsOfDelivery implements Serializable{
	
	private static final long serialVersionUID = 3426264320767011612L;
	
	private String freightPayableAt;
	//private ArrayList serviceLevelText;
	
	
	/**
	 * @return the freightPayableAt
	 */
	public String getFreightPayableAt() {
		return freightPayableAt;
	}
	/**
	 * @param freightPayableAt the freightPayableAt to set
	 */
	public void setFreightPayableAt(String freightPayableAt) {
		this.freightPayableAt = freightPayableAt;
	}
	/**
	 * @return the serviceLevelText
	 *//*
	public ArrayList getServiceLevelText() {
		return serviceLevelText;
	}
	*//**
	 * @param serviceLevelText the serviceLevelText to set
	 *//*
	public void setServiceLevelText(ArrayList serviceLevelText) {
		this.serviceLevelText = serviceLevelText;
	}*/
}