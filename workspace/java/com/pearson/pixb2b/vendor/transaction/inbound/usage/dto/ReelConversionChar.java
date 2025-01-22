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
 * Title		: 	ReelConversionChar.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   25 November, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

/**
 * ReelConversionChar is a data transfer object to store the 
 * usage  details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ReelConversionChar implements java.io.Serializable {	
	private static final long serialVersionUID = 1440630093267550143L;
	
	private ReelWidth reelWidth = null;

	/**
	 * Default constructor.
	 */
	public ReelConversionChar() {
		super();
	}

	/**
	 * @return Returns the reelWidth.
	 */
	public ReelWidth getReelWidth() {
		return reelWidth;
	}

	/**
	 * @param reelWidth The reelWidth to set.
	 */
	public void setReelWidth(ReelWidth reelWidth) {
		this.reelWidth = reelWidth;
	}

	
		
}
