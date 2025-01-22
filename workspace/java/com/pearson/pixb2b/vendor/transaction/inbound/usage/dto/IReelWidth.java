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
package com.pearson.pixb2b.vendor.transaction.inbound.usage.dto;

/**
 * ReelWidth is a data transfer object to store the 
 * Usage details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class IReelWidth implements java.io.Serializable {	
	private static final long serialVersionUID = -1047440540387082141L;
	
	private IReelValue iReelValue = null;

	/**
	 * @return Returns the iReelValue.
	 */
	public IReelValue getIReelValue() {
		return iReelValue;
	}

	/**
	 * @param reelValue The iReelValue to set.
	 */
	public void setIReelValue(IReelValue reelValue) {
		iReelValue = reelValue;
	}

	

	

	
		
}
