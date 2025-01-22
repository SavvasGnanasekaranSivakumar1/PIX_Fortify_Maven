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
 * Title		: 	ReelWidth.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   25 November, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto;

/**
 * ReelWidth is a data transfer object to store the 
 * Usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ReelWidth implements java.io.Serializable {	
	private static final long serialVersionUID = 5653712895178272432L;

	private ReelValue reelValue = null;

	/**
	 * Default constructor.
	 */
	public ReelWidth() {
		super();
	}

	/**
	 * @return Returns the reelValue.
	 */
	public ReelValue getReelValue() {
		return reelValue;
	}

	/**
	 * @param reelValue The reelValue to set.
	 */
	public void setReelValue(ReelValue reelValue) {
		this.reelValue = reelValue;
	}

	
		
}
