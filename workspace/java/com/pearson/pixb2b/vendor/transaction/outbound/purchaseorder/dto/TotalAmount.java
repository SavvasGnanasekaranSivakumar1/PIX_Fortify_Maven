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
 * Title		: 	TotalAmount.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam  26 October, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.outbound.purchaseorder.dto;


/**
 * TotalAmount is a data transfer object to store the 
 * Purchase Order details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class TotalAmount implements java.io.Serializable {
	private static final long serialVersionUID = 789570393496791900L;
	
	private SummaryCurrency summCurrency = null;
	

	/**
	 * @return Returns the summCurrency.
	 */
	public SummaryCurrency getSummCurrency() {
		return summCurrency;
	}


	/**
	 * @param summCurrency The summCurrency to set.
	 */
	public void setSummCurrency(SummaryCurrency summCurrency) {
		this.summCurrency = summCurrency;
	}


	/**
	 * Default constructor.
	 */
	public TotalAmount() {
		super();
	}



	

	
	
}