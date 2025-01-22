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
public class SummaryCurrency implements java.io.Serializable {
	private static final long serialVersionUID = 6388099813401618341L;
	
	private String scurrencyValue = null;
	private String scurrencyType = null;
	
	

	/**
	 * Default constructor.
	 */
	public SummaryCurrency() {
		super();
	}



	/**
	 * @return Returns the scurrencyType.
	 */
	public String getScurrencyType() {
		return scurrencyType;
	}



	/**
	 * @param scurrencyType The scurrencyType to set.
	 */
	public void setScurrencyType(String scurrencyType) {
		this.scurrencyType = scurrencyType;
	}



	/**
	 * @return Returns the scurrencyValue.
	 */
	public String getScurrencyValue() {
		return scurrencyValue;
	}



	/**
	 * @param scurrencyValue The scurrencyValue to set.
	 */
	public void setScurrencyValue(String scurrencyValue) {
		this.scurrencyValue = scurrencyValue;
	}
	
	
	
}