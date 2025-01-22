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
 * Title		: 	DetailValue.java
 * Company 		: 	HCL Technologies
 *------------------------------------------------------------------
 * Ver.		Author			Date			Modifications.
 *------------------------------------------------------------------
 * 1.0	Abhilasha Nigam   25 November, 2009	    Initial version
 * -----------------------------------------------------------------
 */
package com.pearson.pixb2b.vendor.transaction.inbound.inventorystatus.dto;

/**
 * ReelValue is a data transfer object to store the 
 * Inventory status  details and transfer the same between classes.
 * 
 * @author Abhilasha Nigam
 */
public class ReelValue implements java.io.Serializable {	
	private static final long serialVersionUID = -1784487478365886552L;

	private String reelUOM = null;
	private String reelValue = null;
	
	/**
	 * Default constructor.
	 */
	public ReelValue() {
		super();
	}

	/**
	 * @return Returns the reelUOM.
	 */
	public String getReelUOM() {
		return reelUOM;
	}

	/**
	 * @param reelUOM The reelUOM to set.
	 */
	public void setReelUOM(String reelUOM) {
		this.reelUOM = reelUOM;
	}

	/**
	 * @return Returns the reelValue.
	 */
	public String getReelValue() {
		return reelValue;
	}

	/**
	 * @param reelValue The reelValue to set.
	 */
	public void setReelValue(String reelValue) {
		this.reelValue = reelValue;
	}
		
}
