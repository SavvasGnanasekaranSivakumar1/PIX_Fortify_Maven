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
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

/**
 * BasisWeight is a data transfer object to store the 
 * Usage  details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class IBasisWeight implements java.io.Serializable {	
	private static final long serialVersionUID = -2014675987260508302L;

	private IDetailValue iDetailValue = null;

	/**
	 * Default constructor.
	 */
	public IBasisWeight() {
		super();
	}

	/**
	 * @return Returns the iDetailValue.
	 */
	public IDetailValue getIDetailValue() {
		return iDetailValue;
	}

	/**
	 * @param detailValue The iDetailValue to set.
	 */
	public void setIDetailValue(IDetailValue detailValue) {
		iDetailValue = detailValue;
	}

	
		
}
