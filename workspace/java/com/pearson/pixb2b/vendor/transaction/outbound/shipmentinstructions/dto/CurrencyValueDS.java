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
 * Title		: 	CurrencyValueDS.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ranu Sharma		21 Dec, 2011	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

import java.io.Serializable;

/**
 * @author Ranu.Sharma
 *
 */
public class CurrencyValueDS implements Serializable{

	
	private static final long serialVersionUID = -8189132306293064462L;

	private String currType = null;
	private String currValue = null;
	
	
	/**
	 * Default constructor.
	 */
	public CurrencyValueDS() {
		super();
	}


	/**
	 * @return Returns the currType.
	 */
	public String getCurrType() {
		return currType;
	}


	/**
	 * @param currType The currType to set.
	 */
	public void setCurrType(String currType) {
		this.currType = currType;
	}


	/**
	 * @return Returns the currValue.
	 */
	public String getCurrValue() {
		return currValue;
	}


	/**
	 * @param currValue The currValue to set.
	 */
	public void setCurrValue(String currValue) {
		this.currValue = currValue;
	}
	
	
}
