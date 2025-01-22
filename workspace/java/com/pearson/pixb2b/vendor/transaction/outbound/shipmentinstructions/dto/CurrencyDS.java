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
 * Title		: 	CurrencyDS.java
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
public class CurrencyDS implements Serializable{

	private String currencyValue = null;
	private String currencyType = null;
	
	

	/**
	 * Default constructor.
	 */
	public CurrencyDS() {
		super();
	}

	/**
	 * @return Returns the currencyType.
	 */
	public String getCurrencyType() {
		return currencyType;
	}


	/**
	 * @param currencyType The currencyType to set.
	 */
	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}


	/**
	 * @return Returns the currencyValue.
	 */
	public String getCurrencyValue() {
		return currencyValue;
	}


	/**
	 * @param currencyValue The currencyValue to set.
	 */
	public void setCurrencyValue(String currencyValue) {
		this.currencyValue = currencyValue;
	}
}
