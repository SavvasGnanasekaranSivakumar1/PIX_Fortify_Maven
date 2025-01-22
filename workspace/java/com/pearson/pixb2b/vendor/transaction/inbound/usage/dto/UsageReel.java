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
 * Title		: 	ICReel.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   3 Dec, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

/**
 * UsageReel is a data transfer object to store the 
 * usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class UsageReel implements java.io.Serializable {	
	private static final long serialVersionUID = -1983295136466858004L;
	
	private ReelConversionChar reelChar = null;

	/**
	 * Default constructor.
	 */
	public UsageReel() {
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
