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
 * Title		: 	Currency.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam  236 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;


/**
 * Currency is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class Currency implements java.io.Serializable {
	private static final long serialVersionUID = -8661342991964373753L;
	
	private String currencyValue = null;
	private String currencyType = null;
	
	

	/**
	 * Default constructor.
	 */
	public Currency() {
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