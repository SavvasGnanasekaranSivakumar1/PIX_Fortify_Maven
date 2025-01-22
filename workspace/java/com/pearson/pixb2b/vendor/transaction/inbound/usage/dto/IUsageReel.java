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
public class IUsageReel implements java.io.Serializable {	
	private static final long serialVersionUID = 1674423494824123024L;

	private IReelConversionChar iReelChar = null;

	/**
	 * Default constructor.
	 */
	public IUsageReel() {
		super();
	}

	/**
	 * @return Returns the iReelChar.
	 */
	public IReelConversionChar getIReelChar() {
		return iReelChar;
	}

	/**
	 * @param reelChar The iReelChar to set.
	 */
	public void setIReelChar(IReelConversionChar reelChar) {
		iReelChar = reelChar;
	}

	
		
}
