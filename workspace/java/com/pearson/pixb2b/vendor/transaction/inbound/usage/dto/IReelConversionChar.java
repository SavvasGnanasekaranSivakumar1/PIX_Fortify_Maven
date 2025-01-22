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
public class IReelConversionChar implements java.io.Serializable {	
	private static final long serialVersionUID = -3541062481325233471L;
	
	private IReelWidth iReelWidth = null;

	/**
	 * Default constructor.
	 */
	public IReelConversionChar() {
		super();
	}

	/**
	 * @return Returns the iReelWidth.
	 */
	public IReelWidth getIReelWidth() {
		return iReelWidth;
	}

	/**
	 * @param reelWidth The iReelWidth to set.
	 */
	public void setIReelWidth(IReelWidth reelWidth) {
		iReelWidth = reelWidth;
	}

	

	
		
}
