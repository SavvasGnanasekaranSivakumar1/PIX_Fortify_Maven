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
 * Title		: 	PricePerUnitDS.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author				Date			Modifications.
 *------------------------------------------------------------------
 * 1.0		Ranu Sharma		20 Dec, 2011	Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.shipmentinstructions.dto;

import java.io.Serializable;


/**
 * @author Ranu.Sharma
 *
 */
public class PricePerUnitDS implements Serializable{

	
	private CurrencyDS currency= null;
	private PPValueDS ppuValue = null;

	/**
	 * Default constructor.
	 */
	public PricePerUnitDS() {
		super();
	}



	/**
	 * @param currency The currency to set.
	 */
	public void setCurrency(CurrencyDS currency) {
		this.currency = currency;
	}


	/**
	 * @return Returns the currency.
	 */
	public CurrencyDS getCurrency() {
		return currency;
	}


	
	/**
	 * @return Returns the ppuValue.
	 */
	public PPValueDS getPpuValue() {
		return ppuValue;
	}


	/**
	 * @param ppuValue The ppuValue to set.
	 */
	public void setPpuValue(PPValueDS ppuValue) {
		this.ppuValue = ppuValue;
	}
}
