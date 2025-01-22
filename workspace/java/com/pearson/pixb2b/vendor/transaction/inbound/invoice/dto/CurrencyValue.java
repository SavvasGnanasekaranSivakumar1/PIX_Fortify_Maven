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
 * Title		: 	SCQuantity.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   26 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.invoice.dto;


/**
 * CurrencyValue is a data transfer object to store the 
 * Invoice details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class CurrencyValue implements java.io.Serializable {
	private static final long serialVersionUID = 1491300691022874817L;

	private String currType = null;
	private String currValue = null;
	
	
	/**
	 * Default constructor.
	 */
	public CurrencyValue() {
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