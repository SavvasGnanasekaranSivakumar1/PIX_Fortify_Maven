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
 * Title		: 	POInformation.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Ashish Agrawal   13 Oct, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

/**
 * POInformation is a helper data transfer object to store the 
 * Purchase Order Information details.
 * 
 * @author Ashish Agrawal
 */
public class POInformation implements java.io.Serializable {
	private static final long serialVersionUID = -6129581974333428800L;
	
	private String poNumber= null;
	/**
	 * Default constructor.
	 */
	public POInformation() {
		super();
	}
	/**
	 * @return the poNumber
	 */
	public String getPoNumber() {
		return poNumber;
	}
	/**
	 * @param poNumber the poNumber to set
	 */
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
}
