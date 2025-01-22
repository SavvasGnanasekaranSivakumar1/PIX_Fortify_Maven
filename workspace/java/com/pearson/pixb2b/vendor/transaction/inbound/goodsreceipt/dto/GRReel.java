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
 * Title		: 	GRReel.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   13 Jan,2010    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.goodsreceipt.dto;

/**
 * GRReel is a data transfer object to store the 
 * goodsreceipt and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class GRReel implements java.io.Serializable {	
	
	private static final long serialVersionUID = -5666579000974660953L;
	private ReelConversionChar reelChar = null;

	/**
	 * Default constructor.
	 */
	public GRReel() {
		super();
	}

	/**
	 * @return Returns the reelChar.
	 */
	public ReelConversionChar getReelChar() {
		return reelChar;
	}

	/**
	 * @param reelChar The reelChar to set.
	 */
	public void setReelChar(ReelConversionChar reelChar) {
		this.reelChar = reelChar;
	}
		
}
