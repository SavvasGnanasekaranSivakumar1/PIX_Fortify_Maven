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
 * Title		: 	BasisWeight.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   25 November, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorychange.dto;

/**
 * BasisWeight is a data transfer object to store the 
 * Inventory change  details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class BasisWeight implements java.io.Serializable {	
	private static final long serialVersionUID = -9220196263348105045L;

	private DetailValue detailValue = null;

	/**
	 * Default constructor.
	 */
	public BasisWeight() {
		super();
	}

	/**
	 * @return Returns the detailValue.
	 */
	public DetailValue getDetailValue() {
		return detailValue;
	}

	/**
	 * @param detailValue The detailValue to set.
	 */
	public void setDetailValue(DetailValue detailValue) {
		this.detailValue = detailValue;
	}
		
}
